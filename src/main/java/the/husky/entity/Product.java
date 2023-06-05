package the.husky.entity;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
public class Product {
    private String productName; // CHECKBODY GOODSNAME
    private double productPrice; // CHECKBODY PRICE
    private double productSum; // PRICE * QUANTITY - DISCOUNT
    private double productQuantity; // CHECKBODY AMOUNT
    private double productDiscount; // TAXES.TAXPRC * sum * quantity / 100
    private String discountTaxType; // TAXES NAME
}
