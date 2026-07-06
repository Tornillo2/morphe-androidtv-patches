package kotlinx.serialization.internal;

import androidx.emoji2.text.flatbuffer.Utf8$UnpairedSurrogateException$$ExternalSyntheticOutline0;
import java.util.Iterator;
import java.util.Map;
import kotlin.collections.MapsKt__MapWithDefaultKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.serialization.InternalSerializationApi;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Encoder;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@InternalSerializationApi
@SourceDebugExtension({"SMAP\nCollectionSerializers.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CollectionSerializers.kt\nkotlinx/serialization/internal/MapLikeSerializer\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Encoding.kt\nkotlinx/serialization/encoding/EncodingKt\n+ 4 Iterators.kt\nkotlin/collections/CollectionsKt__IteratorsKt\n*L\n1#1,283:1\n1#2:284\n489#3,2:285\n491#3,2:289\n32#4,2:287\n*S KotlinDebug\n*F\n+ 1 CollectionSerializers.kt\nkotlinx/serialization/internal/MapLikeSerializer\n*L\n118#1:285,2\n118#1:289,2\n121#1:287,2\n*E\n"})
public abstract class MapLikeSerializer<Key, Value, Collection, Builder extends Map<Key, Value>> extends AbstractCollectionSerializer<Map.Entry<? extends Key, ? extends Value>, Collection, Builder> {

    @NotNull
    public final KSerializer<Key> keySerializer;

    @NotNull
    public final KSerializer<Value> valueSerializer;

    public /* synthetic */ MapLikeSerializer(KSerializer kSerializer, KSerializer kSerializer2, DefaultConstructorMarker defaultConstructorMarker) {
        this(kSerializer, kSerializer2);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public abstract SerialDescriptor getDescriptor();

    @NotNull
    public final KSerializer<Key> getKeySerializer() {
        return this.keySerializer;
    }

    @NotNull
    public final KSerializer<Value> getValueSerializer() {
        return this.valueSerializer;
    }

    public abstract void insertKeyValuePair(@NotNull Builder builder, int i, Key key, Value value);

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer, kotlinx.serialization.SerializationStrategy
    public void serialize(@NotNull Encoder encoder, Collection collection) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        int iCollectionSize = collectionSize(collection);
        SerialDescriptor descriptor = getDescriptor();
        CompositeEncoder compositeEncoderBeginCollection = encoder.beginCollection(descriptor, iCollectionSize);
        Iterator<Map.Entry<? extends Key, ? extends Value>> itCollectionIterator = collectionIterator(collection);
        int i = 0;
        while (itCollectionIterator.hasNext()) {
            Map.Entry<? extends Key, ? extends Value> next = itCollectionIterator.next();
            Key key = next.getKey();
            Value value = next.getValue();
            int i2 = i + 1;
            compositeEncoderBeginCollection.encodeSerializableElement(getDescriptor(), i, this.keySerializer, key);
            i += 2;
            compositeEncoderBeginCollection.encodeSerializableElement(getDescriptor(), i2, this.valueSerializer, value);
        }
        compositeEncoderBeginCollection.endStructure(descriptor);
    }

    public MapLikeSerializer(KSerializer<Key> kSerializer, KSerializer<Value> kSerializer2) {
        this.keySerializer = kSerializer;
        this.valueSerializer = kSerializer2;
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public final void readAll(@NotNull CompositeDecoder decoder, @NotNull Builder builder, int i, int i2) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        Intrinsics.checkNotNullParameter(builder, "builder");
        if (i2 < 0) {
            throw new IllegalArgumentException("Size must be known in advance when using READ_ALL");
        }
        IntProgression intProgressionStep = RangesKt___RangesKt.step(RangesKt___RangesKt.until(0, i2 * 2), 2);
        int i3 = intProgressionStep.first;
        int i4 = intProgressionStep.last;
        int i5 = intProgressionStep.step;
        if ((i5 <= 0 || i3 > i4) && (i5 >= 0 || i4 > i3)) {
            return;
        }
        while (true) {
            readElement(decoder, i + i3, (Map) builder, false);
            if (i3 == i4) {
                return;
            } else {
                i3 += i5;
            }
        }
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public final void readElement(@NotNull CompositeDecoder decoder, int i, @NotNull Builder builder, boolean z) {
        int iDecodeElementIndex;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        Intrinsics.checkNotNullParameter(builder, "builder");
        Object objDecodeSerializableElement$default = CompositeDecoder.CC.decodeSerializableElement$default(decoder, getDescriptor(), i, this.keySerializer, null, 8, null);
        if (z) {
            iDecodeElementIndex = decoder.decodeElementIndex(getDescriptor());
            if (iDecodeElementIndex != i + 1) {
                throw new IllegalArgumentException(Utf8$UnpairedSurrogateException$$ExternalSyntheticOutline0.m("Value must follow key in a map, index for key: ", i, ", returned index for value: ", iDecodeElementIndex).toString());
            }
        } else {
            iDecodeElementIndex = i + 1;
        }
        int i2 = iDecodeElementIndex;
        builder.put(objDecodeSerializableElement$default, (!builder.containsKey(objDecodeSerializableElement$default) || (this.valueSerializer.getDescriptor().getKind() instanceof PrimitiveKind)) ? CompositeDecoder.CC.decodeSerializableElement$default(decoder, getDescriptor(), i2, this.valueSerializer, null, 8, null) : decoder.decodeSerializableElement(getDescriptor(), i2, this.valueSerializer, MapsKt__MapWithDefaultKt.getOrImplicitDefaultNullable(builder, objDecodeSerializableElement$default)));
    }
}
