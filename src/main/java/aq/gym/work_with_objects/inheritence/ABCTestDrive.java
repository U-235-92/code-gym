package aq.gym.work_with_objects.inheritence;

public class ABCTestDrive {

	public static void main(String[] args) {
		System.out.println("+++++++++ Create A = new C() +++");
		B polyB = new C();
		polyB.abstractFromB();
		System.out.println("+++++++++ Create A = new A() +++");
		A a = new A();
		a.fromA();
		System.out.println("+++++++++ Create anonymous new B() { ... } +++");
		B b = new B(58) {
			
			@Override
			public void abstractFromB() {
				thisMethodDoesntDefineInB(); // But you're able to use that method inside declared methods of class; 
			}
			
			public void thisMethodDoesntDefineInB() {
				System.out.println("Trick it! number field of B = " + this.getNumber());
			}
		};
//		b.thisMethodDoesntDefineInB(); // ERR! You can't use defined method out of Anonymous class;
		b.abstractFromB();
	}
}
