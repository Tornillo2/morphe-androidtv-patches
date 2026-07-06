package androidx.media3.common;

import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import androidx.annotation.IntRange;
import androidx.annotation.RequiresApi;
import androidx.media3.common.util.GlUtil;
import androidx.media3.common.util.UnstableApi;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public interface GlObjectsProvider {
    GlTextureInfo createBuffersForTexture(int i, int i2, int i3) throws GlUtil.GlException;

    @RequiresApi(17)
    EGLContext createEglContext(EGLDisplay eGLDisplay, @IntRange(from = 2, to = 3) int i, int[] iArr) throws GlUtil.GlException;

    @RequiresApi(17)
    EGLSurface createEglSurface(EGLDisplay eGLDisplay, Object obj, int i, boolean z) throws GlUtil.GlException;

    @RequiresApi(17)
    EGLSurface createFocusedPlaceholderEglSurface(EGLContext eGLContext, EGLDisplay eGLDisplay) throws GlUtil.GlException;
}
