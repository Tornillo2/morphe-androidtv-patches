package com.amazon.ion.facet;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class UnsupportedFacetException extends UnsupportedOperationException {
    public static final long serialVersionUID = 1;
    public Class<?> myFacetType;
    public Object mySubject;

    public UnsupportedFacetException(Class<?> cls, Object obj) {
        this.myFacetType = cls;
        this.mySubject = obj;
    }

    public Class<?> getFacetType() {
        return this.myFacetType;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return "Facet " + this.myFacetType.getName() + " is not supported by " + this.mySubject;
    }

    public Object getSubject() {
        return this.mySubject;
    }
}
