package com.amazon.primevideo.nativebilling;

import android.os.Handler;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class BillingUtils {

    @NotNull
    public static final Companion Companion = new Companion();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public final void retryWithExponentialBackoff(int i, @NotNull Handler handler, @NotNull Function0<Unit> onExceedTries, @NotNull final Function0<Unit> operationToRetry) {
            Intrinsics.checkNotNullParameter(handler, "handler");
            Intrinsics.checkNotNullParameter(onExceedTries, "onExceedTries");
            Intrinsics.checkNotNullParameter(operationToRetry, "operationToRetry");
            if (i > 3) {
                onExceedTries.invoke();
            } else {
                handler.postDelayed(new Runnable() { // from class: com.amazon.primevideo.nativebilling.BillingUtils$Companion$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        operationToRetry.invoke();
                    }
                }, (long) (Math.pow(2.0d, i) * ((double) 500)));
            }
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }
}
