package com.jumia.model;

import java.util.List;

public class PaginatedSearchResult<T> {

	private List<T> data;
	private Boolean hasNext;
	
	public PaginatedSearchResult(List<T> data, Boolean hasNext) {
		super();
		this.data = data;
		this.hasNext = hasNext;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public Boolean getHasNext() {
		return hasNext;
	}
	public void setHasNext(Boolean hasNext) {
		this.hasNext = hasNext;
	}
}