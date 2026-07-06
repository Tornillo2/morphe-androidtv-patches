package com.amazon.minerva.client.thirdparty.utils;

import com.amazon.ion.IonException;
import com.amazon.ion.IonList;
import com.amazon.ion.IonReader;
import com.amazon.ion.IonString;
import com.amazon.ion.IonStruct;
import com.amazon.ion.IonSymbol;
import com.amazon.ion.IonSystem;
import com.amazon.ion.IonType;
import com.amazon.ion.IonValue;
import com.amazon.ion.IonWriter;
import com.amazon.ion.impl._Private_IonBinaryWriterBuilder;
import com.amazon.ion.impl._Private_IonReaderBuilder;
import com.amazon.ion.impl._Private_IonTextWriterBuilder;
import com.amazon.ion.system.IonBinaryWriterBuilder;
import com.amazon.ion.system.IonReaderBuilder;
import com.amazon.ion.system.IonSystemBuilder;
import com.amazon.ion.system.IonTextWriterBuilder;
import com.amazon.minerva.client.thirdparty.serializer.IonMetricEventConverter;
import com.amazon.minerva.client.thirdparty.transport.MetricEventResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class MetricEventResponseIonConverter {
    public static final String RESPONSE_FIELD_NAME_METRICGROUPID = "metricEventId";
    public static final String RESPONSE_FIELD_NAME_STATUS = "status";
    public static final IonSystem ION_SYSTEM = IonSystemBuilder.STANDARD.build();
    public static final IonReaderBuilder ION_READER_BUILDER = new _Private_IonReaderBuilder.Mutable();
    public static final IonBinaryWriterBuilder ION_BINARY_WRITER_BUILDER = new _Private_IonBinaryWriterBuilder.Mutable();
    public static final IonTextWriterBuilder ION_TEXT_WRITER_BUILDER = _Private_IonTextWriterBuilder.standard();

    public static Map<String, String> convertIonBinaryToResponseMap(byte[] bArr) throws IOException, IonException {
        IonReader ionReaderBuild = ION_READER_BUILDER.build(bArr);
        ArrayList arrayList = new ArrayList();
        if (ionReaderBuild.next() == IonType.LIST) {
            ionReaderBuild.stepIn();
            while (true) {
                IonType next = ionReaderBuild.next();
                if (next == null) {
                    break;
                }
                if (next == IonType.STRUCT) {
                    ionReaderBuild.stepIn();
                    IonString ionString = null;
                    IonSymbol ionSymbol = null;
                    while (ionReaderBuild.next() != null) {
                        String fieldName = ionReaderBuild.getFieldName();
                        if (fieldName.equals("metricEventId")) {
                            ionString = (IonString) IonMetricEventConverter.recursiveReadIonValueByIonType(ionReaderBuild, IonType.STRING);
                        } else if (fieldName.equals("status")) {
                            ionSymbol = (IonSymbol) IonMetricEventConverter.recursiveReadIonValueByIonType(ionReaderBuild, IonType.SYMBOL);
                        }
                    }
                    arrayList.add(new MetricEventResponse(ionString, ionSymbol));
                    ionReaderBuild.stepOut();
                }
            }
            ionReaderBuild.stepOut();
        }
        return convertResponseArrayToResponseMap((MetricEventResponse[]) arrayList.toArray(new MetricEventResponse[0]));
    }

    public static Map<String, String> convertIonTextToResponseMap(String str) throws IOException, IonException {
        return convertIonBinaryToResponseMap(str.getBytes());
    }

    public static byte[] convertMetricEventResponsesToIonBinary(List<MetricEventResponse> list) throws IOException, IonException {
        IonList ionListConvertToIonList = convertToIonList(list);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        IonWriter ionWriterBuild = ION_BINARY_WRITER_BUILDER.build(byteArrayOutputStream);
        try {
            ionListConvertToIonList.writeTo(ionWriterBuild);
            ionWriterBuild.close();
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th) {
            try {
                ionWriterBuild.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static String convertMetricEventResponsesToIonText(List<MetricEventResponse> list) throws IOException, IonException {
        IonList ionListConvertToIonList = convertToIonList(list);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        IonWriter ionWriterBuild = ION_TEXT_WRITER_BUILDER.build(byteArrayOutputStream);
        try {
            ionListConvertToIonList.writeTo(ionWriterBuild);
            ionWriterBuild.close();
            return byteArrayOutputStream.toString();
        } catch (Throwable th) {
            try {
                ionWriterBuild.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static Map<String, String> convertResponseArrayToResponseMap(MetricEventResponse[] metricEventResponseArr) {
        HashMap map = new HashMap();
        for (MetricEventResponse metricEventResponse : metricEventResponseArr) {
            map.put(metricEventResponse.getMetricEventId().stringValue(), metricEventResponse.getStatus().stringValue());
        }
        return map;
    }

    public static IonList convertToIonList(List<MetricEventResponse> list) {
        IonList ionListNewEmptyList = ION_SYSTEM.newEmptyList();
        for (MetricEventResponse metricEventResponse : list) {
            IonStruct ionStructNewEmptyStruct = ION_SYSTEM.newEmptyStruct();
            ionStructNewEmptyStruct.put("metricEventId", metricEventResponse.getMetricEventId());
            ionStructNewEmptyStruct.put("status", metricEventResponse.getStatus());
            ionListNewEmptyList.add((IonValue) ionStructNewEmptyStruct);
        }
        ionListNewEmptyList.add();
        return ionListNewEmptyList;
    }
}
