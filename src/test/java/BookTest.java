import cn.lntu.weiyin.pojo.Book;
import cn.lntu.weiyin.services.BookService;
import cn.lntu.weiyin.utils.Base64Util;
import org.junit.Test;

import java.io.IOException;

public class BookTest {
//    插入数据的部分测试
    @Test
    public void insertBookTest() throws IOException {
//        测试用例（图片）
        String encoder = Base64Util.base64Encoder("cjw.jpg");
//        测试用例
        Book book = new Book();
        book.setBookName("测试用例");
        book.setBookType("测试类型");
        book.setBookPrice(1145.14);
        book.setBookImg(Base64Util.base64Decoder(encoder));

        BookService service = new BookService();
        Boolean aBoolean = service.insertBook(book);

        System.out.println(aBoolean);
    }
}
