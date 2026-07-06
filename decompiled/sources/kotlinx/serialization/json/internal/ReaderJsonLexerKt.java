package kotlinx.serialization.json.internal;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.Json;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class ReaderJsonLexerKt {
    public static final int BATCH_SIZE = 16384;
    public static final int DEFAULT_THRESHOLD = 128;

    @NotNull
    public static final ReaderJsonLexer ReaderJsonLexer(@NotNull Json json, @NotNull InternalJsonReader reader, @NotNull char[] buffer) {
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(reader, "reader");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        return !json.configuration.allowComments ? new ReaderJsonLexer(reader, buffer) : new ReaderJsonLexerWithComments(reader, buffer);
    }

    public static ReaderJsonLexer ReaderJsonLexer$default(Json json, InternalJsonReader internalJsonReader, char[] cArr, int i, Object obj) {
        if ((i & 4) != 0) {
            cArr = CharArrayPoolBatchSize.INSTANCE.take(16384);
        }
        return ReaderJsonLexer(json, internalJsonReader, cArr);
    }
}
