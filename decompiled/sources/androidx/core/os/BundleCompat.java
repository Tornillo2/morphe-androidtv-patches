package androidx.core.os;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.SparseArray;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.ReplaceWith;
import androidx.annotation.RequiresApi;
import java.io.Serializable;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class BundleCompat {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(33)
    public static class Api33Impl {
        public static <T> T getParcelable(@NonNull Bundle bundle, @Nullable String str, @NonNull Class<T> cls) {
            return (T) bundle.getParcelable(str, cls);
        }

        public static <T> T[] getParcelableArray(@NonNull Bundle bundle, @Nullable String str, @NonNull Class<T> cls) {
            return (T[]) bundle.getParcelableArray(str, cls);
        }

        public static <T> ArrayList<T> getParcelableArrayList(@NonNull Bundle bundle, @Nullable String str, @NonNull Class<? extends T> cls) {
            return bundle.getParcelableArrayList(str, cls);
        }

        public static <T extends Serializable> T getSerializable(@NonNull Bundle bundle, @Nullable String str, @NonNull Class<T> cls) {
            return (T) bundle.getSerializable(str, cls);
        }

        public static <T> SparseArray<T> getSparseParcelableArray(@NonNull Bundle bundle, @Nullable String str, @NonNull Class<? extends T> cls) {
            return bundle.getSparseParcelableArray(str, cls);
        }
    }

    @Nullable
    @ReplaceWith(expression = "bundle.getBinder(key)")
    @Deprecated
    public static IBinder getBinder(@NonNull Bundle bundle, @Nullable String str) {
        return bundle.getBinder(str);
    }

    @Nullable
    public static <T> T getParcelable(@NonNull Bundle bundle, @Nullable String str, @NonNull Class<T> cls) {
        if (Build.VERSION.SDK_INT >= 34) {
            return (T) Api33Impl.getParcelable(bundle, str, cls);
        }
        T t = (T) bundle.getParcelable(str);
        if (cls.isInstance(t)) {
            return t;
        }
        return null;
    }

    @Nullable
    @SuppressLint({"ArrayReturn", "NullableCollection"})
    public static Parcelable[] getParcelableArray(@NonNull Bundle bundle, @Nullable String str, @NonNull Class<? extends Parcelable> cls) {
        return Build.VERSION.SDK_INT >= 34 ? (Parcelable[]) Api33Impl.getParcelableArray(bundle, str, cls) : bundle.getParcelableArray(str);
    }

    @Nullable
    @SuppressLint({"ConcreteCollection", "NullableCollection"})
    public static <T> ArrayList<T> getParcelableArrayList(@NonNull Bundle bundle, @Nullable String str, @NonNull Class<? extends T> cls) {
        return Build.VERSION.SDK_INT >= 34 ? Api33Impl.getParcelableArrayList(bundle, str, cls) : bundle.getParcelableArrayList(str);
    }

    @Nullable
    public static <T extends Serializable> T getSerializable(@NonNull Bundle bundle, @Nullable String str, @NonNull Class<T> cls) {
        if (Build.VERSION.SDK_INT >= 34) {
            return (T) Api33Impl.getSerializable(bundle, str, cls);
        }
        T t = (T) bundle.getSerializable(str);
        if (cls.isInstance(t)) {
            return t;
        }
        return null;
    }

    @Nullable
    public static <T> SparseArray<T> getSparseParcelableArray(@NonNull Bundle bundle, @Nullable String str, @NonNull Class<? extends T> cls) {
        return Build.VERSION.SDK_INT >= 34 ? Api33Impl.getSparseParcelableArray(bundle, str, cls) : bundle.getSparseParcelableArray(str);
    }

    @ReplaceWith(expression = "bundle.putBinder(key, binder)")
    @Deprecated
    public static void putBinder(@NonNull Bundle bundle, @Nullable String str, @Nullable IBinder iBinder) {
        bundle.putBinder(str, iBinder);
    }
}
