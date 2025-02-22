package aq.gym.patterns.command;

public class FanHighCommand extends FanCommand {

	public FanHighCommand(Fan fan) {
		super(fan);
	}

	@Override
	public void execute() {
		super.execute();
		fan.high();
	}
}
