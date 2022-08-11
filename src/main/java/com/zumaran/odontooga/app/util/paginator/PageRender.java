package com.zumaran.odontooga.app.util.paginator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageRender<T> {
    
    private String url;

    private Page<T> page;

    private int totalPages;

    private int nByPage;

    private int currentPage;

    private List<PageItem> pages;

    public PageRender(String url, Page<T> page) {
        this.url = url;
        this.page = page;
        this.pages = new ArrayList<PageItem>();

        nByPage = 5;
        totalPages = page.getTotalPages();
        // COUNT START AT {0}
        currentPage = page.getNumber()+1;

        int from, to;
        if (totalPages <= nByPage) {
            from = 1;
            to = totalPages;
        } else {
            if (currentPage <= nByPage / 2) {
                from = 1;
                to = nByPage;
            } else if(currentPage >= totalPages - nByPage / 2) {
                from = totalPages - nByPage + 1;
                to = nByPage;
            } else {
                from = currentPage - nByPage / 2;
                to = nByPage;
            }
        }

        for(int i = 0; i< to; i++) { pages.add(new PageItem(from + i, currentPage == from + i));}

    }

    public String getUrl() {
        return url;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public List<PageItem> getPages() {
        return pages;
    }

    public boolean isFirst(){ return page.isFirst(); }

    public boolean isLast(){ return page.isLast(); }

    public boolean isHasNext(){ return page.hasNext(); }

    public boolean isHasPrevious(){ return page.hasPrevious(); }


}
