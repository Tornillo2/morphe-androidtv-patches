package com.amazon.livingroom.deviceproperties;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.amazon.livingroom.deviceproperties.OverridableDeviceProperties;
import com.amazon.livingroom.di.ApplicationContext;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.reporting.Log;
import j$.util.DesugarCollections;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import javax.inject.Inject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public class LocalOverridesProvider implements OverridableDeviceProperties.OverridesProvider {
    public static final String FILE_NAME = "local-overrides.properties";
    public static final String LOG_TAG = "LocalOverridesProvider";
    public final Context context;
    public Properties overrides;

    @Inject
    public LocalOverridesProvider(@NonNull @ApplicationContext Context context) {
        this.context = context;
    }

    @Override // com.amazon.livingroom.deviceproperties.OverridableDeviceProperties.OverridesProvider
    @NonNull
    public synchronized Map<String, String> getOverrides() {
        return DesugarCollections.unmodifiableMap(loadOverrides());
    }

    public final Properties loadOverrides() {
        if (this.overrides == null) {
            this.overrides = new Properties();
            File file = new File(this.context.getFilesDir(), FILE_NAME);
            try {
                InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
                try {
                    Log.w(LOG_TAG, "Loading device properties overrides from " + file);
                    this.overrides.load(inputStreamReader);
                    for (Map.Entry entry : this.overrides.entrySet()) {
                        Log.w(LOG_TAG, String.format(Locale.US, "Property '%s' is overridden to '%s'", entry.getKey(), entry.getValue()));
                    }
                    inputStreamReader.close();
                } finally {
                }
            } catch (FileNotFoundException unused) {
            } catch (IOException e) {
                throw new RuntimeException("Failed to read device properties overrides from " + file, e);
            }
        }
        return this.overrides;
    }

    public final void saveOverrides() {
        File file = new File(this.context.getFilesDir(), FILE_NAME);
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
            try {
                this.overrides.store(outputStreamWriter, (String) null);
                outputStreamWriter.close();
            } finally {
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to write device properties overrides to " + file, e);
        }
    }

    public synchronized void setOverrides(Bundle bundle) {
        try {
            this.overrides = bundle.getBoolean("CLEAR_OVERRIDES") ? new Properties() : loadOverrides();
            for (String str : bundle.keySet()) {
                if (str != null && str.startsWith("OVERRIDE_")) {
                    String strSubstring = str.substring(9);
                    String string = bundle.getString(str);
                    if (string != null) {
                        this.overrides.setProperty(strSubstring, string);
                    }
                }
            }
            saveOverrides();
        } catch (Throwable th) {
            throw th;
        }
    }
}
