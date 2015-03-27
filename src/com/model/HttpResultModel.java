package com.model;

public class HttpResultModel {
	
	public static enum Status{
		BEFOREDOWNLOAD, DOWNLOADING, SUCCESS, ERROR
	}
	
	public void handle(Status status) {
		switch(status) {
			case BEFOREDOWNLOAD:
				break;
			case DOWNLOADING:
				break;
			case SUCCESS:
				break;
			case ERROR:
				break;
			default:
				break;
		}
	}
	
}
