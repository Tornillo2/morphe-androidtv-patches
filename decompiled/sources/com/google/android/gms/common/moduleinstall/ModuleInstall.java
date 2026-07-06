package com.google.android.gms.common.moduleinstall;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.gms.common.moduleinstall.internal.zay;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class ModuleInstall {
    @NonNull
    public static ModuleInstallClient getClient(@NonNull Activity activity) {
        return new zay(activity);
    }

    @NonNull
    public static ModuleInstallClient getClient(@NonNull Context context) {
        return new zay(context);
    }
}
