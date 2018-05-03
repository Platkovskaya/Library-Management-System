package com.epam.library.dao.impl;

import com.epam.library.constants.LibraryConstants;
import com.epam.library.dao.BaseDAO;
import com.epam.library.domain.Book;
import com.epam.library.domain.Employee;
import com.epam.library.domain.Entity;
import com.epam.library.utils.ConnectionUtils;
import com.epam.library.utils.RequestUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements BaseDAO {

	@Override
	public void create(Entity entity) {

		Employee employee = (Employee) entity;

		String request = String.format(
				LibraryConstants.DataBase.Commands.Employee.ADD,
				employee.getName(),
				employee.getEmail(),
				employee.getDateOfBirth()
		);

		RequestUtils.sendUpdateRequest(request);
	}

	@Override
	public Entity read(int id) {

		String request = String.format(
				LibraryConstants.DataBase.Commands.Employee.SELECT_BY_ID,
				id
		);

		ResultSet resultSet = RequestUtils.sendQueryRequest(request);

		Employee employee = null;
		try {
			if (resultSet != null) {
				while (resultSet.next()) {
					employee = new Employee (
							resultSet.getInt("employee_id"),
							resultSet.getString("name"),
							resultSet.getString("email"),
							resultSet.getDate("date_of_birth")
					);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employee;
	}

	@Override
	public List readAll() {
		String request = LibraryConstants.DataBase.Commands.Employee.SELECT;

		ResultSet resultSet = RequestUtils.sendQueryRequest(request);

		List<Employee> employees = new ArrayList<>();
		try {
			if (resultSet != null) {
				while (resultSet.next()) {
					Employee employee = new Employee (
							resultSet.getInt("employee_id"),
							resultSet.getString("name"),
							resultSet.getString("email"),
							resultSet.getDate("date_of_birth")
					);
					employees.add(employee);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employees;
	}

	@Override
	public void update(Entity entity) {

		Employee employee = (Employee) entity;

		String request = String.format(
				LibraryConstants.DataBase.Commands.Employee.UPDATE,
				employee.getName(),
				employee.getEmail(),
				employee.getDateOfBirth(),
				employee.getId()
		);

		RequestUtils.sendUpdateRequest(request);
	}

	@Override
	public void delete(int id) {
		String request = String.format(
				LibraryConstants.DataBase.Commands.Employee.DELETE,
				id
		);

		RequestUtils.sendUpdateRequest(request);
	}

}