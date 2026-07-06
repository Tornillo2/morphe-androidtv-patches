package androidx.media3.common.util;

import android.content.Context;
import android.opengl.GLES20;
import androidx.annotation.Nullable;
import androidx.media3.common.util.GlUtil;
import java.io.IOException;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class GlProgram {
    public static final int GL_SAMPLER_EXTERNAL_2D_Y2Y_EXT = 35815;
    public final Map<String, Attribute> attributeByName;
    public final Attribute[] attributes;
    public final int programId;
    public final Map<String, Uniform> uniformByName;
    public final Uniform[] uniforms;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Attribute {

        @Nullable
        public Buffer buffer;
        public final int location;
        public final String name;
        public int size;

        public Attribute(String str, int i) {
            this.name = str;
            this.location = i;
        }

        public static Attribute create(int i, int i2) {
            int[] iArr = new int[1];
            GLES20.glGetProgramiv(i, 35722, iArr, 0);
            int i3 = iArr[0];
            byte[] bArr = new byte[i3];
            GLES20.glGetActiveAttrib(i, i2, i3, new int[1], 0, new int[1], 0, new int[1], 0, bArr, 0);
            String str = new String(bArr, 0, GlProgram.getCStringLength(bArr));
            return new Attribute(str, GLES20.glGetAttribLocation(i, str));
        }

        public void bind() throws GlUtil.GlException {
            Buffer buffer = this.buffer;
            Assertions.checkNotNull(buffer, "call setBuffer before bind");
            GLES20.glBindBuffer(34962, 0);
            GLES20.glVertexAttribPointer(this.location, this.size, 5126, false, 0, buffer);
            GLES20.glEnableVertexAttribArray(this.location);
            GlUtil.checkGlError();
        }

        public void setBuffer(float[] fArr, int i) {
            this.buffer = GlUtil.createBuffer(fArr);
            this.size = i;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Uniform {
        public final float[] floatValue = new float[16];
        public final int[] intValue = new int[4];
        public final int location;
        public final String name;
        public int texIdValue;
        public int texUnitIndex;
        public final int type;

        public Uniform(String str, int i, int i2) {
            this.name = str;
            this.location = i;
            this.type = i2;
        }

        public static Uniform create(int i, int i2) {
            int[] iArr = new int[1];
            GLES20.glGetProgramiv(i, 35719, iArr, 0);
            int[] iArr2 = new int[1];
            int i3 = iArr[0];
            byte[] bArr = new byte[i3];
            GLES20.glGetActiveUniform(i, i2, i3, new int[1], 0, new int[1], 0, iArr2, 0, bArr, 0);
            String str = new String(bArr, 0, GlProgram.getCStringLength(bArr));
            return new Uniform(str, GLES20.glGetUniformLocation(i, str), iArr2[0]);
        }

        public void bind() throws GlUtil.GlException {
            switch (this.type) {
                case 5124:
                    GLES20.glUniform1iv(this.location, 1, this.intValue, 0);
                    GlUtil.checkGlError();
                    return;
                case 5126:
                    GLES20.glUniform1fv(this.location, 1, this.floatValue, 0);
                    GlUtil.checkGlError();
                    return;
                case 35664:
                    GLES20.glUniform2fv(this.location, 1, this.floatValue, 0);
                    GlUtil.checkGlError();
                    return;
                case 35665:
                    GLES20.glUniform3fv(this.location, 1, this.floatValue, 0);
                    GlUtil.checkGlError();
                    return;
                case 35667:
                    GLES20.glUniform2iv(this.location, 1, this.intValue, 0);
                    GlUtil.checkGlError();
                    return;
                case 35668:
                    GLES20.glUniform3iv(this.location, 1, this.intValue, 0);
                    GlUtil.checkGlError();
                    return;
                case 35669:
                    GLES20.glUniform4iv(this.location, 1, this.intValue, 0);
                    GlUtil.checkGlError();
                    return;
                case 35675:
                    GLES20.glUniformMatrix3fv(this.location, 1, false, this.floatValue, 0);
                    GlUtil.checkGlError();
                    return;
                case 35676:
                    GLES20.glUniformMatrix4fv(this.location, 1, false, this.floatValue, 0);
                    GlUtil.checkGlError();
                    return;
                case 35678:
                case 35815:
                case 36198:
                    if (this.texIdValue == 0) {
                        throw new IllegalStateException("No call to setSamplerTexId() before bind.");
                    }
                    GLES20.glActiveTexture(this.texUnitIndex + 33984);
                    GlUtil.checkGlError();
                    GlUtil.bindTexture(this.type == 35678 ? 3553 : 36197, this.texIdValue);
                    GLES20.glUniform1i(this.location, this.texUnitIndex);
                    GlUtil.checkGlError();
                    return;
                default:
                    throw new IllegalStateException("Unexpected uniform type: " + this.type);
            }
        }

        public void setFloat(float f) {
            this.floatValue[0] = f;
        }

        public void setFloats(float[] fArr) {
            System.arraycopy(fArr, 0, this.floatValue, 0, fArr.length);
        }

        public void setInt(int i) {
            this.intValue[0] = i;
        }

        public void setInts(int[] iArr) {
            System.arraycopy(iArr, 0, this.intValue, 0, iArr.length);
        }

        public void setSamplerTexId(int i, int i2) {
            this.texIdValue = i;
            this.texUnitIndex = i2;
        }
    }

    public GlProgram(Context context, String str, String str2) throws IOException, GlUtil.GlException {
        this(Util.loadAsset(context, str), Util.loadAsset(context, str2));
    }

    public static void addShader(int i, int i2, String str) throws GlUtil.GlException {
        int iGlCreateShader = GLES20.glCreateShader(i2);
        GLES20.glShaderSource(iGlCreateShader, str);
        GLES20.glCompileShader(iGlCreateShader);
        int[] iArr = {0};
        GLES20.glGetShaderiv(iGlCreateShader, 35713, iArr, 0);
        GlUtil.checkGlException(iArr[0] == 1, GLES20.glGetShaderInfoLog(iGlCreateShader) + ", source: " + str);
        GLES20.glAttachShader(i, iGlCreateShader);
        GLES20.glDeleteShader(iGlCreateShader);
        GlUtil.checkGlError();
    }

    public static int getAttributeLocation(int i, String str) {
        return GLES20.glGetAttribLocation(i, str);
    }

    public static int getCStringLength(byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            if (bArr[i] == 0) {
                return i;
            }
        }
        return bArr.length;
    }

    public static int getUniformLocation(int i, String str) {
        return GLES20.glGetUniformLocation(i, str);
    }

    public void bindAttributesAndUniforms() throws GlUtil.GlException {
        for (Attribute attribute : this.attributes) {
            attribute.bind();
        }
        for (Uniform uniform : this.uniforms) {
            uniform.bind();
        }
    }

    public void delete() throws GlUtil.GlException {
        GLES20.glDeleteProgram(this.programId);
        GlUtil.checkGlError();
    }

    public int getAttributeArrayLocationAndEnable(String str) throws GlUtil.GlException {
        int iGlGetAttribLocation = GLES20.glGetAttribLocation(this.programId, str);
        GLES20.glEnableVertexAttribArray(iGlGetAttribLocation);
        GlUtil.checkGlError();
        return iGlGetAttribLocation;
    }

    public void setBufferAttribute(String str, float[] fArr, int i) {
        Attribute attribute = this.attributeByName.get(str);
        attribute.getClass();
        attribute.setBuffer(fArr, i);
    }

    public void setFloatUniform(String str, float f) {
        Uniform uniform = this.uniformByName.get(str);
        uniform.getClass();
        uniform.setFloat(f);
    }

    public void setFloatsUniform(String str, float[] fArr) {
        Uniform uniform = this.uniformByName.get(str);
        uniform.getClass();
        uniform.setFloats(fArr);
    }

    public void setIntUniform(String str, int i) {
        Uniform uniform = this.uniformByName.get(str);
        uniform.getClass();
        uniform.setInt(i);
    }

    public void setIntsUniform(String str, int[] iArr) {
        Uniform uniform = this.uniformByName.get(str);
        uniform.getClass();
        uniform.setInts(iArr);
    }

    public void setSamplerTexIdUniform(String str, int i, int i2) {
        Uniform uniform = this.uniformByName.get(str);
        uniform.getClass();
        uniform.texIdValue = i;
        uniform.texUnitIndex = i2;
    }

    public void use() throws GlUtil.GlException {
        GLES20.glUseProgram(this.programId);
        GlUtil.checkGlError();
    }

    public final int getAttributeLocation(String str) {
        return GLES20.glGetAttribLocation(this.programId, str);
    }

    public int getUniformLocation(String str) {
        return GLES20.glGetUniformLocation(this.programId, str);
    }

    public GlProgram(String str, String str2) throws GlUtil.GlException {
        int iGlCreateProgram = GLES20.glCreateProgram();
        this.programId = iGlCreateProgram;
        GlUtil.checkGlError();
        addShader(iGlCreateProgram, 35633, str);
        addShader(iGlCreateProgram, 35632, str2);
        GLES20.glLinkProgram(iGlCreateProgram);
        int[] iArr = {0};
        GLES20.glGetProgramiv(iGlCreateProgram, 35714, iArr, 0);
        GlUtil.checkGlException(iArr[0] == 1, "Unable to link shader program: \n" + GLES20.glGetProgramInfoLog(iGlCreateProgram));
        GLES20.glUseProgram(iGlCreateProgram);
        this.attributeByName = new HashMap();
        int[] iArr2 = new int[1];
        GLES20.glGetProgramiv(iGlCreateProgram, 35721, iArr2, 0);
        this.attributes = new Attribute[iArr2[0]];
        for (int i = 0; i < iArr2[0]; i++) {
            Attribute attributeCreate = Attribute.create(this.programId, i);
            this.attributes[i] = attributeCreate;
            this.attributeByName.put(attributeCreate.name, attributeCreate);
        }
        this.uniformByName = new HashMap();
        int[] iArr3 = new int[1];
        GLES20.glGetProgramiv(this.programId, 35718, iArr3, 0);
        this.uniforms = new Uniform[iArr3[0]];
        for (int i2 = 0; i2 < iArr3[0]; i2++) {
            Uniform uniformCreate = Uniform.create(this.programId, i2);
            this.uniforms[i2] = uniformCreate;
            this.uniformByName.put(uniformCreate.name, uniformCreate);
        }
        GlUtil.checkGlError();
    }
}
