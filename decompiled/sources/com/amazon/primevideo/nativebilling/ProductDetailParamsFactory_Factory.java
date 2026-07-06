package com.amazon.primevideo.nativebilling;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata
public final class ProductDetailParamsFactory_Factory implements Factory<ProductDetailParamsFactory> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class InstanceHolder {
        public static final ProductDetailParamsFactory_Factory INSTANCE = new ProductDetailParamsFactory_Factory();
    }

    public static ProductDetailParamsFactory_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static ProductDetailParamsFactory newInstance() {
        return new ProductDetailParamsFactory();
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public ProductDetailParamsFactory get() {
        return new ProductDetailParamsFactory();
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public Object get() {
        return new ProductDetailParamsFactory();
    }
}
