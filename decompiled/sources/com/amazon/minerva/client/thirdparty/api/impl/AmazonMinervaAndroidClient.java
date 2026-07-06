package com.amazon.minerva.client.thirdparty.api.impl;

import android.content.Context;
import com.amazon.minerva.client.thirdparty.MinervaServiceAndroidAdapter;
import com.amazon.minerva.client.thirdparty.api.AggregatedMetricEvent;
import com.amazon.minerva.client.thirdparty.api.MetricEvent;
import com.amazon.minerva.client.thirdparty.api.callback.MetricRecordCallback;
import com.amazon.minerva.client.thirdparty.api.callback.MetricRecordStatus;
import com.amazon.minerva.client.thirdparty.api.callback.NoOpMetricRecordCallback;
import com.amazon.minerva.client.thirdparty.configuration.ThrottleConfiguration;
import com.amazon.minerva.client.thirdparty.configuration.ValidationConfiguration;
import com.amazon.minerva.client.thirdparty.kpi.KPIMetric;
import com.amazon.minerva.client.thirdparty.metric.Timestamp;
import com.amazon.minerva.client.thirdparty.metric.TypedValue;
import com.amazon.minerva.client.thirdparty.sample.MetricEventSampler;
import com.amazon.minerva.client.thirdparty.throttle.MetricEventThrottler;
import com.amazon.minerva.client.thirdparty.utils.MinervaLogger;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class AmazonMinervaAndroidClient {
    public static final MinervaLogger log = new MinervaLogger("AmazonMinervaAndroidClient");
    public static final NoOpMetricRecordCallback noOpCallback = new NoOpMetricRecordCallback();
    public final Context context;
    public final String deviceType;
    public MetricEventSampler mMetricEventSampler;
    public MetricEventThrottler mMetricEventThrottler;
    public final MinervaServiceAndroidAdapter mMinervaServiceAndroidAdapter;
    public ThrottleConfiguration mThrottleConfiguration;
    public ValidationConfiguration mValidationConfiguration;
    public final String region;

    public AmazonMinervaAndroidClient(Context context, MinervaServiceAndroidAdapter minervaServiceAndroidAdapter, String str, String str2) {
        this.context = context;
        this.mMinervaServiceAndroidAdapter = minervaServiceAndroidAdapter;
        this.region = str;
        this.deviceType = str2;
        this.mThrottleConfiguration = minervaServiceAndroidAdapter.getMinervaServiceManager().getConfigurationManager().getMetricsConfigurationHelper().getThrottleConfiguration();
        this.mValidationConfiguration = minervaServiceAndroidAdapter.getMinervaServiceManager().getConfigurationManager().getMetricsConfigurationHelper().getValidationConfiguration();
        this.mMetricEventThrottler = MetricEventThrottler.getInstance(minervaServiceAndroidAdapter);
        this.mMetricEventSampler = MetricEventSampler.getInstance(minervaServiceAndroidAdapter);
    }

    public void flush() {
        this.mMinervaServiceAndroidAdapter.flush();
    }

    public final boolean isMetricEventValid(MetricEvent metricEvent) {
        try {
            int maxKeyValuePairCount = this.mValidationConfiguration.getMaxKeyValuePairCount();
            int maxKeySizeBytes = this.mValidationConfiguration.getMaxKeySizeBytes();
            int maxValueSizeBytes = this.mValidationConfiguration.getMaxValueSizeBytes();
            int maxMetricEventSizeBytes = this.mValidationConfiguration.getMaxMetricEventSizeBytes();
            if (metricEvent.getNumInvalidKeyValuePairs() > 0) {
                log.error(String.format(Locale.US, "Metric Event contains %d invalid key value pairs. ", Integer.valueOf(metricEvent.getNumInvalidKeyValuePairs()), metricEvent.getMetricGroupId(), metricEvent.getSchemaId()));
                reportKPI(KPIMetric.KEY_VALUE_PAIR_FAILED_VALIDATION.metricName, metricEvent.getMetricGroupId());
                reportKPI(KPIMetric.API_VALIDATION.metricName, metricEvent.getMetricGroupId());
                return false;
            }
            if (metricEvent.getKeyValuePairCount() == 0) {
                log.error(String.format("Metric Event does not contain any key value pairs.", "metricGroup: %s, schemaId %s", metricEvent.getMetricGroupId(), metricEvent.getSchemaId()));
                reportKPI(KPIMetric.METRIC_EVENT_HAS_NO_KEY_VALUE_PAIR.metricName, metricEvent.getMetricGroupId());
                reportKPI(KPIMetric.API_VALIDATION.metricName, metricEvent.getMetricGroupId());
                return false;
            }
            if (metricEvent.getKeyValuePairCount() > maxKeyValuePairCount) {
                log.error(String.format(Locale.US, "Metric Event contains %d custom key value pairs, exceeded the limit %d. metricGroup: %s, schemaId %s", Integer.valueOf(metricEvent.getKeyValuePairCount()), Integer.valueOf(maxKeyValuePairCount), metricEvent.getMetricGroupId(), metricEvent.getSchemaId()));
                reportKPI(KPIMetric.KEY_VALUE_PAIR_COUNT_EXCEEDED_MAX.metricName, metricEvent.getMetricGroupId());
                reportKPI(KPIMetric.API_VALIDATION.metricName, metricEvent.getMetricGroupId());
                return false;
            }
            int length = 0;
            for (Map.Entry<String, TypedValue<?>> entry : metricEvent.getKeyValuePairs().entrySet()) {
                String key = entry.getKey();
                if (!key.startsWith(Attributes.PREDEFINED_ATTRIBUTE_PREFIX)) {
                    if (key.length() > maxKeySizeBytes) {
                        log.error(String.format(Locale.US, "Metric Event has key '%s' exceeding the maximum size %d. metricGroup: %s, schemaId %s", key, Integer.valueOf(maxKeySizeBytes), metricEvent.getMetricGroupId(), metricEvent.getSchemaId()));
                        reportKPI(KPIMetric.KEY_SIZE_EXCEEDED_MAX.metricName, metricEvent.getMetricGroupId());
                        reportKPI(KPIMetric.API_VALIDATION.metricName, metricEvent.getMetricGroupId());
                        return false;
                    }
                    TypedValue<?> value = entry.getValue();
                    if (value.getSizeInBytes() > maxValueSizeBytes) {
                        log.error(String.format(Locale.US, "Metric Event has key '%s', its value size is %d, exceeded the limit %d. metricGroup: %s, schemaId %s", key, Integer.valueOf(value.getSizeInBytes()), Integer.valueOf(maxValueSizeBytes), metricEvent.getMetricGroupId(), metricEvent.getSchemaId()));
                        reportKPI(KPIMetric.VALUE_SIZE_EXCEEDED_MAX.metricName, metricEvent.getMetricGroupId());
                        reportKPI(KPIMetric.API_VALIDATION.metricName, metricEvent.getMetricGroupId());
                        return false;
                    }
                    length += key.length() + value.getSizeInBytes();
                }
            }
            if (length <= maxMetricEventSizeBytes) {
                return true;
            }
            log.error(String.format(Locale.US, "Metric Event size is %d, exceeded the limit %d. metricGroup: %s, schemaId %s", Integer.valueOf(length), Integer.valueOf(maxMetricEventSizeBytes), metricEvent.getMetricGroupId(), metricEvent.getSchemaId()));
            reportKPI(KPIMetric.METRIC_EVENT_SIZE_EXCEEDED_MAX.metricName, metricEvent.getMetricGroupId());
            reportKPI(KPIMetric.API_VALIDATION.metricName, metricEvent.getMetricGroupId());
            return false;
        } catch (Exception e) {
            log.warn("validation for metric event meet exception: " + e + "Drop the metric event (can't record)");
            return false;
        }
    }

    public void record(MetricEvent metricEvent) {
        recordInternal(metricEvent, noOpCallback);
    }

    public final void recordInternal(MetricEvent metricEvent, MetricRecordCallback metricRecordCallback) {
        if (metricEvent instanceof AggregatedMetricEvent) {
            ((AggregatedMetricEvent) metricEvent).convertAggregatedToKeyValuePairs();
        }
        if (!isMetricEventValid(metricEvent)) {
            MetricRecordStatus metricRecordStatus = MetricRecordStatus.INVALID_METRIC_EVENT;
            metricRecordCallback.getClass();
            return;
        }
        try {
            if (this.mThrottleConfiguration.getThrottleSwitch() == 1 && this.mMetricEventThrottler.shouldThrottleMetricEvent(metricEvent)) {
                reportKPI(KPIMetric.THROTTLE.metricName, metricEvent.getMetricGroupId());
                log.debug(String.format("metricEvent was throttled. metricGroupId: %s, schemaId: %s", metricEvent.getMetricGroupId(), metricEvent.getSchemaId()));
                MetricRecordStatus metricRecordStatus2 = MetricRecordStatus.THROTTLED;
                metricRecordCallback.getClass();
                return;
            }
            if (this.mMetricEventSampler.shouldSampleMetricEvent(metricEvent)) {
                metricEvent.setClientTimestamp(Timestamp.now());
                this.mMinervaServiceAndroidAdapter.record(this.region, metricEvent.getMetricGroupId(), metricEvent.getSchemaId(), metricEvent.getMetricEventId().toString(), metricEvent.getClientTimestamp().epochMillis, (int) TimeUnit.MILLISECONDS.toMinutes(metricEvent.getClientTimestamp().getTimeZoneOffset()), metricEvent.getKeyValuePairsAsDataPoints());
                metricEvent.clear();
            } else {
                reportKPI(KPIMetric.SAMPLE.metricName, metricEvent.getMetricGroupId());
                log.debug(String.format("metricEvent was dropped by sampling. metricGroupId: %s, schemaId: %s", metricEvent.getMetricGroupId(), metricEvent.getSchemaId()));
                MetricRecordStatus metricRecordStatus3 = MetricRecordStatus.SAMPLED;
                metricRecordCallback.getClass();
            }
        } catch (Exception e) {
            log.error("Exception happens during metric event record: " + e);
        }
    }

    public final void reportKPI(String str, String str2) {
        if (this.mMinervaServiceAndroidAdapter.getPeriodicKPIReporter() != null) {
            this.mMinervaServiceAndroidAdapter.getPeriodicKPIReporter().report(str, str2, 1L);
        }
    }

    @Deprecated
    public void shutdown() {
        log.info("Shutting down the Minerva Client and write metrics from memory to disk.");
        this.mMinervaServiceAndroidAdapter.shutdown();
    }

    @Deprecated
    public void shutdownWithUpload() {
        log.info("Shutting down the Minerva Client and write metrics from memory to disk then trigger upload.");
        this.mMinervaServiceAndroidAdapter.shutdownWithUpload();
    }

    public Future<Void> upload() {
        return this.mMinervaServiceAndroidAdapter.upload();
    }

    public void record(MetricEvent metricEvent, MetricRecordCallback metricRecordCallback) {
        recordInternal(metricEvent, metricRecordCallback);
    }
}
