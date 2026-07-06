package com.google.android.exoplayer2.source.chunk;

import android.util.SparseArray;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.analytics.PlayerId;
import com.google.android.exoplayer2.extractor.ChunkIndex;
import com.google.android.exoplayer2.extractor.DummyTrackOutput;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor;
import com.google.android.exoplayer2.extractor.mp4.FragmentedMp4Extractor;
import com.google.android.exoplayer2.source.chunk.ChunkExtractor;
import com.google.android.exoplayer2.upstream.DataReader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class BundledChunkExtractor implements ExtractorOutput, ChunkExtractor {
    public static final ChunkExtractor.Factory FACTORY = new BundledChunkExtractor$$ExternalSyntheticLambda0();
    public static final PositionHolder POSITION_HOLDER = new PositionHolder();
    public final SparseArray<BindingTrackOutput> bindingTrackOutputs = new SparseArray<>();
    public long endTimeUs;
    public final Extractor extractor;
    public boolean extractorInitialized;
    public final Format primaryTrackManifestFormat;
    public final int primaryTrackType;
    public Format[] sampleFormats;
    public SeekMap seekMap;

    @Nullable
    public ChunkExtractor.TrackOutputProvider trackOutputProvider;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class BindingTrackOutput implements TrackOutput {
        public long endTimeUs;
        public final DummyTrackOutput fakeTrackOutput = new DummyTrackOutput();
        public final int id;

        @Nullable
        public final Format manifestFormat;
        public Format sampleFormat;
        public TrackOutput trackOutput;
        public final int type;

        public BindingTrackOutput(int i, int i2, @Nullable Format format) {
            this.id = i;
            this.type = i2;
            this.manifestFormat = format;
        }

        public void bind(@Nullable ChunkExtractor.TrackOutputProvider trackOutputProvider, long j) {
            if (trackOutputProvider == null) {
                this.trackOutput = this.fakeTrackOutput;
                return;
            }
            this.endTimeUs = j;
            TrackOutput trackOutputTrack = trackOutputProvider.track(this.id, this.type);
            this.trackOutput = trackOutputTrack;
            Format format = this.sampleFormat;
            if (format != null) {
                trackOutputTrack.format(format);
            }
        }

        @Override // com.google.android.exoplayer2.extractor.TrackOutput
        public void format(Format format) {
            Format format2 = this.manifestFormat;
            if (format2 != null) {
                format = format.withManifestFormatInfo(format2);
            }
            this.sampleFormat = format;
            TrackOutput trackOutput = this.trackOutput;
            Util.castNonNull(trackOutput);
            trackOutput.format(this.sampleFormat);
        }

        @Override // com.google.android.exoplayer2.extractor.TrackOutput
        public /* synthetic */ int sampleData(DataReader dataReader, int i, boolean z) {
            return sampleData(dataReader, i, z, 0);
        }

        @Override // com.google.android.exoplayer2.extractor.TrackOutput
        public void sampleMetadata(long j, int i, int i2, int i3, @Nullable TrackOutput.CryptoData cryptoData) {
            long j2 = this.endTimeUs;
            if (j2 != -9223372036854775807L && j >= j2) {
                this.trackOutput = this.fakeTrackOutput;
            }
            TrackOutput trackOutput = this.trackOutput;
            Util.castNonNull(trackOutput);
            trackOutput.sampleMetadata(j, i, i2, i3, cryptoData);
        }

        @Override // com.google.android.exoplayer2.extractor.TrackOutput
        public /* synthetic */ void sampleData(ParsableByteArray parsableByteArray, int i) {
            sampleData(parsableByteArray, i, 0);
        }

        @Override // com.google.android.exoplayer2.extractor.TrackOutput
        public int sampleData(DataReader dataReader, int i, boolean z, int i2) throws IOException {
            TrackOutput trackOutput = this.trackOutput;
            Util.castNonNull(trackOutput);
            return trackOutput.sampleData(dataReader, i, z);
        }

        @Override // com.google.android.exoplayer2.extractor.TrackOutput
        public void sampleData(ParsableByteArray parsableByteArray, int i, int i2) {
            TrackOutput trackOutput = this.trackOutput;
            Util.castNonNull(trackOutput);
            trackOutput.sampleData(parsableByteArray, i);
        }
    }

    public static /* synthetic */ ChunkExtractor $r8$lambda$9Ya_LtLVS2dGXJ930dYWmsfZ93s(int i, Format format, boolean z, List list, TrackOutput trackOutput, PlayerId playerId) {
        Extractor fragmentedMp4Extractor;
        String str = format.containerMimeType;
        if (MimeTypes.isText(str)) {
            return null;
        }
        if (MimeTypes.isMatroska(str)) {
            fragmentedMp4Extractor = new MatroskaExtractor(1);
        } else {
            fragmentedMp4Extractor = new FragmentedMp4Extractor(z ? 4 : 0, null, null, list, trackOutput);
        }
        return new BundledChunkExtractor(fragmentedMp4Extractor, i, format);
    }

    public BundledChunkExtractor(Extractor extractor, int i, Format format) {
        this.extractor = extractor;
        this.primaryTrackType = i;
        this.primaryTrackManifestFormat = format;
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorOutput
    public void endTracks() {
        Format[] formatArr = new Format[this.bindingTrackOutputs.size()];
        for (int i = 0; i < this.bindingTrackOutputs.size(); i++) {
            Format format = this.bindingTrackOutputs.valueAt(i).sampleFormat;
            Assertions.checkStateNotNull(format);
            formatArr[i] = format;
        }
        this.sampleFormats = formatArr;
    }

    @Override // com.google.android.exoplayer2.source.chunk.ChunkExtractor
    @Nullable
    public ChunkIndex getChunkIndex() {
        SeekMap seekMap = this.seekMap;
        if (seekMap instanceof ChunkIndex) {
            return (ChunkIndex) seekMap;
        }
        return null;
    }

    @Override // com.google.android.exoplayer2.source.chunk.ChunkExtractor
    @Nullable
    public Format[] getSampleFormats() {
        return this.sampleFormats;
    }

    @Override // com.google.android.exoplayer2.source.chunk.ChunkExtractor
    public void init(@Nullable ChunkExtractor.TrackOutputProvider trackOutputProvider, long j, long j2) {
        this.trackOutputProvider = trackOutputProvider;
        this.endTimeUs = j2;
        if (!this.extractorInitialized) {
            this.extractor.init(this);
            if (j != -9223372036854775807L) {
                this.extractor.seek(0L, j);
            }
            this.extractorInitialized = true;
            return;
        }
        Extractor extractor = this.extractor;
        if (j == -9223372036854775807L) {
            j = 0;
        }
        extractor.seek(0L, j);
        for (int i = 0; i < this.bindingTrackOutputs.size(); i++) {
            this.bindingTrackOutputs.valueAt(i).bind(trackOutputProvider, j2);
        }
    }

    @Override // com.google.android.exoplayer2.source.chunk.ChunkExtractor
    public boolean read(ExtractorInput extractorInput) throws IOException {
        int i = this.extractor.read(extractorInput, POSITION_HOLDER);
        Assertions.checkState(i != 1);
        return i == 0;
    }

    @Override // com.google.android.exoplayer2.source.chunk.ChunkExtractor
    public void release() {
        this.extractor.release();
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorOutput
    public void seekMap(SeekMap seekMap) {
        this.seekMap = seekMap;
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorOutput
    public TrackOutput track(int i, int i2) {
        BindingTrackOutput bindingTrackOutput = this.bindingTrackOutputs.get(i);
        if (bindingTrackOutput == null) {
            Assertions.checkState(this.sampleFormats == null);
            bindingTrackOutput = new BindingTrackOutput(i, i2, i2 == this.primaryTrackType ? this.primaryTrackManifestFormat : null);
            bindingTrackOutput.bind(this.trackOutputProvider, this.endTimeUs);
            this.bindingTrackOutputs.put(i, bindingTrackOutput);
        }
        return bindingTrackOutput;
    }
}
