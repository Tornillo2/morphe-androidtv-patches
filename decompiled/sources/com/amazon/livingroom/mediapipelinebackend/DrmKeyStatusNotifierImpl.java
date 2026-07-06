package com.amazon.livingroom.mediapipelinebackend;

import android.media.MediaDrm;
import androidx.annotation.RequiresApi;
import com.amazon.avod.mpb.media.drm.AndroidDrmSystem$OnKeyStatusChangeListener$$ExternalSyntheticApiModelOutline0;
import com.amazon.avod.mpb.media.drm.DrmKeyStatusNotifier;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RequiresApi(23)
@SourceDebugExtension({"SMAP\nDrmKeyStatusNotifierImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DrmKeyStatusNotifierImpl.kt\ncom/amazon/livingroom/mediapipelinebackend/DrmKeyStatusNotifierImpl\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,65:1\n1761#2,3:66\n*S KotlinDebug\n*F\n+ 1 DrmKeyStatusNotifierImpl.kt\ncom/amazon/livingroom/mediapipelinebackend/DrmKeyStatusNotifierImpl\n*L\n35#1:66,3\n*E\n"})
public final class DrmKeyStatusNotifierImpl implements DrmKeyStatusNotifier {
    public Function1<? super Boolean, Unit> callback;

    @NotNull
    public final Set<String> sessionsWithRestrictedKeys = new LinkedHashSet();

    public static final CharSequence toHexString$lambda$3(byte b) {
        return String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
    }

    @Override // com.amazon.avod.mpb.media.drm.DrmKeyStatusNotifier
    public void onKeyStatusChange(@NotNull byte[] sessionId, @NotNull List<MediaDrm.KeyStatus> keyInformation) {
        boolean z;
        Intrinsics.checkNotNullParameter(sessionId, "sessionId");
        Intrinsics.checkNotNullParameter(keyInformation, "keyInformation");
        synchronized (this) {
            try {
                String hexString = toHexString(sessionId);
                boolean zAdd = true;
                if (keyInformation.isEmpty()) {
                    z = false;
                } else {
                    Iterator<T> it = keyInformation.iterator();
                    while (it.hasNext()) {
                        if (AndroidDrmSystem$OnKeyStatusChangeListener$$ExternalSyntheticApiModelOutline0.m(it.next()).getStatusCode() == 2) {
                            z = true;
                            break;
                        }
                    }
                    z = false;
                }
                if (z) {
                    zAdd = this.sessionsWithRestrictedKeys.add(hexString);
                } else if (!this.sessionsWithRestrictedKeys.remove(hexString) || !this.sessionsWithRestrictedKeys.isEmpty()) {
                    zAdd = false;
                }
                if (zAdd) {
                    MpbLog.i("DrmKeyStatusNotifier: session " + hexString + " has restricted keys=" + z + ", total sessions with restrictions=" + this.sessionsWithRestrictedKeys.size());
                    Function1<? super Boolean, Unit> function1 = this.callback;
                    if (function1 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("callback");
                        throw null;
                    }
                    function1.invoke(Boolean.valueOf(z));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.amazon.avod.mpb.media.drm.DrmKeyStatusNotifier
    public void removeSession(@NotNull byte[] sessionId) {
        Intrinsics.checkNotNullParameter(sessionId, "sessionId");
        synchronized (this) {
            try {
                String hexString = toHexString(sessionId);
                MpbLog.i("DrmKeyStatusNotifier: removing session " + hexString);
                if (this.sessionsWithRestrictedKeys.remove(hexString) && this.sessionsWithRestrictedKeys.isEmpty()) {
                    Function1<? super Boolean, Unit> function1 = this.callback;
                    if (function1 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("callback");
                        throw null;
                    }
                    function1.invoke(Boolean.FALSE);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.amazon.avod.mpb.media.drm.DrmKeyStatusNotifier
    public void setKeyStatusChangeCallback(@NotNull Function1<? super Boolean, Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.callback = callback;
    }

    public final String toHexString(byte[] bArr) {
        return ArraysKt___ArraysKt.joinToString$default(bArr, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) new DrmKeyStatusNotifierImpl$$ExternalSyntheticLambda0(), 30, (Object) null);
    }
}
