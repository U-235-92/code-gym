package aq.gym.io.file_system_analyzer;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		if(args.length > 0) {
			String fileToAnalize = "src/main/java/aq/gym/io/file_system_analyzer";
			System.out.println("Source path: " + fileToAnalize);
			System.out.println("Absolute path: " + FileSystemAnalyzer.absolutePath(fileToAnalize)); 
			System.out.println("The files which contain .class: ");
			List<String> patternFiles = FileSystemAnalyzer.findByMask(fileToAnalize, ".class");
			if(patternFiles.size() > 0)
				patternFiles.forEach(path -> System.out.println(path));
			else
				System.out.println("No files");
			System.out.println("Number of nested catalogs: " + FileSystemAnalyzer.nestedCatalogsNumber(fileToAnalize));
			System.out.println("Number of nested files: " + FileSystemAnalyzer.nestedFilesNumber(fileToAnalize));
			System.out.println("Parent direcory: " + FileSystemAnalyzer.parent(fileToAnalize));
			System.out.println("Size of path: " + FileSystemAnalyzer.size(fileToAnalize) + " bytes");
		} else {
			System.out.println("Specify a path");
		}
	}

}
