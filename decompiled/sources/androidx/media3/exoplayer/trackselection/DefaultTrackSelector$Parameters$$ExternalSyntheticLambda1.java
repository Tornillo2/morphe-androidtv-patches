package androidx.media3.exoplayer.trackselection;

import androidx.media3.exoplayer.source.TrackGroupArray;
import com.google.common.base.Function;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class DefaultTrackSelector$Parameters$$ExternalSyntheticLambda1 implements Function {
    @Override // com.google.common.base.Function
    public final Object apply(Object obj) {
        return ((TrackGroupArray) obj).toBundle();
    }
}
