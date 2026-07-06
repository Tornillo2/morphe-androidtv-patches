package androidx.core.view;

import android.graphics.Insets;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Build;
import android.view.DisplayCutout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class DisplayCutoutCompat {
    public final DisplayCutout mDisplayCutout;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(28)
    public static class Api28Impl {
        public static DisplayCutout createDisplayCutout(Rect rect, List<Rect> list) {
            return new DisplayCutout(rect, list);
        }

        public static List<Rect> getBoundingRects(DisplayCutout displayCutout) {
            return displayCutout.getBoundingRects();
        }

        public static int getSafeInsetBottom(DisplayCutout displayCutout) {
            return displayCutout.getSafeInsetBottom();
        }

        public static int getSafeInsetLeft(DisplayCutout displayCutout) {
            return displayCutout.getSafeInsetLeft();
        }

        public static int getSafeInsetRight(DisplayCutout displayCutout) {
            return displayCutout.getSafeInsetRight();
        }

        public static int getSafeInsetTop(DisplayCutout displayCutout) {
            return displayCutout.getSafeInsetTop();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(29)
    public static class Api29Impl {
        public static DisplayCutout createDisplayCutout(Insets insets, Rect rect, Rect rect2, Rect rect3, Rect rect4) {
            return new DisplayCutout(insets, rect, rect2, rect3, rect4);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(30)
    public static class Api30Impl {
        public static DisplayCutout createDisplayCutout(Insets insets, Rect rect, Rect rect2, Rect rect3, Rect rect4, Insets insets2) {
            return new DisplayCutout(insets, rect, rect2, rect3, rect4, insets2);
        }

        public static Insets getWaterfallInsets(DisplayCutout displayCutout) {
            return displayCutout.getWaterfallInsets();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(31)
    public static class Api31Impl {
        @Nullable
        public static Path getCutoutPath(DisplayCutout displayCutout) {
            return displayCutout.getCutoutPath();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(33)
    public static class Api33Impl {
        public static DisplayCutout createDisplayCutout(Insets insets, Rect rect, Rect rect2, Rect rect3, Rect rect4, Insets insets2, Path path) {
            return new DisplayCutout.Builder().setSafeInsets(insets).setBoundingRectLeft(rect).setBoundingRectTop(rect2).setBoundingRectRight(rect3).setBoundingRectBottom(rect4).setWaterfallInsets(insets2).setCutoutPath(path).build();
        }
    }

    public DisplayCutoutCompat(@Nullable Rect rect, @Nullable List<Rect> list) {
        this(Build.VERSION.SDK_INT >= 28 ? Api28Impl.createDisplayCutout(rect, list) : null);
    }

    public static DisplayCutout constructDisplayCutout(@NonNull androidx.core.graphics.Insets insets, @Nullable Rect rect, @Nullable Rect rect2, @Nullable Rect rect3, @Nullable Rect rect4, @NonNull androidx.core.graphics.Insets insets2, @Nullable Path path) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 33) {
            return Api33Impl.createDisplayCutout(insets.toPlatformInsets(), rect, rect2, rect3, rect4, insets2.toPlatformInsets(), path);
        }
        if (i >= 30) {
            return Api30Impl.createDisplayCutout(insets.toPlatformInsets(), rect, rect2, rect3, rect4, insets2.toPlatformInsets());
        }
        if (i >= 29) {
            return Api29Impl.createDisplayCutout(insets.toPlatformInsets(), rect, rect2, rect3, rect4);
        }
        if (i < 28) {
            return null;
        }
        Rect rect5 = new Rect(insets.left, insets.top, insets.right, insets.bottom);
        ArrayList arrayList = new ArrayList();
        if (rect != null) {
            arrayList.add(rect);
        }
        if (rect2 != null) {
            arrayList.add(rect2);
        }
        if (rect3 != null) {
            arrayList.add(rect3);
        }
        if (rect4 != null) {
            arrayList.add(rect4);
        }
        return Api28Impl.createDisplayCutout(rect5, arrayList);
    }

    public static DisplayCutoutCompat wrap(DisplayCutout displayCutout) {
        if (displayCutout == null) {
            return null;
        }
        return new DisplayCutoutCompat(displayCutout);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DisplayCutoutCompat.class != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.mDisplayCutout, ((DisplayCutoutCompat) obj).mDisplayCutout);
    }

    @NonNull
    public List<Rect> getBoundingRects() {
        return Build.VERSION.SDK_INT >= 28 ? Api28Impl.getBoundingRects(this.mDisplayCutout) : Collections.EMPTY_LIST;
    }

    @Nullable
    public Path getCutoutPath() {
        if (Build.VERSION.SDK_INT >= 31) {
            return Api31Impl.getCutoutPath(this.mDisplayCutout);
        }
        return null;
    }

    public int getSafeInsetBottom() {
        if (Build.VERSION.SDK_INT >= 28) {
            return Api28Impl.getSafeInsetBottom(this.mDisplayCutout);
        }
        return 0;
    }

    public int getSafeInsetLeft() {
        if (Build.VERSION.SDK_INT >= 28) {
            return Api28Impl.getSafeInsetLeft(this.mDisplayCutout);
        }
        return 0;
    }

    public int getSafeInsetRight() {
        if (Build.VERSION.SDK_INT >= 28) {
            return Api28Impl.getSafeInsetRight(this.mDisplayCutout);
        }
        return 0;
    }

    public int getSafeInsetTop() {
        if (Build.VERSION.SDK_INT >= 28) {
            return Api28Impl.getSafeInsetTop(this.mDisplayCutout);
        }
        return 0;
    }

    @NonNull
    public androidx.core.graphics.Insets getWaterfallInsets() {
        return Build.VERSION.SDK_INT >= 30 ? androidx.core.graphics.Insets.toCompatInsets(Api30Impl.getWaterfallInsets(this.mDisplayCutout)) : androidx.core.graphics.Insets.NONE;
    }

    public int hashCode() {
        DisplayCutout displayCutout = this.mDisplayCutout;
        if (displayCutout == null) {
            return 0;
        }
        return displayCutout.hashCode();
    }

    @NonNull
    public String toString() {
        return "DisplayCutoutCompat{" + this.mDisplayCutout + "}";
    }

    @RequiresApi(28)
    public DisplayCutout unwrap() {
        return this.mDisplayCutout;
    }

    public DisplayCutoutCompat(@NonNull androidx.core.graphics.Insets insets, @Nullable Rect rect, @Nullable Rect rect2, @Nullable Rect rect3, @Nullable Rect rect4, @NonNull androidx.core.graphics.Insets insets2) {
        this(constructDisplayCutout(insets, rect, rect2, rect3, rect4, insets2, null));
    }

    public DisplayCutoutCompat(@NonNull androidx.core.graphics.Insets insets, @Nullable Rect rect, @Nullable Rect rect2, @Nullable Rect rect3, @Nullable Rect rect4, @NonNull androidx.core.graphics.Insets insets2, @Nullable Path path) {
        this(constructDisplayCutout(insets, rect, rect2, rect3, rect4, insets2, path));
    }

    public DisplayCutoutCompat(DisplayCutout displayCutout) {
        this.mDisplayCutout = displayCutout;
    }
}
