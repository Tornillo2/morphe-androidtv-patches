package com.google.android.gms.common;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.DialogInterface;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zac implements DialogInterface.OnClickListener {
    public final /* synthetic */ Activity zaa;
    public final /* synthetic */ int zab;
    public final /* synthetic */ ActivityResultLauncher zac;
    public final /* synthetic */ GoogleApiAvailability zad;

    public zac(GoogleApiAvailability googleApiAvailability, Activity activity, int i, ActivityResultLauncher activityResultLauncher) {
        this.zad = googleApiAvailability;
        this.zaa = activity;
        this.zab = i;
        this.zac = activityResultLauncher;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        PendingIntent errorResolutionPendingIntent = this.zad.getErrorResolutionPendingIntent(this.zaa, this.zab, 0);
        if (errorResolutionPendingIntent == null) {
            return;
        }
        this.zac.launch(new IntentSenderRequest.Builder(errorResolutionPendingIntent.getIntentSender()).build());
    }
}
