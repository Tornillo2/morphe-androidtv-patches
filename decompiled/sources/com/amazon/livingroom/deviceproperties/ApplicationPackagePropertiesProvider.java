package com.amazon.livingroom.deviceproperties;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.core.content.pm.PackageInfoCompat;
import androidx.core.os.EnvironmentCompat;
import com.amazon.livingroom.di.ApplicationContext;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.reporting.Log;
import javax.inject.Inject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public class ApplicationPackagePropertiesProvider {
    public static final String LOG_TAG = "ApplicationPackagePropertiesProvider";
    public long applicationVersionCode;
    public final Context context;
    public boolean initialized;
    public final PackageManager packageManager;
    public String applicationVersionName = EnvironmentCompat.MEDIA_UNKNOWN;
    public String applicationPackageName = EnvironmentCompat.MEDIA_UNKNOWN;

    @Inject
    public ApplicationPackagePropertiesProvider(@NonNull @ApplicationContext Context context, @NonNull PackageManager packageManager) {
        this.context = context;
        this.packageManager = packageManager;
    }

    public final synchronized void ensureInitialized() {
        PackageInfo packageInfo;
        if (this.initialized) {
            return;
        }
        String packageName = this.context.getPackageName();
        this.applicationPackageName = packageName;
        try {
            packageInfo = this.packageManager.getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(LOG_TAG, "Failed to find package " + this.applicationPackageName, e);
            packageInfo = null;
        }
        if (packageInfo == null) {
            Log.e(LOG_TAG, "Got null package info for " + this.applicationPackageName);
            return;
        }
        this.applicationVersionCode = PackageInfoCompat.getLongVersionCode(packageInfo);
        if (!TextUtils.isEmpty(packageInfo.versionName)) {
            this.applicationVersionName = packageInfo.versionName;
        }
        this.initialized = true;
    }

    public String getApplicationPackageName() {
        ensureInitialized();
        return this.applicationPackageName;
    }

    public long getApplicationVersionCode() {
        ensureInitialized();
        return this.applicationVersionCode;
    }

    @NonNull
    public String getApplicationVersionName() {
        ensureInitialized();
        return this.applicationVersionName;
    }
}
