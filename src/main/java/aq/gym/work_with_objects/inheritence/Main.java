package aq.gym.work_with_objects.inheritence;

public class Main {

	public static void main(String[] args) {
		Orange orange = new Orange();
		orange.printT("Hello");
		
		Point1D point1d = new Point2D();
		Point1D p1 = point1d.getPoint();
		Point2D point2d = new Point2D();
		Point2D p2 = point2d.getPoint();
		System.out.println(p1);
		System.out.println(p2);
	}

}
