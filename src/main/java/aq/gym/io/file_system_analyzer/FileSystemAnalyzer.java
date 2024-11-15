package aq.gym.io.file_system_analyzer;

import java.util.List;

public class FileSystemAnalyzer {

	private FileSystemAnalyzer() {}
	
	public String parent(String path) {
		return null;
	}
	
	public static String absolutePath(String file) {
		return null;
	}
	
	public static int nestedCatalogsNumber(String path) {
		return -1;
	}
	
	public static int nestedFilesNumber(String path) {
		return -1;
	}
	
	public static long size(String path) {
		return -1L;
	}
	
	public List<String> findByMask(String startPath, String mask) {
		return null;
	}
	
	public boolean transfer(String fromPath, String toPath) {
		return false;
	}
	
	public boolean delete(String path, String... flags) {
		return false;
	}
}
