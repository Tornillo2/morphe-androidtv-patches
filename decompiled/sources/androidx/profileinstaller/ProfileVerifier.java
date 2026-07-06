package androidx.profileinstaller;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.WorkerThread;
import androidx.concurrent.futures.ResolvableFuture;
import com.google.common.util.concurrent.ListenableFuture;
import j$.util.Objects;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class ProfileVerifier {
    public static final String CUR_PROFILES_BASE_DIR = "/data/misc/profiles/cur/0/";
    public static final String PROFILE_FILE_NAME = "primary.prof";
    public static final String PROFILE_INSTALLED_CACHE_FILE_NAME = "profileInstalled";
    public static final String REF_PROFILES_BASE_DIR = "/data/misc/profiles/ref/";
    public static final String TAG = "ProfileVerifier";
    public static final ResolvableFuture<CompilationStatus> sFuture = ResolvableFuture.create();
    public static final Object SYNC_OBJ = new Object();

    @Nullable
    public static CompilationStatus sCompilationStatus = null;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(33)
    public static class Api33Impl {
        @DoNotInline
        public static PackageInfo getPackageInfo(PackageManager packageManager, Context context) throws PackageManager.NameNotFoundException {
            return packageManager.getPackageInfo(context.getPackageName(), PackageManager.PackageInfoFlags.of(0L));
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static class Cache {
        public static final int SCHEMA = 1;
        public final long mInstalledCurrentProfileSize;
        public final long mPackageLastUpdateTime;
        public final int mResultCode;
        public final int mSchema;

        public Cache(int i, int i2, long j, long j2) {
            this.mSchema = i;
            this.mResultCode = i2;
            this.mPackageLastUpdateTime = j;
            this.mInstalledCurrentProfileSize = j2;
        }

        public static Cache readFromFile(@NonNull File file) throws IOException {
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
            try {
                Cache cache = new Cache(dataInputStream.readInt(), dataInputStream.readInt(), dataInputStream.readLong(), dataInputStream.readLong());
                dataInputStream.close();
                return cache;
            } finally {
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && (obj instanceof Cache)) {
                Cache cache = (Cache) obj;
                if (this.mResultCode == cache.mResultCode && this.mPackageLastUpdateTime == cache.mPackageLastUpdateTime && this.mSchema == cache.mSchema && this.mInstalledCurrentProfileSize == cache.mInstalledCurrentProfileSize) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(this.mResultCode), Long.valueOf(this.mPackageLastUpdateTime), Integer.valueOf(this.mSchema), Long.valueOf(this.mInstalledCurrentProfileSize));
        }

        public void writeOnFile(@NonNull File file) throws IOException {
            file.delete();
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));
            try {
                dataOutputStream.writeInt(this.mSchema);
                dataOutputStream.writeInt(this.mResultCode);
                dataOutputStream.writeLong(this.mPackageLastUpdateTime);
                dataOutputStream.writeLong(this.mInstalledCurrentProfileSize);
                dataOutputStream.close();
            } catch (Throwable th) {
                try {
                    dataOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class CompilationStatus {
        public static final int RESULT_CODE_COMPILED_WITH_PROFILE = 1;
        public static final int RESULT_CODE_COMPILED_WITH_PROFILE_NON_MATCHING = 3;
        public static final int RESULT_CODE_ERROR_CACHE_FILE_EXISTS_BUT_CANNOT_BE_READ = 131072;
        public static final int RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE = 196608;
        public static final int RESULT_CODE_ERROR_CODE_BIT_SHIFT = 16;
        public static final int RESULT_CODE_ERROR_PACKAGE_NAME_DOES_NOT_EXIST = 65536;
        public static final int RESULT_CODE_ERROR_UNSUPPORTED_API_VERSION = 262144;
        public static final int RESULT_CODE_NO_PROFILE = 0;
        public static final int RESULT_CODE_PROFILE_ENQUEUED_FOR_COMPILATION = 2;
        public final boolean mHasCurrentProfile;
        public final boolean mHasReferenceProfile;
        public final int mResultCode;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        @Retention(RetentionPolicy.SOURCE)
        @RestrictTo({RestrictTo.Scope.LIBRARY})
        public @interface ResultCode {
        }

        public CompilationStatus(int i, boolean z, boolean z2) {
            this.mResultCode = i;
            this.mHasCurrentProfile = z2;
            this.mHasReferenceProfile = z;
        }

        public int getProfileInstallResultCode() {
            return this.mResultCode;
        }

        public boolean hasProfileEnqueuedForCompilation() {
            return this.mHasCurrentProfile;
        }

        public boolean isCompiledWithProfile() {
            return this.mHasReferenceProfile;
        }
    }

    @NonNull
    public static ListenableFuture<CompilationStatus> getCompilationStatusAsync() {
        return sFuture;
    }

    public static long getPackageLastUpdateTime(Context context) throws PackageManager.NameNotFoundException {
        PackageManager packageManager = context.getApplicationContext().getPackageManager();
        return Build.VERSION.SDK_INT >= 33 ? Api33Impl.getPackageInfo(packageManager, context).lastUpdateTime : packageManager.getPackageInfo(context.getPackageName(), 0).lastUpdateTime;
    }

    public static CompilationStatus setCompilationStatus(int i, boolean z, boolean z2) {
        CompilationStatus compilationStatus = new CompilationStatus(i, z, z2);
        sCompilationStatus = compilationStatus;
        sFuture.set(compilationStatus);
        return sCompilationStatus;
    }

    @NonNull
    @WorkerThread
    public static CompilationStatus writeProfileVerification(@NonNull Context context) {
        return writeProfileVerification(context, false);
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    @WorkerThread
    public static CompilationStatus writeProfileVerification(@NonNull Context context, boolean z) {
        Cache fromFile;
        int i;
        CompilationStatus compilationStatus;
        if (!z && (compilationStatus = sCompilationStatus) != null) {
            return compilationStatus;
        }
        synchronized (SYNC_OBJ) {
            if (!z) {
                try {
                    CompilationStatus compilationStatus2 = sCompilationStatus;
                    if (compilationStatus2 != null) {
                        return compilationStatus2;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            int i2 = Build.VERSION.SDK_INT;
            int i3 = 0;
            if (i2 >= 28 && i2 != 30) {
                File file = new File(new File(REF_PROFILES_BASE_DIR, context.getPackageName()), "primary.prof");
                long length = file.length();
                boolean z2 = file.exists() && length > 0;
                File file2 = new File(new File(CUR_PROFILES_BASE_DIR, context.getPackageName()), "primary.prof");
                long length2 = file2.length();
                boolean z3 = file2.exists() && length2 > 0;
                try {
                    long packageLastUpdateTime = getPackageLastUpdateTime(context);
                    File file3 = new File(context.getFilesDir(), PROFILE_INSTALLED_CACHE_FILE_NAME);
                    if (file3.exists()) {
                        try {
                            fromFile = Cache.readFromFile(file3);
                        } catch (IOException unused) {
                            return setCompilationStatus(131072, z2, z3);
                        }
                    } else {
                        fromFile = null;
                    }
                    if (fromFile != null && fromFile.mPackageLastUpdateTime == packageLastUpdateTime && (i = fromFile.mResultCode) != 2) {
                        i3 = i;
                    } else if (z2) {
                        i3 = 1;
                    } else if (z3) {
                        i3 = 2;
                    }
                    if (z && z3 && i3 != 1) {
                        i3 = 2;
                    }
                    int i4 = (fromFile == null || fromFile.mResultCode != 2 || i3 != 1 || length >= fromFile.mInstalledCurrentProfileSize) ? i3 : 3;
                    Cache cache = new Cache(1, i4, packageLastUpdateTime, length2);
                    if (fromFile == null || !fromFile.equals(cache)) {
                        try {
                            cache.writeOnFile(file3);
                        } catch (IOException unused2) {
                            i4 = CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                        }
                    }
                    return setCompilationStatus(i4, z2, z3);
                } catch (PackageManager.NameNotFoundException unused3) {
                    return setCompilationStatus(65536, z2, z3);
                }
            }
            return setCompilationStatus(262144, false, false);
        }
    }
}
