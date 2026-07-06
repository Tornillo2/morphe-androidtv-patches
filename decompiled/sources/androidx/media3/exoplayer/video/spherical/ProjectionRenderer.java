package androidx.media3.exoplayer.video.spherical;

import android.opengl.GLES20;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.media3.common.util.GlProgram;
import androidx.media3.common.util.GlUtil;
import androidx.media3.exoplayer.video.spherical.Projection;
import java.nio.Buffer;
import java.nio.FloatBuffer;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class ProjectionRenderer {
    public static final String FRAGMENT_SHADER = "// This is required since the texture data is GL_TEXTURE_EXTERNAL_OES.\n#extension GL_OES_EGL_image_external : require\nprecision mediump float;\n// Standard texture rendering shader.\nuniform samplerExternalOES uTexture;\nvarying vec2 vTexCoords;\nvoid main() {\n  gl_FragColor = texture2D(uTexture, vTexCoords);\n}\n";
    public static final String TAG = "ProjectionRenderer";
    public static final String VERTEX_SHADER = "uniform mat4 uMvpMatrix;\nuniform mat3 uTexMatrix;\nattribute vec4 aPosition;\nattribute vec2 aTexCoords;\nvarying vec2 vTexCoords;\n// Standard transformation.\nvoid main() {\n  gl_Position = uMvpMatrix * aPosition;\n  vTexCoords = (uTexMatrix * vec3(aTexCoords, 1)).xy;\n}\n";

    @Nullable
    public MeshData leftMeshData;
    public int mvpMatrixHandle;
    public int positionHandle;
    public GlProgram program;

    @Nullable
    public MeshData rightMeshData;
    public int stereoMode;
    public int texCoordsHandle;
    public int textureHandle;
    public int uTexMatrixHandle;
    public static final float[] TEX_MATRIX_WHOLE = {1.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 1.0f, 1.0f};
    public static final float[] TEX_MATRIX_TOP = {1.0f, 0.0f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, 0.5f, 1.0f};
    public static final float[] TEX_MATRIX_BOTTOM = {1.0f, 0.0f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, 1.0f, 1.0f};
    public static final float[] TEX_MATRIX_LEFT = {0.5f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 1.0f, 1.0f};
    public static final float[] TEX_MATRIX_RIGHT = {0.5f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.5f, 1.0f, 1.0f};

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class MeshData {
        public final int drawMode;
        public final FloatBuffer textureBuffer;
        public final FloatBuffer vertexBuffer;
        public final int vertexCount;

        public MeshData(Projection.SubMesh subMesh) {
            this.vertexCount = subMesh.getVertexCount();
            this.vertexBuffer = GlUtil.createBuffer(subMesh.vertices);
            this.textureBuffer = GlUtil.createBuffer(subMesh.textureCoords);
            int i = subMesh.mode;
            if (i == 1) {
                this.drawMode = 5;
            } else if (i != 2) {
                this.drawMode = 4;
            } else {
                this.drawMode = 6;
            }
        }
    }

    public static boolean isSupported(Projection projection) {
        Projection.Mesh mesh = projection.leftMesh;
        Projection.Mesh mesh2 = projection.rightMesh;
        Projection.SubMesh[] subMeshArr = mesh.subMeshes;
        if (subMeshArr.length == 1 && subMeshArr[0].textureId == 0) {
            Projection.SubMesh[] subMeshArr2 = mesh2.subMeshes;
            if (subMeshArr2.length == 1 && subMeshArr2[0].textureId == 0) {
                return true;
            }
        }
        return false;
    }

    public void draw(int i, float[] fArr, boolean z) {
        MeshData meshData = z ? this.rightMeshData : this.leftMeshData;
        if (meshData == null) {
            return;
        }
        int i2 = this.stereoMode;
        GLES20.glUniformMatrix3fv(this.uTexMatrixHandle, 1, false, i2 == 1 ? z ? TEX_MATRIX_BOTTOM : TEX_MATRIX_TOP : i2 == 2 ? z ? TEX_MATRIX_RIGHT : TEX_MATRIX_LEFT : TEX_MATRIX_WHOLE, 0);
        GLES20.glUniformMatrix4fv(this.mvpMatrixHandle, 1, false, fArr, 0);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(36197, i);
        GLES20.glUniform1i(this.textureHandle, 0);
        try {
            GlUtil.checkGlError();
        } catch (GlUtil.GlException e) {
            Log.e("ProjectionRenderer", "Failed to bind uniforms", e);
        }
        GLES20.glVertexAttribPointer(this.positionHandle, 3, 5126, false, 12, (Buffer) meshData.vertexBuffer);
        try {
            GlUtil.checkGlError();
        } catch (GlUtil.GlException e2) {
            Log.e("ProjectionRenderer", "Failed to load position data", e2);
        }
        GLES20.glVertexAttribPointer(this.texCoordsHandle, 2, 5126, false, 8, (Buffer) meshData.textureBuffer);
        try {
            GlUtil.checkGlError();
        } catch (GlUtil.GlException e3) {
            Log.e("ProjectionRenderer", "Failed to load texture data", e3);
        }
        GLES20.glDrawArrays(meshData.drawMode, 0, meshData.vertexCount);
        try {
            GlUtil.checkGlError();
        } catch (GlUtil.GlException e4) {
            Log.e("ProjectionRenderer", "Failed to render", e4);
        }
    }

    public void init() {
        try {
            GlProgram glProgram = new GlProgram("uniform mat4 uMvpMatrix;\nuniform mat3 uTexMatrix;\nattribute vec4 aPosition;\nattribute vec2 aTexCoords;\nvarying vec2 vTexCoords;\n// Standard transformation.\nvoid main() {\n  gl_Position = uMvpMatrix * aPosition;\n  vTexCoords = (uTexMatrix * vec3(aTexCoords, 1)).xy;\n}\n", "// This is required since the texture data is GL_TEXTURE_EXTERNAL_OES.\n#extension GL_OES_EGL_image_external : require\nprecision mediump float;\n// Standard texture rendering shader.\nuniform samplerExternalOES uTexture;\nvarying vec2 vTexCoords;\nvoid main() {\n  gl_FragColor = texture2D(uTexture, vTexCoords);\n}\n");
            this.program = glProgram;
            this.mvpMatrixHandle = GLES20.glGetUniformLocation(glProgram.programId, "uMvpMatrix");
            this.uTexMatrixHandle = GLES20.glGetUniformLocation(this.program.programId, "uTexMatrix");
            this.positionHandle = this.program.getAttributeArrayLocationAndEnable("aPosition");
            this.texCoordsHandle = this.program.getAttributeArrayLocationAndEnable("aTexCoords");
            this.textureHandle = GLES20.glGetUniformLocation(this.program.programId, "uTexture");
        } catch (GlUtil.GlException e) {
            Log.e("ProjectionRenderer", "Failed to initialize the program", e);
        }
    }

    public void setProjection(Projection projection) {
        if (isSupported(projection)) {
            this.stereoMode = projection.stereoMode;
            MeshData meshData = new MeshData(projection.leftMesh.subMeshes[0]);
            this.leftMeshData = meshData;
            if (!projection.singleMesh) {
                meshData = new MeshData(projection.rightMesh.subMeshes[0]);
            }
            this.rightMeshData = meshData;
        }
    }

    public void shutdown() {
        GlProgram glProgram = this.program;
        if (glProgram != null) {
            try {
                glProgram.delete();
            } catch (GlUtil.GlException e) {
                Log.e("ProjectionRenderer", "Failed to delete the shader program", e);
            }
        }
    }
}
