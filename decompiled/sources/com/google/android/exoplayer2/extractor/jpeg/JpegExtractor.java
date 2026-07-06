package com.google.android.exoplayer2.extractor.jpeg;

import androidx.annotation.Nullable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.mp4.Mp4Extractor;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.mp4.MotionPhotoMetadata;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class JpegExtractor implements Extractor {
    public static final long EXIF_HEADER = 1165519206;
    public static final int EXIF_ID_CODE_LENGTH = 6;
    public static final String HEADER_XMP_APP1 = "http://ns.adobe.com/xap/1.0/";
    public static final int IMAGE_TRACK_ID = 1024;
    public static final int MARKER_APP0 = 65504;
    public static final int MARKER_APP1 = 65505;
    public static final int MARKER_SOI = 65496;
    public static final int MARKER_SOS = 65498;
    public static final int STATE_ENDED = 6;
    public static final int STATE_READING_MARKER = 0;
    public static final int STATE_READING_MOTION_PHOTO_VIDEO = 5;
    public static final int STATE_READING_SEGMENT = 2;
    public static final int STATE_READING_SEGMENT_LENGTH = 1;
    public static final int STATE_SNIFFING_MOTION_PHOTO_VIDEO = 4;
    public ExtractorOutput extractorOutput;
    public ExtractorInput lastExtractorInput;
    public int marker;

    @Nullable
    public MotionPhotoMetadata motionPhotoMetadata;

    @Nullable
    public Mp4Extractor mp4Extractor;
    public StartOffsetExtractorInput mp4ExtractorStartOffsetExtractorInput;
    public int segmentLength;
    public int state;
    public final ParsableByteArray scratch = new ParsableByteArray(6);
    public long mp4StartPosition = -1;

    @Nullable
    public static MotionPhotoMetadata getMotionPhotoMetadata(String str, long j) throws IOException {
        MotionPhotoDescription motionPhotoDescription;
        if (j == -1 || (motionPhotoDescription = XmpMotionPhotoDescriptionParser.parse(str)) == null) {
            return null;
        }
        return motionPhotoDescription.getMotionPhotoMetadata(j);
    }

    public final void advancePeekPositionToNextSegment(ExtractorInput extractorInput) throws IOException {
        this.scratch.reset(2);
        extractorInput.peekFully(this.scratch.data, 0, 2);
        extractorInput.advancePeekPosition(this.scratch.readUnsignedShort() - 2);
    }

    public final void endReadingWithImageTrack() {
        outputImageTrack(new Metadata.Entry[0]);
        ExtractorOutput extractorOutput = this.extractorOutput;
        extractorOutput.getClass();
        extractorOutput.endTracks();
        this.extractorOutput.seekMap(new SeekMap.Unseekable(-9223372036854775807L));
        this.state = 6;
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        this.extractorOutput = extractorOutput;
    }

    public final void outputImageTrack(Metadata.Entry... entryArr) {
        ExtractorOutput extractorOutput = this.extractorOutput;
        extractorOutput.getClass();
        TrackOutput trackOutputTrack = extractorOutput.track(1024, 4);
        Format.Builder builder = new Format.Builder();
        builder.containerMimeType = "image/jpeg";
        builder.metadata = new Metadata(entryArr);
        trackOutputTrack.format(new Format(builder));
    }

    public final int peekMarker(ExtractorInput extractorInput) throws IOException {
        this.scratch.reset(2);
        extractorInput.peekFully(this.scratch.data, 0, 2);
        return this.scratch.readUnsignedShort();
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        int i = this.state;
        if (i == 0) {
            readMarker(extractorInput);
            return 0;
        }
        if (i == 1) {
            readSegmentLength(extractorInput);
            return 0;
        }
        if (i == 2) {
            readSegment(extractorInput);
            return 0;
        }
        if (i == 4) {
            long position = extractorInput.getPosition();
            long j = this.mp4StartPosition;
            if (position != j) {
                positionHolder.position = j;
                return 1;
            }
            sniffMotionPhotoVideo(extractorInput);
            return 0;
        }
        if (i != 5) {
            if (i == 6) {
                return -1;
            }
            throw new IllegalStateException();
        }
        if (this.mp4ExtractorStartOffsetExtractorInput == null || extractorInput != this.lastExtractorInput) {
            this.lastExtractorInput = extractorInput;
            this.mp4ExtractorStartOffsetExtractorInput = new StartOffsetExtractorInput(extractorInput, this.mp4StartPosition);
        }
        Mp4Extractor mp4Extractor = this.mp4Extractor;
        mp4Extractor.getClass();
        int i2 = mp4Extractor.read(this.mp4ExtractorStartOffsetExtractorInput, positionHolder);
        if (i2 == 1) {
            positionHolder.position += this.mp4StartPosition;
        }
        return i2;
    }

    public final void readMarker(ExtractorInput extractorInput) throws IOException {
        this.scratch.reset(2);
        extractorInput.readFully(this.scratch.data, 0, 2);
        int unsignedShort = this.scratch.readUnsignedShort();
        this.marker = unsignedShort;
        if (unsignedShort == 65498) {
            if (this.mp4StartPosition != -1) {
                this.state = 4;
                return;
            } else {
                endReadingWithImageTrack();
                return;
            }
        }
        if ((unsignedShort < 65488 || unsignedShort > 65497) && unsignedShort != 65281) {
            this.state = 1;
        }
    }

    public final void readSegment(ExtractorInput extractorInput) throws IOException {
        String delimiterTerminatedString;
        if (this.marker == 65505) {
            ParsableByteArray parsableByteArray = new ParsableByteArray(this.segmentLength);
            extractorInput.readFully(parsableByteArray.data, 0, this.segmentLength);
            if (this.motionPhotoMetadata == null && "http://ns.adobe.com/xap/1.0/".equals(parsableByteArray.readDelimiterTerminatedString((char) 0)) && (delimiterTerminatedString = parsableByteArray.readDelimiterTerminatedString((char) 0)) != null) {
                MotionPhotoMetadata motionPhotoMetadata = getMotionPhotoMetadata(delimiterTerminatedString, extractorInput.getLength());
                this.motionPhotoMetadata = motionPhotoMetadata;
                if (motionPhotoMetadata != null) {
                    this.mp4StartPosition = motionPhotoMetadata.videoStartPosition;
                }
            }
        } else {
            extractorInput.skipFully(this.segmentLength);
        }
        this.state = 0;
    }

    public final void readSegmentLength(ExtractorInput extractorInput) throws IOException {
        this.scratch.reset(2);
        extractorInput.readFully(this.scratch.data, 0, 2);
        this.segmentLength = this.scratch.readUnsignedShort() - 2;
        this.state = 2;
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void release() {
        Mp4Extractor mp4Extractor = this.mp4Extractor;
        if (mp4Extractor != null) {
            mp4Extractor.getClass();
        }
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void seek(long j, long j2) {
        if (j == 0) {
            this.state = 0;
            this.mp4Extractor = null;
        } else if (this.state == 5) {
            Mp4Extractor mp4Extractor = this.mp4Extractor;
            mp4Extractor.getClass();
            mp4Extractor.seek(j, j2);
        }
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) throws IOException {
        if (peekMarker(extractorInput) != 65496) {
            return false;
        }
        int iPeekMarker = peekMarker(extractorInput);
        this.marker = iPeekMarker;
        if (iPeekMarker == 65504) {
            advancePeekPositionToNextSegment(extractorInput);
            this.marker = peekMarker(extractorInput);
        }
        if (this.marker != 65505) {
            return false;
        }
        extractorInput.advancePeekPosition(2);
        this.scratch.reset(6);
        extractorInput.peekFully(this.scratch.data, 0, 6);
        return this.scratch.readUnsignedInt() == 1165519206 && this.scratch.readUnsignedShort() == 0;
    }

    public final void sniffMotionPhotoVideo(ExtractorInput extractorInput) throws IOException {
        if (!extractorInput.peekFully(this.scratch.data, 0, 1, true)) {
            endReadingWithImageTrack();
            return;
        }
        extractorInput.resetPeekPosition();
        if (this.mp4Extractor == null) {
            this.mp4Extractor = new Mp4Extractor(0);
        }
        StartOffsetExtractorInput startOffsetExtractorInput = new StartOffsetExtractorInput(extractorInput, this.mp4StartPosition);
        this.mp4ExtractorStartOffsetExtractorInput = startOffsetExtractorInput;
        if (!this.mp4Extractor.sniff(startOffsetExtractorInput)) {
            endReadingWithImageTrack();
            return;
        }
        Mp4Extractor mp4Extractor = this.mp4Extractor;
        long j = this.mp4StartPosition;
        ExtractorOutput extractorOutput = this.extractorOutput;
        extractorOutput.getClass();
        mp4Extractor.extractorOutput = new StartOffsetExtractorOutput(j, extractorOutput);
        startReadingMotionPhoto();
    }

    public final void startReadingMotionPhoto() {
        MotionPhotoMetadata motionPhotoMetadata = this.motionPhotoMetadata;
        motionPhotoMetadata.getClass();
        outputImageTrack(motionPhotoMetadata);
        this.state = 5;
    }
}
