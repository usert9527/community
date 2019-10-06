package cn.user9527.mycommunity.util;
public class PageUtil {
	/*1，每页显示多少条		pageSize = 5	自己定义的
	2，当前是第几页		nowPage			自己选的
	3，总共有多少条记录 	rowCount =100;	从数据库中查询出来的
	4，总共有多少页		pageCount = rowCount%pageSize==0?rowCount/pageSize:rowCount/pageSize+1
	5，上一页			nowPage-1
	6，下一页			nowPage+1
	7，首页				1
	8，尾页				pageCount
	9，起始行			startRow=(nowPage-1)*pageSize*/
	private int pageSize;
	private int nowPage;
	private int rowCount;
	private int pageCount;
	private int prevPage;
	private int nextPage;
	private int firstPage=1;
	private int endPage;
	private int rowStart;
	
	public PageUtil(){
		
	}
	public PageUtil(int pageSize ,int nowPage,int rowCount){
		this.pageSize = pageSize;
		this.nowPage = nowPage;
		this.rowCount = rowCount;
		this.pageCount = rowCount%pageSize==0?rowCount/pageSize:rowCount/pageSize+1;
		if(nowPage==1){
			this.prevPage = 1;
		}else{
			this.prevPage = nowPage-1;
		}
		
		this.nextPage = nowPage+1;
		this.endPage = pageCount;
		this.rowStart=(nowPage-1)*pageSize;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getPrevPage() {
		return prevPage;
	}
	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getFirstPage() {
		return firstPage;
	}
	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getRowStart() {
		return rowStart;
	}
	public void setRowStart(int rowStart) {
		this.rowStart = rowStart;
	}
	
}
