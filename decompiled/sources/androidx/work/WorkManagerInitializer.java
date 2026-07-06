package androidx.work;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.startup.Initializer;
import androidx.work.Configuration;
import androidx.work.impl.WorkManagerImpl;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class WorkManagerInitializer implements Initializer<WorkManager> {
    public static final String TAG = Logger.tagWithPrefix("WrkMgrInitializer");

    @Override // androidx.startup.Initializer
    @NonNull
    public List<Class<? extends Initializer<?>>> dependencies() {
        return Collections.EMPTY_LIST;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.startup.Initializer
    @NonNull
    public WorkManager create(@NonNull Context context) {
        Logger.get().debug(TAG, "Initializing WorkManager with default configuration.", new Throwable[0]);
        WorkManagerImpl.initialize(context, new Configuration(new Configuration.Builder()));
        return WorkManagerImpl.getInstance(context);
    }
}
