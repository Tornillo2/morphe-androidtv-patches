package kotlinx.serialization.builtins;

import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.Unit;
import kotlin.jvm.internal.BooleanCompanionObject;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.CharCompanionObject;
import kotlin.jvm.internal.DoubleCompanionObject;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlin.jvm.internal.IntCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.jvm.internal.ShortCompanionObject;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.reflect.KClass;
import kotlin.time.Duration;
import kotlin.uuid.ExperimentalUuidApi;
import kotlin.uuid.Uuid;
import kotlinx.serialization.ExperimentalSerializationApi;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.BooleanArraySerializer;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.ByteArraySerializer;
import kotlinx.serialization.internal.ByteSerializer;
import kotlinx.serialization.internal.CharArraySerializer;
import kotlinx.serialization.internal.CharSerializer;
import kotlinx.serialization.internal.DoubleArraySerializer;
import kotlinx.serialization.internal.DoubleSerializer;
import kotlinx.serialization.internal.DurationSerializer;
import kotlinx.serialization.internal.FloatArraySerializer;
import kotlinx.serialization.internal.FloatSerializer;
import kotlinx.serialization.internal.IntArraySerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.LinkedHashSetSerializer;
import kotlinx.serialization.internal.LongArraySerializer;
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.MapEntrySerializer;
import kotlinx.serialization.internal.NothingSerializer;
import kotlinx.serialization.internal.NullableSerializer;
import kotlinx.serialization.internal.PairSerializer;
import kotlinx.serialization.internal.ReferenceArraySerializer;
import kotlinx.serialization.internal.ShortArraySerializer;
import kotlinx.serialization.internal.ShortSerializer;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.internal.TripleSerializer;
import kotlinx.serialization.internal.UByteArraySerializer;
import kotlinx.serialization.internal.UByteSerializer;
import kotlinx.serialization.internal.UIntArraySerializer;
import kotlinx.serialization.internal.UIntSerializer;
import kotlinx.serialization.internal.ULongArraySerializer;
import kotlinx.serialization.internal.ULongSerializer;
import kotlinx.serialization.internal.UShortArraySerializer;
import kotlinx.serialization.internal.UShortSerializer;
import kotlinx.serialization.internal.UnitSerializer;
import kotlinx.serialization.internal.UuidSerializer;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class BuiltinSerializersKt {
    @ExperimentalSerializationApi
    @NotNull
    public static final <T, E extends T> KSerializer<E[]> ArraySerializer(@NotNull KClass<T> kClass, @NotNull KSerializer<E> elementSerializer) {
        Intrinsics.checkNotNullParameter(kClass, "kClass");
        Intrinsics.checkNotNullParameter(elementSerializer, "elementSerializer");
        return new ReferenceArraySerializer(kClass, elementSerializer);
    }

    @NotNull
    public static final KSerializer<boolean[]> BooleanArraySerializer() {
        return BooleanArraySerializer.INSTANCE;
    }

    @NotNull
    public static final KSerializer<byte[]> ByteArraySerializer() {
        return ByteArraySerializer.INSTANCE;
    }

    @NotNull
    public static final KSerializer<char[]> CharArraySerializer() {
        return CharArraySerializer.INSTANCE;
    }

    @NotNull
    public static final KSerializer<double[]> DoubleArraySerializer() {
        return DoubleArraySerializer.INSTANCE;
    }

    @NotNull
    public static final KSerializer<float[]> FloatArraySerializer() {
        return FloatArraySerializer.INSTANCE;
    }

    @NotNull
    public static final KSerializer<int[]> IntArraySerializer() {
        return IntArraySerializer.INSTANCE;
    }

    @NotNull
    public static final <T> KSerializer<List<T>> ListSerializer(@NotNull KSerializer<T> elementSerializer) {
        Intrinsics.checkNotNullParameter(elementSerializer, "elementSerializer");
        return new ArrayListSerializer(elementSerializer);
    }

    @NotNull
    public static final KSerializer<long[]> LongArraySerializer() {
        return LongArraySerializer.INSTANCE;
    }

    @NotNull
    public static final <K, V> KSerializer<Map.Entry<K, V>> MapEntrySerializer(@NotNull KSerializer<K> keySerializer, @NotNull KSerializer<V> valueSerializer) {
        Intrinsics.checkNotNullParameter(keySerializer, "keySerializer");
        Intrinsics.checkNotNullParameter(valueSerializer, "valueSerializer");
        return new MapEntrySerializer(keySerializer, valueSerializer);
    }

    @NotNull
    public static final <K, V> KSerializer<Map<K, V>> MapSerializer(@NotNull KSerializer<K> keySerializer, @NotNull KSerializer<V> valueSerializer) {
        Intrinsics.checkNotNullParameter(keySerializer, "keySerializer");
        Intrinsics.checkNotNullParameter(valueSerializer, "valueSerializer");
        return new LinkedHashMapSerializer(keySerializer, valueSerializer);
    }

    @ExperimentalSerializationApi
    @NotNull
    public static final KSerializer NothingSerializer() {
        return NothingSerializer.INSTANCE;
    }

    @NotNull
    public static final <K, V> KSerializer<Pair<K, V>> PairSerializer(@NotNull KSerializer<K> keySerializer, @NotNull KSerializer<V> valueSerializer) {
        Intrinsics.checkNotNullParameter(keySerializer, "keySerializer");
        Intrinsics.checkNotNullParameter(valueSerializer, "valueSerializer");
        return new PairSerializer(keySerializer, valueSerializer);
    }

    @NotNull
    public static final <T> KSerializer<Set<T>> SetSerializer(@NotNull KSerializer<T> elementSerializer) {
        Intrinsics.checkNotNullParameter(elementSerializer, "elementSerializer");
        return new LinkedHashSetSerializer(elementSerializer);
    }

    @NotNull
    public static final KSerializer<short[]> ShortArraySerializer() {
        return ShortArraySerializer.INSTANCE;
    }

    @NotNull
    public static final <A, B, C> KSerializer<Triple<A, B, C>> TripleSerializer(@NotNull KSerializer<A> aSerializer, @NotNull KSerializer<B> bSerializer, @NotNull KSerializer<C> cSerializer) {
        Intrinsics.checkNotNullParameter(aSerializer, "aSerializer");
        Intrinsics.checkNotNullParameter(bSerializer, "bSerializer");
        Intrinsics.checkNotNullParameter(cSerializer, "cSerializer");
        return new TripleSerializer(aSerializer, bSerializer, cSerializer);
    }

    @ExperimentalSerializationApi
    @ExperimentalUnsignedTypes
    @NotNull
    public static final KSerializer<UByteArray> UByteArraySerializer() {
        return UByteArraySerializer.INSTANCE;
    }

    @ExperimentalSerializationApi
    @ExperimentalUnsignedTypes
    @NotNull
    public static final KSerializer<UIntArray> UIntArraySerializer() {
        return UIntArraySerializer.INSTANCE;
    }

    @ExperimentalSerializationApi
    @ExperimentalUnsignedTypes
    @NotNull
    public static final KSerializer<ULongArray> ULongArraySerializer() {
        return ULongArraySerializer.INSTANCE;
    }

    @ExperimentalSerializationApi
    @ExperimentalUnsignedTypes
    @NotNull
    public static final KSerializer<UShortArray> UShortArraySerializer() {
        return UShortArraySerializer.INSTANCE;
    }

    @NotNull
    public static final <T> KSerializer<T> getNullable(@NotNull KSerializer<T> kSerializer) {
        Intrinsics.checkNotNullParameter(kSerializer, "<this>");
        return kSerializer.getDescriptor().isNullable() ? kSerializer : new NullableSerializer(kSerializer);
    }

    @NotNull
    public static final KSerializer<Character> serializer(@NotNull CharCompanionObject charCompanionObject) {
        Intrinsics.checkNotNullParameter(charCompanionObject, "<this>");
        return CharSerializer.INSTANCE;
    }

    @ExperimentalSerializationApi
    public static final <T, E extends T> KSerializer<E[]> ArraySerializer(KSerializer<E> elementSerializer) {
        Intrinsics.checkNotNullParameter(elementSerializer, "elementSerializer");
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    @NotNull
    public static final KSerializer<Byte> serializer(@NotNull ByteCompanionObject byteCompanionObject) {
        Intrinsics.checkNotNullParameter(byteCompanionObject, "<this>");
        return ByteSerializer.INSTANCE;
    }

    @NotNull
    public static final KSerializer<Short> serializer(@NotNull ShortCompanionObject shortCompanionObject) {
        Intrinsics.checkNotNullParameter(shortCompanionObject, "<this>");
        return ShortSerializer.INSTANCE;
    }

    @NotNull
    public static final KSerializer<Integer> serializer(@NotNull IntCompanionObject intCompanionObject) {
        Intrinsics.checkNotNullParameter(intCompanionObject, "<this>");
        return IntSerializer.INSTANCE;
    }

    @NotNull
    public static final KSerializer<Long> serializer(@NotNull LongCompanionObject longCompanionObject) {
        Intrinsics.checkNotNullParameter(longCompanionObject, "<this>");
        return LongSerializer.INSTANCE;
    }

    @NotNull
    public static final KSerializer<Float> serializer(@NotNull FloatCompanionObject floatCompanionObject) {
        Intrinsics.checkNotNullParameter(floatCompanionObject, "<this>");
        return FloatSerializer.INSTANCE;
    }

    @NotNull
    public static final KSerializer<Double> serializer(@NotNull DoubleCompanionObject doubleCompanionObject) {
        Intrinsics.checkNotNullParameter(doubleCompanionObject, "<this>");
        return DoubleSerializer.INSTANCE;
    }

    @NotNull
    public static final KSerializer<Boolean> serializer(@NotNull BooleanCompanionObject booleanCompanionObject) {
        Intrinsics.checkNotNullParameter(booleanCompanionObject, "<this>");
        return BooleanSerializer.INSTANCE;
    }

    @NotNull
    public static final KSerializer<Unit> serializer(@NotNull Unit unit) {
        Intrinsics.checkNotNullParameter(unit, "<this>");
        return UnitSerializer.INSTANCE;
    }

    @NotNull
    public static final KSerializer<String> serializer(@NotNull StringCompanionObject stringCompanionObject) {
        Intrinsics.checkNotNullParameter(stringCompanionObject, "<this>");
        return StringSerializer.INSTANCE;
    }

    @NotNull
    public static final KSerializer<UInt> serializer(@NotNull UInt.Companion companion) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return UIntSerializer.INSTANCE;
    }

    @NotNull
    public static final KSerializer<ULong> serializer(@NotNull ULong.Companion companion) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return ULongSerializer.INSTANCE;
    }

    @NotNull
    public static final KSerializer<UByte> serializer(@NotNull UByte.Companion companion) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return UByteSerializer.INSTANCE;
    }

    @NotNull
    public static final KSerializer<UShort> serializer(@NotNull UShort.Companion companion) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return UShortSerializer.INSTANCE;
    }

    @NotNull
    public static final KSerializer<Duration> serializer(@NotNull Duration.Companion companion) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return DurationSerializer.INSTANCE;
    }

    @ExperimentalUuidApi
    @NotNull
    public static final KSerializer<Uuid> serializer(@NotNull Uuid.Companion companion) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return UuidSerializer.INSTANCE;
    }

    public static /* synthetic */ void getNullable$annotations(KSerializer kSerializer) {
    }
}
