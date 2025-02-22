package aq.gym.patterns.command;

import aq.gym.patterns.command.Fan.Speed;
import io.reactivex.rxjava3.annotations.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class FanCommand implements Command {

	@NonNull
	protected Fan fan;
	private Fan.Speed previousSpeed;
	
	@Override
	public void undo() {
		if(previousSpeed == Speed.LOW) {
			fan.low();
		} else if(previousSpeed == Speed.MEDIUM) {
			fan.medium();
		} else if(previousSpeed == Speed.HIGH) {
			fan.high();
		} else if(previousSpeed == Speed.OFF) {
			fan.off();
		}
	}
	
	@Override
	public void execute() {
		previousSpeed = fan.getSpeed();
	}
}
