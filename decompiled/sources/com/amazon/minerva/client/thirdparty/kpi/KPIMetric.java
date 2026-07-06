package com.amazon.minerva.client.thirdparty.kpi;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public enum KPIMetric {
    KEY_VALUE_PAIR_FAILED_VALIDATION("DroppedMetricEvent.API.KeyValuePairFailedValidation"),
    KEY_VALUE_PAIR_COUNT_EXCEEDED_MAX("DroppedMetricEvent.API.KeyValuePairCountExceededMax"),
    KEY_SIZE_EXCEEDED_MAX("DroppedMetricEvent.API.KeySizeExceededMax"),
    VALUE_SIZE_EXCEEDED_MAX("DroppedMetricEvent.API.ValueSizeExceededMax"),
    METRIC_EVENT_SIZE_EXCEEDED_MAX("DroppedMetricEvent.API.MetricEventSizeExceededMax"),
    METRIC_EVENT_HAS_NO_KEY_VALUE_PAIR("DroppedMetricEvent.API.MetricEventHasNoKeyValuePair"),
    API_VALIDATION("DroppedMetricEvent.API.Validation"),
    SAMPLE("DroppedMetricEvent.API.Sample"),
    THROTTLE("DroppedMetricEvent.API.Throttle"),
    BUFFER_QUEUE("DroppedMetricEvent.API.BufferQueue"),
    REMOTE_EXCEPTION("DroppedMetricEvent.API.RemoteException"),
    IPC("ReceivedMetricEvent.Service.IPC"),
    DENYLIST("DroppedMetricEvent.Service.Denylist"),
    CHILD_PROFILE("DroppedMetricEvent.Service.ChildProfile"),
    USER_CONTROL("DroppedMetricEvent.Service.UserControl"),
    RECORD_ON_DISK("ReceivedMetricEvent.Service.RecordOnDisk"),
    DISK_EXCEEDED_MAX("DroppedMetricEvent.Service.DiskExceededMax"),
    NUMBER_OF_FILES_EXCEEDED_MAX("DroppedMetricEvent.Service.NumberOfFilesExceededMax"),
    TTL_EXCEEDED_MAX("DroppedMetricEvent.Service.TTLExceededMax"),
    UPLOAD_CLIENT_ERROR("DroppedMetricEvent.Service.UploadClientError"),
    UPLOAD_SERVER_ERROR("Diagnostic.Service.UploadServerError"),
    UPLOAD_CONNECTION_ERROR("Diagnostic.Service.UploadConnectionError"),
    UPLOAD_UNEXPECTED_ERROR("Diagnostic.Service.UploadUnexpectedError"),
    BATCH_CREATED("MetricBatch.Service.Created"),
    BATCH_IOEXCEPTION_DROPPED("MetricBatch.Service.IOExceptionDropped"),
    BATCH_DISK_EXCEEDED_MAX("MetricBatch.Service.DiskExceededMax"),
    BATCH_NUMBER_OF_FILES_EXCEEDED_MAX("MetricBatch.Service.NumberOfFilesExceededMax"),
    BATCH_TTL_DROPPED("MetricBatch.Service.TTLDropped"),
    BATCH_UPLOADED("MetricBatch.Service.Uploaded"),
    BATCH_UPLOAD_SUCCESSFUL("MetricBatch.Service.UploadSuccessful"),
    BATCH_UPLOAD_RETRIABLE("MetricBatch.Service.UploadRetriable"),
    BATCH_UPLOAD_NON_RETRIABLE("MetricBatch.Service.UploadNonRetriable");

    public final String metricName;

    KPIMetric(String str) {
        this.metricName = str;
    }

    public String getMetricName() {
        return this.metricName;
    }
}
