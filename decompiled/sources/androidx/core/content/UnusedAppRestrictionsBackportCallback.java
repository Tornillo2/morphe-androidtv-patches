package androidx.core.content;

import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.core.app.unusedapprestrictions.IUnusedAppRestrictionsBackportCallback;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class UnusedAppRestrictionsBackportCallback {
    public IUnusedAppRestrictionsBackportCallback mCallback;

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public UnusedAppRestrictionsBackportCallback(@NonNull IUnusedAppRestrictionsBackportCallback iUnusedAppRestrictionsBackportCallback) {
        this.mCallback = iUnusedAppRestrictionsBackportCallback;
    }

    public void onResult(boolean z, boolean z2) throws RemoteException {
        this.mCallback.onIsPermissionRevocationEnabledForAppResult(z, z2);
    }
}
