package kotlinx.serialization.json;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.ExperimentalSerializationApi;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SourceDebugExtension({"SMAP\nJsonElementBuilders.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JsonElementBuilders.kt\nkotlinx/serialization/json/JsonElementBuildersKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,231:1\n29#1,3:232\n52#1,3:235\n29#1,3:238\n52#1,3:241\n1557#2:244\n1628#2,3:245\n1557#2:248\n1628#2,3:249\n1557#2:252\n1628#2,3:253\n*S KotlinDebug\n*F\n+ 1 JsonElementBuilders.kt\nkotlinx/serialization/json/JsonElementBuildersKt\n*L\n82#1:232,3\n90#1:235,3\n189#1:238,3\n197#1:241,3\n207#1:244\n207#1:245,3\n217#1:248\n217#1:249,3\n227#1:252\n227#1:253,3\n*E\n"})
public final class JsonElementBuildersKt {
    public static final boolean add(@NotNull JsonArrayBuilder jsonArrayBuilder, @Nullable Boolean bool) {
        Intrinsics.checkNotNullParameter(jsonArrayBuilder, "<this>");
        jsonArrayBuilder.add(JsonElementKt.JsonPrimitive(bool));
        return true;
    }

    @ExperimentalSerializationApi
    @JvmName(name = "addAllBooleans")
    public static final boolean addAllBooleans(@NotNull JsonArrayBuilder jsonArrayBuilder, @NotNull Collection<Boolean> values) {
        Intrinsics.checkNotNullParameter(jsonArrayBuilder, "<this>");
        Intrinsics.checkNotNullParameter(values, "values");
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(values, 10));
        Iterator<T> it = values.iterator();
        while (it.hasNext()) {
            arrayList.add(JsonElementKt.JsonPrimitive((Boolean) it.next()));
        }
        return jsonArrayBuilder.addAll(arrayList);
    }

    @ExperimentalSerializationApi
    @JvmName(name = "addAllNumbers")
    public static final boolean addAllNumbers(@NotNull JsonArrayBuilder jsonArrayBuilder, @NotNull Collection<? extends Number> values) {
        Intrinsics.checkNotNullParameter(jsonArrayBuilder, "<this>");
        Intrinsics.checkNotNullParameter(values, "values");
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(values, 10));
        Iterator<T> it = values.iterator();
        while (it.hasNext()) {
            arrayList.add(JsonElementKt.JsonPrimitive((Number) it.next()));
        }
        return jsonArrayBuilder.addAll(arrayList);
    }

    @ExperimentalSerializationApi
    @JvmName(name = "addAllStrings")
    public static final boolean addAllStrings(@NotNull JsonArrayBuilder jsonArrayBuilder, @NotNull Collection<String> values) {
        Intrinsics.checkNotNullParameter(jsonArrayBuilder, "<this>");
        Intrinsics.checkNotNullParameter(values, "values");
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(values, 10));
        Iterator<T> it = values.iterator();
        while (it.hasNext()) {
            arrayList.add(JsonElementKt.JsonPrimitive((String) it.next()));
        }
        return jsonArrayBuilder.addAll(arrayList);
    }

    public static final boolean addJsonArray(@NotNull JsonArrayBuilder jsonArrayBuilder, @NotNull Function1<? super JsonArrayBuilder, Unit> builderAction) {
        Intrinsics.checkNotNullParameter(jsonArrayBuilder, "<this>");
        Intrinsics.checkNotNullParameter(builderAction, "builderAction");
        JsonArrayBuilder jsonArrayBuilder2 = new JsonArrayBuilder();
        builderAction.invoke(jsonArrayBuilder2);
        jsonArrayBuilder.add(jsonArrayBuilder2.build());
        return true;
    }

    public static final boolean addJsonObject(@NotNull JsonArrayBuilder jsonArrayBuilder, @NotNull Function1<? super JsonObjectBuilder, Unit> builderAction) {
        Intrinsics.checkNotNullParameter(jsonArrayBuilder, "<this>");
        Intrinsics.checkNotNullParameter(builderAction, "builderAction");
        JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
        builderAction.invoke(jsonObjectBuilder);
        jsonArrayBuilder.add(jsonObjectBuilder.build());
        return true;
    }

    @NotNull
    public static final JsonArray buildJsonArray(@NotNull Function1<? super JsonArrayBuilder, Unit> builderAction) {
        Intrinsics.checkNotNullParameter(builderAction, "builderAction");
        JsonArrayBuilder jsonArrayBuilder = new JsonArrayBuilder();
        builderAction.invoke(jsonArrayBuilder);
        return jsonArrayBuilder.build();
    }

    @NotNull
    public static final JsonObject buildJsonObject(@NotNull Function1<? super JsonObjectBuilder, Unit> builderAction) {
        Intrinsics.checkNotNullParameter(builderAction, "builderAction");
        JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
        builderAction.invoke(jsonObjectBuilder);
        return jsonObjectBuilder.build();
    }

    @Nullable
    public static final JsonElement put(@NotNull JsonObjectBuilder jsonObjectBuilder, @NotNull String key, @Nullable Boolean bool) {
        Intrinsics.checkNotNullParameter(jsonObjectBuilder, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        return jsonObjectBuilder.put(key, JsonElementKt.JsonPrimitive(bool));
    }

    @Nullable
    public static final JsonElement putJsonArray(@NotNull JsonObjectBuilder jsonObjectBuilder, @NotNull String key, @NotNull Function1<? super JsonArrayBuilder, Unit> builderAction) {
        Intrinsics.checkNotNullParameter(jsonObjectBuilder, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(builderAction, "builderAction");
        JsonArrayBuilder jsonArrayBuilder = new JsonArrayBuilder();
        builderAction.invoke(jsonArrayBuilder);
        return jsonObjectBuilder.put(key, jsonArrayBuilder.build());
    }

    @Nullable
    public static final JsonElement putJsonObject(@NotNull JsonObjectBuilder jsonObjectBuilder, @NotNull String key, @NotNull Function1<? super JsonObjectBuilder, Unit> builderAction) {
        Intrinsics.checkNotNullParameter(jsonObjectBuilder, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(builderAction, "builderAction");
        JsonObjectBuilder jsonObjectBuilder2 = new JsonObjectBuilder();
        builderAction.invoke(jsonObjectBuilder2);
        return jsonObjectBuilder.put(key, jsonObjectBuilder2.build());
    }

    public static final boolean add(@NotNull JsonArrayBuilder jsonArrayBuilder, @Nullable Number number) {
        Intrinsics.checkNotNullParameter(jsonArrayBuilder, "<this>");
        jsonArrayBuilder.add(JsonElementKt.JsonPrimitive(number));
        return true;
    }

    @Nullable
    public static final JsonElement put(@NotNull JsonObjectBuilder jsonObjectBuilder, @NotNull String key, @Nullable Number number) {
        Intrinsics.checkNotNullParameter(jsonObjectBuilder, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        return jsonObjectBuilder.put(key, JsonElementKt.JsonPrimitive(number));
    }

    public static final boolean add(@NotNull JsonArrayBuilder jsonArrayBuilder, @Nullable String str) {
        Intrinsics.checkNotNullParameter(jsonArrayBuilder, "<this>");
        jsonArrayBuilder.add(JsonElementKt.JsonPrimitive(str));
        return true;
    }

    @Nullable
    public static final JsonElement put(@NotNull JsonObjectBuilder jsonObjectBuilder, @NotNull String key, @Nullable String str) {
        Intrinsics.checkNotNullParameter(jsonObjectBuilder, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        return jsonObjectBuilder.put(key, JsonElementKt.JsonPrimitive(str));
    }

    @ExperimentalSerializationApi
    public static final boolean add(@NotNull JsonArrayBuilder jsonArrayBuilder, @Nullable Void r1) {
        Intrinsics.checkNotNullParameter(jsonArrayBuilder, "<this>");
        jsonArrayBuilder.add(JsonNull.INSTANCE);
        return true;
    }

    @ExperimentalSerializationApi
    @Nullable
    public static final JsonElement put(@NotNull JsonObjectBuilder jsonObjectBuilder, @NotNull String key, @Nullable Void r2) {
        Intrinsics.checkNotNullParameter(jsonObjectBuilder, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        return jsonObjectBuilder.put(key, JsonNull.INSTANCE);
    }
}
