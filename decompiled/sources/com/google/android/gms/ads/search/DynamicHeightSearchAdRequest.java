package com.google.android.gms.ads.search;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.internal.client.zzdx;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import kotlinx.coroutines.DebugKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class DynamicHeightSearchAdRequest {
    public final SearchAdRequest zza;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder {
        public final zzb zza = new zzb();
        public final Bundle zzb = new Bundle();

        @NonNull
        public Builder addCustomEventExtrasBundle(@NonNull Class<? extends CustomEvent> cls, @NonNull Bundle bundle) {
            this.zza.zzb(cls, bundle);
            return this;
        }

        @NonNull
        public Builder addNetworkExtras(@NonNull NetworkExtras networkExtras) {
            this.zza.zzc(networkExtras);
            return this;
        }

        @NonNull
        public Builder addNetworkExtrasBundle(@NonNull Class<? extends MediationAdapter> cls, @NonNull Bundle bundle) {
            this.zza.zzd(cls, bundle);
            return this;
        }

        @NonNull
        public DynamicHeightSearchAdRequest build() {
            zzb zzbVar = this.zza;
            zzbVar.zza.zzt(AdMobAdapter.class, this.zzb);
            return new DynamicHeightSearchAdRequest(this, null);
        }

        @NonNull
        public Builder setAdBorderSelectors(@NonNull String str) {
            this.zzb.putString("csa_adBorderSelectors", str);
            return this;
        }

        @NonNull
        public Builder setAdTest(boolean z) {
            this.zzb.putString("csa_adtest", true != z ? DebugKt.DEBUG_PROPERTY_VALUE_OFF : DebugKt.DEBUG_PROPERTY_VALUE_ON);
            return this;
        }

        @NonNull
        public Builder setAdjustableLineHeight(int i) {
            this.zzb.putString("csa_adjustableLineHeight", Integer.toString(i));
            return this;
        }

        @NonNull
        public Builder setAdvancedOptionValue(@NonNull String str, @NonNull String str2) {
            this.zzb.putString(str, str2);
            return this;
        }

        @NonNull
        public Builder setAttributionSpacingBelow(int i) {
            this.zzb.putString("csa_attributionSpacingBelow", Integer.toString(i));
            return this;
        }

        @NonNull
        public Builder setBorderSelections(@NonNull String str) {
            this.zzb.putString("csa_borderSelections", str);
            return this;
        }

        @NonNull
        public Builder setChannel(@NonNull String str) {
            this.zzb.putString("csa_channel", str);
            return this;
        }

        @NonNull
        public Builder setColorAdBorder(@NonNull String str) {
            this.zzb.putString("csa_colorAdBorder", str);
            return this;
        }

        @NonNull
        public Builder setColorAdSeparator(@NonNull String str) {
            this.zzb.putString("csa_colorAdSeparator", str);
            return this;
        }

        @NonNull
        public Builder setColorAnnotation(@NonNull String str) {
            this.zzb.putString("csa_colorAnnotation", str);
            return this;
        }

        @NonNull
        public Builder setColorAttribution(@NonNull String str) {
            this.zzb.putString("csa_colorAttribution", str);
            return this;
        }

        @NonNull
        public Builder setColorBackground(@NonNull String str) {
            this.zzb.putString("csa_colorBackground", str);
            return this;
        }

        @NonNull
        public Builder setColorBorder(@NonNull String str) {
            this.zzb.putString("csa_colorBorder", str);
            return this;
        }

        @NonNull
        public Builder setColorDomainLink(@NonNull String str) {
            this.zzb.putString("csa_colorDomainLink", str);
            return this;
        }

        @NonNull
        public Builder setColorText(@NonNull String str) {
            this.zzb.putString("csa_colorText", str);
            return this;
        }

        @NonNull
        public Builder setColorTitleLink(@NonNull String str) {
            this.zzb.putString("csa_colorTitleLink", str);
            return this;
        }

        @NonNull
        public Builder setCssWidth(int i) {
            this.zzb.putString("csa_width", Integer.toString(i));
            return this;
        }

        @NonNull
        public Builder setDetailedAttribution(boolean z) {
            this.zzb.putString("csa_detailedAttribution", Boolean.toString(z));
            return this;
        }

        @NonNull
        public Builder setFontFamily(@NonNull String str) {
            this.zzb.putString("csa_fontFamily", str);
            return this;
        }

        @NonNull
        public Builder setFontFamilyAttribution(@NonNull String str) {
            this.zzb.putString("csa_fontFamilyAttribution", str);
            return this;
        }

        @NonNull
        public Builder setFontSizeAnnotation(int i) {
            this.zzb.putString("csa_fontSizeAnnotation", Integer.toString(i));
            return this;
        }

        @NonNull
        public Builder setFontSizeAttribution(int i) {
            this.zzb.putString("csa_fontSizeAttribution", Integer.toString(i));
            return this;
        }

        @NonNull
        public Builder setFontSizeDescription(int i) {
            this.zzb.putString("csa_fontSizeDescription", Integer.toString(i));
            return this;
        }

        @NonNull
        public Builder setFontSizeDomainLink(int i) {
            this.zzb.putString("csa_fontSizeDomainLink", Integer.toString(i));
            return this;
        }

        @NonNull
        public Builder setFontSizeTitle(int i) {
            this.zzb.putString("csa_fontSizeTitle", Integer.toString(i));
            return this;
        }

        @NonNull
        public Builder setHostLanguage(@NonNull String str) {
            this.zzb.putString("csa_hl", str);
            return this;
        }

        @NonNull
        public Builder setIsClickToCallEnabled(boolean z) {
            this.zzb.putString("csa_clickToCall", Boolean.toString(z));
            return this;
        }

        @NonNull
        public Builder setIsLocationEnabled(boolean z) {
            this.zzb.putString("csa_location", Boolean.toString(z));
            return this;
        }

        @NonNull
        public Builder setIsPlusOnesEnabled(boolean z) {
            this.zzb.putString("csa_plusOnes", Boolean.toString(z));
            return this;
        }

        @NonNull
        public Builder setIsSellerRatingsEnabled(boolean z) {
            this.zzb.putString("csa_sellerRatings", Boolean.toString(z));
            return this;
        }

        @NonNull
        public Builder setIsSiteLinksEnabled(boolean z) {
            this.zzb.putString("csa_siteLinks", Boolean.toString(z));
            return this;
        }

        @NonNull
        public Builder setIsTitleBold(boolean z) {
            this.zzb.putString("csa_titleBold", Boolean.toString(z));
            return this;
        }

        @NonNull
        public Builder setIsTitleUnderlined(boolean z) {
            this.zzb.putString("csa_noTitleUnderline", Boolean.toString(!z));
            return this;
        }

        @NonNull
        public Builder setLocationColor(@NonNull String str) {
            this.zzb.putString("csa_colorLocation", str);
            return this;
        }

        @NonNull
        public Builder setLocationFontSize(int i) {
            this.zzb.putString("csa_fontSizeLocation", Integer.toString(i));
            return this;
        }

        @NonNull
        public Builder setLongerHeadlines(boolean z) {
            this.zzb.putString("csa_longerHeadlines", Boolean.toString(z));
            return this;
        }

        @NonNull
        public Builder setNumber(int i) {
            this.zzb.putString("csa_number", Integer.toString(i));
            return this;
        }

        @NonNull
        public Builder setPage(int i) {
            this.zzb.putString("csa_adPage", Integer.toString(i));
            return this;
        }

        @NonNull
        public Builder setQuery(@NonNull String str) {
            this.zza.zzb = str;
            return this;
        }

        @NonNull
        public Builder setStyleId(@NonNull String str) {
            this.zzb.putString("csa_styleId", str);
            return this;
        }

        @NonNull
        public Builder setVerticalSpacing(int i) {
            this.zzb.putString("csa_verticalSpacing", Integer.toString(i));
            return this;
        }
    }

    public /* synthetic */ DynamicHeightSearchAdRequest(Builder builder, zza zzaVar) {
        this.zza = new SearchAdRequest(builder.zza, null);
    }

    @Nullable
    public <T extends CustomEvent> Bundle getCustomEventExtrasBundle(@NonNull Class<T> cls) {
        return this.zza.zza.zzd(cls);
    }

    @Nullable
    public <T extends MediationAdapter> Bundle getNetworkExtrasBundle(@NonNull Class<T> cls) {
        return this.zza.zza.zzf(cls);
    }

    @NonNull
    public String getQuery() {
        return this.zza.zzb;
    }

    public boolean isTestDevice(@NonNull Context context) {
        return this.zza.zza.zzs(context);
    }

    public final zzdx zza() {
        return this.zza.zza;
    }
}
