package com.amazon.ignitionshared.pear;

import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.json.JsonContentPolymorphicSerializer;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class WatchStateSerializer extends JsonContentPolymorphicSerializer<WatchState> {

    @NotNull
    public static final WatchStateSerializer INSTANCE = new WatchStateSerializer();

    public WatchStateSerializer() {
        super(Reflection.getOrCreateKotlinClass(WatchState.class));
    }

    @Override // kotlinx.serialization.json.JsonContentPolymorphicSerializer
    @NotNull
    public DeserializationStrategy<WatchState> selectDeserializer(@NotNull JsonElement element) {
        Intrinsics.checkNotNullParameter(element, "element");
        return (JsonElementKt.getJsonObject(element).containsKey((Object) "episodeNumber") && JsonElementKt.getJsonObject(element).containsKey((Object) "bookmarkTimeSeconds")) ? WatchStateEpisodeStarted.Companion.serializer() : JsonElementKt.getJsonObject(element).containsKey((Object) "episodeNumber") ? WatchStateEpisode.Companion.serializer() : (JsonElementKt.getJsonObject(element).containsKey((Object) "episodeNumber") || !JsonElementKt.getJsonObject(element).containsKey((Object) "bookmarkTimeSeconds")) ? WatchStateMovie.Companion.serializer() : WatchStateMovieStarted.Companion.serializer();
    }
}
