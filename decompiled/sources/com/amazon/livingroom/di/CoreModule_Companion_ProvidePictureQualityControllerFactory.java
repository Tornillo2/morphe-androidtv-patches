package com.amazon.livingroom.di;

import com.sony.dtv.picturequalitycontrol.PictureQualityController;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata("com.amazon.livingroom.di.ApplicationScope")
@DaggerGenerated
@QualifierMetadata
public final class CoreModule_Companion_ProvidePictureQualityControllerFactory implements Factory<PictureQualityController> {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class InstanceHolder {
        public static final CoreModule_Companion_ProvidePictureQualityControllerFactory INSTANCE = new CoreModule_Companion_ProvidePictureQualityControllerFactory();
    }

    public static CoreModule_Companion_ProvidePictureQualityControllerFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static PictureQualityController providePictureQualityController() {
        CoreModule.Companion.getClass();
        return new PictureQualityController();
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public PictureQualityController get() {
        return providePictureQualityController();
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public Object get() {
        return providePictureQualityController();
    }
}
