package com.corehandle.command;

public class FetchCrashInfoCommand extends BaseCommand {

	public void setTag(String tag) {
		this.tag=String.format("fetch crash data : %s", tag);
	}
	
}
