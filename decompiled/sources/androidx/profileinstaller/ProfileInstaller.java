package androidx.profileinstaller;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.WorkerThread;
import androidx.media3.exoplayer.offline.DefaultDownloaderFactory$$ExternalSyntheticLambda0;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class ProfileInstaller {
    public static final int DIAGNOSTIC_CURRENT_PROFILE_DOES_NOT_EXIST = 2;
    public static final int DIAGNOSTIC_CURRENT_PROFILE_EXISTS = 1;
    public static final int DIAGNOSTIC_PROFILE_IS_COMPRESSED = 5;
    public static final int DIAGNOSTIC_REF_PROFILE_DOES_NOT_EXIST = 4;
    public static final int DIAGNOSTIC_REF_PROFILE_EXISTS = 3;
    public static final DiagnosticsCallback EMPTY_DIAGNOSTICS = new AnonymousClass1();

    @NonNull
    public static final DiagnosticsCallback LOG_DIAGNOSTICS = new AnonymousClass2();
    public static final String PROFILE_BASE_DIR = "/data/misc/profiles/cur/0";
    public static final String PROFILE_FILE = "primary.prof";
    public static final String PROFILE_INSTALLER_SKIP_FILE_NAME = "profileinstaller_profileWrittenFor_lastUpdateTime.dat";
    public static final String PROFILE_META_LOCATION = "dexopt/baseline.profm";
    public static final String PROFILE_SOURCE_LOCATION = "dexopt/baseline.prof";
    public static final int RESULT_ALREADY_INSTALLED = 2;
    public static final int RESULT_BASELINE_PROFILE_NOT_FOUND = 6;
    public static final int RESULT_BENCHMARK_OPERATION_FAILURE = 15;
    public static final int RESULT_BENCHMARK_OPERATION_SUCCESS = 14;
    public static final int RESULT_BENCHMARK_OPERATION_UNKNOWN = 16;
    public static final int RESULT_DELETE_SKIP_FILE_SUCCESS = 11;
    public static final int RESULT_DESIRED_FORMAT_UNSUPPORTED = 5;
    public static final int RESULT_INSTALL_SKIP_FILE_SUCCESS = 10;
    public static final int RESULT_INSTALL_SUCCESS = 1;
    public static final int RESULT_IO_EXCEPTION = 7;
    public static final int RESULT_META_FILE_REQUIRED_BUT_NOT_FOUND = 9;
    public static final int RESULT_NOT_WRITABLE = 4;
    public static final int RESULT_PARSE_EXCEPTION = 8;
    public static final int RESULT_SAVE_PROFILE_SIGNALLED = 12;
    public static final int RESULT_SAVE_PROFILE_SKIPPED = 13;
    public static final int RESULT_UNSUPPORTED_ART_VERSION = 3;
    public static final String TAG = "ProfileInstaller";

    /* JADX INFO: renamed from: androidx.profileinstaller.ProfileInstaller$2, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass2 implements DiagnosticsCallback {
        public static final String TAG = "ProfileInstaller";

        @Override // androidx.profileinstaller.ProfileInstaller.DiagnosticsCallback
        public void onDiagnosticReceived(int i, @Nullable Object obj) {
            Log.d("ProfileInstaller", i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "" : "DIAGNOSTIC_PROFILE_IS_COMPRESSED" : "DIAGNOSTIC_REF_PROFILE_DOES_NOT_EXIST" : "DIAGNOSTIC_REF_PROFILE_EXISTS" : "DIAGNOSTIC_CURRENT_PROFILE_DOES_NOT_EXIST" : "DIAGNOSTIC_CURRENT_PROFILE_EXISTS");
        }

        @Override // androidx.profileinstaller.ProfileInstaller.DiagnosticsCallback
        public void onResultReceived(int i, @Nullable Object obj) {
            String str;
            switch (i) {
                case 1:
                    str = "RESULT_INSTALL_SUCCESS";
                    break;
                case 2:
                    str = "RESULT_ALREADY_INSTALLED";
                    break;
                case 3:
                    str = "RESULT_UNSUPPORTED_ART_VERSION";
                    break;
                case 4:
                    str = "RESULT_NOT_WRITABLE";
                    break;
                case 5:
                    str = "RESULT_DESIRED_FORMAT_UNSUPPORTED";
                    break;
                case 6:
                    str = "RESULT_BASELINE_PROFILE_NOT_FOUND";
                    break;
                case 7:
                    str = "RESULT_IO_EXCEPTION";
                    break;
                case 8:
                    str = "RESULT_PARSE_EXCEPTION";
                    break;
                case 9:
                default:
                    str = "";
                    break;
                case 10:
                    str = "RESULT_INSTALL_SKIP_FILE_SUCCESS";
                    break;
                case 11:
                    str = "RESULT_DELETE_SKIP_FILE_SUCCESS";
                    break;
            }
            if (i == 6 || i == 7 || i == 8) {
                Log.e("ProfileInstaller", str, (Throwable) obj);
            } else {
                Log.d("ProfileInstaller", str);
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public @interface DiagnosticCode {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface DiagnosticsCallback {
        void onDiagnosticReceived(int i, @Nullable Object obj);

        void onResultReceived(int i, @Nullable Object obj);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public @interface ResultCode {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static boolean deleteProfileWrittenFor(@NonNull File file) {
        return new File(file, PROFILE_INSTALLER_SKIP_FILE_NAME).delete();
    }

    @WorkerThread
    public static void deleteSkipFile(@NonNull Context context, @NonNull Executor executor, @NonNull DiagnosticsCallback diagnosticsCallback) {
        deleteProfileWrittenFor(context.getFilesDir());
        result(executor, diagnosticsCallback, 11, null);
    }

    public static void diagnostic(@NonNull Executor executor, @NonNull final DiagnosticsCallback diagnosticsCallback, final int i, @Nullable final Object obj) {
        executor.execute(new Runnable() { // from class: androidx.profileinstaller.ProfileInstaller$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                diagnosticsCallback.onDiagnosticReceived(i, obj);
            }
        });
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    @WorkerThread
    public static boolean hasAlreadyWrittenProfileForThisInstall(PackageInfo packageInfo, File file, DiagnosticsCallback diagnosticsCallback) {
        File file2 = new File(file, PROFILE_INSTALLER_SKIP_FILE_NAME);
        if (!file2.exists()) {
            return false;
        }
        try {
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file2));
            try {
                long j = dataInputStream.readLong();
                dataInputStream.close();
                boolean z = j == packageInfo.lastUpdateTime;
                if (z) {
                    diagnosticsCallback.onResultReceived(2, null);
                }
                return z;
            } finally {
            }
        } catch (IOException unused) {
            return false;
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static void noteProfileWrittenFor(@NonNull PackageInfo packageInfo, @NonNull File file) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(new File(file, PROFILE_INSTALLER_SKIP_FILE_NAME)));
            try {
                dataOutputStream.writeLong(packageInfo.lastUpdateTime);
                dataOutputStream.close();
            } finally {
            }
        } catch (IOException unused) {
        }
    }

    public static void result(@NonNull Executor executor, @NonNull final DiagnosticsCallback diagnosticsCallback, final int i, @Nullable final Object obj) {
        executor.execute(new Runnable() { // from class: androidx.profileinstaller.ProfileInstaller$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                diagnosticsCallback.onResultReceived(i, obj);
            }
        });
    }

    public static boolean transcodeAndWrite(@NonNull AssetManager assetManager, @NonNull String str, @NonNull PackageInfo packageInfo, @NonNull File file, @NonNull String str2, @NonNull Executor executor, @NonNull DiagnosticsCallback diagnosticsCallback) {
        DeviceProfileWriter deviceProfileWriter = new DeviceProfileWriter(assetManager, executor, diagnosticsCallback, str2, PROFILE_SOURCE_LOCATION, PROFILE_META_LOCATION, new File(new File(PROFILE_BASE_DIR, str), "primary.prof"));
        if (!deviceProfileWriter.deviceAllowsProfileInstallerAotWrites()) {
            return false;
        }
        boolean zWrite = deviceProfileWriter.read().transcodeIfNeeded().write();
        if (zWrite) {
            noteProfileWrittenFor(packageInfo, file);
        }
        return zWrite;
    }

    @WorkerThread
    public static void writeProfile(@NonNull Context context) {
        writeProfile(context, new DefaultDownloaderFactory$$ExternalSyntheticLambda0(), EMPTY_DIAGNOSTICS, false);
    }

    @WorkerThread
    public static void writeSkipFile(@NonNull Context context, @NonNull Executor executor, @NonNull DiagnosticsCallback diagnosticsCallback) {
        try {
            noteProfileWrittenFor(context.getPackageManager().getPackageInfo(context.getApplicationContext().getPackageName(), 0), context.getFilesDir());
            result(executor, diagnosticsCallback, 10, null);
        } catch (PackageManager.NameNotFoundException e) {
            result(executor, diagnosticsCallback, 7, e);
        }
    }

    @WorkerThread
    public static void writeProfile(@NonNull Context context, @NonNull Executor executor, @NonNull DiagnosticsCallback diagnosticsCallback) {
        writeProfile(context, executor, diagnosticsCallback, false);
    }

    @WorkerThread
    public static void writeProfile(@NonNull Context context, @NonNull Executor executor, @NonNull DiagnosticsCallback diagnosticsCallback, boolean z) {
        Context applicationContext = context.getApplicationContext();
        String packageName = applicationContext.getPackageName();
        ApplicationInfo applicationInfo = applicationContext.getApplicationInfo();
        AssetManager assets = applicationContext.getAssets();
        String name = new File(applicationInfo.sourceDir).getName();
        boolean z2 = false;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            File filesDir = context.getFilesDir();
            if (!z && hasAlreadyWrittenProfileForThisInstall(packageInfo, filesDir, diagnosticsCallback)) {
                Log.d("ProfileInstaller", "Skipping profile installation for " + context.getPackageName());
                ProfileVerifier.writeProfileVerification(context, false);
                return;
            }
            Log.d("ProfileInstaller", "Installing profile for " + context.getPackageName());
            if (transcodeAndWrite(assets, packageName, packageInfo, filesDir, name, executor, diagnosticsCallback) && z) {
                z2 = true;
            }
            ProfileVerifier.writeProfileVerification(context, z2);
        } catch (PackageManager.NameNotFoundException e) {
            diagnosticsCallback.onResultReceived(7, e);
            ProfileVerifier.writeProfileVerification(context, false);
        }
    }

    /* JADX INFO: renamed from: androidx.profileinstaller.ProfileInstaller$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 implements DiagnosticsCallback {
        @Override // androidx.profileinstaller.ProfileInstaller.DiagnosticsCallback
        public void onDiagnosticReceived(int i, @Nullable Object obj) {
        }

        @Override // androidx.profileinstaller.ProfileInstaller.DiagnosticsCallback
        public void onResultReceived(int i, @Nullable Object obj) {
        }
    }
}
