package aq.gym.patterns.adapter;

public class DuckAdapter extends Duck {

	private Cat cat;
	
	public DuckAdapter(Cat cat) {
		this.cat = cat;
	}
	
	@Override
	public void makeNoise() {
		cat.mew();
	}
}
