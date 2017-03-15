package xyz.skybox.downloader.bizs;

class DLThreadInfo {
    String id;
    String baseUrl;
    long start, end;
    boolean isStop;

    DLThreadInfo(String id, String baseUrl, long start, long end) {
        this.id = id;
        this.baseUrl = baseUrl;
        this.start = start;
        this.end = end;
    }
}