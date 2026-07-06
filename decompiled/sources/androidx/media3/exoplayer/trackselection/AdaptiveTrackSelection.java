package androidx.media3.exoplayer.trackselection;

import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import androidx.media3.common.Format;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.source.chunk.MediaChunk;
import androidx.media3.exoplayer.source.chunk.MediaChunkIterator;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.upstream.BandwidthMeter;
import com.google.common.collect.AbstractListMultimap;
import com.google.common.collect.AbstractMapBasedMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.NaturalOrdering;
import com.google.common.collect.RegularImmutableList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public class AdaptiveTrackSelection extends BaseTrackSelection {
    public static final float DEFAULT_BANDWIDTH_FRACTION = 0.7f;
    public static final float DEFAULT_BUFFERED_FRACTION_TO_LIVE_EDGE_FOR_QUALITY_INCREASE = 0.75f;
    public static final int DEFAULT_MAX_DURATION_FOR_QUALITY_DECREASE_MS = 25000;
    public static final int DEFAULT_MAX_HEIGHT_TO_DISCARD = 719;
    public static final int DEFAULT_MAX_WIDTH_TO_DISCARD = 1279;
    public static final int DEFAULT_MIN_DURATION_FOR_QUALITY_INCREASE_MS = 10000;
    public static final int DEFAULT_MIN_DURATION_TO_RETAIN_AFTER_DISCARD_MS = 25000;
    public static final long MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS = 1000;
    public static final String TAG = "AdaptiveTrackSelection";
    public final ImmutableList<AdaptationCheckpoint> adaptationCheckpoints;
    public final float bandwidthFraction;
    public final BandwidthMeter bandwidthMeter;
    public final float bufferedFractionToLiveEdgeForQualityIncrease;
    public final Clock clock;

    @Nullable
    public MediaChunk lastBufferEvaluationMediaChunk;
    public long lastBufferEvaluationMs;
    public long latestBitrateEstimate;
    public final long maxDurationForQualityDecreaseUs;
    public final int maxHeightToDiscard;
    public final int maxWidthToDiscard;
    public final long minDurationForQualityIncreaseUs;
    public final long minDurationToRetainAfterDiscardUs;
    public float playbackSpeed;
    public int reason;
    public int selectedIndex;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AdaptationCheckpoint {
        public final long allocatedBandwidth;
        public final long totalBandwidth;

        public AdaptationCheckpoint(long j, long j2) {
            this.totalBandwidth = j;
            this.allocatedBandwidth = j2;
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AdaptationCheckpoint)) {
                return false;
            }
            AdaptationCheckpoint adaptationCheckpoint = (AdaptationCheckpoint) obj;
            return this.totalBandwidth == adaptationCheckpoint.totalBandwidth && this.allocatedBandwidth == adaptationCheckpoint.allocatedBandwidth;
        }

        public int hashCode() {
            return (((int) this.totalBandwidth) * 31) + ((int) this.allocatedBandwidth);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Factory implements ExoTrackSelection.Factory {
        public final float bandwidthFraction;
        public final float bufferedFractionToLiveEdgeForQualityIncrease;
        public final Clock clock;
        public final int maxDurationForQualityDecreaseMs;
        public final int maxHeightToDiscard;
        public final int maxWidthToDiscard;
        public final int minDurationForQualityIncreaseMs;
        public final int minDurationToRetainAfterDiscardMs;

        public Factory() {
            this(10000, 25000, 25000, 0.7f);
        }

        public AdaptiveTrackSelection createAdaptiveTrackSelection(TrackGroup trackGroup, int[] iArr, int i, BandwidthMeter bandwidthMeter, ImmutableList<AdaptationCheckpoint> immutableList) {
            return new AdaptiveTrackSelection(trackGroup, iArr, i, bandwidthMeter, this.minDurationForQualityIncreaseMs, this.maxDurationForQualityDecreaseMs, this.minDurationToRetainAfterDiscardMs, this.maxWidthToDiscard, this.maxHeightToDiscard, this.bandwidthFraction, this.bufferedFractionToLiveEdgeForQualityIncrease, immutableList, this.clock);
        }

        /* JADX WARN: Removed duplicated region for block: B:9:0x0015  */
        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection.Factory
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final androidx.media3.exoplayer.trackselection.ExoTrackSelection[] createTrackSelections(androidx.media3.exoplayer.trackselection.ExoTrackSelection.Definition[] r10, androidx.media3.exoplayer.upstream.BandwidthMeter r11, androidx.media3.exoplayer.source.MediaSource.MediaPeriodId r12, androidx.media3.common.Timeline r13) {
            /*
                r9 = this;
                com.google.common.collect.ImmutableList r12 = androidx.media3.exoplayer.trackselection.AdaptiveTrackSelection.getAdaptationCheckpoints(r10)
                int r13 = r10.length
                androidx.media3.exoplayer.trackselection.ExoTrackSelection[] r13 = new androidx.media3.exoplayer.trackselection.ExoTrackSelection[r13]
                r0 = 0
                r1 = 0
            L9:
                int r2 = r10.length
                if (r1 >= r2) goto L40
                r2 = r10[r1]
                if (r2 == 0) goto L15
                int[] r5 = r2.tracks
                int r3 = r5.length
                if (r3 != 0) goto L17
            L15:
                r7 = r11
                goto L3c
            L17:
                int r3 = r5.length
                r4 = 1
                if (r3 != r4) goto L28
                androidx.media3.exoplayer.trackselection.FixedTrackSelection r3 = new androidx.media3.exoplayer.trackselection.FixedTrackSelection
                androidx.media3.common.TrackGroup r4 = r2.group
                r5 = r5[r0]
                int r2 = r2.type
                r3.<init>(r4, r5, r2)
                r7 = r11
                goto L3a
            L28:
                androidx.media3.common.TrackGroup r4 = r2.group
                int r6 = r2.type
                java.lang.Object r2 = r12.get(r1)
                r8 = r2
                com.google.common.collect.ImmutableList r8 = (com.google.common.collect.ImmutableList) r8
                r3 = r9
                r7 = r11
                androidx.media3.exoplayer.trackselection.AdaptiveTrackSelection r11 = r3.createAdaptiveTrackSelection(r4, r5, r6, r7, r8)
                r3 = r11
            L3a:
                r13[r1] = r3
            L3c:
                int r1 = r1 + 1
                r11 = r7
                goto L9
            L40:
                return r13
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.trackselection.AdaptiveTrackSelection.Factory.createTrackSelections(androidx.media3.exoplayer.trackselection.ExoTrackSelection$Definition[], androidx.media3.exoplayer.upstream.BandwidthMeter, androidx.media3.exoplayer.source.MediaSource$MediaPeriodId, androidx.media3.common.Timeline):androidx.media3.exoplayer.trackselection.ExoTrackSelection[]");
        }

        public Factory(int i, int i2, int i3, float f) {
            this(i, i2, i3, 1279, 719, f, 0.75f, Clock.DEFAULT);
        }

        public Factory(int i, int i2, int i3, int i4, int i5, float f) {
            this(i, i2, i3, i4, i5, f, 0.75f, Clock.DEFAULT);
        }

        public Factory(int i, int i2, int i3, float f, float f2, Clock clock) {
            this(i, i2, i3, 1279, 719, f, f2, clock);
        }

        public Factory(int i, int i2, int i3, int i4, int i5, float f, float f2, Clock clock) {
            this.minDurationForQualityIncreaseMs = i;
            this.maxDurationForQualityDecreaseMs = i2;
            this.minDurationToRetainAfterDiscardMs = i3;
            this.maxWidthToDiscard = i4;
            this.maxHeightToDiscard = i5;
            this.bandwidthFraction = f;
            this.bufferedFractionToLiveEdgeForQualityIncrease = f2;
            this.clock = clock;
        }
    }

    public AdaptiveTrackSelection(TrackGroup trackGroup, int[] iArr, BandwidthMeter bandwidthMeter) {
        this(trackGroup, iArr, 0, bandwidthMeter, 10000L, 25000L, 25000L, 1279, 719, 0.7f, 0.75f, ImmutableList.of(), Clock.DEFAULT);
    }

    public static void addCheckpoint(List<ImmutableList.Builder<AdaptationCheckpoint>> list, long[] jArr) {
        long j = 0;
        for (long j2 : jArr) {
            j += j2;
        }
        for (int i = 0; i < list.size(); i++) {
            ImmutableList.Builder<AdaptationCheckpoint> builder = list.get(i);
            if (builder != null) {
                builder.add(new AdaptationCheckpoint(j, jArr[i]));
            }
        }
    }

    public static ImmutableList<ImmutableList<AdaptationCheckpoint>> getAdaptationCheckpoints(ExoTrackSelection.Definition[] definitionArr) {
        ArrayList arrayList = new ArrayList();
        for (ExoTrackSelection.Definition definition : definitionArr) {
            if (definition == null || definition.tracks.length <= 1) {
                arrayList.add(null);
            } else {
                ImmutableList.Builder builder = ImmutableList.builder();
                builder.add(new AdaptationCheckpoint(0L, 0L));
                arrayList.add(builder);
            }
        }
        long[][] sortedTrackBitrates = getSortedTrackBitrates(definitionArr);
        int[] iArr = new int[sortedTrackBitrates.length];
        long[] jArr = new long[sortedTrackBitrates.length];
        for (int i = 0; i < sortedTrackBitrates.length; i++) {
            long[] jArr2 = sortedTrackBitrates[i];
            jArr[i] = jArr2.length == 0 ? 0L : jArr2[0];
        }
        addCheckpoint(arrayList, jArr);
        ImmutableList<Integer> switchOrder = getSwitchOrder(sortedTrackBitrates);
        for (int i2 = 0; i2 < switchOrder.size(); i2++) {
            int iIntValue = switchOrder.get(i2).intValue();
            int i3 = iArr[iIntValue] + 1;
            iArr[iIntValue] = i3;
            jArr[iIntValue] = sortedTrackBitrates[iIntValue][i3];
            addCheckpoint(arrayList, jArr);
        }
        for (int i4 = 0; i4 < definitionArr.length; i4++) {
            if (arrayList.get(i4) != null) {
                jArr[i4] = jArr[i4] * 2;
            }
        }
        addCheckpoint(arrayList, jArr);
        ImmutableList.Builder builder2 = ImmutableList.builder();
        for (int i5 = 0; i5 < arrayList.size(); i5++) {
            ImmutableList.Builder builder3 = (ImmutableList.Builder) arrayList.get(i5);
            builder2.add(builder3 == null ? RegularImmutableList.EMPTY : builder3.build());
        }
        return builder2.build();
    }

    public static long[][] getSortedTrackBitrates(ExoTrackSelection.Definition[] definitionArr) {
        long[][] jArr = new long[definitionArr.length][];
        for (int i = 0; i < definitionArr.length; i++) {
            ExoTrackSelection.Definition definition = definitionArr[i];
            if (definition == null) {
                jArr[i] = new long[0];
            } else {
                jArr[i] = new long[definition.tracks.length];
                int i2 = 0;
                while (true) {
                    int[] iArr = definition.tracks;
                    if (i2 >= iArr.length) {
                        break;
                    }
                    long j = definition.group.formats[iArr[i2]].bitrate;
                    long[] jArr2 = jArr[i];
                    if (j == -1) {
                        j = 0;
                    }
                    jArr2[i2] = j;
                    i2++;
                }
                Arrays.sort(jArr[i]);
            }
        }
        return jArr;
    }

    public static ImmutableList<Integer> getSwitchOrder(long[][] jArr) {
        Object objBuild = MultimapBuilder.treeKeys(NaturalOrdering.INSTANCE).arrayListValues(2).build();
        for (int i = 0; i < jArr.length; i++) {
            long[] jArr2 = jArr[i];
            if (jArr2.length > 1) {
                int length = jArr2.length;
                double[] dArr = new double[length];
                int i2 = 0;
                while (true) {
                    long[] jArr3 = jArr[i];
                    double dLog = 0.0d;
                    if (i2 >= jArr3.length) {
                        break;
                    }
                    long j = jArr3[i2];
                    if (j != -1) {
                        dLog = Math.log(j);
                    }
                    dArr[i2] = dLog;
                    i2++;
                }
                int i3 = length - 1;
                double d = dArr[i3] - dArr[0];
                int i4 = 0;
                while (i4 < i3) {
                    double d2 = dArr[i4];
                    i4++;
                    ((AbstractListMultimap) objBuild).put(Double.valueOf(d == 0.0d ? 1.0d : (((d2 + dArr[i4]) * 0.5d) - dArr[0]) / d), Integer.valueOf(i));
                }
            }
        }
        return ImmutableList.copyOf(((AbstractMapBasedMultimap) objBuild).values());
    }

    public boolean canSelectFormat(Format format, int i, long j) {
        return ((long) i) <= j;
    }

    public final int determineIdealSelectedIndex(long j, long j2) {
        long allocatedBandwidth = getAllocatedBandwidth(j2);
        int i = 0;
        for (int i2 = 0; i2 < this.length; i2++) {
            if (j == Long.MIN_VALUE || !isTrackExcluded(i2, j)) {
                Format format = this.formats[i2];
                if (canSelectFormat(format, format.bitrate, allocatedBandwidth)) {
                    return i2;
                }
                i = i2;
            }
        }
        return i;
    }

    @Override // androidx.media3.exoplayer.trackselection.BaseTrackSelection, androidx.media3.exoplayer.trackselection.ExoTrackSelection
    @CallSuper
    public void disable() {
        this.lastBufferEvaluationMediaChunk = null;
    }

    @Override // androidx.media3.exoplayer.trackselection.BaseTrackSelection, androidx.media3.exoplayer.trackselection.ExoTrackSelection
    @CallSuper
    public void enable() {
        this.lastBufferEvaluationMs = -9223372036854775807L;
        this.lastBufferEvaluationMediaChunk = null;
    }

    @Override // androidx.media3.exoplayer.trackselection.BaseTrackSelection, androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public int evaluateQueueSize(long j, List<? extends MediaChunk> list) {
        int i;
        int i2;
        long jElapsedRealtime = this.clock.elapsedRealtime();
        if (!shouldEvaluateQueueSize(jElapsedRealtime, list)) {
            return list.size();
        }
        this.lastBufferEvaluationMs = jElapsedRealtime;
        this.lastBufferEvaluationMediaChunk = list.isEmpty() ? null : (MediaChunk) Iterables.getLast(list);
        if (list.isEmpty()) {
            return 0;
        }
        int size = list.size();
        long playoutDurationForMediaDuration = Util.getPlayoutDurationForMediaDuration(list.get(size - 1).startTimeUs - j, this.playbackSpeed);
        long minDurationToRetainAfterDiscardUs = getMinDurationToRetainAfterDiscardUs();
        if (playoutDurationForMediaDuration >= minDurationToRetainAfterDiscardUs) {
            Format format = this.formats[determineIdealSelectedIndex(jElapsedRealtime, getLastChunkDurationUs(list))];
            for (int i3 = 0; i3 < size; i3++) {
                MediaChunk mediaChunk = list.get(i3);
                Format format2 = mediaChunk.trackFormat;
                if (Util.getPlayoutDurationForMediaDuration(mediaChunk.startTimeUs - j, this.playbackSpeed) >= minDurationToRetainAfterDiscardUs && format2.bitrate < format.bitrate && (i = format2.height) != -1 && i <= this.maxHeightToDiscard && (i2 = format2.width) != -1 && i2 <= this.maxWidthToDiscard && i < format.height) {
                    return i3;
                }
            }
        }
        return size;
    }

    public final long getAllocatedBandwidth(long j) {
        long totalAllocatableBandwidth = getTotalAllocatableBandwidth(j);
        if (this.adaptationCheckpoints.isEmpty()) {
            return totalAllocatableBandwidth;
        }
        int i = 1;
        while (i < this.adaptationCheckpoints.size() - 1 && this.adaptationCheckpoints.get(i).totalBandwidth < totalAllocatableBandwidth) {
            i++;
        }
        AdaptationCheckpoint adaptationCheckpoint = this.adaptationCheckpoints.get(i - 1);
        AdaptationCheckpoint adaptationCheckpoint2 = this.adaptationCheckpoints.get(i);
        long j2 = adaptationCheckpoint.totalBandwidth;
        float f = (totalAllocatableBandwidth - j2) / (adaptationCheckpoint2.totalBandwidth - j2);
        return adaptationCheckpoint.allocatedBandwidth + ((long) (f * (adaptationCheckpoint2.allocatedBandwidth - r2)));
    }

    public final long getLastChunkDurationUs(List<? extends MediaChunk> list) {
        if (list.isEmpty()) {
            return -9223372036854775807L;
        }
        MediaChunk mediaChunk = (MediaChunk) Iterables.getLast(list);
        long j = mediaChunk.startTimeUs;
        if (j != -9223372036854775807L) {
            long j2 = mediaChunk.endTimeUs;
            if (j2 != -9223372036854775807L) {
                return j2 - j;
            }
        }
        return -9223372036854775807L;
    }

    @Override // androidx.media3.exoplayer.trackselection.BaseTrackSelection, androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public long getLatestBitrateEstimate() {
        return this.latestBitrateEstimate;
    }

    public long getMinDurationToRetainAfterDiscardUs() {
        return this.minDurationToRetainAfterDiscardUs;
    }

    public final long getNextChunkDurationUs(MediaChunkIterator[] mediaChunkIteratorArr, List<? extends MediaChunk> list) {
        int i = this.selectedIndex;
        if (i < mediaChunkIteratorArr.length && mediaChunkIteratorArr[i].next()) {
            mediaChunkIteratorArr[this.selectedIndex].getChunkEndTimeUs();
            throw null;
        }
        for (MediaChunkIterator mediaChunkIterator : mediaChunkIteratorArr) {
            if (mediaChunkIterator.next()) {
                mediaChunkIterator.getChunkEndTimeUs();
                throw null;
            }
        }
        return getLastChunkDurationUs(list);
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public int getSelectedIndex() {
        return this.selectedIndex;
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    @Nullable
    public Object getSelectionData() {
        return null;
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public int getSelectionReason() {
        return this.reason;
    }

    public final long getTotalAllocatableBandwidth(long j) {
        long bitrateEstimate = this.bandwidthMeter.getBitrateEstimate();
        this.latestBitrateEstimate = bitrateEstimate;
        long j2 = (long) (bitrateEstimate * this.bandwidthFraction);
        long timeToFirstByteEstimateUs = this.bandwidthMeter.getTimeToFirstByteEstimateUs();
        if (timeToFirstByteEstimateUs == -9223372036854775807L || j == -9223372036854775807L) {
            return (long) (j2 / this.playbackSpeed);
        }
        float f = j;
        return (long) ((j2 * Math.max((f / this.playbackSpeed) - timeToFirstByteEstimateUs, 0.0f)) / f);
    }

    public final long minDurationForQualityIncreaseUs(long j, long j2) {
        if (j == -9223372036854775807L) {
            return this.minDurationForQualityIncreaseUs;
        }
        if (j2 != -9223372036854775807L) {
            j -= j2;
        }
        return Math.min((long) (j * this.bufferedFractionToLiveEdgeForQualityIncrease), this.minDurationForQualityIncreaseUs);
    }

    @Override // androidx.media3.exoplayer.trackselection.BaseTrackSelection, androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public void onPlaybackSpeed(float f) {
        this.playbackSpeed = f;
    }

    public boolean shouldEvaluateQueueSize(long j, List<? extends MediaChunk> list) {
        long j2 = this.lastBufferEvaluationMs;
        if (j2 == -9223372036854775807L || j - j2 >= 1000) {
            return true;
        }
        return (list.isEmpty() || ((MediaChunk) Iterables.getLast(list)).equals(this.lastBufferEvaluationMediaChunk)) ? false : true;
    }

    @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public void updateSelectedTrack(long j, long j2, long j3, List<? extends MediaChunk> list, MediaChunkIterator[] mediaChunkIteratorArr) {
        long jElapsedRealtime = this.clock.elapsedRealtime();
        long nextChunkDurationUs = getNextChunkDurationUs(mediaChunkIteratorArr, list);
        int i = this.reason;
        if (i == 0) {
            this.reason = 1;
            this.selectedIndex = determineIdealSelectedIndex(jElapsedRealtime, nextChunkDurationUs);
            return;
        }
        int i2 = this.selectedIndex;
        int iIndexOf = list.isEmpty() ? -1 : indexOf(((MediaChunk) Iterables.getLast(list)).trackFormat);
        if (iIndexOf != -1) {
            i = ((MediaChunk) Iterables.getLast(list)).trackSelectionReason;
            i2 = iIndexOf;
        }
        int iDetermineIdealSelectedIndex = determineIdealSelectedIndex(jElapsedRealtime, nextChunkDurationUs);
        if (iDetermineIdealSelectedIndex != i2 && !isTrackExcluded(i2, jElapsedRealtime)) {
            Format[] formatArr = this.formats;
            Format format = formatArr[i2];
            Format format2 = formatArr[iDetermineIdealSelectedIndex];
            long jMinDurationForQualityIncreaseUs = minDurationForQualityIncreaseUs(j3, nextChunkDurationUs);
            int i3 = format2.bitrate;
            int i4 = format.bitrate;
            if ((i3 > i4 && j2 < jMinDurationForQualityIncreaseUs) || (i3 < i4 && j2 >= this.maxDurationForQualityDecreaseUs)) {
                iDetermineIdealSelectedIndex = i2;
            }
        }
        if (iDetermineIdealSelectedIndex != i2) {
            i = 3;
        }
        this.reason = i;
        this.selectedIndex = iDetermineIdealSelectedIndex;
    }

    public AdaptiveTrackSelection(TrackGroup trackGroup, int[] iArr, int i, BandwidthMeter bandwidthMeter, long j, long j2, long j3, int i2, int i3, float f, float f2, List<AdaptationCheckpoint> list, Clock clock) {
        long j4;
        super(trackGroup, iArr, i);
        if (j3 < j) {
            Log.w("AdaptiveTrackSelection", "Adjusting minDurationToRetainAfterDiscardMs to be at least minDurationForQualityIncreaseMs");
            j4 = j;
        } else {
            j4 = j3;
        }
        this.bandwidthMeter = bandwidthMeter;
        this.minDurationForQualityIncreaseUs = j * 1000;
        this.maxDurationForQualityDecreaseUs = j2 * 1000;
        this.minDurationToRetainAfterDiscardUs = j4 * 1000;
        this.maxWidthToDiscard = i2;
        this.maxHeightToDiscard = i3;
        this.bandwidthFraction = f;
        this.bufferedFractionToLiveEdgeForQualityIncrease = f2;
        this.adaptationCheckpoints = ImmutableList.copyOf((Collection) list);
        this.clock = clock;
        this.playbackSpeed = 1.0f;
        this.reason = 0;
        this.lastBufferEvaluationMs = -9223372036854775807L;
        this.latestBitrateEstimate = -2147483647L;
    }
}
