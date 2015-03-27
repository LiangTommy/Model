package com.corehandle.command;

import com.utils.Util;

public final class CommandHandler {
	
	public static CommandHandler mExecutor;

	public static synchronized CommandHandler getCommandExecutor() {
		if(mExecutor==null) {
			mExecutor=new CommandHandler();
		}
		return mExecutor;
	}
	
	public void sendCommand(BaseCommand command) {
		Util.logInfo("command-looper", "command is added " + command.hashCode());
		CommandQueue.getInstance().enqueue(command);
	}
	
	/**
	 * 销毁命令处理循环
	 */
	public void destroy() {
		
	}
	
}
