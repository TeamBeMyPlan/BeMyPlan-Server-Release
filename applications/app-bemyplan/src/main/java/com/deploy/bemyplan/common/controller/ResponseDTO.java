package com.deploy.bemyplan.common.controller;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseDTO {

    private String responseMessage;

    private ResponseDTO(final String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public static ResponseDTO of(final String response){
        return new ResponseDTO(response);
    }
}
