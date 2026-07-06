package kotlinx.serialization.internal;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.InternalSerializationApi;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.modules.SerializersModule;
import kotlinx.serialization.modules.SerializersModuleKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@InternalSerializationApi
@SourceDebugExtension({"SMAP\nTagged.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Tagged.kt\nkotlinx/serialization/internal/TaggedDecoder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Decoding.kt\nkotlinx/serialization/encoding/DecodingKt\n*L\n1#1,342:1\n1#2:343\n271#3,2:344\n*S KotlinDebug\n*F\n+ 1 Tagged.kt\nkotlinx/serialization/internal/TaggedDecoder\n*L\n287#1:344,2\n*E\n"})
public abstract class TaggedDecoder<Tag> implements Decoder, CompositeDecoder {
    public boolean flag;

    @NotNull
    public final ArrayList<Tag> tagStack = new ArrayList<>();

    public static final Object decodeNullableSerializableElement$lambda$3(TaggedDecoder taggedDecoder, DeserializationStrategy deserializationStrategy, Object obj) {
        if (deserializationStrategy.getDescriptor().isNullable() || taggedDecoder.decodeNotNullMark()) {
            return taggedDecoder.decodeSerializableValue(deserializationStrategy, obj);
        }
        return null;
    }

    @Override // kotlinx.serialization.encoding.Decoder
    @NotNull
    public CompositeDecoder beginStructure(@NotNull SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return this;
    }

    public final void copyTagsTo(@NotNull TaggedDecoder<Tag> other) {
        Intrinsics.checkNotNullParameter(other, "other");
        other.tagStack.addAll(this.tagStack);
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public final boolean decodeBoolean() {
        return decodeTaggedBoolean(popTag());
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final boolean decodeBooleanElement(@NotNull SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeTaggedBoolean(getTag(descriptor, i));
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public final byte decodeByte() {
        return decodeTaggedByte(popTag());
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final byte decodeByteElement(@NotNull SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeTaggedByte(getTag(descriptor, i));
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public final char decodeChar() {
        return decodeTaggedChar(popTag());
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final char decodeCharElement(@NotNull SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeTaggedChar(getTag(descriptor, i));
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public /* synthetic */ int decodeCollectionSize(SerialDescriptor serialDescriptor) {
        CompositeDecoder.CC.$default$decodeCollectionSize(this, serialDescriptor);
        return -1;
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public final double decodeDouble() {
        return decodeTaggedDouble(popTag());
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final double decodeDoubleElement(@NotNull SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeTaggedDouble(getTag(descriptor, i));
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public final int decodeEnum(@NotNull SerialDescriptor enumDescriptor) {
        Intrinsics.checkNotNullParameter(enumDescriptor, "enumDescriptor");
        return decodeTaggedEnum(popTag(), enumDescriptor);
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public final float decodeFloat() {
        return decodeTaggedFloat(popTag());
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final float decodeFloatElement(@NotNull SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeTaggedFloat(getTag(descriptor, i));
    }

    @Override // kotlinx.serialization.encoding.Decoder
    @NotNull
    public Decoder decodeInline(@NotNull SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeTaggedInline(popTag(), descriptor);
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    @NotNull
    public final Decoder decodeInlineElement(@NotNull SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeTaggedInline(getTag(descriptor, i), descriptor.getElementDescriptor(i));
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public final int decodeInt() {
        return decodeTaggedInt(popTag());
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final int decodeIntElement(@NotNull SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeTaggedInt(getTag(descriptor, i));
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public final long decodeLong() {
        return decodeTaggedLong(popTag());
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final long decodeLongElement(@NotNull SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeTaggedLong(getTag(descriptor, i));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.encoding.Decoder
    public boolean decodeNotNullMark() {
        Object objLastOrNull = CollectionsKt___CollectionsKt.lastOrNull((List<? extends Object>) this.tagStack);
        if (objLastOrNull == null) {
            return false;
        }
        return decodeTaggedNotNullMark(objLastOrNull);
    }

    @Override // kotlinx.serialization.encoding.Decoder
    @Nullable
    public final Void decodeNull() {
        return null;
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    @Nullable
    public final <T> T decodeNullableSerializableElement(@NotNull SerialDescriptor descriptor, int i, @NotNull final DeserializationStrategy<? extends T> deserializer, @Nullable final T t) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        return (T) tagBlock(getTag(descriptor, i), new Function0() { // from class: kotlinx.serialization.internal.TaggedDecoder$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return TaggedDecoder.decodeNullableSerializableElement$lambda$3(this.f$0, deserializer, t);
            }
        });
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public /* synthetic */ Object decodeNullableSerializableValue(DeserializationStrategy deserializationStrategy) {
        return Decoder.CC.$default$decodeNullableSerializableValue(this, deserializationStrategy);
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public /* synthetic */ boolean decodeSequentially() {
        CompositeDecoder.CC.$default$decodeSequentially(this);
        return false;
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final <T> T decodeSerializableElement(@NotNull SerialDescriptor descriptor, int i, @NotNull final DeserializationStrategy<? extends T> deserializer, @Nullable final T t) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        return (T) tagBlock(getTag(descriptor, i), new Function0() { // from class: kotlinx.serialization.internal.TaggedDecoder$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.f$0.decodeSerializableValue(deserializer, t);
            }
        });
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public /* synthetic */ Object decodeSerializableValue(DeserializationStrategy deserializationStrategy) {
        return Decoder.CC.$default$decodeSerializableValue(this, deserializationStrategy);
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public final short decodeShort() {
        return decodeTaggedShort(popTag());
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final short decodeShortElement(@NotNull SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeTaggedShort(getTag(descriptor, i));
    }

    @Override // kotlinx.serialization.encoding.Decoder
    @NotNull
    public final String decodeString() {
        return decodeTaggedString(popTag());
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    @NotNull
    public final String decodeStringElement(@NotNull SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeTaggedString(getTag(descriptor, i));
    }

    public boolean decodeTaggedBoolean(Tag tag) {
        decodeTaggedValue(tag);
        throw null;
    }

    public byte decodeTaggedByte(Tag tag) {
        decodeTaggedValue(tag);
        throw null;
    }

    public char decodeTaggedChar(Tag tag) {
        decodeTaggedValue(tag);
        throw null;
    }

    public double decodeTaggedDouble(Tag tag) {
        decodeTaggedValue(tag);
        throw null;
    }

    public int decodeTaggedEnum(Tag tag, @NotNull SerialDescriptor enumDescriptor) {
        Intrinsics.checkNotNullParameter(enumDescriptor, "enumDescriptor");
        decodeTaggedValue(tag);
        throw null;
    }

    public float decodeTaggedFloat(Tag tag) {
        decodeTaggedValue(tag);
        throw null;
    }

    @NotNull
    public Decoder decodeTaggedInline(Tag tag, @NotNull SerialDescriptor inlineDescriptor) {
        Intrinsics.checkNotNullParameter(inlineDescriptor, "inlineDescriptor");
        pushTag(tag);
        return this;
    }

    public int decodeTaggedInt(Tag tag) {
        decodeTaggedValue(tag);
        throw null;
    }

    public long decodeTaggedLong(Tag tag) {
        decodeTaggedValue(tag);
        throw null;
    }

    public boolean decodeTaggedNotNullMark(Tag tag) {
        return true;
    }

    @Nullable
    public Void decodeTaggedNull(Tag tag) {
        return null;
    }

    public short decodeTaggedShort(Tag tag) {
        decodeTaggedValue(tag);
        throw null;
    }

    @NotNull
    public String decodeTaggedString(Tag tag) {
        decodeTaggedValue(tag);
        throw null;
    }

    @NotNull
    public Object decodeTaggedValue(Tag tag) {
        throw new SerializationException(Reflection.getOrCreateKotlinClass(getClass()) + " can't retrieve untyped values");
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public void endStructure(@NotNull SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
    }

    public final Tag getCurrentTag() {
        return (Tag) CollectionsKt___CollectionsKt.last((List) this.tagStack);
    }

    @Nullable
    public final Tag getCurrentTagOrNull() {
        return (Tag) CollectionsKt___CollectionsKt.lastOrNull((List) this.tagStack);
    }

    @Override // kotlinx.serialization.encoding.Decoder, kotlinx.serialization.encoding.CompositeDecoder
    @NotNull
    public SerializersModule getSerializersModule() {
        return SerializersModuleKt.getEmptySerializersModule();
    }

    public abstract Tag getTag(@NotNull SerialDescriptor serialDescriptor, int i);

    @NotNull
    public final ArrayList<Tag> getTagStack$kotlinx_serialization_core() {
        return this.tagStack;
    }

    public final Tag popTag() {
        ArrayList<Tag> arrayList = this.tagStack;
        Tag tagRemove = arrayList.remove(CollectionsKt__CollectionsKt.getLastIndex(arrayList));
        this.flag = true;
        return tagRemove;
    }

    public final void pushTag(Tag tag) {
        this.tagStack.add(tag);
    }

    public final <E> E tagBlock(Tag tag, Function0<? extends E> function0) {
        pushTag(tag);
        E eInvoke = function0.invoke();
        if (!this.flag) {
            popTag();
        }
        this.flag = false;
        return eInvoke;
    }

    public <T> T decodeSerializableValue(@NotNull DeserializationStrategy<? extends T> deserializer, @Nullable T t) {
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        return (T) decodeSerializableValue(deserializer);
    }
}
