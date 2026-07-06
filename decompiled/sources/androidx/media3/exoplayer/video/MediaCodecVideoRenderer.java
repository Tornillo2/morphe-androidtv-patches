package androidx.media3.exoplayer.video;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Pair;
import android.view.Display;
import android.view.Surface;
import androidx.annotation.CallSuper;
import androidx.annotation.DoNotInline;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.media3.common.Effect;
import androidx.media3.common.Format;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.VideoSize;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.MediaFormatUtil;
import androidx.media3.common.util.Size;
import androidx.media3.common.util.TraceUtil;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.DecoderCounters;
import androidx.media3.exoplayer.DecoderReuseEvaluation;
import androidx.media3.exoplayer.ExoPlaybackException;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.RendererConfiguration;
import androidx.media3.exoplayer.mediacodec.MediaCodecAdapter;
import androidx.media3.exoplayer.mediacodec.MediaCodecDecoderException;
import androidx.media3.exoplayer.mediacodec.MediaCodecInfo;
import androidx.media3.exoplayer.mediacodec.MediaCodecRenderer;
import androidx.media3.exoplayer.mediacodec.MediaCodecSelector;
import androidx.media3.exoplayer.mediacodec.MediaCodecUtil;
import androidx.media3.exoplayer.video.CompositingVideoSinkProvider;
import androidx.media3.exoplayer.video.VideoFrameReleaseControl;
import androidx.media3.exoplayer.video.VideoRendererEventListener;
import androidx.media3.exoplayer.video.VideoSink;
import com.amazon.avod.mpb.api.query.MediaCodecQuerier;
import com.amazon.livingroom.deviceproperties.VideoCapabilitiesProvider;
import com.google.android.gms.common.Scopes;
import com.google.common.collect.RegularImmutableList;
import com.google.common.util.concurrent.DirectExecutor;
import java.nio.ByteBuffer;
import java.util.List;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public class MediaCodecVideoRenderer extends MediaCodecRenderer implements VideoFrameReleaseControl.FrameTimingEvaluator {
    public static final int HEVC_MAX_INPUT_SIZE_THRESHOLD = 2097152;
    public static final float INITIAL_FORMAT_MAX_INPUT_SIZE_SCALE_FACTOR = 1.5f;
    public static final String KEY_CROP_BOTTOM = "crop-bottom";
    public static final String KEY_CROP_LEFT = "crop-left";
    public static final String KEY_CROP_RIGHT = "crop-right";
    public static final String KEY_CROP_TOP = "crop-top";
    public static final long MIN_EARLY_US_LATE_THRESHOLD = -30000;
    public static final long MIN_EARLY_US_VERY_LATE_THRESHOLD = -500000;
    public static final int[] STANDARD_LONG_EDGE_VIDEO_PX = {VideoCapabilitiesProvider.FULL_HD_WIDTH, 1600, 1440, MediaCodecQuerier.HD_MIN_WIDTH, 960, 854, 640, 540, 480};
    public static final String TAG = "MediaCodecVideoRenderer";
    public static final long TUNNELING_EOS_PRESENTATION_TIME_US = Long.MAX_VALUE;
    public static boolean deviceNeedsSetOutputSurfaceWorkaround;
    public static boolean evaluatedDeviceNeedsSetOutputSurfaceWorkaround;
    public int buffersInCodecCount;
    public boolean codecHandlesHdr10PlusOutOfBandMetadata;
    public CodecMaxValues codecMaxValues;
    public boolean codecNeedsSetOutputSurfaceWorkaround;
    public int consecutiveDroppedFrameCount;
    public final Context context;
    public VideoSize decodedVideoSize;
    public final boolean deviceNeedsNoPostProcessWorkaround;

    @Nullable
    public Surface displaySurface;
    public long droppedFrameAccumulationStartTimeMs;
    public int droppedFrames;
    public final VideoRendererEventListener.EventDispatcher eventDispatcher;

    @Nullable
    public VideoFrameMetadataListener frameMetadataListener;
    public boolean hasEffects;
    public boolean hasInitializedPlayback;
    public boolean haveReportedFirstFrameRenderedForCurrentSurface;
    public long lastFrameReleaseTimeNs;
    public final int maxDroppedFramesToNotify;

    @Nullable
    public Size outputResolution;

    @Nullable
    public PlaceholderSurface placeholderSurface;

    @Nullable
    public VideoSize reportedVideoSize;
    public int scalingMode;
    public long totalVideoFrameProcessingOffsetUs;
    public boolean tunneling;
    public int tunnelingAudioSessionId;

    @Nullable
    public OnFrameRenderedListenerV23 tunnelingOnFrameRenderedListener;
    public int videoFrameProcessingOffsetCount;
    public final VideoFrameReleaseControl videoFrameReleaseControl;
    public final VideoFrameReleaseControl.FrameReleaseInfo videoFrameReleaseInfo;

    @Nullable
    public VideoSink videoSink;
    public final VideoSinkProvider videoSinkProvider;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(26)
    public static final class Api26 {
        @DoNotInline
        public static boolean doesDisplaySupportDolbyVision(Context context) {
            DisplayManager displayManager = (DisplayManager) context.getSystemService("display");
            Display display = displayManager != null ? displayManager.getDisplay(0) : null;
            if (display != null && display.isHdr()) {
                for (int i : display.getHdrCapabilities().getSupportedHdrTypes()) {
                    if (i == 1) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class CodecMaxValues {
        public final int height;
        public final int inputSize;
        public final int width;

        public CodecMaxValues(int i, int i2, int i3) {
            this.width = i;
            this.height = i2;
            this.inputSize = i3;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(23)
    public final class OnFrameRenderedListenerV23 implements MediaCodecAdapter.OnFrameRenderedListener, Handler.Callback {
        public static final int HANDLE_FRAME_RENDERED = 0;
        public final Handler handler;

        public OnFrameRenderedListenerV23(MediaCodecAdapter mediaCodecAdapter) {
            Handler handlerCreateHandlerForCurrentLooper = Util.createHandlerForCurrentLooper(this);
            this.handler = handlerCreateHandlerForCurrentLooper;
            mediaCodecAdapter.setOnFrameRenderedListener(this, handlerCreateHandlerForCurrentLooper);
        }

        public final void handleFrameRendered(long j) {
            MediaCodecVideoRenderer mediaCodecVideoRenderer = MediaCodecVideoRenderer.this;
            if (this != mediaCodecVideoRenderer.tunnelingOnFrameRenderedListener || mediaCodecVideoRenderer.codec == null) {
                return;
            }
            if (j == Long.MAX_VALUE) {
                mediaCodecVideoRenderer.pendingOutputEndOfStream = true;
                return;
            }
            try {
                mediaCodecVideoRenderer.onProcessedTunneledBuffer(j);
            } catch (ExoPlaybackException e) {
                MediaCodecVideoRenderer.this.pendingPlaybackException = e;
            }
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what != 0) {
                return false;
            }
            handleFrameRendered(Util.toLong(message.arg1, message.arg2));
            return true;
        }

        @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter.OnFrameRenderedListener
        public void onFrameRendered(MediaCodecAdapter mediaCodecAdapter, long j, long j2) {
            if (Util.SDK_INT >= 30) {
                handleFrameRendered(j);
            } else {
                this.handler.sendMessageAtFrontOfQueue(Message.obtain(this.handler, 0, (int) (j >> 32), (int) j));
            }
        }
    }

    public MediaCodecVideoRenderer(Context context, MediaCodecSelector mediaCodecSelector) {
        this(context, mediaCodecSelector, 0L);
    }

    public static boolean codecAppliesRotation() {
        return Util.SDK_INT >= 21;
    }

    @RequiresApi(21)
    public static void configureTunnelingV21(MediaFormat mediaFormat, int i) {
        mediaFormat.setFeatureEnabled("tunneled-playback", true);
        mediaFormat.setInteger("audio-session-id", i);
    }

    public static boolean deviceNeedsNoPostProcessWorkaround() {
        return "NVIDIA".equals(Util.MANUFACTURER);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:6:0x001b  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0111  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean evaluateDeviceNeedsSetOutputSurfaceWorkaround() {
        /*
            Method dump skipped, instruction units count: 3190
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.video.MediaCodecVideoRenderer.evaluateDeviceNeedsSetOutputSurfaceWorkaround():boolean");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int getCodecMaxInputSize(androidx.media3.exoplayer.mediacodec.MediaCodecInfo r9, androidx.media3.common.Format r10) {
        /*
            Method dump skipped, instruction units count: 276
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.video.MediaCodecVideoRenderer.getCodecMaxInputSize(androidx.media3.exoplayer.mediacodec.MediaCodecInfo, androidx.media3.common.Format):int");
    }

    @Nullable
    public static Point getCodecMaxSize(MediaCodecInfo mediaCodecInfo, Format format) {
        int i = format.height;
        int i2 = format.width;
        boolean z = i > i2;
        int i3 = z ? i : i2;
        if (z) {
            i = i2;
        }
        float f = i / i3;
        for (int i4 : STANDARD_LONG_EDGE_VIDEO_PX) {
            int i5 = (int) (i4 * f);
            if (i4 <= i3 || i5 <= i) {
                break;
            }
            if (Util.SDK_INT >= 21) {
                int i6 = z ? i5 : i4;
                if (!z) {
                    i4 = i5;
                }
                Point pointAlignVideoSizeV21 = mediaCodecInfo.alignVideoSizeV21(i6, i4);
                float f2 = format.frameRate;
                if (pointAlignVideoSizeV21 != null && mediaCodecInfo.isVideoSizeAndRateSupportedV21(pointAlignVideoSizeV21.x, pointAlignVideoSizeV21.y, f2)) {
                    return pointAlignVideoSizeV21;
                }
            } else {
                try {
                    int iCeilDivide = Util.ceilDivide(i4, 16) * 16;
                    int iCeilDivide2 = Util.ceilDivide(i5, 16) * 16;
                    if (iCeilDivide * iCeilDivide2 <= MediaCodecUtil.maxH264DecodableFrameSize()) {
                        int i7 = z ? iCeilDivide2 : iCeilDivide;
                        if (!z) {
                            iCeilDivide = iCeilDivide2;
                        }
                        return new Point(i7, iCeilDivide);
                    }
                } catch (MediaCodecUtil.DecoderQueryException unused) {
                }
            }
        }
        return null;
    }

    public static int getMaxInputSize(MediaCodecInfo mediaCodecInfo, Format format) {
        if (format.maxInputSize == -1) {
            return getCodecMaxInputSize(mediaCodecInfo, format);
        }
        int size = format.initializationData.size();
        int length = 0;
        for (int i = 0; i < size; i++) {
            length += format.initializationData.get(i).length;
        }
        return format.maxInputSize + length;
    }

    public static int getMaxSampleSize(int i, int i2) {
        return (i * 3) / (i2 * 2);
    }

    @RequiresApi(29)
    public static void setHdr10PlusInfoV29(MediaCodecAdapter mediaCodecAdapter, byte[] bArr) {
        Bundle bundle = new Bundle();
        bundle.putByteArray("hdr10-plus-info", bArr);
        mediaCodecAdapter.setParameters(bundle);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3, types: [androidx.media3.exoplayer.video.VideoFrameReleaseControl] */
    /* JADX WARN: Type inference failed for: r0v8, types: [androidx.media3.exoplayer.video.VideoSinkProvider] */
    /* JADX WARN: Type inference failed for: r5v0, types: [androidx.media3.exoplayer.BaseRenderer, androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.video.MediaCodecVideoRenderer] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v3, types: [android.view.Surface] */
    /* JADX WARN: Type inference failed for: r6v8, types: [androidx.media3.exoplayer.video.PlaceholderSurface] */
    /* JADX WARN: Type inference failed for: r6v9 */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private void setOutput(@Nullable Object obj) throws ExoPlaybackException {
        ?? NewInstanceV17 = obj instanceof Surface ? (Surface) obj : 0;
        if (NewInstanceV17 == 0) {
            PlaceholderSurface placeholderSurface = this.placeholderSurface;
            if (placeholderSurface != null) {
                NewInstanceV17 = placeholderSurface;
            } else {
                MediaCodecInfo mediaCodecInfo = this.codecInfo;
                if (mediaCodecInfo != null && shouldUsePlaceholderSurface(mediaCodecInfo)) {
                    NewInstanceV17 = PlaceholderSurface.newInstanceV17(this.context, mediaCodecInfo.secure);
                    this.placeholderSurface = NewInstanceV17;
                }
            }
        }
        if (this.displaySurface == NewInstanceV17) {
            if (NewInstanceV17 == 0 || NewInstanceV17 == this.placeholderSurface) {
                return;
            }
            maybeRenotifyVideoSizeChanged();
            maybeRenotifyRenderedFirstFrame();
            return;
        }
        this.displaySurface = NewInstanceV17;
        this.videoFrameReleaseControl.setOutputSurface(NewInstanceV17);
        this.haveReportedFirstFrameRenderedForCurrentSurface = false;
        int i = this.state;
        MediaCodecAdapter mediaCodecAdapter = this.codec;
        if (mediaCodecAdapter != null && !this.videoSinkProvider.isInitialized()) {
            if (Util.SDK_INT < 23 || NewInstanceV17 == 0 || this.codecNeedsSetOutputSurfaceWorkaround) {
                releaseCodec();
                maybeInitCodecOrBypass();
            } else {
                setOutputSurfaceV23(mediaCodecAdapter, NewInstanceV17);
            }
        }
        if (NewInstanceV17 == 0 || NewInstanceV17 == this.placeholderSurface) {
            this.reportedVideoSize = null;
            if (this.videoSinkProvider.isInitialized()) {
                this.videoSinkProvider.clearOutputSurfaceInfo();
            }
        } else {
            maybeRenotifyVideoSizeChanged();
            if (i == 2) {
                this.videoFrameReleaseControl.join();
            }
            if (this.videoSinkProvider.isInitialized()) {
                this.videoSinkProvider.setOutputSurfaceInfo(NewInstanceV17, Size.UNKNOWN);
            }
        }
        maybeSetupTunnelingForFirstFrame();
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public DecoderReuseEvaluation canReuseCodec(MediaCodecInfo mediaCodecInfo, Format format, Format format2) {
        DecoderReuseEvaluation decoderReuseEvaluationCanReuseCodec = mediaCodecInfo.canReuseCodec(format, format2);
        int i = decoderReuseEvaluationCanReuseCodec.discardReasons;
        CodecMaxValues codecMaxValues = this.codecMaxValues;
        codecMaxValues.getClass();
        if (format2.width > codecMaxValues.width || format2.height > codecMaxValues.height) {
            i |= 256;
        }
        if (getMaxInputSize(mediaCodecInfo, format2) > codecMaxValues.inputSize) {
            i |= 64;
        }
        int i2 = i;
        return new DecoderReuseEvaluation(mediaCodecInfo.name, format, format2, i2 != 0 ? 0 : decoderReuseEvaluationCanReuseCodec.result, i2);
    }

    public boolean codecNeedsSetOutputSurfaceWorkaround(String str) {
        if (str.startsWith("OMX.google")) {
            return false;
        }
        synchronized (MediaCodecVideoRenderer.class) {
            try {
                if (!evaluatedDeviceNeedsSetOutputSurfaceWorkaround) {
                    deviceNeedsSetOutputSurfaceWorkaround = evaluateDeviceNeedsSetOutputSurfaceWorkaround();
                    evaluatedDeviceNeedsSetOutputSurfaceWorkaround = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return deviceNeedsSetOutputSurfaceWorkaround;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public MediaCodecDecoderException createDecoderException(Throwable th, @Nullable MediaCodecInfo mediaCodecInfo) {
        return new MediaCodecVideoDecoderException(th, mediaCodecInfo, this.displaySurface);
    }

    public void dropOutputBuffer(MediaCodecAdapter mediaCodecAdapter, int i, long j) {
        TraceUtil.beginSection("dropVideoBuffer");
        mediaCodecAdapter.releaseOutputBuffer(i, false);
        TraceUtil.endSection();
        updateDroppedBufferCounters(0, 1);
    }

    @Override // androidx.media3.exoplayer.BaseRenderer, androidx.media3.exoplayer.Renderer
    public void enableMayRenderStartOfStream() {
        this.videoFrameReleaseControl.getClass();
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public int getCodecBufferFlags(DecoderInputBuffer decoderInputBuffer) {
        return (Util.SDK_INT < 34 || !this.tunneling || decoderInputBuffer.timeUs >= this.lastResetPositionUs) ? 0 : 32;
    }

    public CodecMaxValues getCodecMaxValues(MediaCodecInfo mediaCodecInfo, Format format, Format[] formatArr) {
        int codecMaxInputSize;
        int iMax = format.width;
        int iMax2 = format.height;
        int maxInputSize = getMaxInputSize(mediaCodecInfo, format);
        if (formatArr.length == 1) {
            if (maxInputSize != -1 && (codecMaxInputSize = getCodecMaxInputSize(mediaCodecInfo, format)) != -1) {
                maxInputSize = Math.min((int) (maxInputSize * 1.5f), codecMaxInputSize);
            }
            return new CodecMaxValues(iMax, iMax2, maxInputSize);
        }
        int length = formatArr.length;
        boolean z = false;
        for (int i = 0; i < length; i++) {
            Format format2 = formatArr[i];
            if (format.colorInfo != null && format2.colorInfo == null) {
                Format.Builder builder = new Format.Builder(format2);
                builder.colorInfo = format.colorInfo;
                format2 = new Format(builder);
            }
            if (mediaCodecInfo.canReuseCodec(format, format2).result != 0) {
                int i2 = format2.width;
                z |= i2 == -1 || format2.height == -1;
                iMax = Math.max(iMax, i2);
                iMax2 = Math.max(iMax2, format2.height);
                maxInputSize = Math.max(maxInputSize, getMaxInputSize(mediaCodecInfo, format2));
            }
        }
        if (z) {
            Log.w("MediaCodecVideoRenderer", "Resolutions unknown. Codec max resolution: " + iMax + "x" + iMax2);
            Point codecMaxSize = getCodecMaxSize(mediaCodecInfo, format);
            if (codecMaxSize != null) {
                iMax = Math.max(iMax, codecMaxSize.x);
                iMax2 = Math.max(iMax2, codecMaxSize.y);
                Format.Builder builder2 = new Format.Builder(format);
                builder2.width = iMax;
                builder2.height = iMax2;
                maxInputSize = Math.max(maxInputSize, getCodecMaxInputSize(mediaCodecInfo, new Format(builder2)));
                Log.w("MediaCodecVideoRenderer", "Codec max resolution adjusted to: " + iMax + "x" + iMax2);
            }
        }
        return new CodecMaxValues(iMax, iMax2, maxInputSize);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public boolean getCodecNeedsEosPropagation() {
        return this.tunneling && Util.SDK_INT < 23;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public float getCodecOperatingRateV23(float f, Format format, Format[] formatArr) {
        float fMax = -1.0f;
        for (Format format2 : formatArr) {
            float f2 = format2.frameRate;
            if (f2 != -1.0f) {
                fMax = Math.max(fMax, f2);
            }
        }
        if (fMax == -1.0f) {
            return -1.0f;
        }
        return fMax * f;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public List<MediaCodecInfo> getDecoderInfos(MediaCodecSelector mediaCodecSelector, Format format, boolean z) throws MediaCodecUtil.DecoderQueryException {
        return MediaCodecUtil.getDecoderInfosSortedByFormatSupport(getDecoderInfos(this.context, mediaCodecSelector, format, z, this.tunneling), format);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    @TargetApi(17)
    public MediaCodecAdapter.Configuration getMediaCodecConfiguration(MediaCodecInfo mediaCodecInfo, Format format, @Nullable MediaCrypto mediaCrypto, float f) {
        PlaceholderSurface placeholderSurface = this.placeholderSurface;
        if (placeholderSurface != null && placeholderSurface.secure != mediaCodecInfo.secure) {
            releasePlaceholderSurface();
        }
        String str = mediaCodecInfo.codecMimeType;
        Format[] formatArr = this.streamFormats;
        formatArr.getClass();
        CodecMaxValues codecMaxValues = getCodecMaxValues(mediaCodecInfo, format, formatArr);
        this.codecMaxValues = codecMaxValues;
        MediaFormat mediaFormat = getMediaFormat(format, str, codecMaxValues, f, this.deviceNeedsNoPostProcessWorkaround, this.tunneling ? this.tunnelingAudioSessionId : 0);
        if (this.displaySurface == null) {
            if (!shouldUsePlaceholderSurface(mediaCodecInfo)) {
                throw new IllegalStateException();
            }
            if (this.placeholderSurface == null) {
                this.placeholderSurface = PlaceholderSurface.newInstanceV17(this.context, mediaCodecInfo.secure);
            }
            this.displaySurface = this.placeholderSurface;
        }
        maybeSetKeyAllowFrameDrop(mediaFormat);
        VideoSink videoSink = this.videoSink;
        return MediaCodecAdapter.Configuration.createForVideoDecoding(mediaCodecInfo, mediaFormat, format, videoSink != null ? videoSink.getInputSurface() : this.displaySurface, mediaCrypto);
    }

    @SuppressLint({"InlinedApi"})
    @TargetApi(21)
    public MediaFormat getMediaFormat(Format format, String str, CodecMaxValues codecMaxValues, float f, boolean z, int i) {
        Pair<Integer, Integer> codecProfileAndLevel;
        MediaFormat mediaFormat = new MediaFormat();
        mediaFormat.setString("mime", str);
        mediaFormat.setInteger("width", format.width);
        mediaFormat.setInteger("height", format.height);
        MediaFormatUtil.setCsdBuffers(mediaFormat, format.initializationData);
        MediaFormatUtil.maybeSetFloat(mediaFormat, "frame-rate", format.frameRate);
        MediaFormatUtil.maybeSetInteger(mediaFormat, "rotation-degrees", format.rotationDegrees);
        MediaFormatUtil.maybeSetColorInfo(mediaFormat, format.colorInfo);
        if ("video/dolby-vision".equals(format.sampleMimeType) && (codecProfileAndLevel = MediaCodecUtil.getCodecProfileAndLevel(format)) != null) {
            MediaFormatUtil.maybeSetInteger(mediaFormat, Scopes.PROFILE, ((Integer) codecProfileAndLevel.first).intValue());
        }
        mediaFormat.setInteger("max-width", codecMaxValues.width);
        mediaFormat.setInteger("max-height", codecMaxValues.height);
        MediaFormatUtil.maybeSetInteger(mediaFormat, "max-input-size", codecMaxValues.inputSize);
        if (Util.SDK_INT >= 23) {
            mediaFormat.setInteger("priority", 0);
            if (f != -1.0f) {
                mediaFormat.setFloat("operating-rate", f);
            }
        }
        if (z) {
            mediaFormat.setInteger("no-post-process", 1);
            mediaFormat.setInteger("auto-frc", 0);
        }
        if (i != 0) {
            configureTunnelingV21(mediaFormat, i);
        }
        mediaFormat.setInteger("push-blank-buffers-on-shutdown", 1);
        return mediaFormat;
    }

    @Override // androidx.media3.exoplayer.Renderer, androidx.media3.exoplayer.RendererCapabilities
    public String getName() {
        return "MediaCodecVideoRenderer";
    }

    @Nullable
    public Surface getSurface() {
        return this.displaySurface;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    @TargetApi(29)
    public void handleInputBufferSupplementalData(DecoderInputBuffer decoderInputBuffer) throws ExoPlaybackException {
        if (this.codecHandlesHdr10PlusOutOfBandMetadata) {
            ByteBuffer byteBuffer = decoderInputBuffer.supplementalData;
            byteBuffer.getClass();
            if (byteBuffer.remaining() >= 7) {
                byte b = byteBuffer.get();
                short s = byteBuffer.getShort();
                short s2 = byteBuffer.getShort();
                byte b2 = byteBuffer.get();
                byte b3 = byteBuffer.get();
                byteBuffer.position(0);
                if (b == -75 && s == 60 && s2 == 1 && b2 == 4) {
                    if (b3 == 0 || b3 == 1) {
                        byte[] bArr = new byte[byteBuffer.remaining()];
                        byteBuffer.get(bArr);
                        byteBuffer.position(0);
                        MediaCodecAdapter mediaCodecAdapter = this.codec;
                        mediaCodecAdapter.getClass();
                        setHdr10PlusInfoV29(mediaCodecAdapter, bArr);
                    }
                }
            }
        }
    }

    @Override // androidx.media3.exoplayer.BaseRenderer, androidx.media3.exoplayer.PlayerMessage.Target
    public void handleMessage(int i, @Nullable Object obj) throws ExoPlaybackException {
        Surface surface;
        if (i == 1) {
            setOutput(obj);
            return;
        }
        if (i == 7) {
            obj.getClass();
            VideoFrameMetadataListener videoFrameMetadataListener = (VideoFrameMetadataListener) obj;
            this.frameMetadataListener = videoFrameMetadataListener;
            this.videoSinkProvider.setVideoFrameMetadataListener(videoFrameMetadataListener);
            return;
        }
        if (i == 10) {
            obj.getClass();
            int iIntValue = ((Integer) obj).intValue();
            if (this.tunnelingAudioSessionId != iIntValue) {
                this.tunnelingAudioSessionId = iIntValue;
                if (this.tunneling) {
                    releaseCodec();
                    return;
                }
                return;
            }
            return;
        }
        if (i == 4) {
            obj.getClass();
            int iIntValue2 = ((Integer) obj).intValue();
            this.scalingMode = iIntValue2;
            MediaCodecAdapter mediaCodecAdapter = this.codec;
            if (mediaCodecAdapter != null) {
                mediaCodecAdapter.setVideoScalingMode(iIntValue2);
                return;
            }
            return;
        }
        if (i == 5) {
            VideoFrameReleaseControl videoFrameReleaseControl = this.videoFrameReleaseControl;
            obj.getClass();
            videoFrameReleaseControl.setChangeFrameRateStrategy(((Integer) obj).intValue());
            return;
        }
        if (i == 13) {
            obj.getClass();
            setVideoEffects((List) obj);
            return;
        }
        if (i != 14) {
            return;
        }
        obj.getClass();
        this.outputResolution = (Size) obj;
        if (this.videoSinkProvider.isInitialized()) {
            Size size = this.outputResolution;
            size.getClass();
            if (size.width != 0) {
                Size size2 = this.outputResolution;
                size2.getClass();
                if (size2.height == 0 || (surface = this.displaySurface) == null) {
                    return;
                }
                VideoSinkProvider videoSinkProvider = this.videoSinkProvider;
                Size size3 = this.outputResolution;
                size3.getClass();
                videoSinkProvider.setOutputSurfaceInfo(surface, size3);
            }
        }
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.Renderer
    public boolean isEnded() {
        if (!this.outputStreamEnded) {
            return false;
        }
        VideoSink videoSink = this.videoSink;
        return videoSink == null || videoSink.isEnded();
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.Renderer
    public boolean isReady() {
        PlaceholderSurface placeholderSurface;
        VideoSink videoSink;
        boolean z = super.isReady() && ((videoSink = this.videoSink) == null || videoSink.isReady());
        if (z && (((placeholderSurface = this.placeholderSurface) != null && this.displaySurface == placeholderSurface) || this.codec == null || this.tunneling)) {
            return true;
        }
        return this.videoFrameReleaseControl.isReady(z);
    }

    public boolean maybeDropBuffersToKeyframe(long j, boolean z) throws ExoPlaybackException {
        int iSkipSource = skipSource(j);
        if (iSkipSource == 0) {
            return false;
        }
        if (z) {
            DecoderCounters decoderCounters = this.decoderCounters;
            decoderCounters.skippedInputBufferCount += iSkipSource;
            decoderCounters.skippedOutputBufferCount += this.buffersInCodecCount;
        } else {
            this.decoderCounters.droppedToKeyframeCount++;
            updateDroppedBufferCounters(iSkipSource, this.buffersInCodecCount);
        }
        flushOrReinitializeCodec();
        VideoSink videoSink = this.videoSink;
        if (videoSink != null) {
            videoSink.flush();
        }
        return true;
    }

    public final void maybeNotifyDroppedFrames() {
        if (this.droppedFrames > 0) {
            Clock clock = this.clock;
            clock.getClass();
            long jElapsedRealtime = clock.elapsedRealtime();
            this.eventDispatcher.droppedFrames(this.droppedFrames, jElapsedRealtime - this.droppedFrameAccumulationStartTimeMs);
            this.droppedFrames = 0;
            this.droppedFrameAccumulationStartTimeMs = jElapsedRealtime;
        }
    }

    public final void maybeNotifyRenderedFirstFrame() {
        if (!this.videoFrameReleaseControl.onFrameReleasedIsFirstFrame() || this.displaySurface == null) {
            return;
        }
        notifyRenderedFirstFrame();
    }

    public final void maybeNotifyVideoFrameProcessingOffset() {
        int i = this.videoFrameProcessingOffsetCount;
        if (i != 0) {
            this.eventDispatcher.reportVideoFrameProcessingOffset(this.totalVideoFrameProcessingOffsetUs, i);
            this.totalVideoFrameProcessingOffsetUs = 0L;
            this.videoFrameProcessingOffsetCount = 0;
        }
    }

    public final void maybeNotifyVideoSizeChanged(VideoSize videoSize) {
        if (videoSize.equals(VideoSize.UNKNOWN) || videoSize.equals(this.reportedVideoSize)) {
            return;
        }
        this.reportedVideoSize = videoSize;
        this.eventDispatcher.videoSizeChanged(videoSize);
    }

    public final boolean maybeReleaseFrame(MediaCodecAdapter mediaCodecAdapter, int i, long j, Format format) {
        long releaseTimeNs = this.videoFrameReleaseInfo.getReleaseTimeNs();
        long earlyUs = this.videoFrameReleaseInfo.getEarlyUs();
        if (Util.SDK_INT >= 21) {
            if (releaseTimeNs == this.lastFrameReleaseTimeNs) {
                skipOutputBuffer(mediaCodecAdapter, i, j);
            } else {
                notifyFrameMetadataListener(j, releaseTimeNs, format);
                renderOutputBufferV21(mediaCodecAdapter, i, j, releaseTimeNs);
                releaseTimeNs = releaseTimeNs;
            }
            updateVideoFrameProcessingOffsetCounters(earlyUs);
            this.lastFrameReleaseTimeNs = releaseTimeNs;
            return true;
        }
        if (earlyUs >= 30000) {
            return false;
        }
        if (earlyUs > 11000) {
            try {
                Thread.sleep((earlyUs - 10000) / 1000);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        notifyFrameMetadataListener(j, releaseTimeNs, format);
        renderOutputBuffer(mediaCodecAdapter, i, j);
        updateVideoFrameProcessingOffsetCounters(earlyUs);
        return true;
    }

    public final void maybeRenotifyRenderedFirstFrame() {
        Surface surface = this.displaySurface;
        if (surface == null || !this.haveReportedFirstFrameRenderedForCurrentSurface) {
            return;
        }
        this.eventDispatcher.renderedFirstFrame(surface);
    }

    public final void maybeRenotifyVideoSizeChanged() {
        VideoSize videoSize = this.reportedVideoSize;
        if (videoSize != null) {
            this.eventDispatcher.videoSizeChanged(videoSize);
        }
    }

    public final void maybeSetKeyAllowFrameDrop(MediaFormat mediaFormat) {
        VideoSink videoSink = this.videoSink;
        if (videoSink == null || videoSink.isFrameDropAllowedOnInput()) {
            return;
        }
        mediaFormat.setInteger("allow-frame-drop", 0);
    }

    public final void maybeSetupTunnelingForFirstFrame() {
        int i;
        MediaCodecAdapter mediaCodecAdapter;
        if (!this.tunneling || (i = Util.SDK_INT) < 23 || (mediaCodecAdapter = this.codec) == null) {
            return;
        }
        this.tunnelingOnFrameRenderedListener = new OnFrameRenderedListenerV23(mediaCodecAdapter);
        if (i >= 33) {
            Bundle bundle = new Bundle();
            bundle.putInt("tunnel-peek", 1);
            mediaCodecAdapter.setParameters(bundle);
        }
    }

    public final void notifyFrameMetadataListener(long j, long j2, Format format) {
        VideoFrameMetadataListener videoFrameMetadataListener = this.frameMetadataListener;
        if (videoFrameMetadataListener != null) {
            videoFrameMetadataListener.onVideoFrameAboutToBeRendered(j, j2, format, this.codecOutputMediaFormat);
        }
    }

    @RequiresNonNull({"displaySurface"})
    public final void notifyRenderedFirstFrame() {
        this.eventDispatcher.renderedFirstFrame(this.displaySurface);
        this.haveReportedFirstFrameRenderedForCurrentSurface = true;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public void onCodecError(Exception exc) {
        Log.e("MediaCodecVideoRenderer", "Video codec error", exc);
        this.eventDispatcher.videoCodecError(exc);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public void onCodecInitialized(String str, MediaCodecAdapter.Configuration configuration, long j, long j2) {
        this.eventDispatcher.decoderInitialized(str, j, j2);
        this.codecNeedsSetOutputSurfaceWorkaround = codecNeedsSetOutputSurfaceWorkaround(str);
        MediaCodecInfo mediaCodecInfo = this.codecInfo;
        mediaCodecInfo.getClass();
        this.codecHandlesHdr10PlusOutOfBandMetadata = mediaCodecInfo.isHdr10PlusOutOfBandMetadataSupported();
        maybeSetupTunnelingForFirstFrame();
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public void onCodecReleased(String str) {
        this.eventDispatcher.decoderReleased(str);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.BaseRenderer
    public void onDisabled() {
        this.reportedVideoSize = null;
        this.videoFrameReleaseControl.lowerFirstFrameState(0);
        maybeSetupTunnelingForFirstFrame();
        this.haveReportedFirstFrameRenderedForCurrentSurface = false;
        this.tunnelingOnFrameRenderedListener = null;
        try {
            super.onDisabled();
        } finally {
            this.eventDispatcher.disabled(this.decoderCounters);
            this.eventDispatcher.videoSizeChanged(VideoSize.UNKNOWN);
        }
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.BaseRenderer
    public void onEnabled(boolean z, boolean z2) throws ExoPlaybackException {
        super.onEnabled(z, z2);
        RendererConfiguration rendererConfiguration = this.configuration;
        rendererConfiguration.getClass();
        boolean z3 = rendererConfiguration.tunneling;
        Assertions.checkState((z3 && this.tunnelingAudioSessionId == 0) ? false : true);
        if (this.tunneling != z3) {
            this.tunneling = z3;
            releaseCodec();
        }
        this.eventDispatcher.enabled(this.decoderCounters);
        this.videoFrameReleaseControl.firstFrameState = z2 ? 1 : 0;
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onInit() {
        Clock clock = this.clock;
        clock.getClass();
        this.videoFrameReleaseControl.clock = clock;
        this.videoSinkProvider.setClock(clock);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    @Nullable
    public DecoderReuseEvaluation onInputFormatChanged(FormatHolder formatHolder) throws ExoPlaybackException {
        DecoderReuseEvaluation decoderReuseEvaluationOnInputFormatChanged = super.onInputFormatChanged(formatHolder);
        VideoRendererEventListener.EventDispatcher eventDispatcher = this.eventDispatcher;
        Format format = formatHolder.format;
        format.getClass();
        eventDispatcher.inputFormatChanged(format, decoderReuseEvaluationOnInputFormatChanged);
        return decoderReuseEvaluationOnInputFormatChanged;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public void onOutputFormatChanged(Format format, @Nullable MediaFormat mediaFormat) {
        int integer;
        int integer2;
        MediaCodecAdapter mediaCodecAdapter = this.codec;
        if (mediaCodecAdapter != null) {
            mediaCodecAdapter.setVideoScalingMode(this.scalingMode);
        }
        int i = 0;
        if (this.tunneling) {
            integer = format.width;
            integer2 = format.height;
        } else {
            mediaFormat.getClass();
            boolean z = mediaFormat.containsKey("crop-right") && mediaFormat.containsKey("crop-left") && mediaFormat.containsKey("crop-bottom") && mediaFormat.containsKey("crop-top");
            integer = z ? (mediaFormat.getInteger("crop-right") - mediaFormat.getInteger("crop-left")) + 1 : mediaFormat.getInteger("width");
            integer2 = z ? (mediaFormat.getInteger("crop-bottom") - mediaFormat.getInteger("crop-top")) + 1 : mediaFormat.getInteger("height");
        }
        float f = format.pixelWidthHeightRatio;
        if (codecAppliesRotation()) {
            int i2 = format.rotationDegrees;
            if (i2 == 90 || i2 == 270) {
                f = 1.0f / f;
                int i3 = integer2;
                integer2 = integer;
                integer = i3;
            }
        } else if (this.videoSink == null) {
            i = format.rotationDegrees;
        }
        this.decodedVideoSize = new VideoSize(integer, integer2, i, f);
        this.videoFrameReleaseControl.setFrameRate(format.frameRate);
        VideoSink videoSink = this.videoSink;
        if (videoSink == null || mediaFormat == null) {
            return;
        }
        videoSink.getClass();
        Format.Builder builder = new Format.Builder(format);
        builder.width = integer;
        builder.height = integer2;
        builder.rotationDegrees = i;
        builder.pixelWidthHeightRatio = f;
        videoSink.registerInputStream(1, new Format(builder));
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.BaseRenderer
    public void onPositionReset(long j, boolean z) throws ExoPlaybackException {
        VideoSink videoSink = this.videoSink;
        if (videoSink != null) {
            videoSink.flush();
        }
        super.onPositionReset(j, z);
        if (this.videoSinkProvider.isInitialized()) {
            this.videoSinkProvider.setStreamOffsetUs(this.outputStreamInfo.streamOffsetUs);
        }
        this.videoFrameReleaseControl.reset();
        if (z) {
            this.videoFrameReleaseControl.join();
        }
        maybeSetupTunnelingForFirstFrame();
        this.consecutiveDroppedFrameCount = 0;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    @CallSuper
    public void onProcessedOutputBuffer(long j) {
        super.onProcessedOutputBuffer(j);
        if (this.tunneling) {
            return;
        }
        this.buffersInCodecCount--;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public void onProcessedStreamChange() {
        this.videoFrameReleaseControl.lowerFirstFrameState(2);
        maybeSetupTunnelingForFirstFrame();
        if (this.videoSinkProvider.isInitialized()) {
            this.videoSinkProvider.setStreamOffsetUs(this.outputStreamInfo.streamOffsetUs);
        }
    }

    public void onProcessedTunneledBuffer(long j) throws ExoPlaybackException {
        updateOutputFormatForTime(j);
        maybeNotifyVideoSizeChanged(this.decodedVideoSize);
        this.decoderCounters.renderedOutputBufferCount++;
        maybeNotifyRenderedFirstFrame();
        onProcessedOutputBuffer(j);
    }

    public final void onProcessedTunneledEndOfStream() {
        this.pendingOutputEndOfStream = true;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    @CallSuper
    public void onQueueInputBuffer(DecoderInputBuffer decoderInputBuffer) throws ExoPlaybackException {
        boolean z = this.tunneling;
        if (!z) {
            this.buffersInCodecCount++;
        }
        if (Util.SDK_INT >= 23 || !z) {
            return;
        }
        onProcessedTunneledBuffer(decoderInputBuffer.timeUs);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    @CallSuper
    public void onReadyToInitializeCodec(Format format) throws ExoPlaybackException {
        Size size;
        if (this.hasEffects && !this.hasInitializedPlayback && !this.videoSinkProvider.isInitialized()) {
            try {
                this.videoSinkProvider.initialize(format);
                this.videoSinkProvider.setStreamOffsetUs(this.outputStreamInfo.streamOffsetUs);
                VideoFrameMetadataListener videoFrameMetadataListener = this.frameMetadataListener;
                if (videoFrameMetadataListener != null) {
                    this.videoSinkProvider.setVideoFrameMetadataListener(videoFrameMetadataListener);
                }
                Surface surface = this.displaySurface;
                if (surface != null && (size = this.outputResolution) != null) {
                    this.videoSinkProvider.setOutputSurfaceInfo(surface, size);
                }
            } catch (VideoSink.VideoSinkException e) {
                throw createRendererException(e, format, false, 7000);
            }
        }
        if (this.videoSink == null && this.videoSinkProvider.isInitialized()) {
            VideoSink sink = this.videoSinkProvider.getSink();
            this.videoSink = sink;
            sink.setListener(new VideoSink.Listener() { // from class: androidx.media3.exoplayer.video.MediaCodecVideoRenderer.1
                @Override // androidx.media3.exoplayer.video.VideoSink.Listener
                public void onError(VideoSink videoSink, VideoSink.VideoSinkException videoSinkException) {
                    MediaCodecVideoRenderer mediaCodecVideoRenderer = MediaCodecVideoRenderer.this;
                    mediaCodecVideoRenderer.pendingPlaybackException = mediaCodecVideoRenderer.createRendererException(videoSinkException, videoSinkException.format, false, PlaybackException.ERROR_CODE_VIDEO_FRAME_PROCESSING_FAILED);
                }

                @Override // androidx.media3.exoplayer.video.VideoSink.Listener
                public void onFirstFrameRendered(VideoSink videoSink) {
                    Assertions.checkStateNotNull(MediaCodecVideoRenderer.this.displaySurface);
                    MediaCodecVideoRenderer.this.notifyRenderedFirstFrame();
                }

                @Override // androidx.media3.exoplayer.video.VideoSink.Listener
                public void onFrameDropped(VideoSink videoSink) {
                    MediaCodecVideoRenderer.this.updateDroppedBufferCounters(0, 1);
                }

                @Override // androidx.media3.exoplayer.video.VideoSink.Listener
                public void onVideoSizeChanged(VideoSink videoSink, VideoSize videoSize) {
                }
            }, DirectExecutor.INSTANCE);
        }
        this.hasInitializedPlayback = true;
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onRelease() {
        if (this.videoSinkProvider.isInitialized()) {
            this.videoSinkProvider.release();
        }
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.BaseRenderer
    @TargetApi(17)
    public void onReset() {
        try {
            super.onReset();
        } finally {
            this.hasInitializedPlayback = false;
            if (this.placeholderSurface != null) {
                releasePlaceholderSurface();
            }
        }
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.BaseRenderer
    public void onStarted() {
        this.droppedFrames = 0;
        Clock clock = this.clock;
        clock.getClass();
        this.droppedFrameAccumulationStartTimeMs = clock.elapsedRealtime();
        this.totalVideoFrameProcessingOffsetUs = 0L;
        this.videoFrameProcessingOffsetCount = 0;
        this.videoFrameReleaseControl.onStarted();
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.BaseRenderer
    public void onStopped() {
        maybeNotifyDroppedFrames();
        maybeNotifyVideoFrameProcessingOffset();
        this.videoFrameReleaseControl.onStopped();
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public boolean processOutputBuffer(long j, long j2, @Nullable MediaCodecAdapter mediaCodecAdapter, @Nullable ByteBuffer byteBuffer, int i, int i2, int i3, long j3, boolean z, boolean z2, Format format) throws ExoPlaybackException {
        mediaCodecAdapter.getClass();
        MediaCodecRenderer.OutputStreamInfo outputStreamInfo = this.outputStreamInfo;
        long j4 = j3 - outputStreamInfo.streamOffsetUs;
        int frameReleaseAction = this.videoFrameReleaseControl.getFrameReleaseAction(j3, j, j2, outputStreamInfo.startPositionUs, z2, this.videoFrameReleaseInfo);
        if (z && !z2) {
            skipOutputBuffer(mediaCodecAdapter, i, j4);
            return true;
        }
        if (this.displaySurface != this.placeholderSurface) {
            VideoSink videoSink = this.videoSink;
            if (videoSink != null) {
                try {
                    videoSink.render(j, j2);
                    long jRegisterInputFrame = this.videoSink.registerInputFrame(j4, z2);
                    if (jRegisterInputFrame != -9223372036854775807L) {
                        renderOutputBuffer(mediaCodecAdapter, i, j4, jRegisterInputFrame);
                        return true;
                    }
                } catch (VideoSink.VideoSinkException e) {
                    throw createRendererException(e, e.format, false, PlaybackException.ERROR_CODE_VIDEO_FRAME_PROCESSING_FAILED);
                }
            } else {
                if (frameReleaseAction == 0) {
                    Clock clock = this.clock;
                    clock.getClass();
                    long jNanoTime = clock.nanoTime();
                    notifyFrameMetadataListener(j4, jNanoTime, format);
                    renderOutputBuffer(mediaCodecAdapter, i, j4, jNanoTime);
                    updateVideoFrameProcessingOffsetCounters(this.videoFrameReleaseInfo.getEarlyUs());
                    return true;
                }
                if (frameReleaseAction == 1) {
                    return maybeReleaseFrame(mediaCodecAdapter, i, j4, format);
                }
                if (frameReleaseAction == 2) {
                    dropOutputBuffer(mediaCodecAdapter, i, j4);
                    updateVideoFrameProcessingOffsetCounters(this.videoFrameReleaseInfo.getEarlyUs());
                    return true;
                }
                if (frameReleaseAction == 3) {
                    skipOutputBuffer(mediaCodecAdapter, i, j4);
                    updateVideoFrameProcessingOffsetCounters(this.videoFrameReleaseInfo.getEarlyUs());
                    return true;
                }
                if (frameReleaseAction != 4 && frameReleaseAction != 5) {
                    throw new IllegalStateException(String.valueOf(frameReleaseAction));
                }
            }
        } else if (this.videoFrameReleaseInfo.getEarlyUs() < 30000) {
            skipOutputBuffer(mediaCodecAdapter, i, j4);
            updateVideoFrameProcessingOffsetCounters(this.videoFrameReleaseInfo.getEarlyUs());
            return true;
        }
        return false;
    }

    @RequiresApi(17)
    public final void releasePlaceholderSurface() {
        Surface surface = this.displaySurface;
        PlaceholderSurface placeholderSurface = this.placeholderSurface;
        if (surface == placeholderSurface) {
            this.displaySurface = null;
        }
        if (placeholderSurface != null) {
            placeholderSurface.release();
            this.placeholderSurface = null;
        }
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.Renderer
    @CallSuper
    public void render(long j, long j2) throws ExoPlaybackException {
        super.render(j, j2);
        VideoSink videoSink = this.videoSink;
        if (videoSink != null) {
            try {
                videoSink.render(j, j2);
            } catch (VideoSink.VideoSinkException e) {
                throw createRendererException(e, e.format, false, PlaybackException.ERROR_CODE_VIDEO_FRAME_PROCESSING_FAILED);
            }
        }
    }

    public final void renderOutputBuffer(MediaCodecAdapter mediaCodecAdapter, int i, long j, long j2) {
        if (Util.SDK_INT >= 21) {
            renderOutputBufferV21(mediaCodecAdapter, i, j, j2);
        } else {
            renderOutputBuffer(mediaCodecAdapter, i, j);
        }
    }

    @RequiresApi(21)
    public void renderOutputBufferV21(MediaCodecAdapter mediaCodecAdapter, int i, long j, long j2) {
        TraceUtil.beginSection("releaseOutputBuffer");
        mediaCodecAdapter.releaseOutputBuffer(i, j2);
        TraceUtil.endSection();
        this.decoderCounters.renderedOutputBufferCount++;
        this.consecutiveDroppedFrameCount = 0;
        if (this.videoSink == null) {
            maybeNotifyVideoSizeChanged(this.decodedVideoSize);
            maybeNotifyRenderedFirstFrame();
        }
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    @CallSuper
    public void resetCodecStateForFlush() {
        super.resetCodecStateForFlush();
        this.buffersInCodecCount = 0;
    }

    @RequiresApi(23)
    public void setOutputSurfaceV23(MediaCodecAdapter mediaCodecAdapter, Surface surface) {
        mediaCodecAdapter.setOutputSurface(surface);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.BaseRenderer, androidx.media3.exoplayer.Renderer
    public void setPlaybackSpeed(float f, float f2) throws ExoPlaybackException {
        super.setPlaybackSpeed(f, f2);
        this.videoFrameReleaseControl.setPlaybackSpeed(f);
        VideoSink videoSink = this.videoSink;
        if (videoSink != null) {
            videoSink.setPlaybackSpeed(f);
        }
    }

    public void setVideoEffects(List<Effect> list) {
        this.videoSinkProvider.setVideoEffects(list);
        this.hasEffects = true;
    }

    public boolean shouldDropBuffersToKeyframe(long j, long j2, boolean z) {
        return j < MIN_EARLY_US_VERY_LATE_THRESHOLD && !z;
    }

    @Override // androidx.media3.exoplayer.video.VideoFrameReleaseControl.FrameTimingEvaluator
    public boolean shouldDropFrame(long j, long j2, boolean z) {
        return shouldDropOutputBuffer(j, j2, z);
    }

    public boolean shouldDropOutputBuffer(long j, long j2, boolean z) {
        return j < MIN_EARLY_US_LATE_THRESHOLD && !z;
    }

    @Override // androidx.media3.exoplayer.video.VideoFrameReleaseControl.FrameTimingEvaluator
    public boolean shouldForceReleaseFrame(long j, long j2) {
        return shouldForceRenderOutputBuffer(j, j2);
    }

    public boolean shouldForceRenderOutputBuffer(long j, long j2) {
        return j < MIN_EARLY_US_LATE_THRESHOLD && j2 > 100000;
    }

    @Override // androidx.media3.exoplayer.video.VideoFrameReleaseControl.FrameTimingEvaluator
    public boolean shouldIgnoreFrame(long j, long j2, long j3, boolean z, boolean z2) throws ExoPlaybackException {
        return shouldDropBuffersToKeyframe(j, j3, z) && maybeDropBuffersToKeyframe(j2, z2);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    public boolean shouldInitCodec(MediaCodecInfo mediaCodecInfo) {
        return this.displaySurface != null || shouldUsePlaceholderSurface(mediaCodecInfo);
    }

    public boolean shouldSkipBuffersWithIdenticalReleaseTime() {
        return true;
    }

    public final boolean shouldUsePlaceholderSurface(MediaCodecInfo mediaCodecInfo) {
        if (Util.SDK_INT < 23 || this.tunneling || codecNeedsSetOutputSurfaceWorkaround(mediaCodecInfo.name)) {
            return false;
        }
        return !mediaCodecInfo.secure || PlaceholderSurface.isSecureSupported(this.context);
    }

    public void skipOutputBuffer(MediaCodecAdapter mediaCodecAdapter, int i, long j) {
        TraceUtil.beginSection("skipVideoBuffer");
        mediaCodecAdapter.releaseOutputBuffer(i, false);
        TraceUtil.endSection();
        this.decoderCounters.skippedOutputBufferCount++;
    }

    /* JADX WARN: Removed duplicated region for block: B:63:0x00d7  */
    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int supportsFormat(androidx.media3.exoplayer.mediacodec.MediaCodecSelector r14, androidx.media3.common.Format r15) throws androidx.media3.exoplayer.mediacodec.MediaCodecUtil.DecoderQueryException {
        /*
            Method dump skipped, instruction units count: 222
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.video.MediaCodecVideoRenderer.supportsFormat(androidx.media3.exoplayer.mediacodec.MediaCodecSelector, androidx.media3.common.Format):int");
    }

    public void updateDroppedBufferCounters(int i, int i2) {
        DecoderCounters decoderCounters = this.decoderCounters;
        decoderCounters.droppedInputBufferCount += i;
        int i3 = i + i2;
        decoderCounters.droppedBufferCount += i3;
        this.droppedFrames += i3;
        int i4 = this.consecutiveDroppedFrameCount + i3;
        this.consecutiveDroppedFrameCount = i4;
        decoderCounters.maxConsecutiveDroppedBufferCount = Math.max(i4, decoderCounters.maxConsecutiveDroppedBufferCount);
        int i5 = this.maxDroppedFramesToNotify;
        if (i5 <= 0 || this.droppedFrames < i5) {
            return;
        }
        maybeNotifyDroppedFrames();
    }

    public void updateVideoFrameProcessingOffsetCounters(long j) {
        this.decoderCounters.addVideoFrameProcessingOffsets(j, 1);
        this.totalVideoFrameProcessingOffsetUs += j;
        this.videoFrameProcessingOffsetCount++;
    }

    public MediaCodecVideoRenderer(Context context, MediaCodecSelector mediaCodecSelector, long j) {
        this(context, mediaCodecSelector, j, null, null, 0);
    }

    public MediaCodecVideoRenderer(Context context, MediaCodecSelector mediaCodecSelector, long j, @Nullable Handler handler, @Nullable VideoRendererEventListener videoRendererEventListener, int i) {
        this(context, MediaCodecAdapter.Factory.CC.getDefault(context), mediaCodecSelector, j, false, handler, videoRendererEventListener, i, 30.0f, null);
    }

    public static List<MediaCodecInfo> getDecoderInfos(Context context, MediaCodecSelector mediaCodecSelector, Format format, boolean z, boolean z2) throws MediaCodecUtil.DecoderQueryException {
        String str = format.sampleMimeType;
        if (str == null) {
            return RegularImmutableList.EMPTY;
        }
        if (Util.SDK_INT >= 26 && "video/dolby-vision".equals(str) && !Api26.doesDisplaySupportDolbyVision(context)) {
            List<MediaCodecInfo> alternativeDecoderInfos = MediaCodecUtil.getAlternativeDecoderInfos(mediaCodecSelector, format, z, z2);
            if (!alternativeDecoderInfos.isEmpty()) {
                return alternativeDecoderInfos;
            }
        }
        return MediaCodecUtil.getDecoderInfosSoftMatch(mediaCodecSelector, format, z, z2);
    }

    public void renderOutputBuffer(MediaCodecAdapter mediaCodecAdapter, int i, long j) {
        TraceUtil.beginSection("releaseOutputBuffer");
        mediaCodecAdapter.releaseOutputBuffer(i, true);
        TraceUtil.endSection();
        this.decoderCounters.renderedOutputBufferCount++;
        this.consecutiveDroppedFrameCount = 0;
        if (this.videoSink == null) {
            maybeNotifyVideoSizeChanged(this.decodedVideoSize);
            maybeNotifyRenderedFirstFrame();
        }
    }

    public MediaCodecVideoRenderer(Context context, MediaCodecSelector mediaCodecSelector, long j, boolean z, @Nullable Handler handler, @Nullable VideoRendererEventListener videoRendererEventListener, int i) {
        this(context, MediaCodecAdapter.Factory.CC.getDefault(context), mediaCodecSelector, j, z, handler, videoRendererEventListener, i, 30.0f, null);
    }

    public MediaCodecVideoRenderer(Context context, MediaCodecAdapter.Factory factory, MediaCodecSelector mediaCodecSelector, long j, boolean z, @Nullable Handler handler, @Nullable VideoRendererEventListener videoRendererEventListener, int i) {
        this(context, factory, mediaCodecSelector, j, z, handler, videoRendererEventListener, i, 30.0f, null);
    }

    public MediaCodecVideoRenderer(Context context, MediaCodecAdapter.Factory factory, MediaCodecSelector mediaCodecSelector, long j, boolean z, @Nullable Handler handler, @Nullable VideoRendererEventListener videoRendererEventListener, int i, float f) {
        this(context, factory, mediaCodecSelector, j, z, handler, videoRendererEventListener, i, f, null);
    }

    public MediaCodecVideoRenderer(Context context, MediaCodecAdapter.Factory factory, MediaCodecSelector mediaCodecSelector, long j, boolean z, @Nullable Handler handler, @Nullable VideoRendererEventListener videoRendererEventListener, int i, float f, @Nullable VideoSinkProvider videoSinkProvider) {
        super(2, factory, mediaCodecSelector, z, f);
        this.maxDroppedFramesToNotify = i;
        Context applicationContext = context.getApplicationContext();
        this.context = applicationContext;
        this.eventDispatcher = new VideoRendererEventListener.EventDispatcher(handler, videoRendererEventListener);
        VideoSinkProvider videoSinkProviderBuild = videoSinkProvider == null ? new CompositingVideoSinkProvider.Builder(applicationContext).build() : videoSinkProvider;
        if (videoSinkProviderBuild.getVideoFrameReleaseControl() == null) {
            videoSinkProviderBuild.setVideoFrameReleaseControl(new VideoFrameReleaseControl(applicationContext, this, j));
        }
        this.videoSinkProvider = videoSinkProviderBuild;
        VideoFrameReleaseControl videoFrameReleaseControl = videoSinkProviderBuild.getVideoFrameReleaseControl();
        Assertions.checkStateNotNull(videoFrameReleaseControl);
        this.videoFrameReleaseControl = videoFrameReleaseControl;
        this.videoFrameReleaseInfo = new VideoFrameReleaseControl.FrameReleaseInfo();
        this.deviceNeedsNoPostProcessWorkaround = deviceNeedsNoPostProcessWorkaround();
        this.scalingMode = 1;
        this.decodedVideoSize = VideoSize.UNKNOWN;
        this.tunnelingAudioSessionId = 0;
        this.reportedVideoSize = null;
    }

    public void onReadyToRegisterVideoSinkInputStream() {
    }
}
