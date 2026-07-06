package kotlinx.serialization.internal;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import java.lang.annotation.Annotation;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.PublishedApi;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.ClassSerialDescriptorBuilder;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.descriptors.StructureKind;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@PublishedApi
@SourceDebugExtension({"SMAP\nObjectSerializer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ObjectSerializer.kt\nkotlinx/serialization/internal/ObjectSerializer\n+ 2 Decoding.kt\nkotlinx/serialization/encoding/DecodingKt\n*L\n1#1,57:1\n571#2,4:58\n*S KotlinDebug\n*F\n+ 1 ObjectSerializer.kt\nkotlinx/serialization/internal/ObjectSerializer\n*L\n43#1:58,4\n*E\n"})
public final class ObjectSerializer<T> implements KSerializer<T> {

    @NotNull
    public List<? extends Annotation> _annotations;

    @NotNull
    public final Lazy descriptor$delegate;

    @NotNull
    public final T objectInstance;

    public ObjectSerializer(@NotNull final String serialName, @NotNull T objectInstance) {
        Intrinsics.checkNotNullParameter(serialName, "serialName");
        Intrinsics.checkNotNullParameter(objectInstance, "objectInstance");
        this.objectInstance = objectInstance;
        this._annotations = EmptyList.INSTANCE;
        this.descriptor$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: kotlinx.serialization.internal.ObjectSerializer$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return ObjectSerializer.descriptor_delegate$lambda$1(serialName, this);
            }
        });
    }

    public static final SerialDescriptor descriptor_delegate$lambda$1(String str, final ObjectSerializer objectSerializer) {
        return SerialDescriptorsKt.buildSerialDescriptor(str, StructureKind.OBJECT.INSTANCE, new SerialDescriptor[0], new Function1() { // from class: kotlinx.serialization.internal.ObjectSerializer$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ObjectSerializer.descriptor_delegate$lambda$1$lambda$0(this.f$0, (ClassSerialDescriptorBuilder) obj);
            }
        });
    }

    public static final Unit descriptor_delegate$lambda$1$lambda$0(ObjectSerializer objectSerializer, ClassSerialDescriptorBuilder buildSerialDescriptor) {
        Intrinsics.checkNotNullParameter(buildSerialDescriptor, "$this$buildSerialDescriptor");
        buildSerialDescriptor.setAnnotations(objectSerializer._annotations);
        return Unit.INSTANCE;
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    public T deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor = getDescriptor();
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(descriptor);
        compositeDecoderBeginStructure.decodeSequentially();
        int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(getDescriptor());
        if (iDecodeElementIndex != -1) {
            throw new SerializationException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Unexpected index ", iDecodeElementIndex));
        }
        compositeDecoderBeginStructure.endStructure(descriptor);
        return this.objectInstance;
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return (SerialDescriptor) this.descriptor$delegate.getValue();
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(@NotNull Encoder encoder, @NotNull T value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        encoder.beginStructure(getDescriptor()).endStructure(getDescriptor());
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @PublishedApi
    public ObjectSerializer(@NotNull String serialName, @NotNull T objectInstance, @NotNull Annotation[] classAnnotations) {
        this(serialName, objectInstance);
        Intrinsics.checkNotNullParameter(serialName, "serialName");
        Intrinsics.checkNotNullParameter(objectInstance, "objectInstance");
        Intrinsics.checkNotNullParameter(classAnnotations, "classAnnotations");
        this._annotations = ArraysKt___ArraysJvmKt.asList(classAnnotations);
    }
}
