package androidx.activity.result;

import android.annotation.SuppressLint;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class ActivityResultLauncher<I> {
    @NonNull
    public abstract ActivityResultContract<I, ?> getContract();

    public void launch(@SuppressLint({"UnknownNullness"}) I i) {
        launch(i, null);
    }

    public abstract void launch(@SuppressLint({"UnknownNullness"}) I i, @Nullable ActivityOptionsCompat activityOptionsCompat);

    @MainThread
    public abstract void unregister();
}
