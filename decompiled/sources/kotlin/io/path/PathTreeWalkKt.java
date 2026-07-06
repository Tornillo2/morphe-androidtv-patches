package kotlin.io.path;

import com.google.common.io.MoreFiles$PathByteSource$$ExternalSyntheticApiModelOutline0;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class PathTreeWalkKt {
    public static final boolean createsCycle(PathNode pathNode) {
        Object obj;
        for (PathNode pathNode2 = pathNode.parent; pathNode2 != null; pathNode2 = pathNode2.parent) {
            Object obj2 = pathNode2.key;
            if (obj2 == null || (obj = pathNode.key) == null) {
                try {
                    if (Files.isSameFile(pathNode2.path, pathNode.path)) {
                        return true;
                    }
                } catch (IOException | SecurityException unused) {
                    continue;
                }
            } else if (Intrinsics.areEqual(obj2, obj)) {
                return true;
            }
        }
        return false;
    }

    public static final Object keyOf(Path path, LinkOption[] linkOptionArr) {
        try {
            LinkOption[] linkOptionArr2 = (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length);
            BasicFileAttributes attributes = Files.readAttributes(path, (Class<BasicFileAttributes>) MoreFiles$PathByteSource$$ExternalSyntheticApiModelOutline0.m(), (LinkOption[]) Arrays.copyOf(linkOptionArr2, linkOptionArr2.length));
            Intrinsics.checkNotNullExpressionValue(attributes, "readAttributes(...)");
            return attributes.fileKey();
        } catch (Throwable unused) {
            return null;
        }
    }
}
