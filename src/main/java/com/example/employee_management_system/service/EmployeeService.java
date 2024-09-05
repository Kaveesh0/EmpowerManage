package com.example.employee_management_system.service;

import com.example.employee_management_system.entity.Employee;
import com.example.employee_management_system.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    public Employee postEmployee(Employee employee){
        return employeeRepository.save(employee);
    }
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }
    public  void  deleteEmployee(Long id ){
        if (!employeeRepository.existsById(id)){
            throw new EntityNotFoundException("Emploee with ID "+ id + "not found");
        }
        employeeRepository.deleteById((id));
    }
    public  Employee getEmployeeById(Long id){
        return employeeRepository.findById(id).orElse(null);
     }
    public  Employee updateEmployee(Long id, Employee employee){
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()){
            Employee existinEmployee = optionalEmployee.get();
            existinEmployee.setEmail(employee.getEmail());
            existinEmployee.setName(employee.getName());
            existinEmployee.setPhone(employee.getPhone());
            existinEmployee.setDepartment(employee.getDepartment());
            return employeeRepository.save(existinEmployee);
        }
        return null;
    }
}
