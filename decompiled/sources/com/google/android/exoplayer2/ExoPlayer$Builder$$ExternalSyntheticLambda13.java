package com.google.android.exoplayer2;

import com.google.android.exoplayer2.analytics.DefaultAnalyticsCollector;
import com.google.android.exoplayer2.util.Clock;
import com.google.common.base.Function;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class ExoPlayer$Builder$$ExternalSyntheticLambda13 implements Function {
    @Override // com.google.common.base.Function
    public final Object apply(Object obj) {
        return new DefaultAnalyticsCollector((Clock) obj);
    }
}
