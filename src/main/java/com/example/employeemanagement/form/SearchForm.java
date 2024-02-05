package com.example.employeemanagement.form;

import java.util.List;

public class SearchForm {
    /** 名前 */
    private String name;
    /** 始まりの年齢 */
    private Integer from_age;
    /** 終わりの年齢 */
    private Integer end_age;
    /** 性別 */
    private String gender;
    /** 部署ID */
    private List<Integer> department_id_list;
    public String getName() {
      return name;
    }
    public void setName(String name) {
      this.name = name;
    }
    public Integer getFrom_age() {
      return from_age;
    }
    public void setFrom_age(Integer from_age) {
      this.from_age = from_age;
    }
    public Integer getEnd_age() {
      return end_age;
    }
    public void setEnd_age(Integer end_age) {
      this.end_age = end_age;
    }
    public String getGender() {
      return gender;
    }
    public void setGender(String gender) {
      this.gender = gender;
    }
    public List<Integer> getDepartment_id_list() {
      return department_id_list;
    }
    public void setDepartment_id_list(List<Integer> department_id_list) {
      this.department_id_list = department_id_list;
    }
    @Override
    public String toString() {
      return "SearchForm [name=" + name + ", from_age=" + from_age + ", end_age=" + end_age + ", gender="
          + gender + ", department_id_list=" + department_id_list + "]";
    }

    
}
