package kotlinx.serialization.internal;

import java.util.HashSet;
import java.util.Set;
import kotlin.PublishedApi;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@PublishedApi
public final class HashSetSerializer<E> extends CollectionSerializer<E, Set<? extends E>, HashSet<E>> {

    @NotNull
    public final SerialDescriptor descriptor;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HashSetSerializer(@NotNull KSerializer<E> eSerializer) {
        super(eSerializer);
        Intrinsics.checkNotNullParameter(eSerializer, "eSerializer");
        this.descriptor = new HashSetClassDesc(eSerializer.getDescriptor());
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public Object builder() {
        return new HashSet();
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public void checkCapacity(@NotNull HashSet<E> hashSet, int i) {
        Intrinsics.checkNotNullParameter(hashSet, "<this>");
    }

    @Override // kotlinx.serialization.internal.CollectionLikeSerializer, kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    @NotNull
    public Set<E> toResult(@NotNull HashSet<E> hashSet) {
        Intrinsics.checkNotNullParameter(hashSet, "<this>");
        return hashSet;
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    @NotNull
    public HashSet<E> builder() {
        return new HashSet<>();
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public int builderSize(@NotNull HashSet<E> hashSet) {
        Intrinsics.checkNotNullParameter(hashSet, "<this>");
        return hashSet.size();
    }

    @Override // kotlinx.serialization.internal.CollectionLikeSerializer
    public void insert(@NotNull HashSet<E> hashSet, int i, E e) {
        Intrinsics.checkNotNullParameter(hashSet, "<this>");
        hashSet.add(e);
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    @NotNull
    public HashSet<E> toBuilder(@NotNull Set<? extends E> set) {
        Intrinsics.checkNotNullParameter(set, "<this>");
        HashSet<E> hashSet = set instanceof HashSet ? (HashSet) set : null;
        return hashSet == null ? new HashSet<>(set) : hashSet;
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public Object toResult(Object obj) {
        HashSet hashSet = (HashSet) obj;
        Intrinsics.checkNotNullParameter(hashSet, "<this>");
        return hashSet;
    }
}
