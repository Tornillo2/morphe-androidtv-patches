package com.google.android.exoplayer2.mediacodec;

import android.content.Context;
import com.google.android.exoplayer2.mediacodec.AsynchronousMediaCodecAdapter;
import com.google.android.exoplayer2.mediacodec.MediaCodecAdapter;
import com.google.android.exoplayer2.mediacodec.SynchronousMediaCodecAdapter;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class DefaultMediaCodecAdapterFactory implements MediaCodecAdapter.Factory {
    public static final int MODE_DEFAULT = 0;
    public static final int MODE_DISABLED = 2;
    public static final int MODE_ENABLED = 1;
    public static final String TAG = "DMCodecAdapterFactory";
    public int asynchronousMode;
    public final Context context;
    public boolean enableSynchronizeCodecInteractionsWithQueueing;

    public DefaultMediaCodecAdapterFactory() {
        this(null);
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecAdapter.Factory
    public MediaCodecAdapter createAdapter(MediaCodecAdapter.Configuration configuration) throws IOException {
        int i;
        Context context = this.context;
        boolean zHasSystemFeature = context != null ? context.getPackageManager().hasSystemFeature("com.amazon.hardware.tv_screen") : false;
        int i2 = Util.SDK_INT;
        if (i2 < 23 || ((i = this.asynchronousMode) != 1 && ((i != 0 || i2 < 31) && !(i == 0 && zHasSystemFeature && i2 >= 28)))) {
            return new SynchronousMediaCodecAdapter.Factory().createAdapter(configuration);
        }
        int trackType = MimeTypes.getTrackType(configuration.format.sampleMimeType);
        Log.i("DMCodecAdapterFactory", "Creating an asynchronous MediaCodec adapter for track type " + Util.getTrackTypeString(trackType));
        return new AsynchronousMediaCodecAdapter.Factory(trackType, this.enableSynchronizeCodecInteractionsWithQueueing).createAdapter(configuration);
    }

    public void experimentalSetSynchronizeCodecInteractionsWithQueueingEnabled(boolean z) {
        this.enableSynchronizeCodecInteractionsWithQueueing = z;
    }

    @CanIgnoreReturnValue
    public DefaultMediaCodecAdapterFactory forceDisableAsynchronous() {
        this.asynchronousMode = 2;
        return this;
    }

    @CanIgnoreReturnValue
    public DefaultMediaCodecAdapterFactory forceEnableAsynchronous() {
        this.asynchronousMode = 1;
        return this;
    }

    public DefaultMediaCodecAdapterFactory(Context context) {
        this.context = context;
        this.asynchronousMode = 0;
    }
}
