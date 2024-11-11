package aq.gym.thread.bus_stop;

import java.util.concurrent.TimeUnit;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Bus {

	private static int idCounter;
	public int id;
	@NonNull
	private int number;
	@NonNull
	private String name;
	
	{
		idCounter++;
		id = idCounter;
	}
	
	public void tryStop(BusStop busStop) {
		if(busStop.isAbleReceiveBus()) {
			System.out.printf("The bus %s, number: %d, id: %d stopped on a bus stop %s%n", name, number, id, busStop.getName());
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				busStop.release();
			}
			System.out.printf("The bus %s, number: %d, id: %d leave a bus stop %s%n", name, number, id, busStop.getName());
		} else {
			System.out.printf("The bus %s, number: %d, id: %d passed a bus stop %s%n", name, number, id, busStop.getName());
		}
	}
}
