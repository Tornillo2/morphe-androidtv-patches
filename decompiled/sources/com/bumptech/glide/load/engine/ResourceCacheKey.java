package com.bumptech.glide.load.engine;

import androidx.annotation.NonNull;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.util.LruCache;
import com.bumptech.glide.util.Util;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class ResourceCacheKey implements Key {
    public static final LruCache<Class<?>, byte[]> RESOURCE_CLASS_BYTES = new LruCache<>(50);
    public final ArrayPool arrayPool;
    public final Class<?> decodedResourceClass;
    public final int height;
    public final Options options;
    public final Key signature;
    public final Key sourceKey;
    public final Transformation<?> transformation;
    public final int width;

    public ResourceCacheKey(ArrayPool arrayPool, Key key, Key key2, int i, int i2, Transformation<?> transformation, Class<?> cls, Options options) {
        this.arrayPool = arrayPool;
        this.sourceKey = key;
        this.signature = key2;
        this.width = i;
        this.height = i2;
        this.transformation = transformation;
        this.decodedResourceClass = cls;
        this.options = options;
    }

    @Override // com.bumptech.glide.load.Key
    public boolean equals(Object obj) {
        if (obj instanceof ResourceCacheKey) {
            ResourceCacheKey resourceCacheKey = (ResourceCacheKey) obj;
            if (this.height == resourceCacheKey.height && this.width == resourceCacheKey.width && Util.bothNullOrEqual(this.transformation, resourceCacheKey.transformation) && this.decodedResourceClass.equals(resourceCacheKey.decodedResourceClass) && this.sourceKey.equals(resourceCacheKey.sourceKey) && this.signature.equals(resourceCacheKey.signature) && this.options.equals(resourceCacheKey.options)) {
                return true;
            }
        }
        return false;
    }

    public final byte[] getResourceClassBytes() {
        LruCache<Class<?>, byte[]> lruCache = RESOURCE_CLASS_BYTES;
        byte[] bArr = lruCache.get(this.decodedResourceClass);
        if (bArr != null) {
            return bArr;
        }
        byte[] bytes = this.decodedResourceClass.getName().getBytes(Key.CHARSET);
        lruCache.put(this.decodedResourceClass, bytes);
        return bytes;
    }

    @Override // com.bumptech.glide.load.Key
    public int hashCode() {
        int iHashCode = ((((this.signature.hashCode() + (this.sourceKey.hashCode() * 31)) * 31) + this.width) * 31) + this.height;
        Transformation<?> transformation = this.transformation;
        if (transformation != null) {
            iHashCode = (iHashCode * 31) + transformation.hashCode();
        }
        return this.options.values.hashCode() + ((this.decodedResourceClass.hashCode() + (iHashCode * 31)) * 31);
    }

    public String toString() {
        return "ResourceCacheKey{sourceKey=" + this.sourceKey + ", signature=" + this.signature + ", width=" + this.width + ", height=" + this.height + ", decodedResourceClass=" + this.decodedResourceClass + ", transformation='" + this.transformation + "', options=" + this.options + '}';
    }

    @Override // com.bumptech.glide.load.Key
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        byte[] bArr = (byte[]) this.arrayPool.getExact(8, byte[].class);
        ByteBuffer.wrap(bArr).putInt(this.width).putInt(this.height).array();
        this.signature.updateDiskCacheKey(messageDigest);
        this.sourceKey.updateDiskCacheKey(messageDigest);
        messageDigest.update(bArr);
        Transformation<?> transformation = this.transformation;
        if (transformation != null) {
            transformation.updateDiskCacheKey(messageDigest);
        }
        this.options.updateDiskCacheKey(messageDigest);
        messageDigest.update(getResourceClassBytes());
        this.arrayPool.put(bArr);
    }
}
