package com.amazon.minerva.client.thirdparty.serializer;

import com.amazon.ion.IonList;
import com.amazon.ion.IonReader;
import com.amazon.ion.IonString;
import com.amazon.ion.IonStruct;
import com.amazon.ion.IonSymbol;
import com.amazon.ion.IonSystem;
import com.amazon.ion.IonTimestamp;
import com.amazon.ion.IonType;
import com.amazon.ion.IonValue;
import com.amazon.ion.IonWriter;
import com.amazon.ion.impl._Private_IonBinaryWriterBuilder;
import com.amazon.ion.impl._Private_IonReaderBuilder;
import com.amazon.ion.system.IonBinaryWriterBuilder;
import com.amazon.ion.system.IonReaderBuilder;
import com.amazon.ion.system.IonSystemBuilder;
import com.amazon.ion.system.IonTextWriterBuilder;
import com.amazon.minerva.client.thirdparty.metric.IonMetricEvent;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class IonMetricEventConverter {
    public static final String ION_FIELD_NAME_CLIENTTIMESTAMP = "clientTimestamp";
    public static final String ION_FIELD_NAME_KEYVALUEPAIRS = "keyValuePairs";
    public static final String ION_FIELD_NAME_METRICEVENTID = "metricEventId";
    public static final String ION_FIELD_NAME_METRICGROUPID = "metricGroupId";
    public static final String ION_FIELD_NAME_METRICSCHEMAID = "metricSchemaId";
    public static final IonReaderBuilder ION_READER_BUILDER = new _Private_IonReaderBuilder.Mutable();
    public static final IonBinaryWriterBuilder ION_BINARY_WRITER_BUILDER = new _Private_IonBinaryWriterBuilder.Mutable();
    public static final IonTextWriterBuilder ION_TEXT_WRITER_BUILDER = IonTextWriterBuilder.standard();
    public static final IonSystem ION_SYSTEM = IonSystemBuilder.STANDARD.build();

    public static List<IonMetricEvent> convertIonBinaryToIonMetricEvents(byte[] bArr) throws IOException {
        IonReader ionReaderBuild = ION_READER_BUILDER.build(bArr);
        ArrayList arrayList = new ArrayList();
        if (ionReaderBuild.next() != IonType.LIST) {
            return arrayList;
        }
        ionReaderBuild.stepIn();
        while (true) {
            IonType next = ionReaderBuild.next();
            if (next == null) {
                ionReaderBuild.stepOut();
                return arrayList;
            }
            if (next == IonType.STRUCT) {
                ionReaderBuild.stepIn();
                IonSymbol ionSymbol = null;
                IonSymbol ionSymbol2 = null;
                IonTimestamp ionTimestamp = null;
                IonString ionString = null;
                IonStruct ionStruct = null;
                while (ionReaderBuild.next() != null) {
                    String fieldName = ionReaderBuild.getFieldName();
                    if (fieldName.equals("metricGroupId")) {
                        ionSymbol = (IonSymbol) recursiveReadIonValueByIonType(ionReaderBuild, IonType.SYMBOL);
                    } else if (fieldName.equals(ION_FIELD_NAME_METRICSCHEMAID)) {
                        ionSymbol2 = (IonSymbol) recursiveReadIonValueByIonType(ionReaderBuild, IonType.SYMBOL);
                    } else if (fieldName.equals(ION_FIELD_NAME_CLIENTTIMESTAMP)) {
                        ionTimestamp = (IonTimestamp) recursiveReadIonValueByIonType(ionReaderBuild, IonType.TIMESTAMP);
                    } else if (fieldName.equals("metricEventId")) {
                        ionString = (IonString) recursiveReadIonValueByIonType(ionReaderBuild, IonType.STRING);
                    } else if (fieldName.equals(ION_FIELD_NAME_KEYVALUEPAIRS)) {
                        ionStruct = (IonStruct) recursiveReadIonValueByIonType(ionReaderBuild, IonType.STRUCT);
                    }
                }
                arrayList.add(new IonMetricEvent(ionSymbol, ionSymbol2, ionTimestamp, ionString, ionStruct));
                ionReaderBuild.stepOut();
            }
        }
    }

    public static byte[] convertIonMetricEventsToIonBinary(List<IonMetricEvent> list) throws IOException {
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

    public static String convertIonMetricEventsToIonText(List<IonMetricEvent> list) throws IOException {
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

    public static IonList convertToIonList(List<IonMetricEvent> list) {
        IonList ionListNewEmptyList = ION_SYSTEM.newEmptyList();
        for (IonMetricEvent ionMetricEvent : list) {
            IonStruct ionStructNewEmptyStruct = ION_SYSTEM.newEmptyStruct();
            ionStructNewEmptyStruct.put("metricGroupId", ionMetricEvent.getMetricGroupId());
            ionStructNewEmptyStruct.put(ION_FIELD_NAME_METRICSCHEMAID, ionMetricEvent.getMetricSchemaId());
            ionStructNewEmptyStruct.put(ION_FIELD_NAME_CLIENTTIMESTAMP, ionMetricEvent.getClientTimestamp());
            ionStructNewEmptyStruct.put("metricEventId", ionMetricEvent.getMetricEventId());
            ionStructNewEmptyStruct.put(ION_FIELD_NAME_KEYVALUEPAIRS, ionMetricEvent.getKeyValuePairs());
            ionListNewEmptyList.add((IonValue) ionStructNewEmptyStruct);
        }
        ionListNewEmptyList.add();
        return ionListNewEmptyList;
    }

    public static IonValue recursiveReadIonValueByIonType(IonReader ionReader, IonType ionType) {
        if (ionType == IonType.SYMBOL) {
            return ION_SYSTEM.newSymbol(ionReader.symbolValue());
        }
        IonType ionType2 = IonType.STRING;
        if (ionType == ionType2) {
            return ION_SYSTEM.newString(ionReader.stringValue());
        }
        if (ionType == IonType.BOOL) {
            return ION_SYSTEM.newBool(ionReader.booleanValue());
        }
        if (ionType == IonType.INT) {
            return ION_SYSTEM.newInt(ionReader.longValue());
        }
        if (ionType == ionType2) {
            return ION_SYSTEM.newString(ionReader.stringValue());
        }
        if (ionType == IonType.FLOAT) {
            return ION_SYSTEM.newFloat(ionReader.doubleValue());
        }
        if (ionType == IonType.TIMESTAMP) {
            return ION_SYSTEM.newTimestamp(ionReader.timestampValue());
        }
        if (ionType != IonType.STRUCT) {
            return null;
        }
        ionReader.stepIn();
        IonStruct ionStructNewEmptyStruct = ION_SYSTEM.newEmptyStruct();
        while (true) {
            IonType next = ionReader.next();
            if (next == null) {
                ionReader.stepOut();
                return ionStructNewEmptyStruct;
            }
            String fieldName = ionReader.getFieldName();
            IonValue ionValueRecursiveReadIonValueByIonType = recursiveReadIonValueByIonType(ionReader, next);
            if (ionValueRecursiveReadIonValueByIonType != null) {
                ionStructNewEmptyStruct.put(fieldName, ionValueRecursiveReadIonValueByIonType);
            }
        }
    }
}
