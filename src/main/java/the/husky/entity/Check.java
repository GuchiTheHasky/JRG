package the.husky.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;
@Getter
@Setter
@ToString
@Builder
public class Check {
    private long checkId;
    private String orgName;
    private String pointName;
    private long fiscalNumber;
    private String pointAddress;
    private String orderTaxNumber;
    private double totalSum;
    private Date orderDate;
    private String tin;
    private double taxPercent;
    private double taxSum;
    private String taxType;
    private List<Product> products;
}

















/////////////////////////////////////////////////////////////////
//// Реліз №2 Генерує Список чеків зі списком продуктів


//package the.husky;
//
//import lombok.Builder;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper.java;
//import org.sqlite.SQLiteDataSource;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@Getter
//@Setter
//@ToString
//@Builder
//public class Check {
//
//    private final static String SELECT = "SELECT " +
//            "CHECKHEAD.ID AS checkId, " +
//            "ORGNAME AS orgName, " +
//            "POINTNAME AS pointName, " +
//            "FN AS fiscalNumber, " +
//            "POINTADDR AS pointAddress, " +
//            "ORDERTAXNUM AS orderTaxNumber, " +
//            "TOTALSUM AS totalSum, " +
//            "ORDERDATE AS orderDate, " +
//            "TIN AS tin, " +
//            "CHECKTAX.TAXPRC AS taxPercent, " +
//            "TOTALSUM * (CHECKTAX.TAXPRC / 100) AS taxSum, " +
//            "TAXCODE AS taxType, " +
//            "GOODSNAME AS productName, " +
//            "PRICE AS productPrice, " +
//            "PRICE * AMOUNT * (1 - TAXES.TAXPRC / 100) AS productSum, " +
//            "AMOUNT AS productQuantity, " +
//            "PRICE * AMOUNT - (PRICE * AMOUNT * (1 - TAXES.TAXPRC / 100)) AS productDiscount, " +
//            "LETTER AS discountTaxType " +
//            "FROM CHECKHEAD " +
//            "JOIN CHECKBODY ON CHECKHEAD.ID = CHECKBODY.CHECKID " +
//            "JOIN TAXES ON TAXES.NAME = CHECKBODY.LETTER " +
//            "JOIN CHECKTAX ON CHECKHEAD.ID = CHECKTAX.CHECKID";
//
//    private long checkId;
//    private String orgName;
//    private String pointName;
//    private long fiscalNumber;
//    private String pointAddress;
//    private String orderTaxNumber;
//    private double totalSum;
//    private Date orderDate;
//    private String tin;
//    private double taxPercent;
//    private double taxSum;
//    private String taxType;
//    private List<Product> products;
//
//    public static void main(String[] args) {
//        SQLiteDataSource dataSource = new SQLiteDataSource();
//        dataSource.setUrl("jdbc:sqlite:C:/Program Files/SQLiteStudio/task/data.db");
//
//        JdbcTemplate template = new JdbcTemplate(dataSource);
//
//        List<List<Check>> checks = template.query(SELECT, new CheckRowMapper());
//
//        System.out.println(checks);
//    }
//
//    private static class CheckRowMapper implements RowMapper.java<List<Check>> {
//        @Override
//        public List<Check> mapRow(ResultSet rs, int rowNum) throws SQLException {
//            List<Check> checks = new ArrayList<>();
//            long currentCheckId = -1;
//
//            while (rs.next()) {
//                long checkId = rs.getLong("checkId");
//
//                if (checkId != currentCheckId) {
//                    Check.CheckBuilder builder = Check.builder()
//                            .checkId(checkId)
//                            .orgName(rs.getString("orgName"))
//                            .pointName(rs.getString("pointName"))
//                            .fiscalNumber(rs.getLong("fiscalNumber"))
//                            .pointAddress(rs.getString("pointAddress"))
//                            .orderTaxNumber(rs.getString("orderTaxNumber"))
//                            .totalSum(rs.getDouble("totalSum"))
//                            .orderDate(rs.getDate("orderDate"))
//                            .tin(rs.getString("tin"))
//                            .taxPercent(rs.getDouble("taxPercent"))
//                            .taxSum(rs.getDouble("taxSum"))
//                            .taxType(rs.getString("taxType"));
//
//                    List<Product> products = new ArrayList<>();
//                    Product product = Product.builder()
//                            .productName(rs.getString("productName"))
//                            .productPrice(rs.getDouble("productPrice"))
//                            .productSum(rs.getDouble("productSum"))
//                            .productQuantity(rs.getDouble("productQuantity"))
//                            .productDiscount(rs.getDouble("productDiscount"))
//                            .discountTaxType(rs.getString("discountTaxType"))
//                            .build();
//                    products.add(product);
//
//                    Check check = builder.products(products).build();
//                    checks.add(check);
//
//                    currentCheckId = checkId;
//                } else {
//                    Product product = Product.builder()
//                            .productName(rs.getString("productName"))
//                            .productPrice(rs.getDouble("productPrice"))
//                            .productSum(rs.getDouble("productSum"))
//                            .productQuantity(rs.getDouble("productQuantity"))
//                            .productDiscount(rs.getDouble("productDiscount"))
//                            .discountTaxType(rs.getString("discountTaxType"))
//                            .build();
//
//                    checks.get(checks.size() - 1).getProducts().add(product);
//                }
//            }
//            return checks;
//        }
//    }
//}







//////////////////////////////////////////////////////
// РЕЛІЗ №1 ГЕНЕРУЄ СПИСОК, АЛЕ ЛИШЕ З ОДНИМ ПРОДУКТОМ
//////////////////////////////////////////////////////

//package the.husky;
//
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.sqlite.SQLiteDataSource;
//
//import java.util.*;
//
//@Getter
//@Setter
//@ToString
//public class Check {
//
//    private final static String SELECT = "SELECT ORGNAME                                                      AS orgName,\n" +
//            "       POINTNAME                                                    AS pointName,\n" +
//            "       FN                                                           AS fiscalNumber,\n" +
//            "       POINTADDR                                                    AS pointAddress,\n" +
//            "       ORDERTAXNUM                                                  AS orderTaxNumber,\n" +
//            "       TOTALSUM                                                     AS totalSum,\n" +
//            "       ORDERDATE                                                    AS orderDate,\n" +
//            "       TIN                                                          AS tin,\n" +
//            "       CHECKTAX.TAXPRC                                              AS taxPercent,\n" +
//            "       TOTALSUM * (CHECKTAX.TAXPRC / 100)                           AS taxSum,\n" +
//            "       TAXCODE                                                      AS taxType,\n" +
//            "       GOODSNAME                                                    AS name,\n" +
//            "       PRICE                                                        AS price,\n" +
//            "       PRICE * AMOUNT * (1 - TAXES.TAXPRC / 100)                    AS sum,\n" +
//            "       AMOUNT                                                       AS quantity,\n" +
//            "       PRICE * AMOUNT - (PRICE * AMOUNT * (1 - TAXES.TAXPRC / 100)) AS discount,\n" +
//            "       LETTER                                                       AS discountTaxType\n" +
//            "\n" +
//            "FROM CHECKHEAD\n" +
//            "         JOIN CHECKBODY ON CHECKHEAD.ID = CHECKBODY.CHECKID\n" +
//            "\n" +
//            "         JOIN TAXES\n" +
//            "              ON TAXES.NAME = CHECKBODY.LETTER\n" +
//            "         JOIN CHECKTAX ON CHECKHEAD.ID = CHECKTAX.CHECKID;";
//
//    private String orgName;// ORGNAME CHECKHEAD
//    private String pointName;// POINTNAME CHECKHEAD
//    private long fiscalNumber;// FN CHECKHEAD
//    private String pointAddress;// POINTADDR CHECKHEAD
//    private String orderTaxNumber;// ORDERTAXNUM CHECKHEAD
//    private double totalSum;// TOTALSUM CHECKHEAD
//    private Date orderDate;// ORDERDATE CHECKHEAD //TODO DATETIME
//    private String tin;// TIN CHECKHEAD
//    private double taxPercent;// TAXPRC JOIN CHECKTAX ON CHECKHEAD.ID = CHECKTAX.CHECKID
//    private double taxSum;// СУМА ВСІЧ ТОВАРІВ * TAXPERCENT
//    private String taxType;// TAXCODE
//    private List<Product> products;
//
//
//    public static void main(String[] args) {
//
//        SQLiteDataSource dataSource = new SQLiteDataSource();
//        dataSource.setUrl("jdbc:sqlite:C:/Program Files/SQLiteStudio/task/data.db");
//
//        JdbcTemplate template = new JdbcTemplate(dataSource);
//
//        List<Check> checks = template.query(SELECT, (rs) -> {
//            List<Check> checkList = new ArrayList<>();
//
//            while (rs.next()) {
//                Check check = new Check();
//                check.setOrgName(rs.getString("orgName"));
//                check.setPointName(rs.getString("pointName"));
//                check.setFiscalNumber(rs.getLong("fiscalNumber"));
//                check.setPointAddress(rs.getString("pointAddress"));
//                check.setOrderTaxNumber(rs.getString("orderTaxNumber"));
//                check.setTotalSum(rs.getDouble("totalSum"));
//                check.setOrderDate(rs.getDate("orderDate"));
//                check.setTin(rs.getString("tin"));
//                check.setTaxPercent(rs.getDouble("taxPercent"));
//                check.setTaxSum(rs.getDouble("taxSum"));
//                check.setTaxType(rs.getString("taxType"));
//                check.setProducts(new ArrayList<>());
//
//                checkList.add(check);
//
//                Product product = new Product();
//                product.setName(rs.getString("name"));
//                product.setPrice(rs.getDouble("price"));
//                product.setSum(rs.getDouble("sum"));
//                product.setQuantity(rs.getDouble("quantity"));
//                product.setDiscount(rs.getDouble("discount"));
//                product.setDiscountTaxType(rs.getString("discountTaxType"));
//
//                check.getProducts().add(product);
//            }
//
//            return checkList;
//        });
//
//        assert checks != null;
//        for (Check check : checks) {
//            System.out.println(check);
//        }
//    }
//}
//
//
