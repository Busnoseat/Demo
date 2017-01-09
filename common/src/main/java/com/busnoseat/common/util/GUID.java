package com.busnoseat.common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * The type GUID.
 * @Description:  <br>     GUID产生一个36位的全局唯一id结构如：xxxxxxxx-xxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx <br>     1-8 位系统当前时间的低32位<br>     9-16位是服务主机的ip地址<br>     17-24位是创建对象的hashcode<br>     25-32 是SecureRandom或者Random产生的随机数<br>     构造函数GUID()默认采用Random产生随机数, GUID（true）则是采用SecureRandom产生随机数<br>     SecureRandom 保证每个随机数的产生均为密码加强，而Random则只保证单一随机数的产生位密码加强。<br>     SecureRandom产生随机数的时间大概为Random产生随机数的3.5倍。     <p>     用法：通过new GUID(true).next()获取一个唯一的id。<br>     如果是为了效率原因，可以通过new GUID(true).next()来获取。此id没有经过md5加密，为明文传输
 * @author liheng
 * @Date 2016 /3/8
 */
public class GUID {
    private static String a;
    private static Random b;
    private static SecureRandom c;
    private final String d;
    private String e;
    private final boolean f;

    private static int a(byte[] abyte) {
        int i = abyte[0] & -16777216;
        i += abyte[1] & 16711680;
        i += abyte[2] & 65280;
        i += abyte[3] & 255;
        return i;
    }

    /**
     * Instantiates a new Guid.
     */
    public GUID() {
        this(false);
    }

    /**
     * Instantiates a new Guid.
     *
     * @param flag the flag
     */
    public GUID(boolean flag) {
        f = flag;
        StringBuffer stringBuffer = new StringBuffer(a);
        stringBuffer.append(a(System.identityHashCode(this), 8));
        d = stringBuffer.toString();
        next();
    }

    /**
     * Next guid.
     *
     * @return the guid
     */
    public GUID next() {
        int i = (int) System.currentTimeMillis();
        int j = f ? c.nextInt() : b.nextInt();
        e = a(i, 8) + d + a(j, 8);
        return this;
    }

    /**
     * To plain string string.
     *
     * @return the string
     */
    public String toPlainString() {
        return e;
    }

    @Override public String toString() {
        String s = toPlainString().toUpperCase();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(s.substring(0, 8));
        stringBuffer.append("-");
        stringBuffer.append(s.substring(8, 12));
        stringBuffer.append("-");
        stringBuffer.append(s.substring(12, 16));
        stringBuffer.append("-");
        stringBuffer.append(s.substring(16, 20));
        stringBuffer.append("-");
        stringBuffer.append(s.substring(20));
        return stringBuffer.toString();
    }

    @Override public int hashCode() {
        return toPlainString().hashCode();
    }

    @Override public boolean equals(Object obj) {
        return (obj instanceof GUID) && ((GUID) obj).toPlainString().equals(toPlainString());
    }

    private static String a(int i, int j) {
        String s = Integer.toHexString(i);
        if (s.length() < j) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int k = 0; k < j - s.length(); k++) {
                stringBuffer.append("0");
            }
            stringBuffer.append(s);
            return stringBuffer.toString();
        } else {
            return s;
        }
    }

    static {
        InetAddress inetAddress;
        try {
            inetAddress = InetAddress.getLocalHost();
            byte abyte[] = inetAddress.getAddress();
            a = a(a(abyte), 8);
        } catch (UnknownHostException e) {
            // e.printStackTrace();
        }
        c = new SecureRandom();
        long l = c.nextLong();
        b = new Random(l);
    }

}
