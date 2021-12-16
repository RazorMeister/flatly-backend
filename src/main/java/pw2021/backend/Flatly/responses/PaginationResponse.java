package pw2021.backend.Flatly.responses;

public class PaginationResponse <T> {
    private T data;
    private PaginationData pagination;

    public PaginationResponse(T data, PaginationData pagination) {
        this.data = data;
        this.pagination = pagination;
    }

    public T getData() {
        return data;
    }

    public PaginationData getPagination() {
        return pagination;
    }
}


