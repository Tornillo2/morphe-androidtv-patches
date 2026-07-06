package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

import java.io.IOException;
import java.util.Locale;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzek extends zzen {
    public final byte[] zzc;
    public final int zzd;
    public int zze;

    public zzek(byte[] bArr, int i, int i2) {
        int length = bArr.length;
        if (((length - i2) | i2) < 0) {
            throw new IllegalArgumentException(String.format(Locale.US, "Array range is invalid. Buffer.length=%d, offset=%d, length=%d", Integer.valueOf(length), 0, Integer.valueOf(i2)));
        }
        this.zzc = bArr;
        this.zze = 0;
        this.zzd = i2;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzen
    public final int zza() {
        return this.zzd - this.zze;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzen
    public final void zzb(byte b) throws IOException {
        int i = this.zze;
        try {
            int i2 = i + 1;
            try {
                this.zzc[i] = b;
                this.zze = i2;
            } catch (IndexOutOfBoundsException e) {
                e = e;
                i = i2;
                throw new zzel(i, this.zzd, 1, e);
            }
        } catch (IndexOutOfBoundsException e2) {
            e = e2;
        }
    }

    public final void zzc(byte[] bArr, int i, int i2) throws IOException {
        try {
            System.arraycopy(bArr, 0, this.zzc, this.zze, i2);
            this.zze += i2;
        } catch (IndexOutOfBoundsException e) {
            throw new zzel(this.zze, this.zzd, i2, e);
        }
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzen
    public final void zzd(int i, boolean z) throws IOException {
        zzv(i << 3);
        zzb(z ? (byte) 1 : (byte) 0);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzen
    public final void zze(int i, zzeg zzegVar) throws IOException {
        zzv((i << 3) | 2);
        zzf(zzegVar);
    }

    public final void zzf(zzeg zzegVar) throws IOException {
        zzv(zzegVar.zzd());
        zzegVar.zzg(this);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzen
    public final void zzg(int i, int i2) throws IOException {
        zzv((i << 3) | 5);
        zzh(i2);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzen
    public final void zzh(int i) throws IOException {
        int i2 = this.zze;
        try {
            byte[] bArr = this.zzc;
            bArr[i2] = (byte) i;
            bArr[i2 + 1] = (byte) (i >> 8);
            bArr[i2 + 2] = (byte) (i >> 16);
            bArr[i2 + 3] = (byte) (i >> 24);
            this.zze = i2 + 4;
        } catch (IndexOutOfBoundsException e) {
            throw new zzel(i2, this.zzd, 4, e);
        }
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzen
    public final void zzi(int i, long j) throws IOException {
        zzv((i << 3) | 1);
        zzj(j);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzen
    public final void zzj(long j) throws IOException {
        int i = this.zze;
        try {
            byte[] bArr = this.zzc;
            bArr[i] = (byte) j;
            bArr[i + 1] = (byte) (j >> 8);
            bArr[i + 2] = (byte) (j >> 16);
            bArr[i + 3] = (byte) (j >> 24);
            bArr[i + 4] = (byte) (j >> 32);
            bArr[i + 5] = (byte) (j >> 40);
            bArr[i + 6] = (byte) (j >> 48);
            bArr[i + 7] = (byte) (j >> 56);
            this.zze = i + 8;
        } catch (IndexOutOfBoundsException e) {
            throw new zzel(i, this.zzd, 8, e);
        }
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzen
    public final void zzk(int i, int i2) throws IOException {
        zzv(i << 3);
        zzl(i2);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzen
    public final void zzl(int i) throws IOException {
        if (i >= 0) {
            zzv(i);
        } else {
            zzx(i);
        }
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzen
    public final void zzm(byte[] bArr, int i, int i2) throws IOException {
        zzc(bArr, 0, i2);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzen
    public final void zzn(int i, zzgj zzgjVar, zzgt zzgtVar) throws IOException {
        zzv((i << 3) | 2);
        zzv(((zzdq) zzgjVar).zze(zzgtVar));
        zzgtVar.zzi(zzgjVar, this.zza);
    }

    public final void zzo(zzgj zzgjVar) throws IOException {
        zzv(zzgjVar.zzj());
        zzgjVar.zzL(this);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzen
    public final void zzp(int i, zzgj zzgjVar) throws IOException {
        zzv(11);
        zzu(2, i);
        zzv(26);
        zzo(zzgjVar);
        zzv(12);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzen
    public final void zzq(int i, zzeg zzegVar) throws IOException {
        zzv(11);
        zzu(2, i);
        zze(3, zzegVar);
        zzv(12);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzen
    public final void zzr(int i, String str) throws IOException {
        zzv((i << 3) | 2);
        zzs(str);
    }

    public final void zzs(String str) throws IOException {
        int i = this.zze;
        try {
            int iZzC = zzen.zzC(str.length() * 3);
            int iZzC2 = zzen.zzC(str.length());
            if (iZzC2 != iZzC) {
                zzv(zzhp.zzc(str));
                byte[] bArr = this.zzc;
                int i2 = this.zze;
                this.zze = zzhp.zzb(str, bArr, i2, this.zzd - i2);
                return;
            }
            int i3 = i + iZzC2;
            this.zze = i3;
            int iZzb = zzhp.zzb(str, this.zzc, i3, this.zzd - i3);
            this.zze = i;
            zzv((iZzb - i) - iZzC2);
            this.zze = iZzb;
        } catch (zzho e) {
            this.zze = i;
            zzF(str, e);
        } catch (IndexOutOfBoundsException e2) {
            throw new zzel(e2);
        }
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzen
    public final void zzt(int i, int i2) throws IOException {
        zzv((i << 3) | i2);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzen
    public final void zzu(int i, int i2) throws IOException {
        zzv(i << 3);
        zzv(i2);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzen
    public final void zzv(int i) throws IOException {
        int i2;
        IndexOutOfBoundsException indexOutOfBoundsException;
        int i3 = this.zze;
        while ((i & (-128)) != 0) {
            try {
                i2 = i3 + 1;
                try {
                    this.zzc[i3] = (byte) (i | 128);
                    i >>>= 7;
                    i3 = i2;
                } catch (IndexOutOfBoundsException e) {
                    indexOutOfBoundsException = e;
                    i3 = i2;
                    throw new zzel(i3, this.zzd, 1, indexOutOfBoundsException);
                }
            } catch (IndexOutOfBoundsException e2) {
                indexOutOfBoundsException = e2;
                throw new zzel(i3, this.zzd, 1, indexOutOfBoundsException);
            }
        }
        i2 = i3 + 1;
        this.zzc[i3] = (byte) i;
        this.zze = i2;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzen
    public final void zzw(int i, long j) throws IOException {
        zzv(i << 3);
        zzx(j);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzen
    public final void zzx(long j) throws IOException {
        int i;
        IndexOutOfBoundsException indexOutOfBoundsException;
        int i2 = this.zze;
        if (!zzen.zzd || this.zzd - i2 < 10) {
            int i3 = i2;
            while ((j & (-128)) != 0) {
                try {
                    int i4 = i3 + 1;
                    try {
                        this.zzc[i3] = (byte) (((int) j) | 128);
                        j >>>= 7;
                        i3 = i4;
                    } catch (IndexOutOfBoundsException e) {
                        indexOutOfBoundsException = e;
                        i3 = i4;
                        throw new zzel(i3, this.zzd, 1, indexOutOfBoundsException);
                    }
                } catch (IndexOutOfBoundsException e2) {
                    indexOutOfBoundsException = e2;
                }
            }
            i = i3 + 1;
            try {
                this.zzc[i3] = (byte) j;
            } catch (IndexOutOfBoundsException e3) {
                indexOutOfBoundsException = e3;
                i3 = i;
                throw new zzel(i3, this.zzd, 1, indexOutOfBoundsException);
            }
        } else {
            while ((j & (-128)) != 0) {
                zzhm.zzn(this.zzc, i2, (byte) (((int) j) | 128));
                j >>>= 7;
                i2++;
            }
            i = i2 + 1;
            zzhm.zzn(this.zzc, i2, (byte) j);
        }
        this.zze = i;
    }
}
