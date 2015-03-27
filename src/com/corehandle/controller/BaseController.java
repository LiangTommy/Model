package com.corehandle.controller;

import com.corehandle.command.BaseCommand;
import com.corehandle.common.CommandListener;

public class BaseController implements IController{

	private BaseCommand mCommand;
	private CommandListener mListener;
	
	@Override
	public BaseCommand getCommand() {
		return mCommand;
	}

	@Override
	public void setCommand(BaseCommand command) {
		this.mCommand=command;
	}

	@Override
	public void execute() {
		CommandExecutor.getCommandExecutor().sendCommand(mCommand);
	}

	@Override
	public void interrupt() {

	}

	@Override
	public void destroy() {

	}

	@Override
	public CommandListener getCommandListener() {
		return mListener;
	}

	@Override
	public void setCommandListener(CommandListener listener) {
		this.mListener = listener;
	}

}
