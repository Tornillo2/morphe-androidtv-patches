package org.apache.commons.lang3;

import android.support.v4.media.MediaMetadataCompat$Builder$$ExternalSyntheticOutline0;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.arch.Processor;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class ArchUtils {
    public static final Map<String, Processor> ARCH_TO_PROCESSOR = new HashMap();

    static {
        init();
    }

    public static void addProcessor(String str, Processor processor) {
        Map<String, Processor> map = ARCH_TO_PROCESSOR;
        if (map.containsKey(str)) {
            throw new IllegalStateException(MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("Key ", str, " already exists in processor map"));
        }
        map.put(str, processor);
    }

    public static void addProcessors(Processor processor, String... strArr) {
        for (String str : strArr) {
            addProcessor(str, processor);
        }
    }

    public static Processor getProcessor() {
        return getProcessor(SystemUtils.OS_ARCH);
    }

    public static void init() {
        init_X86_32Bit();
        init_X86_64Bit();
        init_IA64_32Bit();
        init_IA64_64Bit();
        init_PPC_32Bit();
        init_PPC_64Bit();
    }

    public static void init_IA64_32Bit() {
        addProcessors(new Processor(Processor.Arch.BIT_32, Processor.Type.IA_64), "ia64_32", "ia64n");
    }

    public static void init_IA64_64Bit() {
        addProcessors(new Processor(Processor.Arch.BIT_64, Processor.Type.IA_64), "ia64", "ia64w");
    }

    public static void init_PPC_32Bit() {
        addProcessors(new Processor(Processor.Arch.BIT_32, Processor.Type.PPC), "ppc", "power", "powerpc", "power_pc", "power_rs");
    }

    public static void init_PPC_64Bit() {
        addProcessors(new Processor(Processor.Arch.BIT_64, Processor.Type.PPC), "ppc64", "power64", "powerpc64", "power_pc64", "power_rs64");
    }

    public static void init_X86_32Bit() {
        addProcessors(new Processor(Processor.Arch.BIT_32, Processor.Type.X86), "x86", "i386", "i486", "i586", "i686", "pentium");
    }

    public static void init_X86_64Bit() {
        addProcessors(new Processor(Processor.Arch.BIT_64, Processor.Type.X86), "x86_64", "amd64", "em64t", "universal");
    }

    public static Processor getProcessor(String str) {
        return ARCH_TO_PROCESSOR.get(str);
    }
}
