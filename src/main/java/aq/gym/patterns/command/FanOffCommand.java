package aq.gym.patterns.command;

public class FanOffCommand extends FanCommand {
	
	public FanOffCommand(Fan fan) {
		super(fan);
	}

	@Override
	public void execute() {
		super.execute();
		fan.off();
	}
}
