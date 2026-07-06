package kotlinx.serialization.modules;

import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.ExperimentalSerializationApi;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationStrategy;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@ExperimentalSerializationApi
public interface SerializersModuleCollector {

    /* JADX INFO: renamed from: kotlinx.serialization.modules.SerializersModuleCollector$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
        public static void $default$contextual(SerializersModuleCollector serializersModuleCollector, @NotNull KClass kClass, @NotNull final KSerializer serializer) {
            Intrinsics.checkNotNullParameter(kClass, "kClass");
            Intrinsics.checkNotNullParameter(serializer, "serializer");
            serializersModuleCollector.contextual(kClass, new Function1() { // from class: kotlinx.serialization.modules.SerializersModuleCollector$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    KSerializer kSerializer = serializer;
                    List it = (List) obj;
                    Intrinsics.checkNotNullParameter(it, "it");
                    return kSerializer;
                }
            });
        }

        @Deprecated(level = DeprecationLevel.WARNING, message = "Deprecated in favor of function with more precise name: polymorphicDefaultDeserializer", replaceWith = @ReplaceWith(expression = "polymorphicDefaultDeserializer(baseClass, defaultDeserializerProvider)", imports = {}))
        public static void $default$polymorphicDefault(SerializersModuleCollector serializersModuleCollector, @NotNull KClass baseClass, @NotNull Function1 defaultDeserializerProvider) {
            Intrinsics.checkNotNullParameter(baseClass, "baseClass");
            Intrinsics.checkNotNullParameter(defaultDeserializerProvider, "defaultDeserializerProvider");
            serializersModuleCollector.polymorphicDefaultDeserializer(baseClass, defaultDeserializerProvider);
        }

        public static KSerializer contextual$lambda$0(KSerializer kSerializer, List it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return kSerializer;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DefaultImpls {
        @Deprecated
        public static <T> void contextual(@NotNull SerializersModuleCollector serializersModuleCollector, @NotNull KClass<T> kClass, @NotNull KSerializer<T> serializer) {
            Intrinsics.checkNotNullParameter(kClass, "kClass");
            Intrinsics.checkNotNullParameter(serializer, "serializer");
            CC.$default$contextual(serializersModuleCollector, kClass, serializer);
        }

        @Deprecated(level = DeprecationLevel.WARNING, message = "Deprecated in favor of function with more precise name: polymorphicDefaultDeserializer", replaceWith = @ReplaceWith(expression = "polymorphicDefaultDeserializer(baseClass, defaultDeserializerProvider)", imports = {}))
        @Deprecated
        public static <Base> void polymorphicDefault(@NotNull SerializersModuleCollector serializersModuleCollector, @NotNull KClass<Base> baseClass, @NotNull Function1<? super String, ? extends DeserializationStrategy<? extends Base>> defaultDeserializerProvider) {
            Intrinsics.checkNotNullParameter(baseClass, "baseClass");
            Intrinsics.checkNotNullParameter(defaultDeserializerProvider, "defaultDeserializerProvider");
            CC.$default$polymorphicDefault(serializersModuleCollector, baseClass, defaultDeserializerProvider);
        }
    }

    <T> void contextual(@NotNull KClass<T> kClass, @NotNull Function1<? super List<? extends KSerializer<?>>, ? extends KSerializer<?>> function1);

    <T> void contextual(@NotNull KClass<T> kClass, @NotNull KSerializer<T> kSerializer);

    <Base, Sub extends Base> void polymorphic(@NotNull KClass<Base> kClass, @NotNull KClass<Sub> kClass2, @NotNull KSerializer<Sub> kSerializer);

    @Deprecated(level = DeprecationLevel.WARNING, message = "Deprecated in favor of function with more precise name: polymorphicDefaultDeserializer", replaceWith = @ReplaceWith(expression = "polymorphicDefaultDeserializer(baseClass, defaultDeserializerProvider)", imports = {}))
    <Base> void polymorphicDefault(@NotNull KClass<Base> kClass, @NotNull Function1<? super String, ? extends DeserializationStrategy<? extends Base>> function1);

    <Base> void polymorphicDefaultDeserializer(@NotNull KClass<Base> kClass, @NotNull Function1<? super String, ? extends DeserializationStrategy<? extends Base>> function1);

    <Base> void polymorphicDefaultSerializer(@NotNull KClass<Base> kClass, @NotNull Function1<? super Base, ? extends SerializationStrategy<? super Base>> function1);
}
