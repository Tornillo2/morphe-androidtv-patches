package com.google.android.gms.common.api;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.api.internal.ApiKey;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.NonGmsServiceBrokerClient;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.common.api.internal.RegistrationMethods;
import com.google.android.gms.common.api.internal.StatusExceptionMapper;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.gms.common.api.internal.zaae;
import com.google.android.gms.common.api.internal.zabq;
import com.google.android.gms.common.api.internal.zabv;
import com.google.android.gms.common.api.internal.zact;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.InlineMe;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;
import java.util.Collections;
import java.util.Set;
import org.checkerframework.checker.initialization.qual.NotOnlyInitialized;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class GoogleApi<O extends Api.ApiOptions> implements HasApiKey<O> {

    @NonNull
    public final GoogleApiManager zaa;
    public final Context zab;

    @Nullable
    public final String zac;
    public final Api zad;
    public final Api.ApiOptions zae;
    public final ApiKey zaf;
    public final Looper zag;
    public final int zah;

    @NotOnlyInitialized
    public final GoogleApiClient zai;
    public final StatusExceptionMapper zaj;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @KeepForSdk
    public static class Settings {

        @NonNull
        @KeepForSdk
        public static final Settings DEFAULT_SETTINGS = new Builder().build();

        @NonNull
        public final StatusExceptionMapper zaa;

        @NonNull
        public final Looper zab;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        @KeepForSdk
        public static class Builder {
            public StatusExceptionMapper zaa;
            public Looper zab;

            @KeepForSdk
            public Builder() {
            }

            @NonNull
            @KeepForSdk
            public Settings build() {
                if (this.zaa == null) {
                    this.zaa = new ApiExceptionMapper();
                }
                if (this.zab == null) {
                    this.zab = Looper.getMainLooper();
                }
                return new Settings(this.zaa, null, this.zab);
            }

            @NonNull
            @CanIgnoreReturnValue
            @KeepForSdk
            public Builder setLooper(@NonNull Looper looper) {
                Preconditions.checkNotNull(looper, "Looper must not be null.");
                this.zab = looper;
                return this;
            }

            @NonNull
            @CanIgnoreReturnValue
            @KeepForSdk
            public Builder setMapper(@NonNull StatusExceptionMapper statusExceptionMapper) {
                Preconditions.checkNotNull(statusExceptionMapper, "StatusExceptionMapper must not be null.");
                this.zaa = statusExceptionMapper;
                return this;
            }
        }

        @KeepForSdk
        public Settings(StatusExceptionMapper statusExceptionMapper, Account account, Looper looper) {
            this.zaa = statusExceptionMapper;
            this.zab = looper;
        }
    }

    @KeepForSdk
    @MainThread
    public GoogleApi(@NonNull Activity activity, @NonNull Api<O> api, @NonNull O o, @NonNull Settings settings) {
        this(activity, activity, api, o, settings);
    }

    @NonNull
    @KeepForSdk
    public GoogleApiClient asGoogleApiClient() {
        return this.zai;
    }

    @NonNull
    @KeepForSdk
    public ClientSettings.Builder createClientSettingsBuilder() {
        Account account;
        GoogleSignInAccount googleSignInAccount;
        GoogleSignInAccount googleSignInAccount2;
        ClientSettings.Builder builder = new ClientSettings.Builder();
        Api.ApiOptions apiOptions = this.zae;
        if (!(apiOptions instanceof Api.ApiOptions.HasGoogleSignInAccountOptions) || (googleSignInAccount2 = ((Api.ApiOptions.HasGoogleSignInAccountOptions) apiOptions).getGoogleSignInAccount()) == null) {
            Api.ApiOptions apiOptions2 = this.zae;
            account = apiOptions2 instanceof Api.ApiOptions.HasAccountOptions ? ((Api.ApiOptions.HasAccountOptions) apiOptions2).getAccount() : null;
        } else {
            account = googleSignInAccount2.getAccount();
        }
        builder.zaa = account;
        Api.ApiOptions apiOptions3 = this.zae;
        Set<Scope> requestedScopes = (!(apiOptions3 instanceof Api.ApiOptions.HasGoogleSignInAccountOptions) || (googleSignInAccount = ((Api.ApiOptions.HasGoogleSignInAccountOptions) apiOptions3).getGoogleSignInAccount()) == null) ? Collections.EMPTY_SET : googleSignInAccount.getRequestedScopes();
        builder.zaa(requestedScopes);
        builder.zad = this.zab.getClass().getName();
        builder.zac = this.zab.getPackageName();
        return builder;
    }

    @NonNull
    @KeepForSdk
    public Task<Boolean> disconnectService() {
        return this.zaa.zan(this);
    }

    @NonNull
    @KeepForSdk
    public <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T doBestEffortWrite(@NonNull T t) {
        zad(2, t);
        return t;
    }

    @NonNull
    @KeepForSdk
    public <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T doRead(@NonNull T t) {
        zad(0, t);
        return t;
    }

    @NonNull
    @ResultIgnorabilityUnspecified
    @Deprecated
    @KeepForSdk
    public <A extends Api.AnyClient, T extends RegisterListenerMethod<A, ?>, U extends UnregisterListenerMethod<A, ?>> Task<Void> doRegisterEventListener(@NonNull T t, @NonNull U u) {
        Preconditions.checkNotNull(t);
        Preconditions.checkNotNull(u);
        Preconditions.checkNotNull(t.getListenerKey(), "Listener has already been released.");
        Preconditions.checkNotNull(u.getListenerKey(), "Listener has already been released.");
        Preconditions.checkArgument(Objects.equal(t.getListenerKey(), u.getListenerKey()), "Listener registration and unregistration methods must be constructed with the same ListenerHolder.");
        return this.zaa.zao(this, t, u, zad.zaa);
    }

    @NonNull
    @ResultIgnorabilityUnspecified
    @KeepForSdk
    public Task<Boolean> doUnregisterEventListener(@NonNull ListenerHolder.ListenerKey<?> listenerKey) {
        return doUnregisterEventListener(listenerKey, 0);
    }

    @NonNull
    @KeepForSdk
    public <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T doWrite(@NonNull T t) {
        zad(1, t);
        return t;
    }

    @Nullable
    public String getApiFallbackAttributionTag(@NonNull Context context) {
        return null;
    }

    @Override // com.google.android.gms.common.api.HasApiKey
    @NonNull
    public final ApiKey<O> getApiKey() {
        return this.zaf;
    }

    @NonNull
    @KeepForSdk
    public O getApiOptions() {
        return (O) this.zae;
    }

    @NonNull
    @KeepForSdk
    public Context getApplicationContext() {
        return this.zab;
    }

    @Nullable
    @KeepForSdk
    public String getContextAttributionTag() {
        return this.zac;
    }

    @Nullable
    @KeepForSdk
    @Deprecated
    public String getContextFeatureId() {
        return this.zac;
    }

    @NonNull
    @KeepForSdk
    public Looper getLooper() {
        return this.zag;
    }

    @NonNull
    @KeepForSdk
    public <L> ListenerHolder<L> registerListener(@NonNull L l, @NonNull String str) {
        return ListenerHolders.createListenerHolder(l, this.zag, str);
    }

    public final int zaa() {
        return this.zah;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @WorkerThread
    public final Api.Client zab(Looper looper, zabq zabqVar) {
        ClientSettings clientSettingsBuild = createClientSettingsBuilder().build();
        Api.AbstractClientBuilder abstractClientBuilder = this.zad.zaa;
        Preconditions.checkNotNull(abstractClientBuilder);
        Api.Client clientBuildClient = abstractClientBuilder.buildClient(this.zab, looper, clientSettingsBuild, this.zae, (GoogleApiClient.ConnectionCallbacks) zabqVar, (GoogleApiClient.OnConnectionFailedListener) zabqVar);
        String contextAttributionTag = getContextAttributionTag();
        if (contextAttributionTag != null && (clientBuildClient instanceof BaseGmsClient)) {
            ((BaseGmsClient) clientBuildClient).setAttributionTag(contextAttributionTag);
        }
        if (contextAttributionTag != null && (clientBuildClient instanceof NonGmsServiceBrokerClient)) {
            ((NonGmsServiceBrokerClient) clientBuildClient).zal = contextAttributionTag;
        }
        return clientBuildClient;
    }

    public final zact zac(Context context, Handler handler) {
        return new zact(context, handler, createClientSettingsBuilder().build());
    }

    public final BaseImplementation.ApiMethodImpl zad(int i, @NonNull BaseImplementation.ApiMethodImpl apiMethodImpl) {
        apiMethodImpl.zak();
        this.zaa.zau(this, i, apiMethodImpl);
        return apiMethodImpl;
    }

    public final Task zae(int i, @NonNull TaskApiCall taskApiCall) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zaa.zav(this, i, taskApiCall, taskCompletionSource, this.zaj);
        return taskCompletionSource.zza;
    }

    @KeepForSdk
    @Deprecated
    public GoogleApi(@NonNull Activity activity, @NonNull Api<O> api, @NonNull O o, @NonNull StatusExceptionMapper statusExceptionMapper) {
        Settings.Builder builder = new Settings.Builder();
        builder.setMapper(statusExceptionMapper);
        builder.setLooper(activity.getMainLooper());
        this(activity, activity, api, o, builder.build());
    }

    @NonNull
    @ResultIgnorabilityUnspecified
    @KeepForSdk
    public <TResult, A extends Api.AnyClient> Task<TResult> doBestEffortWrite(@NonNull TaskApiCall<A, TResult> taskApiCall) {
        return zae(2, taskApiCall);
    }

    @NonNull
    @ResultIgnorabilityUnspecified
    @KeepForSdk
    public <TResult, A extends Api.AnyClient> Task<TResult> doRead(@NonNull TaskApiCall<A, TResult> taskApiCall) {
        return zae(0, taskApiCall);
    }

    @NonNull
    @ResultIgnorabilityUnspecified
    @KeepForSdk
    public Task<Boolean> doUnregisterEventListener(@NonNull ListenerHolder.ListenerKey<?> listenerKey, int i) {
        Preconditions.checkNotNull(listenerKey, "Listener key cannot be null.");
        return this.zaa.zap(this, listenerKey, i);
    }

    @NonNull
    @ResultIgnorabilityUnspecified
    @KeepForSdk
    public <TResult, A extends Api.AnyClient> Task<TResult> doWrite(@NonNull TaskApiCall<A, TResult> taskApiCall) {
        return zae(1, taskApiCall);
    }

    public GoogleApi(@NonNull Context context, @Nullable Activity activity, Api api, Api.ApiOptions apiOptions, Settings settings) {
        Preconditions.checkNotNull(context, "Null context is not permitted.");
        Preconditions.checkNotNull(api, "Api must not be null.");
        Preconditions.checkNotNull(settings, "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
        Context applicationContext = context.getApplicationContext();
        Preconditions.checkNotNull(applicationContext, "The provided context did not have an application context.");
        this.zab = applicationContext;
        String attributionTag = Build.VERSION.SDK_INT >= 30 ? context.getAttributionTag() : null;
        this.zac = attributionTag;
        this.zad = api;
        this.zae = apiOptions;
        this.zag = settings.zab;
        ApiKey apiKey = new ApiKey(api, apiOptions, attributionTag);
        this.zaf = apiKey;
        this.zai = new zabv(this);
        GoogleApiManager googleApiManagerZak = GoogleApiManager.zak(applicationContext);
        this.zaa = googleApiManagerZak;
        this.zah = googleApiManagerZak.zal.getAndIncrement();
        this.zaj = settings.zaa;
        if (activity != null && !(activity instanceof GoogleApiActivity) && Looper.myLooper() == Looper.getMainLooper()) {
            zaae.zad(activity, googleApiManagerZak, apiKey);
        }
        googleApiManagerZak.zaz(this);
    }

    @NonNull
    @ResultIgnorabilityUnspecified
    @KeepForSdk
    public <A extends Api.AnyClient> Task<Void> doRegisterEventListener(@NonNull RegistrationMethods<A, ?> registrationMethods) {
        Preconditions.checkNotNull(registrationMethods);
        Preconditions.checkNotNull(registrationMethods.register.getListenerKey(), "Listener has already been released.");
        Preconditions.checkNotNull(registrationMethods.zaa.getListenerKey(), "Listener has already been released.");
        return this.zaa.zao(this, registrationMethods.register, registrationMethods.zaa, registrationMethods.zab);
    }

    @InlineMe(imports = {"com.google.android.gms.common.api.GoogleApi.Settings"}, replacement = "this(context, api, options, new Settings.Builder().setLooper(looper).setMapper(mapper).build())")
    @KeepForSdk
    @Deprecated
    public GoogleApi(@NonNull Context context, @NonNull Api<O> api, @NonNull O o, @NonNull Looper looper, @NonNull StatusExceptionMapper statusExceptionMapper) {
        Settings.Builder builder = new Settings.Builder();
        builder.setLooper(looper);
        builder.setMapper(statusExceptionMapper);
        this(context, (Activity) null, api, o, builder.build());
    }

    @KeepForSdk
    public GoogleApi(@NonNull Context context, @NonNull Api<O> api, @NonNull O o, @NonNull Settings settings) {
        this(context, (Activity) null, api, o, settings);
    }

    @InlineMe(imports = {"com.google.android.gms.common.api.GoogleApi.Settings"}, replacement = "this(context, api, options, new Settings.Builder().setMapper(mapper).build())")
    @KeepForSdk
    @Deprecated
    public GoogleApi(@NonNull Context context, @NonNull Api<O> api, @NonNull O o, @NonNull StatusExceptionMapper statusExceptionMapper) {
        Settings.Builder builder = new Settings.Builder();
        builder.setMapper(statusExceptionMapper);
        this(context, (Activity) null, api, o, builder.build());
    }
}
