package com.google.android.datatransport.cct.internal;

import androidx.annotation.Nullable;
import com.google.android.datatransport.cct.internal.NetworkConnectionInfo;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class AutoValue_NetworkConnectionInfo extends NetworkConnectionInfo {
    public final NetworkConnectionInfo.MobileSubtype mobileSubtype;
    public final NetworkConnectionInfo.NetworkType networkType;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder extends NetworkConnectionInfo.Builder {
        public NetworkConnectionInfo.MobileSubtype mobileSubtype;
        public NetworkConnectionInfo.NetworkType networkType;

        @Override // com.google.android.datatransport.cct.internal.NetworkConnectionInfo.Builder
        public NetworkConnectionInfo build() {
            return new AutoValue_NetworkConnectionInfo(this.networkType, this.mobileSubtype);
        }

        @Override // com.google.android.datatransport.cct.internal.NetworkConnectionInfo.Builder
        public NetworkConnectionInfo.Builder setMobileSubtype(@Nullable NetworkConnectionInfo.MobileSubtype mobileSubtype) {
            this.mobileSubtype = mobileSubtype;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.NetworkConnectionInfo.Builder
        public NetworkConnectionInfo.Builder setNetworkType(@Nullable NetworkConnectionInfo.NetworkType networkType) {
            this.networkType = networkType;
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NetworkConnectionInfo)) {
            return false;
        }
        NetworkConnectionInfo networkConnectionInfo = (NetworkConnectionInfo) obj;
        NetworkConnectionInfo.NetworkType networkType = this.networkType;
        if (networkType == null) {
            if (networkConnectionInfo.getNetworkType() != null) {
                return false;
            }
        } else if (!networkType.equals(networkConnectionInfo.getNetworkType())) {
            return false;
        }
        NetworkConnectionInfo.MobileSubtype mobileSubtype = this.mobileSubtype;
        return mobileSubtype == null ? networkConnectionInfo.getMobileSubtype() == null : mobileSubtype.equals(networkConnectionInfo.getMobileSubtype());
    }

    @Override // com.google.android.datatransport.cct.internal.NetworkConnectionInfo
    @Nullable
    public NetworkConnectionInfo.MobileSubtype getMobileSubtype() {
        return this.mobileSubtype;
    }

    @Override // com.google.android.datatransport.cct.internal.NetworkConnectionInfo
    @Nullable
    public NetworkConnectionInfo.NetworkType getNetworkType() {
        return this.networkType;
    }

    public int hashCode() {
        NetworkConnectionInfo.NetworkType networkType = this.networkType;
        int iHashCode = ((networkType == null ? 0 : networkType.hashCode()) ^ 1000003) * 1000003;
        NetworkConnectionInfo.MobileSubtype mobileSubtype = this.mobileSubtype;
        return iHashCode ^ (mobileSubtype != null ? mobileSubtype.hashCode() : 0);
    }

    public String toString() {
        return "NetworkConnectionInfo{networkType=" + this.networkType + ", mobileSubtype=" + this.mobileSubtype + "}";
    }

    public AutoValue_NetworkConnectionInfo(@Nullable NetworkConnectionInfo.NetworkType networkType, @Nullable NetworkConnectionInfo.MobileSubtype mobileSubtype) {
        this.networkType = networkType;
        this.mobileSubtype = mobileSubtype;
    }
}
