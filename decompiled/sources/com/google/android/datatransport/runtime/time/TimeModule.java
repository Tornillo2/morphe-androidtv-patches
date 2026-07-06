package com.google.android.datatransport.runtime.time;

import com.google.android.datatransport.runtime.dagger.Module;
import com.google.android.datatransport.runtime.dagger.Provides;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Module
public abstract class TimeModule {
    @Provides
    @WallTime
    public static Clock eventClock() {
        return new WallTimeClock();
    }

    @Provides
    @Monotonic
    public static Clock uptimeClock() {
        return new UptimeClock();
    }
}
