package aq.gym.contests.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class TaskList {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		double[] data = Arrays.stream(reader.readLine().split("\s")).mapToDouble(Double::valueOf).toArray();
		double a = data[0], b = data[1], c = data[2];
		double v0 = data[3], v1 = data[4], v2 = data[5];
		double t1 = a/v0 + c/v1 + b/v2; 		
		double t2 = a/v0 + c/v1 + c/v2 + a/v2;	
		double t3 = a/v0 + a/v1 + b/v0 + b/v1;
		double t4 = b/v0 + c/v1 + a/v2;
		double t5 = b/v0 + c/v1 + c/v2 + b/v2;
		double t6 = b/v0 + b/v1 + a/v0 + a/v1;
		double t7 = a/v0 + c/v0 + c/v1 + a/v2;
		double t8 = b/v0 + c/v0 + c/v1 + b/v2;
		double t9 = a/v0 + c/v0 + b/v1 + a/v0 + a/v1;
		double t10 = a/v0 + c/v0 + b/v1 + b/v0 + c/v0 + a/v1;
		double t11 = b/v0 + c/v0 + a/v1 + b/v0 + b/v1;
		double t12 = b/v0 + c/v0 + a/v1 + a/v0 + c/v0 + b/v1;
		TreeSet<Double> set = new TreeSet<>(List.of(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12));
		System.out.println(set.first());
	}
}
//	H - Home
//	S - Shop
//	P - Post
//double t1 = a/v0 + c/v1 + b/v2;         // H→S→P→H 
//double t2 = b/v0 + c/v1 + a/v2;         // H→P→S→H
//double t3 = a/v0 + a/v1 + b/v0 + b/v1;  // H→S→H→P→H  
//double t4 = b/v0 + b/v1 + a/v0 + a/v1;  // H→P→H→S→H
//double t5 = a/v0 + c/v1 + c/v2 + a/v1;  // H→S→P→S→H 
//double t6 = b/v0 + c/v1 + c/v2 + b/v1;  // H→P→S→P→H 
//double t7 = a/v0 + c/v0 + c/v1 + a/v2;  // H→S→P→S→H 
//double t8 = b/v0 + c/v0 + c/v1 + b/v2;  // H→P→S→P→H

//double t1 = a/v0 + c/v1 + b/v2;
//double t2 = a/v0 + c/v1 + c/v2 + a/v2;
//double t3 = a/v0 + a/v1 + b/v0 + b/v1;
//double t4 = b/v0 + c/v1 + a/v2;
//double t5 = b/v0 + c/v1 + c/v2 + b/v2;
//double t6 = b/v0 + b/v1 + a/v0 + a/v1;
//double t7 = a/v0 + c/v0 + c/v1 + a/v2;
//double t8 = b/v0 + c/v0 + c/v1 + b/v2;
