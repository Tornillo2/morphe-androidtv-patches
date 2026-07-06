package kotlin.io.path;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class PathRelativizer {

    @NotNull
    public static final PathRelativizer INSTANCE = new PathRelativizer();
    public static final Path emptyPath = Paths.get("", new String[0]);
    public static final Path parentPath = Paths.get("..", new String[0]);

    /* JADX WARN: Removed duplicated region for block: B:16:0x0056  */
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.nio.file.Path tryRelativeTo(@org.jetbrains.annotations.NotNull java.nio.file.Path r7, @org.jetbrains.annotations.NotNull java.nio.file.Path r8) {
        /*
            r6 = this;
            java.lang.String r0 = "path"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.lang.String r0 = "base"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            java.nio.file.Path r8 = r8.normalize()
            java.nio.file.Path r7 = r7.normalize()
            java.nio.file.Path r0 = r8.relativize(r7)
            int r1 = r8.getNameCount()
            int r2 = r7.getNameCount()
            int r1 = java.lang.Math.min(r1, r2)
            r2 = 0
            r3 = 0
        L24:
            if (r3 >= r1) goto L47
            java.nio.file.Path r4 = r8.getName(r3)
            java.nio.file.Path r5 = kotlin.io.path.PathRelativizer.parentPath
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r5)
            if (r4 == 0) goto L47
            java.nio.file.Path r4 = r7.getName(r3)
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r5)
            if (r4 == 0) goto L3f
            int r3 = r3 + 1
            goto L24
        L3f:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r8 = "Unable to compute relative path"
            r7.<init>(r8)
            throw r7
        L47:
            boolean r1 = r7.equals(r8)
            if (r1 != 0) goto L56
            java.nio.file.Path r1 = kotlin.io.path.PathRelativizer.emptyPath
            boolean r8 = r8.equals(r1)
            if (r8 == 0) goto L56
            goto L8b
        L56:
            java.lang.String r7 = r0.toString()
            java.nio.file.FileSystem r8 = r0.getFileSystem()
            java.lang.String r8 = r8.getSeparator()
            java.lang.String r1 = "getSeparator(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r1)
            r1 = 2
            r3 = 0
            boolean r8 = kotlin.text.StringsKt__StringsJVMKt.endsWith$default(r7, r8, r2, r1, r3)
            if (r8 == 0) goto L8a
            java.nio.file.FileSystem r8 = r0.getFileSystem()
            java.nio.file.FileSystem r0 = r0.getFileSystem()
            java.lang.String r0 = r0.getSeparator()
            int r0 = r0.length()
            java.lang.String r7 = kotlin.text.StringsKt___StringsKt.dropLast(r7, r0)
            java.lang.String[] r0 = new java.lang.String[r2]
            java.nio.file.Path r7 = r8.getPath(r7, r0)
            goto L8b
        L8a:
            r7 = r0
        L8b:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.path.PathRelativizer.tryRelativeTo(java.nio.file.Path, java.nio.file.Path):java.nio.file.Path");
    }
}
