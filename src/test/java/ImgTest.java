import cn.lntu.weiyin.utils.Base64Util;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import java.io.*;

public class ImgTest {
    @Test
    public void coder() throws IOException {
        String encoder = Base64Util.base64Encoder("cjw.jpg");
        System.out.println(encoder);
        String decoder = Base64Util.base64Decoder(encoder);
        System.out.println(decoder);
    }
}
