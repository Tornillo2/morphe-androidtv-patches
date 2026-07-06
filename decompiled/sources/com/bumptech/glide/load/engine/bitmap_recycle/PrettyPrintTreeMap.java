package com.bumptech.glide.load.engine.bitmap_recycle;

import androidx.core.accessibilityservice.AccessibilityServiceInfoCompat$$ExternalSyntheticOutline0;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class PrettyPrintTreeMap<K, V> extends TreeMap<K, V> {
    @Override // java.util.AbstractMap
    public String toString() {
        StringBuilder sbM = AccessibilityServiceInfoCompat$$ExternalSyntheticOutline0.m("( ");
        for (Map.Entry<K, V> entry : entrySet()) {
            sbM.append('{');
            sbM.append(entry.getKey());
            sbM.append(':');
            sbM.append(entry.getValue());
            sbM.append("}, ");
        }
        if (!isEmpty()) {
            sbM.replace(sbM.length() - 2, sbM.length(), "");
        }
        sbM.append(" )");
        return sbM.toString();
    }
}
