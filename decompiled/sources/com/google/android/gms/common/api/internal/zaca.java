package com.google.android.gms.common.api.internal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.BaseImplementation;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface zaca {
    ConnectionResult zab();

    ConnectionResult zac(long j, TimeUnit timeUnit);

    @Nullable
    ConnectionResult zad(@NonNull Api api);

    BaseImplementation.ApiMethodImpl zae(@NonNull BaseImplementation.ApiMethodImpl apiMethodImpl);

    BaseImplementation.ApiMethodImpl zaf(@NonNull BaseImplementation.ApiMethodImpl apiMethodImpl);

    void zaq();

    void zar();

    void zas(String str, @Nullable FileDescriptor fileDescriptor, PrintWriter printWriter, @Nullable String[] strArr);

    void zat();

    void zau();

    boolean zaw();

    boolean zax();

    boolean zay(SignInConnectionListener signInConnectionListener);
}
