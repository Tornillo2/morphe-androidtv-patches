package com.google.android.datatransport;

import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@AutoValue
public abstract class Event<T> {
    public static <T> Event<T> ofData(int i, T t) {
        return new AutoValue_Event(Integer.valueOf(i), t, Priority.DEFAULT);
    }

    public static <T> Event<T> ofTelemetry(int i, T t) {
        return new AutoValue_Event(Integer.valueOf(i), t, Priority.VERY_LOW);
    }

    public static <T> Event<T> ofUrgent(int i, T t) {
        return new AutoValue_Event(Integer.valueOf(i), t, Priority.HIGHEST);
    }

    @Nullable
    public abstract Integer getCode();

    public abstract T getPayload();

    public abstract Priority getPriority();

    public static <T> Event<T> ofData(T t) {
        return new AutoValue_Event(null, t, Priority.DEFAULT);
    }

    public static <T> Event<T> ofTelemetry(T t) {
        return new AutoValue_Event(null, t, Priority.VERY_LOW);
    }

    public static <T> Event<T> ofUrgent(T t) {
        return new AutoValue_Event(null, t, Priority.HIGHEST);
    }
}
