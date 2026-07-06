package com.amazon.livingroom.mediapipelinebackend;

import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.livingroom.di.ApplicationScope;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public class HdcpChecker {
    public static final long MIN_CHECK_INTERVAL_NS = TimeUnit.NANOSECONDS.convert(10, TimeUnit.SECONDS);
    public boolean freeze;
    public final HdcpVersionProvider hdcpVersionProvider;
    public long lastCheckTimeNs;
    public HdcpVersion lastCheckedHdcpVersion;
    public HdcpVersion maxHdcpVersion;

    @Inject
    public HdcpChecker(HdcpVersionProvider hdcpVersionProvider) {
        this.hdcpVersionProvider = hdcpVersionProvider;
    }

    @NonNull
    public HdcpVersion getCurrentHdcpVersion(boolean z) {
        long jElapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
        HdcpVersion hdcpVersion = this.lastCheckedHdcpVersion;
        if (hdcpVersion != null && hdcpVersion.getMajorVersion() != 0 && !z && (this.freeze || jElapsedRealtimeNanos - this.lastCheckTimeNs < MIN_CHECK_INTERVAL_NS)) {
            return this.lastCheckedHdcpVersion;
        }
        HdcpVersion currentHdcpVersion = getCurrentHdcpVersion();
        HdcpVersion hdcpVersion2 = this.lastCheckedHdcpVersion;
        if (hdcpVersion2 == null || hdcpVersion2.compareTo(currentHdcpVersion) != 0) {
            MpbLog.i("Current HDCP version: " + currentHdcpVersion.getFullVersion());
            if (!this.freeze) {
                this.lastCheckedHdcpVersion = currentHdcpVersion;
                this.lastCheckTimeNs = jElapsedRealtimeNanos;
            }
        }
        return currentHdcpVersion;
    }

    @Nullable
    public HdcpVersion getMaxHdcpVersion() {
        return this.maxHdcpVersion;
    }

    public void setFreeze(boolean z) {
        this.freeze = z;
    }

    public final HdcpVersion getCurrentHdcpVersion() {
        String currentHdcpVersion = this.hdcpVersionProvider.getCurrentHdcpVersion();
        HdcpVersion hdcpVersion = this.lastCheckedHdcpVersion;
        if (hdcpVersion != null && hdcpVersion.getFullVersion().equals(currentHdcpVersion)) {
            return this.lastCheckedHdcpVersion;
        }
        HdcpVersion hdcpVersion2 = HdcpVersion.parse(currentHdcpVersion);
        HdcpVersion hdcpVersion3 = this.maxHdcpVersion;
        if (hdcpVersion3 != null && hdcpVersion3.compareTo(hdcpVersion2) >= 0) {
            return hdcpVersion2;
        }
        this.maxHdcpVersion = hdcpVersion2;
        return hdcpVersion2;
    }
}
