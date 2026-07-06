package kotlinx.serialization.json.internal;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.Json;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class StringJsonLexerKt {
    @NotNull
    public static final StringJsonLexer StringJsonLexer(@NotNull Json json, @NotNull String source) {
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(source, "source");
        return !json.configuration.allowComments ? new StringJsonLexer(source) : new StringJsonLexerWithComments(source);
    }
}
