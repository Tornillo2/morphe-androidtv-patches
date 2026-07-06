package com.google.android.datatransport;

import androidx.annotation.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class AutoValue_Event<T> extends Event<T> {
    public final Integer code;
    public final T payload;
    public final Priority priority;

    public AutoValue_Event(@Nullable Integer num, T t, Priority priority) {
        this.code = num;
        if (t == null) {
            throw new NullPointerException("Null payload");
        }
        this.payload = t;
        if (priority == null) {
            throw new NullPointerException("Null priority");
        }
        this.priority = priority;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Event)) {
            return false;
        }
        Event event = (Event) obj;
        Integer num = this.code;
        if (num == null) {
            if (event.getCode() != null) {
                return false;
            }
        } else if (!num.equals(event.getCode())) {
            return false;
        }
        return this.payload.equals(event.getPayload()) && this.priority.equals(event.getPriority());
    }

    @Override // com.google.android.datatransport.Event
    @Nullable
    public Integer getCode() {
        return this.code;
    }

    @Override // com.google.android.datatransport.Event
    public T getPayload() {
        return this.payload;
    }

    @Override // com.google.android.datatransport.Event
    public Priority getPriority() {
        return this.priority;
    }

    public int hashCode() {
        Integer num = this.code;
        return (((((num == null ? 0 : num.hashCode()) ^ 1000003) * 1000003) ^ this.payload.hashCode()) * 1000003) ^ this.priority.hashCode();
    }

    public String toString() {
        return "Event{code=" + this.code + ", payload=" + this.payload + ", priority=" + this.priority + "}";
    }
}
