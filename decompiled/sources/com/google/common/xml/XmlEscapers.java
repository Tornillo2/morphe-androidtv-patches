package com.google.common.xml;

import com.google.common.annotations.GwtCompatible;
import com.google.common.escape.Escaper;
import com.google.common.escape.Escapers;
import org.apache.commons.lang3.CharUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public class XmlEscapers {
    public static final char MAX_ASCII_CONTROL_CHAR = 31;
    public static final char MIN_ASCII_CONTROL_CHAR = 0;
    public static final Escaper XML_ATTRIBUTE_ESCAPER;
    public static final Escaper XML_CONTENT_ESCAPER;
    public static final Escaper XML_ESCAPER;

    static {
        Escapers.Builder builder = Escapers.builder();
        builder.safeMin = (char) 0;
        builder.safeMax = (char) 65533;
        builder.unsafeReplacement = "�";
        for (char c = 0; c <= 31; c = (char) (c + 1)) {
            if (c != '\t' && c != '\n' && c != '\r') {
                builder.addEscape(c, "�");
            }
        }
        builder.addEscape('&', "&amp;");
        builder.addEscape('<', "&lt;");
        builder.addEscape('>', "&gt;");
        XML_CONTENT_ESCAPER = builder.build();
        builder.addEscape('\'', "&apos;");
        builder.addEscape('\"', "&quot;");
        XML_ESCAPER = builder.build();
        builder.addEscape('\t', "&#x9;");
        builder.addEscape('\n', "&#xA;");
        builder.addEscape(CharUtils.CR, "&#xD;");
        XML_ATTRIBUTE_ESCAPER = builder.build();
    }

    public static Escaper xmlAttributeEscaper() {
        return XML_ATTRIBUTE_ESCAPER;
    }

    public static Escaper xmlContentEscaper() {
        return XML_CONTENT_ESCAPER;
    }
}
