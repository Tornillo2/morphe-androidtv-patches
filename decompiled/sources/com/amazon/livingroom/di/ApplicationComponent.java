package com.amazon.livingroom.di;

import com.amazon.ignitionshared.receiver.BootUpReceiver;
import com.amazon.ignitionshared.receiver.ScheduleRecommendationsOnInstallReceiver;
import com.amazon.ignitionshared.service.AppStartupConfigCacheRefresher;
import com.amazon.ignitionshared.service.ClearWatchNextWorker;
import com.amazon.ignitionshared.service.DtidRequestOnStartupWorker;
import com.amazon.ignitionshared.service.PeriodicUpdateRecommendationsWorker;
import com.amazon.ignitionshared.service.UpdateRecommendationsWorker;
import com.amazon.ignitionshared.service.UpdateWatchNextWorker;
import com.amazon.livingroom.di.MainActivityComponent;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface ApplicationComponent {

    @NotNull
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static ApplicationComponent instance;

        @NotNull
        public final ApplicationComponent getInstance() {
            ApplicationComponent applicationComponent = instance;
            if (applicationComponent != null) {
                return applicationComponent;
            }
            Intrinsics.throwUninitializedPropertyAccessException("instance");
            throw null;
        }

        public final void setInstance(@NotNull ApplicationComponent applicationComponent) {
            Intrinsics.checkNotNullParameter(applicationComponent, "<set-?>");
            instance = applicationComponent;
        }
    }

    void inject(@NotNull BootUpReceiver bootUpReceiver);

    void inject(@NotNull ScheduleRecommendationsOnInstallReceiver scheduleRecommendationsOnInstallReceiver);

    void inject(@NotNull AppStartupConfigCacheRefresher.InternalWorker internalWorker);

    void inject(@NotNull ClearWatchNextWorker clearWatchNextWorker);

    void inject(@NotNull DtidRequestOnStartupWorker dtidRequestOnStartupWorker);

    void inject(@NotNull PeriodicUpdateRecommendationsWorker periodicUpdateRecommendationsWorker);

    void inject(@NotNull UpdateRecommendationsWorker updateRecommendationsWorker);

    void inject(@NotNull UpdateWatchNextWorker updateWatchNextWorker);

    @NotNull
    MainActivityComponent.Builder mainActivityComponent();
}
