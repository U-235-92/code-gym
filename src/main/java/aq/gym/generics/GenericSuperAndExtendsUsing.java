package aq.gym.generics;

import java.util.List;

public class GenericSuperAndExtendsUsing {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		List<Integer> intNumbers = List.of(1, 2, 3);
		List<Number> numbers = List.of(1L, 1, 1.0);
		List<Object> objects = List.of(new Object(), new Object());
		doSuper(numbers); // You're allow to pass List<Number> because it includes <? super X>  
		doSuper(objects); // You're allow to pass List<Object> because it includes <? super X>
//		doSuper(intNumbers); // ERR! You aren't allow to pass List<Integer> i.e. it doesn't include <? super X> 
		doExtends(intNumbers); // You can pass any List of [X] end [? extends X]  
		doExtends(numbers); // You can pass any List of [X] end [? extends X]
//		doExtends(objects); // ERR! You can pass any List of [X] end [? extends X]
	}

	@SuppressWarnings("unused")
//	You are able to pass any [super type of X and X] <? super X>  
	protected static void doSuper(List<? super Number> list) { 
//		list.add(new Object()); // ERR! You can't add Object or other super classes of type X <? super X>
		list.add(Integer.valueOf(1)); // You're able to add only Number or other subclasses of Number;
		list.add(Double.valueOf(5.8)); // You're able to add only Number or other subclasses of Number;
//		int a = list.getFirst(); // ERR! You can get ONLY super type of type X (in this case super type of Number -> Object)
		Object obj = list.getLast(); // You can get ONLY super type of type X (in this case super type of Number -> Object)
	}
	
	@SuppressWarnings("unused")
	protected static void doExtends(List<? extends Number> list) {
//		list.add(Integer.valueOf(1)); // ERR! You aren't able to add element in list i.e. it can hold any type extends Number
//		list.add(new Object()); // ERR! You aren't able to add super type of type X of list
		Number num = list.getFirst(); // You can get ONLY [X type] from list (in this case Number)
//		Integer wrong = list.getLast(); // ERR! You can get ONLY [X type] from list (in this case Number)
	}
}
