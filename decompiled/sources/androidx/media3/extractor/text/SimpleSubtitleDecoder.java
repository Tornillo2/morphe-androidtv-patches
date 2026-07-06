package androidx.media3.extractor.text;

import androidx.annotation.Nullable;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.decoder.DecoderOutputBuffer;
import androidx.media3.decoder.SimpleDecoder;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public abstract class SimpleSubtitleDecoder extends SimpleDecoder<SubtitleInputBuffer, SubtitleOutputBuffer, SubtitleDecoderException> implements SubtitleDecoder {
    public final String name;

    /* JADX INFO: renamed from: androidx.media3.extractor.text.SimpleSubtitleDecoder$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 extends SubtitleOutputBuffer {
        public AnonymousClass1() {
        }

        @Override // androidx.media3.decoder.DecoderOutputBuffer
        public void release() {
            SimpleSubtitleDecoder.this.releaseOutputBuffer(this);
        }
    }

    public SimpleSubtitleDecoder(String str) {
        super(new SubtitleInputBuffer[2], new SubtitleOutputBuffer[2]);
        this.name = str;
        setInitialInputBufferSize(1024);
    }

    @Override // androidx.media3.decoder.SimpleDecoder
    public DecoderInputBuffer createInputBuffer() {
        return new SubtitleInputBuffer();
    }

    @Override // androidx.media3.decoder.SimpleDecoder
    public DecoderOutputBuffer createOutputBuffer() {
        return new AnonymousClass1();
    }

    public abstract Subtitle decode(byte[] bArr, int i, boolean z) throws SubtitleDecoderException;

    @Override // androidx.media3.decoder.Decoder
    public final String getName() {
        return this.name;
    }

    @Override // androidx.media3.decoder.SimpleDecoder
    public final SubtitleInputBuffer createInputBuffer() {
        return new SubtitleInputBuffer();
    }

    @Override // androidx.media3.decoder.SimpleDecoder
    public final SubtitleOutputBuffer createOutputBuffer() {
        return new AnonymousClass1();
    }

    @Override // androidx.media3.decoder.SimpleDecoder
    public final SubtitleDecoderException createUnexpectedDecodeException(Throwable th) {
        return new SubtitleDecoderException("Unexpected decode error", th);
    }

    @Override // androidx.media3.decoder.SimpleDecoder
    @Nullable
    public final SubtitleDecoderException decode(SubtitleInputBuffer subtitleInputBuffer, SubtitleOutputBuffer subtitleOutputBuffer, boolean z) {
        try {
            ByteBuffer byteBuffer = subtitleInputBuffer.data;
            byteBuffer.getClass();
            subtitleOutputBuffer.setContent(subtitleInputBuffer.timeUs, decode(byteBuffer.array(), byteBuffer.limit(), z), subtitleInputBuffer.subsampleOffsetUs);
            subtitleOutputBuffer.clearFlag(Integer.MIN_VALUE);
            return null;
        } catch (SubtitleDecoderException e) {
            return e;
        }
    }

    @Override // androidx.media3.extractor.text.SubtitleDecoder
    public void setPositionUs(long j) {
    }
}
