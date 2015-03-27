package com.corehandle.controller;

import java.util.concurrent.LinkedBlockingQueue;
import com.corehandle.command.BaseCommand;

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
