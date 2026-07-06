package org.apache.commons.text.lookup;

import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class StringLookupFactory {
    public static final StringLookupFactory INSTANCE = new StringLookupFactory();
    public static final String KEY_BASE64_DECODER = "base64Decoder";
    public static final String KEY_BASE64_ENCODER = "base64Encoder";
    public static final String KEY_CONST = "const";
    public static final String KEY_DATE = "date";
    public static final String KEY_DNS = "dns";
    public static final String KEY_ENV = "env";
    public static final String KEY_FILE = "file";
    public static final String KEY_JAVA = "java";
    public static final String KEY_LOCALHOST = "localhost";
    public static final String KEY_PROPERTIES = "properties";
    public static final String KEY_RESOURCE_BUNDLE = "resourceBundle";
    public static final String KEY_SCRIPT = "script";
    public static final String KEY_SYS = "sys";
    public static final String KEY_URL = "url";
    public static final String KEY_URL_DECODER = "urlDecoder";
    public static final String KEY_URL_ENCODER = "urlEncoder";
    public static final String KEY_XML = "xml";

    public static void clear() {
        ConstantStringLookup.clear();
    }

    public void addDefaultStringLookups(Map<String, StringLookup> map) {
        if (map != null) {
            map.put("base64", Base64DecoderStringLookup.INSTANCE);
            for (DefaultStringLookup defaultStringLookup : DefaultStringLookup.values()) {
                map.put(InterpolatorStringLookup.toKey(defaultStringLookup.key), defaultStringLookup.lookup);
            }
        }
    }

    public StringLookup base64DecoderStringLookup() {
        return Base64DecoderStringLookup.INSTANCE;
    }

    public StringLookup base64EncoderStringLookup() {
        return Base64EncoderStringLookup.INSTANCE;
    }

    @Deprecated
    public StringLookup base64StringLookup() {
        return Base64DecoderStringLookup.INSTANCE;
    }

    public StringLookup constantStringLookup() {
        return ConstantStringLookup.INSTANCE;
    }

    public StringLookup dateStringLookup() {
        return DateStringLookup.INSTANCE;
    }

    public StringLookup dnsStringLookup() {
        return DnsStringLookup.INSTANCE;
    }

    public StringLookup environmentVariableStringLookup() {
        return EnvironmentVariableStringLookup.INSTANCE;
    }

    public StringLookup fileStringLookup() {
        return FileStringLookup.INSTANCE;
    }

    public StringLookup interpolatorStringLookup() {
        return InterpolatorStringLookup.INSTANCE;
    }

    public StringLookup javaPlatformStringLookup() {
        return JavaPlatformStringLookup.INSTANCE;
    }

    public StringLookup localHostStringLookup() {
        return LocalHostStringLookup.INSTANCE;
    }

    public <V> StringLookup mapStringLookup(Map<String, V> map) {
        return new MapStringLookup(map);
    }

    public StringLookup nullStringLookup() {
        return NullStringLookup.INSTANCE;
    }

    public StringLookup propertiesStringLookup() {
        return PropertiesStringLookup.INSTANCE;
    }

    public StringLookup resourceBundleStringLookup() {
        return ResourceBundleStringLookup.INSTANCE;
    }

    public StringLookup scriptStringLookup() {
        return ScriptStringLookup.INSTANCE;
    }

    public StringLookup systemPropertyStringLookup() {
        return SystemPropertyStringLookup.INSTANCE;
    }

    public StringLookup urlDecoderStringLookup() {
        return UrlDecoderStringLookup.INSTANCE;
    }

    public StringLookup urlEncoderStringLookup() {
        return UrlEncoderStringLookup.INSTANCE;
    }

    public StringLookup urlStringLookup() {
        return UrlStringLookup.INSTANCE;
    }

    public StringLookup xmlStringLookup() {
        return XmlStringLookup.INSTANCE;
    }

    public StringLookup interpolatorStringLookup(Map<String, StringLookup> map, StringLookup stringLookup, boolean z) {
        return new InterpolatorStringLookup(map, stringLookup, z);
    }

    public StringLookup resourceBundleStringLookup(String str) {
        return new ResourceBundleStringLookup(str);
    }

    public <V> StringLookup interpolatorStringLookup(Map<String, V> map) {
        return new InterpolatorStringLookup(map);
    }

    public StringLookup interpolatorStringLookup(StringLookup stringLookup) {
        return new InterpolatorStringLookup(stringLookup);
    }
}
