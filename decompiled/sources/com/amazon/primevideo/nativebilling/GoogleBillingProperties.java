package com.amazon.primevideo.nativebilling;

import com.amazon.livingroom.deviceproperties.BillingProperties;
import com.amazon.livingroom.di.ApplicationScope;
import javax.inject.Inject;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public final class GoogleBillingProperties implements BillingProperties {
    public final int alternativeBillingVersion;

    @NotNull
    public final BillingClientStatusProvider billingClientStatusProvider;
    public final int channelFlowVersion;
    public final int primeAddOnFlowVersion;
    public final int primeFlowVersion;
    public final int tvodFlowVersion;

    @Inject
    public GoogleBillingProperties(@NotNull BillingClientStatusProvider billingClientStatusProvider) {
        Intrinsics.checkNotNullParameter(billingClientStatusProvider, "billingClientStatusProvider");
        this.billingClientStatusProvider = billingClientStatusProvider;
        this.primeFlowVersion = 2;
        this.tvodFlowVersion = 1;
        this.channelFlowVersion = 1;
        this.alternativeBillingVersion = 1;
        this.primeAddOnFlowVersion = 1;
    }

    @Override // com.amazon.livingroom.deviceproperties.BillingProperties
    public int getAmpAlternativeBillingVersion() {
        return this.alternativeBillingVersion;
    }

    @Override // com.amazon.livingroom.deviceproperties.BillingProperties
    public int getEmpChannelsFlowVersion() {
        if (this.billingClientStatusProvider.isChannelsSupported()) {
            return this.channelFlowVersion;
        }
        return 0;
    }

    @Override // com.amazon.livingroom.deviceproperties.BillingProperties
    public int getEmpPrimeAddOnFlowVersion() {
        if (this.billingClientStatusProvider.isPrimeAddOnSupported()) {
            return this.primeAddOnFlowVersion;
        }
        return 0;
    }

    @Override // com.amazon.livingroom.deviceproperties.BillingProperties
    public int getEmpPrimeFlowVersion() {
        if (this.billingClientStatusProvider.isPrimeSupported()) {
            return this.primeFlowVersion;
        }
        return 0;
    }

    @Override // com.amazon.livingroom.deviceproperties.BillingProperties
    public int getEmpTvodFlowVersion() {
        if (this.billingClientStatusProvider.isTvodSupported()) {
            return this.tvodFlowVersion;
        }
        return 0;
    }
}
