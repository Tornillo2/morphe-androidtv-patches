package com.amazon.minerva.client.thirdparty.api;

import android.util.Log;
import com.amazon.minerva.client.thirdparty.metric.DataPoint;
import com.amazon.minerva.client.thirdparty.metric.Timestamp;
import com.amazon.minerva.client.thirdparty.metric.TypedValue;
import com.amazon.minerva.client.thirdparty.metric.ValueType;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class MetricEvent {
    public static final Pattern KEY_VALIDATION_PATTERN = Pattern.compile("^[a-zA-Z]$|^[a-zA-Z][a-zA-Z0-9_.]*[^.]$");
    public static final Pattern PATTERN_METRIC_GROUP_ID = Pattern.compile("[0-9a-z]{8}");
    public static final Pattern PATTERN_SCHEMA_ID = Pattern.compile("[0-9a-z]{4}/\\d+/[0-9a-f]{8}");
    public static final String TAG = "MetricEvent";
    public Timestamp mClientTimestamp;
    public Map<String, TypedValue<?>> mKeyValuePairs;
    public UUID mMetricEventId;
    public String mMetricGroupId;
    public int mNumInvalidKeyValuePairs;
    public int mSamplingRate;
    public String mSchemaId;

    public MetricEvent(String str, String str2, int i) {
        this(str, str2);
        this.mSamplingRate = i;
    }

    public boolean IsCustomKeyValid(String str) {
        if (str == null) {
            Log.e(TAG, "The key for adding custom key value pair should not be null.");
            this.mNumInvalidKeyValuePairs++;
            return false;
        }
        if (str.isEmpty()) {
            Log.e(TAG, "The key for adding custom key value pair should not be empty.");
            this.mNumInvalidKeyValuePairs++;
            return false;
        }
        if (str.startsWith(Attributes.PREDEFINED_ATTRIBUTE_PREFIX)) {
            Log.e(TAG, "The key for adding custom key value pair should not start with underscore.");
            this.mNumInvalidKeyValuePairs++;
            return false;
        }
        if (KEY_VALIDATION_PATTERN.matcher(str).find()) {
            return true;
        }
        Log.e(TAG, "The key for adding custom key value pair should only contain alphanumeric, underscore and period. The first character can only be alphabet and the last character can only not be period");
        this.mNumInvalidKeyValuePairs++;
        return false;
    }

    public void addBoolean(String str, boolean z) {
        if (IsCustomKeyValid(str)) {
            this.mKeyValuePairs.put(str, ValueType.BOOLEAN.of(Boolean.valueOf(z)));
        }
    }

    public void addDouble(String str, double d) {
        if (IsCustomKeyValid(str)) {
            this.mKeyValuePairs.put(str, ValueType.FLOAT.of(Double.valueOf(d)));
        }
    }

    public void addLong(String str, long j) {
        if (IsCustomKeyValid(str)) {
            this.mKeyValuePairs.put(str, ValueType.INTEGER.of(Long.valueOf(j)));
        }
    }

    public void addPredefined(Predefined predefined) {
        if (predefined == null) {
            Log.e(TAG, "The arguments for adding predefined key value pairs should not be null.");
            this.mNumInvalidKeyValuePairs++;
        } else {
            if (this.mKeyValuePairs.containsKey(predefined.key)) {
                return;
            }
            this.mKeyValuePairs.put(predefined.key, ValueType.SYMBOL.of(""));
        }
    }

    public void addString(String str, String str2) {
        if (str2 == null || str2.isEmpty()) {
            Log.e(TAG, "The second parameter for adding String key-value pair should not be null or empty String.");
            this.mNumInvalidKeyValuePairs++;
        } else if (IsCustomKeyValid(str)) {
            this.mKeyValuePairs.put(str, ValueType.SYMBOL.of(str2));
        }
    }

    public void addTimestamp(String str, Date date) {
        addTimestamp(str, date, TimeZone.getDefault());
    }

    public void clear() {
        this.mKeyValuePairs.clear();
        this.mMetricEventId = UUID.randomUUID();
        this.mClientTimestamp = null;
        this.mNumInvalidKeyValuePairs = 0;
    }

    public Timestamp getClientTimestamp() {
        return this.mClientTimestamp;
    }

    public int getKeyValuePairCount() {
        return this.mKeyValuePairs.size();
    }

    public Map<String, TypedValue<?>> getKeyValuePairs() {
        return this.mKeyValuePairs;
    }

    public List<DataPoint> getKeyValuePairsAsDataPoints() {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, TypedValue<?>> entry : this.mKeyValuePairs.entrySet()) {
            arrayList.add(new DataPoint(entry.getKey(), entry.getValue().getType(), entry.getValue().getValueAsString()));
        }
        return arrayList;
    }

    public UUID getMetricEventId() {
        return this.mMetricEventId;
    }

    public String getMetricGroupId() {
        return this.mMetricGroupId;
    }

    public int getNumInvalidKeyValuePairs() {
        return this.mNumInvalidKeyValuePairs;
    }

    public int getSamplingRate() {
        return this.mSamplingRate;
    }

    public String getSchemaId() {
        return this.mSchemaId;
    }

    public void setClientTimestamp(Timestamp timestamp) {
        this.mClientTimestamp = timestamp;
    }

    public void addTimestamp(String str, Date date, TimeZone timeZone) {
        if (date == null || timeZone == null) {
            Log.e(TAG, "The arguments for adding Timestamp key-value pair should not be null.");
            this.mNumInvalidKeyValuePairs++;
        } else if (IsCustomKeyValid(str)) {
            this.mKeyValuePairs.put(str, ValueType.TIMESTAMP.of(Timestamp.of(date.getTime(), timeZone)));
        }
    }

    public MetricEvent(String str, String str2) {
        this.mNumInvalidKeyValuePairs = 0;
        Objects.requireNonNull(str, "metricGroupId cannot be null.");
        Objects.requireNonNull(str2, "schemaId cannot be null.");
        if (PATTERN_METRIC_GROUP_ID.matcher(str).matches()) {
            if (PATTERN_SCHEMA_ID.matcher(str2).matches()) {
                this.mMetricGroupId = str;
                this.mSchemaId = str2;
                this.mKeyValuePairs = new LinkedHashMap();
                this.mMetricEventId = UUID.randomUUID();
                this.mSamplingRate = -1;
                return;
            }
            throw new IllegalArgumentException("Invalid argument for schemaId, validation failed.");
        }
        throw new IllegalArgumentException("Invalid argument for metricGroupId, validation failed.");
    }
}
