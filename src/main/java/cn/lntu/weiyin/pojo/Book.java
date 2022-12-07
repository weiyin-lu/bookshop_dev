package cn.lntu.weiyin.pojo;

import lombok.Data;

@Data
public class Book {
    private Integer id;
//    book_name
    private String bookName;
//    book_type
    private String bookType;
//    book_price
    private Double bookPrice;
//    book_img base64
    private String bookImg;
}
