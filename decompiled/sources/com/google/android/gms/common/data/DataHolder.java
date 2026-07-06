package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.database.CharArrayBuffer;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.sqlite.CursorWrapper;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@KeepForSdk
@KeepName
@SafeParcelable.Class(creator = "DataHolderCreator", validate = true)
public final class DataHolder extends AbstractSafeParcelable implements Closeable {

    @NonNull
    @KeepForSdk
    public static final Parcelable.Creator<DataHolder> CREATOR = new zaf();
    public static final Builder zaf = new zab(new String[0], null, 0 == true ? 1 : 0);

    @SafeParcelable.VersionField(id = 1000)
    public final int zaa;
    public Bundle zab;
    public int[] zac;
    public int zad;
    public boolean zae;

    @SafeParcelable.Field(getter = "getColumns", id = 1)
    public final String[] zag;

    @SafeParcelable.Field(getter = "getWindows", id = 2)
    public final CursorWindow[] zah;

    @SafeParcelable.Field(getter = "getStatusCode", id = 3)
    public final int zai;

    @Nullable
    @SafeParcelable.Field(getter = "getMetadata", id = 4)
    public final Bundle zaj;
    public boolean zak;

    @SafeParcelable.Constructor
    public DataHolder(@SafeParcelable.Param(id = 1000) int i, @SafeParcelable.Param(id = 1) String[] strArr, @SafeParcelable.Param(id = 2) CursorWindow[] cursorWindowArr, @SafeParcelable.Param(id = 3) int i2, @Nullable @SafeParcelable.Param(id = 4) Bundle bundle) {
        this.zae = false;
        this.zak = true;
        this.zaa = i;
        this.zag = strArr;
        this.zah = cursorWindowArr;
        this.zai = i2;
        this.zaj = bundle;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NonNull
    @KeepForSdk
    public static Builder builder(@NonNull String[] strArr) {
        return new Builder(strArr, null, 0 == true ? 1 : 0);
    }

    @NonNull
    @KeepForSdk
    public static DataHolder empty(int i) {
        return new DataHolder(zaf, i, (Bundle) null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x0128, code lost:
    
        if (r5 != false) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x012a, code lost:
    
        android.util.Log.d("DataHolder", "Couldn't populate window data for row " + r4 + " - allocating new window.");
        r2.freeLastRow();
        r2 = new android.database.CursorWindow(false);
        r2.setStartPosition(r4);
        r2.setNumColumns(r13.zaa.length);
        r3.add(r2);
        r4 = r4 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x015a, code lost:
    
        r4 = r4 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0164, code lost:
    
        throw new com.google.android.gms.common.data.zad("Could not add the value to a new CursorWindow. The size of value may be larger than what a CursorWindow can handle.");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.database.CursorWindow[] zaf(com.google.android.gms.common.data.DataHolder.Builder r13, int r14) {
        /*
            Method dump skipped, instruction units count: 389
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.data.DataHolder.zaf(com.google.android.gms.common.data.DataHolder$Builder, int):android.database.CursorWindow[]");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    @KeepForSdk
    public void close() {
        synchronized (this) {
            try {
                if (!this.zae) {
                    this.zae = true;
                    int i = 0;
                    while (true) {
                        CursorWindow[] cursorWindowArr = this.zah;
                        if (i >= cursorWindowArr.length) {
                            break;
                        }
                        cursorWindowArr[i].close();
                        i++;
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void finalize() throws Throwable {
        try {
            if (this.zak && this.zah.length > 0 && !isClosed()) {
                close();
                Log.e("DataBuffer", "Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (internal object: " + toString() + ")");
            }
        } finally {
            super.finalize();
        }
    }

    @KeepForSdk
    public boolean getBoolean(@NonNull String str, int i, int i2) {
        zae(str, i);
        return this.zah[i2].getLong(i, this.zab.getInt(str)) == 1;
    }

    @NonNull
    @KeepForSdk
    public byte[] getByteArray(@NonNull String str, int i, int i2) {
        zae(str, i);
        return this.zah[i2].getBlob(i, this.zab.getInt(str));
    }

    @KeepForSdk
    public int getCount() {
        return this.zad;
    }

    @KeepForSdk
    public int getInteger(@NonNull String str, int i, int i2) {
        zae(str, i);
        return this.zah[i2].getInt(i, this.zab.getInt(str));
    }

    @KeepForSdk
    public long getLong(@NonNull String str, int i, int i2) {
        zae(str, i);
        return this.zah[i2].getLong(i, this.zab.getInt(str));
    }

    @Nullable
    @KeepForSdk
    public Bundle getMetadata() {
        return this.zaj;
    }

    @KeepForSdk
    public int getStatusCode() {
        return this.zai;
    }

    @NonNull
    @KeepForSdk
    public String getString(@NonNull String str, int i, int i2) {
        zae(str, i);
        return this.zah[i2].getString(i, this.zab.getInt(str));
    }

    @KeepForSdk
    public int getWindowIndex(int i) {
        int length;
        int i2 = 0;
        Preconditions.checkState(i >= 0 && i < this.zad);
        while (true) {
            int[] iArr = this.zac;
            length = iArr.length;
            if (i2 >= length) {
                break;
            }
            if (i < iArr[i2]) {
                i2--;
                break;
            }
            i2++;
        }
        return i2 == length ? i2 - 1 : i2;
    }

    @KeepForSdk
    public boolean hasColumn(@NonNull String str) {
        return this.zab.containsKey(str);
    }

    @KeepForSdk
    public boolean hasNull(@NonNull String str, int i, int i2) {
        zae(str, i);
        return this.zah[i2].isNull(i, this.zab.getInt(str));
    }

    @KeepForSdk
    public boolean isClosed() {
        boolean z;
        synchronized (this) {
            z = this.zae;
        }
        return z;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NonNull Parcel parcel, int i) {
        String[] strArr = this.zag;
        int iZza = SafeParcelWriter.zza(parcel, 20293);
        SafeParcelWriter.writeStringArray(parcel, 1, strArr, false);
        SafeParcelWriter.writeTypedArray(parcel, 2, this.zah, i, false);
        int i2 = this.zai;
        SafeParcelWriter.zzc(parcel, 3, 4);
        parcel.writeInt(i2);
        SafeParcelWriter.writeBundle(parcel, 4, this.zaj, false);
        int i3 = this.zaa;
        SafeParcelWriter.zzc(parcel, 1000, 4);
        parcel.writeInt(i3);
        SafeParcelWriter.zzb(parcel, iZza);
        if ((i & 1) != 0) {
            close();
        }
    }

    public final double zaa(@NonNull String str, int i, int i2) {
        zae(str, i);
        return this.zah[i2].getDouble(i, this.zab.getInt(str));
    }

    public final float zab(@NonNull String str, int i, int i2) {
        zae(str, i);
        return this.zah[i2].getFloat(i, this.zab.getInt(str));
    }

    public final void zac(@NonNull String str, int i, int i2, @NonNull CharArrayBuffer charArrayBuffer) {
        zae(str, i);
        this.zah[i2].copyStringToBuffer(i, this.zab.getInt(str), charArrayBuffer);
    }

    public final void zad() {
        this.zab = new Bundle();
        int i = 0;
        int i2 = 0;
        while (true) {
            String[] strArr = this.zag;
            if (i2 >= strArr.length) {
                break;
            }
            this.zab.putInt(strArr[i2], i2);
            i2++;
        }
        this.zac = new int[this.zah.length];
        int numRows = 0;
        while (true) {
            CursorWindow[] cursorWindowArr = this.zah;
            if (i >= cursorWindowArr.length) {
                this.zad = numRows;
                return;
            }
            this.zac[i] = numRows;
            numRows += this.zah[i].getNumRows() - (numRows - cursorWindowArr[i].getStartPosition());
            i++;
        }
    }

    public final void zae(String str, int i) {
        Bundle bundle = this.zab;
        if (bundle == null || !bundle.containsKey(str)) {
            throw new IllegalArgumentException("No such column: ".concat(String.valueOf(str)));
        }
        if (isClosed()) {
            throw new IllegalArgumentException("Buffer is closed.");
        }
        if (i < 0 || i >= this.zad) {
            throw new CursorIndexOutOfBoundsException(i, this.zad);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @KeepForSdk
    public static class Builder {
        public final String[] zaa;
        public final ArrayList zab;
        public final HashMap zac;

        public /* synthetic */ Builder(String[] strArr, String str, zac zacVar) {
            Preconditions.checkNotNull(strArr);
            this.zaa = strArr;
            this.zab = new ArrayList();
            this.zac = new HashMap();
        }

        @NonNull
        @KeepForSdk
        public DataHolder build(int i) {
            return new DataHolder(this, i, (Bundle) null);
        }

        @NonNull
        @CanIgnoreReturnValue
        @KeepForSdk
        public Builder withRow(@NonNull ContentValues contentValues) {
            Asserts.checkNotNull(contentValues);
            HashMap map = new HashMap(contentValues.size());
            for (Map.Entry<String, Object> entry : contentValues.valueSet()) {
                map.put(entry.getKey(), entry.getValue());
            }
            return zaa(map);
        }

        @NonNull
        @CanIgnoreReturnValue
        public Builder zaa(@NonNull HashMap map) {
            Asserts.checkNotNull(map);
            this.zab.add(map);
            return this;
        }

        @NonNull
        @KeepForSdk
        public DataHolder build(int i, @NonNull Bundle bundle) {
            return new DataHolder(this, i, bundle);
        }
    }

    public DataHolder(Builder builder, int i, @Nullable Bundle bundle) {
        this(builder.zaa, zaf(builder, -1), i, (Bundle) null);
    }

    @KeepForSdk
    public DataHolder(@NonNull String[] strArr, @NonNull CursorWindow[] cursorWindowArr, int i, @Nullable Bundle bundle) {
        this.zae = false;
        this.zak = true;
        this.zaa = 1;
        Preconditions.checkNotNull(strArr);
        this.zag = strArr;
        Preconditions.checkNotNull(cursorWindowArr);
        this.zah = cursorWindowArr;
        this.zai = i;
        this.zaj = bundle;
        zad();
    }

    /* JADX WARN: Finally extract failed */
    public DataHolder(@NonNull Cursor cursor, int i, @Nullable Bundle bundle) {
        int startPosition;
        CursorWrapper cursorWrapper = new CursorWrapper(cursor);
        String[] columnNames = cursorWrapper.getColumnNames();
        ArrayList arrayList = new ArrayList();
        try {
            int count = cursorWrapper.getCount();
            CursorWindow window = cursorWrapper.zza.getWindow();
            if (window == null || window.getStartPosition() != 0) {
                startPosition = 0;
            } else {
                window.acquireReference();
                cursorWrapper.setWindow(null);
                arrayList.add(window);
                startPosition = window.getNumRows();
            }
            while (startPosition < count) {
                if (!cursorWrapper.moveToPosition(startPosition)) {
                    break;
                }
                CursorWindow window2 = cursorWrapper.zza.getWindow();
                if (window2 != null) {
                    window2.acquireReference();
                    cursorWrapper.setWindow(null);
                } else {
                    window2 = new CursorWindow(false);
                    window2.setStartPosition(startPosition);
                    cursorWrapper.fillWindow(startPosition, window2);
                }
                if (window2.getNumRows() == 0) {
                    break;
                }
                arrayList.add(window2);
                startPosition = window2.getStartPosition() + window2.getNumRows();
            }
            cursorWrapper.close();
            this(columnNames, (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]), i, bundle);
        } catch (Throwable th) {
            cursorWrapper.close();
            throw th;
        }
    }
}
