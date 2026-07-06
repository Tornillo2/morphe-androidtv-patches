package com.amazon.livingroom.deviceproperties;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.core.os.EnvironmentCompat;
import com.amazon.livingroom.di.ApplicationContext;
import javax.inject.Inject;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class ApplicationSourcePropertiesProvider {

    @NotNull
    public final Context context;

    @NotNull
    public final PackageManager packageManager;

    @Inject
    public ApplicationSourcePropertiesProvider(@ApplicationContext @NotNull Context context, @NotNull PackageManager packageManager) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(packageManager, "packageManager");
        this.context = context;
        this.packageManager = packageManager;
    }

    @NotNull
    public final String getInstallSource() {
        String installingPackageName;
        String packageName = this.context.getPackageName();
        if (Build.VERSION.SDK_INT >= 30) {
            try {
                installingPackageName = this.packageManager.getInstallSourceInfo(packageName).getInstallingPackageName();
            } catch (PackageManager.NameNotFoundException unused) {
                installingPackageName = null;
            }
        } else {
            installingPackageName = this.packageManager.getInstallerPackageName(packageName);
        }
        return installingPackageName == null ? EnvironmentCompat.MEDIA_UNKNOWN : installingPackageName;
    }
}
