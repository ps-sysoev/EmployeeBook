package service;

import dto.EmployeeDto;
import lombok.RequiredArgsConstructor;
import mapper.EmployeeMapper;
import model.Employee;
import repository.Repository;
import sort.EmployeeComparatorAsc;
import sort.EmployeeComparatorDesc;
import sort.Ordered;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class EmployeeService {
    private final Repository<Long, Employee> employeeMemRepository;
    private final EmployeeMapper employeeMapper;

    public boolean save(EmployeeDto employee) {
        Employee result = employeeMemRepository.save(employeeMapper.fromEmployeeDtoToEmployeeEntity(employee));

        return result.getId() != 0;
    }

    public boolean update(long id, EmployeeDto employee) {
        return employeeMemRepository.update(id, employeeMapper.fromEmployeeDtoToEmployeeEntity(employee));
    }

    public boolean delete(long id) {
        return employeeMemRepository.delete(id);
    }

    public Optional<EmployeeDto> findById(Long id) {
        return employeeMemRepository.findById(id)
                .map(employeeMapper::fromEmployeeToEmployeeDto);
    }

    public List<EmployeeDto> findAll(Ordered ordered) {
        List<EmployeeDto> employees = getMappingData(employeeMemRepository.findAll().stream());

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
