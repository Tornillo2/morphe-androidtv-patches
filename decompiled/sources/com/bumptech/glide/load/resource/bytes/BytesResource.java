package com.bumptech.glide.load.resource.bytes;

import androidx.annotation.NonNull;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.util.Preconditions;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class BytesResource implements Resource<byte[]> {
    public final byte[] bytes;

    public BytesResource(byte[] bArr) {
        Preconditions.checkNotNull(bArr, "Argument must not be null");
        this.bytes = bArr;
    }

    @Override // com.bumptech.glide.load.engine.Resource
    @NonNull
    public Class<byte[]> getResourceClass() {
        return byte[].class;
    }

    @Override // com.bumptech.glide.load.engine.Resource
    public int getSize() {
        return this.bytes.length;
    }

    @Override // com.bumptech.glide.load.engine.Resource
    @NonNull
    public byte[] get() {
        return this.bytes;
    }

    @Override // com.bumptech.glide.load.engine.Resource
    public void recycle() {
    }
}
