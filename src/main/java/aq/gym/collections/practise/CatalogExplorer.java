package aq.gym.collections.practise;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CatalogExplorer {

	public static void main(String[] args) {
		try {
			System.out.println("EXPLORE BY WALK:");
			List<String> paths = exploreByWalk(new File("src/main/java/aq/gym/algorithms_and_structures").toPath());
			paths.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("EXPLORE BY RECURSION:");
		List<String> paths = new LinkedList<String>(); 
		exploreByRecursion(new File("src/main/java/aq/gym/algorithms_and_structures"), paths);
		paths.forEach(System.out::println);
	}
	
	private static List<String> exploreByWalk(Path path) throws IOException {
		return Files.walk(path).map(p -> p.toString()).collect(Collectors.toList());
	}
	
	private static void exploreByRecursion(File file, List<String> files) {
		for(File sub : file.listFiles()) {			
			files.add(sub.toString());
			if(sub.isDirectory()) {
				exploreByRecursion(sub, files);
			} 
		}
	}
}
