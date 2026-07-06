package com.google.android.exoplayer2.audio;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Handler;
import androidx.annotation.CallSuper;
import androidx.annotation.DoNotInline;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline2;
import androidx.media3.exoplayer.audio.AudioCapabilities$Api33$$ExternalSyntheticApiModelOutline0;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Renderer;
import com.google.android.exoplayer2.RendererCapabilities;
import com.google.android.exoplayer2.RendererConfiguration;
import com.google.android.exoplayer2.analytics.PlayerId;
import com.google.android.exoplayer2.audio.AudioRendererEventListener;
import com.google.android.exoplayer2.audio.AudioSink;
import com.google.android.exoplayer2.audio.DefaultAudioSink;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.decoder.DecoderReuseEvaluation;
import com.google.android.exoplayer2.mediacodec.MediaCodecAdapter;
import com.google.android.exoplayer2.mediacodec.MediaCodecInfo;
import com.google.android.exoplayer2.mediacodec.MediaCodecRenderer;
import com.google.android.exoplayer2.mediacodec.MediaCodecSelector;
import com.google.android.exoplayer2.mediacodec.MediaCodecUtil;
import com.google.android.exoplayer2.util.AmazonQuirks;
import com.google.android.exoplayer2.util.Logger;
import com.google.android.exoplayer2.util.MediaClock;
import com.google.android.exoplayer2.util.MediaFormatUtil;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class MediaCodecAudioRenderer extends MediaCodecRenderer implements MediaClock {
    public static final String TAG = "MediaCodecAudioRenderer";
    public static final String VIVO_BITS_PER_SAMPLE_KEY = "v-bits-per-sample";
    public boolean allowFirstBufferPositionDiscontinuity;
    public boolean allowPositionDiscontinuity;
    public final AudioSink audioSink;
    public boolean audioSinkNeedsReset;
    public int codecMaxInputSize;
    public boolean codecNeedsDiscardChannelsWorkaround;
    public final Context context;
    public long currentPositionUs;

    @Nullable
    public Format decryptOnlyCodecFormat;
    public final AudioRendererEventListener.EventDispatcher eventDispatcher;
    public boolean experimentalKeepAudioTrackOnSeek;

    @Nullable
    public Format inputFormat;
    public final Logger log;

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

        @Override // com.google.android.exoplayer2.audio.AudioSink.Listener
        public void onAudioSinkError(Exception exc) {
            MediaCodecAudioRenderer.this.log.e("Audio sink error", exc);
            MediaCodecAudioRenderer.this.eventDispatcher.audioSinkError(exc);
        }

        @Override // com.google.android.exoplayer2.audio.AudioSink.Listener
        public void onOffloadBufferEmptying() {
            Renderer.WakeupListener wakeupListener = MediaCodecAudioRenderer.this.wakeupListener;
            if (wakeupListener != null) {
                wakeupListener.onWakeup();
            }
        }

        @Override // com.google.android.exoplayer2.audio.AudioSink.Listener
        public void onOffloadBufferFull() {
            Renderer.WakeupListener wakeupListener = MediaCodecAudioRenderer.this.wakeupListener;
            if (wakeupListener != null) {
                wakeupListener.onSleep();
            }
        }

        @Override // com.google.android.exoplayer2.audio.AudioSink.Listener
        public void onPositionAdvancing(long j) {
            MediaCodecAudioRenderer.this.eventDispatcher.positionAdvancing(j);
        }

        @Override // com.google.android.exoplayer2.audio.AudioSink.Listener
        public void onPositionDiscontinuity() {
            MediaCodecAudioRenderer.this.onPositionDiscontinuity();
        }

        @Override // com.google.android.exoplayer2.audio.AudioSink.Listener
        public void onSkipSilenceEnabledChanged(boolean z) {
            MediaCodecAudioRenderer.this.eventDispatcher.skipSilenceEnabledChanged(z);
        }

        @Override // com.google.android.exoplayer2.audio.AudioSink.Listener
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

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public DecoderReuseEvaluation canReuseCodec(MediaCodecInfo mediaCodecInfo, Format format, Format format2) {
        DecoderReuseEvaluation decoderReuseEvaluationCanReuseCodec = mediaCodecInfo.canReuseCodec(format, format2);
        int i = decoderReuseEvaluationCanReuseCodec.discardReasons;
        if (getCodecMaxInputSize(mediaCodecInfo, format2) > this.codecMaxInputSize) {
            i |= 64;
        }
        int i2 = i;
        return new DecoderReuseEvaluation(mediaCodecInfo.name, format, format2, i2 != 0 ? 0 : decoderReuseEvaluationCanReuseCodec.result, i2);
    }

    public void experimentalSetEnableKeepAudioTrackOnSeek(boolean z) {
        this.experimentalKeepAudioTrackOnSeek = z;
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

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
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

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public List<MediaCodecInfo> getDecoderInfos(MediaCodecSelector mediaCodecSelector, Format format, boolean z) throws MediaCodecUtil.DecoderQueryException {
        return MediaCodecUtil.getDecoderInfosSortedByFormatSupport(getDecoderInfos(mediaCodecSelector, format, z, this.audioSink), format);
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public MediaCodecAdapter.Configuration getMediaCodecConfiguration(MediaCodecInfo mediaCodecInfo, Format format, @Nullable MediaCrypto mediaCrypto, float f) {
        Format[] formatArr = this.streamFormats;
        formatArr.getClass();
        this.codecMaxInputSize = getCodecMaxInputSize(mediaCodecInfo, format, formatArr);
        this.log.setTAG(mediaCodecInfo.name + "-MediaCodecAudioRenderer");
        this.codecNeedsDiscardChannelsWorkaround = codecNeedsDiscardChannelsWorkaround(mediaCodecInfo.name);
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

    @Override // com.google.android.exoplayer2.Renderer, com.google.android.exoplayer2.RendererCapabilities
    public String getName() {
        return "MediaCodecAudioRenderer";
    }

    @Override // com.google.android.exoplayer2.util.MediaClock
    public PlaybackParameters getPlaybackParameters() {
        return this.audioSink.getPlaybackParameters();
    }

    @Override // com.google.android.exoplayer2.util.MediaClock
    public long getPositionUs() {
        if (this.state == 2) {
            updateCurrentPosition();
        }
        return this.currentPositionUs;
    }

    @Override // com.google.android.exoplayer2.BaseRenderer, com.google.android.exoplayer2.PlayerMessage.Target
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
        switch (i) {
            case 9:
                this.audioSink.setSkipSilenceEnabled(((Boolean) obj).booleanValue());
                break;
            case 10:
                this.audioSink.setAudioSessionId(((Integer) obj).intValue());
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

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.Renderer
    public boolean isEnded() {
        return this.outputStreamEnded && this.audioSink.isEnded();
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.Renderer
    public boolean isReady() {
        return this.audioSink.hasPendingData() || super.isReady();
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void onCodecError(Exception exc) {
        this.log.e("Audio codec error", exc);
        this.eventDispatcher.audioCodecError(exc);
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void onCodecInitialized(String str, MediaCodecAdapter.Configuration configuration, long j, long j2) {
        this.eventDispatcher.decoderInitialized(str, j, j2);
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void onCodecReleased(String str) {
        this.eventDispatcher.decoderReleased(str);
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.BaseRenderer
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

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.BaseRenderer
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
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    @Nullable
    public DecoderReuseEvaluation onInputFormatChanged(FormatHolder formatHolder) throws ExoPlaybackException {
        Format format = formatHolder.format;
        format.getClass();
        this.inputFormat = format;
        DecoderReuseEvaluation decoderReuseEvaluationOnInputFormatChanged = super.onInputFormatChanged(formatHolder);
        this.eventDispatcher.inputFormatChanged(this.inputFormat, decoderReuseEvaluationOnInputFormatChanged);
        return decoderReuseEvaluationOnInputFormatChanged;
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void onOutputFormatChanged(Format format, @Nullable MediaFormat mediaFormat) throws ExoPlaybackException {
        int i;
        this.log.i("onOutputFormatChanged: outputFormat:" + mediaFormat + ", codec:" + format.codecs);
        Format format2 = this.decryptOnlyCodecFormat;
        int[] iArr = null;
        if (format2 != null) {
            format = format2;
        } else if (this.codec != null) {
            int pcmEncoding = "audio/raw".equals(format.sampleMimeType) ? format.pcmEncoding : (Util.SDK_INT < 24 || !mediaFormat.containsKey("pcm-encoding")) ? mediaFormat.containsKey("v-bits-per-sample") ? Util.getPcmEncoding(mediaFormat.getInteger("v-bits-per-sample")) : AmazonQuirks.isAmazonDevice() ? MimeTypes.getEncoding(mediaFormat.getString("mime"), format.codecs) : 2 : mediaFormat.getInteger("pcm-encoding");
            Format.Builder builder = new Format.Builder();
            builder.sampleMimeType = "audio/raw";
            builder.pcmEncoding = pcmEncoding;
            builder.encoderDelay = format.encoderDelay;
            builder.encoderPadding = format.encoderPadding;
            builder.channelCount = mediaFormat.getInteger("channel-count");
            builder.sampleRate = mediaFormat.getInteger("sample-rate");
            Format format3 = new Format(builder);
            if (this.codecNeedsDiscardChannelsWorkaround && format3.channelCount == 6 && (i = format.channelCount) < 6) {
                iArr = new int[i];
                for (int i2 = 0; i2 < format.channelCount; i2++) {
                    iArr[i2] = i2;
                }
            }
            format = format3;
        }
        try {
            this.audioSink.configure(format, 0, iArr);
        } catch (AudioSink.ConfigurationException e) {
            throw createRendererException(e, e.format, false, 5001);
        }
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void onOutputStreamOffsetUsChanged(long j) {
        this.audioSink.setOutputStreamOffsetUs(j);
    }

    @CallSuper
    public void onPositionDiscontinuity() {
        this.allowPositionDiscontinuity = true;
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.BaseRenderer
    public void onPositionReset(long j, boolean z) throws ExoPlaybackException {
        super.onPositionReset(j, z);
        if (this.experimentalKeepAudioTrackOnSeek) {
            this.audioSink.experimentalFlushWithoutAudioTrackRelease();
        } else {
            this.audioSink.flush();
        }
        this.currentPositionUs = j;
        this.allowFirstBufferPositionDiscontinuity = true;
        this.allowPositionDiscontinuity = true;
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void onProcessedStreamChange() {
        this.audioSink.handleDiscontinuity();
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void onQueueInputBuffer(DecoderInputBuffer decoderInputBuffer) {
        if (!this.allowFirstBufferPositionDiscontinuity || decoderInputBuffer.getFlag(Integer.MIN_VALUE)) {
            return;
        }
        if (Math.abs(decoderInputBuffer.timeUs - this.currentPositionUs) > 500000) {
            this.currentPositionUs = decoderInputBuffer.timeUs;
        }
        this.allowFirstBufferPositionDiscontinuity = false;
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.BaseRenderer
    public void onReset() {
        try {
            super.onReset();
        } finally {
            if (this.audioSinkNeedsReset) {
                this.audioSinkNeedsReset = false;
                this.audioSink.reset();
            }
        }
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.BaseRenderer
    public void onStarted() {
        this.audioSink.play();
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.BaseRenderer
    public void onStopped() {
        updateCurrentPosition();
        this.audioSink.pause();
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public boolean processOutputBuffer(long j, long j2, @Nullable MediaCodecAdapter mediaCodecAdapter, @Nullable ByteBuffer byteBuffer, int i, int i2, int i3, long j3, boolean z, boolean z2, Format format) throws ExoPlaybackException {
        byteBuffer.getClass();
        if (this.log.allowDebug()) {
            Logger logger = this.log;
            StringBuilder sbM = AbstractResolvableFuture$$ExternalSyntheticOutline2.m("processOutputBuffer: positionUs = ", j, ", elapsedRealtimeUs =  ");
            sbM.append(j2);
            sbM.append(", bufferIndex = ");
            sbM.append(i);
            sbM.append(", isDecodeOnlyBuffer = ");
            sbM.append(z);
            sbM.append(", isLastBuffer = ");
            sbM.append(z2);
            sbM.append(", bufferPresentationTimeUs = ");
            sbM.append(j3);
            logger.d(sbM.toString());
        }
        if (this.decryptOnlyCodecFormat != null && (i2 & 2) != 0) {
            mediaCodecAdapter.getClass();
            mediaCodecAdapter.releaseOutputBuffer(i, false);
            return true;
        }
        if (z) {
            if (mediaCodecAdapter != null) {
                mediaCodecAdapter.releaseOutputBuffer(i, false);
            }
            this.decoderCounters.skippedOutputBufferCount += i3;
            this.audioSink.handleDiscontinuity();
            return true;
        }
        try {
            if (!this.audioSink.handleBuffer(byteBuffer, j3, i3)) {
                return false;
            }
            if (mediaCodecAdapter != null) {
                mediaCodecAdapter.releaseOutputBuffer(i, false);
            }
            this.decoderCounters.renderedOutputBufferCount += i3;
            return true;
        } catch (AudioSink.InitializationException e) {
            throw createRendererException(e, this.inputFormat, e.isRecoverable, 5001);
        } catch (AudioSink.WriteException e2) {
            throw createRendererException(e2, format, e2.isRecoverable, 5002);
        }
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void renderToEndOfStream() throws ExoPlaybackException {
        try {
            this.audioSink.playToEndOfStream();
        } catch (AudioSink.WriteException e) {
            throw createRendererException(e, e.format, e.isRecoverable, 5002);
        }
    }

    @Override // com.google.android.exoplayer2.util.MediaClock
    public void setPlaybackParameters(PlaybackParameters playbackParameters) {
        this.audioSink.setPlaybackParameters(playbackParameters);
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public boolean shouldUseBypass(Format format) {
        return this.audioSink.supportsFormat(format);
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public int supportsFormat(MediaCodecSelector mediaCodecSelector, Format format) throws MediaCodecUtil.DecoderQueryException {
        boolean z;
        boolean z2 = true;
        int iCreate = RendererCapabilities.CC.create(1, 0, 0);
        if (!MimeTypes.isAudio(format.sampleMimeType)) {
            return RendererCapabilities.CC.create(0, 0, 0);
        }
        int i = Util.SDK_INT >= 21 ? 32 : 0;
        boolean z3 = format.cryptoType != 0;
        boolean zSupportsFormatDrm = MediaCodecRenderer.supportsFormatDrm(format);
        int i2 = 8;
        if (zSupportsFormatDrm && this.audioSink.supportsFormat(format) && (!z3 || MediaCodecUtil.getDecryptOnlyDecoderInfo() != null)) {
            return RendererCapabilities.CC.create(4, 8, i);
        }
        if (("audio/raw".equals(format.sampleMimeType) && !this.audioSink.supportsFormat(format)) || !this.audioSink.supportsFormat(Util.getPcmFormat(2, format.channelCount, format.sampleRate))) {
            return iCreate;
        }
        List<MediaCodecInfo> decoderInfos = getDecoderInfos(mediaCodecSelector, format, false, this.audioSink);
        if (decoderInfos.isEmpty()) {
            return iCreate;
        }
        if (!zSupportsFormatDrm) {
            return RendererCapabilities.CC.create(2, 0, 0);
        }
        MediaCodecInfo mediaCodecInfo = decoderInfos.get(0);
        boolean zIsFormatSupported = mediaCodecInfo.isFormatSupported(format);
        if (zIsFormatSupported) {
            z2 = zIsFormatSupported;
            z = true;
        } else {
            for (int i3 = 1; i3 < decoderInfos.size(); i3++) {
                MediaCodecInfo mediaCodecInfo2 = decoderInfos.get(i3);
                if (mediaCodecInfo2.isFormatSupported(format)) {
                    mediaCodecInfo = mediaCodecInfo2;
                    z = false;
                    break;
                }
            }
            z2 = zIsFormatSupported;
            z = true;
        }
        int i4 = z2 ? 4 : 3;
        if (z2 && mediaCodecInfo.isSeamlessAdaptationSupported(format)) {
            i2 = 16;
        }
        return RendererCapabilities.CC.create(i4, i2, i, mediaCodecInfo.hardwareAccelerated ? 64 : 0, z ? 128 : 0);
    }

    public MediaCodecAudioRenderer(Context context, MediaCodecSelector mediaCodecSelector, @Nullable Handler handler, @Nullable AudioRendererEventListener audioRendererEventListener) {
        this(context, mediaCodecSelector, handler, audioRendererEventListener, AudioCapabilities.DEFAULT_AUDIO_CAPABILITIES, new AudioProcessor[0]);
    }

    public MediaCodecAudioRenderer(Context context, MediaCodecSelector mediaCodecSelector, @Nullable Handler handler, @Nullable AudioRendererEventListener audioRendererEventListener, AudioCapabilities audioCapabilities, AudioProcessor... audioProcessorArr) {
        DefaultAudioSink.Builder builder = new DefaultAudioSink.Builder();
        builder.audioCapabilities = (AudioCapabilities) MoreObjects.firstNonNull(audioCapabilities, AudioCapabilities.DEFAULT_AUDIO_CAPABILITIES);
        builder.setAudioProcessors(audioProcessorArr);
        this(context, mediaCodecSelector, handler, audioRendererEventListener, builder.build());
    }

    public static List<MediaCodecInfo> getDecoderInfos(MediaCodecSelector mediaCodecSelector, Format format, boolean z, AudioSink audioSink) throws MediaCodecUtil.DecoderQueryException {
        MediaCodecInfo decryptOnlyDecoderInfo;
        String str = format.sampleMimeType;
        if (str == null) {
            return ImmutableList.of();
        }
        if (audioSink.supportsFormat(format) && AmazonQuirks.useDefaultPassthroughDecoder() && (decryptOnlyDecoderInfo = MediaCodecUtil.getDecryptOnlyDecoderInfo()) != null) {
            return ImmutableList.of(decryptOnlyDecoderInfo);
        }
        List<MediaCodecInfo> decoderInfos = mediaCodecSelector.getDecoderInfos(str, z, false);
        String alternativeCodecMimeType = MediaCodecUtil.getAlternativeCodecMimeType(format);
        if (alternativeCodecMimeType == null) {
            return ImmutableList.copyOf((Collection) decoderInfos);
        }
        List<MediaCodecInfo> decoderInfos2 = mediaCodecSelector.getDecoderInfos(alternativeCodecMimeType, z, false);
        ImmutableList.Builder builder = ImmutableList.builder();
        builder.addAll((Iterable) decoderInfos);
        builder.addAll((Iterable) decoderInfos2);
        return builder.build();
    }

    private int getCodecMaxInputSize(MediaCodecInfo mediaCodecInfo, Format format) {
        int i;
        if (!"OMX.google.raw.decoder".equals(mediaCodecInfo.name) || (i = Util.SDK_INT) >= 24 || (i == 23 && Util.isTv(this.context))) {
            return format.maxInputSize;
        }
        return -1;
    }

    public MediaCodecAudioRenderer(Context context, MediaCodecSelector mediaCodecSelector, @Nullable Handler handler, @Nullable AudioRendererEventListener audioRendererEventListener, AudioSink audioSink) {
        this(context, MediaCodecAdapter.Factory.DEFAULT, mediaCodecSelector, false, handler, audioRendererEventListener, audioSink);
    }

    public MediaCodecAudioRenderer(Context context, MediaCodecSelector mediaCodecSelector, boolean z, @Nullable Handler handler, @Nullable AudioRendererEventListener audioRendererEventListener, AudioSink audioSink) {
        this(context, MediaCodecAdapter.Factory.DEFAULT, mediaCodecSelector, z, handler, audioRendererEventListener, audioSink);
    }

    public MediaCodecAudioRenderer(Context context, MediaCodecAdapter.Factory factory, MediaCodecSelector mediaCodecSelector, boolean z, @Nullable Handler handler, @Nullable AudioRendererEventListener audioRendererEventListener, AudioSink audioSink) {
        super(1, factory, mediaCodecSelector, z, 44100.0f);
        this.log = new Logger(Logger.Module.Audio, "MediaCodecAudioRenderer");
        this.context = context.getApplicationContext();
        this.audioSink = audioSink;
        this.eventDispatcher = new AudioRendererEventListener.EventDispatcher(handler, audioRendererEventListener);
        audioSink.setListener(new AudioSinkListener());
    }

    @Override // com.google.android.exoplayer2.BaseRenderer, com.google.android.exoplayer2.Renderer
    @Nullable
    public MediaClock getMediaClock() {
        return this;
    }
}
