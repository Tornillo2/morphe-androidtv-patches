package kotlin.io;

import java.io.File;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class FilePathComponents {

    @NotNull
    public final File root;

    @NotNull
    public final List<File> segments;

    /* JADX WARN: Multi-variable type inference failed */
    public FilePathComponents(@NotNull File root, @NotNull List<? extends File> segments) {
        Intrinsics.checkNotNullParameter(root, "root");
        Intrinsics.checkNotNullParameter(segments, "segments");
        this.root = root;
        this.segments = segments;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FilePathComponents copy$kotlin_stdlib$default(FilePathComponents filePathComponents, File file, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            file = filePathComponents.root;
        }
        if ((i & 2) != 0) {
            list = filePathComponents.segments;
        }
        return filePathComponents.copy$kotlin_stdlib(file, list);
    }

    @NotNull
    public final File component1() {
        return this.root;
    }

    @NotNull
    public final List<File> component2() {
        return this.segments;
    }

    @NotNull
    public final FilePathComponents copy$kotlin_stdlib(@NotNull File root, @NotNull List<? extends File> segments) {
        Intrinsics.checkNotNullParameter(root, "root");
        Intrinsics.checkNotNullParameter(segments, "segments");
        return new FilePathComponents(root, segments);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FilePathComponents)) {
            return false;
        }
        FilePathComponents filePathComponents = (FilePathComponents) obj;
        return Intrinsics.areEqual(this.root, filePathComponents.root) && Intrinsics.areEqual(this.segments, filePathComponents.segments);
    }

    @NotNull
    public final File getRoot() {
        return this.root;
    }

    @NotNull
    public final String getRootName() {
        String path = this.root.getPath();
        Intrinsics.checkNotNullExpressionValue(path, "getPath(...)");
        return path;
    }

    @NotNull
    public final List<File> getSegments() {
        return this.segments;
    }

    public final int getSize() {
        return this.segments.size();
    }

    public int hashCode() {
        return this.segments.hashCode() + (this.root.hashCode() * 31);
    }

    public final boolean isRooted() {
        String path = this.root.getPath();
        Intrinsics.checkNotNullExpressionValue(path, "getPath(...)");
        return path.length() > 0;
    }

    @NotNull
    public final File subPath(int i, int i2) {
        if (i < 0 || i > i2 || i2 > this.segments.size()) {
            throw new IllegalArgumentException();
        }
        List<File> listSubList = this.segments.subList(i, i2);
        String separator = File.separator;
        Intrinsics.checkNotNullExpressionValue(separator, "separator");
        return new File(CollectionsKt___CollectionsKt.joinToString$default(listSubList, separator, null, null, 0, null, null, 62, null));
    }

    @NotNull
    public String toString() {
        return "FilePathComponents(root=" + this.root + ", segments=" + this.segments + ')';
    }
}
