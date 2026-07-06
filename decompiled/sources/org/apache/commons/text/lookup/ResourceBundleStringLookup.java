package org.apache.commons.text.lookup;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class ResourceBundleStringLookup extends AbstractStringLookup {
    public static final ResourceBundleStringLookup INSTANCE = new ResourceBundleStringLookup(null);
    public final String bundleName;

    public ResourceBundleStringLookup(String str) {
        this.bundleName = str;
    }

    @Override // org.apache.commons.text.lookup.StringLookup
    public String lookup(String str) {
        if (str == null) {
            return null;
        }
        String[] strArrSplit = str.split(AbstractStringLookup.SPLIT_STR);
        int length = strArrSplit.length;
        String str2 = this.bundleName;
        boolean z = str2 == null;
        if (z && length != 2) {
            throw IllegalArgumentExceptions.format("Bad resource bundle key format [%s]; expected format is BundleName:KeyName.", str);
        }
        if (str2 != null && length != 1) {
            throw IllegalArgumentExceptions.format("Bad resource bundle key format [%s]; expected format is KeyName.", str);
        }
        if (z) {
            str2 = strArrSplit[0];
        }
        String str3 = z ? strArrSplit[1] : strArrSplit[0];
        try {
            return ResourceBundle.getBundle(str2).getString(str3);
        } catch (MissingResourceException unused) {
            return null;
        } catch (Exception e) {
            throw IllegalArgumentExceptions.format(e, "Error looking up resource bundle [%s] and key [%s].", str2, str3);
        }
    }

    public ResourceBundleStringLookup() {
        this(null);
    }
}
