package com.google.android.gms.common.moduleinstall.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.common.moduleinstall.InstallStatusListener;
import com.google.android.gms.common.moduleinstall.ModuleInstallResponse;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zau extends zaa {
    public final /* synthetic */ AtomicReference zaa;
    public final /* synthetic */ TaskCompletionSource zab;
    public final /* synthetic */ InstallStatusListener zac;
    public final /* synthetic */ zay zad;

    public zau(zay zayVar, AtomicReference atomicReference, TaskCompletionSource taskCompletionSource, InstallStatusListener installStatusListener) {
        this.zad = zayVar;
        this.zaa = atomicReference;
        this.zab = taskCompletionSource;
        this.zac = installStatusListener;
    }

    @Override // com.google.android.gms.common.moduleinstall.internal.zaa, com.google.android.gms.common.moduleinstall.internal.zae
    public final void zad(Status status, @Nullable ModuleInstallResponse moduleInstallResponse) {
        if (moduleInstallResponse != null) {
            this.zaa.set(moduleInstallResponse);
        }
        TaskUtil.trySetResultOrApiException(status, null, this.zab);
        if (!status.isSuccess() || (moduleInstallResponse != null && moduleInstallResponse.zab)) {
            this.zad.doUnregisterEventListener(ListenerHolders.createListenerKey(this.zac, "InstallStatusListener"), 27306);
        }
    }
}
