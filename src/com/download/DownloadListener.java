package com.download;

import com.model.HttpResultModel;

public interface DownloadListener {

//	publishProgress ����ǰ���ȵĸ���
	
	public void onProgressUpdate(int progress);
	
	public void onCancel(HttpResultModel model);
	
	public void onSuccess(HttpResultModel model);
	
	public void onError(HttpResultModel model);
	
}
