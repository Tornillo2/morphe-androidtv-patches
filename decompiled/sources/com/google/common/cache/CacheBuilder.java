package com.google.common.cache;

import android.support.v4.media.session.MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Ascii;
import com.google.common.base.Equivalence;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.base.Ticker;
import com.google.common.cache.AbstractCache;
import com.google.common.cache.LocalCache;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import j$.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible(emulated = true)
public final class CacheBuilder<K, V> {
    public static final int DEFAULT_CONCURRENCY_LEVEL = 4;
    public static final int DEFAULT_EXPIRATION_NANOS = 0;
    public static final int DEFAULT_INITIAL_CAPACITY = 16;
    public static final int DEFAULT_REFRESH_NANOS = 0;
    public static final int UNSET_INT = -1;
    public Equivalence<Object> keyEquivalence;
    public LocalCache.Strength keyStrength;
    public RemovalListener<? super K, ? super V> removalListener;
    public Ticker ticker;
    public Equivalence<Object> valueEquivalence;
    public LocalCache.Strength valueStrength;
    public Weigher<? super K, ? super V> weigher;
    public static final Supplier<? extends AbstractCache.StatsCounter> NULL_STATS_COUNTER = new Suppliers.SupplierOfInstance(new AnonymousClass1());
    public static final CacheStats EMPTY_STATS = new CacheStats(0, 0, 0, 0, 0, 0);
    public static final Supplier<AbstractCache.StatsCounter> CACHE_STATS_COUNTER = new AnonymousClass2();
    public static final Ticker NULL_TICKER = new AnonymousClass3();
    public boolean strictParsing = true;
    public int initialCapacity = -1;
    public int concurrencyLevel = -1;
    public long maximumSize = -1;
    public long maximumWeight = -1;
    public long expireAfterWriteNanos = -1;
    public long expireAfterAccessNanos = -1;
    public long refreshNanos = -1;
    public Supplier<? extends AbstractCache.StatsCounter> statsCounterSupplier = NULL_STATS_COUNTER;

    /* JADX INFO: renamed from: com.google.common.cache.CacheBuilder$2, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass2 implements Supplier<AbstractCache.StatsCounter> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.common.base.Supplier
        public AbstractCache.StatsCounter get() {
            return new AbstractCache.SimpleStatsCounter();
        }
    }

    /* JADX INFO: renamed from: com.google.common.cache.CacheBuilder$3, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass3 extends Ticker {
        @Override // com.google.common.base.Ticker
        public long read() {
            return 0L;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class LoggerHolder {
        public static final Logger logger = Logger.getLogger(CacheBuilder.class.getName());
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum OneWeigher implements Weigher<Object, Object> {
        INSTANCE;

        public static /* synthetic */ OneWeigher[] $values() {
            return new OneWeigher[]{INSTANCE};
        }

        @Override // com.google.common.cache.Weigher
        public int weigh(Object key, Object value) {
            return 1;
        }
    }

    @GwtIncompatible
    public static CacheBuilder<Object, Object> from(CacheBuilderSpec spec) {
        CacheBuilder<Object, Object> cacheBuilder = spec.toCacheBuilder();
        cacheBuilder.strictParsing = false;
        return cacheBuilder;
    }

    public static CacheBuilder<Object, Object> newBuilder() {
        return new CacheBuilder<>();
    }

    @IgnoreJRERequirement
    @GwtIncompatible
    public static long toNanosSaturated(Duration duration) {
        try {
            return duration.toNanos();
        } catch (ArithmeticException unused) {
            return duration.isNegative() ? Long.MIN_VALUE : Long.MAX_VALUE;
        }
    }

    public <K1 extends K, V1 extends V> LoadingCache<K1, V1> build(CacheLoader<? super K1, V1> loader) {
        checkWeightWithWeigher();
        return new LocalCache.LocalLoadingCache(this, loader);
    }

    public final void checkNonLoadingCache() {
        Preconditions.checkState(this.refreshNanos == -1, "refreshAfterWrite requires a LoadingCache");
    }

    public final void checkWeightWithWeigher() {
        if (this.weigher == null) {
            Preconditions.checkState(this.maximumWeight == -1, "maximumWeight requires weigher");
        } else if (this.strictParsing) {
            Preconditions.checkState(this.maximumWeight != -1, "weigher requires maximumWeight");
        } else if (this.maximumWeight == -1) {
            LoggerHolder.logger.log(Level.WARNING, "ignoring weigher specified without maximumWeight");
        }
    }

    @CanIgnoreReturnValue
    public CacheBuilder<K, V> concurrencyLevel(int concurrencyLevel) {
        int i = this.concurrencyLevel;
        Preconditions.checkState(i == -1, "concurrency level was already set to %s", i);
        Preconditions.checkArgument(concurrencyLevel > 0);
        this.concurrencyLevel = concurrencyLevel;
        return this;
    }

    @GwtIncompatible
    @CanIgnoreReturnValue
    @IgnoreJRERequirement
    public CacheBuilder<K, V> expireAfterAccess(Duration duration) {
        expireAfterAccess(toNanosSaturated(duration), TimeUnit.NANOSECONDS);
        return this;
    }

    @GwtIncompatible
    @CanIgnoreReturnValue
    @IgnoreJRERequirement
    public CacheBuilder<K, V> expireAfterWrite(Duration duration) {
        expireAfterWrite(toNanosSaturated(duration), TimeUnit.NANOSECONDS);
        return this;
    }

    public int getConcurrencyLevel() {
        int i = this.concurrencyLevel;
        if (i == -1) {
            return 4;
        }
        return i;
    }

    public long getExpireAfterAccessNanos() {
        long j = this.expireAfterAccessNanos;
        if (j == -1) {
            return 0L;
        }
        return j;
    }

    public long getExpireAfterWriteNanos() {
        long j = this.expireAfterWriteNanos;
        if (j == -1) {
            return 0L;
        }
        return j;
    }

    public int getInitialCapacity() {
        int i = this.initialCapacity;
        if (i == -1) {
            return 16;
        }
        return i;
    }

    public Equivalence<Object> getKeyEquivalence() {
        return (Equivalence) MoreObjects.firstNonNull(this.keyEquivalence, getKeyStrength().defaultEquivalence());
    }

    public LocalCache.Strength getKeyStrength() {
        return (LocalCache.Strength) MoreObjects.firstNonNull(this.keyStrength, LocalCache.Strength.STRONG);
    }

    public long getMaximumWeight() {
        if (this.expireAfterWriteNanos == 0 || this.expireAfterAccessNanos == 0) {
            return 0L;
        }
        return this.weigher == null ? this.maximumSize : this.maximumWeight;
    }

    public long getRefreshNanos() {
        long j = this.refreshNanos;
        if (j == -1) {
            return 0L;
        }
        return j;
    }

    public <K1 extends K, V1 extends V> RemovalListener<K1, V1> getRemovalListener() {
        return (RemovalListener) MoreObjects.firstNonNull(this.removalListener, NullListener.INSTANCE);
    }

    public Supplier<? extends AbstractCache.StatsCounter> getStatsCounterSupplier() {
        return this.statsCounterSupplier;
    }

    public Ticker getTicker(boolean recordsTime) {
        Ticker ticker = this.ticker;
        return ticker != null ? ticker : recordsTime ? Ticker.SYSTEM_TICKER : NULL_TICKER;
    }

    public Equivalence<Object> getValueEquivalence() {
        return (Equivalence) MoreObjects.firstNonNull(this.valueEquivalence, getValueStrength().defaultEquivalence());
    }

    public LocalCache.Strength getValueStrength() {
        return (LocalCache.Strength) MoreObjects.firstNonNull(this.valueStrength, LocalCache.Strength.STRONG);
    }

    public <K1 extends K, V1 extends V> Weigher<K1, V1> getWeigher() {
        return (Weigher) MoreObjects.firstNonNull(this.weigher, OneWeigher.INSTANCE);
    }

    @CanIgnoreReturnValue
    public CacheBuilder<K, V> initialCapacity(int initialCapacity) {
        int i = this.initialCapacity;
        Preconditions.checkState(i == -1, "initial capacity was already set to %s", i);
        Preconditions.checkArgument(initialCapacity >= 0);
        this.initialCapacity = initialCapacity;
        return this;
    }

    public boolean isRecordingStats() {
        return this.statsCounterSupplier == CACHE_STATS_COUNTER;
    }

    @CanIgnoreReturnValue
    @GwtIncompatible
    public CacheBuilder<K, V> keyEquivalence(Equivalence<Object> equivalence) {
        Equivalence<Object> equivalence2 = this.keyEquivalence;
        Preconditions.checkState(equivalence2 == null, "key equivalence was already set to %s", equivalence2);
        equivalence.getClass();
        this.keyEquivalence = equivalence;
        return this;
    }

    @CanIgnoreReturnValue
    @GwtIncompatible
    public CacheBuilder<K, V> lenientParsing() {
        this.strictParsing = false;
        return this;
    }

    @CanIgnoreReturnValue
    public CacheBuilder<K, V> maximumSize(long maximumSize) {
        long j = this.maximumSize;
        Preconditions.checkState(j == -1, "maximum size was already set to %s", j);
        long j2 = this.maximumWeight;
        Preconditions.checkState(j2 == -1, "maximum weight was already set to %s", j2);
        Preconditions.checkState(this.weigher == null, "maximum size can not be combined with weigher");
        Preconditions.checkArgument(maximumSize >= 0, "maximum size must not be negative");
        this.maximumSize = maximumSize;
        return this;
    }

    @CanIgnoreReturnValue
    @GwtIncompatible
    public CacheBuilder<K, V> maximumWeight(long maximumWeight) {
        long j = this.maximumWeight;
        Preconditions.checkState(j == -1, "maximum weight was already set to %s", j);
        long j2 = this.maximumSize;
        Preconditions.checkState(j2 == -1, "maximum size was already set to %s", j2);
        Preconditions.checkArgument(maximumWeight >= 0, "maximum weight must not be negative");
        this.maximumWeight = maximumWeight;
        return this;
    }

    @CanIgnoreReturnValue
    public CacheBuilder<K, V> recordStats() {
        this.statsCounterSupplier = CACHE_STATS_COUNTER;
        return this;
    }

    @GwtIncompatible
    @CanIgnoreReturnValue
    @IgnoreJRERequirement
    public CacheBuilder<K, V> refreshAfterWrite(Duration duration) {
        refreshAfterWrite(toNanosSaturated(duration), TimeUnit.NANOSECONDS);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <K1 extends K, V1 extends V> CacheBuilder<K1, V1> removalListener(RemovalListener<? super K1, ? super V1> listener) {
        Preconditions.checkState(this.removalListener == null);
        listener.getClass();
        this.removalListener = listener;
        return this;
    }

    @CanIgnoreReturnValue
    public CacheBuilder<K, V> setKeyStrength(LocalCache.Strength strength) {
        LocalCache.Strength strength2 = this.keyStrength;
        Preconditions.checkState(strength2 == null, "Key strength was already set to %s", strength2);
        strength.getClass();
        this.keyStrength = strength;
        return this;
    }

    @CanIgnoreReturnValue
    public CacheBuilder<K, V> setValueStrength(LocalCache.Strength strength) {
        LocalCache.Strength strength2 = this.valueStrength;
        Preconditions.checkState(strength2 == null, "Value strength was already set to %s", strength2);
        strength.getClass();
        this.valueStrength = strength;
        return this;
    }

    @CanIgnoreReturnValue
    @GwtIncompatible
    public CacheBuilder<K, V> softValues() {
        setValueStrength(LocalCache.Strength.SOFT);
        return this;
    }

    @CanIgnoreReturnValue
    public CacheBuilder<K, V> ticker(Ticker ticker) {
        Preconditions.checkState(this.ticker == null);
        ticker.getClass();
        this.ticker = ticker;
        return this;
    }

    public String toString() {
        MoreObjects.ToStringHelper stringHelper = MoreObjects.toStringHelper(this);
        int i = this.initialCapacity;
        if (i != -1) {
            stringHelper.add("initialCapacity", i);
        }
        int i2 = this.concurrencyLevel;
        if (i2 != -1) {
            stringHelper.add("concurrencyLevel", i2);
        }
        long j = this.maximumSize;
        if (j != -1) {
            stringHelper.add("maximumSize", j);
        }
        long j2 = this.maximumWeight;
        if (j2 != -1) {
            stringHelper.add("maximumWeight", j2);
        }
        if (this.expireAfterWriteNanos != -1) {
            stringHelper.addHolder("expireAfterWrite", MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0.m(new StringBuilder(), this.expireAfterWriteNanos, "ns"));
        }
        if (this.expireAfterAccessNanos != -1) {
            stringHelper.addHolder("expireAfterAccess", MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0.m(new StringBuilder(), this.expireAfterAccessNanos, "ns"));
        }
        LocalCache.Strength strength = this.keyStrength;
        if (strength != null) {
            stringHelper.addHolder("keyStrength", Ascii.toLowerCase(strength.toString()));
        }
        LocalCache.Strength strength2 = this.valueStrength;
        if (strength2 != null) {
            stringHelper.addHolder("valueStrength", Ascii.toLowerCase(strength2.toString()));
        }
        if (this.keyEquivalence != null) {
            stringHelper.addValue("keyEquivalence");
        }
        if (this.valueEquivalence != null) {
            stringHelper.addValue("valueEquivalence");
        }
        if (this.removalListener != null) {
            stringHelper.addValue("removalListener");
        }
        return stringHelper.toString();
    }

    @CanIgnoreReturnValue
    @GwtIncompatible
    public CacheBuilder<K, V> valueEquivalence(Equivalence<Object> equivalence) {
        Equivalence<Object> equivalence2 = this.valueEquivalence;
        Preconditions.checkState(equivalence2 == null, "value equivalence was already set to %s", equivalence2);
        equivalence.getClass();
        this.valueEquivalence = equivalence;
        return this;
    }

    @CanIgnoreReturnValue
    @GwtIncompatible
    public CacheBuilder<K, V> weakKeys() {
        setKeyStrength(LocalCache.Strength.WEAK);
        return this;
    }

    @CanIgnoreReturnValue
    @GwtIncompatible
    public CacheBuilder<K, V> weakValues() {
        setValueStrength(LocalCache.Strength.WEAK);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @CanIgnoreReturnValue
    @GwtIncompatible
    public <K1 extends K, V1 extends V> CacheBuilder<K1, V1> weigher(Weigher<? super K1, ? super V1> weigher) {
        Preconditions.checkState(this.weigher == null);
        if (this.strictParsing) {
            long j = this.maximumSize;
            Preconditions.checkState(j == -1, "weigher can not be combined with maximum size (%s provided)", j);
        }
        weigher.getClass();
        this.weigher = weigher;
        return this;
    }

    @CanIgnoreReturnValue
    public CacheBuilder<K, V> expireAfterAccess(long duration, TimeUnit unit) {
        long j = this.expireAfterAccessNanos;
        Preconditions.checkState(j == -1, "expireAfterAccess was already set to %s ns", j);
        Preconditions.checkArgument(duration >= 0, "duration cannot be negative: %s %s", duration, unit);
        this.expireAfterAccessNanos = unit.toNanos(duration);
        return this;
    }

    @CanIgnoreReturnValue
    public CacheBuilder<K, V> expireAfterWrite(long duration, TimeUnit unit) {
        long j = this.expireAfterWriteNanos;
        Preconditions.checkState(j == -1, "expireAfterWrite was already set to %s ns", j);
        Preconditions.checkArgument(duration >= 0, "duration cannot be negative: %s %s", duration, unit);
        this.expireAfterWriteNanos = unit.toNanos(duration);
        return this;
    }

    @CanIgnoreReturnValue
    @GwtIncompatible
    public CacheBuilder<K, V> refreshAfterWrite(long duration, TimeUnit unit) {
        unit.getClass();
        long j = this.refreshNanos;
        Preconditions.checkState(j == -1, "refresh was already set to %s ns", j);
        Preconditions.checkArgument(duration > 0, "duration must be positive: %s %s", duration, unit);
        this.refreshNanos = unit.toNanos(duration);
        return this;
    }

    @GwtIncompatible
    public static CacheBuilder<Object, Object> from(String spec) {
        CacheBuilder<Object, Object> cacheBuilder = CacheBuilderSpec.parse(spec).toCacheBuilder();
        cacheBuilder.strictParsing = false;
        return cacheBuilder;
    }

    public <K1 extends K, V1 extends V> Cache<K1, V1> build() {
        checkWeightWithWeigher();
        checkNonLoadingCache();
        return new LocalCache.LocalManualCache(this);
    }

    /* JADX INFO: renamed from: com.google.common.cache.CacheBuilder$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 implements AbstractCache.StatsCounter {
        @Override // com.google.common.cache.AbstractCache.StatsCounter
        public CacheStats snapshot() {
            return CacheBuilder.EMPTY_STATS;
        }

        @Override // com.google.common.cache.AbstractCache.StatsCounter
        public void recordEviction() {
        }

        @Override // com.google.common.cache.AbstractCache.StatsCounter
        public void recordHits(int count) {
        }

        @Override // com.google.common.cache.AbstractCache.StatsCounter
        public void recordLoadException(long loadTime) {
        }

        @Override // com.google.common.cache.AbstractCache.StatsCounter
        public void recordLoadSuccess(long loadTime) {
        }

        @Override // com.google.common.cache.AbstractCache.StatsCounter
        public void recordMisses(int count) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum NullListener implements RemovalListener<Object, Object> {
        INSTANCE;

        public static /* synthetic */ NullListener[] $values() {
            return new NullListener[]{INSTANCE};
        }

        @Override // com.google.common.cache.RemovalListener
        public void onRemoval(RemovalNotification<Object, Object> notification) {
        }
    }
}
