package com.amazon.minerva.client.thirdparty.api.impl;

import android.content.Context;
import com.amazon.minerva.client.thirdparty.MinervaServiceAndroidAdapter;
import com.amazon.minerva.client.thirdparty.api.AmazonMinerva;
import com.amazon.minerva.client.thirdparty.api.MetricEvent;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class AmazonMinervaImpl implements AmazonMinerva {
    public AmazonMinervaAndroidClient mAmazonMinervaClient;

    public AmazonMinervaImpl(Context context, MinervaServiceAndroidAdapter minervaServiceAndroidAdapter, String str, String str2) {
        this.mAmazonMinervaClient = new AmazonMinervaAndroidClient(context, minervaServiceAndroidAdapter, str, str2);
    }

    @Override // com.amazon.minerva.client.thirdparty.api.AmazonMinerva
    public void flush() {
        this.mAmazonMinervaClient.flush();
    }

    @Override // com.amazon.minerva.client.thirdparty.api.AmazonMinerva
    public void record(MetricEvent metricEvent) {
        this.mAmazonMinervaClient.record(metricEvent);
    }

    @Override // com.amazon.minerva.client.thirdparty.api.AmazonMinerva
    @Deprecated
    public void shutdown() {
        this.mAmazonMinervaClient.shutdown();
    }

    @Override // com.amazon.minerva.client.thirdparty.api.AmazonMinerva
    @Deprecated
    public void shutdownWithUpload() {
        this.mAmazonMinervaClient.shutdownWithUpload();
    }
}
