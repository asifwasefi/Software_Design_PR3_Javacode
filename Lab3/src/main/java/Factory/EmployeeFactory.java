package Factory;

import employee.Employee;

public class EmployeeFactory {

    public Employee getEmployee(String name, String function)
    {
        if (function == "Programmer" || function == "CustomerService" || function == "Manager")
            return new Employee(name, function);
        else
            return null;
    }
}
