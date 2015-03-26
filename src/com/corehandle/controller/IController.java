package com.corehandle.controller;

import com.corehandle.command.BaseCommand;
import com.corehandle.common.CommandListener;

public interface IController {

	/**
	 * ��ȡ��Ҫִ�е�����
	 *  
	 * @return
	 */
	public BaseCommand getCommand();

	/**
	 * ������Ҫִ�е�����
	 * 
	 * @param command
	 */
	public void setCommand(BaseCommand command);
	
	/**
	 * ִ�в���
	 */
	public void execute();

	/**
	 * �ж�
	 */
	public void interrupt();
	
	/**
	 * ����
	 */
	public void destroy();
	
	/**
	 * ��������ִ�м�����
	 * 
	 * @return
	 */
	public CommandListener getCommandListener();

	/**
	 * ��������ִ�м�����
	 * 
	 * @param listener
	 */
	public void setCommandListener(CommandListener listener);

}
