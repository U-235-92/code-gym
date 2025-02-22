package aq.gym.patterns.command;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LightOnCommand implements Command {

	private Light light;

	@Override
	public void execute() {
		light.on();
	}

	@Override
	public void undo() {
		light.off();
	}
}
