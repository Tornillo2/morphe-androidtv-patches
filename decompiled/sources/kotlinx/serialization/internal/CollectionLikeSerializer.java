package kotlinx.serialization.internal;

import java.util.Iterator;
import kotlin.PublishedApi;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Encoder;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@PublishedApi
@SourceDebugExtension({"SMAP\nCollectionSerializers.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CollectionSerializers.kt\nkotlinx/serialization/internal/CollectionLikeSerializer\n+ 2 Encoding.kt\nkotlinx/serialization/encoding/EncodingKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,283:1\n489#2,4:284\n1#3:288\n*S KotlinDebug\n*F\n+ 1 CollectionSerializers.kt\nkotlinx/serialization/internal/CollectionLikeSerializer\n*L\n66#1:284,4\n*E\n"})
public abstract class CollectionLikeSerializer<Element, Collection, Builder> extends AbstractCollectionSerializer<Element, Collection, Builder> {

    @NotNull
    public final KSerializer<Element> elementSerializer;

    public /* synthetic */ CollectionLikeSerializer(KSerializer kSerializer, DefaultConstructorMarker defaultConstructorMarker) {
        this(kSerializer);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public abstract SerialDescriptor getDescriptor();

    public abstract void insert(Builder builder, int i, Element element);

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public final void readAll(@NotNull CompositeDecoder decoder, Builder builder, int i, int i2) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        if (i2 < 0) {
            throw new IllegalArgumentException("Size must be known in advance when using READ_ALL");
        }
        for (int i3 = 0; i3 < i2; i3++) {
            readElement(decoder, i + i3, builder, false);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public void readElement(@NotNull CompositeDecoder decoder, int i, Builder builder, boolean z) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        insert(builder, i, CompositeDecoder.CC.decodeSerializableElement$default(decoder, getDescriptor(), i, this.elementSerializer, null, 8, null));
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer, kotlinx.serialization.SerializationStrategy
    public void serialize(@NotNull Encoder encoder, Collection collection) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        int iCollectionSize = collectionSize(collection);
        SerialDescriptor descriptor = getDescriptor();
        CompositeEncoder compositeEncoderBeginCollection = encoder.beginCollection(descriptor, iCollectionSize);
        Iterator<Element> itCollectionIterator = collectionIterator(collection);
        for (int i = 0; i < iCollectionSize; i++) {
            compositeEncoderBeginCollection.encodeSerializableElement(getDescriptor(), i, this.elementSerializer, itCollectionIterator.next());
        }
        compositeEncoderBeginCollection.endStructure(descriptor);
    }

    public CollectionLikeSerializer(KSerializer<Element> kSerializer) {
        this.elementSerializer = kSerializer;
    }
}
