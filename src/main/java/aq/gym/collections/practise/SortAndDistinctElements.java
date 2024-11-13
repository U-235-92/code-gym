package aq.gym.collections.practise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

public class SortAndDistinctElements {

	public static void main(String[] args) {
		List<Element> elements = new ArrayList<>(List.of(
				Element.getInstance("A", 12345),
				Element.getInstance("A", 12345),
				Element.getInstance("B", 54321), 
				Element.getInstance("C", 6789), 
				Element.getInstance("D", 9876), 
				Element.getInstance("A", 12345),
				Element.getInstance("E", 5082)));
		sort(elements);
		System.out.println("Before:");
		elements.stream().forEach(System.out::println);
		System.out.println("After:");
		distinct(elements).forEach(System.out::println);
	}
	
	private static void sort(List<Element> elements) {
		elements.sort(Comparator.comparing(Element::getCode));
	}
	
	private static Set<Element> distinct(List<Element> elements) {
		return new LinkedHashSet<SortAndDistinctElements.Element>(elements);
	}

	@Getter
	@ToString
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	private static class Element {
		private String name;
		private int code;
		
		static Element getInstance(String name, int code) {
			return new Element(name, code);
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(name);
		}
		
		@Override
		public boolean equals(Object obj) {
			if(obj == null || obj.getClass() != Element.class)
				return false;
			if(obj == this)
				return true;
			Element element = (Element) obj;
			return element.name.equals(name);				
		}
	}
}
