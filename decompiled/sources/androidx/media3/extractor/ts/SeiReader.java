package androidx.media3.extractor.ts;

import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.extractor.CeaUtil;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.ts.TsPayloadReader;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class SeiReader {
    public final List<Format> closedCaptionFormats;
    public final TrackOutput[] outputs;

    public SeiReader(List<Format> list) {
        this.closedCaptionFormats = list;
        this.outputs = new TrackOutput[list.size()];
    }

    public void consume(long j, ParsableByteArray parsableByteArray) {
        CeaUtil.consume(j, parsableByteArray, this.outputs);
    }

    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        for (int i = 0; i < this.outputs.length; i++) {
            trackIdGenerator.generateNewId();
            trackIdGenerator.maybeThrowUninitializedError();
            TrackOutput trackOutputTrack = extractorOutput.track(trackIdGenerator.trackId, 3);
            Format format = this.closedCaptionFormats.get(i);
            String str = format.sampleMimeType;
            Assertions.checkArgument("application/cea-608".equals(str) || "application/cea-708".equals(str), "Invalid closed caption MIME type provided: " + str);
            String str2 = format.id;
            if (str2 == null) {
                trackIdGenerator.maybeThrowUninitializedError();
                str2 = trackIdGenerator.formatId;
            }
            Format.Builder builder = new Format.Builder();
            builder.id = str2;
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
