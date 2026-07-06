package com.android.billingclient.api;

import androidx.annotation.NonNull;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@zzt
public final class QueryProductDetailsResult {
    public final List<ProductDetails> productDetailsList;
    public final List<UnfetchedProduct> unfetchedProductList;

    public QueryProductDetailsResult(List<ProductDetails> list, List<UnfetchedProduct> list2) {
        this.productDetailsList = list;
        this.unfetchedProductList = list2;
    }

    @NonNull
    public static QueryProductDetailsResult create(@NonNull List<ProductDetails> list, @NonNull List<UnfetchedProduct> list2) {
        return new QueryProductDetailsResult(list, list2);
    }

    @NonNull
    @zzt
    public List<ProductDetails> getProductDetailsList() {
        return this.productDetailsList;
    }

    @NonNull
    @zzt
    public List<UnfetchedProduct> getUnfetchedProductList() {
        return this.unfetchedProductList;
    }
}
