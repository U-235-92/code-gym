package aq.gym.collections.practise;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FileReader {

	public static void main(String[] args) {
		File textFile = new File("src/main/java/aq/gym/collections/practise/file_reader_text.txt");
		File textFileReverse = new File("src/main/java/aq/gym/collections/practise/file_reader_text_reverse.txt");
		try {
			List<String> lines = getFileLines(textFile);
			putLinesDescOrder(textFileReverse, lines);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static List<String> getFileLines(File file) throws IOException {
		return Files.lines(file.toPath()).collect(Collectors.toList());
	}
	
	private static void putLinesDescOrder(File file, List<String> lines) throws IOException {
		Collections.reverse(lines);
		Files.write(file.toPath(), lines, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
	}
}
