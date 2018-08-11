package com.acfun.common.constant;

/**
 * Created by Tivon on 2017-12-20.
 * 文件上传限制常量
 */
public class FileLimit {

    public static final String[] EXCEL_ALLOW_UPLOAD_TYPES = { ".xls", ".xlsx" };

    public static final long EXCEL_MAX_UPLOAD_SIZE = 5 * 1024 * 1024L;

    public static final String[] FILE_UPLOAD_TYPES = { ".xls", ".xlsx", ".txt", ".pdf", ".doc", ".docx"};

    public static final long FILE_UPLOAD_SIZE = 10 * 1024 * 1024L;

    public static final int MAX_EXPROT_ROW = 3000;

}
