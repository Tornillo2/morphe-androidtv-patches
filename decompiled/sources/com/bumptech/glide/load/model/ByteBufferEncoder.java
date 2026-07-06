package com.bumptech.glide.load.model;

import android.util.Log;
import androidx.annotation.NonNull;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.util.ByteBufferUtil;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class ByteBufferEncoder implements Encoder<ByteBuffer> {
    public static final String TAG = "ByteBufferEncoder";

    @Override // com.bumptech.glide.load.Encoder
    public boolean encode(@NonNull ByteBuffer byteBuffer, @NonNull File file, @NonNull Options options) throws Throwable {
        try {
            ByteBufferUtil.toFile(byteBuffer, file);
            return true;
        } catch (IOException e) {
            if (!Log.isLoggable(TAG, 3)) {
                return false;
            }
            Log.d(TAG, "Failed to write data", e);
            return false;
        }
    }
}
