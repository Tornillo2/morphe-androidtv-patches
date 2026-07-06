package androidx.media3.common;

import androidx.media3.common.util.GlUtil;
import androidx.media3.common.util.UnstableApi;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class GlTextureInfo {
    public static final GlTextureInfo UNSET = new GlTextureInfo(-1, -1, -1, -1, -1);
    public final int fboId;
    public final int height;
    public final int rboId;
    public final int texId;
    public final int width;

    public GlTextureInfo(int i, int i2, int i3, int i4, int i5) {
        this.texId = i;
        this.fboId = i2;
        this.rboId = i3;
        this.width = i4;
        this.height = i5;
    }

    public void release() throws GlUtil.GlException {
        int i = this.texId;
        if (i != -1) {
            GlUtil.deleteTexture(i);
        }
        int i2 = this.fboId;
        if (i2 != -1) {
            GlUtil.deleteFbo(i2);
        }
        int i3 = this.rboId;
        if (i3 != -1) {
            GlUtil.deleteRbo(i3);
        }
    }
}
