package com.bumptech.glide.load.model;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.ActivityCompat$$ExternalSyntheticOutline0;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class LazyHeaders implements Headers {
    public volatile Map<String, String> combinedHeaders;
    public final Map<String, List<LazyHeaderFactory>> headers;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder {
        public static final Map<String, List<LazyHeaderFactory>> DEFAULT_HEADERS;
        public static final String DEFAULT_USER_AGENT;
        public static final String USER_AGENT_HEADER = "User-Agent";
        public boolean copyOnModify = true;
        public Map<String, List<LazyHeaderFactory>> headers = DEFAULT_HEADERS;
        public boolean isUserAgentDefault = true;

        static {
            String sanitizedUserAgent = getSanitizedUserAgent();
            DEFAULT_USER_AGENT = sanitizedUserAgent;
            HashMap map = new HashMap(2);
            if (!TextUtils.isEmpty(sanitizedUserAgent)) {
                map.put("User-Agent", Collections.singletonList(new StringHeaderFactory(sanitizedUserAgent)));
            }
            DEFAULT_HEADERS = DesugarCollections.unmodifiableMap(map);
        }

        @VisibleForTesting
        public static String getSanitizedUserAgent() {
            String property = System.getProperty("http.agent");
            if (TextUtils.isEmpty(property)) {
                return property;
            }
            int length = property.length();
            StringBuilder sb = new StringBuilder(property.length());
            for (int i = 0; i < length; i++) {
                char cCharAt = property.charAt(i);
                if ((cCharAt > 31 || cCharAt == '\t') && cCharAt < 127) {
                    sb.append(cCharAt);
                } else {
                    sb.append('?');
                }
            }
            return sb.toString();
        }

        public Builder addHeader(@NonNull String str, @NonNull String str2) {
            addHeader(str, new StringHeaderFactory(str2));
            return this;
        }

        public LazyHeaders build() {
            this.copyOnModify = true;
            return new LazyHeaders(this.headers);
        }

        public final Map<String, List<LazyHeaderFactory>> copyHeaders() {
            HashMap map = new HashMap(this.headers.size());
            for (Map.Entry<String, List<LazyHeaderFactory>> entry : this.headers.entrySet()) {
                map.put(entry.getKey(), new ArrayList(entry.getValue()));
            }
            return map;
        }

        public final void copyIfNecessary() {
            if (this.copyOnModify) {
                this.copyOnModify = false;
                this.headers = copyHeaders();
            }
        }

        public final List<LazyHeaderFactory> getFactories(String str) {
            List<LazyHeaderFactory> list = this.headers.get(str);
            if (list != null) {
                return list;
            }
            ArrayList arrayList = new ArrayList();
            this.headers.put(str, arrayList);
            return arrayList;
        }

        public Builder setHeader(@NonNull String str, @Nullable String str2) {
            setHeader(str, str2 == null ? null : new StringHeaderFactory(str2));
            return this;
        }

        public Builder addHeader(@NonNull String str, @NonNull LazyHeaderFactory lazyHeaderFactory) {
            if (this.isUserAgentDefault && "User-Agent".equalsIgnoreCase(str)) {
                setHeader(str, lazyHeaderFactory);
                return this;
            }
            copyIfNecessary();
            getFactories(str).add(lazyHeaderFactory);
            return this;
        }

        public Builder setHeader(@NonNull String str, @Nullable LazyHeaderFactory lazyHeaderFactory) {
            copyIfNecessary();
            if (lazyHeaderFactory == null) {
                this.headers.remove(str);
            } else {
                List<LazyHeaderFactory> factories = getFactories(str);
                factories.clear();
                factories.add(lazyHeaderFactory);
            }
            if (this.isUserAgentDefault && "User-Agent".equalsIgnoreCase(str)) {
                this.isUserAgentDefault = false;
            }
            return this;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class StringHeaderFactory implements LazyHeaderFactory {

        @NonNull
        public final String value;

        public StringHeaderFactory(@NonNull String str) {
            this.value = str;
        }

        @Override // com.bumptech.glide.load.model.LazyHeaderFactory
        public String buildHeader() {
            return this.value;
        }

        public boolean equals(Object obj) {
            if (obj instanceof StringHeaderFactory) {
                return this.value.equals(((StringHeaderFactory) obj).value);
            }
            return false;
        }

        public int hashCode() {
            return this.value.hashCode();
        }

        public String toString() {
            return ActivityCompat$$ExternalSyntheticOutline0.m(new StringBuilder("StringHeaderFactory{value='"), this.value, "'}");
        }
    }

    public LazyHeaders(Map<String, List<LazyHeaderFactory>> map) {
        this.headers = DesugarCollections.unmodifiableMap(map);
    }

    @NonNull
    public final String buildHeaderValue(@NonNull List<LazyHeaderFactory> list) {
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            String strBuildHeader = list.get(i).buildHeader();
            if (!TextUtils.isEmpty(strBuildHeader)) {
                sb.append(strBuildHeader);
                if (i != list.size() - 1) {
                    sb.append(',');
                }
            }
        }
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (obj instanceof LazyHeaders) {
            return this.headers.equals(((LazyHeaders) obj).headers);
        }
        return false;
    }

    public final Map<String, String> generateHeaders() {
        HashMap map = new HashMap();
        for (Map.Entry<String, List<LazyHeaderFactory>> entry : this.headers.entrySet()) {
            String strBuildHeaderValue = buildHeaderValue(entry.getValue());
            if (!TextUtils.isEmpty(strBuildHeaderValue)) {
                map.put(entry.getKey(), strBuildHeaderValue);
            }
        }
        return map;
    }

    @Override // com.bumptech.glide.load.model.Headers
    public Map<String, String> getHeaders() {
        if (this.combinedHeaders == null) {
            synchronized (this) {
                try {
                    if (this.combinedHeaders == null) {
                        this.combinedHeaders = DesugarCollections.unmodifiableMap(generateHeaders());
                    }
                } finally {
                }
            }
        }
        return this.combinedHeaders;
    }

    public int hashCode() {
        return this.headers.hashCode();
    }

    public String toString() {
        return "LazyHeaders{headers=" + this.headers + '}';
    }
}
