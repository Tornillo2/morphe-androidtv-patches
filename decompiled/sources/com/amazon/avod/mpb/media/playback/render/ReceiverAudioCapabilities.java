package com.amazon.avod.mpb.media.playback.render;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.google.common.annotations.VisibleForTesting;
import java.util.Arrays;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nReceiverAudioCapabilities.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ReceiverAudioCapabilities.kt\ncom/amazon/avod/mpb/media/playback/render/ReceiverAudioCapabilities\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,50:1\n1#2:51\n*E\n"})
public final class ReceiverAudioCapabilities {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final ReceiverAudioCapabilities DEFAULT_RECEIVER_AUDIO_CAPABILITIES = new ReceiverAudioCapabilities(new int[]{2});

    @NotNull
    public final int[] supportedEncodings;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final ReceiverAudioCapabilities getCapabilities(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intentRegisterReceiver = context.registerReceiver(null, new IntentFilter("android.media.action.HDMI_AUDIO_PLUG"));
            if (intentRegisterReceiver == null || intentRegisterReceiver.getIntExtra("android.media.extra.AUDIO_PLUG_STATE", 0) == 0) {
                return ReceiverAudioCapabilities.DEFAULT_RECEIVER_AUDIO_CAPABILITIES;
            }
            int[] intArrayExtra = intentRegisterReceiver.getIntArrayExtra("android.media.extra.ENCODINGS");
            return intArrayExtra == null ? ReceiverAudioCapabilities.DEFAULT_RECEIVER_AUDIO_CAPABILITIES : new ReceiverAudioCapabilities(intArrayExtra);
        }

        @NotNull
        public final ReceiverAudioCapabilities getDEFAULT_RECEIVER_AUDIO_CAPABILITIES() {
            return ReceiverAudioCapabilities.DEFAULT_RECEIVER_AUDIO_CAPABILITIES;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    @VisibleForTesting
    public ReceiverAudioCapabilities(@NotNull int[] supportedEncodings) {
        Intrinsics.checkNotNullParameter(supportedEncodings, "supportedEncodings");
        int[] iArrCopyOf = Arrays.copyOf(supportedEncodings, supportedEncodings.length);
        Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(...)");
        Arrays.sort(iArrCopyOf);
        this.supportedEncodings = iArrCopyOf;
    }

    public final boolean supportsEncoding(int i) {
        return Arrays.binarySearch(this.supportedEncodings, i) >= 0;
    }
}
