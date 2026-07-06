package kotlinx.serialization.json.internal;

import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SourceDebugExtension({"SMAP\nCommentLexers.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CommentLexers.kt\nkotlinx/serialization/json/internal/StringJsonLexerWithComments\n+ 2 AbstractJsonLexer.kt\nkotlinx/serialization/json/internal/AbstractJsonLexer\n*L\n1#1,219:1\n158#2:220\n*S KotlinDebug\n*F\n+ 1 CommentLexers.kt\nkotlinx/serialization/json/internal/StringJsonLexerWithComments\n*L\n66#1:220\n*E\n"})
public final class StringJsonLexerWithComments extends StringJsonLexer {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StringJsonLexerWithComments(@NotNull String source) {
        super(source);
        Intrinsics.checkNotNullParameter(source, "source");
    }

    @Override // kotlinx.serialization.json.internal.StringJsonLexer, kotlinx.serialization.json.internal.AbstractJsonLexer
    public boolean canConsumeValue() {
        int iSkipWhitespaces = skipWhitespaces();
        if (iSkipWhitespaces >= this.source.length() || iSkipWhitespaces == -1) {
            return false;
        }
        return isValidValueStart(this.source.charAt(iSkipWhitespaces));
    }

    @Override // kotlinx.serialization.json.internal.StringJsonLexer, kotlinx.serialization.json.internal.AbstractJsonLexer
    public byte consumeNextToken() {
        String str = this.source;
        int iSkipWhitespaces = skipWhitespaces();
        if (iSkipWhitespaces >= str.length() || iSkipWhitespaces == -1) {
            return (byte) 10;
        }
        this.currentPosition = iSkipWhitespaces + 1;
        return AbstractJsonLexerKt.charToTokenClass(str.charAt(iSkipWhitespaces));
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public byte peekNextToken() {
        String str = this.source;
        int iSkipWhitespaces = skipWhitespaces();
        if (iSkipWhitespaces >= str.length() || iSkipWhitespaces == -1) {
            return (byte) 10;
        }
        this.currentPosition = iSkipWhitespaces;
        return AbstractJsonLexerKt.charToTokenClass(str.charAt(iSkipWhitespaces));
    }

    @Override // kotlinx.serialization.json.internal.StringJsonLexer, kotlinx.serialization.json.internal.AbstractJsonLexer
    public int skipWhitespaces() {
        int i;
        int iIndexOf$default = this.currentPosition;
        if (iIndexOf$default == -1) {
            return iIndexOf$default;
        }
        String str = this.source;
        while (iIndexOf$default < str.length()) {
            char cCharAt = str.charAt(iIndexOf$default);
            if (cCharAt != ' ' && cCharAt != '\n' && cCharAt != '\r' && cCharAt != '\t') {
                if (cCharAt != '/' || (i = iIndexOf$default + 1) >= str.length()) {
                    break;
                }
                char cCharAt2 = str.charAt(i);
                if (cCharAt2 == '*') {
                    int iIndexOf$default2 = StringsKt__StringsKt.indexOf$default((CharSequence) str, "*/", iIndexOf$default + 2, false, 4, (Object) null);
                    if (iIndexOf$default2 == -1) {
                        this.currentPosition = str.length();
                        AbstractJsonLexer.fail$default(this, "Expected end of the block comment: \"*/\", but had EOF instead", 0, null, 6, null);
                        throw null;
                    }
                    iIndexOf$default = iIndexOf$default2 + 2;
                } else {
                    if (cCharAt2 != '/') {
                        break;
                    }
                    iIndexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) str, '\n', iIndexOf$default + 2, false, 4, (Object) null);
                    iIndexOf$default = iIndexOf$default == -1 ? str.length() : iIndexOf$default + 1;
                }
            }
        }
        this.currentPosition = iIndexOf$default;
        return iIndexOf$default;
    }

    @Override // kotlinx.serialization.json.internal.StringJsonLexer, kotlinx.serialization.json.internal.AbstractJsonLexer
    public void consumeNextToken(char c) {
        String str = this.source;
        int iSkipWhitespaces = skipWhitespaces();
        if (iSkipWhitespaces < str.length() && iSkipWhitespaces != -1) {
            char cCharAt = str.charAt(iSkipWhitespaces);
            this.currentPosition = iSkipWhitespaces + 1;
            if (cCharAt == c) {
                return;
            }
            unexpectedToken(c);
            throw null;
        }
        this.currentPosition = -1;
        unexpectedToken(c);
        throw null;
    }
}
