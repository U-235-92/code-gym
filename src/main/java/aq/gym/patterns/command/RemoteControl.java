package aq.gym.patterns.command;

import java.util.ArrayList;
import java.util.List;

public class RemoteControl {

	private List<Command> onCommands;
	private List<Command> offCommands;
	private Command undoCommand;
	
	public RemoteControl() {
		onCommands = new ArrayList<Command>();
		offCommands = new ArrayList<Command>();
		undoCommand = new NoCommand();
	}
	
	public void addCommand(Command onCommand, Command offCommand) {
		if(!onCommands.contains(onCommand))
			onCommands.add(onCommand);
		if(!offCommands.contains(offCommand))
			offCommands.add(offCommand);
	}
	
	public void on(int commandIdx) {
		if(isCommandIdxCorrect(commandIdx, onCommands.size())) {			
			Command command = onCommands.get(commandIdx);
			doCommand(command);
		}
	}
	
	public void off(int commandIdx) {
		if(isCommandIdxCorrect(commandIdx, offCommands.size())) {			
			Command command = offCommands.get(commandIdx);
			doCommand(command);
		}
	}
	
	private boolean isCommandIdxCorrect(int commandIdx, int size) {
		return commandIdx < size && commandIdx >= 0;
	}
	
	private void doCommand(Command command) {
		command.execute();
		undoCommand = command;
	}
	
	public void undo() {
		undoCommand.undo();
	}
}
