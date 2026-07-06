package kotlinx.serialization.json.internal;

import android.support.v4.media.MediaMetadataCompat$Builder$$ExternalSyntheticOutline0;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SourceDebugExtension({"SMAP\nReaderJsonLexer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ReaderJsonLexer.kt\nkotlinx/serialization/json/internal/ReaderJsonLexer\n+ 2 AbstractJsonLexer.kt\nkotlinx/serialization/json/internal/AbstractJsonLexer\n+ 3 AbstractJsonLexer.kt\nkotlinx/serialization/json/internal/AbstractJsonLexer$fail$1\n*L\n1#1,221:1\n158#2:222\n158#2:223\n158#2:224\n226#2,10:225\n229#3:235\n*S KotlinDebug\n*F\n+ 1 ReaderJsonLexer.kt\nkotlinx/serialization/json/internal/ReaderJsonLexer\n*L\n66#1:222\n133#1:223\n150#1:224\n181#1:225,10\n181#1:235\n*E\n"})
public class ReaderJsonLexer extends AbstractJsonLexer {

    @NotNull
    public final char[] buffer;

    @NotNull
    public final InternalJsonReader reader;

    @NotNull
    public final ArrayAsSequence source;

    @JvmField
    public int threshold;

    public ReaderJsonLexer(InternalJsonReader internalJsonReader, char[] cArr, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(internalJsonReader, (i & 2) != 0 ? CharArrayPoolBatchSize.INSTANCE.take(16384) : cArr);
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public void appendRange(int i, int i2) {
        this.escapedString.append(getSource().buffer, i, i2 - i);
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public boolean canConsumeValue() {
        ensureHaveChars();
        int i = this.currentPosition;
        while (true) {
            int iPrefetchOrEof = prefetchOrEof(i);
            if (iPrefetchOrEof == -1) {
                this.currentPosition = iPrefetchOrEof;
                return false;
            }
            char c = getSource().buffer[iPrefetchOrEof];
            if (c != ' ' && c != '\n' && c != '\r' && c != '\t') {
                this.currentPosition = iPrefetchOrEof;
                return isValidValueStart(c);
            }
            i = iPrefetchOrEof + 1;
        }
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    @NotNull
    public String consumeKeyString() {
        consumeNextToken('\"');
        int i = this.currentPosition;
        int iIndexOf = indexOf('\"', i);
        if (iIndexOf == -1) {
            int iPrefetchOrEof = prefetchOrEof(i);
            if (iPrefetchOrEof != -1) {
                return consumeString(getSource(), this.currentPosition, iPrefetchOrEof);
            }
            int i2 = this.currentPosition;
            int i3 = i2 - 1;
            AbstractJsonLexer.fail$default(this, MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("Expected quotation mark '\"', but had '", (i2 == getSource().length() || i3 < 0) ? "EOF" : String.valueOf(getSource().charAt(i3)), "' instead"), i3, null, 4, null);
            throw null;
        }
        for (int i4 = i; i4 < iIndexOf; i4++) {
            if (getSource().buffer[i4] == '\\') {
                return consumeString(getSource(), this.currentPosition, i4);
            }
        }
        this.currentPosition = iIndexOf + 1;
        return substring(i, iIndexOf);
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public byte consumeNextToken() {
        ensureHaveChars();
        ArrayAsSequence source = getSource();
        int i = this.currentPosition;
        while (true) {
            int iPrefetchOrEof = prefetchOrEof(i);
            if (iPrefetchOrEof == -1) {
                this.currentPosition = iPrefetchOrEof;
                return (byte) 10;
            }
            int i2 = iPrefetchOrEof + 1;
            byte bCharToTokenClass = AbstractJsonLexerKt.charToTokenClass(source.buffer[iPrefetchOrEof]);
            if (bCharToTokenClass != 3) {
                this.currentPosition = i2;
                return bCharToTokenClass;
            }
            i = i2;
        }
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public void ensureHaveChars() {
        int i = getSource().length - this.currentPosition;
        if (i > this.threshold) {
            return;
        }
        preload(i);
    }

    @NotNull
    public final char[] getBuffer() {
        return this.buffer;
    }

    @NotNull
    public final InternalJsonReader getReader() {
        return this.reader;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public int indexOf(char c, int i) {
        ArrayAsSequence source = getSource();
        int i2 = source.length;
        while (i < i2) {
            if (source.buffer[i] == c) {
                return i;
            }
            i++;
        }
        return -1;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    @Nullable
    public String peekLeadingMatchingValue(@NotNull String keyToMatch, boolean z) {
        Intrinsics.checkNotNullParameter(keyToMatch, "keyToMatch");
        return null;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public int prefetchOrEof(int i) {
        if (i < getSource().length) {
            return i;
        }
        this.currentPosition = i;
        ensureHaveChars();
        return (this.currentPosition != 0 || getSource().length == 0) ? -1 : 0;
    }

    public final void preload(int i) {
        char[] cArr = getSource().buffer;
        if (i != 0) {
            int i2 = this.currentPosition;
            ArraysKt___ArraysJvmKt.copyInto(cArr, cArr, 0, i2, i2 + i);
        }
        int i3 = getSource().length;
        while (true) {
            if (i == i3) {
                break;
            }
            int i4 = this.reader.read(cArr, i, i3 - i);
            if (i4 == -1) {
                getSource().trim(i);
                this.threshold = -1;
                break;
            }
            i += i4;
        }
        this.currentPosition = 0;
    }

    public final void release() {
        CharArrayPoolBatchSize.INSTANCE.release(this.buffer);
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public int skipWhitespaces() {
        int iPrefetchOrEof;
        char c;
        int i = this.currentPosition;
        while (true) {
            iPrefetchOrEof = prefetchOrEof(i);
            if (iPrefetchOrEof == -1 || !((c = getSource().buffer[iPrefetchOrEof]) == ' ' || c == '\n' || c == '\r' || c == '\t')) {
                break;
            }
            i = iPrefetchOrEof + 1;
        }
        this.currentPosition = iPrefetchOrEof;
        return iPrefetchOrEof;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    @NotNull
    public String substring(int i, int i2) {
        return getSource().substring(i, i2);
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    @NotNull
    public ArrayAsSequence getSource() {
        return this.source;
    }

    public ReaderJsonLexer(@NotNull InternalJsonReader reader, @NotNull char[] buffer) {
        Intrinsics.checkNotNullParameter(reader, "reader");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        this.reader = reader;
        this.buffer = buffer;
        this.threshold = 128;
        this.source = new ArrayAsSequence(buffer);
        preload(0);
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public void consumeNextToken(char c) {
        ensureHaveChars();
        ArrayAsSequence source = getSource();
        int i = this.currentPosition;
        while (true) {
            int iPrefetchOrEof = prefetchOrEof(i);
            if (iPrefetchOrEof != -1) {
                int i2 = iPrefetchOrEof + 1;
                char c2 = source.buffer[iPrefetchOrEof];
                if (c2 != ' ' && c2 != '\n' && c2 != '\r' && c2 != '\t') {
                    this.currentPosition = i2;
                    if (c2 == c) {
                        return;
                    }
                    unexpectedToken(c);
                    throw null;
                }
                i = i2;
            } else {
                this.currentPosition = iPrefetchOrEof;
                unexpectedToken(c);
                throw null;
            }
        }
    }
}
