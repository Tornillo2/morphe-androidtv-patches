package kotlin.io;

import com.amazon.avod.mpb.media.ExternalFourCCMapper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.io.FileTreeWalk;
import kotlin.io.FileTreeWalk.FileTreeWalkIterator;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SourceDebugExtension({"SMAP\nUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Utils.kt\nkotlin/io/FilesKt__UtilsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n*L\n1#1,473:1\n1#2:474\n1292#3,3:475\n*S KotlinDebug\n*F\n+ 1 Utils.kt\nkotlin/io/FilesKt__UtilsKt\n*L\n347#1:475,3\n*E\n"})
public class FilesKt__UtilsKt extends FilesKt__FileTreeWalkKt {

    /* JADX INFO: renamed from: kotlin.io.FilesKt__UtilsKt$copyRecursively$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AnonymousClass1 implements Function2 {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) throws IOException {
            invoke((File) obj, (IOException) obj2);
            throw null;
        }

        public final Void invoke(File file, IOException exception) throws IOException {
            Intrinsics.checkNotNullParameter(file, "<unused var>");
            Intrinsics.checkNotNullParameter(exception, "exception");
            throw exception;
        }
    }

    public static final boolean copyRecursively(@NotNull File file, @NotNull File target, boolean z, @NotNull final Function2<? super File, ? super IOException, ? extends OnErrorAction> onError) throws IOException {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(target, "target");
        Intrinsics.checkNotNullParameter(onError, "onError");
        if (!file.exists()) {
            return onError.invoke(file, new NoSuchFileException(file, null, "The source file doesn't exist.", 2, null)) != OnErrorAction.TERMINATE;
        }
        try {
            FileTreeWalk.FileTreeWalkIterator fileTreeWalkIterator = FilesKt__FileTreeWalkKt.walkTopDown(file).onFail(new Function2() { // from class: kotlin.io.FilesKt__UtilsKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return FilesKt__UtilsKt.copyRecursively$lambda$4$FilesKt__UtilsKt(onError, (File) obj, (IOException) obj2);
                }
            }).new FileTreeWalkIterator();
            while (fileTreeWalkIterator.hasNext()) {
                File next = fileTreeWalkIterator.next();
                if (next.exists()) {
                    File file2 = new File(target, toRelativeString(next, file));
                    if (file2.exists() && (!next.isDirectory() || !file2.isDirectory())) {
                        if (z) {
                            if (file2.isDirectory()) {
                                if (!deleteRecursively(file2)) {
                                }
                            } else if (!file2.delete()) {
                            }
                        }
                        if (onError.invoke(file2, new FileAlreadyExistsException(next, file2, "The destination file already exists.")) == OnErrorAction.TERMINATE) {
                            return false;
                        }
                    }
                    if (next.isDirectory()) {
                        file2.mkdirs();
                    } else {
                        boolean z2 = z;
                        copyTo$default(next, file2, z2, 0, 4, null);
                        if (file2.length() != next.length() && onError.invoke(next, new IOException("Source file wasn't copied completely, length of destination file differs.")) == OnErrorAction.TERMINATE) {
                            return false;
                        }
                        z = z2;
                    }
                } else if (onError.invoke(next, new NoSuchFileException(next, null, "The source file doesn't exist.", 2, null)) == OnErrorAction.TERMINATE) {
                    return false;
                }
            }
            return true;
        } catch (TerminateException unused) {
            return false;
        }
    }

    public static /* synthetic */ boolean copyRecursively$default(File file, File file2, boolean z, Function2 function2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            function2 = AnonymousClass1.INSTANCE;
        }
        return copyRecursively(file, file2, z, function2);
    }

    public static final Unit copyRecursively$lambda$4$FilesKt__UtilsKt(Function2 function2, File f, IOException e) throws TerminateException {
        Intrinsics.checkNotNullParameter(f, "f");
        Intrinsics.checkNotNullParameter(e, "e");
        if (function2.invoke(f, e) != OnErrorAction.TERMINATE) {
            return Unit.INSTANCE;
        }
        throw new TerminateException(f);
    }

    @NotNull
    public static final File copyTo(@NotNull File file, @NotNull File target, boolean z, int i) throws IOException {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(target, "target");
        if (!file.exists()) {
            throw new NoSuchFileException(file, null, "The source file doesn't exist.", 2, null);
        }
        if (target.exists()) {
            if (!z) {
                throw new FileAlreadyExistsException(file, target, "The destination file already exists.");
            }
            if (!target.delete()) {
                throw new FileAlreadyExistsException(file, target, "Tried to overwrite the destination, but failed to delete it.");
            }
        }
        if (file.isDirectory()) {
            if (target.mkdirs()) {
                return target;
            }
            throw new FileSystemException(file, target, "Failed to create target directory.");
        }
        File parentFile = target.getParentFile();
        if (parentFile != null) {
            parentFile.mkdirs();
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(target);
            try {
                ByteStreamsKt.copyTo(fileInputStream, fileOutputStream, i);
                fileOutputStream.close();
                fileInputStream.close();
                return target;
            } finally {
            }
        } finally {
        }
    }

    public static /* synthetic */ File copyTo$default(File file, File file2, boolean z, int i, int i2, Object obj) throws IOException {
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            i = 8192;
        }
        copyTo(file, file2, z, i);
        return file2;
    }

    @Deprecated(message = "Avoid creating temporary directories in the default temp location with this function due to too wide permissions on the newly created directory. Use kotlin.io.path.createTempDirectory instead.")
    @NotNull
    public static final File createTempDir(@NotNull String prefix, @Nullable String str, @Nullable File file) throws IOException {
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        File fileCreateTempFile = File.createTempFile(prefix, str, file);
        fileCreateTempFile.delete();
        if (fileCreateTempFile.mkdir()) {
            return fileCreateTempFile;
        }
        throw new IOException("Unable to create temporary directory " + fileCreateTempFile + '.');
    }

    public static /* synthetic */ File createTempDir$default(String str, String str2, File file, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "tmp";
        }
        if ((i & 2) != 0) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            file = null;
        }
        return createTempDir(str, str2, file);
    }

    @Deprecated(message = "Avoid creating temporary files in the default temp location with this function due to too wide permissions on the newly created file. Use kotlin.io.path.createTempFile instead or resort to java.io.File.createTempFile.")
    @NotNull
    public static final File createTempFile(@NotNull String prefix, @Nullable String str, @Nullable File file) throws IOException {
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        File fileCreateTempFile = File.createTempFile(prefix, str, file);
        Intrinsics.checkNotNullExpressionValue(fileCreateTempFile, "createTempFile(...)");
        return fileCreateTempFile;
    }

    public static /* synthetic */ File createTempFile$default(String str, String str2, File file, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "tmp";
        }
        if ((i & 2) != 0) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            file = null;
        }
        return createTempFile(str, str2, file);
    }

    public static final boolean deleteRecursively(@NotNull File file) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        while (true) {
            boolean z = true;
            for (File file2 : FilesKt__FileTreeWalkKt.walkBottomUp(file)) {
                if (!file2.delete() && file2.exists()) {
                    z = false;
                } else {
                    if (z) {
                        break;
                    }
                    z = false;
                }
            }
            return z;
        }
    }

    public static final boolean endsWith(@NotNull File file, @NotNull File other) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        FilePathComponents components = FilesKt__FilePathComponentsKt.toComponents(file);
        FilePathComponents components2 = FilesKt__FilePathComponentsKt.toComponents(other);
        if (components2.isRooted()) {
            return file.equals(other);
        }
        int size = components.segments.size() - components2.segments.size();
        if (size < 0) {
            return false;
        }
        List<File> list = components.segments;
        return list.subList(size, list.size()).equals(components2.segments);
    }

    @NotNull
    public static final String getExtension(@NotNull File file) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        String name = file.getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        return StringsKt__StringsKt.substringAfterLast(name, '.', "");
    }

    @NotNull
    public static final String getInvariantSeparatorsPath(@NotNull File file) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        char c = File.separatorChar;
        if (c != '/') {
            String path = file.getPath();
            Intrinsics.checkNotNullExpressionValue(path, "getPath(...)");
            return StringsKt__StringsJVMKt.replace$default(path, c, '/', false, 4, (Object) null);
        }
        String path2 = file.getPath();
        Intrinsics.checkNotNullExpressionValue(path2, "getPath(...)");
        return path2;
    }

    @NotNull
    public static final String getNameWithoutExtension(@NotNull File file) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        String name = file.getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        return StringsKt__StringsKt.substringBeforeLast$default(name, ExternalFourCCMapper.CODEC_NAME_SPLITTER, (String) null, 2, (Object) null);
    }

    @NotNull
    public static final File normalize(@NotNull File file) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        FilePathComponents components = FilesKt__FilePathComponentsKt.toComponents(file);
        File file2 = components.root;
        List<File> listNormalize$FilesKt__UtilsKt = normalize$FilesKt__UtilsKt(components.segments);
        String separator = File.separator;
        Intrinsics.checkNotNullExpressionValue(separator, "separator");
        return resolve(file2, CollectionsKt___CollectionsKt.joinToString$default(listNormalize$FilesKt__UtilsKt, separator, null, null, 0, null, null, 62, null));
    }

    public static final FilePathComponents normalize$FilesKt__UtilsKt(FilePathComponents filePathComponents) {
        return new FilePathComponents(filePathComponents.root, normalize$FilesKt__UtilsKt(filePathComponents.segments));
    }

    @NotNull
    public static final File relativeTo(@NotNull File file, @NotNull File base) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(base, "base");
        return new File(toRelativeString(file, base));
    }

    @Nullable
    public static final File relativeToOrNull(@NotNull File file, @NotNull File base) throws IOException {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(base, "base");
        String relativeStringOrNull$FilesKt__UtilsKt = toRelativeStringOrNull$FilesKt__UtilsKt(file, base);
        if (relativeStringOrNull$FilesKt__UtilsKt != null) {
            return new File(relativeStringOrNull$FilesKt__UtilsKt);
        }
        return null;
    }

    @NotNull
    public static final File relativeToOrSelf(@NotNull File file, @NotNull File base) throws IOException {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(base, "base");
        String relativeStringOrNull$FilesKt__UtilsKt = toRelativeStringOrNull$FilesKt__UtilsKt(file, base);
        return relativeStringOrNull$FilesKt__UtilsKt != null ? new File(relativeStringOrNull$FilesKt__UtilsKt) : file;
    }

    @NotNull
    public static final File resolve(@NotNull File file, @NotNull File relative) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(relative, "relative");
        if (FilesKt__FilePathComponentsKt.isRooted(relative)) {
            return relative;
        }
        String string = file.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        if (string.length() != 0) {
            char c = File.separatorChar;
            if (!StringsKt__StringsKt.endsWith$default((CharSequence) string, c, false, 2, (Object) null)) {
                return new File(string + c + relative);
            }
        }
        return new File(string + relative);
    }

    @NotNull
    public static final File resolveSibling(@NotNull File file, @NotNull File relative) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(relative, "relative");
        FilePathComponents components = FilesKt__FilePathComponentsKt.toComponents(file);
        return resolve(resolve(components.root, components.segments.size() == 0 ? new File("..") : components.subPath(0, components.segments.size() - 1)), relative);
    }

    public static final boolean startsWith(@NotNull File file, @NotNull File other) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        FilePathComponents components = FilesKt__FilePathComponentsKt.toComponents(file);
        FilePathComponents components2 = FilesKt__FilePathComponentsKt.toComponents(other);
        if (Intrinsics.areEqual(components.root, components2.root) && components.segments.size() >= components2.segments.size()) {
            return components.segments.subList(0, components2.segments.size()).equals(components2.segments);
        }
        return false;
    }

    @NotNull
    public static final String toRelativeString(@NotNull File file, @NotNull File base) throws IOException {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(base, "base");
        String relativeStringOrNull$FilesKt__UtilsKt = toRelativeStringOrNull$FilesKt__UtilsKt(file, base);
        if (relativeStringOrNull$FilesKt__UtilsKt != null) {
            return relativeStringOrNull$FilesKt__UtilsKt;
        }
        throw new IllegalArgumentException("this and base files have different roots: " + file + " and " + base + '.');
    }

    public static final String toRelativeStringOrNull$FilesKt__UtilsKt(File file, File file2) throws IOException {
        FilePathComponents filePathComponentsNormalize$FilesKt__UtilsKt = normalize$FilesKt__UtilsKt(FilesKt__FilePathComponentsKt.toComponents(file));
        FilePathComponents filePathComponentsNormalize$FilesKt__UtilsKt2 = normalize$FilesKt__UtilsKt(FilesKt__FilePathComponentsKt.toComponents(file2));
        if (!Intrinsics.areEqual(filePathComponentsNormalize$FilesKt__UtilsKt.root, filePathComponentsNormalize$FilesKt__UtilsKt2.root)) {
            return null;
        }
        int size = filePathComponentsNormalize$FilesKt__UtilsKt2.segments.size();
        int size2 = filePathComponentsNormalize$FilesKt__UtilsKt.segments.size();
        int iMin = Math.min(size2, size);
        int i = 0;
        while (i < iMin && Intrinsics.areEqual(filePathComponentsNormalize$FilesKt__UtilsKt.segments.get(i), filePathComponentsNormalize$FilesKt__UtilsKt2.segments.get(i))) {
            i++;
        }
        StringBuilder sb = new StringBuilder();
        int i2 = size - 1;
        if (i <= i2) {
            while (!Intrinsics.areEqual(filePathComponentsNormalize$FilesKt__UtilsKt2.segments.get(i2).getName(), "..")) {
                sb.append("..");
                if (i2 != i) {
                    sb.append(File.separatorChar);
                }
                if (i2 != i) {
                    i2--;
                }
            }
            return null;
        }
        if (i < size2) {
            if (i < size) {
                sb.append(File.separatorChar);
            }
            List listDrop = CollectionsKt___CollectionsKt.drop(filePathComponentsNormalize$FilesKt__UtilsKt.segments, i);
            String separator = File.separator;
            Intrinsics.checkNotNullExpressionValue(separator, "separator");
            CollectionsKt___CollectionsKt.joinTo$default(listDrop, sb, separator, null, null, 0, null, null, 124, null);
        }
        return sb.toString();
    }

    @NotNull
    public static final File resolve(@NotNull File file, @NotNull String relative) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(relative, "relative");
        return resolve(file, new File(relative));
    }

    public static final List<File> normalize$FilesKt__UtilsKt(List<? extends File> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (File file : list) {
            String name = file.getName();
            if (!Intrinsics.areEqual(name, ExternalFourCCMapper.CODEC_NAME_SPLITTER)) {
                if (Intrinsics.areEqual(name, "..")) {
                    if (arrayList.isEmpty() || Intrinsics.areEqual(((File) CollectionsKt___CollectionsKt.last((List) arrayList)).getName(), "..")) {
                        arrayList.add(file);
                    } else {
                        arrayList.remove(arrayList.size() - 1);
                    }
                } else {
                    arrayList.add(file);
                }
            }
        }
        return arrayList;
    }

    @NotNull
    public static final File resolveSibling(@NotNull File file, @NotNull String relative) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(relative, "relative");
        return resolveSibling(file, new File(relative));
    }

    public static final boolean endsWith(@NotNull File file, @NotNull String other) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        return endsWith(file, new File(other));
    }

    public static final boolean startsWith(@NotNull File file, @NotNull String other) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        return startsWith(file, new File(other));
    }
}
