package androidx.core.view;

import android.os.Build;
import android.view.ViewStructure;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class ViewStructureCompat {
    public final Object mWrappedObj;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(23)
    public static class Api23Impl {
        public static void setClassName(ViewStructure viewStructure, String str) {
            viewStructure.setClassName(str);
        }

        public static void setContentDescription(ViewStructure viewStructure, CharSequence charSequence) {
            viewStructure.setContentDescription(charSequence);
        }

        public static void setDimens(ViewStructure viewStructure, int i, int i2, int i3, int i4, int i5, int i6) {
            viewStructure.setDimens(i, i2, i3, i4, i5, i6);
        }

        public static void setText(ViewStructure viewStructure, CharSequence charSequence) {
            viewStructure.setText(charSequence);
        }
    }

    public ViewStructureCompat(@NonNull ViewStructure viewStructure) {
        this.mWrappedObj = viewStructure;
    }

    @NonNull
    @RequiresApi(23)
    public static ViewStructureCompat toViewStructureCompat(@NonNull ViewStructure viewStructure) {
        return new ViewStructureCompat(viewStructure);
    }

    public void setClassName(@NonNull String str) {
        if (Build.VERSION.SDK_INT >= 23) {
            ViewStructureCompat$$ExternalSyntheticApiModelOutline0.m(this.mWrappedObj).setClassName(str);
        }
    }

    public void setContentDescription(@NonNull CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 23) {
            ViewStructureCompat$$ExternalSyntheticApiModelOutline0.m(this.mWrappedObj).setContentDescription(charSequence);
        }
    }

    public void setDimens(int i, int i2, int i3, int i4, int i5, int i6) {
        if (Build.VERSION.SDK_INT >= 23) {
            ViewStructureCompat$$ExternalSyntheticApiModelOutline0.m(this.mWrappedObj).setDimens(i, i2, i3, i4, i5, i6);
        }
    }

    public void setText(@NonNull CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 23) {
            ViewStructureCompat$$ExternalSyntheticApiModelOutline0.m(this.mWrappedObj).setText(charSequence);
        }
    }

    @NonNull
    @RequiresApi(23)
    public ViewStructure toViewStructure() {
        return ViewStructureCompat$$ExternalSyntheticApiModelOutline0.m(this.mWrappedObj);
    }
}
