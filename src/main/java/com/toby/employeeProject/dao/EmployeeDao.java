package com.toby.employeeProject.dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.toby.employeeProject.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class EmployeeDao implements EmployeeDaoIntf{
	@Autowired	
	private EntityManager entityManager;
	
	@Override
	@Transactional
	public Employee saveEmployee(Employee employee) {
		entityManager.merge(employee);
		return employee;
		
	}

	@Override
	@Transactional
	public Employee getEmployeeById(int id) {
		Employee employee = entityManager.find(Employee.class, id);
		return employee;
	}

	@Override
	@Transactional
	public ArrayList<Employee> getAllEmployees() {
		ArrayList<Employee> employees = (ArrayList<Employee>) entityManager.createQuery("from Employee",Employee.class).getResultList();
		return employees;
	}

	@Override
	@Transactional
	public String deleteEmployee(int id) {
		Query query = entityManager.createQuery("delete from Employee where id =: employeeId");
		query.setParameter("employeeId", id);
		query.executeUpdate();
		return "Success";
	}
	

}
