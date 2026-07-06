package com.amazonaws.mobileconnectors.remoteconfiguration.internal.model;

import com.amazonaws.mobileconnectors.remoteconfiguration.Configuration;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class RemoteConfigurationImpl implements RemoteConfiguration {
    public final String mAppConfigurationId;
    public final Configuration mConfiguration;
    public final String mEntityTag;
    public final int mOrigin;
    public final boolean mUpdate;

    public RemoteConfigurationImpl(Configuration configuration, String str, int i, String str2, boolean z) {
        if (str == null) {
            throw new NullPointerException("The Application Configuration ID may not be null");
        }
        if (i != 1 && i != 2 && i != 3) {
            throw new IllegalArgumentException("Invalid configuration origin.");
        }
        this.mConfiguration = configuration;
        this.mAppConfigurationId = str;
        this.mOrigin = i;
        this.mEntityTag = str2;
        this.mUpdate = z;
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.internal.model.RemoteConfiguration
    public String getAppConfigurationId() {
        return this.mAppConfigurationId;
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.internal.model.RemoteConfiguration
    public Configuration getConfiguration() {
        return this.mConfiguration;
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.internal.model.RemoteConfiguration
    public String getEntityTag() {
        return this.mEntityTag;
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.internal.model.RemoteConfiguration
    public int getOrigin() {
        return this.mOrigin;
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.internal.model.RemoteConfiguration
    public boolean isUpdate() {
        return this.mUpdate;
    }
}
