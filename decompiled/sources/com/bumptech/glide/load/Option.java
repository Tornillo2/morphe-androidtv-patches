package com.bumptech.glide.load;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat$$ExternalSyntheticOutline0;
import com.bumptech.glide.util.Preconditions;
import java.security.MessageDigest;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class Option<T> {
    public static final CacheKeyUpdater<Object> EMPTY_UPDATER = new AnonymousClass1();
    public final CacheKeyUpdater<T> cacheKeyUpdater;
    public final T defaultValue;
    public final String key;
    public volatile byte[] keyBytes;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface CacheKeyUpdater<T> {
        void update(@NonNull byte[] bArr, @NonNull T t, @NonNull MessageDigest messageDigest);
    }

    public Option(@NonNull String str, @Nullable T t, @NonNull CacheKeyUpdater<T> cacheKeyUpdater) {
        Preconditions.checkNotEmpty(str);
        this.key = str;
        this.defaultValue = t;
        Preconditions.checkNotNull(cacheKeyUpdater, "Argument must not be null");
        this.cacheKeyUpdater = cacheKeyUpdater;
    }

    @NonNull
    public static <T> Option<T> disk(@NonNull String str, @NonNull CacheKeyUpdater<T> cacheKeyUpdater) {
        return new Option<>(str, null, cacheKeyUpdater);
    }

    @NonNull
    public static <T> CacheKeyUpdater<T> emptyUpdater() {
        return (CacheKeyUpdater<T>) EMPTY_UPDATER;
    }

    @NonNull
    public static <T> Option<T> memory(@NonNull String str) {
        return new Option<>(str, null, EMPTY_UPDATER);
    }

    public boolean equals(Object obj) {
        if (obj instanceof Option) {
            return this.key.equals(((Option) obj).key);
        }
        return false;
    }

    @Nullable
    public T getDefaultValue() {
        return this.defaultValue;
    }

    @NonNull
    public final byte[] getKeyBytes() {
        if (this.keyBytes == null) {
            this.keyBytes = this.key.getBytes(Key.CHARSET);
        }
        return this.keyBytes;
    }

    public int hashCode() {
        return this.key.hashCode();
    }

    public String toString() {
        return ActivityCompat$$ExternalSyntheticOutline0.m(new StringBuilder("Option{key='"), this.key, "'}");
    }

    public void update(@NonNull T t, @NonNull MessageDigest messageDigest) {
        this.cacheKeyUpdater.update(getKeyBytes(), t, messageDigest);
    }

    @NonNull
    public static <T> Option<T> disk(@NonNull String str, @Nullable T t, @NonNull CacheKeyUpdater<T> cacheKeyUpdater) {
        return new Option<>(str, t, cacheKeyUpdater);
    }

    @NonNull
    public static <T> Option<T> memory(@NonNull String str, @NonNull T t) {
        return new Option<>(str, t, EMPTY_UPDATER);
    }

    /* JADX INFO: renamed from: com.bumptech.glide.load.Option$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 implements CacheKeyUpdater<Object> {
        @Override // com.bumptech.glide.load.Option.CacheKeyUpdater
        public void update(@NonNull byte[] bArr, @NonNull Object obj, @NonNull MessageDigest messageDigest) {
        }
    }
}
