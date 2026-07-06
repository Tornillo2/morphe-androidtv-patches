package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.source.SampleQueue;
import com.google.android.exoplayer2.util.Consumer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class SampleQueue$$ExternalSyntheticLambda0 implements Consumer {
    @Override // com.google.android.exoplayer2.util.Consumer
    public final void accept(Object obj) {
        ((SampleQueue.SharedSampleMetadata) obj).drmSessionReference.release();
    }
}
