package androidx.media3.extractor.text.webvtt;

import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.text.Subtitle;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class WebvttSubtitle implements Subtitle {
    public final List<WebvttCueInfo> cueInfos;
    public final long[] cueTimesUs;
    public final long[] sortedCueTimesUs;

    public WebvttSubtitle(List<WebvttCueInfo> list) {
        this.cueInfos = DesugarCollections.unmodifiableList(new ArrayList(list));
        this.cueTimesUs = new long[list.size() * 2];
        for (int i = 0; i < list.size(); i++) {
            WebvttCueInfo webvttCueInfo = list.get(i);
            int i2 = i * 2;
            long[] jArr = this.cueTimesUs;
            jArr[i2] = webvttCueInfo.startTimeUs;
            jArr[i2 + 1] = webvttCueInfo.endTimeUs;
        }
        long[] jArr2 = this.cueTimesUs;
        long[] jArrCopyOf = Arrays.copyOf(jArr2, jArr2.length);
        this.sortedCueTimesUs = jArrCopyOf;
        Arrays.sort(jArrCopyOf);
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public List<Cue> getCues(long j) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < this.cueInfos.size(); i++) {
            long[] jArr = this.cueTimesUs;
            int i2 = i * 2;
            if (jArr[i2] <= j && j < jArr[i2 + 1]) {
                WebvttCueInfo webvttCueInfo = this.cueInfos.get(i);
                Cue cue = webvttCueInfo.cue;
                if (cue.line == -3.4028235E38f) {
                    arrayList2.add(webvttCueInfo);
                } else {
                    arrayList.add(cue);
                }
            }
        }
        Collections.sort(arrayList2, new WebvttSubtitle$$ExternalSyntheticLambda0());
        for (int i3 = 0; i3 < arrayList2.size(); i3++) {
            Cue cue2 = ((WebvttCueInfo) arrayList2.get(i3)).cue;
            cue2.getClass();
            Cue.Builder builder = new Cue.Builder(cue2);
            builder.line = (-1) - i3;
            builder.lineType = 1;
            arrayList.add(builder.build());
        }
        return arrayList;
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public long getEventTime(int i) {
        Assertions.checkArgument(i >= 0);
        Assertions.checkArgument(i < this.sortedCueTimesUs.length);
        return this.sortedCueTimesUs[i];
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public int getEventTimeCount() {
        return this.sortedCueTimesUs.length;
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public int getNextEventTimeIndex(long j) {
        int iBinarySearchCeil = Util.binarySearchCeil(this.sortedCueTimesUs, j, false, false);
        if (iBinarySearchCeil < this.sortedCueTimesUs.length) {
            return iBinarySearchCeil;
        }
        return -1;
    }
}
