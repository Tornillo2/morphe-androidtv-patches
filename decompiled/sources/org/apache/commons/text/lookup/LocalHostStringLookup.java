package org.apache.commons.text.lookup;

import java.net.InetAddress;
import java.net.UnknownHostException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class LocalHostStringLookup extends AbstractStringLookup {
    public static final LocalHostStringLookup INSTANCE = new LocalHostStringLookup();

    @Override // org.apache.commons.text.lookup.StringLookup
    public String lookup(String str) {
        if (str == null) {
            return null;
        }
        try {
            int iHashCode = str.hashCode();
            if (iHashCode != -1147692044) {
                if (iHashCode != 3373707) {
                    if (iHashCode == 1339224004 && str.equals(InetAddressKeys.KEY_CANONICAL_NAME)) {
                        return InetAddress.getLocalHost().getCanonicalHostName();
                    }
                } else if (str.equals("name")) {
                    return InetAddress.getLocalHost().getHostName();
                }
            } else if (str.equals(InetAddressKeys.KEY_ADDRESS)) {
                return InetAddress.getLocalHost().getHostAddress();
            }
            throw new IllegalArgumentException(str);
        } catch (UnknownHostException unused) {
            return null;
        }
    }
}
