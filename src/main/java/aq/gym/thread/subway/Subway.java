package aq.gym.thread.subway;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Subway {

	private String name;
	private List<Route> routes = new ArrayList<Route>();
	
	public Subway(String name, List<Route> routes) {
		this.name = name;
		this.routes.addAll(routes);
	}

	public void use(Train train) {
		while(true) {			
			for(Route route : routes) {
				if(route.isAblePassTrain()) {
					try {
						System.out.printf("The train [%s] is going through a subway %s, route %s%n",
								train.getName(), name, route.getName());
						TimeUnit.SECONDS.sleep(2);
						System.out.printf("The train [%s] left a subway %s, route %s%n", 
								train.getName(), name, route.getName());
						TimeUnit.MILLISECONDS.sleep(100);
						route.setFree();
						return;
					} catch (InterruptedException e) {
						e.printStackTrace();
					} 
				}
			}
		}
	}
	
	public String getName() {
		return name;
	}

	public List<Route> getRoutes() {
		return List.copyOf(routes);
	}
}
