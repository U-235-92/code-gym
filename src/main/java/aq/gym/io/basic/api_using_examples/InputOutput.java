package aq.gym.io.basic.api_using_examples;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.FileChannel;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class InputOutput {

	public void printFileAsIO(Path path) {
		try(FileInputStream fis = new FileInputStream(path.toFile());
				InputStreamReader isr = new InputStreamReader(fis);
				BufferedReader br = new BufferedReader(isr)) {
			String line = null;
			StringBuilder sb = new StringBuilder();
			while((line = br.readLine()) != null) {
				sb.append(line);
			}
			System.out.println("IO RESULT:");
			System.out.println(sb);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void printFileAsNIO(Path path) {
		try(FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ)) {
			ByteBuffer byteBuffer = ByteBuffer.allocate(4);
			StringBuilder sb = new StringBuilder();
			while(fileChannel.read(byteBuffer) > 0) {
				byteBuffer.flip();
				sb.append(new String(byteBuffer.array(), 0, byteBuffer.limit()));
				byteBuffer.clear();
			}
			System.out.println("NIO RESULT:");
			System.out.println(sb);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void printFileAsAsynchFutureNIO(Path path) {
		try(AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ)) {
			int buffSize = 256;
			ByteBuffer byteBuffer = ByteBuffer.allocate((int) (asynchronousFileChannel.size() > buffSize ? asynchronousFileChannel.size() : buffSize));
			Future<Integer> future = asynchronousFileChannel.read(byteBuffer, 0);
			future.get();
			StringBuilder sb = new StringBuilder();
			sb.append(new String(byteBuffer.array()));
			System.out.println("ASYNCHRONOUS FUTURE:");
			System.out.println(sb);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void printFileAsAsynchCompletionNIO(Path path) {
		try(AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ)) {
			int buffSize = 256;
			String str = "TEST";
			ByteBuffer byteBuffer = ByteBuffer.allocate((int) (asynchronousFileChannel.size() > buffSize  ? asynchronousFileChannel.size() : buffSize));
			asynchronousFileChannel.read(byteBuffer, 0, str, new CompletionHandler<Integer, String>() {

				@Override
				public void completed(Integer result, String attachment) {
					System.out.println("STR: " + str + " ATT: " + attachment);
					System.out.println("ASYNCHRONOUS COMPLETION:");
					System.out.println(new String(byteBuffer.array()));
				}

				@Override
				public void failed(Throwable exc, String attachment) {
					exc.printStackTrace();
				}
				
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeStringAsIO(Path path, String string) {
		try(FileOutputStream fos = new FileOutputStream(path.toFile());
				OutputStreamWriter osw = new OutputStreamWriter(fos);
				BufferedWriter bw = new BufferedWriter(osw)) {
			bw.write(string);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeStringAsNIO(Path path, String string) {
		mkfile(path);
		try(FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ, StandardOpenOption.WRITE)) {
			ByteBuffer buff = ByteBuffer.allocate(string.length());
			buff.put(string.getBytes());
			buff.flip();
			fileChannel.write(buff);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeStringAsAsynchFutureNIO(Path path, String string) {
		deleteFile(path);
		mkfile(path);
		try(AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ, StandardOpenOption.WRITE)) {
			ByteBuffer buff =  ByteBuffer.allocate(string.length()); 
			buff.put(string.getBytes());
			buff.flip();
			asynchronousFileChannel.write(buff, 0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void writeStringAsAsynchCompletionNIO(Path path, String string) {
		deleteFile(path);
		mkfile(path);
		try(AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ, StandardOpenOption.WRITE)) {
			ByteBuffer buff = ByteBuffer.allocate(string.length());
			buff.put(string.getBytes());
			buff.flip();
			asynchronousFileChannel.write(buff, 0, null, new CompletionHandler<Integer, Object>() {

				@Override
				public void completed(Integer result, Object attachment) {
					
				}

				@Override
				public void failed(Throwable exc, Object attachment) {
					exc.printStackTrace();
				}
				
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void mkfile(Path path) {
		if(Files.notExists(path)) {
			try {
				Files.createFile(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void makeAnOldFile() {
		File file = new File("src/main/java/aq/gym/io/basic/api_using_examples/old_stuff.txt");
		if(!file.exists()) {
			try {
				file.createNewFile();
				file.setLastModified(0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void getFileMetaData(Path path) {
		try {
			BasicFileAttributes basicFileAttributes = Files.readAttributes(path, BasicFileAttributes.class);
			System.out.println("created: " + LocalDateTime.ofInstant(basicFileAttributes.creationTime().toInstant(), ZoneOffset.systemDefault()).format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).localizedBy(Locale.getDefault())) + ", size: " + basicFileAttributes.size() + " bytes");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void printDirectoryContentByWalk(Path path) {
		try (Stream<Path> pathStream = Files.walk(path)){
			pathStream.forEach(p -> System.out.println(p));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void printDirectoryContentByDirectoryStream(Path path) {
		System.out.println(path);
		try (DirectoryStream<Path> entries = Files.newDirectoryStream(path)){
			for(Path entry : entries) {
				if(Files.isDirectory(entry)) {
					printDirectoryContentByDirectoryStream(entry);
				} else {					
					System.out.println(entry);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void printDirectoryContentByFileVisitor(Path path) {
		try {
			Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					System.out.println(file);
					return FileVisitResult.CONTINUE;
				}
				
				@Override
				public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
					System.out.println(dir);
					return FileVisitResult.CONTINUE;
				}
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void printDirectoryContentByRecursion(Path path) {
		File file = path.toFile();
		printDirectoryContentByRecursion0(file);
	}
	
	private void printDirectoryContentByRecursion0(File file) {
		for(File f : file.listFiles()) {
			if(f.isDirectory()) {
				System.out.println(f);
				printDirectoryContentByRecursion0(f);
			} else {
				System.out.println(f);
			}
		}
	}

	public void zip(Path source, Path destination) {
		try(FileOutputStream fos = new FileOutputStream(destination.toFile());
				ZipOutputStream zos = new ZipOutputStream(fos)) {
			zip0(zos, source.toFile());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void zip0(ZipOutputStream zos, File source) throws IOException {
		for(File file : source.listFiles()) {
			if(file.isDirectory()) {
				String directoryName = file.getPath().endsWith("/") ? file.getPath() : file.getPath() + "/";
				ZipEntry zipEntry = new ZipEntry(directoryName);
				zos.putNextEntry(zipEntry);
				zip0(zos, file);
				zos.closeEntry();
			} else {
				ZipEntry zipEntry = new ZipEntry(file.getPath());
				zos.putNextEntry(zipEntry);
				try(FileInputStream fis = new FileInputStream(file);
						BufferedInputStream bis = new BufferedInputStream(fis)) {
					byte[] bytes = new byte[256];
					while(bis.read(bytes) != -1) {
						zos.write(bytes);
					}
				} 
				zos.closeEntry();
			}
		}
	}

	public void unzip(Path source, Path destination) {
		try(FileInputStream fis = new FileInputStream(source.toFile());
				BufferedInputStream bis = new BufferedInputStream(fis);
				ZipInputStream zis = new ZipInputStream(bis)) {
			ZipEntry zipEntry = null;
			while((zipEntry = zis.getNextEntry()) != null) {
				if(zipEntry.isDirectory()) {
					String zipEntryName = zipEntry.getName();
					Path destiantionDir = destination.resolve(Path.of(zipEntryName));
					if(Files.notExists(destiantionDir)) {
						Files.createDirectories(destiantionDir);
					}
				} else {
					Path file = destination.resolve(Path.of(zipEntry.getName()));
					if(Files.notExists(file)) {
						Files.createFile(file);
						try(FileOutputStream fos = new FileOutputStream(file.toFile())) {
							byte[] bytes = new byte[256];
							while(zis.read(bytes) != -1) {
								fos.write(bytes);
							}
						}
					}
				}
				zis.closeEntry();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void makeDirectories(Path root) {
		if(Files.notExists(root)) {				
			makeRootDir(root);
			makeSubDirs(root);
		} else {
			makeSubDirs(root);
		}		
	}
	
	private void makeRootDir(Path root) {
		try {
			if(Files.notExists(root)) {				
				Files.createDirectory(root);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	private void makeSubDirs(Path root) {
		try {
			Path sub1 = root.resolve(Path.of("sub1"));
			if(Files.notExists(sub1)) {					
				Files.createDirectory(sub1);
				for(int i = 0; i < 5; i++) {
					Path file = sub1.resolve("file" + i + ".txt");
					if(Files.notExists(file)) {
						Path fPath = Files.createFile(file);
						Files.writeString(fPath, "just for fun" + i);
					}
				}
			}
			Path sub2 = sub1.resolveSibling("sub2");
			if(Files.notExists(sub2)) {					
				Files.createDirectory(sub2);
			}
			Path sub3 = sub1.resolveSibling("sub3");
			if(Files.notExists(sub3)) {					
				Files.createDirectory(sub3);
				for(int i = 0; i < 2; i++) {
					Path file = sub3.resolve("file" + i + ".txt");
					if(Files.notExists(file)) {
						Files.createFile(file);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public void deleteDirectory(Path path) {
		try {
			Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					Files.delete(file);
					return FileVisitResult.CONTINUE;
				}
				
				@Override
				public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
					Files.delete(dir);
					return FileVisitResult.CONTINUE;
				}
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteFile(Path path) {
		try {
			Files.deleteIfExists(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
