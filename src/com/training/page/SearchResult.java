package com.training.page;

import java.util.List;

public class SearchResult<T> {
	private List<T> result;// 对象集合
	private Pagination pagination;// 分页数据

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

}
