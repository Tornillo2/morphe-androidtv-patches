package com.google.android.gms.internal.measurement;

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
public final class zznb {
    public static final zznb zza;
    public static final zznb zzb;
    public static final zznb zzc;
    public static final zznb zzd;
    public static final zznb zze;
    public static final zznb zzf;
    public static final zznb zzg;
    public static final zznb zzh;
    public static final zznb zzi;
    public static final zznb zzj;
    public static final zznb zzk;
    public static final zznb zzl;
    public static final zznb zzm;
    public static final zznb zzn;
    public static final zznb zzo;
    public static final zznb zzp;
    public static final zznb zzq;
    public static final zznb zzr;
    public static final /* synthetic */ zznb[] zzs;
    public final zznc zzt;

    static {
        zznb zznbVar = new zznb("DOUBLE", 0, zznc.DOUBLE, 1);
        zza = zznbVar;
        zznb zznbVar2 = new zznb("FLOAT", 1, zznc.FLOAT, 5);
        zzb = zznbVar2;
        zznc zzncVar = zznc.LONG;
        zznb zznbVar3 = new zznb("INT64", 2, zzncVar, 0);
        zzc = zznbVar3;
        zznb zznbVar4 = new zznb("UINT64", 3, zzncVar, 0);
        zzd = zznbVar4;
        zznc zzncVar2 = zznc.INT;
        zznb zznbVar5 = new zznb("INT32", 4, zzncVar2, 0);
        zze = zznbVar5;
        zznb zznbVar6 = new zznb("FIXED64", 5, zzncVar, 1);
        zzf = zznbVar6;
        zznb zznbVar7 = new zznb("FIXED32", 6, zzncVar2, 5);
        zzg = zznbVar7;
        zznb zznbVar8 = new zznb("BOOL", 7, zznc.BOOLEAN, 0);
        zzh = zznbVar8;
        zznb zznbVar9 = new zznb("STRING", 8, zznc.STRING, 2);
        zzi = zznbVar9;
        zznc zzncVar3 = zznc.MESSAGE;
        zznb zznbVar10 = new zznb("GROUP", 9, zzncVar3, 3);
        zzj = zznbVar10;
        zznb zznbVar11 = new zznb("MESSAGE", 10, zzncVar3, 2);
        zzk = zznbVar11;
        zznb zznbVar12 = new zznb("BYTES", 11, zznc.BYTE_STRING, 2);
        zzl = zznbVar12;
        zznb zznbVar13 = new zznb("UINT32", 12, zzncVar2, 0);
        zzm = zznbVar13;
        zznb zznbVar14 = new zznb("ENUM", 13, zznc.ENUM, 0);
        zzn = zznbVar14;
        zznb zznbVar15 = new zznb("SFIXED32", 14, zzncVar2, 5);
        zzo = zznbVar15;
        zznb zznbVar16 = new zznb("SFIXED64", 15, zzncVar, 1);
        zzp = zznbVar16;
        zznb zznbVar17 = new zznb("SINT32", 16, zzncVar2, 0);
        zzq = zznbVar17;
        zznb zznbVar18 = new zznb("SINT64", 17, zzncVar, 0);
        zzr = zznbVar18;
        zzs = new zznb[]{zznbVar, zznbVar2, zznbVar3, zznbVar4, zznbVar5, zznbVar6, zznbVar7, zznbVar8, zznbVar9, zznbVar10, zznbVar11, zznbVar12, zznbVar13, zznbVar14, zznbVar15, zznbVar16, zznbVar17, zznbVar18};
    }

    public zznb(String str, int i, zznc zzncVar, int i2) {
        this.zzt = zzncVar;
    }

    public static zznb[] values() {
        return (zznb[]) zzs.clone();
    }

    public final zznc zza() {
        return this.zzt;
    }
}
