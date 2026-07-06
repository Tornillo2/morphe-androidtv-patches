package com.amazonaws.mobileconnectors.remoteconfiguration.internal.net;

import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.model.RemoteConfiguration;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@FunctionalInterface
public interface RemoteConfigurationFetcher {
    RemoteConfiguration fetch(String str, Attributes attributes, String str2, String str3);
}
