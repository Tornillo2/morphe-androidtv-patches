package androidx.core.app;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class ActivityOptionsCompat {
    public static final String EXTRA_USAGE_TIME_REPORT = "android.activity.usage_time";
    public static final String EXTRA_USAGE_TIME_REPORT_PACKAGES = "android.usage_time_packages";

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class ActivityOptionsCompatImpl extends ActivityOptionsCompat {
        public final ActivityOptions mActivityOptions;

        public ActivityOptionsCompatImpl(ActivityOptions activityOptions) {
            this.mActivityOptions = activityOptions;
        }

        @Override // androidx.core.app.ActivityOptionsCompat
        public Rect getLaunchBounds() {
            if (Build.VERSION.SDK_INT < 24) {
                return null;
            }
            return Api24Impl.getLaunchBounds(this.mActivityOptions);
        }

        @Override // androidx.core.app.ActivityOptionsCompat
        public void requestUsageTimeReport(@NonNull PendingIntent pendingIntent) {
            if (Build.VERSION.SDK_INT >= 23) {
                Api23Impl.requestUsageTimeReport(this.mActivityOptions, pendingIntent);
            }
        }

        @Override // androidx.core.app.ActivityOptionsCompat
        @NonNull
        public ActivityOptionsCompat setLaunchBounds(@Nullable Rect rect) {
            return Build.VERSION.SDK_INT < 24 ? this : new ActivityOptionsCompatImpl(Api24Impl.setLaunchBounds(this.mActivityOptions, rect));
        }

        @Override // androidx.core.app.ActivityOptionsCompat
        @NonNull
        public ActivityOptionsCompat setPendingIntentBackgroundActivityStartMode(int i) {
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 34) {
                Api34Impl.setPendingIntentBackgroundActivityStartMode(this.mActivityOptions, i);
                return this;
            }
            if (i2 >= 33) {
                Api33Impl.setPendingIntentBackgroundActivityLaunchAllowed(this.mActivityOptions, i != 2);
            }
            return this;
        }

        @Override // androidx.core.app.ActivityOptionsCompat
        @NonNull
        public ActivityOptionsCompat setShareIdentityEnabled(boolean z) {
            return Build.VERSION.SDK_INT < 34 ? this : new ActivityOptionsCompatImpl(Api34Impl.setShareIdentityEnabled(this.mActivityOptions, z));
        }

        @Override // androidx.core.app.ActivityOptionsCompat
        public Bundle toBundle() {
            return this.mActivityOptions.toBundle();
        }

        @Override // androidx.core.app.ActivityOptionsCompat
        public void update(@NonNull ActivityOptionsCompat activityOptionsCompat) {
            if (activityOptionsCompat instanceof ActivityOptionsCompatImpl) {
                this.mActivityOptions.update(((ActivityOptionsCompatImpl) activityOptionsCompat).mActivityOptions);
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(21)
    public static class Api21Impl {
        public static ActivityOptions makeSceneTransitionAnimation(Activity activity, View view, String str) {
            return ActivityOptions.makeSceneTransitionAnimation(activity, view, str);
        }

        public static ActivityOptions makeTaskLaunchBehind() {
            return ActivityOptions.makeTaskLaunchBehind();
        }

        @SafeVarargs
        public static ActivityOptions makeSceneTransitionAnimation(Activity activity, Pair<View, String>... pairArr) {
            return ActivityOptions.makeSceneTransitionAnimation(activity, pairArr);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(23)
    public static class Api23Impl {
        public static ActivityOptions makeBasic() {
            return ActivityOptions.makeBasic();
        }

        public static ActivityOptions makeClipRevealAnimation(View view, int i, int i2, int i3, int i4) {
            return ActivityOptions.makeClipRevealAnimation(view, i, i2, i3, i4);
        }

        public static void requestUsageTimeReport(ActivityOptions activityOptions, PendingIntent pendingIntent) {
            activityOptions.requestUsageTimeReport(pendingIntent);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(24)
    public static class Api24Impl {
        public static Rect getLaunchBounds(ActivityOptions activityOptions) {
            return activityOptions.getLaunchBounds();
        }

        public static ActivityOptions setLaunchBounds(ActivityOptions activityOptions, Rect rect) {
            return activityOptions.setLaunchBounds(rect);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(33)
    public static class Api33Impl {
        public static void setPendingIntentBackgroundActivityLaunchAllowed(ActivityOptions activityOptions, boolean z) {
            activityOptions.setPendingIntentBackgroundActivityLaunchAllowed(z);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(34)
    public static class Api34Impl {
        public static ActivityOptions setPendingIntentBackgroundActivityStartMode(ActivityOptions activityOptions, int i) {
            return activityOptions.setPendingIntentBackgroundActivityStartMode(i);
        }

        public static ActivityOptions setShareIdentityEnabled(ActivityOptions activityOptions, boolean z) {
            return activityOptions.setShareIdentityEnabled(z);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public @interface BackgroundActivityStartMode {
    }

    @NonNull
    public static ActivityOptionsCompat makeBasic() {
        return Build.VERSION.SDK_INT >= 23 ? new ActivityOptionsCompatImpl(Api23Impl.makeBasic()) : new ActivityOptionsCompat();
    }

    @NonNull
    public static ActivityOptionsCompat makeClipRevealAnimation(@NonNull View view, int i, int i2, int i3, int i4) {
        return Build.VERSION.SDK_INT >= 23 ? new ActivityOptionsCompatImpl(Api23Impl.makeClipRevealAnimation(view, i, i2, i3, i4)) : new ActivityOptionsCompat();
    }

    @NonNull
    public static ActivityOptionsCompat makeCustomAnimation(@NonNull Context context, int i, int i2) {
        return new ActivityOptionsCompatImpl(ActivityOptions.makeCustomAnimation(context, i, i2));
    }

    @NonNull
    public static ActivityOptionsCompat makeScaleUpAnimation(@NonNull View view, int i, int i2, int i3, int i4) {
        return new ActivityOptionsCompatImpl(ActivityOptions.makeScaleUpAnimation(view, i, i2, i3, i4));
    }

    @NonNull
    public static ActivityOptionsCompat makeSceneTransitionAnimation(@NonNull Activity activity, @NonNull View view, @NonNull String str) {
        return new ActivityOptionsCompatImpl(ActivityOptions.makeSceneTransitionAnimation(activity, view, str));
    }

    @NonNull
    public static ActivityOptionsCompat makeTaskLaunchBehind() {
        return new ActivityOptionsCompatImpl(ActivityOptions.makeTaskLaunchBehind());
    }

    @NonNull
    public static ActivityOptionsCompat makeThumbnailScaleUpAnimation(@NonNull View view, @NonNull Bitmap bitmap, int i, int i2) {
        return new ActivityOptionsCompatImpl(ActivityOptions.makeThumbnailScaleUpAnimation(view, bitmap, i, i2));
    }

    @Nullable
    public Rect getLaunchBounds() {
        return null;
    }

    @Nullable
    public Bundle toBundle() {
        return null;
    }

    @NonNull
    public static ActivityOptionsCompat makeSceneTransitionAnimation(@NonNull Activity activity, @Nullable androidx.core.util.Pair<View, String>... pairArr) {
        Pair[] pairArr2;
        if (pairArr != null) {
            pairArr2 = new Pair[pairArr.length];
            for (int i = 0; i < pairArr.length; i++) {
                androidx.core.util.Pair<View, String> pair = pairArr[i];
                pairArr2[i] = Pair.create(pair.first, pair.second);
            }
        } else {
            pairArr2 = null;
        }
        return new ActivityOptionsCompatImpl(ActivityOptions.makeSceneTransitionAnimation(activity, pairArr2));
    }

    public void requestUsageTimeReport(@NonNull PendingIntent pendingIntent) {
    }

    @NonNull
    public ActivityOptionsCompat setLaunchBounds(@Nullable Rect rect) {
        return this;
    }

    @NonNull
    public ActivityOptionsCompat setPendingIntentBackgroundActivityStartMode(int i) {
        return this;
    }

    @NonNull
    public ActivityOptionsCompat setShareIdentityEnabled(boolean z) {
        return this;
    }

    public void update(@NonNull ActivityOptionsCompat activityOptionsCompat) {
    }
}
