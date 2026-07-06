package com.google.android.gms.common.moduleinstall.internal;

import android.app.Activity;
import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.OptionalModuleApi;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.RegistrationMethods;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.moduleinstall.InstallStatusListener;
import com.google.android.gms.common.moduleinstall.ModuleAvailabilityResponse;
import com.google.android.gms.common.moduleinstall.ModuleInstallClient;
import com.google.android.gms.common.moduleinstall.ModuleInstallIntentResponse;
import com.google.android.gms.common.moduleinstall.ModuleInstallRequest;
import com.google.android.gms.common.moduleinstall.ModuleInstallResponse;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;
import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zay extends GoogleApi implements ModuleInstallClient {
    public static final /* synthetic */ int zab = 0;
    public static final Api.ClientKey zac;
    public static final Api.AbstractClientBuilder zad;
    public static final Api zae;

    static {
        Api.ClientKey clientKey = new Api.ClientKey();
        zac = clientKey;
        zaq zaqVar = new zaq();
        zad = zaqVar;
        zae = new Api("ModuleInstall.API", zaqVar, clientKey);
    }

    public zay(Activity activity) {
        super(activity, activity, zae, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public static final ApiFeatureRequest zad(boolean z, OptionalModuleApi... optionalModuleApiArr) {
        Preconditions.checkNotNull(optionalModuleApiArr, "Requested APIs must not be null.");
        Preconditions.checkArgument(optionalModuleApiArr.length > 0, "Please provide at least one OptionalModuleApi.");
        for (OptionalModuleApi optionalModuleApi : optionalModuleApiArr) {
            Preconditions.checkNotNull(optionalModuleApi, "Requested API must not be null.");
        }
        return ApiFeatureRequest.zaa(Arrays.asList(optionalModuleApiArr), z);
    }

    @Override // com.google.android.gms.common.moduleinstall.ModuleInstallClient
    public final Task<ModuleAvailabilityResponse> areModulesAvailable(OptionalModuleApi... optionalModuleApiArr) {
        final ApiFeatureRequest apiFeatureRequestZad = zad(false, optionalModuleApiArr);
        if (apiFeatureRequestZad.zab.isEmpty()) {
            return Tasks.forResult(new ModuleAvailabilityResponse(true, 0));
        }
        TaskApiCall.Builder builder = TaskApiCall.builder();
        builder.zac = new Feature[]{com.google.android.gms.internal.base.zav.zaa};
        builder.zad = 27301;
        builder.zab = false;
        builder.zaa = new RemoteCall() { // from class: com.google.android.gms.common.moduleinstall.internal.zal
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                ((zaf) ((zaz) obj).getService()).zae(new zar(this.zaa, (TaskCompletionSource) obj2), apiFeatureRequestZad);
            }
        };
        return zae(0, builder.build());
    }

    @Override // com.google.android.gms.common.moduleinstall.ModuleInstallClient
    public final Task<Void> deferredInstall(OptionalModuleApi... optionalModuleApiArr) {
        final ApiFeatureRequest apiFeatureRequestZad = zad(false, optionalModuleApiArr);
        if (apiFeatureRequestZad.zab.isEmpty()) {
            return Tasks.forResult(null);
        }
        TaskApiCall.Builder builder = TaskApiCall.builder();
        builder.zac = new Feature[]{com.google.android.gms.internal.base.zav.zaa};
        builder.zad = 27302;
        builder.zab = false;
        builder.zaa = new RemoteCall() { // from class: com.google.android.gms.common.moduleinstall.internal.zap
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                ((zaf) ((zaz) obj).getService()).zag(new zas(this.zaa, (TaskCompletionSource) obj2), apiFeatureRequestZad, null);
            }
        };
        return zae(0, builder.build());
    }

    @Override // com.google.android.gms.common.moduleinstall.ModuleInstallClient
    public final Task<ModuleInstallIntentResponse> getInstallModulesIntent(OptionalModuleApi... optionalModuleApiArr) {
        final ApiFeatureRequest apiFeatureRequestZad = zad(true, optionalModuleApiArr);
        if (apiFeatureRequestZad.zab.isEmpty()) {
            return Tasks.forResult(new ModuleInstallIntentResponse(null));
        }
        TaskApiCall.Builder builder = TaskApiCall.builder();
        builder.zac = new Feature[]{com.google.android.gms.internal.base.zav.zaa};
        builder.zad = 27307;
        builder.zaa = new RemoteCall() { // from class: com.google.android.gms.common.moduleinstall.internal.zan
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                ((zaf) ((zaz) obj).getService()).zaf(new zaw(this.zaa, (TaskCompletionSource) obj2), apiFeatureRequestZad);
            }
        };
        return zae(0, builder.build());
    }

    @Override // com.google.android.gms.common.moduleinstall.ModuleInstallClient
    public final Task<ModuleInstallResponse> installModules(ModuleInstallRequest moduleInstallRequest) {
        final ApiFeatureRequest apiFeatureRequestFromModuleInstallRequest = ApiFeatureRequest.fromModuleInstallRequest(moduleInstallRequest);
        final InstallStatusListener installStatusListener = moduleInstallRequest.zab;
        Executor executor = moduleInstallRequest.zac;
        if (apiFeatureRequestFromModuleInstallRequest.zab.isEmpty()) {
            return Tasks.forResult(new ModuleInstallResponse(0, false));
        }
        if (installStatusListener == null) {
            TaskApiCall.Builder builder = TaskApiCall.builder();
            builder.zac = new Feature[]{com.google.android.gms.internal.base.zav.zaa};
            builder.zab = true;
            builder.zad = 27304;
            builder.zaa = new RemoteCall() { // from class: com.google.android.gms.common.moduleinstall.internal.zao
                /* JADX WARN: Multi-variable type inference failed */
                @Override // com.google.android.gms.common.api.internal.RemoteCall
                public final void accept(Object obj, Object obj2) throws RemoteException {
                    ((zaf) ((zaz) obj).getService()).zag(new zat(this.zaa, (TaskCompletionSource) obj2), apiFeatureRequestFromModuleInstallRequest, null);
                }
            };
            return zae(0, builder.build());
        }
        ListenerHolder listenerHolderCreateListenerHolder = executor == null ? ListenerHolders.createListenerHolder(installStatusListener, this.zag, "InstallStatusListener") : ListenerHolders.createListenerHolder(installStatusListener, executor, "InstallStatusListener");
        final zaab zaabVar = new zaab(listenerHolderCreateListenerHolder);
        final AtomicReference atomicReference = new AtomicReference();
        RemoteCall remoteCall = new RemoteCall() { // from class: com.google.android.gms.common.moduleinstall.internal.zai
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                ((zaf) ((zaz) obj).getService()).zag(new zau(this.zaa, atomicReference, (TaskCompletionSource) obj2, installStatusListener), apiFeatureRequestFromModuleInstallRequest, zaabVar);
            }
        };
        RemoteCall remoteCall2 = new RemoteCall() { // from class: com.google.android.gms.common.moduleinstall.internal.zaj
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                ((zaf) ((zaz) obj).getService()).zai(new zav(this.zaa, (TaskCompletionSource) obj2), zaabVar);
            }
        };
        RegistrationMethods.Builder builder2 = RegistrationMethods.builder();
        builder2.zad = listenerHolderCreateListenerHolder;
        builder2.zae = new Feature[]{com.google.android.gms.internal.base.zav.zaa};
        builder2.zaf = true;
        builder2.zaa = remoteCall;
        builder2.zab = remoteCall2;
        builder2.zag = 27305;
        return doRegisterEventListener(builder2.build()).onSuccessTask(new SuccessContinuation() { // from class: com.google.android.gms.common.moduleinstall.internal.zak
            @Override // com.google.android.gms.tasks.SuccessContinuation
            public final Task then(Object obj) {
                int i = zay.zab;
                AtomicReference atomicReference2 = atomicReference;
                return atomicReference2.get() != null ? Tasks.forResult((ModuleInstallResponse) atomicReference2.get()) : Tasks.forException(new ApiException(Status.RESULT_INTERNAL_ERROR));
            }
        });
    }

    @Override // com.google.android.gms.common.moduleinstall.ModuleInstallClient
    public final Task<Void> releaseModules(OptionalModuleApi... optionalModuleApiArr) {
        final ApiFeatureRequest apiFeatureRequestZad = zad(false, optionalModuleApiArr);
        if (apiFeatureRequestZad.zab.isEmpty()) {
            return Tasks.forResult(null);
        }
        TaskApiCall.Builder builder = TaskApiCall.builder();
        builder.zac = new Feature[]{com.google.android.gms.internal.base.zav.zaa};
        builder.zad = 27303;
        builder.zab = false;
        builder.zaa = new RemoteCall() { // from class: com.google.android.gms.common.moduleinstall.internal.zam
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                ((zaf) ((zaz) obj).getService()).zah(new zax(this.zaa, (TaskCompletionSource) obj2), apiFeatureRequestZad);
            }
        };
        return zae(0, builder.build());
    }

    @Override // com.google.android.gms.common.moduleinstall.ModuleInstallClient
    @ResultIgnorabilityUnspecified
    public final Task<Boolean> unregisterListener(InstallStatusListener installStatusListener) {
        return doUnregisterEventListener(ListenerHolders.createListenerKey(installStatusListener, "InstallStatusListener"), 27306);
    }

    public zay(Context context) {
        super(context, (Activity) null, zae, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }
}
