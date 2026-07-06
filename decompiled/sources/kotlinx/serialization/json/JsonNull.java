package kotlinx.serialization.json;

import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@Serializable(with = JsonNullSerializer.class)
public final class JsonNull extends JsonPrimitive {

    @NotNull
    public static final JsonNull INSTANCE = new JsonNull();

    @NotNull
    public static final String content = "null";

    @Override // kotlinx.serialization.json.JsonPrimitive
    @NotNull
    public String getContent() {
        return content;
    }

    @Override // kotlinx.serialization.json.JsonPrimitive
    public boolean isString() {
        return false;
    }

    @NotNull
    public final KSerializer<JsonNull> serializer() {
        return JsonNullSerializer.INSTANCE;
    }
}
