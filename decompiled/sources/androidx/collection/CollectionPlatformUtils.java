package androidx.collection;

import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class CollectionPlatformUtils {

    @NotNull
    public static final CollectionPlatformUtils INSTANCE = new CollectionPlatformUtils();

    @NotNull
    public final IndexOutOfBoundsException createIndexOutOfBoundsException$collection() {
        return new ArrayIndexOutOfBoundsException();
    }
}
