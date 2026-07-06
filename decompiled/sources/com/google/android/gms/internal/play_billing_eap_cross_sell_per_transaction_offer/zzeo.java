package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

import java.io.IOException;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzeo implements zzhs {
    public final zzen zza;

    public zzeo(zzen zzenVar) {
        byte[] bArr = zzfm.zzb;
        this.zza = zzenVar;
        zzenVar.zza = this;
    }

    public static zzeo zza(zzen zzenVar) {
        zzeo zzeoVar = zzenVar.zza;
        return zzeoVar != null ? zzeoVar : new zzeo(zzenVar);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhs
    public final void zzA(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzfy)) {
            if (!z) {
                while (i2 < list.size()) {
                    this.zza.zzi(i, ((Long) list.get(i2)).longValue());
                    i2++;
                }
                return;
            }
            zzen zzenVar = this.zza;
            zzenVar.zzt(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                ((Long) list.get(i4)).getClass();
                i3 += 8;
            }
            zzenVar.zzv(i3);
            while (i2 < list.size()) {
                zzenVar.zzj(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        zzfy zzfyVar = (zzfy) list;
        if (!z) {
            while (i2 < zzfyVar.zzc) {
                this.zza.zzi(i, zzfyVar.zze(i2));
                i2++;
            }
            return;
        }
        zzen zzenVar2 = this.zza;
        zzenVar2.zzt(i, 2);
        int i5 = 0;
        for (int i6 = 0; i6 < zzfyVar.zzc; i6++) {
            zzfyVar.zze(i6);
            i5 += 8;
        }
        zzenVar2.zzv(i5);
        while (i2 < zzfyVar.zzc) {
            zzenVar2.zzj(zzfyVar.zze(i2));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhs
    public final void zzB(int i, int i2) throws IOException {
        this.zza.zzu(i, (i2 >> 31) ^ (i2 + i2));
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhs
    public final void zzC(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzfh)) {
            if (!z) {
                while (i2 < list.size()) {
                    zzen zzenVar = this.zza;
                    int iIntValue = ((Integer) list.get(i2)).intValue();
                    zzenVar.zzu(i, (iIntValue >> 31) ^ (iIntValue + iIntValue));
                    i2++;
                }
                return;
            }
            zzen zzenVar2 = this.zza;
            zzenVar2.zzt(i, 2);
            int iZzC = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                int iIntValue2 = ((Integer) list.get(i3)).intValue();
                iZzC += zzen.zzC((iIntValue2 >> 31) ^ (iIntValue2 + iIntValue2));
            }
            zzenVar2.zzv(iZzC);
            while (i2 < list.size()) {
                int iIntValue3 = ((Integer) list.get(i2)).intValue();
                zzenVar2.zzv((iIntValue3 >> 31) ^ (iIntValue3 + iIntValue3));
                i2++;
            }
            return;
        }
        zzfh zzfhVar = (zzfh) list;
        if (!z) {
            while (i2 < zzfhVar.zzd) {
                zzen zzenVar3 = this.zza;
                int iZze = zzfhVar.zze(i2);
                zzenVar3.zzu(i, (iZze >> 31) ^ (iZze + iZze));
                i2++;
            }
            return;
        }
        zzen zzenVar4 = this.zza;
        zzenVar4.zzt(i, 2);
        int iZzC2 = 0;
        for (int i4 = 0; i4 < zzfhVar.zzd; i4++) {
            int iZze2 = zzfhVar.zze(i4);
            iZzC2 += zzen.zzC((iZze2 >> 31) ^ (iZze2 + iZze2));
        }
        zzenVar4.zzv(iZzC2);
        while (i2 < zzfhVar.zzd) {
            int iZze3 = zzfhVar.zze(i2);
            zzenVar4.zzv((iZze3 >> 31) ^ (iZze3 + iZze3));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhs
    public final void zzD(int i, long j) throws IOException {
        this.zza.zzw(i, (j >> 63) ^ (j + j));
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhs
    public final void zzE(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzfy)) {
            if (!z) {
                while (i2 < list.size()) {
                    zzen zzenVar = this.zza;
                    long jLongValue = ((Long) list.get(i2)).longValue();
                    zzenVar.zzw(i, (jLongValue >> 63) ^ (jLongValue + jLongValue));
                    i2++;
                }
                return;
            }
            zzen zzenVar2 = this.zza;
            zzenVar2.zzt(i, 2);
            int iZzD = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                long jLongValue2 = ((Long) list.get(i3)).longValue();
                iZzD += zzen.zzD((jLongValue2 >> 63) ^ (jLongValue2 + jLongValue2));
            }
            zzenVar2.zzv(iZzD);
            while (i2 < list.size()) {
                long jLongValue3 = ((Long) list.get(i2)).longValue();
                zzenVar2.zzx((jLongValue3 >> 63) ^ (jLongValue3 + jLongValue3));
                i2++;
            }
            return;
        }
        zzfy zzfyVar = (zzfy) list;
        if (!z) {
            while (i2 < zzfyVar.zzc) {
                zzen zzenVar3 = this.zza;
                long jZze = zzfyVar.zze(i2);
                zzenVar3.zzw(i, (jZze >> 63) ^ (jZze + jZze));
                i2++;
            }
            return;
        }
        zzen zzenVar4 = this.zza;
        zzenVar4.zzt(i, 2);
        int iZzD2 = 0;
        for (int i4 = 0; i4 < zzfyVar.zzc; i4++) {
            long jZze2 = zzfyVar.zze(i4);
            iZzD2 += zzen.zzD((jZze2 >> 63) ^ (jZze2 + jZze2));
        }
        zzenVar4.zzv(iZzD2);
        while (i2 < zzfyVar.zzc) {
            long jZze3 = zzfyVar.zze(i2);
            zzenVar4.zzx((jZze3 >> 63) ^ (jZze3 + jZze3));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhs
    @Deprecated
    public final void zzF(int i) throws IOException {
        this.zza.zzt(i, 3);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhs
    public final void zzG(int i, String str) throws IOException {
        this.zza.zzr(i, str);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhs
    public final void zzH(int i, List list) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzfv)) {
            while (i2 < list.size()) {
                this.zza.zzr(i, (String) list.get(i2));
                i2++;
            }
            return;
        }
        zzfv zzfvVar = (zzfv) list;
        while (i2 < list.size()) {
            Object objZza = zzfvVar.zza();
            if (objZza instanceof String) {
                this.zza.zzr(i, (String) objZza);
            } else {
                this.zza.zze(i, (zzeg) objZza);
            }
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhs
    public final void zzI(int i, int i2) throws IOException {
        this.zza.zzu(i, i2);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhs
    public final void zzJ(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzfh)) {
            if (!z) {
                while (i2 < list.size()) {
                    this.zza.zzu(i, ((Integer) list.get(i2)).intValue());
                    i2++;
                }
                return;
            }
            zzen zzenVar = this.zza;
            zzenVar.zzt(i, 2);
            int iZzC = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzC += zzen.zzC(((Integer) list.get(i3)).intValue());
            }
            zzenVar.zzv(iZzC);
            while (i2 < list.size()) {
                zzenVar.zzv(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        zzfh zzfhVar = (zzfh) list;
        if (!z) {
            while (i2 < zzfhVar.zzd) {
                this.zza.zzu(i, zzfhVar.zze(i2));
                i2++;
            }
            return;
        }
        zzen zzenVar2 = this.zza;
        zzenVar2.zzt(i, 2);
        int iZzC2 = 0;
        for (int i4 = 0; i4 < zzfhVar.zzd; i4++) {
            iZzC2 += zzen.zzC(zzfhVar.zze(i4));
        }
        zzenVar2.zzv(iZzC2);
        while (i2 < zzfhVar.zzd) {
            zzenVar2.zzv(zzfhVar.zze(i2));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhs
    public final void zzK(int i, long j) throws IOException {
        this.zza.zzw(i, j);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhs
    public final void zzL(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzfy)) {
            if (!z) {
                while (i2 < list.size()) {
                    this.zza.zzw(i, ((Long) list.get(i2)).longValue());
                    i2++;
                }
                return;
            }
            zzen zzenVar = this.zza;
            zzenVar.zzt(i, 2);
            int iZzD = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzD += zzen.zzD(((Long) list.get(i3)).longValue());
            }
            zzenVar.zzv(iZzD);
            while (i2 < list.size()) {
                zzenVar.zzx(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        zzfy zzfyVar = (zzfy) list;
        if (!z) {
            while (i2 < zzfyVar.zzc) {
                this.zza.zzw(i, zzfyVar.zze(i2));
                i2++;
            }
            return;
        }
        zzen zzenVar2 = this.zza;
        zzenVar2.zzt(i, 2);
        int iZzD2 = 0;
        for (int i4 = 0; i4 < zzfyVar.zzc; i4++) {
            iZzD2 += zzen.zzD(zzfyVar.zze(i4));
        }
        zzenVar2.zzv(iZzD2);
        while (i2 < zzfyVar.zzc) {
            zzenVar2.zzx(zzfyVar.zze(i2));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhs
    public final void zzb(int i, boolean z) throws IOException {
        this.zza.zzd(i, z);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhs
    public final void zzc(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzdw)) {
            if (!z) {
                while (i2 < list.size()) {
                    this.zza.zzd(i, ((Boolean) list.get(i2)).booleanValue());
                    i2++;
                }
                return;
            }
            zzen zzenVar = this.zza;
            zzenVar.zzt(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                ((Boolean) list.get(i4)).getClass();
                i3++;
            }
            zzenVar.zzv(i3);
            while (i2 < list.size()) {
                zzenVar.zzb(((Boolean) list.get(i2)).booleanValue() ? (byte) 1 : (byte) 0);
                i2++;
            }
            return;
        }
        zzdw zzdwVar = (zzdw) list;
        if (!z) {
            while (i2 < zzdwVar.zzc) {
                this.zza.zzd(i, zzdwVar.zzf(i2));
                i2++;
            }
            return;
        }
        zzen zzenVar2 = this.zza;
        zzenVar2.zzt(i, 2);
        int i5 = 0;
        for (int i6 = 0; i6 < zzdwVar.zzc; i6++) {
            zzdwVar.zzf(i6);
            i5++;
        }
        zzenVar2.zzv(i5);
        while (i2 < zzdwVar.zzc) {
            zzenVar2.zzb(zzdwVar.zzf(i2) ? (byte) 1 : (byte) 0);
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhs
    public final void zzd(int i, zzeg zzegVar) throws IOException {
        this.zza.zze(i, zzegVar);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhs
    public final void zze(int i, List list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.zza.zze(i, (zzeg) list.get(i2));
        }
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhs
    public final void zzf(int i, double d) throws IOException {
        this.zza.zzi(i, Double.doubleToRawLongBits(d));
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhs
    public final void zzg(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzep)) {
            if (!z) {
                while (i2 < list.size()) {
                    this.zza.zzi(i, Double.doubleToRawLongBits(((Double) list.get(i2)).doubleValue()));
                    i2++;
                }
                return;
            }
            zzen zzenVar = this.zza;
            zzenVar.zzt(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                ((Double) list.get(i4)).getClass();
                i3 += 8;
            }
            zzenVar.zzv(i3);
            while (i2 < list.size()) {
                zzenVar.zzj(Double.doubleToRawLongBits(((Double) list.get(i2)).doubleValue()));
                i2++;
            }
            return;
        }
        zzep zzepVar = (zzep) list;
        if (!z) {
            while (i2 < zzepVar.zzc) {
                this.zza.zzi(i, Double.doubleToRawLongBits(zzepVar.zze(i2)));
                i2++;
            }
            return;
        }
        zzen zzenVar2 = this.zza;
        zzenVar2.zzt(i, 2);
        int i5 = 0;
        for (int i6 = 0; i6 < zzepVar.zzc; i6++) {
            zzepVar.zze(i6);
            i5 += 8;
        }
        zzenVar2.zzv(i5);
        while (i2 < zzepVar.zzc) {
            zzenVar2.zzj(Double.doubleToRawLongBits(zzepVar.zze(i2)));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhs
    @Deprecated
    public final void zzh(int i) throws IOException {
        this.zza.zzt(i, 4);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhs
    public final void zzi(int i, int i2) throws IOException {
        this.zza.zzk(i, i2);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhs
    public final void zzj(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzfh)) {
            if (!z) {
                while (i2 < list.size()) {
                    this.zza.zzk(i, ((Integer) list.get(i2)).intValue());
                    i2++;
                }
                return;
            }
            zzen zzenVar = this.zza;
            zzenVar.zzt(i, 2);
            int iZzD = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzD += zzen.zzD(((Integer) list.get(i3)).intValue());
            }
            zzenVar.zzv(iZzD);
            while (i2 < list.size()) {
                zzenVar.zzl(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        zzfh zzfhVar = (zzfh) list;
        if (!z) {
            while (i2 < zzfhVar.zzd) {
                this.zza.zzk(i, zzfhVar.zze(i2));
                i2++;
            }
            return;
        }
        zzen zzenVar2 = this.zza;
        zzenVar2.zzt(i, 2);
        int iZzD2 = 0;
        for (int i4 = 0; i4 < zzfhVar.zzd; i4++) {
            iZzD2 += zzen.zzD(zzfhVar.zze(i4));
        }
        zzenVar2.zzv(iZzD2);
        while (i2 < zzfhVar.zzd) {
            zzenVar2.zzl(zzfhVar.zze(i2));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhs
    public final void zzk(int i, int i2) throws IOException {
        this.zza.zzg(i, i2);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhs
    public final void zzl(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzfh)) {
            if (!z) {
                while (i2 < list.size()) {
                    this.zza.zzg(i, ((Integer) list.get(i2)).intValue());
                    i2++;
                }
                return;
            }
            zzen zzenVar = this.zza;
            zzenVar.zzt(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                ((Integer) list.get(i4)).getClass();
                i3 += 4;
            }
            zzenVar.zzv(i3);
            while (i2 < list.size()) {
                zzenVar.zzh(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        zzfh zzfhVar = (zzfh) list;
        if (!z) {
            while (i2 < zzfhVar.zzd) {
                this.zza.zzg(i, zzfhVar.zze(i2));
                i2++;
            }
            return;
        }
        zzen zzenVar2 = this.zza;
        zzenVar2.zzt(i, 2);
        int i5 = 0;
        for (int i6 = 0; i6 < zzfhVar.zzd; i6++) {
            zzfhVar.zze(i6);
            i5 += 4;
        }
        zzenVar2.zzv(i5);
        while (i2 < zzfhVar.zzd) {
            zzenVar2.zzh(zzfhVar.zze(i2));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhs
    public final void zzm(int i, long j) throws IOException {
        this.zza.zzi(i, j);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhs
    public final void zzn(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzfy)) {
            if (!z) {
                while (i2 < list.size()) {
                    this.zza.zzi(i, ((Long) list.get(i2)).longValue());
                    i2++;
                }
                return;
            }
            zzen zzenVar = this.zza;
            zzenVar.zzt(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                ((Long) list.get(i4)).getClass();
                i3 += 8;
            }
            zzenVar.zzv(i3);
            while (i2 < list.size()) {
                zzenVar.zzj(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        zzfy zzfyVar = (zzfy) list;
        if (!z) {
            while (i2 < zzfyVar.zzc) {
                this.zza.zzi(i, zzfyVar.zze(i2));
                i2++;
            }
            return;
        }
        zzen zzenVar2 = this.zza;
        zzenVar2.zzt(i, 2);
        int i5 = 0;
        for (int i6 = 0; i6 < zzfyVar.zzc; i6++) {
            zzfyVar.zze(i6);
            i5 += 8;
        }
        zzenVar2.zzv(i5);
        while (i2 < zzfyVar.zzc) {
            zzenVar2.zzj(zzfyVar.zze(i2));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhs
    public final void zzo(int i, float f) throws IOException {
        this.zza.zzg(i, Float.floatToRawIntBits(f));
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhs
    public final void zzp(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzez)) {
            if (!z) {
                while (i2 < list.size()) {
                    this.zza.zzg(i, Float.floatToRawIntBits(((Float) list.get(i2)).floatValue()));
                    i2++;
                }
                return;
            }
            zzen zzenVar = this.zza;
            zzenVar.zzt(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                ((Float) list.get(i4)).getClass();
                i3 += 4;
            }
            zzenVar.zzv(i3);
            while (i2 < list.size()) {
                zzenVar.zzh(Float.floatToRawIntBits(((Float) list.get(i2)).floatValue()));
                i2++;
            }
            return;
        }
        zzez zzezVar = (zzez) list;
        if (!z) {
            while (i2 < zzezVar.zzc) {
                this.zza.zzg(i, Float.floatToRawIntBits(zzezVar.zze(i2)));
                i2++;
            }
            return;
        }
        zzen zzenVar2 = this.zza;
        zzenVar2.zzt(i, 2);
        int i5 = 0;
        for (int i6 = 0; i6 < zzezVar.zzc; i6++) {
            zzezVar.zze(i6);
            i5 += 4;
        }
        zzenVar2.zzv(i5);
        while (i2 < zzezVar.zzc) {
            zzenVar2.zzh(Float.floatToRawIntBits(zzezVar.zze(i2)));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhs
    public final void zzq(int i, Object obj, zzgt zzgtVar) throws IOException {
        zzen zzenVar = this.zza;
        zzenVar.zzt(i, 3);
        zzgtVar.zzi((zzgj) obj, zzenVar.zza);
        zzenVar.zzt(i, 4);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhs
    public final void zzr(int i, int i2) throws IOException {
        this.zza.zzk(i, i2);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhs
    public final void zzs(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzfh)) {
            if (!z) {
                while (i2 < list.size()) {
                    this.zza.zzk(i, ((Integer) list.get(i2)).intValue());
                    i2++;
                }
                return;
            }
            zzen zzenVar = this.zza;
            zzenVar.zzt(i, 2);
            int iZzD = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzD += zzen.zzD(((Integer) list.get(i3)).intValue());
            }
            zzenVar.zzv(iZzD);
            while (i2 < list.size()) {
                zzenVar.zzl(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        zzfh zzfhVar = (zzfh) list;
        if (!z) {
            while (i2 < zzfhVar.zzd) {
                this.zza.zzk(i, zzfhVar.zze(i2));
                i2++;
            }
            return;
        }
        zzen zzenVar2 = this.zza;
        zzenVar2.zzt(i, 2);
        int iZzD2 = 0;
        for (int i4 = 0; i4 < zzfhVar.zzd; i4++) {
            iZzD2 += zzen.zzD(zzfhVar.zze(i4));
        }
        zzenVar2.zzv(iZzD2);
        while (i2 < zzfhVar.zzd) {
            zzenVar2.zzl(zzfhVar.zze(i2));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhs
    public final void zzt(int i, long j) throws IOException {
        this.zza.zzw(i, j);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhs
    public final void zzu(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzfy)) {
            if (!z) {
                while (i2 < list.size()) {
                    this.zza.zzw(i, ((Long) list.get(i2)).longValue());
                    i2++;
                }
                return;
            }
            zzen zzenVar = this.zza;
            zzenVar.zzt(i, 2);
            int iZzD = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzD += zzen.zzD(((Long) list.get(i3)).longValue());
            }
            zzenVar.zzv(iZzD);
            while (i2 < list.size()) {
                zzenVar.zzx(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        zzfy zzfyVar = (zzfy) list;
        if (!z) {
            while (i2 < zzfyVar.zzc) {
                this.zza.zzw(i, zzfyVar.zze(i2));
                i2++;
            }
            return;
        }
        zzen zzenVar2 = this.zza;
        zzenVar2.zzt(i, 2);
        int iZzD2 = 0;
        for (int i4 = 0; i4 < zzfyVar.zzc; i4++) {
            iZzD2 += zzen.zzD(zzfyVar.zze(i4));
        }
        zzenVar2.zzv(iZzD2);
        while (i2 < zzfyVar.zzc) {
            zzenVar2.zzx(zzfyVar.zze(i2));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhs
    public final void zzv(int i, Object obj, zzgt zzgtVar) throws IOException {
        this.zza.zzn(i, (zzgj) obj, zzgtVar);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhs
    public final void zzw(int i, Object obj) throws IOException {
        if (obj instanceof zzeg) {
            this.zza.zzq(i, (zzeg) obj);
        } else {
            this.zza.zzp(i, (zzgj) obj);
        }
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhs
    public final void zzx(int i, int i2) throws IOException {
        this.zza.zzg(i, i2);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhs
    public final void zzy(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzfh)) {
            if (!z) {
                while (i2 < list.size()) {
                    this.zza.zzg(i, ((Integer) list.get(i2)).intValue());
                    i2++;
                }
                return;
            }
            zzen zzenVar = this.zza;
            zzenVar.zzt(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                ((Integer) list.get(i4)).getClass();
                i3 += 4;
            }
            zzenVar.zzv(i3);
            while (i2 < list.size()) {
                zzenVar.zzh(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        zzfh zzfhVar = (zzfh) list;
        if (!z) {
            while (i2 < zzfhVar.zzd) {
                this.zza.zzg(i, zzfhVar.zze(i2));
                i2++;
            }
            return;
        }
        zzen zzenVar2 = this.zza;
        zzenVar2.zzt(i, 2);
        int i5 = 0;
        for (int i6 = 0; i6 < zzfhVar.zzd; i6++) {
            zzfhVar.zze(i6);
            i5 += 4;
        }
        zzenVar2.zzv(i5);
        while (i2 < zzfhVar.zzd) {
            zzenVar2.zzh(zzfhVar.zze(i2));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzhs
    public final void zzz(int i, long j) throws IOException {
        this.zza.zzi(i, j);
    }
}
