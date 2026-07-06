package com.amazon.livingroom.mediapipelinebackend;

import android.media.DeniedByServerException;
import android.media.MediaDrm;
import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$$ExternalSyntheticOutline0;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.avod.mpb.media.drm.MediaDrmProvisioner;
import com.amazon.livingroom.di.ApplicationScope;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.TimeoutError;
import com.android.volley.toolbox.RequestFuture;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.inject.Inject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public class DrmProvisioner implements MediaDrmProvisioner {
    public static final float BACKOFF_MULT = 2.0f;
    public static final int INITIAL_TIMEOUT_MS = 5000;
    public static final int MAX_NUM_HTTP_RETRIES = 2;
    public final RequestQueue requestQueue;

    @Inject
    public DrmProvisioner(@NonNull RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
    }

    public final void attemptProvisioning(MediaDrm mediaDrm) {
        try {
            mediaDrm.provideProvisionResponse(getProvisionResponse(requestProvisioning(buildRequestUrl(mediaDrm))));
        } catch (DeniedByServerException e) {
            throw new DrmProvisioningException(24, "Provision request denied by server", e);
        }
    }

    public final String buildRequestUrl(MediaDrm mediaDrm) {
        MediaDrm.ProvisionRequest provisionRequest = mediaDrm.getProvisionRequest();
        String defaultUrl = provisionRequest.getDefaultUrl();
        if (TextUtils.isEmpty(defaultUrl)) {
            throw new DrmProvisioningException(21, "Got empty URL from ProvisionRequest", null);
        }
        StringBuilder sbM = MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$$ExternalSyntheticOutline0.m(defaultUrl, "&signedRequest=");
        sbM.append(new String(provisionRequest.getData(), StandardCharsets.UTF_8));
        return sbM.toString();
    }

    public final byte[] getProvisionResponse(Future<byte[]> future) {
        try {
            return future.get();
        } catch (InterruptedException e) {
            throw new DrmProvisioningException(23, "Provision network request interrupted", e);
        } catch (ExecutionException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof TimeoutError) {
                throw new DrmProvisioningException(25, "Provision network request timed out", cause);
            }
            throw new DrmProvisioningException(22, "Provision network request failed", cause);
        }
    }

    @Override // com.amazon.avod.mpb.media.drm.MediaDrmProvisioner
    public void provision(@NonNull MediaDrm mediaDrm) {
        DrmProvisioningException drmProvisioningException = null;
        DrmProvisioningException e = null;
        int i = 0;
        while (true) {
            if (i >= 3) {
                break;
            }
            try {
                attemptProvisioning(mediaDrm);
                break;
            } catch (DrmProvisioningException e2) {
                e = e2;
                if (e.getErrorCode() == 25) {
                    MpbLog.w(String.format(Locale.US, "Failed DRM provisioning attempt %d of %d. Giving up due to multiple HTTP request timeouts.", Integer.valueOf(i), 3), e);
                    drmProvisioningException = e;
                } else {
                    MpbLog.w(String.format(Locale.US, "Failed DRM provisioning attempt %d of %d. Retrying...", Integer.valueOf(i), 3), e);
                    i++;
                }
            }
        }
        drmProvisioningException = e;
        if (drmProvisioningException != null) {
            throw drmProvisioningException;
        }
    }

    public final Future<byte[]> requestProvisioning(String str) {
        RequestFuture requestFuture = new RequestFuture();
        ByteArrayRequest byteArrayRequest = new ByteArrayRequest(1, str, requestFuture, requestFuture);
        byteArrayRequest.mRetryPolicy = new DefaultRetryPolicy(5000, 2, 2.0f);
        requestFuture.mRequest = this.requestQueue.add(byteArrayRequest);
        return requestFuture;
    }
}
