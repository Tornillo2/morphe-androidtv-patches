package kotlinx.serialization.internal;

import kotlin.PublishedApi;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@PublishedApi
public final class FloatArraySerializer extends PrimitiveArraySerializer<Float, float[], FloatArrayBuilder> implements KSerializer<float[]> {

    @NotNull
    public static final FloatArraySerializer INSTANCE = new FloatArraySerializer();

    public FloatArraySerializer() {
        super(BuiltinSerializersKt.serializer(FloatCompanionObject.INSTANCE));
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    public float[] empty() {
        return new float[0];
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    public void readElement(@NotNull CompositeDecoder decoder, int i, @NotNull FloatArrayBuilder builder, boolean z) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        Intrinsics.checkNotNullParameter(builder, "builder");
        builder.append$kotlinx_serialization_core(decoder.decodeFloatElement(this.descriptor, i));
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    public void writeContent(@NotNull CompositeEncoder encoder, @NotNull float[] content, int i) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(content, "content");
        for (int i2 = 0; i2 < i; i2++) {
            encoder.encodeFloatElement(this.descriptor, i2, content[i2]);
        }
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public int collectionSize(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return fArr.length;
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    @NotNull
    /* JADX INFO: renamed from: empty, reason: avoid collision after fix types in other method */
    public float[] empty2() {
        return new float[0];
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    @NotNull
    public FloatArrayBuilder toBuilder(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return new FloatArrayBuilder(fArr);
    }
}
