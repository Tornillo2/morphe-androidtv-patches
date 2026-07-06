package kotlin.io.path;

import java.nio.file.Path;
import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class PathNode {

    @Nullable
    public Iterator<PathNode> contentIterator;

    @Nullable
    public final Object key;

    @Nullable
    public final PathNode parent;

    @NotNull
    public final Path path;

    public PathNode(@NotNull Path path, @Nullable Object obj, @Nullable PathNode pathNode) {
        Intrinsics.checkNotNullParameter(path, "path");
        this.path = path;
        this.key = obj;
        this.parent = pathNode;
    }

    @Nullable
    public final Iterator<PathNode> getContentIterator() {
        return this.contentIterator;
    }

    @Nullable
    public final Object getKey() {
        return this.key;
    }

    @Nullable
    public final PathNode getParent() {
        return this.parent;
    }

    @NotNull
    public final Path getPath() {
        return this.path;
    }

    public final void setContentIterator(@Nullable Iterator<PathNode> it) {
        this.contentIterator = it;
    }
}
