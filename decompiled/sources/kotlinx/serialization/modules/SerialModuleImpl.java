package kotlinx.serialization.modules;

import java.util.List;
import java.util.Map;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.reflect.KClass;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.modules.ContextualProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SourceDebugExtension({"SMAP\nSerializersModule.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SerializersModule.kt\nkotlinx/serialization/modules/SerialModuleImpl\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 3 Platform.common.kt\nkotlinx/serialization/internal/Platform_commonKt\n*L\n1#1,245:1\n216#2,2:246\n216#2:248\n216#2:249\n217#2:251\n217#2:252\n216#2,2:253\n216#2,2:255\n78#3:250\n*S KotlinDebug\n*F\n+ 1 SerializersModule.kt\nkotlinx/serialization/modules/SerialModuleImpl\n*L\n186#1:246,2\n196#1:248\n197#1:249\n197#1:251\n196#1:252\n206#1:253,2\n210#1:255,2\n201#1:250\n*E\n"})
public final class SerialModuleImpl extends SerializersModule {

    @NotNull
    public final Map<KClass<?>, ContextualProvider> class2ContextualFactory;
    public final boolean hasInterfaceContextualSerializers;

    @NotNull
    public final Map<KClass<?>, Function1<String, DeserializationStrategy<?>>> polyBase2DefaultDeserializerProvider;

    @NotNull
    public final Map<KClass<?>, Function1<?, SerializationStrategy<?>>> polyBase2DefaultSerializerProvider;

    @NotNull
    public final Map<KClass<?>, Map<String, KSerializer<?>>> polyBase2NamedSerializers;

    @JvmField
    @NotNull
    public final Map<KClass<?>, Map<KClass<?>, KSerializer<?>>> polyBase2Serializers;

    /* JADX WARN: Multi-variable type inference failed */
    public SerialModuleImpl(@NotNull Map<KClass<?>, ? extends ContextualProvider> class2ContextualFactory, @NotNull Map<KClass<?>, ? extends Map<KClass<?>, ? extends KSerializer<?>>> polyBase2Serializers, @NotNull Map<KClass<?>, ? extends Function1<?, ? extends SerializationStrategy<?>>> polyBase2DefaultSerializerProvider, @NotNull Map<KClass<?>, ? extends Map<String, ? extends KSerializer<?>>> polyBase2NamedSerializers, @NotNull Map<KClass<?>, ? extends Function1<? super String, ? extends DeserializationStrategy<?>>> polyBase2DefaultDeserializerProvider, boolean z) {
        Intrinsics.checkNotNullParameter(class2ContextualFactory, "class2ContextualFactory");
        Intrinsics.checkNotNullParameter(polyBase2Serializers, "polyBase2Serializers");
        Intrinsics.checkNotNullParameter(polyBase2DefaultSerializerProvider, "polyBase2DefaultSerializerProvider");
        Intrinsics.checkNotNullParameter(polyBase2NamedSerializers, "polyBase2NamedSerializers");
        Intrinsics.checkNotNullParameter(polyBase2DefaultDeserializerProvider, "polyBase2DefaultDeserializerProvider");
        this.class2ContextualFactory = class2ContextualFactory;
        this.polyBase2Serializers = polyBase2Serializers;
        this.polyBase2DefaultSerializerProvider = polyBase2DefaultSerializerProvider;
        this.polyBase2NamedSerializers = polyBase2NamedSerializers;
        this.polyBase2DefaultDeserializerProvider = polyBase2DefaultDeserializerProvider;
        this.hasInterfaceContextualSerializers = z;
    }

    @Override // kotlinx.serialization.modules.SerializersModule
    public void dumpTo(@NotNull SerializersModuleCollector collector) {
        Intrinsics.checkNotNullParameter(collector, "collector");
        for (Map.Entry<KClass<?>, ContextualProvider> entry : this.class2ContextualFactory.entrySet()) {
            KClass<?> key = entry.getKey();
            ContextualProvider value = entry.getValue();
            if (value instanceof ContextualProvider.Argless) {
                Intrinsics.checkNotNull(key, "null cannot be cast to non-null type kotlin.reflect.KClass<kotlin.Any>");
                KSerializer<?> kSerializer = ((ContextualProvider.Argless) value).serializer;
                Intrinsics.checkNotNull(kSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<kotlin.Any>");
                collector.contextual(key, kSerializer);
            } else {
                if (!(value instanceof ContextualProvider.WithTypeArguments)) {
                    throw new NoWhenBranchMatchedException();
                }
                collector.contextual(key, ((ContextualProvider.WithTypeArguments) value).provider);
            }
        }
        for (Map.Entry<KClass<?>, Map<KClass<?>, KSerializer<?>>> entry2 : this.polyBase2Serializers.entrySet()) {
            KClass<?> key2 = entry2.getKey();
            for (Map.Entry<KClass<?>, KSerializer<?>> entry3 : entry2.getValue().entrySet()) {
                KClass<?> key3 = entry3.getKey();
                KSerializer<?> value2 = entry3.getValue();
                Intrinsics.checkNotNull(key2, "null cannot be cast to non-null type kotlin.reflect.KClass<kotlin.Any>");
                Intrinsics.checkNotNull(key3, "null cannot be cast to non-null type kotlin.reflect.KClass<kotlin.Any>");
                Intrinsics.checkNotNull(value2, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
                collector.polymorphic(key2, key3, value2);
            }
        }
        for (Map.Entry<KClass<?>, Function1<?, SerializationStrategy<?>>> entry4 : this.polyBase2DefaultSerializerProvider.entrySet()) {
            KClass<?> key4 = entry4.getKey();
            Function1<?, SerializationStrategy<?>> value3 = entry4.getValue();
            Intrinsics.checkNotNull(key4, "null cannot be cast to non-null type kotlin.reflect.KClass<kotlin.Any>");
            Intrinsics.checkNotNull(value3, "null cannot be cast to non-null type kotlin.Function1<@[ParameterName(name = \"value\")] kotlin.Any, kotlinx.serialization.SerializationStrategy<kotlin.Any>?>");
            TypeIntrinsics.beforeCheckcastToFunctionOfArity(value3, 1);
            collector.polymorphicDefaultSerializer(key4, value3);
        }
        for (Map.Entry<KClass<?>, Function1<String, DeserializationStrategy<?>>> entry5 : this.polyBase2DefaultDeserializerProvider.entrySet()) {
            KClass<?> key5 = entry5.getKey();
            Function1<String, DeserializationStrategy<?>> value4 = entry5.getValue();
            Intrinsics.checkNotNull(key5, "null cannot be cast to non-null type kotlin.reflect.KClass<kotlin.Any>");
            Intrinsics.checkNotNull(value4, "null cannot be cast to non-null type kotlin.Function1<@[ParameterName(name = \"className\")] kotlin.String?, kotlinx.serialization.DeserializationStrategy<kotlin.Any>?>");
            TypeIntrinsics.beforeCheckcastToFunctionOfArity(value4, 1);
            collector.polymorphicDefaultDeserializer(key5, value4);
        }
    }

    @Override // kotlinx.serialization.modules.SerializersModule
    @Nullable
    public <T> KSerializer<T> getContextual(@NotNull KClass<T> kClass, @NotNull List<? extends KSerializer<?>> typeArgumentsSerializers) {
        Intrinsics.checkNotNullParameter(kClass, "kClass");
        Intrinsics.checkNotNullParameter(typeArgumentsSerializers, "typeArgumentsSerializers");
        ContextualProvider contextualProvider = this.class2ContextualFactory.get(kClass);
        KSerializer<T> kSerializer = contextualProvider != null ? (KSerializer<T>) contextualProvider.invoke(typeArgumentsSerializers) : null;
        if (kSerializer instanceof KSerializer) {
            return kSerializer;
        }
        return null;
    }

    @Override // kotlinx.serialization.modules.SerializersModule
    public boolean getHasInterfaceContextualSerializers$kotlinx_serialization_core() {
        return this.hasInterfaceContextualSerializers;
    }

    @Override // kotlinx.serialization.modules.SerializersModule
    @Nullable
    public <T> SerializationStrategy<T> getPolymorphic(@NotNull KClass<? super T> baseClass, @NotNull T value) {
        Intrinsics.checkNotNullParameter(baseClass, "baseClass");
        Intrinsics.checkNotNullParameter(value, "value");
        if (baseClass.isInstance(value)) {
            Map<KClass<?>, KSerializer<?>> map = this.polyBase2Serializers.get(baseClass);
            KSerializer<?> kSerializer = map != null ? map.get(Reflection.getOrCreateKotlinClass(value.getClass())) : null;
            KSerializer<?> kSerializer2 = kSerializer instanceof SerializationStrategy ? kSerializer : null;
            if (kSerializer2 != null) {
                return kSerializer2;
            }
            Function1<?, SerializationStrategy<?>> function1 = this.polyBase2DefaultSerializerProvider.get(baseClass);
            Function1<?, SerializationStrategy<?>> function12 = TypeIntrinsics.isFunctionOfArity(function1, 1) ? function1 : null;
            if (function12 != null) {
                return (SerializationStrategy) function12.invoke(value);
            }
        }
        return null;
    }

    @Override // kotlinx.serialization.modules.SerializersModule
    @Nullable
    public <T> DeserializationStrategy<T> getPolymorphic(@NotNull KClass<? super T> baseClass, @Nullable String str) {
        Intrinsics.checkNotNullParameter(baseClass, "baseClass");
        Map<String, KSerializer<?>> map = this.polyBase2NamedSerializers.get(baseClass);
        KSerializer<?> kSerializer = map != null ? map.get(str) : null;
        if (!(kSerializer instanceof KSerializer)) {
            kSerializer = null;
        }
        if (kSerializer != null) {
            return kSerializer;
        }
        Function1<String, DeserializationStrategy<?>> function1 = this.polyBase2DefaultDeserializerProvider.get(baseClass);
        Function1<String, DeserializationStrategy<?>> function12 = TypeIntrinsics.isFunctionOfArity(function1, 1) ? function1 : null;
        if (function12 != null) {
            return (DeserializationStrategy) function12.invoke(str);
        }
        return null;
    }
}
