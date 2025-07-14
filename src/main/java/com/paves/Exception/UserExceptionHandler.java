package com.paves.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserExceptionHandler extends RuntimeException{
    private String exMsg;
}
