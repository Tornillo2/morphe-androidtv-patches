package com.google.android.gms.internal.consent_sdk;

import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import androidx.annotation.UiThread;
import java.io.ByteArrayOutputStream;
import java.util.Locale;
import java.util.concurrent.Executor;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzbm implements zzg {
    public final Application zza;
    public final zzbi zzb;
    public final Handler zzc;
    public final Executor zzd;
    public final zzh zze;
    public final zzak zzf;
    public final zzay zzg;
    public final zzam zzh;

    public zzbm(Application application, zzbi zzbiVar, Handler handler, Executor executor, zzh zzhVar, zzak zzakVar, zzay zzayVar, zzam zzamVar) {
        this.zza = application;
        this.zzb = zzbiVar;
        this.zzc = handler;
        this.zzd = executor;
        this.zze = zzhVar;
        this.zzf = zzakVar;
        this.zzg = zzayVar;
        this.zzh = zzamVar;
    }

    @Override // com.google.android.gms.internal.consent_sdk.zzg
    public final Executor zza() {
        final Handler handler = this.zzc;
        return new Executor() { // from class: com.google.android.gms.internal.consent_sdk.zzbl
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                handler.post(runnable);
            }
        };
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:24:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0053  */
    @Override // com.google.android.gms.internal.consent_sdk.zzg
    @androidx.annotation.UiThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean zzb(java.lang.String r3, org.json.JSONObject r4) {
        /*
            r2 = this;
            int r0 = r3.hashCode()
            r1 = 1
            switch(r0) {
                case -1370505102: goto L7f;
                case -278739366: goto L6c;
                case 150940456: goto L60;
                case 1671672458: goto La;
                default: goto L8;
            }
        L8:
            goto L8d
        La:
            java.lang.String r0 = "dismiss"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L8d
            java.lang.String r3 = "status"
            java.lang.String r3 = r4.optString(r3)
            int r4 = r3.hashCode()
            switch(r4) {
                case -954325659: goto L44;
                case -258041904: goto L3b;
                case 429411856: goto L32;
                case 467888915: goto L29;
                case 1666911234: goto L20;
                default: goto L1f;
            }
        L1f:
            goto L53
        L20:
            java.lang.String r4 = "non_personalized"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L53
            goto L4c
        L29:
            java.lang.String r4 = "CONSENT_SIGNAL_PERSONALIZED_ADS"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L53
            goto L4c
        L32:
            java.lang.String r4 = "CONSENT_SIGNAL_SUFFICIENT"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L53
            goto L4c
        L3b:
            java.lang.String r4 = "personalized"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L53
            goto L4c
        L44:
            java.lang.String r4 = "CONSENT_SIGNAL_NON_PERSONALIZED_ADS"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L53
        L4c:
            com.google.android.gms.internal.consent_sdk.zzay r3 = r2.zzg
            r4 = 3
            r3.zzc(r4)
            goto L5f
        L53:
            com.google.android.gms.internal.consent_sdk.zzay r3 = r2.zzg
            com.google.android.gms.internal.consent_sdk.zzj r4 = new com.google.android.gms.internal.consent_sdk.zzj
            java.lang.String r0 = "We are getting something wrong with the webview."
            r4.<init>(r1, r0)
            r3.zzd(r4)
        L5f:
            return r1
        L60:
            java.lang.String r0 = "browser"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L8d
            r2.zzf(r4)
            return r1
        L6c:
            java.lang.String r4 = "configure_app_assets"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L8d
            java.util.concurrent.Executor r3 = r2.zzd
            com.google.android.gms.internal.consent_sdk.zzbk r4 = new com.google.android.gms.internal.consent_sdk.zzbk
            r4.<init>()
            r3.execute(r4)
            return r1
        L7f:
            java.lang.String r4 = "load_complete"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L8d
            com.google.android.gms.internal.consent_sdk.zzay r3 = r2.zzg
            r3.zze()
            return r1
        L8d:
            r3 = 0
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.consent_sdk.zzbm.zzb(java.lang.String, org.json.JSONObject):boolean");
    }

    public final void zzc() {
        String strConcat;
        Application application = this.zza;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("app_name", application.getPackageManager().getApplicationLabel(application.getApplicationInfo()).toString());
            Drawable applicationIcon = application.getPackageManager().getApplicationIcon(application.getApplicationInfo());
            if (applicationIcon == null) {
                strConcat = null;
            } else {
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(applicationIcon.getIntrinsicWidth(), applicationIcon.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmapCreateBitmap);
                applicationIcon.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                applicationIcon.draw(canvas);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmapCreateBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                String strValueOf = String.valueOf(Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2));
                strConcat = strValueOf.length() != 0 ? "data:image/png;base64,".concat(strValueOf) : new String("data:image/png;base64,");
            }
            jSONObject.put("app_icon", strConcat);
            jSONObject.put("stored_infos_map", this.zzh.zzb());
        } catch (JSONException unused) {
        }
        this.zzg.zzh.zzb("UMP_configureFormWithAppAssets", jSONObject.toString());
    }

    public final void zzd(String str) {
        String strValueOf = String.valueOf(str);
        Log.d("UserMessagingPlatform", strValueOf.length() != 0 ? "Receive consent action: ".concat(strValueOf) : new String("Receive consent action: "));
        Uri uri = Uri.parse(str);
        this.zze.zzb(uri.getQueryParameter("action"), uri.getQueryParameter("args"), this, this.zzf);
    }

    public final void zze(int i, String str, String str2) {
        this.zzg.zzf(new zzj(2, String.format(Locale.US, "WebResourceError(%d, %s): %s", Integer.valueOf(i), str2, str)));
    }

    @UiThread
    public final void zzf(JSONObject jSONObject) {
        String strOptString = jSONObject.optString("url");
        if (TextUtils.isEmpty(strOptString)) {
            Log.d("UserMessagingPlatform", "Action[browser]: empty url.");
        }
        Uri uri = Uri.parse(strOptString);
        if (uri.getScheme() == null) {
            String strValueOf = String.valueOf(strOptString);
            Log.d("UserMessagingPlatform", strValueOf.length() != 0 ? "Action[browser]: empty scheme: ".concat(strValueOf) : new String("Action[browser]: empty scheme: "));
        }
        try {
            this.zzb.startActivity(new Intent("android.intent.action.VIEW", uri));
        } catch (ActivityNotFoundException e) {
            String strValueOf2 = String.valueOf(strOptString);
            Log.d("UserMessagingPlatform", strValueOf2.length() != 0 ? "Action[browser]: can not open url: ".concat(strValueOf2) : new String("Action[browser]: can not open url: "), e);
        }
    }
}
