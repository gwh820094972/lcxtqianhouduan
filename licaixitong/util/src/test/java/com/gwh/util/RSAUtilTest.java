package com.gwh.util;

import org.junit.Test;

public class RSAUtilTest {
    static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDJX3ZscSAJMbSQzG4guTZAQVI\n" +
            "GSSeG3gvUo1x+hnMW6FcFXOY/QQYOFvDkrDmvuemO7id/zQf0Fy2k0oY9e5XdcSS\n" +
            "MxJHdxaSP+na5hyIr3RtQznaRoPWiiEeOe6FSt19CouTdFoPHLUp5uhkDobGz1hC\n" +
            "3FtN9G2nT3HC71V+xwIDAQAB";
    static final String privateKey ="MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAMMlfdmxxIAkxtJD\n" +
            "MbiC5NkBBUgZJJ4beC9SjXH6GcxboVwVc5j9BBg4W8OSsOa+56Y7uJ3/NB/QXLaT\n" +
            "Shj17ld1xJIzEkd3FpI/6drmHIivdG1DOdpGg9aKIR457oVK3X0Ki5N0Wg8ctSnm\n" +
            "6GQOhsbPWELcW030badPccLvVX7HAgMBAAECgYEAj6IPwGR8mkj3Yhl9Bi6pnCOJ\n" +
            "LKj877aOPl6Hp2WLCYRD/w/29pr8IYuR1yAoPp4yyILafsgxKF1ncCZ2+mpBUytZ\n" +
            "VFpIG+cXG+O+sjM0K3CKvdsk+wgj4VSGeC60AKn0Wqx0QhhX2Csbw7MDpIOZKqko\n" +
            "Y1m9kYawUQOxFo7+DwkCQQDiQMVcq41d/yIiaH5x0PdDI281WunYuJiBkvLC1+Z2\n" +
            "/+aE2MPb6vaidN1yDBWjt1paq3NiBr7IMcD98dt3bBD7AkEA3M28P94OiIvoh4yc\n" +
            "0HpiVvxuihXIOK+ZrL46NnkbsafdqlQiVfD2sqqTL47qOlucL7VpHlpWWnGpmBmO\n" +
            "CvgXpQJBAJryJLdsPSpMWcIasc2cgFmYYMa3dkKXWMoUKvDJDuocInCESs3HQhTL\n" +
            "2AQMxp0SKmzB0gkl+W38tOC0y8ojVhUCQAf2FxKKdCWyPbkddc4Ci6ctROMPs/9v\n" +
            "PXBAD6NFLJ10OwbYL8C/hnWctt/hHErsYK5LsTWZ9kEelgnY6gk1Ns0CQQCrpdm1\n" +
            "3Oa0/79PEaOCLXRXNZi1uY21vnM3GSZ9ohV4CAA7NMLkl6vhOyniDMzJ+BlpYmHi\n" +
            "gJyCAefsN+n4fLoQ";

    @Test
    public void signTest(){
        String text = "123456";
        String sign =RSAUtil.sign(text,privateKey);
        System.out.println(sign);
        System.out.println(RSAUtil.verify(text, sign, publicKey));
    }
}
