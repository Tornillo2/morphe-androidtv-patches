package com.amazon.minerva.client.thirdparty.api.impl;

import android.content.Context;
import com.amazon.minerva.client.thirdparty.MinervaServiceAndroidAdapter;
import com.amazon.minerva.client.thirdparty.api.AmazonMinervaV2;
import com.amazon.minerva.client.thirdparty.api.MetricEvent;
import com.amazon.minerva.client.thirdparty.api.callback.MetricRecordCallback;
import java.util.concurrent.Future;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class AmazonMinervaV2Impl extends AmazonMinervaImpl implements AmazonMinervaV2 {
    public AmazonMinervaV2Impl(Context context, MinervaServiceAndroidAdapter minervaServiceAndroidAdapter, String str, String str2) {
        super(context, minervaServiceAndroidAdapter, str, str2);
    }

    @Override // com.amazon.minerva.client.thirdparty.api.AmazonMinervaV2
    public void record(MetricEvent metricEvent, MetricRecordCallback metricRecordCallback) {
        this.mAmazonMinervaClient.record(metricEvent, metricRecordCallback);
    }

    @Override // com.amazon.minerva.client.thirdparty.api.AmazonMinervaV2
    public Future<Void> upload() {
        return this.mAmazonMinervaClient.upload();
    }
}
