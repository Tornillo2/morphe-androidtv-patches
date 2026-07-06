package kotlinx.serialization.json;

import java.lang.annotation.Annotation;
import java.util.List;
import kotlin.PublishedApi;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.ExperimentalSerializationApi;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.AbstractCollectionSerializer;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.CollectionLikeSerializer;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@PublishedApi
public final class JsonArraySerializer implements KSerializer<JsonArray> {

    @NotNull
    public static final JsonArraySerializer INSTANCE = new JsonArraySerializer();

    @NotNull
    public static final SerialDescriptor descriptor = JsonArrayDescriptor.INSTANCE;

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    public JsonArray deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        JsonElementSerializersKt.asJsonDecoder(decoder);
        return new JsonArray((List) ((AbstractCollectionSerializer) BuiltinSerializersKt.ListSerializer(JsonElementSerializer.INSTANCE)).deserialize(decoder));
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(@NotNull Encoder encoder, @NotNull JsonArray value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        JsonElementSerializersKt.asJsonEncoder(encoder);
        ((CollectionLikeSerializer) BuiltinSerializersKt.ListSerializer(JsonElementSerializer.INSTANCE)).serialize(encoder, value);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class JsonArrayDescriptor implements SerialDescriptor {

        @NotNull
        public static final JsonArrayDescriptor INSTANCE = new JsonArrayDescriptor();

        @NotNull
        public static final String serialName = "kotlinx.serialization.json.JsonArray";
        public final /* synthetic */ SerialDescriptor $$delegate_0 = ((ArrayListSerializer) BuiltinSerializersKt.ListSerializer(JsonElementSerializer.INSTANCE)).descriptor;

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
