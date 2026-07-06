package com.google.android.datatransport.runtime.backends;

import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.datatransport.runtime.time.Clock;
import com.google.auto.value.AutoValue;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@AutoValue
public abstract class CreationContext {
    public static final String DEFAULT_BACKEND_NAME = "cct";

    public static CreationContext create(Context context, Clock clock, Clock clock2) {
        return new AutoValue_CreationContext(context, clock, clock2, "cct");
    }

    public abstract Context getApplicationContext();

    @NonNull
    public abstract String getBackendName();

    public abstract Clock getMonotonicClock();

    public abstract Clock getWallClock();

    public static CreationContext create(Context context, Clock clock, Clock clock2, String str) {
        return new AutoValue_CreationContext(context, clock, clock2, str);
    }
}
