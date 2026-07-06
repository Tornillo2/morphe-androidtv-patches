package com.android.volley.toolbox;

import com.android.volley.AuthFailureError;
import com.android.volley.Header;
import com.android.volley.Request;
import j$.util.DesugarCollections;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import org.apache.http.ProtocolVersion;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class BaseHttpStack implements HttpStack {
    public abstract HttpResponse executeRequest(Request<?> request, Map<String, String> map) throws IOException, AuthFailureError;

    @Override // com.android.volley.toolbox.HttpStack
    @Deprecated
    public final org.apache.http.HttpResponse performRequest(Request<?> request, Map<String, String> map) throws IOException, AuthFailureError {
        HttpResponse httpResponseExecuteRequest = executeRequest(request, map);
        BasicHttpResponse basicHttpResponse = new BasicHttpResponse(new BasicStatusLine(new ProtocolVersion("HTTP", 1, 1), httpResponseExecuteRequest.mStatusCode, ""));
        ArrayList arrayList = new ArrayList();
        for (Header header : DesugarCollections.unmodifiableList(httpResponseExecuteRequest.mHeaders)) {
            arrayList.add(new BasicHeader(header.mName, header.mValue));
        }
        basicHttpResponse.setHeaders((org.apache.http.Header[]) arrayList.toArray(new org.apache.http.Header[0]));
        InputStream content = httpResponseExecuteRequest.getContent();
        if (content != null) {
            BasicHttpEntity basicHttpEntity = new BasicHttpEntity();
            basicHttpEntity.setContent(content);
            basicHttpEntity.setContentLength(httpResponseExecuteRequest.mContentLength);
            basicHttpResponse.setEntity(basicHttpEntity);
        }
        return basicHttpResponse;
    }
}
