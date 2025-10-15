package aq.gym.work_with_objects.inheritence;

public class ABCTestDrive {

	public static void main(String[] args) {
		System.out.println("+++++++++ Create A = new C() +++");
		A polyA = new C();
		System.out.println("+++++++++ Create A = new A() +++");
		A a = new A();
		B b = new B() {
			
			@Override
			public void abstractFromB() {
				thisMethodDoesntDefineInB(); // But you're able to use that method inside declared methods of class; 
			}
			
			public void thisMethodDoesntDefineInB() {
				System.out.println("Trick it!");
			}
		};
//		b.thisMethodDoesntDefineInB(); // ERR! You can't use defined method out of Anonymous class;
		b.abstractFromB();
	}
}
