package com.second.mall.modules.common.entity;

import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName SearchBean
 * @Author icy
 * @Data 2020/12/29 15:11
 * @Version v1.0
 **/
public class SearchBean {

    public final static int DEFAULT_CURRENT_PAGE = 1;
    public final static int DEFAULT_PAGE_SIZE = 5;

    private int currentPage;
    private int pageSize;
    private String keyWord;
    private String orderBy;
    private String direction;

    public void initSearchBean() {
        if (this != null) {
            this.setCurrentPage(this.getCurrentPage() == 0 ? DEFAULT_CURRENT_PAGE : this.getCurrentPage());
            this.setPageSize(this.getPageSize() == 0 ? DEFAULT_PAGE_SIZE : this.getPageSize());
        }
        if (StringUtils.isNotBlank(this.keyWord)) {
            this.keyWord.replaceAll("'", "");
        }
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

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getOrder() {
        return orderBy;
    }

    public void setOrder(String order) {
        this.orderBy = orderBy;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }


}
