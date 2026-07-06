package kotlin.io.path;

import com.amazon.avod.mpb.media.ExternalFourCCMapper;
import com.amazon.minerva.identifiers.schemaid.SchemaId;
import com.google.common.net.MediaType;
import java.io.IOException;
import java.net.URI;
import java.nio.file.CopyOption;
import java.nio.file.DirectoryStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileStore;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.UserPrincipal;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.PublishedApi;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.WasExperimental;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.EmptySet;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.internal.InlineOnly;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SourceDebugExtension({"SMAP\nPathUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PathUtils.kt\nkotlin/io/path/PathsKt__PathUtilsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,1253:1\n1#2:1254\n1869#3,2:1255\n*S KotlinDebug\n*F\n+ 1 PathUtils.kt\nkotlin/io/path/PathsKt__PathUtilsKt\n*L\n440#1:1255,2\n*E\n"})
public class PathsKt__PathUtilsKt extends PathsKt__PathRecursiveFunctionsKt {
    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    public static final Path Path(String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        Path path2 = Paths.get(path, new String[0]);
        Intrinsics.checkNotNullExpressionValue(path2, "get(...)");
        return path2;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    public static final Path absolute(Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Path absolutePath = path.toAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "toAbsolutePath(...)");
        return absolutePath;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    public static final String absolutePathString(Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        return path.toAbsolutePath().toString();
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    public static final Path copyTo(Path path, Path target, boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(target, "target");
        CopyOption[] copyOptionArr = z ? new CopyOption[]{StandardCopyOption.REPLACE_EXISTING} : new CopyOption[0];
        Path pathCopy = Files.copy(path, target, (CopyOption[]) Arrays.copyOf(copyOptionArr, copyOptionArr.length));
        Intrinsics.checkNotNullExpressionValue(pathCopy, "copy(...)");
        return pathCopy;
    }

    public static /* synthetic */ Path copyTo$default(Path path, Path target, boolean z, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            z = false;
        }
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(target, "target");
        CopyOption[] copyOptionArr = z ? new CopyOption[]{StandardCopyOption.REPLACE_EXISTING} : new CopyOption[0];
        Path pathCopy = Files.copy(path, target, (CopyOption[]) Arrays.copyOf(copyOptionArr, copyOptionArr.length));
        Intrinsics.checkNotNullExpressionValue(pathCopy, "copy(...)");
        return pathCopy;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    public static final Path createDirectories(Path path, FileAttribute<?>... attributes) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        Path pathCreateDirectories = Files.createDirectories(path, (FileAttribute[]) Arrays.copyOf(attributes, attributes.length));
        Intrinsics.checkNotNullExpressionValue(pathCreateDirectories, "createDirectories(...)");
        return pathCreateDirectories;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    public static final Path createDirectory(Path path, FileAttribute<?>... attributes) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        Path pathCreateDirectory = Files.createDirectory(path, (FileAttribute[]) Arrays.copyOf(attributes, attributes.length));
        Intrinsics.checkNotNullExpressionValue(pathCreateDirectory, "createDirectory(...)");
        return pathCreateDirectory;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    public static final Path createFile(Path path, FileAttribute<?>... attributes) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        Path pathCreateFile = Files.createFile(path, (FileAttribute[]) Arrays.copyOf(attributes, attributes.length));
        Intrinsics.checkNotNullExpressionValue(pathCreateFile, "createFile(...)");
        return pathCreateFile;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    public static final Path createLinkPointingTo(Path path, Path target) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(target, "target");
        Path pathCreateLink = Files.createLink(path, target);
        Intrinsics.checkNotNullExpressionValue(pathCreateLink, "createLink(...)");
        return pathCreateLink;
    }

    @SinceKotlin(version = "1.9")
    @NotNull
    public static final Path createParentDirectories(@NotNull Path path, @NotNull FileAttribute<?>... attributes) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        Path parent = path.getParent();
        if (parent != null && !Files.isDirectory(parent, (LinkOption[]) Arrays.copyOf(new LinkOption[0], 0))) {
            try {
                FileAttribute[] fileAttributeArr = (FileAttribute[]) Arrays.copyOf(attributes, attributes.length);
                Intrinsics.checkNotNullExpressionValue(Files.createDirectories(parent, (FileAttribute[]) Arrays.copyOf(fileAttributeArr, fileAttributeArr.length)), "createDirectories(...)");
                return path;
            } catch (FileAlreadyExistsException e) {
                if (!Files.isDirectory(parent, (LinkOption[]) Arrays.copyOf(new LinkOption[0], 0))) {
                    throw e;
                }
            }
        }
        return path;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    public static final Path createSymbolicLinkPointingTo(Path path, Path target, FileAttribute<?>... attributes) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(target, "target");
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        Path pathCreateSymbolicLink = Files.createSymbolicLink(path, target, (FileAttribute[]) Arrays.copyOf(attributes, attributes.length));
        Intrinsics.checkNotNullExpressionValue(pathCreateSymbolicLink, "createSymbolicLink(...)");
        return pathCreateSymbolicLink;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    public static final Path createTempDirectory(String str, FileAttribute<?>... attributes) throws IOException {
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        Path pathCreateTempDirectory = Files.createTempDirectory(str, (FileAttribute[]) Arrays.copyOf(attributes, attributes.length));
        Intrinsics.checkNotNullExpressionValue(pathCreateTempDirectory, "createTempDirectory(...)");
        return pathCreateTempDirectory;
    }

    public static /* synthetic */ Path createTempDirectory$default(String str, FileAttribute[] attributes, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            str = null;
        }
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        Path pathCreateTempDirectory = Files.createTempDirectory(str, (FileAttribute[]) Arrays.copyOf(attributes, attributes.length));
        Intrinsics.checkNotNullExpressionValue(pathCreateTempDirectory, "createTempDirectory(...)");
        return pathCreateTempDirectory;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    public static final Path createTempFile(String str, String str2, FileAttribute<?>... attributes) throws IOException {
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        Path pathCreateTempFile = Files.createTempFile(str, str2, (FileAttribute[]) Arrays.copyOf(attributes, attributes.length));
        Intrinsics.checkNotNullExpressionValue(pathCreateTempFile, "createTempFile(...)");
        return pathCreateTempFile;
    }

    public static /* synthetic */ Path createTempFile$default(String str, String str2, FileAttribute[] attributes, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            str2 = null;
        }
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        Path pathCreateTempFile = Files.createTempFile(str, str2, (FileAttribute[]) Arrays.copyOf(attributes, attributes.length));
        Intrinsics.checkNotNullExpressionValue(pathCreateTempFile, "createTempFile(...)");
        return pathCreateTempFile;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    public static final void deleteExisting(Path path) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Files.delete(path);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    public static final boolean deleteIfExists(Path path) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        return Files.deleteIfExists(path);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    public static final Path div(Path path, Path other) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Path pathResolve = path.resolve(other);
        Intrinsics.checkNotNullExpressionValue(pathResolve, "resolve(...)");
        return pathResolve;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    public static final boolean exists(Path path, LinkOption... options) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(options, "options");
        return Files.exists(path, (LinkOption[]) Arrays.copyOf(options, options.length));
    }

    @PublishedApi
    @NotNull
    public static final Void fileAttributeViewNotAvailable(@NotNull Path path, @NotNull Class<?> attributeViewClass) {
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(attributeViewClass, "attributeViewClass");
        throw new UnsupportedOperationException("The desired attribute view type " + attributeViewClass + " is not available for the file " + path + '.');
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    public static final <V extends FileAttributeView> V fileAttributesView(Path path, LinkOption... options) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    public static final <V extends FileAttributeView> V fileAttributesViewOrNull(Path path, LinkOption... options) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    public static final long fileSize(Path path) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        return Files.size(path);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    public static final FileStore fileStore(Path path) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        FileStore fileStore = Files.getFileStore(path);
        Intrinsics.checkNotNullExpressionValue(fileStore, "getFileStore(...)");
        return fileStore;
    }

    @SinceKotlin(version = "2.1")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @NotNull
    public static final FileVisitor<Path> fileVisitor(@NotNull Function1<? super FileVisitorBuilder, Unit> builderAction) {
        Intrinsics.checkNotNullParameter(builderAction, "builderAction");
        FileVisitorBuilderImpl fileVisitorBuilderImpl = new FileVisitorBuilderImpl();
        builderAction.invoke(fileVisitorBuilderImpl);
        return fileVisitorBuilderImpl.build();
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    public static final void forEachDirectoryEntry(Path path, String glob, Function1<? super Path, Unit> action) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(glob, "glob");
        Intrinsics.checkNotNullParameter(action, "action");
        DirectoryStream<Path> directoryStreamNewDirectoryStream = Files.newDirectoryStream(path, glob);
        try {
            Intrinsics.checkNotNull(directoryStreamNewDirectoryStream);
            Iterator<Path> it = directoryStreamNewDirectoryStream.iterator();
            while (it.hasNext()) {
                action.invoke(it.next());
            }
            CloseableKt.closeFinally(directoryStreamNewDirectoryStream, null);
        } finally {
        }
    }

    public static /* synthetic */ void forEachDirectoryEntry$default(Path path, String glob, Function1 action, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            glob = MediaType.WILDCARD;
        }
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(glob, "glob");
        Intrinsics.checkNotNullParameter(action, "action");
        DirectoryStream<Path> directoryStreamNewDirectoryStream = Files.newDirectoryStream(path, glob);
        try {
            Intrinsics.checkNotNull(directoryStreamNewDirectoryStream);
            Iterator<Path> it = directoryStreamNewDirectoryStream.iterator();
            while (it.hasNext()) {
                action.invoke(it.next());
            }
            CloseableKt.closeFinally(directoryStreamNewDirectoryStream, null);
        } finally {
        }
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    public static final Object getAttribute(Path path, String attribute, LinkOption... options) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(attribute, "attribute");
        Intrinsics.checkNotNullParameter(options, "options");
        return Files.getAttribute(path, attribute, (LinkOption[]) Arrays.copyOf(options, options.length));
    }

    @NotNull
    public static final String getExtension(@NotNull Path path) {
        String string;
        Intrinsics.checkNotNullParameter(path, "<this>");
        Path fileName = path.getFileName();
        return (fileName == null || (string = fileName.toString()) == null) ? "" : StringsKt__StringsKt.substringAfterLast(string, '.', "");
    }

    public static final String getInvariantSeparatorsPath(Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        return getInvariantSeparatorsPathString(path);
    }

    @NotNull
    public static final String getInvariantSeparatorsPathString(@NotNull Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        String separator = path.getFileSystem().getSeparator();
        if (Intrinsics.areEqual(separator, SchemaId.METRIC_SCHEMA_ID_DELIMITER)) {
            return path.toString();
        }
        String string = path.toString();
        Intrinsics.checkNotNull(separator);
        return StringsKt__StringsJVMKt.replace$default(string, separator, SchemaId.METRIC_SCHEMA_ID_DELIMITER, false, 4, (Object) null);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    public static final FileTime getLastModifiedTime(Path path, LinkOption... options) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(options, "options");
        FileTime lastModifiedTime = Files.getLastModifiedTime(path, (LinkOption[]) Arrays.copyOf(options, options.length));
        Intrinsics.checkNotNullExpressionValue(lastModifiedTime, "getLastModifiedTime(...)");
        return lastModifiedTime;
    }

    @NotNull
    public static final String getName(@NotNull Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Path fileName = path.getFileName();
        String string = fileName != null ? fileName.toString() : null;
        return string == null ? "" : string;
    }

    @NotNull
    public static final String getNameWithoutExtension(@NotNull Path path) {
        String string;
        Intrinsics.checkNotNullParameter(path, "<this>");
        Path fileName = path.getFileName();
        return (fileName == null || (string = fileName.toString()) == null) ? "" : StringsKt__StringsKt.substringBeforeLast$default(string, ExternalFourCCMapper.CODEC_NAME_SPLITTER, (String) null, 2, (Object) null);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    public static final UserPrincipal getOwner(Path path, LinkOption... options) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(options, "options");
        return Files.getOwner(path, (LinkOption[]) Arrays.copyOf(options, options.length));
    }

    public static final String getPathString(Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        return path.toString();
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    public static final Set<PosixFilePermission> getPosixFilePermissions(Path path, LinkOption... options) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(options, "options");
        Set<PosixFilePermission> posixFilePermissions = Files.getPosixFilePermissions(path, (LinkOption[]) Arrays.copyOf(options, options.length));
        Intrinsics.checkNotNullExpressionValue(posixFilePermissions, "getPosixFilePermissions(...)");
        return posixFilePermissions;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    public static final boolean isDirectory(Path path, LinkOption... options) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(options, "options");
        return Files.isDirectory(path, (LinkOption[]) Arrays.copyOf(options, options.length));
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    public static final boolean isExecutable(Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        return Files.isExecutable(path);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    public static final boolean isHidden(Path path) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        return Files.isHidden(path);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    public static final boolean isReadable(Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        return Files.isReadable(path);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    public static final boolean isRegularFile(Path path, LinkOption... options) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(options, "options");
        return Files.isRegularFile(path, (LinkOption[]) Arrays.copyOf(options, options.length));
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    public static final boolean isSameFileAs(Path path, Path other) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        return Files.isSameFile(path, other);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    public static final boolean isSymbolicLink(Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        return Files.isSymbolicLink(path);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    public static final boolean isWritable(Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        return Files.isWritable(path);
    }

    @SinceKotlin(version = "1.5")
    @NotNull
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    public static final List<Path> listDirectoryEntries(@NotNull Path path, @NotNull String glob) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(glob, "glob");
        DirectoryStream<Path> directoryStreamNewDirectoryStream = Files.newDirectoryStream(path, glob);
        try {
            Intrinsics.checkNotNull(directoryStreamNewDirectoryStream);
            List<Path> list = CollectionsKt___CollectionsKt.toList(directoryStreamNewDirectoryStream);
            CloseableKt.closeFinally(directoryStreamNewDirectoryStream, null);
            return list;
        } finally {
        }
    }

    public static /* synthetic */ List listDirectoryEntries$default(Path path, String str, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            str = MediaType.WILDCARD;
        }
        return listDirectoryEntries(path, str);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    public static final Path moveTo(Path path, Path target, CopyOption... options) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(target, "target");
        Intrinsics.checkNotNullParameter(options, "options");
        Path pathMove = Files.move(path, target, (CopyOption[]) Arrays.copyOf(options, options.length));
        Intrinsics.checkNotNullExpressionValue(pathMove, "move(...)");
        return pathMove;
    }

    public static /* synthetic */ Path moveTo$default(Path path, Path target, boolean z, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            z = false;
        }
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(target, "target");
        CopyOption[] copyOptionArr = z ? new CopyOption[]{StandardCopyOption.REPLACE_EXISTING} : new CopyOption[0];
        Path pathMove = Files.move(path, target, (CopyOption[]) Arrays.copyOf(copyOptionArr, copyOptionArr.length));
        Intrinsics.checkNotNullExpressionValue(pathMove, "move(...)");
        return pathMove;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    public static final boolean notExists(Path path, LinkOption... options) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(options, "options");
        return Files.notExists(path, (LinkOption[]) Arrays.copyOf(options, options.length));
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    public static final <A extends BasicFileAttributes> A readAttributes(Path path, LinkOption... options) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    public static final Path readSymbolicLink(Path path) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Path symbolicLink = Files.readSymbolicLink(path);
        Intrinsics.checkNotNullExpressionValue(symbolicLink, "readSymbolicLink(...)");
        return symbolicLink;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @NotNull
    public static final Path relativeTo(@NotNull Path path, @NotNull Path base) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(base, "base");
        try {
            return PathRelativizer.INSTANCE.tryRelativeTo(path, base);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage() + "\nthis path: " + path + "\nbase path: " + base, e);
        }
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @Nullable
    public static final Path relativeToOrNull(@NotNull Path path, @NotNull Path base) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(base, "base");
        try {
            return PathRelativizer.INSTANCE.tryRelativeTo(path, base);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @NotNull
    public static final Path relativeToOrSelf(@NotNull Path path, @NotNull Path base) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(base, "base");
        Path pathRelativeToOrNull = relativeToOrNull(path, base);
        return pathRelativeToOrNull == null ? path : pathRelativeToOrNull;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    public static final Path setAttribute(Path path, String attribute, Object obj, LinkOption... options) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(attribute, "attribute");
        Intrinsics.checkNotNullParameter(options, "options");
        Path attribute2 = Files.setAttribute(path, attribute, obj, (LinkOption[]) Arrays.copyOf(options, options.length));
        Intrinsics.checkNotNullExpressionValue(attribute2, "setAttribute(...)");
        return attribute2;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    public static final Path setLastModifiedTime(Path path, FileTime value) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(value, "value");
        Path lastModifiedTime = Files.setLastModifiedTime(path, value);
        Intrinsics.checkNotNullExpressionValue(lastModifiedTime, "setLastModifiedTime(...)");
        return lastModifiedTime;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    public static final Path setOwner(Path path, UserPrincipal value) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(value, "value");
        Path owner = Files.setOwner(path, value);
        Intrinsics.checkNotNullExpressionValue(owner, "setOwner(...)");
        return owner;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    public static final Path setPosixFilePermissions(Path path, Set<? extends PosixFilePermission> value) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(value, "value");
        Path posixFilePermissions = Files.setPosixFilePermissions(path, value);
        Intrinsics.checkNotNullExpressionValue(posixFilePermissions, "setPosixFilePermissions(...)");
        return posixFilePermissions;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    public static final Path toPath(URI uri) {
        Intrinsics.checkNotNullParameter(uri, "<this>");
        Path path = Paths.get(uri);
        Intrinsics.checkNotNullExpressionValue(path, "get(...)");
        return path;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    public static final <T> T useDirectoryEntries(Path path, String glob, Function1<? super Sequence<? extends Path>, ? extends T> block) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(glob, "glob");
        Intrinsics.checkNotNullParameter(block, "block");
        DirectoryStream<Path> directoryStreamNewDirectoryStream = Files.newDirectoryStream(path, glob);
        try {
            Intrinsics.checkNotNull(directoryStreamNewDirectoryStream);
            T tInvoke = block.invoke(CollectionsKt___CollectionsKt.asSequence(directoryStreamNewDirectoryStream));
            CloseableKt.closeFinally(directoryStreamNewDirectoryStream, null);
            return tInvoke;
        } finally {
        }
    }

    public static /* synthetic */ Object useDirectoryEntries$default(Path path, String glob, Function1 block, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            glob = MediaType.WILDCARD;
        }
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(glob, "glob");
        Intrinsics.checkNotNullParameter(block, "block");
        DirectoryStream<Path> directoryStreamNewDirectoryStream = Files.newDirectoryStream(path, glob);
        try {
            Intrinsics.checkNotNull(directoryStreamNewDirectoryStream);
            Object objInvoke = block.invoke(CollectionsKt___CollectionsKt.asSequence(directoryStreamNewDirectoryStream));
            CloseableKt.closeFinally(directoryStreamNewDirectoryStream, null);
            return objInvoke;
        } finally {
        }
    }

    @SinceKotlin(version = "2.1")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    public static final void visitFileTree(@NotNull Path path, @NotNull FileVisitor<Path> visitor, int i, boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(visitor, "visitor");
        Files.walkFileTree(path, z ? SetsKt__SetsJVMKt.setOf(FileVisitOption.FOLLOW_LINKS) : EmptySet.INSTANCE, i, visitor);
    }

    public static /* synthetic */ void visitFileTree$default(Path path, FileVisitor fileVisitor, int i, boolean z, int i2, Object obj) throws IOException {
        if ((i2 & 2) != 0) {
            i = Integer.MAX_VALUE;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        visitFileTree(path, (FileVisitor<Path>) fileVisitor, i, z);
    }

    @SinceKotlin(version = "2.1")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @NotNull
    public static final Sequence<Path> walk(@NotNull Path path, @NotNull PathWalkOption... options) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(options, "options");
        return new PathTreeWalk(path, options);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    public static final Path Path(String base, String... subpaths) {
        Intrinsics.checkNotNullParameter(base, "base");
        Intrinsics.checkNotNullParameter(subpaths, "subpaths");
        Path path = Paths.get(base, (String[]) Arrays.copyOf(subpaths, subpaths.length));
        Intrinsics.checkNotNullExpressionValue(path, "get(...)");
        return path;
    }

    @SinceKotlin(version = "1.5")
    @NotNull
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    public static final Path createTempDirectory(@Nullable Path path, @Nullable String str, @NotNull FileAttribute<?>... attributes) throws IOException {
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        if (path != null) {
            Path pathCreateTempDirectory = Files.createTempDirectory(path, str, (FileAttribute[]) Arrays.copyOf(attributes, attributes.length));
            Intrinsics.checkNotNullExpressionValue(pathCreateTempDirectory, "createTempDirectory(...)");
            return pathCreateTempDirectory;
        }
        Path pathCreateTempDirectory2 = Files.createTempDirectory(str, (FileAttribute[]) Arrays.copyOf(attributes, attributes.length));
        Intrinsics.checkNotNullExpressionValue(pathCreateTempDirectory2, "createTempDirectory(...)");
        return pathCreateTempDirectory2;
    }

    @SinceKotlin(version = "1.5")
    @NotNull
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    public static final Path createTempFile(@Nullable Path path, @Nullable String str, @Nullable String str2, @NotNull FileAttribute<?>... attributes) throws IOException {
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        if (path != null) {
            Path pathCreateTempFile = Files.createTempFile(path, str, str2, (FileAttribute[]) Arrays.copyOf(attributes, attributes.length));
            Intrinsics.checkNotNullExpressionValue(pathCreateTempFile, "createTempFile(...)");
            return pathCreateTempFile;
        }
        Path pathCreateTempFile2 = Files.createTempFile(str, str2, (FileAttribute[]) Arrays.copyOf(attributes, attributes.length));
        Intrinsics.checkNotNullExpressionValue(pathCreateTempFile2, "createTempFile(...)");
        return pathCreateTempFile2;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    public static final Path div(Path path, String other) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Path pathResolve = path.resolve(other);
        Intrinsics.checkNotNullExpressionValue(pathResolve, "resolve(...)");
        return pathResolve;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    public static final Path moveTo(Path path, Path target, boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(target, "target");
        CopyOption[] copyOptionArr = z ? new CopyOption[]{StandardCopyOption.REPLACE_EXISTING} : new CopyOption[0];
        Path pathMove = Files.move(path, target, (CopyOption[]) Arrays.copyOf(copyOptionArr, copyOptionArr.length));
        Intrinsics.checkNotNullExpressionValue(pathMove, "move(...)");
        return pathMove;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    public static final Map<String, Object> readAttributes(Path path, String attributes, LinkOption... options) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        Intrinsics.checkNotNullParameter(options, "options");
        Map<String, Object> attributes2 = Files.readAttributes(path, attributes, (LinkOption[]) Arrays.copyOf(options, options.length));
        Intrinsics.checkNotNullExpressionValue(attributes2, "readAttributes(...)");
        return attributes2;
    }

    public static /* synthetic */ void visitFileTree$default(Path path, int i, boolean z, Function1 function1, int i2, Object obj) throws IOException {
        if ((i2 & 1) != 0) {
            i = Integer.MAX_VALUE;
        }
        if ((i2 & 2) != 0) {
            z = false;
        }
        visitFileTree(path, i, z, (Function1<? super FileVisitorBuilder, Unit>) function1);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    public static final Path copyTo(Path path, Path target, CopyOption... options) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(target, "target");
        Intrinsics.checkNotNullParameter(options, "options");
        Path pathCopy = Files.copy(path, target, (CopyOption[]) Arrays.copyOf(options, options.length));
        Intrinsics.checkNotNullExpressionValue(pathCopy, "copy(...)");
        return pathCopy;
    }

    public static /* synthetic */ Path createTempDirectory$default(Path path, String str, FileAttribute[] fileAttributeArr, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            str = null;
        }
        return createTempDirectory(path, str, fileAttributeArr);
    }

    public static /* synthetic */ Path createTempFile$default(Path path, String str, String str2, FileAttribute[] fileAttributeArr, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            str = null;
        }
        if ((i & 4) != 0) {
            str2 = null;
        }
        return createTempFile(path, str, str2, fileAttributeArr);
    }

    @SinceKotlin(version = "2.1")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    public static final void visitFileTree(@NotNull Path path, int i, boolean z, @NotNull Function1<? super FileVisitorBuilder, Unit> builderAction) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(builderAction, "builderAction");
        visitFileTree(path, fileVisitor(builderAction), i, z);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    public static /* synthetic */ void getExtension$annotations(Path path) {
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @Deprecated(level = DeprecationLevel.ERROR, message = "Use invariantSeparatorsPathString property instead.", replaceWith = @ReplaceWith(expression = "invariantSeparatorsPathString", imports = {}))
    @ExperimentalPathApi
    public static /* synthetic */ void getInvariantSeparatorsPath$annotations(Path path) {
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    public static /* synthetic */ void getInvariantSeparatorsPathString$annotations(Path path) {
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    public static /* synthetic */ void getName$annotations(Path path) {
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    public static /* synthetic */ void getNameWithoutExtension$annotations(Path path) {
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    public static /* synthetic */ void getPathString$annotations(Path path) {
    }
}
