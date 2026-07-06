package com.amazon.ignitionshared;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.content.pm.PackageManager;
import android.view.accessibility.AccessibilityManager;
import com.amazon.minerva.identifiers.schemaid.SchemaId;
import com.amazon.reporting.Log;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class IgnitionAccessibilityServiceInfo {
    public final Context context;
    public final AccessibilityServiceInfo info;

    public IgnitionAccessibilityServiceInfo(Context context, AccessibilityServiceInfo accessibilityServiceInfo) {
        this.context = context;
        this.info = accessibilityServiceInfo;
    }

    public static IgnitionAccessibilityServiceInfo[] fromAccessibilityServiceInfoList(List<AccessibilityServiceInfo> list, Context context) {
        IgnitionAccessibilityServiceInfo[] ignitionAccessibilityServiceInfoArr = new IgnitionAccessibilityServiceInfo[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ignitionAccessibilityServiceInfoArr[i] = new IgnitionAccessibilityServiceInfo(context, list.get(i));
        }
        return ignitionAccessibilityServiceInfoArr;
    }

    public static IgnitionAccessibilityServiceInfo[] getAccessibilityServices(Context context) {
        return fromAccessibilityServiceInfoList(((AccessibilityManager) context.getSystemService("accessibility")).getInstalledAccessibilityServiceList(), context);
    }

    public static IgnitionAccessibilityServiceInfo[] getEnabledAccessibilityServices(Context context) {
        return fromAccessibilityServiceInfoList(((AccessibilityManager) context.getSystemService("accessibility")).getEnabledAccessibilityServiceList(-1), context);
    }

    public boolean canInterceptInput() {
        return (this.info.flags & 32) != 0;
    }

    public String getId() {
        return this.info.getId();
    }

    public String getLabel() {
        return this.info.getResolveInfo().loadLabel(this.context.getPackageManager()).toString();
    }

    public String getVersion() {
        try {
            return this.context.getPackageManager().getPackageInfo(getId().split(SchemaId.METRIC_SCHEMA_ID_DELIMITER)[0], 0).versionName;
        } catch (PackageManager.NameNotFoundException | IndexOutOfBoundsException e) {
            Log.e("Ignition", "Failed to get version of accessibility service '" + getLabel() + "': " + e.getMessage());
            return null;
        }
    }

    public boolean providesSpokenFeedback() {
        return (this.info.feedbackType & 1) != 0;
    }
}
