package com.amazon.livingroom.accessibility;

import com.amazon.livingroom.mediapipelinebackend.ResultHolder;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface TextToSpeechEngine extends AutoCloseable {
    @Override // java.lang.AutoCloseable
    void close();

    int getMaximumSpeechLength();

    @NotNull
    ResultHolder<String> getSupportedLanguage(int i);

    @NotNull
    ResultHolder<Integer> getSupportedLanguagesCount();

    boolean isSpeakingEnabled();

    boolean isSpeakingSupported();

    int speak(@NotNull String str, @NotNull String str2, boolean z);

    int stopAllSpeech();
}
