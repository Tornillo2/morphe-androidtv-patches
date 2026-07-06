package com.amazon.livingroom.deviceproperties;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.os.Handler;
import android.view.Display;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.amazon.ignitionshared.GMBMessageSender;
import com.amazon.livingroom.di.ApplicationContext;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.reporting.Log;
import j$.util.Objects;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public class DisplayPropertiesProvider {
    public static final String LOG_TAG = "DisplayPropertiesProvider";
    public static final String PROPERTY_DEVICE_TYPE = "ro.hdmi.device_type";
    public final Context context;
    public DisplayProperties displayProperties;
    public final GMBMessageSender gmbMessageSender;
    public final BroadcastReceiver hdmiAudioPlugReceiver;
    public long lastCheckTimeNs;
    public final MaxVideoResolutionProvider maxVideoResolutionProvider;
    public final PackageManager packageManager;
    public final SystemProperties systemProperties;
    public final WindowManager windowManager;
    public static final String[] LEGACY_HDR10_FEATURE_FLAGS = {"com.sony.dtv.hardware.hdr", "philips.hardware.hdr"};
    public static final String[] LEGACY_HDR10_SYSTEM_PROPERTIES = {"ro.nrdp.hdr10.revision", "ro.system.hdr10.revision", "sys.hwc.hdr.supported"};
    public static final long MIN_CHECK_INTERVAL_NS = TimeUnit.SECONDS.toNanos(5);

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class DisplayListener implements DisplayManager.DisplayListener {
        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayAdded(int i) {
            Log.i(DisplayPropertiesProvider.LOG_TAG, "Display added: " + i + " - refreshing display properties");
            DisplayPropertiesProvider.this.invalidateDisplayProperties();
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayChanged(int i) {
            Log.i(DisplayPropertiesProvider.LOG_TAG, "Display changed: " + i + " - refreshing display properties");
            DisplayPropertiesProvider.this.invalidateDisplayProperties();
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayRemoved(int i) {
            Log.i(DisplayPropertiesProvider.LOG_TAG, "Display removed: " + i + " - refreshing display properties");
            DisplayPropertiesProvider.this.invalidateDisplayProperties();
        }

        public DisplayListener() {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class DisplayProperties {
        public final boolean hasExternalOutput;
        public final boolean hdmiAudioPluggedIn;
        public final int maxVideoHeight;
        public final int maxVideoWidth;
        public final boolean supportsDolbyVision;
        public final boolean supportsHdcp;
        public final boolean supportsHdr10;
        public final boolean supportsHdr10Plus;

        public DisplayProperties(int i, int i2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
            this.maxVideoWidth = i;
            this.maxVideoHeight = i2;
            this.supportsHdcp = z;
            this.supportsHdr10 = z2;
            this.supportsHdr10Plus = z3;
            this.supportsDolbyVision = z4;
            this.hasExternalOutput = z5;
            this.hdmiAudioPluggedIn = z6;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                DisplayProperties displayProperties = (DisplayProperties) obj;
                if (this.maxVideoWidth == displayProperties.maxVideoWidth && this.maxVideoHeight == displayProperties.maxVideoHeight && this.supportsHdcp == displayProperties.supportsHdcp && this.supportsHdr10 == displayProperties.supportsHdr10 && this.supportsDolbyVision == displayProperties.supportsDolbyVision && this.hasExternalOutput == displayProperties.hasExternalOutput && this.hdmiAudioPluggedIn == displayProperties.hdmiAudioPluggedIn) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(this.maxVideoWidth), Integer.valueOf(this.maxVideoHeight), Boolean.valueOf(this.supportsHdcp), Boolean.valueOf(this.supportsHdr10), Boolean.valueOf(this.supportsDolbyVision), Boolean.valueOf(this.hdmiAudioPluggedIn));
        }

        @NonNull
        public String toString() {
            return "DisplayProperties{maxVideoWidth=" + this.maxVideoWidth + ", maxVideoHeight=" + this.maxVideoHeight + ", supportsHdcp=" + this.supportsHdcp + ", supportsHdr10=" + this.supportsHdr10 + ", supportsDolbyVision=" + this.supportsDolbyVision + ", hdmiAudioPluggedIn=" + this.hdmiAudioPluggedIn + '}';
        }
    }

    @Inject
    public DisplayPropertiesProvider(WindowManager windowManager, DisplayManager displayManager, PackageManager packageManager, SystemProperties systemProperties, MaxVideoResolutionProvider maxVideoResolutionProvider, @ApplicationContext Context context, final GMBMessageSender gMBMessageSender, Handler handler) {
        this.windowManager = windowManager;
        this.packageManager = packageManager;
        this.systemProperties = systemProperties;
        this.maxVideoResolutionProvider = maxVideoResolutionProvider;
        this.context = context;
        this.gmbMessageSender = gMBMessageSender;
        displayManager.registerDisplayListener(new DisplayListener(), handler);
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.amazon.livingroom.deviceproperties.DisplayPropertiesProvider.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if ("android.media.action.HDMI_AUDIO_PLUG".equals(intent.getAction())) {
                    Log.i(DisplayPropertiesProvider.LOG_TAG, "[HDMI_AUDIO_PLUG] Value changed");
                    boolean hdmiAudioPluggedIn = DisplayPropertiesProvider.this.getHdmiAudioPluggedIn();
                    DisplayPropertiesProvider.this.invalidateDisplayProperties();
                    gMBMessageSender.sendGMBMessageToClient("hdmi_audio_plug_changed", String.valueOf(hdmiAudioPluggedIn), 0L);
                }
            }
        };
        this.hdmiAudioPlugReceiver = broadcastReceiver;
        context.registerReceiver(broadcastReceiver, new IntentFilter("android.media.action.HDMI_AUDIO_PLUG"));
    }

    public synchronized DisplayProperties getCurrentDisplayProperties() {
        try {
            long jNanoTime = System.nanoTime();
            DisplayProperties displayProperties = this.displayProperties;
            if (displayProperties == null || this.lastCheckTimeNs + MIN_CHECK_INTERVAL_NS <= jNanoTime) {
                DisplayProperties currentDisplayPropertiesInternal = getCurrentDisplayPropertiesInternal();
                this.displayProperties = currentDisplayPropertiesInternal;
                this.lastCheckTimeNs = jNanoTime;
                if (!Objects.equals(currentDisplayPropertiesInternal, displayProperties)) {
                    Log.i(LOG_TAG, "Detected " + this.displayProperties);
                }
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.displayProperties;
    }

    public final DisplayProperties getCurrentDisplayPropertiesInternal() {
        boolean hdr10SupportLegacy;
        boolean z;
        boolean z2;
        Display defaultDisplay = this.windowManager.getDefaultDisplay();
        int flags = defaultDisplay.getFlags();
        boolean z3 = ((flags & 2) == 0 || (flags & 1) == 0) ? false : true;
        Point maxVideoResolution = this.maxVideoResolutionProvider.getMaxVideoResolution(defaultDisplay);
        if (Build.VERSION.SDK_INT >= 24) {
            hdr10SupportLegacy = false;
            boolean z4 = false;
            boolean z5 = false;
            for (int i : getHdrCapabilities(defaultDisplay)) {
                if (i == 1) {
                    z5 = true;
                } else if (i == 2) {
                    hdr10SupportLegacy = true;
                } else if (i == 4) {
                    z4 = true;
                }
            }
            z = z4;
            z2 = z5;
        } else {
            hdr10SupportLegacy = getHdr10SupportLegacy();
            z = false;
            z2 = false;
        }
        return new DisplayProperties(maxVideoResolution.x, maxVideoResolution.y, z3, hdr10SupportLegacy, z, z2, getHasExternalOutput(), getHdmiAudioPluggedIn());
    }

    public final boolean getHasExternalOutput() {
        try {
            return !this.systemProperties.get(PROPERTY_DEVICE_TYPE, "4").trim().equals("0");
        } catch (Exception unused) {
            Log.w(LOG_TAG, "Error parsing device type property 'ro.hdmi.device_type'");
            return true;
        }
    }

    public final boolean getHdmiAudioPluggedIn() {
        try {
            Intent intentRegisterReceiver = this.context.registerReceiver(null, new IntentFilter("android.media.action.HDMI_AUDIO_PLUG"));
            if (intentRegisterReceiver == null || intentRegisterReceiver.getExtras() == null) {
                Log.e(LOG_TAG, "[HDMI_AUDIO_PLUG] No sticky intent found");
            } else {
                Object obj = intentRegisterReceiver.getExtras().get("android.media.extra.AUDIO_PLUG_STATE");
                if (obj instanceof Integer) {
                    Log.i(LOG_TAG, "[HDMI_AUDIO_PLUG] State: " + obj);
                    return ((Integer) obj).intValue() == 1;
                }
                Log.e(LOG_TAG, "[HDMI_AUDIO_PLUG] Unexpected state type: ".concat(obj.getClass().getName()));
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, "[HDMI_AUDIO_PLUG] Error checking state", e);
        }
        return false;
    }

    public final boolean getHdr10SupportLegacy() {
        for (String str : LEGACY_HDR10_FEATURE_FLAGS) {
            if (this.packageManager.hasSystemFeature(str)) {
                return true;
            }
        }
        for (String str2 : LEGACY_HDR10_SYSTEM_PROPERTIES) {
            if ("1".equals(this.systemProperties.get(str2, ""))) {
                return true;
            }
        }
        return false;
    }

    @RequiresApi(api = 24)
    public final int[] getHdrCapabilities(Display display) {
        int[] iArr = new int[0];
        if (Build.VERSION.SDK_INT >= 34) {
            Display.Mode mode = display.getMode();
            if (mode != null) {
                return mode.getSupportedHdrTypes();
            }
        } else {
            Display.HdrCapabilities hdrCapabilities = display.getHdrCapabilities();
            if (hdrCapabilities != null) {
                return hdrCapabilities.getSupportedHdrTypes();
            }
        }
        return iArr;
    }

    public final synchronized void invalidateDisplayProperties() {
        this.displayProperties = null;
    }
}
