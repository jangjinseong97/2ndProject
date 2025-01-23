package com.green.jobdone.user.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class UserPwPatchRes {
    private String message;
    private int result;
}
