package xyz.skybox.downloader.bizs;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import xyz.skybox.downloader.interfaces.IDListener;

/**
 * 下载实体类
 * Download entity.
 *
 * @author AigeStudio 2015-05-16
 */
public class DLInfo {
    public long totalBytes;
    public long currentBytes;
    public String fileName;
    public String dirPath;
    public String baseUrl;
    public String realUrl;

    int redirect;
    boolean hasListener;
    boolean isResume;
    boolean isStop;
    String mimeType;
    String eTag;
    String disposition;
    String location;
    List<DLHeader> requestHeaders;
    final List<DLThreadInfo> threads;
    IDListener listener;
    File file;

    DLInfo() {
        threads = new ArrayList<>();
    }

    synchronized void addDLThread(DLThreadInfo info) {
        threads.add(info);
    }

    synchronized void removeDLThread(DLThreadInfo info) {
        threads.remove(info);
    }

    @Override
    public String toString() {
        return "DLInfo{" +
                "totalBytes=" + totalBytes +
                ", currentBytes=" + currentBytes +
                ", fileName='" + fileName + '\'' +
                ", dirPath='" + dirPath + '\'' +
                ", baseUrl='" + baseUrl + '\'' +
                ", realUrl='" + realUrl + '\'' +
                ", redirect=" + redirect +
                ", hasListener=" + hasListener +
                ", isResume=" + isResume +
                ", isStop=" + isStop +
                ", mimeType='" + mimeType + '\'' +
                ", eTag='" + eTag + '\'' +
                ", disposition='" + disposition + '\'' +
                ", location='" + location + '\'' +
                ", requestHeaders=" + requestHeaders +
                ", threads=" + threads +
                ", listener=" + listener +
                ", file=" + file +
                '}';
    }
}