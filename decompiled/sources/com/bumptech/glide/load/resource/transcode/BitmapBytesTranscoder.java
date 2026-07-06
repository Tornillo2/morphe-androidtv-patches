package com.bumptech.glide.load.resource.transcode;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.bytes.BytesResource;
import java.io.ByteArrayOutputStream;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class BitmapBytesTranscoder implements ResourceTranscoder<Bitmap, byte[]> {
    public final Bitmap.CompressFormat compressFormat;
    public final int quality;

    public BitmapBytesTranscoder() {
        this(Bitmap.CompressFormat.JPEG, 100);
    }

    @Override // com.bumptech.glide.load.resource.transcode.ResourceTranscoder
    @Nullable
    public Resource<byte[]> transcode(@NonNull Resource<Bitmap> resource, @NonNull Options options) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        resource.get().compress(this.compressFormat, this.quality, byteArrayOutputStream);
        resource.recycle();
        return new BytesResource(byteArrayOutputStream.toByteArray());
    }

    public BitmapBytesTranscoder(@NonNull Bitmap.CompressFormat compressFormat, int i) {
        this.compressFormat = compressFormat;
        this.quality = i;
    }
}
