package com.tej.Employee.mapper;

import com.tej.Employee.dto.EmployeeDto;
import com.tej.Employee.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {LocalDateMapper.class})
public interface EmployeeMapper {

    EmployeeDto EmployeeDaoToDto(Employee employee);

    Employee EmployeeDtoToDao(EmployeeDto employeeDto);

    @Mapping(target = "id", ignore = true)
    void updateEmployeeFromDto(EmployeeDto dto, @MappingTarget Employee employee);
}
