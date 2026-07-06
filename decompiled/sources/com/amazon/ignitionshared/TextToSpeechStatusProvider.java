package com.amazon.ignitionshared;

import android.content.Context;
import android.view.accessibility.AccessibilityManager;
import com.amazon.livingroom.di.ApplicationContext;
import com.amazon.livingroom.di.ApplicationScope;
import javax.inject.Inject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public class TextToSpeechStatusProvider {
    public final AccessibilityManager accessibilityManager;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum TtsEnabledStatus {
        UNCHECKED,
        NO_ACCESSIBILITY_MANAGER,
        ACCESSIBILITY_MANAGER_DISABLED,
        NO_SPOKEN_FEEDBACK_SERVICE,
        ENABLED
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum TtsSupportStatus {
        UNCHECKED,
        NO_ACCESSIBILITY_MANAGER,
        SUPPORTED
    }

    @Inject
    public TextToSpeechStatusProvider(@ApplicationContext Context context) {
        this.accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
    }

    public TtsEnabledStatus getTtsEnabledStatus() {
        AccessibilityManager accessibilityManager = this.accessibilityManager;
        return accessibilityManager == null ? TtsEnabledStatus.NO_ACCESSIBILITY_MANAGER : !accessibilityManager.isEnabled() ? TtsEnabledStatus.ACCESSIBILITY_MANAGER_DISABLED : this.accessibilityManager.getEnabledAccessibilityServiceList(1).isEmpty() ? TtsEnabledStatus.NO_SPOKEN_FEEDBACK_SERVICE : TtsEnabledStatus.ENABLED;
    }

    public TtsSupportStatus getTtsSupportStatus() {
        return this.accessibilityManager == null ? TtsSupportStatus.NO_ACCESSIBILITY_MANAGER : TtsSupportStatus.SUPPORTED;
    }
}
