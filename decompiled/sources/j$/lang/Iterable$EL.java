package j$.lang;

import j$.util.Collection;
import j$.util.List;
import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.Spliterators;
import j$.util.n0;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.function.Consumer;

/* JADX INFO: renamed from: j$.lang.Iterable$-EL, reason: invalid class name */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class Iterable$EL {
    public static Spliterator spliterator(Iterable iterable) {
        if (iterable instanceof a) {
            return ((a) iterable).spliterator();
        }
        if (iterable instanceof LinkedHashSet) {
            return Spliterators.spliterator((LinkedHashSet) iterable, 17);
        }
        if (!(iterable instanceof SortedSet)) {
            return iterable instanceof Set ? Spliterators.spliterator((Set) iterable, 1) : iterable instanceof List ? List.CC.$default$spliterator((java.util.List) iterable) : iterable instanceof Collection ? Collection.CC.$default$spliterator((java.util.Collection) iterable) : Spliterators.spliteratorUnknownSize(iterable.iterator(), 0);
        }
        SortedSet sortedSet = (SortedSet) iterable;
        return new n0(sortedSet, sortedSet);
    }

    public static void forEach(Iterable iterable, Consumer consumer) {
        if (iterable instanceof a) {
            ((a) iterable).forEach(consumer);
            return;
        }
        if (!(iterable instanceof java.util.Collection)) {
            Iterable$CC.$default$forEach(iterable, consumer);
            return;
        }
        Objects.requireNonNull(consumer);
        Iterator it = ((java.util.Collection) iterable).iterator();
        while (it.hasNext()) {
            consumer.n(it.next());
        }
    }
}
