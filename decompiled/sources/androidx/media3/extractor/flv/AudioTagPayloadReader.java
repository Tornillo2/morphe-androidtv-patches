package androidx.media3.extractor.flv;

import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.AacUtil;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.flv.TagPayloadReader;
import java.util.Collections;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class AudioTagPayloadReader extends TagPayloadReader {
    public static final int AAC_PACKET_TYPE_AAC_RAW = 1;
    public static final int AAC_PACKET_TYPE_SEQUENCE_HEADER = 0;
    public static final int AUDIO_FORMAT_AAC = 10;
    public static final int AUDIO_FORMAT_ALAW = 7;
    public static final int AUDIO_FORMAT_MP3 = 2;
    public static final int AUDIO_FORMAT_ULAW = 8;
    public static final int[] AUDIO_SAMPLING_RATE_TABLE = {5512, 11025, 22050, 44100};
    public int audioFormat;
    public boolean hasOutputFormat;
    public boolean hasParsedAudioDataHeader;

    public AudioTagPayloadReader(TrackOutput trackOutput) {
        super(trackOutput);
    }

    @Override // androidx.media3.extractor.flv.TagPayloadReader
    public boolean parseHeader(ParsableByteArray parsableByteArray) throws TagPayloadReader.UnsupportedFormatException {
        if (this.hasParsedAudioDataHeader) {
            parsableByteArray.skipBytes(1);
        } else {
            int unsignedByte = parsableByteArray.readUnsignedByte();
            int i = (unsignedByte >> 4) & 15;
            this.audioFormat = i;
            if (i == 2) {
                int i2 = AUDIO_SAMPLING_RATE_TABLE[(unsignedByte >> 2) & 3];
                Format.Builder builder = new Format.Builder();
                builder.sampleMimeType = MimeTypes.normalizeMimeType("audio/mpeg");
                builder.channelCount = 1;
                builder.sampleRate = i2;
                this.output.format(new Format(builder));
                this.hasOutputFormat = true;
            } else if (i == 7 || i == 8) {
                String str = i == 7 ? "audio/g711-alaw" : "audio/g711-mlaw";
                Format.Builder builder2 = new Format.Builder();
                builder2.sampleMimeType = MimeTypes.normalizeMimeType(str);
                builder2.channelCount = 1;
                builder2.sampleRate = 8000;
                this.output.format(new Format(builder2));
                this.hasOutputFormat = true;
            } else if (i != 10) {
                throw new TagPayloadReader.UnsupportedFormatException("Audio format not supported: " + this.audioFormat);
            }
            this.hasParsedAudioDataHeader = true;
        }
        return true;
    }

    @Override // androidx.media3.extractor.flv.TagPayloadReader
    public boolean parsePayload(ParsableByteArray parsableByteArray, long j) throws ParserException {
        if (this.audioFormat == 2) {
            int iBytesLeft = parsableByteArray.bytesLeft();
            this.output.sampleData(parsableByteArray, iBytesLeft);
            this.output.sampleMetadata(j, 1, iBytesLeft, 0, null);
            return true;
        }
        int unsignedByte = parsableByteArray.readUnsignedByte();
        if (unsignedByte != 0 || this.hasOutputFormat) {
            if (this.audioFormat == 10 && unsignedByte != 1) {
                return false;
            }
            int iBytesLeft2 = parsableByteArray.bytesLeft();
            this.output.sampleData(parsableByteArray, iBytesLeft2);
            this.output.sampleMetadata(j, 1, iBytesLeft2, 0, null);
            return true;
        }
        int iBytesLeft3 = parsableByteArray.bytesLeft();
        byte[] bArr = new byte[iBytesLeft3];
        parsableByteArray.readBytes(bArr, 0, iBytesLeft3);
        AacUtil.Config audioSpecificConfig = AacUtil.parseAudioSpecificConfig(bArr);
        Format.Builder builder = new Format.Builder();
        builder.sampleMimeType = MimeTypes.normalizeMimeType("audio/mp4a-latm");
        builder.codecs = audioSpecificConfig.codecs;
        builder.channelCount = audioSpecificConfig.channelCount;
        builder.sampleRate = audioSpecificConfig.sampleRateHz;
        builder.initializationData = Collections.singletonList(bArr);
        this.output.format(new Format(builder));
        this.hasOutputFormat = true;
        return false;
    }

    @Override // androidx.media3.extractor.flv.TagPayloadReader
    public void seek() {
    }
}
