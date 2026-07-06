package com.google.android.gms.common.internal;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@KeepForSdk
public class TelemetryLoggingOptions implements Api.ApiOptions.Optional {

    @NonNull
    public static final TelemetryLoggingOptions zaa = new Builder().build();

    @Nullable
    public final String zab;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @KeepForSdk
    public static class Builder {

        @Nullable
        public String zaa;

        public Builder() {
        }

        @NonNull
        @KeepForSdk
        public TelemetryLoggingOptions build() {
            return new TelemetryLoggingOptions(this.zaa, null);
        }

        @NonNull
        @CanIgnoreReturnValue
        @KeepForSdk
        public Builder setApi(@Nullable String str) {
            this.zaa = str;
            return this;
        }

        public /* synthetic */ Builder(zaac zaacVar) {
        }
    }

    public /* synthetic */ TelemetryLoggingOptions(String str, zaad zaadVar) {
        this.zab = str;
    }

    @NonNull
    @KeepForSdk
    public static Builder builder() {
        return new Builder();
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TelemetryLoggingOptions) {
            return Objects.equal(this.zab, ((TelemetryLoggingOptions) obj).zab);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zab});
    }

    @NonNull
    public final Bundle zaa() {
        Bundle bundle = new Bundle();
        String str = this.zab;
        if (str != null) {
            bundle.putString("api", str);
        }
        return bundle;
    }
}
