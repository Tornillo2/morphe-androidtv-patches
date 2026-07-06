package kotlinx.serialization.json.internal;

import java.util.Set;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.internal.UByteSerializer;
import kotlinx.serialization.internal.UIntSerializer;
import kotlinx.serialization.internal.ULongSerializer;
import kotlinx.serialization.internal.UShortSerializer;
import kotlinx.serialization.json.JsonElementKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class StreamingJsonEncoderKt {

    @NotNull
    public static final Set<SerialDescriptor> unsignedNumberDescriptors;

    static {
        ((UIntSerializer) BuiltinSerializersKt.serializer(UInt.Companion)).getClass();
        ((ULongSerializer) BuiltinSerializersKt.serializer(ULong.Companion)).getClass();
        ((UByteSerializer) BuiltinSerializersKt.serializer(UByte.Companion)).getClass();
        ((UShortSerializer) BuiltinSerializersKt.serializer(UShort.Companion)).getClass();
        unsignedNumberDescriptors = ArraysKt___ArraysKt.toSet(new SerialDescriptor[]{UIntSerializer.descriptor, ULongSerializer.descriptor, UByteSerializer.descriptor, UShortSerializer.descriptor});
    }

    public static final boolean isUnquotedLiteral(@NotNull SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        return serialDescriptor.isInline() && serialDescriptor.equals(JsonElementKt.getJsonUnquotedLiteralDescriptor());
    }

    public static final boolean isUnsignedNumber(@NotNull SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        return serialDescriptor.isInline() && unsignedNumberDescriptors.contains(serialDescriptor);
    }
}
