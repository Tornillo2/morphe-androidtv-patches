package androidx.media3.extractor.text;

import androidx.annotation.Nullable;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.decoder.DecoderOutputBuffer;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public abstract class SubtitleOutputBuffer extends DecoderOutputBuffer implements Subtitle {
    public long subsampleOffsetUs;

    @Nullable
    public Subtitle subtitle;

    @Override // androidx.media3.decoder.DecoderOutputBuffer, androidx.media3.decoder.Buffer
    public void clear() {
        super.clear();
        this.subtitle = null;
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public List<Cue> getCues(long j) {
        Subtitle subtitle = this.subtitle;
        subtitle.getClass();
        return subtitle.getCues(j - this.subsampleOffsetUs);
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public long getEventTime(int i) {
        Subtitle subtitle = this.subtitle;
        subtitle.getClass();
        return subtitle.getEventTime(i) + this.subsampleOffsetUs;
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public int getEventTimeCount() {
        Subtitle subtitle = this.subtitle;
        subtitle.getClass();
        return subtitle.getEventTimeCount();
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public int getNextEventTimeIndex(long j) {
        Subtitle subtitle = this.subtitle;
        subtitle.getClass();
        return subtitle.getNextEventTimeIndex(j - this.subsampleOffsetUs);
    }

    public void setContent(long j, Subtitle subtitle, long j2) {
        this.timeUs = j;
        this.subtitle = subtitle;
        if (j2 != Long.MAX_VALUE) {
            j = j2;
        }
        this.subsampleOffsetUs = j;
    }
}
