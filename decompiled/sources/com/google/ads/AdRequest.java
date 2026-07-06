package com.google.ads;

import androidx.annotation.NonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public final class AdRequest {

    @NonNull
    public static final String LOGTAG = "Ads";

    @NonNull
    public static final String TEST_EMULATOR = "B3EEABB8EE11C2BE770B684D95219ECB";

    @NonNull
    public static final String VERSION = "0.0.0";

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum ErrorCode {
        INVALID_REQUEST("Invalid Ad request."),
        NO_FILL("Ad request successful, but no ad returned due to lack of ad inventory."),
        NETWORK_ERROR("A network error occurred."),
        INTERNAL_ERROR("There was an internal error.");

        public final String zzb;

        ErrorCode(String str) {
            this.zzb = str;
        }

        @Override // java.lang.Enum
        @NonNull
        public String toString() {
            return this.zzb;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum Gender {
        UNKNOWN,
        MALE,
        FEMALE
    }
}
