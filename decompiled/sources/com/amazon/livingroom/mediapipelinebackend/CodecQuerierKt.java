package com.amazon.livingroom.mediapipelinebackend;

import androidx.annotation.RequiresApi;
import com.amazon.avod.mpb.media.ExternalFourCCMapper;
import com.amazon.avod.mpb.media.playback.MediaCodecEnumeratorImpl;
import java.util.Iterator;
import java.util.Map;
import kotlin.Pair;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nCodecQuerier.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CodecQuerier.kt\ncom/amazon/livingroom/mediapipelinebackend/CodecQuerierKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,190:1\n1#2:191\n*E\n"})
public final class CodecQuerierKt {
    public static final int SUPPORTED = 1;
    public static final int SUPPORTED_HARDWARE_ACCELERATED = 2;
    public static final int UNSUPPORTED = 0;

    @RequiresApi(24)
    @NotNull
    public static Map<String, Integer> transferFunctionMap = MapsKt__MapsKt.mapOf(new Pair("srgb", 3), new Pair(MediaCodecEnumeratorImpl.TRANSFER_FUNCTION_PQ, 6), new Pair(MediaCodecEnumeratorImpl.TRANSFER_FUNCTION_HLG, 7));

    @RequiresApi(24)
    @NotNull
    public static Map<String, Integer> colorGamutMap = MapsKt__MapsKt.mapOf(new Pair("srgb", 1), new Pair(MediaCodecEnumeratorImpl.COLOR_GAMUT_REC_2020, 6));

    public static final int getChannelCount(@NotNull String input) {
        Intrinsics.checkNotNullParameter(input, "input");
        Iterator it = StringsKt__StringsKt.split$default((CharSequence) input, new String[]{ExternalFourCCMapper.CODEC_NAME_SPLITTER}, false, 0, 6, (Object) null).iterator();
        int i = 0;
        while (it.hasNext()) {
            i += Integer.parseInt((String) it.next());
        }
        return i;
    }

    @NotNull
    public static final Map<String, Integer> getColorGamutMap() {
        return colorGamutMap;
    }

    @NotNull
    public static final Map<String, Integer> getTransferFunctionMap() {
        return transferFunctionMap;
    }

    public static final void setColorGamutMap(@NotNull Map<String, Integer> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        colorGamutMap = map;
    }

    public static final void setTransferFunctionMap(@NotNull Map<String, Integer> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        transferFunctionMap = map;
    }
}
