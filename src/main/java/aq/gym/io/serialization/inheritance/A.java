package aq.gym.io.serialization.inheritance;

import lombok.ToString;

@ToString
public class A {

	protected int a;
	protected static int stat = 999;
	
	public A() {
		System.out.println("A constructor");
	}
}
