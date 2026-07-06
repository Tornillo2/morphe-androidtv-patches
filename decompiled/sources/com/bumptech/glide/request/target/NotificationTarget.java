package com.bumptech.glide.request.target;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.RemoteViews;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.util.Preconditions;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class NotificationTarget extends CustomTarget<Bitmap> {
    public final Context context;
    public final Notification notification;
    public final int notificationId;
    public final String notificationTag;
    public final RemoteViews remoteViews;
    public final int viewId;

    public NotificationTarget(Context context, int i, RemoteViews remoteViews, Notification notification, int i2) {
        this(context, i, remoteViews, notification, i2, null);
    }

    private void setBitmap(@Nullable Bitmap bitmap) {
        this.remoteViews.setImageViewBitmap(this.viewId, bitmap);
        update();
    }

    private void update() {
        NotificationManager notificationManager = (NotificationManager) this.context.getSystemService("notification");
        Preconditions.checkNotNull(notificationManager, "Argument must not be null");
        notificationManager.notify(this.notificationTag, this.notificationId, this.notification);
    }

    @Override // com.bumptech.glide.request.target.Target
    public void onLoadCleared(@Nullable Drawable drawable) {
        setBitmap(null);
    }

    @Override // com.bumptech.glide.request.target.Target
    public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
        onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
    }

    public NotificationTarget(Context context, int i, RemoteViews remoteViews, Notification notification, int i2, String str) {
        this(context, Integer.MIN_VALUE, Integer.MIN_VALUE, i, remoteViews, notification, i2, str);
    }

    public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
        setBitmap(bitmap);
    }

    public NotificationTarget(Context context, int i, int i2, int i3, RemoteViews remoteViews, Notification notification, int i4, String str) {
        super(i, i2);
        Preconditions.checkNotNull(context, "Context must not be null!");
        this.context = context;
        Preconditions.checkNotNull(notification, "Notification object can not be null!");
        this.notification = notification;
        Preconditions.checkNotNull(remoteViews, "RemoteViews object can not be null!");
        this.remoteViews = remoteViews;
        this.viewId = i3;
        this.notificationId = i4;
        this.notificationTag = str;
    }
}
