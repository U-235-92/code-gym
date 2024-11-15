package aq.gym.io.file_system_analyzer;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileSystemAnalyzer {

	private FileSystemAnalyzer() {}
	
	public static String parent(String path) {
		String result = Paths.get(path).getParent().toString();
		return result;
	}
	
	public static String absolutePath(String file) {
		String result = Paths.get(file).toAbsolutePath().toString();
		return result;
	}
	
	public static int nestedCatalogsNumber(String path) {
		int[] nestedCatalogNumberWrap = new int[] {-1};
		try {
			Files.walkFileTree(Paths.get(path), new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
					nestedCatalogNumberWrap[0]++;
					return FileVisitResult.CONTINUE;
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return nestedCatalogNumberWrap[0];
	}
	
	public static int nestedFilesNumber(String path) {
		int[] nestedFileNumberWrap = new int[] {0};
		try {
			Files.walkFileTree(Paths.get(path), new SimpleFileVisitor<Path>() {
				
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					nestedFileNumberWrap[0]++;
					return FileVisitResult.CONTINUE;
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return nestedFileNumberWrap[0];
	}
	
	public static long size(String path) {
		try {
			long[] sizeWrap = new long[] {0L};
			Files.walkFileTree(Paths.get(path), new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					sizeWrap[0] += Files.size(file);
					return FileVisitResult.CONTINUE;
				}
			});
			return sizeWrap[0];
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1L;
	}
	
	public static List<String> findByMask(String startPath, String mask) {
		List<String> result = new ArrayList<String>();
		try {
			startPath = absolutePath(startPath);
			result = Files.walk(Paths.get(startPath))
				.filter(path -> path.toString().contains(mask))
				.map(Path::toString)
				.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void transfer(String fromPath, String toPath) {
		try {
			Files.move(Paths.get(fromPath), Paths.get(toPath), StandardCopyOption.ATOMIC_MOVE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void delete(String path) {
		try {
			Files.walkFileTree(Paths.get(path), new SimpleFileVisitor<Path>() {
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
			e.printStackTrace();
		}
	}
}
