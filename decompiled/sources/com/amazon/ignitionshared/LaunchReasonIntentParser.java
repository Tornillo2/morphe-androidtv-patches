package com.amazon.ignitionshared;

import android.content.Intent;
import com.amazon.ignitionshared.deeplink.DeepLinkIntentParser;
import com.amazon.livingroom.di.ApplicationScope;
import j$.util.Objects;
import javax.inject.Inject;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public final class LaunchReasonIntentParser {

    @NotNull
    public final DeepLinkIntentParser deepLinkIntentParser;

    @Inject
    public LaunchReasonIntentParser(@NotNull DeepLinkIntentParser deepLinkIntentParser) {
        Intrinsics.checkNotNullParameter(deepLinkIntentParser, "deepLinkIntentParser");
        this.deepLinkIntentParser = deepLinkIntentParser;
    }

    @NotNull
    public final LaunchReason parse(@NotNull Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        return this.deepLinkIntentParser.parse(intent) != null ? LaunchReason.DEEP_LINKING : Objects.equals(intent.getAction(), AmazonButtonIntentMatcher.AMAZON_BUTTON_ACTION) ? LaunchReason.HOT_KEY : LaunchReason.LAUNCHER;
    }
}
