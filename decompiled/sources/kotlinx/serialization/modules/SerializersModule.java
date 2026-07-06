package kotlinx.serialization.modules;

import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.ExperimentalSerializationApi;
import kotlinx.serialization.InternalSerializationApi;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationStrategy;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public abstract class SerializersModule {
    public SerializersModule() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static KSerializer getContextual$default(SerializersModule serializersModule, KClass kClass, List list, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getContextual");
        }
        if ((i & 2) != 0) {
            list = EmptyList.INSTANCE;
        }
        return serializersModule.getContextual(kClass, list);
    }

    @ExperimentalSerializationApi
    public abstract void dumpTo(@NotNull SerializersModuleCollector serializersModuleCollector);

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Deprecated in favor of overload with default parameter", replaceWith = @ReplaceWith(expression = "getContextual(kclass)", imports = {}))
    @ExperimentalSerializationApi
    public final KSerializer getContextual(KClass kclass) {
        Intrinsics.checkNotNullParameter(kclass, "kclass");
        return getContextual(kclass, EmptyList.INSTANCE);
    }

    @ExperimentalSerializationApi
    @Nullable
    public abstract <T> KSerializer<T> getContextual(@NotNull KClass<T> kClass, @NotNull List<? extends KSerializer<?>> list);

    public abstract boolean getHasInterfaceContextualSerializers$kotlinx_serialization_core();

    @ExperimentalSerializationApi
    @Nullable
    public abstract <T> DeserializationStrategy<T> getPolymorphic(@NotNull KClass<? super T> kClass, @Nullable String str);

    @ExperimentalSerializationApi
    @Nullable
    public abstract <T> SerializationStrategy<T> getPolymorphic(@NotNull KClass<? super T> kClass, @NotNull T t);

    public SerializersModule(DefaultConstructorMarker defaultConstructorMarker) {
    }

    @InternalSerializationApi
    public static /* synthetic */ void getHasInterfaceContextualSerializers$kotlinx_serialization_core$annotations() {
    }
}
