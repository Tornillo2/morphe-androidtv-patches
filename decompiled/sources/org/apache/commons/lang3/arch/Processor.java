package org.apache.commons.lang3.arch;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class Processor {
    public final Arch arch;
    public final Type type;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum Arch {
        BIT_32,
        BIT_64,
        UNKNOWN
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum Type {
        X86,
        IA_64,
        PPC,
        UNKNOWN
    }

    public Processor(Arch arch, Type type) {
        this.arch = arch;
        this.type = type;
    }

    public Arch getArch() {
        return this.arch;
    }

    public Type getType() {
        return this.type;
    }

    public boolean is32Bit() {
        return Arch.BIT_32.equals(this.arch);
    }

    public boolean is64Bit() {
        return Arch.BIT_64.equals(this.arch);
    }

    public boolean isIA64() {
        return Type.IA_64.equals(this.type);
    }

    public boolean isPPC() {
        return Type.PPC.equals(this.type);
    }

    public boolean isX86() {
        return Type.X86.equals(this.type);
    }
}
