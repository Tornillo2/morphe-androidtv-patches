package kotlinx.coroutines.debug.internal;

import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class ConcurrentWeakMapKt {
    public static final int MAGIC = -1640531527;
    public static final int MIN_CAPACITY = 16;

    @NotNull
    public static final Symbol REHASH = new Symbol("REHASH");

    @NotNull
    public static final Marked MARKED_NULL = new Marked(null);

    @NotNull
    public static final Marked MARKED_TRUE = new Marked(Boolean.TRUE);

    public static final /* synthetic */ Void access$noImpl() {
        noImpl();
        throw null;
    }

    public static final Marked mark(Object obj) {
        return obj == null ? MARKED_NULL : obj.equals(Boolean.TRUE) ? MARKED_TRUE : new Marked(obj);
    }

    public static final Void noImpl() {
        throw new UnsupportedOperationException("not implemented");
    }
}
