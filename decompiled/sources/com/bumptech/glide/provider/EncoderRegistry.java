package com.bumptech.glide.provider;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.load.Encoder;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class EncoderRegistry {
    public final List<Entry<?>> encoders = new ArrayList();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Entry<T> {
        public final Class<T> dataClass;
        public final Encoder<T> encoder;

        public Entry(@NonNull Class<T> cls, @NonNull Encoder<T> encoder) {
            this.dataClass = cls;
            this.encoder = encoder;
        }

        public boolean handles(@NonNull Class<?> cls) {
            return this.dataClass.isAssignableFrom(cls);
        }
    }

    public synchronized <T> void append(@NonNull Class<T> cls, @NonNull Encoder<T> encoder) {
        this.encoders.add(new Entry<>(cls, encoder));
    }

    @Nullable
    public synchronized <T> Encoder<T> getEncoder(@NonNull Class<T> cls) {
        for (Entry<?> entry : this.encoders) {
            if (entry.dataClass.isAssignableFrom(cls)) {
                return (Encoder<T>) entry.encoder;
            }
        }
        return null;
    }

    public synchronized <T> void prepend(@NonNull Class<T> cls, @NonNull Encoder<T> encoder) {
        this.encoders.add(0, new Entry<>(cls, encoder));
    }
}
