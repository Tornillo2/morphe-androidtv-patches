package com.google.common.html;

import com.google.common.annotations.GwtCompatible;
import com.google.common.escape.Escaper;
import com.google.common.escape.Escapers;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public final class HtmlEscapers {
    public static final Escaper HTML_ESCAPER;

    static {
        Escapers.Builder builder = Escapers.builder();
        builder.addEscape('\"', "&quot;");
        builder.addEscape('\'', "&#39;");
        builder.addEscape('&', "&amp;");
        builder.addEscape('<', "&lt;");
        builder.addEscape('>', "&gt;");
        HTML_ESCAPER = builder.build();
    }

    public static Escaper htmlEscaper() {
        return HTML_ESCAPER;
    }
}
