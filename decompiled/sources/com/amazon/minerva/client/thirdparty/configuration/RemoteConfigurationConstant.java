package com.amazon.minerva.client.thirdparty.configuration;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public enum RemoteConfigurationConstant {
    THROTTLE_SWITCH(0),
    MAX_THROTTLE_CREDIT(1),
    DEFAULT_THROTTLE_CREDIT_HOUR(2),
    DEFAULT_SAMPLING_RATE(3),
    MAX_KEY_VALUE_PAIR_COUNT(4),
    MAX_KEY_SIZE(5),
    MAX_VALUE_SIZE(6),
    MAX_METRIC_EVENT_SIZE(7);

    public final int index;

    RemoteConfigurationConstant(int i) {
        this.index = i;
    }

    public int getIndex() {
        return this.index;
    }
}
