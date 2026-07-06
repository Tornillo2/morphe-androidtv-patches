package androidx.media3.extractor.metadata.emsg;

import androidx.media3.common.Metadata;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.extractor.metadata.MetadataInputBuffer;
import androidx.media3.extractor.metadata.SimpleMetadataDecoder;
import java.nio.ByteBuffer;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class EventMessageDecoder extends SimpleMetadataDecoder {
    @Override // androidx.media3.extractor.metadata.SimpleMetadataDecoder
    public Metadata decode(MetadataInputBuffer metadataInputBuffer, ByteBuffer byteBuffer) {
        return new Metadata(decode(new ParsableByteArray(byteBuffer.array(), byteBuffer.limit())));
    }

    public EventMessage decode(ParsableByteArray parsableByteArray) {
        String delimiterTerminatedString = parsableByteArray.readDelimiterTerminatedString((char) 0);
        delimiterTerminatedString.getClass();
        String delimiterTerminatedString2 = parsableByteArray.readDelimiterTerminatedString((char) 0);
        delimiterTerminatedString2.getClass();
        return new EventMessage(delimiterTerminatedString, delimiterTerminatedString2, parsableByteArray.readLong(), parsableByteArray.readLong(), Arrays.copyOfRange(parsableByteArray.data, parsableByteArray.position, parsableByteArray.limit));
    }
}
