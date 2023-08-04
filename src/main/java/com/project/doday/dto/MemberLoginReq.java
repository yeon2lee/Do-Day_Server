package com.project.doday.dto;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberLoginReq {
    private String userId;
    private String password;
}
