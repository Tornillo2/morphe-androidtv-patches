package org.apache.commons.lang3.exception;

import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.tuple.Pair;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class ContextedRuntimeException extends RuntimeException implements ExceptionContext {
    public static final long serialVersionUID = 20110706;
    public final ExceptionContext exceptionContext;

    public ContextedRuntimeException() {
        this.exceptionContext = new DefaultExceptionContext();
    }

    @Override // org.apache.commons.lang3.exception.ExceptionContext
    public List<Pair<String, Object>> getContextEntries() {
        return this.exceptionContext.getContextEntries();
    }

    @Override // org.apache.commons.lang3.exception.ExceptionContext
    public Set<String> getContextLabels() {
        return this.exceptionContext.getContextLabels();
    }

    @Override // org.apache.commons.lang3.exception.ExceptionContext
    public List<Object> getContextValues(String str) {
        return this.exceptionContext.getContextValues(str);
    }

    @Override // org.apache.commons.lang3.exception.ExceptionContext
    public Object getFirstContextValue(String str) {
        return this.exceptionContext.getFirstContextValue(str);
    }

    @Override // org.apache.commons.lang3.exception.ExceptionContext
    public String getFormattedExceptionMessage(String str) {
        return this.exceptionContext.getFormattedExceptionMessage(str);
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return getFormattedExceptionMessage(super.getMessage());
    }

    public String getRawMessage() {
        return super.getMessage();
    }

    @Override // org.apache.commons.lang3.exception.ExceptionContext
    public ContextedRuntimeException addContextValue(String str, Object obj) {
        this.exceptionContext.addContextValue(str, obj);
        return this;
    }

    @Override // org.apache.commons.lang3.exception.ExceptionContext
    public ContextedRuntimeException setContextValue(String str, Object obj) {
        this.exceptionContext.setContextValue(str, obj);
        return this;
    }

    public ContextedRuntimeException(String str) {
        super(str);
        this.exceptionContext = new DefaultExceptionContext();
    }

    public ContextedRuntimeException(Throwable th) {
        super(th);
        this.exceptionContext = new DefaultExceptionContext();
    }

    public ContextedRuntimeException(String str, Throwable th) {
        super(str, th);
        this.exceptionContext = new DefaultExceptionContext();
    }

    public ContextedRuntimeException(String str, Throwable th, ExceptionContext exceptionContext) {
        super(str, th);
        this.exceptionContext = exceptionContext == null ? new DefaultExceptionContext() : exceptionContext;
    }
}
