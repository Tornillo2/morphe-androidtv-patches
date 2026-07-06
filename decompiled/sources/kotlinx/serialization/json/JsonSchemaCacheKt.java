package kotlinx.serialization.json;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.DescriptorSchemaCache;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class JsonSchemaCacheKt {
    @NotNull
    public static final DescriptorSchemaCache getSchemaCache(@NotNull Json json) {
        Intrinsics.checkNotNullParameter(json, "<this>");
        return json._schemaCache;
    }

    public static /* synthetic */ void getSchemaCache$annotations(Json json) {
    }
}
