package com.pennant.EmployeeCrud.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pennant.EmployeeCrud.DTO.EmployeeDTO;
import com.pennant.EmployeeCrud.DTO.EmployeeListDTO;

import JDBCUTILITIES.JdbcUtil;

public class EmployeeCrudServiceImpl implements EmployeeCrudService {

	private Connection con;
	private PreparedStatement psmt;
	private ResultSet rs;

	@Override
	public EmployeeListDTO addEmployee(EmployeeDTO employee) {
		EmployeeListDTO employees = new EmployeeListDTO();

		return employees;
	}

	@Override
	public EmployeeListDTO editEmployee(EmployeeDTO employee) {
		EmployeeListDTO employees = new EmployeeListDTO();

		return employees;
	}

	@Override
	public EmployeeListDTO deleteEmployee(Integer empId) {
		EmployeeListDTO employees = new EmployeeListDTO();
		con = JdbcUtil.getHikariConnection();
		try {
			psmt = con.prepareStatement(
					"WITH deleted_row AS (DELETE FROM dummy_emp WHERE empno = ? RETURNING empno)SELECT * FROM dummy_emp WHERE empno NOT IN (SELECT empno FROM deleted_row);");
			psmt.setInt(1, empId);
			rs = psmt.executeQuery();
			while (rs.next()) {
				EmployeeDTO employee = new EmployeeDTO();
				employee.setEmpNo(rs.getInt("empno"));
				employee.setEmpName(rs.getString("ename"));
				employee.setEmpJob(rs.getString("job"));
				employee.setEmpMgr(rs.getInt("mgr"));
				employee.setEmpHireDate(rs.getDate("hiredate"));
				employee.setEmpSal(rs.getDouble("sal"));
				employee.setEmpCommission(rs.getDouble("comm"));
				employee.setEmpDeptno(rs.getInt("deptno"));
				employees.add(employee);
			}
			JdbcUtil.closeConnections(con, psmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employees;
	}

	@Override
	public EmployeeListDTO getEmployees() {
		EmployeeListDTO employees = new EmployeeListDTO();
		con = JdbcUtil.getHikariConnection();
		try {
			psmt = con.prepareStatement("select * from i213_employee");
			rs = psmt.executeQuery();
			while (rs.next()) {
				EmployeeDTO employee = new EmployeeDTO();
				employee.setEmpNo(rs.getInt("empno"));
				employee.setEmpName(rs.getString("ename"));
				employee.setEmpJob(rs.getString("job"));
				employee.setEmpMgr(rs.getInt("mgr"));
				employee.setEmpHireDate(rs.getDate("hiredate"));
				employee.setEmpSal(rs.getDouble("sal"));
				employee.setEmpCommission(rs.getDouble("comm"));
				employee.setEmpDeptno(rs.getInt("deptno"));
				employees.add(employee);
			}
			JdbcUtil.closeConnections(con, psmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employees;
	}
}
