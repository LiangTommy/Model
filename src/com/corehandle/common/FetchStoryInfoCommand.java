package com.corehandle.common;

import com.corehandle.command.BaseCommand;

public class FetchStoryInfoCommand extends BaseCommand{

	public void setTag(String tag) {
		this.tag=String.format("-----------fetch story data : %s", tag);
	}
	
}
