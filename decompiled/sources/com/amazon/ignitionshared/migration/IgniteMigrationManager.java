package com.amazon.ignitionshared.migration;

import android.content.Context;
import com.amazon.ignitionshared.EngineAssetsHashKeys;
import com.amazon.livingroom.di.ApplicationContext;
import com.amazon.livingroom.di.ApplicationScope;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public class IgniteMigrationManager extends MigrationManager {
    public static final String PATH_TO_PLUGIN_DIR = "/plugins";
    public static final String PATH_TO_STORAGE_DIR = "/plugins/com.amazon.ignition.framework.storage/var/data/buckets/";

    @Inject
    public IgniteMigrationManager(@ApplicationContext Context context) {
        super(context);
    }

    @Override // com.amazon.ignitionshared.migration.MigrationManager
    public File getCleanupDir() {
        return new File(getApplicationDataDir(), PATH_TO_PLUGIN_DIR);
    }

    @Override // com.amazon.ignitionshared.migration.MigrationManager
    public List<File> getExceptionFiles() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new File(getApplicationDataDir(), PATH_TO_STORAGE_DIR));
        return arrayList;
    }

    @Override // com.amazon.ignitionshared.migration.MigrationManager
    public String getPreviousEngineHashKey() {
        return EngineAssetsHashKeys.IGNITION_PLUGINS_HASH_KEY;
    }

    @Override // com.amazon.ignitionshared.migration.MigrationManager
    public boolean migrateUserData() {
        return true;
    }
}
