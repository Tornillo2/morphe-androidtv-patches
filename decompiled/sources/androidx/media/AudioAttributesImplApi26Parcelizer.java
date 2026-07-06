package androidx.media;

import android.media.AudioAttributes;
import androidx.annotation.RestrictTo;
import androidx.versionedparcelable.VersionedParcel;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY})
public class AudioAttributesImplApi26Parcelizer {
    public static AudioAttributesImplApi26 read(VersionedParcel versionedParcel) {
        AudioAttributesImplApi26 audioAttributesImplApi26 = new AudioAttributesImplApi26();
        audioAttributesImplApi26.mAudioAttributes = (AudioAttributes) versionedParcel.readParcelable(audioAttributesImplApi26.mAudioAttributes, 1);
        audioAttributesImplApi26.mLegacyStreamType = versionedParcel.readInt(audioAttributesImplApi26.mLegacyStreamType, 2);
        return audioAttributesImplApi26;
    }

    public static void write(AudioAttributesImplApi26 audioAttributesImplApi26, VersionedParcel versionedParcel) {
        versionedParcel.setSerializationFlags(false, false);
        versionedParcel.writeParcelable(audioAttributesImplApi26.mAudioAttributes, 1);
        versionedParcel.writeInt(audioAttributesImplApi26.mLegacyStreamType, 2);
    }
}
