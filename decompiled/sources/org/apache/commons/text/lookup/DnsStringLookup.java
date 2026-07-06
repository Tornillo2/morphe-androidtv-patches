package org.apache.commons.text.lookup;

import java.net.InetAddress;
import java.net.UnknownHostException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class DnsStringLookup extends AbstractStringLookup {
    public static final DnsStringLookup INSTANCE = new DnsStringLookup();

    @Override // org.apache.commons.text.lookup.StringLookup
    public String lookup(String str) {
        if (str == null) {
            return null;
        }
        String[] strArrSplit = str.trim().split("\\|");
        int length = strArrSplit.length;
        String strTrim = strArrSplit[0].trim();
        if (length >= 2) {
            str = strArrSplit[1].trim();
        }
        try {
            InetAddress byName = InetAddress.getByName(str);
            int iHashCode = strTrim.hashCode();
            if (iHashCode != -1147692044) {
                if (iHashCode != 3373707) {
                    if (iHashCode == 1339224004 && strTrim.equals(InetAddressKeys.KEY_CANONICAL_NAME)) {
                        return byName.getCanonicalHostName();
                    }
                } else if (strTrim.equals("name")) {
                    return byName.getHostName();
                }
            } else if (strTrim.equals(InetAddressKeys.KEY_ADDRESS)) {
                return byName.getHostAddress();
            }
            return byName.getHostAddress();
        } catch (UnknownHostException unused) {
            return null;
        }
    }
}
