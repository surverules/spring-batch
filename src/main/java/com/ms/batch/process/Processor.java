package com.ms.batch.process;

import com.ms.batch.model.Employee;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Processor implements ItemProcessor<String, Employee> {

    @Override
    public Employee process(String s) throws Exception {
        Employee employee = new Employee();
        employee.setFname(s);
        employee.setId(new Random(5).nextInt());
        employee.setLname(s);
        return employee;
    }
}

