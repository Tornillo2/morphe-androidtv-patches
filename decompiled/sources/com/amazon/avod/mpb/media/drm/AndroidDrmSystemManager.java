package com.amazon.avod.mpb.media.drm;

import android.media.UnsupportedSchemeException;
import android.os.Build;
import com.amazon.avod.mpb.api.core.FailoverManager;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendException;
import com.amazon.avod.mpb.api.core.MediaPipelineBackendResultCode;
import com.amazon.avod.mpb.api.core.MpbLog;
import com.amazon.avod.mpb.api.core.NoOpFailoverManager;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;
import javax.annotation.concurrent.ThreadSafe;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.json.Json;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ThreadSafe
@SourceDebugExtension({"SMAP\nAndroidDrmSystemManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AndroidDrmSystemManager.kt\ncom/amazon/avod/mpb/media/drm/AndroidDrmSystemManager\n+ 2 Json.kt\nkotlinx/serialization/json/Json\n*L\n1#1,172:1\n205#2:173\n*S KotlinDebug\n*F\n+ 1 AndroidDrmSystemManager.kt\ncom/amazon/avod/mpb/media/drm/AndroidDrmSystemManager\n*L\n42#1:173\n*E\n"})
public final class AndroidDrmSystemManager {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final String TAG = "AndroidDrmSystemManager";

    @NotNull
    public final Set<AndroidDrmSystem> activeDrmSystems;

    @NotNull
    public final String capabilities;

    @NotNull
    public final MediaDrmProvisioner drmProvisioner;

    @NotNull
    public final Function4<UUID, MediaDrmProvisioner, DrmKeyStatusNotifier, FailoverManager, AndroidDrmSystem> drmSystemProvider;

    @NotNull
    public final FailoverManager failoverManager;

    @NotNull
    public final Object mutex;

    /* JADX INFO: renamed from: com.amazon.avod.mpb.media.drm.AndroidDrmSystemManager$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function4<UUID, MediaDrmProvisioner, DrmKeyStatusNotifier, FailoverManager, AndroidDrmSystem> {
        public AnonymousClass1(Object obj) {
            super(4, obj, Companion.class, "getDrmSystem", "getDrmSystem(Ljava/util/UUID;Lcom/amazon/avod/mpb/media/drm/MediaDrmProvisioner;Lcom/amazon/avod/mpb/media/drm/DrmKeyStatusNotifier;Lcom/amazon/avod/mpb/api/core/FailoverManager;)Lcom/amazon/avod/mpb/media/drm/AndroidDrmSystem;", 0);
        }

        @Override // kotlin.jvm.functions.Function4
        public final AndroidDrmSystem invoke(UUID p0, MediaDrmProvisioner p1, DrmKeyStatusNotifier p2, FailoverManager p3) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            Intrinsics.checkNotNullParameter(p1, "p1");
            Intrinsics.checkNotNullParameter(p2, "p2");
            Intrinsics.checkNotNullParameter(p3, "p3");
            return ((Companion) this.receiver).getDrmSystem(p0, p1, p2, p3);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public final AndroidDrmSystem getDrmSystem(UUID uuid, MediaDrmProvisioner mediaDrmProvisioner, DrmKeyStatusNotifier drmKeyStatusNotifier, FailoverManager failoverManager) {
            return new AndroidDrmSystem(uuid, mediaDrmProvisioner, Build.VERSION.SDK_INT, new AndroidBase64Util(), drmKeyStatusNotifier, failoverManager);
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AndroidDrmSystemManager(@NotNull MediaDrmProvisioner drmProvisioner, @NotNull FailoverManager failoverManager, @NotNull Function4<? super UUID, ? super MediaDrmProvisioner, ? super DrmKeyStatusNotifier, ? super FailoverManager, AndroidDrmSystem> drmSystemProvider, @NotNull DrmSystemCapabilitiesProvider capabilitiesProvider) throws MediaPipelineBackendException {
        Intrinsics.checkNotNullParameter(drmProvisioner, "drmProvisioner");
        Intrinsics.checkNotNullParameter(failoverManager, "failoverManager");
        Intrinsics.checkNotNullParameter(drmSystemProvider, "drmSystemProvider");
        Intrinsics.checkNotNullParameter(capabilitiesProvider, "capabilitiesProvider");
        this.drmProvisioner = drmProvisioner;
        this.failoverManager = failoverManager;
        this.drmSystemProvider = drmSystemProvider;
        this.mutex = new Object();
        this.activeDrmSystems = new HashSet();
        try {
            Json.Default r2 = Json.Default;
            DrmSystemCapabilities capabilities = capabilitiesProvider.getCapabilities();
            r2.getClass();
            this.capabilities = r2.encodeToString(DrmSystemCapabilities.Companion.serializer(), capabilities);
        } catch (Exception e) {
            MpbLog.exceptionf(e, "AndroidDrmSystemManager failed to encode capabilities", new Object[0]);
            throw new MediaPipelineBackendException("AndroidDrmSystemManager failed to encode capabilities", MediaPipelineBackendResultCode.AV_MPB_DRM_CAPABILITIES_QUERY_EXCEPTION);
        }
    }

    @NotNull
    public final AndroidDrmSystem createSystem(@NotNull String schemeName, @NotNull DrmKeyStatusNotifier keyStatusNotifier) throws MediaPipelineBackendException {
        AndroidDrmSystem androidDrmSystemInvoke;
        Intrinsics.checkNotNullParameter(schemeName, "schemeName");
        Intrinsics.checkNotNullParameter(keyStatusNotifier, "keyStatusNotifier");
        synchronized (this.mutex) {
            MpbLog.devf("AndroidDrmSystemManager.createSystem", new Object[0]);
            UUID drmSchemeId = getDrmSchemeId(schemeName);
            if (drmSchemeId == null) {
                String str = "AndroidDrmSystemManager.createSystem unrecognized DRM scheme '" + schemeName + "'";
                MpbLog.warnf(str, new Object[0]);
                throw new MediaPipelineBackendException(str, MediaPipelineBackendResultCode.DRM_CREATE_SYSTEM_UNKNOWN_SCHEME);
            }
            try {
                try {
                    androidDrmSystemInvoke = this.drmSystemProvider.invoke(drmSchemeId, this.drmProvisioner, keyStatusNotifier, this.failoverManager);
                    this.activeDrmSystems.add(androidDrmSystemInvoke);
                    MpbLog.devf("AndroidDrmSystemManager.createSystem - created drmSystem=" + androidDrmSystemInvoke, new Object[0]);
                } catch (Exception e) {
                    MpbLog.exceptionf(e, "AndroidDrmSystemManager.createSystem failed to initialize drmSystem", new Object[0]);
                    throw new MediaPipelineBackendException("AndroidDrmSystemManager.createSystem failed to initialize drmSystem", MediaPipelineBackendResultCode.DRM_CREATE_SYSTEM_FAILED_TO_CREATE_MEDIA_DRM, e);
                }
            } catch (UnsupportedSchemeException e2) {
                String str2 = "AndroidDrmSystemManager.createSystem unsupported DRM scheme '" + drmSchemeId + "'";
                MpbLog.exceptionf(e2, str2, new Object[0]);
                throw new MediaPipelineBackendException(str2, MediaPipelineBackendResultCode.DRM_CREATE_SYSTEM_UNSUPPORTED_SCHEME);
            } catch (MediaPipelineBackendException e3) {
                MpbLog.exceptionf(e3, "AndroidDrmSystemManager.createSystem failed to provision", new Object[0]);
                throw e3;
            }
        }
        return androidDrmSystemInvoke;
    }

    public final void destroySystem(@NotNull AndroidDrmSystem drmSystem) {
        Intrinsics.checkNotNullParameter(drmSystem, "drmSystem");
        synchronized (this.mutex) {
            MpbLog.devf("AndroidDrmSystemManager.destroySystem drmSystem=" + drmSystem, new Object[0]);
            this.activeDrmSystems.remove(drmSystem);
            drmSystem.close();
        }
    }

    @NotNull
    public final String getCapabilities() {
        return this.capabilities;
    }

    public final UUID getDrmSchemeId(String str) {
        if (Intrinsics.areEqual(str, "com.microsoft.playready")) {
            DrmUtils.INSTANCE.getClass();
            return DrmUtils.PLAYREADY_UUID;
        }
        if (!Intrinsics.areEqual(str, "com.widevine.alpha")) {
            return null;
        }
        DrmUtils.INSTANCE.getClass();
        return DrmUtils.WIDEVINE_UUID;
    }

    public final void shutdown() {
        synchronized (this.mutex) {
            MpbLog.devf("AndroidDrmSystemManager.shutdown", new Object[0]);
            if (!this.activeDrmSystems.isEmpty()) {
                MpbLog.warnf("AndroidDrmSystemManager.shutdown shutting down DRM with " + this.activeDrmSystems.size() + " active systems", new Object[0]);
                Iterator<AndroidDrmSystem> it = this.activeDrmSystems.iterator();
                while (it.hasNext()) {
                    try {
                        it.next().close();
                    } catch (Exception e) {
                        MpbLog.exceptionf(e, "AndroidDrmSystemManager.shutdown failed to close DRM system", new Object[0]);
                        throw new MediaPipelineBackendException("AndroidDrmSystemManager.shutdown failed to close DRM system", MediaPipelineBackendResultCode.DRM_SHUTDOWN_FAILED_TO_RELEASE_MEDIA_DRM);
                    }
                }
                this.activeDrmSystems.clear();
            }
        }
    }

    public /* synthetic */ AndroidDrmSystemManager(MediaDrmProvisioner mediaDrmProvisioner, FailoverManager failoverManager, Function4 function4, DrmSystemCapabilitiesProvider drmSystemCapabilitiesProvider, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(mediaDrmProvisioner, (i & 2) != 0 ? NoOpFailoverManager.INSTANCE : failoverManager, (i & 4) != 0 ? new AnonymousClass1(Companion) : function4, (i & 8) != 0 ? new AndroidDrmSystemCapabilitiesProvider() : drmSystemCapabilitiesProvider);
    }
}
