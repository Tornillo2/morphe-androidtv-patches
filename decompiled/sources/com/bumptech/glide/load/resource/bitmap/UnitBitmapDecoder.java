package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.util.Util;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class UnitBitmapDecoder implements ResourceDecoder<Bitmap, Bitmap> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class NonOwnedBitmapResource implements Resource<Bitmap> {
        public final Bitmap bitmap;

        public NonOwnedBitmapResource(@NonNull Bitmap bitmap) {
            this.bitmap = bitmap;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.bumptech.glide.load.engine.Resource
        @NonNull
        public Bitmap get() {
            return this.bitmap;
        }

        @Override // com.bumptech.glide.load.engine.Resource
        @NonNull
        public Class<Bitmap> getResourceClass() {
            return Bitmap.class;
        }

        @Override // com.bumptech.glide.load.engine.Resource
        public int getSize() {
            return Util.getBitmapByteSize(this.bitmap);
        }

        @Override // com.bumptech.glide.load.engine.Resource
        @NonNull
        public Bitmap get() {
            return this.bitmap;
        }

        @Override // com.bumptech.glide.load.engine.Resource
        public void recycle() {
        }
    }

    @Override // com.bumptech.glide.load.ResourceDecoder
    public Resource<Bitmap> decode(@NonNull Bitmap bitmap, int i, int i2, @NonNull Options options) throws IOException {
        return new NonOwnedBitmapResource(bitmap);
    }

    /* JADX INFO: renamed from: handles, reason: avoid collision after fix types in other method */
    public boolean handles2(@NonNull Bitmap bitmap, @NonNull Options options) {
        return true;
    }

    @Override // com.bumptech.glide.load.ResourceDecoder
    public /* bridge */ /* synthetic */ boolean handles(@NonNull Bitmap bitmap, @NonNull Options options) throws IOException {
        return true;
    }

    /* JADX INFO: renamed from: decode, reason: avoid collision after fix types in other method */
    public Resource<Bitmap> decode2(@NonNull Bitmap bitmap, int i, int i2, @NonNull Options options) {
        return new NonOwnedBitmapResource(bitmap);
    }
}
