package kotlin;

import kotlin.internal.InlineOnly;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class HashCodeKt {
    @SinceKotlin(version = "1.3")
    @InlineOnly
    public static final int hashCode(Object obj) {
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }
}
