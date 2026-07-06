package androidx.core.content;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.io.Serializable;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class IntentCompat {

    @SuppressLint({"ActionValue"})
    public static final String ACTION_CREATE_REMINDER = "android.intent.action.CREATE_REMINDER";
    public static final String CATEGORY_LEANBACK_LAUNCHER = "android.intent.category.LEANBACK_LAUNCHER";
    public static final String EXTRA_HTML_TEXT = "android.intent.extra.HTML_TEXT";
    public static final String EXTRA_START_PLAYBACK = "android.intent.extra.START_PLAYBACK";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_TIME = "android.intent.extra.TIME";

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(33)
    public static class Api33Impl {
        public static <T> T[] getParcelableArrayExtra(@NonNull Intent intent, @Nullable String str, @NonNull Class<T> cls) {
            return (T[]) intent.getParcelableArrayExtra(str, cls);
        }

        public static <T> ArrayList<T> getParcelableArrayListExtra(@NonNull Intent intent, @Nullable String str, @NonNull Class<? extends T> cls) {
            return intent.getParcelableArrayListExtra(str, cls);
        }

        public static <T> T getParcelableExtra(@NonNull Intent intent, @Nullable String str, @NonNull Class<T> cls) {
            return (T) intent.getParcelableExtra(str, cls);
        }

        public static <T extends Serializable> T getSerializableExtra(@NonNull Intent intent, @Nullable String str, @NonNull Class<T> cls) {
            return (T) intent.getSerializableExtra(str, cls);
        }
    }

    @NonNull
    public static Intent createManageUnusedAppRestrictionsIntent(@NonNull Context context, @NonNull String str) {
        if (!PackageManagerCompat.areUnusedAppRestrictionsAvailable(context.getPackageManager())) {
            throw new UnsupportedOperationException("Unused App Restriction features are not available on this device");
        }
        int i = Build.VERSION.SDK_INT;
        if (i >= 31) {
            return new Intent("android.settings.APPLICATION_DETAILS_SETTINGS").setData(Uri.fromParts("package", str, null));
        }
        Intent data = new Intent(PackageManagerCompat.ACTION_PERMISSION_REVOCATION_SETTINGS).setData(Uri.fromParts("package", str, null));
        if (i >= 30) {
            return data;
        }
        String permissionRevocationVerifierApp = PackageManagerCompat.getPermissionRevocationVerifierApp(context.getPackageManager());
        permissionRevocationVerifierApp.getClass();
        return data.setPackage(permissionRevocationVerifierApp);
    }

    @Nullable
    @SuppressLint({"ArrayReturn", "NullableCollection"})
    public static Parcelable[] getParcelableArrayExtra(@NonNull Intent intent, @Nullable String str, @NonNull Class<? extends Parcelable> cls) {
        return Build.VERSION.SDK_INT >= 34 ? (Parcelable[]) Api33Impl.getParcelableArrayExtra(intent, str, cls) : intent.getParcelableArrayExtra(str);
    }

    @Nullable
    @SuppressLint({"ConcreteCollection", "NullableCollection"})
    public static <T> ArrayList<T> getParcelableArrayListExtra(@NonNull Intent intent, @Nullable String str, @NonNull Class<? extends T> cls) {
        return Build.VERSION.SDK_INT >= 34 ? Api33Impl.getParcelableArrayListExtra(intent, str, cls) : intent.getParcelableArrayListExtra(str);
    }

    @Nullable
    public static <T> T getParcelableExtra(@NonNull Intent intent, @Nullable String str, @NonNull Class<T> cls) {
        if (Build.VERSION.SDK_INT >= 34) {
            return (T) Api33Impl.getParcelableExtra(intent, str, cls);
        }
        T t = (T) intent.getParcelableExtra(str);
        if (cls.isInstance(t)) {
            return t;
        }
        return null;
    }

    @Nullable
    public static <T extends Serializable> T getSerializableExtra(@NonNull Intent intent, @Nullable String str, @NonNull Class<T> cls) {
        if (Build.VERSION.SDK_INT >= 34) {
            return (T) Api33Impl.getSerializableExtra(intent, str, cls);
        }
        T t = (T) intent.getSerializableExtra(str);
        if (cls.isInstance(t)) {
            return t;
        }
        return null;
    }

    @NonNull
    public static Intent makeMainSelectorActivity(@NonNull String str, @NonNull String str2) {
        return Intent.makeMainSelectorActivity(str, str2);
    }
}
