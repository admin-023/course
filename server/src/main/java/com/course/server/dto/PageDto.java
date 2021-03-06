package com.course.server.dto;

import java.util.List;

public class PageDto<T> {
    protected int page;//当前页码
    protected int size;//每页条数
    protected long total;//总数
    protected List<T> list;

    public int getPage() {
        return page;
    }


    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotal(long total) {
        return this.total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
    @Override
    public String toString() {
        return "PageDto{" +
                "page=" + page +
                ", size=" + size +
                ", total=" + total +
                ", list=" + list +
                '}';
    }
}
