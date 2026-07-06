package androidx.media3.common.audio;

import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import java.math.RoundingMode;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class SpeedChangingAudioProcessor extends BaseAudioProcessor {
    public long bytesRead;
    public boolean endOfStreamQueuedToSonic;
    public final SpeedProvider speedProvider;
    public final SonicAudioProcessor sonicAudioProcessor = new SonicAudioProcessor();
    public float currentSpeed = 1.0f;

    public SpeedChangingAudioProcessor(SpeedProvider speedProvider) {
        this.speedProvider = speedProvider;
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor, androidx.media3.common.audio.AudioProcessor
    public ByteBuffer getOutput() {
        return isUsingSonic() ? this.sonicAudioProcessor.getOutput() : super.getOutput();
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor, androidx.media3.common.audio.AudioProcessor
    public boolean isEnded() {
        return super.isEnded() && this.sonicAudioProcessor.isEnded();
    }

    public final boolean isUsingSonic() {
        return this.currentSpeed != 1.0f;
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public AudioProcessor.AudioFormat onConfigure(AudioProcessor.AudioFormat audioFormat) throws AudioProcessor.UnhandledAudioFormatException {
        return this.sonicAudioProcessor.configure(audioFormat);
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public void onFlush() {
        this.sonicAudioProcessor.flush();
        this.endOfStreamQueuedToSonic = false;
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public void onQueueEndOfStream() {
        if (this.endOfStreamQueuedToSonic) {
            return;
        }
        this.sonicAudioProcessor.queueEndOfStream();
        this.endOfStreamQueuedToSonic = true;
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public void onReset() {
        this.currentSpeed = 1.0f;
        this.bytesRead = 0L;
        this.sonicAudioProcessor.reset();
        this.endOfStreamQueuedToSonic = false;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public void queueInput(ByteBuffer byteBuffer) {
        int iScaleLargeValue;
        long j = this.bytesRead;
        AudioProcessor.AudioFormat audioFormat = this.inputAudioFormat;
        long j2 = ((long) audioFormat.sampleRate) * ((long) audioFormat.bytesPerFrame);
        RoundingMode roundingMode = RoundingMode.FLOOR;
        long jScaleLargeValue = Util.scaleLargeValue(j, 1000000L, j2, roundingMode);
        float speed = this.speedProvider.getSpeed(jScaleLargeValue);
        if (speed != this.currentSpeed) {
            this.currentSpeed = speed;
            if (isUsingSonic()) {
                this.sonicAudioProcessor.setSpeed(speed);
                this.sonicAudioProcessor.setPitch(speed);
            }
            flush();
        }
        int iLimit = byteBuffer.limit();
        long nextSpeedChangeTimeUs = this.speedProvider.getNextSpeedChangeTimeUs(jScaleLargeValue);
        if (nextSpeedChangeTimeUs != -9223372036854775807L) {
            long j3 = nextSpeedChangeTimeUs - jScaleLargeValue;
            AudioProcessor.AudioFormat audioFormat2 = this.inputAudioFormat;
            iScaleLargeValue = (int) Util.scaleLargeValue(j3, ((long) audioFormat2.sampleRate) * ((long) audioFormat2.bytesPerFrame), 1000000L, roundingMode);
            int i = this.inputAudioFormat.bytesPerFrame;
            int i2 = i - (iScaleLargeValue % i);
            if (i2 != i) {
                iScaleLargeValue += i2;
            }
            byteBuffer.limit(Math.min(iLimit, byteBuffer.position() + iScaleLargeValue));
        } else {
            iScaleLargeValue = -1;
        }
        long jPosition = byteBuffer.position();
        if (isUsingSonic()) {
            this.sonicAudioProcessor.queueInput(byteBuffer);
            if (iScaleLargeValue != -1 && ((long) byteBuffer.position()) - jPosition == iScaleLargeValue) {
                this.sonicAudioProcessor.queueEndOfStream();
                this.endOfStreamQueuedToSonic = true;
            }
        } else {
            ByteBuffer byteBufferReplaceOutputBuffer = replaceOutputBuffer(byteBuffer.remaining());
            if (byteBuffer.hasRemaining()) {
                byteBufferReplaceOutputBuffer.put(byteBuffer);
            }
            byteBufferReplaceOutputBuffer.flip();
        }
        this.bytesRead = (((long) byteBuffer.position()) - jPosition) + this.bytesRead;
        byteBuffer.limit(iLimit);
    }
}
