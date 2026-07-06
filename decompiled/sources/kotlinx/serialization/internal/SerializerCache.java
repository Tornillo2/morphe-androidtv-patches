package kotlinx.serialization.internal;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public interface SerializerCache<T> {

    /* JADX INFO: renamed from: kotlinx.serialization.internal.SerializerCache$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
        public static boolean $default$isStored(SerializerCache serializerCache, @NotNull KClass key) {
            Intrinsics.checkNotNullParameter(key, "key");
            return false;
        }

        public static /* synthetic */ boolean access$isStored$jd(SerializerCache serializerCache, KClass kClass) {
            $default$isStored(serializerCache, kClass);
            return false;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DefaultImpls {
        @Deprecated
        public static <T> boolean isStored(@NotNull SerializerCache<T> serializerCache, @NotNull KClass<?> key) {
            Intrinsics.checkNotNullParameter(key, "key");
            CC.$default$isStored(serializerCache, key);
            return false;
        }
    }

    @Nullable
    KSerializer<T> get(@NotNull KClass<Object> kClass);

    boolean isStored(@NotNull KClass<?> kClass);
}
