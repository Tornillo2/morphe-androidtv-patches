package kotlinx.serialization.internal;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.PublishedApi;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.descriptors.StructureKind;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@PublishedApi
@SourceDebugExtension({"SMAP\nPluginGeneratedSerialDescriptor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PluginGeneratedSerialDescriptor.kt\nkotlinx/serialization/internal/PluginGeneratedSerialDescriptor\n+ 2 Platform.kt\nkotlinx/serialization/internal/PlatformKt\n+ 3 PluginGeneratedSerialDescriptor.kt\nkotlinx/serialization/internal/PluginGeneratedSerialDescriptorKt\n+ 4 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,134:1\n16#2:135\n21#2:136\n16#2:137\n16#2:138\n111#3,10:139\n11158#4:149\n11493#4,3:150\n*S KotlinDebug\n*F\n+ 1 PluginGeneratedSerialDescriptor.kt\nkotlinx/serialization/internal/PluginGeneratedSerialDescriptor\n*L\n76#1:135\n79#1:136\n81#1:137\n82#1:138\n93#1:139,10\n40#1:149\n40#1:150,3\n*E\n"})
public class PluginGeneratedSerialDescriptor implements SerialDescriptor, CachedNames {

    @NotNull
    public final Lazy _hashCode$delegate;
    public int added;

    @NotNull
    public final Lazy childSerializers$delegate;

    @Nullable
    public List<Annotation> classAnnotations;
    public final int elementsCount;

    @NotNull
    public final boolean[] elementsOptionality;

    @Nullable
    public final GeneratedSerializer<?> generatedSerializer;

    @NotNull
    public Map<String, Integer> indices;

    @NotNull
    public final String[] names;

    @NotNull
    public final List<Annotation>[] propertiesAnnotations;

    @NotNull
    public final String serialName;

    @NotNull
    public final Lazy typeParameterDescriptors$delegate;

    public PluginGeneratedSerialDescriptor(@NotNull String serialName, @Nullable GeneratedSerializer<?> generatedSerializer, int i) {
        Intrinsics.checkNotNullParameter(serialName, "serialName");
        this.serialName = serialName;
        this.generatedSerializer = generatedSerializer;
        this.elementsCount = i;
        this.added = -1;
        String[] strArr = new String[i];
        for (int i2 = 0; i2 < i; i2++) {
            strArr[i2] = "[UNINITIALIZED]";
        }
        this.names = strArr;
        int i3 = this.elementsCount;
        this.propertiesAnnotations = new List[i3];
        this.elementsOptionality = new boolean[i3];
        this.indices = MapsKt__MapsKt.emptyMap();
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.PUBLICATION;
        this.childSerializers$delegate = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: kotlinx.serialization.internal.PluginGeneratedSerialDescriptor$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PluginGeneratedSerialDescriptor.childSerializers_delegate$lambda$0(this.f$0);
            }
        });
        this.typeParameterDescriptors$delegate = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: kotlinx.serialization.internal.PluginGeneratedSerialDescriptor$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PluginGeneratedSerialDescriptor.typeParameterDescriptors_delegate$lambda$2(this.f$0);
            }
        });
        this._hashCode$delegate = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: kotlinx.serialization.internal.PluginGeneratedSerialDescriptor$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Integer.valueOf(PluginGeneratedSerialDescriptor._hashCode_delegate$lambda$3(this.f$0));
            }
        });
    }

    public static final int _hashCode_delegate$lambda$3(PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor) {
        return PluginGeneratedSerialDescriptorKt.hashCodeImpl(pluginGeneratedSerialDescriptor, pluginGeneratedSerialDescriptor.getTypeParameterDescriptors$kotlinx_serialization_core());
    }

    public static /* synthetic */ void addElement$default(PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor, String str, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: addElement");
        }
        if ((i & 2) != 0) {
            z = false;
        }
        pluginGeneratedSerialDescriptor.addElement(str, z);
    }

    public static final KSerializer[] childSerializers_delegate$lambda$0(PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor) {
        GeneratedSerializer<?> generatedSerializer = pluginGeneratedSerialDescriptor.generatedSerializer;
        return generatedSerializer != null ? generatedSerializer.childSerializers() : PluginHelperInterfacesKt.EMPTY_SERIALIZER_ARRAY;
    }

    private final int get_hashCode() {
        return ((Number) this._hashCode$delegate.getValue()).intValue();
    }

    public static final CharSequence toString$lambda$6(PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor, int i) {
        return pluginGeneratedSerialDescriptor.getElementName(i) + ": " + pluginGeneratedSerialDescriptor.getElementDescriptor(i).getSerialName();
    }

    public static final SerialDescriptor[] typeParameterDescriptors_delegate$lambda$2(PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor) {
        ArrayList arrayList;
        KSerializer<?>[] kSerializerArrTypeParametersSerializers;
        GeneratedSerializer<?> generatedSerializer = pluginGeneratedSerialDescriptor.generatedSerializer;
        if (generatedSerializer == null || (kSerializerArrTypeParametersSerializers = generatedSerializer.typeParametersSerializers()) == null) {
            arrayList = null;
        } else {
            arrayList = new ArrayList(kSerializerArrTypeParametersSerializers.length);
            for (KSerializer<?> kSerializer : kSerializerArrTypeParametersSerializers) {
                arrayList.add(kSerializer.getDescriptor());
            }
        }
        return Platform_commonKt.compactArray(arrayList);
    }

    public final void addElement(@NotNull String name, boolean z) {
        Intrinsics.checkNotNullParameter(name, "name");
        String[] strArr = this.names;
        int i = this.added + 1;
        this.added = i;
        strArr[i] = name;
        this.elementsOptionality[i] = z;
        this.propertiesAnnotations[i] = null;
        if (i == this.elementsCount - 1) {
            this.indices = buildIndices();
        }
    }

    public final Map<String, Integer> buildIndices() {
        HashMap map = new HashMap();
        int length = this.names.length;
        for (int i = 0; i < length; i++) {
            map.put(this.names[i], Integer.valueOf(i));
        }
        return map;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PluginGeneratedSerialDescriptor)) {
            return false;
        }
        SerialDescriptor serialDescriptor = (SerialDescriptor) obj;
        if (!Intrinsics.areEqual(getSerialName(), serialDescriptor.getSerialName()) || !Arrays.equals(getTypeParameterDescriptors$kotlinx_serialization_core(), ((PluginGeneratedSerialDescriptor) obj).getTypeParameterDescriptors$kotlinx_serialization_core()) || getElementsCount() != serialDescriptor.getElementsCount()) {
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
        List<Annotation> list = this.classAnnotations;
        return list == null ? EmptyList.INSTANCE : list;
    }

    public final KSerializer<?>[] getChildSerializers() {
        return (KSerializer[]) this.childSerializers$delegate.getValue();
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    @NotNull
    public List<Annotation> getElementAnnotations(int i) {
        List<Annotation> list = this.propertiesAnnotations[i];
        return list == null ? EmptyList.INSTANCE : list;
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    @NotNull
    public SerialDescriptor getElementDescriptor(int i) {
        return getChildSerializers()[i].getDescriptor();
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public int getElementIndex(@NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        Integer num = this.indices.get(name);
        if (num != null) {
            return num.intValue();
        }
        return -3;
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    @NotNull
    public String getElementName(int i) {
        return this.names[i];
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public final int getElementsCount() {
        return this.elementsCount;
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    @NotNull
    public SerialKind getKind() {
        return StructureKind.CLASS.INSTANCE;
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    @NotNull
    public String getSerialName() {
        return this.serialName;
    }

    @Override // kotlinx.serialization.internal.CachedNames
    @NotNull
    public Set<String> getSerialNames() {
        return this.indices.keySet();
    }

    @NotNull
    public final SerialDescriptor[] getTypeParameterDescriptors$kotlinx_serialization_core() {
        return (SerialDescriptor[]) this.typeParameterDescriptors$delegate.getValue();
    }

    public int hashCode() {
        return get_hashCode();
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public boolean isElementOptional(int i) {
        return this.elementsOptionality[i];
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public /* synthetic */ boolean isInline() {
        return false;
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public /* synthetic */ boolean isNullable() {
        return false;
    }

    public final void pushAnnotation(@NotNull Annotation annotation) {
        Intrinsics.checkNotNullParameter(annotation, "annotation");
        List<Annotation> arrayList = this.propertiesAnnotations[this.added];
        if (arrayList == null) {
            arrayList = new ArrayList<>(1);
            this.propertiesAnnotations[this.added] = arrayList;
        }
        arrayList.add(annotation);
    }

    public final void pushClassAnnotation(@NotNull Annotation a) {
        Intrinsics.checkNotNullParameter(a, "a");
        if (this.classAnnotations == null) {
            this.classAnnotations = new ArrayList(1);
        }
        List<Annotation> list = this.classAnnotations;
        Intrinsics.checkNotNull(list);
        list.add(a);
    }

    @NotNull
    public String toString() {
        return CollectionsKt___CollectionsKt.joinToString$default(RangesKt___RangesKt.until(0, this.elementsCount), ", ", getSerialName() + '(', ")", 0, null, new Function1() { // from class: kotlinx.serialization.internal.PluginGeneratedSerialDescriptor$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PluginGeneratedSerialDescriptor.toString$lambda$6(this.f$0, ((Integer) obj).intValue());
            }
        }, 24, null);
    }

    public /* synthetic */ PluginGeneratedSerialDescriptor(String str, GeneratedSerializer generatedSerializer, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? null : generatedSerializer, i);
    }
}
