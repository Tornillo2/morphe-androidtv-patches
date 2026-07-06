package kotlinx.serialization.json.internal;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonElement;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class JsonTreeListDecoder extends AbstractJsonTreeDecoder {
    public int currentIndex;
    public final int size;

    @NotNull
    public final JsonArray value;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JsonTreeListDecoder(@NotNull Json json, @NotNull JsonArray value) {
        super(json, value, null, 4, null);
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(value, "value");
        this.value = value;
        this.size = value.content.size();
        this.currentIndex = -1;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonTreeDecoder
    @NotNull
    public JsonElement currentElement(@NotNull String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        return this.value.get(Integer.parseInt(tag));
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public int decodeElementIndex(@NotNull SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        int i = this.currentIndex;
        if (i >= this.size - 1) {
            return -1;
        }
        int i2 = i + 1;
        this.currentIndex = i2;
        return i2;
    }

    @Override // kotlinx.serialization.internal.NamedValueDecoder
    @NotNull
    public String elementName(@NotNull SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return String.valueOf(i);
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonTreeDecoder
    @NotNull
    public JsonArray getValue() {
        return this.value;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonTreeDecoder
    public JsonElement getValue() {
        return this.value;
    }
}
