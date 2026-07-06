package com.amazon.minerva.client.thirdparty.transport;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class UploadResult {
    public static final String CLIENT_ERROR = "CLIENT_ERROR";
    public static final String NO_CONNECTION = "NO_CONNECTION";
    public static final String SERVER_ERROR = "SERVER_ERROR";
    public static final String SUCCESS = "SUCCESS";
    public static final String UNEXPECTED_ERROR = "UNEXPECTED_ERROR";
    public byte[] responsePayload;
    public String uploadMessage;
    public String uploadStatus;

    public UploadResult(String str, String str2) {
        this.uploadStatus = str;
        this.uploadMessage = str2;
    }

    public byte[] getResponsePayload() {
        return this.responsePayload;
    }

    public String getUploadMessage() {
        return this.uploadMessage;
    }

    public String getUploadStatus() {
        return this.uploadStatus;
    }

    public UploadResult(String str, String str2, byte[] bArr) {
        this.uploadStatus = str;
        this.uploadMessage = str2;
        this.responsePayload = bArr;
    }
}
