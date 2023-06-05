package the.husky.jdbc.mapper;

import org.springframework.jdbc.core.RowMapper;
import the.husky.entity.Check;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckRowMapper implements RowMapper<Check> {
    @Override
    public Check mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Check.builder()
                .checkId(rs.getLong("checkId"))
                .orgName(rs.getString("orgName"))
                .pointName(rs.getString("pointName"))
                .fiscalNumber(rs.getLong("fiscalNumber"))
                .pointAddress(rs.getString("pointAddress"))
                .orderTaxNumber(rs.getString("orderTaxNumber"))
                .totalSum(rs.getDouble("totalSum"))
                .orderDate(rs.getDate("orderDate"))
                .tin(rs.getString("tin"))
                .taxPercent(rs.getDouble("taxPercent"))
                .taxSum(rs.getDouble("taxSum"))
                .taxType(rs.getString("taxType"))
                .build();
    }
}
