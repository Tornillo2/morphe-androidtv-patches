package com.google.android.datatransport.cct.internal;

import androidx.annotation.Nullable;
import com.google.android.datatransport.cct.internal.ClientInfo;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class AutoValue_ClientInfo extends ClientInfo {
    public final AndroidClientInfo androidClientInfo;
    public final ClientInfo.ClientType clientType;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder extends ClientInfo.Builder {
        public AndroidClientInfo androidClientInfo;
        public ClientInfo.ClientType clientType;

        @Override // com.google.android.datatransport.cct.internal.ClientInfo.Builder
        public ClientInfo build() {
            return new AutoValue_ClientInfo(this.clientType, this.androidClientInfo);
        }

        @Override // com.google.android.datatransport.cct.internal.ClientInfo.Builder
        public ClientInfo.Builder setAndroidClientInfo(@Nullable AndroidClientInfo androidClientInfo) {
            this.androidClientInfo = androidClientInfo;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.ClientInfo.Builder
        public ClientInfo.Builder setClientType(@Nullable ClientInfo.ClientType clientType) {
            this.clientType = clientType;
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ClientInfo)) {
            return false;
        }
        ClientInfo clientInfo = (ClientInfo) obj;
        ClientInfo.ClientType clientType = this.clientType;
        if (clientType == null) {
            if (clientInfo.getClientType() != null) {
                return false;
            }
        } else if (!clientType.equals(clientInfo.getClientType())) {
            return false;
        }
        AndroidClientInfo androidClientInfo = this.androidClientInfo;
        return androidClientInfo == null ? clientInfo.getAndroidClientInfo() == null : androidClientInfo.equals(clientInfo.getAndroidClientInfo());
    }

    @Override // com.google.android.datatransport.cct.internal.ClientInfo
    @Nullable
    public AndroidClientInfo getAndroidClientInfo() {
        return this.androidClientInfo;
    }

    @Override // com.google.android.datatransport.cct.internal.ClientInfo
    @Nullable
    public ClientInfo.ClientType getClientType() {
        return this.clientType;
    }

    public int hashCode() {
        ClientInfo.ClientType clientType = this.clientType;
        int iHashCode = ((clientType == null ? 0 : clientType.hashCode()) ^ 1000003) * 1000003;
        AndroidClientInfo androidClientInfo = this.androidClientInfo;
        return iHashCode ^ (androidClientInfo != null ? androidClientInfo.hashCode() : 0);
    }

    public String toString() {
        return "ClientInfo{clientType=" + this.clientType + ", androidClientInfo=" + this.androidClientInfo + "}";
    }

    public AutoValue_ClientInfo(@Nullable ClientInfo.ClientType clientType, @Nullable AndroidClientInfo androidClientInfo) {
        this.clientType = clientType;
        this.androidClientInfo = androidClientInfo;
    }
}
