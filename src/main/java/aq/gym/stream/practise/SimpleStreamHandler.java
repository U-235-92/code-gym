package aq.gym.stream.practise;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class SimpleStreamHandler {

	public List<String> threeHighCaloricDishNames(List<Dish> menu) {
		return menu.stream()
				.filter(dish -> dish.getCalories() > 300)
				.map(Dish::getName)
				.limit(3)
				.collect(Collectors.toList());
	}
	
	public List<Dish> vegeterianDishes(List<Dish> menu) {
		return menu.stream()
				.filter(Dish::isVegetarian)
				.toList();
	}
	
	public void printDistinctNumbers() {
		List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
		numbers.stream()
			.filter(i -> i % 2 == 0)
			.distinct()
			.forEach(System.out::println);
	}
	
	public void printDistinctLetters() {
		String[] words = {"Hello", "world"};
		Arrays.stream(words)
			.map(word -> word.split(""))
			.flatMap(Arrays::stream)
			.distinct()
			.forEach(System.out::print);
	}
	
	public boolean isVegeterianMenu(List<Dish> menu) {
		return menu.stream().anyMatch(Dish::isVegetarian);
	}
	
	public Optional<Dish> anyBegeterianDish(List<Dish> menu) {
		return menu.stream().filter(Dish::isVegetarian).findAny();
	}
	
	public Optional<Dish> mostCaloriesDish(List<Dish> menu) {
		Comparator<Dish> caloriesComparator = Comparator.comparing(Dish::getCalories);
		return menu.stream().collect(Collectors.maxBy(caloriesComparator));
	}
	
	public int totalCaloriesCollectorWay(List<Dish> menu) {
		return menu.stream().collect(Collectors.summingInt(Dish::getCalories));
	}
	
	public int totalCaloriesReduceWay(List<Dish> menu) {
//		return menu.stream().collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));
		return menu.stream().mapToInt(Dish::getCalories).reduce(Integer::sum).orElse(0);
	}
	
	public IntSummaryStatistics menuCaloriesStatistic(List<Dish> menu) {
		return menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
	}
	
	public String shortMenu(List<Dish> menu) {
		return menu.stream().map(Dish::getName).collect(Collectors.joining(", "));
	}
	
	public Map<Dish.Type, List<Dish>> dishesByType(List<Dish> menu) {
		return menu.stream().collect(Collectors.groupingBy(Dish::getType));
	}
	
	public Map<Dish.Type, List<Dish>> caloricDishesByType(List<Dish> menu) {
		return menu.stream()
				.collect(Collectors.groupingBy(Dish::getType, 
							Collectors.filtering(dish -> dish.getCalories() > 500, Collectors.toList())));
	}
}
