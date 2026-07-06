package com.amazon.minerva.client.thirdparty.sample;

import com.amazon.minerva.client.thirdparty.MinervaServiceAndroidAdapter;
import com.amazon.minerva.client.thirdparty.api.MetricEvent;
import com.amazon.minerva.client.thirdparty.configuration.SamplingConfiguration;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class MetricEventSampler {
    public static volatile MetricEventSampler sMetricEventSampler;
    public MinervaServiceAndroidAdapter mMinervaServiceAndroidAdapter;
    public SamplingConfiguration mSamplingconfiguration;

    public MetricEventSampler(MinervaServiceAndroidAdapter minervaServiceAndroidAdapter) {
        this.mMinervaServiceAndroidAdapter = minervaServiceAndroidAdapter;
    }

    public static MetricEventSampler getInstance(MinervaServiceAndroidAdapter minervaServiceAndroidAdapter) {
        if (sMetricEventSampler == null) {
            synchronized (MetricEventSampler.class) {
                try {
                    if (sMetricEventSampler == null) {
                        sMetricEventSampler = new MetricEventSampler(minervaServiceAndroidAdapter);
                    }
                } finally {
                }
            }
        }
        return sMetricEventSampler;
    }

    public SamplingConfiguration getSamplingConfiguration() {
        SamplingConfiguration samplingConfiguration = this.mMinervaServiceAndroidAdapter.getMinervaServiceManager().getConfigurationManager().getMetricsConfigurationHelper().getSamplingConfiguration();
        this.mSamplingconfiguration = samplingConfiguration;
        return samplingConfiguration;
    }

    public boolean shouldSampleMetricEvent(MetricEvent metricEvent) {
        int samplingRate = metricEvent.getSamplingRate();
        if (samplingRate < 0 || samplingRate > 100) {
            samplingRate = getSamplingConfiguration().getDefaultSamplingRate();
        }
        return samplingRate > LocalSamplingKeyGenerator.getLocalSamplingKey();
    }
}
