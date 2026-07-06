package kotlinx.serialization.modules;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.PublishedApi;
import kotlin.collections.CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1;
import kotlin.collections.MapsKt___MapsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KClass;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.internal.PlatformKt;
import kotlinx.serialization.modules.ContextualProvider;
import kotlinx.serialization.modules.SerializersModuleCollector;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SourceDebugExtension({"SMAP\nSerializersModuleBuilders.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SerializersModuleBuilders.kt\nkotlinx/serialization/modules/SerializersModuleBuilder\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,263:1\n381#2,7:264\n381#2,7:271\n1#3:278\n*S KotlinDebug\n*F\n+ 1 SerializersModuleBuilders.kt\nkotlinx/serialization/modules/SerializersModuleBuilder\n*L\n196#1:264,7\n197#1:271,7\n*E\n"})
public final class SerializersModuleBuilder implements SerializersModuleCollector {
    public boolean hasInterfaceContextualSerializers;

    @NotNull
    public final Map<KClass<?>, ContextualProvider> class2ContextualProvider = new HashMap();

    @NotNull
    public final Map<KClass<?>, Map<KClass<?>, KSerializer<?>>> polyBase2Serializers = new HashMap();

    @NotNull
    public final Map<KClass<?>, Function1<?, SerializationStrategy<?>>> polyBase2DefaultSerializerProvider = new HashMap();

    @NotNull
    public final Map<KClass<?>, Map<String, KSerializer<?>>> polyBase2NamedSerializers = new HashMap();

    @NotNull
    public final Map<KClass<?>, Function1<String, DeserializationStrategy<?>>> polyBase2DefaultDeserializerProvider = new HashMap();

    @PublishedApi
    public SerializersModuleBuilder() {
    }

    public static /* synthetic */ void registerPolymorphicSerializer$default(SerializersModuleBuilder serializersModuleBuilder, KClass kClass, KClass kClass2, KSerializer kSerializer, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            z = false;
        }
        serializersModuleBuilder.registerPolymorphicSerializer(kClass, kClass2, kSerializer, z);
    }

    public static /* synthetic */ void registerSerializer$default(SerializersModuleBuilder serializersModuleBuilder, KClass kClass, ContextualProvider contextualProvider, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        serializersModuleBuilder.registerSerializer(kClass, contextualProvider, z);
    }

    @PublishedApi
    @NotNull
    public final SerializersModule build() {
        return new SerialModuleImpl(this.class2ContextualProvider, this.polyBase2Serializers, this.polyBase2DefaultSerializerProvider, this.polyBase2NamedSerializers, this.polyBase2DefaultDeserializerProvider, this.hasInterfaceContextualSerializers);
    }

    @Override // kotlinx.serialization.modules.SerializersModuleCollector
    public <T> void contextual(@NotNull KClass<T> kClass, @NotNull KSerializer<T> serializer) {
        Intrinsics.checkNotNullParameter(kClass, "kClass");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        registerSerializer(kClass, new ContextualProvider.Argless(serializer), false);
    }

    public final void include(@NotNull SerializersModule module) {
        Intrinsics.checkNotNullParameter(module, "module");
        module.dumpTo(this);
    }

    @Override // kotlinx.serialization.modules.SerializersModuleCollector
    public <Base, Sub extends Base> void polymorphic(@NotNull KClass<Base> baseClass, @NotNull KClass<Sub> actualClass, @NotNull KSerializer<Sub> actualSerializer) {
        Intrinsics.checkNotNullParameter(baseClass, "baseClass");
        Intrinsics.checkNotNullParameter(actualClass, "actualClass");
        Intrinsics.checkNotNullParameter(actualSerializer, "actualSerializer");
        registerPolymorphicSerializer(baseClass, actualClass, actualSerializer, false);
    }

    @Override // kotlinx.serialization.modules.SerializersModuleCollector
    public /* synthetic */ void polymorphicDefault(KClass kClass, Function1 function1) {
        SerializersModuleCollector.CC.$default$polymorphicDefault(this, kClass, function1);
    }

    @Override // kotlinx.serialization.modules.SerializersModuleCollector
    public <Base> void polymorphicDefaultDeserializer(@NotNull KClass<Base> baseClass, @NotNull Function1<? super String, ? extends DeserializationStrategy<? extends Base>> defaultDeserializerProvider) {
        Intrinsics.checkNotNullParameter(baseClass, "baseClass");
        Intrinsics.checkNotNullParameter(defaultDeserializerProvider, "defaultDeserializerProvider");
        registerDefaultPolymorphicDeserializer(baseClass, defaultDeserializerProvider, false);
    }

    @Override // kotlinx.serialization.modules.SerializersModuleCollector
    public <Base> void polymorphicDefaultSerializer(@NotNull KClass<Base> baseClass, @NotNull Function1<? super Base, ? extends SerializationStrategy<? super Base>> defaultSerializerProvider) {
        Intrinsics.checkNotNullParameter(baseClass, "baseClass");
        Intrinsics.checkNotNullParameter(defaultSerializerProvider, "defaultSerializerProvider");
        registerDefaultPolymorphicSerializer(baseClass, defaultSerializerProvider, false);
    }

    @JvmName(name = "registerDefaultPolymorphicDeserializer")
    public final <Base> void registerDefaultPolymorphicDeserializer(@NotNull KClass<Base> baseClass, @NotNull Function1<? super String, ? extends DeserializationStrategy<? extends Base>> defaultDeserializerProvider, boolean z) {
        Intrinsics.checkNotNullParameter(baseClass, "baseClass");
        Intrinsics.checkNotNullParameter(defaultDeserializerProvider, "defaultDeserializerProvider");
        Function1<String, DeserializationStrategy<?>> function1 = this.polyBase2DefaultDeserializerProvider.get(baseClass);
        if (function1 == null || function1.equals(defaultDeserializerProvider) || z) {
            this.polyBase2DefaultDeserializerProvider.put(baseClass, defaultDeserializerProvider);
            return;
        }
        throw new IllegalArgumentException("Default deserializers provider for " + baseClass + " is already registered: " + function1);
    }

    @JvmName(name = "registerDefaultPolymorphicSerializer")
    public final <Base> void registerDefaultPolymorphicSerializer(@NotNull KClass<Base> baseClass, @NotNull Function1<? super Base, ? extends SerializationStrategy<? super Base>> defaultSerializerProvider, boolean z) {
        Intrinsics.checkNotNullParameter(baseClass, "baseClass");
        Intrinsics.checkNotNullParameter(defaultSerializerProvider, "defaultSerializerProvider");
        Function1<?, SerializationStrategy<?>> function1 = this.polyBase2DefaultSerializerProvider.get(baseClass);
        if (function1 == null || function1.equals(defaultSerializerProvider) || z) {
            this.polyBase2DefaultSerializerProvider.put(baseClass, defaultSerializerProvider);
            return;
        }
        throw new IllegalArgumentException("Default serializers provider for " + baseClass + " is already registered: " + function1);
    }

    @JvmName(name = "registerPolymorphicSerializer")
    public final <Base, Sub extends Base> void registerPolymorphicSerializer(@NotNull KClass<Base> baseClass, @NotNull KClass<Sub> concreteClass, @NotNull KSerializer<Sub> concreteSerializer, boolean z) {
        Object next;
        KClass kClass;
        Intrinsics.checkNotNullParameter(baseClass, "baseClass");
        Intrinsics.checkNotNullParameter(concreteClass, "concreteClass");
        Intrinsics.checkNotNullParameter(concreteSerializer, "concreteSerializer");
        String serialName = concreteSerializer.getDescriptor().getSerialName();
        Map<KClass<?>, Map<KClass<?>, KSerializer<?>>> map = this.polyBase2Serializers;
        Map<KClass<?>, KSerializer<?>> map2 = map.get(baseClass);
        if (map2 == null) {
            map2 = new HashMap<>();
            map.put(baseClass, map2);
        }
        Map<KClass<?>, KSerializer<?>> map3 = map2;
        Map<KClass<?>, Map<String, KSerializer<?>>> map4 = this.polyBase2NamedSerializers;
        Map<String, KSerializer<?>> map5 = map4.get(baseClass);
        if (map5 == null) {
            map5 = new HashMap<>();
            map4.put(baseClass, map5);
        }
        Map<String, KSerializer<?>> map6 = map5;
        KSerializer<?> kSerializer = map3.get(concreteClass);
        if (kSerializer != null && !kSerializer.equals(concreteSerializer)) {
            if (!z) {
                throw new SerializerAlreadyRegisteredException(baseClass, concreteClass);
            }
            map6.remove(kSerializer.getDescriptor().getSerialName());
        }
        KSerializer<?> kSerializer2 = map6.get(serialName);
        if (kSerializer2 != null && !kSerializer2.equals(concreteSerializer)) {
            Iterator it = ((CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1) MapsKt___MapsKt.asSequence(map3)).$this_asSequence$inlined.iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                } else {
                    next = it.next();
                    if (((Map.Entry) next).getValue() == kSerializer2) {
                        break;
                    }
                }
            }
            Map.Entry entry = (Map.Entry) next;
            if (entry == null || (kClass = (KClass) entry.getKey()) == null) {
                throw new IllegalStateException(("Name " + serialName + " is registered in the module but no Kotlin class is associated with it.").toString());
            }
            if (!z) {
                throw new IllegalArgumentException("Multiple polymorphic serializers in a scope of '" + baseClass + "' have the same serial name '" + serialName + "': " + concreteSerializer + " for '" + concreteClass + "' and " + kSerializer2 + " for '" + kClass + '\'');
            }
            map3.remove(kClass);
        }
        map3.put(concreteClass, concreteSerializer);
        map6.put(serialName, concreteSerializer);
    }

    @JvmName(name = "registerSerializer")
    public final <T> void registerSerializer(@NotNull KClass<T> forClass, @NotNull ContextualProvider provider, boolean z) {
        ContextualProvider contextualProvider;
        Intrinsics.checkNotNullParameter(forClass, "forClass");
        Intrinsics.checkNotNullParameter(provider, "provider");
        if (!z && (contextualProvider = this.class2ContextualProvider.get(forClass)) != null && !contextualProvider.equals(provider)) {
            throw new SerializerAlreadyRegisteredException("Contextual serializer or serializer provider for " + forClass + " already registered in this module");
        }
        this.class2ContextualProvider.put(forClass, provider);
        if (PlatformKt.isInterface(forClass)) {
            this.hasInterfaceContextualSerializers = true;
        }
    }

    @Override // kotlinx.serialization.modules.SerializersModuleCollector
    public <T> void contextual(@NotNull KClass<T> kClass, @NotNull Function1<? super List<? extends KSerializer<?>>, ? extends KSerializer<?>> provider) {
        Intrinsics.checkNotNullParameter(kClass, "kClass");
        Intrinsics.checkNotNullParameter(provider, "provider");
        registerSerializer(kClass, new ContextualProvider.WithTypeArguments(provider), false);
    }
}
