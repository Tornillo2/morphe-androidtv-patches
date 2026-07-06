package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Ascii;
import com.google.common.base.Equivalence;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.MapMakerInternalMap;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtCompatible(emulated = true)
public final class MapMaker {
    public static final int DEFAULT_CONCURRENCY_LEVEL = 4;
    public static final int DEFAULT_INITIAL_CAPACITY = 16;
    public static final int UNSET_INT = -1;
    public Equivalence<Object> keyEquivalence;
    public MapMakerInternalMap.Strength keyStrength;
    public boolean useCustomMap;
    public MapMakerInternalMap.Strength valueStrength;
    public int initialCapacity = -1;
    public int concurrencyLevel = -1;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum Dummy {
        VALUE;

        public static /* synthetic */ Dummy[] $values() {
            return new Dummy[]{VALUE};
        }
    }

    @CanIgnoreReturnValue
    public MapMaker concurrencyLevel(int concurrencyLevel) {
        int i = this.concurrencyLevel;
        Preconditions.checkState(i == -1, "concurrency level was already set to %s", i);
        Preconditions.checkArgument(concurrencyLevel > 0);
        this.concurrencyLevel = concurrencyLevel;
        return this;
    }

    public int getConcurrencyLevel() {
        int i = this.concurrencyLevel;
        if (i == -1) {
            return 4;
        }
        return i;
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

    public MapMakerInternalMap.Strength getKeyStrength() {
        return (MapMakerInternalMap.Strength) MoreObjects.firstNonNull(this.keyStrength, MapMakerInternalMap.Strength.STRONG);
    }

    public MapMakerInternalMap.Strength getValueStrength() {
        return (MapMakerInternalMap.Strength) MoreObjects.firstNonNull(this.valueStrength, MapMakerInternalMap.Strength.STRONG);
    }

    @CanIgnoreReturnValue
    public MapMaker initialCapacity(int initialCapacity) {
        int i = this.initialCapacity;
        Preconditions.checkState(i == -1, "initial capacity was already set to %s", i);
        Preconditions.checkArgument(initialCapacity >= 0);
        this.initialCapacity = initialCapacity;
        return this;
    }

    @CanIgnoreReturnValue
    @GwtIncompatible
    public MapMaker keyEquivalence(Equivalence<Object> equivalence) {
        Equivalence<Object> equivalence2 = this.keyEquivalence;
        Preconditions.checkState(equivalence2 == null, "key equivalence was already set to %s", equivalence2);
        equivalence.getClass();
        this.keyEquivalence = equivalence;
        this.useCustomMap = true;
        return this;
    }

    public <K, V> ConcurrentMap<K, V> makeMap() {
        return !this.useCustomMap ? new ConcurrentHashMap(getInitialCapacity(), 0.75f, getConcurrencyLevel()) : MapMakerInternalMap.create(this);
    }

    @CanIgnoreReturnValue
    public MapMaker setKeyStrength(MapMakerInternalMap.Strength strength) {
        MapMakerInternalMap.Strength strength2 = this.keyStrength;
        Preconditions.checkState(strength2 == null, "Key strength was already set to %s", strength2);
        strength.getClass();
        this.keyStrength = strength;
        if (strength != MapMakerInternalMap.Strength.STRONG) {
            this.useCustomMap = true;
        }
        return this;
    }

    @CanIgnoreReturnValue
    public MapMaker setValueStrength(MapMakerInternalMap.Strength strength) {
        MapMakerInternalMap.Strength strength2 = this.valueStrength;
        Preconditions.checkState(strength2 == null, "Value strength was already set to %s", strength2);
        strength.getClass();
        this.valueStrength = strength;
        if (strength != MapMakerInternalMap.Strength.STRONG) {
            this.useCustomMap = true;
        }
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
        MapMakerInternalMap.Strength strength = this.keyStrength;
        if (strength != null) {
            stringHelper.addHolder("keyStrength", Ascii.toLowerCase(strength.toString()));
        }
        MapMakerInternalMap.Strength strength2 = this.valueStrength;
        if (strength2 != null) {
            stringHelper.addHolder("valueStrength", Ascii.toLowerCase(strength2.toString()));
        }
        if (this.keyEquivalence != null) {
            stringHelper.addValue("keyEquivalence");
        }
        return stringHelper.toString();
    }

    @CanIgnoreReturnValue
    @GwtIncompatible
    public MapMaker weakKeys() {
        setKeyStrength(MapMakerInternalMap.Strength.WEAK);
        return this;
    }

    @CanIgnoreReturnValue
    @GwtIncompatible
    public MapMaker weakValues() {
        setValueStrength(MapMakerInternalMap.Strength.WEAK);
        return this;
    }
}
