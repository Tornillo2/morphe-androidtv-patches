package com.amazon.minerva.client.thirdparty.kpi;

import android.util.Log;
import com.amazon.ion.IonException;
import com.amazon.minerva.client.thirdparty.configuration.MetricsConfigurationHelper;
import com.amazon.minerva.client.thirdparty.metric.IonMetricEvent;
import com.amazon.minerva.client.thirdparty.serializer.MetricBatchSerializer;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class ServiceKPIReporter extends AbstractKPIReporter {
    public static final String TAG = "StorageManager";
    public final MetricBatchSerializer mMetricBatchSerializer;
    public final MetricsConfigurationHelper mMetricsConfigurationHelper;

    public ServiceKPIReporter(MetricsConfigurationHelper metricsConfigurationHelper) {
        this.mMetricsConfigurationHelper = metricsConfigurationHelper;
        this.mMetricBatchSerializer = metricsConfigurationHelper.getUploadConfiguration().createMetricBatchSerializer();
    }

    public void report(KPIMetric kPIMetric, byte[] bArr) {
        try {
            Iterator<IonMetricEvent> it = this.mMetricBatchSerializer.deserialize(bArr).iterator();
            while (it.hasNext()) {
                report(kPIMetric.metricName, it.next().getMetricGroupId().stringValue(), 1L);
            }
        } catch (IonException e) {
            e = e;
            Log.e(TAG, "Error happens when converting Ion Binary to list of IonMetricEvent.", e);
        } catch (IOException e2) {
            e = e2;
            Log.e(TAG, "Error happens when converting Ion Binary to list of IonMetricEvent.", e);
        } catch (Exception e3) {
            Log.e(TAG, "Unexpected error when converting Ion Binary to list of IonMetricEvent.", e3);
        }
    }

    @Override // com.amazon.minerva.client.thirdparty.kpi.AbstractKPIReporter
    public void shutdown() {
    }
}
