package kotlinx.serialization.json.internal;

import android.support.v4.media.MediaMetadataCompat$Builder$$ExternalSyntheticOutline0;
import java.util.Iterator;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.StringsKt___StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SourceDebugExtension({"SMAP\nStringJsonLexer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StringJsonLexer.kt\nkotlinx/serialization/json/internal/StringJsonLexer\n+ 2 AbstractJsonLexer.kt\nkotlinx/serialization/json/internal/AbstractJsonLexer\n+ 3 AbstractJsonLexer.kt\nkotlinx/serialization/json/internal/AbstractJsonLexer$fail$1\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,129:1\n158#2:130\n158#2:131\n158#2:132\n158#2:133\n226#2,10:134\n229#3:144\n1863#4,2:145\n*S KotlinDebug\n*F\n+ 1 StringJsonLexer.kt\nkotlinx/serialization/json/internal/StringJsonLexer\n*L\n23#1:130\n38#1:131\n57#1:132\n73#1:133\n95#1:134,10\n95#1:144\n109#1:145,2\n*E\n"})
public class StringJsonLexer extends AbstractJsonLexer {

    @NotNull
    public final String source;

    public StringJsonLexer(@NotNull String source) {
        Intrinsics.checkNotNullParameter(source, "source");
        this.source = source;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public boolean canConsumeValue() {
        int i = this.currentPosition;
        if (i == -1) {
            return false;
        }
        String source = getSource();
        while (i < source.length()) {
            char cCharAt = source.charAt(i);
            if (cCharAt != ' ' && cCharAt != '\n' && cCharAt != '\r' && cCharAt != '\t') {
                this.currentPosition = i;
                return isValidValueStart(cCharAt);
            }
            i++;
        }
        this.currentPosition = i;
        return false;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    @NotNull
    public String consumeKeyString() {
        consumeNextToken('\"');
        int i = this.currentPosition;
        int iIndexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) getSource(), '\"', i, false, 4, (Object) null);
        if (iIndexOf$default == -1) {
            consumeStringLenient();
            int i2 = this.currentPosition;
            AbstractJsonLexer.fail$default(this, MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("Expected quotation mark '\"', but had '", (i2 == getSource().length() || i2 < 0) ? "EOF" : String.valueOf(getSource().charAt(i2)), "' instead"), i2, null, 4, null);
            throw null;
        }
        for (int i3 = i; i3 < iIndexOf$default; i3++) {
            if (getSource().charAt(i3) == '\\') {
                return consumeString(getSource(), this.currentPosition, i3);
            }
        }
        this.currentPosition = iIndexOf$default + 1;
        String strSubstring = getSource().substring(i, iIndexOf$default);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return strSubstring;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public byte consumeNextToken() {
        String source = getSource();
        int i = this.currentPosition;
        while (i != -1 && i < source.length()) {
            int i2 = i + 1;
            char cCharAt = source.charAt(i);
            if (cCharAt != ' ' && cCharAt != '\n' && cCharAt != '\r' && cCharAt != '\t') {
                this.currentPosition = i2;
                return AbstractJsonLexerKt.charToTokenClass(cCharAt);
            }
            i = i2;
        }
        this.currentPosition = source.length();
        return (byte) 10;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public void consumeStringChunked(boolean z, @NotNull Function1<? super String, Unit> consumeChunk) {
        Intrinsics.checkNotNullParameter(consumeChunk, "consumeChunk");
        Iterator<T> it = StringsKt___StringsKt.chunked(z ? consumeStringLenient() : consumeString(), 16384).iterator();
        while (it.hasNext()) {
            consumeChunk.invoke(it.next());
        }
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    @Nullable
    public String peekLeadingMatchingValue(@NotNull String keyToMatch, boolean z) {
        Intrinsics.checkNotNullParameter(keyToMatch, "keyToMatch");
        int i = this.currentPosition;
        try {
            if (consumeNextToken() == 6 && Intrinsics.areEqual(peekString(z), keyToMatch)) {
                this.peekedString = null;
                if (consumeNextToken() == 5) {
                    return peekString(z);
                }
            }
            return null;
        } finally {
            this.currentPosition = i;
            this.peekedString = null;
        }
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public int prefetchOrEof(int i) {
        if (i < getSource().length()) {
            return i;
        }
        return -1;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public int skipWhitespaces() {
        char cCharAt;
        int i = this.currentPosition;
        if (i == -1) {
            return i;
        }
        String source = getSource();
        while (i < source.length() && ((cCharAt = source.charAt(i)) == ' ' || cCharAt == '\n' || cCharAt == '\r' || cCharAt == '\t')) {
            i++;
        }
        this.currentPosition = i;
        return i;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    @NotNull
    public String getSource() {
        return this.source;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public void consumeNextToken(char c) {
        if (this.currentPosition != -1) {
            String source = getSource();
            int i = this.currentPosition;
            while (i < source.length()) {
                int i2 = i + 1;
                char cCharAt = source.charAt(i);
                if (cCharAt != ' ' && cCharAt != '\n' && cCharAt != '\r' && cCharAt != '\t') {
                    this.currentPosition = i2;
                    if (cCharAt == c) {
                        return;
                    }
                    unexpectedToken(c);
                    throw null;
                }
                i = i2;
            }
            this.currentPosition = -1;
            unexpectedToken(c);
            throw null;
        }
        unexpectedToken(c);
        throw null;
    }
}
