package com.bumptech.glide.load.engine.bitmap_recycle;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class ByteArrayAdapter implements ArrayAdapterInterface<byte[]> {
    public static final String TAG = "ByteArrayPool";

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.ArrayAdapterInterface
    public int getArrayLength(byte[] bArr) {
        return bArr.length;
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.ArrayAdapterInterface
    public int getElementSizeInBytes() {
        return 1;
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.ArrayAdapterInterface
    public String getTag() {
        return TAG;
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.ArrayAdapterInterface
    public byte[] newArray(int i) {
        return new byte[i];
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.ArrayAdapterInterface
    /* JADX INFO: renamed from: newArray, reason: avoid collision after fix types in other method */
    public byte[] newArray2(int i) {
        return new byte[i];
    }

    /* JADX INFO: renamed from: getArrayLength, reason: avoid collision after fix types in other method */
    public int getArrayLength2(byte[] bArr) {
        return bArr.length;
    }
}
