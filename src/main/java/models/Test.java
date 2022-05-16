package models;


import interfaces.SQLable;

public class Test implements SQLable {

  private long test;


  public long getTest() {
    return test;
  }

  public void setTest(long test) {
    this.test = test;
  }


  @Override
  public String getSQLInsert() {
    return "INSERT INTO `test` (`test`) VALUES (" + test + ");";
  }

  @Override
  public String getSQLUpdate() {
    return "UPDATE `test` SET `test` = " + test + " WHERE `test`.`test` = " + test + ";";
  }

  @Override
  public String getSQLDelete() {
    return "DELETE FROM `test` WHERE `test`.`test` = " + test + ";";
  }

  @Override
  public String getSQLSelect() {
    return "SELECT * FROM `test` WHERE `test`.`test` = " + test + ";";
  }
}
