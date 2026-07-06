package com.google.common.net;

import androidx.core.app.RemoteInput$$ExternalSyntheticOutline0;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.net.InetAddress;
import java.text.ParseException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@J2ktIncompatible
@GwtIncompatible
public final class HostSpecifier {
    public final String canonicalForm;

    public HostSpecifier(String canonicalForm) {
        this.canonicalForm = canonicalForm;
    }

    @CanIgnoreReturnValue
    public static HostSpecifier from(String specifier) throws ParseException {
        try {
            return fromValid(specifier);
        } catch (IllegalArgumentException e) {
            ParseException parseException = new ParseException(RemoteInput$$ExternalSyntheticOutline0.m("Invalid host specifier: ", specifier), 0);
            parseException.initCause(e);
            throw parseException;
        }
    }

    public static HostSpecifier fromValid(String specifier) {
        InetAddress inetAddressForString;
        HostAndPort hostAndPortFromString = HostAndPort.fromString(specifier);
        Preconditions.checkArgument(!hostAndPortFromString.hasPort());
        String str = hostAndPortFromString.host;
        try {
            inetAddressForString = InetAddresses.forString(str);
        } catch (IllegalArgumentException unused) {
            inetAddressForString = null;
        }
        if (inetAddressForString != null) {
            return new HostSpecifier(InetAddresses.toUriString(inetAddressForString));
        }
        InternetDomainName internetDomainNameFrom = InternetDomainName.from(str);
        if (internetDomainNameFrom.hasPublicSuffix()) {
            return new HostSpecifier(internetDomainNameFrom.name);
        }
        throw new IllegalArgumentException("Domain name does not have a recognized public suffix: ".concat(str));
    }

    public static boolean isValid(String specifier) {
        try {
            fromValid(specifier);
            return true;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof HostSpecifier) {
            return this.canonicalForm.equals(((HostSpecifier) other).canonicalForm);
        }
        return false;
    }

    public int hashCode() {
        return this.canonicalForm.hashCode();
    }

    public String toString() {
        return this.canonicalForm;
    }
}
