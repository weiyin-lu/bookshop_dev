package cn.lntu.weiyin.utils;
//对文件进行Base64编解码

import org.apache.commons.codec.binary.Base64;

import java.io.*;
import java.util.UUID;

public class Base64Util {
    /**
     * 对文件BASE64编码
     * @param filePath 文件路径，此文件名应当从数据库中得出
     * @return BASE64编码结果
     */
    public static String base64Encoder(String filePath) throws IOException {
//        读取文件
        FileInputStream fileInputStream = new FileInputStream(filePath);
//        按照文件长度生成字节数组
        byte[] bytes = new byte[fileInputStream.available()];
//        写入数组
        fileInputStream.read(bytes);
//        关闭
        fileInputStream.close();
//        转换为base64编码
        String encodeBase64String = Base64.encodeBase64String(bytes);
        return encodeBase64String;
    }

    /**
     * BASE64解码图片
     * @param encode BASE64编码
     * @return 解码后的文件相对路径，src开始
     * @throws IOException
     */
    public static String base64Decoder(String encode) throws IOException {
//        解码BASE64
        byte[] decodeBase64 = Base64.decodeBase64(encode);
//        创建一个随机的UUID
        UUID uuid = UUID.randomUUID();
//        创建目录
        new File("bookimg/").mkdirs();
//        拼接文件路径
        String filePath = "bookimg/" + uuid + ".jpg";
//        创建文件
        File file = new File(filePath);
        file.createNewFile();
//        创建并写入到输出流
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(decodeBase64);
//        关闭
        fileOutputStream.close();
        return filePath;
    }

}
