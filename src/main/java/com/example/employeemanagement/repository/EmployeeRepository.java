package com.example.employeemanagement.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.employeemanagement.form.SearchForm;
import com.example.employeemanagement.model.Employee;

@Repository
public class EmployeeRepository {
  @Autowired
  private NamedParameterJdbcTemplate template;

  private static final Logger logger = LoggerFactory.getLogger(EmployeeRepository.class);
  private static final String FIND_BY_ID_SQL = """
    SELECT
      id, name, age, gender, department_id
    FROM
      employees
    WHERE
      id = :id;
    """;

  private static final String FIND_ALL_SQL = """
    SELECT
      id, name, age, gender, department_id
    FROM
      employees
    ORDER BY
      age;
  """;
  private static final String INSERT_SQL = """
    INSERT INTO employees
    (name, age, gender, department_id)
    VALUES
    (:name, :age, :gender, :department_id);
  """;
  private static final String UPDATE_SQL = """
    UPDATE employees
    SET
    (name = :name, age = :age, gender = :gender, department_id = :department_id)
    WHERE
    id = :id;
  """;
  private static final String DELETE_SQL = """
    DELETE
    FROM
      employees
    WHERE
      id = :id;
  """;
  private static final String SEARCH_SQL = """
    SELECT
      id, name, age, gender, department_id
    FROM
      employees 
  """;
  private static final RowMapper<Employee> EMPLOYEE_ROWMAPPER = (rs,rowNum) ->{
    Employee employee = new Employee();
    employee.setId(rs.getInt("id"));
    employee.setName(rs.getString("name"));
    employee.setAge(rs.getInt("age"));
    employee.setGender(rs.getString("gender"));
    employee.setDepartment_id(rs.getInt("department_id"));

    return employee;
  };

  

  /**
   * 従業員を一人取得.
   * 
   * @param id 従業員ID.
   * @return 指定されたidを持つ従業員.
   *         従業員が存在しない場合はnull.
   */
  public Employee findById(Integer id){
    SqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
    Employee employee = template.queryForObject(FIND_BY_ID_SQL, params, EMPLOYEE_ROWMAPPER);
    return employee;
  }

  /**
   * 従業員の一覧を取得. 
   *
   * @return 全従業員のリスト.
   *         存在しない場合は空のリスト.
   */
  public List<Employee> findAll(){
    List<Employee> list = template.query(FIND_ALL_SQL, EMPLOYEE_ROWMAPPER);
    return list;
  }

  /**
   * 従業員を登録する.
   * 
   * @param 従業員情報
   * @return 登録された従業員情報.
   */
  public Employee save(Employee employee){
    SqlParameterSource params = new BeanPropertySqlParameterSource(employee);
    if(employee.getId() == null){
      KeyHolder keyHolder = new GeneratedKeyHolder();
      String[] keyColumnNames = {"id"};
      template.update(INSERT_SQL, params, keyHolder, keyColumnNames);
      employee.setId(keyHolder.getKey().intValue());
    } else {
      template.update(UPDATE_SQL, params);
    }
    return employee;
  }

  /**
   * 従業員を削除する.
   * 
   * @param 従業員ID
   * @return 削除された件数
   */
  public Integer deleteById(Integer id){
    SqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
    return template.update(DELETE_SQL, params);
  }

  /**
   * 与えられた条件で従業員を検索し、結果のリストを返す.
   * 
   * @param condition 検索条件を保持するオブジェクト.
   * @return 該当する全従業員のリスト.
   *         存在しない場合は空のリスト.
   */
  public List<Employee> search(SearchForm form){
    StringBuilder builder = new StringBuilder();
    builder.append(SEARCH_SQL);
    builder.append("Where 1 = 1");

    // System.out.println("formの内容" + form);
    logger.info("formの内容" + form.toString());
    

    if(!form.getName().equals("")){
      logger.info("名前検索の実行");
      builder.append(" AND ");
      builder.append(" name LIKE ");
      builder.append(" '%" + form.getName() + "%' ");
    }

    if (!form.getGender().equals("その他")) {
      logger.info("性別検索の実行");
      builder.append(" AND ");
      builder.append(" gender = ");
      builder.append("'" + form.getGender()+ "'");
    }

    if (form.getDepartment_id_list() != null && form.getDepartment_id_list().size() > 0) {
      logger.info("部署検索の実行");
      builder.append(" AND ");
      builder.append("department_id IN ( ");
      for(int i = 0; i < form.getDepartment_id_list().size(); i++ ){
        builder.append(form.getDepartment_id_list().get(i));
        if(i + 1 < form.getDepartment_id_list().size()){
          builder.append(" , ");
        }
      }
      builder.append(")");
    }

    if (form.getFrom_age() != null) {
      builder.append(" AND ");
      builder.append(" age >=  " + form.getFrom_age() + "");
    }

    if (form.getEnd_age() != null) {
      builder.append(" AND ");
      builder.append(" age <=  " + form.getEnd_age() + "");
    }
    

    builder.append(";");
    String search_query = builder.toString();
    
    SqlParameterSource params = new MapSqlParameterSource();

    // System.out.println(search_query);
    logger.info("クエリの内容" + search_query);
    
    List<Employee> list = template.query(search_query, params, EMPLOYEE_ROWMAPPER);

    return list;
  }

}
