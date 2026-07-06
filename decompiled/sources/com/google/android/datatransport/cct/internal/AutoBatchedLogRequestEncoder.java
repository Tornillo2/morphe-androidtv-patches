package com.google.android.datatransport.cct.internal;

import com.google.android.datatransport.cct.CctTransportBackend;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.config.Configurator;
import com.google.firebase.encoders.config.EncoderConfig;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class AutoBatchedLogRequestEncoder implements Configurator {
    public static final int CODEGEN_VERSION = 2;
    public static final Configurator CONFIG = new AutoBatchedLogRequestEncoder();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AndroidClientInfoEncoder implements ObjectEncoder<AndroidClientInfo> {
        public static final AndroidClientInfoEncoder INSTANCE = new AndroidClientInfoEncoder();
        public static final FieldDescriptor SDKVERSION_DESCRIPTOR = FieldDescriptor.of("sdkVersion");
        public static final FieldDescriptor MODEL_DESCRIPTOR = FieldDescriptor.of(CctTransportBackend.KEY_MODEL);
        public static final FieldDescriptor HARDWARE_DESCRIPTOR = FieldDescriptor.of("hardware");
        public static final FieldDescriptor DEVICE_DESCRIPTOR = FieldDescriptor.of(CctTransportBackend.KEY_DEVICE);
        public static final FieldDescriptor PRODUCT_DESCRIPTOR = FieldDescriptor.of(CctTransportBackend.KEY_PRODUCT);
        public static final FieldDescriptor OSBUILD_DESCRIPTOR = FieldDescriptor.of("osBuild");
        public static final FieldDescriptor MANUFACTURER_DESCRIPTOR = FieldDescriptor.of(CctTransportBackend.KEY_MANUFACTURER);
        public static final FieldDescriptor FINGERPRINT_DESCRIPTOR = FieldDescriptor.of(CctTransportBackend.KEY_FINGERPRINT);
        public static final FieldDescriptor LOCALE_DESCRIPTOR = FieldDescriptor.of("locale");
        public static final FieldDescriptor COUNTRY_DESCRIPTOR = FieldDescriptor.of(CctTransportBackend.KEY_COUNTRY);
        public static final FieldDescriptor MCCMNC_DESCRIPTOR = FieldDescriptor.of("mccMnc");
        public static final FieldDescriptor APPLICATIONBUILD_DESCRIPTOR = FieldDescriptor.of("applicationBuild");

        @Override // com.google.firebase.encoders.Encoder
        public void encode(AndroidClientInfo androidClientInfo, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(SDKVERSION_DESCRIPTOR, androidClientInfo.getSdkVersion());
            objectEncoderContext.add(MODEL_DESCRIPTOR, androidClientInfo.getModel());
            objectEncoderContext.add(HARDWARE_DESCRIPTOR, androidClientInfo.getHardware());
            objectEncoderContext.add(DEVICE_DESCRIPTOR, androidClientInfo.getDevice());
            objectEncoderContext.add(PRODUCT_DESCRIPTOR, androidClientInfo.getProduct());
            objectEncoderContext.add(OSBUILD_DESCRIPTOR, androidClientInfo.getOsBuild());
            objectEncoderContext.add(MANUFACTURER_DESCRIPTOR, androidClientInfo.getManufacturer());
            objectEncoderContext.add(FINGERPRINT_DESCRIPTOR, androidClientInfo.getFingerprint());
            objectEncoderContext.add(LOCALE_DESCRIPTOR, androidClientInfo.getLocale());
            objectEncoderContext.add(COUNTRY_DESCRIPTOR, androidClientInfo.getCountry());
            objectEncoderContext.add(MCCMNC_DESCRIPTOR, androidClientInfo.getMccMnc());
            objectEncoderContext.add(APPLICATIONBUILD_DESCRIPTOR, androidClientInfo.getApplicationBuild());
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class BatchedLogRequestEncoder implements ObjectEncoder<BatchedLogRequest> {
        public static final BatchedLogRequestEncoder INSTANCE = new BatchedLogRequestEncoder();
        public static final FieldDescriptor LOGREQUEST_DESCRIPTOR = FieldDescriptor.of("logRequest");

        @Override // com.google.firebase.encoders.Encoder
        public void encode(BatchedLogRequest batchedLogRequest, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(LOGREQUEST_DESCRIPTOR, batchedLogRequest.getLogRequests());
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ClientInfoEncoder implements ObjectEncoder<ClientInfo> {
        public static final ClientInfoEncoder INSTANCE = new ClientInfoEncoder();
        public static final FieldDescriptor CLIENTTYPE_DESCRIPTOR = FieldDescriptor.of("clientType");
        public static final FieldDescriptor ANDROIDCLIENTINFO_DESCRIPTOR = FieldDescriptor.of("androidClientInfo");

        @Override // com.google.firebase.encoders.Encoder
        public void encode(ClientInfo clientInfo, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(CLIENTTYPE_DESCRIPTOR, clientInfo.getClientType());
            objectEncoderContext.add(ANDROIDCLIENTINFO_DESCRIPTOR, clientInfo.getAndroidClientInfo());
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class LogEventEncoder implements ObjectEncoder<LogEvent> {
        public static final LogEventEncoder INSTANCE = new LogEventEncoder();
        public static final FieldDescriptor EVENTTIMEMS_DESCRIPTOR = FieldDescriptor.of("eventTimeMs");
        public static final FieldDescriptor EVENTCODE_DESCRIPTOR = FieldDescriptor.of("eventCode");
        public static final FieldDescriptor EVENTUPTIMEMS_DESCRIPTOR = FieldDescriptor.of("eventUptimeMs");
        public static final FieldDescriptor SOURCEEXTENSION_DESCRIPTOR = FieldDescriptor.of("sourceExtension");
        public static final FieldDescriptor SOURCEEXTENSIONJSONPROTO3_DESCRIPTOR = FieldDescriptor.of("sourceExtensionJsonProto3");
        public static final FieldDescriptor TIMEZONEOFFSETSECONDS_DESCRIPTOR = FieldDescriptor.of("timezoneOffsetSeconds");
        public static final FieldDescriptor NETWORKCONNECTIONINFO_DESCRIPTOR = FieldDescriptor.of("networkConnectionInfo");

        @Override // com.google.firebase.encoders.Encoder
        public void encode(LogEvent logEvent, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(EVENTTIMEMS_DESCRIPTOR, logEvent.getEventTimeMs());
            objectEncoderContext.add(EVENTCODE_DESCRIPTOR, logEvent.getEventCode());
            objectEncoderContext.add(EVENTUPTIMEMS_DESCRIPTOR, logEvent.getEventUptimeMs());
            objectEncoderContext.add(SOURCEEXTENSION_DESCRIPTOR, logEvent.getSourceExtension());
            objectEncoderContext.add(SOURCEEXTENSIONJSONPROTO3_DESCRIPTOR, logEvent.getSourceExtensionJsonProto3());
            objectEncoderContext.add(TIMEZONEOFFSETSECONDS_DESCRIPTOR, logEvent.getTimezoneOffsetSeconds());
            objectEncoderContext.add(NETWORKCONNECTIONINFO_DESCRIPTOR, logEvent.getNetworkConnectionInfo());
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class LogRequestEncoder implements ObjectEncoder<LogRequest> {
        public static final LogRequestEncoder INSTANCE = new LogRequestEncoder();
        public static final FieldDescriptor REQUESTTIMEMS_DESCRIPTOR = FieldDescriptor.of("requestTimeMs");
        public static final FieldDescriptor REQUESTUPTIMEMS_DESCRIPTOR = FieldDescriptor.of("requestUptimeMs");
        public static final FieldDescriptor CLIENTINFO_DESCRIPTOR = FieldDescriptor.of("clientInfo");
        public static final FieldDescriptor LOGSOURCE_DESCRIPTOR = FieldDescriptor.of("logSource");
        public static final FieldDescriptor LOGSOURCENAME_DESCRIPTOR = FieldDescriptor.of("logSourceName");
        public static final FieldDescriptor LOGEVENT_DESCRIPTOR = FieldDescriptor.of("logEvent");
        public static final FieldDescriptor QOSTIER_DESCRIPTOR = FieldDescriptor.of("qosTier");

        @Override // com.google.firebase.encoders.Encoder
        public void encode(LogRequest logRequest, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(REQUESTTIMEMS_DESCRIPTOR, logRequest.getRequestTimeMs());
            objectEncoderContext.add(REQUESTUPTIMEMS_DESCRIPTOR, logRequest.getRequestUptimeMs());
            objectEncoderContext.add(CLIENTINFO_DESCRIPTOR, logRequest.getClientInfo());
            objectEncoderContext.add(LOGSOURCE_DESCRIPTOR, logRequest.getLogSource());
            objectEncoderContext.add(LOGSOURCENAME_DESCRIPTOR, logRequest.getLogSourceName());
            objectEncoderContext.add(LOGEVENT_DESCRIPTOR, logRequest.getLogEvents());
            objectEncoderContext.add(QOSTIER_DESCRIPTOR, logRequest.getQosTier());
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class NetworkConnectionInfoEncoder implements ObjectEncoder<NetworkConnectionInfo> {
        public static final NetworkConnectionInfoEncoder INSTANCE = new NetworkConnectionInfoEncoder();
        public static final FieldDescriptor NETWORKTYPE_DESCRIPTOR = FieldDescriptor.of("networkType");
        public static final FieldDescriptor MOBILESUBTYPE_DESCRIPTOR = FieldDescriptor.of("mobileSubtype");

        @Override // com.google.firebase.encoders.Encoder
        public void encode(NetworkConnectionInfo networkConnectionInfo, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(NETWORKTYPE_DESCRIPTOR, networkConnectionInfo.getNetworkType());
            objectEncoderContext.add(MOBILESUBTYPE_DESCRIPTOR, networkConnectionInfo.getMobileSubtype());
        }
    }

    @Override // com.google.firebase.encoders.config.Configurator
    public void configure(EncoderConfig<?> encoderConfig) {
        BatchedLogRequestEncoder batchedLogRequestEncoder = BatchedLogRequestEncoder.INSTANCE;
        encoderConfig.registerEncoder(BatchedLogRequest.class, batchedLogRequestEncoder);
        encoderConfig.registerEncoder(AutoValue_BatchedLogRequest.class, batchedLogRequestEncoder);
        LogRequestEncoder logRequestEncoder = LogRequestEncoder.INSTANCE;
        encoderConfig.registerEncoder(LogRequest.class, logRequestEncoder);
        encoderConfig.registerEncoder(AutoValue_LogRequest.class, logRequestEncoder);
        ClientInfoEncoder clientInfoEncoder = ClientInfoEncoder.INSTANCE;
        encoderConfig.registerEncoder(ClientInfo.class, clientInfoEncoder);
        encoderConfig.registerEncoder(AutoValue_ClientInfo.class, clientInfoEncoder);
        AndroidClientInfoEncoder androidClientInfoEncoder = AndroidClientInfoEncoder.INSTANCE;
        encoderConfig.registerEncoder(AndroidClientInfo.class, androidClientInfoEncoder);
        encoderConfig.registerEncoder(AutoValue_AndroidClientInfo.class, androidClientInfoEncoder);
        LogEventEncoder logEventEncoder = LogEventEncoder.INSTANCE;
        encoderConfig.registerEncoder(LogEvent.class, logEventEncoder);
        encoderConfig.registerEncoder(AutoValue_LogEvent.class, logEventEncoder);
        NetworkConnectionInfoEncoder networkConnectionInfoEncoder = NetworkConnectionInfoEncoder.INSTANCE;
        encoderConfig.registerEncoder(NetworkConnectionInfo.class, networkConnectionInfoEncoder);
        encoderConfig.registerEncoder(AutoValue_NetworkConnectionInfo.class, networkConnectionInfoEncoder);
    }
}
