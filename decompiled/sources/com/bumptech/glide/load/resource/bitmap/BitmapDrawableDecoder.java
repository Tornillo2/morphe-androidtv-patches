package com.bumptech.glide.load.resource.bitmap;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import androidx.annotation.NonNull;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.util.Preconditions;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class BitmapDrawableDecoder<DataType> implements ResourceDecoder<DataType, BitmapDrawable> {
    public final ResourceDecoder<DataType, Bitmap> decoder;
    public final Resources resources;

    public BitmapDrawableDecoder(Context context, ResourceDecoder<DataType, Bitmap> resourceDecoder) {
        this(context.getResources(), resourceDecoder);
    }

    @Override // com.bumptech.glide.load.ResourceDecoder
    public Resource<BitmapDrawable> decode(@NonNull DataType datatype, int i, int i2, @NonNull Options options) throws IOException {
        return LazyBitmapDrawableResource.obtain(this.resources, this.decoder.decode(datatype, i, i2, options));
    }

    @Override // com.bumptech.glide.load.ResourceDecoder
    public boolean handles(@NonNull DataType datatype, @NonNull Options options) throws IOException {
        return this.decoder.handles(datatype, options);
    }

    @Deprecated
    public BitmapDrawableDecoder(Resources resources, BitmapPool bitmapPool, ResourceDecoder<DataType, Bitmap> resourceDecoder) {
        this(resources, resourceDecoder);
    }

    public BitmapDrawableDecoder(@NonNull Resources resources, @NonNull ResourceDecoder<DataType, Bitmap> resourceDecoder) {
        Preconditions.checkNotNull(resources, "Argument must not be null");
        this.resources = resources;
        Preconditions.checkNotNull(resourceDecoder, "Argument must not be null");
        this.decoder = resourceDecoder;
    }
}
