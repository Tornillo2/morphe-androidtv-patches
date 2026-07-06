package org.apache.commons.text.lookup;

import j$.util.Base64;
import java.nio.charset.StandardCharsets;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class Base64EncoderStringLookup extends AbstractStringLookup {
    public static final Base64EncoderStringLookup INSTANCE = new Base64EncoderStringLookup();

    @Override // org.apache.commons.text.lookup.StringLookup
    public String lookup(String str) {
        if (str == null) {
            return null;
        }
        return Base64.getEncoder().encodeToString(str.getBytes(StandardCharsets.ISO_8859_1));
    }
}
