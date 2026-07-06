package com.bumptech.glide.load.engine.bitmap_recycle;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class IntegerArrayAdapter implements ArrayAdapterInterface<int[]> {
    public static final String TAG = "IntegerArrayPool";

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.ArrayAdapterInterface
    public int getArrayLength(int[] iArr) {
        return iArr.length;
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.ArrayAdapterInterface
    public int getElementSizeInBytes() {
        return 4;
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.ArrayAdapterInterface
    public String getTag() {
        return TAG;
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.ArrayAdapterInterface
    public int[] newArray(int i) {
        return new int[i];
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.ArrayAdapterInterface
    /* JADX INFO: renamed from: newArray, reason: avoid collision after fix types in other method */
    public int[] newArray2(int i) {
        return new int[i];
    }

    /* JADX INFO: renamed from: getArrayLength, reason: avoid collision after fix types in other method */
    public int getArrayLength2(int[] iArr) {
        return iArr.length;
    }
}
