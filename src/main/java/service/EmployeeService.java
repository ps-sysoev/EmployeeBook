package service;

import dto.EmployeeDto;
import mapper.EmployeeMapper;
import model.Employee;
import repository.impl.EmployeeMemRepository;
import sort.EmployeeComparatorAsc;
import sort.EmployeeComparatorDesc;
import sort.Ordered;
import util.Constants;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmployeeService {
    private final EmployeeMemRepository employeeMemRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeMemRepository employeeMemRepository, EmployeeMapper employeeMapper) {
        this.employeeMemRepository = employeeMemRepository;
        this.employeeMapper = employeeMapper;

        employeeMemRepository.init();
    }

    public boolean save(Employee employee) {
        Employee result = employeeMemRepository.save(employee);

        return result.getId() != Constants.INCORRECT_ID;
    }

    public boolean update(long id, Employee employee) {
        return employeeMemRepository.update(id, employee);
    }

    public boolean delete(long id) {
        return employeeMemRepository.delete(id);
    }

    public Optional<EmployeeDto> findById(Long id) {
        return employeeMemRepository.findById(id)
                .map(employeeMapper::fromEmployeeToEmployeeDto);
    }

    public List<EmployeeDto> findAll(Ordered ordered) {
        List<EmployeeDto>  employees = getMappingData(employeeMemRepository.findAll().stream());

        switch (ordered) {
            case ASC: {
                employees.sort(new EmployeeComparatorAsc());

                return employees;
            }
            case DESC: {
                employees.sort(new EmployeeComparatorDesc());

                return employees;
            }
            case DEFAULT: {
                return employees;
            }
        }

        return List.of();
    }

    public List<EmployeeDto> findAllByName(String name) {
        return getMappingData(employeeMemRepository.findByName(name).stream());
    }

    public List<EmployeeDto> findAllByIntervalDate(String begin, String end) {
        return getMappingData(employeeMemRepository.findByCreatedDateInterval(
                employeeMapper.parseDate(begin),
                employeeMapper.parseDate(end)
        ).stream());
    }

    private List<EmployeeDto> getMappingData(Stream<Employee> employees) {
        return employees
                .map(employeeMapper::fromEmployeeToEmployeeDto)
                .collect(Collectors.toList());
    }
}
