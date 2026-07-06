package kotlinx.serialization.json;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class JsonKt {

    @NotNull
    public static final String defaultDiscriminator = "type";

    @NotNull
    public static final String defaultIndent = "    ";

    @NotNull
    public static final Json Json(@NotNull Json from, @NotNull Function1<? super JsonBuilder, Unit> builderAction) {
        Intrinsics.checkNotNullParameter(from, "from");
        Intrinsics.checkNotNullParameter(builderAction, "builderAction");
        JsonBuilder jsonBuilder = new JsonBuilder(from);
        builderAction.invoke(jsonBuilder);
        return new JsonImpl(jsonBuilder.build$kotlinx_serialization_json(), jsonBuilder.serializersModule);
    }

    public static /* synthetic */ Json Json$default(Json json, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            json = Json.Default;
        }
        return Json(json, function1);
    }

    public static final <T> T decodeFromJsonElement(Json json, JsonElement json2) {
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter(json2, "json");
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    public static final <T> JsonElement encodeToJsonElement(Json json, T t) {
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.throwUndefinedForReified();
        throw null;
    }
}
