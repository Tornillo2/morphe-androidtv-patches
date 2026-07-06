package androidx.core.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.ContentInfo;
import android.view.Display;
import android.view.KeyEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.autofill.AutofillId;
import android.view.contentcapture.ContentCaptureSession;
import androidx.annotation.FloatRange;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.ReplaceWith;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.UiThread;
import androidx.collection.SimpleArrayMap;
import androidx.core.R;
import androidx.core.util.Preconditions;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.WindowInsetsAnimationCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeProviderCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.core.view.autofill.AutofillIdCompat;
import androidx.core.view.contentcapture.ContentCaptureSessionCompat;
import com.google.common.net.MediaType;
import j$.util.Objects;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"PrivateConstructorForUtilityClass"})
public class ViewCompat {
    public static final int ACCESSIBILITY_LIVE_REGION_ASSERTIVE = 2;
    public static final int ACCESSIBILITY_LIVE_REGION_NONE = 0;
    public static final int ACCESSIBILITY_LIVE_REGION_POLITE = 1;

    @Deprecated
    public static final int IMPORTANT_FOR_ACCESSIBILITY_AUTO = 0;

    @Deprecated
    public static final int IMPORTANT_FOR_ACCESSIBILITY_NO = 2;

    @Deprecated
    public static final int IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS = 4;

    @Deprecated
    public static final int IMPORTANT_FOR_ACCESSIBILITY_YES = 1;
    public static final int IMPORTANT_FOR_CONTENT_CAPTURE_AUTO = 0;
    public static final int IMPORTANT_FOR_CONTENT_CAPTURE_NO = 2;
    public static final int IMPORTANT_FOR_CONTENT_CAPTURE_NO_EXCLUDE_DESCENDANTS = 8;
    public static final int IMPORTANT_FOR_CONTENT_CAPTURE_YES = 1;
    public static final int IMPORTANT_FOR_CONTENT_CAPTURE_YES_EXCLUDE_DESCENDANTS = 4;

    @Deprecated
    public static final int LAYER_TYPE_HARDWARE = 2;

    @Deprecated
    public static final int LAYER_TYPE_NONE = 0;

    @Deprecated
    public static final int LAYER_TYPE_SOFTWARE = 1;

    @Deprecated
    public static final int LAYOUT_DIRECTION_INHERIT = 2;

    @Deprecated
    public static final int LAYOUT_DIRECTION_LOCALE = 3;

    @Deprecated
    public static final int LAYOUT_DIRECTION_LTR = 0;

    @Deprecated
    public static final int LAYOUT_DIRECTION_RTL = 1;

    @Deprecated
    public static final int MEASURED_HEIGHT_STATE_SHIFT = 16;

    @Deprecated
    public static final int MEASURED_SIZE_MASK = 16777215;

    @Deprecated
    public static final int MEASURED_STATE_MASK = -16777216;

    @Deprecated
    public static final int MEASURED_STATE_TOO_SMALL = 16777216;

    @Deprecated
    public static final int OVER_SCROLL_ALWAYS = 0;

    @Deprecated
    public static final int OVER_SCROLL_IF_CONTENT_SCROLLS = 1;

    @Deprecated
    public static final int OVER_SCROLL_NEVER = 2;
    public static final int SCROLL_AXIS_HORIZONTAL = 1;
    public static final int SCROLL_AXIS_NONE = 0;
    public static final int SCROLL_AXIS_VERTICAL = 2;
    public static final int SCROLL_INDICATOR_BOTTOM = 2;
    public static final int SCROLL_INDICATOR_END = 32;
    public static final int SCROLL_INDICATOR_LEFT = 4;
    public static final int SCROLL_INDICATOR_RIGHT = 8;
    public static final int SCROLL_INDICATOR_START = 16;
    public static final int SCROLL_INDICATOR_TOP = 1;
    public static final String TAG = "ViewCompat";
    public static final int TYPE_NON_TOUCH = 1;
    public static final int TYPE_TOUCH = 0;
    public static boolean sAccessibilityDelegateCheckFailed = false;
    public static Field sAccessibilityDelegateField;
    public static Method sChildrenDrawingOrderMethod;
    public static Method sDispatchFinishTemporaryDetach;
    public static Method sDispatchStartTemporaryDetach;
    public static boolean sTempDetachBound;
    public static ThreadLocal<Rect> sThreadLocalRect;
    public static WeakHashMap<View, String> sTransitionNameMap;
    public static WeakHashMap<View, ViewPropertyAnimatorCompat> sViewPropertyAnimatorMap;
    public static final int[] ACCESSIBILITY_ACTIONS_RESOURCE_IDS = {R.id.accessibility_custom_action_0, R.id.accessibility_custom_action_1, R.id.accessibility_custom_action_2, R.id.accessibility_custom_action_3, R.id.accessibility_custom_action_4, R.id.accessibility_custom_action_5, R.id.accessibility_custom_action_6, R.id.accessibility_custom_action_7, R.id.accessibility_custom_action_8, R.id.accessibility_custom_action_9, R.id.accessibility_custom_action_10, R.id.accessibility_custom_action_11, R.id.accessibility_custom_action_12, R.id.accessibility_custom_action_13, R.id.accessibility_custom_action_14, R.id.accessibility_custom_action_15, R.id.accessibility_custom_action_16, R.id.accessibility_custom_action_17, R.id.accessibility_custom_action_18, R.id.accessibility_custom_action_19, R.id.accessibility_custom_action_20, R.id.accessibility_custom_action_21, R.id.accessibility_custom_action_22, R.id.accessibility_custom_action_23, R.id.accessibility_custom_action_24, R.id.accessibility_custom_action_25, R.id.accessibility_custom_action_26, R.id.accessibility_custom_action_27, R.id.accessibility_custom_action_28, R.id.accessibility_custom_action_29, R.id.accessibility_custom_action_30, R.id.accessibility_custom_action_31};
    public static final OnReceiveContentViewBehavior NO_OP_ON_RECEIVE_CONTENT_VIEW_BEHAVIOR = new ViewCompat$$ExternalSyntheticLambda0();
    public static final AccessibilityPaneVisibilityManager sAccessibilityPaneVisibilityManager = new AccessibilityPaneVisibilityManager();

    /* JADX INFO: renamed from: androidx.core.view.ViewCompat$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 extends AccessibilityViewProperty<Boolean> {
        public AnonymousClass1(int i, Class cls, int i2) {
            super(i, cls, 0, i2);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
        @RequiresApi(28)
        public Boolean frameworkGet(@NonNull View view) {
            return Boolean.valueOf(Api28Impl.isScreenReaderFocusable(view));
        }

        @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
        @RequiresApi(28)
        public void frameworkSet(@NonNull View view, Boolean bool) {
            Api28Impl.setScreenReaderFocusable(view, bool.booleanValue());
        }

        @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
        public boolean shouldUpdate(Boolean bool, Boolean bool2) {
            return !booleanNullToFalseEquals(bool, bool2);
        }
    }

    /* JADX INFO: renamed from: androidx.core.view.ViewCompat$2, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass2 extends AccessibilityViewProperty<CharSequence> {
        public AnonymousClass2(int i, Class cls, int i2, int i3) {
            super(i, cls, i2, i3);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
        @RequiresApi(28)
        public CharSequence frameworkGet(View view) {
            return Api28Impl.getAccessibilityPaneTitle(view);
        }

        @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
        @RequiresApi(28)
        public void frameworkSet(View view, CharSequence charSequence) {
            Api28Impl.setAccessibilityPaneTitle(view, charSequence);
        }

        @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
        public boolean shouldUpdate(CharSequence charSequence, CharSequence charSequence2) {
            return !TextUtils.equals(charSequence, charSequence2);
        }
    }

    /* JADX INFO: renamed from: androidx.core.view.ViewCompat$3, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass3 extends AccessibilityViewProperty<CharSequence> {
        public AnonymousClass3(int i, Class cls, int i2, int i3) {
            super(i, cls, i2, i3);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
        @RequiresApi(30)
        public CharSequence frameworkGet(View view) {
            return Api30Impl.getStateDescription(view);
        }

        @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
        @RequiresApi(30)
        public void frameworkSet(View view, CharSequence charSequence) {
            Api30Impl.setStateDescription(view, charSequence);
        }

        @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
        public boolean shouldUpdate(CharSequence charSequence, CharSequence charSequence2) {
            return !TextUtils.equals(charSequence, charSequence2);
        }
    }

    /* JADX INFO: renamed from: androidx.core.view.ViewCompat$4, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass4 extends AccessibilityViewProperty<Boolean> {
        public AnonymousClass4(int i, Class cls, int i2) {
            super(i, cls, 0, i2);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
        @RequiresApi(28)
        public Boolean frameworkGet(View view) {
            return Boolean.valueOf(Api28Impl.isAccessibilityHeading(view));
        }

        @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
        @RequiresApi(28)
        public void frameworkSet(View view, Boolean bool) {
            Api28Impl.setAccessibilityHeading(view, bool.booleanValue());
        }

        @Override // androidx.core.view.ViewCompat.AccessibilityViewProperty
        public boolean shouldUpdate(Boolean bool, Boolean bool2) {
            return !booleanNullToFalseEquals(bool, bool2);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class AccessibilityViewProperty<T> {
        public final int mContentChangeType;
        public final int mFrameworkMinimumSdk;
        public final int mTagKey;
        public final Class<T> mType;

        public AccessibilityViewProperty(int i, Class<T> cls, int i2) {
            this(i, cls, 0, i2);
        }

        public boolean booleanNullToFalseEquals(Boolean bool, Boolean bool2) {
            return (bool != null && bool.booleanValue()) == (bool2 != null && bool2.booleanValue());
        }

        public final boolean frameworkAvailable() {
            return Build.VERSION.SDK_INT >= this.mFrameworkMinimumSdk;
        }

        public abstract T frameworkGet(View view);

        public abstract void frameworkSet(View view, T t);

        public T get(View view) {
            if (frameworkAvailable()) {
                return frameworkGet(view);
            }
            T t = (T) view.getTag(this.mTagKey);
            if (this.mType.isInstance(t)) {
                return t;
            }
            return null;
        }

        public void set(View view, T t) {
            if (frameworkAvailable()) {
                frameworkSet(view, t);
            } else if (shouldUpdate(get(view), t)) {
                ViewCompat.ensureAccessibilityDelegateCompat(view);
                view.setTag(this.mTagKey, t);
                ViewCompat.notifyViewAccessibilityStateChangedIfNeeded(view, this.mContentChangeType);
            }
        }

        public boolean shouldUpdate(T t, T t2) {
            return !t2.equals(t);
        }

        public AccessibilityViewProperty(int i, Class<T> cls, int i2, int i3) {
            this.mTagKey = i;
            this.mType = cls;
            this.mContentChangeType = i2;
            this.mFrameworkMinimumSdk = i3;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(20)
    public static class Api20Impl {
        private Api20Impl() {
        }

        public static WindowInsets dispatchApplyWindowInsets(View view, WindowInsets windowInsets) {
            return view.dispatchApplyWindowInsets(windowInsets);
        }

        public static WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
            return view.onApplyWindowInsets(windowInsets);
        }

        public static void requestApplyInsets(View view) {
            view.requestApplyInsets();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(21)
    public static class Api21Impl {
        private Api21Impl() {
        }

        public static void callCompatInsetAnimationCallback(@NonNull WindowInsets windowInsets, @NonNull View view) {
            View.OnApplyWindowInsetsListener onApplyWindowInsetsListener = (View.OnApplyWindowInsetsListener) view.getTag(R.id.tag_window_insets_animation_callback);
            if (onApplyWindowInsetsListener != null) {
                onApplyWindowInsetsListener.onApplyWindowInsets(view, windowInsets);
            }
        }

        public static WindowInsetsCompat computeSystemWindowInsets(@NonNull View view, @NonNull WindowInsetsCompat windowInsetsCompat, @NonNull Rect rect) {
            WindowInsets windowInsets = windowInsetsCompat.toWindowInsets();
            if (windowInsets != null) {
                return WindowInsetsCompat.toWindowInsetsCompat(view.computeSystemWindowInsets(windowInsets, rect), view);
            }
            rect.setEmpty();
            return windowInsetsCompat;
        }

        public static boolean dispatchNestedFling(@NonNull View view, float f, float f2, boolean z) {
            return view.dispatchNestedFling(f, f2, z);
        }

        public static boolean dispatchNestedPreFling(@NonNull View view, float f, float f2) {
            return view.dispatchNestedPreFling(f, f2);
        }

        public static boolean dispatchNestedPreScroll(View view, int i, int i2, int[] iArr, int[] iArr2) {
            return view.dispatchNestedPreScroll(i, i2, iArr, iArr2);
        }

        public static boolean dispatchNestedScroll(View view, int i, int i2, int i3, int i4, int[] iArr) {
            return view.dispatchNestedScroll(i, i2, i3, i4, iArr);
        }

        public static ColorStateList getBackgroundTintList(View view) {
            return view.getBackgroundTintList();
        }

        public static PorterDuff.Mode getBackgroundTintMode(View view) {
            return view.getBackgroundTintMode();
        }

        public static float getElevation(View view) {
            return view.getElevation();
        }

        @Nullable
        public static WindowInsetsCompat getRootWindowInsets(@NonNull View view) {
            return WindowInsetsCompat.Api21ReflectionHolder.getRootWindowInsets(view);
        }

        public static String getTransitionName(View view) {
            return view.getTransitionName();
        }

        public static float getTranslationZ(View view) {
            return view.getTranslationZ();
        }

        public static float getZ(@NonNull View view) {
            return view.getZ();
        }

        public static boolean hasNestedScrollingParent(View view) {
            return view.hasNestedScrollingParent();
        }

        public static boolean isImportantForAccessibility(View view) {
            return view.isImportantForAccessibility();
        }

        public static boolean isNestedScrollingEnabled(View view) {
            return view.isNestedScrollingEnabled();
        }

        public static void setBackgroundTintList(View view, ColorStateList colorStateList) {
            view.setBackgroundTintList(colorStateList);
        }

        public static void setBackgroundTintMode(View view, PorterDuff.Mode mode) {
            view.setBackgroundTintMode(mode);
        }

        public static void setElevation(View view, float f) {
            view.setElevation(f);
        }

        public static void setNestedScrollingEnabled(View view, boolean z) {
            view.setNestedScrollingEnabled(z);
        }

        public static void setOnApplyWindowInsetsListener(@NonNull final View view, @Nullable final OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
            if (Build.VERSION.SDK_INT < 30) {
                view.setTag(R.id.tag_on_apply_window_listener, onApplyWindowInsetsListener);
            }
            if (onApplyWindowInsetsListener == null) {
                view.setOnApplyWindowInsetsListener((View.OnApplyWindowInsetsListener) view.getTag(R.id.tag_window_insets_animation_callback));
            } else {
                view.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: androidx.core.view.ViewCompat.Api21Impl.1
                    public WindowInsetsCompat mLastInsets = null;

                    @Override // android.view.View.OnApplyWindowInsetsListener
                    public WindowInsets onApplyWindowInsets(View view2, WindowInsets windowInsets) {
                        WindowInsetsCompat windowInsetsCompat = WindowInsetsCompat.toWindowInsetsCompat(windowInsets, view2);
                        int i = Build.VERSION.SDK_INT;
                        if (i < 30) {
                            Api21Impl.callCompatInsetAnimationCallback(windowInsets, view);
                            if (windowInsetsCompat.equals(this.mLastInsets)) {
                                return onApplyWindowInsetsListener.onApplyWindowInsets(view2, windowInsetsCompat).toWindowInsets();
                            }
                        }
                        this.mLastInsets = windowInsetsCompat;
                        WindowInsetsCompat windowInsetsCompatOnApplyWindowInsets = onApplyWindowInsetsListener.onApplyWindowInsets(view2, windowInsetsCompat);
                        if (i >= 30) {
                            return windowInsetsCompatOnApplyWindowInsets.toWindowInsets();
                        }
                        ViewCompat.requestApplyInsets(view2);
                        return windowInsetsCompatOnApplyWindowInsets.toWindowInsets();
                    }
                });
            }
        }

        public static void setTransitionName(View view, String str) {
            view.setTransitionName(str);
        }

        public static void setTranslationZ(View view, float f) {
            view.setTranslationZ(f);
        }

        public static void setZ(@NonNull View view, float f) {
            view.setZ(f);
        }

        public static boolean startNestedScroll(View view, int i) {
            return view.startNestedScroll(i);
        }

        public static void stopNestedScroll(View view) {
            view.stopNestedScroll();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(23)
    public static class Api23Impl {
        private Api23Impl() {
        }

        @Nullable
        public static WindowInsetsCompat getRootWindowInsets(@NonNull View view) {
            WindowInsets rootWindowInsets = view.getRootWindowInsets();
            if (rootWindowInsets == null) {
                return null;
            }
            WindowInsetsCompat windowInsetsCompat = WindowInsetsCompat.toWindowInsetsCompat(rootWindowInsets);
            windowInsetsCompat.setRootWindowInsets(windowInsetsCompat);
            windowInsetsCompat.copyRootViewBounds(view.getRootView());
            return windowInsetsCompat;
        }

        public static int getScrollIndicators(@NonNull View view) {
            return view.getScrollIndicators();
        }

        public static void setScrollIndicators(@NonNull View view, int i) {
            view.setScrollIndicators(i);
        }

        public static void setScrollIndicators(@NonNull View view, int i, int i2) {
            view.setScrollIndicators(i, i2);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(24)
    public static class Api24Impl {
        private Api24Impl() {
        }

        public static void cancelDragAndDrop(@NonNull View view) {
            view.cancelDragAndDrop();
        }

        public static void dispatchFinishTemporaryDetach(View view) {
            view.dispatchFinishTemporaryDetach();
        }

        public static void dispatchStartTemporaryDetach(View view) {
            view.dispatchStartTemporaryDetach();
        }

        public static void setPointerIcon(@NonNull View view, PointerIcon pointerIcon) {
            view.setPointerIcon(pointerIcon);
        }

        public static boolean startDragAndDrop(@NonNull View view, @Nullable ClipData clipData, @NonNull View.DragShadowBuilder dragShadowBuilder, @Nullable Object obj, int i) {
            return view.startDragAndDrop(clipData, dragShadowBuilder, obj, i);
        }

        public static void updateDragShadow(@NonNull View view, @NonNull View.DragShadowBuilder dragShadowBuilder) {
            view.updateDragShadow(dragShadowBuilder);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(26)
    public static class Api26Impl {
        private Api26Impl() {
        }

        public static void addKeyboardNavigationClusters(@NonNull View view, Collection<View> collection, int i) {
            view.addKeyboardNavigationClusters(collection, i);
        }

        public static AutofillId getAutofillId(View view) {
            return view.getAutofillId();
        }

        public static int getImportantForAutofill(View view) {
            return view.getImportantForAutofill();
        }

        public static int getNextClusterForwardId(@NonNull View view) {
            return view.getNextClusterForwardId();
        }

        public static boolean hasExplicitFocusable(@NonNull View view) {
            return view.hasExplicitFocusable();
        }

        public static boolean isFocusedByDefault(@NonNull View view) {
            return view.isFocusedByDefault();
        }

        public static boolean isImportantForAutofill(View view) {
            return view.isImportantForAutofill();
        }

        public static boolean isKeyboardNavigationCluster(@NonNull View view) {
            return view.isKeyboardNavigationCluster();
        }

        public static View keyboardNavigationClusterSearch(@NonNull View view, View view2, int i) {
            return view.keyboardNavigationClusterSearch(view2, i);
        }

        public static boolean restoreDefaultFocus(@NonNull View view) {
            return view.restoreDefaultFocus();
        }

        public static void setAutofillHints(@NonNull View view, String... strArr) {
            view.setAutofillHints(strArr);
        }

        public static void setFocusedByDefault(@NonNull View view, boolean z) {
            view.setFocusedByDefault(z);
        }

        public static void setImportantForAutofill(View view, int i) {
            view.setImportantForAutofill(i);
        }

        public static void setKeyboardNavigationCluster(@NonNull View view, boolean z) {
            view.setKeyboardNavigationCluster(z);
        }

        public static void setNextClusterForwardId(View view, int i) {
            view.setNextClusterForwardId(i);
        }

        public static void setTooltipText(@NonNull View view, CharSequence charSequence) {
            view.setTooltipText(charSequence);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(28)
    public static class Api28Impl {
        private Api28Impl() {
        }

        public static void addOnUnhandledKeyEventListener(@NonNull View view, @NonNull final OnUnhandledKeyEventListenerCompat onUnhandledKeyEventListenerCompat) {
            SimpleArrayMap simpleArrayMap = (SimpleArrayMap) view.getTag(R.id.tag_unhandled_key_listeners);
            if (simpleArrayMap == null) {
                simpleArrayMap = new SimpleArrayMap();
                view.setTag(R.id.tag_unhandled_key_listeners, simpleArrayMap);
            }
            Objects.requireNonNull(onUnhandledKeyEventListenerCompat);
            View.OnUnhandledKeyEventListener onUnhandledKeyEventListener = new View.OnUnhandledKeyEventListener() { // from class: androidx.core.view.ViewCompat$Api28Impl$$ExternalSyntheticLambda0
                @Override // android.view.View.OnUnhandledKeyEventListener
                public final boolean onUnhandledKeyEvent(View view2, KeyEvent keyEvent) {
                    return onUnhandledKeyEventListenerCompat.onUnhandledKeyEvent(view2, keyEvent);
                }
            };
            simpleArrayMap.put(onUnhandledKeyEventListenerCompat, onUnhandledKeyEventListener);
            view.addOnUnhandledKeyEventListener(onUnhandledKeyEventListener);
        }

        public static CharSequence getAccessibilityPaneTitle(View view) {
            return view.getAccessibilityPaneTitle();
        }

        public static boolean isAccessibilityHeading(View view) {
            return view.isAccessibilityHeading();
        }

        public static boolean isScreenReaderFocusable(View view) {
            return view.isScreenReaderFocusable();
        }

        public static void removeOnUnhandledKeyEventListener(@NonNull View view, @NonNull OnUnhandledKeyEventListenerCompat onUnhandledKeyEventListenerCompat) {
            View.OnUnhandledKeyEventListener onUnhandledKeyEventListener;
            SimpleArrayMap simpleArrayMap = (SimpleArrayMap) view.getTag(R.id.tag_unhandled_key_listeners);
            if (simpleArrayMap == null || (onUnhandledKeyEventListener = (View.OnUnhandledKeyEventListener) simpleArrayMap.get(onUnhandledKeyEventListenerCompat)) == null) {
                return;
            }
            view.removeOnUnhandledKeyEventListener(onUnhandledKeyEventListener);
        }

        public static <T> T requireViewById(View view, int i) {
            return (T) view.requireViewById(i);
        }

        public static void setAccessibilityHeading(View view, boolean z) {
            view.setAccessibilityHeading(z);
        }

        public static void setAccessibilityPaneTitle(View view, CharSequence charSequence) {
            view.setAccessibilityPaneTitle(charSequence);
        }

        public static void setAutofillId(View view, AutofillIdCompat autofillIdCompat) {
            view.setAutofillId(autofillIdCompat == null ? null : autofillIdCompat.toAutofillId());
        }

        public static void setScreenReaderFocusable(View view, boolean z) {
            view.setScreenReaderFocusable(z);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(29)
    public static class Api29Impl {
        private Api29Impl() {
        }

        public static View.AccessibilityDelegate getAccessibilityDelegate(View view) {
            return view.getAccessibilityDelegate();
        }

        public static ContentCaptureSession getContentCaptureSession(View view) {
            return view.getContentCaptureSession();
        }

        public static List<Rect> getSystemGestureExclusionRects(View view) {
            return view.getSystemGestureExclusionRects();
        }

        public static void saveAttributeDataForStyleable(@NonNull View view, @NonNull Context context, @NonNull int[] iArr, @Nullable AttributeSet attributeSet, @NonNull TypedArray typedArray, int i, int i2) {
            view.saveAttributeDataForStyleable(context, iArr, attributeSet, typedArray, i, i2);
        }

        public static void setContentCaptureSession(View view, ContentCaptureSessionCompat contentCaptureSessionCompat) {
            view.setContentCaptureSession(contentCaptureSessionCompat == null ? null : contentCaptureSessionCompat.toContentCaptureSession());
        }

        public static void setSystemGestureExclusionRects(View view, List<Rect> list) {
            view.setSystemGestureExclusionRects(list);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(30)
    public static class Api30Impl {
        private Api30Impl() {
        }

        public static int getImportantForContentCapture(View view) {
            return view.getImportantForContentCapture();
        }

        public static CharSequence getStateDescription(View view) {
            return view.getStateDescription();
        }

        @Nullable
        public static WindowInsetsControllerCompat getWindowInsetsController(@NonNull View view) {
            WindowInsetsController windowInsetsController = view.getWindowInsetsController();
            if (windowInsetsController != null) {
                return new WindowInsetsControllerCompat(windowInsetsController);
            }
            return null;
        }

        public static boolean isImportantForContentCapture(View view) {
            return view.isImportantForContentCapture();
        }

        public static void setImportantForContentCapture(View view, int i) {
            view.setImportantForContentCapture(i);
        }

        public static void setStateDescription(View view, CharSequence charSequence) {
            view.setStateDescription(charSequence);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(31)
    public static final class Api31Impl {
        private Api31Impl() {
        }

        @Nullable
        public static String[] getReceiveContentMimeTypes(@NonNull View view) {
            return view.getReceiveContentMimeTypes();
        }

        @Nullable
        public static ContentInfoCompat performReceiveContent(@NonNull View view, @NonNull ContentInfoCompat contentInfoCompat) {
            ContentInfo contentInfo = contentInfoCompat.toContentInfo();
            ContentInfo contentInfoPerformReceiveContent = view.performReceiveContent(contentInfo);
            if (contentInfoPerformReceiveContent == null) {
                return null;
            }
            return contentInfoPerformReceiveContent == contentInfo ? contentInfoCompat : ContentInfoCompat.toContentInfoCompat(contentInfoPerformReceiveContent);
        }

        public static void setOnReceiveContentListener(@NonNull View view, @Nullable String[] strArr, @Nullable OnReceiveContentListener onReceiveContentListener) {
            if (onReceiveContentListener == null) {
                view.setOnReceiveContentListener(strArr, null);
            } else {
                view.setOnReceiveContentListener(strArr, new OnReceiveContentListenerAdapter(onReceiveContentListener));
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public @interface FocusDirection {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public @interface FocusRealDirection {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public @interface FocusRelativeDirection {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public @interface NestedScrollType {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(31)
    public static final class OnReceiveContentListenerAdapter implements android.view.OnReceiveContentListener {

        @NonNull
        public final OnReceiveContentListener mJetpackListener;

        public OnReceiveContentListenerAdapter(@NonNull OnReceiveContentListener onReceiveContentListener) {
            this.mJetpackListener = onReceiveContentListener;
        }

        @Override // android.view.OnReceiveContentListener
        @Nullable
        public ContentInfo onReceiveContent(@NonNull View view, @NonNull ContentInfo contentInfo) {
            ContentInfoCompat contentInfoCompat = ContentInfoCompat.toContentInfoCompat(contentInfo);
            ContentInfoCompat contentInfoCompatOnReceiveContent = this.mJetpackListener.onReceiveContent(view, contentInfoCompat);
            if (contentInfoCompatOnReceiveContent == null) {
                return null;
            }
            return contentInfoCompatOnReceiveContent == contentInfoCompat ? contentInfo : contentInfoCompatOnReceiveContent.toContentInfo();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface OnUnhandledKeyEventListenerCompat {
        boolean onUnhandledKeyEvent(@NonNull View view, @NonNull KeyEvent keyEvent);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public @interface ScrollAxis {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public @interface ScrollIndicators {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class UnhandledKeyEventManager {
        public static final ArrayList<WeakReference<View>> sViewsWithListeners = new ArrayList<>();

        @Nullable
        public WeakHashMap<View, Boolean> mViewsContainingListeners = null;
        public SparseArray<WeakReference<View>> mCapturedKeys = null;
        public WeakReference<KeyEvent> mLastDispatchedPreViewKeyEvent = null;

        public static UnhandledKeyEventManager at(View view) {
            UnhandledKeyEventManager unhandledKeyEventManager = (UnhandledKeyEventManager) view.getTag(R.id.tag_unhandled_key_event_manager);
            if (unhandledKeyEventManager != null) {
                return unhandledKeyEventManager;
            }
            UnhandledKeyEventManager unhandledKeyEventManager2 = new UnhandledKeyEventManager();
            view.setTag(R.id.tag_unhandled_key_event_manager, unhandledKeyEventManager2);
            return unhandledKeyEventManager2;
        }

        public static void registerListeningView(View view) {
            ArrayList<WeakReference<View>> arrayList = sViewsWithListeners;
            synchronized (arrayList) {
                try {
                    int size = arrayList.size();
                    int i = 0;
                    while (i < size) {
                        WeakReference<View> weakReference = arrayList.get(i);
                        i++;
                        if (weakReference.get() == view) {
                            return;
                        }
                    }
                    sViewsWithListeners.add(new WeakReference<>(view));
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public static void unregisterListeningView(View view) {
            synchronized (sViewsWithListeners) {
                int i = 0;
                while (true) {
                    try {
                        ArrayList<WeakReference<View>> arrayList = sViewsWithListeners;
                        if (i >= arrayList.size()) {
                            return;
                        }
                        if (arrayList.get(i).get() == view) {
                            arrayList.remove(i);
                            return;
                        }
                        i++;
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        }

        public boolean dispatch(View view, KeyEvent keyEvent) {
            if (keyEvent.getAction() == 0) {
                recalcViewsWithUnhandled();
            }
            View viewDispatchInOrder = dispatchInOrder(view, keyEvent);
            if (keyEvent.getAction() == 0) {
                int keyCode = keyEvent.getKeyCode();
                if (viewDispatchInOrder != null && !KeyEvent.isModifierKey(keyCode)) {
                    getCapturedKeys().put(keyCode, new WeakReference<>(viewDispatchInOrder));
                }
            }
            return viewDispatchInOrder != null;
        }

        @Nullable
        public final View dispatchInOrder(View view, KeyEvent keyEvent) {
            WeakHashMap<View, Boolean> weakHashMap = this.mViewsContainingListeners;
            if (weakHashMap != null && weakHashMap.containsKey(view)) {
                if (view instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view;
                    for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                        View viewDispatchInOrder = dispatchInOrder(viewGroup.getChildAt(childCount), keyEvent);
                        if (viewDispatchInOrder != null) {
                            return viewDispatchInOrder;
                        }
                    }
                }
                if (onUnhandledKeyEvent(view, keyEvent)) {
                    return view;
                }
            }
            return null;
        }

        public final SparseArray<WeakReference<View>> getCapturedKeys() {
            if (this.mCapturedKeys == null) {
                this.mCapturedKeys = new SparseArray<>();
            }
            return this.mCapturedKeys;
        }

        public final boolean onUnhandledKeyEvent(@NonNull View view, @NonNull KeyEvent keyEvent) {
            ArrayList arrayList = (ArrayList) view.getTag(R.id.tag_unhandled_key_listeners);
            if (arrayList == null) {
                return false;
            }
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                if (((OnUnhandledKeyEventListenerCompat) arrayList.get(size)).onUnhandledKeyEvent(view, keyEvent)) {
                    return true;
                }
            }
            return false;
        }

        public boolean preDispatch(KeyEvent keyEvent) {
            WeakReference<View> weakReferenceValueAt;
            int iIndexOfKey;
            WeakReference<KeyEvent> weakReference = this.mLastDispatchedPreViewKeyEvent;
            if (weakReference != null && weakReference.get() == keyEvent) {
                return false;
            }
            this.mLastDispatchedPreViewKeyEvent = new WeakReference<>(keyEvent);
            SparseArray<WeakReference<View>> capturedKeys = getCapturedKeys();
            if (keyEvent.getAction() != 1 || (iIndexOfKey = capturedKeys.indexOfKey(keyEvent.getKeyCode())) < 0) {
                weakReferenceValueAt = null;
            } else {
                weakReferenceValueAt = capturedKeys.valueAt(iIndexOfKey);
                capturedKeys.removeAt(iIndexOfKey);
            }
            if (weakReferenceValueAt == null) {
                weakReferenceValueAt = capturedKeys.get(keyEvent.getKeyCode());
            }
            if (weakReferenceValueAt == null) {
                return false;
            }
            View view = weakReferenceValueAt.get();
            if (view != null && view.isAttachedToWindow()) {
                onUnhandledKeyEvent(view, keyEvent);
            }
            return true;
        }

        public final void recalcViewsWithUnhandled() {
            WeakHashMap<View, Boolean> weakHashMap = this.mViewsContainingListeners;
            if (weakHashMap != null) {
                weakHashMap.clear();
            }
            ArrayList<WeakReference<View>> arrayList = sViewsWithListeners;
            if (arrayList.isEmpty()) {
                return;
            }
            synchronized (arrayList) {
                try {
                    if (this.mViewsContainingListeners == null) {
                        this.mViewsContainingListeners = new WeakHashMap<>();
                    }
                    for (int size = arrayList.size() - 1; size >= 0; size--) {
                        ArrayList<WeakReference<View>> arrayList2 = sViewsWithListeners;
                        View view = arrayList2.get(size).get();
                        if (view == null) {
                            arrayList2.remove(size);
                        } else {
                            this.mViewsContainingListeners.put(view, Boolean.TRUE);
                            for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
                                this.mViewsContainingListeners.put((View) parent, Boolean.TRUE);
                            }
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    @Deprecated
    public ViewCompat() {
    }

    public static AccessibilityViewProperty<Boolean> accessibilityHeadingProperty() {
        return new AnonymousClass4(R.id.tag_accessibility_heading, Boolean.class, 0, 28);
    }

    public static int addAccessibilityAction(@NonNull View view, @NonNull CharSequence charSequence, @NonNull AccessibilityViewCommand accessibilityViewCommand) {
        int availableActionIdFromResources = getAvailableActionIdFromResources(view, charSequence);
        if (availableActionIdFromResources != -1) {
            addAccessibilityAction(view, new AccessibilityNodeInfoCompat.AccessibilityActionCompat(availableActionIdFromResources, charSequence, accessibilityViewCommand));
        }
        return availableActionIdFromResources;
    }

    public static void addKeyboardNavigationClusters(@NonNull View view, @NonNull Collection<View> collection, int i) {
        if (Build.VERSION.SDK_INT >= 26) {
            Api26Impl.addKeyboardNavigationClusters(view, collection, i);
        }
    }

    public static void addOnUnhandledKeyEventListener(@NonNull View view, @NonNull OnUnhandledKeyEventListenerCompat onUnhandledKeyEventListenerCompat) {
        if (Build.VERSION.SDK_INT >= 28) {
            Api28Impl.addOnUnhandledKeyEventListener(view, onUnhandledKeyEventListenerCompat);
            return;
        }
        ArrayList arrayList = (ArrayList) view.getTag(R.id.tag_unhandled_key_listeners);
        if (arrayList == null) {
            arrayList = new ArrayList();
            view.setTag(R.id.tag_unhandled_key_listeners, arrayList);
        }
        arrayList.add(onUnhandledKeyEventListenerCompat);
        if (arrayList.size() == 1) {
            UnhandledKeyEventManager.registerListeningView(view);
        }
    }

    @NonNull
    @Deprecated
    public static ViewPropertyAnimatorCompat animate(@NonNull View view) {
        if (sViewPropertyAnimatorMap == null) {
            sViewPropertyAnimatorMap = new WeakHashMap<>();
        }
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = sViewPropertyAnimatorMap.get(view);
        if (viewPropertyAnimatorCompat != null) {
            return viewPropertyAnimatorCompat;
        }
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat2 = new ViewPropertyAnimatorCompat(view);
        sViewPropertyAnimatorMap.put(view, viewPropertyAnimatorCompat2);
        return viewPropertyAnimatorCompat2;
    }

    public static void bindTempDetach() {
        try {
            sDispatchStartTemporaryDetach = View.class.getDeclaredMethod("dispatchStartTemporaryDetach", null);
            sDispatchFinishTemporaryDetach = View.class.getDeclaredMethod("dispatchFinishTemporaryDetach", null);
        } catch (NoSuchMethodException e) {
            Log.e(TAG, "Couldn't find method", e);
        }
        sTempDetachBound = true;
    }

    @ReplaceWith(expression = "view.canScrollHorizontally(direction)")
    @Deprecated
    public static boolean canScrollHorizontally(View view, int i) {
        return view.canScrollHorizontally(i);
    }

    @ReplaceWith(expression = "view.canScrollVertically(direction)")
    @Deprecated
    public static boolean canScrollVertically(View view, int i) {
        return view.canScrollVertically(i);
    }

    public static void cancelDragAndDrop(@NonNull View view) {
        if (Build.VERSION.SDK_INT >= 24) {
            Api24Impl.cancelDragAndDrop(view);
        }
    }

    @Deprecated
    public static int combineMeasuredStates(int i, int i2) {
        return View.combineMeasuredStates(i, i2);
    }

    public static void compatOffsetLeftAndRight(View view, int i) {
        view.offsetLeftAndRight(i);
        if (view.getVisibility() == 0) {
            tickleInvalidationFlag(view);
            Object parent = view.getParent();
            if (parent instanceof View) {
                tickleInvalidationFlag((View) parent);
            }
        }
    }

    public static void compatOffsetTopAndBottom(View view, int i) {
        view.offsetTopAndBottom(i);
        if (view.getVisibility() == 0) {
            tickleInvalidationFlag(view);
            Object parent = view.getParent();
            if (parent instanceof View) {
                tickleInvalidationFlag((View) parent);
            }
        }
    }

    @NonNull
    public static WindowInsetsCompat computeSystemWindowInsets(@NonNull View view, @NonNull WindowInsetsCompat windowInsetsCompat, @NonNull Rect rect) {
        return Api21Impl.computeSystemWindowInsets(view, windowInsetsCompat, rect);
    }

    @NonNull
    public static WindowInsetsCompat dispatchApplyWindowInsets(@NonNull View view, @NonNull WindowInsetsCompat windowInsetsCompat) {
        WindowInsets windowInsets = windowInsetsCompat.toWindowInsets();
        if (windowInsets != null) {
            WindowInsets windowInsetsDispatchApplyWindowInsets = Api20Impl.dispatchApplyWindowInsets(view, windowInsets);
            if (!windowInsetsDispatchApplyWindowInsets.equals(windowInsets)) {
                return WindowInsetsCompat.toWindowInsetsCompat(windowInsetsDispatchApplyWindowInsets, view);
            }
        }
        return windowInsetsCompat;
    }

    public static void dispatchFinishTemporaryDetach(@NonNull View view) {
        if (Build.VERSION.SDK_INT >= 24) {
            Api24Impl.dispatchFinishTemporaryDetach(view);
            return;
        }
        if (!sTempDetachBound) {
            bindTempDetach();
        }
        Method method = sDispatchFinishTemporaryDetach;
        if (method == null) {
            view.onFinishTemporaryDetach();
            return;
        }
        try {
            method.invoke(view, null);
        } catch (Exception e) {
            Log.d(TAG, "Error calling dispatchFinishTemporaryDetach", e);
        }
    }

    public static boolean dispatchNestedFling(@NonNull View view, float f, float f2, boolean z) {
        return Api21Impl.dispatchNestedFling(view, f, f2, z);
    }

    public static boolean dispatchNestedPreFling(@NonNull View view, float f, float f2) {
        return Api21Impl.dispatchNestedPreFling(view, f, f2);
    }

    public static boolean dispatchNestedPreScroll(@NonNull View view, int i, int i2, @Nullable int[] iArr, @Nullable int[] iArr2) {
        return Api21Impl.dispatchNestedPreScroll(view, i, i2, iArr, iArr2);
    }

    public static boolean dispatchNestedScroll(@NonNull View view, int i, int i2, int i3, int i4, @Nullable int[] iArr) {
        return Api21Impl.dispatchNestedScroll(view, i, i2, i3, i4, iArr);
    }

    public static void dispatchStartTemporaryDetach(@NonNull View view) {
        if (Build.VERSION.SDK_INT >= 24) {
            Api24Impl.dispatchStartTemporaryDetach(view);
            return;
        }
        if (!sTempDetachBound) {
            bindTempDetach();
        }
        Method method = sDispatchStartTemporaryDetach;
        if (method == null) {
            view.onStartTemporaryDetach();
            return;
        }
        try {
            method.invoke(view, null);
        } catch (Exception e) {
            Log.d(TAG, "Error calling dispatchStartTemporaryDetach", e);
        }
    }

    @UiThread
    public static boolean dispatchUnhandledKeyEventBeforeCallback(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        return UnhandledKeyEventManager.at(view).dispatch(view, keyEvent);
    }

    @UiThread
    public static boolean dispatchUnhandledKeyEventBeforeHierarchy(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        return UnhandledKeyEventManager.at(view).preDispatch(keyEvent);
    }

    public static void enableAccessibleClickableSpanSupport(@NonNull View view) {
        ensureAccessibilityDelegateCompat(view);
    }

    public static void ensureAccessibilityDelegateCompat(@NonNull View view) {
        AccessibilityDelegateCompat accessibilityDelegate = getAccessibilityDelegate(view);
        if (accessibilityDelegate == null) {
            accessibilityDelegate = new AccessibilityDelegateCompat();
        }
        setAccessibilityDelegate(view, accessibilityDelegate);
    }

    @Deprecated
    public static int generateViewId() {
        return View.generateViewId();
    }

    @Nullable
    public static AccessibilityDelegateCompat getAccessibilityDelegate(@NonNull View view) {
        View.AccessibilityDelegate accessibilityDelegateInternal = getAccessibilityDelegateInternal(view);
        if (accessibilityDelegateInternal == null) {
            return null;
        }
        return accessibilityDelegateInternal instanceof AccessibilityDelegateCompat.AccessibilityDelegateAdapter ? ((AccessibilityDelegateCompat.AccessibilityDelegateAdapter) accessibilityDelegateInternal).mCompat : new AccessibilityDelegateCompat(accessibilityDelegateInternal);
    }

    @Nullable
    public static View.AccessibilityDelegate getAccessibilityDelegateInternal(@NonNull View view) {
        return Build.VERSION.SDK_INT >= 29 ? Api29Impl.getAccessibilityDelegate(view) : getAccessibilityDelegateThroughReflection(view);
    }

    @Nullable
    public static View.AccessibilityDelegate getAccessibilityDelegateThroughReflection(@NonNull View view) {
        if (sAccessibilityDelegateCheckFailed) {
            return null;
        }
        if (sAccessibilityDelegateField == null) {
            try {
                Field declaredField = View.class.getDeclaredField("mAccessibilityDelegate");
                sAccessibilityDelegateField = declaredField;
                declaredField.setAccessible(true);
            } catch (Throwable unused) {
                sAccessibilityDelegateCheckFailed = true;
                return null;
            }
        }
        try {
            Object obj = sAccessibilityDelegateField.get(view);
            if (obj instanceof View.AccessibilityDelegate) {
                return (View.AccessibilityDelegate) obj;
            }
            return null;
        } catch (Throwable unused2) {
            sAccessibilityDelegateCheckFailed = true;
            return null;
        }
    }

    @ReplaceWith(expression = "view.getAccessibilityLiveRegion()")
    @Deprecated
    public static int getAccessibilityLiveRegion(@NonNull View view) {
        return view.getAccessibilityLiveRegion();
    }

    @Nullable
    public static AccessibilityNodeProviderCompat getAccessibilityNodeProvider(@NonNull View view) {
        AccessibilityNodeProvider accessibilityNodeProvider = view.getAccessibilityNodeProvider();
        if (accessibilityNodeProvider != null) {
            return new AccessibilityNodeProviderCompat(accessibilityNodeProvider);
        }
        return null;
    }

    @Nullable
    @UiThread
    public static CharSequence getAccessibilityPaneTitle(@NonNull View view) {
        return paneTitleProperty().get(view);
    }

    public static List<AccessibilityNodeInfoCompat.AccessibilityActionCompat> getActionList(View view) {
        ArrayList arrayList = (ArrayList) view.getTag(R.id.tag_accessibility_actions);
        if (arrayList != null) {
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        view.setTag(R.id.tag_accessibility_actions, arrayList2);
        return arrayList2;
    }

    @ReplaceWith(expression = "view.getAlpha()")
    @Deprecated
    public static float getAlpha(View view) {
        return view.getAlpha();
    }

    @Nullable
    public static AutofillIdCompat getAutofillId(@NonNull View view) {
        if (Build.VERSION.SDK_INT >= 26) {
            return new AutofillIdCompat(Api26Impl.getAutofillId(view));
        }
        return null;
    }

    public static int getAvailableActionIdFromResources(View view, @NonNull CharSequence charSequence) {
        List<AccessibilityNodeInfoCompat.AccessibilityActionCompat> actionList = getActionList(view);
        for (int i = 0; i < actionList.size(); i++) {
            if (TextUtils.equals(charSequence, actionList.get(i).getLabel())) {
                return actionList.get(i).getId();
            }
        }
        int i2 = 0;
        int i3 = -1;
        while (true) {
            int[] iArr = ACCESSIBILITY_ACTIONS_RESOURCE_IDS;
            if (i2 >= iArr.length || i3 != -1) {
                break;
            }
            int i4 = iArr[i2];
            boolean z = true;
            for (int i5 = 0; i5 < actionList.size(); i5++) {
                z &= actionList.get(i5).getId() != i4;
            }
            if (z) {
                i3 = i4;
            }
            i2++;
        }
        return i3;
    }

    @Nullable
    public static ColorStateList getBackgroundTintList(@NonNull View view) {
        return Api21Impl.getBackgroundTintList(view);
    }

    @Nullable
    public static PorterDuff.Mode getBackgroundTintMode(@NonNull View view) {
        return Api21Impl.getBackgroundTintMode(view);
    }

    @Nullable
    @ReplaceWith(expression = "view.getClipBounds()")
    @Deprecated
    public static Rect getClipBounds(@NonNull View view) {
        return view.getClipBounds();
    }

    @Nullable
    public static ContentCaptureSessionCompat getContentCaptureSession(@NonNull View view) {
        ContentCaptureSession contentCaptureSession;
        if (Build.VERSION.SDK_INT < 29 || (contentCaptureSession = Api29Impl.getContentCaptureSession(view)) == null) {
            return null;
        }
        return new ContentCaptureSessionCompat(contentCaptureSession, view);
    }

    @Nullable
    @ReplaceWith(expression = "view.getDisplay()")
    @Deprecated
    public static Display getDisplay(@NonNull View view) {
        return view.getDisplay();
    }

    public static float getElevation(@NonNull View view) {
        return Api21Impl.getElevation(view);
    }

    public static Rect getEmptyTempRect() {
        if (sThreadLocalRect == null) {
            sThreadLocalRect = new ThreadLocal<>();
        }
        Rect rect = sThreadLocalRect.get();
        if (rect == null) {
            rect = new Rect();
            sThreadLocalRect.set(rect);
        }
        rect.setEmpty();
        return rect;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static OnReceiveContentViewBehavior getFallback(@NonNull View view) {
        return view instanceof OnReceiveContentViewBehavior ? (OnReceiveContentViewBehavior) view : NO_OP_ON_RECEIVE_CONTENT_VIEW_BEHAVIOR;
    }

    @ReplaceWith(expression = "view.getFitsSystemWindows()")
    @Deprecated
    public static boolean getFitsSystemWindows(@NonNull View view) {
        return view.getFitsSystemWindows();
    }

    @ReplaceWith(expression = "view.getImportantForAccessibility()")
    @Deprecated
    public static int getImportantForAccessibility(@NonNull View view) {
        return view.getImportantForAccessibility();
    }

    @SuppressLint({"InlinedApi"})
    public static int getImportantForAutofill(@NonNull View view) {
        if (Build.VERSION.SDK_INT >= 26) {
            return Api26Impl.getImportantForAutofill(view);
        }
        return 0;
    }

    public static int getImportantForContentCapture(@NonNull View view) {
        if (Build.VERSION.SDK_INT >= 30) {
            return Api30Impl.getImportantForContentCapture(view);
        }
        return 0;
    }

    @ReplaceWith(expression = "view.getLabelFor()")
    @Deprecated
    public static int getLabelFor(@NonNull View view) {
        return view.getLabelFor();
    }

    @ReplaceWith(expression = "view.getLayerType()")
    @Deprecated
    public static int getLayerType(View view) {
        return view.getLayerType();
    }

    @ReplaceWith(expression = "view.getLayoutDirection()")
    @Deprecated
    public static int getLayoutDirection(@NonNull View view) {
        return view.getLayoutDirection();
    }

    @Nullable
    @ReplaceWith(expression = "view.getMatrix()")
    @Deprecated
    public static Matrix getMatrix(View view) {
        return view.getMatrix();
    }

    @ReplaceWith(expression = "view.getMeasuredHeightAndState()")
    @Deprecated
    public static int getMeasuredHeightAndState(View view) {
        return view.getMeasuredHeightAndState();
    }

    @ReplaceWith(expression = "view.getMeasuredState()")
    @Deprecated
    public static int getMeasuredState(View view) {
        return view.getMeasuredState();
    }

    @ReplaceWith(expression = "view.getMeasuredWidthAndState()")
    @Deprecated
    public static int getMeasuredWidthAndState(View view) {
        return view.getMeasuredWidthAndState();
    }

    @ReplaceWith(expression = "view.getMinimumHeight()")
    @Deprecated
    public static int getMinimumHeight(@NonNull View view) {
        return view.getMinimumHeight();
    }

    @ReplaceWith(expression = "view.getMinimumWidth()")
    @Deprecated
    public static int getMinimumWidth(@NonNull View view) {
        return view.getMinimumWidth();
    }

    public static int getNextClusterForwardId(@NonNull View view) {
        if (Build.VERSION.SDK_INT >= 26) {
            return Api26Impl.getNextClusterForwardId(view);
        }
        return -1;
    }

    @Nullable
    public static String[] getOnReceiveContentMimeTypes(@NonNull View view) {
        return Build.VERSION.SDK_INT >= 31 ? Api31Impl.getReceiveContentMimeTypes(view) : (String[]) view.getTag(R.id.tag_on_receive_content_mime_types);
    }

    @ReplaceWith(expression = "view.getOverScrollMode()")
    @Deprecated
    public static int getOverScrollMode(View view) {
        return view.getOverScrollMode();
    }

    @Px
    @ReplaceWith(expression = "view.getPaddingEnd()")
    @Deprecated
    public static int getPaddingEnd(@NonNull View view) {
        return view.getPaddingEnd();
    }

    @Px
    @ReplaceWith(expression = "view.getPaddingStart()")
    @Deprecated
    public static int getPaddingStart(@NonNull View view) {
        return view.getPaddingStart();
    }

    @Nullable
    @ReplaceWith(expression = "view.getParentForAccessibility()")
    @Deprecated
    public static ViewParent getParentForAccessibility(@NonNull View view) {
        return view.getParentForAccessibility();
    }

    @ReplaceWith(expression = "view.getPivotX()")
    @Deprecated
    public static float getPivotX(View view) {
        return view.getPivotX();
    }

    @ReplaceWith(expression = "view.getPivotY()")
    @Deprecated
    public static float getPivotY(View view) {
        return view.getPivotY();
    }

    @Nullable
    public static WindowInsetsCompat getRootWindowInsets(@NonNull View view) {
        return Build.VERSION.SDK_INT >= 23 ? Api23Impl.getRootWindowInsets(view) : Api21Impl.getRootWindowInsets(view);
    }

    @ReplaceWith(expression = "view.getRotation()")
    @Deprecated
    public static float getRotation(View view) {
        return view.getRotation();
    }

    @ReplaceWith(expression = "view.getRotationX()")
    @Deprecated
    public static float getRotationX(View view) {
        return view.getRotationX();
    }

    @ReplaceWith(expression = "view.getRotationY()")
    @Deprecated
    public static float getRotationY(View view) {
        return view.getRotationY();
    }

    @ReplaceWith(expression = "view.getScaleX()")
    @Deprecated
    public static float getScaleX(View view) {
        return view.getScaleX();
    }

    @ReplaceWith(expression = "view.getScaleY()")
    @Deprecated
    public static float getScaleY(View view) {
        return view.getScaleY();
    }

    public static int getScrollIndicators(@NonNull View view) {
        if (Build.VERSION.SDK_INT >= 23) {
            return Api23Impl.getScrollIndicators(view);
        }
        return 0;
    }

    @Nullable
    @UiThread
    public static CharSequence getStateDescription(@NonNull View view) {
        return stateDescriptionProperty().get(view);
    }

    @NonNull
    public static List<Rect> getSystemGestureExclusionRects(@NonNull View view) {
        return Build.VERSION.SDK_INT >= 29 ? Api29Impl.getSystemGestureExclusionRects(view) : Collections.EMPTY_LIST;
    }

    @Nullable
    public static String getTransitionName(@NonNull View view) {
        return Api21Impl.getTransitionName(view);
    }

    @ReplaceWith(expression = "view.getTranslationX()")
    @Deprecated
    public static float getTranslationX(View view) {
        return view.getTranslationX();
    }

    @ReplaceWith(expression = "view.getTranslationY()")
    @Deprecated
    public static float getTranslationY(View view) {
        return view.getTranslationY();
    }

    public static float getTranslationZ(@NonNull View view) {
        return Api21Impl.getTranslationZ(view);
    }

    @Nullable
    @Deprecated
    public static WindowInsetsControllerCompat getWindowInsetsController(@NonNull View view) {
        if (Build.VERSION.SDK_INT >= 30) {
            return Api30Impl.getWindowInsetsController(view);
        }
        for (Context context = view.getContext(); context instanceof ContextWrapper; context = ((ContextWrapper) context).getBaseContext()) {
            if (context instanceof Activity) {
                Window window = ((Activity) context).getWindow();
                if (window != null) {
                    return new WindowInsetsControllerCompat(window, view);
                }
                return null;
            }
        }
        return null;
    }

    @ReplaceWith(expression = "view.getWindowSystemUiVisibility()")
    @Deprecated
    public static int getWindowSystemUiVisibility(@NonNull View view) {
        return view.getWindowSystemUiVisibility();
    }

    @ReplaceWith(expression = "view.getX()")
    @Deprecated
    public static float getX(View view) {
        return view.getX();
    }

    @ReplaceWith(expression = "view.getY()")
    @Deprecated
    public static float getY(View view) {
        return view.getY();
    }

    public static float getZ(@NonNull View view) {
        return Api21Impl.getZ(view);
    }

    public static boolean hasAccessibilityDelegate(@NonNull View view) {
        return getAccessibilityDelegateInternal(view) != null;
    }

    public static boolean hasExplicitFocusable(@NonNull View view) {
        return Build.VERSION.SDK_INT >= 26 ? Api26Impl.hasExplicitFocusable(view) : view.hasFocusable();
    }

    public static boolean hasNestedScrollingParent(@NonNull View view) {
        return Api21Impl.hasNestedScrollingParent(view);
    }

    @ReplaceWith(expression = "view.hasOnClickListeners()")
    @Deprecated
    public static boolean hasOnClickListeners(@NonNull View view) {
        return view.hasOnClickListeners();
    }

    @ReplaceWith(expression = "view.hasOverlappingRendering()")
    @Deprecated
    public static boolean hasOverlappingRendering(@NonNull View view) {
        return view.hasOverlappingRendering();
    }

    @ReplaceWith(expression = "view.hasTransientState()")
    @Deprecated
    public static boolean hasTransientState(@NonNull View view) {
        return view.hasTransientState();
    }

    @UiThread
    public static boolean isAccessibilityHeading(@NonNull View view) {
        Boolean bool = accessibilityHeadingProperty().get(view);
        return bool != null && bool.booleanValue();
    }

    @ReplaceWith(expression = "view.isAttachedToWindow()")
    @Deprecated
    public static boolean isAttachedToWindow(@NonNull View view) {
        return view.isAttachedToWindow();
    }

    public static boolean isFocusedByDefault(@NonNull View view) {
        if (Build.VERSION.SDK_INT >= 26) {
            return Api26Impl.isFocusedByDefault(view);
        }
        return false;
    }

    public static boolean isImportantForAccessibility(@NonNull View view) {
        return Api21Impl.isImportantForAccessibility(view);
    }

    public static boolean isImportantForAutofill(@NonNull View view) {
        if (Build.VERSION.SDK_INT >= 26) {
            return Api26Impl.isImportantForAutofill(view);
        }
        return true;
    }

    public static boolean isImportantForContentCapture(@NonNull View view) {
        if (Build.VERSION.SDK_INT >= 30) {
            return Api30Impl.isImportantForContentCapture(view);
        }
        return false;
    }

    @ReplaceWith(expression = "view.isInLayout()")
    @Deprecated
    public static boolean isInLayout(@NonNull View view) {
        return view.isInLayout();
    }

    public static boolean isKeyboardNavigationCluster(@NonNull View view) {
        if (Build.VERSION.SDK_INT >= 26) {
            return Api26Impl.isKeyboardNavigationCluster(view);
        }
        return false;
    }

    @ReplaceWith(expression = "view.isLaidOut()")
    @Deprecated
    public static boolean isLaidOut(@NonNull View view) {
        return view.isLaidOut();
    }

    @ReplaceWith(expression = "view.isLayoutDirectionResolved()")
    @Deprecated
    public static boolean isLayoutDirectionResolved(@NonNull View view) {
        return view.isLayoutDirectionResolved();
    }

    public static boolean isNestedScrollingEnabled(@NonNull View view) {
        return Api21Impl.isNestedScrollingEnabled(view);
    }

    @ReplaceWith(expression = "view.isOpaque()")
    @Deprecated
    public static boolean isOpaque(View view) {
        return view.isOpaque();
    }

    @ReplaceWith(expression = "view.isPaddingRelative()")
    @Deprecated
    public static boolean isPaddingRelative(@NonNull View view) {
        return view.isPaddingRelative();
    }

    @UiThread
    public static boolean isScreenReaderFocusable(@NonNull View view) {
        Boolean bool = screenReaderFocusableProperty().get(view);
        return bool != null && bool.booleanValue();
    }

    @ReplaceWith(expression = "view.jumpDrawablesToCurrentState()")
    @Deprecated
    public static void jumpDrawablesToCurrentState(View view) {
        view.jumpDrawablesToCurrentState();
    }

    @Nullable
    public static View keyboardNavigationClusterSearch(@NonNull View view, @Nullable View view2, int i) {
        if (Build.VERSION.SDK_INT >= 26) {
            return Api26Impl.keyboardNavigationClusterSearch(view, view2, i);
        }
        return null;
    }

    public static void notifyViewAccessibilityStateChangedIfNeeded(View view, int i) {
        AccessibilityManager accessibilityManager = (AccessibilityManager) view.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled()) {
            boolean z = getAccessibilityPaneTitle(view) != null && view.isShown() && view.getWindowVisibility() == 0;
            if (view.getAccessibilityLiveRegion() != 0 || z) {
                AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain();
                accessibilityEventObtain.setEventType(z ? 32 : 2048);
                accessibilityEventObtain.setContentChangeTypes(i);
                if (z) {
                    accessibilityEventObtain.getText().add(getAccessibilityPaneTitle(view));
                    setImportantForAccessibilityIfNeeded(view);
                }
                view.sendAccessibilityEventUnchecked(accessibilityEventObtain);
                return;
            }
            if (i != 32) {
                if (view.getParent() != null) {
                    try {
                        view.getParent().notifySubtreeAccessibilityStateChanged(view, view, i);
                        return;
                    } catch (AbstractMethodError e) {
                        Log.e(TAG, view.getParent().getClass().getSimpleName().concat(" does not fully implement ViewParent"), e);
                        return;
                    }
                }
                return;
            }
            AccessibilityEvent accessibilityEventObtain2 = AccessibilityEvent.obtain();
            view.onInitializeAccessibilityEvent(accessibilityEventObtain2);
            accessibilityEventObtain2.setEventType(32);
            accessibilityEventObtain2.setContentChangeTypes(i);
            accessibilityEventObtain2.setSource(view);
            view.onPopulateAccessibilityEvent(accessibilityEventObtain2);
            accessibilityEventObtain2.getText().add(getAccessibilityPaneTitle(view));
            accessibilityManager.sendAccessibilityEvent(accessibilityEventObtain2);
        }
    }

    public static void offsetLeftAndRight(@NonNull View view, int i) {
        boolean z;
        if (Build.VERSION.SDK_INT >= 23) {
            view.offsetLeftAndRight(i);
            return;
        }
        Rect emptyTempRect = getEmptyTempRect();
        Object parent = view.getParent();
        if (parent instanceof View) {
            View view2 = (View) parent;
            emptyTempRect.set(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom());
            z = !emptyTempRect.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        } else {
            z = false;
        }
        compatOffsetLeftAndRight(view, i);
        if (z && emptyTempRect.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom())) {
            ((View) parent).invalidate(emptyTempRect);
        }
    }

    public static void offsetTopAndBottom(@NonNull View view, int i) {
        boolean z;
        if (Build.VERSION.SDK_INT >= 23) {
            view.offsetTopAndBottom(i);
            return;
        }
        Rect emptyTempRect = getEmptyTempRect();
        Object parent = view.getParent();
        if (parent instanceof View) {
            View view2 = (View) parent;
            emptyTempRect.set(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom());
            z = !emptyTempRect.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        } else {
            z = false;
        }
        compatOffsetTopAndBottom(view, i);
        if (z && emptyTempRect.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom())) {
            ((View) parent).invalidate(emptyTempRect);
        }
    }

    @NonNull
    public static WindowInsetsCompat onApplyWindowInsets(@NonNull View view, @NonNull WindowInsetsCompat windowInsetsCompat) {
        WindowInsets windowInsets = windowInsetsCompat.toWindowInsets();
        if (windowInsets != null) {
            WindowInsets windowInsetsOnApplyWindowInsets = Api20Impl.onApplyWindowInsets(view, windowInsets);
            if (!windowInsetsOnApplyWindowInsets.equals(windowInsets)) {
                return WindowInsetsCompat.toWindowInsetsCompat(windowInsetsOnApplyWindowInsets, view);
            }
        }
        return windowInsetsCompat;
    }

    @ReplaceWith(expression = "v.onInitializeAccessibilityEvent(event)")
    @Deprecated
    public static void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        view.onInitializeAccessibilityEvent(accessibilityEvent);
    }

    @ReplaceWith(expression = "v.onInitializeAccessibilityNodeInfo(info.unwrap())")
    @Deprecated
    public static void onInitializeAccessibilityNodeInfo(@NonNull View view, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        view.onInitializeAccessibilityNodeInfo(accessibilityNodeInfoCompat.unwrap());
    }

    @ReplaceWith(expression = "v.onPopulateAccessibilityEvent(event)")
    @Deprecated
    public static void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        view.onPopulateAccessibilityEvent(accessibilityEvent);
    }

    public static AccessibilityViewProperty<CharSequence> paneTitleProperty() {
        return new AnonymousClass2(R.id.tag_accessibility_pane_title, CharSequence.class, 8, 28);
    }

    @ReplaceWith(expression = "view.performAccessibilityAction(action, arguments)")
    @Deprecated
    public static boolean performAccessibilityAction(@NonNull View view, int i, @Nullable Bundle bundle) {
        return view.performAccessibilityAction(i, bundle);
    }

    public static boolean performHapticFeedback(@NonNull View view, int i) {
        int feedbackConstantOrFallback = HapticFeedbackConstantsCompat.getFeedbackConstantOrFallback(i);
        if (feedbackConstantOrFallback == -1) {
            return false;
        }
        return view.performHapticFeedback(feedbackConstantOrFallback);
    }

    @Nullable
    public static ContentInfoCompat performReceiveContent(@NonNull View view, @NonNull ContentInfoCompat contentInfoCompat) {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "performReceiveContent: " + contentInfoCompat + ", view=" + view.getClass().getSimpleName() + "[" + view.getId() + "]");
        }
        if (Build.VERSION.SDK_INT >= 31) {
            return Api31Impl.performReceiveContent(view, contentInfoCompat);
        }
        OnReceiveContentListener onReceiveContentListener = (OnReceiveContentListener) view.getTag(R.id.tag_on_receive_content_listener);
        if (onReceiveContentListener == null) {
            return getFallback(view).onReceiveContent(contentInfoCompat);
        }
        ContentInfoCompat contentInfoCompatOnReceiveContent = onReceiveContentListener.onReceiveContent(view, contentInfoCompat);
        if (contentInfoCompatOnReceiveContent == null) {
            return null;
        }
        return getFallback(view).onReceiveContent(contentInfoCompatOnReceiveContent);
    }

    @ReplaceWith(expression = "view.postInvalidateOnAnimation()")
    @Deprecated
    public static void postInvalidateOnAnimation(@NonNull View view) {
        view.postInvalidateOnAnimation();
    }

    @ReplaceWith(expression = "view.postOnAnimation(action)")
    @Deprecated
    public static void postOnAnimation(@NonNull View view, @NonNull Runnable runnable) {
        view.postOnAnimation(runnable);
    }

    @ReplaceWith(expression = "view.postOnAnimationDelayed(action, delayMillis)")
    @SuppressLint({"LambdaLast"})
    @Deprecated
    public static void postOnAnimationDelayed(@NonNull View view, @NonNull Runnable runnable, long j) {
        view.postOnAnimationDelayed(runnable, j);
    }

    public static void removeAccessibilityAction(@NonNull View view, int i) {
        removeActionWithId(i, view);
        notifyViewAccessibilityStateChangedIfNeeded(view, 0);
    }

    public static void removeActionWithId(int i, View view) {
        List<AccessibilityNodeInfoCompat.AccessibilityActionCompat> actionList = getActionList(view);
        for (int i2 = 0; i2 < actionList.size(); i2++) {
            if (actionList.get(i2).getId() == i) {
                actionList.remove(i2);
                return;
            }
        }
    }

    public static void removeOnUnhandledKeyEventListener(@NonNull View view, @NonNull OnUnhandledKeyEventListenerCompat onUnhandledKeyEventListenerCompat) {
        if (Build.VERSION.SDK_INT >= 28) {
            Api28Impl.removeOnUnhandledKeyEventListener(view, onUnhandledKeyEventListenerCompat);
            return;
        }
        ArrayList arrayList = (ArrayList) view.getTag(R.id.tag_unhandled_key_listeners);
        if (arrayList != null) {
            arrayList.remove(onUnhandledKeyEventListenerCompat);
            if (arrayList.size() == 0) {
                UnhandledKeyEventManager.unregisterListeningView(view);
            }
        }
    }

    public static void replaceAccessibilityAction(@NonNull View view, @NonNull AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat, @Nullable CharSequence charSequence, @Nullable AccessibilityViewCommand accessibilityViewCommand) {
        if (accessibilityViewCommand == null && charSequence == null) {
            removeAccessibilityAction(view, accessibilityActionCompat.getId());
        } else {
            addAccessibilityAction(view, accessibilityActionCompat.createReplacementAction(charSequence, accessibilityViewCommand));
        }
    }

    public static void requestApplyInsets(@NonNull View view) {
        Api20Impl.requestApplyInsets(view);
    }

    @NonNull
    public static <T extends View> T requireViewById(@NonNull View view, @IdRes int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            return (T) Api28Impl.requireViewById(view, i);
        }
        T t = (T) view.findViewById(i);
        if (t != null) {
            return t;
        }
        throw new IllegalArgumentException("ID does not reference a View inside this View");
    }

    @Deprecated
    public static int resolveSizeAndState(int i, int i2, int i3) {
        return View.resolveSizeAndState(i, i2, i3);
    }

    public static boolean restoreDefaultFocus(@NonNull View view) {
        return Build.VERSION.SDK_INT >= 26 ? Api26Impl.restoreDefaultFocus(view) : view.requestFocus();
    }

    public static void saveAttributeDataForStyleable(@NonNull View view, @NonNull @SuppressLint({"ContextFirst"}) Context context, @NonNull int[] iArr, @Nullable AttributeSet attributeSet, @NonNull TypedArray typedArray, int i, int i2) {
        if (Build.VERSION.SDK_INT >= 29) {
            Api29Impl.saveAttributeDataForStyleable(view, context, iArr, attributeSet, typedArray, i, i2);
        }
    }

    public static AccessibilityViewProperty<Boolean> screenReaderFocusableProperty() {
        return new AnonymousClass1(R.id.tag_screen_reader_focusable, Boolean.class, 0, 28);
    }

    public static void setAccessibilityDelegate(@NonNull View view, @Nullable AccessibilityDelegateCompat accessibilityDelegateCompat) {
        if (accessibilityDelegateCompat == null && (getAccessibilityDelegateInternal(view) instanceof AccessibilityDelegateCompat.AccessibilityDelegateAdapter)) {
            accessibilityDelegateCompat = new AccessibilityDelegateCompat();
        }
        setImportantForAccessibilityIfNeeded(view);
        view.setAccessibilityDelegate(accessibilityDelegateCompat == null ? null : accessibilityDelegateCompat.getBridge());
    }

    @UiThread
    public static void setAccessibilityHeading(@NonNull View view, boolean z) {
        accessibilityHeadingProperty().set(view, Boolean.valueOf(z));
    }

    @ReplaceWith(expression = "view.setAccessibilityLiveRegion(mode)")
    @Deprecated
    public static void setAccessibilityLiveRegion(@NonNull View view, int i) {
        view.setAccessibilityLiveRegion(i);
    }

    @UiThread
    public static void setAccessibilityPaneTitle(@NonNull View view, @Nullable CharSequence charSequence) {
        paneTitleProperty().set(view, charSequence);
        if (charSequence != null) {
            sAccessibilityPaneVisibilityManager.addAccessibilityPane(view);
        } else {
            sAccessibilityPaneVisibilityManager.removeAccessibilityPane(view);
        }
    }

    @ReplaceWith(expression = "view.setActivated(activated)")
    @Deprecated
    public static void setActivated(View view, boolean z) {
        view.setActivated(z);
    }

    @ReplaceWith(expression = "view.setAlpha(value)")
    @Deprecated
    public static void setAlpha(View view, @FloatRange(from = 0.0d, to = 1.0d) float f) {
        view.setAlpha(f);
    }

    public static void setAutofillHints(@NonNull View view, @Nullable String... strArr) {
        if (Build.VERSION.SDK_INT >= 26) {
            Api26Impl.setAutofillHints(view, strArr);
        }
    }

    public static void setAutofillId(@NonNull View view, @Nullable AutofillIdCompat autofillIdCompat) {
        if (Build.VERSION.SDK_INT >= 28) {
            Api28Impl.setAutofillId(view, autofillIdCompat);
        }
    }

    @ReplaceWith(expression = "view.setBackground(background)")
    @Deprecated
    public static void setBackground(@NonNull View view, @Nullable Drawable drawable) {
        view.setBackground(drawable);
    }

    public static void setBackgroundTintList(@NonNull View view, @Nullable ColorStateList colorStateList) {
        int i = Build.VERSION.SDK_INT;
        Api21Impl.setBackgroundTintList(view, colorStateList);
        if (i == 21) {
            Drawable background = view.getBackground();
            boolean z = (Api21Impl.getBackgroundTintList(view) == null && Api21Impl.getBackgroundTintMode(view) == null) ? false : true;
            if (background == null || !z) {
                return;
            }
            if (background.isStateful()) {
                background.setState(view.getDrawableState());
            }
            view.setBackground(background);
        }
    }

    public static void setBackgroundTintMode(@NonNull View view, @Nullable PorterDuff.Mode mode) {
        int i = Build.VERSION.SDK_INT;
        Api21Impl.setBackgroundTintMode(view, mode);
        if (i == 21) {
            Drawable background = view.getBackground();
            boolean z = (Api21Impl.getBackgroundTintList(view) == null && Api21Impl.getBackgroundTintMode(view) == null) ? false : true;
            if (background == null || !z) {
                return;
            }
            if (background.isStateful()) {
                background.setState(view.getDrawableState());
            }
            view.setBackground(background);
        }
    }

    @SuppressLint({"BanUncheckedReflection"})
    @Deprecated
    public static void setChildrenDrawingOrderEnabled(ViewGroup viewGroup, boolean z) {
        if (sChildrenDrawingOrderMethod == null) {
            try {
                sChildrenDrawingOrderMethod = ViewGroup.class.getDeclaredMethod("setChildrenDrawingOrderEnabled", Boolean.TYPE);
            } catch (NoSuchMethodException e) {
                Log.e(TAG, "Unable to find childrenDrawingOrderEnabled", e);
            }
            sChildrenDrawingOrderMethod.setAccessible(true);
        }
        try {
            sChildrenDrawingOrderMethod.invoke(viewGroup, Boolean.valueOf(z));
        } catch (IllegalAccessException e2) {
            Log.e(TAG, "Unable to invoke childrenDrawingOrderEnabled", e2);
        } catch (IllegalArgumentException e3) {
            Log.e(TAG, "Unable to invoke childrenDrawingOrderEnabled", e3);
        } catch (InvocationTargetException e4) {
            Log.e(TAG, "Unable to invoke childrenDrawingOrderEnabled", e4);
        }
    }

    @ReplaceWith(expression = "view.setClipBounds(clipBounds)")
    @Deprecated
    public static void setClipBounds(@NonNull View view, @Nullable Rect rect) {
        view.setClipBounds(rect);
    }

    public static void setContentCaptureSession(@NonNull View view, @Nullable ContentCaptureSessionCompat contentCaptureSessionCompat) {
        if (Build.VERSION.SDK_INT >= 29) {
            Api29Impl.setContentCaptureSession(view, contentCaptureSessionCompat);
        }
    }

    public static void setElevation(@NonNull View view, float f) {
        Api21Impl.setElevation(view, f);
    }

    @ReplaceWith(expression = "view.setFitsSystemWindows(fitSystemWindows)")
    @Deprecated
    public static void setFitsSystemWindows(View view, boolean z) {
        view.setFitsSystemWindows(z);
    }

    public static void setFocusedByDefault(@NonNull View view, boolean z) {
        if (Build.VERSION.SDK_INT >= 26) {
            Api26Impl.setFocusedByDefault(view, z);
        }
    }

    @ReplaceWith(expression = "view.setHasTransientState(hasTransientState)")
    @Deprecated
    public static void setHasTransientState(@NonNull View view, boolean z) {
        view.setHasTransientState(z);
    }

    @ReplaceWith(expression = "view.setImportantForAccessibility(mode)")
    @UiThread
    @Deprecated
    public static void setImportantForAccessibility(@NonNull View view, int i) {
        view.setImportantForAccessibility(i);
    }

    public static void setImportantForAccessibilityIfNeeded(View view) {
        if (view.getImportantForAccessibility() == 0) {
            view.setImportantForAccessibility(1);
        }
    }

    public static void setImportantForAutofill(@NonNull View view, int i) {
        if (Build.VERSION.SDK_INT >= 26) {
            Api26Impl.setImportantForAutofill(view, i);
        }
    }

    public static void setImportantForContentCapture(@NonNull View view, int i) {
        if (Build.VERSION.SDK_INT >= 30) {
            Api30Impl.setImportantForContentCapture(view, i);
        }
    }

    public static void setKeyboardNavigationCluster(@NonNull View view, boolean z) {
        if (Build.VERSION.SDK_INT >= 26) {
            Api26Impl.setKeyboardNavigationCluster(view, z);
        }
    }

    @ReplaceWith(expression = "view.setLabelFor(labeledId)")
    @Deprecated
    public static void setLabelFor(@NonNull View view, @IdRes int i) {
        view.setLabelFor(i);
    }

    @ReplaceWith(expression = "view.setLayerPaint(paint)")
    @Deprecated
    public static void setLayerPaint(@NonNull View view, @Nullable Paint paint) {
        view.setLayerPaint(paint);
    }

    @ReplaceWith(expression = "view.setLayerType(layerType, paint)")
    @Deprecated
    public static void setLayerType(View view, int i, Paint paint) {
        view.setLayerType(i, paint);
    }

    @ReplaceWith(expression = "view.setLayoutDirection(layoutDirection)")
    @Deprecated
    public static void setLayoutDirection(@NonNull View view, int i) {
        view.setLayoutDirection(i);
    }

    public static void setNestedScrollingEnabled(@NonNull View view, boolean z) {
        Api21Impl.setNestedScrollingEnabled(view, z);
    }

    public static void setNextClusterForwardId(@NonNull View view, int i) {
        if (Build.VERSION.SDK_INT >= 26) {
            Api26Impl.setNextClusterForwardId(view, i);
        }
    }

    public static void setOnApplyWindowInsetsListener(@NonNull View view, @Nullable OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        Api21Impl.setOnApplyWindowInsetsListener(view, onApplyWindowInsetsListener);
    }

    public static void setOnReceiveContentListener(@NonNull View view, @Nullable String[] strArr, @Nullable OnReceiveContentListener onReceiveContentListener) {
        if (Build.VERSION.SDK_INT >= 31) {
            Api31Impl.setOnReceiveContentListener(view, strArr, onReceiveContentListener);
            return;
        }
        if (strArr == null || strArr.length == 0) {
            strArr = null;
        }
        boolean z = false;
        if (onReceiveContentListener != null) {
            Preconditions.checkArgument(strArr != null, "When the listener is set, MIME types must also be set");
        }
        if (strArr != null) {
            int length = strArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                if (strArr[i].startsWith(MediaType.WILDCARD)) {
                    z = true;
                    break;
                }
                i++;
            }
            Preconditions.checkArgument(!z, "A MIME type set here must not start with *: " + Arrays.toString(strArr));
        }
        view.setTag(R.id.tag_on_receive_content_mime_types, strArr);
        view.setTag(R.id.tag_on_receive_content_listener, onReceiveContentListener);
    }

    @ReplaceWith(expression = "view.setOverScrollMode(overScrollMode)")
    @Deprecated
    public static void setOverScrollMode(View view, int i) {
        view.setOverScrollMode(i);
    }

    @ReplaceWith(expression = "view.setPaddingRelative(start, top, end, bottom)")
    @Deprecated
    public static void setPaddingRelative(@NonNull View view, @Px int i, @Px int i2, @Px int i3, @Px int i4) {
        view.setPaddingRelative(i, i2, i3, i4);
    }

    @ReplaceWith(expression = "view.setPivotX(value)")
    @Deprecated
    public static void setPivotX(View view, float f) {
        view.setPivotX(f);
    }

    @ReplaceWith(expression = "view.setPivotY(value)")
    @Deprecated
    public static void setPivotY(View view, float f) {
        view.setPivotY(f);
    }

    public static void setPointerIcon(@NonNull View view, @Nullable PointerIconCompat pointerIconCompat) {
        if (Build.VERSION.SDK_INT >= 24) {
            Api24Impl.setPointerIcon(view, pointerIconCompat != null ? pointerIconCompat.mPointerIcon : null);
        }
    }

    @ReplaceWith(expression = "view.setRotation(value)")
    @Deprecated
    public static void setRotation(View view, float f) {
        view.setRotation(f);
    }

    @ReplaceWith(expression = "view.setRotationX(value)")
    @Deprecated
    public static void setRotationX(View view, float f) {
        view.setRotationX(f);
    }

    @ReplaceWith(expression = "view.setRotationY(value)")
    @Deprecated
    public static void setRotationY(View view, float f) {
        view.setRotationY(f);
    }

    @ReplaceWith(expression = "view.setSaveFromParentEnabled(enabled)")
    @Deprecated
    public static void setSaveFromParentEnabled(View view, boolean z) {
        view.setSaveFromParentEnabled(z);
    }

    @ReplaceWith(expression = "view.setScaleX(value)")
    @Deprecated
    public static void setScaleX(View view, float f) {
        view.setScaleX(f);
    }

    @ReplaceWith(expression = "view.setScaleY(value)")
    @Deprecated
    public static void setScaleY(View view, float f) {
        view.setScaleY(f);
    }

    @UiThread
    public static void setScreenReaderFocusable(@NonNull View view, boolean z) {
        screenReaderFocusableProperty().set(view, Boolean.valueOf(z));
    }

    public static void setScrollIndicators(@NonNull View view, int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            Api23Impl.setScrollIndicators(view, i);
        }
    }

    @UiThread
    public static void setStateDescription(@NonNull View view, @Nullable CharSequence charSequence) {
        stateDescriptionProperty().set(view, charSequence);
    }

    public static void setSystemGestureExclusionRects(@NonNull View view, @NonNull List<Rect> list) {
        if (Build.VERSION.SDK_INT >= 29) {
            Api29Impl.setSystemGestureExclusionRects(view, list);
        }
    }

    public static void setTooltipText(@NonNull View view, @Nullable CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 26) {
            Api26Impl.setTooltipText(view, charSequence);
        }
    }

    public static void setTransitionName(@NonNull View view, @Nullable String str) {
        Api21Impl.setTransitionName(view, str);
    }

    @ReplaceWith(expression = "view.setTranslationX(value)")
    @Deprecated
    public static void setTranslationX(View view, float f) {
        view.setTranslationX(f);
    }

    @ReplaceWith(expression = "view.setTranslationY(value)")
    @Deprecated
    public static void setTranslationY(View view, float f) {
        view.setTranslationY(f);
    }

    public static void setTranslationZ(@NonNull View view, float f) {
        Api21Impl.setTranslationZ(view, f);
    }

    public static void setWindowInsetsAnimationCallback(@NonNull View view, @Nullable WindowInsetsAnimationCompat.Callback callback) {
        WindowInsetsAnimationCompat.setCallback(view, callback);
    }

    @ReplaceWith(expression = "view.setX(value)")
    @Deprecated
    public static void setX(View view, float f) {
        view.setX(f);
    }

    @ReplaceWith(expression = "view.setY(value)")
    @Deprecated
    public static void setY(View view, float f) {
        view.setY(f);
    }

    public static void setZ(@NonNull View view, float f) {
        Api21Impl.setZ(view, f);
    }

    public static boolean startDragAndDrop(@NonNull View view, @Nullable ClipData clipData, @NonNull View.DragShadowBuilder dragShadowBuilder, @Nullable Object obj, int i) {
        return Build.VERSION.SDK_INT >= 24 ? Api24Impl.startDragAndDrop(view, clipData, dragShadowBuilder, obj, i) : view.startDrag(clipData, dragShadowBuilder, obj, i);
    }

    public static boolean startNestedScroll(@NonNull View view, int i) {
        return Api21Impl.startNestedScroll(view, i);
    }

    public static AccessibilityViewProperty<CharSequence> stateDescriptionProperty() {
        return new AnonymousClass3(R.id.tag_state_description, CharSequence.class, 64, 30);
    }

    public static void stopNestedScroll(@NonNull View view) {
        Api21Impl.stopNestedScroll(view);
    }

    public static void tickleInvalidationFlag(View view) {
        float translationY = view.getTranslationY();
        view.setTranslationY(1.0f + translationY);
        view.setTranslationY(translationY);
    }

    public static void updateDragShadow(@NonNull View view, @NonNull View.DragShadowBuilder dragShadowBuilder) {
        if (Build.VERSION.SDK_INT >= 24) {
            Api24Impl.updateDragShadow(view, dragShadowBuilder);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean dispatchNestedPreScroll(@NonNull View view, int i, int i2, @Nullable int[] iArr, @Nullable int[] iArr2, int i3) {
        if (view instanceof NestedScrollingChild2) {
            return ((NestedScrollingChild2) view).dispatchNestedPreScroll(i, i2, iArr, iArr2, i3);
        }
        if (i3 == 0) {
            return Api21Impl.dispatchNestedPreScroll(view, i, i2, iArr, iArr2);
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void dispatchNestedScroll(@NonNull View view, int i, int i2, int i3, int i4, @Nullable int[] iArr, int i5, @NonNull int[] iArr2) {
        if (view instanceof NestedScrollingChild3) {
            ((NestedScrollingChild3) view).dispatchNestedScroll(i, i2, i3, i4, iArr, i5, iArr2);
        } else {
            dispatchNestedScroll(view, i, i2, i3, i4, iArr, i5);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean hasNestedScrollingParent(@NonNull View view, int i) {
        if (view instanceof NestedScrollingChild2) {
            ((NestedScrollingChild2) view).hasNestedScrollingParent(i);
            return false;
        }
        if (i == 0) {
            return Api21Impl.hasNestedScrollingParent(view);
        }
        return false;
    }

    @ReplaceWith(expression = "view.postInvalidateOnAnimation(left, top, right, bottom)")
    @Deprecated
    public static void postInvalidateOnAnimation(@NonNull View view, int i, int i2, int i3, int i4) {
        view.postInvalidateOnAnimation(i, i2, i3, i4);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean startNestedScroll(@NonNull View view, int i, int i2) {
        if (view instanceof NestedScrollingChild2) {
            return ((NestedScrollingChild2) view).startNestedScroll(i, i2);
        }
        if (i2 == 0) {
            return Api21Impl.startNestedScroll(view, i);
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void stopNestedScroll(@NonNull View view, int i) {
        if (view instanceof NestedScrollingChild2) {
            ((NestedScrollingChild2) view).stopNestedScroll(i);
        } else if (i == 0) {
            Api21Impl.stopNestedScroll(view);
        }
    }

    public static boolean performHapticFeedback(@NonNull View view, int i, int i2) {
        int feedbackConstantOrFallback = HapticFeedbackConstantsCompat.getFeedbackConstantOrFallback(i);
        if (feedbackConstantOrFallback == -1) {
            return false;
        }
        return view.performHapticFeedback(feedbackConstantOrFallback, i2);
    }

    public static void setScrollIndicators(@NonNull View view, int i, int i2) {
        if (Build.VERSION.SDK_INT >= 23) {
            Api23Impl.setScrollIndicators(view, i, i2);
        }
    }

    public static void addAccessibilityAction(@NonNull View view, @NonNull AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat) {
        ensureAccessibilityDelegateCompat(view);
        removeActionWithId(accessibilityActionCompat.getId(), view);
        getActionList(view).add(accessibilityActionCompat);
        notifyViewAccessibilityStateChangedIfNeeded(view, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean dispatchNestedScroll(@NonNull View view, int i, int i2, int i3, int i4, @Nullable int[] iArr, int i5) {
        if (view instanceof NestedScrollingChild2) {
            return ((NestedScrollingChild2) view).dispatchNestedScroll(i, i2, i3, i4, iArr, i5);
        }
        if (i5 == 0) {
            return Api21Impl.dispatchNestedScroll(view, i, i2, i3, i4, iArr);
        }
        return false;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class AccessibilityPaneVisibilityManager implements ViewTreeObserver.OnGlobalLayoutListener, View.OnAttachStateChangeListener {
        public final WeakHashMap<View, Boolean> mPanesToVisible = new WeakHashMap<>();

        public void addAccessibilityPane(View view) {
            this.mPanesToVisible.put(view, Boolean.valueOf(view.isShown() && view.getWindowVisibility() == 0));
            view.addOnAttachStateChangeListener(this);
            if (view.isAttachedToWindow()) {
                view.getViewTreeObserver().addOnGlobalLayoutListener(this);
            }
        }

        public final void checkPaneVisibility(Map.Entry<View, Boolean> entry) {
            View key = entry.getKey();
            boolean zBooleanValue = entry.getValue().booleanValue();
            boolean z = key.isShown() && key.getWindowVisibility() == 0;
            if (zBooleanValue != z) {
                ViewCompat.notifyViewAccessibilityStateChangedIfNeeded(key, z ? 16 : 32);
                entry.setValue(Boolean.valueOf(z));
            }
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (Build.VERSION.SDK_INT < 28) {
                Iterator<Map.Entry<View, Boolean>> it = this.mPanesToVisible.entrySet().iterator();
                while (it.hasNext()) {
                    checkPaneVisibility(it.next());
                }
            }
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            registerForLayoutCallback(view);
        }

        public final void registerForLayoutCallback(View view) {
            view.getViewTreeObserver().addOnGlobalLayoutListener(this);
        }

        public void removeAccessibilityPane(View view) {
            this.mPanesToVisible.remove(view);
            view.removeOnAttachStateChangeListener(this);
            view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }

        public final void unregisterForLayoutCallback(View view) {
            view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
        }
    }

    public static /* synthetic */ ContentInfoCompat $r8$lambda$mRexKut8PHPxTytR38MAzVT9ekI(ContentInfoCompat contentInfoCompat) {
        return contentInfoCompat;
    }
}
