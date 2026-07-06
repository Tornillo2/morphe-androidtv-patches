package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.app.job.JobInfo;
import androidx.annotation.RequiresApi;
import com.google.android.datatransport.Priority;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.AutoValue_SchedulerConfig_ConfigValue;
import com.google.android.datatransport.runtime.time.Clock;
import com.google.auto.value.AutoValue;
import j$.util.DesugarCollections;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@AutoValue
public abstract class SchedulerConfig {
    public static final long BACKOFF_LOG_BASE = 10000;
    public static final long ONE_SECOND = 1000;
    public static final long THIRTY_SECONDS = 30000;
    public static final long TWENTY_FOUR_HOURS = 86400000;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Builder {
        public Clock clock;
        public Map<Priority, ConfigValue> values = new HashMap();

        public Builder addConfig(Priority priority, ConfigValue configValue) {
            this.values.put(priority, configValue);
            return this;
        }

        public SchedulerConfig build() {
            if (this.clock == null) {
                throw new NullPointerException("missing required property: clock");
            }
            if (this.values.keySet().size() < Priority.values().length) {
                throw new IllegalStateException("Not all priorities have been configured");
            }
            Map<Priority, ConfigValue> map = this.values;
            this.values = new HashMap();
            return new AutoValue_SchedulerConfig(this.clock, map);
        }

        public Builder setClock(Clock clock) {
            this.clock = clock;
            return this;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @AutoValue
    public static abstract class ConfigValue {

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        @AutoValue.Builder
        public static abstract class Builder {
            public abstract ConfigValue build();

            public abstract Builder setDelta(long j);

            public abstract Builder setFlags(Set<Flag> set);

            public abstract Builder setMaxAllowedDelay(long j);
        }

        public static Builder builder() {
            AutoValue_SchedulerConfig_ConfigValue.Builder builder = new AutoValue_SchedulerConfig_ConfigValue.Builder();
            builder.setFlags(Collections.EMPTY_SET);
            return builder;
        }

        public abstract long getDelta();

        public abstract Set<Flag> getFlags();

        public abstract long getMaxAllowedDelay();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum Flag {
        NETWORK_UNMETERED,
        DEVICE_IDLE,
        DEVICE_CHARGING
    }

    public static Builder builder() {
        return new Builder();
    }

    public static SchedulerConfig create(Clock clock, Map<Priority, ConfigValue> map) {
        return new AutoValue_SchedulerConfig(clock, map);
    }

    public static SchedulerConfig getDefault(Clock clock) {
        Builder builder = new Builder();
        Priority priority = Priority.DEFAULT;
        AutoValue_SchedulerConfig_ConfigValue.Builder builder2 = (AutoValue_SchedulerConfig_ConfigValue.Builder) ConfigValue.builder();
        builder2.delta = 30000L;
        builder2.maxAllowedDelay = 86400000L;
        builder.values.put(priority, builder2.build());
        Priority priority2 = Priority.HIGHEST;
        AutoValue_SchedulerConfig_ConfigValue.Builder builder3 = (AutoValue_SchedulerConfig_ConfigValue.Builder) ConfigValue.builder();
        builder3.delta = 1000L;
        builder3.maxAllowedDelay = 86400000L;
        builder.values.put(priority2, builder3.build());
        Priority priority3 = Priority.VERY_LOW;
        AutoValue_SchedulerConfig_ConfigValue.Builder builder4 = (AutoValue_SchedulerConfig_ConfigValue.Builder) ConfigValue.builder();
        builder4.delta = 86400000L;
        builder4.maxAllowedDelay = 86400000L;
        builder4.setFlags(immutableSetOf(Flag.DEVICE_IDLE));
        builder.values.put(priority3, builder4.build());
        builder.clock = clock;
        return builder.build();
    }

    public static <T> Set<T> immutableSetOf(T... tArr) {
        return DesugarCollections.unmodifiableSet(new HashSet(Arrays.asList(tArr)));
    }

    public final long adjustedExponentialBackoff(int i, long j) {
        return (long) (Math.pow(3.0d, i - 1) * j * Math.max(1.0d, Math.log(10000.0d) / Math.log((j > 1 ? j : 2L) * ((long) r7))));
    }

    @RequiresApi(api = 21)
    public JobInfo.Builder configureJob(JobInfo.Builder builder, Priority priority, long j, int i) {
        builder.setMinimumLatency(getScheduleDelay(priority, j, i));
        populateFlags(builder, getValues().get(priority).getFlags());
        return builder;
    }

    public abstract Clock getClock();

    public Set<Flag> getFlags(Priority priority) {
        return getValues().get(priority).getFlags();
    }

    public long getScheduleDelay(Priority priority, long j, int i) {
        long time = j - getClock().getTime();
        ConfigValue configValue = getValues().get(priority);
        return Math.min(Math.max(adjustedExponentialBackoff(i, configValue.getDelta()), time), configValue.getMaxAllowedDelay());
    }

    public abstract Map<Priority, ConfigValue> getValues();

    @RequiresApi(api = 21)
    public final void populateFlags(JobInfo.Builder builder, Set<Flag> set) {
        if (set.contains(Flag.NETWORK_UNMETERED)) {
            builder.setRequiredNetworkType(2);
        } else {
            builder.setRequiredNetworkType(1);
        }
        if (set.contains(Flag.DEVICE_CHARGING)) {
            builder.setRequiresCharging(true);
        }
        if (set.contains(Flag.DEVICE_IDLE)) {
            builder.setRequiresDeviceIdle(true);
        }
    }
}
