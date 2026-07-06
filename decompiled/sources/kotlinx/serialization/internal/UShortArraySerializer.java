package kotlinx.serialization.internal;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.PublishedApi;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.ExperimentalSerializationApi;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@PublishedApi
@ExperimentalSerializationApi
@ExperimentalUnsignedTypes
public final class UShortArraySerializer extends PrimitiveArraySerializer<UShort, UShortArray, UShortArrayBuilder> implements KSerializer<UShortArray> {

    @NotNull
    public static final UShortArraySerializer INSTANCE = new UShortArraySerializer();

    public UShortArraySerializer() {
        super(BuiltinSerializersKt.serializer(UShort.Companion));
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public /* synthetic */ int collectionSize(Object obj) {
        return m2183collectionSizerL5Bavg(((UShortArray) obj).storage);
    }

    /* JADX INFO: renamed from: collectionSize-rL5Bavg, reason: not valid java name */
    public int m2183collectionSizerL5Bavg(@NotNull short[] collectionSize) {
        Intrinsics.checkNotNullParameter(collectionSize, "$this$collectionSize");
        return collectionSize.length;
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    public UShortArray empty() {
        return new UShortArray(new short[0]);
    }

    @NotNull
    /* JADX INFO: renamed from: empty-amswpOA, reason: not valid java name */
    public short[] m2184emptyamswpOA() {
        return new short[0];
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    public void readElement(@NotNull CompositeDecoder decoder, int i, @NotNull UShortArrayBuilder builder, boolean z) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        Intrinsics.checkNotNullParameter(builder, "builder");
        builder.m2181appendxj2QHRw$kotlinx_serialization_core(decoder.decodeInlineElement(this.descriptor, i).decodeShort());
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public /* synthetic */ Object toBuilder(Object obj) {
        return m2185toBuilderrL5Bavg(((UShortArray) obj).storage);
    }

    @NotNull
    /* JADX INFO: renamed from: toBuilder-rL5Bavg, reason: not valid java name */
    public UShortArrayBuilder m2185toBuilderrL5Bavg(@NotNull short[] toBuilder) {
        Intrinsics.checkNotNullParameter(toBuilder, "$this$toBuilder");
        return new UShortArrayBuilder(toBuilder);
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    public /* synthetic */ void writeContent(CompositeEncoder compositeEncoder, UShortArray uShortArray, int i) {
        m2186writeContenteny0XGE(compositeEncoder, uShortArray.storage, i);
    }

    /* JADX INFO: renamed from: writeContent-eny0XGE, reason: not valid java name */
    public void m2186writeContenteny0XGE(@NotNull CompositeEncoder encoder, @NotNull short[] content, int i) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(content, "content");
        for (int i2 = 0; i2 < i; i2++) {
            encoder.encodeInlineElement(this.descriptor, i2).encodeShort(content[i2]);
        }
    }
}
