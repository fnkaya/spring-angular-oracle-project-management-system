package com.fnkaya.projectmanagement.common.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Getter
@Setter
public class Page<T> {

    private int page;

    private int size;

    private int totalElements;

    private List<T> content;

    private Page() {}

    public static <T> Page<T> of(int page, int size, int totalElement, List<T> content){
        Page<T> nPage = new Page<T>();

        nPage.setPage(page);
        nPage.setSize(size);
        nPage.setTotalElements(totalElement);
        nPage.setContent(content);

        return nPage;
    }

    public static <T> Page<T> of(Pageable pageable, int totalElement, List<T> content){
        Page<T> nPage = new Page<T>();

        nPage.setPage(pageable.getPageNumber());
        nPage.setSize(pageable.getPageSize());
        nPage.setTotalElements(totalElement);
        nPage.setContent(content);

        return nPage;
    }
}
