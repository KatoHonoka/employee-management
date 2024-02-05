package com.example.employeemanagement.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.example.employeemanagement.model.Department;

public class DepartmentRepository {
  @Autowired
  private NamedParameterJdbcTemplate template;

  private static final String FIND_ALL_SQL = """
    SELECT
      id, name
    FROM
      departments
    ORDER BY
      id;
  """;

  private static final RowMapper <Department> DEPARTMENT_ROWMAPPER = (rs,rowNum) ->{
    Department department = new Department();
    department.setId(rs.getInt("id"));
    department.setName(rs.getString("name"));

    return department;
  };


  /**
   * 部署の一覧を取得. 
   *
   * @return 全従業員のリスト.
   *         存在しない場合は空のリスト.
   */
  public List<Department> findAll(){
    List<Department> list = template.query(FIND_ALL_SQL, DEPARTMENT_ROWMAPPER);
    return list;
  }
}
