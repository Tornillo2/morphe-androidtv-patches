package com.amazon.ignitionshared.network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class SecureSslSocketFactory extends SSLSocketFactory {
    public final SSLSocketFactory delegate;
    public final String[] supportedCipherSuites;

    public SecureSslSocketFactory(SSLSocketFactory sSLSocketFactory) {
        this.delegate = sSLSocketFactory;
        this.supportedCipherSuites = filterSupportedCipherSuites(sSLSocketFactory.getSupportedCipherSuites());
    }

    public static SecureSslSocketFactory createFromDefault() {
        return new SecureSslSocketFactory((SSLSocketFactory) SSLSocketFactory.getDefault());
    }

    public static String[] filterSupportedCipherSuites(String[] strArr) {
        List listAsList = Arrays.asList("TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384", "TLS_RSA_WITH_AES_256_GCM_SHA384", "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256", "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA", "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA", "TLS_RSA_WITH_AES_128_GCM_SHA256", "TLS_RSA_WITH_AES_128_CBC_SHA");
        ArrayList arrayList = new ArrayList(listAsList.size());
        for (String str : strArr) {
            if (listAsList.contains(str)) {
                arrayList.add(str);
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket() throws IOException {
        return wrapSocket(this.delegate.createSocket());
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getDefaultCipherSuites() {
        String[] strArr = this.supportedCipherSuites;
        return (String[]) Arrays.copyOf(strArr, strArr.length);
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getSupportedCipherSuites() {
        String[] strArr = this.supportedCipherSuites;
        return (String[]) Arrays.copyOf(strArr, strArr.length);
    }

    public final Socket wrapSocket(Socket socket) {
        return new SecureSslSocket((SSLSocket) socket, this.supportedCipherSuites);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i) throws IOException {
        return wrapSocket(this.delegate.createSocket(str, i));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException {
        return wrapSocket(this.delegate.createSocket(str, i, inetAddress, i2));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        return wrapSocket(this.delegate.createSocket(inetAddress, i));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        return wrapSocket(this.delegate.createSocket(inetAddress, i, inetAddress2, i2));
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        return wrapSocket(this.delegate.createSocket(socket, str, i, z));
    }
}
