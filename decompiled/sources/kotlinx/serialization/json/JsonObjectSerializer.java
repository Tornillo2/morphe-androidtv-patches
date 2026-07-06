package kotlinx.serialization.json;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import kotlin.PublishedApi;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.serialization.ExperimentalSerializationApi;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.AbstractCollectionSerializer;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.MapLikeSerializer;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@PublishedApi
public final class JsonObjectSerializer implements KSerializer<JsonObject> {

    @NotNull
    public static final JsonObjectSerializer INSTANCE = new JsonObjectSerializer();

    @NotNull
    public static final SerialDescriptor descriptor = JsonObjectDescriptor.INSTANCE;

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    public JsonObject deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        JsonElementSerializersKt.asJsonDecoder(decoder);
        return new JsonObject((Map) ((AbstractCollectionSerializer) BuiltinSerializersKt.MapSerializer(BuiltinSerializersKt.serializer(StringCompanionObject.INSTANCE), JsonElementSerializer.INSTANCE)).deserialize(decoder));
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(@NotNull Encoder encoder, @NotNull JsonObject value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        JsonElementSerializersKt.asJsonEncoder(encoder);
        ((MapLikeSerializer) BuiltinSerializersKt.MapSerializer(BuiltinSerializersKt.serializer(StringCompanionObject.INSTANCE), JsonElementSerializer.INSTANCE)).serialize(encoder, value);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class JsonObjectDescriptor implements SerialDescriptor {

        @NotNull
        public static final JsonObjectDescriptor INSTANCE = new JsonObjectDescriptor();

        @NotNull
        public static final String serialName = "kotlinx.serialization.json.JsonObject";
        public final /* synthetic */ SerialDescriptor $$delegate_0 = ((LinkedHashMapSerializer) BuiltinSerializersKt.MapSerializer(BuiltinSerializersKt.serializer(StringCompanionObject.INSTANCE), JsonElementSerializer.INSTANCE)).descriptor;

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        @NotNull
        public List<Annotation> getAnnotations() {
            return this.$$delegate_0.getAnnotations();
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        @NotNull
        public List<Annotation> getElementAnnotations(int i) {
            return this.$$delegate_0.getElementAnnotations(i);
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        @NotNull
        public SerialDescriptor getElementDescriptor(int i) {
            return this.$$delegate_0.getElementDescriptor(i);
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        public int getElementIndex(@NotNull String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            return this.$$delegate_0.getElementIndex(name);
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        @NotNull
        public String getElementName(int i) {
            return this.$$delegate_0.getElementName(i);
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        public int getElementsCount() {
            return this.$$delegate_0.getElementsCount();
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        @NotNull
        public SerialKind getKind() {
            return this.$$delegate_0.getKind();
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        @NotNull
        public String getSerialName() {
            return serialName;
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        public boolean isElementOptional(int i) {
            return this.$$delegate_0.isElementOptional(i);
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        public boolean isInline() {
            return this.$$delegate_0.isInline();
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        public boolean isNullable() {
            return this.$$delegate_0.isNullable();
        }

        @ExperimentalSerializationApi
        public static /* synthetic */ void getSerialName$annotations() {
        }
    }
}
