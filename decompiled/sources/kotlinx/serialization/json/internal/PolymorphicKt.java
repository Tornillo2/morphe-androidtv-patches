package kotlinx.serialization.json.internal;

import androidx.activity.result.ActivityResultRegistry$$ExternalSyntheticOutline0;
import androidx.media3.common.TrackGroup$$ExternalSyntheticOutline0;
import java.lang.annotation.Annotation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.ClassReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.PolymorphicSerializerKt;
import kotlinx.serialization.SealedClassSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.PolymorphicKind;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.internal.AbstractPolymorphicSerializer;
import kotlinx.serialization.internal.JsonInternalDependenciesKt;
import kotlinx.serialization.json.ClassDiscriminatorMode;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonClassDiscriminator;
import kotlinx.serialization.json.JsonDecoder;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SourceDebugExtension({"SMAP\nPolymorphic.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Polymorphic.kt\nkotlinx/serialization/json/internal/PolymorphicKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 TreeJsonEncoder.kt\nkotlinx/serialization/json/internal/TreeJsonEncoderKt\n*L\n1#1,109:1\n1#2:110\n270#3,8:111\n*S KotlinDebug\n*F\n+ 1 Polymorphic.kt\nkotlinx/serialization/json/internal/PolymorphicKt\n*L\n83#1:111,8\n*E\n"})
public final class PolymorphicKt {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ClassDiscriminatorMode.values().length];
            try {
                iArr[ClassDiscriminatorMode.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ClassDiscriminatorMode.POLYMORPHIC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ClassDiscriminatorMode.ALL_JSON_OBJECTS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final void checkKind(@NotNull SerialKind kind) {
        Intrinsics.checkNotNullParameter(kind, "kind");
        if (kind instanceof SerialKind.ENUM) {
            throw new IllegalStateException("Enums cannot be serialized polymorphically with 'type' parameter. You can use 'JsonBuilder.useArrayPolymorphism' instead");
        }
        if (kind instanceof PrimitiveKind) {
            throw new IllegalStateException("Primitives cannot be serialized polymorphically with 'type' parameter. You can use 'JsonBuilder.useArrayPolymorphism' instead");
        }
        if (kind instanceof PolymorphicKind) {
            throw new IllegalStateException("Actual serializer for polymorphic cannot be polymorphic itself");
        }
    }

    @NotNull
    public static final String classDiscriminator(@NotNull SerialDescriptor serialDescriptor, @NotNull Json json) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        Intrinsics.checkNotNullParameter(json, "json");
        for (Annotation annotation : serialDescriptor.getAnnotations()) {
            if (annotation instanceof JsonClassDiscriminator) {
                return ((JsonClassDiscriminator) annotation).discriminator();
            }
        }
        return json.configuration.classDiscriminator;
    }

    public static final <T> T decodeSerializableValuePolymorphic(@NotNull JsonDecoder jsonDecoder, @NotNull DeserializationStrategy<? extends T> deserializer, @NotNull Function0<String> path) {
        Intrinsics.checkNotNullParameter(jsonDecoder, "<this>");
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(path, "path");
        if (!(deserializer instanceof AbstractPolymorphicSerializer) || jsonDecoder.getJson().configuration.useArrayPolymorphism) {
            return deserializer.deserialize(jsonDecoder);
        }
        AbstractPolymorphicSerializer abstractPolymorphicSerializer = (AbstractPolymorphicSerializer) deserializer;
        String strClassDiscriminator = classDiscriminator(abstractPolymorphicSerializer.getDescriptor(), jsonDecoder.getJson());
        JsonElement jsonElementDecodeJsonElement = jsonDecoder.decodeJsonElement();
        String serialName = abstractPolymorphicSerializer.getDescriptor().getSerialName();
        if (jsonElementDecodeJsonElement instanceof JsonObject) {
            JsonObject jsonObject = (JsonObject) jsonElementDecodeJsonElement;
            JsonElement jsonElement = (JsonElement) jsonObject.get((Object) strClassDiscriminator);
            try {
                return (T) TreeJsonDecoderKt.readPolymorphicJson(jsonDecoder.getJson(), strClassDiscriminator, jsonObject, PolymorphicSerializerKt.findPolymorphicSerializer((AbstractPolymorphicSerializer) deserializer, jsonDecoder, jsonElement != null ? JsonElementKt.getContentOrNull(JsonElementKt.getJsonPrimitive(jsonElement)) : null));
            } catch (SerializationException e) {
                String message = e.getMessage();
                Intrinsics.checkNotNull(message);
                throw JsonExceptionsKt.JsonDecodingException(-1, message, jsonObject.toString());
            }
        }
        StringBuilder sb = new StringBuilder("Expected ");
        sb.append(((ClassReference) Reflection.factory.getOrCreateKotlinClass(AbstractJsonTreeDecoder$$ExternalSyntheticOutline0.m((ClassReference) Reflection.getOrCreateKotlinClass(JsonObject.class), sb, ", but had ", jsonElementDecodeJsonElement))).getSimpleName());
        sb.append(" as the serialized body of ");
        sb.append(serialName);
        sb.append(" at element: ");
        sb.append(path.invoke());
        throw JsonExceptionsKt.JsonDecodingException(-1, sb.toString(), jsonElementDecodeJsonElement.toString());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:21:0x005f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T> void encodePolymorphically(@org.jetbrains.annotations.NotNull kotlinx.serialization.json.JsonEncoder r3, @org.jetbrains.annotations.NotNull kotlinx.serialization.SerializationStrategy<? super T> r4, T r5, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super java.lang.String, ? super java.lang.String, kotlin.Unit> r6) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "serializer"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "ifPolymorphic"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            kotlinx.serialization.json.Json r0 = r3.getJson()
            kotlinx.serialization.json.JsonConfiguration r0 = r0.configuration
            boolean r0 = r0.useArrayPolymorphism
            if (r0 == 0) goto L1d
            r4.serialize(r3, r5)
            return
        L1d:
            boolean r0 = r4 instanceof kotlinx.serialization.internal.AbstractPolymorphicSerializer
            if (r0 == 0) goto L2e
            kotlinx.serialization.json.Json r1 = r3.getJson()
            kotlinx.serialization.json.JsonConfiguration r1 = r1.configuration
            kotlinx.serialization.json.ClassDiscriminatorMode r1 = r1.classDiscriminatorMode
            kotlinx.serialization.json.ClassDiscriminatorMode r2 = kotlinx.serialization.json.ClassDiscriminatorMode.NONE
            if (r1 == r2) goto L72
            goto L5f
        L2e:
            kotlinx.serialization.json.Json r1 = r3.getJson()
            kotlinx.serialization.json.JsonConfiguration r1 = r1.configuration
            kotlinx.serialization.json.ClassDiscriminatorMode r1 = r1.classDiscriminatorMode
            int[] r2 = kotlinx.serialization.json.internal.PolymorphicKt.WhenMappings.$EnumSwitchMapping$0
            int r1 = r1.ordinal()
            r1 = r2[r1]
            r2 = 1
            if (r1 == r2) goto L72
            r2 = 2
            if (r1 == r2) goto L72
            r2 = 3
            if (r1 != r2) goto L6c
            kotlinx.serialization.descriptors.SerialDescriptor r1 = r4.getDescriptor()
            kotlinx.serialization.descriptors.SerialKind r1 = r1.getKind()
            kotlinx.serialization.descriptors.StructureKind$CLASS r2 = kotlinx.serialization.descriptors.StructureKind.CLASS.INSTANCE
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r2)
            if (r2 != 0) goto L5f
            kotlinx.serialization.descriptors.StructureKind$OBJECT r2 = kotlinx.serialization.descriptors.StructureKind.OBJECT.INSTANCE
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r2)
            if (r1 == 0) goto L72
        L5f:
            kotlinx.serialization.descriptors.SerialDescriptor r1 = r4.getDescriptor()
            kotlinx.serialization.json.Json r2 = r3.getJson()
            java.lang.String r1 = classDiscriminator(r1, r2)
            goto L73
        L6c:
            kotlin.NoWhenBranchMatchedException r3 = new kotlin.NoWhenBranchMatchedException
            r3.<init>()
            throw r3
        L72:
            r1 = 0
        L73:
            if (r0 == 0) goto Lb1
            r0 = r4
            kotlinx.serialization.internal.AbstractPolymorphicSerializer r0 = (kotlinx.serialization.internal.AbstractPolymorphicSerializer) r0
            if (r5 == 0) goto L90
            kotlinx.serialization.SerializationStrategy r0 = kotlinx.serialization.PolymorphicSerializerKt.findPolymorphicSerializer(r0, r3, r5)
            if (r1 == 0) goto L8e
            validateIfSealed(r4, r0, r1)
            kotlinx.serialization.descriptors.SerialDescriptor r4 = r0.getDescriptor()
            kotlinx.serialization.descriptors.SerialKind r4 = r4.getKind()
            checkKind(r4)
        L8e:
            r4 = r0
            goto Lb1
        L90:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Value for serializer "
            r3.<init>(r4)
            kotlinx.serialization.descriptors.SerialDescriptor r4 = r0.getDescriptor()
            r3.append(r4)
            java.lang.String r4 = " should always be non-null. Please report issue to the kotlinx.serialization tracker."
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.String r3 = r3.toString()
            r4.<init>(r3)
            throw r4
        Lb1:
            if (r1 == 0) goto Lbe
            kotlinx.serialization.descriptors.SerialDescriptor r0 = r4.getDescriptor()
            java.lang.String r0 = r0.getSerialName()
            r6.invoke(r1, r0)
        Lbe:
            r4.serialize(r3, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.serialization.json.internal.PolymorphicKt.encodePolymorphically(kotlinx.serialization.json.JsonEncoder, kotlinx.serialization.SerializationStrategy, java.lang.Object, kotlin.jvm.functions.Function2):void");
    }

    @NotNull
    public static final Void throwJsonElementPolymorphicException(@Nullable String str, @NotNull JsonElement element) {
        Intrinsics.checkNotNullParameter(element, "element");
        StringBuilder sbM = ActivityResultRegistry$$ExternalSyntheticOutline0.m("Class with serial name ", str, " cannot be serialized polymorphically because it is represented as ");
        sbM.append(((ClassReference) Reflection.getOrCreateKotlinClass(element.getClass())).getSimpleName());
        sbM.append(". Make sure that its JsonTransformingSerializer returns JsonObject, so class discriminator can be added to it.");
        throw new JsonEncodingException(sbM.toString());
    }

    public static final void validateIfSealed(SerializationStrategy<?> serializationStrategy, SerializationStrategy<?> serializationStrategy2, String str) {
        if ((serializationStrategy instanceof SealedClassSerializer) && JsonInternalDependenciesKt.jsonCachedSerialNames(serializationStrategy2.getDescriptor()).contains(str)) {
            StringBuilder sbM = TrackGroup$$ExternalSyntheticOutline0.m("Sealed class '", serializationStrategy2.getDescriptor().getSerialName(), "' cannot be serialized as base class '", ((SealedClassSerializer) serializationStrategy).getDescriptor().getSerialName(), "' because it has property name that conflicts with JSON class discriminator '");
            sbM.append(str);
            sbM.append("'. You can either change class discriminator in JsonConfiguration, rename property with @SerialName annotation or fall back to array polymorphism");
            throw new IllegalStateException(sbM.toString().toString());
        }
    }
}
