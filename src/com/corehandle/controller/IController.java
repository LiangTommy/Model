package com.corehandle.controller;

import com.corehandle.command.BaseCommand;
import com.corehandle.common.CommandListener;

public interface IController {

	/**
	 * 获取需要执行的任务
	 *  
	 * @return
	 */
	public BaseCommand getCommand();

	/**
	 * 设置需要执行的任务
	 * 
	 * @param command
	 */
	public void setCommand(BaseCommand command);
	
	/**
	 * 执行操作
	 */
	public void execute();

	/**
	 * 中断
	 */
	public void interrupt();
	
	/**
	 * 销毁
	 */
	public void destroy();
	
	/**
	 * 设置任务执行监听器
	 * 
	 * @return
	 */
	public CommandListener getCommandListener();

	/**
	 * 设置任务执行监听器
	 * 
	 * @param listener
	 */
	public void setCommandListener(CommandListener listener);

}
