package com.amazon.avod.mpb.threading;

import androidx.collection.ObjectListKt$$ExternalSyntheticOutline1;
import com.amazon.avod.mpb.threading.internal.NamedThreadFactory;
import com.amazon.avod.mpb.util.Preconditions2;
import com.google.common.base.Preconditions;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnegative;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class ScheduledExecutorBuilder {
    public boolean allowCoreThreadExpiry;
    public long coreThreadExpiryTimeQuantity;

    @NotNull
    public TimeUnit coreThreadExpiryTimeUnit;

    @NotNull
    public final String name;
    public int threadPoolSize;

    public ScheduledExecutorBuilder(@NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.name = name;
        this.coreThreadExpiryTimeUnit = TimeUnit.SECONDS;
        this.threadPoolSize = 1;
    }

    @NotNull
    public final ScheduledExecutorBuilder allowCoreThreadExpiry(@Nonnegative long j, @NotNull TimeUnit timeUnit) {
        Intrinsics.checkNotNullParameter(timeUnit, "timeUnit");
        Preconditions2.checkNonNegative(j, "time");
        this.coreThreadExpiryTimeQuantity = j;
        this.coreThreadExpiryTimeUnit = timeUnit;
        this.allowCoreThreadExpiry = true;
        return this;
    }

    @NotNull
    public final ScheduledThreadPoolExecutor build() {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(this.threadPoolSize, new NamedThreadFactory(this.name, 5));
        if (this.allowCoreThreadExpiry) {
            scheduledThreadPoolExecutor.setKeepAliveTime(this.coreThreadExpiryTimeQuantity, this.coreThreadExpiryTimeUnit);
            scheduledThreadPoolExecutor.allowCoreThreadTimeOut(true);
        }
        return scheduledThreadPoolExecutor;
    }

    @NotNull
    public final ScheduledExecutorBuilder withFixedThreadPoolSize(@Nonnegative int i) {
        Preconditions.checkArgument(i > 0, ObjectListKt$$ExternalSyntheticOutline1.m("threadPoolSize <= 0 (", i, ")"), new Object[0]);
        this.threadPoolSize = i;
        return this;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ScheduledExecutorBuilder(@NotNull Class<?> cls) {
        this(cls.getSimpleName());
        Intrinsics.checkNotNullParameter(cls, "cls");
    }
}
