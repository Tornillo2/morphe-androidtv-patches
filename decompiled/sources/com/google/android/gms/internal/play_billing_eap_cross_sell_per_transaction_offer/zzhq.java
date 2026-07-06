package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'zzc' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzhq {
    public static final zzhq zza;
    public static final zzhq zzb;
    public static final zzhq zzc;
    public static final zzhq zzd;
    public static final zzhq zze;
    public static final zzhq zzf;
    public static final zzhq zzg;
    public static final zzhq zzh;
    public static final zzhq zzi;
    public static final zzhq zzj;
    public static final zzhq zzk;
    public static final zzhq zzl;
    public static final zzhq zzm;
    public static final zzhq zzn;
    public static final zzhq zzo;
    public static final zzhq zzp;
    public static final zzhq zzq;
    public static final zzhq zzr;
    public static final /* synthetic */ zzhq[] zzs;
    public final zzhr zzt;
    public final int zzu;

    static {
        zzhq zzhqVar = new zzhq("DOUBLE", 0, zzhr.zzd, 1);
        zza = zzhqVar;
        zzhq zzhqVar2 = new zzhq("FLOAT", 1, zzhr.zzc, 5);
        zzb = zzhqVar2;
        zzhr zzhrVar = zzhr.zzb;
        zzhq zzhqVar3 = new zzhq("INT64", 2, zzhrVar, 0);
        zzc = zzhqVar3;
        zzhq zzhqVar4 = new zzhq("UINT64", 3, zzhrVar, 0);
        zzd = zzhqVar4;
        zzhr zzhrVar2 = zzhr.zza;
        zzhq zzhqVar5 = new zzhq("INT32", 4, zzhrVar2, 0);
        zze = zzhqVar5;
        zzhq zzhqVar6 = new zzhq("FIXED64", 5, zzhrVar, 1);
        zzf = zzhqVar6;
        zzhq zzhqVar7 = new zzhq("FIXED32", 6, zzhrVar2, 5);
        zzg = zzhqVar7;
        zzhq zzhqVar8 = new zzhq("BOOL", 7, zzhr.zze, 0);
        zzh = zzhqVar8;
        zzhq zzhqVar9 = new zzhq("STRING", 8, zzhr.zzf, 2);
        zzi = zzhqVar9;
        zzhr zzhrVar3 = zzhr.zzi;
        zzhq zzhqVar10 = new zzhq("GROUP", 9, zzhrVar3, 3);
        zzj = zzhqVar10;
        zzhq zzhqVar11 = new zzhq("MESSAGE", 10, zzhrVar3, 2);
        zzk = zzhqVar11;
        zzhq zzhqVar12 = new zzhq("BYTES", 11, zzhr.zzg, 2);
        zzl = zzhqVar12;
        zzhq zzhqVar13 = new zzhq("UINT32", 12, zzhrVar2, 0);
        zzm = zzhqVar13;
        zzhq zzhqVar14 = new zzhq("ENUM", 13, zzhr.zzh, 0);
        zzn = zzhqVar14;
        zzhq zzhqVar15 = new zzhq("SFIXED32", 14, zzhrVar2, 5);
        zzo = zzhqVar15;
        zzhq zzhqVar16 = new zzhq("SFIXED64", 15, zzhrVar, 1);
        zzp = zzhqVar16;
        zzhq zzhqVar17 = new zzhq("SINT32", 16, zzhrVar2, 0);
        zzq = zzhqVar17;
        zzhq zzhqVar18 = new zzhq("SINT64", 17, zzhrVar, 0);
        zzr = zzhqVar18;
        zzs = new zzhq[]{zzhqVar, zzhqVar2, zzhqVar3, zzhqVar4, zzhqVar5, zzhqVar6, zzhqVar7, zzhqVar8, zzhqVar9, zzhqVar10, zzhqVar11, zzhqVar12, zzhqVar13, zzhqVar14, zzhqVar15, zzhqVar16, zzhqVar17, zzhqVar18};
    }

    public zzhq(String str, int i, zzhr zzhrVar, int i2) {
        this.zzt = zzhrVar;
        this.zzu = i2;
    }

    public static zzhq[] values() {
        return (zzhq[]) zzs.clone();
    }

    public final zzhr zza() {
        return this.zzt;
    }
}
