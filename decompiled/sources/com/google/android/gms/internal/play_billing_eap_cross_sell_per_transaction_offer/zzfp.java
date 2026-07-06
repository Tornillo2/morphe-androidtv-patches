package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'zzb' uses external variables
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
public final class zzfp {
    public static final zzfp zza;
    public static final zzfp zzb;
    public static final zzfp zzc;
    public static final zzfp zzd;
    public static final zzfp zze;
    public static final zzfp zzf;
    public static final zzfp zzg;
    public static final zzfp zzh;
    public static final zzfp zzi;
    public static final zzfp zzj;
    public static final /* synthetic */ zzfp[] zzk;
    public final Class zzl;

    static {
        zzfp zzfpVar = new zzfp("VOID", 0, Void.class, Void.class, null);
        zza = zzfpVar;
        Class cls = Integer.TYPE;
        zzfp zzfpVar2 = new zzfp("INT", 1, cls, Integer.class, 0);
        zzb = zzfpVar2;
        zzfp zzfpVar3 = new zzfp("LONG", 2, Long.TYPE, Long.class, 0L);
        zzc = zzfpVar3;
        zzfp zzfpVar4 = new zzfp("FLOAT", 3, Float.TYPE, Float.class, Float.valueOf(0.0f));
        zzd = zzfpVar4;
        zzfp zzfpVar5 = new zzfp("DOUBLE", 4, Double.TYPE, Double.class, Double.valueOf(0.0d));
        zze = zzfpVar5;
        zzfp zzfpVar6 = new zzfp("BOOLEAN", 5, Boolean.TYPE, Boolean.class, Boolean.FALSE);
        zzf = zzfpVar6;
        zzfp zzfpVar7 = new zzfp("STRING", 6, String.class, String.class, "");
        zzg = zzfpVar7;
        zzfp zzfpVar8 = new zzfp("BYTE_STRING", 7, zzeg.class, zzeg.class, zzeg.zzb);
        zzh = zzfpVar8;
        zzfp zzfpVar9 = new zzfp("ENUM", 8, cls, Integer.class, null);
        zzi = zzfpVar9;
        zzfp zzfpVar10 = new zzfp("MESSAGE", 9, Object.class, Object.class, null);
        zzj = zzfpVar10;
        zzk = new zzfp[]{zzfpVar, zzfpVar2, zzfpVar3, zzfpVar4, zzfpVar5, zzfpVar6, zzfpVar7, zzfpVar8, zzfpVar9, zzfpVar10};
    }

    public zzfp(String str, int i, Class cls, Class cls2, Object obj) {
        this.zzl = cls2;
    }

    public static zzfp[] values() {
        return (zzfp[]) zzk.clone();
    }

    public final Class zza() {
        return this.zzl;
    }
}
