package com.amazon.avod.mpb.media.drm;

import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class AndroidDrmSystemCapabilitiesProvider implements DrmSystemCapabilitiesProvider {

    @NotNull
    public final Lazy capabilities$delegate = LazyKt__LazyJVMKt.lazy(new AndroidDrmSystemCapabilitiesProvider$$ExternalSyntheticLambda0());

    public static final DrmSystemCapabilities capabilities_delegate$lambda$0() {
        return new DrmSystemCapabilities(CollectionsKt__CollectionsJVMKt.listOf(new SupportedSystem(DrmSystemName.WIDEVINE, CollectionsKt__CollectionsJVMKt.listOf(EncryptionScheme.CENC), CollectionsKt__CollectionsKt.listOf((Object[]) new SessionType[]{SessionType.PERSISTENT, SessionType.NON_PERSISTENT}), 6)));
    }

    @Override // com.amazon.avod.mpb.media.drm.DrmSystemCapabilitiesProvider
    @NotNull
    public DrmSystemCapabilities getCapabilities() {
        return (DrmSystemCapabilities) this.capabilities$delegate.getValue();
    }
}
