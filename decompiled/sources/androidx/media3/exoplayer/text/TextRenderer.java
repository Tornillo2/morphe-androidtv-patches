package androidx.media3.exoplayer.text;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.Nullable;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.text.Cue;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.decoder.DecoderException;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.BaseRenderer;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.extractor.text.CueDecoder;
import androidx.media3.extractor.text.CuesWithTiming;
import androidx.media3.extractor.text.SubtitleDecoder;
import androidx.media3.extractor.text.SubtitleDecoderException;
import androidx.media3.extractor.text.SubtitleInputBuffer;
import androidx.media3.extractor.text.SubtitleOutputBuffer;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.RegularImmutableList;
import j$.util.Objects;
import java.nio.ByteBuffer;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class TextRenderer extends BaseRenderer implements Handler.Callback {
    public static final int MSG_UPDATE_OUTPUT = 0;
    public static final int REPLACEMENT_STATE_NONE = 0;
    public static final int REPLACEMENT_STATE_SIGNAL_END_OF_STREAM = 1;
    public static final int REPLACEMENT_STATE_WAIT_END_OF_STREAM = 2;
    public static final String TAG = "TextRenderer";
    public final CueDecoder cueDecoder;
    public final DecoderInputBuffer cueDecoderInputBuffer;
    public CuesResolver cuesResolver;
    public int decoderReplacementState;
    public long finalStreamEndPositionUs;
    public final FormatHolder formatHolder;
    public boolean inputStreamEnded;
    public long lastRendererPositionUs;
    public boolean legacyDecodingEnabled;

    @Nullable
    public SubtitleOutputBuffer nextSubtitle;
    public int nextSubtitleEventIndex;

    @Nullable
    public SubtitleInputBuffer nextSubtitleInputBuffer;
    public final TextOutput output;

    @Nullable
    public final Handler outputHandler;
    public boolean outputStreamEnded;
    public long outputStreamOffsetUs;

    @Nullable
    public Format streamFormat;

    @Nullable
    public SubtitleOutputBuffer subtitle;

    @Nullable
    public SubtitleDecoder subtitleDecoder;
    public final SubtitleDecoderFactory subtitleDecoderFactory;
    public boolean waitingForKeyFrame;

    public TextRenderer(TextOutput textOutput, @Nullable Looper looper) {
        this(textOutput, looper, SubtitleDecoderFactory.DEFAULT);
    }

    @SideEffectFree
    private long getPresentationTimeUs(long j) {
        Assertions.checkState(j != -9223372036854775807L);
        Assertions.checkState(this.outputStreamOffsetUs != -9223372036854775807L);
        return j - this.outputStreamOffsetUs;
    }

    @SideEffectFree
    public static boolean isCuesWithTiming(Format format) {
        return Objects.equals(format.sampleMimeType, MimeTypes.APPLICATION_MEDIA3_CUES);
    }

    @RequiresNonNull({"streamFormat"})
    public final void assertLegacyDecodingEnabledIfRequired() {
        Assertions.checkState(this.legacyDecodingEnabled || Objects.equals(this.streamFormat.sampleMimeType, "application/cea-608") || Objects.equals(this.streamFormat.sampleMimeType, "application/x-mp4-cea-608") || Objects.equals(this.streamFormat.sampleMimeType, "application/cea-708"), "Legacy decoding is disabled, can't handle " + this.streamFormat.sampleMimeType + " samples (expected application/x-media3-cues).");
    }

    public final void clearOutput() {
        updateOutput(new CueGroup(RegularImmutableList.EMPTY, getPresentationTimeUs(this.lastRendererPositionUs)));
    }

    public void experimentalSetLegacyDecodingEnabled(boolean z) {
        this.legacyDecodingEnabled = z;
    }

    @RequiresNonNull({"subtitle"})
    @SideEffectFree
    public final long getCurrentEventTimeUs(long j) {
        int nextEventTimeIndex = this.subtitle.getNextEventTimeIndex(j);
        if (nextEventTimeIndex == 0 || this.subtitle.getEventTimeCount() == 0) {
            return this.subtitle.timeUs;
        }
        if (nextEventTimeIndex != -1) {
            return this.subtitle.getEventTime(nextEventTimeIndex - 1);
        }
        return this.subtitle.getEventTime(r2.getEventTimeCount() - 1);
    }

    @Override // androidx.media3.exoplayer.Renderer, androidx.media3.exoplayer.RendererCapabilities
    public String getName() {
        return "TextRenderer";
    }

    public final long getNextEventTime() {
        if (this.nextSubtitleEventIndex == -1) {
            return Long.MAX_VALUE;
        }
        this.subtitle.getClass();
        if (this.nextSubtitleEventIndex >= this.subtitle.getEventTimeCount()) {
            return Long.MAX_VALUE;
        }
        return this.subtitle.getEventTime(this.nextSubtitleEventIndex);
    }

    public final void handleDecoderError(SubtitleDecoderException subtitleDecoderException) {
        Log.e("TextRenderer", "Subtitle decoding failed. streamFormat=" + this.streamFormat, subtitleDecoderException);
        clearOutput();
        replaceSubtitleDecoder();
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        if (message.what != 0) {
            throw new IllegalStateException();
        }
        invokeUpdateOutputInternal((CueGroup) message.obj);
        return true;
    }

    public final void initSubtitleDecoder() {
        this.waitingForKeyFrame = true;
        SubtitleDecoderFactory subtitleDecoderFactory = this.subtitleDecoderFactory;
        Format format = this.streamFormat;
        format.getClass();
        this.subtitleDecoder = subtitleDecoderFactory.createDecoder(format);
    }

    public final void invokeUpdateOutputInternal(CueGroup cueGroup) {
        this.output.onCues(cueGroup.cues);
        this.output.onCues(cueGroup);
    }

    @Override // androidx.media3.exoplayer.Renderer
    public boolean isEnded() {
        return this.outputStreamEnded;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public boolean isReady() {
        return true;
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onDisabled() {
        this.streamFormat = null;
        this.finalStreamEndPositionUs = -9223372036854775807L;
        clearOutput();
        this.outputStreamOffsetUs = -9223372036854775807L;
        this.lastRendererPositionUs = -9223372036854775807L;
        if (this.subtitleDecoder != null) {
            releaseSubtitleDecoder();
        }
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onPositionReset(long j, boolean z) {
        this.lastRendererPositionUs = j;
        CuesResolver cuesResolver = this.cuesResolver;
        if (cuesResolver != null) {
            cuesResolver.clear();
        }
        clearOutput();
        this.inputStreamEnded = false;
        this.outputStreamEnded = false;
        this.finalStreamEndPositionUs = -9223372036854775807L;
        Format format = this.streamFormat;
        if (format == null || isCuesWithTiming(format)) {
            return;
        }
        if (this.decoderReplacementState != 0) {
            replaceSubtitleDecoder();
            return;
        }
        releaseSubtitleBuffers();
        SubtitleDecoder subtitleDecoder = this.subtitleDecoder;
        subtitleDecoder.getClass();
        subtitleDecoder.flush();
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onStreamChanged(Format[] formatArr, long j, long j2, MediaSource.MediaPeriodId mediaPeriodId) {
        this.outputStreamOffsetUs = j2;
        Format format = formatArr[0];
        this.streamFormat = format;
        if (isCuesWithTiming(format)) {
            this.cuesResolver = this.streamFormat.cueReplacementBehavior == 1 ? new MergingCuesResolver() : new ReplacingCuesResolver();
            return;
        }
        assertLegacyDecodingEnabledIfRequired();
        if (this.subtitleDecoder != null) {
            this.decoderReplacementState = 1;
        } else {
            initSubtitleDecoder();
        }
    }

    @RequiresNonNull({"this.cuesResolver"})
    public final boolean readAndDecodeCuesWithTiming(long j) {
        if (this.inputStreamEnded || readSource(this.formatHolder, this.cueDecoderInputBuffer, 0) != -4) {
            return false;
        }
        if (this.cueDecoderInputBuffer.getFlag(4)) {
            this.inputStreamEnded = true;
            return false;
        }
        this.cueDecoderInputBuffer.flip();
        ByteBuffer byteBuffer = this.cueDecoderInputBuffer.data;
        byteBuffer.getClass();
        CuesWithTiming cuesWithTimingDecode = this.cueDecoder.decode(this.cueDecoderInputBuffer.timeUs, byteBuffer.array(), byteBuffer.arrayOffset(), byteBuffer.limit());
        this.cueDecoderInputBuffer.clear();
        return this.cuesResolver.addCues(cuesWithTimingDecode, j);
    }

    public final void releaseSubtitleBuffers() {
        this.nextSubtitleInputBuffer = null;
        this.nextSubtitleEventIndex = -1;
        SubtitleOutputBuffer subtitleOutputBuffer = this.subtitle;
        if (subtitleOutputBuffer != null) {
            subtitleOutputBuffer.release();
            this.subtitle = null;
        }
        SubtitleOutputBuffer subtitleOutputBuffer2 = this.nextSubtitle;
        if (subtitleOutputBuffer2 != null) {
            subtitleOutputBuffer2.release();
            this.nextSubtitle = null;
        }
    }

    public final void releaseSubtitleDecoder() {
        releaseSubtitleBuffers();
        SubtitleDecoder subtitleDecoder = this.subtitleDecoder;
        subtitleDecoder.getClass();
        subtitleDecoder.release();
        this.subtitleDecoder = null;
        this.decoderReplacementState = 0;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public void render(long j, long j2) throws DecoderException {
        if (this.streamIsFinal) {
            long j3 = this.finalStreamEndPositionUs;
            if (j3 != -9223372036854775807L && j >= j3) {
                releaseSubtitleBuffers();
                this.outputStreamEnded = true;
            }
        }
        if (this.outputStreamEnded) {
            return;
        }
        Format format = this.streamFormat;
        format.getClass();
        if (Objects.equals(format.sampleMimeType, MimeTypes.APPLICATION_MEDIA3_CUES)) {
            this.cuesResolver.getClass();
            renderFromCuesWithTiming(j);
        } else {
            assertLegacyDecodingEnabledIfRequired();
            renderFromSubtitles(j);
        }
    }

    @RequiresNonNull({"this.cuesResolver"})
    public final void renderFromCuesWithTiming(long j) {
        boolean andDecodeCuesWithTiming = readAndDecodeCuesWithTiming(j);
        long nextCueChangeTimeUs = this.cuesResolver.getNextCueChangeTimeUs(this.lastRendererPositionUs);
        if (nextCueChangeTimeUs == Long.MIN_VALUE && this.inputStreamEnded && !andDecodeCuesWithTiming) {
            this.outputStreamEnded = true;
        }
        if (nextCueChangeTimeUs != Long.MIN_VALUE && nextCueChangeTimeUs <= j) {
            andDecodeCuesWithTiming = true;
        }
        if (andDecodeCuesWithTiming) {
            ImmutableList<Cue> cuesAtTimeUs = this.cuesResolver.getCuesAtTimeUs(j);
            long previousCueChangeTimeUs = this.cuesResolver.getPreviousCueChangeTimeUs(j);
            updateOutput(new CueGroup(cuesAtTimeUs, getPresentationTimeUs(previousCueChangeTimeUs)));
            this.cuesResolver.discardCuesBeforeTimeUs(previousCueChangeTimeUs);
        }
        this.lastRendererPositionUs = j;
    }

    public final void renderFromSubtitles(long j) throws DecoderException {
        boolean z;
        this.lastRendererPositionUs = j;
        if (this.nextSubtitle == null) {
            SubtitleDecoder subtitleDecoder = this.subtitleDecoder;
            subtitleDecoder.getClass();
            subtitleDecoder.setPositionUs(j);
            try {
                SubtitleDecoder subtitleDecoder2 = this.subtitleDecoder;
                subtitleDecoder2.getClass();
                this.nextSubtitle = subtitleDecoder2.dequeueOutputBuffer();
            } catch (SubtitleDecoderException e) {
                handleDecoderError(e);
                return;
            }
        }
        if (this.state != 2) {
            return;
        }
        if (this.subtitle != null) {
            long nextEventTime = getNextEventTime();
            z = false;
            while (nextEventTime <= j) {
                this.nextSubtitleEventIndex++;
                nextEventTime = getNextEventTime();
                z = true;
            }
        } else {
            z = false;
        }
        SubtitleOutputBuffer subtitleOutputBuffer = this.nextSubtitle;
        if (subtitleOutputBuffer != null) {
            if (subtitleOutputBuffer.getFlag(4)) {
                if (!z && getNextEventTime() == Long.MAX_VALUE) {
                    if (this.decoderReplacementState == 2) {
                        replaceSubtitleDecoder();
                    } else {
                        releaseSubtitleBuffers();
                        this.outputStreamEnded = true;
                    }
                }
            } else if (subtitleOutputBuffer.timeUs <= j) {
                SubtitleOutputBuffer subtitleOutputBuffer2 = this.subtitle;
                if (subtitleOutputBuffer2 != null) {
                    subtitleOutputBuffer2.release();
                }
                this.nextSubtitleEventIndex = subtitleOutputBuffer.getNextEventTimeIndex(j);
                this.subtitle = subtitleOutputBuffer;
                this.nextSubtitle = null;
                z = true;
            }
        }
        if (z) {
            this.subtitle.getClass();
            updateOutput(new CueGroup(this.subtitle.getCues(j), getPresentationTimeUs(getCurrentEventTimeUs(j))));
        }
        if (this.decoderReplacementState == 2) {
            return;
        }
        while (!this.inputStreamEnded) {
            try {
                SubtitleInputBuffer subtitleInputBufferDequeueInputBuffer = this.nextSubtitleInputBuffer;
                if (subtitleInputBufferDequeueInputBuffer == null) {
                    SubtitleDecoder subtitleDecoder3 = this.subtitleDecoder;
                    subtitleDecoder3.getClass();
                    subtitleInputBufferDequeueInputBuffer = subtitleDecoder3.dequeueInputBuffer();
                    if (subtitleInputBufferDequeueInputBuffer == null) {
                        return;
                    } else {
                        this.nextSubtitleInputBuffer = subtitleInputBufferDequeueInputBuffer;
                    }
                }
                if (this.decoderReplacementState == 1) {
                    subtitleInputBufferDequeueInputBuffer.flags = 4;
                    SubtitleDecoder subtitleDecoder4 = this.subtitleDecoder;
                    subtitleDecoder4.getClass();
                    subtitleDecoder4.queueInputBuffer(subtitleInputBufferDequeueInputBuffer);
                    this.nextSubtitleInputBuffer = null;
                    this.decoderReplacementState = 2;
                    return;
                }
                int source = readSource(this.formatHolder, subtitleInputBufferDequeueInputBuffer, 0);
                if (source == -4) {
                    if (subtitleInputBufferDequeueInputBuffer.getFlag(4)) {
                        this.inputStreamEnded = true;
                        this.waitingForKeyFrame = false;
                    } else {
                        Format format = this.formatHolder.format;
                        if (format == null) {
                            return;
                        }
                        subtitleInputBufferDequeueInputBuffer.subsampleOffsetUs = format.subsampleOffsetUs;
                        subtitleInputBufferDequeueInputBuffer.flip();
                        this.waitingForKeyFrame &= !subtitleInputBufferDequeueInputBuffer.getFlag(1);
                    }
                    if (!this.waitingForKeyFrame) {
                        if (subtitleInputBufferDequeueInputBuffer.timeUs < this.lastResetPositionUs) {
                            subtitleInputBufferDequeueInputBuffer.addFlag(Integer.MIN_VALUE);
                        }
                        SubtitleDecoder subtitleDecoder5 = this.subtitleDecoder;
                        subtitleDecoder5.getClass();
                        subtitleDecoder5.queueInputBuffer(subtitleInputBufferDequeueInputBuffer);
                        this.nextSubtitleInputBuffer = null;
                    }
                } else if (source == -3) {
                    return;
                }
            } catch (SubtitleDecoderException e2) {
                handleDecoderError(e2);
                return;
            }
        }
    }

    public final void replaceSubtitleDecoder() {
        releaseSubtitleDecoder();
        initSubtitleDecoder();
    }

    public void setFinalStreamEndPositionUs(long j) {
        Assertions.checkState(this.streamIsFinal);
        this.finalStreamEndPositionUs = j;
    }

    @Override // androidx.media3.exoplayer.RendererCapabilities
    public int supportsFormat(Format format) {
        if (isCuesWithTiming(format) || this.subtitleDecoderFactory.supportsFormat(format)) {
            return RendererCapabilities.CC.create(format.cryptoType == 0 ? 4 : 2, 0, 0, 0);
        }
        return MimeTypes.isText(format.sampleMimeType) ? RendererCapabilities.CC.create(1, 0, 0, 0) : RendererCapabilities.CC.create(0, 0, 0, 0);
    }

    public final void updateOutput(CueGroup cueGroup) {
        Handler handler = this.outputHandler;
        if (handler != null) {
            handler.obtainMessage(0, cueGroup).sendToTarget();
        } else {
            invokeUpdateOutputInternal(cueGroup);
        }
    }

    public TextRenderer(TextOutput textOutput, @Nullable Looper looper, SubtitleDecoderFactory subtitleDecoderFactory) {
        super(3);
        textOutput.getClass();
        this.output = textOutput;
        this.outputHandler = looper == null ? null : new Handler(looper, this);
        this.subtitleDecoderFactory = subtitleDecoderFactory;
        this.cueDecoder = new CueDecoder();
        this.cueDecoderInputBuffer = new DecoderInputBuffer(1, 0);
        this.formatHolder = new FormatHolder();
        this.finalStreamEndPositionUs = -9223372036854775807L;
        this.outputStreamOffsetUs = -9223372036854775807L;
        this.lastRendererPositionUs = -9223372036854775807L;
        this.legacyDecodingEnabled = true;
    }
}
