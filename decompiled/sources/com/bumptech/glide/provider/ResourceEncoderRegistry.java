package com.bumptech.glide.provider;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.load.ResourceEncoder;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class ResourceEncoderRegistry {
    public final List<Entry<?>> encoders = new ArrayList();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Entry<T> {
        public final ResourceEncoder<T> encoder;
        public final Class<T> resourceClass;

        public Entry(@NonNull Class<T> cls, @NonNull ResourceEncoder<T> resourceEncoder) {
            this.resourceClass = cls;
            this.encoder = resourceEncoder;
        }

        public boolean handles(@NonNull Class<?> cls) {
            return this.resourceClass.isAssignableFrom(cls);
        }
    }

    public synchronized <Z> void append(@NonNull Class<Z> cls, @NonNull ResourceEncoder<Z> resourceEncoder) {
        this.encoders.add(new Entry<>(cls, resourceEncoder));
    }

    @Nullable
    public synchronized <Z> ResourceEncoder<Z> get(@NonNull Class<Z> cls) {
        int size = this.encoders.size();
        for (int i = 0; i < size; i++) {
            Entry<?> entry = this.encoders.get(i);
            if (entry.resourceClass.isAssignableFrom(cls)) {
                return (ResourceEncoder<Z>) entry.encoder;
            }
        }
        return null;
    }

    public synchronized <Z> void prepend(@NonNull Class<Z> cls, @NonNull ResourceEncoder<Z> resourceEncoder) {
        this.encoders.add(0, new Entry<>(cls, resourceEncoder));
    }
}
