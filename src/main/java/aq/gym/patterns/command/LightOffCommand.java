package aq.gym.patterns.command;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LightOffCommand implements Command {

	private Light light;
	
	@Override
	public void execute() {
		light.off();
	}

	@Override
	public void undo() {
		light.on();
	}
}
