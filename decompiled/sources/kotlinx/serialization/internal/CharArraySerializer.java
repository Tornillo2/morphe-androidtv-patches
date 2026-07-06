package kotlinx.serialization.internal;

import kotlin.PublishedApi;
import kotlin.jvm.internal.CharCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@PublishedApi
public final class CharArraySerializer extends PrimitiveArraySerializer<Character, char[], CharArrayBuilder> implements KSerializer<char[]> {

    @NotNull
    public static final CharArraySerializer INSTANCE = new CharArraySerializer();

    public CharArraySerializer() {
        super(BuiltinSerializersKt.serializer(CharCompanionObject.INSTANCE));
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    public char[] empty() {
        return new char[0];
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    public void readElement(@NotNull CompositeDecoder decoder, int i, @NotNull CharArrayBuilder builder, boolean z) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        Intrinsics.checkNotNullParameter(builder, "builder");
        builder.append$kotlinx_serialization_core(decoder.decodeCharElement(this.descriptor, i));
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    public void writeContent(@NotNull CompositeEncoder encoder, @NotNull char[] content, int i) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(content, "content");
        for (int i2 = 0; i2 < i; i2++) {
            encoder.encodeCharElement(this.descriptor, i2, content[i2]);
        }
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public int collectionSize(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return cArr.length;
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    @NotNull
    /* JADX INFO: renamed from: empty, reason: avoid collision after fix types in other method */
    public char[] empty2() {
        return new char[0];
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    @NotNull
    public CharArrayBuilder toBuilder(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return new CharArrayBuilder(cArr);
    }
}
