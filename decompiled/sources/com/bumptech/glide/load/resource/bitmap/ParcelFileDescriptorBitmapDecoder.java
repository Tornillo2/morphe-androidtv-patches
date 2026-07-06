package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.os.ParcelFileDescriptor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@RequiresApi(21)
public final class ParcelFileDescriptorBitmapDecoder implements ResourceDecoder<ParcelFileDescriptor, Bitmap> {
    public final Downsampler downsampler;

    public ParcelFileDescriptorBitmapDecoder(Downsampler downsampler) {
        this.downsampler = downsampler;
    }

    @Override // com.bumptech.glide.load.ResourceDecoder
    @Nullable
    public Resource<Bitmap> decode(@NonNull ParcelFileDescriptor parcelFileDescriptor, int i, int i2, @NonNull Options options) throws IOException {
        return this.downsampler.decode(parcelFileDescriptor, i, i2, options);
    }

    @Override // com.bumptech.glide.load.ResourceDecoder
    public /* bridge */ /* synthetic */ boolean handles(@NonNull ParcelFileDescriptor parcelFileDescriptor, @NonNull Options options) throws IOException {
        handles2(parcelFileDescriptor, options);
        return true;
    }

    /* JADX INFO: renamed from: handles, reason: avoid collision after fix types in other method */
    public boolean handles2(@NonNull ParcelFileDescriptor parcelFileDescriptor, @NonNull Options options) {
        this.downsampler.getClass();
        return true;
    }

    @Nullable
    /* JADX INFO: renamed from: decode, reason: avoid collision after fix types in other method */
    public Resource<Bitmap> decode2(@NonNull ParcelFileDescriptor parcelFileDescriptor, int i, int i2, @NonNull Options options) throws IOException {
        return this.downsampler.decode(parcelFileDescriptor, i, i2, options);
    }
}
