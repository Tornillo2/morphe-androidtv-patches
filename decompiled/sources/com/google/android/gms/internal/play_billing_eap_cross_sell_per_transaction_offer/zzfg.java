package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzfc;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzfg;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zzfg<MessageType extends zzfg<MessageType, BuilderType>, BuilderType extends zzfc<MessageType, BuilderType>> extends zzdq<MessageType, BuilderType> {
    private static final Map zzb = new ConcurrentHashMap();
    private int zzd = -1;
    protected zzhg zzc = zzhg.zza;

    public static final boolean zzA(zzfg zzfgVar, boolean z) {
        byte bByteValue = ((Byte) zzfgVar.zzb(1, null, null)).byteValue();
        if (bByteValue == 1) {
            return true;
        }
        if (bByteValue == 0) {
            return false;
        }
        boolean zZzk = zzgq.zza().zzb(zzfgVar.getClass()).zzk(zzfgVar);
        if (z) {
            zzfgVar.zzb(2, true != zZzk ? null : zzfgVar, null);
        }
        return zZzk;
    }

    public static zzfg zzd(zzfg zzfgVar, byte[] bArr, int i, int i2, zzes zzesVar) throws zzfo {
        if (i2 == 0) {
            return zzfgVar;
        }
        zzfg zzfgVarZzo = zzfgVar.zzo();
        try {
            zzgt zzgtVarZzb = zzgq.zza().zzb(zzfgVarZzo.getClass());
            zzgtVarZzb.zzh(zzfgVarZzo, bArr, 0, i2, new zzdu(zzesVar));
            zzgtVarZzb.zzf(zzfgVarZzo);
            return zzfgVarZzo;
        } catch (zzfo e) {
            throw e;
        } catch (zzhe e2) {
            throw e2.zza();
        } catch (IOException e3) {
            if (e3.getCause() instanceof zzfo) {
                throw ((zzfo) e3.getCause());
            }
            throw new zzfo(e3.getMessage(), e3);
        } catch (IndexOutOfBoundsException unused) {
            throw new zzfo("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
    }

    public static zzfg zzn(Class cls) {
        Map map = zzb;
        zzfg zzfgVar = (zzfg) map.get(cls);
        if (zzfgVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzfgVar = (zzfg) map.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (zzfgVar != null) {
            return zzfgVar;
        }
        zzfg zzfgVar2 = (zzfg) ((zzfg) zzhm.zze(cls)).zzb(6, null, null);
        if (zzfgVar2 == null) {
            throw new IllegalStateException();
        }
        map.put(cls, zzfgVar2);
        return zzfgVar2;
    }

    public static zzfg zzp(zzfg zzfgVar, byte[] bArr, zzes zzesVar) throws zzfo {
        zzfg zzfgVarZzd = zzd(zzfgVar, bArr, 0, bArr.length, zzesVar);
        if (zzfgVarZzd == null || zzA(zzfgVarZzd, true)) {
            return zzfgVarZzd;
        }
        throw new zzhe(zzfgVarZzd).zza();
    }

    public static zzfk zzq() {
        return zzfh.zzf();
    }

    public static zzfl zzr() {
        return zzgr.zze();
    }

    public static Object zzs(Method method, Object obj, Object... objArr) {
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

    public static Object zzt(zzgj zzgjVar, String str, Object[] objArr) {
        return new zzgs(zzgjVar, str, objArr);
    }

    public static void zzw(Class cls, zzfg zzfgVar) {
        zzfgVar.zzv();
        zzb.put(cls, zzfgVar);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return zzgq.zza().zzb(getClass()).zzj(this, (zzfg) obj);
    }

    public final int hashCode() {
        if (zzz()) {
            return zzi();
        }
        int i = this.zza;
        if (i != 0) {
            return i;
        }
        int iZzi = zzi();
        this.zza = iZzi;
        return iZzi;
    }

    public final String toString() {
        return zzgl.zza(this, super.toString());
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzgj
    public final /* synthetic */ zzgi zzK() {
        return (zzfc) zzb(5, null, null);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzgj
    public final void zzL(zzen zzenVar) throws IOException {
        zzgq.zza().zzb(getClass()).zzi(this, zzeo.zza(zzenVar));
    }

    public abstract Object zzb(int i, Object obj, Object obj2);

    public final int zzc(zzgt zzgtVar) {
        return zzgq.zza().zzb(getClass()).zza(this);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzdq
    public final int zze(zzgt zzgtVar) {
        if (zzz()) {
            int iZza = zzgtVar.zza(this);
            if (iZza >= 0) {
                return iZza;
            }
            throw new IllegalStateException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("serialized size must be non-negative, was ", iZza));
        }
        int i = this.zzd & Integer.MAX_VALUE;
        if (i != Integer.MAX_VALUE) {
            return i;
        }
        int iZza2 = zzgtVar.zza(this);
        if (iZza2 < 0) {
            throw new IllegalStateException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("serialized size must be non-negative, was ", iZza2));
        }
        this.zzd = (this.zzd & Integer.MIN_VALUE) | iZza2;
        return iZza2;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzgk
    public final /* synthetic */ zzgj zzh() {
        return (zzfg) zzb(6, null, null);
    }

    public final int zzi() {
        return zzgq.zza().zzb(getClass()).zzb(this);
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzgj
    public final int zzj() {
        if (zzz()) {
            int iZzc = zzc(null);
            if (iZzc >= 0) {
                return iZzc;
            }
            throw new IllegalStateException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("serialized size must be non-negative, was ", iZzc));
        }
        int i = this.zzd & Integer.MAX_VALUE;
        if (i != Integer.MAX_VALUE) {
            return i;
        }
        int iZzc2 = zzc(null);
        if (iZzc2 < 0) {
            throw new IllegalStateException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("serialized size must be non-negative, was ", iZzc2));
        }
        this.zzd = (this.zzd & Integer.MIN_VALUE) | iZzc2;
        return iZzc2;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzgk
    public final boolean zzk() {
        return zzA(this, true);
    }

    public final zzfc zzl() {
        return (zzfc) zzb(5, null, null);
    }

    public final zzfc zzm() {
        zzfc zzfcVar = (zzfc) zzb(5, null, null);
        zzfcVar.zzd(this);
        return zzfcVar;
    }

    public final zzfg zzo() {
        return (zzfg) zzb(4, null, null);
    }

    public final void zzu() {
        zzgq.zza().zzb(getClass()).zzf(this);
        zzv();
    }

    public final void zzv() {
        this.zzd &= Integer.MAX_VALUE;
    }

    public final void zzx(int i) {
        this.zzd = (this.zzd & Integer.MIN_VALUE) | Integer.MAX_VALUE;
    }

    public final boolean zzz() {
        return (this.zzd & Integer.MIN_VALUE) != 0;
    }
}
