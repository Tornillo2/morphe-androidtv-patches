package android.support.v4.media;

import androidx.annotation.RestrictTo;
import androidx.media.AudioAttributesImplApi21;
import androidx.versionedparcelable.VersionedParcel;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY})
public final class AudioAttributesImplApi21Parcelizer extends androidx.media.AudioAttributesImplApi21Parcelizer {
    public static AudioAttributesImplApi21 read(VersionedParcel versionedParcel) {
        return androidx.media.AudioAttributesImplApi21Parcelizer.read(versionedParcel);
    }

    public static void write(AudioAttributesImplApi21 audioAttributesImplApi21, VersionedParcel versionedParcel) {
        androidx.media.AudioAttributesImplApi21Parcelizer.write(audioAttributesImplApi21, versionedParcel);
    }
}
