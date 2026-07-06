package androidx.media3.exoplayer.audio;

import androidx.annotation.Nullable;
import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.WavUtil;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class TeeAudioProcessor extends androidx.media3.common.audio.BaseAudioProcessor {
    public final AudioBufferSink audioBufferSink;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface AudioBufferSink {
        void flush(int i, int i2, int i3);

        void handleBuffer(ByteBuffer byteBuffer);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class WavFileAudioBufferSink implements AudioBufferSink {
        public static final int FILE_SIZE_MINUS_44_OFFSET = 40;
        public static final int FILE_SIZE_MINUS_8_OFFSET = 4;
        public static final int HEADER_LENGTH = 44;
        public static final String TAG = "WaveFileAudioBufferSink";
        public int bytesWritten;
        public int channelCount;
        public int counter;
        public int encoding;
        public final String outputFileNamePrefix;

        @Nullable
        public RandomAccessFile randomAccessFile;
        public int sampleRateHz;
        public final byte[] scratchBuffer;
        public final ByteBuffer scratchByteBuffer;

        public WavFileAudioBufferSink(String str) {
            this.outputFileNamePrefix = str;
            byte[] bArr = new byte[1024];
            this.scratchBuffer = bArr;
            this.scratchByteBuffer = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        }

        @Override // androidx.media3.exoplayer.audio.TeeAudioProcessor.AudioBufferSink
        public void flush(int i, int i2, int i3) {
            try {
                reset();
            } catch (IOException e) {
                Log.e("WaveFileAudioBufferSink", "Error resetting", e);
            }
            this.sampleRateHz = i;
            this.channelCount = i2;
            this.encoding = i3;
        }

        public final String getNextOutputFileName() {
            String str = this.outputFileNamePrefix;
            int i = this.counter;
            this.counter = i + 1;
            return Util.formatInvariant("%s-%04d.wav", str, Integer.valueOf(i));
        }

        @Override // androidx.media3.exoplayer.audio.TeeAudioProcessor.AudioBufferSink
        public void handleBuffer(ByteBuffer byteBuffer) {
            try {
                maybePrepareFile();
                writeBuffer(byteBuffer);
            } catch (IOException e) {
                Log.e("WaveFileAudioBufferSink", "Error writing data", e);
            }
        }

        public final void maybePrepareFile() throws IOException {
            if (this.randomAccessFile != null) {
                return;
            }
            RandomAccessFile randomAccessFile = new RandomAccessFile(getNextOutputFileName(), "rw");
            writeFileHeader(randomAccessFile);
            this.randomAccessFile = randomAccessFile;
            this.bytesWritten = 44;
        }

        public final void reset() throws IOException {
            RandomAccessFile randomAccessFile = this.randomAccessFile;
            if (randomAccessFile == null) {
                return;
            }
            try {
                this.scratchByteBuffer.clear();
                this.scratchByteBuffer.putInt(this.bytesWritten - 8);
                randomAccessFile.seek(4L);
                randomAccessFile.write(this.scratchBuffer, 0, 4);
                this.scratchByteBuffer.clear();
                this.scratchByteBuffer.putInt(this.bytesWritten - 44);
                randomAccessFile.seek(40L);
                randomAccessFile.write(this.scratchBuffer, 0, 4);
            } catch (IOException e) {
                Log.w("WaveFileAudioBufferSink", "Error updating file size", e);
            }
            try {
                randomAccessFile.close();
            } finally {
                this.randomAccessFile = null;
            }
        }

        public final void writeBuffer(ByteBuffer byteBuffer) throws IOException {
            RandomAccessFile randomAccessFile = this.randomAccessFile;
            randomAccessFile.getClass();
            while (byteBuffer.hasRemaining()) {
                int iMin = Math.min(byteBuffer.remaining(), this.scratchBuffer.length);
                byteBuffer.get(this.scratchBuffer, 0, iMin);
                randomAccessFile.write(this.scratchBuffer, 0, iMin);
                this.bytesWritten += iMin;
            }
        }

        public final void writeFileHeader(RandomAccessFile randomAccessFile) throws IOException {
            randomAccessFile.writeInt(1380533830);
            randomAccessFile.writeInt(-1);
            randomAccessFile.writeInt(1463899717);
            randomAccessFile.writeInt(1718449184);
            this.scratchByteBuffer.clear();
            this.scratchByteBuffer.putInt(16);
            this.scratchByteBuffer.putShort((short) WavUtil.getTypeForPcmEncoding(this.encoding));
            this.scratchByteBuffer.putShort((short) this.channelCount);
            this.scratchByteBuffer.putInt(this.sampleRateHz);
            int pcmFrameSize = Util.getPcmFrameSize(this.encoding, this.channelCount);
            this.scratchByteBuffer.putInt(this.sampleRateHz * pcmFrameSize);
            this.scratchByteBuffer.putShort((short) pcmFrameSize);
            this.scratchByteBuffer.putShort((short) ((pcmFrameSize * 8) / this.channelCount));
            randomAccessFile.write(this.scratchBuffer, 0, this.scratchByteBuffer.position());
            randomAccessFile.writeInt(1684108385);
            randomAccessFile.writeInt(-1);
        }
    }

    public TeeAudioProcessor(AudioBufferSink audioBufferSink) {
        audioBufferSink.getClass();
        this.audioBufferSink = audioBufferSink;
    }

    public final void flushSinkIfActive() {
        if (isActive()) {
            AudioBufferSink audioBufferSink = this.audioBufferSink;
            AudioProcessor.AudioFormat audioFormat = this.inputAudioFormat;
            audioBufferSink.flush(audioFormat.sampleRate, audioFormat.channelCount, audioFormat.encoding);
        }
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public void onFlush() {
        flushSinkIfActive();
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public void onQueueEndOfStream() {
        flushSinkIfActive();
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public void onReset() {
        flushSinkIfActive();
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public void queueInput(ByteBuffer byteBuffer) {
        int iRemaining = byteBuffer.remaining();
        if (iRemaining == 0) {
            return;
        }
        this.audioBufferSink.handleBuffer(Util.createReadOnlyByteBuffer(byteBuffer));
        replaceOutputBuffer(iRemaining).put(byteBuffer).flip();
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public AudioProcessor.AudioFormat onConfigure(AudioProcessor.AudioFormat audioFormat) {
        return audioFormat;
    }
}
