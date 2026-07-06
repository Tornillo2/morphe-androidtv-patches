package com.amazon.ignitionshared;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ScopeMetadata("com.amazon.livingroom.di.ApplicationScope")
@DaggerGenerated
@QualifierMetadata
public final class NativeAllocatorMessageHandler_Factory implements Factory<NativeAllocatorMessageHandler> {
    public final Provider<GMBMessageProcessor> gmbMessageProcessorProvider;
    public final Provider<NativeAllocator> nativeAllocatorProvider;

    public NativeAllocatorMessageHandler_Factory(Provider<GMBMessageProcessor> provider, Provider<NativeAllocator> provider2) {
        this.gmbMessageProcessorProvider = provider;
        this.nativeAllocatorProvider = provider2;
    }

    public static NativeAllocatorMessageHandler_Factory create(Provider<GMBMessageProcessor> provider, Provider<NativeAllocator> provider2) {
        return new NativeAllocatorMessageHandler_Factory(provider, provider2);
    }

    public static NativeAllocatorMessageHandler newInstance(GMBMessageProcessor gMBMessageProcessor, NativeAllocator nativeAllocator) {
        return new NativeAllocatorMessageHandler(gMBMessageProcessor, nativeAllocator);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public NativeAllocatorMessageHandler get() {
        return new NativeAllocatorMessageHandler(this.gmbMessageProcessorProvider.get(), this.nativeAllocatorProvider.get());
    }
}
