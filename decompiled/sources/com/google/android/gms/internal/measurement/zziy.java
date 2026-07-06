package com.google.android.gms.internal.measurement;

import androidx.emoji2.text.flatbuffer.Utf8$UnpairedSurrogateException$$ExternalSyntheticOutline0;
import java.io.IOException;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class zziy extends zzix {
    public final byte[] zza;

    public zziy(byte[] bArr) {
        bArr.getClass();
        this.zza = bArr;
    }

    @Override // com.google.android.gms.internal.measurement.zzjb
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof zzjb) && zzd() == ((zzjb) obj).zzd()) {
            if (zzd() == 0) {
                return true;
            }
            if (!(obj instanceof zziy)) {
                return obj.equals(this);
            }
            zziy zziyVar = (zziy) obj;
            int i = this.zzc;
            int i2 = zziyVar.zzc;
            if (i == 0 || i2 == 0 || i == i2) {
                int iZzd = zzd();
                if (iZzd > zziyVar.zzd()) {
                    throw new IllegalArgumentException("Length too large: " + iZzd + zzd());
                }
                if (iZzd > zziyVar.zzd()) {
                    throw new IllegalArgumentException(Utf8$UnpairedSurrogateException$$ExternalSyntheticOutline0.m("Ran off end of other: 0, ", iZzd, ", ", zziyVar.zzd()));
                }
                byte[] bArr = this.zza;
                byte[] bArr2 = zziyVar.zza;
                int i3 = 0;
                int i4 = 0;
                while (i3 < iZzd) {
                    if (bArr[i3] == bArr2[i4]) {
                        i3++;
                        i4++;
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzjb
    public byte zza(int i) {
        return this.zza[i];
    }

    @Override // com.google.android.gms.internal.measurement.zzjb
    public byte zzb(int i) {
        return this.zza[i];
    }

    public int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.measurement.zzjb
    public int zzd() {
        return this.zza.length;
    }

    @Override // com.google.android.gms.internal.measurement.zzjb
    public final int zze(int i, int i2, int i3) {
        return zzkk.zzd(i, this.zza, 0, i3);
    }

    @Override // com.google.android.gms.internal.measurement.zzjb
    public final zzjb zzf(int i, int i2) {
        int iZzj = zzjb.zzj(0, i2, zzd());
        return iZzj == 0 ? zzjb.zzb : new zziv(this.zza, 0, iZzj);
    }

    @Override // com.google.android.gms.internal.measurement.zzjb
    public final String zzg(Charset charset) {
        return new String(this.zza, 0, zzd(), charset);
    }

    @Override // com.google.android.gms.internal.measurement.zzjb
    public final void zzh(zzir zzirVar) throws IOException {
        ((zzjg) zzirVar).zzc(this.zza, 0, zzd());
    }

    @Override // com.google.android.gms.internal.measurement.zzjb
    public final boolean zzi() {
        return zzna.zzf(this.zza, 0, zzd());
    }
}
