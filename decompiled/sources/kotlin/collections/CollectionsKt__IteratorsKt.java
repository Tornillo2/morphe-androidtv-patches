package kotlin.collections;

import java.util.Iterator;
import kotlin.Unit;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class CollectionsKt__IteratorsKt extends CollectionsKt__IteratorsJVMKt {
    public static final <T> void forEach(@NotNull Iterator<? extends T> it, @NotNull Function1<? super T, Unit> operation) {
        Intrinsics.checkNotNullParameter(it, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        while (it.hasNext()) {
            operation.invoke(it.next());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @InlineOnly
    public static final <T> Iterator<T> iterator(Iterator<? extends T> it) {
        Intrinsics.checkNotNullParameter(it, "<this>");
        return it;
    }

    @NotNull
    public static final <T> Iterator<IndexedValue<T>> withIndex(@NotNull Iterator<? extends T> it) {
        Intrinsics.checkNotNullParameter(it, "<this>");
        return new IndexingIterator(it);
    }
}
