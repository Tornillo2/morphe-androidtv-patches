package androidx.media3.common.audio;

import android.support.v4.media.session.PlaybackStateCompat;
import androidx.annotation.Nullable;
import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public class SonicAudioProcessor implements AudioProcessor {
    public static final float CLOSE_THRESHOLD = 1.0E-4f;
    public static final int MIN_BYTES_FOR_DURATION_SCALING_CALCULATION = 1024;
    public static final int SAMPLE_RATE_NO_CHANGE = -1;
    public ByteBuffer buffer;
    public AudioProcessor.AudioFormat inputAudioFormat;
    public long inputBytes;
    public boolean inputEnded;
    public AudioProcessor.AudioFormat outputAudioFormat;
    public ByteBuffer outputBuffer;
    public long outputBytes;
    public AudioProcessor.AudioFormat pendingInputAudioFormat;
    public AudioProcessor.AudioFormat pendingOutputAudioFormat;
    public int pendingOutputSampleRate;
    public boolean pendingSonicRecreation;
    public ShortBuffer shortBuffer;

    @Nullable
    public Sonic sonic;
    public float speed = 1.0f;
    public float pitch = 1.0f;

    public SonicAudioProcessor() {
        AudioProcessor.AudioFormat audioFormat = AudioProcessor.AudioFormat.NOT_SET;
        this.pendingInputAudioFormat = audioFormat;
        this.pendingOutputAudioFormat = audioFormat;
        this.inputAudioFormat = audioFormat;
        this.outputAudioFormat = audioFormat;
        ByteBuffer byteBuffer = AudioProcessor.EMPTY_BUFFER;
        this.buffer = byteBuffer;
        this.shortBuffer = byteBuffer.asShortBuffer();
        this.outputBuffer = byteBuffer;
        this.pendingOutputSampleRate = -1;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public final AudioProcessor.AudioFormat configure(AudioProcessor.AudioFormat audioFormat) throws AudioProcessor.UnhandledAudioFormatException {
        if (audioFormat.encoding != 2) {
            throw new AudioProcessor.UnhandledAudioFormatException(audioFormat);
        }
        int i = this.pendingOutputSampleRate;
        if (i == -1) {
            i = audioFormat.sampleRate;
        }
        this.pendingInputAudioFormat = audioFormat;
        AudioProcessor.AudioFormat audioFormat2 = new AudioProcessor.AudioFormat(i, audioFormat.channelCount, 2);
        this.pendingOutputAudioFormat = audioFormat2;
        this.pendingSonicRecreation = true;
        return audioFormat2;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public final void flush() {
        if (isActive()) {
            AudioProcessor.AudioFormat audioFormat = this.pendingInputAudioFormat;
            this.inputAudioFormat = audioFormat;
            AudioProcessor.AudioFormat audioFormat2 = this.pendingOutputAudioFormat;
            this.outputAudioFormat = audioFormat2;
            if (this.pendingSonicRecreation) {
                this.sonic = new Sonic(audioFormat.sampleRate, audioFormat.channelCount, this.speed, this.pitch, audioFormat2.sampleRate);
            } else {
                Sonic sonic = this.sonic;
                if (sonic != null) {
                    sonic.flush();
                }
            }
        }
        this.outputBuffer = AudioProcessor.EMPTY_BUFFER;
        this.inputBytes = 0L;
        this.outputBytes = 0L;
        this.inputEnded = false;
    }

    public final long getMediaDuration(long j) {
        if (this.outputBytes < PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) {
            return (long) (((double) this.speed) * j);
        }
        long j2 = this.inputBytes;
        Sonic sonic = this.sonic;
        sonic.getClass();
        long pendingInputBytes = j2 - ((long) sonic.getPendingInputBytes());
        int i = this.outputAudioFormat.sampleRate;
        int i2 = this.inputAudioFormat.sampleRate;
        return i == i2 ? Util.scaleLargeValue(j, pendingInputBytes, this.outputBytes, RoundingMode.FLOOR) : Util.scaleLargeValue(j, pendingInputBytes * ((long) i), this.outputBytes * ((long) i2), RoundingMode.FLOOR);
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public final ByteBuffer getOutput() {
        int outputSize;
        Sonic sonic = this.sonic;
        if (sonic != null && (outputSize = sonic.getOutputSize()) > 0) {
            if (this.buffer.capacity() < outputSize) {
                ByteBuffer byteBufferOrder = ByteBuffer.allocateDirect(outputSize).order(ByteOrder.nativeOrder());
                this.buffer = byteBufferOrder;
                this.shortBuffer = byteBufferOrder.asShortBuffer();
            } else {
                this.buffer.clear();
                this.shortBuffer.clear();
            }
            sonic.getOutput(this.shortBuffer);
            this.outputBytes += (long) outputSize;
            this.buffer.limit(outputSize);
            this.outputBuffer = this.buffer;
        }
        ByteBuffer byteBuffer = this.outputBuffer;
        this.outputBuffer = AudioProcessor.EMPTY_BUFFER;
        return byteBuffer;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public final boolean isActive() {
        if (this.pendingOutputAudioFormat.sampleRate != -1) {
            return Math.abs(this.speed - 1.0f) >= 1.0E-4f || Math.abs(this.pitch - 1.0f) >= 1.0E-4f || this.pendingOutputAudioFormat.sampleRate != this.pendingInputAudioFormat.sampleRate;
        }
        return false;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public final boolean isEnded() {
        if (!this.inputEnded) {
            return false;
        }
        Sonic sonic = this.sonic;
        return sonic == null || sonic.getOutputSize() == 0;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public final void queueEndOfStream() {
        Sonic sonic = this.sonic;
        if (sonic != null) {
            sonic.queueEndOfStream();
        }
        this.inputEnded = true;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public final void queueInput(ByteBuffer byteBuffer) {
        if (byteBuffer.hasRemaining()) {
            Sonic sonic = this.sonic;
            sonic.getClass();
            ShortBuffer shortBufferAsShortBuffer = byteBuffer.asShortBuffer();
            int iRemaining = byteBuffer.remaining();
            this.inputBytes += (long) iRemaining;
            sonic.queueInput(shortBufferAsShortBuffer);
            byteBuffer.position(byteBuffer.position() + iRemaining);
        }
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public final void reset() {
        this.speed = 1.0f;
        this.pitch = 1.0f;
        AudioProcessor.AudioFormat audioFormat = AudioProcessor.AudioFormat.NOT_SET;
        this.pendingInputAudioFormat = audioFormat;
        this.pendingOutputAudioFormat = audioFormat;
        this.inputAudioFormat = audioFormat;
        this.outputAudioFormat = audioFormat;
        ByteBuffer byteBuffer = AudioProcessor.EMPTY_BUFFER;
        this.buffer = byteBuffer;
        this.shortBuffer = byteBuffer.asShortBuffer();
        this.outputBuffer = byteBuffer;
        this.pendingOutputSampleRate = -1;
        this.pendingSonicRecreation = false;
        this.sonic = null;
        this.inputBytes = 0L;
        this.outputBytes = 0L;
        this.inputEnded = false;
    }

    public final void setOutputSampleRateHz(int i) {
        this.pendingOutputSampleRate = i;
    }

    public final void setPitch(float f) {
        if (this.pitch != f) {
            this.pitch = f;
            this.pendingSonicRecreation = true;
        }
    }

    public final void setSpeed(float f) {
        if (this.speed != f) {
            this.speed = f;
            this.pendingSonicRecreation = true;
        }
    }
}
