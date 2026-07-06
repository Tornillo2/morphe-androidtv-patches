package androidx.media3.exoplayer.util;

import android.os.SystemClock;
import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.exoplayer.upstream.Loader;
import com.google.common.base.Ascii;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.ConcurrentModificationException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class SntpClient {
    public static final String DEFAULT_NTP_HOST = "time.android.com";
    public static final int NTP_LEAP_NOSYNC = 3;
    public static final int NTP_MODE_BROADCAST = 5;
    public static final int NTP_MODE_CLIENT = 3;
    public static final int NTP_MODE_SERVER = 4;
    public static final int NTP_PACKET_SIZE = 48;
    public static final int NTP_PORT = 123;
    public static final int NTP_STRATUM_DEATH = 0;
    public static final int NTP_STRATUM_MAX = 15;
    public static final int NTP_VERSION = 3;
    public static final long OFFSET_1900_TO_1970 = 2208988800L;
    public static final int ORIGINATE_TIME_OFFSET = 24;
    public static final int RECEIVE_TIME_OFFSET = 32;
    public static final int TIMEOUT_MS = 10000;
    public static final int TRANSMIT_TIME_OFFSET = 40;

    @GuardedBy("valueLock")
    public static long elapsedRealtimeOffsetMs = 0;

    @GuardedBy("valueLock")
    public static boolean isInitialized = false;

    @GuardedBy("valueLock")
    public static String ntpHost = "time.android.com";
    public static final Object loaderLock = new Object();
    public static final Object valueLock = new Object();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface InitializationCallback {
        void onInitializationFailed(IOException iOException);

        void onInitialized();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class NtpTimeLoadable implements Loader.Loadable {
        public NtpTimeLoadable() {
        }

        @Override // androidx.media3.exoplayer.upstream.Loader.Loadable
        public void load() throws IOException {
            synchronized (SntpClient.loaderLock) {
                Object obj = SntpClient.valueLock;
                synchronized (obj) {
                    if (SntpClient.isInitialized) {
                        return;
                    }
                    long jLoadNtpTimeOffsetMs = SntpClient.loadNtpTimeOffsetMs();
                    synchronized (obj) {
                        SntpClient.elapsedRealtimeOffsetMs = jLoadNtpTimeOffsetMs;
                        SntpClient.isInitialized = true;
                    }
                }
            }
        }

        public NtpTimeLoadable(AnonymousClass1 anonymousClass1) {
        }

        @Override // androidx.media3.exoplayer.upstream.Loader.Loadable
        public void cancelLoad() {
        }
    }

    public static void checkValidServerReply(byte b, byte b2, int i, long j) throws IOException {
        if (b == 3) {
            throw new IOException("SNTP: Unsynchronized server");
        }
        if (b2 != 4 && b2 != 5) {
            throw new IOException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("SNTP: Untrusted mode: ", b2));
        }
        if (i == 0 || i > 15) {
            throw new IOException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("SNTP: Untrusted stratum: ", i));
        }
        if (j == 0) {
            throw new IOException("SNTP: Zero transmitTime");
        }
    }

    public static long getElapsedRealtimeOffsetMs() {
        long j;
        synchronized (valueLock) {
            try {
                j = isInitialized ? elapsedRealtimeOffsetMs : -9223372036854775807L;
            } catch (Throwable th) {
                throw th;
            }
        }
        return j;
    }

    public static String getNtpHost() {
        String str;
        synchronized (valueLock) {
            str = ntpHost;
        }
        return str;
    }

    public static void initialize(@Nullable Loader loader, @Nullable InitializationCallback initializationCallback) {
        if (isInitialized()) {
            if (initializationCallback != null) {
                initializationCallback.onInitialized();
            }
        } else {
            if (loader == null) {
                loader = new Loader("SntpClient");
            }
            loader.startLoading(new NtpTimeLoadable(), new NtpTimeCallback(initializationCallback), 1);
        }
    }

    public static boolean isInitialized() {
        boolean z;
        synchronized (valueLock) {
            z = isInitialized;
        }
        return z;
    }

    public static long loadNtpTimeOffsetMs() throws IOException {
        InetAddress byName = InetAddress.getByName(getNtpHost());
        DatagramSocket datagramSocket = new DatagramSocket();
        try {
            datagramSocket.setSoTimeout(10000);
            byte[] bArr = new byte[48];
            DatagramPacket datagramPacket = new DatagramPacket(bArr, 48, byName, 123);
            bArr[0] = Ascii.ESC;
            long jCurrentTimeMillis = System.currentTimeMillis();
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            writeTimestamp(bArr, 40, jCurrentTimeMillis);
            datagramSocket.send(datagramPacket);
            datagramSocket.receive(new DatagramPacket(bArr, 48));
            long jElapsedRealtime2 = SystemClock.elapsedRealtime();
            long j = (jElapsedRealtime2 - jElapsedRealtime) + jCurrentTimeMillis;
            byte b = bArr[0];
            int i = bArr[1] & 255;
            long timestamp = readTimestamp(bArr, 24);
            long timestamp2 = readTimestamp(bArr, 32);
            long timestamp3 = readTimestamp(bArr, 40);
            checkValidServerReply((byte) ((b >> 6) & 3), (byte) (b & 7), i, timestamp3);
            long j2 = (j + (((timestamp3 - j) + (timestamp2 - timestamp)) / 2)) - jElapsedRealtime2;
            datagramSocket.close();
            return j2;
        } catch (Throwable th) {
            try {
                datagramSocket.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static long read32(byte[] bArr, int i) {
        int i2 = bArr[i];
        int i3 = bArr[i + 1];
        int i4 = bArr[i + 2];
        int i5 = bArr[i + 3];
        if ((i2 & 128) == 128) {
            i2 = (i2 & 127) + 128;
        }
        if ((i3 & 128) == 128) {
            i3 = (i3 & 127) + 128;
        }
        if ((i4 & 128) == 128) {
            i4 = (i4 & 127) + 128;
        }
        if ((i5 & 128) == 128) {
            i5 = (i5 & 127) + 128;
        }
        return (((long) i2) << 24) + (((long) i3) << 16) + (((long) i4) << 8) + ((long) i5);
    }

    public static long readTimestamp(byte[] bArr, int i) {
        long j = read32(bArr, i);
        long j2 = read32(bArr, i + 4);
        if (j == 0 && j2 == 0) {
            return 0L;
        }
        return ((j2 * 1000) / 4294967296L) + ((j - 2208988800L) * 1000);
    }

    public static void setNtpHost(String str) {
        synchronized (valueLock) {
            try {
                if (!ntpHost.equals(str)) {
                    ntpHost = str;
                    isInitialized = false;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static void writeTimestamp(byte[] bArr, int i, long j) {
        if (j == 0) {
            Arrays.fill(bArr, i, i + 8, (byte) 0);
            return;
        }
        long j2 = j / 1000;
        long j3 = j - (j2 * 1000);
        bArr[i] = (byte) (r2 >> 24);
        bArr[i + 1] = (byte) (r2 >> 16);
        bArr[i + 2] = (byte) (r2 >> 8);
        bArr[i + 3] = (byte) (j2 + 2208988800L);
        long j4 = (j3 * 4294967296L) / 1000;
        bArr[i + 4] = (byte) (j4 >> 24);
        bArr[i + 5] = (byte) (j4 >> 16);
        bArr[i + 6] = (byte) (j4 >> 8);
        bArr[i + 7] = (byte) (Math.random() * 255.0d);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class NtpTimeCallback implements Loader.Callback<Loader.Loadable> {

        @Nullable
        public final InitializationCallback callback;

        public NtpTimeCallback(@Nullable InitializationCallback initializationCallback) {
            this.callback = initializationCallback;
        }

        @Override // androidx.media3.exoplayer.upstream.Loader.Callback
        public void onLoadCompleted(Loader.Loadable loadable, long j, long j2) {
            if (this.callback != null) {
                if (SntpClient.isInitialized()) {
                    this.callback.onInitialized();
                } else {
                    this.callback.onInitializationFailed(new IOException(new ConcurrentModificationException()));
                }
            }
        }

        @Override // androidx.media3.exoplayer.upstream.Loader.Callback
        public Loader.LoadErrorAction onLoadError(Loader.Loadable loadable, long j, long j2, IOException iOException, int i) {
            InitializationCallback initializationCallback = this.callback;
            if (initializationCallback != null) {
                initializationCallback.onInitializationFailed(iOException);
            }
            return Loader.DONT_RETRY;
        }

        @Override // androidx.media3.exoplayer.upstream.Loader.Callback
        public void onLoadCanceled(Loader.Loadable loadable, long j, long j2, boolean z) {
        }
    }
}
