package androidx.core.util;

import android.annotation.SuppressLint;
import android.util.Half;
import androidx.annotation.RequiresApi;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"ClassVerificationFailure"})
@SourceDebugExtension({"SMAP\nHalf.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Half.kt\nandroidx/core/util/HalfKt\n*L\n1#1,57:1\n42#1:58\n*S KotlinDebug\n*F\n+ 1 Half.kt\nandroidx/core/util/HalfKt\n*L\n49#1:58\n*E\n"})
public final class HalfKt {
    @RequiresApi(26)
    @NotNull
    public static final Half toHalf(short s) {
        return Half.valueOf(s);
    }

    @RequiresApi(26)
    @NotNull
    public static final Half toHalf(float f) {
        return Half.valueOf(f);
    }

    @RequiresApi(26)
    @NotNull
    public static final Half toHalf(@NotNull String str) {
        return Half.valueOf(str);
    }

    @RequiresApi(26)
    @NotNull
    public static final Half toHalf(double d) {
        return Half.valueOf((float) d);
    }
}
