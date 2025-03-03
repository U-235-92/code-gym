package aq.gym.contests.insomnia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Insomnia {

	public static void main(String[] args) {
		try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
			int insomnia = 0;
			String buildingDataLine = loadBuildingDataLine(bufferedReader);
			int[] buildingData = getBuildingData(buildingDataLine);
			Queue<String> windowLines = loadWindowLines(bufferedReader, buildingData);
			int height = getNumberOfWindowsInFlatInHeight(buildingData);
			int width = getNumberOfWindowsInFlatInWidth(buildingData);
			int floors = getNumberOfFloors(buildingData);
			insomnia = getInsomniaCount(windowLines, width, height, floors);
			System.out.println(insomnia);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String loadBuildingDataLine(BufferedReader bufferedReader) throws IOException {
		String line = bufferedReader.readLine();
		return line;
	}
	
	private static Queue<String> loadWindowLines(BufferedReader bufferedReader, int[] buildingData) throws IOException {
		int floors = getNumberOfFloors(buildingData);
		int flats = getNumberOfFlats(buildingData);
		int height = getNumberOfWindowsInFlatInHeight(buildingData);
		int width = getNumberOfWindowsInFlatInWidth(buildingData);
		int count = floors * height;
		Queue<String> dataLines = new LinkedList<String>();
		while(count > 0) {
			String windowLine = bufferedReader.readLine();
			if(windowLine.length() == flats * width) {				
				dataLines.add(windowLine);
				count--;
			}
		}
		return dataLines;
	}
	
	private static int[] getBuildingData(String dataLine) {
		String[] data = dataLine.split("\\s");
		return Arrays.stream(data).mapToInt(Integer::valueOf).toArray();
	}
	
	private static int getNumberOfFloors(int[] buildingData) {
		return buildingData[0];
	}
	
	private static int getNumberOfFlats(int[] buildingData) {
		return buildingData[1];
	}
	
	private static int getNumberOfWindowsInFlatInHeight(int[] buildingData) {
		return buildingData[2];
	}
	
	private static int getNumberOfWindowsInFlatInWidth(int[] buildingData) {
		return buildingData[3];
	}
	
	private static int getInsomniaCount(Queue<String> windowLines, int width, int height, int floors) {
		int insomnia = 0;
		String flatLine = "";
		int start = 0, end = width;
		while(floors > 0) {
			String floorLine = windowLines.remove();
			flatLine += floorLine.substring(start, end);
			String remain = floorLine.substring(end);
			windowLines.add(remain);
			if(flatLine.length() == height * width) {
				int n = Arrays.stream(flatLine.split("")).filter(s -> s.equals("X")).mapToInt(s -> 1).sum();
				double condition = (height * width) / 2.0;
				if(n >= condition) {
					insomnia++;
				}
				flatLine = "";
				floors--;
			}
		}
		return insomnia;
	}
}
