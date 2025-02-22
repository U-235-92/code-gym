package aq.gym.patterns.factory;

public class AirbusFactory extends PlaneFactory {

	private static final String MANUFACTURER = "Airbus";
	
	@Override
	public Plane makePlane(Model model) {
		if(model == Model.A320) {
			return new Airbus(model, MANUFACTURER, 180);
		} else if(model == Model.A350) {
			return new Airbus(model, MANUFACTURER, 300);
		} else if(model == Model.A380) {
			return new Airbus(model, MANUFACTURER, 600);
		} else {
			throw new IllegalArgumentException("Wrong model " + model);
		}
	}
}
