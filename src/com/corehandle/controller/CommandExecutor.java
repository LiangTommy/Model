package com.corehandle.controller;

import java.util.concurrent.LinkedBlockingQueue;
import com.corehandle.command.BaseCommand;
import com.utils.Util;

public final class CommandExecutor {
	
	private boolean isDestroyed = false;
	private Object mLock = new Object();
	private CommandLooper mLooper = new CommandLooper();
	public static CommandExecutor mExecutor;
	private LinkedBlockingQueue<BaseCommand> mQueue = new LinkedBlockingQueue<BaseCommand>();
	
	public static CommandExecutor getCommandExecutor() {
		if(mExecutor==null) {
			mExecutor=new CommandExecutor();
			Util.logWarn("class index", "++++++++++++CommandExecutor:"+mExecutor.hashCode());
		}
		return mExecutor;
	}
	
	private CommandExecutor() {
		init();
	}
	
	public void init() {
		mLooper.start();
	}
	
	public void executeCommand(BaseCommand command) {
		mQueue.add(command);
		synchronized (mLock) {
			mLock.notify();
		}
	}
	
	class CommandLooper extends Thread {
		@Override
		public void run() {
			Util.logInfo("command-looper", "looper is onStart !!!");
			while(!isDestroyed) {
				synchronized(mLock) {
					BaseCommand command=mQueue.poll();
					if(command!=null) {
						command.execute();
					}
					if(mQueue.size()==0) {
						Util.logInfo("command-looper", "looper is wait !!!");
						try {
							mLock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
							isDestroyed=true;
						}
						Util.logWarn("command-looper", "looper is notify !!!");
					}
				}
			}
		}
	};
	
}
