package the.husky.jdbc.mapper;

import org.springframework.jdbc.core.RowMapper;
import the.husky.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Product.builder()
                .productName(rs.getString("productName"))
                .productPrice(rs.getDouble("productPrice"))
                .productSum(rs.getDouble("productSum"))
                .productQuantity(rs.getDouble("productQuantity"))
                .productDiscount(rs.getDouble("productDiscount"))
                .discountTaxType(rs.getString("discountTaxType"))
                .build();
    }
}
