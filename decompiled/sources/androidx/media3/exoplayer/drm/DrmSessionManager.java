package androidx.media3.exoplayer.drm;

import android.os.Looper;
import androidx.annotation.Nullable;
import androidx.media3.common.Format;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.drm.DrmSession;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public interface DrmSessionManager {
    public static final DrmSessionManager DRM_UNSUPPORTED;

    @Deprecated
    public static final DrmSessionManager DUMMY;

    static {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        DRM_UNSUPPORTED = anonymousClass1;
        DUMMY = anonymousClass1;
    }

    @Nullable
    DrmSession acquireSession(@Nullable DrmSessionEventListener.EventDispatcher eventDispatcher, Format format);

    int getCryptoType(Format format);

    DrmSessionReference preacquireSession(@Nullable DrmSessionEventListener.EventDispatcher eventDispatcher, Format format);

    void prepare();

    void release();

    void setPlayer(Looper looper, PlayerId playerId);

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface DrmSessionReference {
        public static final DrmSessionReference EMPTY = new DrmSessionManager$DrmSessionReference$$ExternalSyntheticLambda0();

        void release();

        /* JADX INFO: renamed from: androidx.media3.exoplayer.drm.DrmSessionManager$DrmSessionReference$-CC, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public final /* synthetic */ class CC {
            public static /* synthetic */ void lambda$static$0() {
            }
        }
    }

    /* JADX INFO: renamed from: androidx.media3.exoplayer.drm.DrmSessionManager$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
        public static DrmSessionReference $default$preacquireSession(DrmSessionManager drmSessionManager, @Nullable DrmSessionEventListener.EventDispatcher eventDispatcher, Format format) {
            return DrmSessionReference.EMPTY;
        }

        @Deprecated
        public static DrmSessionManager getDummyDrmSessionManager() {
            return DrmSessionManager.DRM_UNSUPPORTED;
        }

        public static void $default$prepare(DrmSessionManager drmSessionManager) {
        }

        public static void $default$release(DrmSessionManager drmSessionManager) {
        }
    }

    /* JADX INFO: renamed from: androidx.media3.exoplayer.drm.DrmSessionManager$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 implements DrmSessionManager {
        @Override // androidx.media3.exoplayer.drm.DrmSessionManager
        @Nullable
        public DrmSession acquireSession(@Nullable DrmSessionEventListener.EventDispatcher eventDispatcher, Format format) {
            if (format.drmInitData == null) {
                return null;
            }
            return new ErrorStateDrmSession(new DrmSession.DrmSessionException(new UnsupportedDrmException(1), 6001));
        }

        @Override // androidx.media3.exoplayer.drm.DrmSessionManager
        public int getCryptoType(Format format) {
            return format.drmInitData != null ? 1 : 0;
        }

        @Override // androidx.media3.exoplayer.drm.DrmSessionManager
        public /* synthetic */ DrmSessionReference preacquireSession(DrmSessionEventListener.EventDispatcher eventDispatcher, Format format) {
            return CC.$default$preacquireSession(this, eventDispatcher, format);
        }

        @Override // androidx.media3.exoplayer.drm.DrmSessionManager
        public /* synthetic */ void prepare() {
            CC.$default$prepare(this);
        }

        @Override // androidx.media3.exoplayer.drm.DrmSessionManager
        public /* synthetic */ void release() {
            CC.$default$release(this);
        }

        @Override // androidx.media3.exoplayer.drm.DrmSessionManager
        public void setPlayer(Looper looper, PlayerId playerId) {
        }
    }
}
