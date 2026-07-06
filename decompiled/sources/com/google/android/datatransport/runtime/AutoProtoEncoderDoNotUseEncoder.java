package com.google.android.datatransport.runtime;

import com.google.android.datatransport.runtime.firebase.transport.ClientMetrics;
import com.google.android.datatransport.runtime.firebase.transport.GlobalMetrics;
import com.google.android.datatransport.runtime.firebase.transport.LogEventDropped;
import com.google.android.datatransport.runtime.firebase.transport.LogSourceMetrics;
import com.google.android.datatransport.runtime.firebase.transport.StorageMetrics;
import com.google.android.datatransport.runtime.firebase.transport.TimeWindow;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.config.Configurator;
import com.google.firebase.encoders.config.EncoderConfig;
import com.google.firebase.encoders.proto.AtProtobuf;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class AutoProtoEncoderDoNotUseEncoder implements Configurator {
    public static final int CODEGEN_VERSION = 2;
    public static final Configurator CONFIG = new AutoProtoEncoderDoNotUseEncoder();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ClientMetricsEncoder implements ObjectEncoder<ClientMetrics> {
        public static final FieldDescriptor APPNAMESPACE_DESCRIPTOR;
        public static final FieldDescriptor GLOBALMETRICS_DESCRIPTOR;
        public static final ClientMetricsEncoder INSTANCE = new ClientMetricsEncoder();
        public static final FieldDescriptor LOGSOURCEMETRICS_DESCRIPTOR;
        public static final FieldDescriptor WINDOW_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("window");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty(atProtobuf.build());
            WINDOW_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("logSourceMetrics");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty(atProtobuf2.build());
            LOGSOURCEMETRICS_DESCRIPTOR = builder2.build();
            FieldDescriptor.Builder builder3 = new FieldDescriptor.Builder("globalMetrics");
            AtProtobuf atProtobuf3 = new AtProtobuf();
            atProtobuf3.tag = 3;
            builder3.withProperty(atProtobuf3.build());
            GLOBALMETRICS_DESCRIPTOR = builder3.build();
            FieldDescriptor.Builder builder4 = new FieldDescriptor.Builder("appNamespace");
            AtProtobuf atProtobuf4 = new AtProtobuf();
            atProtobuf4.tag = 4;
            builder4.withProperty(atProtobuf4.build());
            APPNAMESPACE_DESCRIPTOR = builder4.build();
        }

        @Override // com.google.firebase.encoders.Encoder
        public void encode(ClientMetrics clientMetrics, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(WINDOW_DESCRIPTOR, clientMetrics.window_);
            objectEncoderContext.add(LOGSOURCEMETRICS_DESCRIPTOR, clientMetrics.log_source_metrics_);
            objectEncoderContext.add(GLOBALMETRICS_DESCRIPTOR, clientMetrics.global_metrics_);
            objectEncoderContext.add(APPNAMESPACE_DESCRIPTOR, clientMetrics.app_namespace_);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class GlobalMetricsEncoder implements ObjectEncoder<GlobalMetrics> {
        public static final GlobalMetricsEncoder INSTANCE = new GlobalMetricsEncoder();
        public static final FieldDescriptor STORAGEMETRICS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("storageMetrics");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty(atProtobuf.build());
            STORAGEMETRICS_DESCRIPTOR = builder.build();
        }

        @Override // com.google.firebase.encoders.Encoder
        public void encode(GlobalMetrics globalMetrics, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(STORAGEMETRICS_DESCRIPTOR, globalMetrics.storage_metrics_);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class LogEventDroppedEncoder implements ObjectEncoder<LogEventDropped> {
        public static final FieldDescriptor EVENTSDROPPEDCOUNT_DESCRIPTOR;
        public static final LogEventDroppedEncoder INSTANCE = new LogEventDroppedEncoder();
        public static final FieldDescriptor REASON_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("eventsDroppedCount");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty(atProtobuf.build());
            EVENTSDROPPEDCOUNT_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("reason");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 3;
            builder2.withProperty(atProtobuf2.build());
            REASON_DESCRIPTOR = builder2.build();
        }

        @Override // com.google.firebase.encoders.Encoder
        public void encode(LogEventDropped logEventDropped, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(EVENTSDROPPEDCOUNT_DESCRIPTOR, logEventDropped.events_dropped_count_);
            objectEncoderContext.add(REASON_DESCRIPTOR, logEventDropped.reason_);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class LogSourceMetricsEncoder implements ObjectEncoder<LogSourceMetrics> {
        public static final LogSourceMetricsEncoder INSTANCE = new LogSourceMetricsEncoder();
        public static final FieldDescriptor LOGEVENTDROPPED_DESCRIPTOR;
        public static final FieldDescriptor LOGSOURCE_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("logSource");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty(atProtobuf.build());
            LOGSOURCE_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("logEventDropped");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty(atProtobuf2.build());
            LOGEVENTDROPPED_DESCRIPTOR = builder2.build();
        }

        @Override // com.google.firebase.encoders.Encoder
        public void encode(LogSourceMetrics logSourceMetrics, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(LOGSOURCE_DESCRIPTOR, logSourceMetrics.log_source_);
            objectEncoderContext.add(LOGEVENTDROPPED_DESCRIPTOR, logSourceMetrics.log_event_dropped_);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ProtoEncoderDoNotUseEncoder implements ObjectEncoder<ProtoEncoderDoNotUse> {
        public static final ProtoEncoderDoNotUseEncoder INSTANCE = new ProtoEncoderDoNotUseEncoder();
        public static final FieldDescriptor CLIENTMETRICS_DESCRIPTOR = FieldDescriptor.of("clientMetrics");

        @Override // com.google.firebase.encoders.Encoder
        public void encode(ProtoEncoderDoNotUse protoEncoderDoNotUse, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(CLIENTMETRICS_DESCRIPTOR, protoEncoderDoNotUse.getClientMetrics());
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class StorageMetricsEncoder implements ObjectEncoder<StorageMetrics> {
        public static final FieldDescriptor CURRENTCACHESIZEBYTES_DESCRIPTOR;
        public static final StorageMetricsEncoder INSTANCE = new StorageMetricsEncoder();
        public static final FieldDescriptor MAXCACHESIZEBYTES_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("currentCacheSizeBytes");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty(atProtobuf.build());
            CURRENTCACHESIZEBYTES_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("maxCacheSizeBytes");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty(atProtobuf2.build());
            MAXCACHESIZEBYTES_DESCRIPTOR = builder2.build();
        }

        @Override // com.google.firebase.encoders.Encoder
        public void encode(StorageMetrics storageMetrics, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(CURRENTCACHESIZEBYTES_DESCRIPTOR, storageMetrics.current_cache_size_bytes_);
            objectEncoderContext.add(MAXCACHESIZEBYTES_DESCRIPTOR, storageMetrics.max_cache_size_bytes_);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class TimeWindowEncoder implements ObjectEncoder<TimeWindow> {
        public static final FieldDescriptor ENDMS_DESCRIPTOR;
        public static final TimeWindowEncoder INSTANCE = new TimeWindowEncoder();
        public static final FieldDescriptor STARTMS_DESCRIPTOR;

        static {
            FieldDescriptor.Builder builder = new FieldDescriptor.Builder("startMs");
            AtProtobuf atProtobuf = new AtProtobuf();
            atProtobuf.tag = 1;
            builder.withProperty(atProtobuf.build());
            STARTMS_DESCRIPTOR = builder.build();
            FieldDescriptor.Builder builder2 = new FieldDescriptor.Builder("endMs");
            AtProtobuf atProtobuf2 = new AtProtobuf();
            atProtobuf2.tag = 2;
            builder2.withProperty(atProtobuf2.build());
            ENDMS_DESCRIPTOR = builder2.build();
        }

        @Override // com.google.firebase.encoders.Encoder
        public void encode(TimeWindow timeWindow, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(STARTMS_DESCRIPTOR, timeWindow.start_ms_);
            objectEncoderContext.add(ENDMS_DESCRIPTOR, timeWindow.end_ms_);
        }
    }

    @Override // com.google.firebase.encoders.config.Configurator
    public void configure(EncoderConfig<?> encoderConfig) {
        encoderConfig.registerEncoder(ProtoEncoderDoNotUse.class, ProtoEncoderDoNotUseEncoder.INSTANCE);
        encoderConfig.registerEncoder(ClientMetrics.class, ClientMetricsEncoder.INSTANCE);
        encoderConfig.registerEncoder(TimeWindow.class, TimeWindowEncoder.INSTANCE);
        encoderConfig.registerEncoder(LogSourceMetrics.class, LogSourceMetricsEncoder.INSTANCE);
        encoderConfig.registerEncoder(LogEventDropped.class, LogEventDroppedEncoder.INSTANCE);
        encoderConfig.registerEncoder(GlobalMetrics.class, GlobalMetricsEncoder.INSTANCE);
        encoderConfig.registerEncoder(StorageMetrics.class, StorageMetricsEncoder.INSTANCE);
    }
}
