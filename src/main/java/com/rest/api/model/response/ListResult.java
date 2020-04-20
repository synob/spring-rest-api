package com.rest.api.model.response;

import java.util.List;

import lombok.Data;

@Data
public class ListResult<T> extends CommonResult {

    private List<T> list;
}