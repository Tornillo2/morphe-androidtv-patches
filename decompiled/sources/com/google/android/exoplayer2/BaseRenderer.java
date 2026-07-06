package com.google.android.exoplayer2;

import androidx.annotation.Nullable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.analytics.PlayerId;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MediaClock;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class BaseRenderer implements Renderer, RendererCapabilities {

    @Nullable
    public RendererConfiguration configuration;
    public int index;
    public long lastResetPositionUs;
    public PlayerId playerId;
    public int state;

    @Nullable
    public SampleStream stream;

    @Nullable
    public Format[] streamFormats;
    public boolean streamIsFinal;
    public long streamOffsetUs;
    public boolean throwRendererExceptionIsExecuting;
    public final int trackType;
    public final FormatHolder formatHolder = new FormatHolder();
    public long readingPositionUs = Long.MIN_VALUE;

    public BaseRenderer(int i) {
        this.trackType = i;
    }

    public final ExoPlaybackException createRendererException(Throwable th, @Nullable Format format, int i) {
        return createRendererException(th, format, false, i);
    }

    @Override // com.google.android.exoplayer2.Renderer
    public final void disable() {
        Assertions.checkState(this.state == 1);
        this.formatHolder.clear();
        this.state = 0;
        this.stream = null;
        this.streamFormats = null;
        this.streamIsFinal = false;
        onDisabled();
    }

    @Override // com.google.android.exoplayer2.Renderer
    public final void enable(RendererConfiguration rendererConfiguration, Format[] formatArr, SampleStream sampleStream, long j, boolean z, boolean z2, long j2, long j3) throws ExoPlaybackException {
        Assertions.checkState(this.state == 0);
        this.configuration = rendererConfiguration;
        this.state = 1;
        onEnabled(z, z2);
        replaceStream(formatArr, sampleStream, j2, j3);
        resetPosition(j, z);
    }

    public final RendererConfiguration getConfiguration() {
        RendererConfiguration rendererConfiguration = this.configuration;
        rendererConfiguration.getClass();
        return rendererConfiguration;
    }

    public final FormatHolder getFormatHolder() {
        this.formatHolder.clear();
        return this.formatHolder;
    }

    public final int getIndex() {
        return this.index;
    }

    public final long getLastResetPositionUs() {
        return this.lastResetPositionUs;
    }

    @Override // com.google.android.exoplayer2.Renderer
    @Nullable
    public MediaClock getMediaClock() {
        return null;
    }

    public final PlayerId getPlayerId() {
        PlayerId playerId = this.playerId;
        playerId.getClass();
        return playerId;
    }

    @Override // com.google.android.exoplayer2.Renderer
    public final long getReadingPositionUs() {
        return this.readingPositionUs;
    }

    @Override // com.google.android.exoplayer2.Renderer
    public final int getState() {
        return this.state;
    }

    @Override // com.google.android.exoplayer2.Renderer
    @Nullable
    public final SampleStream getStream() {
        return this.stream;
    }

    public final Format[] getStreamFormats() {
        Format[] formatArr = this.streamFormats;
        formatArr.getClass();
        return formatArr;
    }

    @Override // com.google.android.exoplayer2.Renderer, com.google.android.exoplayer2.RendererCapabilities
    public final int getTrackType() {
        return this.trackType;
    }

    @Override // com.google.android.exoplayer2.Renderer
    public final boolean hasReadStreamToEnd() {
        return this.readingPositionUs == Long.MIN_VALUE;
    }

    @Override // com.google.android.exoplayer2.Renderer
    public final void init(int i, PlayerId playerId) {
        this.index = i;
        this.playerId = playerId;
    }

    @Override // com.google.android.exoplayer2.Renderer
    public final boolean isCurrentStreamFinal() {
        return this.streamIsFinal;
    }

    public final boolean isSourceReady() {
        if (hasReadStreamToEnd()) {
            return this.streamIsFinal;
        }
        SampleStream sampleStream = this.stream;
        sampleStream.getClass();
        return sampleStream.isReady();
    }

    @Override // com.google.android.exoplayer2.Renderer
    public final void maybeThrowStreamError() throws IOException {
        SampleStream sampleStream = this.stream;
        sampleStream.getClass();
        sampleStream.maybeThrowError();
    }

    public final int readSource(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i) {
        SampleStream sampleStream = this.stream;
        sampleStream.getClass();
        int data = sampleStream.readData(formatHolder, decoderInputBuffer, i);
        if (data == -4) {
            if (decoderInputBuffer.getFlag(4)) {
                this.readingPositionUs = Long.MIN_VALUE;
                return this.streamIsFinal ? -4 : -3;
            }
            long j = decoderInputBuffer.timeUs + this.streamOffsetUs;
            decoderInputBuffer.timeUs = j;
            this.readingPositionUs = Math.max(this.readingPositionUs, j);
            return data;
        }
        if (data == -5) {
            Format format = formatHolder.format;
            format.getClass();
            if (format.subsampleOffsetUs != Long.MAX_VALUE) {
                Format.Builder builder = new Format.Builder(format);
                builder.subsampleOffsetUs = format.subsampleOffsetUs + this.streamOffsetUs;
                formatHolder.format = new Format(builder);
            }
        }
        return data;
    }

    @Override // com.google.android.exoplayer2.Renderer
    public final void replaceStream(Format[] formatArr, SampleStream sampleStream, long j, long j2) throws ExoPlaybackException {
        Assertions.checkState(!this.streamIsFinal);
        this.stream = sampleStream;
        if (this.readingPositionUs == Long.MIN_VALUE) {
            this.readingPositionUs = j;
        }
        this.streamFormats = formatArr;
        this.streamOffsetUs = j2;
        onStreamChanged(formatArr, j, j2);
    }

    @Override // com.google.android.exoplayer2.Renderer
    public final void reset() {
        Assertions.checkState(this.state == 0);
        this.formatHolder.clear();
        onReset();
    }

    @Override // com.google.android.exoplayer2.Renderer
    public final void resetPosition(long j) throws ExoPlaybackException {
        resetPosition(j, false);
    }

    @Override // com.google.android.exoplayer2.Renderer
    public final void setCurrentStreamFinal() {
        this.streamIsFinal = true;
    }

    public int skipSource(long j) {
        SampleStream sampleStream = this.stream;
        sampleStream.getClass();
        return sampleStream.skipData(j - this.streamOffsetUs);
    }

    @Override // com.google.android.exoplayer2.Renderer
    public final void start() throws ExoPlaybackException {
        Assertions.checkState(this.state == 1);
        this.state = 2;
        onStarted();
    }

    @Override // com.google.android.exoplayer2.Renderer
    public final void stop() {
        Assertions.checkState(this.state == 2);
        this.state = 1;
        onStopped();
    }

    @Override // com.google.android.exoplayer2.RendererCapabilities
    public int supportsMixedMimeTypeAdaptation() throws ExoPlaybackException {
        return 0;
    }

    public final ExoPlaybackException createRendererException(Throwable th, @Nullable Format format, boolean z, int i) {
        int i2;
        if (format == null || this.throwRendererExceptionIsExecuting) {
            i2 = 4;
        } else {
            this.throwRendererExceptionIsExecuting = true;
            try {
                int iSupportsFormat = supportsFormat(format) & 7;
                this.throwRendererExceptionIsExecuting = false;
                i2 = iSupportsFormat;
            } catch (ExoPlaybackException unused) {
                this.throwRendererExceptionIsExecuting = false;
                i2 = 4;
            } catch (Throwable th2) {
                this.throwRendererExceptionIsExecuting = false;
                throw th2;
            }
        }
        return ExoPlaybackException.createForRenderer(th, getName(), this.index, format, i2, z, i);
    }

    public final void resetPosition(long j, boolean z) throws ExoPlaybackException {
        this.streamIsFinal = false;
        this.lastResetPositionUs = j;
        this.readingPositionUs = j;
        onPositionReset(j, z);
    }

    @Override // com.google.android.exoplayer2.Renderer
    public final RendererCapabilities getCapabilities() {
        return this;
    }

    public void onDisabled() {
    }

    public void onReset() {
    }

    public void onStarted() throws ExoPlaybackException {
    }

    public void onStopped() {
    }

    @Override // com.google.android.exoplayer2.PlayerMessage.Target
    public void handleMessage(int i, @Nullable Object obj) throws ExoPlaybackException {
    }

    public void onEnabled(boolean z, boolean z2) throws ExoPlaybackException {
    }

    public void onPositionReset(long j, boolean z) throws ExoPlaybackException {
    }

    @Override // com.google.android.exoplayer2.Renderer
    public /* synthetic */ void setPlaybackSpeed(float f, float f2) {
    }

    public void onStreamChanged(Format[] formatArr, long j, long j2) throws ExoPlaybackException {
    }
}
