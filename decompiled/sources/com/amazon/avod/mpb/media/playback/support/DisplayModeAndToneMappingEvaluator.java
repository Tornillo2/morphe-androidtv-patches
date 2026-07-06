package com.amazon.avod.mpb.media.playback.support;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.media.MediaCodecList;
import android.media.MediaFormat;
import androidx.annotation.RequiresApi;
import com.amazon.avod.mpb.api.core.MpbLog;
import com.amazon.avod.mpb.util.Preconditions2;
import com.google.common.collect.ImmutableList;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class DisplayModeAndToneMappingEvaluator {

    @NotNull
    public static final DisplayModeAndToneMappingEvaluator INSTANCE = new DisplayModeAndToneMappingEvaluator();

    @JvmStatic
    @NotNull
    public static final ImmutableList<HdrFormat> getSupportedHdrFormats(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (Preconditions2.isSdkIntAtLeast(24)) {
            return INSTANCE.getSupportedHdrFormatsV24(context);
        }
        MpbLog.devf("Display tone mapping evaluation is not supported on this platform.", new Object[0]);
        ImmutableList<HdrFormat> immutableListOf = ImmutableList.of();
        Intrinsics.checkNotNullExpressionValue(immutableListOf, "of(...)");
        return immutableListOf;
    }

    @JvmStatic
    @Nullable
    public static final String lookupDecoderWithMimeType(@NotNull MediaCodecList mediaCodecList, @NotNull String mimeType, boolean z) {
        Intrinsics.checkNotNullParameter(mediaCodecList, "mediaCodecList");
        Intrinsics.checkNotNullParameter(mimeType, "mimeType");
        try {
            MediaFormat mediaFormat = new MediaFormat();
            mediaFormat.setString("mime", mimeType);
            if (z) {
                mediaFormat.setFeatureEnabled("secure-playback", true);
            }
            String strFindDecoderForFormat = mediaCodecList.findDecoderForFormat(mediaFormat);
            MpbLog.devf("Found decoder %s for mime %s", strFindDecoderForFormat, mimeType);
            return strFindDecoderForFormat;
        } catch (IllegalArgumentException e) {
            MpbLog.exceptionf(e, "Unable to get decoder for mime: %s,", mimeType);
            return null;
        }
    }

    @RequiresApi(24)
    public final ImmutableList<HdrFormat> getSupportedHdrFormatsV24(Context context) {
        ImmutableList.Builder builder = ImmutableList.builder();
        Object systemService = context.getSystemService("display");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.hardware.display.DisplayManager");
        for (int i : ((DisplayManager) systemService).getDisplay(0).getHdrCapabilities().getSupportedHdrTypes()) {
            if (i == 1) {
                MpbLog.devf("DoVi HDR is supported and enabled by config.", new Object[0]);
                builder.add(HdrFormat.DolbyVision);
            }
            if (i == 2) {
                MpbLog.devf("HDR10 HDR is supported and enabled by config.", new Object[0]);
                builder.add(HdrFormat.Hdr10);
            }
        }
        ImmutableList<HdrFormat> immutableListBuild = builder.build();
        Intrinsics.checkNotNullExpressionValue(immutableListBuild, "build(...)");
        return immutableListBuild;
    }
}
