package kotlinx.serialization.descriptors;

import java.util.Iterator;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SourceDebugExtension({"SMAP\nIterables.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Iterables.kt\nkotlin/collections/CollectionsKt__IterablesKt$Iterable$1\n+ 2 SerialDescriptor.kt\nkotlinx/serialization/descriptors/SerialDescriptorKt\n*L\n1#1,17:1\n439#2,8:18\n*E\n"})
public final class SerialDescriptorKt$special$$inlined$Iterable$2 implements Iterable<String>, KMappedMarker {
    public final /* synthetic */ SerialDescriptor $this_elementNames$inlined;

    public SerialDescriptorKt$special$$inlined$Iterable$2(SerialDescriptor serialDescriptor) {
        this.$this_elementNames$inlined = serialDescriptor;
    }

    @Override // java.lang.Iterable
    public Iterator<String> iterator() {
        return new SerialDescriptorKt$elementNames$1$1(this.$this_elementNames$inlined);
    }
}
