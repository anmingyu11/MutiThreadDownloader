package xyz.skybox.downloader.bizs;

public final class DLError {
    private DLError() {
    }

    private static String[] ERRORS = new String[]{
            "ERROR_NOT_NETWORK", "ERROR_CREATE_FILE",
            "ERROR_INVALID_URL", "ERROR_REPEAT_URL",
            "ERROR_CANNOT_GET_URL", "ERROR_OPEN_CONNECT",
            "ERROR_UNHANDLED_REDIRECT"
    };

    /**
     * 没有网络
     */
    public static final int ERROR_NOT_NETWORK = 0;
    /**
     * 创建文件失败
     */
    public static final int ERROR_CREATE_FILE = 1;
    /**
     * 无效Url
     */
    public static final int ERROR_INVALID_URL = 2;
    /**
     * 重复的下载地址
     */
    public static final int ERROR_REPEAT_URL = 3;
    /**
     * 无法获取真实下载地址
     */
    public static final int ERROR_CANNOT_GET_URL = 4;
    /**
     * 建立连接出错
     */
    public static final int ERROR_OPEN_CONNECT = 5;
    /**
     * 未能处理的重定向错误
     */
    public static final int ERROR_UNHANDLED_REDIRECT = 6;
}