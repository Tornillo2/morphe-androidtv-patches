package kotlinx.serialization.json;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.PublishedApi;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.ExperimentalSerializationApi;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@JsonDslMarker
public final class JsonArrayBuilder {

    @NotNull
    public final List<JsonElement> content = new ArrayList();

    @PublishedApi
    public JsonArrayBuilder() {
    }

    public final boolean add(@NotNull JsonElement element) {
        Intrinsics.checkNotNullParameter(element, "element");
        this.content.add(element);
        return true;
    }

    @ExperimentalSerializationApi
    public final boolean addAll(@NotNull Collection<? extends JsonElement> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return this.content.addAll(elements);
    }

    @PublishedApi
    @NotNull
    public final JsonArray build() {
        return new JsonArray(this.content);
    }
}
