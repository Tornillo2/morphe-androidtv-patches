package com.google.android.datatransport.runtime.scheduling;

import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig;
import com.google.android.datatransport.runtime.time.Clock;
import javax.inject.Provider;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class SchedulingConfigModule_ConfigFactory implements Factory<SchedulerConfig> {
    public final Provider<Clock> clockProvider;

    public SchedulingConfigModule_ConfigFactory(Provider<Clock> provider) {
        this.clockProvider = provider;
    }

    public static SchedulerConfig config(Clock clock) {
        return SchedulerConfig.getDefault(clock);
    }

    public static SchedulingConfigModule_ConfigFactory create(Provider<Clock> provider) {
        return new SchedulingConfigModule_ConfigFactory(provider);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public SchedulerConfig get() {
        return SchedulerConfig.getDefault(this.clockProvider.get());
    }
}
