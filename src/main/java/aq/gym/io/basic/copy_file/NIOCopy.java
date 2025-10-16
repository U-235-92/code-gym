package aq.gym.io.basic.copy_file;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NIOCopy {

	public static boolean copy(Path src, Path target) {
		ByteBuffer buff = ByteBuffer.allocate(4);
		try(FileChannel srcChannel = FileChannel.open(src, StandardOpenOption.READ);
				FileChannel targetChannel = FileChannel.open(target, StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
			while(srcChannel.read(buff) != -1) {
				buff.flip();
				targetChannel.write(buff);
				buff.compact();
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void copyAsync(Path src, Path target) {
		ByteBuffer buff = ByteBuffer.allocate(4);
		ExecutorService executor = Executors.newCachedThreadPool((run) -> {
			Thread thread = new Thread(run);
			thread.setDaemon(false);
			return thread;
		});
		Set<StandardOpenOption> srcOpenOptions = Collections.singleton(StandardOpenOption.READ);
		Set<StandardOpenOption> targetOpenOptions = new HashSet<>(Arrays.asList(StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING));
		AsynchronousFileChannel srcChannel = null;
		AsynchronousFileChannel targetChannel = null;
		try {
//			srcChannel = AsynchronousFileChannel.open(src, srcOpenOptions, executor);
//			targetChannel = AsynchronousFileChannel.open(target, targetOpenOptions, executor);
			srcChannel = AsynchronousFileChannel.open(src, StandardOpenOption.READ);
			targetChannel = AsynchronousFileChannel.open(target, StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		copyAsync0(srcChannel, targetChannel, buff, executor, 0);
	}

	private static void copyAsync0(AsynchronousFileChannel srcChannel, AsynchronousFileChannel targetChannel,
			ByteBuffer buff, ExecutorService executor, int position) {
		srcChannel.read(buff, position, targetChannel, new CompletionHandler<Integer, AsynchronousFileChannel>() {
			
			@Override
			public void completed(Integer result, AsynchronousFileChannel channel) {
				buff.flip();
				
				targetChannel.write(buff, position, targetChannel, new CompletionHandler<Integer, AsynchronousFileChannel>() {

					@Override
					public void completed(Integer result, AsynchronousFileChannel channel) {
						if(result != -1) {
							buff.compact();
							copyAsync0(srcChannel, targetChannel, buff, executor, position + result);
						} 
					}

					@Override
					public void failed(Throwable exc, AsynchronousFileChannel channel) {
						closeChannels(srcChannel, targetChannel);
						exc.printStackTrace();
					}
				});
			}

			@Override
			public void failed(Throwable exc, AsynchronousFileChannel channel) {
				closeChannels(srcChannel, targetChannel);
				exc.printStackTrace();
			}
		});
	}
	
	private static void closeChannels(AsynchronousFileChannel...channels) {
		Arrays.stream(channels).forEach(channel -> {
			try {
				channel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
}
