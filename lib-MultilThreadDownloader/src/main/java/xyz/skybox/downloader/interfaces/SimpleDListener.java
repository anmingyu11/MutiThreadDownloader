package xyz.skybox.downloader.interfaces;

import java.io.File;

public class SimpleDListener implements IDListener {

    @Override
    public void onPrepare() {

    }

    @Override
    public void onStart(String fileName, String realUrl, long fileLength) {

    }

    @Override
    public void onProgress(long progress) {

    }

    @Override
    public void onStop(long progress) {

    }

    @Override
    public void onFinish(File file) {

    }

    @Override
    public void onError(int status, String error) {

    }
}