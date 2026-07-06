package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline1;
import com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzch;
import com.google.common.util.concurrent.OverflowAvoidingLockSupport;
import j$.util.Objects;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;
import sun.misc.Unsafe;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zzci<V> extends zzdd implements zzcx<V> {
    public static final Object zza = new Object();
    public static final zzcw zzb = new zzcw(zzch.class);
    public static final boolean zzc;
    public static final zza zzd;
    public volatile zzch.zzd listenersField;
    public volatile Object valueField;
    public volatile zze waitersField;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public abstract class zza {
        public /* synthetic */ zza(zzcm zzcmVar) {
        }

        public abstract zzch.zzd zza(zzci zzciVar, zzch.zzd zzdVar);

        public abstract zze zzb(zzci zzciVar, zze zzeVar);

        public abstract void zzc(zze zzeVar, zze zzeVar2);

        public abstract void zzd(zze zzeVar, Thread thread);

        public abstract boolean zze(zzci zzciVar, zzch.zzd zzdVar, zzch.zzd zzdVar2);

        public abstract boolean zzf(zzci zzciVar, Object obj, Object obj2);

        public abstract boolean zzg(zzci zzciVar, zze zzeVar, zze zzeVar2);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class zzb extends zza {
        public static final AtomicReferenceFieldUpdater<zze, Thread> zza = AtomicReferenceFieldUpdater.newUpdater(zze.class, Thread.class, "thread");
        public static final AtomicReferenceFieldUpdater<zze, zze> zzb = AtomicReferenceFieldUpdater.newUpdater(zze.class, zze.class, "next");
        public static final AtomicReferenceFieldUpdater<? super zzci<?>, zze> zzc = AtomicReferenceFieldUpdater.newUpdater(zzci.class, zze.class, "waitersField");
        public static final AtomicReferenceFieldUpdater<? super zzci<?>, zzch.zzd> zzd = AtomicReferenceFieldUpdater.newUpdater(zzci.class, zzch.zzd.class, "listenersField");
        public static final AtomicReferenceFieldUpdater<? super zzci<?>, Object> zze = AtomicReferenceFieldUpdater.newUpdater(zzci.class, Object.class, "valueField");

        public zzb() {
            throw null;
        }

        @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzci.zza
        public final zzch.zzd zza(zzci zzciVar, zzch.zzd zzdVar) {
            return zzd.getAndSet(zzciVar, zzdVar);
        }

        @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzci.zza
        public final zze zzb(zzci zzciVar, zze zzeVar) {
            return zzc.getAndSet(zzciVar, zzeVar);
        }

        @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzci.zza
        public final void zzc(zze zzeVar, zze zzeVar2) {
            zzb.lazySet(zzeVar, zzeVar2);
        }

        @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzci.zza
        public final void zzd(zze zzeVar, Thread thread) {
            zza.lazySet(zzeVar, thread);
        }

        @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzci.zza
        public final boolean zze(zzci zzciVar, zzch.zzd zzdVar, zzch.zzd zzdVar2) {
            return zzcj.zza(zzd, zzciVar, zzdVar, zzdVar2);
        }

        @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzci.zza
        public final boolean zzf(zzci zzciVar, Object obj, Object obj2) {
            return zzcj.zza(zze, zzciVar, obj, obj2);
        }

        @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzci.zza
        public final boolean zzg(zzci zzciVar, zze zzeVar, zze zzeVar2) {
            return zzcj.zza(zzc, zzciVar, zzeVar, zzeVar2);
        }

        public /* synthetic */ zzb(zzcm zzcmVar) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class zzc extends zza {
        public zzc() {
            throw null;
        }

        @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzci.zza
        public final zzch.zzd zza(zzci zzciVar, zzch.zzd zzdVar) {
            zzch.zzd zzdVar2;
            synchronized (zzciVar) {
                try {
                    zzdVar2 = zzciVar.listenersField;
                    if (zzdVar2 != zzdVar) {
                        zzciVar.listenersField = zzdVar;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return zzdVar2;
        }

        @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzci.zza
        public final zze zzb(zzci zzciVar, zze zzeVar) {
            zze zzeVar2;
            synchronized (zzciVar) {
                try {
                    zzeVar2 = zzciVar.waitersField;
                    if (zzeVar2 != zzeVar) {
                        zzciVar.waitersField = zzeVar;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return zzeVar2;
        }

        @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzci.zza
        public final void zzc(zze zzeVar, zze zzeVar2) {
            zzeVar.next = zzeVar2;
        }

        @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzci.zza
        public final void zzd(zze zzeVar, Thread thread) {
            zzeVar.thread = thread;
        }

        @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzci.zza
        public final boolean zze(zzci zzciVar, zzch.zzd zzdVar, zzch.zzd zzdVar2) {
            synchronized (zzciVar) {
                try {
                    if (zzciVar.listenersField != zzdVar) {
                        return false;
                    }
                    zzciVar.listenersField = zzdVar2;
                    return true;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzci.zza
        public final boolean zzf(zzci zzciVar, Object obj, Object obj2) {
            synchronized (zzciVar) {
                try {
                    if (zzciVar.valueField != obj) {
                        return false;
                    }
                    zzciVar.valueField = obj2;
                    return true;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzci.zza
        public final boolean zzg(zzci zzciVar, zze zzeVar, zze zzeVar2) {
            synchronized (zzciVar) {
                try {
                    if (zzciVar.waitersField != zzeVar) {
                        return false;
                    }
                    zzciVar.waitersField = zzeVar2;
                    return true;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public /* synthetic */ zzc(zzcm zzcmVar) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class zzd extends zza {
        public static final Unsafe zza;
        public static final long zzb;
        public static final long zzc;
        public static final long zzd;
        public static final long zze;
        public static final long zzf;
        public static final /* synthetic */ int zzg = 0;

        static {
            Unsafe unsafe;
            try {
                try {
                    unsafe = Unsafe.getUnsafe();
                } catch (PrivilegedActionException e) {
                    throw new RuntimeException("Could not initialize intrinsics", e.getCause());
                }
            } catch (SecurityException unused) {
                unsafe = (Unsafe) AccessController.doPrivileged(new zzcl());
            }
            try {
                zzc = unsafe.objectFieldOffset(zzci.class.getDeclaredField("waitersField"));
                zzb = unsafe.objectFieldOffset(zzci.class.getDeclaredField("listenersField"));
                zzd = unsafe.objectFieldOffset(zzci.class.getDeclaredField("valueField"));
                zze = unsafe.objectFieldOffset(zze.class.getDeclaredField("thread"));
                zzf = unsafe.objectFieldOffset(zze.class.getDeclaredField("next"));
                zza = unsafe;
            } catch (NoSuchFieldException e2) {
                throw new RuntimeException(e2);
            }
        }

        public zzd() {
            throw null;
        }

        @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzci.zza
        public final zzch.zzd zza(zzci zzciVar, zzch.zzd zzdVar) {
            zzch.zzd zzdVar2;
            do {
                zzdVar2 = zzciVar.listenersField;
                if (zzdVar == zzdVar2) {
                    break;
                }
            } while (!zze(zzciVar, zzdVar2, zzdVar));
            return zzdVar2;
        }

        @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzci.zza
        public final zze zzb(zzci zzciVar, zze zzeVar) {
            zze zzeVar2;
            do {
                zzeVar2 = zzciVar.waitersField;
                if (zzeVar == zzeVar2) {
                    break;
                }
            } while (!zzg(zzciVar, zzeVar2, zzeVar));
            return zzeVar2;
        }

        @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzci.zza
        public final void zzc(zze zzeVar, zze zzeVar2) {
            zza.putObject(zzeVar, zzf, zzeVar2);
        }

        @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzci.zza
        public final void zzd(zze zzeVar, Thread thread) {
            zza.putObject(zzeVar, zze, thread);
        }

        @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzci.zza
        public final boolean zze(zzci zzciVar, zzch.zzd zzdVar, zzch.zzd zzdVar2) {
            return zzck.zza(zza, zzciVar, zzb, zzdVar, zzdVar2);
        }

        @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzci.zza
        public final boolean zzf(zzci zzciVar, Object obj, Object obj2) {
            return zzck.zza(zza, zzciVar, zzd, obj, obj2);
        }

        @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzci.zza
        public final boolean zzg(zzci zzciVar, zze zzeVar, zze zzeVar2) {
            return zzck.zza(zza, zzciVar, zzc, zzeVar, zzeVar2);
        }

        public /* synthetic */ zzd(zzcm zzcmVar) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class zze {
        public static final zze zza = new zze();
        public volatile zze next;
        public volatile Thread thread;

        public zze(boolean z) {
        }

        public zze() {
            zzci.zzn(this, Thread.currentThread());
        }
    }

    static {
        boolean z;
        zza zzcVar;
        Throwable th;
        Throwable th2;
        try {
            z = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));
        } catch (SecurityException unused) {
            z = false;
        }
        zzc = z;
        String property = System.getProperty("java.runtime.name", "");
        Throwable th3 = null;
        if (property == null || property.contains("Android")) {
            try {
                zzcVar = new zzd();
            } catch (Error | Exception e) {
                try {
                    zzcVar = new zzb();
                } catch (Error | Exception e2) {
                    th3 = e2;
                    zzcVar = new zzc();
                }
                th = th3;
                th2 = e;
            }
        } else {
            try {
                zzcVar = new zzb();
            } catch (NoClassDefFoundError unused2) {
                zzcVar = new zzc();
            }
        }
        th = null;
        th2 = null;
        zzd = zzcVar;
        if (th != null) {
            zzcw zzcwVar = zzb;
            Logger loggerZza = zzcwVar.zza();
            Level level = Level.SEVERE;
            loggerZza.logp(level, "com.google.common.util.concurrent.AbstractFutureState", "<clinit>", "UnsafeAtomicHelper is broken!", th2);
            zzcwVar.zza().logp(level, "com.google.common.util.concurrent.AbstractFutureState", "<clinit>", "AtomicReferenceFieldUpdaterAtomicHelper is broken!", th);
        }
    }

    public static /* synthetic */ void zzn(zze zzeVar, Thread thread) {
        zzd.zzd(zzeVar, thread);
    }

    public static boolean zzq(zzci zzciVar, Object obj, Object obj2) {
        return zzd.zzf(zzciVar, obj, obj2);
    }

    public final void zza(zze zzeVar) {
        zzeVar.thread = null;
        while (true) {
            zze zzeVar2 = this.waitersField;
            if (zzeVar2 != zze.zza) {
                zze zzeVar3 = null;
                while (zzeVar2 != null) {
                    zze zzeVar4 = zzeVar2.next;
                    if (zzeVar2.thread != null) {
                        zzeVar3 = zzeVar2;
                    } else if (zzeVar3 != null) {
                        zzeVar3.next = zzeVar4;
                        if (zzeVar3.thread == null) {
                            break;
                        }
                    } else if (!zzd.zzg(this, zzeVar2, zzeVar4)) {
                        break;
                    }
                    zzeVar2 = zzeVar4;
                }
                return;
            }
            return;
        }
    }

    public final zzch.zzd zzk(zzch.zzd zzdVar) {
        return zzd.zza(this, zzdVar);
    }

    public final Object zzl() throws ExecutionException, InterruptedException {
        Object obj;
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        Object obj2 = this.valueField;
        if ((obj2 != null) && zzch.zzh(obj2)) {
            return zzch.zzc(obj2);
        }
        zze zzeVar = this.waitersField;
        if (zzeVar != zze.zza) {
            zze zzeVar2 = new zze();
            do {
                zza zzaVar = zzd;
                zzaVar.zzc(zzeVar2, zzeVar);
                if (zzaVar.zzg(this, zzeVar, zzeVar2)) {
                    do {
                        LockSupport.park(this);
                        if (Thread.interrupted()) {
                            zza(zzeVar2);
                            throw new InterruptedException();
                        }
                        obj = this.valueField;
                    } while (!((obj != null) & zzch.zzh(obj)));
                    return zzch.zzc(obj);
                }
                zzeVar = this.waitersField;
            } while (zzeVar != zze.zza);
        }
        Object obj3 = this.valueField;
        Objects.requireNonNull(obj3);
        return zzch.zzc(obj3);
    }

    public final Object zzm(long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        long nanos = timeUnit.toNanos(j);
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        Object obj = this.valueField;
        boolean z = true;
        if ((obj != null) && zzch.zzh(obj)) {
            return zzch.zzc(obj);
        }
        long jNanoTime = nanos > 0 ? System.nanoTime() + nanos : 0L;
        if (nanos >= 1000) {
            zze zzeVar = this.waitersField;
            if (zzeVar != zze.zza) {
                zze zzeVar2 = new zze();
                do {
                    zza zzaVar = zzd;
                    zzaVar.zzc(zzeVar2, zzeVar);
                    if (zzaVar.zzg(this, zzeVar, zzeVar2)) {
                        do {
                            LockSupport.parkNanos(this, Math.min(nanos, OverflowAvoidingLockSupport.MAX_NANOSECONDS_THRESHOLD));
                            if (Thread.interrupted()) {
                                zza(zzeVar2);
                                throw new InterruptedException();
                            }
                            Object obj2 = this.valueField;
                            if ((obj2 != null) && zzch.zzh(obj2)) {
                                return zzch.zzc(obj2);
                            }
                            nanos = jNanoTime - System.nanoTime();
                        } while (nanos >= 1000);
                        zza(zzeVar2);
                    } else {
                        zzeVar = this.waitersField;
                    }
                } while (zzeVar != zze.zza);
            }
            Object obj3 = this.valueField;
            Objects.requireNonNull(obj3);
            return zzch.zzc(obj3);
        }
        while (nanos > 0) {
            Object obj4 = this.valueField;
            if ((obj4 != null) && zzch.zzh(obj4)) {
                return zzch.zzc(obj4);
            }
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
            nanos = jNanoTime - System.nanoTime();
        }
        String string = toString();
        String string2 = timeUnit.toString();
        Locale locale = Locale.ROOT;
        String lowerCase = string2.toLowerCase(locale);
        String strConcat = "Waited " + j + StringUtils.SPACE + timeUnit.toString().toLowerCase(locale);
        if (nanos + 1000 < 0) {
            String strConcat2 = strConcat.concat(" (plus ");
            long j2 = -nanos;
            long jConvert = timeUnit.convert(j2, TimeUnit.NANOSECONDS);
            long nanos2 = j2 - timeUnit.toNanos(jConvert);
            if (jConvert != 0 && nanos2 <= 1000) {
                z = false;
            }
            if (jConvert > 0) {
                String strConcat3 = strConcat2 + jConvert + StringUtils.SPACE + lowerCase;
                if (z) {
                    strConcat3 = strConcat3.concat(",");
                }
                strConcat2 = strConcat3.concat(StringUtils.SPACE);
            }
            if (z) {
                strConcat2 = strConcat2 + nanos2 + " nanoseconds ";
            }
            strConcat = strConcat2.concat("delay)");
        }
        if (isDone()) {
            throw new TimeoutException(strConcat.concat(" but future completed as timeout expired"));
        }
        throw new TimeoutException(AbstractResolvableFuture$$ExternalSyntheticOutline1.m(strConcat, " for ", string));
    }

    public final void zzo() {
        for (zze zzeVarZzb = zzd.zzb(this, zze.zza); zzeVarZzb != null; zzeVarZzb = zzeVarZzb.next) {
            Thread thread = zzeVarZzb.thread;
            if (thread != null) {
                zzeVarZzb.thread = null;
                LockSupport.unpark(thread);
            }
        }
    }

    public final boolean zzp(zzch.zzd zzdVar, zzch.zzd zzdVar2) {
        return zzd.zze(this, zzdVar, zzdVar2);
    }
}
