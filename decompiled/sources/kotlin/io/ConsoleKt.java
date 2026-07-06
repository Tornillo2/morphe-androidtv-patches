package kotlin.io;

import java.io.InputStream;
import java.nio.charset.Charset;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@JvmName(name = "ConsoleKt")
public final class ConsoleKt {
    @InlineOnly
    public static final void print(Object obj) {
        System.out.print(obj);
    }

    @InlineOnly
    public static final void println(Object obj) {
        System.out.println(obj);
    }

    @Nullable
    public static final String readLine() {
        LineReader lineReader = LineReader.INSTANCE;
        InputStream in = System.in;
        Intrinsics.checkNotNullExpressionValue(in, "in");
        Charset charsetDefaultCharset = Charset.defaultCharset();
        Intrinsics.checkNotNullExpressionValue(charsetDefaultCharset, "defaultCharset(...)");
        return lineReader.readLine(in, charsetDefaultCharset);
    }

    @SinceKotlin(version = "1.6")
    @NotNull
    public static final String readln() {
        String line = readLine();
        if (line != null) {
            return line;
        }
        throw new ReadAfterEOFException("EOF has already been reached");
    }

    @SinceKotlin(version = "1.6")
    @Nullable
    public static final String readlnOrNull() {
        return readLine();
    }

    @InlineOnly
    public static final void print(int i) {
        System.out.print(i);
    }

    @InlineOnly
    public static final void println(int i) {
        System.out.println(i);
    }

    @InlineOnly
    public static final void print(long j) {
        System.out.print(j);
    }

    @InlineOnly
    public static final void println(long j) {
        System.out.println(j);
    }

    @InlineOnly
    public static final void print(byte b) {
        System.out.print(Byte.valueOf(b));
    }

    @InlineOnly
    public static final void println(byte b) {
        System.out.println(Byte.valueOf(b));
    }

    @InlineOnly
    public static final void print(short s) {
        System.out.print(Short.valueOf(s));
    }

    @InlineOnly
    public static final void println(short s) {
        System.out.println(Short.valueOf(s));
    }

    @InlineOnly
    public static final void print(char c) {
        System.out.print(c);
    }

    @InlineOnly
    public static final void println(char c) {
        System.out.println(c);
    }

    @InlineOnly
    public static final void print(boolean z) {
        System.out.print(z);
    }

    @InlineOnly
    public static final void println(boolean z) {
        System.out.println(z);
    }

    @InlineOnly
    public static final void print(float f) {
        System.out.print(f);
    }

    @InlineOnly
    public static final void println(float f) {
        System.out.println(f);
    }

    @InlineOnly
    public static final void print(double d) {
        System.out.print(d);
    }

    @InlineOnly
    public static final void println(double d) {
        System.out.println(d);
    }

    @InlineOnly
    public static final void print(char[] message) {
        Intrinsics.checkNotNullParameter(message, "message");
        System.out.print(message);
    }

    @InlineOnly
    public static final void println(char[] message) {
        Intrinsics.checkNotNullParameter(message, "message");
        System.out.println(message);
    }

    @InlineOnly
    public static final void println() {
        System.out.println();
    }
}
