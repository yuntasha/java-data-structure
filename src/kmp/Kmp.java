package kmp;

import java.util.ArrayList;
import java.util.List;

public class Kmp {
    private String pattern;
    private String string;

    public Kmp(String pattern, String string) {
        this.pattern = pattern;
        this.string = string;
    }

    public int count() {
        int result = 0;

        int patternIdx = 0;

        int[] table = getPi();

        for (int stringIdx = 0; stringIdx < string.length(); stringIdx++) {
            while (patternIdx > 0 && string.charAt(stringIdx) != pattern.charAt(patternIdx)) {
                patternIdx = table[patternIdx - 1];
            }

            if (string.charAt(stringIdx) == pattern.charAt(patternIdx)) {
                patternIdx++;
                if (patternIdx == pattern.length()) {
                    result++;
                    patternIdx = table[patternIdx - 1];
                }
            }
        }

        return result;
    }

    public List<Integer> getIdx() {
        List<Integer> result = new ArrayList<>();

        int patternIdx = 0;

        int[] table = getPi();

        for (int stringIdx = 0; stringIdx < string.length(); stringIdx++) {
            while (patternIdx > 0 && string.charAt(stringIdx) != pattern.charAt(patternIdx)) {
                patternIdx = table[patternIdx - 1];
            }

            if (string.charAt(stringIdx) == pattern.charAt(patternIdx)) {
                patternIdx++;
                if (patternIdx == pattern.length()) {
                    result.add(stringIdx - patternIdx + 1);
                    patternIdx = table[patternIdx - 1];
                }
            }
        }

        return result;
    }

    public int[] getPi() {
        int[] table = new int[pattern.length()];

        int pre = 0;

        for (int suf = 1; suf < pattern.length(); suf++) {
            while (pre > 0 && pattern.charAt(pre) != pattern.charAt(suf)) {
                pre = table[pre - 1];
            }

            if (pattern.charAt(pre) == pattern.charAt(suf)) {
                table[suf] = ++pre;
            }
        }

        return table;
    }
}
