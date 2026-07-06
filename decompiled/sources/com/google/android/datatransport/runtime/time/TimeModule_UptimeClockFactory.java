package com.google.android.datatransport.runtime.time;

import com.google.android.datatransport.runtime.dagger.internal.Factory;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class TimeModule_UptimeClockFactory implements Factory<Clock> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class InstanceHolder {
        public static final TimeModule_UptimeClockFactory INSTANCE = new TimeModule_UptimeClockFactory();
    }

    public static TimeModule_UptimeClockFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static Clock uptimeClock() {
        return new UptimeClock();
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public Clock get() {
        return new UptimeClock();
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public Object get() {
        return new UptimeClock();
    }
}
