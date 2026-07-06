package com.google.common.io;

import androidx.core.app.RemoteInput$$ExternalSyntheticOutline0;
import com.amazon.avod.mpb.media.ExternalFourCCMapper;
import com.amazon.minerva.identifiers.schemaid.SchemaId;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Absent;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Present;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.graph.SuccessorsFunction;
import com.google.common.graph.Traverser;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.io.ByteSink;
import com.google.common.io.ByteSource;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.InlineMe;
import j$.util.DesugarCollections;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtIncompatible
public final class Files {
    public static final SuccessorsFunction<File> FILE_TREE = new Files$$ExternalSyntheticLambda0();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class FileByteSink extends ByteSink {
        public final File file;
        public final ImmutableSet<FileWriteMode> modes;

        public FileByteSink(File file, FileWriteMode... modes) {
            file.getClass();
            this.file = file;
            this.modes = ImmutableSet.copyOf(modes);
        }

        public String toString() {
            return "Files.asByteSink(" + this.file + ", " + this.modes + ")";
        }

        @Override // com.google.common.io.ByteSink
        public FileOutputStream openStream() throws IOException {
            return new FileOutputStream(this.file, this.modes.contains(FileWriteMode.APPEND));
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class FileByteSource extends ByteSource {
        public final File file;

        public FileByteSource(File file) {
            file.getClass();
            this.file = file;
        }

        @Override // com.google.common.io.ByteSource
        public byte[] read() throws Throwable {
            Closer closerCreate = Closer.create();
            try {
                FileInputStream fileInputStreamOpenStream = openStream();
                closerCreate.register(fileInputStreamOpenStream);
                return ByteStreams.toByteArray(fileInputStreamOpenStream, fileInputStreamOpenStream.getChannel().size());
            } finally {
            }
        }

        @Override // com.google.common.io.ByteSource
        public long size() throws IOException {
            if (this.file.isFile()) {
                return this.file.length();
            }
            throw new FileNotFoundException(this.file.toString());
        }

        @Override // com.google.common.io.ByteSource
        public Optional<Long> sizeIfKnown() {
            return this.file.isFile() ? new Present(Long.valueOf(this.file.length())) : Absent.INSTANCE;
        }

        public String toString() {
            return "Files.asByteSource(" + this.file + ")";
        }

        @Override // com.google.common.io.ByteSource
        public FileInputStream openStream() throws IOException {
            return new FileInputStream(this.file);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum FilePredicate implements Predicate<File> {
        IS_DIRECTORY { // from class: com.google.common.io.Files.FilePredicate.1
            @Override // com.google.common.base.Predicate
            public boolean apply(File file) {
                return file.isDirectory();
            }

            @Override // java.lang.Enum
            public String toString() {
                return "Files.isDirectory()";
            }

            /* JADX INFO: renamed from: apply, reason: avoid collision after fix types in other method */
            public boolean apply2(File file) {
                return file.isDirectory();
            }
        },
        IS_FILE { // from class: com.google.common.io.Files.FilePredicate.2
            @Override // com.google.common.base.Predicate
            public boolean apply(File file) {
                return file.isFile();
            }

            @Override // java.lang.Enum
            public String toString() {
                return "Files.isFile()";
            }

            /* JADX INFO: renamed from: apply, reason: avoid collision after fix types in other method */
            public boolean apply2(File file) {
                return file.isFile();
            }
        };

        FilePredicate(AnonymousClass1 anonymousClass1) {
        }
    }

    public static /* synthetic */ Iterable $r8$lambda$g7gcEab07m_zNK77QKHEYQHkSNI(File file) {
        File[] fileArrListFiles;
        return (!file.isDirectory() || (fileArrListFiles = file.listFiles()) == null) ? ImmutableList.of() : DesugarCollections.unmodifiableList(Arrays.asList(fileArrListFiles));
    }

    @InlineMe(imports = {"com.google.common.io.FileWriteMode", "com.google.common.io.Files"}, replacement = "Files.asCharSink(to, charset, FileWriteMode.APPEND).write(from)")
    @Deprecated
    public static void append(CharSequence from, File to, Charset charset) throws IOException {
        asCharSink(to, charset, FileWriteMode.APPEND).write(from);
    }

    public static ByteSink asByteSink(File file, FileWriteMode... modes) {
        return new FileByteSink(file, modes);
    }

    public static ByteSource asByteSource(File file) {
        return new FileByteSource(file);
    }

    public static CharSink asCharSink(File file, Charset charset, FileWriteMode... modes) {
        return new ByteSink.AsCharSink(charset);
    }

    public static CharSource asCharSource(File file, Charset charset) {
        return new ByteSource.AsCharSource(charset);
    }

    public static void copy(File from, OutputStream to) throws Throwable {
        new FileByteSource(from).copyTo(to);
    }

    public static void createParentDirs(File file) throws IOException {
        file.getClass();
        File parentFile = file.getCanonicalFile().getParentFile();
        if (parentFile == null) {
            return;
        }
        parentFile.mkdirs();
        if (parentFile.isDirectory()) {
            return;
        }
        throw new IOException("Unable to create parent directories of " + file);
    }

    @Beta
    @Deprecated
    public static File createTempDir() {
        return TempFileCreator.INSTANCE.createTempDir();
    }

    public static boolean equal(File file1, File file2) throws IOException {
        file1.getClass();
        file2.getClass();
        if (file1 == file2 || file1.equals(file2)) {
            return true;
        }
        long length = file1.length();
        long length2 = file2.length();
        if (length == 0 || length2 == 0 || length == length2) {
            return new FileByteSource(file1).contentEquals(new FileByteSource(file2));
        }
        return false;
    }

    public static Traverser<File> fileTraverser() {
        return Traverser.forTree(FILE_TREE);
    }

    public static String getFileExtension(String fullName) {
        fullName.getClass();
        String name = new File(fullName).getName();
        int iLastIndexOf = name.lastIndexOf(46);
        return iLastIndexOf == -1 ? "" : name.substring(iLastIndexOf + 1);
    }

    public static String getNameWithoutExtension(String file) {
        file.getClass();
        String name = new File(file).getName();
        int iLastIndexOf = name.lastIndexOf(46);
        return iLastIndexOf == -1 ? name : name.substring(0, iLastIndexOf);
    }

    @InlineMe(imports = {"com.google.common.io.Files"}, replacement = "Files.asByteSource(file).hash(hashFunction)")
    @Deprecated
    public static HashCode hash(File file, HashFunction hashFunction) throws IOException {
        return new FileByteSource(file).hash(hashFunction);
    }

    public static Predicate<File> isDirectory() {
        return FilePredicate.IS_DIRECTORY;
    }

    public static Predicate<File> isFile() {
        return FilePredicate.IS_FILE;
    }

    public static MappedByteBuffer map(File file, FileChannel.MapMode mode) throws IOException {
        return mapInternal(file, mode, -1L);
    }

    public static MappedByteBuffer mapInternal(File file, FileChannel.MapMode mode, long size) throws Throwable {
        file.getClass();
        mode.getClass();
        Closer closerCreate = Closer.create();
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, mode == FileChannel.MapMode.READ_ONLY ? "r" : "rw");
            closerCreate.register(randomAccessFile);
            FileChannel channel = randomAccessFile.getChannel();
            closerCreate.register(channel);
            if (size == -1) {
                size = channel.size();
            }
            return channel.map(mode, 0L, size);
        } finally {
        }
    }

    public static void move(File from, File to) throws Throwable {
        from.getClass();
        to.getClass();
        Preconditions.checkArgument(!from.equals(to), "Source %s and destination %s must be different", from, to);
        if (from.renameTo(to)) {
            return;
        }
        copy(from, to);
        if (from.delete()) {
            return;
        }
        if (to.delete()) {
            throw new IOException("Unable to delete " + from);
        }
        throw new IOException("Unable to delete " + to);
    }

    public static BufferedReader newReader(File file, Charset charset) throws FileNotFoundException {
        file.getClass();
        charset.getClass();
        return new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
    }

    public static BufferedWriter newWriter(File file, Charset charset) throws FileNotFoundException {
        file.getClass();
        charset.getClass();
        return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charset));
    }

    @Deprecated
    @CanIgnoreReturnValue
    @InlineMe(imports = {"com.google.common.io.Files"}, replacement = "Files.asByteSource(file).read(processor)")
    @ParametricNullness
    public static <T> T readBytes(File file, ByteProcessor<T> byteProcessor) throws IOException {
        return (T) new FileByteSource(file).read(byteProcessor);
    }

    @InlineMe(imports = {"com.google.common.io.Files"}, replacement = "Files.asCharSource(file, charset).readFirstLine()")
    @Deprecated
    public static String readFirstLine(File file, Charset charset) throws IOException {
        return asCharSource(file, charset).readFirstLine();
    }

    public static List<String> readLines(File file, Charset charset) throws IOException {
        return (List) asCharSource(file, charset).readLines(new LineProcessor<List<String>>() { // from class: com.google.common.io.Files.1
            public final List<String> result = new ArrayList();

            @Override // com.google.common.io.LineProcessor
            public boolean processLine(String line) {
                this.result.add(line);
                return true;
            }

            @Override // com.google.common.io.LineProcessor
            public List<String> getResult() {
                return this.result;
            }
        });
    }

    public static String simplifyPath(String pathname) {
        pathname.getClass();
        if (pathname.length() != 0) {
            Splitter.AnonymousClass5 anonymousClass5 = new Splitter.AnonymousClass5(Splitter.on('/').omitEmptyStrings(), pathname);
            ArrayList arrayList = new ArrayList();
            for (String str : anonymousClass5) {
                str.getClass();
                if (!str.equals(ExternalFourCCMapper.CODEC_NAME_SPLITTER)) {
                    if (!str.equals("..")) {
                        arrayList.add(str);
                    } else if (arrayList.size() <= 0 || ((String) arrayList.get(arrayList.size() - 1)).equals("..")) {
                        arrayList.add("..");
                    } else {
                        arrayList.remove(arrayList.size() - 1);
                    }
                }
            }
            String strJoin = Joiner.on('/').join(arrayList.iterator());
            if (pathname.charAt(0) == '/') {
                strJoin = RemoteInput$$ExternalSyntheticOutline0.m(SchemaId.METRIC_SCHEMA_ID_DELIMITER, strJoin);
            }
            while (strJoin.startsWith("/../")) {
                strJoin = strJoin.substring(3);
            }
            if (strJoin.equals("/..")) {
                return SchemaId.METRIC_SCHEMA_ID_DELIMITER;
            }
            if (!strJoin.isEmpty()) {
                return strJoin;
            }
        }
        return ExternalFourCCMapper.CODEC_NAME_SPLITTER;
    }

    public static byte[] toByteArray(File file) throws IOException {
        return new FileByteSource(file).read();
    }

    @InlineMe(imports = {"com.google.common.io.Files"}, replacement = "Files.asCharSource(file, charset).read()")
    @Deprecated
    public static String toString(File file, Charset charset) throws IOException {
        return asCharSource(file, charset).read();
    }

    public static void touch(File file) throws IOException {
        file.getClass();
        if (file.createNewFile() || file.setLastModified(System.currentTimeMillis())) {
            return;
        }
        throw new IOException("Unable to update modification time of " + file);
    }

    public static void write(byte[] from, File to) throws IOException {
        new FileByteSink(to, new FileWriteMode[0]).write(from);
    }

    public static MappedByteBuffer map(File file, FileChannel.MapMode mode, long size) throws IOException {
        Preconditions.checkArgument(size >= 0, "size (%s) may not be negative", size);
        return mapInternal(file, mode, size);
    }

    @Deprecated
    @CanIgnoreReturnValue
    @InlineMe(imports = {"com.google.common.io.Files"}, replacement = "Files.asCharSource(file, charset).readLines(callback)")
    @ParametricNullness
    public static <T> T readLines(File file, Charset charset, LineProcessor<T> lineProcessor) throws IOException {
        return (T) asCharSource(file, charset).readLines(lineProcessor);
    }

    public static void copy(File from, File to) throws Throwable {
        Preconditions.checkArgument(!from.equals(to), "Source %s and destination %s must be different", from, to);
        new FileByteSource(from).copyTo(new FileByteSink(to, new FileWriteMode[0]));
    }

    public static MappedByteBuffer map(File file) throws IOException {
        file.getClass();
        return mapInternal(file, FileChannel.MapMode.READ_ONLY, -1L);
    }

    @InlineMe(imports = {"com.google.common.io.Files"}, replacement = "Files.asCharSink(to, charset).write(from)")
    @Deprecated
    public static void write(CharSequence from, File to, Charset charset) throws IOException {
        asCharSink(to, charset, new FileWriteMode[0]).write(from);
    }

    @InlineMe(imports = {"com.google.common.io.Files"}, replacement = "Files.asCharSource(from, charset).copyTo(to)")
    @Deprecated
    public static void copy(File from, Charset charset, Appendable to) throws Throwable {
        asCharSource(from, charset).copyTo(to);
    }
}
