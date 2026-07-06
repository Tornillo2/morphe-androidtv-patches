package kotlinx.serialization.json.internal;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonElement;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class TreeJsonEncoderKt {

    @NotNull
    public static final String PRIMITIVE_TAG = "primitive";

    public static final <T extends JsonElement> T cast(JsonElement value, String serialName, Function0<String> path) {
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(serialName, "serialName");
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    public static final boolean getRequiresTopLevelTag(SerialDescriptor serialDescriptor) {
        return (serialDescriptor.getKind() instanceof PrimitiveKind) || serialDescriptor.getKind() == SerialKind.ENUM.INSTANCE;
    }

    @JsonFriendModuleApi
    @NotNull
    public static final <T> JsonElement writeJson(@NotNull Json json, T t, @NotNull SerializationStrategy<? super T> serializer) {
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        new JsonTreeEncoder(json, new Function1() { // from class: kotlinx.serialization.json.internal.TreeJsonEncoderKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TreeJsonEncoderKt.writeJson$lambda$0(objectRef, (JsonElement) obj);
            }
        }).encodeSerializableValue(serializer, t);
        T t2 = objectRef.element;
        if (t2 != null) {
            return (JsonElement) t2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("result");
        throw null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit writeJson$lambda$0(Ref.ObjectRef objectRef, JsonElement it) {
        Intrinsics.checkNotNullParameter(it, "it");
        objectRef.element = it;
        return Unit.INSTANCE;
    }
}
