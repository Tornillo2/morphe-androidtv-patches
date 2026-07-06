package androidx.media3.datasource.cache;

import android.net.Uri;
import androidx.annotation.Nullable;
import androidx.media3.common.util.UnstableApi;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public class ContentMetadataMutations {
    public final Map<String, Object> editedValues = new HashMap();
    public final List<String> removedValues = new ArrayList();

    public static ContentMetadataMutations setContentLength(ContentMetadataMutations contentMetadataMutations, long j) {
        return contentMetadataMutations.set("exo_len", j);
    }

    public static ContentMetadataMutations setRedirectedUri(ContentMetadataMutations contentMetadataMutations, @Nullable Uri uri) {
        return uri == null ? contentMetadataMutations.remove("exo_redir") : contentMetadataMutations.set("exo_redir", uri.toString());
    }

    @CanIgnoreReturnValue
    public final ContentMetadataMutations checkAndSet(String str, Object obj) {
        Map<String, Object> map = this.editedValues;
        str.getClass();
        obj.getClass();
        map.put(str, obj);
        this.removedValues.remove(str);
        return this;
    }

    public Map<String, Object> getEditedValues() {
        HashMap map = new HashMap(this.editedValues);
        for (Map.Entry entry : map.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof byte[]) {
                byte[] bArr = (byte[]) value;
                entry.setValue(Arrays.copyOf(bArr, bArr.length));
            }
        }
        return DesugarCollections.unmodifiableMap(map);
    }

    public List<String> getRemovedValues() {
        return DesugarCollections.unmodifiableList(new ArrayList(this.removedValues));
    }

    @CanIgnoreReturnValue
    public ContentMetadataMutations remove(String str) {
        this.removedValues.add(str);
        this.editedValues.remove(str);
        return this;
    }

    @CanIgnoreReturnValue
    public ContentMetadataMutations set(String str, String str2) {
        checkAndSet(str, str2);
        return this;
    }

    @CanIgnoreReturnValue
    public ContentMetadataMutations set(String str, long j) {
        checkAndSet(str, Long.valueOf(j));
        return this;
    }

    @CanIgnoreReturnValue
    public ContentMetadataMutations set(String str, byte[] bArr) {
        checkAndSet(str, Arrays.copyOf(bArr, bArr.length));
        return this;
    }
}
