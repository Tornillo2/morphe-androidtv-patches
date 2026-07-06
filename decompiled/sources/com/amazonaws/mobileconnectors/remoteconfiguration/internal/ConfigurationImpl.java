package com.amazonaws.mobileconnectors.remoteconfiguration.internal;

import com.amazonaws.mobileconnectors.remoteconfiguration.Configuration;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class ConfigurationImpl implements Configuration {
    public final String json;
    public final Date timestamp;

    public ConfigurationImpl(String str) {
        this(str, null);
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.Configuration
    public JSONObject getAsJsonObject() {
        try {
            return new JSONObject(this.json);
        } catch (JSONException e) {
            throw new IllegalStateException("The configuration is invalid.", e);
        }
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.Configuration
    public String getAsJsonString() {
        return this.json;
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.Configuration
    public Date getTimestamp() {
        return this.timestamp;
    }

    public ConfigurationImpl(String str, Date date) {
        if (str == null) {
            throw new NullPointerException("The JSON may not be null.");
        }
        this.json = str;
        this.timestamp = date;
    }
}
