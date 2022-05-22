package models;

import interfaces.SQLable;
import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class Log implements SQLable {

  private long idLog;
  private long idAgent;
  private String action;
  private String information;

  @Override
  public String getSQLInsert() {
    return "INSERT INTO logs (idagent, action, information) VALUES (" + idAgent + ", '" + action + "', " + information + ")";
  }

  @Override
  public String getSQLUpdate() {
    return null;
  }

  @Override
  public String getSQLDelete() {
    return null;
  }

  @Override
  public String getSQLSelect() {
    return null;
  }
}
