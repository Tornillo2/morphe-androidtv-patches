package androidx.core.content;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class SharedPreferencesKt {
    @SuppressLint({"ApplySharedPref"})
    public static final void edit(@NotNull SharedPreferences sharedPreferences, boolean z, @NotNull Function1<? super SharedPreferences.Editor, Unit> function1) {
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        function1.invoke(editorEdit);
        if (z) {
            editorEdit.commit();
        } else {
            editorEdit.apply();
        }
    }

    public static /* synthetic */ void edit$default(SharedPreferences sharedPreferences, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        function1.invoke(editorEdit);
        if (z) {
            editorEdit.commit();
        } else {
            editorEdit.apply();
        }
    }
}
