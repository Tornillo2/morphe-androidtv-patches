package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzlb implements zzlv {
    public static final zzlh zza = new zzkz();
    public final zzlh zzb;

    public zzlb() {
        zzlh zzlhVar;
        try {
            zzlhVar = (zzlh) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", null).invoke(null, null);
        } catch (Exception unused) {
            zzlhVar = zza;
        }
        zzla zzlaVar = new zzla(zzjx.zza, zzlhVar);
        zzkk.zzf(zzlaVar, "messageInfoFactory");
        this.zzb = zzlaVar;
    }

    public static boolean zzb(zzlg zzlgVar) {
        return zzlgVar.zzc() == 1;
    }

    @Override // com.google.android.gms.internal.measurement.zzlv
    public final zzlu zza(Class cls) {
        zzlw.zzG(cls);
        zzlg zzlgVarZzb = this.zzb.zzb(cls);
        return zzlgVarZzb.zzb() ? zzkc.class.isAssignableFrom(cls) ? new zzln(zzlw.zzd, zzjr.zzb(), zzlgVarZzb.zza()) : new zzln(zzlw.zzb, zzjr.zza(), zzlgVarZzb.zza()) : zzkc.class.isAssignableFrom(cls) ? zzlgVarZzb.zzc() == 1 ? zzlm.zzl(cls, zzlgVarZzb, zzlp.zzb(), zzkx.zzd(), zzlw.zzd, zzjr.zzb(), zzlf.zzb()) : zzlm.zzl(cls, zzlgVarZzb, zzlp.zzb(), zzkx.zzd(), zzlw.zzd, null, zzlf.zzb()) : zzlgVarZzb.zzc() == 1 ? zzlm.zzl(cls, zzlgVarZzb, zzlp.zza(), zzkx.zzc(), zzlw.zzb, zzjr.zza(), zzlf.zza()) : zzlm.zzl(cls, zzlgVarZzb, zzlp.zza(), zzkx.zzc(), zzlw.zzc, null, zzlf.zza());
    }
}
