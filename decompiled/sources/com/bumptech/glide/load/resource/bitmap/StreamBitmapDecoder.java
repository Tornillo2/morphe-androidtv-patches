package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.Downsampler;
import com.bumptech.glide.util.ExceptionCatchingInputStream;
import com.bumptech.glide.util.MarkEnforcingInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class StreamBitmapDecoder implements ResourceDecoder<InputStream, Bitmap> {
    public final ArrayPool byteArrayPool;
    public final Downsampler downsampler;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class UntrustedCallbacks implements Downsampler.DecodeCallbacks {
        public final RecyclableBufferedInputStream bufferedStream;
        public final ExceptionCatchingInputStream exceptionStream;

        public UntrustedCallbacks(RecyclableBufferedInputStream recyclableBufferedInputStream, ExceptionCatchingInputStream exceptionCatchingInputStream) {
            this.bufferedStream = recyclableBufferedInputStream;
            this.exceptionStream = exceptionCatchingInputStream;
        }

        @Override // com.bumptech.glide.load.resource.bitmap.Downsampler.DecodeCallbacks
        public void onDecodeComplete(BitmapPool bitmapPool, Bitmap bitmap) throws IOException {
            IOException exception = this.exceptionStream.getException();
            if (exception != null) {
                if (bitmap == null) {
                    throw exception;
                }
                bitmapPool.put(bitmap);
                throw exception;
            }
        }

        @Override // com.bumptech.glide.load.resource.bitmap.Downsampler.DecodeCallbacks
        public void onObtainBounds() {
            this.bufferedStream.fixMarkLimit();
        }
    }

    public StreamBitmapDecoder(Downsampler downsampler, ArrayPool arrayPool) {
        this.downsampler = downsampler;
        this.byteArrayPool = arrayPool;
    }

    @Override // com.bumptech.glide.load.ResourceDecoder
    public /* bridge */ /* synthetic */ boolean handles(@NonNull InputStream inputStream, @NonNull Options options) throws IOException {
        handles2(inputStream, options);
        return true;
    }

    @Override // com.bumptech.glide.load.ResourceDecoder
    public Resource<Bitmap> decode(@NonNull InputStream inputStream, int i, int i2, @NonNull Options options) throws IOException {
        RecyclableBufferedInputStream recyclableBufferedInputStream;
        boolean z;
        if (inputStream instanceof RecyclableBufferedInputStream) {
            recyclableBufferedInputStream = (RecyclableBufferedInputStream) inputStream;
            z = false;
        } else {
            recyclableBufferedInputStream = new RecyclableBufferedInputStream(inputStream, this.byteArrayPool);
            z = true;
        }
        ExceptionCatchingInputStream exceptionCatchingInputStreamObtain = ExceptionCatchingInputStream.obtain(recyclableBufferedInputStream);
        try {
            Resource<Bitmap> resourceDecode = this.downsampler.decode(new MarkEnforcingInputStream(exceptionCatchingInputStreamObtain), i, i2, options, new UntrustedCallbacks(recyclableBufferedInputStream, exceptionCatchingInputStreamObtain));
            exceptionCatchingInputStreamObtain.release();
            if (z) {
                recyclableBufferedInputStream.release();
            }
            return resourceDecode;
        } finally {
        }
    }

    /* JADX INFO: renamed from: handles, reason: avoid collision after fix types in other method */
    public boolean handles2(@NonNull InputStream inputStream, @NonNull Options options) {
        this.downsampler.getClass();
        return true;
    }
}
