package androidx.media3.exoplayer.audio;

import android.os.Handler;
import android.os.SystemClock;
import androidx.annotation.CallSuper;
import androidx.annotation.DoNotInline;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.AuxEffectInfo;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.TraceUtil;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.CryptoConfig;
import androidx.media3.decoder.Decoder;
import androidx.media3.decoder.DecoderException;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.decoder.SimpleDecoderOutputBuffer;
import androidx.media3.exoplayer.BaseRenderer;
import androidx.media3.exoplayer.DecoderCounters;
import androidx.media3.exoplayer.DecoderReuseEvaluation;
import androidx.media3.exoplayer.ExoPlaybackException;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.MediaClock;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.RendererConfiguration;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.audio.AudioRendererEventListener;
import androidx.media3.exoplayer.audio.AudioSink;
import androidx.media3.exoplayer.audio.DefaultAudioSink;
import androidx.media3.exoplayer.drm.DrmSession;
import androidx.media3.exoplayer.source.MediaSource;
import com.google.common.base.MoreObjects;
import com.google.errorprone.annotations.ForOverride;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public abstract class DecoderAudioRenderer<T extends Decoder<DecoderInputBuffer, ? extends SimpleDecoderOutputBuffer, ? extends DecoderException>> extends BaseRenderer implements MediaClock {
    public static final int MAX_PENDING_OUTPUT_STREAM_OFFSET_COUNT = 10;
    public static final int REINITIALIZATION_STATE_NONE = 0;
    public static final int REINITIALIZATION_STATE_SIGNAL_END_OF_STREAM = 1;
    public static final int REINITIALIZATION_STATE_WAIT_END_OF_STREAM = 2;
    public static final String TAG = "DecoderAudioRenderer";
    public boolean allowPositionDiscontinuity;
    public final AudioSink audioSink;
    public boolean audioTrackNeedsConfigure;
    public long currentPositionUs;

    @Nullable
    public T decoder;
    public DecoderCounters decoderCounters;

    @Nullable
    public DrmSession decoderDrmSession;
    public boolean decoderReceivedBuffers;
    public int decoderReinitializationState;
    public int encoderDelay;
    public int encoderPadding;
    public final AudioRendererEventListener.EventDispatcher eventDispatcher;
    public boolean firstStreamSampleRead;
    public final DecoderInputBuffer flagsOnlyBuffer;
    public boolean hasPendingReportedSkippedSilence;

    @Nullable
    public DecoderInputBuffer inputBuffer;
    public Format inputFormat;
    public boolean inputStreamEnded;

    @Nullable
    public SimpleDecoderOutputBuffer outputBuffer;
    public boolean outputStreamEnded;
    public long outputStreamOffsetUs;
    public int pendingOutputStreamOffsetCount;
    public final long[] pendingOutputStreamOffsetsUs;

    @Nullable
    public DrmSession sourceDrmSession;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(23)
    public static final class Api23 {
        @DoNotInline
        public static void setAudioSinkPreferredDevice(AudioSink audioSink, @Nullable Object obj) {
            audioSink.setPreferredDevice(AudioCapabilities$Api33$$ExternalSyntheticApiModelOutline0.m(obj));
        }
    }

    public DecoderAudioRenderer() {
        this(null, null, null, new AudioProcessor[0]);
    }

    private void flushDecoder() throws ExoPlaybackException {
        if (this.decoderReinitializationState != 0) {
            releaseDecoder();
            maybeInitDecoder();
            return;
        }
        this.inputBuffer = null;
        SimpleDecoderOutputBuffer simpleDecoderOutputBuffer = this.outputBuffer;
        if (simpleDecoderOutputBuffer != null) {
            simpleDecoderOutputBuffer.release();
            this.outputBuffer = null;
        }
        T t = this.decoder;
        t.getClass();
        t.flush();
        t.setOutputStartTimeUs(this.lastResetPositionUs);
        this.decoderReceivedBuffers = false;
    }

    private void onInputFormatChanged(FormatHolder formatHolder) throws ExoPlaybackException {
        Format format = formatHolder.format;
        format.getClass();
        setSourceDrmSession(formatHolder.drmSession);
        Format format2 = this.inputFormat;
        this.inputFormat = format;
        this.encoderDelay = format.encoderDelay;
        this.encoderPadding = format.encoderPadding;
        T t = this.decoder;
        if (t == null) {
            maybeInitDecoder();
            this.eventDispatcher.inputFormatChanged(this.inputFormat, null);
            return;
        }
        DecoderReuseEvaluation decoderReuseEvaluation = this.sourceDrmSession != this.decoderDrmSession ? new DecoderReuseEvaluation(t.getName(), format2, format, 0, 128) : canReuseDecoder(t.getName(), format2, format);
        if (decoderReuseEvaluation.result == 0) {
            if (this.decoderReceivedBuffers) {
                this.decoderReinitializationState = 1;
            } else {
                releaseDecoder();
                maybeInitDecoder();
                this.audioTrackNeedsConfigure = true;
            }
        }
        this.eventDispatcher.inputFormatChanged(this.inputFormat, decoderReuseEvaluation);
    }

    private void releaseDecoder() {
        this.inputBuffer = null;
        this.outputBuffer = null;
        this.decoderReinitializationState = 0;
        this.decoderReceivedBuffers = false;
        T t = this.decoder;
        if (t != null) {
            this.decoderCounters.decoderReleaseCount++;
            t.release();
            this.eventDispatcher.decoderReleased(this.decoder.getName());
            this.decoder = null;
        }
        setDecoderDrmSession(null);
    }

    @ForOverride
    public DecoderReuseEvaluation canReuseDecoder(String str, Format format, Format format2) {
        return new DecoderReuseEvaluation(str, format, format2, 0, 1);
    }

    @ForOverride
    public abstract T createDecoder(Format format, @Nullable CryptoConfig cryptoConfig) throws DecoderException;

    /* JADX WARN: Removed duplicated region for block: B:15:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0060  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean drainOutputBuffer() throws androidx.media3.exoplayer.ExoPlaybackException, androidx.media3.decoder.DecoderException, androidx.media3.exoplayer.audio.AudioSink.WriteException, androidx.media3.exoplayer.audio.AudioSink.InitializationException, androidx.media3.exoplayer.audio.AudioSink.ConfigurationException {
        /*
            r8 = this;
            androidx.media3.decoder.SimpleDecoderOutputBuffer r0 = r8.outputBuffer
            r1 = 0
            if (r0 != 0) goto L30
            T extends androidx.media3.decoder.Decoder<androidx.media3.decoder.DecoderInputBuffer, ? extends androidx.media3.decoder.SimpleDecoderOutputBuffer, ? extends androidx.media3.decoder.DecoderException> r0 = r8.decoder
            java.lang.Object r0 = r0.dequeueOutputBuffer()
            androidx.media3.decoder.SimpleDecoderOutputBuffer r0 = (androidx.media3.decoder.SimpleDecoderOutputBuffer) r0
            r8.outputBuffer = r0
            if (r0 != 0) goto L13
            goto Lc7
        L13:
            int r0 = r0.skippedOutputBufferCount
            if (r0 <= 0) goto L23
            androidx.media3.exoplayer.DecoderCounters r2 = r8.decoderCounters
            int r3 = r2.skippedOutputBufferCount
            int r3 = r3 + r0
            r2.skippedOutputBufferCount = r3
            androidx.media3.exoplayer.audio.AudioSink r0 = r8.audioSink
            r0.handleDiscontinuity()
        L23:
            androidx.media3.decoder.SimpleDecoderOutputBuffer r0 = r8.outputBuffer
            r2 = 134217728(0x8000000, float:3.85186E-34)
            boolean r0 = r0.getFlag(r2)
            if (r0 == 0) goto L30
            r8.processFirstSampleOfStream()
        L30:
            androidx.media3.decoder.SimpleDecoderOutputBuffer r0 = r8.outputBuffer
            r2 = 4
            boolean r0 = r0.getFlag(r2)
            r2 = 0
            r3 = 1
            if (r0 == 0) goto L60
            int r0 = r8.decoderReinitializationState
            r4 = 2
            if (r0 != r4) goto L49
            r8.releaseDecoder()
            r8.maybeInitDecoder()
            r8.audioTrackNeedsConfigure = r3
            return r1
        L49:
            androidx.media3.decoder.SimpleDecoderOutputBuffer r0 = r8.outputBuffer
            r0.release()
            r8.outputBuffer = r2
            r8.processEndOfStream()     // Catch: androidx.media3.exoplayer.audio.AudioSink.WriteException -> L54
            return r1
        L54:
            r0 = move-exception
            androidx.media3.common.Format r1 = r0.format
            boolean r2 = r0.isRecoverable
            r3 = 5002(0x138a, float:7.009E-42)
            androidx.media3.exoplayer.ExoPlaybackException r0 = r8.createRendererException(r0, r1, r2, r3)
            throw r0
        L60:
            boolean r0 = r8.audioTrackNeedsConfigure
            if (r0 == 0) goto Laa
            T extends androidx.media3.decoder.Decoder<androidx.media3.decoder.DecoderInputBuffer, ? extends androidx.media3.decoder.SimpleDecoderOutputBuffer, ? extends androidx.media3.decoder.DecoderException> r0 = r8.decoder
            androidx.media3.common.Format r0 = r8.getOutputFormat(r0)
            r0.getClass()
            androidx.media3.common.Format$Builder r4 = new androidx.media3.common.Format$Builder
            r4.<init>(r0)
            int r0 = r8.encoderDelay
            r4.encoderDelay = r0
            int r0 = r8.encoderPadding
            r4.encoderPadding = r0
            androidx.media3.common.Format r0 = r8.inputFormat
            androidx.media3.common.Metadata r5 = r0.metadata
            r4.metadata = r5
            java.lang.String r5 = r0.id
            r4.id = r5
            java.lang.String r5 = r0.label
            r4.label = r5
            java.util.List<androidx.media3.common.Label> r0 = r0.labels
            com.google.common.collect.ImmutableList r0 = com.google.common.collect.ImmutableList.copyOf(r0)
            r4.labels = r0
            androidx.media3.common.Format r0 = r8.inputFormat
            java.lang.String r5 = r0.language
            r4.language = r5
            int r5 = r0.selectionFlags
            r4.selectionFlags = r5
            int r0 = r0.roleFlags
            r4.roleFlags = r0
            androidx.media3.common.Format r0 = new androidx.media3.common.Format
            r0.<init>(r4)
            androidx.media3.exoplayer.audio.AudioSink r4 = r8.audioSink
            r4.configure(r0, r1, r2)
            r8.audioTrackNeedsConfigure = r1
        Laa:
            androidx.media3.exoplayer.audio.AudioSink r0 = r8.audioSink
            androidx.media3.decoder.SimpleDecoderOutputBuffer r4 = r8.outputBuffer
            java.nio.ByteBuffer r5 = r4.data
            long r6 = r4.timeUs
            boolean r0 = r0.handleBuffer(r5, r6, r3)
            if (r0 == 0) goto Lc7
            androidx.media3.exoplayer.DecoderCounters r0 = r8.decoderCounters
            int r1 = r0.renderedOutputBufferCount
            int r1 = r1 + r3
            r0.renderedOutputBufferCount = r1
            androidx.media3.decoder.SimpleDecoderOutputBuffer r0 = r8.outputBuffer
            r0.release()
            r8.outputBuffer = r2
            return r3
        Lc7:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.audio.DecoderAudioRenderer.drainOutputBuffer():boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0020  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean feedInputBuffer() throws androidx.media3.exoplayer.ExoPlaybackException, androidx.media3.decoder.DecoderException {
        /*
            r8 = this;
            T extends androidx.media3.decoder.Decoder<androidx.media3.decoder.DecoderInputBuffer, ? extends androidx.media3.decoder.SimpleDecoderOutputBuffer, ? extends androidx.media3.decoder.DecoderException> r0 = r8.decoder
            r1 = 0
            if (r0 == 0) goto L9f
            int r2 = r8.decoderReinitializationState
            r3 = 2
            if (r2 == r3) goto L9f
            boolean r2 = r8.inputStreamEnded
            if (r2 == 0) goto L10
            goto L9f
        L10:
            androidx.media3.decoder.DecoderInputBuffer r2 = r8.inputBuffer
            if (r2 != 0) goto L20
            java.lang.Object r0 = r0.dequeueInputBuffer()
            androidx.media3.decoder.DecoderInputBuffer r0 = (androidx.media3.decoder.DecoderInputBuffer) r0
            r8.inputBuffer = r0
            if (r0 != 0) goto L20
            goto L9f
        L20:
            int r0 = r8.decoderReinitializationState
            r2 = 4
            r4 = 0
            r5 = 1
            if (r0 != r5) goto L35
            androidx.media3.decoder.DecoderInputBuffer r0 = r8.inputBuffer
            r0.flags = r2
            T extends androidx.media3.decoder.Decoder<androidx.media3.decoder.DecoderInputBuffer, ? extends androidx.media3.decoder.SimpleDecoderOutputBuffer, ? extends androidx.media3.decoder.DecoderException> r2 = r8.decoder
            r2.queueInputBuffer(r0)
            r8.inputBuffer = r4
            r8.decoderReinitializationState = r3
            return r1
        L35:
            androidx.media3.exoplayer.FormatHolder r0 = r8.getFormatHolder()
            androidx.media3.decoder.DecoderInputBuffer r3 = r8.inputBuffer
            int r3 = r8.readSource(r0, r3, r1)
            r6 = -5
            if (r3 == r6) goto L9b
            r0 = -4
            if (r3 == r0) goto L4f
            r0 = -3
            if (r3 != r0) goto L49
            goto L9f
        L49:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>()
            throw r0
        L4f:
            androidx.media3.decoder.DecoderInputBuffer r0 = r8.inputBuffer
            boolean r0 = r0.getFlag(r2)
            if (r0 == 0) goto L63
            r8.inputStreamEnded = r5
            T extends androidx.media3.decoder.Decoder<androidx.media3.decoder.DecoderInputBuffer, ? extends androidx.media3.decoder.SimpleDecoderOutputBuffer, ? extends androidx.media3.decoder.DecoderException> r0 = r8.decoder
            androidx.media3.decoder.DecoderInputBuffer r2 = r8.inputBuffer
            r0.queueInputBuffer(r2)
            r8.inputBuffer = r4
            return r1
        L63:
            boolean r0 = r8.firstStreamSampleRead
            if (r0 != 0) goto L70
            r8.firstStreamSampleRead = r5
            androidx.media3.decoder.DecoderInputBuffer r0 = r8.inputBuffer
            r1 = 134217728(0x8000000, float:3.85186E-34)
            r0.addFlag(r1)
        L70:
            androidx.media3.decoder.DecoderInputBuffer r0 = r8.inputBuffer
            long r1 = r0.timeUs
            long r6 = r8.lastResetPositionUs
            int r3 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r3 >= 0) goto L7f
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0.addFlag(r1)
        L7f:
            androidx.media3.decoder.DecoderInputBuffer r0 = r8.inputBuffer
            r0.flip()
            androidx.media3.decoder.DecoderInputBuffer r0 = r8.inputBuffer
            androidx.media3.common.Format r1 = r8.inputFormat
            r0.format = r1
            T extends androidx.media3.decoder.Decoder<androidx.media3.decoder.DecoderInputBuffer, ? extends androidx.media3.decoder.SimpleDecoderOutputBuffer, ? extends androidx.media3.decoder.DecoderException> r1 = r8.decoder
            r1.queueInputBuffer(r0)
            r8.decoderReceivedBuffers = r5
            androidx.media3.exoplayer.DecoderCounters r0 = r8.decoderCounters
            int r1 = r0.queuedInputBufferCount
            int r1 = r1 + r5
            r0.queuedInputBufferCount = r1
            r8.inputBuffer = r4
            return r5
        L9b:
            r8.onInputFormatChanged(r0)
            return r5
        L9f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.audio.DecoderAudioRenderer.feedInputBuffer():boolean");
    }

    @Nullable
    @ForOverride
    public int[] getChannelMapping(T t) {
        return null;
    }

    @ForOverride
    public abstract Format getOutputFormat(T t);

    @Override // androidx.media3.exoplayer.MediaClock
    public PlaybackParameters getPlaybackParameters() {
        return this.audioSink.getPlaybackParameters();
    }

    @Override // androidx.media3.exoplayer.MediaClock
    public long getPositionUs() {
        if (this.state == 2) {
            updateCurrentPosition();
        }
        return this.currentPositionUs;
    }

    public final int getSinkFormatSupport(Format format) {
        return this.audioSink.getFormatSupport(format);
    }

    @Override // androidx.media3.exoplayer.BaseRenderer, androidx.media3.exoplayer.PlayerMessage.Target
    public void handleMessage(int i, @Nullable Object obj) throws ExoPlaybackException {
        if (i == 2) {
            this.audioSink.setVolume(((Float) obj).floatValue());
            return;
        }
        if (i == 3) {
            this.audioSink.setAudioAttributes((AudioAttributes) obj);
            return;
        }
        if (i == 6) {
            this.audioSink.setAuxEffectInfo((AuxEffectInfo) obj);
            return;
        }
        if (i == 12) {
            if (Util.SDK_INT >= 23) {
                Api23.setAudioSinkPreferredDevice(this.audioSink, obj);
            }
        } else if (i == 9) {
            this.audioSink.setSkipSilenceEnabled(((Boolean) obj).booleanValue());
        } else {
            if (i != 10) {
                return;
            }
            this.audioSink.setAudioSessionId(((Integer) obj).intValue());
        }
    }

    @Override // androidx.media3.exoplayer.MediaClock
    public boolean hasSkippedSilenceSinceLastCall() {
        boolean z = this.hasPendingReportedSkippedSilence;
        this.hasPendingReportedSkippedSilence = false;
        return z;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public boolean isEnded() {
        return this.outputStreamEnded && this.audioSink.isEnded();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public boolean isReady() {
        if (this.audioSink.hasPendingData()) {
            return true;
        }
        if (this.inputFormat != null) {
            return isSourceReady() || this.outputBuffer != null;
        }
        return false;
    }

    public final void maybeInitDecoder() throws ExoPlaybackException {
        CryptoConfig cryptoConfig;
        if (this.decoder != null) {
            return;
        }
        setDecoderDrmSession(this.sourceDrmSession);
        DrmSession drmSession = this.decoderDrmSession;
        if (drmSession != null) {
            cryptoConfig = drmSession.getCryptoConfig();
            if (cryptoConfig == null && this.decoderDrmSession.getError() == null) {
                return;
            }
        } else {
            cryptoConfig = null;
        }
        try {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            TraceUtil.beginSection("createAudioDecoder");
            T t = (T) createDecoder(this.inputFormat, cryptoConfig);
            this.decoder = t;
            t.setOutputStartTimeUs(this.lastResetPositionUs);
            TraceUtil.endSection();
            long jElapsedRealtime2 = SystemClock.elapsedRealtime();
            this.eventDispatcher.decoderInitialized(this.decoder.getName(), jElapsedRealtime2, jElapsedRealtime2 - jElapsedRealtime);
            this.decoderCounters.decoderInitCount++;
        } catch (DecoderException e) {
            Log.e("DecoderAudioRenderer", "Audio codec error", e);
            this.eventDispatcher.audioCodecError(e);
            throw createRendererException(e, this.inputFormat, false, 4001);
        } catch (OutOfMemoryError e2) {
            throw createRendererException(e2, this.inputFormat, false, 4001);
        }
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onDisabled() {
        this.inputFormat = null;
        this.audioTrackNeedsConfigure = true;
        setOutputStreamOffsetUs(-9223372036854775807L);
        this.hasPendingReportedSkippedSilence = false;
        try {
            setSourceDrmSession(null);
            releaseDecoder();
            this.audioSink.reset();
        } finally {
            this.eventDispatcher.disabled(this.decoderCounters);
        }
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onEnabled(boolean z, boolean z2) throws ExoPlaybackException {
        DecoderCounters decoderCounters = new DecoderCounters();
        this.decoderCounters = decoderCounters;
        this.eventDispatcher.enabled(decoderCounters);
        RendererConfiguration rendererConfiguration = this.configuration;
        rendererConfiguration.getClass();
        if (rendererConfiguration.tunneling) {
            this.audioSink.enableTunnelingV21();
        } else {
            this.audioSink.disableTunneling();
        }
        AudioSink audioSink = this.audioSink;
        PlayerId playerId = this.playerId;
        playerId.getClass();
        audioSink.setPlayerId(playerId);
        AudioSink audioSink2 = this.audioSink;
        Clock clock = this.clock;
        clock.getClass();
        audioSink2.setClock(clock);
    }

    @CallSuper
    @ForOverride
    public void onPositionDiscontinuity() {
        this.allowPositionDiscontinuity = true;
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onPositionReset(long j, boolean z) throws ExoPlaybackException {
        this.audioSink.flush();
        this.currentPositionUs = j;
        this.hasPendingReportedSkippedSilence = false;
        this.allowPositionDiscontinuity = true;
        this.inputStreamEnded = false;
        this.outputStreamEnded = false;
        if (this.decoder != null) {
            flushDecoder();
        }
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onStarted() {
        this.audioSink.play();
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onStopped() {
        updateCurrentPosition();
        this.audioSink.pause();
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onStreamChanged(Format[] formatArr, long j, long j2, MediaSource.MediaPeriodId mediaPeriodId) throws ExoPlaybackException {
        this.firstStreamSampleRead = false;
        if (this.outputStreamOffsetUs == -9223372036854775807L) {
            setOutputStreamOffsetUs(j2);
            return;
        }
        int i = this.pendingOutputStreamOffsetCount;
        if (i == this.pendingOutputStreamOffsetsUs.length) {
            Log.w("DecoderAudioRenderer", "Too many stream changes, so dropping offset: " + this.pendingOutputStreamOffsetsUs[this.pendingOutputStreamOffsetCount - 1]);
        } else {
            this.pendingOutputStreamOffsetCount = i + 1;
        }
        this.pendingOutputStreamOffsetsUs[this.pendingOutputStreamOffsetCount - 1] = j2;
    }

    public final void processEndOfStream() throws AudioSink.WriteException {
        this.outputStreamEnded = true;
        this.audioSink.playToEndOfStream();
    }

    public final void processFirstSampleOfStream() {
        this.audioSink.handleDiscontinuity();
        if (this.pendingOutputStreamOffsetCount != 0) {
            setOutputStreamOffsetUs(this.pendingOutputStreamOffsetsUs[0]);
            int i = this.pendingOutputStreamOffsetCount - 1;
            this.pendingOutputStreamOffsetCount = i;
            long[] jArr = this.pendingOutputStreamOffsetsUs;
            System.arraycopy(jArr, 1, jArr, 0, i);
        }
    }

    @Override // androidx.media3.exoplayer.Renderer
    public void render(long j, long j2) throws ExoPlaybackException {
        if (this.outputStreamEnded) {
            try {
                this.audioSink.playToEndOfStream();
                return;
            } catch (AudioSink.WriteException e) {
                throw createRendererException(e, e.format, e.isRecoverable, 5002);
            }
        }
        if (this.inputFormat == null) {
            FormatHolder formatHolder = getFormatHolder();
            this.flagsOnlyBuffer.clear();
            int source = readSource(formatHolder, this.flagsOnlyBuffer, 2);
            if (source != -5) {
                if (source == -4) {
                    Assertions.checkState(this.flagsOnlyBuffer.getFlag(4));
                    this.inputStreamEnded = true;
                    try {
                        processEndOfStream();
                        return;
                    } catch (AudioSink.WriteException e2) {
                        throw createRendererException(e2, null, false, 5002);
                    }
                }
                return;
            }
            onInputFormatChanged(formatHolder);
        }
        maybeInitDecoder();
        if (this.decoder != null) {
            try {
                TraceUtil.beginSection("drainAndFeed");
                while (drainOutputBuffer()) {
                }
                while (feedInputBuffer()) {
                }
                TraceUtil.endSection();
                synchronized (this.decoderCounters) {
                }
            } catch (DecoderException e3) {
                Log.e("DecoderAudioRenderer", "Audio codec error", e3);
                this.eventDispatcher.audioCodecError(e3);
                throw createRendererException(e3, this.inputFormat, false, 4003);
            } catch (AudioSink.ConfigurationException e4) {
                throw createRendererException(e4, e4.format, false, 5001);
            } catch (AudioSink.InitializationException e5) {
                throw createRendererException(e5, e5.format, e5.isRecoverable, 5001);
            } catch (AudioSink.WriteException e6) {
                throw createRendererException(e6, e6.format, e6.isRecoverable, 5002);
            }
        }
    }

    public final void setDecoderDrmSession(@Nullable DrmSession drmSession) {
        DrmSession.CC.replaceSession(this.decoderDrmSession, drmSession);
        this.decoderDrmSession = drmSession;
    }

    public final void setOutputStreamOffsetUs(long j) {
        this.outputStreamOffsetUs = j;
        if (j != -9223372036854775807L) {
            this.audioSink.setOutputStreamOffsetUs(j);
        }
    }

    @Override // androidx.media3.exoplayer.MediaClock
    public void setPlaybackParameters(PlaybackParameters playbackParameters) {
        this.audioSink.setPlaybackParameters(playbackParameters);
    }

    public final void setSourceDrmSession(@Nullable DrmSession drmSession) {
        DrmSession.CC.replaceSession(this.sourceDrmSession, drmSession);
        this.sourceDrmSession = drmSession;
    }

    public final boolean sinkSupportsFormat(Format format) {
        return this.audioSink.supportsFormat(format);
    }

    @Override // androidx.media3.exoplayer.RendererCapabilities
    public final int supportsFormat(Format format) {
        if (!MimeTypes.isAudio(format.sampleMimeType)) {
            return RendererCapabilities.CC.create(0, 0, 0, 0);
        }
        int iSupportsFormatInternal = supportsFormatInternal(format);
        if (iSupportsFormatInternal <= 2) {
            return RendererCapabilities.CC.create(iSupportsFormatInternal, 0, 0, 0);
        }
        return RendererCapabilities.CC.create(iSupportsFormatInternal, 8, Util.SDK_INT >= 21 ? 32 : 0);
    }

    @ForOverride
    public abstract int supportsFormatInternal(Format format);

    public final void updateCurrentPosition() {
        long currentPositionUs = this.audioSink.getCurrentPositionUs(isEnded());
        if (currentPositionUs != Long.MIN_VALUE) {
            if (!this.allowPositionDiscontinuity) {
                currentPositionUs = Math.max(this.currentPositionUs, currentPositionUs);
            }
            this.currentPositionUs = currentPositionUs;
            this.allowPositionDiscontinuity = false;
        }
    }

    public DecoderAudioRenderer(@Nullable Handler handler, @Nullable AudioRendererEventListener audioRendererEventListener, AudioProcessor... audioProcessorArr) {
        this(handler, audioRendererEventListener, null, audioProcessorArr);
    }

    public DecoderAudioRenderer(@Nullable Handler handler, @Nullable AudioRendererEventListener audioRendererEventListener, AudioCapabilities audioCapabilities, AudioProcessor... audioProcessorArr) {
        DefaultAudioSink.Builder builder = new DefaultAudioSink.Builder();
        builder.audioCapabilities = (AudioCapabilities) MoreObjects.firstNonNull(audioCapabilities, AudioCapabilities.DEFAULT_AUDIO_CAPABILITIES);
        builder.setAudioProcessors(audioProcessorArr);
        this(handler, audioRendererEventListener, builder.build());
    }

    public DecoderAudioRenderer(@Nullable Handler handler, @Nullable AudioRendererEventListener audioRendererEventListener, AudioSink audioSink) {
        super(1);
        this.eventDispatcher = new AudioRendererEventListener.EventDispatcher(handler, audioRendererEventListener);
        this.audioSink = audioSink;
        audioSink.setListener(new AudioSinkListener());
        this.flagsOnlyBuffer = DecoderInputBuffer.newNoDataInstance();
        this.decoderReinitializationState = 0;
        this.audioTrackNeedsConfigure = true;
        setOutputStreamOffsetUs(-9223372036854775807L);
        this.pendingOutputStreamOffsetsUs = new long[10];
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class AudioSinkListener implements AudioSink.Listener {
        public AudioSinkListener() {
        }

        @Override // androidx.media3.exoplayer.audio.AudioSink.Listener
        public void onAudioSinkError(Exception exc) {
            Log.e("DecoderAudioRenderer", "Audio sink error", exc);
            DecoderAudioRenderer.this.eventDispatcher.audioSinkError(exc);
        }

        @Override // androidx.media3.exoplayer.audio.AudioSink.Listener
        public void onAudioTrackInitialized(AudioSink.AudioTrackConfig audioTrackConfig) {
            DecoderAudioRenderer.this.eventDispatcher.audioTrackInitialized(audioTrackConfig);
        }

        @Override // androidx.media3.exoplayer.audio.AudioSink.Listener
        public void onAudioTrackReleased(AudioSink.AudioTrackConfig audioTrackConfig) {
            DecoderAudioRenderer.this.eventDispatcher.audioTrackReleased(audioTrackConfig);
        }

        @Override // androidx.media3.exoplayer.audio.AudioSink.Listener
        public void onPositionAdvancing(long j) {
            DecoderAudioRenderer.this.eventDispatcher.positionAdvancing(j);
        }

        @Override // androidx.media3.exoplayer.audio.AudioSink.Listener
        public void onPositionDiscontinuity() {
            DecoderAudioRenderer.this.onPositionDiscontinuity();
        }

        @Override // androidx.media3.exoplayer.audio.AudioSink.Listener
        public void onSilenceSkipped() {
            DecoderAudioRenderer.this.hasPendingReportedSkippedSilence = true;
        }

        @Override // androidx.media3.exoplayer.audio.AudioSink.Listener
        public void onSkipSilenceEnabledChanged(boolean z) {
            DecoderAudioRenderer.this.eventDispatcher.skipSilenceEnabledChanged(z);
        }

        @Override // androidx.media3.exoplayer.audio.AudioSink.Listener
        public void onUnderrun(int i, long j, long j2) {
            DecoderAudioRenderer.this.eventDispatcher.underrun(i, j, j2);
        }

        @Override // androidx.media3.exoplayer.audio.AudioSink.Listener
        public /* synthetic */ void onAudioCapabilitiesChanged() {
        }

        @Override // androidx.media3.exoplayer.audio.AudioSink.Listener
        public /* synthetic */ void onOffloadBufferEmptying() {
        }

        @Override // androidx.media3.exoplayer.audio.AudioSink.Listener
        public /* synthetic */ void onOffloadBufferFull() {
        }
    }

    @Override // androidx.media3.exoplayer.BaseRenderer, androidx.media3.exoplayer.Renderer
    @Nullable
    public MediaClock getMediaClock() {
        return this;
    }
}
