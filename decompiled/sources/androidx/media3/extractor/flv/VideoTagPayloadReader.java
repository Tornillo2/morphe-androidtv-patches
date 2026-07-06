package androidx.media3.extractor.flv;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.extractor.AvcConfig;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.flv.TagPayloadReader;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class VideoTagPayloadReader extends TagPayloadReader {
    public static final int AVC_PACKET_TYPE_AVC_NALU = 1;
    public static final int AVC_PACKET_TYPE_SEQUENCE_HEADER = 0;
    public static final int VIDEO_CODEC_AVC = 7;
    public static final int VIDEO_FRAME_KEYFRAME = 1;
    public static final int VIDEO_FRAME_VIDEO_INFO = 5;
    public int frameType;
    public boolean hasOutputFormat;
    public boolean hasOutputKeyframe;
    public final ParsableByteArray nalLength;
    public final ParsableByteArray nalStartCode;
    public int nalUnitLengthFieldLength;

    public VideoTagPayloadReader(TrackOutput trackOutput) {
        super(trackOutput);
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalLength = new ParsableByteArray(4);
    }

    @Override // androidx.media3.extractor.flv.TagPayloadReader
    public boolean parseHeader(ParsableByteArray parsableByteArray) throws TagPayloadReader.UnsupportedFormatException {
        int unsignedByte = parsableByteArray.readUnsignedByte();
        int i = (unsignedByte >> 4) & 15;
        int i2 = unsignedByte & 15;
        if (i2 != 7) {
            throw new TagPayloadReader.UnsupportedFormatException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Video format not supported: ", i2));
        }
        this.frameType = i;
        return i != 5;
    }

    @Override // androidx.media3.extractor.flv.TagPayloadReader
    public boolean parsePayload(ParsableByteArray parsableByteArray, long j) throws ParserException {
        int unsignedByte = parsableByteArray.readUnsignedByte();
        long int24 = (((long) parsableByteArray.readInt24()) * 1000) + j;
        if (unsignedByte == 0 && !this.hasOutputFormat) {
            byte[] bArr = new byte[parsableByteArray.bytesLeft()];
            ParsableByteArray parsableByteArray2 = new ParsableByteArray(bArr);
            parsableByteArray.readBytes(bArr, 0, parsableByteArray.bytesLeft());
            AvcConfig avcConfig = AvcConfig.parse(parsableByteArray2);
            this.nalUnitLengthFieldLength = avcConfig.nalUnitLengthFieldLength;
            Format.Builder builder = new Format.Builder();
            builder.sampleMimeType = MimeTypes.normalizeMimeType("video/avc");
            builder.codecs = avcConfig.codecs;
            builder.width = avcConfig.width;
            builder.height = avcConfig.height;
            builder.pixelWidthHeightRatio = avcConfig.pixelWidthHeightRatio;
            builder.initializationData = avcConfig.initializationData;
            this.output.format(new Format(builder));
            this.hasOutputFormat = true;
            return false;
        }
        if (unsignedByte == 1 && this.hasOutputFormat) {
            int i = this.frameType == 1 ? 1 : 0;
            if (this.hasOutputKeyframe || i != 0) {
                byte[] bArr2 = this.nalLength.data;
                bArr2[0] = 0;
                bArr2[1] = 0;
                bArr2[2] = 0;
                int i2 = 4 - this.nalUnitLengthFieldLength;
                int i3 = 0;
                while (parsableByteArray.bytesLeft() > 0) {
                    parsableByteArray.readBytes(this.nalLength.data, i2, this.nalUnitLengthFieldLength);
                    this.nalLength.setPosition(0);
                    int unsignedIntToInt = this.nalLength.readUnsignedIntToInt();
                    this.nalStartCode.setPosition(0);
                    this.output.sampleData(this.nalStartCode, 4);
                    this.output.sampleData(parsableByteArray, unsignedIntToInt);
                    i3 = i3 + 4 + unsignedIntToInt;
                }
                this.output.sampleMetadata(int24, i, i3, 0, null);
                this.hasOutputKeyframe = true;
                return true;
            }
        }
        return false;
    }

    @Override // androidx.media3.extractor.flv.TagPayloadReader
    public void seek() {
        this.hasOutputKeyframe = false;
    }
}
