package androidx.media3.extractor.text.ttml;

import androidx.annotation.VisibleForTesting;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.text.Subtitle;
import j$.util.DesugarCollections;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class TtmlSubtitle implements Subtitle {
    public final long[] eventTimesUs;
    public final Map<String, TtmlStyle> globalStyles;
    public final Map<String, String> imageMap;
    public final Map<String, TtmlRegion> regionMap;
    public final TtmlNode root;

    public TtmlSubtitle(TtmlNode ttmlNode, Map<String, TtmlStyle> map, Map<String, TtmlRegion> map2, Map<String, String> map3) {
        this.root = ttmlNode;
        this.regionMap = map2;
        this.imageMap = map3;
        this.globalStyles = map != null ? DesugarCollections.unmodifiableMap(map) : Collections.EMPTY_MAP;
        this.eventTimesUs = ttmlNode.getEventTimesUs();
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public List<Cue> getCues(long j) {
        return this.root.getCues(j, this.globalStyles, this.regionMap, this.imageMap);
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public long getEventTime(int i) {
        return this.eventTimesUs[i];
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public int getEventTimeCount() {
        return this.eventTimesUs.length;
    }

    @VisibleForTesting
    public Map<String, TtmlStyle> getGlobalStyles() {
        return this.globalStyles;
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public int getNextEventTimeIndex(long j) {
        int iBinarySearchCeil = Util.binarySearchCeil(this.eventTimesUs, j, false, false);
        if (iBinarySearchCeil < this.eventTimesUs.length) {
            return iBinarySearchCeil;
        }
        return -1;
    }

    @VisibleForTesting
    public TtmlNode getRoot() {
        return this.root;
    }
}
