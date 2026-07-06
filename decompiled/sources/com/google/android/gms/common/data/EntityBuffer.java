package com.google.android.gms.common.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ObjectListKt$$ExternalSyntheticOutline1;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@KeepForSdk
public abstract class EntityBuffer<T> extends AbstractDataBuffer<T> {
    public boolean zaa;
    public ArrayList zab;

    @KeepForSdk
    public EntityBuffer(@NonNull DataHolder dataHolder) {
        super(dataHolder);
        this.zaa = false;
    }

    @Override // com.google.android.gms.common.data.AbstractDataBuffer, com.google.android.gms.common.data.DataBuffer
    @NonNull
    @ResultIgnorabilityUnspecified
    @KeepForSdk
    public final T get(int i) {
        int iIntValue;
        int iIntValue2;
        zab();
        int iZaa = zaa(i);
        int i2 = 0;
        if (i >= 0 && i != this.zab.size()) {
            if (i == this.zab.size() - 1) {
                DataHolder dataHolder = this.mDataHolder;
                Preconditions.checkNotNull(dataHolder);
                iIntValue = dataHolder.zad;
                iIntValue2 = ((Integer) this.zab.get(i)).intValue();
            } else {
                iIntValue = ((Integer) this.zab.get(i + 1)).intValue();
                iIntValue2 = ((Integer) this.zab.get(i)).intValue();
            }
            i2 = iIntValue - iIntValue2;
            if (i2 == 1) {
                int iZaa2 = zaa(i);
                DataHolder dataHolder2 = this.mDataHolder;
                Preconditions.checkNotNull(dataHolder2);
                dataHolder2.getWindowIndex(iZaa2);
                i2 = 1;
            }
        }
        return getEntry(iZaa, i2);
    }

    @Nullable
    @KeepForSdk
    public String getChildDataMarkerColumn() {
        return null;
    }

    @Override // com.google.android.gms.common.data.AbstractDataBuffer, com.google.android.gms.common.data.DataBuffer
    @KeepForSdk
    public int getCount() {
        zab();
        return this.zab.size();
    }

    @NonNull
    @KeepForSdk
    public abstract T getEntry(int i, int i2);

    @NonNull
    @KeepForSdk
    public abstract String getPrimaryDataMarkerColumn();

    public final int zaa(int i) {
        if (i < 0 || i >= this.zab.size()) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Position ", i, " is out of bounds for this buffer"));
        }
        return ((Integer) this.zab.get(i)).intValue();
    }

    public final void zab() {
        synchronized (this) {
            try {
                if (!this.zaa) {
                    DataHolder dataHolder = this.mDataHolder;
                    Preconditions.checkNotNull(dataHolder);
                    int i = dataHolder.zad;
                    ArrayList arrayList = new ArrayList();
                    this.zab = arrayList;
                    if (i > 0) {
                        arrayList.add(0);
                        String primaryDataMarkerColumn = getPrimaryDataMarkerColumn();
                        String string = this.mDataHolder.getString(primaryDataMarkerColumn, 0, this.mDataHolder.getWindowIndex(0));
                        for (int i2 = 1; i2 < i; i2++) {
                            int windowIndex = this.mDataHolder.getWindowIndex(i2);
                            String string2 = this.mDataHolder.getString(primaryDataMarkerColumn, i2, windowIndex);
                            if (string2 == null) {
                                throw new NullPointerException("Missing value for markerColumn: " + primaryDataMarkerColumn + ", at row: " + i2 + ", for window: " + windowIndex);
                            }
                            if (!string2.equals(string)) {
                                this.zab.add(Integer.valueOf(i2));
                                string = string2;
                            }
                        }
                    }
                    this.zaa = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
