package com.download;

public interface DownloadStatusListener {
	
	public void onCancel(int ID);
	
	public void onSuccess(int ID);

}
