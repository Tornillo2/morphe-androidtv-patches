package androidx.media3.exoplayer.video.spherical;

import androidx.annotation.Nullable;
import androidx.media3.common.Format;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.BaseRenderer;
import androidx.media3.exoplayer.ExoPlaybackException;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.source.MediaSource;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class CameraMotionRenderer extends BaseRenderer {
    public static final int SAMPLE_WINDOW_DURATION_US = 100000;
    public static final String TAG = "CameraMotionRenderer";
    public final DecoderInputBuffer buffer;
    public long lastTimestampUs;

    @Nullable
    public CameraMotionListener listener;
    public long offsetUs;
    public final ParsableByteArray scratch;

    public CameraMotionRenderer() {
        super(6);
        this.buffer = new DecoderInputBuffer(1, 0);
        this.scratch = new ParsableByteArray();
    }

    @Override // androidx.media3.exoplayer.Renderer, androidx.media3.exoplayer.RendererCapabilities
    public String getName() {
        return "CameraMotionRenderer";
    }

    @Override // androidx.media3.exoplayer.BaseRenderer, androidx.media3.exoplayer.PlayerMessage.Target
    public void handleMessage(int i, @Nullable Object obj) throws ExoPlaybackException {
        if (i == 8) {
            this.listener = (CameraMotionListener) obj;
        }
    }

    @Override // androidx.media3.exoplayer.Renderer
    public boolean isEnded() {
        return hasReadStreamToEnd();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public boolean isReady() {
        return true;
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onDisabled() {
        resetListener();
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onPositionReset(long j, boolean z) {
        this.lastTimestampUs = Long.MIN_VALUE;
        resetListener();
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onStreamChanged(Format[] formatArr, long j, long j2, MediaSource.MediaPeriodId mediaPeriodId) {
        this.offsetUs = j2;
    }

    @Nullable
    public final float[] parseMetadata(ByteBuffer byteBuffer) {
        if (byteBuffer.remaining() != 16) {
            return null;
        }
        this.scratch.reset(byteBuffer.array(), byteBuffer.limit());
        this.scratch.setPosition(byteBuffer.arrayOffset() + 4);
        float[] fArr = new float[3];
        for (int i = 0; i < 3; i++) {
            fArr[i] = Float.intBitsToFloat(this.scratch.readLittleEndianInt());
        }
        return fArr;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public void render(long j, long j2) {
        while (!hasReadStreamToEnd() && this.lastTimestampUs < 100000 + j) {
            this.buffer.clear();
            if (readSource(getFormatHolder(), this.buffer, 0) != -4 || this.buffer.getFlag(4)) {
                return;
            }
            DecoderInputBuffer decoderInputBuffer = this.buffer;
            long j3 = decoderInputBuffer.timeUs;
            this.lastTimestampUs = j3;
            boolean z = j3 < this.lastResetPositionUs;
            if (this.listener != null && !z) {
                decoderInputBuffer.flip();
                ByteBuffer byteBuffer = this.buffer.data;
                Util.castNonNull(byteBuffer);
                float[] metadata = parseMetadata(byteBuffer);
                if (metadata != null) {
                    this.listener.onCameraMotion(this.lastTimestampUs - this.offsetUs, metadata);
                }
            }
        }
    }

    public final void resetListener() {
        CameraMotionListener cameraMotionListener = this.listener;
        if (cameraMotionListener != null) {
            cameraMotionListener.onCameraMotionReset();
        }
    }

    @Override // androidx.media3.exoplayer.RendererCapabilities
    public int supportsFormat(Format format) {
        return "application/x-camera-motion".equals(format.sampleMimeType) ? RendererCapabilities.CC.create(4, 0, 0, 0) : RendererCapabilities.CC.create(0, 0, 0, 0);
    }
}
