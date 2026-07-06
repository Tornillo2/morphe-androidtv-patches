package kotlinx.serialization.json;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.PublishedApi;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@JsonDslMarker
public final class JsonObjectBuilder {

    @NotNull
    public final Map<String, JsonElement> content = new LinkedHashMap();

    @PublishedApi
    public JsonObjectBuilder() {
    }

    @PublishedApi
    @NotNull
    public final JsonObject build() {
        return new JsonObject(this.content);
    }

    @Nullable
    public final JsonElement put(@NotNull String key, @NotNull JsonElement element) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(element, "element");
        return this.content.put(key, element);
    }
}
