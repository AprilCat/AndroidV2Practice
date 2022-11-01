package com.voidsoul.servicebestpractice;

public interface DownloadListener {
    void onProgress(int preogress);
    void onSuccess();
    void onFailed();
    void onPaused();
    void onCanceled();
}
