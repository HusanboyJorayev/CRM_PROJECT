package com.company.securityroleconfiguration.service;

import com.company.securityroleconfiguration.dto.EmployeeDto;
import com.company.securityroleconfiguration.dto.ResponseDto;
import com.company.securityroleconfiguration.dto.SimpleCrud;
import com.company.securityroleconfiguration.module.Employee;
import com.company.securityroleconfiguration.repository.EmployeeRepository;
import com.company.securityroleconfiguration.service.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService implements SimpleCrud<Integer, EmployeeDto> {
    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;

    @Override
    public ResponseDto<EmployeeDto> create(EmployeeDto dto) {

        try {
            Employee employee = this.employeeMapper.toEntity(dto);
            employee.setCreatedAt(LocalDateTime.now());
            this.employeeRepository.save(employee);

            return ResponseDto.<EmployeeDto>builder()
                    .success(true)
                    .message("OK")
                    .data(this.employeeMapper.toDto(employee))
                    .build();

        } catch (Exception e) {

            return ResponseDto.<EmployeeDto>builder()
                    .code(-1)
                    .message("employee while ")
                    .build();
        }
    }

    @Override
    public ResponseDto<EmployeeDto> get(Integer id) {
        Optional<Employee> optional = this.employeeRepository.findByEmpIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {

            return ResponseDto.<EmployeeDto>builder()
                    .code(-1)
                    .message("Employee is not fond")
                    .build();
        }
        var employee = optional.get();
        return ResponseDto.<EmployeeDto>builder()
                .success(true)
                .message("Ok")
                .data(this.employeeMapper.toDto(employee))
                .build();
    }

    @Override
    public ResponseDto<EmployeeDto> update(EmployeeDto dto, Integer id) {

        try {
            Optional<Employee> optional = this.employeeRepository.findByEmpIdAndDeletedAtIsNull(id);
            if (optional.isEmpty()) {
                return ResponseDto.<EmployeeDto>builder()
                        .code(-1)
                        .message("Employee is not found")
                        .build();
            }
            var employee = optional.get();
            employee.setCreatedAt(LocalDateTime.now());
            this.employeeMapper.update(employee, dto);
            this.employeeRepository.save(employee);

            return ResponseDto.<EmployeeDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(this.employeeMapper.toDto(employee))
                    .build();
        } catch (Exception e) {

            return ResponseDto.<EmployeeDto>builder()
                    .code(-1)
                    .message("Employee while updating error")
                    .build();
        }
    }

    @Override
    public ResponseDto<EmployeeDto> delete(Integer id) {

        Optional<Employee> optional = this.employeeRepository.findByEmpIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {

            return ResponseDto.<EmployeeDto>builder()
                    .code(-1)
                    .message("Ok")
                    .build();
        }
        var employee = optional.get();
        employee.setUpdatedAt(LocalDateTime.now());
        this.employeeRepository.save(employee);
        return ResponseDto.<EmployeeDto>builder()
                .success(true)
                .message("Ok")
                .data(this.employeeMapper.toDto(employee))
                .build();
    }

    public ResponseDto<List<EmployeeDto>> getAll() {
        List<Employee> employee = this.employeeRepository.findAllByDeletedAtIsNull();
        if (employee.isEmpty()) {

            return ResponseDto.<List<EmployeeDto>>builder()
                    .code(-1)
                    .message("Employees are not found")
                    .build();
        }
        return ResponseDto.<List<EmployeeDto>>builder()
                .success(true)
                .message("Ok")
                .data(employee.stream().map(this.employeeMapper::toDto).toList())
                .build();
    }
}
