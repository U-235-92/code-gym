package aq.gym.contests.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class NumberIntersectionOfTwoArrays {

//	https://coderun.yandex.ru/problem/intersection-sets/description
	public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> list1 = Arrays.stream(reader.readLine().split("\\s"))
        		.map(Integer::valueOf)
        		.collect(Collectors.toList());
        List<Integer> list2 = Arrays.stream(reader.readLine().split("\\s"))
        		.map(Integer::valueOf)
        		.collect(Collectors.toList());
        List<Integer> intersectionList = NumberIntersectionOfTwoArrays.getIntersection(list1, list2);
        intersectionList.forEach(num -> System.out.print(num + " "));
        reader.close();
    }

	private static List<Integer> getIntersection(List<Integer> list1, List<Integer> list2) {
        List<Integer> result = new ArrayList<>();
        Collections.sort(list1);
        Collections.sort(list2);
        int i = 0, j = 0;
        while (i < list1.size() && j < list2.size()) {
            int a = list1.get(i);
            int b = list2.get(j);
            if (a == b) {
                result.add(a);
                i++;
                j++;
            } else if (a < b) {
                i++;
            } else {
                j++;
            }
        }
        return result;
    }
}
