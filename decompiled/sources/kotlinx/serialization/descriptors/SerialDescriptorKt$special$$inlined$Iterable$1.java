package kotlinx.serialization.descriptors;

import java.util.Iterator;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SourceDebugExtension({"SMAP\nIterables.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Iterables.kt\nkotlin/collections/CollectionsKt__IterablesKt$Iterable$1\n+ 2 SerialDescriptor.kt\nkotlinx/serialization/descriptors/SerialDescriptorKt\n*L\n1#1,17:1\n415#2,8:18\n*E\n"})
public final class SerialDescriptorKt$special$$inlined$Iterable$1 implements Iterable<SerialDescriptor>, KMappedMarker {
    public final /* synthetic */ SerialDescriptor $this_elementDescriptors$inlined;

    public SerialDescriptorKt$special$$inlined$Iterable$1(SerialDescriptor serialDescriptor) {
        this.$this_elementDescriptors$inlined = serialDescriptor;
    }

    @Override // java.lang.Iterable
    public Iterator<SerialDescriptor> iterator() {
        return new SerialDescriptorKt$elementDescriptors$1$1(this.$this_elementDescriptors$inlined);
    }
}
