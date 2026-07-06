package com.google.android.exoplayer2.text;

import androidx.annotation.Nullable;
import com.google.android.exoplayer2.decoder.DecoderOutputBuffer;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class SubtitleOutputBuffer extends DecoderOutputBuffer implements Subtitle {
    public long subsampleOffsetUs;

    @Nullable
    public Subtitle subtitle;

    @Override // com.google.android.exoplayer2.decoder.Buffer
    public void clear() {
        this.flags = 0;
        this.subtitle = null;
    }

    @Override // com.google.android.exoplayer2.text.Subtitle
    public List<Cue> getCues(long j) {
        Subtitle subtitle = this.subtitle;
        subtitle.getClass();
        return subtitle.getCues(j - this.subsampleOffsetUs);
    }

    @Override // com.google.android.exoplayer2.text.Subtitle
    public long getEventTime(int i) {
        Subtitle subtitle = this.subtitle;
        subtitle.getClass();
        return subtitle.getEventTime(i) + this.subsampleOffsetUs;
    }

    @Override // com.google.android.exoplayer2.text.Subtitle
    public int getEventTimeCount() {
        Subtitle subtitle = this.subtitle;
        subtitle.getClass();
        return subtitle.getEventTimeCount();
    }

    @Override // com.google.android.exoplayer2.text.Subtitle
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
