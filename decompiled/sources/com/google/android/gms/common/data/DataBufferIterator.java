package com.google.android.gms.common.data;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@KeepForSdk
public class DataBufferIterator<T> implements Iterator<T> {

    @NonNull
    public final DataBuffer zaa;
    public int zab;

    public DataBufferIterator(@NonNull DataBuffer dataBuffer) {
        Preconditions.checkNotNull(dataBuffer);
        this.zaa = dataBuffer;
        this.zab = -1;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zab < this.zaa.getCount() + (-1);
    }

    @Override // java.util.Iterator
    @NonNull
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Cannot advance the iterator beyond ", this.zab));
        }
        DataBuffer dataBuffer = this.zaa;
        int i = this.zab + 1;
        this.zab = i;
        return dataBuffer.get(i);
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Cannot remove elements from a DataBufferIterator");
    }
}
