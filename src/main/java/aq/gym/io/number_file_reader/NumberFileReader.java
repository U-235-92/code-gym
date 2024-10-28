package aq.gym.io.number_file_reader;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class NumberFileReader {

	public List<Double> readNumbersFrom(String file) {
		return readNumbers(file);
	}
	
	public double sumNumbersFrom(String file) {
		return readNumbers(file).stream().mapToDouble(Double::doubleValue).reduce(0.0, Double::sum);
	}
	
	public double averageNumbersFrom(String file) {
		return readNumbers(file).stream().mapToDouble(Double::doubleValue).summaryStatistics().getAverage();
	}
	
	private List<Double> readNumbers(String path) {
		File file = new File(path);
		String line = "";
		if(file.exists()) {
			try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
				byte[] bytes = new byte[256];
				while((bis.read(bytes)) != -1) {
					line += new String(bytes);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return Pattern.compile("\\s+")
				.splitAsStream(line)
				.filter(str -> str.matches("[\\d]+\\.[\\d]+"))
				.map(Double::valueOf)
				.collect(Collectors.toList());
	}
}
