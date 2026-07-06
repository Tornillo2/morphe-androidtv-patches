package com.google.common.cache;

import android.support.v4.media.MediaMetadataCompat$Builder$$ExternalSyntheticOutline0;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.CharMatcher;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Platform;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.cache.LocalCache;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtIncompatible
public final class CacheBuilderSpec {
    public static final Splitter KEYS_SPLITTER;
    public static final Splitter KEY_VALUE_SPLITTER;
    public static final ImmutableMap<String, ValueParser> VALUE_PARSERS;

    @VisibleForTesting
    public long accessExpirationDuration;

    @VisibleForTesting
    public TimeUnit accessExpirationTimeUnit;

    @VisibleForTesting
    public Integer concurrencyLevel;

    @VisibleForTesting
    public Integer initialCapacity;

    @VisibleForTesting
    public LocalCache.Strength keyStrength;

    @VisibleForTesting
    public Long maximumSize;

    @VisibleForTesting
    public Long maximumWeight;

    @VisibleForTesting
    public Boolean recordStats;

    @VisibleForTesting
    public long refreshDuration;

    @VisibleForTesting
    public TimeUnit refreshTimeUnit;
    public final String specification;

    @VisibleForTesting
    public LocalCache.Strength valueStrength;

    @VisibleForTesting
    public long writeExpirationDuration;

    @VisibleForTesting
    public TimeUnit writeExpirationTimeUnit;

    /* JADX INFO: renamed from: com.google.common.cache.CacheBuilderSpec$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$google$common$cache$LocalCache$Strength;

        static {
            int[] iArr = new int[LocalCache.Strength.values().length];
            $SwitchMap$com$google$common$cache$LocalCache$Strength = iArr;
            try {
                iArr[LocalCache.Strength.WEAK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$common$cache$LocalCache$Strength[LocalCache.Strength.SOFT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class AccessDurationParser extends DurationParser {
        @Override // com.google.common.cache.CacheBuilderSpec.DurationParser
        public void parseDuration(CacheBuilderSpec spec, long duration, TimeUnit unit) {
            Preconditions.checkArgument(spec.accessExpirationTimeUnit == null, "expireAfterAccess already set");
            spec.accessExpirationDuration = duration;
            spec.accessExpirationTimeUnit = unit;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class ConcurrencyLevelParser extends IntegerParser {
        @Override // com.google.common.cache.CacheBuilderSpec.IntegerParser
        public void parseInteger(CacheBuilderSpec spec, int value) {
            Integer num = spec.concurrencyLevel;
            Preconditions.checkArgument(num == null, "concurrency level was already set to %s", num);
            spec.concurrencyLevel = Integer.valueOf(value);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class DurationParser implements ValueParser {
        @Override // com.google.common.cache.CacheBuilderSpec.ValueParser
        public void parse(CacheBuilderSpec spec, String key, String value) {
            TimeUnit timeUnit;
            if (Platform.stringIsNullOrEmpty(value)) {
                throw new IllegalArgumentException(MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("value of key ", key, " omitted"));
            }
            try {
                char cCharAt = value.charAt(value.length() - 1);
                if (cCharAt == 'd') {
                    timeUnit = TimeUnit.DAYS;
                } else if (cCharAt == 'h') {
                    timeUnit = TimeUnit.HOURS;
                } else if (cCharAt == 'm') {
                    timeUnit = TimeUnit.MINUTES;
                } else {
                    if (cCharAt != 's') {
                        throw new IllegalArgumentException(CacheBuilderSpec.access$000("key %s invalid unit: was %s, must end with one of [dhms]", new Object[]{key, value}));
                    }
                    timeUnit = TimeUnit.SECONDS;
                }
                parseDuration(spec, Long.parseLong(value.substring(0, value.length() - 1)), timeUnit);
            } catch (NumberFormatException unused) {
                throw new IllegalArgumentException(CacheBuilderSpec.access$000("key %s value set to %s, must be integer", new Object[]{key, value}));
            }
        }

        public abstract void parseDuration(CacheBuilderSpec spec, long duration, TimeUnit unit);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class InitialCapacityParser extends IntegerParser {
        @Override // com.google.common.cache.CacheBuilderSpec.IntegerParser
        public void parseInteger(CacheBuilderSpec spec, int value) {
            Integer num = spec.initialCapacity;
            Preconditions.checkArgument(num == null, "initial capacity was already set to %s", num);
            spec.initialCapacity = Integer.valueOf(value);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class IntegerParser implements ValueParser {
        @Override // com.google.common.cache.CacheBuilderSpec.ValueParser
        public void parse(CacheBuilderSpec spec, String key, String value) {
            if (Platform.stringIsNullOrEmpty(value)) {
                throw new IllegalArgumentException(MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("value of key ", key, " omitted"));
            }
            try {
                parseInteger(spec, Integer.parseInt(value));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(CacheBuilderSpec.access$000("key %s value set to %s, must be integer", new Object[]{key, value}), e);
            }
        }

        public abstract void parseInteger(CacheBuilderSpec spec, int value);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class KeyStrengthParser implements ValueParser {
        public final LocalCache.Strength strength;

        public KeyStrengthParser(LocalCache.Strength strength) {
            this.strength = strength;
        }

        @Override // com.google.common.cache.CacheBuilderSpec.ValueParser
        public void parse(CacheBuilderSpec spec, String key, String value) {
            Preconditions.checkArgument(value == null, "key %s does not take values", key);
            LocalCache.Strength strength = spec.keyStrength;
            Preconditions.checkArgument(strength == null, "%s was already set to %s", key, strength);
            spec.keyStrength = this.strength;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class LongParser implements ValueParser {
        @Override // com.google.common.cache.CacheBuilderSpec.ValueParser
        public void parse(CacheBuilderSpec spec, String key, String value) {
            if (Platform.stringIsNullOrEmpty(value)) {
                throw new IllegalArgumentException(MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("value of key ", key, " omitted"));
            }
            try {
                parseLong(spec, Long.parseLong(value));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(CacheBuilderSpec.access$000("key %s value set to %s, must be integer", new Object[]{key, value}), e);
            }
        }

        public abstract void parseLong(CacheBuilderSpec spec, long value);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class MaximumSizeParser extends LongParser {
        @Override // com.google.common.cache.CacheBuilderSpec.LongParser
        public void parseLong(CacheBuilderSpec spec, long value) {
            Long l = spec.maximumSize;
            Preconditions.checkArgument(l == null, "maximum size was already set to %s", l);
            Long l2 = spec.maximumWeight;
            Preconditions.checkArgument(l2 == null, "maximum weight was already set to %s", l2);
            spec.maximumSize = Long.valueOf(value);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class MaximumWeightParser extends LongParser {
        @Override // com.google.common.cache.CacheBuilderSpec.LongParser
        public void parseLong(CacheBuilderSpec spec, long value) {
            Long l = spec.maximumWeight;
            Preconditions.checkArgument(l == null, "maximum weight was already set to %s", l);
            Long l2 = spec.maximumSize;
            Preconditions.checkArgument(l2 == null, "maximum size was already set to %s", l2);
            spec.maximumWeight = Long.valueOf(value);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class RecordStatsParser implements ValueParser {
        @Override // com.google.common.cache.CacheBuilderSpec.ValueParser
        public void parse(CacheBuilderSpec spec, String key, String value) {
            Preconditions.checkArgument(value == null, "recordStats does not take values");
            Preconditions.checkArgument(spec.recordStats == null, "recordStats already set");
            spec.recordStats = Boolean.TRUE;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class RefreshDurationParser extends DurationParser {
        @Override // com.google.common.cache.CacheBuilderSpec.DurationParser
        public void parseDuration(CacheBuilderSpec spec, long duration, TimeUnit unit) {
            Preconditions.checkArgument(spec.refreshTimeUnit == null, "refreshAfterWrite already set");
            spec.refreshDuration = duration;
            spec.refreshTimeUnit = unit;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface ValueParser {
        void parse(CacheBuilderSpec spec, String key, String value);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class ValueStrengthParser implements ValueParser {
        public final LocalCache.Strength strength;

        public ValueStrengthParser(LocalCache.Strength strength) {
            this.strength = strength;
        }

        @Override // com.google.common.cache.CacheBuilderSpec.ValueParser
        public void parse(CacheBuilderSpec spec, String key, String value) {
            Preconditions.checkArgument(value == null, "key %s does not take values", key);
            LocalCache.Strength strength = spec.valueStrength;
            Preconditions.checkArgument(strength == null, "%s was already set to %s", key, strength);
            spec.valueStrength = this.strength;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class WriteDurationParser extends DurationParser {
        @Override // com.google.common.cache.CacheBuilderSpec.DurationParser
        public void parseDuration(CacheBuilderSpec spec, long duration, TimeUnit unit) {
            Preconditions.checkArgument(spec.writeExpirationTimeUnit == null, "expireAfterWrite already set");
            spec.writeExpirationDuration = duration;
            spec.writeExpirationTimeUnit = unit;
        }
    }

    static {
        Splitter splitterOn = Splitter.on(',');
        CharMatcher charMatcher = CharMatcher.Whitespace.INSTANCE;
        KEYS_SPLITTER = splitterOn.trimResults(charMatcher);
        KEY_VALUE_SPLITTER = Splitter.on('=').trimResults(charMatcher);
        ImmutableMap.Builder builder = ImmutableMap.builder();
        builder.put("initialCapacity", new InitialCapacityParser());
        builder.put("maximumSize", new MaximumSizeParser());
        builder.put("maximumWeight", new MaximumWeightParser());
        builder.put("concurrencyLevel", new ConcurrencyLevelParser());
        LocalCache.Strength strength = LocalCache.Strength.WEAK;
        builder.put("weakKeys", new KeyStrengthParser(strength));
        builder.put("softValues", new ValueStrengthParser(LocalCache.Strength.SOFT));
        builder.put("weakValues", new ValueStrengthParser(strength));
        builder.put("recordStats", new RecordStatsParser());
        builder.put("expireAfterAccess", new AccessDurationParser());
        builder.put("expireAfterWrite", new WriteDurationParser());
        builder.put("refreshAfterWrite", new RefreshDurationParser());
        builder.put("refreshInterval", new RefreshDurationParser());
        VALUE_PARSERS = builder.build(true);
    }

    public CacheBuilderSpec(String specification) {
        this.specification = specification;
    }

    public static String access$000(String str, Object[] objArr) {
        return String.format(Locale.ROOT, str, objArr);
    }

    public static CacheBuilderSpec disableCaching() {
        return parse("maximumSize=0");
    }

    public static Long durationInNanos(long duration, TimeUnit unit) {
        if (unit == null) {
            return null;
        }
        return Long.valueOf(unit.toNanos(duration));
    }

    public static String format(String format, Object... args) {
        return String.format(Locale.ROOT, format, args);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static CacheBuilderSpec parse(String cacheBuilderSpecification) {
        CacheBuilderSpec cacheBuilderSpec = new CacheBuilderSpec(cacheBuilderSpecification);
        if (!cacheBuilderSpecification.isEmpty()) {
            Splitter splitter = KEYS_SPLITTER;
            splitter.getClass();
            for (String str : new Splitter.AnonymousClass5(splitter, cacheBuilderSpecification)) {
                ImmutableList immutableListCopyOf = ImmutableList.copyOf(KEY_VALUE_SPLITTER.split(str));
                Preconditions.checkArgument(!immutableListCopyOf.isEmpty(), "blank key-value pair");
                Preconditions.checkArgument(immutableListCopyOf.size() <= 2, "key-value pair %s with more than one equals sign", str);
                String str2 = (String) immutableListCopyOf.get(0);
                ValueParser valueParser = VALUE_PARSERS.get(str2);
                Preconditions.checkArgument(valueParser != null, "unknown key %s", str2);
                valueParser.parse(cacheBuilderSpec, str2, immutableListCopyOf.size() == 1 ? null : (String) immutableListCopyOf.get(1));
            }
        }
        return cacheBuilderSpec;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CacheBuilderSpec)) {
            return false;
        }
        CacheBuilderSpec cacheBuilderSpec = (CacheBuilderSpec) obj;
        return Objects.equal(this.initialCapacity, cacheBuilderSpec.initialCapacity) && Objects.equal(this.maximumSize, cacheBuilderSpec.maximumSize) && Objects.equal(this.maximumWeight, cacheBuilderSpec.maximumWeight) && Objects.equal(this.concurrencyLevel, cacheBuilderSpec.concurrencyLevel) && Objects.equal(this.keyStrength, cacheBuilderSpec.keyStrength) && Objects.equal(this.valueStrength, cacheBuilderSpec.valueStrength) && Objects.equal(this.recordStats, cacheBuilderSpec.recordStats) && Objects.equal(durationInNanos(this.writeExpirationDuration, this.writeExpirationTimeUnit), durationInNanos(cacheBuilderSpec.writeExpirationDuration, cacheBuilderSpec.writeExpirationTimeUnit)) && Objects.equal(durationInNanos(this.accessExpirationDuration, this.accessExpirationTimeUnit), durationInNanos(cacheBuilderSpec.accessExpirationDuration, cacheBuilderSpec.accessExpirationTimeUnit)) && Objects.equal(durationInNanos(this.refreshDuration, this.refreshTimeUnit), durationInNanos(cacheBuilderSpec.refreshDuration, cacheBuilderSpec.refreshTimeUnit));
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.initialCapacity, this.maximumSize, this.maximumWeight, this.concurrencyLevel, this.keyStrength, this.valueStrength, this.recordStats, durationInNanos(this.writeExpirationDuration, this.writeExpirationTimeUnit), durationInNanos(this.accessExpirationDuration, this.accessExpirationTimeUnit), durationInNanos(this.refreshDuration, this.refreshTimeUnit)});
    }

    public CacheBuilder<Object, Object> toCacheBuilder() {
        CacheBuilder<Object, Object> cacheBuilderNewBuilder = CacheBuilder.newBuilder();
        Integer num = this.initialCapacity;
        if (num != null) {
            cacheBuilderNewBuilder.initialCapacity(num.intValue());
        }
        Long l = this.maximumSize;
        if (l != null) {
            cacheBuilderNewBuilder.maximumSize(l.longValue());
        }
        Long l2 = this.maximumWeight;
        if (l2 != null) {
            cacheBuilderNewBuilder.maximumWeight(l2.longValue());
        }
        Integer num2 = this.concurrencyLevel;
        if (num2 != null) {
            cacheBuilderNewBuilder.concurrencyLevel(num2.intValue());
        }
        LocalCache.Strength strength = this.keyStrength;
        if (strength != null) {
            if (AnonymousClass1.$SwitchMap$com$google$common$cache$LocalCache$Strength[strength.ordinal()] != 1) {
                throw new AssertionError();
            }
            cacheBuilderNewBuilder.weakKeys();
        }
        LocalCache.Strength strength2 = this.valueStrength;
        if (strength2 != null) {
            int i = AnonymousClass1.$SwitchMap$com$google$common$cache$LocalCache$Strength[strength2.ordinal()];
            if (i == 1) {
                cacheBuilderNewBuilder.weakValues();
            } else {
                if (i != 2) {
                    throw new AssertionError();
                }
                cacheBuilderNewBuilder.softValues();
            }
        }
        Boolean bool = this.recordStats;
        if (bool != null && bool.booleanValue()) {
            cacheBuilderNewBuilder.statsCounterSupplier = CacheBuilder.CACHE_STATS_COUNTER;
        }
        TimeUnit timeUnit = this.writeExpirationTimeUnit;
        if (timeUnit != null) {
            cacheBuilderNewBuilder.expireAfterWrite(this.writeExpirationDuration, timeUnit);
        }
        TimeUnit timeUnit2 = this.accessExpirationTimeUnit;
        if (timeUnit2 != null) {
            cacheBuilderNewBuilder.expireAfterAccess(this.accessExpirationDuration, timeUnit2);
        }
        TimeUnit timeUnit3 = this.refreshTimeUnit;
        if (timeUnit3 != null) {
            cacheBuilderNewBuilder.refreshAfterWrite(this.refreshDuration, timeUnit3);
        }
        return cacheBuilderNewBuilder;
    }

    public String toParsableString() {
        return this.specification;
    }

    public String toString() {
        MoreObjects.ToStringHelper stringHelper = MoreObjects.toStringHelper(this);
        stringHelper.addHolder().value = this.specification;
        return stringHelper.toString();
    }
}
