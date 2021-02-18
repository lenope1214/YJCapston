package com.cuteblog.bbs4.service;
import lombok.Data;

@Data
public class Criteria {

    private int page; // 선택한 페이지
    private int perPageNum; // 선택한 페이지에 보여줄 갯수
    private int rowStart; // DB에 검색할 start rownum
    private int rowEnd; // DB에 검색할 end rownum
    private long bno; // 게시글 번호

    public Criteria() {
        this.page = 1;
        this.perPageNum = 10;
    }

    public void setPage(int page) {
        if (page <= 0) {
            this.page = 1;
            return;
        }
        this.page = page;
    }

    public void setPerPageNum(int perPageNum) {
        if (perPageNum <= 0 || perPageNum > 100) {
            this.perPageNum = 10;
            return;
        }
        this.perPageNum = perPageNum;
    }

    public void setBno(long bno){
        this.bno = bno;
    }
    public int getPage() {
        return page;
    }

    public int getPageStart() {
        return (this.page - 1) * perPageNum;
    }

    public int getPerPageNum() {
        return this.perPageNum;
    }

    public int getRowStart() {
        rowStart = ((page - 1) * perPageNum) + 1;
        return rowStart;
    }

    public int getRowEnd() {
        rowEnd = rowStart + perPageNum - 1;
        return rowEnd;
    }

    public long getBno(){
        return bno;
    }
}