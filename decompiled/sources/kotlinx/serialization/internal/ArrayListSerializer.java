package kotlinx.serialization.internal;

import java.util.ArrayList;
import java.util.List;
import kotlin.PublishedApi;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.InternalSerializationApi;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@PublishedApi
@InternalSerializationApi
public final class ArrayListSerializer<E> extends CollectionSerializer<E, List<? extends E>, ArrayList<E>> {

    @NotNull
    public final SerialDescriptor descriptor;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ArrayListSerializer(@NotNull KSerializer<E> element) {
        super(element);
        Intrinsics.checkNotNullParameter(element, "element");
        this.descriptor = new ArrayListClassDesc(element.getDescriptor());
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public Object builder() {
        return new ArrayList();
    }

    @Override // kotlinx.serialization.internal.CollectionLikeSerializer, kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    @NotNull
    public List<E> toResult(@NotNull ArrayList<E> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<this>");
        return arrayList;
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    @NotNull
    public ArrayList<E> builder() {
        return new ArrayList<>();
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public int builderSize(@NotNull ArrayList<E> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<this>");
        return arrayList.size();
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public void checkCapacity(@NotNull ArrayList<E> arrayList, int i) {
        Intrinsics.checkNotNullParameter(arrayList, "<this>");
        arrayList.ensureCapacity(i);
    }

    @Override // kotlinx.serialization.internal.CollectionLikeSerializer
    public void insert(@NotNull ArrayList<E> arrayList, int i, E e) {
        Intrinsics.checkNotNullParameter(arrayList, "<this>");
        arrayList.add(i, e);
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    @NotNull
    public ArrayList<E> toBuilder(@NotNull List<? extends E> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        ArrayList<E> arrayList = list instanceof ArrayList ? (ArrayList) list : null;
        return arrayList == null ? new ArrayList<>(list) : arrayList;
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public Object toResult(Object obj) {
        ArrayList arrayList = (ArrayList) obj;
        Intrinsics.checkNotNullParameter(arrayList, "<this>");
        return arrayList;
    }
}
