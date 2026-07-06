package kotlinx.serialization.json;

import java.lang.annotation.Annotation;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class JsonElementSerializersKt {

    /* JADX INFO: renamed from: kotlinx.serialization.json.JsonElementSerializersKt$defer$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass1 implements SerialDescriptor {
        public final Lazy original$delegate;

        public AnonymousClass1(Function0<? extends SerialDescriptor> function0) {
            this.original$delegate = LazyKt__LazyJVMKt.lazy(function0);
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        public List getAnnotations() {
            return EmptyList.INSTANCE;
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        public List<Annotation> getElementAnnotations(int i) {
            return getOriginal().getElementAnnotations(i);
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        public SerialDescriptor getElementDescriptor(int i) {
            return getOriginal().getElementDescriptor(i);
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        public int getElementIndex(String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            return getOriginal().getElementIndex(name);
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        public String getElementName(int i) {
            return getOriginal().getElementName(i);
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        public int getElementsCount() {
            return getOriginal().getElementsCount();
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        public SerialKind getKind() {
            return getOriginal().getKind();
        }

        public final SerialDescriptor getOriginal() {
            return (SerialDescriptor) this.original$delegate.getValue();
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        public String getSerialName() {
            return getOriginal().getSerialName();
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        public boolean isElementOptional(int i) {
            return getOriginal().isElementOptional(i);
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        public /* synthetic */ boolean isInline() {
            return false;
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        public /* synthetic */ boolean isNullable() {
            return false;
        }
    }

    public static final SerialDescriptor access$defer(Function0 function0) {
        return new AnonymousClass1(function0);
    }

    @NotNull
    public static final JsonDecoder asJsonDecoder(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "<this>");
        JsonDecoder jsonDecoder = decoder instanceof JsonDecoder ? (JsonDecoder) decoder : null;
        if (jsonDecoder != null) {
            return jsonDecoder;
        }
        throw new IllegalStateException("This serializer can be used only with Json format.Expected Decoder to be JsonDecoder, got " + Reflection.getOrCreateKotlinClass(decoder.getClass()));
    }

    @NotNull
    public static final JsonEncoder asJsonEncoder(@NotNull Encoder encoder) {
        Intrinsics.checkNotNullParameter(encoder, "<this>");
        JsonEncoder jsonEncoder = encoder instanceof JsonEncoder ? (JsonEncoder) encoder : null;
        if (jsonEncoder != null) {
            return jsonEncoder;
        }
        throw new IllegalStateException("This serializer can be used only with Json format.Expected Encoder to be JsonEncoder, got " + Reflection.getOrCreateKotlinClass(encoder.getClass()));
    }

    public static final SerialDescriptor defer(Function0<? extends SerialDescriptor> function0) {
        return new AnonymousClass1(function0);
    }

    public static final void verify(Encoder encoder) {
        asJsonEncoder(encoder);
    }

    public static final void verify(Decoder decoder) {
        asJsonDecoder(decoder);
    }
}
