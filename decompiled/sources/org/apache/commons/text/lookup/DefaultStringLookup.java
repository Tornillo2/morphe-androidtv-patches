package org.apache.commons.text.lookup;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public enum DefaultStringLookup {
    BASE64_DECODER(StringLookupFactory.KEY_BASE64_DECODER, Base64DecoderStringLookup.INSTANCE),
    BASE64_ENCODER(StringLookupFactory.KEY_BASE64_ENCODER, Base64EncoderStringLookup.INSTANCE),
    CONST(StringLookupFactory.KEY_CONST, ConstantStringLookup.INSTANCE),
    DATE(StringLookupFactory.KEY_DATE, DateStringLookup.INSTANCE),
    DNS(StringLookupFactory.KEY_DNS, DnsStringLookup.INSTANCE),
    ENVIRONMENT(StringLookupFactory.KEY_ENV, EnvironmentVariableStringLookup.INSTANCE),
    FILE(StringLookupFactory.KEY_FILE, FileStringLookup.INSTANCE),
    JAVA(StringLookupFactory.KEY_JAVA, JavaPlatformStringLookup.INSTANCE),
    LOCAL_HOST(StringLookupFactory.KEY_LOCALHOST, LocalHostStringLookup.INSTANCE),
    PROPERTIES(StringLookupFactory.KEY_PROPERTIES, PropertiesStringLookup.INSTANCE),
    RESOURCE_BUNDLE(StringLookupFactory.KEY_RESOURCE_BUNDLE, ResourceBundleStringLookup.INSTANCE),
    SCRIPT(StringLookupFactory.KEY_SCRIPT, ScriptStringLookup.INSTANCE),
    SYSTEM_PROPERTIES("sys", SystemPropertyStringLookup.INSTANCE),
    URL("url", UrlStringLookup.INSTANCE),
    URL_DECODER(StringLookupFactory.KEY_URL_DECODER, UrlDecoderStringLookup.INSTANCE),
    URL_ENCODER(StringLookupFactory.KEY_URL_ENCODER, UrlEncoderStringLookup.INSTANCE),
    XML(StringLookupFactory.KEY_XML, XmlStringLookup.INSTANCE);

    public final String key;
    public final StringLookup lookup;

    static {
        StringLookupFactory stringLookupFactory = StringLookupFactory.INSTANCE;
        stringLookupFactory.getClass();
        stringLookupFactory.getClass();
        stringLookupFactory.getClass();
        stringLookupFactory.getClass();
        stringLookupFactory.getClass();
        stringLookupFactory.getClass();
        stringLookupFactory.getClass();
        stringLookupFactory.getClass();
        stringLookupFactory.getClass();
        stringLookupFactory.getClass();
        stringLookupFactory.getClass();
        stringLookupFactory.getClass();
        stringLookupFactory.getClass();
        stringLookupFactory.getClass();
        stringLookupFactory.getClass();
        stringLookupFactory.getClass();
        stringLookupFactory.getClass();
    }

    DefaultStringLookup(String str, StringLookup stringLookup) {
        this.key = str;
        this.lookup = stringLookup;
    }

    public String getKey() {
        return this.key;
    }

    public StringLookup getStringLookup() {
        return this.lookup;
    }
}
