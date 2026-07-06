package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BasePendingResult;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@KeepForSdk
public class BaseImplementation {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @KeepForSdk
    public interface ResultHolder<R> {
        @KeepForSdk
        void setFailedResult(@NonNull Status status);

        @KeepForSdk
        void setResult(@NonNull R r);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @KeepForSdk
    public static abstract class ApiMethodImpl<R extends Result, A extends Api.AnyClient> extends BasePendingResult<R> implements ResultHolder<R> {

        @Nullable
        @KeepForSdk
        public final Api<?> api;

        @KeepForSdk
        public final Api.AnyClientKey<A> clientKey;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        @KeepForSdk
        @Deprecated
        public ApiMethodImpl(@NonNull Api.AnyClientKey<A> anyClientKey, @NonNull GoogleApiClient googleApiClient) {
            super(googleApiClient);
            Preconditions.checkNotNull(googleApiClient, "GoogleApiClient must not be null");
            Preconditions.checkNotNull(anyClientKey);
            this.clientKey = anyClientKey;
            this.api = null;
        }

        @KeepForSdk
        public abstract void doExecute(@NonNull A a) throws RemoteException;

        @Nullable
        @KeepForSdk
        public final Api<?> getApi() {
            return this.api;
        }

        @NonNull
        @KeepForSdk
        public final Api.AnyClientKey<A> getClientKey() {
            return this.clientKey;
        }

        @KeepForSdk
        public final void run(@NonNull A a) throws DeadObjectException {
            try {
                doExecute(a);
            } catch (DeadObjectException e) {
                setFailedResult(e);
                throw e;
            } catch (RemoteException e2) {
                setFailedResult(e2);
            }
        }

        @KeepForSdk
        public final void setFailedResult(@NonNull RemoteException remoteException) {
            setFailedResult(new Status(8, remoteException.getLocalizedMessage(), null, null));
        }

        @Override // com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder
        @KeepForSdk
        public /* bridge */ /* synthetic */ void setResult(@NonNull Object obj) {
            setResult((Result) obj);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        @KeepForSdk
        public ApiMethodImpl(@NonNull Api<?> api, @NonNull GoogleApiClient googleApiClient) {
            super(googleApiClient);
            Preconditions.checkNotNull(googleApiClient, "GoogleApiClient must not be null");
            Preconditions.checkNotNull(api, "Api must not be null");
            this.clientKey = api.zab;
            this.api = api;
        }

        @Override // com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder
        @KeepForSdk
        public final void setFailedResult(@NonNull Status status) {
            Preconditions.checkArgument(!status.isSuccess(), "Failed result must not be success");
            setResult((Result) createFailedResult(status));
        }

        @KeepForSdk
        @VisibleForTesting
        public ApiMethodImpl(@NonNull BasePendingResult.CallbackHandler<R> callbackHandler) {
            super(callbackHandler);
            this.clientKey = new Api.AnyClientKey<>();
            this.api = null;
        }

        @KeepForSdk
        public void onSetFailedResult(@NonNull R r) {
        }
    }
}
