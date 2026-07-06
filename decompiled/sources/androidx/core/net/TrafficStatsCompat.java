package androidx.core.net;

import android.net.TrafficStats;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class TrafficStatsCompat {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(24)
    public static class Api24Impl {
        public static void tagDatagramSocket(DatagramSocket datagramSocket) throws SocketException {
            TrafficStats.tagDatagramSocket(datagramSocket);
        }

        public static void untagDatagramSocket(DatagramSocket datagramSocket) throws SocketException {
            TrafficStats.untagDatagramSocket(datagramSocket);
        }
    }

    @Deprecated
    public static void clearThreadStatsTag() {
        TrafficStats.clearThreadStatsTag();
    }

    @Deprecated
    public static int getThreadStatsTag() {
        return TrafficStats.getThreadStatsTag();
    }

    @Deprecated
    public static void incrementOperationCount(int i) {
        TrafficStats.incrementOperationCount(i);
    }

    @Deprecated
    public static void setThreadStatsTag(int i) {
        TrafficStats.setThreadStatsTag(i);
    }

    public static void tagDatagramSocket(@NonNull DatagramSocket datagramSocket) throws SocketException {
        if (Build.VERSION.SDK_INT >= 24) {
            Api24Impl.tagDatagramSocket(datagramSocket);
            return;
        }
        ParcelFileDescriptor parcelFileDescriptorFromDatagramSocket = ParcelFileDescriptor.fromDatagramSocket(datagramSocket);
        TrafficStats.tagSocket(new DatagramSocketWrapper(datagramSocket, parcelFileDescriptorFromDatagramSocket.getFileDescriptor()));
        parcelFileDescriptorFromDatagramSocket.detachFd();
    }

    @Deprecated
    public static void tagSocket(Socket socket) throws SocketException {
        TrafficStats.tagSocket(socket);
    }

    public static void untagDatagramSocket(@NonNull DatagramSocket datagramSocket) throws SocketException {
        if (Build.VERSION.SDK_INT >= 24) {
            Api24Impl.untagDatagramSocket(datagramSocket);
            return;
        }
        ParcelFileDescriptor parcelFileDescriptorFromDatagramSocket = ParcelFileDescriptor.fromDatagramSocket(datagramSocket);
        TrafficStats.untagSocket(new DatagramSocketWrapper(datagramSocket, parcelFileDescriptorFromDatagramSocket.getFileDescriptor()));
        parcelFileDescriptorFromDatagramSocket.detachFd();
    }

    @Deprecated
    public static void untagSocket(Socket socket) throws SocketException {
        TrafficStats.untagSocket(socket);
    }

    @Deprecated
    public static void incrementOperationCount(int i, int i2) {
        TrafficStats.incrementOperationCount(i, i2);
    }
}
