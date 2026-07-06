package kotlin.io.path;

import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline0;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class FileVisitorBuilderImpl implements FileVisitorBuilder {
    public boolean isBuilt;

    @Nullable
    public Function2<? super Path, ? super IOException, ? extends FileVisitResult> onPostVisitDirectory;

    @Nullable
    public Function2<? super Path, ? super BasicFileAttributes, ? extends FileVisitResult> onPreVisitDirectory;

    @Nullable
    public Function2<? super Path, ? super BasicFileAttributes, ? extends FileVisitResult> onVisitFile;

    @Nullable
    public Function2<? super Path, ? super IOException, ? extends FileVisitResult> onVisitFileFailed;

    @NotNull
    public final FileVisitor<Path> build() {
        checkIsNotBuilt();
        this.isBuilt = true;
        return new FileVisitorImpl(this.onPreVisitDirectory, this.onVisitFile, this.onVisitFileFailed, this.onPostVisitDirectory);
    }

    public final void checkIsNotBuilt() {
        if (this.isBuilt) {
            throw new IllegalStateException("This builder was already built");
        }
    }

    public final void checkNotDefined(Object obj, String str) {
        if (obj != null) {
            throw new IllegalStateException(AbstractResolvableFuture$$ExternalSyntheticOutline0.m(str, " was already defined"));
        }
    }

    @Override // kotlin.io.path.FileVisitorBuilder
    public void onPostVisitDirectory(@NotNull Function2<? super Path, ? super IOException, ? extends FileVisitResult> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        checkIsNotBuilt();
        checkNotDefined(this.onPostVisitDirectory, "onPostVisitDirectory");
        this.onPostVisitDirectory = function;
    }

    @Override // kotlin.io.path.FileVisitorBuilder
    public void onPreVisitDirectory(@NotNull Function2<? super Path, ? super BasicFileAttributes, ? extends FileVisitResult> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        checkIsNotBuilt();
        checkNotDefined(this.onPreVisitDirectory, "onPreVisitDirectory");
        this.onPreVisitDirectory = function;
    }

    @Override // kotlin.io.path.FileVisitorBuilder
    public void onVisitFile(@NotNull Function2<? super Path, ? super BasicFileAttributes, ? extends FileVisitResult> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        checkIsNotBuilt();
        checkNotDefined(this.onVisitFile, "onVisitFile");
        this.onVisitFile = function;
    }

    @Override // kotlin.io.path.FileVisitorBuilder
    public void onVisitFileFailed(@NotNull Function2<? super Path, ? super IOException, ? extends FileVisitResult> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        checkIsNotBuilt();
        checkNotDefined(this.onVisitFileFailed, "onVisitFileFailed");
        this.onVisitFileFailed = function;
    }
}
