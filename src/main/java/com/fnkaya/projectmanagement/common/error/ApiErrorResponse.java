package com.fnkaya.projectmanagement.common.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErrorResponse {

    private Integer status;

    private String message;

    private String path;

    private LocalDateTime time = LocalDateTime.now();

    private Map<String, String> validationErrors;

    public ApiErrorResponse(Integer status, String message, String path){
        this.status = status;
        this.message = message;
        this.path = path;
    }
}
