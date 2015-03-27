package com.download;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import android.util.SparseArray;

public final class DownloadManager {

	private static DownloadManager mInstance;
	private int mCorePoolSize=6;
	private int mMaximumPoolSize=6;
	private int mKeepAliveTime=5;
	public SparseArray<DownloadTask> mTasks=new SparseArray<DownloadTask>();
	private LinkedBlockingQueue<Runnable> mQueue = new LinkedBlockingQueue<Runnable>();
	
	private ThreadPoolExecutor mThreadPoolExecutor = new ThreadPoolExecutor(mCorePoolSize, 
			mMaximumPoolSize, mKeepAliveTime, TimeUnit. SECONDS, mQueue, new ThreadPoolExecutor.DiscardOldestPolicy()); 
	
	public static synchronized DownloadManager getInstance() {
		if(mInstance==null) {
			mInstance=new DownloadManager();
		}
		return mInstance;
	}
	
	public static void cleanInstance() {
		if(mInstance!=null) {
			mInstance=null;
		}
	}
	
	/**
	 * 添加任务
	 * 
	 * @param task
	 * 			远程下载的任务或本地的任务
	 */
	public void addTask(DownloadTask task) {
		task.setDownloadStatusListener(new DownloadStatusListener() {			
			@Override
			public void onCancel(int ID) {
				mTasks.remove(ID);
			}
			
			@Override
			public void onSuccess(int ID) {
				mTasks.remove(ID);
			}
		});
		task.executeOnExecutor(mThreadPoolExecutor);
	}
	
	/**
	 * 通过ID查找对应的任务
	 * 
	 * @param ID
	 * 		任务的标识	
	 * @return 返回查找到的任务
	 */
	public DownloadTask findTask(int ID) {
		return mTasks.get(ID);
	}
	
	/**
	 * 查找ID对应的任务进行取消
	 * 
	 * @param ID
	 * 		任务的标识	
	 */
	public void cancelTask(int ID) {
		DownloadTask task=mTasks.get(ID);
		if(task != null) {
			task.disConnect();
			task.cancel(true);
		}
	}
	
	/**
	 * 取消所有任务
	 */
	public void cancelAllTasks() {
		for(int i=0;i<mTasks.size();i++) {
			DownloadTask task = mTasks.valueAt(i);
			task.disConnect();
			task.cancel(true);
		}
	}
	
	/**
	 * 销毁操作
	 */
	public void destroy() {
		cancelAllTasks();
		cleanInstance();
	}
	
}
