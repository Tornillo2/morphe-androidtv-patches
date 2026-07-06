package kotlin.io.path;

import com.amazon.avod.mpb.media.ExternalFourCCMapper;
import com.google.common.io.MoreFiles$$ExternalSyntheticApiModelOutline0;
import com.google.common.io.MoreFiles$$ExternalSyntheticApiModelOutline1;
import com.google.common.io.MoreFiles$$ExternalSyntheticApiModelOutline2;
import com.google.common.io.MoreFiles$$ExternalSyntheticApiModelOutline3;
import com.google.common.io.MoreFiles$$ExternalSyntheticApiModelOutline5;
import com.google.common.io.MoreFiles$$ExternalSyntheticApiModelOutline6;
import com.google.common.io.MoreFiles$$ExternalSyntheticApiModelOutline7;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.CopyOption;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystemException;
import java.nio.file.FileSystemLoopException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.SecureDirectoryStream;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.ExceptionsKt__ExceptionsKt;
import kotlin.NoWhenBranchMatchedException;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.SpreadBuilder;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SourceDebugExtension({"SMAP\nPathRecursiveFunctions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PathRecursiveFunctions.kt\nkotlin/io/path/PathsKt__PathRecursiveFunctionsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,530:1\n376#1,2:534\n384#1:536\n384#1:537\n378#1,4:538\n376#1,2:542\n384#1:544\n378#1,4:545\n384#1:549\n376#1,6:550\n376#1,2:556\n384#1:558\n378#1,4:559\n1#2:531\n1869#3,2:532\n*S KotlinDebug\n*F\n+ 1 PathRecursiveFunctions.kt\nkotlin/io/path/PathsKt__PathRecursiveFunctionsKt\n*L\n392#1:534,2\n407#1:536\n410#1:537\n392#1:538,4\n418#1:542,2\n419#1:544\n418#1:545,4\n430#1:549\n438#1:550,6\n461#1:556,2\n462#1:558\n461#1:559,4\n314#1:532,2\n*E\n"})
public class PathsKt__PathRecursiveFunctionsKt extends PathsKt__PathReadWriteKt {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[CopyActionResult.values().length];
            try {
                iArr[CopyActionResult.CONTINUE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CopyActionResult.TERMINATE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[CopyActionResult.SKIP_SUBTREE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[OnErrorResult.values().length];
            try {
                iArr2[OnErrorResult.TERMINATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[OnErrorResult.SKIP_SUBTREE.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* JADX INFO: renamed from: kotlin.io.path.PathsKt__PathRecursiveFunctionsKt$copyToRecursively$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass1 implements Function3 {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) throws Exception {
            invoke(MoreFiles$$ExternalSyntheticApiModelOutline5.m(obj), MoreFiles$$ExternalSyntheticApiModelOutline5.m(obj2), (Exception) obj3);
            throw null;
        }

        public final Void invoke(Path path, Path path2, Exception exception) throws Exception {
            Intrinsics.checkNotNullParameter(path, "<unused var>");
            Intrinsics.checkNotNullParameter(path2, "<unused var>");
            Intrinsics.checkNotNullParameter(exception, "exception");
            throw exception;
        }
    }

    /* JADX INFO: renamed from: kotlin.io.path.PathsKt__PathRecursiveFunctionsKt$copyToRecursively$3, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass3 implements Function3 {
        public static final AnonymousClass3 INSTANCE = new AnonymousClass3();

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) throws Exception {
            invoke(MoreFiles$$ExternalSyntheticApiModelOutline5.m(obj), MoreFiles$$ExternalSyntheticApiModelOutline5.m(obj2), (Exception) obj3);
            throw null;
        }

        public final Void invoke(Path path, Path path2, Exception exception) throws Exception {
            Intrinsics.checkNotNullParameter(path, "<unused var>");
            Intrinsics.checkNotNullParameter(path2, "<unused var>");
            Intrinsics.checkNotNullParameter(exception, "exception");
            throw exception;
        }
    }

    public static final void checkFileName(@NotNull Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        String name = PathsKt__PathUtilsKt.getName(path);
        int iHashCode = name.hashCode();
        if (iHashCode != 46) {
            if (iHashCode != 1518) {
                if (iHashCode != 45679) {
                    if (iHashCode != 45724) {
                        if (iHashCode != 1472) {
                            if (iHashCode != 1473 || !name.equals("./")) {
                                return;
                            }
                        } else if (!name.equals("..")) {
                            return;
                        }
                    } else if (!name.equals("..\\")) {
                        return;
                    }
                } else if (!name.equals("../")) {
                    return;
                }
            } else if (!name.equals(".\\")) {
                return;
            }
        } else if (!name.equals(ExternalFourCCMapper.CODEC_NAME_SPLITTER)) {
            return;
        }
        throw new IllegalFileNameException(path);
    }

    public static final void checkNotSameAs$PathsKt__PathRecursiveFunctionsKt(Path path, Path path2) throws FileSystemLoopException {
        if (Files.isSymbolicLink(path) || !Files.isSameFile(path, path2)) {
            return;
        }
        PathTreeWalk$$ExternalSyntheticApiModelOutline1.m();
        throw PathTreeWalk$$ExternalSyntheticApiModelOutline0.m(path.toString());
    }

    public static final void collectIfThrows$PathsKt__PathRecursiveFunctionsKt(ExceptionsCollector exceptionsCollector, Function0<Unit> function0) {
        try {
            function0.invoke();
        } catch (Exception e) {
            exceptionsCollector.collect(e);
        }
    }

    @SinceKotlin(version = "1.8")
    @ExperimentalPathApi
    @NotNull
    public static final Path copyToRecursively(@NotNull Path path, @NotNull Path target, @NotNull Function3<? super Path, ? super Path, ? super Exception, ? extends OnErrorResult> onError, final boolean z, boolean z2) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(target, "target");
        Intrinsics.checkNotNullParameter(onError, "onError");
        if (z2) {
            copyToRecursively(path, target, onError, z, (Function3<? super CopyActionContext, ? super Path, ? super Path, ? extends CopyActionResult>) new Function3() { // from class: kotlin.io.path.PathsKt__PathRecursiveFunctionsKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return PathsKt__PathRecursiveFunctionsKt.copyToRecursively$lambda$0$PathsKt__PathRecursiveFunctionsKt(z, (CopyActionContext) obj, (Path) obj2, (Path) obj3);
                }
            });
            return target;
        }
        copyToRecursively$default(path, target, onError, z, (Function3) null, 8, (Object) null);
        return target;
    }

    public static final FileVisitResult copyToRecursively$copy$PathsKt__PathRecursiveFunctionsKt(ArrayList<Path> arrayList, Function3<? super CopyActionContext, ? super Path, ? super Path, ? extends CopyActionResult> function3, Path path, Path path2, Path path3, Function3<? super Path, ? super Path, ? super Exception, ? extends OnErrorResult> function32, Path path4, BasicFileAttributes basicFileAttributes) {
        try {
            if (!arrayList.isEmpty()) {
                checkFileName(path4);
                Object objLast = CollectionsKt___CollectionsKt.last((List<? extends Object>) arrayList);
                Intrinsics.checkNotNullExpressionValue(objLast, "last(...)");
                checkNotSameAs$PathsKt__PathRecursiveFunctionsKt(path4, MoreFiles$$ExternalSyntheticApiModelOutline5.m(objLast));
            }
            return toFileVisitResult$PathsKt__PathRecursiveFunctionsKt(function3.invoke(DefaultCopyActionContext.INSTANCE, path4, copyToRecursively$destination$PathsKt__PathRecursiveFunctionsKt(path, path2, path3, path4)));
        } catch (Exception e) {
            return copyToRecursively$error$PathsKt__PathRecursiveFunctionsKt(function32, path, path2, path3, path4, e);
        }
    }

    public static /* synthetic */ Path copyToRecursively$default(Path path, Path path2, Function3 function3, boolean z, boolean z2, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            function3 = AnonymousClass1.INSTANCE;
        }
        copyToRecursively(path, path2, (Function3<? super Path, ? super Path, ? super Exception, ? extends OnErrorResult>) function3, z, z2);
        return path2;
    }

    public static final Path copyToRecursively$destination$PathsKt__PathRecursiveFunctionsKt(Path path, Path path2, Path path3, Path path4) throws IllegalFileNameException {
        Path pathResolve = path2.resolve(PathsKt__PathUtilsKt.relativeTo(path4, path).toString());
        if (pathResolve.normalize().startsWith(path3)) {
            return pathResolve;
        }
        throw new IllegalFileNameException(path4, pathResolve, "Copying files to outside the specified target directory is prohibited. The directory being recursively copied might contain an entry with an illegal name.");
    }

    public static final FileVisitResult copyToRecursively$error$PathsKt__PathRecursiveFunctionsKt(Function3<? super Path, ? super Path, ? super Exception, ? extends OnErrorResult> function3, Path path, Path path2, Path path3, Path path4, Exception exc) {
        return toFileVisitResult$PathsKt__PathRecursiveFunctionsKt(function3.invoke(path4, copyToRecursively$destination$PathsKt__PathRecursiveFunctionsKt(path, path2, path3, path4), exc));
    }

    public static final CopyActionResult copyToRecursively$lambda$0$PathsKt__PathRecursiveFunctionsKt(boolean z, CopyActionContext copyToRecursively, Path src, Path dst) throws IllegalAccessException, IOException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(copyToRecursively, "$this$copyToRecursively");
        Intrinsics.checkNotNullParameter(src, "src");
        Intrinsics.checkNotNullParameter(dst, "dst");
        LinkOption[] linkOptions = LinkFollowing.INSTANCE.toLinkOptions(z);
        boolean zIsDirectory = Files.isDirectory(dst, (LinkOption[]) Arrays.copyOf(new LinkOption[]{LinkOption.NOFOLLOW_LINKS}, 1));
        LinkOption[] linkOptionArr = (LinkOption[]) Arrays.copyOf(linkOptions, linkOptions.length);
        if (!Files.isDirectory(src, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length)) || !zIsDirectory) {
            if (zIsDirectory) {
                deleteRecursively(dst);
            }
            SpreadBuilder spreadBuilder = new SpreadBuilder(2);
            spreadBuilder.addSpread(linkOptions);
            spreadBuilder.add(StandardCopyOption.REPLACE_EXISTING);
            CopyOption[] copyOptionArr = (CopyOption[]) spreadBuilder.list.toArray(new CopyOption[spreadBuilder.list.size()]);
            Intrinsics.checkNotNullExpressionValue(Files.copy(src, dst, (CopyOption[]) Arrays.copyOf(copyOptionArr, copyOptionArr.length)), "copy(...)");
        }
        return CopyActionResult.CONTINUE;
    }

    public static final CopyActionResult copyToRecursively$lambda$1$PathsKt__PathRecursiveFunctionsKt(boolean z, CopyActionContext copyActionContext, Path src, Path dst) {
        Intrinsics.checkNotNullParameter(copyActionContext, "<this>");
        Intrinsics.checkNotNullParameter(src, "src");
        Intrinsics.checkNotNullParameter(dst, "dst");
        return copyActionContext.copyToIgnoringExistingDirectory(src, dst, z);
    }

    public static final Unit copyToRecursively$lambda$6$PathsKt__PathRecursiveFunctionsKt(final ArrayList arrayList, final Function3 function3, final Path path, final Path path2, final Path path3, final Function3 function32, FileVisitorBuilder visitFileTree) {
        Intrinsics.checkNotNullParameter(visitFileTree, "$this$visitFileTree");
        visitFileTree.onPreVisitDirectory(new Function2() { // from class: kotlin.io.path.PathsKt__PathRecursiveFunctionsKt$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return PathsKt__PathRecursiveFunctionsKt.copyToRecursively$lambda$6$lambda$4$PathsKt__PathRecursiveFunctionsKt(arrayList, function3, path, path2, path3, function32, (Path) obj, (BasicFileAttributes) obj2);
            }
        });
        visitFileTree.onVisitFile(new PathsKt__PathRecursiveFunctionsKt$copyToRecursively$5$2(arrayList, function3, path, path2, path3, function32));
        visitFileTree.onVisitFileFailed(new PathsKt__PathRecursiveFunctionsKt$copyToRecursively$5$3(function32, path, path2, path3));
        visitFileTree.onPostVisitDirectory(new Function2() { // from class: kotlin.io.path.PathsKt__PathRecursiveFunctionsKt$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return PathsKt__PathRecursiveFunctionsKt.copyToRecursively$lambda$6$lambda$5$PathsKt__PathRecursiveFunctionsKt(arrayList, function32, path, path2, path3, (Path) obj, (IOException) obj2);
            }
        });
        return Unit.INSTANCE;
    }

    public static final FileVisitResult copyToRecursively$lambda$6$lambda$4$PathsKt__PathRecursiveFunctionsKt(ArrayList arrayList, Function3 function3, Path path, Path path2, Path path3, Function3 function32, Path directory, BasicFileAttributes attributes) {
        Intrinsics.checkNotNullParameter(directory, "directory");
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        FileVisitResult fileVisitResultCopyToRecursively$copy$PathsKt__PathRecursiveFunctionsKt = copyToRecursively$copy$PathsKt__PathRecursiveFunctionsKt(arrayList, function3, path, path2, path3, function32, directory, attributes);
        if (fileVisitResultCopyToRecursively$copy$PathsKt__PathRecursiveFunctionsKt == FileVisitResult.CONTINUE) {
            arrayList.add(directory);
        }
        return fileVisitResultCopyToRecursively$copy$PathsKt__PathRecursiveFunctionsKt;
    }

    public static final FileVisitResult copyToRecursively$lambda$6$lambda$5$PathsKt__PathRecursiveFunctionsKt(ArrayList arrayList, Function3 function3, Path path, Path path2, Path path3, Path directory, IOException iOException) {
        Intrinsics.checkNotNullParameter(directory, "directory");
        CollectionsKt__MutableCollectionsKt.removeLast(arrayList);
        return iOException == null ? FileVisitResult.CONTINUE : copyToRecursively$error$PathsKt__PathRecursiveFunctionsKt(function3, path, path2, path3, directory, iOException);
    }

    @SinceKotlin(version = "1.8")
    @ExperimentalPathApi
    public static final void deleteRecursively(@NotNull Path path) throws IllegalAccessException, IOException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        List<Exception> listDeleteRecursivelyImpl$PathsKt__PathRecursiveFunctionsKt = deleteRecursivelyImpl$PathsKt__PathRecursiveFunctionsKt(path);
        if (listDeleteRecursivelyImpl$PathsKt__PathRecursiveFunctionsKt.isEmpty()) {
            return;
        }
        FileSystemException fileSystemExceptionM = ExceptionsCollector$$ExternalSyntheticApiModelOutline0.m("Failed to delete one or more files. See suppressed exceptions for details.");
        Iterator<T> it = listDeleteRecursivelyImpl$PathsKt__PathRecursiveFunctionsKt.iterator();
        while (it.hasNext()) {
            ExceptionsKt__ExceptionsKt.addSuppressed(fileSystemExceptionM, (Exception) it.next());
        }
        throw fileSystemExceptionM;
    }

    public static final List<Exception> deleteRecursivelyImpl$PathsKt__PathRecursiveFunctionsKt(Path path) throws IOException {
        DirectoryStream<Path> directoryStreamNewDirectoryStream;
        boolean z = false;
        boolean z2 = true;
        ExceptionsCollector exceptionsCollector = new ExceptionsCollector(0, 1, null);
        Path parent = path.getParent();
        if (parent != null) {
            try {
                directoryStreamNewDirectoryStream = Files.newDirectoryStream(parent);
            } catch (Throwable unused) {
                directoryStreamNewDirectoryStream = null;
            }
            if (directoryStreamNewDirectoryStream != null) {
                try {
                    if (MoreFiles$$ExternalSyntheticApiModelOutline6.m(directoryStreamNewDirectoryStream)) {
                        exceptionsCollector.path = parent;
                        SecureDirectoryStream secureDirectoryStreamM = MoreFiles$$ExternalSyntheticApiModelOutline7.m(directoryStreamNewDirectoryStream);
                        Path fileName = path.getFileName();
                        Intrinsics.checkNotNullExpressionValue(fileName, "getFileName(...)");
                        handleEntry$PathsKt__PathRecursiveFunctionsKt(secureDirectoryStreamM, fileName, null, exceptionsCollector);
                    } else {
                        z = true;
                    }
                    directoryStreamNewDirectoryStream.close();
                    z2 = z;
                } finally {
                }
            }
        }
        if (z2) {
            insecureHandleEntry$PathsKt__PathRecursiveFunctionsKt(path, null, exceptionsCollector);
        }
        return exceptionsCollector.collectedExceptions;
    }

    public static final void enterDirectory$PathsKt__PathRecursiveFunctionsKt(SecureDirectoryStream<Path> secureDirectoryStream, Path path, ExceptionsCollector exceptionsCollector) {
        SecureDirectoryStream<Path> secureDirectoryStreamNewDirectoryStream;
        try {
            try {
                secureDirectoryStreamNewDirectoryStream = secureDirectoryStream.newDirectoryStream(path, LinkOption.NOFOLLOW_LINKS);
            } catch (NoSuchFileException unused) {
                secureDirectoryStreamNewDirectoryStream = null;
            }
            if (secureDirectoryStreamNewDirectoryStream == null) {
                return;
            }
            try {
                Iterator<Path> it = secureDirectoryStreamNewDirectoryStream.iterator();
                Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
                while (it.hasNext()) {
                    Path fileName = MoreFiles$$ExternalSyntheticApiModelOutline5.m(it.next()).getFileName();
                    Intrinsics.checkNotNullExpressionValue(fileName, "getFileName(...)");
                    handleEntry$PathsKt__PathRecursiveFunctionsKt(secureDirectoryStreamNewDirectoryStream, fileName, exceptionsCollector.path, exceptionsCollector);
                }
                secureDirectoryStreamNewDirectoryStream.close();
            } finally {
            }
        } catch (Exception e) {
            exceptionsCollector.collect(e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0030 A[Catch: Exception -> 0x0011, NoSuchFileException -> 0x0037, TRY_LEAVE, TryCatch #0 {NoSuchFileException -> 0x0037, blocks: (B:11:0x002c, B:12:0x0030), top: B:19:0x0021 }] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0023 A[Catch: Exception -> 0x0011, TRY_LEAVE, TryCatch #1 {Exception -> 0x0011, blocks: (B:4:0x0005, B:7:0x0013, B:9:0x0023, B:11:0x002c, B:12:0x0030), top: B:21:0x0005 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void handleEntry$PathsKt__PathRecursiveFunctionsKt(java.nio.file.SecureDirectoryStream<java.nio.file.Path> r2, java.nio.file.Path r3, java.nio.file.Path r4, kotlin.io.path.ExceptionsCollector r5) {
        /*
            r5.enterEntry(r3)
            if (r4 == 0) goto L13
            java.nio.file.Path r0 = r5.path     // Catch: java.lang.Exception -> L11
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)     // Catch: java.lang.Exception -> L11
            checkFileName(r0)     // Catch: java.lang.Exception -> L11
            checkNotSameAs$PathsKt__PathRecursiveFunctionsKt(r0, r4)     // Catch: java.lang.Exception -> L11
            goto L13
        L11:
            r2 = move-exception
            goto L34
        L13:
            r4 = 1
            java.nio.file.LinkOption[] r4 = new java.nio.file.LinkOption[r4]     // Catch: java.lang.Exception -> L11
            java.nio.file.LinkOption r0 = com.google.common.io.MoreFiles$$ExternalSyntheticApiModelOutline4.m()     // Catch: java.lang.Exception -> L11
            r1 = 0
            r4[r1] = r0     // Catch: java.lang.Exception -> L11
            boolean r4 = isDirectory$PathsKt__PathRecursiveFunctionsKt(r2, r3, r4)     // Catch: java.lang.Exception -> L11
            if (r4 == 0) goto L30
            int r4 = r5.totalExceptions     // Catch: java.lang.Exception -> L11
            enterDirectory$PathsKt__PathRecursiveFunctionsKt(r2, r3, r5)     // Catch: java.lang.Exception -> L11
            int r0 = r5.totalExceptions     // Catch: java.lang.Exception -> L11
            if (r4 != r0) goto L37
            r2.deleteDirectory(r3)     // Catch: java.lang.Exception -> L11 java.nio.file.NoSuchFileException -> L37
            goto L37
        L30:
            r2.deleteFile(r3)     // Catch: java.lang.Exception -> L11 java.nio.file.NoSuchFileException -> L37
            goto L37
        L34:
            r5.collect(r2)
        L37:
            r5.exitEntry(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.path.PathsKt__PathRecursiveFunctionsKt.handleEntry$PathsKt__PathRecursiveFunctionsKt(java.nio.file.SecureDirectoryStream, java.nio.file.Path, java.nio.file.Path, kotlin.io.path.ExceptionsCollector):void");
    }

    public static final void insecureEnterDirectory$PathsKt__PathRecursiveFunctionsKt(Path path, ExceptionsCollector exceptionsCollector) {
        DirectoryStream<Path> directoryStreamNewDirectoryStream;
        try {
            try {
                directoryStreamNewDirectoryStream = Files.newDirectoryStream(path);
            } catch (Exception e) {
                exceptionsCollector.collect(e);
                return;
            }
        } catch (NoSuchFileException unused) {
            directoryStreamNewDirectoryStream = null;
        }
        if (directoryStreamNewDirectoryStream == null) {
            return;
        }
        try {
            Iterator<Path> it = directoryStreamNewDirectoryStream.iterator();
            Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
            while (it.hasNext()) {
                Path pathM = MoreFiles$$ExternalSyntheticApiModelOutline5.m(it.next());
                Intrinsics.checkNotNull(pathM);
                insecureHandleEntry$PathsKt__PathRecursiveFunctionsKt(pathM, path, exceptionsCollector);
            }
            directoryStreamNewDirectoryStream.close();
        } finally {
        }
    }

    public static final void insecureHandleEntry$PathsKt__PathRecursiveFunctionsKt(Path path, Path path2, ExceptionsCollector exceptionsCollector) {
        if (path2 != null) {
            try {
                checkFileName(path);
                checkNotSameAs$PathsKt__PathRecursiveFunctionsKt(path, path2);
            } catch (Exception e) {
                exceptionsCollector.collect(e);
                return;
            }
        }
        if (!Files.isDirectory(path, (LinkOption[]) Arrays.copyOf(new LinkOption[]{LinkOption.NOFOLLOW_LINKS}, 1))) {
            Files.deleteIfExists(path);
            return;
        }
        int i = exceptionsCollector.totalExceptions;
        insecureEnterDirectory$PathsKt__PathRecursiveFunctionsKt(path, exceptionsCollector);
        if (i == exceptionsCollector.totalExceptions) {
            Files.deleteIfExists(path);
        }
    }

    public static final boolean isDirectory$PathsKt__PathRecursiveFunctionsKt(SecureDirectoryStream<Path> secureDirectoryStream, Path path, LinkOption... linkOptionArr) {
        Boolean boolValueOf;
        try {
            boolValueOf = Boolean.valueOf(MoreFiles$$ExternalSyntheticApiModelOutline3.m(secureDirectoryStream.getFileAttributeView(path, MoreFiles$$ExternalSyntheticApiModelOutline2.m(), (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length))).readAttributes().isDirectory());
        } catch (NoSuchFileException unused) {
            boolValueOf = null;
        }
        if (boolValueOf != null) {
            return boolValueOf.booleanValue();
        }
        return false;
    }

    @ExperimentalPathApi
    public static final FileVisitResult toFileVisitResult$PathsKt__PathRecursiveFunctionsKt(CopyActionResult copyActionResult) {
        int i = WhenMappings.$EnumSwitchMapping$0[copyActionResult.ordinal()];
        if (i == 1) {
            return FileVisitResult.CONTINUE;
        }
        if (i == 2) {
            return FileVisitResult.TERMINATE;
        }
        if (i == 3) {
            return FileVisitResult.SKIP_SUBTREE;
        }
        throw new NoWhenBranchMatchedException();
    }

    public static final <R> R tryIgnoreNoSuchFileException$PathsKt__PathRecursiveFunctionsKt(Function0<? extends R> function0) {
        try {
            return function0.invoke();
        } catch (NoSuchFileException unused) {
            return null;
        }
    }

    @SinceKotlin(version = "1.8")
    @ExperimentalPathApi
    @NotNull
    public static final Path copyToRecursively(@NotNull final Path path, @NotNull final Path target, @NotNull final Function3<? super Path, ? super Path, ? super Exception, ? extends OnErrorResult> onError, boolean z, @NotNull final Function3<? super CopyActionContext, ? super Path, ? super Path, ? extends CopyActionResult> copyAction) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(target, "target");
        Intrinsics.checkNotNullParameter(onError, "onError");
        Intrinsics.checkNotNullParameter(copyAction, "copyAction");
        LinkOption[] linkOptions = LinkFollowing.INSTANCE.toLinkOptions(z);
        LinkOption[] linkOptionArr = (LinkOption[]) Arrays.copyOf(linkOptions, linkOptions.length);
        if (Files.exists(path, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length))) {
            boolean zStartsWith = false;
            if (Files.exists(path, (LinkOption[]) Arrays.copyOf(new LinkOption[0], 0)) && (z || !Files.isSymbolicLink(path))) {
                boolean z2 = Files.exists(target, (LinkOption[]) Arrays.copyOf(new LinkOption[0], 0)) && !Files.isSymbolicLink(target);
                if (!z2 || !Files.isSameFile(path, target)) {
                    if (Intrinsics.areEqual(path.getFileSystem(), target.getFileSystem())) {
                        if (z2) {
                            zStartsWith = target.toRealPath(new LinkOption[0]).startsWith(path.toRealPath(new LinkOption[0]));
                        } else {
                            Path parent = target.getParent();
                            if (parent != null && Files.exists(parent, (LinkOption[]) Arrays.copyOf(new LinkOption[0], 0)) && parent.toRealPath(new LinkOption[0]).startsWith(path.toRealPath(new LinkOption[0]))) {
                                zStartsWith = true;
                            }
                        }
                    }
                    if (zStartsWith) {
                        MoreFiles$$ExternalSyntheticApiModelOutline1.m();
                        throw MoreFiles$$ExternalSyntheticApiModelOutline0.m(path.toString(), target.toString(), "Recursively copying a directory into its subdirectory is prohibited.");
                    }
                }
            }
            final Path pathNormalize = target.normalize();
            final ArrayList arrayList = new ArrayList();
            PathsKt__PathUtilsKt.visitFileTree$default(path, 0, z, new Function1() { // from class: kotlin.io.path.PathsKt__PathRecursiveFunctionsKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return PathsKt__PathRecursiveFunctionsKt.copyToRecursively$lambda$6$PathsKt__PathRecursiveFunctionsKt(arrayList, copyAction, path, target, pathNormalize, onError, (FileVisitorBuilder) obj);
                }
            }, 1, (Object) null);
            return target;
        }
        PathsKt__PathRecursiveFunctionsKt$$ExternalSyntheticApiModelOutline1.m();
        throw PathsKt__PathRecursiveFunctionsKt$$ExternalSyntheticApiModelOutline0.m(path.toString(), target.toString(), "The source file doesn't exist.");
    }

    public static /* synthetic */ Path copyToRecursively$default(Path path, Path path2, Function3 function3, final boolean z, Function3 function32, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            function3 = AnonymousClass3.INSTANCE;
        }
        if ((i & 8) != 0) {
            function32 = new Function3() { // from class: kotlin.io.path.PathsKt__PathRecursiveFunctionsKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    return PathsKt__PathRecursiveFunctionsKt.copyToRecursively$lambda$1$PathsKt__PathRecursiveFunctionsKt(z, (CopyActionContext) obj2, (Path) obj3, (Path) obj4);
                }
            };
        }
        copyToRecursively(path, path2, (Function3<? super Path, ? super Path, ? super Exception, ? extends OnErrorResult>) function3, z, (Function3<? super CopyActionContext, ? super Path, ? super Path, ? extends CopyActionResult>) function32);
        return path2;
    }

    @ExperimentalPathApi
    public static final FileVisitResult toFileVisitResult$PathsKt__PathRecursiveFunctionsKt(OnErrorResult onErrorResult) {
        int i = WhenMappings.$EnumSwitchMapping$1[onErrorResult.ordinal()];
        if (i == 1) {
            return FileVisitResult.TERMINATE;
        }
        if (i == 2) {
            return FileVisitResult.SKIP_SUBTREE;
        }
        throw new NoWhenBranchMatchedException();
    }
}
