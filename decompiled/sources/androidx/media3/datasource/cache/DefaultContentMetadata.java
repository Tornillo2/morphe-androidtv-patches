package androidx.media3.datasource.cache;

import androidx.annotation.Nullable;
import androidx.media3.common.util.UnstableApi;
import com.google.common.base.Charsets;
import j$.util.DesugarCollections;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class DefaultContentMetadata implements ContentMetadata {
    public static final DefaultContentMetadata EMPTY = new DefaultContentMetadata(Collections.EMPTY_MAP);
    public int hashCode;
    public final Map<String, byte[]> metadata;

    public DefaultContentMetadata() {
        this(Collections.EMPTY_MAP);
    }

    public static void addValues(HashMap<String, byte[]> map, Map<String, Object> map2) {
        for (Map.Entry<String, Object> entry : map2.entrySet()) {
            map.put(entry.getKey(), getBytes(entry.getValue()));
        }
    }

    public static Map<String, byte[]> applyMutations(Map<String, byte[]> map, ContentMetadataMutations contentMetadataMutations) {
        HashMap map2 = new HashMap(map);
        removeValues(map2, contentMetadataMutations.getRemovedValues());
        addValues(map2, contentMetadataMutations.getEditedValues());
        return map2;
    }

    public static byte[] getBytes(Object obj) {
        if (obj instanceof Long) {
            return ByteBuffer.allocate(8).putLong(((Long) obj).longValue()).array();
        }
        if (obj instanceof String) {
            return ((String) obj).getBytes(Charsets.UTF_8);
        }
        if (obj instanceof byte[]) {
            return (byte[]) obj;
        }
        throw new IllegalArgumentException();
    }

    public static boolean isMetadataEqual(Map<String, byte[]> map, Map<String, byte[]> map2) {
        if (map.size() != map2.size()) {
            return false;
        }
        for (Map.Entry<String, byte[]> entry : map.entrySet()) {
            if (!Arrays.equals(entry.getValue(), map2.get(entry.getKey()))) {
                return false;
            }
        }
        return true;
    }

    public static void removeValues(HashMap<String, byte[]> map, List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            map.remove(list.get(i));
        }
    }

    @Override // androidx.media3.datasource.cache.ContentMetadata
    public final boolean contains(String str) {
        return this.metadata.containsKey(str);
    }

    public DefaultContentMetadata copyWithMutationsApplied(ContentMetadataMutations contentMetadataMutations) {
        Map<String, byte[]> mapApplyMutations = applyMutations(this.metadata, contentMetadataMutations);
        return isMetadataEqual(this.metadata, mapApplyMutations) ? this : new DefaultContentMetadata(mapApplyMutations);
    }

    public Set<Map.Entry<String, byte[]>> entrySet() {
        return this.metadata.entrySet();
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DefaultContentMetadata.class != obj.getClass()) {
            return false;
        }
        return isMetadataEqual(this.metadata, ((DefaultContentMetadata) obj).metadata);
    }

    @Override // androidx.media3.datasource.cache.ContentMetadata
    @Nullable
    public final byte[] get(String str, @Nullable byte[] bArr) {
        byte[] bArr2 = this.metadata.get(str);
        return bArr2 != null ? Arrays.copyOf(bArr2, bArr2.length) : bArr;
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            int iHashCode = 0;
            for (Map.Entry<String, byte[]> entry : this.metadata.entrySet()) {
                iHashCode += Arrays.hashCode(entry.getValue()) ^ entry.getKey().hashCode();
            }
            this.hashCode = iHashCode;
        }
        return this.hashCode;
    }

    public DefaultContentMetadata(Map<String, byte[]> map) {
        this.metadata = DesugarCollections.unmodifiableMap(map);
    }

    @Override // androidx.media3.datasource.cache.ContentMetadata
    @Nullable
    public final String get(String str, @Nullable String str2) {
        byte[] bArr = this.metadata.get(str);
        return bArr != null ? new String(bArr, Charsets.UTF_8) : str2;
    }

    @Override // androidx.media3.datasource.cache.ContentMetadata
    public final long get(String str, long j) {
        byte[] bArr = this.metadata.get(str);
        return bArr != null ? ByteBuffer.wrap(bArr).getLong() : j;
    }
}
