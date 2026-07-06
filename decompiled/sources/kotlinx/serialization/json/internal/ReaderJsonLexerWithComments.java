package kotlinx.serialization.json.internal;

import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SourceDebugExtension({"SMAP\nCommentLexers.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CommentLexers.kt\nkotlinx/serialization/json/internal/ReaderJsonLexerWithComments\n+ 2 AbstractJsonLexer.kt\nkotlinx/serialization/json/internal/AbstractJsonLexer\n*L\n1#1,219:1\n158#2:220\n*S KotlinDebug\n*F\n+ 1 CommentLexers.kt\nkotlinx/serialization/json/internal/ReaderJsonLexerWithComments\n*L\n204#1:220\n*E\n"})
public final class ReaderJsonLexerWithComments extends ReaderJsonLexer {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReaderJsonLexerWithComments(@NotNull InternalJsonReader reader, @NotNull char[] buffer) {
        super(reader, buffer);
        Intrinsics.checkNotNullParameter(reader, "reader");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
    }

    @Override // kotlinx.serialization.json.internal.ReaderJsonLexer, kotlinx.serialization.json.internal.AbstractJsonLexer
    public boolean canConsumeValue() {
        ensureHaveChars();
        int iSkipWhitespaces = skipWhitespaces();
        ArrayAsSequence arrayAsSequence = this.source;
        if (iSkipWhitespaces >= arrayAsSequence.length || iSkipWhitespaces == -1) {
            return false;
        }
        return isValidValueStart(arrayAsSequence.buffer[iSkipWhitespaces]);
    }

    @Override // kotlinx.serialization.json.internal.ReaderJsonLexer, kotlinx.serialization.json.internal.AbstractJsonLexer
    public void consumeNextToken(char c) {
        ensureHaveChars();
        ArrayAsSequence arrayAsSequence = this.source;
        int iSkipWhitespaces = skipWhitespaces();
        if (iSkipWhitespaces >= arrayAsSequence.length || iSkipWhitespaces == -1) {
            this.currentPosition = -1;
            unexpectedToken(c);
            throw null;
        }
        char c2 = arrayAsSequence.buffer[iSkipWhitespaces];
        this.currentPosition = iSkipWhitespaces + 1;
        if (c2 == c) {
            return;
        }
        unexpectedToken(c);
        throw null;
    }

    public final Pair<Integer, Boolean> handleComment(int i) {
        int i2 = i + 2;
        char c = this.source.buffer[i + 1];
        if (c != '*') {
            if (c != '/') {
                return new Pair<>(Integer.valueOf(i), Boolean.FALSE);
            }
            int iPrefetchOrEof = i2;
            while (i != -1) {
                int iIndexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) this.source, '\n', iPrefetchOrEof, false, 4, (Object) null);
                if (iIndexOf$default != -1) {
                    return new Pair<>(Integer.valueOf(iIndexOf$default + 1), Boolean.TRUE);
                }
                iPrefetchOrEof = prefetchOrEof(this.source.length);
                i = iPrefetchOrEof;
            }
            return new Pair<>(-1, Boolean.TRUE);
        }
        boolean z = false;
        int iPrefetchOrEof2 = i2;
        while (i != -1) {
            int iIndexOf$default2 = StringsKt__StringsKt.indexOf$default((CharSequence) this.source, "*/", iPrefetchOrEof2, false, 4, (Object) null);
            if (iIndexOf$default2 == -1) {
                ArrayAsSequence arrayAsSequence = this.source;
                int i3 = arrayAsSequence.length;
                if (arrayAsSequence.buffer[i3 - 1] == '*') {
                    iPrefetchOrEof2 = prefetchWithinThreshold(i3 - 1);
                    if (z) {
                        break;
                    }
                    i = iPrefetchOrEof2;
                    z = true;
                } else {
                    iPrefetchOrEof2 = prefetchOrEof(i3);
                    i = iPrefetchOrEof2;
                }
            } else {
                return new Pair<>(Integer.valueOf(iIndexOf$default2 + 2), Boolean.TRUE);
            }
        }
        this.currentPosition = this.source.length;
        AbstractJsonLexer.fail$default(this, "Expected end of the block comment: \"*/\", but had EOF instead", 0, null, 6, null);
        throw null;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public byte peekNextToken() {
        ensureHaveChars();
        ArrayAsSequence arrayAsSequence = this.source;
        int iSkipWhitespaces = skipWhitespaces();
        if (iSkipWhitespaces >= arrayAsSequence.length || iSkipWhitespaces == -1) {
            return (byte) 10;
        }
        this.currentPosition = iSkipWhitespaces;
        return AbstractJsonLexerKt.charToTokenClass(arrayAsSequence.buffer[iSkipWhitespaces]);
    }

    public final int prefetchWithinThreshold(int i) {
        if (this.source.length - i > this.threshold) {
            return i;
        }
        this.currentPosition = i;
        ensureHaveChars();
        return (this.currentPosition != 0 || this.source.length() == 0) ? -1 : 0;
    }

    @Override // kotlinx.serialization.json.internal.ReaderJsonLexer, kotlinx.serialization.json.internal.AbstractJsonLexer
    public int skipWhitespaces() {
        int iPrefetchOrEof;
        int i = this.currentPosition;
        while (true) {
            iPrefetchOrEof = prefetchOrEof(i);
            if (iPrefetchOrEof == -1) {
                break;
            }
            ArrayAsSequence arrayAsSequence = this.source;
            char c = arrayAsSequence.buffer[iPrefetchOrEof];
            if (c == ' ' || c == '\n' || c == '\r' || c == '\t') {
                i = iPrefetchOrEof + 1;
            } else {
                if (c != '/' || iPrefetchOrEof + 1 >= arrayAsSequence.length) {
                    break;
                }
                Pair<Integer, Boolean> pairHandleComment = handleComment(iPrefetchOrEof);
                int iIntValue = pairHandleComment.first.intValue();
                if (!pairHandleComment.second.booleanValue()) {
                    iPrefetchOrEof = iIntValue;
                    break;
                }
                i = iIntValue;
            }
        }
        this.currentPosition = iPrefetchOrEof;
        return iPrefetchOrEof;
    }

    @Override // kotlinx.serialization.json.internal.ReaderJsonLexer, kotlinx.serialization.json.internal.AbstractJsonLexer
    public byte consumeNextToken() {
        ensureHaveChars();
        ArrayAsSequence arrayAsSequence = this.source;
        int iSkipWhitespaces = skipWhitespaces();
        if (iSkipWhitespaces >= arrayAsSequence.length || iSkipWhitespaces == -1) {
            return (byte) 10;
        }
        this.currentPosition = iSkipWhitespaces + 1;
        return AbstractJsonLexerKt.charToTokenClass(arrayAsSequence.buffer[iSkipWhitespaces]);
    }
}
