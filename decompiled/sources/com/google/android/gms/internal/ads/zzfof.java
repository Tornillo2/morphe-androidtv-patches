package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzfof {
    public static String zza(String str) {
        int length = str.length();
        int i = 0;
        while (i < length) {
            if (zze(str.charAt(i))) {
                char[] charArray = str.toCharArray();
                while (i < length) {
                    char c = charArray[i];
                    if (zze(c)) {
                        charArray[i] = (char) (c ^ ' ');
                    }
                    i++;
                }
                return String.valueOf(charArray);
            }
            i++;
        }
        return str;
    }

    public static String zzb(String str) {
        int length = str.length();
        int i = 0;
        while (i < length) {
            if (zzd(str.charAt(i))) {
                char[] charArray = str.toCharArray();
                while (i < length) {
                    char c = charArray[i];
                    if (zzd(c)) {
                        charArray[i] = (char) (c ^ ' ');
                    }
                    i++;
                }
                return String.valueOf(charArray);
            }
            i++;
        }
        return str;
    }

    public static boolean zzc(CharSequence charSequence, CharSequence charSequence2) {
        int iZzf;
        int length = charSequence.length();
        if (charSequence == charSequence2) {
            return true;
        }
        if (length != charSequence2.length()) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            char cCharAt = charSequence.charAt(i);
            char cCharAt2 = charSequence2.charAt(i);
            if (cCharAt != cCharAt2 && ((iZzf = zzf(cCharAt)) >= 26 || iZzf != zzf(cCharAt2))) {
                return false;
            }
        }
        return true;
    }

    public static boolean zzd(char c) {
        return c >= 'a' && c <= 'z';
    }

    public static boolean zze(char c) {
        return c >= 'A' && c <= 'Z';
    }

    public static int zzf(char c) {
        return (char) ((c | ' ') - 97);
    }
}
