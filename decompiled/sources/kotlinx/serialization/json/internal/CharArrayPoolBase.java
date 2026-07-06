package kotlinx.serialization.json.internal;

import kotlin.collections.ArrayDeque;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SourceDebugExtension({"SMAP\nArrayPools.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ArrayPools.kt\nkotlinx/serialization/json/internal/CharArrayPoolBase\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,90:1\n1#2:91\n*E\n"})
public class CharArrayPoolBase {

    @NotNull
    public final ArrayDeque<char[]> arrays = new ArrayDeque<>();
    public int charsTotal;

    public final void releaseImpl(@NotNull char[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        synchronized (this) {
            if (this.charsTotal + array.length < ArrayPoolsKt.MAX_CHARS_IN_POOL) {
                this.charsTotal += array.length;
                this.arrays.addLast(array);
            }
        }
    }

    @NotNull
    public final char[] take(int i) {
        char[] cArrRemoveLastOrNull;
        synchronized (this) {
            cArrRemoveLastOrNull = this.arrays.removeLastOrNull();
            if (cArrRemoveLastOrNull != null) {
                this.charsTotal -= cArrRemoveLastOrNull.length;
            } else {
                cArrRemoveLastOrNull = null;
            }
        }
        return cArrRemoveLastOrNull == null ? new char[i] : cArrRemoveLastOrNull;
    }
}
