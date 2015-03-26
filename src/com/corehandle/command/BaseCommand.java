package com.corehandle.command;

import com.corehandle.receiver.BaseReceiver;
import com.utils.Util;

public class BaseCommand {

	public String tag;
	public BaseReceiver mReceiver;

	public BaseReceiver getReceiver() {
		return mReceiver;
	}

	public void setReceiver(BaseReceiver mReceiver) {
		this.mReceiver = mReceiver;
	}

	public void setTag(String tag) {
		this.tag=tag;
	}
	
	public void execute() {
		Util.logInfo("command is executed", ""+tag);
		mReceiver.execute();
	}
	
}
