package com.google.android.datatransport.cct.internal;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat$$ExternalSyntheticOutline0;
import com.google.android.datatransport.cct.internal.AndroidClientInfo;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class AutoValue_AndroidClientInfo extends AndroidClientInfo {
    public final String applicationBuild;
    public final String country;
    public final String device;
    public final String fingerprint;
    public final String hardware;
    public final String locale;
    public final String manufacturer;
    public final String mccMnc;
    public final String model;
    public final String osBuild;
    public final String product;
    public final Integer sdkVersion;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder extends AndroidClientInfo.Builder {
        public String applicationBuild;
        public String country;
        public String device;
        public String fingerprint;
        public String hardware;
        public String locale;
        public String manufacturer;
        public String mccMnc;
        public String model;
        public String osBuild;
        public String product;
        public Integer sdkVersion;

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo build() {
            return new AutoValue_AndroidClientInfo(this.sdkVersion, this.model, this.hardware, this.device, this.product, this.osBuild, this.manufacturer, this.fingerprint, this.locale, this.country, this.mccMnc, this.applicationBuild);
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder setApplicationBuild(@Nullable String str) {
            this.applicationBuild = str;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder setCountry(@Nullable String str) {
            this.country = str;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder setDevice(@Nullable String str) {
            this.device = str;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder setFingerprint(@Nullable String str) {
            this.fingerprint = str;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder setHardware(@Nullable String str) {
            this.hardware = str;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder setLocale(@Nullable String str) {
            this.locale = str;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder setManufacturer(@Nullable String str) {
            this.manufacturer = str;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder setMccMnc(@Nullable String str) {
            this.mccMnc = str;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder setModel(@Nullable String str) {
            this.model = str;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder setOsBuild(@Nullable String str) {
            this.osBuild = str;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder setProduct(@Nullable String str) {
            this.product = str;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder setSdkVersion(@Nullable Integer num) {
            this.sdkVersion = num;
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AndroidClientInfo)) {
            return false;
        }
        AndroidClientInfo androidClientInfo = (AndroidClientInfo) obj;
        Integer num = this.sdkVersion;
        if (num == null) {
            if (androidClientInfo.getSdkVersion() != null) {
                return false;
            }
        } else if (!num.equals(androidClientInfo.getSdkVersion())) {
            return false;
        }
        String str = this.model;
        if (str == null) {
            if (androidClientInfo.getModel() != null) {
                return false;
            }
        } else if (!str.equals(androidClientInfo.getModel())) {
            return false;
        }
        String str2 = this.hardware;
        if (str2 == null) {
            if (androidClientInfo.getHardware() != null) {
                return false;
            }
        } else if (!str2.equals(androidClientInfo.getHardware())) {
            return false;
        }
        String str3 = this.device;
        if (str3 == null) {
            if (androidClientInfo.getDevice() != null) {
                return false;
            }
        } else if (!str3.equals(androidClientInfo.getDevice())) {
            return false;
        }
        String str4 = this.product;
        if (str4 == null) {
            if (androidClientInfo.getProduct() != null) {
                return false;
            }
        } else if (!str4.equals(androidClientInfo.getProduct())) {
            return false;
        }
        String str5 = this.osBuild;
        if (str5 == null) {
            if (androidClientInfo.getOsBuild() != null) {
                return false;
            }
        } else if (!str5.equals(androidClientInfo.getOsBuild())) {
            return false;
        }
        String str6 = this.manufacturer;
        if (str6 == null) {
            if (androidClientInfo.getManufacturer() != null) {
                return false;
            }
        } else if (!str6.equals(androidClientInfo.getManufacturer())) {
            return false;
        }
        String str7 = this.fingerprint;
        if (str7 == null) {
            if (androidClientInfo.getFingerprint() != null) {
                return false;
            }
        } else if (!str7.equals(androidClientInfo.getFingerprint())) {
            return false;
        }
        String str8 = this.locale;
        if (str8 == null) {
            if (androidClientInfo.getLocale() != null) {
                return false;
            }
        } else if (!str8.equals(androidClientInfo.getLocale())) {
            return false;
        }
        String str9 = this.country;
        if (str9 == null) {
            if (androidClientInfo.getCountry() != null) {
                return false;
            }
        } else if (!str9.equals(androidClientInfo.getCountry())) {
            return false;
        }
        String str10 = this.mccMnc;
        if (str10 == null) {
            if (androidClientInfo.getMccMnc() != null) {
                return false;
            }
        } else if (!str10.equals(androidClientInfo.getMccMnc())) {
            return false;
        }
        String str11 = this.applicationBuild;
        return str11 == null ? androidClientInfo.getApplicationBuild() == null : str11.equals(androidClientInfo.getApplicationBuild());
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    @Nullable
    public String getApplicationBuild() {
        return this.applicationBuild;
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    @Nullable
    public String getCountry() {
        return this.country;
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    @Nullable
    public String getDevice() {
        return this.device;
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    @Nullable
    public String getFingerprint() {
        return this.fingerprint;
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    @Nullable
    public String getHardware() {
        return this.hardware;
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    @Nullable
    public String getLocale() {
        return this.locale;
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    @Nullable
    public String getManufacturer() {
        return this.manufacturer;
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    @Nullable
    public String getMccMnc() {
        return this.mccMnc;
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    @Nullable
    public String getModel() {
        return this.model;
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    @Nullable
    public String getOsBuild() {
        return this.osBuild;
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    @Nullable
    public String getProduct() {
        return this.product;
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    @Nullable
    public Integer getSdkVersion() {
        return this.sdkVersion;
    }

    public int hashCode() {
        Integer num = this.sdkVersion;
        int iHashCode = ((num == null ? 0 : num.hashCode()) ^ 1000003) * 1000003;
        String str = this.model;
        int iHashCode2 = (iHashCode ^ (str == null ? 0 : str.hashCode())) * 1000003;
        String str2 = this.hardware;
        int iHashCode3 = (iHashCode2 ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        String str3 = this.device;
        int iHashCode4 = (iHashCode3 ^ (str3 == null ? 0 : str3.hashCode())) * 1000003;
        String str4 = this.product;
        int iHashCode5 = (iHashCode4 ^ (str4 == null ? 0 : str4.hashCode())) * 1000003;
        String str5 = this.osBuild;
        int iHashCode6 = (iHashCode5 ^ (str5 == null ? 0 : str5.hashCode())) * 1000003;
        String str6 = this.manufacturer;
        int iHashCode7 = (iHashCode6 ^ (str6 == null ? 0 : str6.hashCode())) * 1000003;
        String str7 = this.fingerprint;
        int iHashCode8 = (iHashCode7 ^ (str7 == null ? 0 : str7.hashCode())) * 1000003;
        String str8 = this.locale;
        int iHashCode9 = (iHashCode8 ^ (str8 == null ? 0 : str8.hashCode())) * 1000003;
        String str9 = this.country;
        int iHashCode10 = (iHashCode9 ^ (str9 == null ? 0 : str9.hashCode())) * 1000003;
        String str10 = this.mccMnc;
        int iHashCode11 = (iHashCode10 ^ (str10 == null ? 0 : str10.hashCode())) * 1000003;
        String str11 = this.applicationBuild;
        return iHashCode11 ^ (str11 != null ? str11.hashCode() : 0);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("AndroidClientInfo{sdkVersion=");
        sb.append(this.sdkVersion);
        sb.append(", model=");
        sb.append(this.model);
        sb.append(", hardware=");
        sb.append(this.hardware);
        sb.append(", device=");
        sb.append(this.device);
        sb.append(", product=");
        sb.append(this.product);
        sb.append(", osBuild=");
        sb.append(this.osBuild);
        sb.append(", manufacturer=");
        sb.append(this.manufacturer);
        sb.append(", fingerprint=");
        sb.append(this.fingerprint);
        sb.append(", locale=");
        sb.append(this.locale);
        sb.append(", country=");
        sb.append(this.country);
        sb.append(", mccMnc=");
        sb.append(this.mccMnc);
        sb.append(", applicationBuild=");
        return ActivityCompat$$ExternalSyntheticOutline0.m(sb, this.applicationBuild, "}");
    }

    public AutoValue_AndroidClientInfo(@Nullable Integer num, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable String str9, @Nullable String str10, @Nullable String str11) {
        this.sdkVersion = num;
        this.model = str;
        this.hardware = str2;
        this.device = str3;
        this.product = str4;
        this.osBuild = str5;
        this.manufacturer = str6;
        this.fingerprint = str7;
        this.locale = str8;
        this.country = str9;
        this.mccMnc = str10;
        this.applicationBuild = str11;
    }
}
