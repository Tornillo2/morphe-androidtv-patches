package com.amazon.livingroom.deviceproperties;

import android.graphics.Point;
import android.os.Build;
import android.text.TextUtils;
import android.view.Display;
import androidx.annotation.RequiresApi;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.reporting.Log;
import j$.util.Objects;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Inject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public class MaxVideoResolutionProvider {
    public static final String[] DISPLAY_SIZE_SYSTEM_PROPERTY_NAMES = {"vendor.display-size", "sys.display-size"};
    public static final String LOG_TAG = "MaxVideoResolutionProvider";
    public static final Pattern RESOLUTION_FORMAT = Pattern.compile("\\D*(\\d+)\\D+(\\d+)\\D*");
    public String source;
    public final SystemProperties systemProperties;

    @Inject
    public MaxVideoResolutionProvider(SystemProperties systemProperties) {
        this.systemProperties = systemProperties;
    }

    @RequiresApi(23)
    public final Point getDisplayResolutionV23(Display display) {
        Display.Mode mode = display.getMode();
        Point point = new Point(mode.getPhysicalWidth(), mode.getPhysicalHeight());
        this.source = "current display mode";
        return point;
    }

    public final void getDisplaySizeFromSystemProperties(Point point) {
        String[] strArr = DISPLAY_SIZE_SYSTEM_PROPERTY_NAMES;
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            String str = strArr[i];
            String str2 = this.systemProperties.get(str, "");
            if (!TextUtils.isEmpty(str2)) {
                Matcher matcher = RESOLUTION_FORMAT.matcher(str2);
                if (matcher.matches()) {
                    try {
                        int i2 = Integer.parseInt(matcher.group(1));
                        int i3 = Integer.parseInt(matcher.group(2));
                        if (i2 <= point.x || i3 <= point.y) {
                            return;
                        }
                        point.x = i2;
                        point.y = i3;
                        this.source = "system property '" + str + "'='" + str2 + "'";
                        return;
                    } catch (NumberFormatException e) {
                        Log.w(LOG_TAG, String.format(Locale.US, "Failed to parse system property '%s'='%s'", str, str2), e);
                    }
                } else {
                    Log.w(LOG_TAG, String.format(Locale.US, "System property '%s' is present but it is not in the format WIDTHxHEIGHT: '%s'", str, str2));
                }
            }
        }
    }

    public final Point getDisplaySizeLegacy(Display display) {
        Point point = new Point();
        display.getRealSize(point);
        this.source = "default display real size - it may not be accurate";
        return point;
    }

    public Point getMaxVideoResolution(Display display) {
        String str = this.source;
        Point displayResolutionV23 = Build.VERSION.SDK_INT >= 23 ? getDisplayResolutionV23(display) : getDisplaySizeLegacy(display);
        getDisplaySizeFromSystemProperties(displayResolutionV23);
        if (!Objects.equals(this.source, str)) {
            Log.i(LOG_TAG, String.format(Locale.US, "Got max video resolution of %dx%d from %s", Integer.valueOf(displayResolutionV23.x), Integer.valueOf(displayResolutionV23.y), this.source));
        }
        return displayResolutionV23;
    }
}
