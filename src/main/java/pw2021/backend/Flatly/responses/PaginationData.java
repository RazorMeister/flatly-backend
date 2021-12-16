package pw2021.backend.Flatly.responses;

import org.springframework.data.domain.Page;

public class PaginationData {
    private int page;
    private long totalRecords;
    private int totalPages;
    private int recordsOnPage;

    public PaginationData(Page<?> page) {
        this.page = page.getNumber() + 1;
        this.totalRecords = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.recordsOnPage = page.getNumberOfElements();
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

    public int getRecordsOnPage() {
        return recordsOnPage;
    }

    public void setRecordsOnPage(int recordsOnPage) {
        this.recordsOnPage = recordsOnPage;
    }
}
