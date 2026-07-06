package androidx.profileinstaller;

import androidx.annotation.RestrictTo;
import java.util.Arrays;
import org.apache.commons.compress.archivers.tar.TarConstants;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY})
public class ProfileVersion {
    public static final int MAX_SUPPORTED_SDK = 34;
    public static final int MIN_SUPPORTED_SDK = 24;
    public static final byte[] V015_S = {48, TarConstants.LF_LINK, TarConstants.LF_DIR, 0};
    public static final byte[] V010_P = {48, TarConstants.LF_LINK, 48, 0};
    public static final byte[] V009_O_MR1 = {48, 48, 57, 0};
    public static final byte[] V005_O = {48, 48, TarConstants.LF_DIR, 0};
    public static final byte[] V001_N = {48, 48, TarConstants.LF_LINK, 0};
    public static final byte[] METADATA_V001_N = {48, 48, TarConstants.LF_LINK, 0};
    public static final byte[] METADATA_V002 = {48, 48, TarConstants.LF_SYMLINK, 0};

    public static String dexKeySeparator(byte[] bArr) {
        return (Arrays.equals(bArr, V001_N) || Arrays.equals(bArr, V005_O)) ? ":" : "!";
    }
}
