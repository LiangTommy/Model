package com.corehandle.command;

import java.util.concurrent.LinkedBlockingQueue;

public final class CommandQueue {

	private static CommandQueue instance;
	private CommandLooper mLooper;
	private LinkedBlockingQueue<BaseCommand> mQueue = new LinkedBlockingQueue<BaseCommand>();
	
	public static synchronized CommandQueue getInstance() {
		if(instance == null) {
			instance = new CommandQueue();
		}
		return instance;
	}
	
	private CommandQueue() {
		mLooper=new CommandLooper(this);
		mLooper.loop();
	}
	
	public void enqueue(BaseCommand command) {
		mQueue.add(command);
		mLooper.commandHandleNotify();
	}
	
	public boolean isEmpty() {
		return mQueue.size()>0 ? false:true;
	}
	
	public int getSize() {
		return mQueue.size();
	}
	
	public BaseCommand nextCommand() {
		return mQueue.poll();
	}
	
}
