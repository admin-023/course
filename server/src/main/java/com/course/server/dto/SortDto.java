package com.course.server.dto;

public class SortDto {

    /*id*/

    private String id;

    /*旧排序*/

    private int oldSort;

    /*新排序*/

    private int newSort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getOldSort() {
        return oldSort;
    }

    public void setOldSort(int oldSort) {
        this.oldSort = oldSort;
    }

    public int getNewSort() {
        return newSort;
    }

    public void setNewSort(int newSort) {
        this.newSort = newSort;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SortDto{");
        sb.append("id='").append(id).append('\'');
        sb.append(", oldSort=").append(oldSort);
        sb.append(", newSort=").append(newSort);
        sb.append('}');
        return sb.toString();
    }
}
