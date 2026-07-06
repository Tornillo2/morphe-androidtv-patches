package androidx.media3.exoplayer.video.spherical;

import androidx.media3.common.util.Assertions;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class Projection {
    public static final int DRAW_MODE_TRIANGLES = 0;
    public static final int DRAW_MODE_TRIANGLES_FAN = 2;
    public static final int DRAW_MODE_TRIANGLES_STRIP = 1;
    public static final int POSITION_COORDS_PER_VERTEX = 3;
    public static final int TEXTURE_COORDS_PER_VERTEX = 2;
    public final Mesh leftMesh;
    public final Mesh rightMesh;
    public final boolean singleMesh;
    public final int stereoMode;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface DrawMode {
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Mesh {
        public final SubMesh[] subMeshes;

        public Mesh(SubMesh... subMeshArr) {
            this.subMeshes = subMeshArr;
        }

        public SubMesh getSubMesh(int i) {
            return this.subMeshes[i];
        }

        public int getSubMeshCount() {
            return this.subMeshes.length;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SubMesh {
        public static final int VIDEO_TEXTURE_ID = 0;
        public final int mode;
        public final float[] textureCoords;
        public final int textureId;
        public final float[] vertices;

        public SubMesh(int i, float[] fArr, float[] fArr2, int i2) {
            this.textureId = i;
            Assertions.checkArgument(((long) fArr.length) * 2 == ((long) fArr2.length) * 3);
            this.vertices = fArr;
            this.textureCoords = fArr2;
            this.mode = i2;
        }

        public int getVertexCount() {
            return this.vertices.length / 3;
        }
    }

    public Projection(Mesh mesh, int i) {
        this(mesh, mesh, i);
    }

    public static Projection createEquirectangular(int i) {
        return createEquirectangular(50.0f, 36, 72, 180.0f, 360.0f, i);
    }

    public Projection(Mesh mesh, Mesh mesh2, int i) {
        this.leftMesh = mesh;
        this.rightMesh = mesh2;
        this.stereoMode = i;
        this.singleMesh = mesh == mesh2;
    }

    public static Projection createEquirectangular(float f, int i, int i2, float f2, float f3, int i3) {
        int i4;
        float f4;
        float f5;
        int i5 = i;
        Assertions.checkArgument(f > 0.0f);
        Assertions.checkArgument(i5 >= 1);
        Assertions.checkArgument(i2 >= 1);
        Assertions.checkArgument(f2 > 0.0f && f2 <= 180.0f);
        Assertions.checkArgument(f3 > 0.0f && f3 <= 360.0f);
        float radians = (float) Math.toRadians(f2);
        float radians2 = (float) Math.toRadians(f3);
        float f6 = radians / i5;
        float f7 = radians2 / i2;
        int i6 = i2 + 1;
        int i7 = ((i6 * 2) + 2) * i5;
        float[] fArr = new float[i7 * 3];
        float[] fArr2 = new float[i7 * 2];
        int i8 = 0;
        int i9 = 0;
        for (int i10 = 0; i10 < i5; i10 = i4) {
            float f8 = radians / 2.0f;
            float f9 = (i10 * f6) - f8;
            i4 = i10 + 1;
            float f10 = (i4 * f6) - f8;
            int i11 = 0;
            while (i11 < i6) {
                float f11 = radians;
                float f12 = radians2;
                int i12 = 0;
                while (i12 < 2) {
                    if (i12 == 0) {
                        f5 = f9;
                        f4 = f5;
                    } else {
                        f4 = f9;
                        f5 = f10;
                    }
                    float f13 = i11 * f7;
                    int i13 = i4;
                    float f14 = f6;
                    double d = f;
                    double d2 = (f13 + 3.1415927f) - (f12 / 2.0f);
                    double d3 = f5;
                    fArr[i8] = -((float) (Math.cos(d3) * Math.sin(d2) * d));
                    fArr[i8 + 1] = (float) (Math.sin(d3) * d);
                    int i14 = i8 + 3;
                    fArr[i8 + 2] = (float) (Math.cos(d3) * Math.cos(d2) * d);
                    fArr2[i9] = f13 / f12;
                    int i15 = i9 + 2;
                    fArr2[i9 + 1] = ((i10 + i12) * f14) / f11;
                    if ((i11 == 0 && i12 == 0) || (i11 == i2 && i12 == 1)) {
                        System.arraycopy(fArr, i8, fArr, i14, 3);
                        i8 += 6;
                        System.arraycopy(fArr2, i9, fArr2, i15, 2);
                        i9 += 4;
                    } else {
                        i8 = i14;
                        i9 = i15;
                    }
                    i12++;
                    f9 = f4;
                    f6 = f14;
                    i4 = i13;
                }
                i11++;
                radians2 = f12;
                radians = f11;
                f9 = f9;
            }
            i5 = i;
        }
        Mesh mesh = new Mesh(new SubMesh(0, fArr, fArr2, 1));
        return new Projection(mesh, mesh, i3);
    }
}
