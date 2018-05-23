package com.coliseum.model.entities;

public class Hall implements Identified<Integer> {

    public static final String TABLE_NAME = "halls";
    public static final String ID_COLUMN = "id";
    public static final String COUNT_ROWS_COLUMN = "count_rows";
    public static final String COUNT_COLUMNS_COLUMN = "count_columns";
    public static final String NAME_COLUMN = "name";
    public static final String COUNT = "count";

    private Integer id;
    private Integer countRows;
    private Integer countColumns;
    private String name;

    public Hall() {
    }

    public Hall(Integer countRows, Integer countColumns, String name) {
        this.countRows = countRows;
        this.countColumns = countColumns;
        this.name = name;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCountRows() {
        return countRows;
    }

    public void setCountRows(Integer countRows) {
        this.countRows = countRows;
    }

    public Integer getCountColumns() {
        return countColumns;
    }

    public void setCountColumns(Integer countColumns) {
        this.countColumns = countColumns;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hall hall = (Hall) o;

        if (!id.equals(hall.id)) return false;
        if (!countRows.equals(hall.countRows)) return false;
        if (!countColumns.equals(hall.countColumns)) return false;
        return name.equals(hall.name);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + countRows.hashCode();
        result = 31 * result + countColumns.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Hall{" +
                "id=" + id +
                ", countRows=" + countRows +
                ", countColumns=" + countColumns +
                ", name='" + name + '\'' +
                '}';
    }

}
