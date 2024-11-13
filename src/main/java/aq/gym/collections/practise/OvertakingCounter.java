package aq.gym.collections.practise;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class OvertakingCounter {

	public static void main(String[] args) {
		Car ferrari = Car.instance(20, 180, "Ferrari");
		Car lamborghini = Car.instance(50, 180, "Lamborghini");
		Car bmw = Car.instance(100, 150, "BMW");
		Car honda = Car.instance(120, 120, "Honda");
		Car mercedes = Car.instance(200, 150, "Mercedes");
		Car reno = Car.instance(400, 100, "Reno");
		List<Car> cars = new ArrayList<>(List.of(bmw, lamborghini, ferrari, mercedes, reno, honda));
		Map<Car, Integer> overtakingMap = overtakingMap(cars);
		overtakingMap.entrySet()
			.forEach(entry -> System.out.println(entry.getKey().getMark() + " will do " + 
						MessageFormat.format("{0, choice, 0#{0} overtakings | 1#{0} overtaking | 2#{0} overtakings}", entry.getValue())));
	}

	private static Map<Car, Integer> overtakingMap(List<Car> cars) {
		cars.sort(Comparator.comparing(Car::getSpeed).reversed().thenComparing(Car::getPositionX));
		Map<Car, Integer> overtakingMap = new HashMap<>();
		for (int i = 0; i < cars.size(); i++) {
			Car current = cars.get(i);
			overtakingMap.putIfAbsent(current, 0);
			for (int j = i + 1; j < cars.size(); j++) {
				Car next = cars.get(j);
				if (current.getSpeed() > next.getSpeed()) {
					if (current.getPositionX() < next.getPositionX()) {
						overtakingMap.computeIfPresent(current, (car, overtakingCount) -> overtakingCount += 1);
					}
				}
			}
		}
		return overtakingMap;
	}

	@Getter
	@ToString
	@EqualsAndHashCode
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	@AllArgsConstructor(access = AccessLevel.PRIVATE, staticName = "instance")
	private static class Car {
		private int positionX;
		private int speed;
		private String mark;
	}

}
