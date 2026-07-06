package com.google.android.gms.internal.measurement;

import androidx.collection.ObjectListKt$$ExternalSyntheticOutline1;
import androidx.emoji2.text.flatbuffer.Utf8$UnpairedSurrogateException$$ExternalSyntheticOutline0;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zzjb implements Iterable, Serializable {
    public static final Comparator zza;
    public static final zzjb zzb = new zziy(zzkk.zzd);
    public static final zzja zzd;
    public int zzc = 0;

    static {
        int i = zzin.zza;
        zzd = new zzja();
        zza = new zzit();
    }

    public static int zzj(int i, int i2, int i3) {
        int i4 = i2 - i;
        if ((i | i2 | i4 | (i3 - i2)) >= 0) {
            return i4;
        }
        if (i < 0) {
            throw new IndexOutOfBoundsException(ObjectListKt$$ExternalSyntheticOutline1.m("Beginning index: ", i, " < 0"));
        }
        if (i2 < i) {
            throw new IndexOutOfBoundsException(Utf8$UnpairedSurrogateException$$ExternalSyntheticOutline0.m("Beginning index larger than ending index: ", i, ", ", i2));
        }
        throw new IndexOutOfBoundsException(Utf8$UnpairedSurrogateException$$ExternalSyntheticOutline0.m("End index: ", i2, " >= ", i3));
    }

    public static zzjb zzl(byte[] bArr, int i, int i2) {
        zzj(i, i + i2, bArr.length);
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return new zziy(bArr2);
    }

    public static zzjb zzm(String str) {
        return new zziy(str.getBytes(zzkk.zzb));
    }

    public abstract boolean equals(Object obj);

    public final int hashCode() {
        int iZze = this.zzc;
        if (iZze == 0) {
            int iZzd = zzd();
            iZze = zze(iZzd, 0, iZzd);
            if (iZze == 0) {
                iZze = 1;
            }
            this.zzc = iZze;
        }
        return iZze;
    }

    @Override // java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return new zzis(this);
    }

    public final String toString() {
        return String.format(Locale.ROOT, "<ByteString@%s size=%d contents=\"%s\">", Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(zzd()), zzd() <= 50 ? zzmj.zza(this) : zzmj.zza(zzf(0, 47)).concat("..."));
    }

    public abstract byte zza(int i);

    public abstract byte zzb(int i);

    public abstract int zzd();

    public abstract int zze(int i, int i2, int i3);

    public abstract zzjb zzf(int i, int i2);

    public abstract String zzg(Charset charset);

    public abstract void zzh(zzir zzirVar) throws IOException;

    public abstract boolean zzi();

    public final int zzk() {
        return this.zzc;
    }

    public final String zzn(Charset charset) {
        return zzd() == 0 ? "" : zzg(charset);
    }
}
