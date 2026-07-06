package com.google.android.datatransport.runtime;

import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline0;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transformer;
import com.google.android.datatransport.runtime.SendRequest;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class AutoValue_SendRequest extends SendRequest {
    public final Encoding encoding;
    public final Event<?> event;
    public final Transformer<?, byte[]> transformer;
    public final TransportContext transportContext;
    public final String transportName;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder extends SendRequest.Builder {
        public Encoding encoding;
        public Event<?> event;
        public Transformer<?, byte[]> transformer;
        public TransportContext transportContext;
        public String transportName;

        @Override // com.google.android.datatransport.runtime.SendRequest.Builder
        public SendRequest build() {
            String strM = this.transportContext == null ? " transportContext" : "";
            if (this.transportName == null) {
                strM = AbstractResolvableFuture$$ExternalSyntheticOutline0.m(strM, " transportName");
            }
            if (this.event == null) {
                strM = AbstractResolvableFuture$$ExternalSyntheticOutline0.m(strM, " event");
            }
            if (this.transformer == null) {
                strM = AbstractResolvableFuture$$ExternalSyntheticOutline0.m(strM, " transformer");
            }
            if (this.encoding == null) {
                strM = AbstractResolvableFuture$$ExternalSyntheticOutline0.m(strM, " encoding");
            }
            if (strM.isEmpty()) {
                return new AutoValue_SendRequest(this.transportContext, this.transportName, this.event, this.transformer, this.encoding);
            }
            throw new IllegalStateException("Missing required properties:".concat(strM));
        }

        @Override // com.google.android.datatransport.runtime.SendRequest.Builder
        public SendRequest.Builder setEncoding(Encoding encoding) {
            if (encoding == null) {
                throw new NullPointerException("Null encoding");
            }
            this.encoding = encoding;
            return this;
        }

        @Override // com.google.android.datatransport.runtime.SendRequest.Builder
        public SendRequest.Builder setEvent(Event<?> event) {
            if (event == null) {
                throw new NullPointerException("Null event");
            }
            this.event = event;
            return this;
        }

        @Override // com.google.android.datatransport.runtime.SendRequest.Builder
        public SendRequest.Builder setTransformer(Transformer<?, byte[]> transformer) {
            if (transformer == null) {
                throw new NullPointerException("Null transformer");
            }
            this.transformer = transformer;
            return this;
        }

        @Override // com.google.android.datatransport.runtime.SendRequest.Builder
        public SendRequest.Builder setTransportContext(TransportContext transportContext) {
            if (transportContext == null) {
                throw new NullPointerException("Null transportContext");
            }
            this.transportContext = transportContext;
            return this;
        }

        @Override // com.google.android.datatransport.runtime.SendRequest.Builder
        public SendRequest.Builder setTransportName(String str) {
            if (str == null) {
                throw new NullPointerException("Null transportName");
            }
            this.transportName = str;
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SendRequest)) {
            return false;
        }
        SendRequest sendRequest = (SendRequest) obj;
        return this.transportContext.equals(sendRequest.getTransportContext()) && this.transportName.equals(sendRequest.getTransportName()) && this.event.equals(sendRequest.getEvent()) && this.transformer.equals(sendRequest.getTransformer()) && this.encoding.equals(sendRequest.getEncoding());
    }

    @Override // com.google.android.datatransport.runtime.SendRequest
    public Encoding getEncoding() {
        return this.encoding;
    }

    @Override // com.google.android.datatransport.runtime.SendRequest
    public Event<?> getEvent() {
        return this.event;
    }

    @Override // com.google.android.datatransport.runtime.SendRequest
    public Transformer<?, byte[]> getTransformer() {
        return this.transformer;
    }

    @Override // com.google.android.datatransport.runtime.SendRequest
    public TransportContext getTransportContext() {
        return this.transportContext;
    }

    @Override // com.google.android.datatransport.runtime.SendRequest
    public String getTransportName() {
        return this.transportName;
    }

    public int hashCode() {
        return ((((((((this.transportContext.hashCode() ^ 1000003) * 1000003) ^ this.transportName.hashCode()) * 1000003) ^ this.event.hashCode()) * 1000003) ^ this.transformer.hashCode()) * 1000003) ^ this.encoding.hashCode();
    }

    public String toString() {
        return "SendRequest{transportContext=" + this.transportContext + ", transportName=" + this.transportName + ", event=" + this.event + ", transformer=" + this.transformer + ", encoding=" + this.encoding + "}";
    }

    public AutoValue_SendRequest(TransportContext transportContext, String str, Event<?> event, Transformer<?, byte[]> transformer, Encoding encoding) {
        this.transportContext = transportContext;
        this.transportName = str;
        this.event = event;
        this.transformer = transformer;
        this.encoding = encoding;
    }
}
