package kotlin.io.path;

import com.google.common.io.MoreFiles$$ExternalSyntheticApiModelOutline5;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public /* synthetic */ class PathsKt__PathRecursiveFunctionsKt$copyToRecursively$5$2 extends FunctionReferenceImpl implements Function2<Path, BasicFileAttributes, FileVisitResult> {
    public final /* synthetic */ Function3<CopyActionContext, Path, Path, CopyActionResult> $copyAction;
    public final /* synthetic */ Path $normalizedTarget;
    public final /* synthetic */ Function3<Path, Path, Exception, OnErrorResult> $onError;
    public final /* synthetic */ ArrayList<Path> $stack;
    public final /* synthetic */ Path $target;
    public final /* synthetic */ Path $this_copyToRecursively;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public PathsKt__PathRecursiveFunctionsKt$copyToRecursively$5$2(ArrayList<Path> arrayList, Function3<? super CopyActionContext, ? super Path, ? super Path, ? extends CopyActionResult> function3, Path path, Path path2, Path path3, Function3<? super Path, ? super Path, ? super Exception, ? extends OnErrorResult> function32) {
        super(2, Intrinsics.Kotlin.class, "copy", "copyToRecursively$copy$PathsKt__PathRecursiveFunctionsKt(Ljava/util/ArrayList;Lkotlin/jvm/functions/Function3;Ljava/nio/file/Path;Ljava/nio/file/Path;Ljava/nio/file/Path;Lkotlin/jvm/functions/Function3;Ljava/nio/file/Path;Ljava/nio/file/attribute/BasicFileAttributes;)Ljava/nio/file/FileVisitResult;", 0);
        this.$stack = arrayList;
        this.$copyAction = function3;
        this.$this_copyToRecursively = path;
        this.$target = path2;
        this.$normalizedTarget = path3;
        this.$onError = function32;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ FileVisitResult invoke(Path path, BasicFileAttributes basicFileAttributes) {
        return invoke2(MoreFiles$$ExternalSyntheticApiModelOutline5.m(path), PathsKt__PathRecursiveFunctionsKt$copyToRecursively$5$2$$ExternalSyntheticApiModelOutline0.m(basicFileAttributes));
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final FileVisitResult invoke2(Path p0, BasicFileAttributes p1) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        return PathsKt__PathRecursiveFunctionsKt.copyToRecursively$copy$PathsKt__PathRecursiveFunctionsKt(this.$stack, this.$copyAction, this.$this_copyToRecursively, this.$target, this.$normalizedTarget, this.$onError, p0, p1);
    }
}
