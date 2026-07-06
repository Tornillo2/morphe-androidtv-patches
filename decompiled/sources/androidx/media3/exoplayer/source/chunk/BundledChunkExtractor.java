package androidx.media3.exoplayer.source.chunk;

import android.util.SparseArray;
import androidx.annotation.Nullable;
import androidx.media3.common.DataReader;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.source.chunk.ChunkExtractor;
import androidx.media3.extractor.ChunkIndex;
import androidx.media3.extractor.DummyTrackOutput;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.jpeg.JpegExtractor;
import androidx.media3.extractor.mkv.MatroskaExtractor;
import androidx.media3.extractor.mp4.FragmentedMp4Extractor;
import androidx.media3.extractor.png.PngExtractor;
import androidx.media3.extractor.text.DefaultSubtitleParserFactory;
import androidx.media3.extractor.text.SubtitleExtractor;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.text.SubtitleTranscodingExtractor;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import j$.util.Objects;
import java.io.IOException;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class BundledChunkExtractor implements ExtractorOutput, ChunkExtractor {
    public static final Factory FACTORY = new Factory();
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

        @Override // androidx.media3.extractor.TrackOutput
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

        @Override // androidx.media3.extractor.TrackOutput
        public /* synthetic */ void sampleData(ParsableByteArray parsableByteArray, int i) {
            sampleData(parsableByteArray, i, 0);
        }

        @Override // androidx.media3.extractor.TrackOutput
        public void sampleMetadata(long j, int i, int i2, int i3, @Nullable TrackOutput.CryptoData cryptoData) {
            long j2 = this.endTimeUs;
            if (j2 != -9223372036854775807L && j >= j2) {
                this.trackOutput = this.fakeTrackOutput;
            }
            TrackOutput trackOutput = this.trackOutput;
            Util.castNonNull(trackOutput);
            trackOutput.sampleMetadata(j, i, i2, i3, cryptoData);
        }

        @Override // androidx.media3.extractor.TrackOutput
        public int sampleData(DataReader dataReader, int i, boolean z) {
            return sampleData(dataReader, i, z, 0);
        }

        @Override // androidx.media3.extractor.TrackOutput
        public int sampleData(DataReader dataReader, int i, boolean z, int i2) throws IOException {
            TrackOutput trackOutput = this.trackOutput;
            Util.castNonNull(trackOutput);
            return trackOutput.sampleData(dataReader, i, z);
        }

        @Override // androidx.media3.extractor.TrackOutput
        public void sampleData(ParsableByteArray parsableByteArray, int i, int i2) {
            TrackOutput trackOutput = this.trackOutput;
            Util.castNonNull(trackOutput);
            trackOutput.sampleData(parsableByteArray, i);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Factory implements ChunkExtractor.Factory {
        public boolean parseSubtitlesDuringExtraction;
        public SubtitleParser.Factory subtitleParserFactory = new DefaultSubtitleParserFactory();

        @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor.Factory
        @Nullable
        public ChunkExtractor createProgressiveMediaExtractor(int i, Format format, boolean z, List<Format> list, @Nullable TrackOutput trackOutput, PlayerId playerId) {
            Extractor fragmentedMp4Extractor;
            String str = format.containerMimeType;
            if (!MimeTypes.isText(str)) {
                if (MimeTypes.isMatroska(str)) {
                    fragmentedMp4Extractor = new MatroskaExtractor(this.subtitleParserFactory, this.parseSubtitlesDuringExtraction ? 1 : 3);
                } else if (Objects.equals(str, "image/jpeg")) {
                    fragmentedMp4Extractor = new JpegExtractor(1);
                } else if (Objects.equals(str, MimeTypes.IMAGE_PNG)) {
                    fragmentedMp4Extractor = new PngExtractor();
                } else {
                    int i2 = z ? 4 : 0;
                    if (!this.parseSubtitlesDuringExtraction) {
                        i2 |= 32;
                    }
                    fragmentedMp4Extractor = new FragmentedMp4Extractor(this.subtitleParserFactory, i2, null, null, list, trackOutput);
                }
            } else {
                if (!this.parseSubtitlesDuringExtraction) {
                    return null;
                }
                fragmentedMp4Extractor = new SubtitleExtractor(this.subtitleParserFactory.create(format), format);
            }
            if (this.parseSubtitlesDuringExtraction && !MimeTypes.isText(str) && !(fragmentedMp4Extractor.getUnderlyingImplementation() instanceof FragmentedMp4Extractor) && !(fragmentedMp4Extractor.getUnderlyingImplementation() instanceof MatroskaExtractor)) {
                fragmentedMp4Extractor = new SubtitleTranscodingExtractor(fragmentedMp4Extractor, this.subtitleParserFactory);
            }
            return new BundledChunkExtractor(fragmentedMp4Extractor, i, format);
        }

        @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor.Factory
        @CanIgnoreReturnValue
        public Factory experimentalParseSubtitlesDuringExtraction(boolean z) {
            this.parseSubtitlesDuringExtraction = z;
            return this;
        }

        @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor.Factory
        public Format getOutputTextFormat(Format format) {
            String str;
            if (!this.parseSubtitlesDuringExtraction || !this.subtitleParserFactory.supportsFormat(format)) {
                return format;
            }
            format.getClass();
            Format.Builder builder = new Format.Builder(format);
            builder.sampleMimeType = MimeTypes.normalizeMimeType(MimeTypes.APPLICATION_MEDIA3_CUES);
            builder.cueReplacementBehavior = this.subtitleParserFactory.getCueReplacementBehavior(format);
            StringBuilder sb = new StringBuilder();
            sb.append(format.sampleMimeType);
            if (format.codecs != null) {
                str = StringUtils.SPACE + format.codecs;
            } else {
                str = "";
            }
            sb.append(str);
            builder.codecs = sb.toString();
            builder.subsampleOffsetUs = Long.MAX_VALUE;
            return new Format(builder);
        }

        @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor.Factory
        @CanIgnoreReturnValue
        public Factory setSubtitleParserFactory(SubtitleParser.Factory factory) {
            factory.getClass();
            this.subtitleParserFactory = factory;
            return this;
        }

        @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor.Factory
        @CanIgnoreReturnValue
        public ChunkExtractor.Factory experimentalParseSubtitlesDuringExtraction(boolean z) {
            this.parseSubtitlesDuringExtraction = z;
            return this;
        }

        @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor.Factory
        @CanIgnoreReturnValue
        public ChunkExtractor.Factory setSubtitleParserFactory(SubtitleParser.Factory factory) {
            factory.getClass();
            this.subtitleParserFactory = factory;
            return this;
        }
    }

    public BundledChunkExtractor(Extractor extractor, int i, Format format) {
        this.extractor = extractor;
        this.primaryTrackType = i;
        this.primaryTrackManifestFormat = format;
    }

    @Override // androidx.media3.extractor.ExtractorOutput
    public void endTracks() {
        Format[] formatArr = new Format[this.bindingTrackOutputs.size()];
        for (int i = 0; i < this.bindingTrackOutputs.size(); i++) {
            Format format = this.bindingTrackOutputs.valueAt(i).sampleFormat;
            Assertions.checkStateNotNull(format);
            formatArr[i] = format;
        }
        this.sampleFormats = formatArr;
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor
    @Nullable
    public ChunkIndex getChunkIndex() {
        SeekMap seekMap = this.seekMap;
        if (seekMap instanceof ChunkIndex) {
            return (ChunkIndex) seekMap;
        }
        return null;
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor
    @Nullable
    public Format[] getSampleFormats() {
        return this.sampleFormats;
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor
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

    @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor
    public boolean read(ExtractorInput extractorInput) throws IOException {
        int i = this.extractor.read(extractorInput, POSITION_HOLDER);
        Assertions.checkState(i != 1);
        return i == 0;
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkExtractor
    public void release() {
        this.extractor.release();
    }

    @Override // androidx.media3.extractor.ExtractorOutput
    public void seekMap(SeekMap seekMap) {
        this.seekMap = seekMap;
    }

    @Override // androidx.media3.extractor.ExtractorOutput
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
