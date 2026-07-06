package kotlin;

import kotlin.internal.AccessibleLateinitPropertyLiteral;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty0;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@JvmName(name = "LateinitKt")
public final class LateinitKt {
    public static final boolean isInitialized(@AccessibleLateinitPropertyLiteral KProperty0<?> kProperty0) {
        Intrinsics.checkNotNullParameter(kProperty0, "<this>");
        throw new NotImplementedError("Implementation is intrinsic");
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    public static /* synthetic */ void isInitialized$annotations(KProperty0 kProperty0) {
    }
}
