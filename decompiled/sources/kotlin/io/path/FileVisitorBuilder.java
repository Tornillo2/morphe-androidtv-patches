package kotlin.io.path;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.jvm.functions.Function2;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "2.1")
@WasExperimental(markerClass = {ExperimentalPathApi.class})
public interface FileVisitorBuilder {
    void onPostVisitDirectory(@NotNull Function2<? super Path, ? super IOException, ? extends FileVisitResult> function2);

    void onPreVisitDirectory(@NotNull Function2<? super Path, ? super BasicFileAttributes, ? extends FileVisitResult> function2);

    void onVisitFile(@NotNull Function2<? super Path, ? super BasicFileAttributes, ? extends FileVisitResult> function2);

    void onVisitFileFailed(@NotNull Function2<? super Path, ? super IOException, ? extends FileVisitResult> function2);
}
