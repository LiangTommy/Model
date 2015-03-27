package com.corehandle.controller;

import com.corehandle.command.BaseCommand;
import com.utils.Util;

public class CommandLooper {

	private boolean isDestroyed = false;
	private boolean isLocked=false;
	private Object mBlock = new Object();
	private CommandQueue mQueue;
	private CommandThread thread;
	
	public CommandLooper(CommandQueue queue) {
		thread=new CommandThread();
		this.mQueue=queue;
	}
	
	public void loop() {
		thread.start();
	}
	
	public void commandHandleNotify() {
		if(isLocked) {
			synchronized (mBlock) {
				mBlock.notify();
			}
		}
	}
	
	class CommandThread extends Thread {
		@Override
		public void run() {			
			Util.logInfo("command-looper", "looper is onStart !!!");
			
			while(!isDestroyed) {
				synchronized(mBlock) {
					if(mQueue.isEmpty()) {
						Util.logInfo("command-looper", "looper is wait !!!");
						
						try {
							isLocked=true;
							mBlock.wait();
							isLocked=false;
						} 
						catch (InterruptedException e) {
							e.printStackTrace();
							isDestroyed=true;
						}
						
						Util.logWarn("command-looper", "looper is notify !!! , " +
								"queue size is " + mQueue.getSize());
					}
					
					BaseCommand command=mQueue.nextCommand();
					if(command!=null) {
						command.execute();
					}
					
				}
			}
		}
	};
	
	/**
	 *  同步模块(生产者-消费者模式)
	 */
	public void handleCommand() {
		try {
			synchronized (mBlock) {
				if(isLocked) {
					mBlock.wait();
				}
				isLocked=true;		
				
				//TODO the operation...
				
				isLocked=false;
				mBlock.notify();
			}
		}
		catch (Exception e) {
		}
	}
	
}
