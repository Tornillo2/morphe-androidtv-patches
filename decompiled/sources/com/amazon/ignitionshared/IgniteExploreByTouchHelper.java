package com.amazon.ignitionshared;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.widget.ExploreByTouchHelper;
import com.amazon.ignitionshared.IgniteRenderer;
import com.amazon.livingroom.di.ActivityScope;
import com.amazon.livingroom.di.Names;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ActivityScope
public class IgniteExploreByTouchHelper extends ExploreByTouchHelper {
    public static final int BOTTOM = 3;
    public static final int LEFT = 4;
    public static final int RIGHT = 2;
    public static final int TOP = 1;
    public final IgniteRenderer.EventHandler eventHandler;
    public final List<Integer> internalVirtualViewIds;
    public int selectedVirtualViewId;
    public static final Rect MIDDLE_BOUNDS = createVirtualViewBounds(0, 0);
    public static final Rect TOP_BOUNDS = createVirtualViewBounds(0, -1);
    public static final Rect RIGHT_BOUNDS = createVirtualViewBounds(1, 0);
    public static final Rect BOTTOM_BOUNDS = createVirtualViewBounds(0, 1);
    public static final Rect LEFT_BOUNDS = createVirtualViewBounds(-1, 0);

    @Inject
    public IgniteExploreByTouchHelper(@Named(Names.IGNITE_SURFACE) View view, IgniteRenderer.EventHandler eventHandler) {
        super(view);
        this.internalVirtualViewIds = new ArrayList();
        this.eventHandler = eventHandler;
        generateInternalVirtualViewIds();
    }

    public static Rect createVirtualViewBounds(int i, int i2) {
        Rect rect = new Rect();
        rect.top = i2 + 1;
        rect.bottom = i2 + 2;
        rect.left = i + 1;
        rect.right = i + 2;
        return rect;
    }

    public final void generateInternalVirtualViewIds() {
        this.internalVirtualViewIds.clear();
        int i = this.selectedVirtualViewId;
        int i2 = i + 5;
        while (i < i2) {
            this.internalVirtualViewIds.add(Integer.valueOf(i));
            markVirtualViewIdInvalid(i);
            i++;
        }
    }

    @Override // androidx.customview.widget.ExploreByTouchHelper
    public int getVirtualViewAt(float f, float f2) {
        return 0;
    }

    @Override // androidx.customview.widget.ExploreByTouchHelper
    public void getVisibleVirtualViews(List<Integer> list) {
        if (list.size() == 5 && list.containsAll(this.internalVirtualViewIds)) {
            return;
        }
        list.clear();
        list.addAll(this.internalVirtualViewIds);
    }

    public void markVirtualViewIdInvalid(int i) {
        invalidateVirtualView(i, 1);
    }

    @Override // androidx.customview.widget.ExploreByTouchHelper
    public boolean onPerformActionForVirtualView(int i, int i2, @Nullable Bundle bundle) {
        return true;
    }

    @Override // androidx.customview.widget.ExploreByTouchHelper
    public void onPopulateNodeForVirtualView(int i, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        int i2 = i - this.selectedVirtualViewId;
        if (i2 == 1) {
            accessibilityNodeInfoCompat.setBoundsInParent(TOP_BOUNDS);
        } else if (i2 == 2) {
            accessibilityNodeInfoCompat.setBoundsInParent(RIGHT_BOUNDS);
        } else if (i2 == 3) {
            accessibilityNodeInfoCompat.setBoundsInParent(BOTTOM_BOUNDS);
        } else if (i2 != 4) {
            accessibilityNodeInfoCompat.setBoundsInParent(MIDDLE_BOUNDS);
        } else {
            accessibilityNodeInfoCompat.setBoundsInParent(LEFT_BOUNDS);
        }
        accessibilityNodeInfoCompat.setText("");
    }

    @Override // androidx.customview.widget.ExploreByTouchHelper
    public void onVirtualViewKeyboardFocusChanged(int i, boolean z) {
        if (z) {
            int i2 = i - this.selectedVirtualViewId;
            if (i2 == 1) {
                this.eventHandler.simulateKeyEvent(19);
            } else if (i2 == 2) {
                this.eventHandler.simulateKeyEvent(22);
            } else if (i2 == 3) {
                this.eventHandler.simulateKeyEvent(20);
            } else if (i2 == 4) {
                this.eventHandler.simulateKeyEvent(21);
            }
            setSelectedVirtualViewId(i);
        }
    }

    public final void setSelectedVirtualViewId(int i) {
        this.selectedVirtualViewId = i % 5;
        generateInternalVirtualViewIds();
    }
}
