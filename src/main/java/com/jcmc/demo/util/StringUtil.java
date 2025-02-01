package com.jcmc.demo.util;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    public static final String LINE_SEPARATOR = "line.separator";
    public static final String FILE_SEPARATOR = "file.separator";
    public static final String PATH_SEPARATOR = "path.separator";

    public static final String NULL_STRING = "null";
    public static final String EMPTY_STRING = "";
    public static final String WHITESPACE_REGEXP = "\\s";

    public static final String WHITESPACE_STRING = " ";
    public static final String CARRIAGE_RETURN_STRING = "\r";
    public static final String NEWLINE_STRING = "\n";
    public static final String TAB_STRING = "\t";
    public static final String BACKSPACE_STRING = "\b";
    public static final String FORMFEED_STRING = "\f";
    public static final String PERIOD_STRING = ".";
    public static final String COMMA_STRING = ",";
    public static final String COLON_STRING = ":";
    public static final String SEMICOLON_STRING = ";";
    public static final String EXCLAMATION_START_STRING = "¡";
    public static final String EXCLAMATION_END_STRING = "!";
    public static final String QUESTION_START_STRING = "¿";
    public static final String QUESTION_END_STRING = "?";
    public static final String QUOTATION_STRING = "\"";
    public static final String APOSTROPHE_STRING = "\'";
    public static final String CARET_STRING = "^";
    public static final String DASH_STRING = "-";
    public static final String PLUS_STRING = "+";
    public static final String EQUALS_STRING = "=";
    public static final String PARENTHESES_START_STRING = "(";
    public static final String PARENTHESES_END_STRING = ")";
    public static final String BRACKETS_START_STRING = "[";
    public static final String BRACKETS_END_STRING = "]";
    public static final String BRACES_START_STRING = "{";
    public static final String BRACES_END_STRING = "}";
    public static final String CHEVRONS_START_STRING = "<";
    public static final String CHEVRONS_END_STRING = ">";
    public static final String SLASH_STRING = "/";
    public static final String BACKSLASH_STRING = "\\";
    public static final String AMPERSAND_STRING = "&";
    public static final String ASTERISK_STRING = "*";
    public static final String AT_SIGN_STRING = "@";
    public static final String NUMBER_SIGN_STRING = "#";
    public static final String PERCENT_STRING = "%";
    public static final String PIPE_STRING = "|";
    public static final String TILDE_STRING = "~";
    public static final String DOLLAR_STRING = "$";
    public static final String UNDERSCORE_STRING = "_";

    public static final Character WHITESPACE = ' ';
    public static final Character CARRIAGE_RETURN = '\r';
    public static final Character NEWLINE = '\n';
    public static final Character PERIOD = '.';
    public static final Character COMMA = ',';
    public static final Character COLON = ':';
    public static final Character SEMICOLON = ';';
    public static final Character EXCLAMATION_START = '¡';
    public static final Character EXCLAMATION_END = '!';
    public static final Character QUESTION_START = '¿';
    public static final Character QUESTION_END = '?';
    public static final Character QUOTATION = '"';
    public static final Character APOSTROPHE = '\'';
    public static final Character CARET = '^';
    public static final Character DASH = '-';
    public static final Character PLUS = '+';
    public static final Character EQUALS = '=';
    public static final Character PARENTHESES_START = '(';
    public static final Character PARENTHESES_END = ')';
    public static final Character BRACKETS_START = '[';
    public static final Character BRACKETS_END = ']';
    public static final Character BRACES_START = '{';
    public static final Character BRACES_END = '}';
    public static final Character CHEVRONS_START = '<';
    public static final Character CHEVRONS_END = '>';
    public static final Character SLASH = '/';
    public static final Character BACKSLASH = '\\';
    public static final Character AMPERSAND = '&';
    public static final Character ASTERISK = '*';
    public static final Character AT_SIGN = '@';
    public static final Character NUMBER_SIGN = '#';
    public static final Character PERCENT = '%';
    public static final Character PIPE = '|';
    public static final Character TILDE = '~';
    public static final Character DOLLAR = '$';
    public static final Character UNDERSCORE = '_';

    private StringUtil() {}

    static {
        //0-9A-Za-z
        int[][] codes = new int[][]{
                {48, 57},
                {65, 90},
                {97, 122}
        };
        int length;
    }

    public static String lineSeparator() {
        return System.getProperty(LINE_SEPARATOR);
    }

    public static String fileSeparator() {
        return System.getProperty(FILE_SEPARATOR);
    }

    public static String pathSeparator() {
        return System.getProperty(PATH_SEPARATOR);
    }

    public static String removeAccents(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\P{ASCII}");

        return pattern.matcher(normalized).replaceAll(EMPTY_STRING);
    }

    public static String uniqueWhitespace(String input) {
        return input.replaceAll("^ +| +$|( )+", "$1");
    }


    public static String cleanUrl(String url) {
        return cleanUrl(url, null, false);
    }

    public static String cleanUrlPrefix(String url, String prefix) {
        return cleanUrl(url, prefix, false);
    }

    public static String cleanUrlSuffix(String url) {
        return cleanUrl(url, null, true);
    }

    public static String cleanUrl(String url, String prefix, boolean suffix) {
        int index;

        url = (url != null ? decodeUrl(url) : null);

        if(url == null) {
            return null;
        }

        index = url.indexOf(QUESTION_END_STRING);

        if(index > -1) {
            url = url.substring(0, index);
        }

        if(prefix != null && url.startsWith(prefix)) {
            url = url.substring(prefix.length(), url.length());
        }

        if(suffix) {
            index = url.lastIndexOf(PERIOD_STRING);

            if(index > -1) {
                url = url.substring(0, index);
            }
        }

        return url;
    }

    public static String encodeUrl(String url) {
        return encodeUrl(url, StandardCharsets.UTF_8.toString());
    }

    public static String encodeUrl(String url, String encode) {
        try {
            return URLEncoder.encode(url, encode);
        } catch(Exception ex) {
            return null;
        }
    }

    public static String decodeUrl(String url) {
        return decodeUrl(url, StandardCharsets.UTF_8.toString());
    }

    public static String decodeUrl(String url, String encode) {
        try {
            return URLDecoder.decode(url, encode);
        } catch(Exception ex) {
            return null;
        }
    }

    public static String cut(String value, int size) {
        if(value != null && value.length() > size) {
            return value.substring(0, size);
        } else {
            return value;
        }
    }



    public static String join(Object separator, Collection<?> items) {
        return join(separator, items.toArray());
    }

    public static String join(Object separator, Object... items) {
        StringBuilder sb = new StringBuilder();
        String text;
        int index;
        int last;

        if(items != null) {
            last = items.length - 1;
            index = 0;

            for(Object item : items) {
                text = (item != null ? item.toString().trim() : EMPTY_STRING);

                if(!text.isEmpty()) {
                    sb.append(text);

                    if(index != last) {
                        sb.append(separator);
                    }
                }

                index++;
            }
        }

        return sb.toString().trim();
    }

    public static String concat(Object... items) {
        StringBuilder sb = new StringBuilder();

        if(items != null) {
            for(Object item : items) {
                if(item != null) {
                    sb.append(item);
                }
            }
        }

        return sb.toString().trim();
    }

    public static String repeat(String value, int times) {
        StringBuilder repeated = new StringBuilder();

        while(times > 0) {
            repeated.append(value);
            times--;
        }

        return repeated.toString();
    }

    public static boolean match(String value, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);

        return matcher.find();
    }


    public static String hideText(String value) {
        return hideText(value, -1, -1);
    }

    public static String hideText(String value, int hideLength) {
        return hideText(value, hideLength, -1);
    }

    public static String hideText(String value, int hideLength, int lastShown) {
        String part;
        int initShown;
        int hideCount;

        if(value == null) {
            return null;
        }

        value = value.trim();

        if(lastShown > -1 && lastShown < value.length()) {
            initShown = value.length() - lastShown;
            part = value.substring(initShown);
            value = value.substring(0, initShown);
            value = value.replaceAll(String.valueOf(PERIOD),
                    String.valueOf(ASTERISK));
            value += part;
        } else {
            value = value.replaceAll(String.valueOf(PERIOD),
                    String.valueOf(ASTERISK));
        }

        hideCount = value.lastIndexOf(ASTERISK) + 1;

        if(hideLength > 0 && hideLength <= hideCount) {
            value = value.substring(hideCount - hideLength);
        }

        return value;
    }

    public static String trim(String string) {
        return string == null ? null : string.trim();
    }

    public static String trimLines(String string) {
        return trimCharacters(string, NEWLINE, CARRIAGE_RETURN);
    }

    public static String trimLinesLeft(String string) {
        return trimCharactersLeft(string, NEWLINE, CARRIAGE_RETURN);
    }

    public static String trimLinesRight(String string) {
        return trimCharactersRight(string, NEWLINE, CARRIAGE_RETURN);
    }

    public static String trimComma(String string) {
        return trimCharacters(string, COMMA);
    }

    public static String trimCommaLeft(String string) {
        return trimCharactersLeft(string, COMMA);
    }

    public static String trimCommaRight(String string) {
        return trimCharacters(string, COMMA);
    }

    public static String trimCharacters(String string, char... pivots) {
        return trimCharacters(string, true, true, pivots);
    }

    public static String trimCharactersLeft(String string, char... pivots) {
        return trimCharacters(string, true, false, pivots);
    }

    public static String trimCharactersRight(String string, char... pivots) {
        return trimCharacters(string, false, true, pivots);
    }

    private static String trimCharacters(String string, boolean left,
                                         boolean right, char... pivots) {
        Set<Character> discarded = new HashSet<>();
        char[] characters;
        int length;
        int i = 0;
        int j;

        if(string == null) {
            return string;
        }

        discarded.add(WHITESPACE);

        for(char c : pivots) {
            discarded.add(c);
        }

        length = string.length();
        characters = string.toCharArray();
        j = length;

        if(left) {
            while(i < length && discarded.contains(characters[i])) {
                i++;
            }
        }

        if(right) {
            j = length - 1;

            while(j > -1 && discarded.contains(characters[j])) {
                j--;
            }

            j++;
        }

        return (i <= j ? string.substring(i, j) : EMPTY_STRING);
    }

    public static String getWordsRegex(String... words) {
        StringBuilder regex;

        if(words != null && words.length > 0) {
            regex = new StringBuilder();

            for(String word : words) {
                regex.append("(?i)");
                regex.append(word.trim().toUpperCase());
                regex.append("|");
            }

            regex = new StringBuilder(trimCharactersRight(regex.toString(), '|'));

            return regex.toString();
        } else {
            return null;
        }
    }

    public static Pattern getWordsPattern(String... words) {
        String regex = getWordsRegex(words);

        if(regex != null && !regex.isEmpty()) {
            return Pattern.compile(regex);
        } else {
            return null;
        }
    }

}
