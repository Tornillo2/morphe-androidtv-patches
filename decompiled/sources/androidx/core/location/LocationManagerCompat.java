package androidx.core.location;

import android.annotation.SuppressLint;
import android.location.GnssMeasurementsEvent;
import android.location.GnssStatus;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationRequest;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;
import androidx.collection.SimpleArrayMap;
import androidx.core.location.GnssStatusCompat;
import androidx.core.location.LocationManagerCompat;
import androidx.core.location.LocationRequestCompat;
import androidx.core.os.ExecutorCompat;
import androidx.core.util.Consumer;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Preconditions;
import j$.util.Objects;
import j$.util.function.Consumer$CC;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class LocationManagerCompat {
    public static final long GET_CURRENT_LOCATION_TIMEOUT_MS = 30000;
    public static final long MAX_CURRENT_LOCATION_AGE_MS = 10000;
    public static final long PRE_N_LOOPER_TIMEOUT_S = 5;
    public static Field sContextField;
    public static Method sGnssRequestBuilderBuildMethod;
    public static Class<?> sGnssRequestBuilderClass;

    @GuardedBy("sLocationListeners")
    public static final WeakHashMap<LocationListenerKey, WeakReference<LocationListenerTransport>> sLocationListeners = new WeakHashMap<>();
    public static Method sRegisterGnssMeasurementsCallbackMethod;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(24)
    public static class Api24Impl {
        @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
        public static boolean registerGnssMeasurementsCallback(@NonNull LocationManager locationManager, @NonNull GnssMeasurementsEvent.Callback callback) {
            return locationManager.registerGnssMeasurementsCallback(callback);
        }

        @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
        public static boolean registerGnssStatusCallback(LocationManager locationManager, Handler handler, Executor executor, GnssStatusCompat.Callback callback) {
            Preconditions.checkArgument(handler != null);
            SimpleArrayMap<Object, Object> simpleArrayMap = GnssListenersHolder.sGnssStatusListeners;
            synchronized (simpleArrayMap) {
                try {
                    PreRGnssStatusTransport preRGnssStatusTransport = (PreRGnssStatusTransport) simpleArrayMap.get(callback);
                    if (preRGnssStatusTransport == null) {
                        preRGnssStatusTransport = new PreRGnssStatusTransport(callback);
                    } else {
                        preRGnssStatusTransport.unregister();
                    }
                    preRGnssStatusTransport.register(executor);
                    if (!locationManager.registerGnssStatusCallback(preRGnssStatusTransport, handler)) {
                        return false;
                    }
                    simpleArrayMap.put(callback, preRGnssStatusTransport);
                    return true;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public static void unregisterGnssMeasurementsCallback(@NonNull LocationManager locationManager, @NonNull GnssMeasurementsEvent.Callback callback) {
            locationManager.unregisterGnssMeasurementsCallback(callback);
        }

        public static void unregisterGnssStatusCallback(LocationManager locationManager, Object obj) {
            if (obj instanceof PreRGnssStatusTransport) {
                ((PreRGnssStatusTransport) obj).unregister();
            }
            locationManager.unregisterGnssStatusCallback((GnssStatus.Callback) obj);
        }

        @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
        public static boolean registerGnssMeasurementsCallback(@NonNull LocationManager locationManager, @NonNull GnssMeasurementsEvent.Callback callback, @NonNull Handler handler) {
            return locationManager.registerGnssMeasurementsCallback(callback, handler);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(28)
    public static class Api28Impl {
        public static String getGnssHardwareModelName(LocationManager locationManager) {
            return locationManager.getGnssHardwareModelName();
        }

        public static int getGnssYearOfHardware(LocationManager locationManager) {
            return locationManager.getGnssYearOfHardware();
        }

        public static boolean isLocationEnabled(LocationManager locationManager) {
            return locationManager.isLocationEnabled();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(30)
    public static class Api30Impl {
        public static Class<?> sLocationRequestClass;
        public static Method sRequestLocationUpdatesExecutorMethod;

        @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
        public static void getCurrentLocation(LocationManager locationManager, @NonNull String str, @Nullable CancellationSignal cancellationSignal, @NonNull Executor executor, @NonNull final Consumer<Location> consumer) {
            Objects.requireNonNull(consumer);
            locationManager.getCurrentLocation(str, cancellationSignal, executor, new java.util.function.Consumer() { // from class: androidx.core.location.LocationManagerCompat$Api30Impl$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                /* JADX INFO: renamed from: accept */
                public final void n(Object obj) {
                    consumer.accept((Location) obj);
                }

                public /* synthetic */ java.util.function.Consumer andThen(java.util.function.Consumer consumer2) {
                    return Consumer$CC.$default$andThen(this, consumer2);
                }
            });
        }

        @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
        public static boolean registerGnssStatusCallback(LocationManager locationManager, Handler handler, Executor executor, GnssStatusCompat.Callback callback) {
            SimpleArrayMap<Object, Object> simpleArrayMap = GnssListenersHolder.sGnssStatusListeners;
            synchronized (simpleArrayMap) {
                try {
                    GnssStatusTransport gnssStatusTransport = (GnssStatusTransport) simpleArrayMap.get(callback);
                    if (gnssStatusTransport == null) {
                        gnssStatusTransport = new GnssStatusTransport(callback);
                    }
                    if (!locationManager.registerGnssStatusCallback(executor, gnssStatusTransport)) {
                        return false;
                    }
                    simpleArrayMap.put(callback, gnssStatusTransport);
                    return true;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public static boolean tryRequestLocationUpdates(LocationManager locationManager, String str, LocationRequestCompat locationRequestCompat, Executor executor, LocationListenerCompat locationListenerCompat) {
            if (Build.VERSION.SDK_INT >= 30) {
                try {
                    if (sLocationRequestClass == null) {
                        sLocationRequestClass = Class.forName("android.location.LocationRequest");
                    }
                    if (sRequestLocationUpdatesExecutorMethod == null) {
                        Method declaredMethod = LocationManager.class.getDeclaredMethod("requestLocationUpdates", sLocationRequestClass, Executor.class, LocationListener.class);
                        sRequestLocationUpdatesExecutorMethod = declaredMethod;
                        declaredMethod.setAccessible(true);
                    }
                    LocationRequest locationRequest = locationRequestCompat.toLocationRequest(str);
                    if (locationRequest != null) {
                        sRequestLocationUpdatesExecutorMethod.invoke(locationManager, locationRequest, executor, locationListenerCompat);
                        return true;
                    }
                } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | UnsupportedOperationException | InvocationTargetException unused) {
                }
            }
            return false;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(31)
    public static class Api31Impl {
        public static boolean hasProvider(LocationManager locationManager, @NonNull String str) {
            return locationManager.hasProvider(str);
        }

        @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
        public static boolean registerGnssMeasurementsCallback(@NonNull LocationManager locationManager, @NonNull Executor executor, @NonNull GnssMeasurementsEvent.Callback callback) {
            return locationManager.registerGnssMeasurementsCallback(executor, callback);
        }

        @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
        public static void requestLocationUpdates(LocationManager locationManager, @NonNull String str, @NonNull LocationRequest locationRequest, @NonNull Executor executor, @NonNull LocationListener locationListener) {
            locationManager.requestLocationUpdates(str, locationRequest, executor, locationListener);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class GnssListenersHolder {

        @GuardedBy("sGnssStatusListeners")
        public static final SimpleArrayMap<Object, Object> sGnssStatusListeners = new SimpleArrayMap<>();

        @GuardedBy("sGnssMeasurementListeners")
        public static final SimpleArrayMap<GnssMeasurementsEvent.Callback, GnssMeasurementsEvent.Callback> sGnssMeasurementListeners = new SimpleArrayMap<>();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(24)
    public static class GnssMeasurementsTransport extends GnssMeasurementsEvent.Callback {
        public final GnssMeasurementsEvent.Callback mCallback;

        @Nullable
        public volatile Executor mExecutor;

        public static /* synthetic */ void $r8$lambda$O1POJg4UhiN5rDPKafQ5LlIm__0(GnssMeasurementsTransport gnssMeasurementsTransport, Executor executor, GnssMeasurementsEvent gnssMeasurementsEvent) {
            if (gnssMeasurementsTransport.mExecutor != executor) {
                return;
            }
            gnssMeasurementsTransport.mCallback.onGnssMeasurementsReceived(gnssMeasurementsEvent);
        }

        /* JADX INFO: renamed from: $r8$lambda$arrI_uUq-zmww3HQvwSyrTI1xDI, reason: not valid java name */
        public static /* synthetic */ void m48$r8$lambda$arrI_uUqzmww3HQvwSyrTI1xDI(GnssMeasurementsTransport gnssMeasurementsTransport, Executor executor, int i) {
            if (gnssMeasurementsTransport.mExecutor != executor) {
                return;
            }
            gnssMeasurementsTransport.mCallback.onStatusChanged(i);
        }

        public GnssMeasurementsTransport(@NonNull GnssMeasurementsEvent.Callback callback, @NonNull Executor executor) {
            this.mCallback = callback;
            this.mExecutor = executor;
        }

        @Override // android.location.GnssMeasurementsEvent.Callback
        public void onGnssMeasurementsReceived(final GnssMeasurementsEvent gnssMeasurementsEvent) {
            final Executor executor = this.mExecutor;
            if (executor == null) {
                return;
            }
            executor.execute(new Runnable() { // from class: androidx.core.location.LocationManagerCompat$GnssMeasurementsTransport$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    LocationManagerCompat.GnssMeasurementsTransport.$r8$lambda$O1POJg4UhiN5rDPKafQ5LlIm__0(this.f$0, executor, gnssMeasurementsEvent);
                }
            });
        }

        @Override // android.location.GnssMeasurementsEvent.Callback
        public void onStatusChanged(final int i) {
            final Executor executor = this.mExecutor;
            if (executor == null) {
                return;
            }
            executor.execute(new Runnable() { // from class: androidx.core.location.LocationManagerCompat$GnssMeasurementsTransport$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    LocationManagerCompat.GnssMeasurementsTransport.m48$r8$lambda$arrI_uUqzmww3HQvwSyrTI1xDI(this.f$0, executor, i);
                }
            });
        }

        public void unregister() {
            this.mExecutor = null;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(30)
    public static class GnssStatusTransport extends GnssStatus.Callback {
        public final GnssStatusCompat.Callback mCallback;

        public GnssStatusTransport(GnssStatusCompat.Callback callback) {
            Preconditions.checkArgument(callback != null, "invalid null callback");
            this.mCallback = callback;
        }

        @Override // android.location.GnssStatus.Callback
        public void onFirstFix(int i) {
            this.mCallback.getClass();
        }

        @Override // android.location.GnssStatus.Callback
        public void onSatelliteStatusChanged(GnssStatus gnssStatus) {
            GnssStatusCompat.Callback callback = this.mCallback;
            new GnssStatusWrapper(gnssStatus);
            callback.getClass();
        }

        @Override // android.location.GnssStatus.Callback
        public void onStarted() {
            this.mCallback.getClass();
        }

        @Override // android.location.GnssStatus.Callback
        public void onStopped() {
            this.mCallback.getClass();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class GpsStatusTransport implements GpsStatus.Listener {
        public final GnssStatusCompat.Callback mCallback;

        @Nullable
        public volatile Executor mExecutor;
        public final LocationManager mLocationManager;

        public static /* synthetic */ void $r8$lambda$MriBUDqOAPnaKwESE9x_VRF4goI(GpsStatusTransport gpsStatusTransport, Executor executor) {
            if (gpsStatusTransport.mExecutor != executor) {
                return;
            }
            gpsStatusTransport.mCallback.getClass();
        }

        public static /* synthetic */ void $r8$lambda$b64kzWcVdI4q0UXJNvnbRXcPQAY(GpsStatusTransport gpsStatusTransport, Executor executor) {
            if (gpsStatusTransport.mExecutor != executor) {
                return;
            }
            gpsStatusTransport.mCallback.getClass();
        }

        public static /* synthetic */ void $r8$lambda$h9CWj82BwleGjmVrzCcuKSbTYwQ(GpsStatusTransport gpsStatusTransport, Executor executor, int i) {
            if (gpsStatusTransport.mExecutor != executor) {
                return;
            }
            gpsStatusTransport.mCallback.getClass();
        }

        public static /* synthetic */ void $r8$lambda$t0AVwzXoM489wx6RvyazvsnlPVA(GpsStatusTransport gpsStatusTransport, Executor executor, GnssStatusCompat gnssStatusCompat) {
            if (gpsStatusTransport.mExecutor != executor) {
                return;
            }
            gpsStatusTransport.mCallback.getClass();
        }

        public GpsStatusTransport(LocationManager locationManager, GnssStatusCompat.Callback callback) {
            Preconditions.checkArgument(callback != null, "invalid null callback");
            this.mLocationManager = locationManager;
            this.mCallback = callback;
        }

        @Override // android.location.GpsStatus.Listener
        @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
        public void onGpsStatusChanged(int i) {
            GpsStatus gpsStatus;
            final Executor executor = this.mExecutor;
            if (executor == null) {
                return;
            }
            if (i == 1) {
                executor.execute(new Runnable() { // from class: androidx.core.location.LocationManagerCompat$GpsStatusTransport$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        LocationManagerCompat.GpsStatusTransport.$r8$lambda$b64kzWcVdI4q0UXJNvnbRXcPQAY(this.f$0, executor);
                    }
                });
                return;
            }
            if (i == 2) {
                executor.execute(new Runnable() { // from class: androidx.core.location.LocationManagerCompat$GpsStatusTransport$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        LocationManagerCompat.GpsStatusTransport.$r8$lambda$MriBUDqOAPnaKwESE9x_VRF4goI(this.f$0, executor);
                    }
                });
                return;
            }
            if (i != 3) {
                if (i == 4 && (gpsStatus = this.mLocationManager.getGpsStatus(null)) != null) {
                    final GpsStatusWrapper gpsStatusWrapper = new GpsStatusWrapper(gpsStatus);
                    executor.execute(new Runnable() { // from class: androidx.core.location.LocationManagerCompat$GpsStatusTransport$$ExternalSyntheticLambda3
                        @Override // java.lang.Runnable
                        public final void run() {
                            LocationManagerCompat.GpsStatusTransport.$r8$lambda$t0AVwzXoM489wx6RvyazvsnlPVA(this.f$0, executor, gpsStatusWrapper);
                        }
                    });
                    return;
                }
                return;
            }
            GpsStatus gpsStatus2 = this.mLocationManager.getGpsStatus(null);
            if (gpsStatus2 != null) {
                final int timeToFirstFix = gpsStatus2.getTimeToFirstFix();
                executor.execute(new Runnable() { // from class: androidx.core.location.LocationManagerCompat$GpsStatusTransport$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        LocationManagerCompat.GpsStatusTransport.$r8$lambda$h9CWj82BwleGjmVrzCcuKSbTYwQ(this.f$0, executor, timeToFirstFix);
                    }
                });
            }
        }

        public void register(Executor executor) {
            Preconditions.checkState(this.mExecutor == null, null);
            this.mExecutor = executor;
        }

        public void unregister() {
            this.mExecutor = null;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class InlineHandlerExecutor implements Executor {
        public final Handler mHandler;

        public InlineHandlerExecutor(@NonNull Handler handler) {
            handler.getClass();
            this.mHandler = handler;
        }

        @Override // java.util.concurrent.Executor
        public void execute(@NonNull Runnable runnable) {
            if (Looper.myLooper() == this.mHandler.getLooper()) {
                runnable.run();
                return;
            }
            Handler handler = this.mHandler;
            runnable.getClass();
            if (handler.post(runnable)) {
                return;
            }
            throw new RejectedExecutionException(this.mHandler + " is shutting down");
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class LocationListenerKey {
        public final LocationListenerCompat mListener;
        public final String mProvider;

        public LocationListenerKey(String str, LocationListenerCompat locationListenerCompat) {
            ObjectsCompat.requireNonNull(str, "invalid null provider");
            this.mProvider = str;
            ObjectsCompat.requireNonNull(locationListenerCompat, "invalid null listener");
            this.mListener = locationListenerCompat;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof LocationListenerKey)) {
                return false;
            }
            LocationListenerKey locationListenerKey = (LocationListenerKey) obj;
            return this.mProvider.equals(locationListenerKey.mProvider) && this.mListener.equals(locationListenerKey.mListener);
        }

        public int hashCode() {
            return Objects.hash(this.mProvider, this.mListener);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(24)
    public static class PreRGnssStatusTransport extends GnssStatus.Callback {
        public final GnssStatusCompat.Callback mCallback;

        @Nullable
        public volatile Executor mExecutor;

        public static /* synthetic */ void $r8$lambda$6oHL8rJIxM7y_44YqJT7zbqKKV0(PreRGnssStatusTransport preRGnssStatusTransport, Executor executor) {
            if (preRGnssStatusTransport.mExecutor != executor) {
                return;
            }
            preRGnssStatusTransport.mCallback.getClass();
        }

        /* JADX INFO: renamed from: $r8$lambda$HByor2Q9wSAm-Y9TkEf3nPNjq8M, reason: not valid java name */
        public static /* synthetic */ void m49$r8$lambda$HByor2Q9wSAmY9TkEf3nPNjq8M(PreRGnssStatusTransport preRGnssStatusTransport, Executor executor, int i) {
            if (preRGnssStatusTransport.mExecutor != executor) {
                return;
            }
            preRGnssStatusTransport.mCallback.getClass();
        }

        public static /* synthetic */ void $r8$lambda$XAGTBO53f2RwFCQUSWvBpVd2K0k(PreRGnssStatusTransport preRGnssStatusTransport, Executor executor) {
            if (preRGnssStatusTransport.mExecutor != executor) {
                return;
            }
            preRGnssStatusTransport.mCallback.getClass();
        }

        public static void $r8$lambda$_VXEP34KxVQdSRnKE5auDjuJ7G4(PreRGnssStatusTransport preRGnssStatusTransport, Executor executor, GnssStatus gnssStatus) {
            if (preRGnssStatusTransport.mExecutor != executor) {
                return;
            }
            GnssStatusCompat.Callback callback = preRGnssStatusTransport.mCallback;
            new GnssStatusWrapper(gnssStatus);
            callback.getClass();
        }

        public PreRGnssStatusTransport(GnssStatusCompat.Callback callback) {
            Preconditions.checkArgument(callback != null, "invalid null callback");
            this.mCallback = callback;
        }

        @Override // android.location.GnssStatus.Callback
        public void onFirstFix(final int i) {
            final Executor executor = this.mExecutor;
            if (executor == null) {
                return;
            }
            executor.execute(new Runnable() { // from class: androidx.core.location.LocationManagerCompat$PreRGnssStatusTransport$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    LocationManagerCompat.PreRGnssStatusTransport.m49$r8$lambda$HByor2Q9wSAmY9TkEf3nPNjq8M(this.f$0, executor, i);
                }
            });
        }

        @Override // android.location.GnssStatus.Callback
        public void onSatelliteStatusChanged(final GnssStatus gnssStatus) {
            final Executor executor = this.mExecutor;
            if (executor == null) {
                return;
            }
            executor.execute(new Runnable() { // from class: androidx.core.location.LocationManagerCompat$PreRGnssStatusTransport$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    LocationManagerCompat.PreRGnssStatusTransport.$r8$lambda$_VXEP34KxVQdSRnKE5auDjuJ7G4(this.f$0, executor, gnssStatus);
                }
            });
        }

        @Override // android.location.GnssStatus.Callback
        public void onStarted() {
            final Executor executor = this.mExecutor;
            if (executor == null) {
                return;
            }
            executor.execute(new Runnable() { // from class: androidx.core.location.LocationManagerCompat$PreRGnssStatusTransport$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    LocationManagerCompat.PreRGnssStatusTransport.$r8$lambda$XAGTBO53f2RwFCQUSWvBpVd2K0k(this.f$0, executor);
                }
            });
        }

        @Override // android.location.GnssStatus.Callback
        public void onStopped() {
            final Executor executor = this.mExecutor;
            if (executor == null) {
                return;
            }
            executor.execute(new Runnable() { // from class: androidx.core.location.LocationManagerCompat$PreRGnssStatusTransport$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    LocationManagerCompat.PreRGnssStatusTransport.$r8$lambda$6oHL8rJIxM7y_44YqJT7zbqKKV0(this.f$0, executor);
                }
            });
        }

        public void register(Executor executor) {
            Preconditions.checkArgument(executor != null, "invalid null executor");
            Preconditions.checkState(this.mExecutor == null, null);
            this.mExecutor = executor;
        }

        public void unregister() {
            this.mExecutor = null;
        }
    }

    @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
    @Deprecated
    public static void getCurrentLocation(@NonNull LocationManager locationManager, @NonNull String str, @Nullable androidx.core.os.CancellationSignal cancellationSignal, @NonNull Executor executor, @NonNull Consumer<Location> consumer) {
        getCurrentLocation(locationManager, str, cancellationSignal != null ? (CancellationSignal) cancellationSignal.getCancellationSignalObject() : null, executor, consumer);
    }

    @Nullable
    public static String getGnssHardwareModelName(@NonNull LocationManager locationManager) {
        if (Build.VERSION.SDK_INT >= 28) {
            return Api28Impl.getGnssHardwareModelName(locationManager);
        }
        return null;
    }

    public static int getGnssYearOfHardware(@NonNull LocationManager locationManager) {
        if (Build.VERSION.SDK_INT >= 28) {
            return Api28Impl.getGnssYearOfHardware(locationManager);
        }
        return 0;
    }

    public static boolean hasProvider(@NonNull LocationManager locationManager, @NonNull String str) {
        if (Build.VERSION.SDK_INT >= 31) {
            return Api31Impl.hasProvider(locationManager, str);
        }
        if (locationManager.getAllProviders().contains(str)) {
            return true;
        }
        return locationManager.getProvider(str) != null;
    }

    public static boolean isLocationEnabled(@NonNull LocationManager locationManager) {
        return Build.VERSION.SDK_INT >= 28 ? Api28Impl.isLocationEnabled(locationManager) : locationManager.isProviderEnabled("network") || locationManager.isProviderEnabled("gps");
    }

    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    @RequiresApi(24)
    public static boolean registerGnssMeasurementsCallback(@NonNull LocationManager locationManager, @NonNull GnssMeasurementsEvent.Callback callback, @NonNull Handler handler) {
        int i = Build.VERSION.SDK_INT;
        if (i > 30) {
            return Api24Impl.registerGnssMeasurementsCallback(locationManager, callback, handler);
        }
        if (i == 30) {
            return registerGnssMeasurementsCallbackOnR(locationManager, new ExecutorCompat.HandlerExecutor(handler), callback);
        }
        SimpleArrayMap<GnssMeasurementsEvent.Callback, GnssMeasurementsEvent.Callback> simpleArrayMap = GnssListenersHolder.sGnssMeasurementListeners;
        synchronized (simpleArrayMap) {
            try {
                unregisterGnssMeasurementsCallback(locationManager, callback);
                if (!Api24Impl.registerGnssMeasurementsCallback(locationManager, callback, handler)) {
                    return false;
                }
                simpleArrayMap.put(callback, callback);
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @RequiresApi(30)
    public static boolean registerGnssMeasurementsCallbackOnR(@NonNull LocationManager locationManager, @NonNull Executor executor, @NonNull GnssMeasurementsEvent.Callback callback) {
        if (Build.VERSION.SDK_INT != 30) {
            throw new IllegalStateException();
        }
        try {
            if (sGnssRequestBuilderClass == null) {
                sGnssRequestBuilderClass = Class.forName("android.location.GnssRequest$Builder");
            }
            if (sGnssRequestBuilderBuildMethod == null) {
                Method declaredMethod = sGnssRequestBuilderClass.getDeclaredMethod("build", null);
                sGnssRequestBuilderBuildMethod = declaredMethod;
                declaredMethod.setAccessible(true);
            }
            if (sRegisterGnssMeasurementsCallbackMethod == null) {
                Method declaredMethod2 = LocationManager.class.getDeclaredMethod("registerGnssMeasurementsCallback", Class.forName("android.location.GnssRequest"), Executor.class, LocationManagerCompat$$ExternalSyntheticApiModelOutline1.m());
                sRegisterGnssMeasurementsCallbackMethod = declaredMethod2;
                declaredMethod2.setAccessible(true);
            }
            Object objInvoke = sRegisterGnssMeasurementsCallbackMethod.invoke(locationManager, sGnssRequestBuilderBuildMethod.invoke(sGnssRequestBuilderClass.getDeclaredConstructor(null).newInstance(null), null), executor, callback);
            if (objInvoke != null) {
                if (((Boolean) objInvoke).booleanValue()) {
                    return true;
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException unused) {
        }
        return false;
    }

    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    public static boolean registerGnssStatusCallback(@NonNull LocationManager locationManager, @NonNull GnssStatusCompat.Callback callback, @NonNull Handler handler) {
        return Build.VERSION.SDK_INT >= 30 ? registerGnssStatusCallback(locationManager, new ExecutorCompat.HandlerExecutor(handler), callback) : registerGnssStatusCallback(locationManager, new InlineHandlerExecutor(handler), callback);
    }

    @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
    @GuardedBy("sLocationListeners")
    public static void registerLocationListenerTransport(LocationManager locationManager, LocationListenerTransport locationListenerTransport) {
        WeakReference<LocationListenerTransport> weakReferencePut = sLocationListeners.put(locationListenerTransport.getKey(), new WeakReference<>(locationListenerTransport));
        LocationListenerTransport locationListenerTransport2 = weakReferencePut != null ? weakReferencePut.get() : null;
        if (locationListenerTransport2 != null) {
            locationListenerTransport2.unregister();
            locationManager.removeUpdates(locationListenerTransport2);
        }
    }

    @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
    public static void removeUpdates(@NonNull LocationManager locationManager, @NonNull LocationListenerCompat locationListenerCompat) {
        WeakHashMap<LocationListenerKey, WeakReference<LocationListenerTransport>> weakHashMap = sLocationListeners;
        synchronized (weakHashMap) {
            try {
                Iterator<WeakReference<LocationListenerTransport>> it = weakHashMap.values().iterator();
                ArrayList arrayList = null;
                while (it.hasNext()) {
                    LocationListenerTransport locationListenerTransport = it.next().get();
                    if (locationListenerTransport != null) {
                        LocationListenerKey key = locationListenerTransport.getKey();
                        if (key.mListener == locationListenerCompat) {
                            if (arrayList == null) {
                                arrayList = new ArrayList();
                            }
                            arrayList.add(key);
                            locationListenerTransport.unregister();
                            locationManager.removeUpdates(locationListenerTransport);
                        }
                    }
                }
                if (arrayList != null) {
                    int size = arrayList.size();
                    int i = 0;
                    while (i < size) {
                        Object obj = arrayList.get(i);
                        i++;
                        sLocationListeners.remove((LocationListenerKey) obj);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        locationManager.removeUpdates(locationListenerCompat);
    }

    @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
    public static void requestLocationUpdates(@NonNull LocationManager locationManager, @NonNull String str, @NonNull LocationRequestCompat locationRequestCompat, @NonNull Executor executor, @NonNull LocationListenerCompat locationListenerCompat) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 31) {
            locationRequestCompat.getClass();
            Api31Impl.requestLocationUpdates(locationManager, str, LocationRequestCompat.Api31Impl.toLocationRequest(locationRequestCompat), executor, locationListenerCompat);
        } else if (i < 30 || !Api30Impl.tryRequestLocationUpdates(locationManager, str, locationRequestCompat, executor, locationListenerCompat)) {
            LocationListenerTransport locationListenerTransport = new LocationListenerTransport(new LocationListenerKey(str, locationListenerCompat), executor);
            if (Api19Impl.tryRequestLocationUpdates(locationManager, str, locationRequestCompat, locationListenerTransport)) {
                return;
            }
            synchronized (sLocationListeners) {
                locationManager.requestLocationUpdates(str, locationRequestCompat.mIntervalMillis, locationRequestCompat.mMinUpdateDistanceMeters, locationListenerTransport, Looper.getMainLooper());
                registerLocationListenerTransport(locationManager, locationListenerTransport);
            }
        }
    }

    @RequiresApi(24)
    public static void unregisterGnssMeasurementsCallback(@NonNull LocationManager locationManager, @NonNull GnssMeasurementsEvent.Callback callback) {
        if (Build.VERSION.SDK_INT >= 30) {
            locationManager.unregisterGnssMeasurementsCallback(callback);
            return;
        }
        SimpleArrayMap<GnssMeasurementsEvent.Callback, GnssMeasurementsEvent.Callback> simpleArrayMap = GnssListenersHolder.sGnssMeasurementListeners;
        synchronized (simpleArrayMap) {
            try {
                GnssMeasurementsEvent.Callback callbackM = LocationManagerCompat$$ExternalSyntheticApiModelOutline0.m(simpleArrayMap.remove(callback));
                if (callbackM != null) {
                    if (callbackM instanceof GnssMeasurementsTransport) {
                        ((GnssMeasurementsTransport) callbackM).unregister();
                    }
                    locationManager.unregisterGnssMeasurementsCallback(callbackM);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static void unregisterGnssStatusCallback(@NonNull LocationManager locationManager, @NonNull GnssStatusCompat.Callback callback) {
        if (Build.VERSION.SDK_INT >= 24) {
            SimpleArrayMap<Object, Object> simpleArrayMap = GnssListenersHolder.sGnssStatusListeners;
            synchronized (simpleArrayMap) {
                try {
                    Object objRemove = simpleArrayMap.remove(callback);
                    if (objRemove != null) {
                        Api24Impl.unregisterGnssStatusCallback(locationManager, objRemove);
                    }
                } finally {
                }
            }
            return;
        }
        SimpleArrayMap<Object, Object> simpleArrayMap2 = GnssListenersHolder.sGnssStatusListeners;
        synchronized (simpleArrayMap2) {
            try {
                GpsStatusTransport gpsStatusTransport = (GpsStatusTransport) simpleArrayMap2.remove(callback);
                if (gpsStatusTransport != null) {
                    gpsStatusTransport.unregister();
                    locationManager.removeGpsStatusListener(gpsStatusTransport);
                }
            } finally {
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class LocationListenerTransport implements LocationListener {
        public final Executor mExecutor;

        @Nullable
        public volatile LocationListenerKey mKey;

        public static /* synthetic */ void $r8$lambda$7dWvFyS9tcOOG9DZTM00D1BjC_c(LocationListenerTransport locationListenerTransport, String str) {
            LocationListenerKey locationListenerKey = locationListenerTransport.mKey;
            if (locationListenerKey == null) {
                return;
            }
            locationListenerKey.mListener.onProviderEnabled(str);
        }

        public static /* synthetic */ void $r8$lambda$EnzdVuJWkYma4LROYei5ekHTpBI(LocationListenerTransport locationListenerTransport, String str, int i, Bundle bundle) {
            LocationListenerKey locationListenerKey = locationListenerTransport.mKey;
            if (locationListenerKey == null) {
                return;
            }
            locationListenerKey.mListener.onStatusChanged(str, i, bundle);
        }

        public static /* synthetic */ void $r8$lambda$PAuVpNfZIEbR41Iz4TJjO0HCw4E(LocationListenerTransport locationListenerTransport, Location location) {
            LocationListenerKey locationListenerKey = locationListenerTransport.mKey;
            if (locationListenerKey == null) {
                return;
            }
            locationListenerKey.mListener.onLocationChanged(location);
        }

        public static /* synthetic */ void $r8$lambda$ZAleslm5rT6r0vk25seZyfJMAyU(LocationListenerTransport locationListenerTransport, List list) {
            LocationListenerKey locationListenerKey = locationListenerTransport.mKey;
            if (locationListenerKey == null) {
                return;
            }
            locationListenerKey.mListener.onLocationChanged((List<Location>) list);
        }

        public static /* synthetic */ void $r8$lambda$_tG421xxUwziwUZqVHZSZzdIPK8(LocationListenerTransport locationListenerTransport, String str) {
            LocationListenerKey locationListenerKey = locationListenerTransport.mKey;
            if (locationListenerKey == null) {
                return;
            }
            locationListenerKey.mListener.onProviderDisabled(str);
        }

        public static /* synthetic */ void $r8$lambda$dU9SN9exg1Y8NKpIQ6se4sYhy9E(LocationListenerTransport locationListenerTransport, int i) {
            LocationListenerKey locationListenerKey = locationListenerTransport.mKey;
            if (locationListenerKey == null) {
                return;
            }
            locationListenerKey.mListener.onFlushComplete(i);
        }

        public LocationListenerTransport(@Nullable LocationListenerKey locationListenerKey, Executor executor) {
            this.mKey = locationListenerKey;
            this.mExecutor = executor;
        }

        public LocationListenerKey getKey() {
            LocationListenerKey locationListenerKey = this.mKey;
            locationListenerKey.getClass();
            return locationListenerKey;
        }

        @Override // android.location.LocationListener
        public void onFlushComplete(final int i) {
            if (this.mKey == null) {
                return;
            }
            this.mExecutor.execute(new Runnable() { // from class: androidx.core.location.LocationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    LocationManagerCompat.LocationListenerTransport.$r8$lambda$dU9SN9exg1Y8NKpIQ6se4sYhy9E(this.f$0, i);
                }
            });
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(@NonNull final Location location) {
            if (this.mKey == null) {
                return;
            }
            this.mExecutor.execute(new Runnable() { // from class: androidx.core.location.LocationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    LocationManagerCompat.LocationListenerTransport.$r8$lambda$PAuVpNfZIEbR41Iz4TJjO0HCw4E(this.f$0, location);
                }
            });
        }

        @Override // android.location.LocationListener
        public void onProviderDisabled(@NonNull final String str) {
            if (this.mKey == null) {
                return;
            }
            this.mExecutor.execute(new Runnable() { // from class: androidx.core.location.LocationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    LocationManagerCompat.LocationListenerTransport.$r8$lambda$_tG421xxUwziwUZqVHZSZzdIPK8(this.f$0, str);
                }
            });
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(@NonNull final String str) {
            if (this.mKey == null) {
                return;
            }
            this.mExecutor.execute(new Runnable() { // from class: androidx.core.location.LocationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    LocationManagerCompat.LocationListenerTransport.$r8$lambda$7dWvFyS9tcOOG9DZTM00D1BjC_c(this.f$0, str);
                }
            });
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(final String str, final int i, final Bundle bundle) {
            if (this.mKey == null) {
                return;
            }
            this.mExecutor.execute(new Runnable() { // from class: androidx.core.location.LocationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    LocationManagerCompat.LocationListenerTransport.$r8$lambda$EnzdVuJWkYma4LROYei5ekHTpBI(this.f$0, str, i, bundle);
                }
            });
        }

        public void unregister() {
            this.mKey = null;
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(@NonNull final List<Location> list) {
            if (this.mKey == null) {
                return;
            }
            this.mExecutor.execute(new Runnable() { // from class: androidx.core.location.LocationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    LocationManagerCompat.LocationListenerTransport.$r8$lambda$ZAleslm5rT6r0vk25seZyfJMAyU(this.f$0, list);
                }
            });
        }
    }

    @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
    public static void getCurrentLocation(@NonNull LocationManager locationManager, @NonNull String str, @Nullable CancellationSignal cancellationSignal, @NonNull Executor executor, @NonNull final Consumer<Location> consumer) {
        if (Build.VERSION.SDK_INT >= 30) {
            Api30Impl.getCurrentLocation(locationManager, str, cancellationSignal, executor, consumer);
            return;
        }
        if (cancellationSignal != null) {
            cancellationSignal.throwIfCanceled();
        }
        final Location lastKnownLocation = locationManager.getLastKnownLocation(str);
        if (lastKnownLocation != null && SystemClock.elapsedRealtime() - LocationCompat.getElapsedRealtimeMillis(lastKnownLocation) < 10000) {
            executor.execute(new Runnable() { // from class: androidx.core.location.LocationManagerCompat$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    consumer.accept(lastKnownLocation);
                }
            });
            return;
        }
        final CancellableLocationListener cancellableLocationListener = new CancellableLocationListener(locationManager, executor, consumer);
        locationManager.requestLocationUpdates(str, 0L, 0.0f, cancellableLocationListener, Looper.getMainLooper());
        if (cancellationSignal != null) {
            cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: androidx.core.location.LocationManagerCompat$$ExternalSyntheticLambda3
                @Override // android.os.CancellationSignal.OnCancelListener
                public final void onCancel() {
                    cancellableLocationListener.cancel();
                }
            });
        }
        cancellableLocationListener.startTimeout(30000L);
    }

    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    public static boolean registerGnssStatusCallback(@NonNull LocationManager locationManager, @NonNull Executor executor, @NonNull GnssStatusCompat.Callback callback) {
        if (Build.VERSION.SDK_INT >= 30) {
            return registerGnssStatusCallback(locationManager, null, executor, callback);
        }
        Looper looperMyLooper = Looper.myLooper();
        if (looperMyLooper == null) {
            looperMyLooper = Looper.getMainLooper();
        }
        return registerGnssStatusCallback(locationManager, new Handler(looperMyLooper), executor, callback);
    }

    /* JADX WARN: Removed duplicated region for block: B:64:0x00cc A[Catch: all -> 0x00a7, TryCatch #7 {all -> 0x00a7, blocks: (B:27:0x0056, B:60:0x00ad, B:61:0x00c3, B:62:0x00c4, B:64:0x00cc, B:66:0x00d4, B:67:0x00da, B:68:0x00db, B:69:0x00e0, B:70:0x00e1, B:71:0x00e7), top: B:79:0x0056 }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00e1 A[Catch: all -> 0x00a7, TryCatch #7 {all -> 0x00a7, blocks: (B:27:0x0056, B:60:0x00ad, B:61:0x00c3, B:62:0x00c4, B:64:0x00cc, B:66:0x00d4, B:67:0x00da, B:68:0x00db, B:69:0x00e0, B:70:0x00e1, B:71:0x00e7), top: B:79:0x0056 }] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00ea A[Catch: all -> 0x002f, TRY_ENTER, TryCatch #10 {all -> 0x002f, blocks: (B:16:0x0021, B:18:0x0029, B:22:0x0035, B:24:0x004c, B:33:0x0079, B:34:0x0080, B:43:0x008d, B:44:0x0094, B:73:0x00ea, B:74:0x00f1, B:25:0x0050, B:75:0x00f2, B:76:0x0108, B:21:0x0032), top: B:80:0x0021 }] */
    @androidx.annotation.RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean registerGnssStatusCallback(final android.location.LocationManager r9, android.os.Handler r10, java.util.concurrent.Executor r11, androidx.core.location.GnssStatusCompat.Callback r12) {
        /*
            Method dump skipped, instruction units count: 267
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.location.LocationManagerCompat.registerGnssStatusCallback(android.location.LocationManager, android.os.Handler, java.util.concurrent.Executor, androidx.core.location.GnssStatusCompat$Callback):boolean");
    }

    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    @RequiresApi(24)
    public static boolean registerGnssMeasurementsCallback(@NonNull LocationManager locationManager, @NonNull Executor executor, @NonNull GnssMeasurementsEvent.Callback callback) {
        int i = Build.VERSION.SDK_INT;
        if (i > 30) {
            return Api31Impl.registerGnssMeasurementsCallback(locationManager, executor, callback);
        }
        if (i == 30) {
            return registerGnssMeasurementsCallbackOnR(locationManager, executor, callback);
        }
        SimpleArrayMap<GnssMeasurementsEvent.Callback, GnssMeasurementsEvent.Callback> simpleArrayMap = GnssListenersHolder.sGnssMeasurementListeners;
        synchronized (simpleArrayMap) {
            try {
                GnssMeasurementsTransport gnssMeasurementsTransport = new GnssMeasurementsTransport(callback, executor);
                unregisterGnssMeasurementsCallback(locationManager, callback);
                if (!Api24Impl.registerGnssMeasurementsCallback(locationManager, gnssMeasurementsTransport)) {
                    return false;
                }
                simpleArrayMap.put(callback, gnssMeasurementsTransport);
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Api19Impl {
        public static Class<?> sLocationRequestClass;
        public static Method sRequestLocationUpdatesLooperMethod;

        @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
        @SuppressLint({"BanUncheckedReflection"})
        public static boolean tryRequestLocationUpdates(LocationManager locationManager, String str, LocationRequestCompat locationRequestCompat, LocationListenerTransport locationListenerTransport) {
            try {
                if (sLocationRequestClass == null) {
                    sLocationRequestClass = Class.forName("android.location.LocationRequest");
                }
                if (sRequestLocationUpdatesLooperMethod == null) {
                    Method declaredMethod = LocationManager.class.getDeclaredMethod("requestLocationUpdates", sLocationRequestClass, LocationListener.class, Looper.class);
                    sRequestLocationUpdatesLooperMethod = declaredMethod;
                    declaredMethod.setAccessible(true);
                }
                LocationRequest locationRequest = locationRequestCompat.toLocationRequest(str);
                if (locationRequest != null) {
                    synchronized (LocationManagerCompat.sLocationListeners) {
                        sRequestLocationUpdatesLooperMethod.invoke(locationManager, locationRequest, locationListenerTransport, Looper.getMainLooper());
                        LocationManagerCompat.registerLocationListenerTransport(locationManager, locationListenerTransport);
                    }
                    return true;
                }
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | UnsupportedOperationException | InvocationTargetException unused) {
            }
            return false;
        }

        @SuppressLint({"BanUncheckedReflection"})
        public static boolean tryRequestLocationUpdates(LocationManager locationManager, String str, LocationRequestCompat locationRequestCompat, LocationListenerCompat locationListenerCompat, Looper looper) {
            try {
                if (sLocationRequestClass == null) {
                    sLocationRequestClass = Class.forName("android.location.LocationRequest");
                }
                if (sRequestLocationUpdatesLooperMethod == null) {
                    Method declaredMethod = LocationManager.class.getDeclaredMethod("requestLocationUpdates", sLocationRequestClass, LocationListener.class, Looper.class);
                    sRequestLocationUpdatesLooperMethod = declaredMethod;
                    declaredMethod.setAccessible(true);
                }
                LocationRequest locationRequest = locationRequestCompat.toLocationRequest(str);
                if (locationRequest != null) {
                    sRequestLocationUpdatesLooperMethod.invoke(locationManager, locationRequest, locationListenerCompat, looper);
                    return true;
                }
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | UnsupportedOperationException | InvocationTargetException unused) {
            }
            return false;
        }
    }

    @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
    public static void requestLocationUpdates(@NonNull LocationManager locationManager, @NonNull String str, @NonNull LocationRequestCompat locationRequestCompat, @NonNull LocationListenerCompat locationListenerCompat, @NonNull Looper looper) {
        if (Build.VERSION.SDK_INT >= 31) {
            locationRequestCompat.getClass();
            Api31Impl.requestLocationUpdates(locationManager, str, LocationRequestCompat.Api31Impl.toLocationRequest(locationRequestCompat), new ExecutorCompat.HandlerExecutor(new Handler(looper)), locationListenerCompat);
        } else {
            if (Api19Impl.tryRequestLocationUpdates(locationManager, str, locationRequestCompat, locationListenerCompat, looper)) {
                return;
            }
            locationManager.requestLocationUpdates(str, locationRequestCompat.mIntervalMillis, locationRequestCompat.mMinUpdateDistanceMeters, locationListenerCompat, looper);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class CancellableLocationListener implements LocationListener {
        public Consumer<Location> mConsumer;
        public final Executor mExecutor;
        public final LocationManager mLocationManager;
        public final Handler mTimeoutHandler = new Handler(Looper.getMainLooper());

        @Nullable
        public Runnable mTimeoutRunnable;

        @GuardedBy("this")
        public boolean mTriggered;

        public static /* synthetic */ void $r8$lambda$5vD3UikhI2dZ5HryZZTveHwrt_A(CancellableLocationListener cancellableLocationListener) {
            cancellableLocationListener.mTimeoutRunnable = null;
            cancellableLocationListener.onLocationChanged((Location) null);
        }

        public CancellableLocationListener(LocationManager locationManager, Executor executor, Consumer<Location> consumer) {
            this.mLocationManager = locationManager;
            this.mExecutor = executor;
            this.mConsumer = consumer;
        }

        @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
        public void cancel() {
            synchronized (this) {
                try {
                    if (this.mTriggered) {
                        return;
                    }
                    this.mTriggered = true;
                    cleanup();
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
        public final void cleanup() {
            this.mConsumer = null;
            this.mLocationManager.removeUpdates(this);
            Runnable runnable = this.mTimeoutRunnable;
            if (runnable != null) {
                this.mTimeoutHandler.removeCallbacks(runnable);
                this.mTimeoutRunnable = null;
            }
        }

        @Override // android.location.LocationListener
        @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
        public void onLocationChanged(@Nullable final Location location) {
            synchronized (this) {
                try {
                    if (this.mTriggered) {
                        return;
                    }
                    this.mTriggered = true;
                    final Consumer<Location> consumer = this.mConsumer;
                    this.mExecutor.execute(new Runnable() { // from class: androidx.core.location.LocationManagerCompat$CancellableLocationListener$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            consumer.accept(location);
                        }
                    });
                    cleanup();
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // android.location.LocationListener
        @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
        public void onProviderDisabled(@NonNull String str) {
            onLocationChanged((Location) null);
        }

        @SuppressLint({"MissingPermission"})
        public void startTimeout(long j) {
            synchronized (this) {
                try {
                    if (this.mTriggered) {
                        return;
                    }
                    Runnable runnable = new Runnable() { // from class: androidx.core.location.LocationManagerCompat$CancellableLocationListener$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            LocationManagerCompat.CancellableLocationListener.$r8$lambda$5vD3UikhI2dZ5HryZZTveHwrt_A(this.f$0);
                        }
                    };
                    this.mTimeoutRunnable = runnable;
                    this.mTimeoutHandler.postDelayed(runnable, j);
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(@NonNull String str) {
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String str, int i, Bundle bundle) {
        }
    }
}
