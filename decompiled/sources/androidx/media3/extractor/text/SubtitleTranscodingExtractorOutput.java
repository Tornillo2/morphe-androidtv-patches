package androidx.media3.extractor.text;

import android.util.SparseArray;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.text.SubtitleParser;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class SubtitleTranscodingExtractorOutput implements ExtractorOutput {
    public final ExtractorOutput delegate;
    public final SubtitleParser.Factory subtitleParserFactory;
    public final SparseArray<SubtitleTranscodingTrackOutput> textTrackOutputs = new SparseArray<>();

    public SubtitleTranscodingExtractorOutput(ExtractorOutput extractorOutput, SubtitleParser.Factory factory) {
        this.delegate = extractorOutput;
        this.subtitleParserFactory = factory;
    }

    @Override // androidx.media3.extractor.ExtractorOutput
    public void endTracks() {
        this.delegate.endTracks();
    }

    public void resetSubtitleParsers() {
        for (int i = 0; i < this.textTrackOutputs.size(); i++) {
            this.textTrackOutputs.valueAt(i).resetSubtitleParser();
        }
    }

    @Override // androidx.media3.extractor.ExtractorOutput
    public void seekMap(SeekMap seekMap) {
        this.delegate.seekMap(seekMap);
    }

    @Override // androidx.media3.extractor.ExtractorOutput
    public TrackOutput track(int i, int i2) {
        if (i2 != 3) {
            return this.delegate.track(i, i2);
        }
        SubtitleTranscodingTrackOutput subtitleTranscodingTrackOutput = this.textTrackOutputs.get(i);
        if (subtitleTranscodingTrackOutput != null) {
            return subtitleTranscodingTrackOutput;
        }
        SubtitleTranscodingTrackOutput subtitleTranscodingTrackOutput2 = new SubtitleTranscodingTrackOutput(this.delegate.track(i, i2), this.subtitleParserFactory);
        this.textTrackOutputs.put(i, subtitleTranscodingTrackOutput2);
        return subtitleTranscodingTrackOutput2;
    }
}
