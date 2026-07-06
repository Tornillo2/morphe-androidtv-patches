package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.scheduling.persistence.AutoValue_EventStoreConfig;
import com.google.auto.value.AutoValue;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@AutoValue
public abstract class EventStoreConfig {
    public static final EventStoreConfig DEFAULT;
    public static final long DURATION_ONE_WEEK_MS = 604800000;
    public static final int LOAD_BATCH_SIZE = 200;
    public static final int LOCK_TIME_OUT_MS = 10000;
    public static final int MAX_BLOB_BYTE_SIZE_PER_ROW = 81920;
    public static final long MAX_DB_STORAGE_SIZE_IN_BYTES = 10485760;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @AutoValue.Builder
    public static abstract class Builder {
        public abstract EventStoreConfig build();

        public abstract Builder setCriticalSectionEnterTimeoutMs(int i);

        public abstract Builder setEventCleanUpAge(long j);

        public abstract Builder setLoadBatchSize(int i);

        public abstract Builder setMaxBlobByteSizePerRow(int i);

        public abstract Builder setMaxStorageSizeInBytes(long j);
    }

    static {
        AutoValue_EventStoreConfig.Builder builder = new AutoValue_EventStoreConfig.Builder();
        builder.maxStorageSizeInBytes = 10485760L;
        builder.loadBatchSize = 200;
        builder.criticalSectionEnterTimeoutMs = 10000;
        builder.eventCleanUpAge = Long.valueOf(DURATION_ONE_WEEK_MS);
        builder.maxBlobByteSizePerRow = Integer.valueOf(MAX_BLOB_BYTE_SIZE_PER_ROW);
        DEFAULT = builder.build();
    }

    public static Builder builder() {
        return new AutoValue_EventStoreConfig.Builder();
    }

    public abstract int getCriticalSectionEnterTimeoutMs();

    public abstract long getEventCleanUpAge();

    public abstract int getLoadBatchSize();

    public abstract int getMaxBlobByteSizePerRow();

    public abstract long getMaxStorageSizeInBytes();

    public Builder toBuilder() {
        AutoValue_EventStoreConfig.Builder builder = new AutoValue_EventStoreConfig.Builder();
        builder.maxStorageSizeInBytes = Long.valueOf(getMaxStorageSizeInBytes());
        builder.loadBatchSize = Integer.valueOf(getLoadBatchSize());
        builder.criticalSectionEnterTimeoutMs = Integer.valueOf(getCriticalSectionEnterTimeoutMs());
        builder.eventCleanUpAge = Long.valueOf(getEventCleanUpAge());
        builder.maxBlobByteSizePerRow = Integer.valueOf(getMaxBlobByteSizePerRow());
        return builder;
    }
}
