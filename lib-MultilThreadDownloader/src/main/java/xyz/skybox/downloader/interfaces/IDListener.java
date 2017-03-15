package xyz.skybox.downloader.interfaces;

import java.io.File;

/**
 * @author AigeStudio 2015-10-18
 */
public interface IDListener {
    void onPrepare();

    void onStart(String fileName, String realUrl, long fileLength);

    void onProgress(long progress);

    void onStop(long progress);

    void onFinish(File file);

    void onError(int status, String error);
}