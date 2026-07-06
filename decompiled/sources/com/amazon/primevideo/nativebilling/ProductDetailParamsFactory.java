package com.amazon.primevideo.nativebilling;

import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.ProductDetails;
import javax.inject.Inject;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class ProductDetailParamsFactory {
    @Inject
    public ProductDetailParamsFactory() {
    }

    public static /* synthetic */ BillingFlowParams.ProductDetailsParams makeProductDetailParams$default(ProductDetailParamsFactory productDetailParamsFactory, ProductDetails productDetails, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = null;
        }
        return productDetailParamsFactory.makeProductDetailParams(productDetails, str);
    }

    @NotNull
    public final BillingFlowParams.ProductDetailsParams makeProductDetailParams(@NotNull ProductDetails productDetails, @Nullable String str) {
        Intrinsics.checkNotNullParameter(productDetails, "productDetails");
        BillingFlowParams.ProductDetailsParams.Builder builder = new BillingFlowParams.ProductDetailsParams.Builder();
        builder.setProductDetails(productDetails);
        if (str != null) {
            builder.setOfferToken(str);
        }
        return builder.build();
    }
}
