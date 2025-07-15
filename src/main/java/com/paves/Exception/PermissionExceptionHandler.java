package com.paves.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionExceptionHandler extends RuntimeException{
    private String exMsg;
}
