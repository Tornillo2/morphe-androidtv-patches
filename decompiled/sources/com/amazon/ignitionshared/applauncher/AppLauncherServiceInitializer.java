package com.amazon.ignitionshared.applauncher;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.core.app.RemoteInput$$ExternalSyntheticOutline0;
import com.amazon.ignitionshared.GMBMessageProcessor;
import com.amazon.ignitionshared.GMBMessageSender;
import com.amazon.ignitionshared.ServiceInitializer;
import com.amazon.livingroom.di.ApplicationContext;
import com.amazon.livingroom.di.ApplicationScope;
import javax.inject.Inject;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
@SourceDebugExtension({"SMAP\nAppLauncherServiceInitializer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AppLauncherServiceInitializer.kt\ncom/amazon/ignitionshared/applauncher/AppLauncherServiceInitializer\n+ 2 Uri.kt\nandroidx/core/net/UriKt\n*L\n1#1,55:1\n29#2:56\n*S KotlinDebug\n*F\n+ 1 AppLauncherServiceInitializer.kt\ncom/amazon/ignitionshared/applauncher/AppLauncherServiceInitializer\n*L\n32#1:56\n*E\n"})
public final class AppLauncherServiceInitializer implements ServiceInitializer {

    @NotNull
    public static final String APP_LAUNCHER_REQUEST_MESSAGE_TYPE = "gmb.app_launcher.request";

    @NotNull
    public static final String APP_LAUNCHER_RESPONSE_MESSAGE_TYPE = "gmb.app_launcher.response";

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final String SUCCESS_RESPONSE_PAYLOAD = "{\"status\":\"success\"}";

    @NotNull
    public final Context context;

    @NotNull
    public final GMBMessageProcessor gmbMessageProcessor;

    @NotNull
    public final GMBMessageSender gmbMessageSender;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    @Inject
    public AppLauncherServiceInitializer(@ApplicationContext @NotNull Context context, @NotNull GMBMessageProcessor gmbMessageProcessor, @NotNull GMBMessageSender gmbMessageSender) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(gmbMessageProcessor, "gmbMessageProcessor");
        Intrinsics.checkNotNullParameter(gmbMessageSender, "gmbMessageSender");
        this.context = context;
        this.gmbMessageProcessor = gmbMessageProcessor;
        this.gmbMessageSender = gmbMessageSender;
    }

    public static final void initialize$lambda$0(AppLauncherServiceInitializer appLauncherServiceInitializer, String str) {
        try {
            String string = new JSONObject(str).getString("app_id");
            Intent leanbackLaunchIntentForPackage = appLauncherServiceInitializer.context.getPackageManager().getLeanbackLaunchIntentForPackage(string);
            if (leanbackLaunchIntentForPackage != null) {
                leanbackLaunchIntentForPackage.addFlags(268435456);
                appLauncherServiceInitializer.context.startActivity(leanbackLaunchIntentForPackage);
            } else {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + string));
                intent.addFlags(268435456);
                appLauncherServiceInitializer.context.startActivity(intent);
            }
            GMBMessageSender.sendGMBMessageToClient$default(appLauncherServiceInitializer.gmbMessageSender, APP_LAUNCHER_RESPONSE_MESSAGE_TYPE, SUCCESS_RESPONSE_PAYLOAD, 0L, 4, null);
        } catch (Exception e) {
            GMBMessageSender.sendGMBMessageToClient$default(appLauncherServiceInitializer.gmbMessageSender, APP_LAUNCHER_RESPONSE_MESSAGE_TYPE, RemoteInput$$ExternalSyntheticOutline0.m("Failed to launch app with error: ", e.getMessage()), 0L, 4, null);
        }
    }

    @Override // com.amazon.ignitionshared.ServiceInitializer
    public void initialize() {
        this.gmbMessageProcessor.subscribeMessageHandler(APP_LAUNCHER_REQUEST_MESSAGE_TYPE, new GMBMessageProcessor.GMBMessageHandler() { // from class: com.amazon.ignitionshared.applauncher.AppLauncherServiceInitializer$$ExternalSyntheticLambda0
            @Override // com.amazon.ignitionshared.GMBMessageProcessor.GMBMessageHandler
            public final void process(String str) {
                AppLauncherServiceInitializer.initialize$lambda$0(this.f$0, str);
            }
        });
    }
}
