package com.google.common.net;

import com.amazon.avod.mpb.media.ExternalFourCCMapper;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Absent;
import com.google.common.base.Ascii;
import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.thirdparty.publicsuffix.PublicSuffixPatterns;
import com.google.thirdparty.publicsuffix.PublicSuffixType;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Immutable
@GwtCompatible(emulated = true)
public final class InternetDomainName {
    public static final CharMatcher DASH_MATCHER;
    public static final CharMatcher DIGIT_MATCHER;
    public static final CharMatcher LETTER_MATCHER;
    public static final int MAX_DOMAIN_PART_LENGTH = 63;
    public static final int MAX_LENGTH = 253;
    public static final int MAX_PARTS = 127;
    public static final int NO_SUFFIX_FOUND = -1;
    public static final CharMatcher PART_CHAR_MATCHER;
    public static final int SUFFIX_NOT_INITIALIZED = -2;
    public final String name;
    public final ImmutableList<String> parts;

    @LazyInit
    public int publicSuffixIndexCache = -2;

    @LazyInit
    public int registrySuffixIndexCache = -2;
    public static final CharMatcher DOTS_MATCHER = CharMatcher.anyOf(".。．｡");
    public static final Splitter DOT_SPLITTER = Splitter.on('.');
    public static final Joiner DOT_JOINER = Joiner.on('.');

    static {
        CharMatcher charMatcherAnyOf = CharMatcher.anyOf("-_");
        DASH_MATCHER = charMatcherAnyOf;
        CharMatcher.InRange inRange = new CharMatcher.InRange('0', '9');
        DIGIT_MATCHER = inRange;
        CharMatcher.Or or = new CharMatcher.Or(new CharMatcher.InRange('a', 'z'), new CharMatcher.InRange('A', 'Z'));
        LETTER_MATCHER = or;
        PART_CHAR_MATCHER = inRange.or(or).or(charMatcherAnyOf);
    }

    public InternetDomainName(String name) {
        String lowerCase = Ascii.toLowerCase(DOTS_MATCHER.replaceFrom((CharSequence) name, '.'));
        lowerCase = lowerCase.endsWith(ExternalFourCCMapper.CODEC_NAME_SPLITTER) ? lowerCase.substring(0, lowerCase.length() - 1) : lowerCase;
        Preconditions.checkArgument(lowerCase.length() <= 253, "Domain name too long: '%s':", lowerCase);
        this.name = lowerCase;
        Splitter splitter = DOT_SPLITTER;
        splitter.getClass();
        ImmutableList<String> immutableListCopyOf = ImmutableList.copyOf(new Splitter.AnonymousClass5(splitter, lowerCase));
        this.parts = immutableListCopyOf;
        Preconditions.checkArgument(immutableListCopyOf.size() <= 127, "Domain has too many parts: '%s'", lowerCase);
        Preconditions.checkArgument(validateSyntax(immutableListCopyOf), "Not a valid domain name: '%s'", lowerCase);
    }

    @CanIgnoreReturnValue
    public static InternetDomainName from(String domain) {
        domain.getClass();
        return new InternetDomainName(domain);
    }

    public static boolean isValid(String name) {
        try {
            from(name);
            return true;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    public static boolean matchesType(Optional<PublicSuffixType> desiredType, Optional<PublicSuffixType> actualType) {
        return desiredType.isPresent() ? desiredType.equals(actualType) : actualType.isPresent();
    }

    public static boolean validatePart(String part, boolean isFinalPart) {
        if (part.length() >= 1 && part.length() <= 63) {
            if (!PART_CHAR_MATCHER.matchesAllOf(CharMatcher.Ascii.INSTANCE.retainFrom(part))) {
                return false;
            }
            CharMatcher charMatcher = DASH_MATCHER;
            if (!charMatcher.matches(part.charAt(0)) && !charMatcher.matches(part.charAt(part.length() - 1))) {
                return (isFinalPart && DIGIT_MATCHER.matches(part.charAt(0))) ? false : true;
            }
        }
        return false;
    }

    public static boolean validateSyntax(List<String> parts) {
        int size = parts.size() - 1;
        if (!validatePart(parts.get(size), true)) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!validatePart(parts.get(i), false)) {
                return false;
            }
        }
        return true;
    }

    public final InternetDomainName ancestor(int levels) {
        ImmutableList<String> immutableList = this.parts;
        ImmutableList<String> immutableListSubList = immutableList.subList(levels, immutableList.size());
        int length = levels;
        for (int i = 0; i < levels; i++) {
            length += this.parts.get(i).length();
        }
        return new InternetDomainName(this.name.substring(length), immutableListSubList);
    }

    public InternetDomainName child(String leftParts) {
        StringBuilder sb = new StringBuilder();
        leftParts.getClass();
        sb.append(leftParts);
        sb.append(ExternalFourCCMapper.CODEC_NAME_SPLITTER);
        sb.append(this.name);
        return from(sb.toString());
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof InternetDomainName) {
            return this.name.equals(((InternetDomainName) object).name);
        }
        return false;
    }

    public final int findSuffixOfType(Optional<PublicSuffixType> desiredType) {
        int size = this.parts.size();
        for (int i = 0; i < size; i++) {
            String strJoin = DOT_JOINER.join(this.parts.subList(i, size));
            if (i > 0 && matchesType(desiredType, Optional.fromNullable(PublicSuffixPatterns.UNDER.get(strJoin)))) {
                return i - 1;
            }
            if (matchesType(desiredType, Optional.fromNullable(PublicSuffixPatterns.EXACT.get(strJoin)))) {
                return i;
            }
            if (PublicSuffixPatterns.EXCLUDED.containsKey(strJoin)) {
                return i + 1;
            }
        }
        return -1;
    }

    public boolean hasParent() {
        return this.parts.size() > 1;
    }

    public boolean hasPublicSuffix() {
        return publicSuffixIndex() != -1;
    }

    public boolean hasRegistrySuffix() {
        return registrySuffixIndex() != -1;
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public boolean isPublicSuffix() {
        return publicSuffixIndex() == 0;
    }

    public boolean isRegistrySuffix() {
        return registrySuffixIndex() == 0;
    }

    public boolean isTopDomainUnderRegistrySuffix() {
        return registrySuffixIndex() == 1;
    }

    public boolean isTopPrivateDomain() {
        return publicSuffixIndex() == 1;
    }

    public boolean isUnderPublicSuffix() {
        return publicSuffixIndex() > 0;
    }

    public boolean isUnderRegistrySuffix() {
        return registrySuffixIndex() > 0;
    }

    public InternetDomainName parent() {
        Preconditions.checkState(hasParent(), "Domain '%s' has no parent", this.name);
        return ancestor(1);
    }

    public ImmutableList<String> parts() {
        return this.parts;
    }

    public InternetDomainName publicSuffix() {
        if (hasPublicSuffix()) {
            return ancestor(publicSuffixIndex());
        }
        return null;
    }

    public final int publicSuffixIndex() {
        int i = this.publicSuffixIndexCache;
        if (i != -2) {
            return i;
        }
        int iFindSuffixOfType = findSuffixOfType(Absent.INSTANCE);
        this.publicSuffixIndexCache = iFindSuffixOfType;
        return iFindSuffixOfType;
    }

    public InternetDomainName registrySuffix() {
        if (hasRegistrySuffix()) {
            return ancestor(registrySuffixIndex());
        }
        return null;
    }

    public final int registrySuffixIndex() {
        int i = this.registrySuffixIndexCache;
        if (i != -2) {
            return i;
        }
        int iFindSuffixOfType = findSuffixOfType(Optional.of(PublicSuffixType.REGISTRY));
        this.registrySuffixIndexCache = iFindSuffixOfType;
        return iFindSuffixOfType;
    }

    public String toString() {
        return this.name;
    }

    public InternetDomainName topDomainUnderRegistrySuffix() {
        if (isTopDomainUnderRegistrySuffix()) {
            return this;
        }
        Preconditions.checkState(isUnderRegistrySuffix(), "Not under a registry suffix: %s", this.name);
        return ancestor(registrySuffixIndex() - 1);
    }

    public InternetDomainName topPrivateDomain() {
        if (isTopPrivateDomain()) {
            return this;
        }
        Preconditions.checkState(isUnderPublicSuffix(), "Not under a public suffix: %s", this.name);
        return ancestor(publicSuffixIndex() - 1);
    }

    public InternetDomainName(String name, ImmutableList<String> parts) {
        Preconditions.checkArgument(!parts.isEmpty(), "Cannot create an InternetDomainName with zero parts.");
        this.name = name;
        this.parts = parts;
    }
}
