package kotlinx.serialization.json.internal;

import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class CharMappings {

    @JvmField
    @NotNull
    public static final byte[] CHAR_TO_TOKEN;

    @JvmField
    @NotNull
    public static final char[] ESCAPE_2_CHAR;

    @NotNull
    public static final CharMappings INSTANCE;

    static {
        CharMappings charMappings = new CharMappings();
        INSTANCE = charMappings;
        ESCAPE_2_CHAR = new char[AbstractJsonLexerKt.ESC2C_MAX];
        CHAR_TO_TOKEN = new byte[126];
        charMappings.initEscape();
        charMappings.initCharToToken();
    }

    public final void initC2ESC(int i, char c) {
        if (c != 'u') {
            ESCAPE_2_CHAR[c] = (char) i;
        }
    }

    public final void initC2TC(char c, byte b) {
        CHAR_TO_TOKEN[c] = b;
    }

    public final void initCharToToken() {
        for (int i = 0; i < 33; i++) {
            CHAR_TO_TOKEN[i] = 127;
        }
        byte[] bArr = CHAR_TO_TOKEN;
        bArr[9] = 3;
        bArr[10] = 3;
        bArr[13] = 3;
        bArr[32] = 3;
        bArr[44] = 4;
        bArr[58] = 5;
        bArr[123] = 6;
        bArr[125] = 7;
        bArr[91] = 8;
        bArr[93] = 9;
        bArr[34] = 1;
        bArr[92] = 2;
    }

    public final void initEscape() {
        for (int i = 0; i < 32; i++) {
        }
        initC2ESC(8, 'b');
        initC2ESC(9, 't');
        initC2ESC(10, 'n');
        initC2ESC(12, 'f');
        initC2ESC(13, 'r');
        initC2ESC(47, '/');
        initC2ESC(34, '\"');
        initC2ESC(92, '\\');
    }

    public final void initC2ESC(char c, char c2) {
        initC2ESC((int) c, c2);
    }

    public final void initC2TC(int i, byte b) {
        CHAR_TO_TOKEN[i] = b;
    }
}
