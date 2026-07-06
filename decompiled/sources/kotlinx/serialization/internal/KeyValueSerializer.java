package kotlinx.serialization.internal;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import kotlin.PublishedApi;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@PublishedApi
@SourceDebugExtension({"SMAP\nTuples.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Tuples.kt\nkotlinx/serialization/internal/KeyValueSerializer\n+ 2 Decoding.kt\nkotlinx/serialization/encoding/DecodingKt\n*L\n1#1,168:1\n571#2,4:169\n*S KotlinDebug\n*F\n+ 1 Tuples.kt\nkotlinx/serialization/internal/KeyValueSerializer\n*L\n35#1:169,4\n*E\n"})
public abstract class KeyValueSerializer<K, V, R> implements KSerializer<R> {

    @NotNull
    public final KSerializer<K> keySerializer;

    @NotNull
    public final KSerializer<V> valueSerializer;

    public /* synthetic */ KeyValueSerializer(KSerializer kSerializer, KSerializer kSerializer2, DefaultConstructorMarker defaultConstructorMarker) {
        this(kSerializer, kSerializer2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.DeserializationStrategy
    public R deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor = getDescriptor();
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(descriptor);
        compositeDecoderBeginStructure.decodeSequentially();
        Object objDecodeSerializableElement$default = TuplesKt.NULL;
        Object objDecodeSerializableElement$default2 = objDecodeSerializableElement$default;
        while (true) {
            int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(getDescriptor());
            if (iDecodeElementIndex == -1) {
                Object obj = TuplesKt.NULL;
                if (objDecodeSerializableElement$default == obj) {
                    throw new SerializationException("Element 'key' is missing");
                }
                if (objDecodeSerializableElement$default2 == obj) {
                    throw new SerializationException("Element 'value' is missing");
                }
                R r = (R) toResult(objDecodeSerializableElement$default, objDecodeSerializableElement$default2);
                compositeDecoderBeginStructure.endStructure(descriptor);
                return r;
            }
            if (iDecodeElementIndex == 0) {
                objDecodeSerializableElement$default = CompositeDecoder.CC.decodeSerializableElement$default(compositeDecoderBeginStructure, getDescriptor(), 0, this.keySerializer, null, 8, null);
            } else {
                if (iDecodeElementIndex != 1) {
                    throw new SerializationException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Invalid index: ", iDecodeElementIndex));
                }
                objDecodeSerializableElement$default2 = CompositeDecoder.CC.decodeSerializableElement$default(compositeDecoderBeginStructure, getDescriptor(), 1, this.valueSerializer, null, 8, null);
            }
        }
    }

    public abstract K getKey(R r);

    @NotNull
    public final KSerializer<K> getKeySerializer() {
        return this.keySerializer;
    }

    public abstract V getValue(R r);

    @NotNull
    public final KSerializer<V> getValueSerializer() {
        return this.valueSerializer;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(@NotNull Encoder encoder, R r) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(getDescriptor());
        compositeEncoderBeginStructure.encodeSerializableElement(getDescriptor(), 0, this.keySerializer, getKey(r));
        compositeEncoderBeginStructure.encodeSerializableElement(getDescriptor(), 1, this.valueSerializer, getValue(r));
        compositeEncoderBeginStructure.endStructure(getDescriptor());
    }

    public abstract R toResult(K k, V v);

    public KeyValueSerializer(KSerializer<K> kSerializer, KSerializer<V> kSerializer2) {
        this.keySerializer = kSerializer;
        this.valueSerializer = kSerializer2;
    }
}
