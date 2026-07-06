package android.support.v4.media;

import androidx.annotation.RestrictTo;
import androidx.media.AudioAttributesImplApi26;
import androidx.versionedparcelable.VersionedParcel;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY})
public final class AudioAttributesImplApi26Parcelizer extends androidx.media.AudioAttributesImplApi26Parcelizer {
    public static AudioAttributesImplApi26 read(VersionedParcel versionedParcel) {
        return androidx.media.AudioAttributesImplApi26Parcelizer.read(versionedParcel);
    }

    public static void write(AudioAttributesImplApi26 audioAttributesImplApi26, VersionedParcel versionedParcel) {
        androidx.media.AudioAttributesImplApi26Parcelizer.write(audioAttributesImplApi26, versionedParcel);
    }
}
