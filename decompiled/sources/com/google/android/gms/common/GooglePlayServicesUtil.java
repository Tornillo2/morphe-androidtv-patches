package com.google.android.gms.common;

import android.app.Activity;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.HideFirstParty;
import com.google.android.gms.common.internal.zae;
import com.google.errorprone.annotations.InlineMe;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class GooglePlayServicesUtil extends GooglePlayServicesUtilLight {

    @NonNull
    public static final String GMS_ERROR_DIALOG = "GooglePlayServicesErrorDialog";

    @NonNull
    @Deprecated
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";

    @Deprecated
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;

    @NonNull
    public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";

    @Nullable
    @Deprecated
    public static Dialog getErrorDialog(int i, @NonNull Activity activity, int i2) {
        return getErrorDialog(i, activity, i2, null);
    }

    @NonNull
    @Deprecated
    public static PendingIntent getErrorPendingIntent(int i, @NonNull Context context, int i2) {
        return GoogleApiAvailabilityLight.getInstance().getErrorResolutionPendingIntent(context, i, i2);
    }

    @NonNull
    @Deprecated
    public static String getErrorString(int i) {
        return ConnectionResult.zza(i);
    }

    @NonNull
    public static Context getRemoteContext(@NonNull Context context) {
        return GooglePlayServicesUtilLight.getRemoteContext(context);
    }

    @NonNull
    public static Resources getRemoteResource(@NonNull Context context) {
        return GooglePlayServicesUtilLight.getRemoteResource(context);
    }

    @ResultIgnorabilityUnspecified
    @HideFirstParty
    @Deprecated
    public static int isGooglePlayServicesAvailable(@NonNull Context context) {
        return GooglePlayServicesUtilLight.isGooglePlayServicesAvailable(context, GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
    }

    @Deprecated
    public static boolean isUserRecoverableError(int i) {
        return GooglePlayServicesUtilLight.isUserRecoverableError(i);
    }

    @ResultIgnorabilityUnspecified
    @Deprecated
    public static boolean showErrorDialogFragment(int i, @NonNull Activity activity, int i2) {
        return showErrorDialogFragment(i, activity, null, i2, null);
    }

    @Deprecated
    public static void showErrorNotification(int i, @NonNull Context context) {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        if (GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(context, i) || GooglePlayServicesUtilLight.isPlayStorePossiblyUpdating(context, i)) {
            googleApiAvailability.zaf(context);
        } else {
            googleApiAvailability.showErrorNotification(context, i);
        }
    }

    @Nullable
    @Deprecated
    public static Dialog getErrorDialog(int i, @NonNull Activity activity, int i2, @Nullable DialogInterface.OnCancelListener onCancelListener) {
        if (true == GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(activity, i)) {
            i = 18;
        }
        return GoogleApiAvailability.getInstance().getErrorDialog(activity, i, i2, onCancelListener);
    }

    @KeepForSdk
    @Deprecated
    public static int isGooglePlayServicesAvailable(@NonNull Context context, int i) {
        return GooglePlayServicesUtilLight.isGooglePlayServicesAvailable(context, i);
    }

    @ResultIgnorabilityUnspecified
    @InlineMe(imports = {"androidx.fragment.app.Fragment", "com.google.android.gms.common.GooglePlayServicesUtil"}, replacement = "GooglePlayServicesUtil.showErrorDialogFragment(errorCode, activity, (Fragment) null, requestCode, cancelListener)")
    @Deprecated
    public static boolean showErrorDialogFragment(int i, @NonNull Activity activity, int i2, @Nullable DialogInterface.OnCancelListener onCancelListener) {
        return showErrorDialogFragment(i, activity, null, i2, onCancelListener);
    }

    @ResultIgnorabilityUnspecified
    public static boolean showErrorDialogFragment(int i, @NonNull Activity activity, @Nullable Fragment fragment, int i2, @Nullable DialogInterface.OnCancelListener onCancelListener) {
        int i3 = true == GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(activity, i) ? 18 : i;
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        if (fragment == null) {
            return googleApiAvailability.showErrorDialogFragment(activity, i3, i2, onCancelListener);
        }
        Dialog dialogZaa = googleApiAvailability.zaa(activity, i3, new zae(GoogleApiAvailability.zab.getErrorResolutionIntent(activity, i3, "d"), fragment, i2), onCancelListener, null);
        if (dialogZaa == null) {
            return false;
        }
        googleApiAvailability.zad(activity, dialogZaa, GMS_ERROR_DIALOG, onCancelListener);
        return true;
    }
}
