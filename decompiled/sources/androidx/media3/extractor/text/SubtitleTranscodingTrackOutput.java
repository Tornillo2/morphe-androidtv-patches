package androidx.media3.extractor.text;

import androidx.annotation.Nullable;
import androidx.media3.common.DataReader;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.text.SubtitleParser;
import java.io.EOFException;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class SubtitleTranscodingTrackOutput implements TrackOutput {
    public Format currentFormat;

    @Nullable
    public SubtitleParser currentSubtitleParser;
    public final TrackOutput delegate;
    public final SubtitleParser.Factory subtitleParserFactory;
    public final CueEncoder cueEncoder = new CueEncoder();
    public int sampleDataStart = 0;
    public int sampleDataEnd = 0;
    public byte[] sampleData = Util.EMPTY_BYTE_ARRAY;
    public final ParsableByteArray parsableScratch = new ParsableByteArray();

    public SubtitleTranscodingTrackOutput(TrackOutput trackOutput, SubtitleParser.Factory factory) {
        this.delegate = trackOutput;
        this.subtitleParserFactory = factory;
    }

    public final void ensureSampleDataCapacity(int i) {
        int length = this.sampleData.length;
        int i2 = this.sampleDataEnd;
        if (length - i2 >= i) {
            return;
        }
        int i3 = i2 - this.sampleDataStart;
        int iMax = Math.max(i3 * 2, i + i3);
        byte[] bArr = this.sampleData;
        byte[] bArr2 = iMax <= bArr.length ? bArr : new byte[iMax];
        System.arraycopy(bArr, this.sampleDataStart, bArr2, 0, i3);
        this.sampleDataStart = 0;
        this.sampleDataEnd = i3;
        this.sampleData = bArr2;
    }

    @Override // androidx.media3.extractor.TrackOutput
    public void format(Format format) {
        format.sampleMimeType.getClass();
        Assertions.checkArgument(MimeTypes.getTrackType(format.sampleMimeType) == 3);
        if (!format.equals(this.currentFormat)) {
            this.currentFormat = format;
            this.currentSubtitleParser = this.subtitleParserFactory.supportsFormat(format) ? this.subtitleParserFactory.create(format) : null;
        }
        if (this.currentSubtitleParser == null) {
            this.delegate.format(format);
            return;
        }
        TrackOutput trackOutput = this.delegate;
        Format.Builder builder = new Format.Builder(format);
        builder.sampleMimeType = MimeTypes.normalizeMimeType(MimeTypes.APPLICATION_MEDIA3_CUES);
        builder.codecs = format.sampleMimeType;
        builder.subsampleOffsetUs = Long.MAX_VALUE;
        builder.cueReplacementBehavior = this.subtitleParserFactory.getCueReplacementBehavior(format);
        trackOutput.format(new Format(builder));
    }

    public final void outputSample(CuesWithTiming cuesWithTiming, long j, int i) {
        Assertions.checkStateNotNull(this.currentFormat);
        byte[] bArrEncode = this.cueEncoder.encode(cuesWithTiming.cues, cuesWithTiming.durationUs);
        ParsableByteArray parsableByteArray = this.parsableScratch;
        parsableByteArray.getClass();
        parsableByteArray.reset(bArrEncode, bArrEncode.length);
        this.delegate.sampleData(this.parsableScratch, bArrEncode.length);
        int i2 = i & Integer.MAX_VALUE;
        long j2 = cuesWithTiming.startTimeUs;
        if (j2 == -9223372036854775807L) {
            Assertions.checkState(this.currentFormat.subsampleOffsetUs == Long.MAX_VALUE);
        } else {
            long j3 = this.currentFormat.subsampleOffsetUs;
            j = j3 == Long.MAX_VALUE ? j + j2 : j2 + j3;
        }
        this.delegate.sampleMetadata(j, i2, bArrEncode.length, 0, null);
    }

    public void resetSubtitleParser() {
        SubtitleParser subtitleParser = this.currentSubtitleParser;
        if (subtitleParser != null) {
            subtitleParser.reset();
        }
    }

    @Override // androidx.media3.extractor.TrackOutput
    public /* synthetic */ void sampleData(ParsableByteArray parsableByteArray, int i) {
        sampleData(parsableByteArray, i, 0);
    }

    @Override // androidx.media3.extractor.TrackOutput
    public void sampleMetadata(final long j, final int i, int i2, int i3, @Nullable TrackOutput.CryptoData cryptoData) {
        if (this.currentSubtitleParser == null) {
            this.delegate.sampleMetadata(j, i, i2, i3, cryptoData);
            return;
        }
        Assertions.checkArgument(cryptoData == null, "DRM on subtitles is not supported");
        int i4 = (this.sampleDataEnd - i3) - i2;
        this.currentSubtitleParser.parse(this.sampleData, i4, i2, SubtitleParser.OutputOptions.ALL, new Consumer() { // from class: androidx.media3.extractor.text.SubtitleTranscodingTrackOutput$$ExternalSyntheticLambda0
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                this.f$0.outputSample((CuesWithTiming) obj, j, i);
            }
        });
        int i5 = i4 + i2;
        this.sampleDataStart = i5;
        if (i5 == this.sampleDataEnd) {
            this.sampleDataStart = 0;
            this.sampleDataEnd = 0;
        }
    }

    @Override // androidx.media3.extractor.TrackOutput
    public int sampleData(DataReader dataReader, int i, boolean z, int i2) throws IOException {
        if (this.currentSubtitleParser == null) {
            return this.delegate.sampleData(dataReader, i, z, i2);
        }
        ensureSampleDataCapacity(i);
        int i3 = dataReader.read(this.sampleData, this.sampleDataEnd, i);
        if (i3 != -1) {
            this.sampleDataEnd += i3;
            return i3;
        }
        if (z) {
            return -1;
        }
        throw new EOFException();
    }

    @Override // androidx.media3.extractor.TrackOutput
    public void sampleData(ParsableByteArray parsableByteArray, int i, int i2) {
        if (this.currentSubtitleParser == null) {
            this.delegate.sampleData(parsableByteArray, i, i2);
            return;
        }
        ensureSampleDataCapacity(i);
        parsableByteArray.readBytes(this.sampleData, this.sampleDataEnd, i);
        this.sampleDataEnd += i;
    }

    @Override // androidx.media3.extractor.TrackOutput
    public int sampleData(DataReader dataReader, int i, boolean z) {
        return sampleData(dataReader, i, z, 0);
    }
}
