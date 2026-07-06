package org.apache.commons.text;

import j$.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.Validate;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class RandomStringGenerator {
    public final List<Character> characterList;
    public final Set<CharacterPredicate> inclusivePredicates;
    public final int maximumCodePoint;
    public final int minimumCodePoint;
    public final TextRandomProvider random;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Builder implements org.apache.commons.text.Builder<RandomStringGenerator> {
        public static final int DEFAULT_LENGTH = 0;
        public static final int DEFAULT_MAXIMUM_CODE_POINT = 1114111;
        public static final int DEFAULT_MINIMUM_CODE_POINT = 0;
        public List<Character> characterList;
        public Set<CharacterPredicate> inclusivePredicates;
        public TextRandomProvider random;
        public int minimumCodePoint = 0;
        public int maximumCodePoint = 1114111;

        public Builder filteredBy(CharacterPredicate... characterPredicateArr) {
            if (characterPredicateArr == null || characterPredicateArr.length == 0) {
                this.inclusivePredicates = null;
                return this;
            }
            Set<CharacterPredicate> set = this.inclusivePredicates;
            if (set == null) {
                this.inclusivePredicates = new HashSet();
            } else {
                set.clear();
            }
            Collections.addAll(this.inclusivePredicates, characterPredicateArr);
            return this;
        }

        public Builder selectFrom(char... cArr) {
            this.characterList = new ArrayList();
            for (char c : cArr) {
                this.characterList.add(Character.valueOf(c));
            }
            return this;
        }

        public Builder usingRandom(TextRandomProvider textRandomProvider) {
            this.random = textRandomProvider;
            return this;
        }

        public Builder withinRange(int i, int i2) {
            Validate.isTrue(i <= i2, "Minimum code point %d is larger than maximum code point %d", Integer.valueOf(i), Integer.valueOf(i2));
            Validate.isTrue(i >= 0, "Minimum code point %d is negative", i);
            Validate.isTrue(i2 <= 1114111, "Value %d is larger than Character.MAX_CODE_POINT.", i2);
            this.minimumCodePoint = i;
            this.maximumCodePoint = i2;
            return this;
        }

        @Override // org.apache.commons.text.Builder
        public RandomStringGenerator build() {
            return new RandomStringGenerator(this.minimumCodePoint, this.maximumCodePoint, this.inclusivePredicates, this.random, this.characterList);
        }

        public Builder withinRange(char[]... cArr) {
            this.characterList = new ArrayList();
            for (char[] cArr2 : cArr) {
                Validate.isTrue(cArr2.length == 2, "Each pair must contain minimum and maximum code point", new Object[0]);
                char c = cArr2[0];
                char c2 = cArr2[1];
                Validate.isTrue(c <= c2, "Minimum code point %d is larger than maximum code point %d", Integer.valueOf(c), Integer.valueOf(c2));
                for (int i = c; i <= c2; i++) {
                    this.characterList.add(Character.valueOf((char) i));
                }
            }
            return this;
        }
    }

    public String generate(int i) {
        if (i == 0) {
            return "";
        }
        long j = i;
        Validate.isTrue(i > 0, "Length %d is smaller than zero.", j);
        StringBuilder sb = new StringBuilder(i);
        do {
            List<Character> list = this.characterList;
            int iGenerateRandomNumber = (list == null || list.isEmpty()) ? generateRandomNumber(this.minimumCodePoint, this.maximumCodePoint) : generateRandomNumber(this.characterList);
            int type = Character.getType(iGenerateRandomNumber);
            if (type != 0 && type != 18 && type != 19) {
                Set<CharacterPredicate> set = this.inclusivePredicates;
                if (set == null) {
                    sb.appendCodePoint(iGenerateRandomNumber);
                    j--;
                    break;
                }
                Iterator<CharacterPredicate> it = set.iterator();
                while (it.hasNext()) {
                    if (it.next().test(iGenerateRandomNumber)) {
                        sb.appendCodePoint(iGenerateRandomNumber);
                        j--;
                        break;
                    }
                }
            }
        } while (j != 0);
        return sb.toString();
    }

    public final int generateRandomNumber(int i, int i2) {
        TextRandomProvider textRandomProvider = this.random;
        return textRandomProvider != null ? textRandomProvider.nextInt((i2 - i) + 1) + i : ThreadLocalRandom.current().nextInt(i, i2 + 1);
    }

    public RandomStringGenerator(int i, int i2, Set<CharacterPredicate> set, TextRandomProvider textRandomProvider, List<Character> list) {
        this.minimumCodePoint = i;
        this.maximumCodePoint = i2;
        this.inclusivePredicates = set;
        this.random = textRandomProvider;
        this.characterList = list;
    }

    public final int generateRandomNumber(List<Character> list) {
        int size = list.size();
        TextRandomProvider textRandomProvider = this.random;
        if (textRandomProvider != null) {
            return String.valueOf(list.get(textRandomProvider.nextInt(size))).codePointAt(0);
        }
        return String.valueOf(list.get(ThreadLocalRandom.current().nextInt(0, size))).codePointAt(0);
    }

    public String generate(int i, int i2) {
        Validate.isTrue(i >= 0, "Minimum length %d is smaller than zero.", i);
        Validate.isTrue(i <= i2, "Maximum length %d is smaller than minimum length %d.", Integer.valueOf(i2), Integer.valueOf(i));
        return generate(generateRandomNumber(i, i2));
    }
}
