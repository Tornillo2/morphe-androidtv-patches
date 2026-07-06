package kotlinx.serialization.internal;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.PublishedApi;
import kotlin.UByte;
import kotlin.UByteArray;
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
public final class UByteArraySerializer extends PrimitiveArraySerializer<UByte, UByteArray, UByteArrayBuilder> implements KSerializer<UByteArray> {

    @NotNull
    public static final UByteArraySerializer INSTANCE = new UByteArraySerializer();

    public UByteArraySerializer() {
        super(BuiltinSerializersKt.serializer(UByte.Companion));
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public /* synthetic */ int collectionSize(Object obj) {
        return m2159collectionSizeGBYM_sE(((UByteArray) obj).storage);
    }

    /* JADX INFO: renamed from: collectionSize-GBYM_sE, reason: not valid java name */
    public int m2159collectionSizeGBYM_sE(@NotNull byte[] collectionSize) {
        Intrinsics.checkNotNullParameter(collectionSize, "$this$collectionSize");
        return collectionSize.length;
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    public UByteArray empty() {
        return new UByteArray(new byte[0]);
    }

    @NotNull
    /* JADX INFO: renamed from: empty-TcUX1vc, reason: not valid java name */
    public byte[] m2160emptyTcUX1vc() {
        return new byte[0];
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    public void readElement(@NotNull CompositeDecoder decoder, int i, @NotNull UByteArrayBuilder builder, boolean z) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        Intrinsics.checkNotNullParameter(builder, "builder");
        builder.m2157append7apg3OU$kotlinx_serialization_core(decoder.decodeInlineElement(this.descriptor, i).decodeByte());
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public /* synthetic */ Object toBuilder(Object obj) {
        return m2161toBuilderGBYM_sE(((UByteArray) obj).storage);
    }

    @NotNull
    /* JADX INFO: renamed from: toBuilder-GBYM_sE, reason: not valid java name */
    public UByteArrayBuilder m2161toBuilderGBYM_sE(@NotNull byte[] toBuilder) {
        Intrinsics.checkNotNullParameter(toBuilder, "$this$toBuilder");
        return new UByteArrayBuilder(toBuilder);
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    public /* synthetic */ void writeContent(CompositeEncoder compositeEncoder, UByteArray uByteArray, int i) {
        m2162writeContentCoi6ktg(compositeEncoder, uByteArray.storage, i);
    }

    /* JADX INFO: renamed from: writeContent-Coi6ktg, reason: not valid java name */
    public void m2162writeContentCoi6ktg(@NotNull CompositeEncoder encoder, @NotNull byte[] content, int i) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(content, "content");
        for (int i2 = 0; i2 < i; i2++) {
            encoder.encodeInlineElement(this.descriptor, i2).encodeByte(content[i2]);
        }
    }
}
