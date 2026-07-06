package com.google.android.gms.internal.measurement;

import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzll {
    public static String zza(zzlj zzljVar, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(str);
        zzd(zzljVar, sb, 0);
        return sb.toString();
    }

    public static final void zzb(StringBuilder sb, int i, String str, Object obj) {
        if (obj instanceof List) {
            Iterator it = ((List) obj).iterator();
            while (it.hasNext()) {
                zzb(sb, i, str, it.next());
            }
            return;
        }
        if (obj instanceof Map) {
            Iterator it2 = ((Map) obj).entrySet().iterator();
            while (it2.hasNext()) {
                zzb(sb, i, str, (Map.Entry) it2.next());
            }
            return;
        }
        sb.append('\n');
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            sb.append(' ');
        }
        sb.append(str);
        if (obj instanceof String) {
            sb.append(": \"");
            sb.append(zzmj.zza(zzjb.zzm((String) obj)));
            sb.append('\"');
            return;
        }
        if (obj instanceof zzjb) {
            sb.append(": \"");
            sb.append(zzmj.zza((zzjb) obj));
            sb.append('\"');
            return;
        }
        if (obj instanceof zzkc) {
            sb.append(" {");
            zzd((zzkc) obj, sb, i + 2);
            sb.append(StringUtils.LF);
            while (i2 < i) {
                sb.append(' ');
                i2++;
            }
            sb.append("}");
            return;
        }
        if (!(obj instanceof Map.Entry)) {
            sb.append(": ");
            sb.append(obj);
            return;
        }
        sb.append(" {");
        Map.Entry entry = (Map.Entry) obj;
        int i4 = i + 2;
        zzb(sb, i4, "key", entry.getKey());
        zzb(sb, i4, "value", entry.getValue());
        sb.append(StringUtils.LF);
        while (i2 < i) {
            sb.append(' ');
            i2++;
        }
        sb.append("}");
    }

    public static final String zzc(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (Character.isUpperCase(cCharAt)) {
                sb.append(Attributes.PREDEFINED_ATTRIBUTE_PREFIX);
            }
            sb.append(Character.toLowerCase(cCharAt));
        }
        return sb.toString();
    }

    public static void zzd(zzlj zzljVar, StringBuilder sb, int i) {
        boolean zEquals;
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        TreeSet<String> treeSet = new TreeSet();
        for (Method method : zzljVar.getClass().getDeclaredMethods()) {
            map2.put(method.getName(), method);
            if (method.getParameterTypes().length == 0) {
                map.put(method.getName(), method);
                if (method.getName().startsWith("get")) {
                    treeSet.add(method.getName());
                }
            }
        }
        for (String str : treeSet) {
            String strSubstring = str.startsWith("get") ? str.substring(3) : str;
            if (strSubstring.endsWith("List") && !strSubstring.endsWith("OrBuilderList") && !strSubstring.equals("List")) {
                String strConcat = String.valueOf(strSubstring.substring(0, 1).toLowerCase()).concat(String.valueOf(strSubstring.substring(1, strSubstring.length() - 4)));
                Method method2 = (Method) map.get(str);
                if (method2 != null && method2.getReturnType().equals(List.class)) {
                    zzb(sb, i, zzc(strConcat), zzkc.zzbK(method2, zzljVar, new Object[0]));
                }
            }
            if (strSubstring.endsWith("Map") && !strSubstring.equals("Map")) {
                String strConcat2 = String.valueOf(strSubstring.substring(0, 1).toLowerCase()).concat(String.valueOf(strSubstring.substring(1, strSubstring.length() - 3)));
                Method method3 = (Method) map.get(str);
                if (method3 != null && method3.getReturnType().equals(Map.class) && !method3.isAnnotationPresent(Deprecated.class) && Modifier.isPublic(method3.getModifiers())) {
                    zzb(sb, i, zzc(strConcat2), zzkc.zzbK(method3, zzljVar, new Object[0]));
                }
            }
            if (((Method) map2.get("set".concat(strSubstring))) != null && (!strSubstring.endsWith("Bytes") || !map.containsKey("get".concat(String.valueOf(strSubstring.substring(0, strSubstring.length() - 5)))))) {
                String strConcat3 = String.valueOf(strSubstring.substring(0, 1).toLowerCase()).concat(String.valueOf(strSubstring.substring(1)));
                Method method4 = (Method) map.get("get".concat(strSubstring));
                Method method5 = (Method) map.get("has".concat(strSubstring));
                if (method4 != null) {
                    Object objZzbK = zzkc.zzbK(method4, zzljVar, new Object[0]);
                    if (method5 == null) {
                        if (objZzbK instanceof Boolean) {
                            if (((Boolean) objZzbK).booleanValue()) {
                                zzb(sb, i, zzc(strConcat3), objZzbK);
                            }
                        } else if (objZzbK instanceof Integer) {
                            if (((Integer) objZzbK).intValue() != 0) {
                                zzb(sb, i, zzc(strConcat3), objZzbK);
                            }
                        } else if (objZzbK instanceof Float) {
                            if (Float.floatToRawIntBits(((Float) objZzbK).floatValue()) != 0) {
                                zzb(sb, i, zzc(strConcat3), objZzbK);
                            }
                        } else if (!(objZzbK instanceof Double)) {
                            if (objZzbK instanceof String) {
                                zEquals = objZzbK.equals("");
                            } else if (objZzbK instanceof zzjb) {
                                zEquals = objZzbK.equals(zzjb.zzb);
                            } else if (objZzbK instanceof zzlj) {
                                if (objZzbK != ((zzlj) objZzbK).zzbR()) {
                                    zzb(sb, i, zzc(strConcat3), objZzbK);
                                }
                            } else if (!(objZzbK instanceof Enum) || ((Enum) objZzbK).ordinal() != 0) {
                                zzb(sb, i, zzc(strConcat3), objZzbK);
                            }
                            if (!zEquals) {
                                zzb(sb, i, zzc(strConcat3), objZzbK);
                            }
                        } else if (Double.doubleToRawLongBits(((Double) objZzbK).doubleValue()) != 0) {
                            zzb(sb, i, zzc(strConcat3), objZzbK);
                        }
                    } else if (((Boolean) zzkc.zzbK(method5, zzljVar, new Object[0])).booleanValue()) {
                        zzb(sb, i, zzc(strConcat3), objZzbK);
                    }
                }
            }
        }
        if (zzljVar instanceof zzjz) {
            throw null;
        }
        zzmm zzmmVar = ((zzkc) zzljVar).zzc;
        if (zzmmVar != null) {
            zzmmVar.zzg(sb, i);
        }
    }
}
