package kotlinx.serialization.json.internal;

import java.io.InputStream;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class JavaStreamSerialReader implements InternalJsonReader {

    @NotNull
    public final CharsetReader reader;

    public JavaStreamSerialReader(@NotNull InputStream stream) {
        Intrinsics.checkNotNullParameter(stream, "stream");
        this.reader = new CharsetReader(stream, Charsets.UTF_8);
    }

    @Override // kotlinx.serialization.json.internal.InternalJsonReader
    public int read(@NotNull char[] buffer, int i, int i2) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        return this.reader.read(buffer, i, i2);
    }

    public final void release() {
        this.reader.release();
    }
}
