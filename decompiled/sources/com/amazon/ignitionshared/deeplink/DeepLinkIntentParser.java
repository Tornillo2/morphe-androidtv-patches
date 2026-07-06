package com.amazon.ignitionshared.deeplink;

import android.content.Intent;
import kotlin.jvm.JvmStatic;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface DeepLinkIntentParser {

    @NotNull
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: renamed from: com.amazon.ignitionshared.deeplink.DeepLinkIntentParser$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
        static {
            Companion companion = DeepLinkIntentParser.Companion;
        }

        @NotNull
        public static String getInternalDeepLinkKey() {
            DeepLinkIntentParser.Companion.getClass();
            return Companion.internalDeepLinkKey;
        }
    }

    @Nullable
    String parse(@NotNull Intent intent);

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();

        @NotNull
        public static final String internalDeepLinkKey = "com.amazon.ignition.DeepLinkIntent.DEEP_LINK";

        @NotNull
        public final String getInternalDeepLinkKey() {
            return internalDeepLinkKey;
        }

        @JvmStatic
        public static /* synthetic */ void getInternalDeepLinkKey$annotations() {
        }
    }
}
