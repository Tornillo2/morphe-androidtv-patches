package com.google.android.gms.common.data;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import android.support.v4.media.MediaMetadataCompat$Builder$$ExternalSyntheticOutline0;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@KeepForSdk
public class SingleRefDataBufferIterator<T> extends DataBufferIterator<T> {
    public Object zac;

    public SingleRefDataBufferIterator(@NonNull DataBuffer dataBuffer) {
        super(dataBuffer);
    }

    @Override // com.google.android.gms.common.data.DataBufferIterator, java.util.Iterator
    @NonNull
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Cannot advance the iterator beyond ", this.zab));
        }
        int i = this.zab + 1;
        this.zab = i;
        if (i == 0) {
            Object obj = this.zaa.get(0);
            Preconditions.checkNotNull(obj);
            this.zac = obj;
            if (!(obj instanceof DataBufferRef)) {
                throw new IllegalStateException(MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("DataBuffer reference of type ", String.valueOf(obj.getClass()), " is not movable"));
            }
        } else {
            Object obj2 = this.zac;
            Preconditions.checkNotNull(obj2);
            ((DataBufferRef) obj2).zaa(this.zab);
        }
        return this.zac;
    }
}
