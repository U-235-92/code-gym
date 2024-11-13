package aq.gym.io.serialization.inheritance;

import lombok.ToString;

@ToString
public class C extends B {

	private static final long serialVersionUID = 1L;
	
	protected int c;
	protected int cc;
	
	public C() {
		System.out.println("C constructor");
	}

}
