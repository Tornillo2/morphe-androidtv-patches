package com.amazon.livingroom.accessibility;

import android.content.Context;
import android.media.AudioAttributes;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
import androidx.core.app.RemoteInput$$ExternalSyntheticOutline0;
import com.amazon.ignitionshared.TextToSpeechStatusProvider;
import com.amazon.ignitionshared.ThreadUtils;
import com.amazon.ignitionshared.metrics.MinervaConstants;
import com.amazon.ignitionshared.metrics.MinervaMetrics;
import com.amazon.livingroom.di.ApplicationContext;
import com.amazon.livingroom.mediapipelinebackend.CalledFromNative;
import com.amazon.livingroom.mediapipelinebackend.ResultHolder;
import com.amazon.minerva.client.thirdparty.api.MetricEvent;
import com.amazon.minerva.client.thirdparty.transport.UploadResult;
import com.amazon.reporting.Log;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.EmptySet;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class AndroidTextToSpeechEngine implements TextToSpeechEngine {

    @NotNull
    public static final Companion Companion = new Companion();
    public static final int ERROR_INITIALIZATION_FAILED = 3002;
    public static final int ERROR_INITIALIZATION_NOT_FINISHED = 3001;
    public static final int ERROR_LANGUAGE_INDEX_OUT_OF_BOUNDS = 3006;
    public static final int ERROR_LANGUAGE_NOT_SUPPORTED = 3005;
    public static final int GENERIC_ERROR = -1;
    public static final long INIT_TIMEOUT_MS = 10000;

    @NotNull
    public static final String LOG_TAG = "PV-TTS";

    @NotNull
    public static final String METRIC_SOURCE = "TextToSpeechEngine";
    public static final int SUCCESS = 0;

    @NotNull
    public static final String TTS_INITIALIZATION_FINISHED = "TTSInitialization.finished";

    @NotNull
    public static final String TTS_INITIALIZATION_STARTED = "TTSInitialization.started";

    @Nullable
    public List<String> availableLanguages;

    @NotNull
    public final Context context;

    @Nullable
    public String currentLanguage;

    @NotNull
    public InitializationStatus initializationStatus;

    @NotNull
    public final MinervaMetrics minervaMetrics;

    @NotNull
    public final Object mutex;

    @Nullable
    public TextToSpeech tts;

    @NotNull
    public TextToSpeechStatusProvider.TtsEnabledStatus ttsEnabledStatus;

    @NotNull
    public final TextToSpeechStatusProvider ttsStatusProvider;

    @NotNull
    public TextToSpeechStatusProvider.TtsSupportStatus ttsSupportStatus;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class InitializationStatus {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ InitializationStatus[] $VALUES;
        public static final InitializationStatus PENDING = new InitializationStatus("PENDING", 0);
        public static final InitializationStatus SUCCESS = new InitializationStatus(UploadResult.SUCCESS, 1);
        public static final InitializationStatus FAILURE = new InitializationStatus("FAILURE", 2);

        public static final /* synthetic */ InitializationStatus[] $values() {
            return new InitializationStatus[]{PENDING, SUCCESS, FAILURE};
        }

        static {
            InitializationStatus[] initializationStatusArr$values = $values();
            $VALUES = initializationStatusArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(initializationStatusArr$values);
        }

        public InitializationStatus(String str, int i) {
        }

        @NotNull
        public static EnumEntries<InitializationStatus> getEntries() {
            return $ENTRIES;
        }

        public static InitializationStatus valueOf(String str) {
            return (InitializationStatus) Enum.valueOf(InitializationStatus.class, str);
        }

        public static InitializationStatus[] values() {
            return (InitializationStatus[]) $VALUES.clone();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            int[] iArr = new int[TextToSpeechStatusProvider.TtsEnabledStatus.values().length];
            try {
                iArr[TextToSpeechStatusProvider.TtsEnabledStatus.UNCHECKED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[TextToSpeechStatusProvider.TtsEnabledStatus.NO_ACCESSIBILITY_MANAGER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[TextToSpeechStatusProvider.TtsEnabledStatus.ACCESSIBILITY_MANAGER_DISABLED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[TextToSpeechStatusProvider.TtsEnabledStatus.NO_SPOKEN_FEEDBACK_SERVICE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[TextToSpeechStatusProvider.TtsEnabledStatus.ENABLED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[TextToSpeechStatusProvider.TtsSupportStatus.values().length];
            try {
                iArr2[TextToSpeechStatusProvider.TtsSupportStatus.UNCHECKED.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr2[TextToSpeechStatusProvider.TtsSupportStatus.NO_ACCESSIBILITY_MANAGER.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr2[TextToSpeechStatusProvider.TtsSupportStatus.SUPPORTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[InitializationStatus.values().length];
            try {
                iArr3[InitializationStatus.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr3[InitializationStatus.FAILURE.ordinal()] = 2;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr3[InitializationStatus.PENDING.ordinal()] = 3;
            } catch (NoSuchFieldError unused11) {
            }
            $EnumSwitchMapping$2 = iArr3;
        }
    }

    @Inject
    public AndroidTextToSpeechEngine(@ApplicationContext @NotNull Context context, @NotNull TextToSpeechStatusProvider ttsStatusProvider, @NotNull MinervaMetrics minervaMetrics) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(ttsStatusProvider, "ttsStatusProvider");
        Intrinsics.checkNotNullParameter(minervaMetrics, "minervaMetrics");
        this.context = context;
        this.ttsStatusProvider = ttsStatusProvider;
        this.minervaMetrics = minervaMetrics;
        this.mutex = new Object();
        this.initializationStatus = InitializationStatus.PENDING;
        this.ttsSupportStatus = TextToSpeechStatusProvider.TtsSupportStatus.UNCHECKED;
        this.ttsEnabledStatus = TextToSpeechStatusProvider.TtsEnabledStatus.UNCHECKED;
    }

    public static final boolean ensureInitialized$lambda$4$lambda$3(AndroidTextToSpeechEngine androidTextToSpeechEngine) {
        return androidTextToSpeechEngine.initializationStatus != InitializationStatus.PENDING;
    }

    @Override // com.amazon.livingroom.accessibility.TextToSpeechEngine, java.lang.AutoCloseable
    public void close() {
        TextToSpeech textToSpeech;
        synchronized (this.mutex) {
            textToSpeech = this.tts;
            this.tts = null;
        }
        if (textToSpeech != null) {
            textToSpeech.shutdown();
        }
    }

    public final int ensureInitialized() {
        int i;
        synchronized (this.mutex) {
            try {
                if (this.tts == null) {
                    recordMetric(TTS_INITIALIZATION_STARTED, 1);
                    this.tts = AndroidTextToSpeechEngineKt.createTextToSpeech(this.context, new TextToSpeech.OnInitListener() { // from class: com.amazon.livingroom.accessibility.AndroidTextToSpeechEngine$$ExternalSyntheticLambda0
                        @Override // android.speech.tts.TextToSpeech.OnInitListener
                        public final void onInit(int i2) {
                            this.f$0.onTtsInitialized(i2);
                        }
                    });
                    AudioAttributes audioAttributesBuild = new AudioAttributes.Builder().setUsage(11).build();
                    TextToSpeech textToSpeech = this.tts;
                    Intrinsics.checkNotNull(textToSpeech);
                    textToSpeech.setAudioAttributes(audioAttributesBuild);
                }
                ThreadUtils.wait(this.mutex, new ThreadUtils.Condition() { // from class: com.amazon.livingroom.accessibility.AndroidTextToSpeechEngine$$ExternalSyntheticLambda1
                    @Override // com.amazon.ignitionshared.ThreadUtils.Condition
                    public final boolean evaluate() {
                        return AndroidTextToSpeechEngine.ensureInitialized$lambda$4$lambda$3(this.f$0);
                    }
                }, 10000L);
                int i2 = WhenMappings.$EnumSwitchMapping$2[this.initializationStatus.ordinal()];
                if (i2 == 1) {
                    i = 0;
                } else if (i2 == 2) {
                    i = 3002;
                } else {
                    if (i2 != 3) {
                        throw new IllegalStateException("initializationStatus==" + this.initializationStatus);
                    }
                    i = 3001;
                }
            } finally {
            }
        }
        return i;
    }

    public final List<String> getAvailableLanguages() {
        Set<Locale> availableLanguages;
        if (this.availableLanguages == null) {
            TextToSpeech textToSpeech = this.tts;
            if (textToSpeech == null || (availableLanguages = textToSpeech.getAvailableLanguages()) == null) {
                availableLanguages = EmptySet.INSTANCE;
            }
            ArrayList arrayList = new ArrayList(availableLanguages.size());
            Iterator<Locale> it = availableLanguages.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().toLanguageTag());
            }
            this.availableLanguages = arrayList;
        }
        List<String> list = this.availableLanguages;
        Intrinsics.checkNotNull(list, "null cannot be cast to non-null type kotlin.collections.List<kotlin.String>");
        return list;
    }

    public final TextToSpeechStatusProvider.TtsEnabledStatus getCurrentTtsEnabledStatus() {
        TextToSpeechStatusProvider.TtsEnabledStatus ttsEnabledStatus = this.ttsStatusProvider.getTtsEnabledStatus();
        Intrinsics.checkNotNullExpressionValue(ttsEnabledStatus, "getTtsEnabledStatus(...)");
        return ttsEnabledStatus;
    }

    public final TextToSpeechStatusProvider.TtsSupportStatus getCurrentTtsSupportStatus() {
        TextToSpeechStatusProvider.TtsSupportStatus ttsSupportStatus = this.ttsStatusProvider.getTtsSupportStatus();
        Intrinsics.checkNotNullExpressionValue(ttsSupportStatus, "getTtsSupportStatus(...)");
        return ttsSupportStatus;
    }

    @Override // com.amazon.livingroom.accessibility.TextToSpeechEngine
    @CalledFromNative
    public int getMaximumSpeechLength() {
        return TextToSpeech.getMaxSpeechInputLength();
    }

    @Override // com.amazon.livingroom.accessibility.TextToSpeechEngine
    @CalledFromNative
    @NotNull
    public ResultHolder<String> getSupportedLanguage(int i) {
        int iEnsureInitialized = ensureInitialized();
        if (iEnsureInitialized != 0) {
            return ResultHolder.fromErrorCode(iEnsureInitialized);
        }
        List<String> availableLanguages = getAvailableLanguages();
        return (i < 0 || i >= availableLanguages.size()) ? ResultHolder.fromErrorCode(ERROR_LANGUAGE_INDEX_OUT_OF_BOUNDS) : ResultHolder.fromResult(availableLanguages.get(i));
    }

    @Override // com.amazon.livingroom.accessibility.TextToSpeechEngine
    @CalledFromNative
    @NotNull
    public ResultHolder<Integer> getSupportedLanguagesCount() {
        int iEnsureInitialized = ensureInitialized();
        return iEnsureInitialized != 0 ? ResultHolder.fromErrorCode(iEnsureInitialized) : ResultHolder.fromResult(Integer.valueOf(getAvailableLanguages().size()));
    }

    @Override // com.amazon.livingroom.accessibility.TextToSpeechEngine
    @CalledFromNative
    public boolean isSpeakingEnabled() {
        String str;
        TextToSpeechStatusProvider.TtsEnabledStatus ttsEnabledStatus = this.ttsEnabledStatus;
        TextToSpeechStatusProvider.TtsEnabledStatus currentTtsEnabledStatus = getCurrentTtsEnabledStatus();
        this.ttsEnabledStatus = currentTtsEnabledStatus;
        if (currentTtsEnabledStatus != ttsEnabledStatus) {
            int i = WhenMappings.$EnumSwitchMapping$0[currentTtsEnabledStatus.ordinal()];
            if (i == 1) {
                throw new IllegalStateException("Checked TTS but status is still UNCHECKED");
            }
            if (i == 2) {
                str = "TTS is disabled due to no accessibility manager";
            } else if (i == 3) {
                str = "TTS is disabled due to accessibility manager disabled";
            } else if (i == 4) {
                str = "TTS is disabled due to no spoken feedback service enabled";
            } else {
                if (i != 5) {
                    throw new NoWhenBranchMatchedException();
                }
                str = "TTS is enabled";
            }
            Log.i(LOG_TAG, str);
        }
        return this.ttsEnabledStatus == TextToSpeechStatusProvider.TtsEnabledStatus.ENABLED;
    }

    @Override // com.amazon.livingroom.accessibility.TextToSpeechEngine
    @CalledFromNative
    public boolean isSpeakingSupported() {
        String str;
        TextToSpeechStatusProvider.TtsSupportStatus ttsSupportStatus = this.ttsSupportStatus;
        TextToSpeechStatusProvider.TtsSupportStatus currentTtsSupportStatus = getCurrentTtsSupportStatus();
        this.ttsSupportStatus = currentTtsSupportStatus;
        if (currentTtsSupportStatus != ttsSupportStatus) {
            int i = WhenMappings.$EnumSwitchMapping$1[currentTtsSupportStatus.ordinal()];
            if (i == 1) {
                throw new IllegalStateException("Checked TTS support but status is still UNCHECKED");
            }
            if (i == 2) {
                str = "TTS is not supported due to no accessibility manager";
            } else {
                if (i != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                str = "TTS is supported";
            }
            Log.i(LOG_TAG, str);
        }
        return this.ttsSupportStatus == TextToSpeechStatusProvider.TtsSupportStatus.SUPPORTED;
    }

    public final void onTtsInitialized(int i) {
        synchronized (this.mutex) {
            try {
                Log.d(LOG_TAG, "TTS engine initialized: " + i);
                recordMetric(TTS_INITIALIZATION_FINISHED, i == 0 ? 1 : 0);
                if (i == 0) {
                    this.initializationStatus = InitializationStatus.SUCCESS;
                } else {
                    this.initializationStatus = InitializationStatus.FAILURE;
                }
                Object obj = this.mutex;
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type java.lang.Object");
                obj.notifyAll();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void recordMetric(String str, int i) {
        MetricEvent metricEventCreateMetricEvent = this.minervaMetrics.createMetricEvent(MinervaConstants.TEXT_TO_SPEECH_ENGINE_SCHEMA_ID);
        metricEventCreateMetricEvent.addLong(RemoteInput$$ExternalSyntheticOutline0.m("TextToSpeechEngine.", str), i);
        this.minervaMetrics.record(metricEventCreateMetricEvent);
    }

    public final int setLanguage(String str) {
        if (TextUtils.equals(str, this.currentLanguage)) {
            return 0;
        }
        Locale localeForLanguageTag = Locale.forLanguageTag(str);
        TextToSpeech textToSpeech = this.tts;
        Intrinsics.checkNotNull(textToSpeech);
        int language = textToSpeech.setLanguage(localeForLanguageTag);
        this.currentLanguage = str;
        if (language == 0) {
            Log.w(LOG_TAG, "The generic language is available, but not the specific variant " + str);
            return 0;
        }
        if (language >= 0) {
            return 0;
        }
        Log.e(LOG_TAG, "The language " + str + " is not supported for TTS");
        return ERROR_LANGUAGE_NOT_SUPPORTED;
    }

    @Override // com.amazon.livingroom.accessibility.TextToSpeechEngine
    @CalledFromNative
    public int speak(@NotNull String text, @NotNull String language, boolean z) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(language, "language");
        Log.d(LOG_TAG, "TextToSpeechEngine.speak(text=\"" + text + "\", language=" + language + ", isImmediate=" + z + "\")");
        int iEnsureInitialized = ensureInitialized();
        if (iEnsureInitialized != 0) {
            return iEnsureInitialized;
        }
        int language2 = setLanguage(language);
        if (language2 != 0) {
            return language2;
        }
        int i = !z ? 1 : 0;
        TextToSpeech textToSpeech = this.tts;
        Intrinsics.checkNotNull(textToSpeech);
        return textToSpeech.speak(text, i, null, null) == -1 ? -1 : 0;
    }

    @Override // com.amazon.livingroom.accessibility.TextToSpeechEngine
    @CalledFromNative
    public int stopAllSpeech() {
        Log.d(LOG_TAG, "TextToSpeechEngine.stopAllSpeech()");
        synchronized (this.mutex) {
            if (this.tts == null) {
                return 0;
            }
            int iEnsureInitialized = ensureInitialized();
            if (iEnsureInitialized != 0) {
                return iEnsureInitialized;
            }
            TextToSpeech textToSpeech = this.tts;
            Intrinsics.checkNotNull(textToSpeech);
            return textToSpeech.stop() == -1 ? -1 : 0;
        }
    }
}
