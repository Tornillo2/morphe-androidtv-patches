package com.google.common.eventbus;

import androidx.core.app.NotificationCompat;
import com.bumptech.glide.load.engine.executor.GlideExecutor;
import com.google.common.base.MoreObjects;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class DeadEvent {
    public final Object event;
    public final Object source;

    public DeadEvent(Object source, Object event) {
        source.getClass();
        this.source = source;
        event.getClass();
        this.event = event;
    }

    public Object getEvent() {
        return this.event;
    }

    public Object getSource() {
        return this.source;
    }

    public String toString() {
        MoreObjects.ToStringHelper stringHelper = MoreObjects.toStringHelper(this);
        stringHelper.addHolder(GlideExecutor.DEFAULT_SOURCE_EXECUTOR_NAME, this.source);
        stringHelper.addHolder(NotificationCompat.CATEGORY_EVENT, this.event);
        return stringHelper.toString();
    }
}
