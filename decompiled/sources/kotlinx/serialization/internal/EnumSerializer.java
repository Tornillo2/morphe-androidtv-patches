package kotlinx.serialization.internal;

import java.lang.Enum;
import java.util.Arrays;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.PublishedApi;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@PublishedApi
@SourceDebugExtension({"SMAP\nEnums.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Enums.kt\nkotlinx/serialization/internal/EnumSerializer\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,148:1\n13402#2,2:149\n*S KotlinDebug\n*F\n+ 1 Enums.kt\nkotlinx/serialization/internal/EnumSerializer\n*L\n120#1:149,2\n*E\n"})
public final class EnumSerializer<T extends Enum<T>> implements KSerializer<T> {

    @NotNull
    public final Lazy descriptor$delegate;

    @Nullable
    public SerialDescriptor overriddenDescriptor;

    @NotNull
    public final T[] values;

    public EnumSerializer(@NotNull final String serialName, @NotNull T[] values) {
        Intrinsics.checkNotNullParameter(serialName, "serialName");
        Intrinsics.checkNotNullParameter(values, "values");
        this.values = values;
        this.descriptor$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: kotlinx.serialization.internal.EnumSerializer$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return EnumSerializer.descriptor_delegate$lambda$0(this.f$0, serialName);
            }
        });
    }

    public static final SerialDescriptor descriptor_delegate$lambda$0(EnumSerializer enumSerializer, String str) {
        SerialDescriptor serialDescriptor = enumSerializer.overriddenDescriptor;
        return serialDescriptor == null ? enumSerializer.createUnmarkedDescriptor(str) : serialDescriptor;
    }

    public final SerialDescriptor createUnmarkedDescriptor(String str) {
        EnumDescriptor enumDescriptor = new EnumDescriptor(str, this.values.length);
        for (T t : this.values) {
            PluginGeneratedSerialDescriptor.addElement$default(enumDescriptor, t.name(), false, 2, null);
        }
        return enumDescriptor;
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return (SerialDescriptor) this.descriptor$delegate.getValue();
    }

    @NotNull
    public String toString() {
        return "kotlinx.serialization.internal.EnumSerializer<" + getDescriptor().getSerialName() + '>';
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    public T deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        int iDecodeEnum = decoder.decodeEnum(getDescriptor());
        if (iDecodeEnum >= 0) {
            T[] tArr = this.values;
            if (iDecodeEnum < tArr.length) {
                return tArr[iDecodeEnum];
            }
        }
        throw new SerializationException(iDecodeEnum + " is not among valid " + getDescriptor().getSerialName() + " enum values, values size is " + this.values.length);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(@NotNull Encoder encoder, @NotNull T value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        int iIndexOf = ArraysKt___ArraysKt.indexOf(this.values, value);
        if (iIndexOf != -1) {
            encoder.encodeEnum(getDescriptor(), iIndexOf);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(value);
        sb.append(" is not a valid enum ");
        sb.append(getDescriptor().getSerialName());
        sb.append(", must be one of ");
        String string = Arrays.toString(this.values);
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        sb.append(string);
        throw new SerializationException(sb.toString());
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public EnumSerializer(@NotNull String serialName, @NotNull T[] values, @NotNull SerialDescriptor descriptor) {
        this(serialName, values);
        Intrinsics.checkNotNullParameter(serialName, "serialName");
        Intrinsics.checkNotNullParameter(values, "values");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        this.overriddenDescriptor = descriptor;
    }
}
