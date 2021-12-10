package com.chensi.guava.io;

import com.google.common.base.Preconditions;

/***********************************
 * @author chensi
 * @date 2021/12/10 9:18
 ***********************************/
public final class Base64 {

    private final static String CODE_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    private final static char[] CODE_DICT = CODE_STRING.toCharArray();

    private Base64() {

    }

    public static String encode(String plain) {
        Preconditions.checkNotNull(plain);
        StringBuilder result = new StringBuilder();
        String binaryString = toBinary(plain);
        //base64: binaryString divide 6 bit as one group,add zero at right
        int delta = 6 - binaryString.length() % 6;

        for (int i = 0; i < delta && delta != 6; i++) {
            binaryString += '0';
        }
        //zoning
        for (int index = 0; index < binaryString.length() / 6; index++) {
            int begin = index * 6;
            String encoderString = binaryString.substring(begin, begin + 6);
            char encodeChar = CODE_DICT[Integer.valueOf(encoderString, 2)];
            result.append(encodeChar);
        }
        //add '='
        if (delta != 6) {
            for (int i = 0; i < delta / 2; i++) {
                result.append('=');
            }
        }
        return result.toString();
    }

    private static String toBinary(final String source) {
        final StringBuilder binaryResult = new StringBuilder();
        for (int index = 0; index < source.length(); index++) {
            String charBin = Integer.toBinaryString(source.charAt(index));
            //add zero at left
            int detla = 8 - charBin.length();
            for (int d = 0; d < detla; d++) {
                charBin = '0' + charBin;
            }

            binaryResult.append(charBin);
        }

        return binaryResult.toString();
    }

    //decode
    public static String decode(String encrypt) {
        StringBuilder resultBuilder = new StringBuilder();
        String temp = encrypt;
        int equalIndex = temp.indexOf("=");
        if (equalIndex > 0) {
            temp = temp.substring(0, equalIndex);
        }
        String base64Binary = toBase64Binary(temp);
        for (int i = 0; i < base64Binary.length() / 8; i++) {
            int begin = i * 8;
            String str = base64Binary.substring(begin, begin + 8);
            char c = Character.toChars(Integer.valueOf(str, 2))[0];
            resultBuilder.append(c);
        }

        return resultBuilder.toString();
    }

    private static String toBase64Binary(final String source) {

        final StringBuilder binaryResult = new StringBuilder();
        for (int index = 0; index < source.length(); index++) {
            int i = CODE_STRING.indexOf(source.charAt(index));
            String charBin = Integer.toBinaryString(i);
            int delta = 6 - charBin.length();
            for (int d = 0; d < delta; d++) {
                charBin = "0" + charBin;
            }
            binaryResult.append(charBin);
        }
        return binaryResult.toString();
    }

    public static void main(String[] args) {
        System.out.println(encode("hello"));
        System.out.println(encode("a"));
        System.out.println(encode("121"));
        System.out.println("==============");
        System.out.println(decode("aGVsbG8="));
        System.out.println(decode("YQ=="));
        System.out.println(decode("MTIx"));
    }
}
