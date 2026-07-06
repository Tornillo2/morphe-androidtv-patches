package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import android.util.Log;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.ICancelToken;
import com.google.android.gms.common.internal.Preconditions;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@KeepForSdk
@KeepName
public abstract class BasePendingResult<R extends Result> extends PendingResult<R> {
    public static final ThreadLocal zaa = new zaq();
    public static final /* synthetic */ int zad = 0;

    @KeepName
    private zas resultGuardian;

    @NonNull
    public final CallbackHandler zab;

    @NonNull
    public final WeakReference zac;
    public final Object zae;
    public final CountDownLatch zaf;
    public final ArrayList zag;

    @Nullable
    public ResultCallback zah;
    public final AtomicReference zai;

    @Nullable
    public Result zaj;
    public Status zak;
    public volatile boolean zal;
    public boolean zam;
    public boolean zan;

    @Nullable
    public ICancelToken zao;
    public volatile zada zap;
    public boolean zaq;

    @Deprecated
    public BasePendingResult() {
        this.zae = new Object();
        this.zaf = new CountDownLatch(1);
        this.zag = new ArrayList();
        this.zai = new AtomicReference();
        this.zaq = false;
        this.zab = new CallbackHandler(Looper.getMainLooper());
        this.zac = new WeakReference(null);
    }

    public static void zal(@Nullable Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (RuntimeException e) {
                Log.w("BasePendingResult", "Unable to release ".concat(String.valueOf(result)), e);
            }
        }
    }

    @Override // com.google.android.gms.common.api.PendingResult
    public final void addStatusListener(@NonNull PendingResult.StatusListener statusListener) {
        Preconditions.checkArgument(statusListener != null, "Callback cannot be null.");
        synchronized (this.zae) {
            try {
                if (isReady()) {
                    statusListener.onComplete(this.zak);
                } else {
                    this.zag.add(statusListener);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.google.android.gms.common.api.PendingResult
    @NonNull
    @ResultIgnorabilityUnspecified
    public final R await() {
        Preconditions.checkNotMainThread("await must not be called on the UI thread");
        Preconditions.checkState(!this.zal, "Result has already been consumed");
        Preconditions.checkState(this.zap == null, "Cannot await if then() has been called.");
        try {
            this.zaf.await();
        } catch (InterruptedException unused) {
            forceFailureUnlessReady(Status.RESULT_INTERRUPTED);
        }
        Preconditions.checkState(isReady(), "Result is not ready.");
        return (R) zaa();
    }

    @Override // com.google.android.gms.common.api.PendingResult
    @KeepForSdk
    public void cancel() {
        synchronized (this.zae) {
            if (!this.zam && !this.zal) {
                ICancelToken iCancelToken = this.zao;
                if (iCancelToken != null) {
                    try {
                        iCancelToken.cancel();
                    } catch (RemoteException unused) {
                    }
                }
                zal(this.zaj);
                this.zam = true;
                zab(createFailedResult(Status.RESULT_CANCELED));
            }
        }
    }

    @NonNull
    @KeepForSdk
    public abstract R createFailedResult(@NonNull Status status);

    @KeepForSdk
    @Deprecated
    public final void forceFailureUnlessReady(@NonNull Status status) {
        synchronized (this.zae) {
            try {
                if (!isReady()) {
                    setResult(createFailedResult(status));
                    this.zan = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.google.android.gms.common.api.PendingResult
    public final boolean isCanceled() {
        boolean z;
        synchronized (this.zae) {
            z = this.zam;
        }
        return z;
    }

    @KeepForSdk
    public final boolean isReady() {
        return this.zaf.getCount() == 0;
    }

    @KeepForSdk
    public final void setCancelToken(@NonNull ICancelToken iCancelToken) {
        synchronized (this.zae) {
            this.zao = iCancelToken;
        }
    }

    @KeepForSdk
    public final void setResult(@NonNull R r) {
        synchronized (this.zae) {
            try {
                if (this.zan || this.zam) {
                    zal(r);
                    return;
                }
                isReady();
                Preconditions.checkState(!isReady(), "Results have already been set");
                Preconditions.checkState(!this.zal, "Result has already been consumed");
                zab(r);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.google.android.gms.common.api.PendingResult
    @KeepForSdk
    public final void setResultCallback(@Nullable ResultCallback<? super R> resultCallback) {
        synchronized (this.zae) {
            try {
                if (resultCallback == null) {
                    this.zah = null;
                    return;
                }
                boolean z = true;
                Preconditions.checkState(!this.zal, "Result has already been consumed.");
                if (this.zap != null) {
                    z = false;
                }
                Preconditions.checkState(z, "Cannot set callbacks if then() has been called.");
                if (isCanceled()) {
                    return;
                }
                if (isReady()) {
                    this.zab.zaa(resultCallback, zaa());
                } else {
                    this.zah = resultCallback;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.google.android.gms.common.api.PendingResult
    @NonNull
    public final <S extends Result> TransformedResult<S> then(@NonNull ResultTransform<? super R, ? extends S> resultTransform) {
        TransformedResult<S> transformedResultThen;
        Preconditions.checkState(!this.zal, "Result has already been consumed.");
        synchronized (this.zae) {
            try {
                Preconditions.checkState(this.zap == null, "Cannot call then() twice.");
                Preconditions.checkState(this.zah == null, "Cannot call then() if callbacks are set.");
                Preconditions.checkState(!this.zam, "Cannot call then() if result was canceled.");
                this.zaq = true;
                this.zap = new zada(this.zac);
                transformedResultThen = this.zap.then(resultTransform);
                if (isReady()) {
                    this.zab.zaa(this.zap, zaa());
                } else {
                    this.zah = this.zap;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return transformedResultThen;
    }

    public final Result zaa() {
        Result result;
        synchronized (this.zae) {
            Preconditions.checkState(!this.zal, "Result has already been consumed.");
            Preconditions.checkState(isReady(), "Result is not ready.");
            result = this.zaj;
            this.zaj = null;
            this.zah = null;
            this.zal = true;
        }
        zadb zadbVar = (zadb) this.zai.getAndSet(null);
        if (zadbVar != null) {
            zadbVar.zaa.zab.remove(this);
        }
        Preconditions.checkNotNull(result);
        return result;
    }

    public final void zab(Result result) {
        this.zaj = result;
        this.zak = result.getStatus();
        zar zarVar = null;
        this.zao = null;
        this.zaf.countDown();
        if (this.zam) {
            this.zah = null;
        } else {
            ResultCallback resultCallback = this.zah;
            if (resultCallback != null) {
                this.zab.removeMessages(2);
                this.zab.zaa(resultCallback, zaa());
            } else if (this.zaj instanceof Releasable) {
                this.resultGuardian = new zas(this, zarVar);
            }
        }
        ArrayList arrayList = this.zag;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((PendingResult.StatusListener) arrayList.get(i)).onComplete(this.zak);
        }
        this.zag.clear();
    }

    public final void zak() {
        boolean z = true;
        if (!this.zaq && !((Boolean) zaa.get()).booleanValue()) {
            z = false;
        }
        this.zaq = z;
    }

    public final boolean zam() {
        boolean zIsCanceled;
        synchronized (this.zae) {
            try {
                if (((GoogleApiClient) this.zac.get()) == null || !this.zaq) {
                    cancel();
                }
                zIsCanceled = isCanceled();
            } catch (Throwable th) {
                throw th;
            }
        }
        return zIsCanceled;
    }

    public final void zan(@Nullable zadb zadbVar) {
        this.zai.set(zadbVar);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @VisibleForTesting
    public static class CallbackHandler<R extends Result> extends com.google.android.gms.internal.base.zau {
        public CallbackHandler() {
            super(Looper.getMainLooper());
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.os.Handler
        public final void handleMessage(@NonNull Message message) {
            int i = message.what;
            if (i != 1) {
                if (i != 2) {
                    Log.wtf("BasePendingResult", MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Don't know how to handle message: ", i), new Exception());
                    return;
                } else {
                    ((BasePendingResult) message.obj).forceFailureUnlessReady(Status.RESULT_TIMEOUT);
                    return;
                }
            }
            Pair pair = (Pair) message.obj;
            ResultCallback resultCallback = (ResultCallback) pair.first;
            Result result = (Result) pair.second;
            try {
                resultCallback.onResult(result);
            } catch (RuntimeException e) {
                BasePendingResult.zal(result);
                throw e;
            }
        }

        public final void zaa(@NonNull ResultCallback resultCallback, @NonNull Result result) {
            int i = BasePendingResult.zad;
            Preconditions.checkNotNull(resultCallback);
            sendMessage(obtainMessage(1, new Pair(resultCallback, result)));
        }

        public CallbackHandler(@NonNull Looper looper) {
            super(looper);
        }
    }

    @Override // com.google.android.gms.common.api.PendingResult
    @NonNull
    @ResultIgnorabilityUnspecified
    public final R await(long j, @NonNull TimeUnit timeUnit) {
        if (j > 0) {
            Preconditions.checkNotMainThread("await must not be called on the UI thread when time is greater than zero.");
        }
        Preconditions.checkState(!this.zal, "Result has already been consumed.");
        Preconditions.checkState(this.zap == null, "Cannot await if then() has been called.");
        try {
            if (!this.zaf.await(j, timeUnit)) {
                forceFailureUnlessReady(Status.RESULT_TIMEOUT);
            }
        } catch (InterruptedException unused) {
            forceFailureUnlessReady(Status.RESULT_INTERRUPTED);
        }
        Preconditions.checkState(isReady(), "Result is not ready.");
        return (R) zaa();
    }

    @KeepForSdk
    @Deprecated
    public BasePendingResult(@NonNull Looper looper) {
        this.zae = new Object();
        this.zaf = new CountDownLatch(1);
        this.zag = new ArrayList();
        this.zai = new AtomicReference();
        this.zaq = false;
        this.zab = new CallbackHandler(looper);
        this.zac = new WeakReference(null);
    }

    @Override // com.google.android.gms.common.api.PendingResult
    @KeepForSdk
    public final void setResultCallback(@NonNull ResultCallback<? super R> resultCallback, long j, @NonNull TimeUnit timeUnit) {
        synchronized (this.zae) {
            try {
                if (resultCallback == null) {
                    this.zah = null;
                    return;
                }
                boolean z = true;
                Preconditions.checkState(!this.zal, "Result has already been consumed.");
                if (this.zap != null) {
                    z = false;
                }
                Preconditions.checkState(z, "Cannot set callbacks if then() has been called.");
                if (isCanceled()) {
                    return;
                }
                if (isReady()) {
                    this.zab.zaa(resultCallback, zaa());
                } else {
                    this.zah = resultCallback;
                    CallbackHandler callbackHandler = this.zab;
                    callbackHandler.sendMessageDelayed(callbackHandler.obtainMessage(2, this), timeUnit.toMillis(j));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @KeepForSdk
    public BasePendingResult(@Nullable GoogleApiClient googleApiClient) {
        this.zae = new Object();
        this.zaf = new CountDownLatch(1);
        this.zag = new ArrayList();
        this.zai = new AtomicReference();
        this.zaq = false;
        this.zab = new CallbackHandler(googleApiClient != null ? googleApiClient.getLooper() : Looper.getMainLooper());
        this.zac = new WeakReference(googleApiClient);
    }

    @KeepForSdk
    @VisibleForTesting
    public BasePendingResult(@NonNull CallbackHandler<R> callbackHandler) {
        this.zae = new Object();
        this.zaf = new CountDownLatch(1);
        this.zag = new ArrayList();
        this.zai = new AtomicReference();
        this.zaq = false;
        Preconditions.checkNotNull(callbackHandler, "CallbackHandler must not be null");
        this.zab = callbackHandler;
        this.zac = new WeakReference(null);
    }
}
