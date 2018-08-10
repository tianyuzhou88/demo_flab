package org.webdriver.seleniumUI.utils;

public class Base64Decoder {
    public static String decoding(String encoder){
        byte[] decodedBytes = org.apache.commons.codec.binary.Base64.decodeBase64(new String(encoder));
        return new String(decodedBytes);
    }
    public static void main(String[] args){
        System.out.println(Base64Decoder.decoding("Q2lzY29BZG1pbiEyMzQ1DQo="));
    }
}
