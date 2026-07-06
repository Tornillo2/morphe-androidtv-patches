package kotlinx.serialization.internal;

import kotlinx.serialization.InternalSerializationApi;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@InternalSerializationApi
public interface GeneratedSerializer<T> extends KSerializer<T> {

    /* JADX INFO: renamed from: kotlinx.serialization.internal.GeneratedSerializer$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DefaultImpls {
        @Deprecated
        @NotNull
        public static <T> KSerializer<?>[] typeParametersSerializers(@NotNull GeneratedSerializer<T> generatedSerializer) {
            return PluginHelperInterfacesKt.EMPTY_SERIALIZER_ARRAY;
        }
    }

    @NotNull
    KSerializer<?>[] childSerializers();

    @NotNull
    KSerializer<?>[] typeParametersSerializers();
}
