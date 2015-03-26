package com.corehandle.command;

public class FetchStoryInfoCommand extends BaseCommand{

	public void setTag(String tag) {
		this.tag=String.format("fetch story data : %s", tag);
	}
	
}
