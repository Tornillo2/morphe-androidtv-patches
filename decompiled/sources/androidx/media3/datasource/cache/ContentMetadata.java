package androidx.media3.datasource.cache;

import android.net.Uri;
import androidx.annotation.Nullable;
import androidx.media3.common.util.UnstableApi;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public interface ContentMetadata {
    public static final String KEY_CONTENT_LENGTH = "exo_len";
    public static final String KEY_CUSTOM_PREFIX = "custom_";
    public static final String KEY_REDIRECTED_URI = "exo_redir";

    /* JADX INFO: renamed from: androidx.media3.datasource.cache.ContentMetadata$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
        public static long getContentLength(ContentMetadata contentMetadata) {
            return contentMetadata.get("exo_len", -1L);
        }

        @Nullable
        public static Uri getRedirectedUri(ContentMetadata contentMetadata) {
            String str = contentMetadata.get("exo_redir", (String) null);
            if (str == null) {
                return null;
            }
            return Uri.parse(str);
        }
    }

    boolean contains(String str);

    long get(String str, long j);

    @Nullable
    String get(String str, @Nullable String str2);

    @Nullable
    byte[] get(String str, @Nullable byte[] bArr);
}
