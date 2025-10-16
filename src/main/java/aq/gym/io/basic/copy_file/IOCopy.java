package aq.gym.io.basic.copy_file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Path;

public class IOCopy {

	public static boolean copy(Path srcPath, Path targetPath) {
		try(FileInputStream srcStream = new FileInputStream(srcPath.toFile());
				FileOutputStream targetStream = new FileOutputStream(targetPath.toFile())) {
			byte[] buff = new byte[4];
			int read = 0;
			while((read = srcStream.read(buff)) != -1) {
				targetStream.write(buff, 0, read);
				targetStream.flush();
			}
			return true;
		} catch(Exception exc) {
			exc.printStackTrace();
		}
		return false;
	}
}
