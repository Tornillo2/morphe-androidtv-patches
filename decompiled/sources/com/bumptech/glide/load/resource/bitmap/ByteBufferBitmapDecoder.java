package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.util.ByteBufferUtil;
import java.io.IOException;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class ByteBufferBitmapDecoder implements ResourceDecoder<ByteBuffer, Bitmap> {
    public final Downsampler downsampler;

    public ByteBufferBitmapDecoder(Downsampler downsampler) {
        this.downsampler = downsampler;
    }

    @Override // com.bumptech.glide.load.ResourceDecoder
    public /* bridge */ /* synthetic */ boolean handles(@NonNull ByteBuffer byteBuffer, @NonNull Options options) throws IOException {
        handles2(byteBuffer, options);
        return true;
    }

    @Override // com.bumptech.glide.load.ResourceDecoder
    public Resource<Bitmap> decode(@NonNull ByteBuffer byteBuffer, int i, int i2, @NonNull Options options) throws IOException {
        return this.downsampler.decode(ByteBufferUtil.toStream(byteBuffer), i, i2, options, Downsampler.EMPTY_CALLBACKS);
    }

    /* JADX INFO: renamed from: handles, reason: avoid collision after fix types in other method */
    public boolean handles2(@NonNull ByteBuffer byteBuffer, @NonNull Options options) {
        this.downsampler.getClass();
        return true;
    }
}
