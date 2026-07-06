package kotlinx.serialization.json.internal;

import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class JsonToStringWriter implements InternalJsonWriter {

    @NotNull
    public char[] array = CharArrayPool.INSTANCE.take(128);
    public int size;

    private final int ensureTotalCapacity(int i, int i2) {
        int i3 = i2 + i;
        char[] cArr = this.array;
        if (cArr.length <= i3) {
            int i4 = i * 2;
            if (i3 < i4) {
                i3 = i4;
            }
            char[] cArrCopyOf = Arrays.copyOf(cArr, i3);
            Intrinsics.checkNotNullExpressionValue(cArrCopyOf, "copyOf(...)");
            this.array = cArrCopyOf;
        }
        return i;
    }

    public final void appendStringSlowPath(int i, int i2, String str) {
        byte b;
        int length = str.length();
        while (i < length) {
            ensureTotalCapacity(i2, 2);
            char cCharAt = str.charAt(i);
            if (cCharAt >= StringOpsKt.getESCAPE_MARKERS().length || (b = StringOpsKt.ESCAPE_MARKERS[cCharAt]) == 0) {
                int i3 = i2 + 1;
                this.array[i2] = cCharAt;
                i2 = i3;
                i++;
            } else {
                if (b == 1) {
                    String str2 = StringOpsKt.ESCAPE_STRINGS[cCharAt];
                    Intrinsics.checkNotNull(str2);
                    ensureTotalCapacity(i2, str2.length());
                    str2.getChars(0, str2.length(), this.array, i2);
                    int length2 = str2.length() + i2;
                    this.size = length2;
                    i2 = length2;
                } else {
                    char[] cArr = this.array;
                    cArr[i2] = '\\';
                    cArr[i2 + 1] = (char) b;
                    i2 += 2;
                    this.size = i2;
                }
                i++;
            }
        }
        ensureTotalCapacity(i2, 1);
        this.array[i2] = '\"';
        this.size = i2 + 1;
    }

    public final void ensureAdditionalCapacity(int i) {
        ensureTotalCapacity(this.size, i);
    }

    @Override // kotlinx.serialization.json.internal.InternalJsonWriter
    public void release() {
        CharArrayPool.INSTANCE.release(this.array);
    }

    @NotNull
    public String toString() {
        return new String(this.array, 0, this.size);
    }

    @Override // kotlinx.serialization.json.internal.InternalJsonWriter
    public void write(@NotNull String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        int length = text.length();
        if (length == 0) {
            return;
        }
        ensureAdditionalCapacity(length);
        text.getChars(0, text.length(), this.array, this.size);
        this.size += length;
    }

    @Override // kotlinx.serialization.json.internal.InternalJsonWriter
    public void writeChar(char c) {
        ensureAdditionalCapacity(1);
        char[] cArr = this.array;
        int i = this.size;
        this.size = i + 1;
        cArr[i] = c;
    }

    @Override // kotlinx.serialization.json.internal.InternalJsonWriter
    public void writeLong(long j) {
        write(String.valueOf(j));
    }

    @Override // kotlinx.serialization.json.internal.InternalJsonWriter
    public void writeQuoted(@NotNull String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        ensureAdditionalCapacity(text.length() + 2);
        char[] cArr = this.array;
        int i = this.size;
        int i2 = i + 1;
        cArr[i] = '\"';
        int length = text.length();
        text.getChars(0, length, cArr, i2);
        int i3 = length + i2;
        for (int i4 = i2; i4 < i3; i4++) {
            char c = cArr[i4];
            if (c < StringOpsKt.getESCAPE_MARKERS().length && StringOpsKt.ESCAPE_MARKERS[c] != 0) {
                appendStringSlowPath(i4 - i2, i4, text);
                return;
            }
        }
        cArr[i3] = '\"';
        this.size = i3 + 1;
    }
}
