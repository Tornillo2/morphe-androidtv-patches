package androidx.media3.common.audio;

import androidx.annotation.Nullable;
import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.UnstableApi;
import com.google.common.collect.ImmutableList;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class AudioProcessingPipeline {
    public final ImmutableList<AudioProcessor> audioProcessors;
    public boolean inputEnded;
    public AudioProcessor.AudioFormat outputAudioFormat;
    public AudioProcessor.AudioFormat pendingOutputAudioFormat;
    public final List<AudioProcessor> activeAudioProcessors = new ArrayList();
    public ByteBuffer[] outputBuffers = new ByteBuffer[0];

    public AudioProcessingPipeline(ImmutableList<AudioProcessor> immutableList) {
        this.audioProcessors = immutableList;
        AudioProcessor.AudioFormat audioFormat = AudioProcessor.AudioFormat.NOT_SET;
        this.outputAudioFormat = audioFormat;
        this.pendingOutputAudioFormat = audioFormat;
        this.inputEnded = false;
    }

    @CanIgnoreReturnValue
    public AudioProcessor.AudioFormat configure(AudioProcessor.AudioFormat audioFormat) throws AudioProcessor.UnhandledAudioFormatException {
        if (audioFormat.equals(AudioProcessor.AudioFormat.NOT_SET)) {
            throw new AudioProcessor.UnhandledAudioFormatException(audioFormat);
        }
        for (int i = 0; i < this.audioProcessors.size(); i++) {
            AudioProcessor audioProcessor = this.audioProcessors.get(i);
            AudioProcessor.AudioFormat audioFormatConfigure = audioProcessor.configure(audioFormat);
            if (audioProcessor.isActive()) {
                Assertions.checkState(!audioFormatConfigure.equals(AudioProcessor.AudioFormat.NOT_SET));
                audioFormat = audioFormatConfigure;
            }
        }
        this.pendingOutputAudioFormat = audioFormat;
        return audioFormat;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AudioProcessingPipeline)) {
            return false;
        }
        AudioProcessingPipeline audioProcessingPipeline = (AudioProcessingPipeline) obj;
        if (this.audioProcessors.size() != audioProcessingPipeline.audioProcessors.size()) {
            return false;
        }
        for (int i = 0; i < this.audioProcessors.size(); i++) {
            if (this.audioProcessors.get(i) != audioProcessingPipeline.audioProcessors.get(i)) {
                return false;
            }
        }
        return true;
    }

    public void flush() {
        this.activeAudioProcessors.clear();
        this.outputAudioFormat = this.pendingOutputAudioFormat;
        this.inputEnded = false;
        for (int i = 0; i < this.audioProcessors.size(); i++) {
            AudioProcessor audioProcessor = this.audioProcessors.get(i);
            audioProcessor.flush();
            if (audioProcessor.isActive()) {
                this.activeAudioProcessors.add(audioProcessor);
            }
        }
        this.outputBuffers = new ByteBuffer[this.activeAudioProcessors.size()];
        for (int i2 = 0; i2 <= getFinalOutputBufferIndex(); i2++) {
            this.outputBuffers[i2] = this.activeAudioProcessors.get(i2).getOutput();
        }
    }

    public final int getFinalOutputBufferIndex() {
        return this.outputBuffers.length - 1;
    }

    public ByteBuffer getOutput() {
        if (!isOperational()) {
            return AudioProcessor.EMPTY_BUFFER;
        }
        ByteBuffer byteBuffer = this.outputBuffers[getFinalOutputBufferIndex()];
        if (byteBuffer.hasRemaining()) {
            return byteBuffer;
        }
        processData(AudioProcessor.EMPTY_BUFFER);
        return this.outputBuffers[getFinalOutputBufferIndex()];
    }

    public AudioProcessor.AudioFormat getOutputAudioFormat() {
        return this.outputAudioFormat;
    }

    public int hashCode() {
        return this.audioProcessors.hashCode();
    }

    public boolean isEnded() {
        return this.inputEnded && this.activeAudioProcessors.get(getFinalOutputBufferIndex()).isEnded() && !this.outputBuffers[getFinalOutputBufferIndex()].hasRemaining();
    }

    public boolean isOperational() {
        return !this.activeAudioProcessors.isEmpty();
    }

    public final void processData(ByteBuffer byteBuffer) {
        boolean z;
        for (boolean z2 = true; z2; z2 = z) {
            z = false;
            int i = 0;
            while (i <= getFinalOutputBufferIndex()) {
                if (!this.outputBuffers[i].hasRemaining()) {
                    AudioProcessor audioProcessor = this.activeAudioProcessors.get(i);
                    if (!audioProcessor.isEnded()) {
                        ByteBuffer byteBuffer2 = i > 0 ? this.outputBuffers[i - 1] : byteBuffer.hasRemaining() ? byteBuffer : AudioProcessor.EMPTY_BUFFER;
                        long jRemaining = byteBuffer2.remaining();
                        audioProcessor.queueInput(byteBuffer2);
                        this.outputBuffers[i] = audioProcessor.getOutput();
                        z |= jRemaining - ((long) byteBuffer2.remaining()) > 0 || this.outputBuffers[i].hasRemaining();
                    } else if (!this.outputBuffers[i].hasRemaining() && i < getFinalOutputBufferIndex()) {
                        this.activeAudioProcessors.get(i + 1).queueEndOfStream();
                    }
                }
                i++;
            }
        }
    }

    public void queueEndOfStream() {
        if (!isOperational() || this.inputEnded) {
            return;
        }
        this.inputEnded = true;
        this.activeAudioProcessors.get(0).queueEndOfStream();
    }

    public void queueInput(ByteBuffer byteBuffer) {
        if (!isOperational() || this.inputEnded) {
            return;
        }
        processData(byteBuffer);
    }

    public void reset() {
        for (int i = 0; i < this.audioProcessors.size(); i++) {
            AudioProcessor audioProcessor = this.audioProcessors.get(i);
            audioProcessor.flush();
            audioProcessor.reset();
        }
        this.outputBuffers = new ByteBuffer[0];
        AudioProcessor.AudioFormat audioFormat = AudioProcessor.AudioFormat.NOT_SET;
        this.outputAudioFormat = audioFormat;
        this.pendingOutputAudioFormat = audioFormat;
        this.inputEnded = false;
    }
}
