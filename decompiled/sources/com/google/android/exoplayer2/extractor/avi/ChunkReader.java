package com.google.android.exoplayer2.extractor.avi;

import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.SeekPoint;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.upstream.DataReader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class ChunkReader {
    public static final int CHUNK_TYPE_AUDIO = 1651965952;
    public static final int CHUNK_TYPE_VIDEO_COMPRESSED = 1667497984;
    public static final int CHUNK_TYPE_VIDEO_UNCOMPRESSED = 1650720768;
    public static final int INITIAL_INDEX_SIZE = 512;
    public final int alternativeChunkId;
    public int bytesRemainingInCurrentChunk;
    public final int chunkId;
    public int currentChunkIndex;
    public int currentChunkSize;
    public final long durationUs;
    public int indexChunkCount;
    public int indexSize;
    public int[] keyFrameIndices;
    public long[] keyFrameOffsets;
    public final int streamHeaderChunkCount;
    public final TrackOutput trackOutput;

    public ChunkReader(int i, int i2, long j, int i3, TrackOutput trackOutput) {
        boolean z = true;
        if (i2 != 1 && i2 != 2) {
            z = false;
        }
        Assertions.checkArgument(z);
        this.durationUs = j;
        this.streamHeaderChunkCount = i3;
        this.trackOutput = trackOutput;
        this.chunkId = getChunkIdFourCc(i, i2 == 2 ? 1667497984 : 1651965952);
        this.alternativeChunkId = i2 == 2 ? getChunkIdFourCc(i, 1650720768) : -1;
        this.keyFrameOffsets = new long[512];
        this.keyFrameIndices = new int[512];
    }

    public static int getChunkIdFourCc(int i, int i2) {
        return (((i % 10) + 48) << 8) | ((i / 10) + 48) | i2;
    }

    public void advanceCurrentChunk() {
        this.currentChunkIndex++;
    }

    public void appendKeyFrameToIndex(long j) {
        if (this.indexSize == this.keyFrameIndices.length) {
            long[] jArr = this.keyFrameOffsets;
            this.keyFrameOffsets = Arrays.copyOf(jArr, (jArr.length * 3) / 2);
            int[] iArr = this.keyFrameIndices;
            this.keyFrameIndices = Arrays.copyOf(iArr, (iArr.length * 3) / 2);
        }
        long[] jArr2 = this.keyFrameOffsets;
        int i = this.indexSize;
        jArr2[i] = j;
        this.keyFrameIndices[i] = this.indexChunkCount;
        this.indexSize = i + 1;
    }

    public void compactIndex() {
        this.keyFrameOffsets = Arrays.copyOf(this.keyFrameOffsets, this.indexSize);
        this.keyFrameIndices = Arrays.copyOf(this.keyFrameIndices, this.indexSize);
    }

    public final long getChunkTimestampUs(int i) {
        return (this.durationUs * ((long) i)) / ((long) this.streamHeaderChunkCount);
    }

    public long getCurrentChunkTimestampUs() {
        return getChunkTimestampUs(this.currentChunkIndex);
    }

    public long getFrameDurationUs() {
        return getChunkTimestampUs(1);
    }

    public final SeekPoint getSeekPoint(int i) {
        return new SeekPoint(getChunkTimestampUs(1) * ((long) this.keyFrameIndices[i]), this.keyFrameOffsets[i]);
    }

    public SeekMap.SeekPoints getSeekPoints(long j) {
        int chunkTimestampUs = (int) (j / getChunkTimestampUs(1));
        int iBinarySearchFloor = Util.binarySearchFloor(this.keyFrameIndices, chunkTimestampUs, true, true);
        if (this.keyFrameIndices[iBinarySearchFloor] == chunkTimestampUs) {
            SeekPoint seekPoint = getSeekPoint(iBinarySearchFloor);
            return new SeekMap.SeekPoints(seekPoint, seekPoint);
        }
        SeekPoint seekPoint2 = getSeekPoint(iBinarySearchFloor);
        int i = iBinarySearchFloor + 1;
        return i < this.keyFrameOffsets.length ? new SeekMap.SeekPoints(seekPoint2, getSeekPoint(i)) : new SeekMap.SeekPoints(seekPoint2, seekPoint2);
    }

    public boolean handlesChunkId(int i) {
        return this.chunkId == i || this.alternativeChunkId == i;
    }

    public void incrementIndexChunkCount() {
        this.indexChunkCount++;
    }

    public boolean isAudio() {
        return (this.chunkId & 1651965952) == 1651965952;
    }

    public boolean isCurrentFrameAKeyFrame() {
        return Arrays.binarySearch(this.keyFrameIndices, this.currentChunkIndex) >= 0;
    }

    public boolean isVideo() {
        return (this.chunkId & 1667497984) == 1667497984;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public boolean onChunkData(ExtractorInput extractorInput) throws IOException {
        int i = this.bytesRemainingInCurrentChunk;
        int iSampleData = i - this.trackOutput.sampleData((DataReader) extractorInput, i, false);
        this.bytesRemainingInCurrentChunk = iSampleData;
        boolean z = iSampleData == 0;
        if (z) {
            if (this.currentChunkSize > 0) {
                this.trackOutput.sampleMetadata(getChunkTimestampUs(this.currentChunkIndex), isCurrentFrameAKeyFrame() ? 1 : 0, this.currentChunkSize, 0, null);
            }
            advanceCurrentChunk();
        }
        return z;
    }

    public void onChunkStart(int i) {
        this.currentChunkSize = i;
        this.bytesRemainingInCurrentChunk = i;
    }

    public void seekToPosition(long j) {
        if (this.indexSize == 0) {
            this.currentChunkIndex = 0;
        } else {
            this.currentChunkIndex = this.keyFrameIndices[Util.binarySearchFloor(this.keyFrameOffsets, j, true, true)];
        }
    }
}
