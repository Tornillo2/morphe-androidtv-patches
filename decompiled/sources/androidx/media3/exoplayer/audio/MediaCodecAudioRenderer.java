package androidx.media3.exoplayer.audio;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Handler;
import androidx.annotation.CallSuper;
import androidx.annotation.DoNotInline;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.AuxEffectInfo;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.MediaFormatUtil;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.DecoderReuseEvaluation;
import androidx.media3.exoplayer.ExoPlaybackException;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.MediaClock;
import androidx.media3.exoplayer.Renderer;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.RendererConfiguration;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.audio.AudioRendererEventListener;
import androidx.media3.exoplayer.audio.AudioSink;
import androidx.media3.exoplayer.audio.DefaultAudioSink;
import androidx.media3.exoplayer.mediacodec.MediaCodecAdapter;
import androidx.media3.exoplayer.mediacodec.MediaCodecInfo;
import androidx.media3.exoplayer.mediacodec.MediaCodecRenderer;
import androidx.media3.exoplayer.mediacodec.MediaCodecSelector;
import androidx.media3.exoplayer.mediacodec.MediaCodecUtil;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.RegularImmutableList;
import j$.util.Objects;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public class MediaCodecAudioRenderer extends MediaCodecRenderer implements MediaClock {
    public static final String TAG = "MediaCodecAudioRenderer";
    public static final String VIVO_BITS_PER_SAMPLE_KEY = "v-bits-per-sample";
    public boolean allowPositionDiscontinuity;
    public final AudioSink audioSink;
    public boolean audioSinkNeedsReset;
    public int codecMaxInputSize;
    public boolean codecNeedsDiscardChannelsWorkaround;
    public boolean codecNeedsVorbisToAndroidChannelMappingWorkaround;
    public final Context context;
    public long currentPositionUs;

    @Nullable
    public Format decryptOnlyCodecFormat;
    public final AudioRendererEventListener.EventDispatcher eventDispatcher;
    public boolean hasPendingReportedSkippedSilence;

    @Nullable
    public Format inputFormat;

    @Nullable
    public Renderer.WakeupListener wakeupListener;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(23)
    public static final class Api23 {
        @DoNotInline
        public static void setAudioSinkPreferredDevice(AudioSink audioSink, @Nullable Object obj) {
            audioSink.setPreferredDevice(AudioCapabilities$Api33$$ExternalSyntheticApiModelOutline0.m(obj));
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final class AudioSinkListener implements AudioSink.Listener {
        public AudioSinkListener() {
        }

        @Override // androidx.media3.exoplayer.audio.AudioSink.Listener
        public void onAudioCapabilitiesChanged() {
            MediaCodecAudioRenderer.this.onRendererCapabilitiesChanged();
        }

        @Override // androidx.media3.exoplayer.audio.AudioSink.Listener
        public void onAudioSinkError(Exception exc) {
            Log.e("MediaCodecAudioRenderer", "Audio sink error", exc);
            MediaCodecAudioRenderer.this.eventDispatcher.audioSinkError(exc);
        }

        @Override // androidx.media3.exoplayer.audio.AudioSink.Listener
        public void onAudioTrackInitialized(AudioSink.AudioTrackConfig audioTrackConfig) {
            MediaCodecAudioRenderer.this.eventDispatcher.audioTrackInitialized(audioTrackConfig);
        }

        @Override // androidx.media3.exoplayer.audio.AudioSink.Listener
        public void onAudioTrackReleased(AudioSink.AudioTrackConfig audioTrackConfig) {
            MediaCodecAudioRenderer.this.eventDispatcher.audioTrackReleased(audioTrackConfig);
        }

        @Override // androidx.media3.exoplayer.audio.AudioSink.Listener
        public void onOffloadBufferEmptying() {
            Renderer.WakeupListener wakeupListener = MediaCodecAudioRenderer.this.wakeupListener;
            if (wakeupListener != null) {
                wakeupListener.onWakeup();
            }
        }

        @Override // androidx.media3.exoplayer.audio.AudioSink.Listener
        public void onOffloadBufferFull() {
            Renderer.WakeupListener wakeupListener = MediaCodecAudioRenderer.this.wakeupListener;
            if (wakeupListener != null) {
                wakeupListener.onSleep();
            }
        }

        @Override // androidx.media3.exoplayer.audio.AudioSink.Listener
        public void onPositionAdvancing(long j) {
            MediaCodecAudioRenderer.this.eventDispatcher.positionAdvancing(j);
        }

        @Override // androidx.media3.exoplayer.audio.AudioSink.Listener
        public void onPositionDiscontinuity() {
            MediaCodecAudioRenderer.this.onPositionDiscontinuity();
        }

        @Override // androidx.media3.exoplayer.audio.AudioSink.Listener
        public void onSilenceSkipped() {
            MediaCodecAudioRenderer.this.hasPendingReportedSkippedSilence = true;
        }

        @Override // androidx.media3.exoplayer.audio.AudioSink.Listener
        public void onSkipSilenceEnabledChanged(boolean z) {
            MediaCodecAudioRenderer.this.eventDispatcher.skipSilenceEnabledChanged(z);
        }

        @Override // androidx.media3.exoplayer.audio.AudioSink.Listener
        public void onUnderrun(int i, long j, long j2) {
            MediaCodecAudioRenderer.this.eventDispatcher.underrun(i, j, j2);
        }
    }

    public MediaCodecAudioRenderer(Context context, MediaCodecSelector mediaCodecSelector) {
        this(context, mediaCodecSelector, null, null);
    }

    public static boolean codecNeedsDiscardChannelsWorkaround(String str) {
        if (Util.SDK_INT >= 24 || !"OMX.SEC.aac.dec".equals(str) || !"samsung".equals(Util.MANUFACTURER)) {
            return false;
        }
        String str2 = Util.DEVICE;
        return str2.startsWith("zeroflte") || str2.startsWith("herolte") || str2.startsWith("heroqlte");
    }

    public static boolean codecNeedsVorbisToAndroidChannelMappingWorkaround(String str) {
        return str.equals("OMX.google.opus.decoder") || str.equals("c2.android.opus.decoder") || str.equals("OMX.google.vorbis.decoder") || str.equals("c2.android.vorbis.decoder");
    }

    public static boolean deviceDoesntSupportOperatingRate() {
        if (Util.SDK_INT != 23) {
            return false;
        }
        String str = Util.MODEL;
        return "ZTE B2017G".equals(str) || "AXON 7 mini".equals(str);
    }

    private void updateCurrentPosition() {
        long currentPositionUs = this.audioSink.getCurrentPositionUs(isEnded());
        if (currentPositionUs != Long.MIN_VALUE) {
            if (!this.allowPositionDiscontinuity) {
                currentPositionUs = Math.max(this.currentPositionUs, currentPositionUs);
            }
            this.currentPositionUs = currentPositionUs;
            this.allowPositionDiscontinuity = false;
        }
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public DecoderReuseEvaluation canReuseCodec(MediaCodecInfo mediaCodecInfo, Format format, Format format2) {
        DecoderReuseEvaluation decoderReuseEvaluationCanReuseCodec = mediaCodecInfo.canReuseCodec(format, format2);
        int i = decoderReuseEvaluationCanReuseCodec.discardReasons;
        if (isBypassPossible(format2)) {
            i |= 32768;
        }
        if (getCodecMaxInputSize(mediaCodecInfo, format2) > this.codecMaxInputSize) {
            i |= 64;
        }
        int i2 = i;
        return new DecoderReuseEvaluation(mediaCodecInfo.name, format, format2, i2 != 0 ? 0 : decoderReuseEvaluationCanReuseCodec.result, i2);
    }

    public final int getAudioOffloadSupport(Format format) {
        AudioOffloadSupport formatOffloadSupport = this.audioSink.getFormatOffloadSupport(format);
        if (!formatOffloadSupport.isFormatSupported) {
            return 0;
        }
        int i = formatOffloadSupport.isGaplessSupported ? 1536 : 512;
        return formatOffloadSupport.isSpeedChangeSupported ? i | 2048 : i;
    }

    public int getCodecMaxInputSize(MediaCodecInfo mediaCodecInfo, Format format, Format[] formatArr) {
        int codecMaxInputSize = getCodecMaxInputSize(mediaCodecInfo, format);
        if (formatArr.length == 1) {
            return codecMaxInputSize;
        }
        for (Format format2 : formatArr) {
            if (mediaCodecInfo.canReuseCodec(format, format2).result != 0) {
                codecMaxInputSize = Math.max(codecMaxInputSize, getCodecMaxInputSize(mediaCodecInfo, format2));
            }
        }
        return codecMaxInputSize;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public float getCodecOperatingRateV23(float f, Format format, Format[] formatArr) {
        int iMax = -1;
        for (Format format2 : formatArr) {
            int i = format2.sampleRate;
            if (i != -1) {
                iMax = Math.max(iMax, i);
            }
        }
        if (iMax == -1) {
            return -1.0f;
        }
        return iMax * f;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public List<MediaCodecInfo> getDecoderInfos(MediaCodecSelector mediaCodecSelector, Format format, boolean z) throws MediaCodecUtil.DecoderQueryException {
        return MediaCodecUtil.getDecoderInfosSortedByFormatSupport(getDecoderInfos(mediaCodecSelector, format, z, this.audioSink), format);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public MediaCodecAdapter.Configuration getMediaCodecConfiguration(MediaCodecInfo mediaCodecInfo, Format format, @Nullable MediaCrypto mediaCrypto, float f) {
        Format[] formatArr = this.streamFormats;
        formatArr.getClass();
        this.codecMaxInputSize = getCodecMaxInputSize(mediaCodecInfo, format, formatArr);
        this.codecNeedsDiscardChannelsWorkaround = codecNeedsDiscardChannelsWorkaround(mediaCodecInfo.name);
        this.codecNeedsVorbisToAndroidChannelMappingWorkaround = codecNeedsVorbisToAndroidChannelMappingWorkaround(mediaCodecInfo.name);
        MediaFormat mediaFormat = getMediaFormat(format, mediaCodecInfo.codecMimeType, this.codecMaxInputSize, f);
        this.decryptOnlyCodecFormat = (!"audio/raw".equals(mediaCodecInfo.mimeType) || "audio/raw".equals(format.sampleMimeType)) ? null : format;
        return MediaCodecAdapter.Configuration.createForAudioDecoding(mediaCodecInfo, mediaFormat, format, mediaCrypto);
    }

    @SuppressLint({"InlinedApi"})
    public MediaFormat getMediaFormat(Format format, String str, int i, float f) {
        MediaFormat mediaFormat = new MediaFormat();
        mediaFormat.setString("mime", str);
        mediaFormat.setInteger("channel-count", format.channelCount);
        mediaFormat.setInteger("sample-rate", format.sampleRate);
        MediaFormatUtil.setCsdBuffers(mediaFormat, format.initializationData);
        MediaFormatUtil.maybeSetInteger(mediaFormat, "max-input-size", i);
        int i2 = Util.SDK_INT;
        if (i2 >= 23) {
            mediaFormat.setInteger("priority", 0);
            if (f != -1.0f && !deviceDoesntSupportOperatingRate()) {
                mediaFormat.setFloat("operating-rate", f);
            }
        }
        if (i2 <= 28 && "audio/ac4".equals(format.sampleMimeType)) {
            mediaFormat.setInteger("ac4-is-sync", 1);
        }
        if (i2 >= 24 && this.audioSink.getFormatSupport(Util.getPcmFormat(4, format.channelCount, format.sampleRate)) == 2) {
            mediaFormat.setInteger("pcm-encoding", 4);
        }
        if (i2 >= 32) {
            mediaFormat.setInteger("max-output-channel-count", 99);
        }
        return mediaFormat;
    }

    @Override // androidx.media3.exoplayer.Renderer, androidx.media3.exoplayer.RendererCapabilities
    public String getName() {
        return "MediaCodecAudioRenderer";
    }

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

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public void handleInputBufferSupplementalData(DecoderInputBuffer decoderInputBuffer) {
        Format format;
        if (Util.SDK_INT < 29 || (format = decoderInputBuffer.format) == null || !Objects.equals(format.sampleMimeType, "audio/opus") || !this.bypassEnabled) {
            return;
        }
        ByteBuffer byteBuffer = decoderInputBuffer.supplementalData;
        byteBuffer.getClass();
        Format format2 = decoderInputBuffer.format;
        format2.getClass();
        int i = format2.encoderDelay;
        if (byteBuffer.remaining() == 8) {
            this.audioSink.setOffloadDelayPadding(i, (int) ((byteBuffer.order(ByteOrder.LITTLE_ENDIAN).getLong() * 48000) / 1000000000));
        }
    }

    @Override // androidx.media3.exoplayer.BaseRenderer, androidx.media3.exoplayer.PlayerMessage.Target
    public void handleMessage(int i, @Nullable Object obj) throws ExoPlaybackException {
        if (i == 2) {
            AudioSink audioSink = this.audioSink;
            obj.getClass();
            audioSink.setVolume(((Float) obj).floatValue());
            return;
        }
        if (i == 3) {
            AudioAttributes audioAttributes = (AudioAttributes) obj;
            AudioSink audioSink2 = this.audioSink;
            audioAttributes.getClass();
            audioSink2.setAudioAttributes(audioAttributes);
            return;
        }
        if (i == 6) {
            AuxEffectInfo auxEffectInfo = (AuxEffectInfo) obj;
            AudioSink audioSink3 = this.audioSink;
            auxEffectInfo.getClass();
            audioSink3.setAuxEffectInfo(auxEffectInfo);
            return;
        }
        switch (i) {
            case 9:
                AudioSink audioSink4 = this.audioSink;
                obj.getClass();
                audioSink4.setSkipSilenceEnabled(((Boolean) obj).booleanValue());
                break;
            case 10:
                AudioSink audioSink5 = this.audioSink;
                obj.getClass();
                audioSink5.setAudioSessionId(((Integer) obj).intValue());
                break;
            case 11:
                this.wakeupListener = (Renderer.WakeupListener) obj;
                break;
            case 12:
                if (Util.SDK_INT >= 23) {
                    Api23.setAudioSinkPreferredDevice(this.audioSink, obj);
                }
                break;
        }
    }

    @Override // androidx.media3.exoplayer.MediaClock
    public boolean hasSkippedSilenceSinceLastCall() {
        boolean z = this.hasPendingReportedSkippedSilence;
        this.hasPendingReportedSkippedSilence = false;
        return z;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.Renderer
    public boolean isEnded() {
        return this.outputStreamEnded && this.audioSink.isEnded();
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.Renderer
    public boolean isReady() {
        return this.audioSink.hasPendingData() || super.isReady();
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public void onCodecError(Exception exc) {
        Log.e("MediaCodecAudioRenderer", "Audio codec error", exc);
        this.eventDispatcher.audioCodecError(exc);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public void onCodecInitialized(String str, MediaCodecAdapter.Configuration configuration, long j, long j2) {
        this.eventDispatcher.decoderInitialized(str, j, j2);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public void onCodecReleased(String str) {
        this.eventDispatcher.decoderReleased(str);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.BaseRenderer
    public void onDisabled() {
        this.audioSinkNeedsReset = true;
        this.inputFormat = null;
        try {
            this.audioSink.flush();
            try {
                super.onDisabled();
            } finally {
            }
        } catch (Throwable th) {
            try {
                super.onDisabled();
                throw th;
            } finally {
            }
        }
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.BaseRenderer
    public void onEnabled(boolean z, boolean z2) throws ExoPlaybackException {
        super.onEnabled(z, z2);
        this.eventDispatcher.enabled(this.decoderCounters);
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

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    @Nullable
    public DecoderReuseEvaluation onInputFormatChanged(FormatHolder formatHolder) throws ExoPlaybackException {
        Format format = formatHolder.format;
        format.getClass();
        this.inputFormat = format;
        DecoderReuseEvaluation decoderReuseEvaluationOnInputFormatChanged = super.onInputFormatChanged(formatHolder);
        this.eventDispatcher.inputFormatChanged(format, decoderReuseEvaluationOnInputFormatChanged);
        return decoderReuseEvaluationOnInputFormatChanged;
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x00d6 A[Catch: ConfigurationException -> 0x00d4, TryCatch #0 {ConfigurationException -> 0x00d4, blocks: (B:34:0x00b4, B:36:0x00ba, B:38:0x00be, B:40:0x00c7, B:43:0x00d6, B:44:0x00db), top: B:48:0x00b4 }] */
    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onOutputFormatChanged(androidx.media3.common.Format r6, @androidx.annotation.Nullable android.media.MediaFormat r7) throws androidx.media3.exoplayer.ExoPlaybackException {
        /*
            Method dump skipped, instruction units count: 234
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.audio.MediaCodecAudioRenderer.onOutputFormatChanged(androidx.media3.common.Format, android.media.MediaFormat):void");
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public void onOutputStreamOffsetUsChanged(long j) {
        this.audioSink.setOutputStreamOffsetUs(j);
    }

    @CallSuper
    public void onPositionDiscontinuity() {
        this.allowPositionDiscontinuity = true;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.BaseRenderer
    public void onPositionReset(long j, boolean z) throws ExoPlaybackException {
        super.onPositionReset(j, z);
        this.audioSink.flush();
        this.currentPositionUs = j;
        this.hasPendingReportedSkippedSilence = false;
        this.allowPositionDiscontinuity = true;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public void onProcessedStreamChange() {
        this.audioSink.handleDiscontinuity();
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onRelease() {
        this.audioSink.release();
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.BaseRenderer
    public void onReset() {
        this.hasPendingReportedSkippedSilence = false;
        try {
            super.onReset();
        } finally {
            if (this.audioSinkNeedsReset) {
                this.audioSinkNeedsReset = false;
                this.audioSink.reset();
            }
        }
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.BaseRenderer
    public void onStarted() {
        this.audioSink.play();
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.BaseRenderer
    public void onStopped() {
        updateCurrentPosition();
        this.audioSink.pause();
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006f  */
    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean processOutputBuffer(long r1, long r3, @androidx.annotation.Nullable androidx.media3.exoplayer.mediacodec.MediaCodecAdapter r5, @androidx.annotation.Nullable java.nio.ByteBuffer r6, int r7, int r8, int r9, long r10, boolean r12, boolean r13, androidx.media3.common.Format r14) throws androidx.media3.exoplayer.ExoPlaybackException {
        /*
            r0 = this;
            r6.getClass()
            androidx.media3.common.Format r1 = r0.decryptOnlyCodecFormat
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L14
            r1 = r8 & 2
            if (r1 == 0) goto L14
            r5.getClass()
            r5.releaseOutputBuffer(r7, r3)
            return r2
        L14:
            if (r12 == 0) goto L28
            if (r5 == 0) goto L1b
            r5.releaseOutputBuffer(r7, r3)
        L1b:
            androidx.media3.exoplayer.DecoderCounters r1 = r0.decoderCounters
            int r3 = r1.skippedOutputBufferCount
            int r3 = r3 + r9
            r1.skippedOutputBufferCount = r3
            androidx.media3.exoplayer.audio.AudioSink r1 = r0.audioSink
            r1.handleDiscontinuity()
            return r2
        L28:
            androidx.media3.exoplayer.audio.AudioSink r1 = r0.audioSink     // Catch: androidx.media3.exoplayer.audio.AudioSink.WriteException -> L3e androidx.media3.exoplayer.audio.AudioSink.InitializationException -> L40
            boolean r1 = r1.handleBuffer(r6, r10, r9)     // Catch: androidx.media3.exoplayer.audio.AudioSink.WriteException -> L3e androidx.media3.exoplayer.audio.AudioSink.InitializationException -> L40
            if (r1 == 0) goto L3d
            if (r5 == 0) goto L35
            r5.releaseOutputBuffer(r7, r3)
        L35:
            androidx.media3.exoplayer.DecoderCounters r1 = r0.decoderCounters
            int r3 = r1.renderedOutputBufferCount
            int r3 = r3 + r9
            r1.renderedOutputBufferCount = r3
            return r2
        L3d:
            return r3
        L3e:
            r1 = move-exception
            goto L42
        L40:
            r1 = move-exception
            goto L5b
        L42:
            boolean r2 = r1.isRecoverable
            boolean r3 = r0.bypassEnabled
            if (r3 == 0) goto L54
            androidx.media3.exoplayer.RendererConfiguration r3 = r0.configuration
            r3.getClass()
            int r3 = r3.offloadModePreferred
            if (r3 == 0) goto L54
            r3 = 5003(0x138b, float:7.01E-42)
            goto L56
        L54:
            r3 = 5002(0x138a, float:7.009E-42)
        L56:
            androidx.media3.exoplayer.ExoPlaybackException r1 = r0.createRendererException(r1, r14, r2, r3)
            throw r1
        L5b:
            androidx.media3.common.Format r2 = r0.inputFormat
            boolean r3 = r1.isRecoverable
            boolean r4 = r0.bypassEnabled
            if (r4 == 0) goto L6f
            androidx.media3.exoplayer.RendererConfiguration r4 = r0.configuration
            r4.getClass()
            int r4 = r4.offloadModePreferred
            if (r4 == 0) goto L6f
            r4 = 5004(0x138c, float:7.012E-42)
            goto L71
        L6f:
            r4 = 5001(0x1389, float:7.008E-42)
        L71:
            androidx.media3.exoplayer.ExoPlaybackException r1 = r0.createRendererException(r1, r2, r3, r4)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.audio.MediaCodecAudioRenderer.processOutputBuffer(long, long, androidx.media3.exoplayer.mediacodec.MediaCodecAdapter, java.nio.ByteBuffer, int, int, int, long, boolean, boolean, androidx.media3.common.Format):boolean");
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public void renderToEndOfStream() throws ExoPlaybackException {
        try {
            this.audioSink.playToEndOfStream();
        } catch (AudioSink.WriteException e) {
            throw createRendererException(e, e.format, e.isRecoverable, this.bypassEnabled ? PlaybackException.ERROR_CODE_AUDIO_TRACK_OFFLOAD_WRITE_FAILED : 5002);
        }
    }

    @Override // androidx.media3.exoplayer.MediaClock
    public void setPlaybackParameters(PlaybackParameters playbackParameters) {
        this.audioSink.setPlaybackParameters(playbackParameters);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public boolean shouldUseBypass(Format format) {
        RendererConfiguration rendererConfiguration = this.configuration;
        rendererConfiguration.getClass();
        if (rendererConfiguration.offloadModePreferred != 0) {
            int audioOffloadSupport = getAudioOffloadSupport(format);
            if ((audioOffloadSupport & 512) != 0) {
                RendererConfiguration rendererConfiguration2 = this.configuration;
                rendererConfiguration2.getClass();
                if (rendererConfiguration2.offloadModePreferred == 2 || (audioOffloadSupport & 1024) != 0) {
                    return true;
                }
                if (format.encoderDelay == 0 && format.encoderPadding == 0) {
                    return true;
                }
            }
        }
        return this.audioSink.supportsFormat(format);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public int supportsFormat(MediaCodecSelector mediaCodecSelector, Format format) throws MediaCodecUtil.DecoderQueryException {
        int i;
        boolean z;
        boolean z2 = true;
        int iCreate = RendererCapabilities.CC.create(1, 0, 0, 0);
        if (!MimeTypes.isAudio(format.sampleMimeType)) {
            return RendererCapabilities.CC.create(0, 0, 0, 0);
        }
        int i2 = Util.SDK_INT >= 21 ? 32 : 0;
        boolean z3 = format.cryptoType != 0;
        boolean zSupportsFormatDrm = MediaCodecRenderer.supportsFormatDrm(format);
        int i3 = 8;
        if (!zSupportsFormatDrm || (z3 && MediaCodecUtil.getDecryptOnlyDecoderInfo() == null)) {
            i = 0;
        } else {
            int audioOffloadSupport = getAudioOffloadSupport(format);
            if (this.audioSink.supportsFormat(format)) {
                return RendererCapabilities.CC.create(4, 8, i2, audioOffloadSupport);
            }
            i = audioOffloadSupport;
        }
        if (("audio/raw".equals(format.sampleMimeType) && !this.audioSink.supportsFormat(format)) || !this.audioSink.supportsFormat(Util.getPcmFormat(2, format.channelCount, format.sampleRate))) {
            return iCreate;
        }
        List<MediaCodecInfo> decoderInfos = getDecoderInfos(mediaCodecSelector, format, false, this.audioSink);
        if (decoderInfos.isEmpty()) {
            return iCreate;
        }
        if (!zSupportsFormatDrm) {
            return RendererCapabilities.CC.create(2, 0, 0, 0);
        }
        MediaCodecInfo mediaCodecInfo = decoderInfos.get(0);
        boolean zIsFormatSupported = mediaCodecInfo.isFormatSupported(format);
        if (zIsFormatSupported) {
            z2 = zIsFormatSupported;
            z = true;
        } else {
            for (int i4 = 1; i4 < decoderInfos.size(); i4++) {
                MediaCodecInfo mediaCodecInfo2 = decoderInfos.get(i4);
                if (mediaCodecInfo2.isFormatSupported(format)) {
                    mediaCodecInfo = mediaCodecInfo2;
                    z = false;
                    break;
                }
            }
            z2 = zIsFormatSupported;
            z = true;
        }
        int i5 = z2 ? 4 : 3;
        if (z2 && mediaCodecInfo.isSeamlessAdaptationSupported(format)) {
            i3 = 16;
        }
        return RendererCapabilities.CC.create(i5, i3, i2, mediaCodecInfo.hardwareAccelerated ? 64 : 0, z ? 128 : 0, i);
    }

    public MediaCodecAudioRenderer(Context context, MediaCodecSelector mediaCodecSelector, @Nullable Handler handler, @Nullable AudioRendererEventListener audioRendererEventListener) {
        this(context, mediaCodecSelector, handler, audioRendererEventListener, new DefaultAudioSink.Builder(context).build());
    }

    public static List<MediaCodecInfo> getDecoderInfos(MediaCodecSelector mediaCodecSelector, Format format, boolean z, AudioSink audioSink) throws MediaCodecUtil.DecoderQueryException {
        MediaCodecInfo decryptOnlyDecoderInfo;
        if (format.sampleMimeType == null) {
            return RegularImmutableList.EMPTY;
        }
        if (audioSink.supportsFormat(format) && (decryptOnlyDecoderInfo = MediaCodecUtil.getDecryptOnlyDecoderInfo()) != null) {
            return ImmutableList.of(decryptOnlyDecoderInfo);
        }
        return MediaCodecUtil.getDecoderInfosSoftMatch(mediaCodecSelector, format, z, false);
    }

    @Deprecated
    public MediaCodecAudioRenderer(Context context, MediaCodecSelector mediaCodecSelector, @Nullable Handler handler, @Nullable AudioRendererEventListener audioRendererEventListener, AudioCapabilities audioCapabilities, AudioProcessor... audioProcessorArr) {
        DefaultAudioSink.Builder builder = new DefaultAudioSink.Builder();
        builder.audioCapabilities = (AudioCapabilities) MoreObjects.firstNonNull(audioCapabilities, AudioCapabilities.DEFAULT_AUDIO_CAPABILITIES);
        builder.setAudioProcessors(audioProcessorArr);
        this(context, mediaCodecSelector, handler, audioRendererEventListener, builder.build());
    }

    private int getCodecMaxInputSize(MediaCodecInfo mediaCodecInfo, Format format) {
        int i;
        if (!"OMX.google.raw.decoder".equals(mediaCodecInfo.name) || (i = Util.SDK_INT) >= 24 || (i == 23 && Util.isTv(this.context))) {
            return format.maxInputSize;
        }
        return -1;
    }

    public MediaCodecAudioRenderer(Context context, MediaCodecSelector mediaCodecSelector, @Nullable Handler handler, @Nullable AudioRendererEventListener audioRendererEventListener, AudioSink audioSink) {
        this(context, MediaCodecAdapter.Factory.CC.getDefault(context), mediaCodecSelector, false, handler, audioRendererEventListener, audioSink);
    }

    public MediaCodecAudioRenderer(Context context, MediaCodecSelector mediaCodecSelector, boolean z, @Nullable Handler handler, @Nullable AudioRendererEventListener audioRendererEventListener, AudioSink audioSink) {
        this(context, MediaCodecAdapter.Factory.CC.getDefault(context), mediaCodecSelector, z, handler, audioRendererEventListener, audioSink);
    }

    public MediaCodecAudioRenderer(Context context, MediaCodecAdapter.Factory factory, MediaCodecSelector mediaCodecSelector, boolean z, @Nullable Handler handler, @Nullable AudioRendererEventListener audioRendererEventListener, AudioSink audioSink) {
        super(1, factory, mediaCodecSelector, z, 44100.0f);
        this.context = context.getApplicationContext();
        this.audioSink = audioSink;
        this.eventDispatcher = new AudioRendererEventListener.EventDispatcher(handler, audioRendererEventListener);
        audioSink.setListener(new AudioSinkListener());
    }

    @Override // androidx.media3.exoplayer.BaseRenderer, androidx.media3.exoplayer.Renderer
    @Nullable
    public MediaClock getMediaClock() {
        return this;
    }
}
