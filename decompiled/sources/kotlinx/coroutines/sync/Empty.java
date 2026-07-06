package kotlinx.coroutines.sync;

import kotlin.jvm.JvmField;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class Empty {

    @JvmField
    @NotNull
    public final Object locked;

    public Empty(@NotNull Object obj) {
        this.locked = obj;
    }

    @NotNull
    public String toString() {
        return "Empty[" + this.locked + AbstractJsonLexerKt.END_LIST;
    }
}
