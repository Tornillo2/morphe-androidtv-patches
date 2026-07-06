package com.amazonaws.mobileconnectors.remoteconfiguration.internal.model;

import com.amazonaws.mobileconnectors.remoteconfiguration.Configuration;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public interface RemoteConfiguration {
    public static final int ORIGIN_DEFAULT = 1;
    public static final int ORIGIN_OVERWRITTEN = 3;
    public static final int ORIGIN_SYNCED = 2;

    String getAppConfigurationId();

    Configuration getConfiguration();

    String getEntityTag();

    int getOrigin();

    boolean isUpdate();
}
