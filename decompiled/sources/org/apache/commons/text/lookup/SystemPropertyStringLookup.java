package org.apache.commons.text.lookup;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class SystemPropertyStringLookup extends AbstractStringLookup {
    public static final SystemPropertyStringLookup INSTANCE = new SystemPropertyStringLookup();

    @Override // org.apache.commons.text.lookup.StringLookup
    public String lookup(String str) {
        try {
            return System.getProperty(str);
        } catch (IllegalArgumentException | NullPointerException | SecurityException unused) {
            return null;
        }
    }
}
