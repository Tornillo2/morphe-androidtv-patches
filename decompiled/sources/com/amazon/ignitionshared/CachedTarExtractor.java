package com.amazon.ignitionshared;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.preference.PreferenceManager;
import androidx.core.app.ActivityCompat$$ExternalSyntheticOutline0;
import com.amazon.livingroom.di.ApplicationContext;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.livingroom.di.Names;
import com.amazon.reporting.Log;
import java.io.File;
import javax.inject.Inject;
import javax.inject.Named;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public class CachedTarExtractor {
    public static final String TAG = "CachedTarExtractor";
    public final AssetManager assetManager;
    public final String fileHash;
    public final String fileToExtract;
    public final String hashKey;
    public final File outputPath;
    public final SharedPreferences sharedPreferences;

    @Inject
    public CachedTarExtractor(@ApplicationContext Context context, @Named(Names.IGNITE_ASSETS_ARCHIVE_NAME) String str, @Named(Names.IGNITE_ASSETS_ARCHIVE_HASH) String str2, @Named(Names.IGNITE_EXTRACTED_ASSETS_HASH_KEY) String str3, @Named(Names.IGNITE_ASSETS_ARCHIVE_EXTRACTION_DIR) File file) {
        this.assetManager = context.getAssets();
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.fileToExtract = str;
        this.fileHash = str2;
        this.hashKey = str3;
        this.outputPath = file;
    }

    public void extractIfNew() {
        if (this.fileHash.equals(this.sharedPreferences.getString(this.hashKey, null))) {
            Log.i(TAG, "Extraction not performed. Using cached files");
            return;
        }
        if (!AssetExtractor.extractGzAsset(this.assetManager, this.fileToExtract, this.outputPath.getAbsolutePath())) {
            String strM = ActivityCompat$$ExternalSyntheticOutline0.m(new StringBuilder("Extraction of "), this.fileToExtract, " was unsuccessful");
            Log.e(TAG, strM);
            throw new RuntimeException(strM);
        }
        this.sharedPreferences.edit().putString(this.hashKey, this.fileHash).apply();
        Log.i(TAG, "Extraction of " + this.fileToExtract + " was successful");
    }
}
