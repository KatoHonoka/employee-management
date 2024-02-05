package com.example.employeemanagement.model;

import org.springframework.stereotype.Component;

@Component
public class Condition {
  private String column;
  private String operand;
  private String args;
  private String sql_query = "";
  
  public String getColumn() {
    return column;
  }
  public void setColumn(String column) {
    this.column = column;
  }
  public String getArgs() {
    return args;
  }
  public void setArgs(String args) {
    this.args = args;
  }
  public String getOperand() {
    return operand;
  }
  public void setOperand(String operand) {
    this.operand = operand;
  }
  public String getSql_query() {
    return sql_query;
  }
  public void setSql_query(String sql_query) {
    this.sql_query = sql_query;
  }
  @Override
  public String toString() {
    return "Column [column=" + column + ", args=" + args + "]";
  }
}
