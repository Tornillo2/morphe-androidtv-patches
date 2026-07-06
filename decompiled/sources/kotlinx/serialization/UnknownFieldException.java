package kotlinx.serialization;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import kotlin.PublishedApi;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
@PublishedApi
public final class UnknownFieldException extends SerializationException {
    public UnknownFieldException(@Nullable String str) {
        super(str);
    }

    public UnknownFieldException(int i) {
        super(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("An unknown field for index ", i));
    }
}
