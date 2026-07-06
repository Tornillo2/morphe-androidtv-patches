package com.google.android.gms.common.api.internal;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.collection.ArrayMap;
import androidx.collection.ArraySet;
import androidx.collection.ObjectListKt$$ExternalSyntheticOutline1;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.GmsClientSupervisor;
import com.google.android.gms.common.internal.MethodInvocation;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.RootTelemetryConfigManager;
import com.google.android.gms.common.internal.RootTelemetryConfiguration;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.TelemetryData;
import com.google.android.gms.common.internal.TelemetryLogging;
import com.google.android.gms.common.internal.TelemetryLoggingClient;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import org.checkerframework.checker.initialization.qual.NotOnlyInitialized;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@ShowFirstParty
@KeepForSdk
public class GoogleApiManager implements Handler.Callback {

    @NonNull
    public static final Status zaa = new Status(4, "Sign-out occurred while this API call was in progress.", null, null);
    public static final Status zab = new Status(4, "The user must be signed in to make this API call.", null, null);
    public static final Object zac = new Object();

    @Nullable
    @GuardedBy("lock")
    public static GoogleApiManager zad;

    @Nullable
    public TelemetryData zag;

    @Nullable
    public TelemetryLoggingClient zah;
    public final Context zai;
    public final GoogleApiAvailability zaj;
    public final com.google.android.gms.common.internal.zal zak;

    @NotOnlyInitialized
    public final Handler zar;
    public volatile boolean zas;
    public long zae = 10000;
    public boolean zaf = false;
    public final AtomicInteger zal = new AtomicInteger(1);
    public final AtomicInteger zam = new AtomicInteger(0);
    public final Map zan = new ConcurrentHashMap(5, 0.75f, 1);

    @Nullable
    @GuardedBy("lock")
    public zaae zao = null;

    @GuardedBy("lock")
    public final Set zap = new ArraySet();
    public final Set zaq = new ArraySet();

    @KeepForSdk
    public GoogleApiManager(Context context, Looper looper, GoogleApiAvailability googleApiAvailability) {
        this.zas = true;
        this.zai = context;
        com.google.android.gms.internal.base.zau zauVar = new com.google.android.gms.internal.base.zau(looper, this);
        this.zar = zauVar;
        this.zaj = googleApiAvailability;
        this.zak = new com.google.android.gms.common.internal.zal(googleApiAvailability);
        if (DeviceProperties.isAuto(context)) {
            this.zas = false;
        }
        zauVar.sendMessage(zauVar.obtainMessage(6));
    }

    @KeepForSdk
    public static void reportSignOut() {
        synchronized (zac) {
            try {
                GoogleApiManager googleApiManager = zad;
                if (googleApiManager != null) {
                    googleApiManager.zam.incrementAndGet();
                    Handler handler = googleApiManager.zar;
                    handler.sendMessageAtFrontOfQueue(handler.obtainMessage(10));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static Status zaF(ApiKey apiKey, ConnectionResult connectionResult) {
        return new Status(connectionResult, "API: " + apiKey.zab.zac + " is not available on this device. Connection failed with: " + String.valueOf(connectionResult));
    }

    @NonNull
    public static GoogleApiManager zaj() {
        GoogleApiManager googleApiManager;
        synchronized (zac) {
            Preconditions.checkNotNull(zad, "Must guarantee manager is non-null before using getInstance");
            googleApiManager = zad;
        }
        return googleApiManager;
    }

    @NonNull
    @ResultIgnorabilityUnspecified
    public static GoogleApiManager zak(@NonNull Context context) {
        GoogleApiManager googleApiManager;
        synchronized (zac) {
            try {
                if (zad == null) {
                    zad = new GoogleApiManager(context.getApplicationContext(), GmsClientSupervisor.getOrStartHandlerThread().getLooper(), GoogleApiAvailability.getInstance());
                }
                googleApiManager = zad;
            } catch (Throwable th) {
                throw th;
            }
        }
        return googleApiManager;
    }

    @Override // android.os.Handler.Callback
    @WorkerThread
    public final boolean handleMessage(@NonNull Message message) {
        zabq zabqVar;
        int i = message.what;
        switch (i) {
            case 1:
                this.zae = true == ((Boolean) message.obj).booleanValue() ? 10000L : 300000L;
                this.zar.removeMessages(12);
                for (ApiKey apiKey : this.zan.keySet()) {
                    Handler handler = this.zar;
                    handler.sendMessageDelayed(handler.obtainMessage(12, apiKey), this.zae);
                }
                return true;
            case 2:
                zal zalVar = (zal) message.obj;
                for (ApiKey apiKey2 : (ArrayMap.KeySet) zalVar.zaa.keySet()) {
                    zabq zabqVar2 = (zabq) this.zan.get(apiKey2);
                    if (zabqVar2 == null) {
                        zalVar.zac(apiKey2, new ConnectionResult(13), null);
                        return true;
                    }
                    if (zabqVar2.zac.isConnected()) {
                        zalVar.zac(apiKey2, ConnectionResult.RESULT_SUCCESS, zabqVar2.zac.getEndpointPackageName());
                    } else {
                        ConnectionResult connectionResultZad = zabqVar2.zad();
                        if (connectionResultZad != null) {
                            zalVar.zac(apiKey2, connectionResultZad, null);
                        } else {
                            zabqVar2.zat(zalVar);
                            zabqVar2.zao();
                        }
                    }
                }
                return true;
            case 3:
                for (zabq zabqVar3 : this.zan.values()) {
                    zabqVar3.zan();
                    zabqVar3.zao();
                }
                return true;
            case 4:
            case 8:
            case 13:
                zach zachVar = (zach) message.obj;
                zabq zabqVarZaG = (zabq) this.zan.get(zachVar.zac.zaf);
                if (zabqVarZaG == null) {
                    zabqVarZaG = zaG(zachVar.zac);
                }
                if (!zabqVarZaG.zac.requiresSignIn() || this.zam.get() == zachVar.zab) {
                    zabqVarZaG.zap(zachVar.zaa);
                    return true;
                }
                zachVar.zaa.zad(zaa);
                zabqVarZaG.zav();
                return true;
            case 5:
                int i2 = message.arg1;
                ConnectionResult connectionResult = (ConnectionResult) message.obj;
                Iterator it = this.zan.values().iterator();
                while (true) {
                    if (it.hasNext()) {
                        zabqVar = (zabq) it.next();
                        if (zabqVar.zah == i2) {
                        }
                    } else {
                        zabqVar = null;
                    }
                }
                if (zabqVar == null) {
                    Log.wtf("GoogleApiManager", ObjectListKt$$ExternalSyntheticOutline1.m("Could not find API instance ", i2, " while trying to fail enqueued calls."), new Exception());
                    return true;
                }
                int i3 = connectionResult.zzb;
                if (i3 != 13) {
                    zabqVar.zaE(zaF(zabqVar.zad, connectionResult));
                    return true;
                }
                this.zaj.getClass();
                zabqVar.zaE(new Status(17, "Error resolution was canceled by the user, original error message: " + ConnectionResult.zza(i3) + ": " + connectionResult.zzd, null, null));
                return true;
            case 6:
                if (this.zai.getApplicationContext() instanceof Application) {
                    BackgroundDetector.initialize((Application) this.zai.getApplicationContext());
                    BackgroundDetector backgroundDetector = BackgroundDetector.zza;
                    backgroundDetector.addListener(new zabl(this));
                    if (!backgroundDetector.readCurrentStateIfPossible(true)) {
                        this.zae = 300000L;
                        return true;
                    }
                }
                return true;
            case 7:
                zaG((GoogleApi) message.obj);
                return true;
            case 9:
                if (this.zan.containsKey(message.obj)) {
                    ((zabq) this.zan.get(message.obj)).zau();
                    return true;
                }
                return true;
            case 10:
                Iterator it2 = this.zaq.iterator();
                while (it2.hasNext()) {
                    zabq zabqVar4 = (zabq) this.zan.remove((ApiKey) it2.next());
                    if (zabqVar4 != null) {
                        zabqVar4.zav();
                    }
                }
                this.zaq.clear();
                return true;
            case 11:
                if (this.zan.containsKey(message.obj)) {
                    ((zabq) this.zan.get(message.obj)).zaw();
                    return true;
                }
                return true;
            case 12:
                if (this.zan.containsKey(message.obj)) {
                    ((zabq) this.zan.get(message.obj)).zaO(true);
                    return true;
                }
                return true;
            case 14:
                zaaf zaafVar = (zaaf) message.obj;
                ApiKey apiKey3 = zaafVar.zaa;
                if (this.zan.containsKey(apiKey3)) {
                    zaafVar.zab.setResult(Boolean.valueOf(((zabq) this.zan.get(apiKey3)).zaO(false)));
                    return true;
                }
                zaafVar.zab.setResult(Boolean.FALSE);
                return true;
            case 15:
                zabs zabsVar = (zabs) message.obj;
                if (this.zan.containsKey(zabsVar.zaa)) {
                    zabq.zal((zabq) this.zan.get(zabsVar.zaa), zabsVar);
                    return true;
                }
                return true;
            case 16:
                zabs zabsVar2 = (zabs) message.obj;
                if (this.zan.containsKey(zabsVar2.zaa)) {
                    zabq.zam((zabq) this.zan.get(zabsVar2.zaa), zabsVar2);
                    return true;
                }
                return true;
            case 17:
                zaI();
                return true;
            case 18:
                zace zaceVar = (zace) message.obj;
                if (zaceVar.zac == 0) {
                    zaH().log(new TelemetryData(zaceVar.zab, Arrays.asList(zaceVar.zaa)));
                    return true;
                }
                TelemetryData telemetryData = this.zag;
                if (telemetryData != null) {
                    List list = telemetryData.zab;
                    if (telemetryData.zaa != zaceVar.zab || (list != null && list.size() >= zaceVar.zad)) {
                        this.zar.removeMessages(17);
                        zaI();
                    } else {
                        this.zag.zac(zaceVar.zaa);
                    }
                }
                if (this.zag == null) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(zaceVar.zaa);
                    this.zag = new TelemetryData(zaceVar.zab, arrayList);
                    Handler handler2 = this.zar;
                    handler2.sendMessageDelayed(handler2.obtainMessage(17), zaceVar.zac);
                    return true;
                }
                return true;
            case 19:
                this.zaf = false;
                return true;
            default:
                Log.w("GoogleApiManager", "Unknown message id: " + i);
                return false;
        }
    }

    public final void zaA(@NonNull zaae zaaeVar) {
        synchronized (zac) {
            try {
                if (this.zao != zaaeVar) {
                    this.zao = zaaeVar;
                    this.zap.clear();
                }
                this.zap.addAll(zaaeVar.zad);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void zaB(@NonNull zaae zaaeVar) {
        synchronized (zac) {
            try {
                if (this.zao == zaaeVar) {
                    this.zao = null;
                    this.zap.clear();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @WorkerThread
    public final boolean zaD() {
        if (this.zaf) {
            return false;
        }
        RootTelemetryConfiguration rootTelemetryConfiguration = RootTelemetryConfigManager.getInstance().zzc;
        if (rootTelemetryConfiguration != null && !rootTelemetryConfiguration.getMethodInvocationTelemetryEnabled()) {
            return false;
        }
        int iZaa = this.zak.zaa(this.zai, 203400000);
        return iZaa == -1 || iZaa == 0;
    }

    @ResultIgnorabilityUnspecified
    public final boolean zaE(ConnectionResult connectionResult, int i) {
        return this.zaj.zah(this.zai, connectionResult, i);
    }

    @ResultIgnorabilityUnspecified
    @WorkerThread
    public final zabq zaG(GoogleApi googleApi) {
        Map map = this.zan;
        ApiKey apiKey = googleApi.zaf;
        zabq zabqVar = (zabq) map.get(apiKey);
        if (zabqVar == null) {
            zabqVar = new zabq(this, googleApi);
            this.zan.put(apiKey, zabqVar);
        }
        if (zabqVar.zac.requiresSignIn()) {
            this.zaq.add(apiKey);
        }
        zabqVar.zao();
        return zabqVar;
    }

    @WorkerThread
    public final TelemetryLoggingClient zaH() {
        if (this.zah == null) {
            this.zah = TelemetryLogging.getClient(this.zai);
        }
        return this.zah;
    }

    @WorkerThread
    public final void zaI() {
        TelemetryData telemetryData = this.zag;
        if (telemetryData != null) {
            if (telemetryData.zaa > 0 || zaD()) {
                zaH().log(telemetryData);
            }
            this.zag = null;
        }
    }

    public final void zaJ(TaskCompletionSource taskCompletionSource, int i, GoogleApi googleApi) {
        zacd zacdVarZaa;
        if (i == 0 || (zacdVarZaa = zacd.zaa(this, i, googleApi.zaf)) == null) {
            return;
        }
        Task task = taskCompletionSource.getTask();
        final Handler handler = this.zar;
        handler.getClass();
        task.addOnCompleteListener(new Executor() { // from class: com.google.android.gms.common.api.internal.zabk
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                handler.post(runnable);
            }
        }, zacdVarZaa);
    }

    public final int zaa() {
        return this.zal.getAndIncrement();
    }

    @Nullable
    public final zabq zai(ApiKey apiKey) {
        return (zabq) this.zan.get(apiKey);
    }

    @NonNull
    public final Task zam(@NonNull Iterable iterable) {
        zal zalVar = new zal(iterable);
        this.zar.sendMessage(this.zar.obtainMessage(2, zalVar));
        return zalVar.zac.getTask();
    }

    @NonNull
    @ResultIgnorabilityUnspecified
    public final Task zan(@NonNull GoogleApi googleApi) {
        zaaf zaafVar = new zaaf(googleApi.zaf);
        this.zar.sendMessage(this.zar.obtainMessage(14, zaafVar));
        return zaafVar.zab.getTask();
    }

    @NonNull
    public final Task zao(@NonNull GoogleApi googleApi, @NonNull RegisterListenerMethod registerListenerMethod, @NonNull UnregisterListenerMethod unregisterListenerMethod, @NonNull Runnable runnable) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        zaJ(taskCompletionSource, registerListenerMethod.zad, googleApi);
        this.zar.sendMessage(this.zar.obtainMessage(8, new zach(new zaf(new zaci(registerListenerMethod, unregisterListenerMethod, runnable), taskCompletionSource), this.zam.get(), googleApi)));
        return taskCompletionSource.zza;
    }

    @NonNull
    public final Task zap(@NonNull GoogleApi googleApi, @NonNull ListenerHolder.ListenerKey listenerKey, int i) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        zaJ(taskCompletionSource, i, googleApi);
        this.zar.sendMessage(this.zar.obtainMessage(13, new zach(new zah(listenerKey, taskCompletionSource), this.zam.get(), googleApi)));
        return taskCompletionSource.zza;
    }

    public final void zau(@NonNull GoogleApi googleApi, int i, @NonNull BaseImplementation.ApiMethodImpl apiMethodImpl) {
        this.zar.sendMessage(this.zar.obtainMessage(4, new zach(new zae(i, apiMethodImpl), this.zam.get(), googleApi)));
    }

    public final void zav(@NonNull GoogleApi googleApi, int i, @NonNull TaskApiCall taskApiCall, @NonNull TaskCompletionSource taskCompletionSource, @NonNull StatusExceptionMapper statusExceptionMapper) {
        zaJ(taskCompletionSource, taskApiCall.zac, googleApi);
        this.zar.sendMessage(this.zar.obtainMessage(4, new zach(new zag(i, taskApiCall, taskCompletionSource, statusExceptionMapper), this.zam.get(), googleApi)));
    }

    public final void zaw(MethodInvocation methodInvocation, int i, long j, int i2) {
        this.zar.sendMessage(this.zar.obtainMessage(18, new zace(methodInvocation, i, j, i2)));
    }

    public final void zax(@NonNull ConnectionResult connectionResult, int i) {
        if (zaE(connectionResult, i)) {
            return;
        }
        Handler handler = this.zar;
        handler.sendMessage(handler.obtainMessage(5, i, 0, connectionResult));
    }

    public final void zay() {
        Handler handler = this.zar;
        handler.sendMessage(handler.obtainMessage(3));
    }

    public final void zaz(@NonNull GoogleApi googleApi) {
        Handler handler = this.zar;
        handler.sendMessage(handler.obtainMessage(7, googleApi));
    }
}
