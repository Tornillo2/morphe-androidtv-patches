package kotlinx.serialization.descriptors;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.collections.IndexingIterable;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.serialization.internal.CachedNames;
import kotlinx.serialization.internal.Platform_commonKt;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptorKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SourceDebugExtension({"SMAP\nSerialDescriptors.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SerialDescriptors.kt\nkotlinx/serialization/descriptors/SerialDescriptorImpl\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 Platform.kt\nkotlinx/serialization/internal/PlatformKt\n+ 5 PluginGeneratedSerialDescriptor.kt\nkotlinx/serialization/internal/PluginGeneratedSerialDescriptorKt\n*L\n1#1,380:1\n37#2:381\n36#2,3:382\n37#2:385\n36#2,3:386\n1557#3:389\n1628#3,3:390\n16#4:393\n16#4:394\n16#4:395\n21#4:396\n111#5,10:397\n*S KotlinDebug\n*F\n+ 1 SerialDescriptors.kt\nkotlinx/serialization/descriptors/SerialDescriptorImpl\n*L\n350#1:381\n350#1:382,3\n352#1:385\n352#1:386,3\n354#1:389\n354#1:390,3\n358#1:393\n360#1:394\n361#1:395\n362#1:396\n365#1:397,10\n*E\n"})
public final class SerialDescriptorImpl implements SerialDescriptor, CachedNames {

    @NotNull
    public final Lazy _hashCode$delegate;

    @NotNull
    public final List<Annotation> annotations;

    @NotNull
    public final List<Annotation>[] elementAnnotations;

    @NotNull
    public final SerialDescriptor[] elementDescriptors;

    @NotNull
    public final String[] elementNames;

    @NotNull
    public final boolean[] elementOptionality;
    public final int elementsCount;

    @NotNull
    public final SerialKind kind;

    @NotNull
    public final Map<String, Integer> name2Index;

    @NotNull
    public final String serialName;

    @NotNull
    public final Set<String> serialNames;

    @NotNull
    public final SerialDescriptor[] typeParametersDescriptors;

    public SerialDescriptorImpl(@NotNull String serialName, @NotNull SerialKind kind, int i, @NotNull List<? extends SerialDescriptor> typeParameters, @NotNull ClassSerialDescriptorBuilder builder) {
        Intrinsics.checkNotNullParameter(serialName, "serialName");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(typeParameters, "typeParameters");
        Intrinsics.checkNotNullParameter(builder, "builder");
        this.serialName = serialName;
        this.kind = kind;
        this.elementsCount = i;
        this.annotations = builder.annotations;
        this.serialNames = CollectionsKt___CollectionsKt.toHashSet(builder.elementNames);
        String[] strArr = (String[]) builder.elementNames.toArray(new String[0]);
        this.elementNames = strArr;
        this.elementDescriptors = Platform_commonKt.compactArray(builder.elementDescriptors);
        this.elementAnnotations = (List[]) builder.elementAnnotations.toArray(new List[0]);
        this.elementOptionality = CollectionsKt___CollectionsKt.toBooleanArray(builder.elementOptionality);
        Iterable iterableWithIndex = ArraysKt___ArraysKt.withIndex(strArr);
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterableWithIndex, 10));
        for (IndexedValue indexedValue : (IndexingIterable) iterableWithIndex) {
            arrayList.add(new Pair(indexedValue.value, Integer.valueOf(indexedValue.index)));
        }
        this.name2Index = MapsKt__MapsKt.toMap(arrayList);
        this.typeParametersDescriptors = Platform_commonKt.compactArray(typeParameters);
        this._hashCode$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: kotlinx.serialization.descriptors.SerialDescriptorImpl$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                SerialDescriptorImpl serialDescriptorImpl = this.f$0;
                return Integer.valueOf(PluginGeneratedSerialDescriptorKt.hashCodeImpl(serialDescriptorImpl, serialDescriptorImpl.typeParametersDescriptors));
            }
        });
    }

    public static final CharSequence toString$lambda$3(SerialDescriptorImpl serialDescriptorImpl, int i) {
        return serialDescriptorImpl.elementNames[i] + ": " + serialDescriptorImpl.elementDescriptors[i].getSerialName();
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SerialDescriptorImpl)) {
            return false;
        }
        SerialDescriptor serialDescriptor = (SerialDescriptor) obj;
        if (!Intrinsics.areEqual(getSerialName(), serialDescriptor.getSerialName()) || !Arrays.equals(this.typeParametersDescriptors, ((SerialDescriptorImpl) obj).typeParametersDescriptors) || getElementsCount() != serialDescriptor.getElementsCount()) {
            return false;
        }
        int elementsCount = getElementsCount();
        for (int i = 0; i < elementsCount; i++) {
            if (!Intrinsics.areEqual(getElementDescriptor(i).getSerialName(), serialDescriptor.getElementDescriptor(i).getSerialName()) || !Intrinsics.areEqual(getElementDescriptor(i).getKind(), serialDescriptor.getElementDescriptor(i).getKind())) {
                return false;
            }
        }
        return true;
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    @NotNull
    public List<Annotation> getAnnotations() {
        return this.annotations;
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    @NotNull
    public List<Annotation> getElementAnnotations(int i) {
        return this.elementAnnotations[i];
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    @NotNull
    public SerialDescriptor getElementDescriptor(int i) {
        return this.elementDescriptors[i];
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public int getElementIndex(@NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        Integer num = this.name2Index.get(name);
        if (num != null) {
            return num.intValue();
        }
        return -3;
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    @NotNull
    public String getElementName(int i) {
        return this.elementNames[i];
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public int getElementsCount() {
        return this.elementsCount;
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    @NotNull
    public SerialKind getKind() {
        return this.kind;
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    @NotNull
    public String getSerialName() {
        return this.serialName;
    }

    @Override // kotlinx.serialization.internal.CachedNames
    @NotNull
    public Set<String> getSerialNames() {
        return this.serialNames;
    }

    public final int get_hashCode() {
        return ((Number) this._hashCode$delegate.getValue()).intValue();
    }

    public int hashCode() {
        return get_hashCode();
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public boolean isElementOptional(int i) {
        return this.elementOptionality[i];
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public /* synthetic */ boolean isInline() {
        return false;
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public /* synthetic */ boolean isNullable() {
        return false;
    }

    @NotNull
    public String toString() {
        return CollectionsKt___CollectionsKt.joinToString$default(RangesKt___RangesKt.until(0, this.elementsCount), ", ", this.serialName + '(', ")", 0, null, new Function1() { // from class: kotlinx.serialization.descriptors.SerialDescriptorImpl$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SerialDescriptorImpl.toString$lambda$3(this.f$0, ((Integer) obj).intValue());
            }
        }, 24, null);
    }
}
