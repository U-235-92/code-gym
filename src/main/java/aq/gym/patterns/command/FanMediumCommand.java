package aq.gym.patterns.command;

public class FanMediumCommand extends FanCommand {
	
	public FanMediumCommand(Fan fan) {
		super(fan);
	}
	
	@Override
	public void execute() {
		super.execute();
		fan.medium();
	}

}
