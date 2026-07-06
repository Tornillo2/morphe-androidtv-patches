package com.amazon.ignitionshared.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.amazon.ignitionshared.service.DtidRequestOnStartupWorker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class RunOnInstallReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(@NotNull Context context, @NotNull Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        DtidRequestOnStartupWorker.Companion companion = DtidRequestOnStartupWorker.Companion;
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        companion.schedule(applicationContext);
    }
}
