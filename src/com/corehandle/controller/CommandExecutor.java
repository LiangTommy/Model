package com.corehandle.controller;

import java.util.concurrent.LinkedBlockingQueue;

import android.os.Handler;
import android.os.Message;

import com.corehandle.command.BaseCommand;
import com.utils.Util;

public final class CommandExecutor {
	
	private boolean isDestroyed = false;
	private boolean isLocked=false;
	private Object mBlock = new Object();
	private CommandLooper mLooper = new CommandLooper();
	public static CommandExecutor mExecutor;
	private LinkedBlockingQueue<BaseCommand> mQueue = new LinkedBlockingQueue<BaseCommand>();
	
	public static synchronized CommandExecutor getCommandExecutor() {
		if(mExecutor==null) {
			mExecutor=new CommandExecutor();
		}
		return mExecutor;
	}
	
	private CommandExecutor() {
		init();
	}
	
	public void init() {
		mLooper.start();
	}
	
	public synchronized void sendCommand(BaseCommand command) {
		Util.logInfo("command-looper", "command is added " + command.hashCode()+" is lock ? "+isLocked);
		mQueue.add(command);
		if(isLocked) {
			synchronized (mBlock) {
				Util.logInfo("command-looper", "command is notify " + command.hashCode());
				mBlock.notify();
			}
		}
	}
	
	/**
	 * 销毁命令处理循环
	 */
	public void destroy() {
		isDestroyed=true;
		synchronized (mBlock) {
			mBlock.notify();
		}
	}
	
	public void handle() {
		Handler handle=new Handler();
		handle.sendEmptyMessage(3);
		Message msg=Message.obtain();
		handle.sendMessage(msg);
		handle.removeCallbacks(null);
	}
	
	class CommandLooper extends Thread {
		@Override
		public void run() {
			try {
				Util.logInfo("command-looper", "looper is onStart !!!");
				while(!isDestroyed) {
					synchronized(mBlock) {
						if(mQueue.size()==0) {
							Util.logInfo("command-looper", "looper is wait !!!");
							isLocked=true;
							mBlock.wait();
							isLocked=false;
							Util.logWarn("command-looper", "looper is notify !!! , " +
									"queue size is " + mQueue.size());
						}
						BaseCommand command=mQueue.poll();
						if(command!=null) {
							command.execute();
						}
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				isDestroyed=true;
			}
		}
	};
	
	// 同步模块(生产者-消费者模式)
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
