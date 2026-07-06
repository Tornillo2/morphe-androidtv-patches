package androidx.core.location;

import android.location.GnssStatus;
import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RequiresApi(24)
@RestrictTo({RestrictTo.Scope.LIBRARY})
public class GnssStatusWrapper extends GnssStatusCompat {
    public final GnssStatus mWrapped;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(26)
    public static class Api26Impl {
        public static float getCarrierFrequencyHz(GnssStatus gnssStatus, int i) {
            return gnssStatus.getCarrierFrequencyHz(i);
        }

        public static boolean hasCarrierFrequencyHz(GnssStatus gnssStatus, int i) {
            return gnssStatus.hasCarrierFrequencyHz(i);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(30)
    public static class Api30Impl {
        public static float getBasebandCn0DbHz(GnssStatus gnssStatus, int i) {
            return gnssStatus.getBasebandCn0DbHz(i);
        }

        public static boolean hasBasebandCn0DbHz(GnssStatus gnssStatus, int i) {
            return gnssStatus.hasBasebandCn0DbHz(i);
        }
    }

    public GnssStatusWrapper(Object obj) {
        GnssStatus gnssStatusM = GnssStatusWrapper$$ExternalSyntheticApiModelOutline0.m(obj);
        gnssStatusM.getClass();
        this.mWrapped = gnssStatusM;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GnssStatusWrapper) {
            return this.mWrapped.equals(((GnssStatusWrapper) obj).mWrapped);
        }
        return false;
    }

    @Override // androidx.core.location.GnssStatusCompat
    public float getAzimuthDegrees(int i) {
        return this.mWrapped.getAzimuthDegrees(i);
    }

    @Override // androidx.core.location.GnssStatusCompat
    public float getBasebandCn0DbHz(int i) {
        if (Build.VERSION.SDK_INT >= 30) {
            return Api30Impl.getBasebandCn0DbHz(this.mWrapped, i);
        }
        throw new UnsupportedOperationException();
    }

    @Override // androidx.core.location.GnssStatusCompat
    public float getCarrierFrequencyHz(int i) {
        if (Build.VERSION.SDK_INT >= 26) {
            return Api26Impl.getCarrierFrequencyHz(this.mWrapped, i);
        }
        throw new UnsupportedOperationException();
    }

    @Override // androidx.core.location.GnssStatusCompat
    public float getCn0DbHz(int i) {
        return this.mWrapped.getCn0DbHz(i);
    }

    @Override // androidx.core.location.GnssStatusCompat
    public int getConstellationType(int i) {
        return this.mWrapped.getConstellationType(i);
    }

    @Override // androidx.core.location.GnssStatusCompat
    public float getElevationDegrees(int i) {
        return this.mWrapped.getElevationDegrees(i);
    }

    @Override // androidx.core.location.GnssStatusCompat
    public int getSatelliteCount() {
        return this.mWrapped.getSatelliteCount();
    }

    @Override // androidx.core.location.GnssStatusCompat
    public int getSvid(int i) {
        return this.mWrapped.getSvid(i);
    }

    @Override // androidx.core.location.GnssStatusCompat
    public boolean hasAlmanacData(int i) {
        return this.mWrapped.hasAlmanacData(i);
    }

    @Override // androidx.core.location.GnssStatusCompat
    public boolean hasBasebandCn0DbHz(int i) {
        if (Build.VERSION.SDK_INT >= 30) {
            return Api30Impl.hasBasebandCn0DbHz(this.mWrapped, i);
        }
        return false;
    }

    @Override // androidx.core.location.GnssStatusCompat
    public boolean hasCarrierFrequencyHz(int i) {
        if (Build.VERSION.SDK_INT >= 26) {
            return Api26Impl.hasCarrierFrequencyHz(this.mWrapped, i);
        }
        return false;
    }

    @Override // androidx.core.location.GnssStatusCompat
    public boolean hasEphemerisData(int i) {
        return this.mWrapped.hasEphemerisData(i);
    }

    public int hashCode() {
        return this.mWrapped.hashCode();
    }

    @Override // androidx.core.location.GnssStatusCompat
    public boolean usedInFix(int i) {
        return this.mWrapped.usedInFix(i);
    }
}
