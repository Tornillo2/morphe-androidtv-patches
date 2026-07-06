package com.amazon.ignitionshared.migration;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.amazon.ignitionshared.EngineAssetsHashKeys;
import java.io.File;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class MigrationManager {
    public static final String TAG = "MigrationManager";
    public final File applicationDataDir;
    public final SharedPreferences preferences;

    public MigrationManager(Context context) {
        this.applicationDataDir = new File(context.getApplicationInfo().dataDir);
        this.preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public final void cleanupEngineHash() {
        this.preferences.edit().remove(EngineAssetsHashKeys.IGNITION_PLUGINS_HASH_KEY).apply();
    }

    public final void deleteRecursive(File file, List<File> list) {
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                if (!list.contains(file2)) {
                    deleteRecursive(file2, list);
                }
            }
        }
        file.delete();
    }

    public File getApplicationDataDir() {
        return this.applicationDataDir;
    }

    public abstract File getCleanupDir();

    public abstract List<File> getExceptionFiles();

    public abstract String getPreviousEngineHashKey();

    public void migrate() {
        if (this.preferences.getString(EngineAssetsHashKeys.IGNITION_PLUGINS_HASH_KEY, null) == null) {
            return;
        }
        deleteRecursive(getCleanupDir(), getExceptionFiles());
        cleanupEngineHash();
    }

    public abstract boolean migrateUserData();
}
