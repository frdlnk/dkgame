package modelo;

public abstract class DBModel {
	public abstract String getTableSchema();

	public String getTable() {
		return getClass().getSimpleName();
	}
}
