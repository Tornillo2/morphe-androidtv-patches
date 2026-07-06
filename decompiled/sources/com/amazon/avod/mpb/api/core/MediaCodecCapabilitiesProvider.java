package com.amazon.avod.mpb.api.core;

import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class MediaCodecCapabilitiesProvider implements CapabilitiesProvider {

    @NotNull
    public final Lazy capabilities$delegate;

    public MediaCodecCapabilitiesProvider(final boolean z, final boolean z2, final boolean z3, final int i) {
        this.capabilities$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.amazon.avod.mpb.api.core.MediaCodecCapabilitiesProvider$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MediaCodecCapabilitiesProvider.capabilities_delegate$lambda$0(z, z2, z3, i);
            }
        });
    }

    public static final MediaPipelineBackendCapabilities capabilities_delegate$lambda$0(boolean z, boolean z2, boolean z3, int i) {
        return new MediaPipelineBackendCapabilities(z, z2 ? CollectionsKt__CollectionsKt.listOf((Object[]) new PictureMode[]{PictureMode.NONE, PictureMode.FILM_MAKER}) : CollectionsKt__CollectionsJVMKt.listOf(PictureMode.NONE), z3, true, true, i, true, true, true, SetsKt__SetsJVMKt.setOf(TrackConfiguration.AV), true);
    }

    @Override // com.amazon.avod.mpb.api.core.CapabilitiesProvider
    @NotNull
    public MediaPipelineBackendCapabilities getCapabilities() {
        return (MediaPipelineBackendCapabilities) this.capabilities$delegate.getValue();
    }
}
