package com.google.android.exoplayer2.text;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.BaseRenderer;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.RendererCapabilities;
import com.google.android.exoplayer2.decoder.DecoderException;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import com.google.common.collect.RegularImmutableList;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class TextRenderer extends BaseRenderer implements Handler.Callback {
    public static final int MSG_UPDATE_OUTPUT = 0;
    public static final int REPLACEMENT_STATE_NONE = 0;
    public static final int REPLACEMENT_STATE_SIGNAL_END_OF_STREAM = 1;
    public static final int REPLACEMENT_STATE_WAIT_END_OF_STREAM = 2;
    public static final String TAG = "TextRenderer";

    @Nullable
    public SubtitleDecoder decoder;
    public final SubtitleDecoderFactory decoderFactory;
    public int decoderReplacementState;
    public long finalStreamEndPositionUs;
    public final FormatHolder formatHolder;
    public boolean inputStreamEnded;
    public long lastRendererPositionUs;

    @Nullable
    public SubtitleInputBuffer nextInputBuffer;

    @Nullable
    public SubtitleOutputBuffer nextSubtitle;
    public int nextSubtitleEventIndex;
    public final TextOutput output;

    @Nullable
    public final Handler outputHandler;
    public boolean outputStreamEnded;
    public long outputStreamOffsetUs;

    @Nullable
    public Format streamFormat;

    @Nullable
    public SubtitleOutputBuffer subtitle;
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

    private void releaseDecoder() {
        releaseBuffers();
        SubtitleDecoder subtitleDecoder = this.decoder;
        subtitleDecoder.getClass();
        subtitleDecoder.release();
        this.decoder = null;
        this.decoderReplacementState = 0;
    }

    public final void clearOutput() {
        updateOutput(new CueGroup(RegularImmutableList.EMPTY, getPresentationTimeUs(this.lastRendererPositionUs)));
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

    @Override // com.google.android.exoplayer2.Renderer, com.google.android.exoplayer2.RendererCapabilities
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
        replaceDecoder();
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        if (message.what != 0) {
            throw new IllegalStateException();
        }
        invokeUpdateOutputInternal((CueGroup) message.obj);
        return true;
    }

    public final void initDecoder() {
        this.waitingForKeyFrame = true;
        SubtitleDecoderFactory subtitleDecoderFactory = this.decoderFactory;
        Format format = this.streamFormat;
        format.getClass();
        this.decoder = subtitleDecoderFactory.createDecoder(format);
    }

    public final void invokeUpdateOutputInternal(CueGroup cueGroup) {
        this.output.onCues(cueGroup.cues);
        this.output.onCues(cueGroup);
    }

    @Override // com.google.android.exoplayer2.Renderer
    public boolean isEnded() {
        return this.outputStreamEnded;
    }

    @Override // com.google.android.exoplayer2.Renderer
    public boolean isReady() {
        return true;
    }

    @Override // com.google.android.exoplayer2.BaseRenderer
    public void onDisabled() {
        this.streamFormat = null;
        this.finalStreamEndPositionUs = -9223372036854775807L;
        clearOutput();
        this.outputStreamOffsetUs = -9223372036854775807L;
        this.lastRendererPositionUs = -9223372036854775807L;
        releaseDecoder();
    }

    @Override // com.google.android.exoplayer2.BaseRenderer
    public void onPositionReset(long j, boolean z) {
        this.lastRendererPositionUs = j;
        clearOutput();
        this.inputStreamEnded = false;
        this.outputStreamEnded = false;
        this.finalStreamEndPositionUs = -9223372036854775807L;
        if (this.decoderReplacementState != 0) {
            replaceDecoder();
            return;
        }
        releaseBuffers();
        SubtitleDecoder subtitleDecoder = this.decoder;
        subtitleDecoder.getClass();
        subtitleDecoder.flush();
    }

    @Override // com.google.android.exoplayer2.BaseRenderer
    public void onStreamChanged(Format[] formatArr, long j, long j2) {
        this.outputStreamOffsetUs = j2;
        this.streamFormat = formatArr[0];
        if (this.decoder != null) {
            this.decoderReplacementState = 1;
        } else {
            initDecoder();
        }
    }

    public final void releaseBuffers() {
        this.nextInputBuffer = null;
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

    @Override // com.google.android.exoplayer2.Renderer
    public void render(long j, long j2) throws DecoderException {
        boolean z;
        this.lastRendererPositionUs = j;
        if (this.streamIsFinal) {
            long j3 = this.finalStreamEndPositionUs;
            if (j3 != -9223372036854775807L && j >= j3) {
                releaseBuffers();
                this.outputStreamEnded = true;
            }
        }
        if (this.outputStreamEnded) {
            return;
        }
        if (this.nextSubtitle == null) {
            SubtitleDecoder subtitleDecoder = this.decoder;
            subtitleDecoder.getClass();
            subtitleDecoder.setPositionUs(j);
            try {
                SubtitleDecoder subtitleDecoder2 = this.decoder;
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
                        replaceDecoder();
                    } else {
                        releaseBuffers();
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
                SubtitleInputBuffer subtitleInputBufferDequeueInputBuffer = this.nextInputBuffer;
                if (subtitleInputBufferDequeueInputBuffer == null) {
                    SubtitleDecoder subtitleDecoder3 = this.decoder;
                    subtitleDecoder3.getClass();
                    subtitleInputBufferDequeueInputBuffer = subtitleDecoder3.dequeueInputBuffer();
                    if (subtitleInputBufferDequeueInputBuffer == null) {
                        return;
                    } else {
                        this.nextInputBuffer = subtitleInputBufferDequeueInputBuffer;
                    }
                }
                if (this.decoderReplacementState == 1) {
                    subtitleInputBufferDequeueInputBuffer.flags = 4;
                    SubtitleDecoder subtitleDecoder4 = this.decoder;
                    subtitleDecoder4.getClass();
                    subtitleDecoder4.queueInputBuffer(subtitleInputBufferDequeueInputBuffer);
                    this.nextInputBuffer = null;
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
                        SubtitleDecoder subtitleDecoder5 = this.decoder;
                        subtitleDecoder5.getClass();
                        subtitleDecoder5.queueInputBuffer(subtitleInputBufferDequeueInputBuffer);
                        this.nextInputBuffer = null;
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

    public final void replaceDecoder() {
        releaseDecoder();
        initDecoder();
    }

    public void setFinalStreamEndPositionUs(long j) {
        Assertions.checkState(this.streamIsFinal);
        this.finalStreamEndPositionUs = j;
    }

    @Override // com.google.android.exoplayer2.RendererCapabilities
    public int supportsFormat(Format format) {
        if (this.decoderFactory.supportsFormat(format)) {
            return RendererCapabilities.CC.create(format.cryptoType == 0 ? 4 : 2, 0, 0);
        }
        return MimeTypes.isText(format.sampleMimeType) ? RendererCapabilities.CC.create(1, 0, 0) : RendererCapabilities.CC.create(0, 0, 0);
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
        this.outputHandler = looper == null ? null : Util.createHandler(looper, this);
        this.decoderFactory = subtitleDecoderFactory;
        this.formatHolder = new FormatHolder();
        this.finalStreamEndPositionUs = -9223372036854775807L;
        this.outputStreamOffsetUs = -9223372036854775807L;
        this.lastRendererPositionUs = -9223372036854775807L;
    }
}
