package com.bumptech.glide.load.resource.gif;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class GifBitmapProvider implements GifDecoder.BitmapProvider {

    @Nullable
    public final ArrayPool arrayPool;
    public final BitmapPool bitmapPool;

    public GifBitmapProvider(BitmapPool bitmapPool) {
        this(bitmapPool, null);
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder.BitmapProvider
    @NonNull
    public Bitmap obtain(int i, int i2, @NonNull Bitmap.Config config) {
        return this.bitmapPool.getDirty(i, i2, config);
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder.BitmapProvider
    @NonNull
    public byte[] obtainByteArray(int i) {
        ArrayPool arrayPool = this.arrayPool;
        return arrayPool == null ? new byte[i] : (byte[]) arrayPool.get(i, byte[].class);
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder.BitmapProvider
    @NonNull
    public int[] obtainIntArray(int i) {
        ArrayPool arrayPool = this.arrayPool;
        return arrayPool == null ? new int[i] : (int[]) arrayPool.get(i, int[].class);
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder.BitmapProvider
    public void release(@NonNull Bitmap bitmap) {
        this.bitmapPool.put(bitmap);
    }

    public GifBitmapProvider(BitmapPool bitmapPool, @Nullable ArrayPool arrayPool) {
        this.bitmapPool = bitmapPool;
        this.arrayPool = arrayPool;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder.BitmapProvider
    public void release(@NonNull byte[] bArr) {
        ArrayPool arrayPool = this.arrayPool;
        if (arrayPool == null) {
            return;
        }
        arrayPool.put(bArr);
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder.BitmapProvider
    public void release(@NonNull int[] iArr) {
        ArrayPool arrayPool = this.arrayPool;
        if (arrayPool == null) {
            return;
        }
        arrayPool.put(iArr);
    }
}
