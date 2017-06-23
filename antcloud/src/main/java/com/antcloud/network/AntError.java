package com.antcloud.network;

/**
 * @author liyuan
 * @description
 * @email thisuper@qq.com
 * @date 17/6/23 下午4:46
 */

public class AntError {
    public final static int TIMEOUT_ERROR_CODE = 4101;//链接超时
    public final static int NETWORK_ERROR_CODE = 4102;//网络错误
    public final static int PARSE_ERROR_CODE = 4103;//解析出错
    public final static int OTHER_ERROR_CODE = 4104;//其他异常
    private Throwable throwable;
    private String description;
    private int errorCode;


    public AntError(Throwable throwable, String description, int errorCode) {
        this.throwable = throwable;
        this.description = description;
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "AntError{" +
                "throwable=" + throwable +
                ", description='" + description + '\'' +
                ", errorCode=" + errorCode +
                '}';
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
