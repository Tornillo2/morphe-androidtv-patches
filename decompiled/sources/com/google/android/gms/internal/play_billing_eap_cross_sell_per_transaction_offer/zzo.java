package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

import androidx.annotation.Nullable;
import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline1;
import androidx.tvprovider.media.tv.ChannelLogoUtils$$ExternalSyntheticOutline0;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class zzo implements zzcx {
    public static final zzd zzb;
    public static final /* synthetic */ int zzf = 0;
    public static final Object zzh;

    @Nullable
    public volatile Object zzc;

    @Nullable
    public volatile zzh zzd;

    @Nullable
    public volatile zzm zze;
    public static final boolean zza = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));
    public static final Logger zzg = Logger.getLogger(zzo.class.getName());

    static {
        zzd zzlVar;
        try {
            zzlVar = new zzj(AtomicReferenceFieldUpdater.newUpdater(zzm.class, Thread.class, "zzb"), AtomicReferenceFieldUpdater.newUpdater(zzm.class, zzm.class, "zzc"), AtomicReferenceFieldUpdater.newUpdater(zzo.class, zzm.class, "zze"), AtomicReferenceFieldUpdater.newUpdater(zzo.class, zzh.class, "zzd"), AtomicReferenceFieldUpdater.newUpdater(zzo.class, Object.class, "zzc"));
            th = null;
        } catch (Throwable th) {
            th = th;
            zzlVar = new zzl();
        }
        Throwable th2 = th;
        zzb = zzlVar;
        if (th2 != null) {
            zzg.logp(Level.SEVERE, "com.android.billingclient.util.concurrent.AbstractResolvableFuture", "<clinit>", "SafeAtomicHelper is broken!", th2);
        }
        zzh = new Object();
    }

    public static void zzc(zzo zzoVar) {
        zzm zzmVar;
        zzd zzdVar;
        zzh zzhVar;
        do {
            zzmVar = zzoVar.zze;
            zzdVar = zzb;
        } while (!zzdVar.zze(zzoVar, zzmVar, zzm.zza));
        while (zzmVar != null) {
            Thread thread = zzmVar.zzb;
            if (thread != null) {
                zzmVar.zzb = null;
                LockSupport.unpark(thread);
            }
            zzmVar = zzmVar.zzc;
        }
        do {
            zzhVar = zzoVar.zzd;
        } while (!zzdVar.zzc(zzoVar, zzhVar, zzh.zza));
        zzh zzhVar2 = null;
        while (zzhVar != null) {
            zzh zzhVar3 = zzhVar.zzd;
            zzhVar.zzd = zzhVar2;
            zzhVar2 = zzhVar;
            zzhVar = zzhVar3;
        }
        while (zzhVar2 != null) {
            Runnable runnable = zzhVar2.zzb;
            zzh zzhVar4 = zzhVar2.zzd;
            if (runnable instanceof zzk) {
                throw null;
            }
            zzf(runnable, zzhVar2.zzc);
            zzhVar2 = zzhVar4;
        }
    }

    public static void zzf(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e) {
            zzg.logp(Level.SEVERE, "com.android.billingclient.util.concurrent.AbstractResolvableFuture", "executeListener", "RuntimeException while executing runnable " + String.valueOf(runnable) + " with executor " + String.valueOf(executor), (Throwable) e);
        }
    }

    public static final Object zzh(Object obj) throws ExecutionException {
        if (obj instanceof zze) {
            Throwable th = ((zze) obj).zzc;
            CancellationException cancellationException = new CancellationException("Task was cancelled.");
            cancellationException.initCause(th);
            throw cancellationException;
        }
        if (obj instanceof zzg) {
            throw new ExecutionException(((zzg) obj).zza);
        }
        if (obj == zzh) {
            return null;
        }
        return obj;
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z) {
        Object obj = this.zzc;
        if ((obj instanceof zzk) | (obj == null)) {
            zze zzeVar = zza ? new zze(z, new CancellationException("Future.cancel() was called.")) : z ? zze.zza : zze.zzb;
            while (!zzb.zzd(this, obj, zzeVar)) {
                obj = this.zzc;
                if (!(obj instanceof zzk)) {
                }
            }
            zzc(this);
            if (obj instanceof zzk) {
                throw null;
            }
            return true;
        }
        return false;
    }

    @Override // java.util.concurrent.Future
    public final Object get() throws ExecutionException, InterruptedException {
        Object obj;
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        Object obj2 = this.zzc;
        if ((obj2 != null) && (!(obj2 instanceof zzk))) {
            return zzh(obj2);
        }
        zzm zzmVar = this.zze;
        zzm zzmVar2 = zzm.zza;
        if (zzmVar != zzmVar2) {
            zzm zzmVar3 = new zzm();
            do {
                zzd zzdVar = zzb;
                zzdVar.zza(zzmVar3, zzmVar);
                if (zzdVar.zze(this, zzmVar, zzmVar3)) {
                    do {
                        LockSupport.park(this);
                        if (Thread.interrupted()) {
                            zzg(zzmVar3);
                            throw new InterruptedException();
                        }
                        obj = this.zzc;
                    } while (!((obj != null) & (!(obj instanceof zzk))));
                    return zzh(obj);
                }
                zzmVar = this.zze;
            } while (zzmVar != zzmVar2);
        }
        return zzh(this.zzc);
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.zzc instanceof zze;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return (this.zzc != null) & (!(r0 instanceof zzk));
    }

    public final String toString() {
        String strConcat;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("[status=");
        if (this.zzc instanceof zze) {
            sb.append("CANCELLED");
        } else if (isDone()) {
            zze(sb);
        } else {
            try {
                strConcat = zza();
            } catch (RuntimeException e) {
                strConcat = "Exception thrown from implementation: ".concat(String.valueOf(e.getClass()));
            }
            if (strConcat != null && !strConcat.isEmpty()) {
                sb.append("PENDING, info=[");
                sb.append(strConcat);
                sb.append("]");
            } else if (isDone()) {
                zze(sb);
            } else {
                sb.append("PENDING");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    public String zza() {
        if (this.zzc instanceof zzk) {
            return "setFuture=[null]";
        }
        if (this instanceof ScheduledFuture) {
            return ChannelLogoUtils$$ExternalSyntheticOutline0.m("remaining delay=[", ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS), " ms]");
        }
        return null;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzcx
    public final void zzb(Runnable runnable, Executor executor) {
        executor.getClass();
        zzh zzhVar = this.zzd;
        zzh zzhVar2 = zzh.zza;
        if (zzhVar != zzhVar2) {
            zzh zzhVar3 = new zzh(runnable, executor);
            do {
                zzhVar3.zzd = zzhVar;
                if (zzb.zzc(this, zzhVar, zzhVar3)) {
                    return;
                } else {
                    zzhVar = this.zzd;
                }
            } while (zzhVar != zzhVar2);
        }
        zzf(runnable, executor);
    }

    public boolean zzd(@Nullable Object obj) {
        if (obj == null) {
            obj = zzh;
        }
        if (!zzb.zzd(this, null, obj)) {
            return false;
        }
        zzc(this);
        return true;
    }

    public final void zze(StringBuilder sb) {
        V v;
        boolean z = false;
        while (true) {
            try {
                try {
                    v = get();
                    break;
                } catch (InterruptedException unused) {
                    z = true;
                } catch (Throwable th) {
                    if (z) {
                        Thread.currentThread().interrupt();
                    }
                    throw th;
                }
            } catch (CancellationException unused2) {
                sb.append("CANCELLED");
                return;
            } catch (RuntimeException e) {
                sb.append("UNKNOWN, cause=[");
                sb.append(e.getClass());
                sb.append(" thrown from get()]");
                return;
            } catch (ExecutionException e2) {
                sb.append("FAILURE, cause=[");
                sb.append(e2.getCause());
                sb.append("]");
                return;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        sb.append("SUCCESS, result=[");
        sb.append(v == this ? "this future" : String.valueOf(v));
        sb.append("]");
    }

    public final void zzg(zzm zzmVar) {
        zzmVar.zzb = null;
        while (true) {
            zzm zzmVar2 = this.zze;
            if (zzmVar2 != zzm.zza) {
                zzm zzmVar3 = null;
                while (zzmVar2 != null) {
                    zzm zzmVar4 = zzmVar2.zzc;
                    if (zzmVar2.zzb != null) {
                        zzmVar3 = zzmVar2;
                    } else if (zzmVar3 != null) {
                        zzmVar3.zzc = zzmVar4;
                        if (zzmVar3.zzb == null) {
                            break;
                        }
                    } else if (!zzb.zze(this, zzmVar2, zzmVar4)) {
                        break;
                    }
                    zzmVar2 = zzmVar4;
                }
                return;
            }
            return;
        }
    }

    @Override // java.util.concurrent.Future
    public final Object get(long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        boolean z;
        long nanos = timeUnit.toNanos(j);
        if (!Thread.interrupted()) {
            Object obj = this.zzc;
            if ((obj != null) & (!(obj instanceof zzk))) {
                return zzh(obj);
            }
            long jNanoTime = nanos > 0 ? System.nanoTime() + nanos : 0L;
            if (nanos >= 1000) {
                zzm zzmVar = this.zze;
                zzm zzmVar2 = zzm.zza;
                if (zzmVar != zzmVar2) {
                    zzm zzmVar3 = new zzm();
                    z = true;
                    do {
                        zzd zzdVar = zzb;
                        zzdVar.zza(zzmVar3, zzmVar);
                        if (zzdVar.zze(this, zzmVar, zzmVar3)) {
                            do {
                                LockSupport.parkNanos(this, nanos);
                                if (!Thread.interrupted()) {
                                    Object obj2 = this.zzc;
                                    if ((obj2 != null) & (!(obj2 instanceof zzk))) {
                                        return zzh(obj2);
                                    }
                                    nanos = jNanoTime - System.nanoTime();
                                } else {
                                    zzg(zzmVar3);
                                    throw new InterruptedException();
                                }
                            } while (nanos >= 1000);
                            zzg(zzmVar3);
                        } else {
                            zzmVar = this.zze;
                        }
                    } while (zzmVar != zzmVar2);
                }
                return zzh(this.zzc);
            }
            z = true;
            while (nanos > 0) {
                Object obj3 = this.zzc;
                if ((obj3 != null) & (!(obj3 instanceof zzk))) {
                    return zzh(obj3);
                }
                if (!Thread.interrupted()) {
                    nanos = jNanoTime - System.nanoTime();
                } else {
                    throw new InterruptedException();
                }
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
        throw new InterruptedException();
    }
}
