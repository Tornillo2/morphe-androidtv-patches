package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.util.Preconditions;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class FragmentHostCallback<E> extends FragmentContainer {

    @Nullable
    public final Activity mActivity;

    @NonNull
    public final Context mContext;
    public final FragmentManager mFragmentManager;

    @NonNull
    public final Handler mHandler;
    public final int mWindowAnimations;

    public FragmentHostCallback(@Nullable Activity activity, @NonNull Context context, @NonNull Handler handler, int i) {
        this.mFragmentManager = new FragmentManagerImpl();
        this.mActivity = activity;
        Preconditions.checkNotNull(context, "context == null");
        this.mContext = context;
        Preconditions.checkNotNull(handler, "handler == null");
        this.mHandler = handler;
        this.mWindowAnimations = i;
    }

    @Nullable
    public Activity getActivity() {
        return this.mActivity;
    }

    @NonNull
    public Context getContext() {
        return this.mContext;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public Handler getHandler() {
        return this.mHandler;
    }

    @Override // androidx.fragment.app.FragmentContainer
    @Nullable
    public View onFindViewById(int i) {
        return null;
    }

    @Nullable
    public abstract E onGetHost();

    @NonNull
    public LayoutInflater onGetLayoutInflater() {
        return LayoutInflater.from(this.mContext);
    }

    public int onGetWindowAnimations() {
        return this.mWindowAnimations;
    }

    @Override // androidx.fragment.app.FragmentContainer
    public boolean onHasView() {
        return true;
    }

    public boolean onHasWindowAnimations() {
        return true;
    }

    public boolean onShouldSaveFragmentState(@NonNull Fragment fragment) {
        return true;
    }

    public boolean onShouldShowRequestPermissionRationale(@NonNull String str) {
        return false;
    }

    public void onStartActivityFromFragment(@NonNull Fragment fragment, @SuppressLint({"UnknownNullness"}) Intent intent, int i) {
        onStartActivityFromFragment(fragment, intent, i, null);
    }

    @Deprecated
    public void onStartIntentSenderFromFragment(@NonNull Fragment fragment, @SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i, @Nullable Intent intent, int i2, int i3, int i4, @Nullable Bundle bundle) throws IntentSender.SendIntentException {
        if (i != -1) {
            throw new IllegalStateException("Starting intent sender with a requestCode requires a FragmentActivity host");
        }
        this.mActivity.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4, bundle);
    }

    public void onStartActivityFromFragment(@NonNull Fragment fragment, @SuppressLint({"UnknownNullness"}) Intent intent, int i, @Nullable Bundle bundle) {
        if (i != -1) {
            throw new IllegalStateException("Starting activity with a requestCode requires a FragmentActivity host");
        }
        this.mContext.startActivity(intent, bundle);
    }

    public FragmentHostCallback(@NonNull Context context, @NonNull Handler handler, int i) {
        this(context instanceof Activity ? (Activity) context : null, context, handler, i);
    }

    public FragmentHostCallback(@NonNull FragmentActivity fragmentActivity) {
        this(fragmentActivity, fragmentActivity, new Handler(), 0);
    }

    public void onSupportInvalidateOptionsMenu() {
    }

    @Deprecated
    public void onRequestPermissionsFromFragment(@NonNull Fragment fragment, @NonNull String[] strArr, int i) {
    }

    public void onDump(@NonNull String str, @Nullable FileDescriptor fileDescriptor, @NonNull PrintWriter printWriter, @Nullable String[] strArr) {
    }
}
