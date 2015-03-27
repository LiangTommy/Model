package com.download;

import com.model.HttpResultModel;

public interface DownloadListener {

//	publishProgress 请求当前进度的更新
	
	public void onProgressUpdate(int progress);
	
	public void onCancel(HttpResultModel model);
	
	public void onSuccess(HttpResultModel model);
	
	public void onError(HttpResultModel model);
	
}
