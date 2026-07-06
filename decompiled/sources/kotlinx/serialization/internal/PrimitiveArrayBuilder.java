package kotlinx.serialization.internal;

import kotlin.PublishedApi;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@PublishedApi
public abstract class PrimitiveArrayBuilder<Array> {
    public static /* synthetic */ void ensureCapacity$kotlinx_serialization_core$default(PrimitiveArrayBuilder primitiveArrayBuilder, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: ensureCapacity");
        }
        if ((i2 & 1) != 0) {
            i = primitiveArrayBuilder.getPosition$kotlinx_serialization_core() + 1;
        }
        primitiveArrayBuilder.ensureCapacity$kotlinx_serialization_core(i);
    }

    public abstract Array build$kotlinx_serialization_core();

    public abstract void ensureCapacity$kotlinx_serialization_core(int i);

    public abstract int getPosition$kotlinx_serialization_core();
}
