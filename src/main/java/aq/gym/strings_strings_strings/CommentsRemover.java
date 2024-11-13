package aq.gym.strings_strings_strings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommentsRemover {

	public static void main(String[] args) throws IOException {
		String code = Files.lines(Paths.get("src/main/java/aq/gym/strings_strings_strings/CommentsRemover.java"))
				.reduce((s1, s2) -> String.join("\n", s1, s2))
				.get();
		String codeWithNoComments = remove(code);
		System.out.println(codeWithNoComments);
	}

//	first single comment
	private static String remove(String code) {
		/* int a = 0; */
		Function<String, String> singleLineCommentMapper = (line) -> {
			Pattern commentPattern = Pattern.compile("\\s*//.*"); //Just for // fun
			Matcher commentMatcher = commentPattern.matcher(line);
			if(commentMatcher.find()) {
				if(line.trim().startsWith("//")) {
					return "";
				} else {
					commentPattern = Pattern.compile("\".*//.*\"");
					commentMatcher = commentPattern.matcher(line);
					if(commentMatcher.find()) {
						for(int i = commentMatcher.end(); i < line.length() - 2; i++) {
							if(line.charAt(i + 1) == '/' && line.charAt(i + 2) == '/') {
								return line.substring(0, i);
							}
						}
						return line;
					} else {						
						int beginIndexOfComment = commentMatcher.start();
						return line.substring(0, beginIndexOfComment);
					}
				}
			} else {
				return line;
			}
		};		
		String result = Arrays.stream(code.split("\n"))
				.map(singleLineCommentMapper)
				.filter(line -> !line.equals(""))
				.reduce((s1, s2) -> String.join(" ", s1, s2))
				.get();
		result = Arrays.stream(result.split("/\\*.*?\\*/"))
				.reduce((s1, s2) -> String.join(" ", s1, s2)).get();
		//second single comment
		return result;
	}
	
	/*
	 * Multiline comment line 1
	 * Multiline comment line 2
	 */
	@SuppressWarnings("unused")
	private static void thing() {
		System.out.println("Thing!");
	}
	/**
	 * @author abc
	 * 
	 * It has to delete.
	 */
	
}
