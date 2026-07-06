package kotlinx.serialization.json.internal;

import java.util.Map;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonArraySerializer;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonObjectSerializer;
import kotlinx.serialization.json.JsonPrimitive;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class JsonTreeMapEncoder extends JsonTreeEncoder {
    public boolean isKey;
    public String tag;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JsonTreeMapEncoder(@NotNull Json json, @NotNull Function1<? super JsonElement, Unit> nodeConsumer) {
        super(json, nodeConsumer);
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(nodeConsumer, "nodeConsumer");
        this.isKey = true;
    }

    @Override // kotlinx.serialization.json.internal.JsonTreeEncoder, kotlinx.serialization.json.internal.AbstractJsonTreeEncoder
    @NotNull
    public JsonElement getCurrent() {
        return new JsonObject(this.content);
    }

    @Override // kotlinx.serialization.json.internal.JsonTreeEncoder, kotlinx.serialization.json.internal.AbstractJsonTreeEncoder
    public void putElement(@NotNull String key, @NotNull JsonElement element) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(element, "element");
        if (!this.isKey) {
            Map<String, JsonElement> map = this.content;
            String str = this.tag;
            if (str == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tag");
                throw null;
            }
            map.put(str, element);
            this.isKey = true;
            return;
        }
        if (element instanceof JsonPrimitive) {
            this.tag = ((JsonPrimitive) element).getContent();
            this.isKey = false;
        } else {
            if (element instanceof JsonObject) {
                JsonObjectSerializer.INSTANCE.getClass();
                throw JsonExceptionsKt.InvalidKeyKindException(JsonObjectSerializer.descriptor);
            }
            if (!(element instanceof JsonArray)) {
                throw new NoWhenBranchMatchedException();
            }
            JsonArraySerializer.INSTANCE.getClass();
            throw JsonExceptionsKt.InvalidKeyKindException(JsonArraySerializer.descriptor);
        }
    }
}
