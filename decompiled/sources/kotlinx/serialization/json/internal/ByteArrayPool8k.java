package kotlinx.serialization.json.internal;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class ByteArrayPool8k extends ByteArrayPoolBase {

    @NotNull
    public static final ByteArrayPool8k INSTANCE = new ByteArrayPool8k();

    public final void release(@NotNull byte[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        releaseImpl(array);
    }

    @NotNull
    public final byte[] take() {
        return take(8196);
    }
}
