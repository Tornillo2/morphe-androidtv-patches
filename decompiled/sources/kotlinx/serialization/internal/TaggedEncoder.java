package kotlinx.serialization.internal;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.InternalSerializationApi;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.modules.SerializersModule;
import kotlinx.serialization.modules.SerializersModuleKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@InternalSerializationApi
@SourceDebugExtension({"SMAP\nTagged.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Tagged.kt\nkotlinx/serialization/internal/TaggedEncoder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,342:1\n1#2:343\n*E\n"})
public abstract class TaggedEncoder<Tag> implements Encoder, CompositeEncoder {

    @NotNull
    public final ArrayList<Tag> tagStack = new ArrayList<>();

    private final boolean encodeElement(SerialDescriptor serialDescriptor, int i) {
        pushTag(getTag(serialDescriptor, i));
        return true;
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public /* synthetic */ CompositeEncoder beginCollection(SerialDescriptor serialDescriptor, int i) {
        return Encoder.CC.$default$beginCollection(this, serialDescriptor, i);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    @NotNull
    public CompositeEncoder beginStructure(@NotNull SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return this;
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public final void encodeBoolean(boolean z) {
        encodeTaggedBoolean(popTag(), z);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeBooleanElement(@NotNull SerialDescriptor descriptor, int i, boolean z) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        encodeTaggedBoolean(getTag(descriptor, i), z);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public final void encodeByte(byte b) {
        encodeTaggedByte(popTag(), b);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeByteElement(@NotNull SerialDescriptor descriptor, int i, byte b) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        encodeTaggedByte(getTag(descriptor, i), b);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public final void encodeChar(char c) {
        encodeTaggedChar(popTag(), c);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeCharElement(@NotNull SerialDescriptor descriptor, int i, char c) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        encodeTaggedChar(getTag(descriptor, i), c);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public final void encodeDouble(double d) {
        encodeTaggedDouble(popTag(), d);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeDoubleElement(@NotNull SerialDescriptor descriptor, int i, double d) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        encodeTaggedDouble(getTag(descriptor, i), d);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public final void encodeEnum(@NotNull SerialDescriptor enumDescriptor, int i) {
        Intrinsics.checkNotNullParameter(enumDescriptor, "enumDescriptor");
        encodeTaggedEnum(popTag(), enumDescriptor, i);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public final void encodeFloat(float f) {
        encodeTaggedFloat(popTag(), f);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeFloatElement(@NotNull SerialDescriptor descriptor, int i, float f) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        encodeTaggedFloat(getTag(descriptor, i), f);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    @NotNull
    public Encoder encodeInline(@NotNull SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return encodeTaggedInline(popTag(), descriptor);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    @NotNull
    public final Encoder encodeInlineElement(@NotNull SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return encodeTaggedInline(getTag(descriptor, i), descriptor.getElementDescriptor(i));
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public final void encodeInt(int i) {
        encodeTaggedInt(popTag(), i);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeIntElement(@NotNull SerialDescriptor descriptor, int i, int i2) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        encodeTaggedInt(getTag(descriptor, i), i2);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public final void encodeLong(long j) {
        encodeTaggedLong(popTag(), j);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeLongElement(@NotNull SerialDescriptor descriptor, int i, long j) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        encodeTaggedLong(getTag(descriptor, i), j);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public void encodeNotNullMark() {
        CollectionsKt___CollectionsKt.last((List) this.tagStack);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public void encodeNull() {
        encodeTaggedNull(popTag());
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public <T> void encodeNullableSerializableElement(@NotNull SerialDescriptor descriptor, int i, @NotNull SerializationStrategy<? super T> serializer, @Nullable T t) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        pushTag(getTag(descriptor, i));
        encodeNullableSerializableValue(serializer, t);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public /* synthetic */ void encodeNullableSerializableValue(SerializationStrategy serializationStrategy, Object obj) {
        Encoder.CC.$default$encodeNullableSerializableValue(this, serializationStrategy, obj);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public <T> void encodeSerializableElement(@NotNull SerialDescriptor descriptor, int i, @NotNull SerializationStrategy<? super T> serializer, T t) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        pushTag(getTag(descriptor, i));
        encodeSerializableValue(serializer, t);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public /* synthetic */ void encodeSerializableValue(SerializationStrategy serializationStrategy, Object obj) {
        Encoder.CC.$default$encodeSerializableValue(this, serializationStrategy, obj);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public final void encodeShort(short s) {
        encodeTaggedShort(popTag(), s);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeShortElement(@NotNull SerialDescriptor descriptor, int i, short s) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        encodeTaggedShort(getTag(descriptor, i), s);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public final void encodeString(@NotNull String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        encodeTaggedString(popTag(), value);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeStringElement(@NotNull SerialDescriptor descriptor, int i, @NotNull String value) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(value, "value");
        encodeTaggedString(getTag(descriptor, i), value);
    }

    public void encodeTaggedBoolean(Tag tag, boolean z) {
        encodeTaggedValue(tag, Boolean.valueOf(z));
    }

    public void encodeTaggedByte(Tag tag, byte b) {
        encodeTaggedValue(tag, Byte.valueOf(b));
    }

    public void encodeTaggedChar(Tag tag, char c) {
        encodeTaggedValue(tag, Character.valueOf(c));
    }

    public void encodeTaggedDouble(Tag tag, double d) {
        encodeTaggedValue(tag, Double.valueOf(d));
    }

    public void encodeTaggedEnum(Tag tag, @NotNull SerialDescriptor enumDescriptor, int i) {
        Intrinsics.checkNotNullParameter(enumDescriptor, "enumDescriptor");
        encodeTaggedValue(tag, Integer.valueOf(i));
    }

    public void encodeTaggedFloat(Tag tag, float f) {
        encodeTaggedValue(tag, Float.valueOf(f));
    }

    @NotNull
    public Encoder encodeTaggedInline(Tag tag, @NotNull SerialDescriptor inlineDescriptor) {
        Intrinsics.checkNotNullParameter(inlineDescriptor, "inlineDescriptor");
        pushTag(tag);
        return this;
    }

    public void encodeTaggedInt(Tag tag, int i) {
        encodeTaggedValue(tag, Integer.valueOf(i));
    }

    public void encodeTaggedLong(Tag tag, long j) {
        encodeTaggedValue(tag, Long.valueOf(j));
    }

    public void encodeTaggedNull(Tag tag) {
        throw new SerializationException("null is not supported");
    }

    public void encodeTaggedShort(Tag tag, short s) {
        encodeTaggedValue(tag, Short.valueOf(s));
    }

    public void encodeTaggedString(Tag tag, @NotNull String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        encodeTaggedValue(tag, value);
    }

    public void encodeTaggedValue(Tag tag, @NotNull Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        throw new SerializationException("Non-serializable " + Reflection.getOrCreateKotlinClass(value.getClass()) + " is not supported by " + Reflection.factory.getOrCreateKotlinClass(getClass()) + " encoder");
    }

    public void endEncode(@NotNull SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void endStructure(@NotNull SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        if (!this.tagStack.isEmpty()) {
            popTag();
        }
        endEncode(descriptor);
    }

    public final Tag getCurrentTag() {
        return (Tag) CollectionsKt___CollectionsKt.last((List) this.tagStack);
    }

    @Nullable
    public final Tag getCurrentTagOrNull() {
        return (Tag) CollectionsKt___CollectionsKt.lastOrNull((List) this.tagStack);
    }

    @Override // kotlinx.serialization.encoding.Encoder, kotlinx.serialization.encoding.CompositeEncoder
    @NotNull
    public SerializersModule getSerializersModule() {
        return SerializersModuleKt.getEmptySerializersModule();
    }

    public abstract Tag getTag(@NotNull SerialDescriptor serialDescriptor, int i);

    public final Tag popTag() {
        if (this.tagStack.isEmpty()) {
            throw new SerializationException("No tag in stack for requested element");
        }
        ArrayList<Tag> arrayList = this.tagStack;
        return arrayList.remove(CollectionsKt__CollectionsKt.getLastIndex(arrayList));
    }

    public final void pushTag(Tag tag) {
        this.tagStack.add(tag);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public /* synthetic */ boolean shouldEncodeElementDefault(SerialDescriptor serialDescriptor, int i) {
        CompositeEncoder.CC.$default$shouldEncodeElementDefault(this, serialDescriptor, i);
        return true;
    }

    public void encodeTaggedNonNullMark(Tag tag) {
    }
}
