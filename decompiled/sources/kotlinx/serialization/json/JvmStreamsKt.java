package kotlinx.serialization.json;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.ExperimentalSerializationApi;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.json.internal.JavaStreamSerialReader;
import kotlinx.serialization.json.internal.JsonStreamsKt;
import kotlinx.serialization.json.internal.JsonToJavaStreamWriter;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class JvmStreamsKt {
    @ExperimentalSerializationApi
    public static final <T> T decodeFromStream(@NotNull Json json, @NotNull DeserializationStrategy<? extends T> deserializer, @NotNull InputStream stream) {
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(stream, "stream");
        JavaStreamSerialReader javaStreamSerialReader = new JavaStreamSerialReader(stream);
        try {
            return (T) JsonStreamsKt.decodeByReader(json, deserializer, javaStreamSerialReader);
        } finally {
            javaStreamSerialReader.release();
        }
    }

    @ExperimentalSerializationApi
    @NotNull
    public static final <T> Sequence<T> decodeToSequence(@NotNull Json json, @NotNull InputStream stream, @NotNull DeserializationStrategy<? extends T> deserializer, @NotNull DecodeSequenceMode format) {
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter(stream, "stream");
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(format, "format");
        return JsonStreamsKt.decodeToSequenceByReader(json, new JavaStreamSerialReader(stream), deserializer, format);
    }

    public static /* synthetic */ Sequence decodeToSequence$default(Json json, InputStream inputStream, DeserializationStrategy deserializationStrategy, DecodeSequenceMode decodeSequenceMode, int i, Object obj) {
        if ((i & 4) != 0) {
            decodeSequenceMode = DecodeSequenceMode.AUTO_DETECT;
        }
        return decodeToSequence(json, inputStream, deserializationStrategy, decodeSequenceMode);
    }

    @ExperimentalSerializationApi
    public static final <T> void encodeToStream(@NotNull Json json, @NotNull SerializationStrategy<? super T> serializer, T t, @NotNull OutputStream stream) throws IOException {
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        Intrinsics.checkNotNullParameter(stream, "stream");
        JsonToJavaStreamWriter jsonToJavaStreamWriter = new JsonToJavaStreamWriter(stream);
        try {
            JsonStreamsKt.encodeByWriter(json, jsonToJavaStreamWriter, serializer, t);
        } finally {
            jsonToJavaStreamWriter.release();
        }
    }

    @ExperimentalSerializationApi
    public static final <T> Sequence<T> decodeToSequence(Json json, InputStream stream, DecodeSequenceMode format) {
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter(stream, "stream");
        Intrinsics.checkNotNullParameter(format, "format");
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    public static Sequence decodeToSequence$default(Json json, InputStream stream, DecodeSequenceMode format, int i, Object obj) {
        if ((i & 2) != 0) {
            format = DecodeSequenceMode.AUTO_DETECT;
        }
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter(stream, "stream");
        Intrinsics.checkNotNullParameter(format, "format");
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    @ExperimentalSerializationApi
    public static final <T> T decodeFromStream(Json json, InputStream stream) {
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter(stream, "stream");
        Intrinsics.throwUndefinedForReified();
        throw null;
    }

    @ExperimentalSerializationApi
    public static final <T> void encodeToStream(Json json, T t, OutputStream stream) {
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter(stream, "stream");
        Intrinsics.throwUndefinedForReified();
        throw null;
    }
}
