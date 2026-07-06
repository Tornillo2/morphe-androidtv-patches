package androidx.work.impl;

import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.work.Logger;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class WorkDatabasePathHelper {
    public static final String WORK_DATABASE_NAME = "androidx.work.workdb";
    public static final String TAG = Logger.tagWithPrefix("WrkDbPathHelper");
    public static final String[] DATABASE_EXTRA_FILES = {"-journal", "-shm", "-wal"};

    @NonNull
    @VisibleForTesting
    public static File getDatabasePath(@NonNull Context context) {
        return Build.VERSION.SDK_INT < 23 ? context.getDatabasePath(WORK_DATABASE_NAME) : getNoBackupPath(context, WORK_DATABASE_NAME);
    }

    @NonNull
    @VisibleForTesting
    public static File getDefaultDatabasePath(@NonNull Context context) {
        return context.getDatabasePath(WORK_DATABASE_NAME);
    }

    @RequiresApi(23)
    public static File getNoBackupPath(@NonNull Context context, @NonNull String filePath) {
        return new File(context.getNoBackupFilesDir(), filePath);
    }

    @NonNull
    public static String getWorkDatabaseName() {
        return WORK_DATABASE_NAME;
    }

    public static void migrateDatabase(@NonNull Context context) {
        File databasePath = context.getDatabasePath(WORK_DATABASE_NAME);
        if (Build.VERSION.SDK_INT < 23 || !databasePath.exists()) {
            return;
        }
        Logger.get().debug(TAG, "Migrating WorkDatabase to the no-backup directory", new Throwable[0]);
        HashMap map = (HashMap) migrationPaths(context);
        for (File file : map.keySet()) {
            File file2 = (File) map.get(file);
            if (file.exists() && file2 != null) {
                if (file2.exists()) {
                    Logger.get().warning(TAG, String.format("Over-writing contents of %s", file2), new Throwable[0]);
                }
                Logger.get().debug(TAG, file.renameTo(file2) ? String.format("Migrated %s to %s", file, file2) : String.format("Renaming %s to %s failed", file, file2), new Throwable[0]);
            }
        }
    }

    @NonNull
    @VisibleForTesting
    public static Map<File, File> migrationPaths(@NonNull Context context) {
        HashMap map = new HashMap();
        if (Build.VERSION.SDK_INT >= 23) {
            File databasePath = context.getDatabasePath(WORK_DATABASE_NAME);
            File databasePath2 = getDatabasePath(context);
            map.put(databasePath, databasePath2);
            for (String str : DATABASE_EXTRA_FILES) {
                map.put(new File(databasePath.getPath() + str), new File(databasePath2.getPath() + str));
            }
        }
        return map;
    }
}
