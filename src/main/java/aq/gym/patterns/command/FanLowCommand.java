package aq.gym.patterns.command;

public class FanLowCommand extends FanCommand {

	public FanLowCommand(Fan fan) {
		super(fan);
	}
	
	@Override
	public void execute() {
		super.execute();
		fan.low();
	}
}
