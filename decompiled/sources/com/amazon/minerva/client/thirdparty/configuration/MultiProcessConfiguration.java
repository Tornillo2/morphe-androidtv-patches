package com.amazon.minerva.client.thirdparty.configuration;

import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class MultiProcessConfiguration {
    public String mMainProcessName;
    public Set<String> mSecondaryProcessNames;

    public MultiProcessConfiguration(String str, Set<String> set) {
        this.mMainProcessName = str;
        this.mSecondaryProcessNames = set;
    }

    public String getMainProcessName() {
        return this.mMainProcessName;
    }

    public boolean isSecondaryProcessNameOnList(String str) {
        Set<String> set = this.mSecondaryProcessNames;
        if (set != null) {
            return set.contains(str);
        }
        return false;
    }
}
