package uz.pdp.apppcmarket.payLoad;

import lombok.Data;


@Data
public class ProductDto {
    private String name;
    private String model;
    private Integer attachmentId;
    private Integer categoryId;
    private String price;
    private boolean active;
}
