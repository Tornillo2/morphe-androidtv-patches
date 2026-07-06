package com.amazon.livingroom.accessibility;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class AndroidTextToSpeechEngineKt {
    @NotNull
    public static final TextToSpeech createTextToSpeech(@NotNull Context context, @NotNull TextToSpeech.OnInitListener initListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(initListener, "initListener");
        return new TextToSpeech(context, initListener);
    }
}
