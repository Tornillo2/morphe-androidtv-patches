package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.analytics.PlayerId;
import com.google.android.exoplayer2.source.ProgressiveMediaExtractor;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class MediaParserExtractorAdapter$$ExternalSyntheticLambda0 implements ProgressiveMediaExtractor.Factory {
    @Override // com.google.android.exoplayer2.source.ProgressiveMediaExtractor.Factory
    public final ProgressiveMediaExtractor createProgressiveMediaExtractor(PlayerId playerId) {
        return new MediaParserExtractorAdapter(playerId);
    }
}
