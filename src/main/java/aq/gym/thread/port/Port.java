package aq.gym.thread.port;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Port {

	private String name;
	private List<Wharf> wharfs;
	
	public void loadShip(Ship ship) {
		while(true) {
			for(Wharf wharf : wharfs) {
				if(!wharf.isProcessShip()) {
					Thread thread = new Thread(() -> wharf.load(ship));
					thread.start();
					return;
				}
			}
		}
	}
	
	public void unloadShip(Ship ship) {
		while(true) {
			for(Wharf wharf : wharfs) {
				if(!wharf.isProcessShip()) {
					Thread thread = new Thread(() -> wharf.unload(ship));
					thread.start();
					return;
				}
			}
		}
	}

	@Override
	public String toString() {
		return "Port [name=" + name + ", wharfs=" + wharfs + "]";
	}

}
