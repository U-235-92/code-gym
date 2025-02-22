package aq.gym.patterns.factory;

public class BoeingFactory extends PlaneFactory {

private static final String MANUFACTURER = "Boeing";
	
	@Override
	public Plane makePlane(Model model) {
		if(model == Model.B737) {
			return new Boeing(model, MANUFACTURER, 150);
		} else if(model == Model.B777) {
			return new Boeing(model, MANUFACTURER, 350);
		} else if(model == Model.B787) {
			return new Boeing(model, MANUFACTURER, 330);
		} else {
			throw new IllegalArgumentException("Wrong model " + model);
		}
	}
}
