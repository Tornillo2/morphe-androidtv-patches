package com.google.android.gms.common.api.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zacx implements Continuation {
    @Override // com.google.android.gms.tasks.Continuation
    public final Object then(@NonNull Task task) throws Exception {
        if (((Boolean) task.getResult()).booleanValue()) {
            return null;
        }
        throw new ApiException(new Status(13, "listener already unregistered", null, null));
    }
}
