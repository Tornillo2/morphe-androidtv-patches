package kotlinx.serialization.json.internal;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@JsonFriendModuleApi
public abstract class InternalJsonReaderCodePointImpl implements InternalJsonReader {

    @Nullable
    public Character bufferedChar;

    public abstract boolean exhausted();

    public abstract int nextCodePoint();

    @Override // kotlinx.serialization.json.internal.InternalJsonReader
    public final int read(@NotNull char[] buffer, int i, int i2) {
        int i3;
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Character ch = this.bufferedChar;
        if (ch != null) {
            Intrinsics.checkNotNull(ch);
            buffer[i] = ch.charValue();
            this.bufferedChar = null;
            i3 = 1;
        } else {
            i3 = 0;
        }
        while (i3 < i2 && !exhausted()) {
            int iNextCodePoint = nextCodePoint();
            if (iNextCodePoint <= 65535) {
                buffer[i + i3] = (char) iNextCodePoint;
                i3++;
            } else {
                char c = (char) ((iNextCodePoint >>> 10) + JsonStreamsKt.HIGH_SURROGATE_HEADER);
                char c2 = (char) ((iNextCodePoint & 1023) + 56320);
                buffer[i + i3] = c;
                int i4 = i3 + 1;
                if (i4 < i2) {
                    buffer[i4 + i] = c2;
                    i3 += 2;
                } else {
                    this.bufferedChar = Character.valueOf(c2);
                    i3 = i4;
                }
            }
        }
        if (i3 > 0) {
            return i3;
        }
        return -1;
    }
}
