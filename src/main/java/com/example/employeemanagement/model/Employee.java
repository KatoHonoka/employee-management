package com.example.employeemanagement.model;

public class Employee {
  /** ID */
  private Integer id;
  /** 名前 */
  private String name;
  /** 年齢 */
  private Integer age;
  /** 性別 */
  private String gender;
  /** 部署ID */
  private Integer department_id;
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public Integer getAge() {
    return age;
  }
  public void setAge(Integer age) {
    this.age = age;
  }
  public String getGender() {
    return gender;
  }
  public void setGender(String gender) {
    this.gender = gender;
  }
  public Integer getDepartment_id() {
    return department_id;
  }
  public void setDepartment_id(Integer department_id) {
    this.department_id = department_id;
  }
  @Override
  public String toString() {
    return "Employee [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + ", department_id="
        + department_id + "]";
  }
  
}
