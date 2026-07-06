package kotlinx.serialization.internal;

import java.util.List;
import kotlin.collections.EmptyList;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public interface ParametrizedSerializerCache<T> {

    /* JADX INFO: renamed from: kotlinx.serialization.internal.ParametrizedSerializerCache$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX INFO: renamed from: get-gIAlu-s$default, reason: not valid java name */
        public static Object m2154getgIAlus$default(ParametrizedSerializerCache parametrizedSerializerCache, KClass kClass, List list, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: get-gIAlu-s");
            }
            if ((i & 2) != 0) {
                list = EmptyList.INSTANCE;
            }
            return parametrizedSerializerCache.mo2148getgIAlus(kClass, list);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DefaultImpls {
    }

    @NotNull
    /* JADX INFO: renamed from: get-gIAlu-s */
    Object mo2148getgIAlus(@NotNull KClass<Object> kClass, @NotNull List<? extends KType> list);
}
