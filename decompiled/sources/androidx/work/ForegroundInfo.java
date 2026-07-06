package androidx.work;

import android.app.Notification;
import androidx.annotation.NonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class ForegroundInfo {
    public final int mForegroundServiceType;
    public final Notification mNotification;
    public final int mNotificationId;

    public ForegroundInfo(int notificationId, @NonNull Notification notification) {
        this(notificationId, notification, 0);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || ForegroundInfo.class != o.getClass()) {
            return false;
        }
        ForegroundInfo foregroundInfo = (ForegroundInfo) o;
        if (this.mNotificationId == foregroundInfo.mNotificationId && this.mForegroundServiceType == foregroundInfo.mForegroundServiceType) {
            return this.mNotification.equals(foregroundInfo.mNotification);
        }
        return false;
    }

    public int getForegroundServiceType() {
        return this.mForegroundServiceType;
    }

    @NonNull
    public Notification getNotification() {
        return this.mNotification;
    }

    public int getNotificationId() {
        return this.mNotificationId;
    }

    public int hashCode() {
        return this.mNotification.hashCode() + (((this.mNotificationId * 31) + this.mForegroundServiceType) * 31);
    }

    public String toString() {
        return "ForegroundInfo{mNotificationId=" + this.mNotificationId + ", mForegroundServiceType=" + this.mForegroundServiceType + ", mNotification=" + this.mNotification + '}';
    }

    public ForegroundInfo(int notificationId, @NonNull Notification notification, int foregroundServiceType) {
        this.mNotificationId = notificationId;
        this.mNotification = notification;
        this.mForegroundServiceType = foregroundServiceType;
    }
}
