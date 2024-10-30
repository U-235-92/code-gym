package aq.gym.io.serialization.inheritance;

import java.io.Serializable;

import lombok.ToString;

@ToString
public class B extends A implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected int b;
	protected final int finalB;
	
	public B() {
		finalB = 100;
		System.out.println("B constructor");
	}
}
