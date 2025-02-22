package aq.gym.patterns.command;

public class Main {

	public static void main(String[] args) {
		RemoteControl remoteControl = new RemoteControl();
		lightAdjust(remoteControl); 
		fanAdjust(remoteControl);
		remoteControl.on(0);
		remoteControl.off(0);
		remoteControl.undo();
		remoteControl.on(1);
		remoteControl.on(2);
		remoteControl.on(3);
		remoteControl.on(4);
		remoteControl.undo();
	}
	
	private static void lightAdjust(RemoteControl remoteControl) {
		Light light = new Light();
		Command lightOnCommand = new LightOnCommand(light);
		Command lightOffCommand = new LightOffCommand(light);
		remoteControl.addCommand(lightOnCommand, lightOffCommand);
	}

	private static void fanAdjust(RemoteControl remoteControl) {
		Fan fan = new Fan();
		Command fanLowSpeedCommand = new FanLowCommand(fan);
		Command fanMediumSpeedCommand = new FanMediumCommand(fan);
		Command fanHighSpeedCommand = new FanHighCommand(fan);
		Command fanOffSpeedCommand = new FanOffCommand(fan);
		remoteControl.addCommand(fanLowSpeedCommand, fanOffSpeedCommand);
		remoteControl.addCommand(fanMediumSpeedCommand, fanOffSpeedCommand);
		remoteControl.addCommand(fanHighSpeedCommand, fanOffSpeedCommand);
		remoteControl.addCommand(fanOffSpeedCommand, fanOffSpeedCommand);
	}
}
