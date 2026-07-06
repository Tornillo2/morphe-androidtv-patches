package kotlinx.serialization.json.internal;

import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SourceDebugExtension({"SMAP\nArrayPools.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ArrayPools.kt\nkotlinx/serialization/json/internal/CharArrayPoolBatchSize\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,90:1\n1#2:91\n*E\n"})
public final class CharArrayPoolBatchSize extends CharArrayPoolBase {

    @NotNull
    public static final CharArrayPoolBatchSize INSTANCE = new CharArrayPoolBatchSize();

    public final void release(@NotNull char[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        if (array.length == 16384) {
            releaseImpl(array);
        } else {
            throw new IllegalArgumentException(("Inconsistent internal invariant: unexpected array size " + array.length).toString());
        }
    }

    @NotNull
    public final char[] take() {
        return take(16384);
    }
}
