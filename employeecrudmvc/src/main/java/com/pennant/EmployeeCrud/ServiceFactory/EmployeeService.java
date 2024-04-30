package com.pennant.EmployeeCrud.ServiceFactory;

import com.pennant.EmployeeCrud.Service.EmployeeCrudService;
import com.pennant.EmployeeCrud.Service.EmployeeCrudServiceImpl;

public class EmployeeService {

	private static EmployeeCrudService service=null;
	private EmployeeService()
	{
		
	}
	public static EmployeeCrudService getService()
	{
		if(service!=null)
		{
			service= new EmployeeCrudServiceImpl();
		}
		return service;
	}

}
