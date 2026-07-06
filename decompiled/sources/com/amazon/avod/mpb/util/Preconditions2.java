package com.amazon.avod.mpb.util;

import android.os.Build;
import androidx.annotation.ChecksSdkIntAtLeast;
import androidx.collection.MutableFloatList$$ExternalSyntheticOutline0;
import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline2;
import com.amazon.avod.mpb.annotate.Positive;
import javax.annotation.Nonnull;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nPreconditions2.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Preconditions2.kt\ncom/amazon/avod/mpb/util/Preconditions2\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,86:1\n1#2:87\n*E\n"})
public final class Preconditions2 {

    @NotNull
    public static final Preconditions2 INSTANCE = new Preconditions2();

    @JvmStatic
    public static final long checkNonNegative(long j, @Nonnull @NotNull String errorMessage) {
        Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
        INSTANCE.checkAtLeast(j, 0L, errorMessage);
        return j;
    }

    @JvmStatic
    public static final int checkPositive(@Positive int i, @Nonnull @NotNull String errorMessage) {
        Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
        INSTANCE.checkAtLeast(i, 1, errorMessage);
        return i;
    }

    @JvmStatic
    @ChecksSdkIntAtLeast(parameter = 0)
    public static final boolean isSdkIntAtLeast(int i) {
        return Build.VERSION.SDK_INT >= i;
    }

    public final int checkAtLeast(int i, int i2, String str) {
        if (i >= i2) {
            return i;
        }
        StringBuilder sbM = MutableFloatList$$ExternalSyntheticOutline0.m("Number is invalid. Minimum value: ", i2, ". Actual: ", i, ". ");
        sbM.append(str);
        throw new IllegalArgumentException(sbM.toString().toString());
    }

    public final long checkAtLeast(long j, long j2, String str) {
        if (j >= j2) {
            return j;
        }
        StringBuilder sbM = AbstractResolvableFuture$$ExternalSyntheticOutline2.m("Number is invalid. Minimum value: ", j2, ". Actual: ");
        sbM.append(j);
        sbM.append(". ");
        sbM.append(str);
        throw new IllegalArgumentException(sbM.toString().toString());
    }
}
