package com.example.employeemanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employeemanagement.form.SearchForm;
import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.repository.EmployeeRepository;

@Service
public class EmployeeService {
  @Autowired
  private EmployeeRepository repository;

  /**
   * 従業員を一人取得.
   * 
   * @param id 従業員ID
   * @return 指定されたidを持つ従業員.
   *         従業員が存在しない場合はnull.
   */
  public Employee findById(Integer id){
    return repository.findById(id);
  }

  /**
   * 従業員の一覧を取得. 
   *
   * @return 全従業員のリスト.
   *         存在しない場合は空のリスト.
   */
  public List<Employee> findAll(){
    return repository.findAll();
  }

  /**
   * 従業員を登録する.
   * 
   * @param 従業員情報
   * @return 登録された従業員情報
   */
  public Employee save(Employee employee){
    return repository.save(employee);
  }

  /**
   * 従業員を削除する.
   * 
   * @param 従業員ID
   * @return 削除された件数
   */
  public Integer deleteById(Integer id){
    return repository.deleteById(id);
  }

  public List<Employee> search(SearchForm form){
    return repository.search(form);
  }



}
