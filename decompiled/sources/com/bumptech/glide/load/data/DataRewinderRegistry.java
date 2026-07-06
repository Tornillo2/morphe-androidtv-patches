package com.bumptech.glide.load.data;

import androidx.annotation.NonNull;
import com.bumptech.glide.load.data.DataRewinder;
import com.bumptech.glide.util.Preconditions;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class DataRewinderRegistry {
    public static final DataRewinder.Factory<?> DEFAULT_FACTORY = new AnonymousClass1();
    public final Map<Class<?>, DataRewinder.Factory<?>> rewinders = new HashMap();

    /* JADX INFO: renamed from: com.bumptech.glide.load.data.DataRewinderRegistry$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 implements DataRewinder.Factory<Object> {
        @Override // com.bumptech.glide.load.data.DataRewinder.Factory
        @NonNull
        public DataRewinder<Object> build(@NonNull Object obj) {
            return new DefaultRewinder(obj);
        }

        @Override // com.bumptech.glide.load.data.DataRewinder.Factory
        @NonNull
        public Class<Object> getDataClass() {
            throw new UnsupportedOperationException("Not implemented");
        }
    }

    @NonNull
    public synchronized <T> DataRewinder<T> build(@NonNull T t) {
        DataRewinder.Factory<?> factory;
        try {
            Preconditions.checkNotNull(t);
            factory = this.rewinders.get(t.getClass());
            if (factory == null) {
                Iterator<DataRewinder.Factory<?>> it = this.rewinders.values().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    DataRewinder.Factory<?> next = it.next();
                    if (next.getDataClass().isAssignableFrom(t.getClass())) {
                        factory = next;
                        break;
                    }
                }
            }
            if (factory == null) {
                factory = DEFAULT_FACTORY;
            }
        } catch (Throwable th) {
            throw th;
        }
        return (DataRewinder<T>) factory.build(t);
    }

    public synchronized void register(@NonNull DataRewinder.Factory<?> factory) {
        this.rewinders.put(factory.getDataClass(), factory);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DefaultRewinder implements DataRewinder<Object> {
        public final Object data;

        public DefaultRewinder(@NonNull Object obj) {
            this.data = obj;
        }

        @Override // com.bumptech.glide.load.data.DataRewinder
        @NonNull
        public Object rewindAndGet() {
            return this.data;
        }

        @Override // com.bumptech.glide.load.data.DataRewinder
        public void cleanup() {
        }
    }
}
