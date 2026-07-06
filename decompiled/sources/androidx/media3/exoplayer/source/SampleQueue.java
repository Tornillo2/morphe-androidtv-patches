package androidx.media3.exoplayer.source;

import android.os.Looper;
import androidx.annotation.CallSuper;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.media3.common.DataReader;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.drm.DrmSession;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.drm.DrmSessionManager;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.extractor.TrackOutput;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public class SampleQueue implements TrackOutput {

    @VisibleForTesting
    public static final int SAMPLE_CAPACITY_INCREMENT = 1000;
    public static final String TAG = "SampleQueue";
    public int absoluteFirstIndex;

    @Nullable
    public DrmSession currentDrmSession;

    @Nullable
    public Format downstreamFormat;

    @Nullable
    public final DrmSessionEventListener.EventDispatcher drmEventDispatcher;

    @Nullable
    public final DrmSessionManager drmSessionManager;
    public boolean isLastSampleQueued;
    public int length;
    public boolean loggedUnexpectedNonSyncSample;
    public boolean pendingSplice;
    public int readPosition;
    public int relativeFirstIndex;
    public final SampleDataQueue sampleDataQueue;
    public long sampleOffsetUs;

    @Nullable
    public Format unadjustedUpstreamFormat;

    @Nullable
    public Format upstreamFormat;
    public boolean upstreamFormatAdjustmentRequired;

    @Nullable
    public UpstreamFormatChangedListener upstreamFormatChangeListener;
    public long upstreamSourceId;
    public final SampleExtrasHolder extrasHolder = new SampleExtrasHolder();
    public int capacity = 1000;
    public long[] sourceIds = new long[1000];
    public long[] offsets = new long[1000];
    public long[] timesUs = new long[1000];
    public int[] flags = new int[1000];
    public int[] sizes = new int[1000];
    public TrackOutput.CryptoData[] cryptoDatas = new TrackOutput.CryptoData[1000];
    public final SpannedData<SharedSampleMetadata> sharedSampleMetadata = new SpannedData<>(new SampleQueue$$ExternalSyntheticLambda0());
    public long startTimeUs = Long.MIN_VALUE;
    public long largestDiscardedTimestampUs = Long.MIN_VALUE;
    public long largestQueuedTimestampUs = Long.MIN_VALUE;
    public boolean upstreamFormatRequired = true;
    public boolean upstreamKeyframeRequired = true;
    public boolean allSamplesAreSyncSamples = true;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SampleExtrasHolder {

        @Nullable
        public TrackOutput.CryptoData cryptoData;
        public long offset;
        public int size;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SharedSampleMetadata {
        public final DrmSessionManager.DrmSessionReference drmSessionReference;
        public final Format format;

        public SharedSampleMetadata(Format format, DrmSessionManager.DrmSessionReference drmSessionReference) {
            this.format = format;
            this.drmSessionReference = drmSessionReference;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface UpstreamFormatChangedListener {
        void onUpstreamFormatChanged(Format format);
    }

    public SampleQueue(Allocator allocator, @Nullable DrmSessionManager drmSessionManager, @Nullable DrmSessionEventListener.EventDispatcher eventDispatcher) {
        this.drmSessionManager = drmSessionManager;
        this.drmEventDispatcher = eventDispatcher;
        this.sampleDataQueue = new SampleDataQueue(allocator);
    }

    public static SampleQueue createWithDrm(Allocator allocator, DrmSessionManager drmSessionManager, DrmSessionEventListener.EventDispatcher eventDispatcher) {
        drmSessionManager.getClass();
        eventDispatcher.getClass();
        return new SampleQueue(allocator, drmSessionManager, eventDispatcher);
    }

    public static SampleQueue createWithoutDrm(Allocator allocator) {
        return new SampleQueue(allocator, null, null);
    }

    public final synchronized boolean attemptSplice(long j) {
        if (this.length == 0) {
            return j > this.largestDiscardedTimestampUs;
        }
        if (getLargestReadTimestampUs() >= j) {
            return false;
        }
        discardUpstreamSampleMetadata(this.absoluteFirstIndex + countUnreadSamplesBefore(j));
        return true;
    }

    public final synchronized void commitSample(long j, int i, long j2, int i2, @Nullable TrackOutput.CryptoData cryptoData) {
        try {
            int i3 = this.length;
            if (i3 > 0) {
                int relativeIndex = getRelativeIndex(i3 - 1);
                Assertions.checkArgument(this.offsets[relativeIndex] + ((long) this.sizes[relativeIndex]) <= j2);
            }
            this.isLastSampleQueued = (536870912 & i) != 0;
            this.largestQueuedTimestampUs = Math.max(this.largestQueuedTimestampUs, j);
            int relativeIndex2 = getRelativeIndex(this.length);
            this.timesUs[relativeIndex2] = j;
            this.offsets[relativeIndex2] = j2;
            this.sizes[relativeIndex2] = i2;
            this.flags[relativeIndex2] = i;
            this.cryptoDatas[relativeIndex2] = cryptoData;
            this.sourceIds[relativeIndex2] = this.upstreamSourceId;
            if (this.sharedSampleMetadata.isEmpty() || !this.sharedSampleMetadata.getEndValue().format.equals(this.upstreamFormat)) {
                Format format = this.upstreamFormat;
                format.getClass();
                DrmSessionManager drmSessionManager = this.drmSessionManager;
                this.sharedSampleMetadata.appendSpan(getWriteIndex(), new SharedSampleMetadata(format, drmSessionManager != null ? drmSessionManager.preacquireSession(this.drmEventDispatcher, format) : DrmSessionManager.DrmSessionReference.EMPTY));
            }
            int i4 = this.length + 1;
            this.length = i4;
            int i5 = this.capacity;
            if (i4 == i5) {
                int i6 = i5 + 1000;
                long[] jArr = new long[i6];
                long[] jArr2 = new long[i6];
                long[] jArr3 = new long[i6];
                int[] iArr = new int[i6];
                int[] iArr2 = new int[i6];
                TrackOutput.CryptoData[] cryptoDataArr = new TrackOutput.CryptoData[i6];
                int i7 = this.relativeFirstIndex;
                int i8 = i5 - i7;
                System.arraycopy(this.offsets, i7, jArr2, 0, i8);
                System.arraycopy(this.timesUs, this.relativeFirstIndex, jArr3, 0, i8);
                System.arraycopy(this.flags, this.relativeFirstIndex, iArr, 0, i8);
                System.arraycopy(this.sizes, this.relativeFirstIndex, iArr2, 0, i8);
                System.arraycopy(this.cryptoDatas, this.relativeFirstIndex, cryptoDataArr, 0, i8);
                System.arraycopy(this.sourceIds, this.relativeFirstIndex, jArr, 0, i8);
                int i9 = this.relativeFirstIndex;
                System.arraycopy(this.offsets, 0, jArr2, i8, i9);
                System.arraycopy(this.timesUs, 0, jArr3, i8, i9);
                System.arraycopy(this.flags, 0, iArr, i8, i9);
                System.arraycopy(this.sizes, 0, iArr2, i8, i9);
                System.arraycopy(this.cryptoDatas, 0, cryptoDataArr, i8, i9);
                System.arraycopy(this.sourceIds, 0, jArr, i8, i9);
                this.offsets = jArr2;
                this.timesUs = jArr3;
                this.flags = iArr;
                this.sizes = iArr2;
                this.cryptoDatas = cryptoDataArr;
                this.sourceIds = jArr;
                this.relativeFirstIndex = 0;
                this.capacity = i6;
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public final int countUnreadSamplesBefore(long j) {
        int i = this.length;
        int relativeIndex = getRelativeIndex(i - 1);
        while (i > this.readPosition && this.timesUs[relativeIndex] >= j) {
            i--;
            relativeIndex--;
            if (relativeIndex == -1) {
                relativeIndex = this.capacity - 1;
            }
        }
        return i;
    }

    public final synchronized long discardSampleMetadataTo(long j, boolean z, boolean z2) throws Throwable {
        Throwable th;
        try {
            try {
                int i = this.length;
                if (i != 0) {
                    long[] jArr = this.timesUs;
                    int i2 = this.relativeFirstIndex;
                    if (j >= jArr[i2]) {
                        if (z2) {
                            try {
                                int i3 = this.readPosition;
                                if (i3 != i) {
                                    i = i3 + 1;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                throw th;
                            }
                        }
                        int iFindSampleBefore = findSampleBefore(i2, i, j, z);
                        if (iFindSampleBefore == -1) {
                            return -1L;
                        }
                        return discardSamples(iFindSampleBefore);
                    }
                }
                return -1L;
            } catch (Throwable th3) {
                th = th3;
                th = th;
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            th = th;
            throw th;
        }
    }

    public final synchronized long discardSampleMetadataToEnd() {
        int i = this.length;
        if (i == 0) {
            return -1L;
        }
        return discardSamples(i);
    }

    public synchronized long discardSampleMetadataToRead() {
        int i = this.readPosition;
        if (i == 0) {
            return -1L;
        }
        return discardSamples(i);
    }

    @GuardedBy("this")
    public final long discardSamples(int i) {
        this.largestDiscardedTimestampUs = Math.max(this.largestDiscardedTimestampUs, getLargestTimestamp(i));
        this.length -= i;
        int i2 = this.absoluteFirstIndex + i;
        this.absoluteFirstIndex = i2;
        int i3 = this.relativeFirstIndex + i;
        this.relativeFirstIndex = i3;
        int i4 = this.capacity;
        if (i3 >= i4) {
            this.relativeFirstIndex = i3 - i4;
        }
        int i5 = this.readPosition - i;
        this.readPosition = i5;
        if (i5 < 0) {
            this.readPosition = 0;
        }
        this.sharedSampleMetadata.discardTo(i2);
        if (this.length != 0) {
            return this.offsets[this.relativeFirstIndex];
        }
        int i6 = this.relativeFirstIndex;
        if (i6 == 0) {
            i6 = this.capacity;
        }
        int i7 = i6 - 1;
        return this.offsets[i7] + ((long) this.sizes[i7]);
    }

    public final void discardTo(long j, boolean z, boolean z2) {
        this.sampleDataQueue.discardDownstreamTo(discardSampleMetadataTo(j, z, z2));
    }

    public final void discardToEnd() {
        this.sampleDataQueue.discardDownstreamTo(discardSampleMetadataToEnd());
    }

    public final void discardToRead() {
        this.sampleDataQueue.discardDownstreamTo(discardSampleMetadataToRead());
    }

    public final void discardUpstreamFrom(long j) {
        if (this.length == 0) {
            return;
        }
        Assertions.checkArgument(j > getLargestReadTimestampUs());
        discardUpstreamSamples(this.absoluteFirstIndex + countUnreadSamplesBefore(j));
    }

    public final long discardUpstreamSampleMetadata(int i) {
        int writeIndex = getWriteIndex() - i;
        boolean z = false;
        Assertions.checkArgument(writeIndex >= 0 && writeIndex <= this.length - this.readPosition);
        int i2 = this.length - writeIndex;
        this.length = i2;
        this.largestQueuedTimestampUs = Math.max(this.largestDiscardedTimestampUs, getLargestTimestamp(i2));
        if (writeIndex == 0 && this.isLastSampleQueued) {
            z = true;
        }
        this.isLastSampleQueued = z;
        this.sharedSampleMetadata.discardFrom(i);
        int i3 = this.length;
        if (i3 == 0) {
            return 0L;
        }
        int relativeIndex = getRelativeIndex(i3 - 1);
        return this.offsets[relativeIndex] + ((long) this.sizes[relativeIndex]);
    }

    public final void discardUpstreamSamples(int i) {
        this.sampleDataQueue.discardUpstreamSampleBytes(discardUpstreamSampleMetadata(i));
    }

    public final int findSampleAfter(int i, int i2, long j, boolean z) {
        for (int i3 = 0; i3 < i2; i3++) {
            if (this.timesUs[i] >= j) {
                return i3;
            }
            i++;
            if (i == this.capacity) {
                i = 0;
            }
        }
        if (z) {
            return i2;
        }
        return -1;
    }

    public final int findSampleBefore(int i, int i2, long j, boolean z) {
        int i3 = -1;
        for (int i4 = 0; i4 < i2; i4++) {
            long j2 = this.timesUs[i];
            if (j2 > j) {
                break;
            }
            if (!z || (this.flags[i] & 1) != 0) {
                if (j2 == j) {
                    return i4;
                }
                i3 = i4;
            }
            i++;
            if (i == this.capacity) {
                i = 0;
            }
        }
        return i3;
    }

    @Override // androidx.media3.extractor.TrackOutput
    public final void format(Format format) {
        Format adjustedUpstreamFormat = getAdjustedUpstreamFormat(format);
        this.upstreamFormatAdjustmentRequired = false;
        this.unadjustedUpstreamFormat = format;
        boolean upstreamFormat = setUpstreamFormat(adjustedUpstreamFormat);
        UpstreamFormatChangedListener upstreamFormatChangedListener = this.upstreamFormatChangeListener;
        if (upstreamFormatChangedListener == null || !upstreamFormat) {
            return;
        }
        upstreamFormatChangedListener.onUpstreamFormatChanged(adjustedUpstreamFormat);
    }

    @CallSuper
    public Format getAdjustedUpstreamFormat(Format format) {
        if (this.sampleOffsetUs == 0 || format.subsampleOffsetUs == Long.MAX_VALUE) {
            return format;
        }
        Format.Builder builder = new Format.Builder(format);
        builder.subsampleOffsetUs = format.subsampleOffsetUs + this.sampleOffsetUs;
        return new Format(builder);
    }

    public final int getFirstIndex() {
        return this.absoluteFirstIndex;
    }

    public final synchronized long getFirstTimestampUs() {
        return this.length == 0 ? Long.MIN_VALUE : this.timesUs[this.relativeFirstIndex];
    }

    public final synchronized long getLargestQueuedTimestampUs() {
        return this.largestQueuedTimestampUs;
    }

    public final synchronized long getLargestReadTimestampUs() {
        return Math.max(this.largestDiscardedTimestampUs, getLargestTimestamp(this.readPosition));
    }

    public final long getLargestTimestamp(int i) {
        long jMax = Long.MIN_VALUE;
        if (i == 0) {
            return Long.MIN_VALUE;
        }
        int relativeIndex = getRelativeIndex(i - 1);
        for (int i2 = 0; i2 < i; i2++) {
            jMax = Math.max(jMax, this.timesUs[relativeIndex]);
            if ((this.flags[relativeIndex] & 1) != 0) {
                return jMax;
            }
            relativeIndex--;
            if (relativeIndex == -1) {
                relativeIndex = this.capacity - 1;
            }
        }
        return jMax;
    }

    public final int getReadIndex() {
        return this.absoluteFirstIndex + this.readPosition;
    }

    public final int getRelativeIndex(int i) {
        int i2 = this.relativeFirstIndex + i;
        int i3 = this.capacity;
        return i2 < i3 ? i2 : i2 - i3;
    }

    public final synchronized int getSkipCount(long j, boolean z) throws Throwable {
        Throwable th;
        try {
            try {
                int relativeIndex = getRelativeIndex(this.readPosition);
                if (!hasNextSample() || j < this.timesUs[relativeIndex]) {
                    return 0;
                }
                if (j <= this.largestQueuedTimestampUs || !z) {
                    int iFindSampleBefore = findSampleBefore(relativeIndex, this.length - this.readPosition, j, true);
                    if (iFindSampleBefore == -1) {
                        return 0;
                    }
                    return iFindSampleBefore;
                }
                try {
                    return this.length - this.readPosition;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                th = th;
            }
        } catch (Throwable th4) {
            th = th4;
        }
        throw th;
    }

    @Nullable
    public final synchronized Format getUpstreamFormat() {
        return this.upstreamFormatRequired ? null : this.upstreamFormat;
    }

    public final int getWriteIndex() {
        return this.absoluteFirstIndex + this.length;
    }

    public final boolean hasNextSample() {
        return this.readPosition != this.length;
    }

    public final void invalidateUpstreamFormatAdjustment() {
        this.upstreamFormatAdjustmentRequired = true;
    }

    public final synchronized boolean isLastSampleQueued() {
        return this.isLastSampleQueued;
    }

    @CallSuper
    public synchronized boolean isReady(boolean z) {
        Format format;
        boolean z2 = true;
        if (hasNextSample()) {
            if (this.sharedSampleMetadata.get(getReadIndex()).format != this.downstreamFormat) {
                return true;
            }
            return mayReadSample(getRelativeIndex(this.readPosition));
        }
        if (!z && !this.isLastSampleQueued && ((format = this.upstreamFormat) == null || format == this.downstreamFormat)) {
            z2 = false;
        }
        return z2;
    }

    public final boolean mayReadSample(int i) {
        DrmSession drmSession = this.currentDrmSession;
        if (drmSession == null || drmSession.getState() == 4) {
            return true;
        }
        return (this.flags[i] & 1073741824) == 0 && this.currentDrmSession.playClearSamplesWithoutKeys();
    }

    @CallSuper
    public void maybeThrowError() throws IOException {
        DrmSession drmSession = this.currentDrmSession;
        if (drmSession == null || drmSession.getState() != 1) {
            return;
        }
        DrmSession.DrmSessionException error = this.currentDrmSession.getError();
        error.getClass();
        throw error;
    }

    public final void onFormatResult(Format format, FormatHolder formatHolder) {
        Format format2 = this.downstreamFormat;
        boolean z = format2 == null;
        DrmInitData drmInitData = format2 == null ? null : format2.drmInitData;
        this.downstreamFormat = format;
        DrmInitData drmInitData2 = format.drmInitData;
        DrmSessionManager drmSessionManager = this.drmSessionManager;
        formatHolder.format = drmSessionManager != null ? format.copyWithCryptoType(drmSessionManager.getCryptoType(format)) : format;
        formatHolder.drmSession = this.currentDrmSession;
        if (this.drmSessionManager == null) {
            return;
        }
        if (z || !Util.areEqual(drmInitData, drmInitData2)) {
            DrmSession drmSession = this.currentDrmSession;
            DrmSession drmSessionAcquireSession = this.drmSessionManager.acquireSession(this.drmEventDispatcher, format);
            this.currentDrmSession = drmSessionAcquireSession;
            formatHolder.drmSession = drmSessionAcquireSession;
            if (drmSession != null) {
                drmSession.release(this.drmEventDispatcher);
            }
        }
    }

    public final synchronized int peekSampleMetadata(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z, boolean z2, SampleExtrasHolder sampleExtrasHolder) {
        try {
            decoderInputBuffer.waitingForKeys = false;
            if (!hasNextSample()) {
                if (!z2 && !this.isLastSampleQueued) {
                    Format format = this.upstreamFormat;
                    if (format == null || (!z && format == this.downstreamFormat)) {
                        return -3;
                    }
                    format.getClass();
                    onFormatResult(format, formatHolder);
                    return -5;
                }
                decoderInputBuffer.flags = 4;
                decoderInputBuffer.timeUs = Long.MIN_VALUE;
                return -4;
            }
            Format format2 = this.sharedSampleMetadata.get(getReadIndex()).format;
            if (!z && format2 == this.downstreamFormat) {
                int relativeIndex = getRelativeIndex(this.readPosition);
                if (!mayReadSample(relativeIndex)) {
                    decoderInputBuffer.waitingForKeys = true;
                    return -3;
                }
                decoderInputBuffer.flags = this.flags[relativeIndex];
                if (this.readPosition == this.length - 1 && (z2 || this.isLastSampleQueued)) {
                    decoderInputBuffer.addFlag(536870912);
                }
                long j = this.timesUs[relativeIndex];
                decoderInputBuffer.timeUs = j;
                if (j < this.startTimeUs) {
                    decoderInputBuffer.addFlag(Integer.MIN_VALUE);
                }
                sampleExtrasHolder.size = this.sizes[relativeIndex];
                sampleExtrasHolder.offset = this.offsets[relativeIndex];
                sampleExtrasHolder.cryptoData = this.cryptoDatas[relativeIndex];
                return -4;
            }
            onFormatResult(format2, formatHolder);
            return -5;
        } catch (Throwable th) {
            throw th;
        }
    }

    public final synchronized long peekSourceId() {
        try {
        } catch (Throwable th) {
            throw th;
        }
        return hasNextSample() ? this.sourceIds[getRelativeIndex(this.readPosition)] : this.upstreamSourceId;
    }

    @CallSuper
    public void preRelease() {
        discardToEnd();
        releaseDrmSessionReferences();
    }

    @CallSuper
    public int read(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i, boolean z) {
        int iPeekSampleMetadata = peekSampleMetadata(formatHolder, decoderInputBuffer, (i & 2) != 0, z, this.extrasHolder);
        if (iPeekSampleMetadata == -4 && !decoderInputBuffer.getFlag(4)) {
            boolean z2 = (i & 1) != 0;
            if ((4 & i) == 0) {
                if (z2) {
                    this.sampleDataQueue.peekToBuffer(decoderInputBuffer, this.extrasHolder);
                } else {
                    this.sampleDataQueue.readToBuffer(decoderInputBuffer, this.extrasHolder);
                }
            }
            if (!z2) {
                this.readPosition++;
            }
        }
        return iPeekSampleMetadata;
    }

    @CallSuper
    public void release() {
        reset(true);
        releaseDrmSessionReferences();
    }

    public final void releaseDrmSessionReferences() {
        DrmSession drmSession = this.currentDrmSession;
        if (drmSession != null) {
            drmSession.release(this.drmEventDispatcher);
            this.currentDrmSession = null;
            this.downstreamFormat = null;
        }
    }

    public final void reset() {
        reset(false);
    }

    public final synchronized void rewind() {
        this.readPosition = 0;
        this.sampleDataQueue.rewind();
    }

    @Override // androidx.media3.extractor.TrackOutput
    public int sampleData(DataReader dataReader, int i, boolean z) {
        return sampleData(dataReader, i, z, 0);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x004d  */
    @Override // androidx.media3.extractor.TrackOutput
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void sampleMetadata(long r13, int r15, int r16, int r17, @androidx.annotation.Nullable androidx.media3.extractor.TrackOutput.CryptoData r18) {
        /*
            r12 = this;
            boolean r1 = r12.upstreamFormatAdjustmentRequired
            if (r1 == 0) goto Lc
            androidx.media3.common.Format r1 = r12.unadjustedUpstreamFormat
            androidx.media3.common.util.Assertions.checkStateNotNull(r1)
            r12.format(r1)
        Lc:
            r1 = r15 & 1
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L14
            r4 = 1
            goto L15
        L14:
            r4 = 0
        L15:
            boolean r5 = r12.upstreamKeyframeRequired
            if (r5 == 0) goto L1e
            if (r4 != 0) goto L1c
            goto L5e
        L1c:
            r12.upstreamKeyframeRequired = r2
        L1e:
            long r5 = r12.sampleOffsetUs
            long r5 = r5 + r13
            boolean r7 = r12.allSamplesAreSyncSamples
            if (r7 == 0) goto L4d
            long r7 = r12.startTimeUs
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 >= 0) goto L2c
            goto L5e
        L2c:
            if (r1 != 0) goto L4d
            boolean r1 = r12.loggedUnexpectedNonSyncSample
            if (r1 != 0) goto L49
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r7 = "Overriding unexpected non-sync sample for format: "
            r1.<init>(r7)
            androidx.media3.common.Format r7 = r12.upstreamFormat
            r1.append(r7)
            java.lang.String r1 = r1.toString()
            java.lang.String r7 = "SampleQueue"
            androidx.media3.common.util.Log.w(r7, r1)
            r12.loggedUnexpectedNonSyncSample = r3
        L49:
            r1 = r15 | 1
            r3 = r1
            goto L4e
        L4d:
            r3 = r15
        L4e:
            boolean r1 = r12.pendingSplice
            if (r1 == 0) goto L5f
            if (r4 == 0) goto L5e
            boolean r1 = r12.attemptSplice(r5)
            if (r1 != 0) goto L5b
            goto L5e
        L5b:
            r12.pendingSplice = r2
            goto L5f
        L5e:
            return
        L5f:
            androidx.media3.exoplayer.source.SampleDataQueue r1 = r12.sampleDataQueue
            long r1 = r1.getTotalBytesWritten()
            r4 = r16
            long r7 = (long) r4
            long r1 = r1 - r7
            r7 = r17
            long r7 = (long) r7
            long r1 = r1 - r7
            r10 = r5
            r6 = r4
            r4 = r1
            r1 = r10
            r0 = r12
            r7 = r18
            r0.commitSample(r1, r3, r4, r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.source.SampleQueue.sampleMetadata(long, int, int, int, androidx.media3.extractor.TrackOutput$CryptoData):void");
    }

    public final synchronized boolean seekTo(int i) {
        rewind();
        int i2 = this.absoluteFirstIndex;
        if (i >= i2 && i <= this.length + i2) {
            this.startTimeUs = Long.MIN_VALUE;
            this.readPosition = i - i2;
            return true;
        }
        return false;
    }

    public final void setSampleOffsetUs(long j) {
        if (this.sampleOffsetUs != j) {
            this.sampleOffsetUs = j;
            this.upstreamFormatAdjustmentRequired = true;
        }
    }

    public final void setStartTimeUs(long j) {
        this.startTimeUs = j;
    }

    public final synchronized boolean setUpstreamFormat(Format format) {
        try {
            this.upstreamFormatRequired = false;
            if (Util.areEqual(format, this.upstreamFormat)) {
                return false;
            }
            if (this.sharedSampleMetadata.isEmpty() || !this.sharedSampleMetadata.getEndValue().format.equals(format)) {
                this.upstreamFormat = format;
            } else {
                this.upstreamFormat = this.sharedSampleMetadata.getEndValue().format;
            }
            boolean z = this.allSamplesAreSyncSamples;
            Format format2 = this.upstreamFormat;
            this.allSamplesAreSyncSamples = z & MimeTypes.allSamplesAreSyncSamples(format2.sampleMimeType, format2.codecs);
            this.loggedUnexpectedNonSyncSample = false;
            return true;
        } catch (Throwable th) {
            throw th;
        }
    }

    public final void setUpstreamFormatChangeListener(@Nullable UpstreamFormatChangedListener upstreamFormatChangedListener) {
        this.upstreamFormatChangeListener = upstreamFormatChangedListener;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x000e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final synchronized void skip(int r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            if (r3 < 0) goto Le
            int r0 = r2.readPosition     // Catch: java.lang.Throwable -> Lc
            int r0 = r0 + r3
            int r1 = r2.length     // Catch: java.lang.Throwable -> Lc
            if (r0 > r1) goto Le
            r0 = 1
            goto Lf
        Lc:
            r3 = move-exception
            goto L19
        Le:
            r0 = 0
        Lf:
            androidx.media3.common.util.Assertions.checkArgument(r0)     // Catch: java.lang.Throwable -> Lc
            int r0 = r2.readPosition     // Catch: java.lang.Throwable -> Lc
            int r0 = r0 + r3
            r2.readPosition = r0     // Catch: java.lang.Throwable -> Lc
            monitor-exit(r2)
            return
        L19:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> Lc
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.source.SampleQueue.skip(int):void");
    }

    public final void sourceId(long j) {
        this.upstreamSourceId = j;
    }

    public final void splice() {
        this.pendingSplice = true;
    }

    @CallSuper
    public void reset(boolean z) {
        this.sampleDataQueue.reset();
        this.length = 0;
        this.absoluteFirstIndex = 0;
        this.relativeFirstIndex = 0;
        this.readPosition = 0;
        this.upstreamKeyframeRequired = true;
        this.startTimeUs = Long.MIN_VALUE;
        this.largestDiscardedTimestampUs = Long.MIN_VALUE;
        this.largestQueuedTimestampUs = Long.MIN_VALUE;
        this.isLastSampleQueued = false;
        this.sharedSampleMetadata.clear();
        if (z) {
            this.unadjustedUpstreamFormat = null;
            this.upstreamFormat = null;
            this.upstreamFormatRequired = true;
            this.allSamplesAreSyncSamples = true;
        }
    }

    @Override // androidx.media3.extractor.TrackOutput
    public void sampleData(ParsableByteArray parsableByteArray, int i) {
        sampleData(parsableByteArray, i, 0);
    }

    @Override // androidx.media3.extractor.TrackOutput
    public final int sampleData(DataReader dataReader, int i, boolean z, int i2) throws IOException {
        return this.sampleDataQueue.sampleData(dataReader, i, z);
    }

    @Override // androidx.media3.extractor.TrackOutput
    public final void sampleData(ParsableByteArray parsableByteArray, int i, int i2) {
        this.sampleDataQueue.sampleData(parsableByteArray, i);
    }

    @Deprecated
    public static SampleQueue createWithDrm(Allocator allocator, Looper looper, DrmSessionManager drmSessionManager, DrmSessionEventListener.EventDispatcher eventDispatcher) {
        drmSessionManager.setPlayer(looper, PlayerId.UNSET);
        eventDispatcher.getClass();
        return new SampleQueue(allocator, drmSessionManager, eventDispatcher);
    }

    public final synchronized boolean seekTo(long j, boolean z) throws Throwable {
        SampleQueue sampleQueue;
        long j2;
        int iFindSampleBefore;
        try {
            try {
                rewind();
                int relativeIndex = getRelativeIndex(this.readPosition);
                if (!hasNextSample() || j < this.timesUs[relativeIndex] || (j > this.largestQueuedTimestampUs && !z)) {
                    return false;
                }
                if (this.allSamplesAreSyncSamples) {
                    sampleQueue = this;
                    j2 = j;
                    iFindSampleBefore = sampleQueue.findSampleAfter(relativeIndex, this.length - this.readPosition, j2, z);
                } else {
                    sampleQueue = this;
                    j2 = j;
                    iFindSampleBefore = sampleQueue.findSampleBefore(relativeIndex, sampleQueue.length - sampleQueue.readPosition, j2, true);
                }
                if (iFindSampleBefore == -1) {
                    return false;
                }
                sampleQueue.startTimeUs = j2;
                sampleQueue.readPosition += iFindSampleBefore;
                return true;
            } catch (Throwable th) {
                th = th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        throw th;
    }
}
