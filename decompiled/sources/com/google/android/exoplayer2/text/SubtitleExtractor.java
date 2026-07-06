package com.google.android.exoplayer2.text;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.decoder.DecoderException;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.IndexSeekMap;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import com.google.common.primitives.Ints;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class SubtitleExtractor implements Extractor {
    public static final int DEFAULT_BUFFER_SIZE = 1024;
    public static final int STATE_CREATED = 0;
    public static final int STATE_EXTRACTING = 2;
    public static final int STATE_FINISHED = 4;
    public static final int STATE_INITIALIZED = 1;
    public static final int STATE_RELEASED = 5;
    public static final int STATE_SEEKING = 3;
    public int bytesRead;
    public ExtractorOutput extractorOutput;
    public final Format format;
    public final List<ParsableByteArray> samples;
    public long seekTimeUs;
    public int state;
    public final SubtitleDecoder subtitleDecoder;
    public final List<Long> timestamps;
    public TrackOutput trackOutput;
    public final CueEncoder cueEncoder = new CueEncoder();
    public final ParsableByteArray subtitleData = new ParsableByteArray();

    public SubtitleExtractor(SubtitleDecoder subtitleDecoder, Format format) {
        this.subtitleDecoder = subtitleDecoder;
        format.getClass();
        Format.Builder builder = new Format.Builder(format);
        builder.sampleMimeType = MimeTypes.TEXT_EXOPLAYER_CUES;
        builder.codecs = format.sampleMimeType;
        this.format = new Format(builder);
        this.timestamps = new ArrayList();
        this.samples = new ArrayList();
        this.state = 0;
        this.seekTimeUs = -9223372036854775807L;
    }

    public final void decode() throws IOException, DecoderException {
        try {
            SubtitleInputBuffer subtitleInputBufferDequeueInputBuffer = this.subtitleDecoder.dequeueInputBuffer();
            while (subtitleInputBufferDequeueInputBuffer == null) {
                Thread.sleep(5L);
                subtitleInputBufferDequeueInputBuffer = this.subtitleDecoder.dequeueInputBuffer();
            }
            subtitleInputBufferDequeueInputBuffer.ensureSpaceForWrite(this.bytesRead);
            subtitleInputBufferDequeueInputBuffer.data.put(this.subtitleData.data, 0, this.bytesRead);
            subtitleInputBufferDequeueInputBuffer.data.limit(this.bytesRead);
            this.subtitleDecoder.queueInputBuffer(subtitleInputBufferDequeueInputBuffer);
            SubtitleOutputBuffer subtitleOutputBufferDequeueOutputBuffer = this.subtitleDecoder.dequeueOutputBuffer();
            while (subtitleOutputBufferDequeueOutputBuffer == null) {
                Thread.sleep(5L);
                subtitleOutputBufferDequeueOutputBuffer = this.subtitleDecoder.dequeueOutputBuffer();
            }
            for (int i = 0; i < subtitleOutputBufferDequeueOutputBuffer.getEventTimeCount(); i++) {
                byte[] bArrEncode = this.cueEncoder.encode(subtitleOutputBufferDequeueOutputBuffer.getCues(subtitleOutputBufferDequeueOutputBuffer.getEventTime(i)));
                this.timestamps.add(Long.valueOf(subtitleOutputBufferDequeueOutputBuffer.getEventTime(i)));
                this.samples.add(new ParsableByteArray(bArrEncode));
            }
            subtitleOutputBufferDequeueOutputBuffer.release();
        } catch (SubtitleDecoderException e) {
            throw ParserException.createForMalformedContainer("SubtitleDecoder failed.", e);
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException();
        }
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        Assertions.checkState(this.state == 0);
        this.extractorOutput = extractorOutput;
        this.trackOutput = extractorOutput.track(0, 3);
        this.extractorOutput.endTracks();
        this.extractorOutput.seekMap(new IndexSeekMap(new long[]{0}, new long[]{0}, -9223372036854775807L));
        this.trackOutput.format(this.format);
        this.state = 1;
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException, DecoderException {
        int i = this.state;
        Assertions.checkState((i == 0 || i == 5) ? false : true);
        if (this.state == 1) {
            this.subtitleData.reset(extractorInput.getLength() != -1 ? Ints.checkedCast(extractorInput.getLength()) : 1024);
            this.bytesRead = 0;
            this.state = 2;
        }
        if (this.state == 2 && readFromInput(extractorInput)) {
            decode();
            writeToOutput();
            this.state = 4;
        }
        if (this.state == 3 && skipInput(extractorInput)) {
            writeToOutput();
            this.state = 4;
        }
        return this.state == 4 ? -1 : 0;
    }

    public final boolean readFromInput(ExtractorInput extractorInput) throws IOException {
        ParsableByteArray parsableByteArray = this.subtitleData;
        int length = parsableByteArray.data.length;
        int i = this.bytesRead;
        if (length == i) {
            parsableByteArray.ensureCapacity(i + 1024);
        }
        byte[] bArr = this.subtitleData.data;
        int i2 = this.bytesRead;
        int i3 = extractorInput.read(bArr, i2, bArr.length - i2);
        if (i3 != -1) {
            this.bytesRead += i3;
        }
        long length2 = extractorInput.getLength();
        return (length2 != -1 && ((long) this.bytesRead) == length2) || i3 == -1;
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void release() {
        if (this.state == 5) {
            return;
        }
        this.subtitleDecoder.release();
        this.state = 5;
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void seek(long j, long j2) {
        int i = this.state;
        Assertions.checkState((i == 0 || i == 5) ? false : true);
        this.seekTimeUs = j2;
        if (this.state == 2) {
            this.state = 1;
        }
        if (this.state == 4) {
            this.state = 3;
        }
    }

    public final boolean skipInput(ExtractorInput extractorInput) throws IOException {
        return extractorInput.skip((extractorInput.getLength() > (-1L) ? 1 : (extractorInput.getLength() == (-1L) ? 0 : -1)) != 0 ? Ints.checkedCast(extractorInput.getLength()) : 1024) == -1;
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) throws IOException {
        return true;
    }

    public final void writeToOutput() {
        Assertions.checkStateNotNull(this.trackOutput);
        Assertions.checkState(this.timestamps.size() == this.samples.size());
        long j = this.seekTimeUs;
        for (int iBinarySearchFloor = j == -9223372036854775807L ? 0 : Util.binarySearchFloor((List<? extends Comparable<? super Long>>) this.timestamps, Long.valueOf(j), true, true); iBinarySearchFloor < this.samples.size(); iBinarySearchFloor++) {
            ParsableByteArray parsableByteArray = this.samples.get(iBinarySearchFloor);
            parsableByteArray.setPosition(0);
            int length = parsableByteArray.data.length;
            this.trackOutput.sampleData(parsableByteArray, length);
            this.trackOutput.sampleMetadata(this.timestamps.get(iBinarySearchFloor).longValue(), 1, length, 0, null);
        }
    }
}
