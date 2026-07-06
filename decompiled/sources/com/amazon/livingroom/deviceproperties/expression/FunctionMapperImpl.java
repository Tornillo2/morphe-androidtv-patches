package com.amazon.livingroom.deviceproperties.expression;

import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline1;
import com.amazon.livingroom.reflection.ReflectionCall;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.el.FunctionMapper;
import org.apache.commons.text.StringEscapeUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class FunctionMapperImpl extends FunctionMapper {
    public final Map<String, Method> functions = new HashMap();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class CollectionFunctions {
        @ReflectionCall
        @SafeVarargs
        public static <T> Set<T> setOf(T... tArr) {
            HashSet hashSet = new HashSet();
            Collections.addAll(hashSet, tArr);
            return hashSet;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class TextFunctions {
        @ReflectionCall
        public static String toLowerCase(String str) {
            return str.toLowerCase(Locale.US);
        }

        @ReflectionCall
        public static String toUpperCase(String str) {
            return str.toUpperCase(Locale.US);
        }

        @ReflectionCall
        public static String unescape(String str) {
            return StringEscapeUtils.unescapeJava(str);
        }
    }

    public FunctionMapperImpl() {
        addAllMethods("text", TextFunctions.class);
        addAllMethods("collections", CollectionFunctions.class);
    }

    public final void addAllMethods(String str, Class<?> cls) {
        for (Method method : cls.getMethods()) {
            this.functions.put(buildQualifiedName(str, method.getName()), method);
        }
    }

    public final String buildQualifiedName(String str, String str2) {
        return AbstractResolvableFuture$$ExternalSyntheticOutline1.m(str, ":", str2);
    }

    @Override // javax.el.FunctionMapper
    public Method resolveFunction(String str, String str2) {
        return this.functions.get(buildQualifiedName(str, str2));
    }
}
