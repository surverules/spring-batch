package com.ms.batch.process;

import com.ms.batch.model.Employee;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Writer implements ItemWriter<Employee> {

    @Override
    public void write(List<? extends Employee> list) throws Exception {

        for(Employee employee: list){
            System.out.println("Employee is : " + employee);
        }
    }
}
