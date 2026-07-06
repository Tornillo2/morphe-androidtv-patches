package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.load.EncodeStrategy;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.data.BufferedOutputStream;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.util.LogTime;
import com.bumptech.glide.util.Util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class BitmapEncoder implements ResourceEncoder<Bitmap> {
    public static final String TAG = "BitmapEncoder";

    @Nullable
    public final ArrayPool arrayPool;
    public static final Option<Integer> COMPRESSION_QUALITY = Option.memory("com.bumptech.glide.load.resource.bitmap.BitmapEncoder.CompressionQuality", 90);
    public static final Option<Bitmap.CompressFormat> COMPRESSION_FORMAT = Option.memory("com.bumptech.glide.load.resource.bitmap.BitmapEncoder.CompressionFormat");

    public BitmapEncoder(@NonNull ArrayPool arrayPool) {
        this.arrayPool = arrayPool;
    }

    @Override // com.bumptech.glide.load.ResourceEncoder
    @NonNull
    public EncodeStrategy getEncodeStrategy(@NonNull Options options) {
        return EncodeStrategy.TRANSFORMED;
    }

    public final Bitmap.CompressFormat getFormat(Bitmap bitmap, Options options) {
        Bitmap.CompressFormat compressFormat = (Bitmap.CompressFormat) options.get(COMPRESSION_FORMAT);
        return compressFormat != null ? compressFormat : bitmap.hasAlpha() ? Bitmap.CompressFormat.PNG : Bitmap.CompressFormat.JPEG;
    }

    @Override // com.bumptech.glide.load.Encoder
    public boolean encode(@NonNull Resource<Bitmap> resource, @NonNull File file, @NonNull Options options) throws Throwable {
        boolean z;
        FileOutputStream fileOutputStream;
        Bitmap bitmap = resource.get();
        Bitmap.CompressFormat format = getFormat(bitmap, options);
        bitmap.getWidth();
        bitmap.getHeight();
        long logTime = LogTime.getLogTime();
        int iIntValue = ((Integer) options.get(COMPRESSION_QUALITY)).intValue();
        OutputStream bufferedOutputStream = null;
        try {
            try {
                fileOutputStream = new FileOutputStream(file);
            } catch (IOException e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            bufferedOutputStream = this.arrayPool != null ? new BufferedOutputStream(fileOutputStream, this.arrayPool) : fileOutputStream;
            bitmap.compress(format, iIntValue, bufferedOutputStream);
            bufferedOutputStream.close();
            try {
                bufferedOutputStream.close();
            } catch (IOException unused) {
            }
            z = true;
        } catch (IOException e2) {
            e = e2;
            bufferedOutputStream = fileOutputStream;
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Failed to encode Bitmap", e);
            }
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException unused2) {
                }
            }
            z = false;
        } catch (Throwable th2) {
            th = th2;
            bufferedOutputStream = fileOutputStream;
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException unused3) {
                }
            }
            throw th;
        }
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, "Compressed with type: " + format + " of size " + Util.getBitmapByteSize(bitmap) + " in " + LogTime.getElapsedMillis(logTime) + ", options format: " + options.get(COMPRESSION_FORMAT) + ", hasAlpha: " + bitmap.hasAlpha());
        }
        return z;
    }

    @Deprecated
    public BitmapEncoder() {
        this.arrayPool = null;
    }
}
