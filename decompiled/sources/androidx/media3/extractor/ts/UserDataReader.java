package androidx.media3.extractor.ts;

import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.CeaUtil;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.ts.TsPayloadReader;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class UserDataReader {
    public static final int USER_DATA_START_CODE = 434;
    public final List<Format> closedCaptionFormats;
    public final TrackOutput[] outputs;

    public UserDataReader(List<Format> list) {
        this.closedCaptionFormats = list;
        this.outputs = new TrackOutput[list.size()];
    }

    public void consume(long j, ParsableByteArray parsableByteArray) {
        if (parsableByteArray.bytesLeft() < 9) {
            return;
        }
        int i = parsableByteArray.readInt();
        int i2 = parsableByteArray.readInt();
        int unsignedByte = parsableByteArray.readUnsignedByte();
        if (i == 434 && i2 == 1195456820 && unsignedByte == 3) {
            CeaUtil.consumeCcData(j, parsableByteArray, this.outputs);
        }
    }

    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        for (int i = 0; i < this.outputs.length; i++) {
            trackIdGenerator.generateNewId();
            trackIdGenerator.maybeThrowUninitializedError();
            TrackOutput trackOutputTrack = extractorOutput.track(trackIdGenerator.trackId, 3);
            Format format = this.closedCaptionFormats.get(i);
            String str = format.sampleMimeType;
            Assertions.checkArgument("application/cea-608".equals(str) || "application/cea-708".equals(str), "Invalid closed caption MIME type provided: " + str);
            Format.Builder builder = new Format.Builder();
            trackIdGenerator.maybeThrowUninitializedError();
            builder.id = trackIdGenerator.formatId;
            builder.sampleMimeType = MimeTypes.normalizeMimeType(str);
            builder.selectionFlags = format.selectionFlags;
            builder.language = format.language;
            builder.accessibilityChannel = format.accessibilityChannel;
            builder.initializationData = format.initializationData;
            trackOutputTrack.format(new Format(builder));
            this.outputs[i] = trackOutputTrack;
        }
    }
}
