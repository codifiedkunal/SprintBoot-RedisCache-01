package com.kworld.bootredis.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kworld.bootredis.entity.Employee;
import com.kworld.bootredis.entity.SEX;

@Component
public class EmployeeMapper implements RowMapper<Employee>{
	private DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		return Employee.builder()
			.eid(rs.getLong("id"))
			.firstName(rs.getString("first_name"))
			.lastName(rs.getString("last_name"))
			.sex(getSex(rs.getString("sex")))
			.dob(getDOB(rs.getDate("dob")))
			.salary(rs.getDouble("salary"))
			.build();
	}

	private String getDOB(java.sql.Date dt) {
		Date date = dt == null ? dt : new Date(dt.getTime());
		if(date != null)
			return dateFormat.format(date);
		return null;
	}

	private SEX getSex(String sex) {
		if(null == sex)
			return SEX.NA;
		switch (sex) {
			case "M":
			case "m":
				return SEX.MALE;
			case "F":
			case "f":
				return SEX.FEMALE;
			default:
				return SEX.OTHER;
			}
	}
}
