package kotlinx.serialization.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KClass;
import kotlinx.serialization.ExperimentalSerializationApi;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.internal.SerialDescriptorForNullable;
import kotlinx.serialization.modules.SerialModuleImpl;
import kotlinx.serialization.modules.SerializersModule;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SourceDebugExtension({"SMAP\nContextAware.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ContextAware.kt\nkotlinx/serialization/descriptors/ContextAwareKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,111:1\n1#2:112\n1557#3:113\n1628#3,3:114\n*S KotlinDebug\n*F\n+ 1 ContextAware.kt\nkotlinx/serialization/descriptors/ContextAwareKt\n*L\n76#1:113\n76#1:114,3\n*E\n"})
public final class ContextAwareKt {
    @Nullable
    public static final KClass<?> getCapturedKClass(@NotNull SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        if (serialDescriptor instanceof ContextDescriptor) {
            return ((ContextDescriptor) serialDescriptor).kClass;
        }
        if (serialDescriptor instanceof SerialDescriptorForNullable) {
            return getCapturedKClass(((SerialDescriptorForNullable) serialDescriptor).original);
        }
        return null;
    }

    @ExperimentalSerializationApi
    @Nullable
    public static final SerialDescriptor getContextualDescriptor(@NotNull SerializersModule serializersModule, @NotNull SerialDescriptor descriptor) {
        KSerializer contextual$default;
        Intrinsics.checkNotNullParameter(serializersModule, "<this>");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        KClass<?> capturedKClass = getCapturedKClass(descriptor);
        if (capturedKClass == null || (contextual$default = SerializersModule.getContextual$default(serializersModule, capturedKClass, null, 2, null)) == null) {
            return null;
        }
        return contextual$default.getDescriptor();
    }

    @ExperimentalSerializationApi
    @NotNull
    public static final List<SerialDescriptor> getPolymorphicDescriptors(@NotNull SerializersModule serializersModule, @NotNull SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(serializersModule, "<this>");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        KClass<?> capturedKClass = getCapturedKClass(descriptor);
        if (capturedKClass == null) {
            return EmptyList.INSTANCE;
        }
        Map<KClass<?>, KSerializer<?>> map = ((SerialModuleImpl) serializersModule).polyBase2Serializers.get(capturedKClass);
        Collection<KSerializer<?>> collectionValues = map != null ? map.values() : null;
        if (collectionValues == null) {
            collectionValues = EmptyList.INSTANCE;
        }
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(collectionValues, 10));
        Iterator<T> it = collectionValues.iterator();
        while (it.hasNext()) {
            arrayList.add(((KSerializer) it.next()).getDescriptor());
        }
        return arrayList;
    }

    @NotNull
    public static final SerialDescriptor withContext(@NotNull SerialDescriptor serialDescriptor, @NotNull KClass<?> context) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        return new ContextDescriptor(serialDescriptor, context);
    }

    @ExperimentalSerializationApi
    public static /* synthetic */ void getCapturedKClass$annotations(SerialDescriptor serialDescriptor) {
    }
}
