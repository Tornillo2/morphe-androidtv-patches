package kotlinx.serialization.json.internal;

import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class ArrayAsSequence implements CharSequence {

    @NotNull
    public final char[] buffer;
    public int length;

    public ArrayAsSequence(@NotNull char[] buffer) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        this.buffer = buffer;
        this.length = buffer.length;
    }

    @Override // java.lang.CharSequence
    public final char charAt(int i) {
        return this.buffer[i];
    }

    public char get(int i) {
        return this.buffer[i];
    }

    @NotNull
    public final char[] getBuffer$kotlinx_serialization_json() {
        return this.buffer;
    }

    public int getLength() {
        return this.length;
    }

    @Override // java.lang.CharSequence
    public final int length() {
        return this.length;
    }

    public void setLength(int i) {
        this.length = i;
    }

    @Override // java.lang.CharSequence
    @NotNull
    public CharSequence subSequence(int i, int i2) {
        return StringsKt__StringsJVMKt.concatToString(this.buffer, i, Math.min(i2, this.length));
    }

    @NotNull
    public final String substring(int i, int i2) {
        return StringsKt__StringsJVMKt.concatToString(this.buffer, i, Math.min(i2, this.length));
    }

    @Override // java.lang.CharSequence
    @NotNull
    public String toString() {
        return substring(0, this.length);
    }

    public final void trim(int i) {
        this.length = Math.min(this.buffer.length, i);
    }
}
