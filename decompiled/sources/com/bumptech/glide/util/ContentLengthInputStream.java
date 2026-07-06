package com.bumptech.glide.util;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class ContentLengthInputStream extends FilterInputStream {
    public static final String TAG = "ContentLengthStream";
    public static final int UNKNOWN = -1;
    public final long contentLength;
    public int readSoFar;

    public ContentLengthInputStream(@NonNull InputStream inputStream, long j) {
        super(inputStream);
        this.contentLength = j;
    }

    @NonNull
    public static InputStream obtain(@NonNull InputStream inputStream, @Nullable String str) {
        return new ContentLengthInputStream(inputStream, parseContentLength(str));
    }

    public static int parseContentLength(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            if (!Log.isLoggable(TAG, 3)) {
                return -1;
            }
            Log.d(TAG, "failed to parse content length header: " + str, e);
            return -1;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int available() throws IOException {
        return (int) Math.max(this.contentLength - ((long) this.readSoFar), ((FilterInputStream) this).in.available());
    }

    public final int checkReadSoFarOrThrow(int i) throws IOException {
        if (i >= 0) {
            this.readSoFar += i;
            return i;
        }
        if (this.contentLength - ((long) this.readSoFar) <= 0) {
            return i;
        }
        throw new IOException("Failed to read all expected data, expected: " + this.contentLength + ", but read: " + this.readSoFar);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read() throws IOException {
        int i;
        i = super.read();
        checkReadSoFarOrThrow(i >= 0 ? 1 : -1);
        return i;
    }

    @NonNull
    public static InputStream obtain(@NonNull InputStream inputStream, long j) {
        return new ContentLengthInputStream(inputStream, j);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        i3 = super.read(bArr, i, i2);
        checkReadSoFarOrThrow(i3);
        return i3;
    }
}
