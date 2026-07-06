package com.google.common.net;

import android.support.v4.media.MediaMetadataCompat$Builder$$ExternalSyntheticOutline0;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Absent;
import com.google.common.base.Ascii;
import com.google.common.base.CharMatcher;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Present;
import com.google.common.collect.AbstractMultimap;
import com.google.common.collect.EmptyImmutableListMultimap;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.UnmodifiableIterator;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import com.google.errorprone.annotations.concurrent.LazyInit;
import j$.util.Objects;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.text.lookup.StringLookupFactory;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Immutable
@GwtCompatible
public final class MediaType {
    public static final MediaType AAC_AUDIO;
    public static final MediaType ANY_APPLICATION_TYPE;
    public static final MediaType ANY_AUDIO_TYPE;
    public static final MediaType ANY_FONT_TYPE;
    public static final MediaType ANY_IMAGE_TYPE;
    public static final MediaType ANY_TEXT_TYPE;
    public static final MediaType ANY_TYPE;
    public static final MediaType ANY_VIDEO_TYPE;
    public static final MediaType APPLE_MOBILE_CONFIG;
    public static final MediaType APPLE_PASSBOOK;
    public static final MediaType APPLICATION_BINARY;
    public static final String APPLICATION_TYPE = "application";
    public static final MediaType APPLICATION_XML_UTF_8;
    public static final MediaType ATOM_UTF_8;
    public static final String AUDIO_TYPE = "audio";
    public static final MediaType BASIC_AUDIO;
    public static final MediaType BMP;
    public static final MediaType BZIP2;
    public static final MediaType CACHE_MANIFEST_UTF_8;
    public static final MediaType CBOR;
    public static final MediaType CRW;
    public static final MediaType CSS_UTF_8;
    public static final MediaType CSV_UTF_8;
    public static final MediaType DART_UTF_8;
    public static final MediaType EOT;
    public static final MediaType EPUB;
    public static final MediaType FLV_VIDEO;
    public static final MediaType FONT_COLLECTION;
    public static final MediaType FONT_OTF;
    public static final MediaType FONT_SFNT;
    public static final MediaType FONT_TTF;
    public static final String FONT_TYPE = "font";
    public static final MediaType FONT_WOFF;
    public static final MediaType FONT_WOFF2;
    public static final MediaType FORM_DATA;
    public static final MediaType GEO_JSON;
    public static final MediaType GIF;
    public static final MediaType GZIP;
    public static final MediaType HAL_JSON;
    public static final MediaType HEIF;
    public static final MediaType HTML_UTF_8;
    public static final MediaType ICO;
    public static final String IMAGE_TYPE = "image";
    public static final MediaType I_CALENDAR_UTF_8;
    public static final MediaType JAVASCRIPT_UTF_8;
    public static final MediaType JOSE;
    public static final MediaType JOSE_JSON;
    public static final MediaType JP2K;
    public static final MediaType JPEG;
    public static final MediaType JSON_UTF_8;
    public static final MediaType JWT;
    public static final MediaType KEY_ARCHIVE;
    public static final MediaType KML;
    public static final MediaType KMZ;
    public static final MediaType L16_AUDIO;
    public static final MediaType L24_AUDIO;
    public static final CharMatcher LINEAR_WHITE_SPACE;
    public static final MediaType MANIFEST_JSON_UTF_8;
    public static final MediaType MBOX;
    public static final MediaType MD_UTF_8;
    public static final MediaType MEDIA_PRESENTATION_DESCRIPTION;
    public static final MediaType MICROSOFT_EXCEL;
    public static final MediaType MICROSOFT_OUTLOOK;
    public static final MediaType MICROSOFT_POWERPOINT;
    public static final MediaType MICROSOFT_WORD;
    public static final MediaType MP4_AUDIO;
    public static final MediaType MP4_VIDEO;
    public static final MediaType MPEG_AUDIO;
    public static final MediaType MPEG_VIDEO;
    public static final MediaType NACL_APPLICATION;
    public static final MediaType NACL_PORTABLE_APPLICATION;
    public static final MediaType OCTET_STREAM;
    public static final MediaType OGG_AUDIO;
    public static final MediaType OGG_CONTAINER;
    public static final MediaType OGG_VIDEO;
    public static final MediaType OOXML_DOCUMENT;
    public static final MediaType OOXML_PRESENTATION;
    public static final MediaType OOXML_SHEET;
    public static final MediaType OPENDOCUMENT_GRAPHICS;
    public static final MediaType OPENDOCUMENT_PRESENTATION;
    public static final MediaType OPENDOCUMENT_SPREADSHEET;
    public static final MediaType OPENDOCUMENT_TEXT;
    public static final MediaType OPENSEARCH_DESCRIPTION_UTF_8;
    public static final Joiner.MapJoiner PARAMETER_JOINER;
    public static final MediaType PDF;
    public static final MediaType PLAIN_TEXT_UTF_8;
    public static final MediaType PNG;
    public static final MediaType POSTSCRIPT;
    public static final MediaType PROTOBUF;
    public static final MediaType PSD;
    public static final MediaType QUICKTIME;
    public static final CharMatcher QUOTED_TEXT_MATCHER;
    public static final MediaType RDF_XML_UTF_8;
    public static final MediaType RTF_UTF_8;
    public static final MediaType SFNT;
    public static final MediaType SHOCKWAVE_FLASH;
    public static final MediaType SKETCHUP;
    public static final MediaType SOAP_XML_UTF_8;
    public static final MediaType SVG_UTF_8;
    public static final MediaType TAR;
    public static final MediaType TEXT_JAVASCRIPT_UTF_8;
    public static final String TEXT_TYPE = "text";
    public static final MediaType THREE_GPP2_VIDEO;
    public static final MediaType THREE_GPP_VIDEO;
    public static final MediaType TIFF;
    public static final CharMatcher TOKEN_MATCHER;
    public static final MediaType TSV_UTF_8;
    public static final MediaType VCARD_UTF_8;
    public static final String VIDEO_TYPE = "video";
    public static final MediaType VND_REAL_AUDIO;
    public static final MediaType VND_WAVE_AUDIO;
    public static final MediaType VORBIS_AUDIO;
    public static final MediaType VTT_UTF_8;
    public static final MediaType WASM_APPLICATION;
    public static final MediaType WAX_AUDIO;
    public static final MediaType WEBM_AUDIO;
    public static final MediaType WEBM_VIDEO;
    public static final MediaType WEBP;
    public static final String WILDCARD = "*";
    public static final MediaType WMA_AUDIO;
    public static final MediaType WML_UTF_8;
    public static final MediaType WMV;
    public static final MediaType WOFF;
    public static final MediaType WOFF2;
    public static final MediaType XHTML_UTF_8;
    public static final MediaType XML_UTF_8;
    public static final MediaType XRD_UTF_8;
    public static final MediaType ZIP;
    public static final Map<MediaType, MediaType> knownTypes;

    @LazyInit
    public int hashCode;
    public final ImmutableListMultimap<String, String> parameters;

    @LazyInit
    public Optional<Charset> parsedCharset;
    public final String subtype;

    @LazyInit
    public String toString;
    public final String type;
    public static final String CHARSET_ATTRIBUTE = "charset";
    public static final ImmutableListMultimap<String, String> UTF_8_CONSTANT_PARAMETERS = ImmutableListMultimap.of(CHARSET_ATTRIBUTE, Ascii.toLowerCase(StandardCharsets.UTF_8.name()));

    /* JADX INFO: renamed from: $r8$lambda$1FA-fH1BZbwbgXu_qO3UWiV5ltY, reason: not valid java name */
    public static /* synthetic */ String m555$r8$lambda$1FAfH1BZbwbgXu_qO3UWiV5ltY(String str) {
        return (!TOKEN_MATCHER.matchesAllOf(str) || str.isEmpty()) ? escapeAndQuote(str) : str;
    }

    static {
        CharMatcher charMatcher = CharMatcher.Ascii.INSTANCE;
        TOKEN_MATCHER = charMatcher.and(CharMatcher.JavaIsoControl.INSTANCE.negate()).and(new CharMatcher.IsNot(' ')).and(CharMatcher.anyOf("()<>@,;:\\\"/[]?=").negate());
        QUOTED_TEXT_MATCHER = charMatcher.and(CharMatcher.anyOf("\"\\\r").negate());
        LINEAR_WHITE_SPACE = CharMatcher.anyOf(" \t\r\n");
        knownTypes = new HashMap();
        ANY_TYPE = createConstant(WILDCARD, WILDCARD);
        ANY_TEXT_TYPE = createConstant("text", WILDCARD);
        ANY_IMAGE_TYPE = createConstant("image", WILDCARD);
        ANY_AUDIO_TYPE = createConstant("audio", WILDCARD);
        ANY_VIDEO_TYPE = createConstant("video", WILDCARD);
        ANY_APPLICATION_TYPE = createConstant("application", WILDCARD);
        ANY_FONT_TYPE = createConstant(FONT_TYPE, WILDCARD);
        CACHE_MANIFEST_UTF_8 = createConstantUtf8("text", "cache-manifest");
        CSS_UTF_8 = createConstantUtf8("text", "css");
        CSV_UTF_8 = createConstantUtf8("text", "csv");
        HTML_UTF_8 = createConstantUtf8("text", "html");
        I_CALENDAR_UTF_8 = createConstantUtf8("text", "calendar");
        MD_UTF_8 = createConstantUtf8("text", "markdown");
        PLAIN_TEXT_UTF_8 = createConstantUtf8("text", "plain");
        TEXT_JAVASCRIPT_UTF_8 = createConstantUtf8("text", "javascript");
        TSV_UTF_8 = createConstantUtf8("text", "tab-separated-values");
        VCARD_UTF_8 = createConstantUtf8("text", "vcard");
        WML_UTF_8 = createConstantUtf8("text", "vnd.wap.wml");
        XML_UTF_8 = createConstantUtf8("text", StringLookupFactory.KEY_XML);
        VTT_UTF_8 = createConstantUtf8("text", "vtt");
        BMP = createConstant("image", "bmp");
        CRW = createConstant("image", "x-canon-crw");
        GIF = createConstant("image", "gif");
        ICO = createConstant("image", "vnd.microsoft.icon");
        JPEG = createConstant("image", "jpeg");
        PNG = createConstant("image", "png");
        PSD = createConstant("image", "vnd.adobe.photoshop");
        SVG_UTF_8 = createConstantUtf8("image", "svg+xml");
        TIFF = createConstant("image", "tiff");
        WEBP = createConstant("image", "webp");
        HEIF = createConstant("image", "heif");
        JP2K = createConstant("image", "jp2");
        MP4_AUDIO = createConstant("audio", "mp4");
        MPEG_AUDIO = createConstant("audio", "mpeg");
        OGG_AUDIO = createConstant("audio", "ogg");
        WEBM_AUDIO = createConstant("audio", "webm");
        L16_AUDIO = createConstant("audio", "l16");
        L24_AUDIO = createConstant("audio", "l24");
        BASIC_AUDIO = createConstant("audio", "basic");
        AAC_AUDIO = createConstant("audio", "aac");
        VORBIS_AUDIO = createConstant("audio", "vorbis");
        WMA_AUDIO = createConstant("audio", "x-ms-wma");
        WAX_AUDIO = createConstant("audio", "x-ms-wax");
        VND_REAL_AUDIO = createConstant("audio", "vnd.rn-realaudio");
        VND_WAVE_AUDIO = createConstant("audio", "vnd.wave");
        MP4_VIDEO = createConstant("video", "mp4");
        MPEG_VIDEO = createConstant("video", "mpeg");
        OGG_VIDEO = createConstant("video", "ogg");
        QUICKTIME = createConstant("video", "quicktime");
        WEBM_VIDEO = createConstant("video", "webm");
        WMV = createConstant("video", "x-ms-wmv");
        FLV_VIDEO = createConstant("video", "x-flv");
        THREE_GPP_VIDEO = createConstant("video", "3gpp");
        THREE_GPP2_VIDEO = createConstant("video", "3gpp2");
        APPLICATION_XML_UTF_8 = createConstantUtf8("application", StringLookupFactory.KEY_XML);
        ATOM_UTF_8 = createConstantUtf8("application", "atom+xml");
        BZIP2 = createConstant("application", "x-bzip2");
        DART_UTF_8 = createConstantUtf8("application", "dart");
        APPLE_PASSBOOK = createConstant("application", "vnd.apple.pkpass");
        EOT = createConstant("application", "vnd.ms-fontobject");
        EPUB = createConstant("application", "epub+zip");
        FORM_DATA = createConstant("application", "x-www-form-urlencoded");
        KEY_ARCHIVE = createConstant("application", "pkcs12");
        APPLICATION_BINARY = createConstant("application", "binary");
        CBOR = createConstant("application", "cbor");
        GEO_JSON = createConstant("application", "geo+json");
        GZIP = createConstant("application", "x-gzip");
        HAL_JSON = createConstant("application", "hal+json");
        JAVASCRIPT_UTF_8 = createConstantUtf8("application", "javascript");
        JOSE = createConstant("application", "jose");
        JOSE_JSON = createConstant("application", "jose+json");
        JSON_UTF_8 = createConstantUtf8("application", "json");
        JWT = createConstant("application", "jwt");
        MANIFEST_JSON_UTF_8 = createConstantUtf8("application", "manifest+json");
        KML = createConstant("application", "vnd.google-earth.kml+xml");
        KMZ = createConstant("application", "vnd.google-earth.kmz");
        MBOX = createConstant("application", "mbox");
        APPLE_MOBILE_CONFIG = createConstant("application", "x-apple-aspen-config");
        MICROSOFT_EXCEL = createConstant("application", "vnd.ms-excel");
        MICROSOFT_OUTLOOK = createConstant("application", "vnd.ms-outlook");
        MICROSOFT_POWERPOINT = createConstant("application", "vnd.ms-powerpoint");
        MICROSOFT_WORD = createConstant("application", "msword");
        MEDIA_PRESENTATION_DESCRIPTION = createConstant("application", "dash+xml");
        WASM_APPLICATION = createConstant("application", "wasm");
        NACL_APPLICATION = createConstant("application", "x-nacl");
        NACL_PORTABLE_APPLICATION = createConstant("application", "x-pnacl");
        OCTET_STREAM = createConstant("application", "octet-stream");
        OGG_CONTAINER = createConstant("application", "ogg");
        OOXML_DOCUMENT = createConstant("application", "vnd.openxmlformats-officedocument.wordprocessingml.document");
        OOXML_PRESENTATION = createConstant("application", "vnd.openxmlformats-officedocument.presentationml.presentation");
        OOXML_SHEET = createConstant("application", "vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        OPENDOCUMENT_GRAPHICS = createConstant("application", "vnd.oasis.opendocument.graphics");
        OPENDOCUMENT_PRESENTATION = createConstant("application", "vnd.oasis.opendocument.presentation");
        OPENDOCUMENT_SPREADSHEET = createConstant("application", "vnd.oasis.opendocument.spreadsheet");
        OPENDOCUMENT_TEXT = createConstant("application", "vnd.oasis.opendocument.text");
        OPENSEARCH_DESCRIPTION_UTF_8 = createConstantUtf8("application", "opensearchdescription+xml");
        PDF = createConstant("application", "pdf");
        POSTSCRIPT = createConstant("application", "postscript");
        PROTOBUF = createConstant("application", "protobuf");
        RDF_XML_UTF_8 = createConstantUtf8("application", "rdf+xml");
        RTF_UTF_8 = createConstantUtf8("application", "rtf");
        SFNT = createConstant("application", "font-sfnt");
        SHOCKWAVE_FLASH = createConstant("application", "x-shockwave-flash");
        SKETCHUP = createConstant("application", "vnd.sketchup.skp");
        SOAP_XML_UTF_8 = createConstantUtf8("application", "soap+xml");
        TAR = createConstant("application", "x-tar");
        WOFF = createConstant("application", "font-woff");
        WOFF2 = createConstant("application", "font-woff2");
        XHTML_UTF_8 = createConstantUtf8("application", "xhtml+xml");
        XRD_UTF_8 = createConstantUtf8("application", "xrd+xml");
        ZIP = createConstant("application", ArchiveStreamFactory.ZIP);
        FONT_COLLECTION = createConstant(FONT_TYPE, "collection");
        FONT_OTF = createConstant(FONT_TYPE, "otf");
        FONT_SFNT = createConstant(FONT_TYPE, "sfnt");
        FONT_TTF = createConstant(FONT_TYPE, "ttf");
        FONT_WOFF = createConstant(FONT_TYPE, "woff");
        FONT_WOFF2 = createConstant(FONT_TYPE, "woff2");
        PARAMETER_JOINER = new Joiner.MapJoiner(new Joiner("; "), "=");
    }

    public MediaType(String type, String subtype, ImmutableListMultimap<String, String> parameters) {
        this.type = type;
        this.subtype = subtype;
        this.parameters = parameters;
    }

    @CanIgnoreReturnValue
    public static MediaType addKnownType(MediaType mediaType) {
        knownTypes.put(mediaType, mediaType);
        return mediaType;
    }

    public static void consumeSeparator(Tokenizer tokenizer, char c) {
        CharMatcher charMatcher = LINEAR_WHITE_SPACE;
        tokenizer.consumeTokenIfPresent(charMatcher);
        tokenizer.consumeCharacter(c);
        tokenizer.consumeTokenIfPresent(charMatcher);
    }

    public static MediaType create(String type, String subtype) {
        MediaType mediaTypeCreate = create(type, subtype, EmptyImmutableListMultimap.INSTANCE);
        mediaTypeCreate.parsedCharset = Absent.INSTANCE;
        return mediaTypeCreate;
    }

    public static MediaType createApplicationType(String subtype) {
        return create("application", subtype);
    }

    public static MediaType createAudioType(String subtype) {
        return create("audio", subtype);
    }

    public static MediaType createConstant(String type, String subtype) {
        MediaType mediaType = new MediaType(type, subtype, EmptyImmutableListMultimap.INSTANCE);
        knownTypes.put(mediaType, mediaType);
        mediaType.parsedCharset = Absent.INSTANCE;
        return mediaType;
    }

    public static MediaType createConstantUtf8(String type, String subtype) {
        MediaType mediaType = new MediaType(type, subtype, UTF_8_CONSTANT_PARAMETERS);
        knownTypes.put(mediaType, mediaType);
        mediaType.parsedCharset = Optional.of(StandardCharsets.UTF_8);
        return mediaType;
    }

    public static MediaType createFontType(String subtype) {
        return create(FONT_TYPE, subtype);
    }

    public static MediaType createImageType(String subtype) {
        return create("image", subtype);
    }

    public static MediaType createTextType(String subtype) {
        return create("text", subtype);
    }

    public static MediaType createVideoType(String subtype) {
        return create("video", subtype);
    }

    public static String escapeAndQuote(String value) {
        StringBuilder sb = new StringBuilder(value.length() + 16);
        sb.append('\"');
        for (int i = 0; i < value.length(); i++) {
            char cCharAt = value.charAt(i);
            if (cCharAt == '\r' || cCharAt == '\\' || cCharAt == '\"') {
                sb.append('\\');
            }
            sb.append(cCharAt);
        }
        sb.append('\"');
        return sb.toString();
    }

    public static String normalizeParameterValue(String attribute, String value) {
        value.getClass();
        Preconditions.checkArgument(CharMatcher.Ascii.INSTANCE.matchesAllOf(value), "parameter values must be ASCII: %s", value);
        return attribute.equals(CHARSET_ATTRIBUTE) ? Ascii.toLowerCase(value) : value;
    }

    public static String normalizeToken(String token) {
        Preconditions.checkArgument(TOKEN_MATCHER.matchesAllOf(token));
        Preconditions.checkArgument(!token.isEmpty());
        return Ascii.toLowerCase(token);
    }

    @CanIgnoreReturnValue
    public static MediaType parse(String input) {
        String strConsumeToken;
        input.getClass();
        Tokenizer tokenizer = new Tokenizer(input);
        try {
            CharMatcher charMatcher = TOKEN_MATCHER;
            String strConsumeToken2 = tokenizer.consumeToken(charMatcher);
            consumeSeparator(tokenizer, '/');
            String strConsumeToken3 = tokenizer.consumeToken(charMatcher);
            ImmutableListMultimap.Builder builder = new ImmutableListMultimap.Builder();
            while (tokenizer.hasMore()) {
                consumeSeparator(tokenizer, ';');
                CharMatcher charMatcher2 = TOKEN_MATCHER;
                String strConsumeToken4 = tokenizer.consumeToken(charMatcher2);
                consumeSeparator(tokenizer, '=');
                if (tokenizer.previewChar() == '\"') {
                    tokenizer.consumeCharacter('\"');
                    StringBuilder sb = new StringBuilder();
                    while (tokenizer.previewChar() != '\"') {
                        if (tokenizer.previewChar() == '\\') {
                            tokenizer.consumeCharacter('\\');
                            sb.append(tokenizer.consumeCharacter(CharMatcher.Ascii.INSTANCE));
                        } else {
                            sb.append(tokenizer.consumeToken(QUOTED_TEXT_MATCHER));
                        }
                    }
                    strConsumeToken = sb.toString();
                    tokenizer.consumeCharacter('\"');
                } else {
                    strConsumeToken = tokenizer.consumeToken(charMatcher2);
                }
                builder.put(strConsumeToken4, strConsumeToken);
            }
            return create(strConsumeToken2, strConsumeToken3, builder.build());
        } catch (IllegalStateException e) {
            throw new IllegalArgumentException(MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("Could not parse '", input, "'"), e);
        }
    }

    public Optional<Charset> charset() {
        Optional<Charset> optionalOf = this.parsedCharset;
        if (optionalOf == null) {
            optionalOf = Absent.INSTANCE;
            UnmodifiableIterator<String> it = this.parameters.get(CHARSET_ATTRIBUTE).iterator();
            String str = null;
            while (it.hasNext()) {
                String next = it.next();
                if (str == null) {
                    optionalOf = Optional.of(Charset.forName(next));
                    str = next;
                } else if (!str.equals(next)) {
                    throw new IllegalStateException("Multiple charset values defined: " + str + ", " + next);
                }
            }
            this.parsedCharset = optionalOf;
        }
        return optionalOf;
    }

    public final String computeToString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.type);
        sb.append('/');
        sb.append(this.subtype);
        if (!this.parameters.isEmpty()) {
            sb.append("; ");
            PARAMETER_JOINER.appendTo(sb, (Iterable<? extends Map.Entry<?, ?>>) ((AbstractMultimap) Multimaps.transformValues((ListMultimap) this.parameters, (Function) new MediaType$$ExternalSyntheticLambda0())).entries());
        }
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MediaType) {
            MediaType mediaType = (MediaType) obj;
            if (this.type.equals(mediaType.type) && this.subtype.equals(mediaType.subtype)) {
                if (((AbstractMap) parametersAsMap()).equals(mediaType.parametersAsMap())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasWildcard() {
        return this.type.equals(WILDCARD) || this.subtype.equals(WILDCARD);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHash = Objects.hash(this.type, this.subtype, parametersAsMap());
        this.hashCode = iHash;
        return iHash;
    }

    public boolean is(MediaType mediaTypeRange) {
        if (mediaTypeRange.type.equals(WILDCARD) || mediaTypeRange.type.equals(this.type)) {
            return (mediaTypeRange.subtype.equals(WILDCARD) || mediaTypeRange.subtype.equals(this.subtype)) && this.parameters.entries().containsAll(mediaTypeRange.parameters.entries());
        }
        return false;
    }

    public ImmutableListMultimap<String, String> parameters() {
        return this.parameters;
    }

    public final Map<String, ImmutableMultiset<String>> parametersAsMap() {
        return Maps.transformValues(this.parameters.asMap(), new MediaType$$ExternalSyntheticLambda1());
    }

    public String subtype() {
        return this.subtype;
    }

    public String toString() {
        String str = this.toString;
        if (str != null) {
            return str;
        }
        String strComputeToString = computeToString();
        this.toString = strComputeToString;
        return strComputeToString;
    }

    public String type() {
        return this.type;
    }

    public MediaType withCharset(Charset charset) {
        charset.getClass();
        MediaType mediaTypeWithParameter = withParameter(CHARSET_ATTRIBUTE, charset.name());
        mediaTypeWithParameter.parsedCharset = new Present(charset);
        return mediaTypeWithParameter;
    }

    public MediaType withParameter(String attribute, String value) {
        return withParameters(attribute, ImmutableSet.of(value));
    }

    public MediaType withParameters(Multimap<String, String> parameters) {
        return create(this.type, this.subtype, parameters);
    }

    public MediaType withoutParameters() {
        return this.parameters.isEmpty() ? this : create(this.type, this.subtype);
    }

    public MediaType withParameters(String attribute, Iterable<String> values) {
        attribute.getClass();
        values.getClass();
        String strNormalizeToken = normalizeToken(attribute);
        ImmutableListMultimap.Builder builder = new ImmutableListMultimap.Builder();
        UnmodifiableIterator<Map.Entry<String, String>> it = this.parameters.entries().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> next = it.next();
            String key = next.getKey();
            if (!strNormalizeToken.equals(key)) {
                builder.put(key, next.getValue());
            }
        }
        Iterator<String> it2 = values.iterator();
        while (it2.hasNext()) {
            builder.put(strNormalizeToken, normalizeParameterValue(strNormalizeToken, it2.next()));
        }
        MediaType mediaType = new MediaType(this.type, this.subtype, builder.build());
        if (!strNormalizeToken.equals(CHARSET_ATTRIBUTE)) {
            mediaType.parsedCharset = this.parsedCharset;
        }
        return (MediaType) MoreObjects.firstNonNull(knownTypes.get(mediaType), mediaType);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Tokenizer {
        public final String input;
        public int position = 0;

        public Tokenizer(String input) {
            this.input = input;
        }

        public char consumeCharacter(CharMatcher matcher) {
            Preconditions.checkState(hasMore());
            char cPreviewChar = previewChar();
            Preconditions.checkState(matcher.matches(cPreviewChar));
            this.position++;
            return cPreviewChar;
        }

        public String consumeToken(CharMatcher matcher) {
            int i = this.position;
            String strConsumeTokenIfPresent = consumeTokenIfPresent(matcher);
            Preconditions.checkState(this.position != i);
            return strConsumeTokenIfPresent;
        }

        @CanIgnoreReturnValue
        public String consumeTokenIfPresent(CharMatcher matcher) {
            Preconditions.checkState(hasMore());
            int i = this.position;
            this.position = matcher.negate().indexIn(this.input, i);
            return hasMore() ? this.input.substring(i, this.position) : this.input.substring(i);
        }

        public boolean hasMore() {
            int i = this.position;
            return i >= 0 && i < this.input.length();
        }

        public char previewChar() {
            Preconditions.checkState(hasMore());
            return this.input.charAt(this.position);
        }

        @CanIgnoreReturnValue
        public char consumeCharacter(char c) {
            Preconditions.checkState(hasMore());
            Preconditions.checkState(previewChar() == c);
            this.position++;
            return c;
        }
    }

    public static MediaType create(String type, String subtype, Multimap<String, String> parameters) {
        type.getClass();
        subtype.getClass();
        parameters.getClass();
        String strNormalizeToken = normalizeToken(type);
        String strNormalizeToken2 = normalizeToken(subtype);
        Preconditions.checkArgument(!strNormalizeToken.equals(WILDCARD) || strNormalizeToken2.equals(WILDCARD), "A wildcard type cannot be used with a non-wildcard subtype");
        ImmutableListMultimap.Builder builder = new ImmutableListMultimap.Builder();
        for (Map.Entry<String, String> entry : parameters.entries()) {
            String strNormalizeToken3 = normalizeToken(entry.getKey());
            builder.put(strNormalizeToken3, normalizeParameterValue(strNormalizeToken3, entry.getValue()));
        }
        MediaType mediaType = new MediaType(strNormalizeToken, strNormalizeToken2, builder.build());
        return (MediaType) MoreObjects.firstNonNull(knownTypes.get(mediaType), mediaType);
    }
}
