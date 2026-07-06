package com.google.android.gms.common.data;

import android.database.CharArrayBuffer;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@KeepForSdk
public abstract class DataBufferRef {

    @NonNull
    @KeepForSdk
    public final DataHolder mDataHolder;

    @KeepForSdk
    public int mDataRow;
    public int zaa;

    @KeepForSdk
    public DataBufferRef(@NonNull DataHolder dataHolder, int i) {
        Preconditions.checkNotNull(dataHolder);
        this.mDataHolder = dataHolder;
        zaa(i);
    }

    @KeepForSdk
    public void copyToBuffer(@NonNull String str, @NonNull CharArrayBuffer charArrayBuffer) {
        this.mDataHolder.zac(str, this.mDataRow, this.zaa, charArrayBuffer);
    }

    @KeepForSdk
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof DataBufferRef) {
            DataBufferRef dataBufferRef = (DataBufferRef) obj;
            if (Objects.equal(Integer.valueOf(dataBufferRef.mDataRow), Integer.valueOf(this.mDataRow)) && Objects.equal(Integer.valueOf(dataBufferRef.zaa), Integer.valueOf(this.zaa)) && dataBufferRef.mDataHolder == this.mDataHolder) {
                return true;
            }
        }
        return false;
    }

    @KeepForSdk
    public boolean getBoolean(@NonNull String str) {
        return this.mDataHolder.getBoolean(str, this.mDataRow, this.zaa);
    }

    @NonNull
    @KeepForSdk
    public byte[] getByteArray(@NonNull String str) {
        return this.mDataHolder.getByteArray(str, this.mDataRow, this.zaa);
    }

    @KeepForSdk
    public int getDataRow() {
        return this.mDataRow;
    }

    @KeepForSdk
    public double getDouble(@NonNull String str) {
        return this.mDataHolder.zaa(str, this.mDataRow, this.zaa);
    }

    @KeepForSdk
    public float getFloat(@NonNull String str) {
        return this.mDataHolder.zab(str, this.mDataRow, this.zaa);
    }

    @KeepForSdk
    public int getInteger(@NonNull String str) {
        return this.mDataHolder.getInteger(str, this.mDataRow, this.zaa);
    }

    @KeepForSdk
    public long getLong(@NonNull String str) {
        return this.mDataHolder.getLong(str, this.mDataRow, this.zaa);
    }

    @NonNull
    @KeepForSdk
    public String getString(@NonNull String str) {
        return this.mDataHolder.getString(str, this.mDataRow, this.zaa);
    }

    @KeepForSdk
    public boolean hasColumn(@NonNull String str) {
        return this.mDataHolder.zab.containsKey(str);
    }

    @KeepForSdk
    public boolean hasNull(@NonNull String str) {
        return this.mDataHolder.hasNull(str, this.mDataRow, this.zaa);
    }

    @KeepForSdk
    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.mDataRow), Integer.valueOf(this.zaa), this.mDataHolder});
    }

    @KeepForSdk
    public boolean isDataValid() {
        return !this.mDataHolder.isClosed();
    }

    @Nullable
    @KeepForSdk
    public Uri parseUri(@NonNull String str) {
        String string = this.mDataHolder.getString(str, this.mDataRow, this.zaa);
        if (string == null) {
            return null;
        }
        return Uri.parse(string);
    }

    public final void zaa(int i) {
        boolean z = false;
        if (i >= 0 && i < this.mDataHolder.zad) {
            z = true;
        }
        Preconditions.checkState(z);
        this.mDataRow = i;
        this.zaa = this.mDataHolder.getWindowIndex(i);
    }
}
