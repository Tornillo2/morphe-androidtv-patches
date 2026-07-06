package com.bumptech.glide.load.engine;

import androidx.annotation.NonNull;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.util.Preconditions;
import java.security.MessageDigest;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class EngineKey implements Key {
    public int hashCode;
    public final int height;
    public final Object model;
    public final Options options;
    public final Class<?> resourceClass;
    public final Key signature;
    public final Class<?> transcodeClass;
    public final Map<Class<?>, Transformation<?>> transformations;
    public final int width;

    public EngineKey(Object obj, Key key, int i, int i2, Map<Class<?>, Transformation<?>> map, Class<?> cls, Class<?> cls2, Options options) {
        Preconditions.checkNotNull(obj, "Argument must not be null");
        this.model = obj;
        Preconditions.checkNotNull(key, "Signature must not be null");
        this.signature = key;
        this.width = i;
        this.height = i2;
        Preconditions.checkNotNull(map, "Argument must not be null");
        this.transformations = map;
        Preconditions.checkNotNull(cls, "Resource class must not be null");
        this.resourceClass = cls;
        Preconditions.checkNotNull(cls2, "Transcode class must not be null");
        this.transcodeClass = cls2;
        Preconditions.checkNotNull(options, "Argument must not be null");
        this.options = options;
    }

    @Override // com.bumptech.glide.load.Key
    public boolean equals(Object obj) {
        if (obj instanceof EngineKey) {
            EngineKey engineKey = (EngineKey) obj;
            if (this.model.equals(engineKey.model) && this.signature.equals(engineKey.signature) && this.height == engineKey.height && this.width == engineKey.width && this.transformations.equals(engineKey.transformations) && this.resourceClass.equals(engineKey.resourceClass) && this.transcodeClass.equals(engineKey.transcodeClass) && this.options.equals(engineKey.options)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.bumptech.glide.load.Key
    public int hashCode() {
        if (this.hashCode == 0) {
            int iHashCode = this.model.hashCode();
            this.hashCode = iHashCode;
            int iHashCode2 = ((((this.signature.hashCode() + (iHashCode * 31)) * 31) + this.width) * 31) + this.height;
            this.hashCode = iHashCode2;
            int iHashCode3 = this.transformations.hashCode() + (iHashCode2 * 31);
            this.hashCode = iHashCode3;
            int iHashCode4 = this.resourceClass.hashCode() + (iHashCode3 * 31);
            this.hashCode = iHashCode4;
            int iHashCode5 = this.transcodeClass.hashCode() + (iHashCode4 * 31);
            this.hashCode = iHashCode5;
            this.hashCode = this.options.values.hashCode() + (iHashCode5 * 31);
        }
        return this.hashCode;
    }

    public String toString() {
        return "EngineKey{model=" + this.model + ", width=" + this.width + ", height=" + this.height + ", resourceClass=" + this.resourceClass + ", transcodeClass=" + this.transcodeClass + ", signature=" + this.signature + ", hashCode=" + this.hashCode + ", transformations=" + this.transformations + ", options=" + this.options + '}';
    }

    @Override // com.bumptech.glide.load.Key
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        throw new UnsupportedOperationException();
    }
}
