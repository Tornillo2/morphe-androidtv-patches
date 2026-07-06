package com.google.android.gms.common.images;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.SystemClock;
import androidx.annotation.Nullable;
import com.google.android.gms.common.images.ImageManager;
import com.google.android.gms.common.internal.Asserts;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zac implements Runnable {
    public final /* synthetic */ ImageManager zaa;
    public final Uri zab;

    @Nullable
    public final Bitmap zac;
    public final CountDownLatch zad;

    public zac(ImageManager imageManager, @Nullable Uri uri, Bitmap bitmap, boolean z, CountDownLatch countDownLatch) {
        this.zaa = imageManager;
        this.zab = uri;
        this.zac = bitmap;
        this.zad = countDownLatch;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Asserts.checkMainThread("OnBitmapLoadedRunnable must be executed in the main thread");
        ImageManager.ImageReceiver imageReceiver = (ImageManager.ImageReceiver) this.zaa.zai.remove(this.zab);
        if (imageReceiver != null) {
            ArrayList arrayList = imageReceiver.zac;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                zag zagVar = (zag) arrayList.get(i);
                Bitmap bitmap = this.zac;
                if (bitmap != null) {
                    zagVar.zac(this.zaa.zad, bitmap, false);
                } else {
                    ImageManager imageManager = this.zaa;
                    imageManager.zaj.put(this.zab, Long.valueOf(SystemClock.elapsedRealtime()));
                    ImageManager imageManager2 = this.zaa;
                    zagVar.zab(imageManager2.zad, imageManager2.zag, false);
                }
                if (!(zagVar instanceof zaf)) {
                    this.zaa.zah.remove(zagVar);
                }
            }
        }
        this.zad.countDown();
        synchronized (ImageManager.zaa) {
            ImageManager.zab.remove(this.zab);
        }
    }
}
