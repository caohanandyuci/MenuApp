package com.ch.util;

import android.R.integer;

public interface OnDownLoadListener {
	/**
     * 下载成功
     */
    void onDownloadSuccess();

    /**
     * @param progress
     * 下载进度
     */
    void onDownloading(int progress);

    /**
     * 下载失败
     */
    void onDownloadFailed(int errorCode);
}
