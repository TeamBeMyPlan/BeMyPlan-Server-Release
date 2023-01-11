package com.deploy.bemyplan.core.service;

import com.deploy.bemyplan.core.model.CommonResult;
import com.deploy.bemyplan.core.model.SingleResult;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {
    public <T> CommonResult getResult(T data) {
        return getSingleResult(data);
    }

    private <T> CommonResult getSingleResult(T data) {
        SingleResult<T> result = new SingleResult<>();
        result.setData(data);
        return result;
    }
}
