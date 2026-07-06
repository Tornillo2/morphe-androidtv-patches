package androidx.core.app;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.app.RemoteAction;
import android.graphics.drawable.Icon;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.drawable.IconCompat;
import androidx.versionedparcelable.VersionedParcelable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class RemoteActionCompat implements VersionedParcelable {

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public PendingIntent mActionIntent;

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public CharSequence mContentDescription;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean mEnabled;

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public IconCompat mIcon;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean mShouldShowIcon;

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public CharSequence mTitle;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(26)
    public static class Api26Impl {
        public static RemoteAction createRemoteAction(Icon icon, CharSequence charSequence, CharSequence charSequence2, PendingIntent pendingIntent) {
            return new RemoteAction(icon, charSequence, charSequence2, pendingIntent);
        }

        public static PendingIntent getActionIntent(RemoteAction remoteAction) {
            return remoteAction.getActionIntent();
        }

        public static CharSequence getContentDescription(RemoteAction remoteAction) {
            return remoteAction.getContentDescription();
        }

        public static Icon getIcon(RemoteAction remoteAction) {
            return remoteAction.getIcon();
        }

        public static CharSequence getTitle(RemoteAction remoteAction) {
            return remoteAction.getTitle();
        }

        public static boolean isEnabled(RemoteAction remoteAction) {
            return remoteAction.isEnabled();
        }

        public static void setEnabled(RemoteAction remoteAction, boolean z) {
            remoteAction.setEnabled(z);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(28)
    public static class Api28Impl {
        public static void setShouldShowIcon(RemoteAction remoteAction, boolean z) {
            remoteAction.setShouldShowIcon(z);
        }

        public static boolean shouldShowIcon(RemoteAction remoteAction) {
            return remoteAction.shouldShowIcon();
        }
    }

    public RemoteActionCompat(@NonNull IconCompat iconCompat, @NonNull CharSequence charSequence, @NonNull CharSequence charSequence2, @NonNull PendingIntent pendingIntent) {
        iconCompat.getClass();
        this.mIcon = iconCompat;
        charSequence.getClass();
        this.mTitle = charSequence;
        charSequence2.getClass();
        this.mContentDescription = charSequence2;
        pendingIntent.getClass();
        this.mActionIntent = pendingIntent;
        this.mEnabled = true;
        this.mShouldShowIcon = true;
    }

    @NonNull
    @RequiresApi(26)
    public static RemoteActionCompat createFromRemoteAction(@NonNull RemoteAction remoteAction) {
        remoteAction.getClass();
        RemoteActionCompat remoteActionCompat = new RemoteActionCompat(IconCompat.createFromIcon(Api26Impl.getIcon(remoteAction)), Api26Impl.getTitle(remoteAction), Api26Impl.getContentDescription(remoteAction), Api26Impl.getActionIntent(remoteAction));
        remoteActionCompat.mEnabled = Api26Impl.isEnabled(remoteAction);
        if (Build.VERSION.SDK_INT >= 28) {
            remoteActionCompat.mShouldShowIcon = Api28Impl.shouldShowIcon(remoteAction);
        }
        return remoteActionCompat;
    }

    @NonNull
    public PendingIntent getActionIntent() {
        return this.mActionIntent;
    }

    @NonNull
    public CharSequence getContentDescription() {
        return this.mContentDescription;
    }

    @NonNull
    public IconCompat getIcon() {
        return this.mIcon;
    }

    @NonNull
    public CharSequence getTitle() {
        return this.mTitle;
    }

    public boolean isEnabled() {
        return this.mEnabled;
    }

    public void setEnabled(boolean z) {
        this.mEnabled = z;
    }

    public void setShouldShowIcon(boolean z) {
        this.mShouldShowIcon = z;
    }

    @SuppressLint({"KotlinPropertyAccess"})
    public boolean shouldShowIcon() {
        return this.mShouldShowIcon;
    }

    @NonNull
    @RequiresApi(26)
    public RemoteAction toRemoteAction() {
        RemoteAction remoteActionCreateRemoteAction = Api26Impl.createRemoteAction(this.mIcon.toIcon(), this.mTitle, this.mContentDescription, this.mActionIntent);
        Api26Impl.setEnabled(remoteActionCreateRemoteAction, this.mEnabled);
        if (Build.VERSION.SDK_INT >= 28) {
            Api28Impl.setShouldShowIcon(remoteActionCreateRemoteAction, this.mShouldShowIcon);
        }
        return remoteActionCreateRemoteAction;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public RemoteActionCompat() {
    }

    public RemoteActionCompat(@NonNull RemoteActionCompat remoteActionCompat) {
        remoteActionCompat.getClass();
        this.mIcon = remoteActionCompat.mIcon;
        this.mTitle = remoteActionCompat.mTitle;
        this.mContentDescription = remoteActionCompat.mContentDescription;
        this.mActionIntent = remoteActionCompat.mActionIntent;
        this.mEnabled = remoteActionCompat.mEnabled;
        this.mShouldShowIcon = remoteActionCompat.mShouldShowIcon;
    }
}
