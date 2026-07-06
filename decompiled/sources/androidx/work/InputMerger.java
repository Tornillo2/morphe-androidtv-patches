package androidx.work;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.core.app.RemoteInput$$ExternalSyntheticOutline0;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class InputMerger {
    public static final String TAG = Logger.tagWithPrefix("InputMerger");

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static InputMerger fromClassName(String className) {
        try {
            return (InputMerger) Class.forName(className).newInstance();
        } catch (Exception e) {
            Logger.get().error(TAG, RemoteInput$$ExternalSyntheticOutline0.m("Trouble instantiating + ", className), e);
            return null;
        }
    }

    @NonNull
    public abstract Data merge(@NonNull List<Data> inputs);
}
