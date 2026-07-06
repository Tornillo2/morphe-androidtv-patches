package com.bumptech.glide.load.model;

import com.bumptech.glide.load.model.LazyHeaders;
import java.util.Collections;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface Headers {

    @Deprecated
    public static final Headers NONE = new AnonymousClass1();
    public static final Headers DEFAULT = new LazyHeaders.Builder().build();

    /* JADX INFO: renamed from: com.bumptech.glide.load.model.Headers$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 implements Headers {
        @Override // com.bumptech.glide.load.model.Headers
        public Map<String, String> getHeaders() {
            return Collections.EMPTY_MAP;
        }
    }

    Map<String, String> getHeaders();
}
