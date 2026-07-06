package kotlinx.serialization.json.internal;

import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class Composer {

    @JvmField
    @NotNull
    public final InternalJsonWriter writer;
    public boolean writingFirst;

    public Composer(@NotNull InternalJsonWriter writer) {
        Intrinsics.checkNotNullParameter(writer, "writer");
        this.writer = writer;
        this.writingFirst = true;
    }

    public final boolean getWritingFirst() {
        return this.writingFirst;
    }

    public void indent() {
        this.writingFirst = true;
    }

    public void nextItem() {
        this.writingFirst = false;
    }

    public void nextItemIfNotFirst() {
        this.writingFirst = false;
    }

    public final void print(char c) {
        this.writer.writeChar(c);
    }

    public void printQuoted(@NotNull String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.writer.writeQuoted(value);
    }

    public final void setWritingFirst(boolean z) {
        this.writingFirst = z;
    }

    public final void print(@NotNull String v) {
        Intrinsics.checkNotNullParameter(v, "v");
        this.writer.write(v);
    }

    public void print(float f) {
        this.writer.write(String.valueOf(f));
    }

    public void print(double d) {
        this.writer.write(String.valueOf(d));
    }

    public void print(byte b) {
        this.writer.writeLong(b);
    }

    public void print(short s) {
        this.writer.writeLong(s);
    }

    public void print(int i) {
        this.writer.writeLong(i);
    }

    public void print(long j) {
        this.writer.writeLong(j);
    }

    public void print(boolean z) {
        this.writer.write(String.valueOf(z));
    }

    public void space() {
    }

    public void unIndent() {
    }
}
