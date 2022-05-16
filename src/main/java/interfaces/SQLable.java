package interfaces;

public interface SQLable {
    public String getSQLInsert();

    public String getSQLUpdate();

    public String getSQLDelete();

    public String getSQLSelect();
}
