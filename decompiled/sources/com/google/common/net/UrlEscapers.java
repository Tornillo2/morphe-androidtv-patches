package com.google.common.net;

import com.google.common.annotations.GwtCompatible;
import com.google.common.escape.Escaper;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public final class UrlEscapers {
    public static final String URL_PATH_OTHER_SAFE_CHARS_LACKING_PLUS = "-._~!$'()*,;&=@:";
    public static final String URL_FORM_PARAMETER_OTHER_SAFE_CHARS = "-_.*";
    public static final Escaper URL_FORM_PARAMETER_ESCAPER = new PercentEscaper(URL_FORM_PARAMETER_OTHER_SAFE_CHARS, true);
    public static final Escaper URL_PATH_SEGMENT_ESCAPER = new PercentEscaper("-._~!$'()*,;&=@:+", false);
    public static final Escaper URL_FRAGMENT_ESCAPER = new PercentEscaper("-._~!$'()*,;&=@:+/?", false);

    public static Escaper urlFormParameterEscaper() {
        return URL_FORM_PARAMETER_ESCAPER;
    }

    public static Escaper urlFragmentEscaper() {
        return URL_FRAGMENT_ESCAPER;
    }

    public static Escaper urlPathSegmentEscaper() {
        return URL_PATH_SEGMENT_ESCAPER;
    }
}
