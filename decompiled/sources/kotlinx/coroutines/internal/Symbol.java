package kotlinx.coroutines.internal;

import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class Symbol {

    @JvmField
    @NotNull
    public final String symbol;

    public Symbol(@NotNull String str) {
        this.symbol = str;
    }

    @NotNull
    public String toString() {
        return "<" + this.symbol + '>';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <T> T unbox(@Nullable Object obj) {
        if (obj == this) {
            return null;
        }
        return obj;
    }
}
