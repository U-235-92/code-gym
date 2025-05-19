package aq.gym.work_with_objects.inheritence;

public class ChildConstructor extends ParentConstructor {

	//Won't compile if parent class hasn't got [no args] constructor
	//By default if you don't call super() then it will call [no args] constructor from super class 
	public ChildConstructor(String val) {} 
}
