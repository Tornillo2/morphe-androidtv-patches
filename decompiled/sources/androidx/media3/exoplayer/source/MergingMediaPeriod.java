package androidx.media3.exoplayer.source;

import androidx.annotation.Nullable;
import androidx.media3.common.Format;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.chunk.Chunk;
import androidx.media3.exoplayer.source.chunk.MediaChunk;
import androidx.media3.exoplayer.source.chunk.MediaChunkIterator;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class MergingMediaPeriod implements MediaPeriod, MediaPeriod.Callback {

    @Nullable
    public MediaPeriod.Callback callback;
    public SequenceableLoader compositeSequenceableLoader;
    public final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    public final MediaPeriod[] periods;

    @Nullable
    public TrackGroupArray trackGroups;
    public final ArrayList<MediaPeriod> childrenPendingPreparation = new ArrayList<>();
    public final HashMap<TrackGroup, TrackGroup> childTrackGroupByMergedTrackGroup = new HashMap<>();
    public final IdentityHashMap<SampleStream, Integer> streamPeriodIndices = new IdentityHashMap<>();
    public MediaPeriod[] enabledPeriods = new MediaPeriod[0];

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ForwardingTrackSelection implements ExoTrackSelection {
        public final TrackGroup trackGroup;
        public final ExoTrackSelection trackSelection;

        public ForwardingTrackSelection(ExoTrackSelection exoTrackSelection, TrackGroup trackGroup) {
            this.trackSelection = exoTrackSelection;
            this.trackGroup = trackGroup;
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public void disable() {
            this.trackSelection.disable();
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public void enable() {
            this.trackSelection.enable();
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ForwardingTrackSelection)) {
                return false;
            }
            ForwardingTrackSelection forwardingTrackSelection = (ForwardingTrackSelection) obj;
            return this.trackSelection.equals(forwardingTrackSelection.trackSelection) && this.trackGroup.equals(forwardingTrackSelection.trackGroup);
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public int evaluateQueueSize(long j, List<? extends MediaChunk> list) {
            return this.trackSelection.evaluateQueueSize(j, list);
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public boolean excludeTrack(int i, long j) {
            return this.trackSelection.excludeTrack(i, j);
        }

        @Override // androidx.media3.exoplayer.trackselection.TrackSelection
        public Format getFormat(int i) {
            TrackGroup trackGroup = this.trackGroup;
            return trackGroup.formats[this.trackSelection.getIndexInTrackGroup(i)];
        }

        @Override // androidx.media3.exoplayer.trackselection.TrackSelection
        public int getIndexInTrackGroup(int i) {
            return this.trackSelection.getIndexInTrackGroup(i);
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public long getLatestBitrateEstimate() {
            return this.trackSelection.getLatestBitrateEstimate();
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public Format getSelectedFormat() {
            TrackGroup trackGroup = this.trackGroup;
            return trackGroup.formats[this.trackSelection.getSelectedIndexInTrackGroup()];
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public int getSelectedIndex() {
            return this.trackSelection.getSelectedIndex();
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public int getSelectedIndexInTrackGroup() {
            return this.trackSelection.getSelectedIndexInTrackGroup();
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        @Nullable
        public Object getSelectionData() {
            return this.trackSelection.getSelectionData();
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public int getSelectionReason() {
            return this.trackSelection.getSelectionReason();
        }

        @Override // androidx.media3.exoplayer.trackselection.TrackSelection
        public TrackGroup getTrackGroup() {
            return this.trackGroup;
        }

        @Override // androidx.media3.exoplayer.trackselection.TrackSelection
        public int getType() {
            return this.trackSelection.getType();
        }

        public int hashCode() {
            return this.trackSelection.hashCode() + ((this.trackGroup.hashCode() + 527) * 31);
        }

        @Override // androidx.media3.exoplayer.trackselection.TrackSelection
        public int indexOf(Format format) {
            return this.trackSelection.indexOf(this.trackGroup.indexOf(format));
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public boolean isTrackExcluded(int i, long j) {
            return this.trackSelection.isTrackExcluded(i, j);
        }

        @Override // androidx.media3.exoplayer.trackselection.TrackSelection
        public int length() {
            return this.trackSelection.length();
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public void onDiscontinuity() {
            this.trackSelection.onDiscontinuity();
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public void onPlayWhenReadyChanged(boolean z) {
            this.trackSelection.onPlayWhenReadyChanged(z);
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public void onPlaybackSpeed(float f) {
            this.trackSelection.onPlaybackSpeed(f);
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public void onRebuffer() {
            this.trackSelection.onRebuffer();
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public boolean shouldCancelChunkLoad(long j, Chunk chunk, List<? extends MediaChunk> list) {
            return this.trackSelection.shouldCancelChunkLoad(j, chunk, list);
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public void updateSelectedTrack(long j, long j2, long j3, List<? extends MediaChunk> list, MediaChunkIterator[] mediaChunkIteratorArr) {
            this.trackSelection.updateSelectedTrack(j, j2, j3, list, mediaChunkIteratorArr);
        }

        @Override // androidx.media3.exoplayer.trackselection.TrackSelection
        public int indexOf(int i) {
            return this.trackSelection.indexOf(i);
        }
    }

    public MergingMediaPeriod(CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory, long[] jArr, MediaPeriod... mediaPeriodArr) {
        this.compositeSequenceableLoaderFactory = compositeSequenceableLoaderFactory;
        this.periods = mediaPeriodArr;
        this.compositeSequenceableLoader = compositeSequenceableLoaderFactory.empty();
        for (int i = 0; i < mediaPeriodArr.length; i++) {
            long j = jArr[i];
            if (j != 0) {
                this.periods[i] = new TimeOffsetMediaPeriod(mediaPeriodArr[i], j);
            }
        }
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public boolean continueLoading(LoadingInfo loadingInfo) {
        if (this.childrenPendingPreparation.isEmpty()) {
            return this.compositeSequenceableLoader.continueLoading(loadingInfo);
        }
        int size = this.childrenPendingPreparation.size();
        for (int i = 0; i < size; i++) {
            this.childrenPendingPreparation.get(i).continueLoading(loadingInfo);
        }
        return false;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void discardBuffer(long j, boolean z) {
        for (MediaPeriod mediaPeriod : this.enabledPeriods) {
            mediaPeriod.discardBuffer(j, z);
        }
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long getAdjustedSeekPositionUs(long j, SeekParameters seekParameters) {
        MediaPeriod[] mediaPeriodArr = this.enabledPeriods;
        return (mediaPeriodArr.length > 0 ? mediaPeriodArr[0] : this.periods[0]).getAdjustedSeekPositionUs(j, seekParameters);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public long getBufferedPositionUs() {
        return this.compositeSequenceableLoader.getBufferedPositionUs();
    }

    public MediaPeriod getChildPeriod(int i) {
        MediaPeriod mediaPeriod = this.periods[i];
        return mediaPeriod instanceof TimeOffsetMediaPeriod ? ((TimeOffsetMediaPeriod) mediaPeriod).mediaPeriod : mediaPeriod;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public long getNextLoadPositionUs() {
        return this.compositeSequenceableLoader.getNextLoadPositionUs();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public List getStreamKeys(List list) {
        return Collections.EMPTY_LIST;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public TrackGroupArray getTrackGroups() {
        TrackGroupArray trackGroupArray = this.trackGroups;
        trackGroupArray.getClass();
        return trackGroupArray;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public boolean isLoading() {
        return this.compositeSequenceableLoader.isLoading();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void maybeThrowPrepareError() throws IOException {
        for (MediaPeriod mediaPeriod : this.periods) {
            mediaPeriod.maybeThrowPrepareError();
        }
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod.Callback
    public void onPrepared(MediaPeriod mediaPeriod) {
        this.childrenPendingPreparation.remove(mediaPeriod);
        if (!this.childrenPendingPreparation.isEmpty()) {
            return;
        }
        int i = 0;
        for (MediaPeriod mediaPeriod2 : this.periods) {
            i += mediaPeriod2.getTrackGroups().length;
        }
        TrackGroup[] trackGroupArr = new TrackGroup[i];
        int i2 = 0;
        int i3 = 0;
        while (true) {
            MediaPeriod[] mediaPeriodArr = this.periods;
            if (i2 >= mediaPeriodArr.length) {
                this.trackGroups = new TrackGroupArray(trackGroupArr);
                MediaPeriod.Callback callback = this.callback;
                callback.getClass();
                callback.onPrepared(this);
                return;
            }
            TrackGroupArray trackGroups = mediaPeriodArr[i2].getTrackGroups();
            int i4 = trackGroups.length;
            int i5 = 0;
            while (i5 < i4) {
                TrackGroup trackGroup = trackGroups.get(i5);
                Format[] formatArr = new Format[trackGroup.length];
                for (int i6 = 0; i6 < trackGroup.length; i6++) {
                    Format format = trackGroup.formats[i6];
                    format.getClass();
                    Format.Builder builder = new Format.Builder(format);
                    StringBuilder sb = new StringBuilder();
                    sb.append(i2);
                    sb.append(":");
                    String str = format.id;
                    if (str == null) {
                        str = "";
                    }
                    sb.append(str);
                    builder.id = sb.toString();
                    formatArr[i6] = new Format(builder);
                }
                TrackGroup trackGroup2 = new TrackGroup(i2 + ":" + trackGroup.id, formatArr);
                this.childTrackGroupByMergedTrackGroup.put(trackGroup2, trackGroup);
                trackGroupArr[i3] = trackGroup2;
                i5++;
                i3++;
            }
            i2++;
        }
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void prepare(MediaPeriod.Callback callback, long j) {
        this.callback = callback;
        Collections.addAll(this.childrenPendingPreparation, this.periods);
        for (MediaPeriod mediaPeriod : this.periods) {
            mediaPeriod.prepare(this, j);
        }
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long readDiscontinuity() {
        long j = -9223372036854775807L;
        for (MediaPeriod mediaPeriod : this.enabledPeriods) {
            long discontinuity = mediaPeriod.readDiscontinuity();
            if (discontinuity == -9223372036854775807L) {
                if (j != -9223372036854775807L && mediaPeriod.seekToUs(j) != j) {
                    throw new IllegalStateException("Unexpected child seekToUs result.");
                }
            } else if (j == -9223372036854775807L) {
                for (MediaPeriod mediaPeriod2 : this.enabledPeriods) {
                    if (mediaPeriod2 == mediaPeriod) {
                        break;
                    }
                    if (mediaPeriod2.seekToUs(discontinuity) != discontinuity) {
                        throw new IllegalStateException("Unexpected child seekToUs result.");
                    }
                }
                j = discontinuity;
            } else if (discontinuity != j) {
                throw new IllegalStateException("Conflicting discontinuities.");
            }
        }
        return j;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public void reevaluateBuffer(long j) {
        this.compositeSequenceableLoader.reevaluateBuffer(j);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long seekToUs(long j) {
        long jSeekToUs = this.enabledPeriods[0].seekToUs(j);
        int i = 1;
        while (true) {
            MediaPeriod[] mediaPeriodArr = this.enabledPeriods;
            if (i >= mediaPeriodArr.length) {
                return jSeekToUs;
            }
            if (mediaPeriodArr[i].seekToUs(jSeekToUs) != jSeekToUs) {
                throw new IllegalStateException("Unexpected child seekToUs result.");
            }
            i++;
        }
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long selectTracks(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j) {
        int[] iArr = new int[exoTrackSelectionArr.length];
        int[] iArr2 = new int[exoTrackSelectionArr.length];
        for (int i = 0; i < exoTrackSelectionArr.length; i++) {
            SampleStream sampleStream = sampleStreamArr[i];
            Integer num = sampleStream == null ? null : this.streamPeriodIndices.get(sampleStream);
            iArr[i] = num == null ? -1 : num.intValue();
            ExoTrackSelection exoTrackSelection = exoTrackSelectionArr[i];
            if (exoTrackSelection != null) {
                String str = exoTrackSelection.getTrackGroup().id;
                iArr2[i] = Integer.parseInt(str.substring(0, str.indexOf(":")));
            } else {
                iArr2[i] = -1;
            }
        }
        this.streamPeriodIndices.clear();
        int length = exoTrackSelectionArr.length;
        SampleStream[] sampleStreamArr2 = new SampleStream[length];
        SampleStream[] sampleStreamArr3 = new SampleStream[exoTrackSelectionArr.length];
        ExoTrackSelection[] exoTrackSelectionArr2 = new ExoTrackSelection[exoTrackSelectionArr.length];
        ArrayList arrayList = new ArrayList(this.periods.length);
        long j2 = j;
        int i2 = 0;
        while (i2 < this.periods.length) {
            for (int i3 = 0; i3 < exoTrackSelectionArr.length; i3++) {
                sampleStreamArr3[i3] = iArr[i3] == i2 ? sampleStreamArr[i3] : null;
                if (iArr2[i3] == i2) {
                    ExoTrackSelection exoTrackSelection2 = exoTrackSelectionArr[i3];
                    exoTrackSelection2.getClass();
                    TrackGroup trackGroup = this.childTrackGroupByMergedTrackGroup.get(exoTrackSelection2.getTrackGroup());
                    trackGroup.getClass();
                    exoTrackSelectionArr2[i3] = new ForwardingTrackSelection(exoTrackSelection2, trackGroup);
                } else {
                    exoTrackSelectionArr2[i3] = null;
                }
            }
            int i4 = i2;
            long jSelectTracks = this.periods[i2].selectTracks(exoTrackSelectionArr2, zArr, sampleStreamArr3, zArr2, j2);
            if (i4 == 0) {
                j2 = jSelectTracks;
            } else if (jSelectTracks != j2) {
                throw new IllegalStateException("Children enabled at different positions.");
            }
            boolean z = false;
            for (int i5 = 0; i5 < exoTrackSelectionArr.length; i5++) {
                if (iArr2[i5] == i4) {
                    SampleStream sampleStream2 = sampleStreamArr3[i5];
                    sampleStream2.getClass();
                    sampleStreamArr2[i5] = sampleStreamArr3[i5];
                    this.streamPeriodIndices.put(sampleStream2, Integer.valueOf(i4));
                    z = true;
                } else if (iArr[i5] == i4) {
                    Assertions.checkState(sampleStreamArr3[i5] == null);
                }
            }
            if (z) {
                arrayList.add(this.periods[i4]);
            }
            i2 = i4 + 1;
        }
        System.arraycopy(sampleStreamArr2, 0, sampleStreamArr, 0, length);
        this.enabledPeriods = (MediaPeriod[]) arrayList.toArray(new MediaPeriod[0]);
        this.compositeSequenceableLoader = this.compositeSequenceableLoaderFactory.create(arrayList, Lists.transform(arrayList, new MergingMediaPeriod$$ExternalSyntheticLambda0()));
        return j2;
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader.Callback
    public void onContinueLoadingRequested(MediaPeriod mediaPeriod) {
        MediaPeriod.Callback callback = this.callback;
        callback.getClass();
        callback.onContinueLoadingRequested(this);
    }
}
