package com.google.android.gms.common.moduleinstall.internal;

import android.os.IInterface;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.moduleinstall.ModuleAvailabilityResponse;
import com.google.android.gms.common.moduleinstall.ModuleInstallIntentResponse;
import com.google.android.gms.common.moduleinstall.ModuleInstallResponse;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface zae extends IInterface {
    void zab(Status status) throws RemoteException;

    void zac(Status status, @Nullable ModuleInstallIntentResponse moduleInstallIntentResponse) throws RemoteException;

    void zad(Status status, @Nullable ModuleInstallResponse moduleInstallResponse) throws RemoteException;

    void zae(Status status, @Nullable ModuleAvailabilityResponse moduleAvailabilityResponse) throws RemoteException;
}
