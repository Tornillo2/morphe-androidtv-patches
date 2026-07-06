package com.bumptech.glide.load.engine.bitmap_recycle;

import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.bumptech.glide.util.Preconditions;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class LruArrayPool implements ArrayPool {
    public static final int DEFAULT_SIZE = 4194304;

    @VisibleForTesting
    public static final int MAX_OVER_SIZE_MULTIPLE = 8;
    public static final int SINGLE_ARRAY_MAX_SIZE_DIVISOR = 2;
    public final Map<Class<?>, ArrayAdapterInterface<?>> adapters;
    public int currentSize;
    public final GroupedLinkedMap<Key, Object> groupedMap;
    public final KeyPool keyPool;
    public final int maxSize;
    public final Map<Class<?>, NavigableMap<Integer, Integer>> sortedSizes;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Key implements Poolable {
        public Class<?> arrayClass;
        public final KeyPool pool;
        public int size;

        public Key(KeyPool keyPool) {
            this.pool = keyPool;
        }

        public boolean equals(Object obj) {
            if (obj instanceof Key) {
                Key key = (Key) obj;
                if (this.size == key.size && this.arrayClass == key.arrayClass) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            int i = this.size * 31;
            Class<?> cls = this.arrayClass;
            return i + (cls != null ? cls.hashCode() : 0);
        }

        public void init(int i, Class<?> cls) {
            this.size = i;
            this.arrayClass = cls;
        }

        @Override // com.bumptech.glide.load.engine.bitmap_recycle.Poolable
        public void offer() {
            this.pool.offer(this);
        }

        public String toString() {
            return "Key{size=" + this.size + "array=" + this.arrayClass + '}';
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class KeyPool extends BaseKeyPool<Key> {
        @Override // com.bumptech.glide.load.engine.bitmap_recycle.BaseKeyPool
        public Key create() {
            return new Key(this);
        }

        public Key get(int i, Class<?> cls) {
            Key key = get();
            key.size = i;
            key.arrayClass = cls;
            return key;
        }

        @Override // com.bumptech.glide.load.engine.bitmap_recycle.BaseKeyPool
        public Poolable create() {
            return new Key(this);
        }
    }

    @VisibleForTesting
    public LruArrayPool() {
        this.groupedMap = new GroupedLinkedMap<>();
        this.keyPool = new KeyPool();
        this.sortedSizes = new HashMap();
        this.adapters = new HashMap();
        this.maxSize = 4194304;
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool
    public synchronized void clearMemory() {
        evictToSize(0);
    }

    public final void decrementArrayOfSize(int i, Class<?> cls) {
        NavigableMap<Integer, Integer> sizesForAdapter = getSizesForAdapter(cls);
        Integer num = sizesForAdapter.get(Integer.valueOf(i));
        if (num != null) {
            if (num.intValue() == 1) {
                sizesForAdapter.remove(Integer.valueOf(i));
                return;
            } else {
                sizesForAdapter.put(Integer.valueOf(i), Integer.valueOf(num.intValue() - 1));
                return;
            }
        }
        throw new NullPointerException("Tried to decrement empty size, size: " + i + ", this: " + this);
    }

    public final void evict() {
        evictToSize(this.maxSize);
    }

    public final void evictToSize(int i) {
        while (this.currentSize > i) {
            Object objRemoveLast = this.groupedMap.removeLast();
            Preconditions.checkNotNull(objRemoveLast);
            ArrayAdapterInterface adapterFromType = getAdapterFromType(objRemoveLast.getClass());
            this.currentSize -= adapterFromType.getElementSizeInBytes() * adapterFromType.getArrayLength(objRemoveLast);
            decrementArrayOfSize(adapterFromType.getArrayLength(objRemoveLast), objRemoveLast.getClass());
            if (Log.isLoggable(adapterFromType.getTag(), 2)) {
                Log.v(adapterFromType.getTag(), "evicted: " + adapterFromType.getArrayLength(objRemoveLast));
            }
        }
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool
    public synchronized <T> T get(int i, Class<T> cls) {
        Integer numCeilingKey;
        try {
            numCeilingKey = getSizesForAdapter(cls).ceilingKey(Integer.valueOf(i));
        } catch (Throwable th) {
            throw th;
        }
        return (T) getForKey(mayFillRequest(i, numCeilingKey) ? this.keyPool.get(numCeilingKey.intValue(), cls) : this.keyPool.get(i, cls), cls);
    }

    public final <T> ArrayAdapterInterface<T> getAdapterFromObject(T t) {
        return getAdapterFromType(t.getClass());
    }

    public final <T> ArrayAdapterInterface<T> getAdapterFromType(Class<T> cls) {
        ArrayAdapterInterface<T> byteArrayAdapter;
        ArrayAdapterInterface<T> arrayAdapterInterface = (ArrayAdapterInterface) this.adapters.get(cls);
        if (arrayAdapterInterface != null) {
            return arrayAdapterInterface;
        }
        if (cls.equals(int[].class)) {
            byteArrayAdapter = new IntegerArrayAdapter();
        } else {
            if (!cls.equals(byte[].class)) {
                throw new IllegalArgumentException("No array pool found for: ".concat(cls.getSimpleName()));
            }
            byteArrayAdapter = new ByteArrayAdapter();
        }
        this.adapters.put(cls, byteArrayAdapter);
        return byteArrayAdapter;
    }

    @Nullable
    public final <T> T getArrayForKey(Key key) {
        return (T) this.groupedMap.get(key);
    }

    public int getCurrentSize() {
        int elementSizeInBytes = 0;
        for (Class<?> cls : this.sortedSizes.keySet()) {
            for (Integer num : this.sortedSizes.get(cls).keySet()) {
                ArrayAdapterInterface adapterFromType = getAdapterFromType(cls);
                elementSizeInBytes += adapterFromType.getElementSizeInBytes() * this.sortedSizes.get(cls).get(num).intValue() * num.intValue();
            }
        }
        return elementSizeInBytes;
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool
    public synchronized <T> T getExact(int i, Class<T> cls) {
        return (T) getForKey(this.keyPool.get(i, cls), cls);
    }

    public final <T> T getForKey(Key key, Class<T> cls) {
        ArrayAdapterInterface<T> adapterFromType = getAdapterFromType(cls);
        T t = (T) this.groupedMap.get(key);
        if (t != null) {
            this.currentSize -= adapterFromType.getElementSizeInBytes() * adapterFromType.getArrayLength(t);
            decrementArrayOfSize(adapterFromType.getArrayLength(t), cls);
        }
        if (t != null) {
            return t;
        }
        if (Log.isLoggable(adapterFromType.getTag(), 2)) {
            Log.v(adapterFromType.getTag(), "Allocated " + key.size + " bytes");
        }
        return adapterFromType.newArray(key.size);
    }

    public final NavigableMap<Integer, Integer> getSizesForAdapter(Class<?> cls) {
        NavigableMap<Integer, Integer> navigableMap = this.sortedSizes.get(cls);
        if (navigableMap != null) {
            return navigableMap;
        }
        TreeMap treeMap = new TreeMap();
        this.sortedSizes.put(cls, treeMap);
        return treeMap;
    }

    public final boolean isNoMoreThanHalfFull() {
        int i = this.currentSize;
        return i == 0 || this.maxSize / i >= 2;
    }

    public final boolean isSmallEnoughForReuse(int i) {
        return i <= this.maxSize / 2;
    }

    public final boolean mayFillRequest(int i, Integer num) {
        if (num != null) {
            return isNoMoreThanHalfFull() || num.intValue() <= i * 8;
        }
        return false;
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool
    @Deprecated
    public <T> void put(T t, Class<T> cls) {
        put(t);
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool
    public synchronized void trimMemory(int i) {
        try {
            if (i >= 40) {
                clearMemory();
            } else if (i >= 20 || i == 15) {
                evictToSize(this.maxSize / 2);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool
    public synchronized <T> void put(T t) {
        Class<?> cls = t.getClass();
        ArrayAdapterInterface<T> adapterFromType = getAdapterFromType(cls);
        int arrayLength = adapterFromType.getArrayLength(t);
        int elementSizeInBytes = adapterFromType.getElementSizeInBytes() * arrayLength;
        if (isSmallEnoughForReuse(elementSizeInBytes)) {
            Key key = this.keyPool.get(arrayLength, cls);
            this.groupedMap.put(key, t);
            NavigableMap<Integer, Integer> sizesForAdapter = getSizesForAdapter(cls);
            Integer num = sizesForAdapter.get(Integer.valueOf(key.size));
            Integer numValueOf = Integer.valueOf(key.size);
            int iIntValue = 1;
            if (num != null) {
                iIntValue = 1 + num.intValue();
            }
            sizesForAdapter.put(numValueOf, Integer.valueOf(iIntValue));
            this.currentSize += elementSizeInBytes;
            evict();
        }
    }

    public LruArrayPool(int i) {
        this.groupedMap = new GroupedLinkedMap<>();
        this.keyPool = new KeyPool();
        this.sortedSizes = new HashMap();
        this.adapters = new HashMap();
        this.maxSize = i;
    }
}
