package com.google.android.gms.common.api.internal;

import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.IAccountAccessor;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@WorkerThread
public interface zacs {
    void zae(ConnectionResult connectionResult);

    void zaf(@Nullable IAccountAccessor iAccountAccessor, @Nullable Set set);

    void zag(int i);
}
