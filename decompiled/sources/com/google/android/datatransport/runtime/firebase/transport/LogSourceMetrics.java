package com.google.android.datatransport.runtime.firebase.transport;

import com.google.firebase.encoders.annotations.Encodable;
import com.google.firebase.encoders.proto.Protobuf;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class LogSourceMetrics {
    public static final LogSourceMetrics DEFAULT_INSTANCE = new Builder().build();
    public final List<LogEventDropped> log_event_dropped_;
    public final String log_source_;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder {
        public String log_source_ = "";
        public List<LogEventDropped> log_event_dropped_ = new ArrayList();

        public Builder addLogEventDropped(LogEventDropped logEventDropped) {
            this.log_event_dropped_.add(logEventDropped);
            return this;
        }

        public LogSourceMetrics build() {
            return new LogSourceMetrics(this.log_source_, DesugarCollections.unmodifiableList(this.log_event_dropped_));
        }

        public Builder setLogEventDroppedList(List<LogEventDropped> list) {
            this.log_event_dropped_ = list;
            return this;
        }

        public Builder setLogSource(String str) {
            this.log_source_ = str;
            return this;
        }
    }

    public LogSourceMetrics(String str, List<LogEventDropped> list) {
        this.log_source_ = str;
        this.log_event_dropped_ = list;
    }

    public static LogSourceMetrics getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Protobuf(tag = 2)
    @Encodable.Field(name = "logEventDropped")
    public List<LogEventDropped> getLogEventDroppedList() {
        return this.log_event_dropped_;
    }

    @Protobuf(tag = 1)
    public String getLogSource() {
        return this.log_source_;
    }
}
