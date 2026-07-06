package com.bumptech.glide;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import androidx.annotation.CheckResult;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;
import java.io.File;
import java.net.URL;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface ModelTypes<T> {
    @NonNull
    @CheckResult
    T load(@Nullable Bitmap bitmap);

    @NonNull
    @CheckResult
    T load(@Nullable Drawable drawable);

    @NonNull
    @CheckResult
    T load(@Nullable Uri uri);

    @NonNull
    @CheckResult
    T load(@Nullable File file);

    @NonNull
    @CheckResult
    T load(@Nullable @DrawableRes @RawRes Integer num);

    @NonNull
    @CheckResult
    T load(@Nullable Object obj);

    @NonNull
    @CheckResult
    T load(@Nullable String str);

    @CheckResult
    @Deprecated
    T load(@Nullable URL url);

    @NonNull
    @CheckResult
    T load(@Nullable byte[] bArr);
}
