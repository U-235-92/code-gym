package aq.gym.collections.collections_things;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class TrySaveDuplicateInSet {

	public static void main(String[] args) {
		Color red = new Color(1, "RED");
		Color green = new Color(2, "GREEN");
		Set<Color> colors = new LinkedHashSet<>(Set.of(red, green));
		System.out.println(colors);
		green.id = 1; green.color = "RED"; // Attempt of changing object will violate logic of set storage
		System.out.println(colors);
		Color blue = new Color(3, "BLUE");
		colors.add(blue); 
		System.out.println(colors);
		blue.id = 1; blue.color = "RED";
		System.out.println(colors);
	}

	private static class Color {
		
		private int id;
		private String color;
		
		Color(int id, String color) {
			this.id = id;
			this.color = color;
		}
		
		@Override
		public String toString() {
			return String.format("%d %s", id, color);
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(id, color);
		}
		
		@Override
		public boolean equals(Object obj) {
			if(obj == null || obj.getClass() != getClass()) {
				return false;
			}
			if(obj == this) {
				return true;
			}
			Color color = (Color) obj;
			return this.id == color.id && this.color.equals(color.color);
		}
	}
}
