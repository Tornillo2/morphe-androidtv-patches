package kotlinx.serialization.json.internal;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.Json;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class ComposersKt {
    @NotNull
    public static final Composer Composer(@NotNull InternalJsonWriter sb, @NotNull Json json) {
        Intrinsics.checkNotNullParameter(sb, "sb");
        Intrinsics.checkNotNullParameter(json, "json");
        return json.configuration.prettyPrint ? new ComposerWithPrettyPrint(sb, json) : new Composer(sb);
    }
}
