package com.google.common.primitives;

import com.google.common.annotations.GwtCompatible;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@GwtCompatible
public final class ParseRequest {
    public final int radix;
    public final String rawValue;

    public ParseRequest(String rawValue, int radix) {
        this.rawValue = rawValue;
        this.radix = radix;
    }

    public static ParseRequest fromString(String stringValue) {
        if (stringValue.length() == 0) {
            throw new NumberFormatException("empty string");
        }
        char cCharAt = stringValue.charAt(0);
        int i = 16;
        if (stringValue.startsWith("0x") || stringValue.startsWith("0X")) {
            stringValue = stringValue.substring(2);
        } else if (cCharAt == '#') {
            stringValue = stringValue.substring(1);
        } else if (cCharAt != '0' || stringValue.length() <= 1) {
            i = 10;
        } else {
            stringValue = stringValue.substring(1);
            i = 8;
        }
        return new ParseRequest(stringValue, i);
    }
}
