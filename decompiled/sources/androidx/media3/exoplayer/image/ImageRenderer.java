package androidx.media3.exoplayer.image;

import android.graphics.Bitmap;
import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.TraceUtil;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.BaseRenderer;
import androidx.media3.exoplayer.ExoPlaybackException;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.image.ImageDecoder;
import java.util.ArrayDeque;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public class ImageRenderer extends BaseRenderer {
    public static final long IMAGE_PRESENTATION_WINDOW_THRESHOLD_US = 30000;
    public static final int REINITIALIZATION_STATE_NONE = 0;
    public static final int REINITIALIZATION_STATE_SIGNAL_END_OF_STREAM_THEN_WAIT = 2;
    public static final int REINITIALIZATION_STATE_WAIT_END_OF_STREAM = 3;
    public static final String TAG = "ImageRenderer";
    public int currentTileIndex;
    public ImageDecoder decoder;
    public final ImageDecoder.Factory decoderFactory;
    public int decoderReinitializationState;
    public int firstFrameState;
    public final DecoderInputBuffer flagsOnlyBuffer;
    public ImageOutput imageOutput;
    public DecoderInputBuffer inputBuffer;
    public Format inputFormat;
    public boolean inputStreamEnded;
    public long largestQueuedPresentationTimeUs;
    public long lastProcessedOutputBufferTimeUs;
    public TileInfo nextTileInfo;
    public Bitmap outputBitmap;
    public boolean outputStreamEnded;
    public OutputStreamInfo outputStreamInfo;
    public final ArrayDeque<OutputStreamInfo> pendingOutputStreamChanges;
    public boolean readyToOutputTiles;
    public TileInfo tileInfo;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class OutputStreamInfo {
        public static final OutputStreamInfo UNSET = new OutputStreamInfo(-9223372036854775807L, -9223372036854775807L);
        public final long previousStreamLastBufferTimeUs;
        public final long streamOffsetUs;

        public OutputStreamInfo(long j, long j2) {
            this.previousStreamLastBufferTimeUs = j;
            this.streamOffsetUs = j2;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class TileInfo {
        public final long presentationTimeUs;
        public Bitmap tileBitmap;
        public final int tileIndex;

        public TileInfo(int i, long j) {
            this.tileIndex = i;
            this.presentationTimeUs = j;
        }

        public long getPresentationTimeUs() {
            return this.presentationTimeUs;
        }

        public Bitmap getTileBitmap() {
            return this.tileBitmap;
        }

        public int getTileIndex() {
            return this.tileIndex;
        }

        public boolean hasTileBitmap() {
            return this.tileBitmap != null;
        }

        public void setTileBitmap(Bitmap bitmap) {
            this.tileBitmap = bitmap;
        }
    }

    public ImageRenderer(ImageDecoder.Factory factory, ImageOutput imageOutput) {
        super(4);
        this.decoderFactory = factory;
        this.imageOutput = imageOutput == null ? ImageOutput.NO_OP : imageOutput;
        this.flagsOnlyBuffer = DecoderInputBuffer.newNoDataInstance();
        this.outputStreamInfo = OutputStreamInfo.UNSET;
        this.pendingOutputStreamChanges = new ArrayDeque<>();
        this.largestQueuedPresentationTimeUs = -9223372036854775807L;
        this.lastProcessedOutputBufferTimeUs = -9223372036854775807L;
        this.decoderReinitializationState = 0;
        this.firstFrameState = 1;
    }

    public static ImageOutput getImageOutput(ImageOutput imageOutput) {
        return imageOutput == null ? ImageOutput.NO_OP : imageOutput;
    }

    private void onProcessedOutputBuffer(long j) {
        this.lastProcessedOutputBufferTimeUs = j;
        while (!this.pendingOutputStreamChanges.isEmpty() && j >= this.pendingOutputStreamChanges.peek().previousStreamLastBufferTimeUs) {
            this.outputStreamInfo = this.pendingOutputStreamChanges.removeFirst();
        }
    }

    public final boolean canCreateDecoderForFormat(Format format) {
        int iSupportsFormat = this.decoderFactory.supportsFormat(format);
        return iSupportsFormat == RendererCapabilities.CC.create(4, 0, 0, 0) || iSupportsFormat == RendererCapabilities.CC.create(3, 0, 0, 0);
    }

    public final Bitmap cropTileFromImageGrid(int i) {
        Assertions.checkStateNotNull(this.outputBitmap);
        int width = this.outputBitmap.getWidth();
        Format format = this.inputFormat;
        Assertions.checkStateNotNull(format);
        int i2 = width / format.tileCountHorizontal;
        int height = this.outputBitmap.getHeight();
        Format format2 = this.inputFormat;
        Assertions.checkStateNotNull(format2);
        int i3 = height / format2.tileCountVertical;
        Format format3 = this.inputFormat;
        return Bitmap.createBitmap(this.outputBitmap, (i % format3.tileCountVertical) * i2, (i / format3.tileCountHorizontal) * i3, i2, i3);
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00e4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean drainOutput(long r13, long r15) throws androidx.media3.exoplayer.ExoPlaybackException, androidx.media3.exoplayer.image.ImageDecoderException {
        /*
            Method dump skipped, instruction units count: 238
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.image.ImageRenderer.drainOutput(long, long):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0085  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean feedInputBuffer(long r9) throws androidx.media3.decoder.DecoderException {
        /*
            Method dump skipped, instruction units count: 228
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.image.ImageRenderer.feedInputBuffer(long):boolean");
    }

    @Override // androidx.media3.exoplayer.Renderer, androidx.media3.exoplayer.RendererCapabilities
    public String getName() {
        return TAG;
    }

    @Override // androidx.media3.exoplayer.BaseRenderer, androidx.media3.exoplayer.PlayerMessage.Target
    public void handleMessage(int i, Object obj) throws ExoPlaybackException {
        if (i != 15) {
            return;
        }
        setImageOutput(obj instanceof ImageOutput ? (ImageOutput) obj : null);
    }

    @EnsuresNonNull({"decoder"})
    @RequiresNonNull({"inputFormat"})
    public final void initDecoder() throws ExoPlaybackException {
        if (!canCreateDecoderForFormat(this.inputFormat)) {
            throw createRendererException(new ImageDecoderException("Provided decoder factory can't create decoder for format."), this.inputFormat, false, 4005);
        }
        ImageDecoder imageDecoder = this.decoder;
        if (imageDecoder != null) {
            imageDecoder.release();
        }
        this.decoder = this.decoderFactory.createImageDecoder();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public boolean isEnded() {
        return this.outputStreamEnded;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public boolean isReady() {
        int i = this.firstFrameState;
        if (i != 3) {
            return i == 0 && this.readyToOutputTiles;
        }
        return true;
    }

    public final boolean isTileLastInGrid(TileInfo tileInfo) {
        Format format = this.inputFormat;
        Assertions.checkStateNotNull(format);
        if (format.tileCountHorizontal != -1 && this.inputFormat.tileCountVertical != -1) {
            int tileIndex = tileInfo.getTileIndex();
            Format format2 = this.inputFormat;
            Assertions.checkStateNotNull(format2);
            if (tileIndex != (format2.tileCountVertical * this.inputFormat.tileCountHorizontal) - 1) {
                return false;
            }
        }
        return true;
    }

    public final void lowerFirstFrameState(int i) {
        this.firstFrameState = Math.min(this.firstFrameState, i);
    }

    public final void maybeAdvanceTileInfo(long j, DecoderInputBuffer decoderInputBuffer) {
        boolean z = true;
        if (decoderInputBuffer.getFlag(4)) {
            this.readyToOutputTiles = true;
            return;
        }
        int i = this.currentTileIndex;
        long j2 = decoderInputBuffer.timeUs;
        this.nextTileInfo = new TileInfo(i, j2);
        this.currentTileIndex = i + 1;
        if (!this.readyToOutputTiles) {
            boolean z2 = j2 - 30000 <= j && j <= 30000 + j2;
            TileInfo tileInfo = this.tileInfo;
            boolean z3 = tileInfo != null && tileInfo.getPresentationTimeUs() <= j && j < j2;
            TileInfo tileInfo2 = this.nextTileInfo;
            Assertions.checkStateNotNull(tileInfo2);
            boolean zIsTileLastInGrid = isTileLastInGrid(tileInfo2);
            if (!z2 && !z3 && !zIsTileLastInGrid) {
                z = false;
            }
            this.readyToOutputTiles = z;
            if (z3 && !z2) {
                return;
            }
        }
        this.tileInfo = this.nextTileInfo;
        this.nextTileInfo = null;
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onDisabled() {
        this.inputFormat = null;
        this.outputStreamInfo = OutputStreamInfo.UNSET;
        this.pendingOutputStreamChanges.clear();
        releaseDecoderResources();
        this.imageOutput.getClass();
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onEnabled(boolean z, boolean z2) {
        this.firstFrameState = z2 ? 1 : 0;
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onPositionReset(long j, boolean z) throws ExoPlaybackException {
        lowerFirstFrameState(1);
        this.outputStreamEnded = false;
        this.inputStreamEnded = false;
        this.outputBitmap = null;
        this.tileInfo = null;
        this.nextTileInfo = null;
        this.readyToOutputTiles = false;
        this.inputBuffer = null;
        ImageDecoder imageDecoder = this.decoder;
        if (imageDecoder != null) {
            imageDecoder.flush();
        }
        this.pendingOutputStreamChanges.clear();
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onRelease() {
        releaseDecoderResources();
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onReset() {
        releaseDecoderResources();
        lowerFirstFrameState(1);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0023, code lost:
    
        if (r2 >= r5) goto L15;
     */
    @Override // androidx.media3.exoplayer.BaseRenderer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onStreamChanged(androidx.media3.common.Format[] r5, long r6, long r8, androidx.media3.exoplayer.source.MediaSource.MediaPeriodId r10) throws androidx.media3.exoplayer.ExoPlaybackException {
        /*
            r4 = this;
            androidx.media3.exoplayer.image.ImageRenderer$OutputStreamInfo r5 = r4.outputStreamInfo
            long r5 = r5.streamOffsetUs
            r0 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r7 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            if (r7 == 0) goto L33
            java.util.ArrayDeque<androidx.media3.exoplayer.image.ImageRenderer$OutputStreamInfo> r5 = r4.pendingOutputStreamChanges
            boolean r5 = r5.isEmpty()
            if (r5 == 0) goto L26
            long r5 = r4.largestQueuedPresentationTimeUs
            int r7 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            if (r7 == 0) goto L33
            long r2 = r4.lastProcessedOutputBufferTimeUs
            int r7 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r7 == 0) goto L26
            int r7 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r7 < 0) goto L26
            goto L33
        L26:
            java.util.ArrayDeque<androidx.media3.exoplayer.image.ImageRenderer$OutputStreamInfo> r5 = r4.pendingOutputStreamChanges
            androidx.media3.exoplayer.image.ImageRenderer$OutputStreamInfo r6 = new androidx.media3.exoplayer.image.ImageRenderer$OutputStreamInfo
            long r0 = r4.largestQueuedPresentationTimeUs
            r6.<init>(r0, r8)
            r5.add(r6)
            return
        L33:
            androidx.media3.exoplayer.image.ImageRenderer$OutputStreamInfo r5 = new androidx.media3.exoplayer.image.ImageRenderer$OutputStreamInfo
            r5.<init>(r0, r8)
            r4.outputStreamInfo = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.image.ImageRenderer.onStreamChanged(androidx.media3.common.Format[], long, long, androidx.media3.exoplayer.source.MediaSource$MediaPeriodId):void");
    }

    public boolean processOutputBuffer(long j, long j2, Bitmap bitmap, long j3) throws ExoPlaybackException {
        long j4 = j3 - j;
        if (!shouldForceRender() && j4 >= 30000) {
            return false;
        }
        ImageOutput imageOutput = this.imageOutput;
        long j5 = this.outputStreamInfo.streamOffsetUs;
        imageOutput.getClass();
        return true;
    }

    public final void releaseDecoderResources() {
        this.inputBuffer = null;
        this.decoderReinitializationState = 0;
        this.largestQueuedPresentationTimeUs = -9223372036854775807L;
        ImageDecoder imageDecoder = this.decoder;
        if (imageDecoder != null) {
            imageDecoder.release();
            this.decoder = null;
        }
    }

    @Override // androidx.media3.exoplayer.Renderer
    public void render(long j, long j2) throws ExoPlaybackException {
        if (this.outputStreamEnded) {
            return;
        }
        if (this.inputFormat == null) {
            FormatHolder formatHolder = getFormatHolder();
            this.flagsOnlyBuffer.clear();
            int source = readSource(formatHolder, this.flagsOnlyBuffer, 2);
            if (source != -5) {
                if (source == -4) {
                    Assertions.checkState(this.flagsOnlyBuffer.getFlag(4));
                    this.inputStreamEnded = true;
                    this.outputStreamEnded = true;
                    return;
                }
                return;
            }
            Format format = formatHolder.format;
            Assertions.checkStateNotNull(format);
            this.inputFormat = format;
            initDecoder();
        }
        try {
            TraceUtil.beginSection("drainAndFeedDecoder");
            while (drainOutput(j, j2)) {
            }
            while (feedInputBuffer(j)) {
            }
            TraceUtil.endSection();
        } catch (ImageDecoderException e) {
            throw createRendererException(e, null, false, 4003);
        }
    }

    public final void setImageOutput(ImageOutput imageOutput) {
        if (imageOutput == null) {
            imageOutput = ImageOutput.NO_OP;
        }
        this.imageOutput = imageOutput;
    }

    public final boolean shouldForceRender() {
        boolean z = this.state == 2;
        int i = this.firstFrameState;
        if (i == 0) {
            return z;
        }
        if (i == 1) {
            return true;
        }
        if (i == 3) {
            return false;
        }
        throw new IllegalStateException();
    }

    @Override // androidx.media3.exoplayer.RendererCapabilities
    public int supportsFormat(Format format) {
        return this.decoderFactory.supportsFormat(format);
    }
}
