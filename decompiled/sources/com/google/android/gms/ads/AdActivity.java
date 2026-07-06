package com.google.android.gms.ads;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.client.zzay;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzbrv;
import com.google.android.gms.internal.ads.zzbzt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@KeepForSdk
public final class AdActivity extends Activity {

    @NonNull
    @KeepForSdk
    public static final String CLASS_NAME = "com.google.android.gms.ads.AdActivity";

    @Nullable
    public zzbrv zza;

    @Override // android.app.Activity
    public final void onActivityResult(int i, int i2, @NonNull Intent intent) {
        try {
            zzbrv zzbrvVar = this.zza;
            if (zzbrvVar != null) {
                zzbrvVar.zzg(i, i2, intent);
            }
        } catch (Exception e) {
            zzbzt.zzl("#007 Could not call remote method.", e);
        }
        super.onActivityResult(i, i2, intent);
    }

    @Override // android.app.Activity
    public final void onBackPressed() {
        try {
            zzbrv zzbrvVar = this.zza;
            if (zzbrvVar != null) {
                if (!zzbrvVar.zzF()) {
                    return;
                }
            }
        } catch (RemoteException e) {
            zzbzt.zzl("#007 Could not call remote method.", e);
        }
        super.onBackPressed();
        try {
            zzbrv zzbrvVar2 = this.zza;
            if (zzbrvVar2 != null) {
                zzbrvVar2.zzh();
            }
        } catch (RemoteException e2) {
            zzbzt.zzl("#007 Could not call remote method.", e2);
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public final void onConfigurationChanged(@NonNull Configuration configuration) {
        super.onConfigurationChanged(configuration);
        try {
            zzbrv zzbrvVar = this.zza;
            if (zzbrvVar != null) {
                zzbrvVar.zzj(ObjectWrapper.wrap(configuration));
            }
        } catch (RemoteException e) {
            zzbzt.zzl("#007 Could not call remote method.", e);
        }
    }

    @Override // android.app.Activity
    public final void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        zzbrv zzbrvVarZzo = zzay.zza().zzo(this);
        this.zza = zzbrvVarZzo;
        if (zzbrvVarZzo == null) {
            zzbzt.zzl("#007 Could not call remote method.", null);
            finish();
            return;
        }
        try {
            zzbrvVarZzo.zzk(bundle);
        } catch (RemoteException e) {
            zzbzt.zzl("#007 Could not call remote method.", e);
            finish();
        }
    }

    @Override // android.app.Activity
    public final void onDestroy() {
        try {
            zzbrv zzbrvVar = this.zza;
            if (zzbrvVar != null) {
                zzbrvVar.zzl();
            }
        } catch (RemoteException e) {
            zzbzt.zzl("#007 Could not call remote method.", e);
        }
        super.onDestroy();
    }

    @Override // android.app.Activity
    public final void onPause() {
        try {
            zzbrv zzbrvVar = this.zza;
            if (zzbrvVar != null) {
                zzbrvVar.zzn();
            }
        } catch (RemoteException e) {
            zzbzt.zzl("#007 Could not call remote method.", e);
            finish();
        }
        super.onPause();
    }

    @Override // android.app.Activity
    public final void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        try {
            zzbrv zzbrvVar = this.zza;
            if (zzbrvVar != null) {
                zzbrvVar.zzo(i, strArr, iArr);
            }
        } catch (RemoteException e) {
            zzbzt.zzl("#007 Could not call remote method.", e);
        }
    }

    @Override // android.app.Activity
    public final void onRestart() {
        super.onRestart();
        try {
            zzbrv zzbrvVar = this.zza;
            if (zzbrvVar != null) {
                zzbrvVar.zzp();
            }
        } catch (RemoteException e) {
            zzbzt.zzl("#007 Could not call remote method.", e);
            finish();
        }
    }

    @Override // android.app.Activity
    public final void onResume() {
        super.onResume();
        try {
            zzbrv zzbrvVar = this.zza;
            if (zzbrvVar != null) {
                zzbrvVar.zzq();
            }
        } catch (RemoteException e) {
            zzbzt.zzl("#007 Could not call remote method.", e);
            finish();
        }
    }

    @Override // android.app.Activity
    public final void onSaveInstanceState(@NonNull Bundle bundle) {
        try {
            zzbrv zzbrvVar = this.zza;
            if (zzbrvVar != null) {
                zzbrvVar.zzr(bundle);
            }
        } catch (RemoteException e) {
            zzbzt.zzl("#007 Could not call remote method.", e);
            finish();
        }
        super.onSaveInstanceState(bundle);
    }

    @Override // android.app.Activity
    public final void onStart() {
        super.onStart();
        try {
            zzbrv zzbrvVar = this.zza;
            if (zzbrvVar != null) {
                zzbrvVar.zzs();
            }
        } catch (RemoteException e) {
            zzbzt.zzl("#007 Could not call remote method.", e);
            finish();
        }
    }

    @Override // android.app.Activity
    public final void onStop() {
        try {
            zzbrv zzbrvVar = this.zza;
            if (zzbrvVar != null) {
                zzbrvVar.zzt();
            }
        } catch (RemoteException e) {
            zzbzt.zzl("#007 Could not call remote method.", e);
            finish();
        }
        super.onStop();
    }

    @Override // android.app.Activity
    public final void onUserLeaveHint() {
        super.onUserLeaveHint();
        try {
            zzbrv zzbrvVar = this.zza;
            if (zzbrvVar != null) {
                zzbrvVar.zzu();
            }
        } catch (RemoteException e) {
            zzbzt.zzl("#007 Could not call remote method.", e);
        }
    }

    @Override // android.app.Activity
    public final void setContentView(int i) {
        super.setContentView(i);
        zza();
    }

    public final void zza() {
        zzbrv zzbrvVar = this.zza;
        if (zzbrvVar != null) {
            try {
                zzbrvVar.zzw();
            } catch (RemoteException e) {
                zzbzt.zzl("#007 Could not call remote method.", e);
            }
        }
    }

    @Override // android.app.Activity
    public final void setContentView(@NonNull View view) {
        super.setContentView(view);
        zza();
    }

    @Override // android.app.Activity
    public final void setContentView(@NonNull View view, @NonNull ViewGroup.LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
        zza();
    }
}
