package androidx.media3.extractor;

import androidx.annotation.Nullable;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.SeekMap;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public abstract class BinarySearchSeeker {
    public static final long MAX_SKIP_BYTES = 262144;
    public final int minimumSearchRange;
    public final BinarySearchSeekMap seekMap;

    @Nullable
    public SeekOperationParams seekOperationParams;
    public final TimestampSeeker timestampSeeker;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class BinarySearchSeekMap implements SeekMap {
        public final long approxBytesPerFrame;
        public final long ceilingBytePosition;
        public final long ceilingTimePosition;
        public final long durationUs;
        public final long floorBytePosition;
        public final long floorTimePosition;
        public final SeekTimestampConverter seekTimestampConverter;

        public BinarySearchSeekMap(SeekTimestampConverter seekTimestampConverter, long j, long j2, long j3, long j4, long j5, long j6) {
            this.seekTimestampConverter = seekTimestampConverter;
            this.durationUs = j;
            this.floorTimePosition = j2;
            this.ceilingTimePosition = j3;
            this.floorBytePosition = j4;
            this.ceilingBytePosition = j5;
            this.approxBytesPerFrame = j6;
        }

        @Override // androidx.media3.extractor.SeekMap
        public long getDurationUs() {
            return this.durationUs;
        }

        @Override // androidx.media3.extractor.SeekMap
        public SeekMap.SeekPoints getSeekPoints(long j) {
            SeekPoint seekPoint = new SeekPoint(j, SeekOperationParams.calculateNextSearchBytePosition(this.seekTimestampConverter.timeUsToTargetTime(j), this.floorTimePosition, this.ceilingTimePosition, this.floorBytePosition, this.ceilingBytePosition, this.approxBytesPerFrame));
            return new SeekMap.SeekPoints(seekPoint, seekPoint);
        }

        @Override // androidx.media3.extractor.SeekMap
        public boolean isSeekable() {
            return true;
        }

        public long timeUsToTargetTime(long j) {
            return this.seekTimestampConverter.timeUsToTargetTime(j);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class SeekOperationParams {
        public final long approxBytesPerFrame;
        public long ceilingBytePosition;
        public long ceilingTimePosition;
        public long floorBytePosition;
        public long floorTimePosition;
        public long nextSearchBytePosition;
        public final long seekTimeUs;
        public final long targetTimePosition;

        public SeekOperationParams(long j, long j2, long j3, long j4, long j5, long j6, long j7) {
            this.seekTimeUs = j;
            this.targetTimePosition = j2;
            this.floorTimePosition = j3;
            this.ceilingTimePosition = j4;
            this.floorBytePosition = j5;
            this.ceilingBytePosition = j6;
            this.approxBytesPerFrame = j7;
            this.nextSearchBytePosition = calculateNextSearchBytePosition(j2, j3, j4, j5, j6, j7);
        }

        public static long calculateNextSearchBytePosition(long j, long j2, long j3, long j4, long j5, long j6) {
            if (j4 + 1 >= j5 || j2 + 1 >= j3) {
                return j4;
            }
            long j7 = (long) ((j - j2) * ((j5 - j4) / (j3 - j2)));
            return Util.constrainValue(((j7 + j4) - j6) - (j7 / 20), j4, j5 - 1);
        }

        public final long getCeilingBytePosition() {
            return this.ceilingBytePosition;
        }

        public final long getFloorBytePosition() {
            return this.floorBytePosition;
        }

        public final long getNextSearchBytePosition() {
            return this.nextSearchBytePosition;
        }

        public final long getSeekTimeUs() {
            return this.seekTimeUs;
        }

        public final long getTargetTimePosition() {
            return this.targetTimePosition;
        }

        public final void updateNextSearchBytePosition() {
            this.nextSearchBytePosition = calculateNextSearchBytePosition(this.targetTimePosition, this.floorTimePosition, this.ceilingTimePosition, this.floorBytePosition, this.ceilingBytePosition, this.approxBytesPerFrame);
        }

        public final void updateSeekCeiling(long j, long j2) {
            this.ceilingTimePosition = j;
            this.ceilingBytePosition = j2;
            updateNextSearchBytePosition();
        }

        public final void updateSeekFloor(long j, long j2) {
            this.floorTimePosition = j;
            this.floorBytePosition = j2;
            updateNextSearchBytePosition();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface SeekTimestampConverter {
        long timeUsToTargetTime(long j);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class TimestampSearchResult {
        public static final TimestampSearchResult NO_TIMESTAMP_IN_RANGE_RESULT = new TimestampSearchResult(-3, -9223372036854775807L, -1);
        public static final int TYPE_NO_TIMESTAMP = -3;
        public static final int TYPE_POSITION_OVERESTIMATED = -1;
        public static final int TYPE_POSITION_UNDERESTIMATED = -2;
        public static final int TYPE_TARGET_TIMESTAMP_FOUND = 0;
        public final long bytePositionToUpdate;
        public final long timestampToUpdate;
        public final int type;

        public TimestampSearchResult(int i, long j, long j2) {
            this.type = i;
            this.timestampToUpdate = j;
            this.bytePositionToUpdate = j2;
        }

        public static TimestampSearchResult overestimatedResult(long j, long j2) {
            return new TimestampSearchResult(-1, j, j2);
        }

        public static TimestampSearchResult targetFoundResult(long j) {
            return new TimestampSearchResult(0, -9223372036854775807L, j);
        }

        public static TimestampSearchResult underestimatedResult(long j, long j2) {
            return new TimestampSearchResult(-2, j, j2);
        }
    }

    public BinarySearchSeeker(SeekTimestampConverter seekTimestampConverter, TimestampSeeker timestampSeeker, long j, long j2, long j3, long j4, long j5, long j6, int i) {
        this.timestampSeeker = timestampSeeker;
        this.minimumSearchRange = i;
        this.seekMap = new BinarySearchSeekMap(seekTimestampConverter, j, j2, j3, j4, j5, j6);
    }

    public SeekOperationParams createSeekParamsForTargetTimeUs(long j) {
        long jTimeUsToTargetTime = this.seekMap.timeUsToTargetTime(j);
        BinarySearchSeekMap binarySearchSeekMap = this.seekMap;
        return new SeekOperationParams(j, jTimeUsToTargetTime, binarySearchSeekMap.floorTimePosition, binarySearchSeekMap.ceilingTimePosition, binarySearchSeekMap.floorBytePosition, binarySearchSeekMap.ceilingBytePosition, binarySearchSeekMap.approxBytesPerFrame);
    }

    public final SeekMap getSeekMap() {
        return this.seekMap;
    }

    public int handlePendingSeek(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        while (true) {
            SeekOperationParams seekOperationParams = this.seekOperationParams;
            Assertions.checkStateNotNull(seekOperationParams);
            long j = seekOperationParams.floorBytePosition;
            long j2 = seekOperationParams.ceilingBytePosition;
            long j3 = seekOperationParams.nextSearchBytePosition;
            if (j2 - j <= this.minimumSearchRange) {
                markSeekOperationFinished(false, j);
                return seekToPosition(extractorInput, j, positionHolder);
            }
            if (!skipInputUntilPosition(extractorInput, j3)) {
                return seekToPosition(extractorInput, j3, positionHolder);
            }
            extractorInput.resetPeekPosition();
            TimestampSearchResult timestampSearchResultSearchForTimestamp = this.timestampSeeker.searchForTimestamp(extractorInput, seekOperationParams.targetTimePosition);
            int i = timestampSearchResultSearchForTimestamp.type;
            if (i == -3) {
                markSeekOperationFinished(false, j3);
                return seekToPosition(extractorInput, j3, positionHolder);
            }
            if (i == -2) {
                seekOperationParams.updateSeekFloor(timestampSearchResultSearchForTimestamp.timestampToUpdate, timestampSearchResultSearchForTimestamp.bytePositionToUpdate);
            } else {
                if (i != -1) {
                    if (i != 0) {
                        throw new IllegalStateException("Invalid case");
                    }
                    skipInputUntilPosition(extractorInput, timestampSearchResultSearchForTimestamp.bytePositionToUpdate);
                    markSeekOperationFinished(true, timestampSearchResultSearchForTimestamp.bytePositionToUpdate);
                    return seekToPosition(extractorInput, timestampSearchResultSearchForTimestamp.bytePositionToUpdate, positionHolder);
                }
                seekOperationParams.updateSeekCeiling(timestampSearchResultSearchForTimestamp.timestampToUpdate, timestampSearchResultSearchForTimestamp.bytePositionToUpdate);
            }
        }
    }

    public final boolean isSeeking() {
        return this.seekOperationParams != null;
    }

    public final void markSeekOperationFinished(boolean z, long j) {
        this.seekOperationParams = null;
        this.timestampSeeker.onSeekFinished();
    }

    public final int seekToPosition(ExtractorInput extractorInput, long j, PositionHolder positionHolder) {
        if (j == extractorInput.getPosition()) {
            return 0;
        }
        positionHolder.position = j;
        return 1;
    }

    public final void setSeekTargetUs(long j) {
        SeekOperationParams seekOperationParams = this.seekOperationParams;
        if (seekOperationParams == null || seekOperationParams.seekTimeUs != j) {
            this.seekOperationParams = createSeekParamsForTargetTimeUs(j);
        }
    }

    public final boolean skipInputUntilPosition(ExtractorInput extractorInput, long j) throws IOException {
        long position = j - extractorInput.getPosition();
        if (position < 0 || position > 262144) {
            return false;
        }
        extractorInput.skipFully((int) position);
        return true;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface TimestampSeeker {
        void onSeekFinished();

        TimestampSearchResult searchForTimestamp(ExtractorInput extractorInput, long j) throws IOException;

        /* JADX INFO: renamed from: androidx.media3.extractor.BinarySearchSeeker$TimestampSeeker$-CC, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public final /* synthetic */ class CC {
            public static void $default$onSeekFinished(TimestampSeeker timestampSeeker) {
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DefaultSeekTimestampConverter implements SeekTimestampConverter {
        @Override // androidx.media3.extractor.BinarySearchSeeker.SeekTimestampConverter
        public long timeUsToTargetTime(long j) {
            return j;
        }
    }

    public void onSeekOperationFinished(boolean z, long j) {
    }
}
