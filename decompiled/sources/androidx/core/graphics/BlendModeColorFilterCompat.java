package androidx.core.graphics;

import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.graphics.BlendModeUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class BlendModeColorFilterCompat {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(29)
    public static class Api29Impl {
        public static ColorFilter createBlendModeColorFilter(int i, Object obj) {
            return new BlendModeColorFilter(i, (BlendMode) obj);
        }
    }

    @Nullable
    public static ColorFilter createBlendModeColorFilterCompat(int i, @NonNull BlendModeCompat blendModeCompat) {
        if (Build.VERSION.SDK_INT >= 29) {
            Object objObtainBlendModeFromCompat = BlendModeUtils.Api29Impl.obtainBlendModeFromCompat(blendModeCompat);
            if (objObtainBlendModeFromCompat != null) {
                return Api29Impl.createBlendModeColorFilter(i, objObtainBlendModeFromCompat);
            }
            return null;
        }
        PorterDuff.Mode modeObtainPorterDuffFromCompat = BlendModeUtils.obtainPorterDuffFromCompat(blendModeCompat);
        if (modeObtainPorterDuffFromCompat != null) {
            return new PorterDuffColorFilter(i, modeObtainPorterDuffFromCompat);
        }
        return null;
    }
}
