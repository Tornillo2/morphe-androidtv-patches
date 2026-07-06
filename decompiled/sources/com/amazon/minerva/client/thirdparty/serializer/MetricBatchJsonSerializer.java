package com.amazon.minerva.client.thirdparty.serializer;

import android.util.Log;
import com.amazon.ion.IonBool;
import com.amazon.ion.IonFloat;
import com.amazon.ion.IonInt;
import com.amazon.ion.IonList;
import com.amazon.ion.IonStruct;
import com.amazon.ion.IonSymbol;
import com.amazon.ion.IonSystem;
import com.amazon.ion.IonTimestamp;
import com.amazon.ion.IonType;
import com.amazon.ion.IonValue;
import com.amazon.ion.Timestamp;
import com.amazon.ion.ValueFactory;
import com.amazon.ion.system.IonSystemBuilder;
import com.amazon.minerva.client.thirdparty.api.Predefined;
import com.amazon.minerva.client.thirdparty.metric.IonMetricEvent;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class MetricBatchJsonSerializer implements MetricBatchSerializer {
    public static final String COMMON_DIMENSIONS = "commonDimensions";
    public static final String COUNTS = "counts";
    public static final String DIMENSIONS = "dimensions";
    public static final String EVENTS = "events";
    public static final String GROUP_ID = "groupId";
    public static final IonSystem ION_SYSTEM;
    public static final String METRICS = "metrics";
    public static final Set<String> PREDEFINED_KEYS;
    public static final String RECORD_TIME = "recordTime";
    public static final String SCHEMA_ID = "schemaId";
    public static final String TAG = "MetricBatchJsonSerializer";
    public static final String VALUES = "values";
    public static final ValueFactory valueFactory;

    /* JADX INFO: renamed from: com.amazon.minerva.client.thirdparty.serializer.MetricBatchJsonSerializer$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$amazon$ion$IonType;

        static {
            int[] iArr = new int[IonType.values().length];
            $SwitchMap$com$amazon$ion$IonType = iArr;
            try {
                iArr[IonType.BOOL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.TIMESTAMP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.SYMBOL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    static {
        IonSystemBuilder ionSystemBuilder = IonSystemBuilder.STANDARD;
        ION_SYSTEM = ionSystemBuilder.build();
        valueFactory = ionSystemBuilder.build();
        PREDEFINED_KEYS = new HashSet();
        for (Predefined predefined : Predefined.values()) {
            PREDEFINED_KEYS.add(predefined.key);
        }
    }

    public final IonStruct constructKeyValuePairs(Map<String, String> map, JSONObject jSONObject, JSONObject jSONObject2) throws JSONException {
        IonStruct ionStructNewEmptyStruct = ION_SYSTEM.newEmptyStruct();
        Map<String, IonValue> mapCreateDimensionMap = createDimensionMap(jSONObject);
        if (mapCreateDimensionMap == null) {
            return null;
        }
        ionStructNewEmptyStruct.putAll(mapCreateDimensionMap);
        for (Map.Entry entry : ((HashMap) createCommonDimensionMap(map)).entrySet()) {
            if (!ionStructNewEmptyStruct.containsKey(entry.getKey())) {
                ionStructNewEmptyStruct.put((String) entry.getKey(), (IonValue) entry.getValue());
            }
        }
        Map<String, IonValue> mapCreateMetricMap = createMetricMap(jSONObject2);
        if (mapCreateMetricMap == null) {
            return null;
        }
        ionStructNewEmptyStruct.putAll(mapCreateMetricMap);
        return ionStructNewEmptyStruct;
    }

    public final double[] convertIonListToDoubleArray(IonList ionList) {
        double[] dArr = new double[ionList.size()];
        for (int i = 0; i < ionList.size(); i++) {
            IonValue ionValue = ionList.get(i);
            if (!isNumeric(ionValue)) {
                Log.w(TAG, "Non-numeric value found in list at index: " + i);
                return null;
            }
            dArr[i] = getNumericValue(ionValue);
        }
        return dArr;
    }

    public final IonValue convertToIonNumeric(double d) {
        long j = (long) d;
        return d == ((double) j) ? valueFactory.newInt(j) : valueFactory.newFloat(d);
    }

    public final String convertToString(IonValue ionValue) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[ionValue.getType().ordinal()];
        if (i == 1) {
            return Boolean.toString(((IonBool) ionValue).booleanValue());
        }
        if (i == 2) {
            return ((IonTimestamp) ionValue).toString();
        }
        if (i != 3) {
            return null;
        }
        return ((IonSymbol) ionValue).stringValue();
    }

    public final JSONObject createAggregatedMetricJson(IonStruct ionStruct) throws JSONException {
        if (!isAggregatedMetric(ionStruct)) {
            Log.w(TAG, "Invalid aggregated metric structure: missing required fields or invalid size");
            return null;
        }
        IonValue ionValue = ionStruct.get("values");
        IonValue ionValue2 = ionStruct.get("counts");
        if (!(ionValue instanceof IonList) || !(ionValue2 instanceof IonList)) {
            Log.w(TAG, "Values or counts are not IonList type");
            return null;
        }
        IonList ionList = (IonList) ionValue;
        IonList ionList2 = (IonList) ionValue2;
        if (ionList.size() != ionList2.size() || ionList.isEmpty()) {
            Log.w(TAG, "Values and counts lists must be non-empty and of equal size");
            return null;
        }
        double[] dArrConvertIonListToDoubleArray = convertIonListToDoubleArray(ionList);
        double[] dArrConvertIonListToDoubleArray2 = convertIonListToDoubleArray(ionList2);
        if (dArrConvertIonListToDoubleArray == null || dArrConvertIonListToDoubleArray2 == null) {
            Log.w(TAG, "Failed to convert values or counts to double arrays");
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("values", new JSONArray(dArrConvertIonListToDoubleArray));
        jSONObject.put("counts", new JSONArray(dArrConvertIonListToDoubleArray2));
        return jSONObject;
    }

    public final Map<String, IonValue> createCommonDimensionMap(Map<String, String> map) {
        HashMap map2 = new HashMap();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            map2.put(entry.getKey(), valueFactory.newSymbol(entry.getValue()));
        }
        return map2;
    }

    public final Map<String, IonValue> createDimensionMap(JSONObject jSONObject) throws JSONException {
        HashMap map = new HashMap();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            String string = jSONObject.getString(next);
            if (string == null || string.isEmpty()) {
                Log.w(TAG, "Invalid dimension value for key: " + next);
                return null;
            }
            map.put(next, parseStringToIonValue(string));
        }
        return map;
    }

    public final JSONObject[] createDimensionsAndMetrics(IonStruct ionStruct, Map<String, String> map) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            for (IonValue ionValue : ionStruct) {
                String fieldName = ionValue.getFieldName();
                if (isNumeric(ionValue)) {
                    JSONObject jSONObjectCreateMetricValueJson = createMetricValueJson(ionValue);
                    if (jSONObjectCreateMetricValueJson == null) {
                        Log.w(TAG, "Failed to create metric value for key: " + fieldName);
                        return null;
                    }
                    jSONObject2.put(fieldName, jSONObjectCreateMetricValueJson);
                } else {
                    String strConvertToString = convertToString(ionValue);
                    if (strConvertToString == null) {
                        Log.w(TAG, "Failed to convert dimension value for key: " + fieldName);
                        return null;
                    }
                    if (PREDEFINED_KEYS.contains(fieldName)) {
                        String str = map.get(fieldName);
                        if (str == null) {
                            map.put(fieldName, strConvertToString);
                        } else if (!str.equals(strConvertToString)) {
                            jSONObject.put(fieldName, strConvertToString);
                        }
                    } else {
                        jSONObject.put(fieldName, strConvertToString);
                    }
                }
            }
            return new JSONObject[]{jSONObject, jSONObject2};
        } catch (JSONException e) {
            Log.e(TAG, "Error creating dimensions and metrics", e);
            return null;
        }
    }

    public final IonStruct createIonAggregatedMetric(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = jSONObject.getJSONArray("values");
        JSONArray jSONArray2 = jSONObject.getJSONArray("counts");
        if (jSONArray.length() == 0 || jSONArray.length() != jSONArray2.length()) {
            Log.w(TAG, "Invalid aggregated metric: empty arrays or unequal lengths");
            return null;
        }
        IonSystem ionSystem = ION_SYSTEM;
        IonStruct ionStructNewEmptyStruct = ionSystem.newEmptyStruct();
        IonList ionListNewEmptyList = ionSystem.newEmptyList();
        IonList ionListNewEmptyList2 = ionSystem.newEmptyList();
        for (int i = 0; i < jSONArray.length(); i++) {
            ionListNewEmptyList.add(convertToIonNumeric(jSONArray.getDouble(i)));
            ionListNewEmptyList2.add(convertToIonNumeric(jSONArray2.getDouble(i)));
        }
        ionStructNewEmptyStruct.put("values", ionListNewEmptyList);
        ionStructNewEmptyStruct.put("counts", ionListNewEmptyList2);
        return ionStructNewEmptyStruct;
    }

    public final IonMetricEvent createIonMetricEvent(JSONObject jSONObject, Map<String, String> map) throws JSONException {
        if (!hasRequiredFields(jSONObject)) {
            Log.w(TAG, "Event missing required fields");
            return null;
        }
        String string = jSONObject.getString(GROUP_ID);
        String string2 = jSONObject.getString("schemaId");
        try {
            Timestamp timestampForMillis = Timestamp.forMillis((long) (jSONObject.getDouble(RECORD_TIME) * 1000.0d), (Integer) 0);
            IonStruct ionStructConstructKeyValuePairs = constructKeyValuePairs(map, jSONObject.getJSONObject(DIMENSIONS), jSONObject.getJSONObject(METRICS));
            if (ionStructConstructKeyValuePairs == null) {
                return null;
            }
            IonSystem ionSystem = ION_SYSTEM;
            IonSymbol ionSymbolNewSymbol = ionSystem.newSymbol(string);
            IonSymbol ionSymbolNewSymbol2 = ionSystem.newSymbol(string2);
            ValueFactory valueFactory2 = valueFactory;
            return new IonMetricEvent(ionSymbolNewSymbol, ionSymbolNewSymbol2, valueFactory2.newTimestamp(timestampForMillis), valueFactory2.newString(UUID.randomUUID().toString()), ionStructConstructKeyValuePairs);
        } catch (IllegalArgumentException e) {
            Log.w(TAG, "Invalid record time value", e);
            return null;
        }
    }

    public final JSONObject createMetricBatchJson(List<IonMetricEvent> list) throws JSONException {
        HashMap map = new HashMap();
        JSONArray jSONArray = new JSONArray((Collection) new ArrayList(list.size()));
        for (IonMetricEvent ionMetricEvent : list) {
            if (hasRequiredFields(ionMetricEvent)) {
                JSONObject jSONObjectCreateMetricEventJson = createMetricEventJson(ionMetricEvent, map);
                if (jSONObjectCreateMetricEventJson != null) {
                    jSONArray.put(jSONObjectCreateMetricEventJson);
                }
            } else {
                Log.w(TAG, "Skipping invalid event with missing required fields");
            }
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(COMMON_DIMENSIONS, new JSONObject(map));
        jSONObject.put(EVENTS, jSONArray);
        return jSONObject;
    }

    public final JSONObject createMetricEventJson(IonMetricEvent ionMetricEvent, Map<String, String> map) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(GROUP_ID, ionMetricEvent.getMetricGroupId().stringValue());
            jSONObject.put("schemaId", ionMetricEvent.getMetricSchemaId().stringValue());
            IonTimestamp clientTimestamp = ionMetricEvent.getClientTimestamp();
            long millis = clientTimestamp.getMillis();
            Integer localOffset = clientTimestamp.getLocalOffset();
            if (localOffset.intValue() != 0) {
                Log.e(TAG, String.format("Unexpected non-zero timestamp offset detected: %d minutes. All timestamps should be in UTC.", localOffset));
            }
            jSONObject.put(RECORD_TIME, Timestamp.forMillis(millis, localOffset).getMillis() / 1000.0d);
            JSONObject[] jSONObjectArrCreateDimensionsAndMetrics = createDimensionsAndMetrics(ionMetricEvent.getKeyValuePairs(), map);
            if (jSONObjectArrCreateDimensionsAndMetrics == null) {
                Log.w(TAG, "Failed to create dimensions and metrics for event");
                return null;
            }
            jSONObject.put(DIMENSIONS, jSONObjectArrCreateDimensionsAndMetrics[0]);
            jSONObject.put(METRICS, jSONObjectArrCreateDimensionsAndMetrics[1]);
            return jSONObject;
        } catch (JSONException e) {
            Log.e(TAG, "Error creating event JSON", e);
            return null;
        }
    }

    public final Map<String, IonValue> createMetricMap(JSONObject jSONObject) throws JSONException {
        HashMap map = new HashMap();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            JSONObject jSONObject2 = jSONObject.getJSONObject(next);
            if (!jSONObject2.has("values") || !jSONObject2.has("counts")) {
                Log.w(TAG, "Metric missing required arrays: " + next);
                return null;
            }
            if (isAggregatedMetric(jSONObject2)) {
                IonStruct ionStructCreateIonAggregatedMetric = createIonAggregatedMetric(jSONObject2);
                if (ionStructCreateIonAggregatedMetric == null) {
                    return null;
                }
                map.put(next, ionStructCreateIonAggregatedMetric);
            } else {
                JSONArray jSONArray = jSONObject2.getJSONArray("values");
                if (jSONArray.length() != 1) {
                    return null;
                }
                map.put(next, convertToIonNumeric(jSONArray.getDouble(0)));
            }
        }
        return map;
    }

    public final JSONObject createMetricValueJson(IonValue ionValue) {
        try {
            return ionValue instanceof IonStruct ? createAggregatedMetricJson((IonStruct) ionValue) : createNonAggregatedMetricJson(ionValue);
        } catch (JSONException e) {
            Log.w(TAG, "Error creating metric value", e);
            return null;
        }
    }

    public final JSONObject createNonAggregatedMetricJson(IonValue ionValue) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        JSONArray jSONArray2 = new JSONArray();
        jSONArray.put(getNumericValue(ionValue));
        jSONArray2.put(1.0d);
        jSONObject.put("values", jSONArray);
        jSONObject.put("counts", jSONArray2);
        return jSONObject;
    }

    @Override // com.amazon.minerva.client.thirdparty.serializer.MetricBatchSerializer
    public List<IonMetricEvent> deserialize(byte[] bArr) throws IOException {
        if (bArr == null || bArr.length == 0) {
            return Collections.EMPTY_LIST;
        }
        try {
            JSONObject jSONObject = new JSONObject(new String(bArr, StandardCharsets.UTF_8));
            if (jSONObject.has(COMMON_DIMENSIONS) && jSONObject.has(EVENTS)) {
                Map<String, String> mapExtractCommonDimensions = extractCommonDimensions(jSONObject.getJSONObject(COMMON_DIMENSIONS));
                JSONArray jSONArray = jSONObject.getJSONArray(EVENTS);
                ArrayList arrayList = new ArrayList(jSONArray.length());
                for (int i = 0; i < jSONArray.length(); i++) {
                    IonMetricEvent ionMetricEventCreateIonMetricEvent = createIonMetricEvent(jSONArray.getJSONObject(i), mapExtractCommonDimensions);
                    if (ionMetricEventCreateIonMetricEvent != null) {
                        arrayList.add(ionMetricEventCreateIonMetricEvent);
                    }
                }
                return arrayList;
            }
            Log.w(TAG, "Invalid JSON structure: missing required fields");
            return Collections.EMPTY_LIST;
        } catch (JSONException e) {
            Log.e(TAG, "Error deserializing JSON to Ion events", e);
            throw new IOException("Failed to deserialize JSON to Ion events", e);
        }
    }

    public final Map<String, String> extractCommonDimensions(JSONObject jSONObject) throws JSONException {
        HashMap map = new HashMap();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            map.put(next, jSONObject.getString(next));
        }
        return map;
    }

    public final double getNumericValue(IonValue ionValue) {
        return ionValue instanceof IonInt ? ((IonInt) ionValue).longValue() : ((IonFloat) ionValue).doubleValue();
    }

    public final boolean hasRequiredFields(IonMetricEvent ionMetricEvent) {
        return (ionMetricEvent.getMetricGroupId() == null || ionMetricEvent.getMetricSchemaId() == null || ionMetricEvent.getClientTimestamp() == null || ionMetricEvent.getKeyValuePairs() == null) ? false : true;
    }

    public final boolean isAggregatedMetric(IonStruct ionStruct) {
        return ionStruct.containsKey("values") && ionStruct.containsKey("counts") && ionStruct.size() == 2;
    }

    public final boolean isNumeric(IonValue ionValue) {
        return (ionValue instanceof IonInt) || (ionValue instanceof IonFloat) || (ionValue instanceof IonStruct);
    }

    public final IonValue parseStringToIonValue(String str) {
        if (str.equalsIgnoreCase("true") || str.equalsIgnoreCase("false")) {
            return valueFactory.newBool(Boolean.parseBoolean(str));
        }
        try {
            return valueFactory.newTimestamp(Timestamp.valueOf(str));
        } catch (IllegalArgumentException unused) {
            Log.d(TAG, "Value not in timestamp format, treating as symbol");
            return valueFactory.newSymbol(str);
        }
    }

    public byte[] serialize(IonMetricEvent ionMetricEvent) throws IOException {
        return ionMetricEvent == null ? new byte[0] : serialize(Collections.singletonList(ionMetricEvent));
    }

    @Override // com.amazon.minerva.client.thirdparty.serializer.MetricBatchSerializer
    public byte[] serialize(List<IonMetricEvent> list) throws IOException {
        if (list != null && !list.isEmpty()) {
            try {
                return createMetricBatchJson(list).toString().getBytes(StandardCharsets.UTF_8);
            } catch (Exception e) {
                Log.e(TAG, "Error serializing Ion events to JSON", e);
                throw new IOException("Failed to serialize ion metric batch to json", e);
            }
        }
        return new byte[0];
    }

    public final boolean isAggregatedMetric(JSONObject jSONObject) throws JSONException {
        return jSONObject.has("values") && jSONObject.has("counts") && jSONObject.length() == 2 && jSONObject.getJSONArray("values").length() > 1 && jSONObject.getJSONArray("values").length() == jSONObject.getJSONArray("counts").length();
    }

    public final boolean hasRequiredFields(JSONObject jSONObject) {
        return jSONObject.has(GROUP_ID) && jSONObject.has("schemaId") && jSONObject.has(RECORD_TIME) && jSONObject.has(DIMENSIONS) && jSONObject.has(METRICS);
    }
}
