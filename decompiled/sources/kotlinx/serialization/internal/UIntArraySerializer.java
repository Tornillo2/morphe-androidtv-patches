package kotlinx.serialization.internal;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.PublishedApi;
import kotlin.UInt;
import kotlin.UIntArray;
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
public final class UIntArraySerializer extends PrimitiveArraySerializer<UInt, UIntArray, UIntArrayBuilder> implements KSerializer<UIntArray> {

    @NotNull
    public static final UIntArraySerializer INSTANCE = new UIntArraySerializer();

    public UIntArraySerializer() {
        super(BuiltinSerializersKt.serializer(UInt.Companion));
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public /* synthetic */ int collectionSize(Object obj) {
        return m2167collectionSizeajY9A(((UIntArray) obj).storage);
    }

    /* JADX INFO: renamed from: collectionSize--ajY-9A, reason: not valid java name */
    public int m2167collectionSizeajY9A(@NotNull int[] collectionSize) {
        Intrinsics.checkNotNullParameter(collectionSize, "$this$collectionSize");
        return collectionSize.length;
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    public UIntArray empty() {
        return new UIntArray(new int[0]);
    }

    @NotNull
    /* JADX INFO: renamed from: empty--hP7Qyg, reason: not valid java name */
    public int[] m2168emptyhP7Qyg() {
        return new int[0];
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    public void readElement(@NotNull CompositeDecoder decoder, int i, @NotNull UIntArrayBuilder builder, boolean z) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        Intrinsics.checkNotNullParameter(builder, "builder");
        builder.m2165appendWZ4Q5Ns$kotlinx_serialization_core(decoder.decodeInlineElement(this.descriptor, i).decodeInt());
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public /* synthetic */ Object toBuilder(Object obj) {
        return m2169toBuilderajY9A(((UIntArray) obj).storage);
    }

    @NotNull
    /* JADX INFO: renamed from: toBuilder--ajY-9A, reason: not valid java name */
    public UIntArrayBuilder m2169toBuilderajY9A(@NotNull int[] toBuilder) {
        Intrinsics.checkNotNullParameter(toBuilder, "$this$toBuilder");
        return new UIntArrayBuilder(toBuilder);
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    public /* synthetic */ void writeContent(CompositeEncoder compositeEncoder, UIntArray uIntArray, int i) {
        m2170writeContentCPlH8fI(compositeEncoder, uIntArray.storage, i);
    }

    /* JADX INFO: renamed from: writeContent-CPlH8fI, reason: not valid java name */
    public void m2170writeContentCPlH8fI(@NotNull CompositeEncoder encoder, @NotNull int[] content, int i) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(content, "content");
        for (int i2 = 0; i2 < i; i2++) {
            encoder.encodeInlineElement(this.descriptor, i2).encodeInt(content[i2]);
        }
    }
}
