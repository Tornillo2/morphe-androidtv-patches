package com.amazon.ignitionshared.network;

import android.os.Build;
import androidx.annotation.NonNull;
import com.amazon.reporting.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Arrays;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class SecureSslSocket extends SSLSocket {
    public static final String TAG = "SecureSslSocket";
    public final SSLSocket delegate;
    public final String[] supportedCipherSuites;

    public SecureSslSocket(@NonNull SSLSocket sSLSocket, @NonNull String[] strArr) {
        this.delegate = sSLSocket;
        this.supportedCipherSuites = strArr;
        sSLSocket.setEnabledCipherSuites(strArr);
    }

    @Override // javax.net.ssl.SSLSocket
    public void addHandshakeCompletedListener(HandshakeCompletedListener handshakeCompletedListener) {
        this.delegate.addHandshakeCompletedListener(handshakeCompletedListener);
    }

    @Override // java.net.Socket
    public void bind(SocketAddress socketAddress) throws IOException {
        this.delegate.bind(socketAddress);
    }

    @Override // java.net.Socket, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        this.delegate.close();
    }

    @Override // java.net.Socket
    public void connect(SocketAddress socketAddress) throws IOException {
        this.delegate.connect(socketAddress);
    }

    @Override // java.net.Socket
    public SocketChannel getChannel() {
        return this.delegate.getChannel();
    }

    @Override // javax.net.ssl.SSLSocket
    public boolean getEnableSessionCreation() {
        return this.delegate.getEnableSessionCreation();
    }

    @Override // javax.net.ssl.SSLSocket
    public String[] getEnabledCipherSuites() {
        return this.delegate.getEnabledCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocket
    public String[] getEnabledProtocols() {
        return this.delegate.getEnabledProtocols();
    }

    @Override // javax.net.ssl.SSLSocket
    public SSLSession getHandshakeSession() {
        if (Build.VERSION.SDK_INT >= 24) {
            return this.delegate.getHandshakeSession();
        }
        throw new UnsupportedOperationException();
    }

    @Override // java.net.Socket
    public InetAddress getInetAddress() {
        return this.delegate.getInetAddress();
    }

    @Override // java.net.Socket
    public InputStream getInputStream() throws IOException {
        return this.delegate.getInputStream();
    }

    @Override // java.net.Socket
    public boolean getKeepAlive() throws SocketException {
        return this.delegate.getKeepAlive();
    }

    @Override // java.net.Socket
    public InetAddress getLocalAddress() {
        return this.delegate.getLocalAddress();
    }

    @Override // java.net.Socket
    public int getLocalPort() {
        return this.delegate.getLocalPort();
    }

    @Override // java.net.Socket
    public SocketAddress getLocalSocketAddress() {
        return this.delegate.getLocalSocketAddress();
    }

    @Override // javax.net.ssl.SSLSocket
    public boolean getNeedClientAuth() {
        return this.delegate.getNeedClientAuth();
    }

    @Override // java.net.Socket
    public boolean getOOBInline() throws SocketException {
        return this.delegate.getOOBInline();
    }

    @Override // java.net.Socket
    public OutputStream getOutputStream() throws IOException {
        return this.delegate.getOutputStream();
    }

    @Override // java.net.Socket
    public int getPort() {
        return this.delegate.getPort();
    }

    @Override // java.net.Socket
    public synchronized int getReceiveBufferSize() throws SocketException {
        return this.delegate.getReceiveBufferSize();
    }

    @Override // java.net.Socket
    public SocketAddress getRemoteSocketAddress() {
        return this.delegate.getRemoteSocketAddress();
    }

    @Override // java.net.Socket
    public boolean getReuseAddress() throws SocketException {
        return this.delegate.getReuseAddress();
    }

    @Override // javax.net.ssl.SSLSocket
    public SSLParameters getSSLParameters() {
        return this.delegate.getSSLParameters();
    }

    @Override // java.net.Socket
    public synchronized int getSendBufferSize() throws SocketException {
        return this.delegate.getSendBufferSize();
    }

    @Override // javax.net.ssl.SSLSocket
    public SSLSession getSession() {
        return this.delegate.getSession();
    }

    @Override // java.net.Socket
    public int getSoLinger() throws SocketException {
        return this.delegate.getSoLinger();
    }

    @Override // java.net.Socket
    public synchronized int getSoTimeout() throws SocketException {
        return this.delegate.getSoTimeout();
    }

    @Override // javax.net.ssl.SSLSocket
    public String[] getSupportedCipherSuites() {
        String[] strArr = this.supportedCipherSuites;
        return (String[]) Arrays.copyOf(strArr, strArr.length);
    }

    @Override // javax.net.ssl.SSLSocket
    public String[] getSupportedProtocols() {
        return this.delegate.getSupportedProtocols();
    }

    @Override // java.net.Socket
    public boolean getTcpNoDelay() throws SocketException {
        return this.delegate.getTcpNoDelay();
    }

    @Override // java.net.Socket
    public int getTrafficClass() throws SocketException {
        return this.delegate.getTrafficClass();
    }

    @Override // javax.net.ssl.SSLSocket
    public boolean getUseClientMode() {
        return this.delegate.getUseClientMode();
    }

    @Override // javax.net.ssl.SSLSocket
    public boolean getWantClientAuth() {
        return this.delegate.getWantClientAuth();
    }

    @Override // java.net.Socket
    public boolean isBound() {
        return this.delegate.isBound();
    }

    @Override // java.net.Socket
    public boolean isClosed() {
        return this.delegate.isClosed();
    }

    @Override // java.net.Socket
    public boolean isConnected() {
        return this.delegate.isConnected();
    }

    @Override // java.net.Socket
    public boolean isInputShutdown() {
        return this.delegate.isInputShutdown();
    }

    @Override // java.net.Socket
    public boolean isOutputShutdown() {
        return this.delegate.isOutputShutdown();
    }

    @Override // javax.net.ssl.SSLSocket
    public void removeHandshakeCompletedListener(HandshakeCompletedListener handshakeCompletedListener) {
        this.delegate.removeHandshakeCompletedListener(handshakeCompletedListener);
    }

    @Override // java.net.Socket
    public void sendUrgentData(int i) throws IOException {
        this.delegate.sendUrgentData(i);
    }

    @Override // javax.net.ssl.SSLSocket
    public void setEnableSessionCreation(boolean z) {
        this.delegate.setEnableSessionCreation(z);
    }

    @Override // javax.net.ssl.SSLSocket
    public void setEnabledCipherSuites(String[] strArr) {
        if (strArr == null) {
            throw new IllegalArgumentException("Attempted to set enabled cipher suites to null");
        }
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            String[] strArr2 = this.supportedCipherSuites;
            int length = strArr2.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    Log.w(TAG, "Suite \"" + str + "\" was filtered out as not supported");
                    break;
                }
                if (strArr2[i].equals(str)) {
                    arrayList.add(str);
                    break;
                }
                i++;
            }
        }
        if (arrayList.isEmpty()) {
            throw new IllegalArgumentException("Attempted to only enable unsupported cipher suites");
        }
        this.delegate.setEnabledCipherSuites((String[]) arrayList.toArray(new String[0]));
    }

    @Override // javax.net.ssl.SSLSocket
    public void setEnabledProtocols(String[] strArr) {
        this.delegate.setEnabledProtocols(strArr);
    }

    @Override // java.net.Socket
    public void setKeepAlive(boolean z) throws SocketException {
        this.delegate.setKeepAlive(z);
    }

    @Override // javax.net.ssl.SSLSocket
    public void setNeedClientAuth(boolean z) {
        this.delegate.setNeedClientAuth(z);
    }

    @Override // java.net.Socket
    public void setOOBInline(boolean z) throws SocketException {
        this.delegate.setOOBInline(z);
    }

    @Override // java.net.Socket
    public void setPerformancePreferences(int i, int i2, int i3) {
        this.delegate.setPerformancePreferences(i, i2, i3);
    }

    @Override // java.net.Socket
    public synchronized void setReceiveBufferSize(int i) throws SocketException {
        this.delegate.setReceiveBufferSize(i);
    }

    @Override // java.net.Socket
    public void setReuseAddress(boolean z) throws SocketException {
        this.delegate.setReuseAddress(z);
    }

    @Override // javax.net.ssl.SSLSocket
    public void setSSLParameters(SSLParameters sSLParameters) {
        this.delegate.setSSLParameters(sSLParameters);
    }

    @Override // java.net.Socket
    public synchronized void setSendBufferSize(int i) throws SocketException {
        this.delegate.setSendBufferSize(i);
    }

    @Override // java.net.Socket
    public void setSoLinger(boolean z, int i) throws SocketException {
        this.delegate.setSoLinger(z, i);
    }

    @Override // java.net.Socket
    public synchronized void setSoTimeout(int i) throws SocketException {
        this.delegate.setSoTimeout(i);
    }

    @Override // java.net.Socket
    public void setTcpNoDelay(boolean z) throws SocketException {
        this.delegate.setTcpNoDelay(z);
    }

    @Override // java.net.Socket
    public void setTrafficClass(int i) throws SocketException {
        this.delegate.setTrafficClass(i);
    }

    @Override // javax.net.ssl.SSLSocket
    public void setUseClientMode(boolean z) {
        this.delegate.setUseClientMode(z);
    }

    @Override // javax.net.ssl.SSLSocket
    public void setWantClientAuth(boolean z) {
        this.delegate.setWantClientAuth(z);
    }

    @Override // java.net.Socket
    public void shutdownInput() throws IOException {
        this.delegate.shutdownInput();
    }

    @Override // java.net.Socket
    public void shutdownOutput() throws IOException {
        this.delegate.shutdownOutput();
    }

    @Override // javax.net.ssl.SSLSocket
    public void startHandshake() throws IOException {
        this.delegate.startHandshake();
    }

    @Override // java.net.Socket
    public void connect(SocketAddress socketAddress, int i) throws IOException {
        this.delegate.connect(socketAddress, i);
    }
}
