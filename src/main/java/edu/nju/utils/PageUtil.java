package edu.nju.utils;

import java.io.Serializable;


public class PageUtil implements Serializable {
	
	private static final long serialVersionUID = -8315239776914139304L;
	private int currentPage=1;//当前页，默认第一页
	private int pageSize=CommonVariable.PAGESIZE;//页大小
	private int start;//开始位置
	private Long counts;//数据库总记录数
	private int totalPage;//由counts计算最大页数
	private int previous;//上一页页码
	private int next;//下一页页码
	
	/**
	 * 
	 * @param currentPage 当前页
	 * @param counts 数据库总记录数
	 */
	public PageUtil(int currentPage,Long counts){
		this.currentPage=currentPage;
		this.counts = counts;
		this.totalPage=(int) ((counts+pageSize-1)/pageSize);
		this.start=pageSize*(currentPage-1);
		
		if (currentPage==1) {
			this.previous = 1;
		} else {
			this.previous = currentPage-1;
		}
		if (currentPage==totalPage) {
			this.next=totalPage;
		} else {
			this.next=currentPage+1;
		}
	}
	
	/**
	 * 
	 * @param currentPage 当前页
	 * @param counts 数据库总记录数
	 * @param pageSize 页大小
	 */
	public PageUtil(int currentPage,Long counts,int pageSize){
		this.currentPage=currentPage;
		this.counts = counts;
		this.pageSize = pageSize;
		this.totalPage=(int) ((counts+pageSize-1)/pageSize);
		this.start=pageSize*(currentPage-1);
		if (currentPage==1) {
			this.previous = 1;
		} else {
			this.previous = currentPage-1;
		}
		if (currentPage==totalPage) {
			this.next=totalPage;
		} else {
			this.next=currentPage+1;
		}
	}
	
	public int getTotalPage() {
		return totalPage;
	}
	
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Long getCounts() {
		return counts;
	}

	public void setCounts(Long counts) {
		this.counts = counts;
	}

	public int getPrevious() {
		return previous;
	}

	public void setPrevious(int previous) {
		this.previous = previous;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}
	
}
