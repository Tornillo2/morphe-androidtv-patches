package com.amazon.livingroom.mediapipelinebackend;

import android.view.Display;
import androidx.annotation.RequiresApi;
import androidx.collection.MutableFloatList$$ExternalSyntheticOutline0;
import androidx.media3.exoplayer.audio.AudioSink$InitializationException$$ExternalSyntheticOutline0;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.inject.Inject;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nDisplayModeMatcher.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DisplayModeMatcher.kt\ncom/amazon/livingroom/mediapipelinebackend/DisplayModeMatcher\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 5 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,135:1\n3829#2:136\n4344#2,2:137\n1285#3,2:139\n1299#3,4:141\n1#4:145\n538#5:146\n523#5,6:147\n*S KotlinDebug\n*F\n+ 1 DisplayModeMatcher.kt\ncom/amazon/livingroom/mediapipelinebackend/DisplayModeMatcher\n*L\n22#1:136\n22#1:137,2\n24#1:139,2\n24#1:141,4\n63#1:146\n63#1:147,6\n*E\n"})
public final class DisplayModeMatcher {

    @NotNull
    public static final Companion Companion = new Companion();
    public static final int TIMING_ERROR_TOLERANCE = 50;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public final int round(float f) {
            return (int) (f * 10000);
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    @Inject
    public DisplayModeMatcher() {
    }

    @RequiresApi(api = 23)
    @NotNull
    public final Display.Mode findBestMode(@NotNull Display.Mode[] modes, float f, @NotNull Display.Mode activeMode, @NotNull Display.Mode defaultMode) {
        Object next;
        Object next2;
        Intrinsics.checkNotNullParameter(modes, "modes");
        Intrinsics.checkNotNullParameter(activeMode, "activeMode");
        Intrinsics.checkNotNullParameter(defaultMode, "defaultMode");
        int physicalWidth = activeMode.getPhysicalWidth();
        int physicalHeight = activeMode.getPhysicalHeight();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Display.Mode mode : modes) {
            if (mode.getPhysicalWidth() == physicalWidth && mode.getPhysicalHeight() == physicalHeight) {
                arrayList.add(mode);
            }
        }
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        int size = arrayList.size();
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            linkedHashMap.put(obj, Integer.valueOf(Companion.round(getNormalizedAvgTimingCorrection(DisplayModeMatcher$$ExternalSyntheticApiModelOutline0.m(obj), f))));
        }
        Iterator it = linkedHashMap.entrySet().iterator();
        if (it.hasNext()) {
            next = it.next();
            if (it.hasNext()) {
                int iIntValue = ((Number) ((Map.Entry) next).getValue()).intValue();
                do {
                    Object next3 = it.next();
                    int iIntValue2 = ((Number) ((Map.Entry) next3).getValue()).intValue();
                    if (iIntValue > iIntValue2) {
                        next = next3;
                        iIntValue = iIntValue2;
                    }
                } while (it.hasNext());
            }
        } else {
            next = null;
        }
        Map.Entry entry = (Map.Entry) next;
        if (entry == null) {
            StringBuilder sbM = MutableFloatList$$ExternalSyntheticOutline0.m("[FRM] Didn't find suitable display mode for ", physicalWidth, "x", physicalHeight, "@");
            sbM.append(f);
            MpbLog.i(sbM.toString());
            return defaultMode;
        }
        Display.Mode modeM = DisplayModeMatcher$$ExternalSyntheticApiModelOutline0.m(entry.getKey());
        int iIntValue3 = ((Number) entry.getValue()).intValue();
        Integer num = (Integer) linkedHashMap.get(activeMode);
        if (num != null && num.intValue() == iIntValue3) {
            int modeId = activeMode.getModeId();
            int physicalWidth2 = activeMode.getPhysicalWidth();
            int physicalHeight2 = activeMode.getPhysicalHeight();
            float refreshRate = activeMode.getRefreshRate();
            StringBuilder sbM2 = MutableFloatList$$ExternalSyntheticOutline0.m("[FRM] The current display mode id=", modeId, " (", physicalWidth2, "x");
            sbM2.append(physicalHeight2);
            sbM2.append("@");
            sbM2.append(refreshRate);
            sbM2.append(") is the best match for ");
            sbM2.append(f);
            sbM2.append(" FPS");
            MpbLog.i(sbM2.toString());
            return activeMode;
        }
        Integer num2 = (Integer) linkedHashMap.get(defaultMode);
        if (num2 != null && num2.intValue() == iIntValue3) {
            int modeId2 = defaultMode.getModeId();
            int physicalWidth3 = defaultMode.getPhysicalWidth();
            int physicalHeight3 = defaultMode.getPhysicalHeight();
            float refreshRate2 = defaultMode.getRefreshRate();
            StringBuilder sbM3 = MutableFloatList$$ExternalSyntheticOutline0.m("[FRM] The default display mode id=", modeId2, " (", physicalWidth3, "x");
            sbM3.append(physicalHeight3);
            sbM3.append("@");
            sbM3.append(refreshRate2);
            sbM3.append(") is the best match for ");
            sbM3.append(f);
            sbM3.append(" FPS");
            MpbLog.i(sbM3.toString());
            return defaultMode;
        }
        if (iIntValue3 >= 50) {
            int modeId3 = defaultMode.getModeId();
            int physicalWidth4 = defaultMode.getPhysicalWidth();
            int physicalHeight4 = defaultMode.getPhysicalHeight();
            float refreshRate3 = defaultMode.getRefreshRate();
            StringBuilder sbM4 = MutableFloatList$$ExternalSyntheticOutline0.m("[FRM] The best mode has timing error of ", iIntValue3, ", greater than 50, so switching to the default display mode id=", modeId3, " (");
            AudioSink$InitializationException$$ExternalSyntheticOutline0.m(sbM4, physicalWidth4, "x", physicalHeight4, "@");
            sbM4.append(refreshRate3);
            sbM4.append(")");
            MpbLog.i(sbM4.toString());
            return defaultMode;
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (Map.Entry entry2 : linkedHashMap.entrySet()) {
            if (((Number) entry2.getValue()).intValue() == iIntValue3) {
                linkedHashMap2.put(entry2.getKey(), entry2.getValue());
            }
        }
        Iterator it2 = linkedHashMap2.entrySet().iterator();
        if (it2.hasNext()) {
            next2 = it2.next();
            if (it2.hasNext()) {
                float refreshRate4 = DisplayModeMatcher$$ExternalSyntheticApiModelOutline0.m(((Map.Entry) next2).getKey()).getRefreshRate();
                do {
                    Object next4 = it2.next();
                    float refreshRate5 = DisplayModeMatcher$$ExternalSyntheticApiModelOutline0.m(((Map.Entry) next4).getKey()).getRefreshRate();
                    if (Float.compare(refreshRate4, refreshRate5) < 0) {
                        next2 = next4;
                        refreshRate4 = refreshRate5;
                    }
                } while (it2.hasNext());
            }
        } else {
            next2 = null;
        }
        Map.Entry entry3 = (Map.Entry) next2;
        Display.Mode modeM2 = entry3 != null ? DisplayModeMatcher$$ExternalSyntheticApiModelOutline0.m(entry3.getKey()) : null;
        if (modeM2 != null) {
            int modeId4 = modeM2.getModeId();
            int physicalWidth5 = modeM2.getPhysicalWidth();
            int physicalHeight5 = modeM2.getPhysicalHeight();
            float refreshRate6 = modeM2.getRefreshRate();
            StringBuilder sbM5 = MutableFloatList$$ExternalSyntheticOutline0.m("[FRM] Display mode id=", modeId4, " (", physicalWidth5, "x");
            sbM5.append(physicalHeight5);
            sbM5.append("@");
            sbM5.append(refreshRate6);
            sbM5.append(") is the best match for ");
            sbM5.append(f);
            sbM5.append(" FPS");
            MpbLog.i(sbM5.toString());
            return modeM2;
        }
        int modeId5 = modeM.getModeId();
        int physicalWidth6 = modeM.getPhysicalWidth();
        int physicalHeight6 = modeM.getPhysicalHeight();
        float refreshRate7 = modeM.getRefreshRate();
        StringBuilder sb = new StringBuilder("[FRM] Failed to find mode with minError=");
        sb.append(iIntValue3);
        sb.append(" for ");
        sb.append(f);
        sb.append(" FPS, but it should have been mode ");
        AudioSink$InitializationException$$ExternalSyntheticOutline0.m(sb, modeId5, " (", physicalWidth6, "x");
        sb.append(physicalHeight6);
        sb.append("@");
        sb.append(refreshRate7);
        sb.append(")");
        MpbLog.i(sb.toString());
        return modeM;
    }

    @RequiresApi(api = 23)
    public final float getNormalizedAvgTimingCorrection(Display.Mode mode, float f) {
        float refreshRate = mode.getRefreshRate();
        float f2 = refreshRate / f;
        return ((f2 - ((float) Math.floor(f2))) / refreshRate) * f;
    }
}
