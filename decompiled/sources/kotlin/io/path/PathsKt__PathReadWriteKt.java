package kotlin.io.path;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.WasExperimental;
import kotlin.internal.InlineOnly;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt__FileReadWriteKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt___SequencesKt;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SourceDebugExtension({"SMAP\nPathReadWrite.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PathReadWrite.kt\nkotlin/io/path/PathsKt__PathReadWriteKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 ReadWrite.kt\nkotlin/io/TextStreamsKt\n+ 4 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n*L\n1#1,346:1\n1#2:347\n1#2:349\n57#3:348\n1321#4,2:350\n*S KotlinDebug\n*F\n+ 1 PathReadWrite.kt\nkotlin/io/path/PathsKt__PathReadWriteKt\n*L\n219#1:349\n219#1:348\n219#1:350,2\n*E\n"})
public class PathsKt__PathReadWriteKt {
    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    public static final void appendBytes(Path path, byte[] array) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(array, "array");
        Files.write(path, array, StandardOpenOption.APPEND);
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    public static final Path appendLines(Path path, Iterable<? extends CharSequence> lines, Charset charset) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(lines, "lines");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Path pathWrite = Files.write(path, lines, charset, StandardOpenOption.APPEND);
        Intrinsics.checkNotNullExpressionValue(pathWrite, "write(...)");
        return pathWrite;
    }

    public static /* synthetic */ Path appendLines$default(Path path, Iterable lines, Charset charset, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(lines, "lines");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Path pathWrite = Files.write(path, lines, charset, StandardOpenOption.APPEND);
        Intrinsics.checkNotNullExpressionValue(pathWrite, "write(...)");
        return pathWrite;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    public static final void appendText(@NotNull Path path, @NotNull CharSequence text, @NotNull Charset charset) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(charset, "charset");
        writeText(path, text, charset, StandardOpenOption.APPEND);
    }

    public static /* synthetic */ void appendText$default(Path path, CharSequence charSequence, Charset charset, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        appendText(path, charSequence, charset);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    public static final BufferedReader bufferedReader(Path path, Charset charset, int i, OpenOption... options) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Intrinsics.checkNotNullParameter(options, "options");
        return new BufferedReader(new InputStreamReader(Files.newInputStream(path, (OpenOption[]) Arrays.copyOf(options, options.length)), charset), i);
    }

    public static /* synthetic */ BufferedReader bufferedReader$default(Path path, Charset charset, int i, OpenOption[] options, int i2, Object obj) throws IOException {
        if ((i2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        if ((i2 & 2) != 0) {
            i = 8192;
        }
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Intrinsics.checkNotNullParameter(options, "options");
        return new BufferedReader(new InputStreamReader(Files.newInputStream(path, (OpenOption[]) Arrays.copyOf(options, options.length)), charset), i);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    public static final BufferedWriter bufferedWriter(Path path, Charset charset, int i, OpenOption... options) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Intrinsics.checkNotNullParameter(options, "options");
        return new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(path, (OpenOption[]) Arrays.copyOf(options, options.length)), charset), i);
    }

    public static /* synthetic */ BufferedWriter bufferedWriter$default(Path path, Charset charset, int i, OpenOption[] options, int i2, Object obj) throws IOException {
        if ((i2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        if ((i2 & 2) != 0) {
            i = 8192;
        }
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Intrinsics.checkNotNullParameter(options, "options");
        return new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(path, (OpenOption[]) Arrays.copyOf(options, options.length)), charset), i);
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    public static final void forEachLine(Path path, Charset charset, Function1<? super String, Unit> action) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Intrinsics.checkNotNullParameter(action, "action");
        BufferedReader bufferedReaderNewBufferedReader = Files.newBufferedReader(path, charset);
        Intrinsics.checkNotNullExpressionValue(bufferedReaderNewBufferedReader, "newBufferedReader(...)");
        try {
            Iterator<String> it = TextStreamsKt.lineSequence(bufferedReaderNewBufferedReader).iterator();
            while (it.hasNext()) {
                action.invoke(it.next());
            }
            bufferedReaderNewBufferedReader.close();
        } finally {
        }
    }

    public static void forEachLine$default(Path path, Charset charset, Function1 action, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Intrinsics.checkNotNullParameter(action, "action");
        BufferedReader bufferedReaderNewBufferedReader = Files.newBufferedReader(path, charset);
        Intrinsics.checkNotNullExpressionValue(bufferedReaderNewBufferedReader, "newBufferedReader(...)");
        try {
            Iterator<String> it = TextStreamsKt.lineSequence(bufferedReaderNewBufferedReader).iterator();
            while (it.hasNext()) {
                action.invoke(it.next());
            }
            bufferedReaderNewBufferedReader.close();
        } finally {
        }
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    public static final InputStream inputStream(Path path, OpenOption... options) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(options, "options");
        InputStream inputStreamNewInputStream = Files.newInputStream(path, (OpenOption[]) Arrays.copyOf(options, options.length));
        Intrinsics.checkNotNullExpressionValue(inputStreamNewInputStream, "newInputStream(...)");
        return inputStreamNewInputStream;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    public static final OutputStream outputStream(Path path, OpenOption... options) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(options, "options");
        OutputStream outputStreamNewOutputStream = Files.newOutputStream(path, (OpenOption[]) Arrays.copyOf(options, options.length));
        Intrinsics.checkNotNullExpressionValue(outputStreamNewOutputStream, "newOutputStream(...)");
        return outputStreamNewOutputStream;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    public static final byte[] readBytes(Path path) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        byte[] allBytes = Files.readAllBytes(path);
        Intrinsics.checkNotNullExpressionValue(allBytes, "readAllBytes(...)");
        return allBytes;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    public static final List<String> readLines(Path path, Charset charset) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        List<String> allLines = Files.readAllLines(path, charset);
        Intrinsics.checkNotNullExpressionValue(allLines, "readAllLines(...)");
        return allLines;
    }

    public static /* synthetic */ List readLines$default(Path path, Charset charset, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        List<String> allLines = Files.readAllLines(path, charset);
        Intrinsics.checkNotNullExpressionValue(allLines, "readAllLines(...)");
        return allLines;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @NotNull
    public static final String readText(@NotNull Path path, @NotNull Charset charset) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        InputStreamReader inputStreamReader = new InputStreamReader(Files.newInputStream(path, (OpenOption[]) Arrays.copyOf(new OpenOption[0], 0)), charset);
        try {
            String text = TextStreamsKt.readText(inputStreamReader);
            inputStreamReader.close();
            return text;
        } finally {
        }
    }

    public static /* synthetic */ String readText$default(Path path, Charset charset, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return readText(path, charset);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    public static final InputStreamReader reader(Path path, Charset charset, OpenOption... options) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Intrinsics.checkNotNullParameter(options, "options");
        return new InputStreamReader(Files.newInputStream(path, (OpenOption[]) Arrays.copyOf(options, options.length)), charset);
    }

    public static /* synthetic */ InputStreamReader reader$default(Path path, Charset charset, OpenOption[] options, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Intrinsics.checkNotNullParameter(options, "options");
        return new InputStreamReader(Files.newInputStream(path, (OpenOption[]) Arrays.copyOf(options, options.length)), charset);
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    public static final <T> T useLines(Path path, Charset charset, Function1<? super Sequence<String>, ? extends T> block) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Intrinsics.checkNotNullParameter(block, "block");
        BufferedReader bufferedReaderNewBufferedReader = Files.newBufferedReader(path, charset);
        try {
            Intrinsics.checkNotNull(bufferedReaderNewBufferedReader);
            T tInvoke = block.invoke(TextStreamsKt.lineSequence(bufferedReaderNewBufferedReader));
            CloseableKt.closeFinally(bufferedReaderNewBufferedReader, null);
            return tInvoke;
        } finally {
        }
    }

    public static /* synthetic */ Object useLines$default(Path path, Charset charset, Function1 block, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Intrinsics.checkNotNullParameter(block, "block");
        BufferedReader bufferedReaderNewBufferedReader = Files.newBufferedReader(path, charset);
        try {
            Intrinsics.checkNotNull(bufferedReaderNewBufferedReader);
            Object objInvoke = block.invoke(TextStreamsKt.lineSequence(bufferedReaderNewBufferedReader));
            CloseableKt.closeFinally(bufferedReaderNewBufferedReader, null);
            return objInvoke;
        } finally {
        }
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    public static final void writeBytes(Path path, byte[] array, OpenOption... options) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(array, "array");
        Intrinsics.checkNotNullParameter(options, "options");
        Files.write(path, array, (OpenOption[]) Arrays.copyOf(options, options.length));
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    public static final Path writeLines(Path path, Iterable<? extends CharSequence> lines, Charset charset, OpenOption... options) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(lines, "lines");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Intrinsics.checkNotNullParameter(options, "options");
        Path pathWrite = Files.write(path, lines, charset, (OpenOption[]) Arrays.copyOf(options, options.length));
        Intrinsics.checkNotNullExpressionValue(pathWrite, "write(...)");
        return pathWrite;
    }

    public static /* synthetic */ Path writeLines$default(Path path, Iterable lines, Charset charset, OpenOption[] options, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(lines, "lines");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Intrinsics.checkNotNullParameter(options, "options");
        Path pathWrite = Files.write(path, lines, charset, (OpenOption[]) Arrays.copyOf(options, options.length));
        Intrinsics.checkNotNullExpressionValue(pathWrite, "write(...)");
        return pathWrite;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    public static final void writeText(@NotNull Path path, @NotNull CharSequence text, @NotNull Charset charset, @NotNull OpenOption... options) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Intrinsics.checkNotNullParameter(options, "options");
        OutputStream outputStreamNewOutputStream = Files.newOutputStream(path, (OpenOption[]) Arrays.copyOf(options, options.length));
        try {
            if (text instanceof String) {
                Intrinsics.checkNotNull(outputStreamNewOutputStream);
                FilesKt__FileReadWriteKt.writeTextImpl(outputStreamNewOutputStream, (String) text, charset);
            } else {
                CharsetEncoder charsetEncoderNewReplaceEncoder = FilesKt__FileReadWriteKt.newReplaceEncoder(charset);
                CharBuffer charBufferAsReadOnlyBuffer = text instanceof CharBuffer ? ((CharBuffer) text).asReadOnlyBuffer() : CharBuffer.wrap(text);
                int iMin = Math.min(text.length(), 8192);
                Intrinsics.checkNotNull(charsetEncoderNewReplaceEncoder);
                ByteBuffer byteBufferByteBufferForEncoding = FilesKt__FileReadWriteKt.byteBufferForEncoding(iMin, charsetEncoderNewReplaceEncoder);
                while (charBufferAsReadOnlyBuffer.hasRemaining()) {
                    if (charsetEncoderNewReplaceEncoder.encode(charBufferAsReadOnlyBuffer, byteBufferByteBufferForEncoding, true).isError()) {
                        throw new IllegalStateException("Check failed.");
                    }
                    outputStreamNewOutputStream.write(byteBufferByteBufferForEncoding.array(), 0, byteBufferByteBufferForEncoding.position());
                    byteBufferByteBufferForEncoding.clear();
                }
            }
            CloseableKt.closeFinally(outputStreamNewOutputStream, null);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(outputStreamNewOutputStream, th);
                throw th2;
            }
        }
    }

    public static /* synthetic */ void writeText$default(Path path, CharSequence charSequence, Charset charset, OpenOption[] openOptionArr, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        writeText(path, charSequence, charset, openOptionArr);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    @InlineOnly
    public static final OutputStreamWriter writer(Path path, Charset charset, OpenOption... options) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Intrinsics.checkNotNullParameter(options, "options");
        return new OutputStreamWriter(Files.newOutputStream(path, (OpenOption[]) Arrays.copyOf(options, options.length)), charset);
    }

    public static /* synthetic */ OutputStreamWriter writer$default(Path path, Charset charset, OpenOption[] options, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Intrinsics.checkNotNullParameter(options, "options");
        return new OutputStreamWriter(Files.newOutputStream(path, (OpenOption[]) Arrays.copyOf(options, options.length)), charset);
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    public static final Path appendLines(Path path, Sequence<? extends CharSequence> lines, Charset charset) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(lines, "lines");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Path pathWrite = Files.write(path, SequencesKt___SequencesKt.asIterable(lines), charset, StandardOpenOption.APPEND);
        Intrinsics.checkNotNullExpressionValue(pathWrite, "write(...)");
        return pathWrite;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    public static final Path writeLines(Path path, Sequence<? extends CharSequence> lines, Charset charset, OpenOption... options) throws IOException {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(lines, "lines");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Intrinsics.checkNotNullParameter(options, "options");
        Path pathWrite = Files.write(path, SequencesKt___SequencesKt.asIterable(lines), charset, (OpenOption[]) Arrays.copyOf(options, options.length));
        Intrinsics.checkNotNullExpressionValue(pathWrite, "write(...)");
        return pathWrite;
    }

    public static /* synthetic */ Path appendLines$default(Path path, Sequence lines, Charset charset, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(lines, "lines");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Path pathWrite = Files.write(path, SequencesKt___SequencesKt.asIterable(lines), charset, StandardOpenOption.APPEND);
        Intrinsics.checkNotNullExpressionValue(pathWrite, "write(...)");
        return pathWrite;
    }

    public static /* synthetic */ Path writeLines$default(Path path, Sequence lines, Charset charset, OpenOption[] options, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(lines, "lines");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Intrinsics.checkNotNullParameter(options, "options");
        Path pathWrite = Files.write(path, SequencesKt___SequencesKt.asIterable(lines), charset, (OpenOption[]) Arrays.copyOf(options, options.length));
        Intrinsics.checkNotNullExpressionValue(pathWrite, "write(...)");
        return pathWrite;
    }
}
