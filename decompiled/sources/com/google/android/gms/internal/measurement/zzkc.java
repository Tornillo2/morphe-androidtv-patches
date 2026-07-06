package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzjy;
import com.google.android.gms.internal.measurement.zzkc;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zzkc<MessageType extends zzkc<MessageType, BuilderType>, BuilderType extends zzjy<MessageType, BuilderType>> extends zzil<MessageType, BuilderType> {
    private static final Map zza = new ConcurrentHashMap();
    protected zzmm zzc = zzmm.zza;
    protected int zzd = -1;

    public static zzkc zzbC(Class cls) {
        Map map = zza;
        zzkc zzkcVar = (zzkc) map.get(cls);
        if (zzkcVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzkcVar = (zzkc) map.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (zzkcVar != null) {
            return zzkcVar;
        }
        zzkc zzkcVar2 = (zzkc) ((zzkc) zzmv.zze(cls)).zzl(6, null, null);
        if (zzkcVar2 == null) {
            throw new IllegalStateException();
        }
        map.put(cls, zzkcVar2);
        return zzkcVar2;
    }

    public static zzkh zzbD() {
        return zzkd.zzf();
    }

    public static zzki zzbE() {
        return zzky.zzf();
    }

    public static zzki zzbF(zzki zzkiVar) {
        int size = zzkiVar.size();
        return zzkiVar.zzd(size == 0 ? 10 : size + size);
    }

    public static zzkj zzbG() {
        return zzls.zze();
    }

    public static zzkj zzbH(zzkj zzkjVar) {
        int size = zzkjVar.size();
        return zzkjVar.zzd(size == 0 ? 10 : size + size);
    }

    public static Object zzbK(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            if (cause instanceof Error) {
                throw ((Error) cause);
            }
            throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
        }
    }

    public static Object zzbL(zzlj zzljVar, String str, Object[] objArr) {
        return new zzlt(zzljVar, str, objArr);
    }

    public static void zzbM(Class cls, zzkc zzkcVar) {
        zza.put(cls, zzkcVar);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return zzlr.zza().zzb(getClass()).zzj(this, (zzkc) obj);
        }
        return false;
    }

    public final int hashCode() {
        int i = this.zzb;
        if (i != 0) {
            return i;
        }
        int iZzb = zzlr.zza().zzb(getClass()).zzb(this);
        this.zzb = iZzb;
        return iZzb;
    }

    public final String toString() {
        return zzll.zza(this, super.toString());
    }

    public final zzjy zzbA() {
        return (zzjy) zzl(5, null, null);
    }

    public final zzjy zzbB() {
        zzjy zzjyVar = (zzjy) zzl(5, null, null);
        zzjyVar.zzaC(this);
        return zzjyVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzlj
    public final /* synthetic */ zzli zzbI() {
        return (zzjy) zzl(5, null, null);
    }

    @Override // com.google.android.gms.internal.measurement.zzlj
    public final /* synthetic */ zzli zzbJ() {
        zzjy zzjyVar = (zzjy) zzl(5, null, null);
        zzjyVar.zzaC(this);
        return zzjyVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzlj
    public final void zzbN(zzjj zzjjVar) throws IOException {
        zzlr.zza().zzb(getClass()).zzi(this, zzjk.zza(zzjjVar));
    }

    @Override // com.google.android.gms.internal.measurement.zzlk
    public final /* synthetic */ zzlj zzbR() {
        return (zzkc) zzl(6, null, null);
    }

    @Override // com.google.android.gms.internal.measurement.zzil
    public final int zzbu() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.measurement.zzil
    public final void zzbx(int i) {
        this.zzd = i;
    }

    @Override // com.google.android.gms.internal.measurement.zzlj
    public final int zzbz() {
        int i = this.zzd;
        if (i != -1) {
            return i;
        }
        int iZza = zzlr.zza().zzb(getClass()).zza(this);
        this.zzd = iZza;
        return iZza;
    }

    public abstract Object zzl(int i, Object obj, Object obj2);
}
