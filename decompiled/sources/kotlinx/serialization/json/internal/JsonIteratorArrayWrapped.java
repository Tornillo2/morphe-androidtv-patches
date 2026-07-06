package kotlinx.serialization.json.internal;

import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.json.Json;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@SourceDebugExtension({"SMAP\nJsonIterator.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JsonIterator.kt\nkotlinx/serialization/json/internal/JsonIteratorArrayWrapped\n+ 2 AbstractJsonLexer.kt\nkotlinx/serialization/json/internal/AbstractJsonLexer\n+ 3 AbstractJsonLexer.kt\nkotlinx/serialization/json/internal/AbstractJsonLexer$fail$1\n*L\n1#1,103:1\n226#2,10:104\n229#3:114\n*S KotlinDebug\n*F\n+ 1 JsonIterator.kt\nkotlinx/serialization/json/internal/JsonIteratorArrayWrapped\n*L\n99#1:104,10\n99#1:114\n*E\n"})
public final class JsonIteratorArrayWrapped<T> implements Iterator<T>, KMappedMarker {

    @NotNull
    public final DeserializationStrategy<T> deserializer;
    public boolean finished;
    public boolean first;

    @NotNull
    public final Json json;

    @NotNull
    public final ReaderJsonLexer lexer;

    /* JADX WARN: Multi-variable type inference failed */
    public JsonIteratorArrayWrapped(@NotNull Json json, @NotNull ReaderJsonLexer lexer, @NotNull DeserializationStrategy<? extends T> deserializer) {
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(lexer, "lexer");
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        this.json = json;
        this.lexer = lexer;
        this.deserializer = deserializer;
        this.first = true;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (!this.finished) {
            if (this.lexer.peekNextToken() != 9) {
                if (this.lexer.isNotEof() || this.finished) {
                    return true;
                }
                ReaderJsonLexer readerJsonLexer = this.lexer;
                String str = AbstractJsonLexerKt.tokenDescription((byte) 9);
                int i = readerJsonLexer.currentPosition;
                int i2 = i - 1;
                AbstractJsonLexer.fail$default(readerJsonLexer, "Expected " + str + ", but had '" + ((i == readerJsonLexer.getSource().length() || i2 < 0) ? "EOF" : String.valueOf(readerJsonLexer.getSource().charAt(i2))) + "' instead", i2, null, 4, null);
                throw null;
            }
            this.finished = true;
            this.lexer.consumeNextToken((byte) 9);
            if (this.lexer.isNotEof()) {
                if (this.lexer.peekNextToken() != 8) {
                    this.lexer.expectEof();
                    return false;
                }
                AbstractJsonLexer.fail$default(this.lexer, "There is a start of the new array after the one parsed to sequence. ARRAY_WRAPPED mode doesn't merge consecutive arrays.\nIf you need to parse a stream of arrays, please use WHITESPACE_SEPARATED mode instead.", 0, null, 6, null);
                throw null;
            }
        }
        return false;
    }

    @Override // java.util.Iterator
    public T next() {
        if (this.first) {
            this.first = false;
        } else {
            this.lexer.consumeNextToken(',');
        }
        return (T) new StreamingJsonDecoder(this.json, WriteMode.OBJ, this.lexer, this.deserializer.getDescriptor(), null).decodeSerializableValue(this.deserializer);
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
