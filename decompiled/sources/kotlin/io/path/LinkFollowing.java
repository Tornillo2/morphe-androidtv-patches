package kotlin.io.path;

import java.nio.file.FileVisitOption;
import java.nio.file.LinkOption;
import java.util.Set;
import kotlin.collections.EmptySet;
import kotlin.collections.SetsKt__SetsJVMKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class LinkFollowing {

    @NotNull
    public static final LinkFollowing INSTANCE = new LinkFollowing();

    @NotNull
    public static final LinkOption[] nofollowLinkOption = {LinkOption.NOFOLLOW_LINKS};

    @NotNull
    public static final LinkOption[] followLinkOption = new LinkOption[0];

    @NotNull
    public static final Set<FileVisitOption> nofollowVisitOption = EmptySet.INSTANCE;

    @NotNull
    public static final Set<FileVisitOption> followVisitOption = SetsKt__SetsJVMKt.setOf(FileVisitOption.FOLLOW_LINKS);

    @NotNull
    public final LinkOption[] toLinkOptions(boolean z) {
        return z ? followLinkOption : nofollowLinkOption;
    }

    @NotNull
    public final Set<FileVisitOption> toVisitOptions(boolean z) {
        return z ? followVisitOption : nofollowVisitOption;
    }
}
