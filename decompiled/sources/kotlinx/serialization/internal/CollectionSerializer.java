package kotlinx.serialization.internal;

import java.util.Collection;
import java.util.Iterator;
import kotlin.PublishedApi;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@PublishedApi
public abstract class CollectionSerializer<E, C extends Collection<? extends E>, B> extends CollectionLikeSerializer<E, C, B> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CollectionSerializer(@NotNull KSerializer<E> element) {
        super(element);
        Intrinsics.checkNotNullParameter(element, "element");
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    @NotNull
    public Iterator<E> collectionIterator(@NotNull C c) {
        Intrinsics.checkNotNullParameter(c, "<this>");
        return c.iterator();
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public int collectionSize(@NotNull C c) {
        Intrinsics.checkNotNullParameter(c, "<this>");
        return c.size();
    }
}
