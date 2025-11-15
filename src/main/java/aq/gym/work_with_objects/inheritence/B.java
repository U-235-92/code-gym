package aq.gym.work_with_objects.inheritence;

public abstract class B extends A {

	static {
		System.out.println("Static bloc from B");
	}
	
	{
		System.out.println("No-static block from B");
	}
	
	private int number;
	
	public B() {
		System.out.println("Constructor from B");
	}
	
	public B(int number) {
		this();
		this.number = number;
	}
	
	@Override
	public void fromA() { // You're able to enhance visibility of method (protected -> public) 
						  // But! You aren't able to shrink visibility of method (public -> protected -> private)
		super.fromA();
	}
	
//	@Override // ERR! You aren't able to shrink method visibility!
//	protected void youArentAbleShrinkMyVisibility() {
//		// TODO Auto-generated method stub
//		super.youArentAbleShrinkMyVisibility();
//	}
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	public abstract void abstractFromB();
}
