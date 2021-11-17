package cn.ymh.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T    data;

    public CommonResult(Integer code,String message){
        this(code,message,null);    //调用的是所有参数@AllArgsConstructor
    }
}
