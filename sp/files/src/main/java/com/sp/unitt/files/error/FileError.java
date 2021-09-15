package com.sp.unitt.files.error;

public class FileError extends RuntimeException{

    private int code;
    public FileError(int code, String msg){
        super(msg);
        this.code = code;
    }

    public ErrorMessage getErrorMessage(){
        return new ErrorMessage(code, getMessage());
    }


}
