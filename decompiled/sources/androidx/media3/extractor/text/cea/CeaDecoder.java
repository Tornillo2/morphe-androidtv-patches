package androidx.media3.extractor.text.cea;

import androidx.annotation.Nullable;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.DecoderOutputBuffer;
import androidx.media3.extractor.text.Subtitle;
import androidx.media3.extractor.text.SubtitleDecoder;
import androidx.media3.extractor.text.SubtitleDecoderException;
import androidx.media3.extractor.text.SubtitleInputBuffer;
import androidx.media3.extractor.text.SubtitleOutputBuffer;
import androidx.media3.extractor.text.cea.CeaDecoder;
import java.util.ArrayDeque;
import java.util.PriorityQueue;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class CeaDecoder implements SubtitleDecoder {
    public static final int NUM_INPUT_BUFFERS = 10;
    public static final int NUM_OUTPUT_BUFFERS = 2;
    public final ArrayDeque<CeaInputBuffer> availableInputBuffers = new ArrayDeque<>();
    public final ArrayDeque<SubtitleOutputBuffer> availableOutputBuffers;

    @Nullable
    public CeaInputBuffer dequeuedInputBuffer;
    public long playbackPositionUs;
    public long queuedInputBufferCount;
    public final PriorityQueue<CeaInputBuffer> queuedInputBuffers;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class CeaInputBuffer extends SubtitleInputBuffer implements Comparable<CeaInputBuffer> {
        public long queuedInputBufferCount;

        public CeaInputBuffer() {
        }

        @Override // java.lang.Comparable
        public int compareTo(CeaInputBuffer ceaInputBuffer) {
            if (getFlag(4) != ceaInputBuffer.getFlag(4)) {
                return getFlag(4) ? 1 : -1;
            }
            long j = this.timeUs - ceaInputBuffer.timeUs;
            if (j == 0) {
                j = this.queuedInputBufferCount - ceaInputBuffer.queuedInputBufferCount;
                if (j == 0) {
                    return 0;
                }
            }
            return j > 0 ? 1 : -1;
        }

        public CeaInputBuffer(AnonymousClass1 anonymousClass1) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class CeaOutputBuffer extends SubtitleOutputBuffer {
        public DecoderOutputBuffer.Owner<CeaOutputBuffer> owner;

        public CeaOutputBuffer(DecoderOutputBuffer.Owner<CeaOutputBuffer> owner) {
            this.owner = owner;
        }

        @Override // androidx.media3.decoder.DecoderOutputBuffer
        public final void release() {
            this.owner.releaseOutputBuffer(this);
        }
    }

    public CeaDecoder() {
        for (int i = 0; i < 10; i++) {
            this.availableInputBuffers.add(new CeaInputBuffer());
        }
        this.availableOutputBuffers = new ArrayDeque<>();
        for (int i2 = 0; i2 < 2; i2++) {
            this.availableOutputBuffers.add(new CeaOutputBuffer(new DecoderOutputBuffer.Owner() { // from class: androidx.media3.extractor.text.cea.CeaDecoder$$ExternalSyntheticLambda0
                @Override // androidx.media3.decoder.DecoderOutputBuffer.Owner
                public final void releaseOutputBuffer(DecoderOutputBuffer decoderOutputBuffer) {
                    this.f$0.releaseOutputBuffer((CeaDecoder.CeaOutputBuffer) decoderOutputBuffer);
                }
            }));
        }
        this.queuedInputBuffers = new PriorityQueue<>();
    }

    public abstract Subtitle createSubtitle();

    public abstract void decode(SubtitleInputBuffer subtitleInputBuffer);

    @Override // androidx.media3.decoder.Decoder
    public void flush() {
        this.queuedInputBufferCount = 0L;
        this.playbackPositionUs = 0L;
        while (!this.queuedInputBuffers.isEmpty()) {
            CeaInputBuffer ceaInputBufferPoll = this.queuedInputBuffers.poll();
            Util.castNonNull(ceaInputBufferPoll);
            releaseInputBuffer(ceaInputBufferPoll);
        }
        CeaInputBuffer ceaInputBuffer = this.dequeuedInputBuffer;
        if (ceaInputBuffer != null) {
            releaseInputBuffer(ceaInputBuffer);
            this.dequeuedInputBuffer = null;
        }
    }

    @Nullable
    public final SubtitleOutputBuffer getAvailableOutputBuffer() {
        return this.availableOutputBuffers.pollFirst();
    }

    @Override // androidx.media3.decoder.Decoder
    public abstract String getName();

    public final long getPositionUs() {
        return this.playbackPositionUs;
    }

    public abstract boolean isNewSubtitleDataAvailable();

    public final void releaseInputBuffer(CeaInputBuffer ceaInputBuffer) {
        ceaInputBuffer.clear();
        this.availableInputBuffers.add(ceaInputBuffer);
    }

    public void releaseOutputBuffer(SubtitleOutputBuffer subtitleOutputBuffer) {
        subtitleOutputBuffer.clear();
        this.availableOutputBuffers.add(subtitleOutputBuffer);
    }

    @Override // androidx.media3.extractor.text.SubtitleDecoder
    public void setPositionUs(long j) {
        this.playbackPositionUs = j;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.media3.decoder.Decoder
    @Nullable
    public SubtitleInputBuffer dequeueInputBuffer() throws SubtitleDecoderException {
        Assertions.checkState(this.dequeuedInputBuffer == null);
        if (this.availableInputBuffers.isEmpty()) {
            return null;
        }
        CeaInputBuffer ceaInputBufferPollFirst = this.availableInputBuffers.pollFirst();
        this.dequeuedInputBuffer = ceaInputBufferPollFirst;
        return ceaInputBufferPollFirst;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.media3.decoder.Decoder
    @Nullable
    public SubtitleOutputBuffer dequeueOutputBuffer() throws SubtitleDecoderException {
        if (this.availableOutputBuffers.isEmpty()) {
            return null;
        }
        while (!this.queuedInputBuffers.isEmpty()) {
            CeaInputBuffer ceaInputBufferPeek = this.queuedInputBuffers.peek();
            Util.castNonNull(ceaInputBufferPeek);
            if (ceaInputBufferPeek.timeUs > this.playbackPositionUs) {
                return null;
            }
            CeaInputBuffer ceaInputBufferPoll = this.queuedInputBuffers.poll();
            if (ceaInputBufferPoll.getFlag(4)) {
                SubtitleOutputBuffer subtitleOutputBufferPollFirst = this.availableOutputBuffers.pollFirst();
                subtitleOutputBufferPollFirst.addFlag(4);
                ceaInputBufferPoll.clear();
                this.availableInputBuffers.add(ceaInputBufferPoll);
                return subtitleOutputBufferPollFirst;
            }
            decode(ceaInputBufferPoll);
            if (isNewSubtitleDataAvailable()) {
                Subtitle subtitleCreateSubtitle = createSubtitle();
                SubtitleOutputBuffer subtitleOutputBufferPollFirst2 = this.availableOutputBuffers.pollFirst();
                subtitleOutputBufferPollFirst2.setContent(ceaInputBufferPoll.timeUs, subtitleCreateSubtitle, Long.MAX_VALUE);
                ceaInputBufferPoll.clear();
                this.availableInputBuffers.add(ceaInputBufferPoll);
                return subtitleOutputBufferPollFirst2;
            }
            ceaInputBufferPoll.clear();
            this.availableInputBuffers.add(ceaInputBufferPoll);
        }
        return null;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.media3.decoder.Decoder
    public void queueInputBuffer(SubtitleInputBuffer subtitleInputBuffer) throws SubtitleDecoderException {
        Assertions.checkArgument(subtitleInputBuffer == this.dequeuedInputBuffer);
        CeaInputBuffer ceaInputBuffer = (CeaInputBuffer) subtitleInputBuffer;
        if (ceaInputBuffer.getFlag(Integer.MIN_VALUE)) {
            ceaInputBuffer.clear();
            this.availableInputBuffers.add(ceaInputBuffer);
        } else {
            long j = this.queuedInputBufferCount;
            this.queuedInputBufferCount = 1 + j;
            ceaInputBuffer.queuedInputBufferCount = j;
            this.queuedInputBuffers.add(ceaInputBuffer);
        }
        this.dequeuedInputBuffer = null;
    }

    @Override // androidx.media3.decoder.Decoder
    public void release() {
    }

    @Override // androidx.media3.decoder.Decoder
    public final void setOutputStartTimeUs(long j) {
    }
}
