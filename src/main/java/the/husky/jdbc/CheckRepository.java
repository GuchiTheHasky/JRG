package the.husky.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import the.husky.entity.Check;
import the.husky.entity.Product;
import the.husky.jdbc.mapper.CheckRowMapper;
import the.husky.jdbc.mapper.ProductRowMapper;

import java.util.List;

public class CheckRepository {
    private static final int RANDOM_CHECKS_COUNT = 15;

    private static final RowMapper<Check> CHECK_ROW_MAPPER = new CheckRowMapper();
    private static final RowMapper<Product> PRODUCT_ROW_MAPPER = new ProductRowMapper();

    private static final String GET_RANDOM_CHECKS = "SELECT " +
            "CHECKHEAD.ID AS checkId, " +
            "ORGNAME AS orgName, " +
            "POINTNAME AS pointName, " +
            "FN AS fiscalNumber, " +
            "POINTADDR AS pointAddress, " +
            "ORDERTAXNUM AS orderTaxNumber, " +
            "TOTALSUM AS totalSum, " +
            "ORDERDATE AS orderDate, " +
            "TIN AS tin, " +
            "CHECKTAX.TAXPRC AS taxPercent, " +
            "TOTALSUM * (CHECKTAX.TAXPRC / 100) AS taxSum, " +
            "TAXCODE AS taxType " +
            "FROM CHECKHEAD " +
            "JOIN CHECKTAX ON CHECKHEAD.ID = CHECKTAX.CHECKID ORDER BY RANDOM() LIMIT ?";
    private final String GET_PRODUCTS_FOR_CHECK = "SELECT " +
            "GOODSNAME AS productName, " +
            "PRICE AS productPrice, " +
            "PRICE * AMOUNT * (1 - TAXES.TAXPRC / 100) AS productSum, " +
            "AMOUNT AS productQuantity, " +
            "PRICE * AMOUNT - (PRICE * AMOUNT * (1 - TAXES.TAXPRC / 100)) AS productDiscount, " +
            "LETTER AS discountTaxType " +
            "FROM CHECKBODY " +
            "JOIN TAXES ON TAXES.NAME = CHECKBODY.LETTER " +
            "WHERE CHECKBODY.CHECKID = ?";

    private final JdbcTemplate jdbcTemplate;

    public CheckRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Check> getRandomChecks() {
        List<Check> checks = jdbcTemplate.query(GET_RANDOM_CHECKS, CHECK_ROW_MAPPER, RANDOM_CHECKS_COUNT);

        for (Check check : checks) {
            List<Product> products = getProductsForCheck(check.getCheckId());
            check.setProducts(products);
        }
        return checks;
    }

    private List<Product> getProductsForCheck(long checkId) {
        return jdbcTemplate.query(GET_PRODUCTS_FOR_CHECK, PRODUCT_ROW_MAPPER, checkId);
    }
}