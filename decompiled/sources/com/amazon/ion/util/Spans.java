package com.amazon.ion.util;

import com.amazon.ion.Span;
import com.amazon.ion.SpanProvider;
import com.amazon.ion.facet.Faceted;
import com.amazon.ion.facet.Facets;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class Spans {
    public static Span currentSpan(Object obj) {
        SpanProvider spanProvider = (SpanProvider) Facets.asFacet(SpanProvider.class, obj);
        if (spanProvider == null) {
            return null;
        }
        return spanProvider.currentSpan();
    }

    public static <T> T currentSpan(Class<T> cls, Object obj) {
        return (T) Facets.asFacet((Class) cls, (Faceted) currentSpan(obj));
    }
}
