package com.google.common.io;

import androidx.core.app.ActivityCompat$$ExternalSyntheticOutline0;
import com.amazon.avod.mpb.media.ExternalFourCCMapper;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Present;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;
import com.google.common.graph.Traverser;
import com.google.common.io.ByteSink;
import com.google.common.io.ByteSource;
import j$.util.Objects;
import j$.util.stream.Stream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystemException;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.SecureDirectoryStream;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@IgnoreJRERequirement
@J2ktIncompatible
@GwtIncompatible
public final class MoreFiles {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @IgnoreJRERequirement
    public static final class PathByteSink extends ByteSink {
        public final OpenOption[] options;
        public final Path path;

        public PathByteSink(Path path, OpenOption... options) {
            path.getClass();
            this.path = path;
            this.options = (OpenOption[]) options.clone();
        }

        @Override // com.google.common.io.ByteSink
        public OutputStream openStream() throws IOException {
            return java.nio.file.Files.newOutputStream(this.path, this.options);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("MoreFiles.asByteSink(");
            sb.append(this.path);
            sb.append(", ");
            return ActivityCompat$$ExternalSyntheticOutline0.m(sb, Arrays.toString(this.options), ")");
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @IgnoreJRERequirement
    public static final class PathByteSource extends ByteSource {
        public static final LinkOption[] FOLLOW_LINKS = new LinkOption[0];
        public final boolean followLinks;
        public final OpenOption[] options;
        public final Path path;

        public PathByteSource(Path path, OpenOption... options) {
            path.getClass();
            this.path = path;
            OpenOption[] openOptionArr = (OpenOption[]) options.clone();
            this.options = openOptionArr;
            this.followLinks = followLinks(openOptionArr);
        }

        public static boolean followLinks(OpenOption[] options) {
            for (OpenOption openOption : options) {
                if (openOption == LinkOption.NOFOLLOW_LINKS) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.google.common.io.ByteSource
        public CharSource asCharSource(Charset charset) {
            return this.options.length == 0 ? new ByteSource.AsCharSource(charset) { // from class: com.google.common.io.MoreFiles.PathByteSource.1
                @Override // com.google.common.io.CharSource
                public Stream<String> lines() throws IOException {
                    return Stream.VivifiedWrapper.convert(java.nio.file.Files.lines(PathByteSource.this.path, this.charset));
                }
            } : new ByteSource.AsCharSource(charset);
        }

        @Override // com.google.common.io.ByteSource
        public InputStream openStream() throws IOException {
            return java.nio.file.Files.newInputStream(this.path, this.options);
        }

        @Override // com.google.common.io.ByteSource
        public byte[] read() throws IOException {
            SeekableByteChannel seekableByteChannelNewByteChannel = java.nio.file.Files.newByteChannel(this.path, this.options);
            try {
                byte[] byteArray = ByteStreams.toByteArray(Channels.newInputStream(seekableByteChannelNewByteChannel), seekableByteChannelNewByteChannel.size());
                seekableByteChannelNewByteChannel.close();
                return byteArray;
            } catch (Throwable th) {
                if (seekableByteChannelNewByteChannel != null) {
                    try {
                        seekableByteChannelNewByteChannel.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }

        public final BasicFileAttributes readAttributes() throws IOException {
            return java.nio.file.Files.readAttributes(this.path, MoreFiles$PathByteSource$$ExternalSyntheticApiModelOutline0.m(), this.followLinks ? FOLLOW_LINKS : new LinkOption[]{LinkOption.NOFOLLOW_LINKS});
        }

        @Override // com.google.common.io.ByteSource
        public long size() throws IOException {
            BasicFileAttributes attributes = readAttributes();
            if (attributes.isDirectory()) {
                throw new IOException("can't read: is a directory");
            }
            if (attributes.isSymbolicLink()) {
                throw new IOException("can't read: is a symbolic link");
            }
            return attributes.size();
        }

        @Override // com.google.common.io.ByteSource
        public Optional<Long> sizeIfKnown() {
            try {
                BasicFileAttributes attributes = readAttributes();
                return (attributes.isDirectory() || attributes.isSymbolicLink()) ? Absent.INSTANCE : new Present(Long.valueOf(attributes.size()));
            } catch (IOException unused) {
                return Absent.INSTANCE;
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("MoreFiles.asByteSource(");
            sb.append(this.path);
            sb.append(", ");
            return ActivityCompat$$ExternalSyntheticOutline0.m(sb, Arrays.toString(this.options), ")");
        }
    }

    public static Collection<IOException> addException(Collection<IOException> exceptions, IOException e) {
        if (exceptions == null) {
            exceptions = new ArrayList<>();
        }
        exceptions.add(e);
        return exceptions;
    }

    public static ByteSink asByteSink(Path path, OpenOption... options) {
        return new PathByteSink(path, options);
    }

    public static ByteSource asByteSource(Path path, OpenOption... options) {
        return new PathByteSource(path, options);
    }

    public static CharSink asCharSink(Path path, Charset charset, OpenOption... options) {
        return new ByteSink.AsCharSink(charset);
    }

    public static CharSource asCharSource(Path path, Charset charset, OpenOption... options) {
        return new PathByteSource(path, options).asCharSource(charset);
    }

    public static void checkAllowsInsecure(Path path, RecursiveDeleteOption[] options) throws InsecureRecursiveDeleteException {
        if (!Arrays.asList(options).contains(RecursiveDeleteOption.ALLOW_INSECURE)) {
            throw new InsecureRecursiveDeleteException(path.toString());
        }
    }

    public static Collection<IOException> concat(Collection<IOException> exceptions, Collection<IOException> other) {
        if (exceptions == null) {
            return other;
        }
        if (other != null) {
            exceptions.addAll(other);
        }
        return exceptions;
    }

    public static void createParentDirectories(Path path, FileAttribute<?>... attrs) throws IOException {
        Path parent = path.toAbsolutePath().normalize().getParent();
        if (parent == null || java.nio.file.Files.isDirectory(parent, new LinkOption[0])) {
            return;
        }
        java.nio.file.Files.createDirectories(parent, attrs);
        if (java.nio.file.Files.isDirectory(parent, new LinkOption[0])) {
            return;
        }
        throw new IOException("Unable to create parent directories of " + path);
    }

    public static void deleteDirectoryContents(Path path, RecursiveDeleteOption... options) throws IOException {
        Collection<IOException> collectionDeleteDirectoryContentsInsecure;
        try {
            DirectoryStream<Path> directoryStreamNewDirectoryStream = java.nio.file.Files.newDirectoryStream(path);
            try {
                if (MoreFiles$$ExternalSyntheticApiModelOutline6.m(directoryStreamNewDirectoryStream)) {
                    collectionDeleteDirectoryContentsInsecure = deleteDirectoryContentsSecure(MoreFiles$$ExternalSyntheticApiModelOutline7.m(directoryStreamNewDirectoryStream));
                } else {
                    checkAllowsInsecure(path, options);
                    collectionDeleteDirectoryContentsInsecure = deleteDirectoryContentsInsecure(directoryStreamNewDirectoryStream);
                }
                if (directoryStreamNewDirectoryStream != null) {
                    try {
                        directoryStreamNewDirectoryStream.close();
                    } catch (IOException e) {
                        e = e;
                        if (collectionDeleteDirectoryContentsInsecure == null) {
                            throw e;
                        }
                        collectionDeleteDirectoryContentsInsecure.add(e);
                    }
                }
            } finally {
            }
        } catch (IOException e2) {
            e = e2;
            collectionDeleteDirectoryContentsInsecure = null;
        }
        if (collectionDeleteDirectoryContentsInsecure == null) {
            return;
        }
        throwDeleteFailed(path, collectionDeleteDirectoryContentsInsecure);
        throw null;
    }

    public static Collection<IOException> deleteDirectoryContentsInsecure(DirectoryStream<Path> dir) {
        Collection<IOException> collectionConcat = null;
        try {
            Iterator<Path> it = dir.iterator();
            while (it.hasNext()) {
                collectionConcat = concat(collectionConcat, deleteRecursivelyInsecure(MoreFiles$$ExternalSyntheticApiModelOutline5.m(it.next())));
            }
            return collectionConcat;
        } catch (DirectoryIteratorException e) {
            return addException(collectionConcat, e.getCause());
        }
    }

    public static Collection<IOException> deleteDirectoryContentsSecure(SecureDirectoryStream<Path> dir) {
        Collection<IOException> collectionConcat = null;
        try {
            Iterator<Path> it = dir.iterator();
            while (it.hasNext()) {
                collectionConcat = concat(collectionConcat, deleteRecursivelySecure(dir, MoreFiles$$ExternalSyntheticApiModelOutline5.m(it.next()).getFileName()));
            }
            return collectionConcat;
        } catch (DirectoryIteratorException e) {
            return addException(collectionConcat, e.getCause());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0052 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0057  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void deleteRecursively(java.nio.file.Path r4, com.google.common.io.RecursiveDeleteOption... r5) throws java.io.IOException {
        /*
            java.nio.file.Path r0 = getParentPath(r4)
            r1 = 0
            if (r0 == 0) goto L58
            java.nio.file.DirectoryStream r0 = java.nio.file.Files.newDirectoryStream(r0)     // Catch: java.io.IOException -> L47
            boolean r2 = com.google.common.io.MoreFiles$$ExternalSyntheticApiModelOutline6.m(r0)     // Catch: java.lang.Throwable -> L26
            if (r2 == 0) goto L28
            java.nio.file.SecureDirectoryStream r2 = com.google.common.io.MoreFiles$$ExternalSyntheticApiModelOutline7.m(r0)     // Catch: java.lang.Throwable -> L26
            java.nio.file.Path r3 = r4.getFileName()     // Catch: java.lang.Throwable -> L26
            j$.util.Objects.requireNonNull(r3)     // Catch: java.lang.Throwable -> L26
            java.nio.file.Path r3 = com.google.common.io.MoreFiles$$ExternalSyntheticApiModelOutline5.m(r3)     // Catch: java.lang.Throwable -> L26
            java.util.Collection r2 = deleteRecursivelySecure(r2, r3)     // Catch: java.lang.Throwable -> L26
            r3 = 1
            goto L2a
        L26:
            r5 = move-exception
            goto L3c
        L28:
            r3 = 0
            r2 = r1
        L2a:
            if (r0 == 0) goto L32
            r0.close()     // Catch: java.io.IOException -> L30
            goto L32
        L30:
            r5 = move-exception
            goto L4b
        L32:
            if (r3 != 0) goto L50
            checkAllowsInsecure(r4, r5)     // Catch: java.io.IOException -> L30
            java.util.Collection r2 = deleteRecursivelyInsecure(r4)     // Catch: java.io.IOException -> L30
            goto L50
        L3c:
            if (r0 == 0) goto L4a
            r0.close()     // Catch: java.lang.Throwable -> L42
            goto L4a
        L42:
            r0 = move-exception
            r5.addSuppressed(r0)     // Catch: java.io.IOException -> L47
            goto L4a
        L47:
            r5 = move-exception
            r2 = r1
            goto L4b
        L4a:
            throw r5     // Catch: java.io.IOException -> L47
        L4b:
            if (r2 == 0) goto L57
            r2.add(r5)
        L50:
            if (r2 != 0) goto L53
            return
        L53:
            throwDeleteFailed(r4, r2)
            throw r1
        L57:
            throw r5
        L58:
            com.google.common.io.MoreFiles$$ExternalSyntheticApiModelOutline1.m()
            java.lang.String r4 = r4.toString()
            java.lang.String r5 = "can't delete recursively"
            java.nio.file.FileSystemException r4 = com.google.common.io.MoreFiles$$ExternalSyntheticApiModelOutline0.m(r4, r1, r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.io.MoreFiles.deleteRecursively(java.nio.file.Path, com.google.common.io.RecursiveDeleteOption[]):void");
    }

    public static Collection<IOException> deleteRecursivelyInsecure(Path path) {
        Collection<IOException> collectionDeleteDirectoryContentsInsecure = null;
        try {
            if (java.nio.file.Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
                DirectoryStream<Path> directoryStreamNewDirectoryStream = java.nio.file.Files.newDirectoryStream(path);
                try {
                    collectionDeleteDirectoryContentsInsecure = deleteDirectoryContentsInsecure(directoryStreamNewDirectoryStream);
                    if (directoryStreamNewDirectoryStream != null) {
                        directoryStreamNewDirectoryStream.close();
                    }
                } finally {
                }
            }
            if (collectionDeleteDirectoryContentsInsecure == null) {
                java.nio.file.Files.delete(path);
            }
            return collectionDeleteDirectoryContentsInsecure;
        } catch (IOException e) {
            return addException(collectionDeleteDirectoryContentsInsecure, e);
        }
    }

    public static Collection<IOException> deleteRecursivelySecure(SecureDirectoryStream<Path> dir, Path path) {
        Collection<IOException> collectionDeleteDirectoryContentsSecure = null;
        try {
            LinkOption linkOption = LinkOption.NOFOLLOW_LINKS;
            if (!isDirectory(dir, path, linkOption)) {
                dir.deleteFile(path);
                return null;
            }
            SecureDirectoryStream<Path> secureDirectoryStreamNewDirectoryStream = dir.newDirectoryStream(path, linkOption);
            try {
                collectionDeleteDirectoryContentsSecure = deleteDirectoryContentsSecure(secureDirectoryStreamNewDirectoryStream);
                if (secureDirectoryStreamNewDirectoryStream != null) {
                    secureDirectoryStreamNewDirectoryStream.close();
                }
                if (collectionDeleteDirectoryContentsSecure == null) {
                    dir.deleteDirectory(path);
                }
                return collectionDeleteDirectoryContentsSecure;
            } finally {
            }
        } catch (IOException e) {
            return addException(collectionDeleteDirectoryContentsSecure, e);
        }
    }

    public static boolean equal(Path path1, Path path2) throws IOException {
        path1.getClass();
        path2.getClass();
        if (java.nio.file.Files.isSameFile(path1, path2)) {
            return true;
        }
        PathByteSource pathByteSource = new PathByteSource(path1, new OpenOption[0]);
        PathByteSource pathByteSource2 = new PathByteSource(path2, new OpenOption[0]);
        long jLongValue = pathByteSource.sizeIfKnown().or(0L).longValue();
        long jLongValue2 = pathByteSource2.sizeIfKnown().or(0L).longValue();
        if (jLongValue == 0 || jLongValue2 == 0 || jLongValue == jLongValue2) {
            return pathByteSource.contentEquals(pathByteSource2);
        }
        return false;
    }

    public static Traverser<Path> fileTraverser() {
        return Traverser.forTree(new MoreFiles$$ExternalSyntheticLambda11());
    }

    public static Iterable<Path> fileTreeChildren(Path dir) {
        if (!java.nio.file.Files.isDirectory(dir, LinkOption.NOFOLLOW_LINKS)) {
            return ImmutableList.of();
        }
        try {
            return listFiles(dir);
        } catch (IOException e) {
            throw MoreFiles$$ExternalSyntheticApiModelOutline10.m(e);
        }
    }

    public static String getFileExtension(Path path) {
        String string;
        int iLastIndexOf;
        Path fileName = path.getFileName();
        return (fileName == null || (iLastIndexOf = (string = fileName.toString()).lastIndexOf(46)) == -1) ? "" : string.substring(iLastIndexOf + 1);
    }

    public static String getNameWithoutExtension(Path path) {
        Path fileName = path.getFileName();
        if (fileName == null) {
            return "";
        }
        String string = fileName.toString();
        int iLastIndexOf = string.lastIndexOf(46);
        return iLastIndexOf == -1 ? string : string.substring(0, iLastIndexOf);
    }

    public static Path getParentPath(Path path) {
        Path parent = path.getParent();
        if (parent != null) {
            return parent;
        }
        if (path.getNameCount() == 0) {
            return null;
        }
        return path.getFileSystem().getPath(ExternalFourCCMapper.CODEC_NAME_SPLITTER, new String[0]);
    }

    public static Predicate<Path> isDirectory(LinkOption... options) {
        final LinkOption[] linkOptionArr = (LinkOption[]) options.clone();
        return new Predicate<Path>() { // from class: com.google.common.io.MoreFiles.1
            @Override // com.google.common.base.Predicate
            public /* bridge */ /* synthetic */ boolean apply(Path input) {
                return apply2(MoreFiles$$ExternalSyntheticApiModelOutline5.m(input));
            }

            public String toString() {
                return ActivityCompat$$ExternalSyntheticOutline0.m(new StringBuilder("MoreFiles.isDirectory("), Arrays.toString(linkOptionArr), ")");
            }

            /* JADX INFO: renamed from: apply, reason: avoid collision after fix types in other method */
            public boolean apply2(Path input) {
                return java.nio.file.Files.isDirectory(input, linkOptionArr);
            }
        };
    }

    public static Predicate<Path> isRegularFile(LinkOption... options) {
        final LinkOption[] linkOptionArr = (LinkOption[]) options.clone();
        return new Predicate<Path>() { // from class: com.google.common.io.MoreFiles.2
            @Override // com.google.common.base.Predicate
            public /* bridge */ /* synthetic */ boolean apply(Path input) {
                return apply2(MoreFiles$$ExternalSyntheticApiModelOutline5.m(input));
            }

            public String toString() {
                return ActivityCompat$$ExternalSyntheticOutline0.m(new StringBuilder("MoreFiles.isRegularFile("), Arrays.toString(linkOptionArr), ")");
            }

            /* JADX INFO: renamed from: apply, reason: avoid collision after fix types in other method */
            public boolean apply2(Path input) {
                return java.nio.file.Files.isRegularFile(input, linkOptionArr);
            }
        };
    }

    public static ImmutableList<Path> listFiles(Path dir) throws IOException {
        try {
            DirectoryStream<Path> directoryStreamNewDirectoryStream = java.nio.file.Files.newDirectoryStream(dir);
            try {
                ImmutableList<Path> immutableListCopyOf = ImmutableList.copyOf(directoryStreamNewDirectoryStream);
                directoryStreamNewDirectoryStream.close();
                return immutableListCopyOf;
            } finally {
            }
        } catch (DirectoryIteratorException e) {
            throw e.getCause();
        }
    }

    public static NoSuchFileException pathNotFound(Path path, Collection<IOException> exceptions) {
        NoSuchFileException noSuchFileExceptionM;
        String file;
        Path parentPath;
        if (exceptions.size() != 1) {
            return null;
        }
        IOException iOException = (IOException) Iterators.getOnlyElement(exceptions.iterator());
        if (!MoreFiles$$ExternalSyntheticApiModelOutline8.m(iOException) || (file = (noSuchFileExceptionM = MoreFiles$$ExternalSyntheticApiModelOutline9.m(iOException)).getFile()) == null || (parentPath = getParentPath(path)) == null) {
            return null;
        }
        Path fileName = path.getFileName();
        Objects.requireNonNull(fileName);
        if (file.equals(parentPath.resolve(MoreFiles$$ExternalSyntheticApiModelOutline5.m(fileName)).toString())) {
            return noSuchFileExceptionM;
        }
        return null;
    }

    public static void throwDeleteFailed(Path path, Collection<IOException> exceptions) throws FileSystemException {
        NoSuchFileException noSuchFileExceptionPathNotFound = pathNotFound(path, exceptions);
        if (noSuchFileExceptionPathNotFound != null) {
            throw noSuchFileExceptionPathNotFound;
        }
        MoreFiles$$ExternalSyntheticApiModelOutline1.m();
        FileSystemException fileSystemExceptionM = MoreFiles$$ExternalSyntheticApiModelOutline0.m(path.toString(), null, "failed to delete one or more files; see suppressed exceptions for details");
        Iterator<IOException> it = exceptions.iterator();
        while (it.hasNext()) {
            fileSystemExceptionM.addSuppressed(it.next());
        }
        throw fileSystemExceptionM;
    }

    public static void touch(Path path) throws IOException {
        path.getClass();
        try {
            java.nio.file.Files.setLastModifiedTime(path, FileTime.fromMillis(System.currentTimeMillis()));
        } catch (NoSuchFileException unused) {
            try {
                java.nio.file.Files.createFile(path, new FileAttribute[0]);
            } catch (FileAlreadyExistsException unused2) {
            }
        }
    }

    public static boolean isDirectory(SecureDirectoryStream<Path> dir, Path name, LinkOption... options) throws IOException {
        return MoreFiles$$ExternalSyntheticApiModelOutline3.m(dir.getFileAttributeView(name, MoreFiles$$ExternalSyntheticApiModelOutline2.m(), options)).readAttributes().isDirectory();
    }
}
