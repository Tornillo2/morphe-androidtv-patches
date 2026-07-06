package androidx.core.util;

import android.annotation.SuppressLint;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class PairKt {
    @SuppressLint({"UnknownNullness"})
    public static final <F, S> F component1(@NotNull Pair<F, S> pair) {
        return pair.first;
    }

    @SuppressLint({"UnknownNullness"})
    public static final <F, S> S component2(@NotNull Pair<F, S> pair) {
        return pair.second;
    }

    @NotNull
    public static final <F, S> android.util.Pair<F, S> toAndroidPair(@NotNull kotlin.Pair<? extends F, ? extends S> pair) {
        return new android.util.Pair<>(pair.first, pair.second);
    }

    @NotNull
    public static final <F, S> Pair<F, S> toAndroidXPair(@NotNull kotlin.Pair<? extends F, ? extends S> pair) {
        return new Pair<>(pair.first, pair.second);
    }

    @NotNull
    public static final <F, S> kotlin.Pair<F, S> toKotlinPair(@NotNull Pair<F, S> pair) {
        return new kotlin.Pair<>(pair.first, pair.second);
    }

    @SuppressLint({"UnknownNullness"})
    public static final <F, S> F component1(@NotNull android.util.Pair<F, S> pair) {
        return (F) pair.first;
    }

    @SuppressLint({"UnknownNullness"})
    public static final <F, S> S component2(@NotNull android.util.Pair<F, S> pair) {
        return (S) pair.second;
    }

    @NotNull
    public static final <F, S> kotlin.Pair<F, S> toKotlinPair(@NotNull android.util.Pair<F, S> pair) {
        return new kotlin.Pair<>(pair.first, pair.second);
    }
}
