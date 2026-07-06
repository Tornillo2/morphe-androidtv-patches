package kotlinx.serialization.modules;

import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KClass;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.modules.ContextualProvider;
import kotlinx.serialization.modules.SerializersModuleCollector;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SourceDebugExtension({"SMAP\nSerializersModule.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SerializersModule.kt\nkotlinx/serialization/modules/SerializersModuleKt\n+ 2 SerializersModuleBuilders.kt\nkotlinx/serialization/modules/SerializersModuleBuildersKt\n*L\n1#1,245:1\n31#2,3:246\n31#2,3:249\n*S KotlinDebug\n*F\n+ 1 SerializersModule.kt\nkotlinx/serialization/modules/SerializersModuleKt\n*L\n97#1:246,3\n109#1:249,3\n*E\n"})
public final class SerializersModuleKt {

    @NotNull
    public static final SerializersModule EmptySerializersModule = new SerialModuleImpl(MapsKt__MapsKt.emptyMap(), MapsKt__MapsKt.emptyMap(), MapsKt__MapsKt.emptyMap(), MapsKt__MapsKt.emptyMap(), MapsKt__MapsKt.emptyMap(), false);

    @NotNull
    public static final SerializersModule getEmptySerializersModule() {
        return EmptySerializersModule;
    }

    @NotNull
    public static final SerializersModule overwriteWith(@NotNull SerializersModule serializersModule, @NotNull SerializersModule other) {
        Intrinsics.checkNotNullParameter(serializersModule, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        final SerializersModuleBuilder serializersModuleBuilder = new SerializersModuleBuilder();
        serializersModule.dumpTo(serializersModuleBuilder);
        other.dumpTo(new SerializersModuleCollector() { // from class: kotlinx.serialization.modules.SerializersModuleKt$overwriteWith$1$1
            @Override // kotlinx.serialization.modules.SerializersModuleCollector
            public <T> void contextual(KClass<T> kClass, KSerializer<T> serializer) {
                Intrinsics.checkNotNullParameter(kClass, "kClass");
                Intrinsics.checkNotNullParameter(serializer, "serializer");
                serializersModuleBuilder.registerSerializer(kClass, new ContextualProvider.Argless(serializer), true);
            }

            @Override // kotlinx.serialization.modules.SerializersModuleCollector
            public <Base, Sub extends Base> void polymorphic(KClass<Base> baseClass, KClass<Sub> actualClass, KSerializer<Sub> actualSerializer) {
                Intrinsics.checkNotNullParameter(baseClass, "baseClass");
                Intrinsics.checkNotNullParameter(actualClass, "actualClass");
                Intrinsics.checkNotNullParameter(actualSerializer, "actualSerializer");
                serializersModuleBuilder.registerPolymorphicSerializer(baseClass, actualClass, actualSerializer, true);
            }

            @Override // kotlinx.serialization.modules.SerializersModuleCollector
            public /* synthetic */ void polymorphicDefault(KClass kClass, Function1 function1) {
                SerializersModuleCollector.CC.$default$polymorphicDefault(this, kClass, function1);
            }

            @Override // kotlinx.serialization.modules.SerializersModuleCollector
            public <Base> void polymorphicDefaultDeserializer(KClass<Base> baseClass, Function1<? super String, ? extends DeserializationStrategy<? extends Base>> defaultDeserializerProvider) {
                Intrinsics.checkNotNullParameter(baseClass, "baseClass");
                Intrinsics.checkNotNullParameter(defaultDeserializerProvider, "defaultDeserializerProvider");
                serializersModuleBuilder.registerDefaultPolymorphicDeserializer(baseClass, defaultDeserializerProvider, true);
            }

            @Override // kotlinx.serialization.modules.SerializersModuleCollector
            public <Base> void polymorphicDefaultSerializer(KClass<Base> baseClass, Function1<? super Base, ? extends SerializationStrategy<? super Base>> defaultSerializerProvider) {
                Intrinsics.checkNotNullParameter(baseClass, "baseClass");
                Intrinsics.checkNotNullParameter(defaultSerializerProvider, "defaultSerializerProvider");
                serializersModuleBuilder.registerDefaultPolymorphicSerializer(baseClass, defaultSerializerProvider, true);
            }

            @Override // kotlinx.serialization.modules.SerializersModuleCollector
            public <T> void contextual(KClass<T> kClass, Function1<? super List<? extends KSerializer<?>>, ? extends KSerializer<?>> provider) {
                Intrinsics.checkNotNullParameter(kClass, "kClass");
                Intrinsics.checkNotNullParameter(provider, "provider");
                serializersModuleBuilder.registerSerializer(kClass, new ContextualProvider.WithTypeArguments(provider), true);
            }
        });
        return serializersModuleBuilder.build();
    }

    @NotNull
    public static final SerializersModule plus(@NotNull SerializersModule serializersModule, @NotNull SerializersModule other) {
        Intrinsics.checkNotNullParameter(serializersModule, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        SerializersModuleBuilder serializersModuleBuilder = new SerializersModuleBuilder();
        serializersModule.dumpTo(serializersModuleBuilder);
        other.dumpTo(serializersModuleBuilder);
        return serializersModuleBuilder.build();
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Deprecated in the favour of 'EmptySerializersModule()'", replaceWith = @ReplaceWith(expression = "EmptySerializersModule()", imports = {}))
    public static /* synthetic */ void getEmptySerializersModule$annotations() {
    }
}
