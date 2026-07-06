package androidx.media3.exoplayer.upstream;

import androidx.media3.common.util.UnstableApi;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public class SlidingPercentile {
    public static final int MAX_RECYCLED_SAMPLES = 5;
    public static final int SORT_ORDER_BY_INDEX = 1;
    public static final int SORT_ORDER_BY_VALUE = 0;
    public static final int SORT_ORDER_NONE = -1;
    public final int maxWeight;
    public int nextSampleIndex;
    public int recycledSampleCount;
    public int totalWeight;
    public static final Comparator<Sample> INDEX_COMPARATOR = new SlidingPercentile$$ExternalSyntheticLambda0();
    public static final Comparator<Sample> VALUE_COMPARATOR = new SlidingPercentile$$ExternalSyntheticLambda1();
    public final Sample[] recycledSamples = new Sample[5];
    public final ArrayList<Sample> samples = new ArrayList<>();
    public int currentSortOrder = -1;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Sample {
        public int index;
        public float value;
        public int weight;

        public Sample() {
        }

        public Sample(AnonymousClass1 anonymousClass1) {
        }
    }

    /* JADX INFO: renamed from: $r8$lambda$xVrpz1k64v1GGT5OvX-ezvbtrRg, reason: not valid java name */
    public static /* synthetic */ int m166$r8$lambda$xVrpz1k64v1GGT5OvXezvbtrRg(Sample sample, Sample sample2) {
        return sample.index - sample2.index;
    }

    public SlidingPercentile(int i) {
        this.maxWeight = i;
    }

    public void addSample(int i, float f) {
        Sample sample;
        ensureSortedByIndex();
        int i2 = this.recycledSampleCount;
        if (i2 > 0) {
            Sample[] sampleArr = this.recycledSamples;
            int i3 = i2 - 1;
            this.recycledSampleCount = i3;
            sample = sampleArr[i3];
        } else {
            sample = new Sample();
        }
        int i4 = this.nextSampleIndex;
        this.nextSampleIndex = i4 + 1;
        sample.index = i4;
        sample.weight = i;
        sample.value = f;
        this.samples.add(sample);
        this.totalWeight += i;
        while (true) {
            int i5 = this.totalWeight;
            int i6 = this.maxWeight;
            if (i5 <= i6) {
                return;
            }
            int i7 = i5 - i6;
            Sample sample2 = this.samples.get(0);
            int i8 = sample2.weight;
            if (i8 <= i7) {
                this.totalWeight -= i8;
                this.samples.remove(0);
                int i9 = this.recycledSampleCount;
                if (i9 < 5) {
                    Sample[] sampleArr2 = this.recycledSamples;
                    this.recycledSampleCount = i9 + 1;
                    sampleArr2[i9] = sample2;
                }
            } else {
                sample2.weight = i8 - i7;
                this.totalWeight -= i7;
            }
        }
    }

    public final void ensureSortedByIndex() {
        if (this.currentSortOrder != 1) {
            Collections.sort(this.samples, INDEX_COMPARATOR);
            this.currentSortOrder = 1;
        }
    }

    public final void ensureSortedByValue() {
        if (this.currentSortOrder != 0) {
            Collections.sort(this.samples, VALUE_COMPARATOR);
            this.currentSortOrder = 0;
        }
    }

    public float getPercentile(float f) {
        ensureSortedByValue();
        float f2 = f * this.totalWeight;
        int i = 0;
        for (int i2 = 0; i2 < this.samples.size(); i2++) {
            Sample sample = this.samples.get(i2);
            i += sample.weight;
            if (i >= f2) {
                return sample.value;
            }
        }
        if (this.samples.isEmpty()) {
            return Float.NaN;
        }
        return this.samples.get(r5.size() - 1).value;
    }

    public void reset() {
        this.samples.clear();
        this.currentSortOrder = -1;
        this.nextSampleIndex = 0;
        this.totalWeight = 0;
    }
}
