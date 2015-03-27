package com.download;

import com.model.HttpResultModel;
import android.annotation.SuppressLint;
import android.os.AsyncTask;

@SuppressLint("NewApi")
public class DownloadTask extends AsyncTask<String[], Integer, Void>{
	
	private int ID;
	private DownloadListener mDownloadListener;
	private DownloadStatusListener mDownloadStatusListener;
	private HttpResultModel mHttpResultModel;
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public DownloadStatusListener getDownloadStatusListener() {
		return mDownloadStatusListener;
	}

	public void setDownloadStatusListener(
			DownloadStatusListener mDownloadStatusListener) {
		this.mDownloadStatusListener = mDownloadStatusListener;
	}
	
	public DownloadListener getDownloadListener() {
		return mDownloadListener;
	}

	public void setDownloadListener(DownloadListener mDownloadListener) {
		this.mDownloadListener = mDownloadListener;
	}

	@Override
	protected Void doInBackground(String[]... params) {
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		mDownloadListener.onSuccess(mHttpResultModel);
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
		mDownloadListener.onProgressUpdate(values[0]);
	}

	@Override
	protected void onCancelled(Void result) {
		super.onCancelled(result);
		mDownloadListener.onCancel(mHttpResultModel);
	}

	/**
	 * ¶Ï¿ªÍøÂçÁ¬½Ó
	 */
	public void disConnect() {
		
	}
	
}
