package androidx.room.util;

import android.annotation.SuppressLint;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class FileUtil {
    @SuppressLint({"LambdaLast"})
    public static void copy(@NonNull ReadableByteChannel readableByteChannel, @NonNull FileChannel fileChannel) throws Throwable {
        ReadableByteChannel readableByteChannel2;
        FileChannel fileChannel2;
        try {
            try {
                if (Build.VERSION.SDK_INT <= 23) {
                    readableByteChannel2 = readableByteChannel;
                    fileChannel2 = fileChannel;
                    InputStream inputStreamNewInputStream = Channels.newInputStream(readableByteChannel2);
                    OutputStream outputStreamNewOutputStream = Channels.newOutputStream(fileChannel2);
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int i = inputStreamNewInputStream.read(bArr);
                        if (i <= 0) {
                            break;
                        } else {
                            outputStreamNewOutputStream.write(bArr, 0, i);
                        }
                    }
                } else {
                    readableByteChannel2 = readableByteChannel;
                    fileChannel2 = fileChannel;
                    fileChannel2.transferFrom(readableByteChannel2, 0L, Long.MAX_VALUE);
                }
                fileChannel2.force(false);
                readableByteChannel2.close();
                fileChannel2.close();
            } catch (Throwable th) {
                th = th;
                Throwable th2 = th;
                readableByteChannel2.close();
                fileChannel2.close();
                throw th2;
            }
        } catch (Throwable th3) {
            th = th3;
            readableByteChannel2 = readableByteChannel;
            fileChannel2 = fileChannel;
        }
    }
}
