package com.google.android.gms.internal.common;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class zzaa extends zzab {
    public Object[] zza = new Object[4];
    public int zzb = 0;
    public boolean zzc;

    public zzaa(int i) {
    }

    @CanIgnoreReturnValue
    public final zzaa zza(Object obj) {
        obj.getClass();
        int i = this.zzb;
        int i2 = i + 1;
        Object[] objArr = this.zza;
        int length = objArr.length;
        if (length < i2) {
            int i3 = length + (length >> 1) + 1;
            if (i3 < i2) {
                int iHighestOneBit = Integer.highestOneBit(i);
                i3 = iHighestOneBit + iHighestOneBit;
            }
            if (i3 < 0) {
                i3 = Integer.MAX_VALUE;
            }
            this.zza = Arrays.copyOf(objArr, i3);
            this.zzc = false;
        } else if (this.zzc) {
            this.zza = (Object[]) objArr.clone();
            this.zzc = false;
        }
        Object[] objArr2 = this.zza;
        int i4 = this.zzb;
        this.zzb = i4 + 1;
        objArr2[i4] = obj;
        return this;
    }
}
