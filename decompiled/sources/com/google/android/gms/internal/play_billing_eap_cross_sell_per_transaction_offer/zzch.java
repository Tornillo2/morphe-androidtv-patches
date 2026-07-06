package com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer;

import j$.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zzch<V> extends zzci<V> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class zza {
        public static final zza zza;
        public static final zza zzb;
        public final boolean zzc;
        public final Throwable zzd;

        static {
            if (zzci.zzc) {
                zzb = null;
                zza = null;
            } else {
                zzb = new zza(false, null);
                zza = new zza(true, null);
            }
        }

        public zza(boolean z, Throwable th) {
            this.zzc = z;
            this.zzd = th;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class zzb<V> implements Runnable {
        public final zzch<V> zza;
        public final zzcx<? extends V> zzb;

        public zzb(zzch zzchVar, zzcx zzcxVar) {
            this.zza = zzchVar;
            this.zzb = zzcxVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (this.zza.valueField != this) {
                return;
            }
            zzcx<? extends V> zzcxVar = this.zzb;
            if (zzci.zzd.zzf(this.zza, this, zzch.zzr(zzcxVar))) {
                zzch.zzu(this.zza, false);
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class zzc {
        public static final zzc zza = new zzc(new Throwable("Failure occurred while trying to finish a future.") { // from class: com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzch.zzc.1
            {
                super("Failure occurred while trying to finish a future.");
            }

            @Override // java.lang.Throwable
            public final Throwable fillInStackTrace() {
                return this;
            }
        });
        public final Throwable zzb;

        public zzc(Throwable th) {
            th.getClass();
            this.zzb = th;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class zzd {
        public static final zzd zza = new zzd();
        public zzd next;
        public final Runnable zzb;
        public final Executor zzc;

        public zzd() {
            this.zzb = null;
            this.zzc = null;
        }

        public zzd(Runnable runnable, Executor executor) {
            this.zzb = runnable;
            this.zzc = executor;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface zze<V> extends zzcx<V> {
    }

    public static Object zzc(Object obj) throws ExecutionException {
        if (obj instanceof zza) {
            Throwable th = ((zza) obj).zzd;
            CancellationException cancellationException = new CancellationException("Task was cancelled.");
            cancellationException.initCause(th);
            throw cancellationException;
        }
        if (obj instanceof zzc) {
            throw new ExecutionException(((zzc) obj).zzb);
        }
        if (obj == zzci.zza) {
            return null;
        }
        return obj;
    }

    public static boolean zzh(Object obj) {
        return !(obj instanceof zzb);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Object zzr(zzcx zzcxVar) {
        Throwable thZze;
        if (zzcxVar instanceof zze) {
            Object zzaVar = ((zzch) zzcxVar).valueField;
            if (zzaVar instanceof zza) {
                zza zzaVar2 = (zza) zzaVar;
                if (zzaVar2.zzc) {
                    Throwable th = zzaVar2.zzd;
                    zzaVar = th != null ? new zza(false, th) : zza.zzb;
                }
            }
            Objects.requireNonNull(zzaVar);
            return zzaVar;
        }
        if ((zzcxVar instanceof zzdd) && (thZze = ((zzdd) zzcxVar).zze()) != null) {
            return new zzc(thZze);
        }
        boolean zIsCancelled = zzcxVar.isCancelled();
        if ((!zzci.zzc) && zIsCancelled) {
            zza zzaVar3 = zza.zzb;
            Objects.requireNonNull(zzaVar3);
            return zzaVar3;
        }
        try {
            Object objZzs = zzs(zzcxVar);
            return zIsCancelled ? new zza(false, new IllegalArgumentException("get() did not throw CancellationException, despite reporting isCancelled() == true: ".concat(String.valueOf(zzcxVar)))) : objZzs == null ? zzci.zza : objZzs;
        } catch (Error | Exception e) {
            return new zzc(e);
        } catch (CancellationException e2) {
            return !zIsCancelled ? new zzc(new IllegalArgumentException("get() threw CancellationException, despite reporting isCancelled() == false: ".concat(String.valueOf(zzcxVar)), e2)) : new zza(false, e2);
        } catch (ExecutionException e3) {
            return zIsCancelled ? new zza(false, new IllegalArgumentException("get() did not throw CancellationException, despite reporting isCancelled() == true: ".concat(String.valueOf(zzcxVar)), e3)) : new zzc(e3.getCause());
        }
    }

    public static Object zzs(Future future) throws ExecutionException {
        Object obj;
        boolean z = false;
        while (true) {
            try {
                obj = future.get();
                break;
            } catch (InterruptedException unused) {
                z = true;
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        return obj;
    }

    public static void zzu(zzch zzchVar, boolean z) {
        zzd zzdVar;
        zzd zzdVar2 = null;
        while (true) {
            zzchVar.zzo();
            zzchVar.zzg();
            zzd zzdVar3 = zzdVar2;
            zzd zzdVarZza = zzci.zzd.zza(zzchVar, zzd.zza);
            zzd zzdVar4 = zzdVar3;
            while (zzdVarZza != null) {
                zzd zzdVar5 = zzdVarZza.next;
                zzdVarZza.next = zzdVar4;
                zzdVar4 = zzdVarZza;
                zzdVarZza = zzdVar5;
            }
            while (zzdVar4 != null) {
                Runnable runnable = zzdVar4.zzb;
                zzdVar = zzdVar4.next;
                Objects.requireNonNull(runnable);
                Runnable runnable2 = runnable;
                if (runnable2 instanceof zzb) {
                    zzb zzbVar = (zzb) runnable2;
                    zzchVar = zzbVar.zza;
                    if (zzchVar.valueField == zzbVar) {
                        if (zzci.zzd.zzf(zzchVar, zzbVar, zzr(zzbVar.zzb))) {
                            break;
                        }
                    } else {
                        continue;
                    }
                } else {
                    Executor executor = zzdVar4.zzc;
                    Objects.requireNonNull(executor);
                    zzv(runnable2, executor);
                }
                zzdVar4 = zzdVar;
            }
            return;
            zzdVar2 = zzdVar;
        }
    }

    public static void zzv(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (Exception e) {
            zzci.zzb.zza().logp(Level.SEVERE, "com.google.common.util.concurrent.AbstractFuture", "executeListener", "RuntimeException while executing runnable " + String.valueOf(runnable) + " with executor " + String.valueOf(executor), (Throwable) e);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0057, code lost:
    
        return true;
     */
    @Override // java.util.concurrent.Future
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean cancel(boolean r8) {
        /*
            r7 = this;
            java.lang.Object r0 = r7.valueField
            boolean r1 = r0 instanceof com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzch.zzb
            r2 = 0
            r3 = 1
            if (r0 != 0) goto La
            r4 = 1
            goto Lb
        La:
            r4 = 0
        Lb:
            r1 = r1 | r4
            if (r1 == 0) goto L61
            boolean r1 = com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzci.zzc
            if (r1 == 0) goto L1f
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzch$zza r1 = new com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzch$zza
            java.util.concurrent.CancellationException r4 = new java.util.concurrent.CancellationException
            java.lang.String r5 = "Future.cancel() was called."
            r4.<init>(r5)
            r1.<init>(r8, r4)
            goto L29
        L1f:
            if (r8 == 0) goto L24
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzch$zza r1 = com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzch.zza.zza
            goto L26
        L24:
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzch$zza r1 = com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzch.zza.zzb
        L26:
            j$.util.Objects.requireNonNull(r1)
        L29:
            r5 = 0
            r4 = r7
        L2b:
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzci$zza r6 = com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzci.zzd
            boolean r6 = r6.zzf(r4, r0, r1)
            if (r6 == 0) goto L58
            zzu(r4, r8)
            boolean r4 = r0 instanceof com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzch.zzb
            if (r4 == 0) goto L57
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzch$zzb r0 = (com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzch.zzb) r0
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzcx<? extends V> r0 = r0.zzb
            boolean r4 = r0 instanceof com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzch.zze
            if (r4 == 0) goto L54
            r4 = r0
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzch r4 = (com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzch) r4
            java.lang.Object r0 = r4.valueField
            if (r0 != 0) goto L4b
            r5 = 1
            goto L4c
        L4b:
            r5 = 0
        L4c:
            boolean r6 = r0 instanceof com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzch.zzb
            r5 = r5 | r6
            if (r5 == 0) goto L53
            r5 = 1
            goto L2b
        L53:
            return r3
        L54:
            r0.cancel(r8)
        L57:
            return r3
        L58:
            java.lang.Object r0 = r4.valueField
            boolean r6 = zzh(r0)
            if (r6 == 0) goto L2b
            return r5
        L61:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzch.cancel(boolean):boolean");
    }

    @Override // java.util.concurrent.Future
    public final Object get() throws ExecutionException, InterruptedException {
        return zzl();
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.valueField instanceof zza;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        Object obj = this.valueField;
        return (obj != null) & zzh(obj);
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x00a1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String toString() {
        /*
            Method dump skipped, instruction units count: 216
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzch.toString():java.lang.String");
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzcx
    public final void zzb(Runnable runnable, Executor executor) {
        zzd zzdVar;
        zzbe.zzc(executor, "Executor was null.");
        if (!isDone() && (zzdVar = this.listenersField) != zzd.zza) {
            zzd zzdVar2 = new zzd(runnable, executor);
            do {
                zzdVar2.next = zzdVar;
                if (zzci.zzd.zze(this, zzdVar, zzdVar2)) {
                    return;
                } else {
                    zzdVar = this.listenersField;
                }
            } while (zzdVar != zzd.zza);
        }
        zzv(runnable, executor);
    }

    public String zzd() {
        throw null;
    }

    @Override // com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzdd
    public final Throwable zze() {
        if (!(this instanceof zze)) {
            return null;
        }
        Object obj = this.valueField;
        if (obj instanceof zzc) {
            return ((zzc) obj).zzb;
        }
        return null;
    }

    public final boolean zzi(Throwable th) {
        if (!zzci.zzd.zzf(this, null, new zzc(th))) {
            return false;
        }
        zzu(this, false);
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean zzj(com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzcx r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.valueField
            r1 = 0
            if (r0 != 0) goto L3f
            boolean r0 = r6.isDone()
            r2 = 1
            r3 = 0
            if (r0 == 0) goto L1d
            java.lang.Object r6 = zzr(r6)
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzci$zza r0 = com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzci.zzd
            boolean r6 = r0.zzf(r5, r3, r6)
            if (r6 == 0) goto L4a
            zzu(r5, r1)
            return r2
        L1d:
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzch$zzb r0 = new com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzch$zzb
            r0.<init>(r5, r6)
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzci$zza r4 = com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzci.zzd
            boolean r3 = r4.zzf(r5, r3, r0)
            if (r3 == 0) goto L3d
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzcn r1 = com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzcn.INSTANCE     // Catch: java.lang.Throwable -> L30
            r6.zzb(r0, r1)     // Catch: java.lang.Throwable -> L30
            return r2
        L30:
            r6 = move-exception
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzch$zzc r1 = new com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzch$zzc     // Catch: java.lang.Throwable -> L37
            r1.<init>(r6)     // Catch: java.lang.Throwable -> L37
            goto L39
        L37:
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzch$zzc r1 = com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzch.zzc.zza
        L39:
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzci.zzq(r5, r0, r1)
            return r2
        L3d:
            java.lang.Object r0 = r5.valueField
        L3f:
            boolean r2 = r0 instanceof com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzch.zza
            if (r2 == 0) goto L4a
            com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzch$zza r0 = (com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzch.zza) r0
            boolean r0 = r0.zzc
            r6.cancel(r0)
        L4a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzch.zzj(com.google.android.gms.internal.play_billing_eap_cross_sell_per_transaction_offer.zzcx):boolean");
    }

    public final void zzt(StringBuilder sb) {
        try {
            Object objZzs = zzs(this);
            sb.append("SUCCESS, result=[");
            if (objZzs == null) {
                sb.append(AbstractJsonLexerKt.NULL);
            } else if (objZzs == this) {
                sb.append("this future");
            } else {
                sb.append(objZzs.getClass().getName());
                sb.append("@");
                sb.append(Integer.toHexString(System.identityHashCode(objZzs)));
            }
            sb.append("]");
        } catch (CancellationException unused) {
            sb.append("CANCELLED");
        } catch (ExecutionException e) {
            sb.append("FAILURE, cause=[");
            sb.append(e.getCause());
            sb.append("]");
        } catch (Exception e2) {
            sb.append("UNKNOWN, cause=[");
            sb.append(e2.getClass());
            sb.append(" thrown from get()]");
        }
    }

    @Override // java.util.concurrent.Future
    public final Object get(long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        return zzm(j, timeUnit);
    }

    public void zzg() {
    }
}
