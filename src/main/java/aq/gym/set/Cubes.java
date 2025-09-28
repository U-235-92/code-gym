package aq.gym.set;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Cubes {

//	https://coderun.yandex.ru/problem/cubes
	public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] cubesData = reader.readLine().split("\s");
        int n = Integer.valueOf(cubesData[0]);
        int m = Integer.valueOf(cubesData[1]);
        TreeSet<Integer> aColors = readColors(reader, n);
        TreeSet<Integer> bColors = readColors(reader, m);	
        calculate2(aColors, bColors);
        reader.close();
    }
	
	private static TreeSet<Integer> readColors(BufferedReader reader, int colorNumber) throws NumberFormatException, IOException {
		TreeSet<Integer> colors = new TreeSet<>();
		for(int i = 0; i < colorNumber; i++) {
			colors.add(Integer.valueOf(reader.readLine()));
		}
		return colors;
	}
	
	@SuppressWarnings("unused")
//	LTE Error!
	private static void calculate(List<Integer> aColors, List<Integer> bColors) {
		Collections.sort(aColors);
		Collections.sort(bColors);
		List<Integer> intersection = new ArrayList<>();
		List<Integer> aRemainColors = new ArrayList<>();
		List<Integer> bRemainColors = new ArrayList<>();
		int i = 0, j = 0;
		while(i < aColors.size() && j < bColors.size()) {
			int a = aColors.get(i);
			int b = bColors.get(j);
			if(a == b) {
				intersection.add(a);
				i++; j++;
			} else if(a < b) {
				aRemainColors.add(a);
				i++;
			} else {
				bRemainColors.add(b);
				j++;
			}
		}
		while(i < aColors.size()) {
			aRemainColors.add(aColors.get(i));
			i++;
		}
		while(j < bColors.size()) {
			bRemainColors.add(bColors.get(j));
			j++;
		}
		System.out.println(intersection.size());
		intersection.forEach(c -> System.out.print(c + " ")); System.out.println();
		System.out.println(aRemainColors.size());
		aRemainColors.forEach(c -> System.out.print(c + " ")); System.out.println();
		System.out.println(bRemainColors.size());
		bRemainColors.forEach(c -> System.out.print(c + " ")); 
	}
	
	private static void calculate2(Set<Integer> aColors, Set<Integer> bColors) {
		TreeSet<Integer> intersection = new TreeSet<>(aColors);
		intersection.retainAll(bColors);
		aColors.removeAll(intersection);
		bColors.removeAll(intersection);
		System.out.println(intersection.size());
		System.out.println(intersection.stream().map(String::valueOf).collect(Collectors.joining("\s")));
		System.out.println(aColors.size());
		System.out.println(aColors.stream().map(String::valueOf).collect(Collectors.joining("\s"))); 
		System.out.println(bColors.size());
		System.out.println(bColors.stream().map(String::valueOf).collect(Collectors.joining("\s")));  
	}
}
