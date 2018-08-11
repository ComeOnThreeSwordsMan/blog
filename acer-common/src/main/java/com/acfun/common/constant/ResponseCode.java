package com.acfun.common.constant;

/**
 * 响应码枚举对象。
 *
 * @author winston
 *         2017/7/27/027 16:35
 */
public enum ResponseCode {
    //通用
    OK(200, "OK"),
    TOKEN_ERROR(401,"登录令牌失效"),
    UNAUTHORIZED(403, "你没有此操作或资源的权限，请联系管理员授权"),
    BAD_SQL_GRAMMAR_EXCEPTION(-99,"SQL异常"),
    PARAM_VALID_FAIL(-100, "参数校验失败"),
    SYSTEM_ERROR(-101, "很抱歉，出现异常，请联系系统管理员解决"),
    DATA_NOT_EXIST(-102,"数据不存在"),
    FILE_DOWNLOAD_ERROR(-103,"文件下载失败"),
    FILE_UPLOAD_ERROR(-104,"文件上传失败"),
    FILE_TYPE_ERROR(-105,"文件类型不支持"),
    FILE_SIZE_ERROR(-106,"文件大小超出限制"),
    UPDATE_ERROR(-107,"更新失败，请刷新重试"),
    INSERT_ERROR(-108,"新增失败"),
    DELETE_ERROR(-109,"数据删除失败，请刷新重试"),
    FILE_IMPORT_ERROR(-110,"文件导入失败!"),
    FILE_IMPORT_SIZE_ERROR(-111,"文件导入条数超出范围"),
    EXCEL_FILE_EXPORT_ERROR(-112,"excel文件导出失败，没有需要导出的数据或出现异常"),
    INTERFACE_DATA_NOT_ALLOW_DELETE(-113,"接口数据禁止删除"),
    INTERFACE_DATA_NOT_ALLOW_UPDATE(-114,"接口数据禁止修改"),
    DATA_IS_USERD_NOT_ALLOW_DELETE(-115,"数据被引用禁止删除"),
    DATA_IS_USERD_NOT_ALLOW_UPDATE(-116,"数据被引用禁止修改"),
    SUBMIT_ERROR(-117,"提交失败，请刷新重试"),
    WITHDRAW_SUBMIT_ERROR(-118,"撤回提交失败,请刷新重试"),
    LIQUIDATION_ERROR(-119,"清算失败"),
    SAVE_ERROR(-120,"保存失败"),
    NUM_EXIST_ERROR(-121,"编码已存在"),
    NOT_SUBMINT_CAN_NOT_REVOKE_ERROR(-122,"该查询期间数据未提交，无需撤回"),
    ALREADY_SUBMIT_CAN_NOT_EDIT_ERROR(-123,"当期数据已经提交、禁止操作"),
    ASSIGN_ERROR(-124,"分配失败"),
    DATA_ALREADY_ERROR(-125,"数据状态已变更，务重复操作"),
    LAND_PRICECOLLECTION_INSERT_ERROR(-126,"新增失败，可能包含重复数据"),
    DATA_EXIST_ERROR(-126,"数据已存在，禁止重复添加"),
    DICT_NOT_EXIST_ERROR(-126,"基础数据前期认证相符且本期申报抵扣不存在，无法添加"),
    ACCOUNT_NOT_SUBMIT_ERROR(-127,"前置台账没有提交，无法操作"),
    ACCOUNT_REVOKE_ERROR(-128,"后续台账已提交，操作失败"),
    ACCOUNT_SUBJECT_ERROR(-129,"前置台账未提交，操作失败"),
    DELETE_ERROR_HAS_CHILD(-130,"删除失败，请先删除下级节点"),
    SAVE_CAN_NOT(-131,"该数据无法满足条件，无法保存"),
    DATA_IS_CHANGE(-132,"该数据已经变更，请刷新重试"),
    EXPORT_OPERATIONING_ERROR(-133,"导出失败，其他用户正在导出，请稍后重试"),
    TAX_SUBJECT_EMPTY_ERROR(-134,"纳税主体不能为空"),
    NO_DATA_ERROR(-135,"未识别到数据，请检查文件"),
    TEMPLATE_NOT_EXIST(-136,"文件不存在，请联系系统管理员"),
    DO_ERROR(-137,"操作失败"),
    LOGIN_LOCK(-138,"登录失败，您的账号已经被锁定"),
    NOT_ALLOW_OPT_DEFAULT_ADMINISTRATORS(-139,"禁止操作系统预设管理员"),
    REVOKE_ERROR_TAX_DECLARATION_SUBMIT(-140,"撤回提交失败，纳税申报表已经提交"),
    DATA_SUBMIT_NOT_ALLOW_EDIT(-141,"期初数据已经提交，禁止编辑"),



    CLIENT_NON_EXIST(-10000, "账号不存在"),
    TAXABLE_PROJECT_NUM_EXIST(-10800,"应税项目编号已存在"),
    TAX_RATE_NOE_EXIST(-10001,"税率不存在，新增失败"),

    // 用户登录
    USER_NOT_EXIST(-20000, "账号不存在"),
    WRONG_PASSWORD(-20001, "密码有误"),
    ENABLED_USER(-20002, "用户已禁用"),
    LOCKED_USER(-20003, "用户已锁定，30分钟后解锁"),
    EXPIRED_PASSWORD(-20004, "用户密码已过期"),
    WRONG_USER_NAME_OR_PASSWORD(-20005, "用户名或密码有误"),
    USER_LOGIN_FAILED_REPEATEDLY(-20006, "用户重复登录失败"),
    WRONG_OLD_PASSWORD(-20007, "原密码错误"),
    PHONE_NUMBER_NOT_EXIST(-20008, "手机号码不存在"),
    SMS_QUOTA_EXCEEDED(-20009, "短信发送次数已超出当日最大限额"),
    VERIFICATION_VALIDATE_FAILED(-20010, "验证码有误"),
    SECURITY_VERIFY_NEEDED(-20011, "请完成安全验证！"),
    PASSWORD_RESET_TICKET_EXPIRED(-20012, "密码重置的验证信息已过期"),
    PHONE_NUMBER_NOT_MATCH(-20013, "手机号码不匹配"),
    SSO_TOKEN_VALIDATE_FAILED(-20014, "单点登录token校验失败"),
    SWITCH_GROUP_ERROR(-20015,"切换组织失败，用户不属于该组织"),
    WRONG_USER_DISABLED_OR_DELETED(-20016,"该帐号已删除或者禁用，如有疑问请联系管理员。"),
    WRONG_ROLE_USE(-20017,"角色已被分配用户"),
    ADD_USER_ORG_ERROR(-20018,"用户不能直接挂到区域下，请先建立第三级组织"),
    ROLE_USE_EXIST(-20019,"角色已存在"),

    //发票
    INVOICE_IS_SUBMIT_ERROR(-30101,"当期发票已提交，需重新提交请先撤回已提交的数据"),

    // 组织架构
    USER_NOT_BELONG_TO_ANY_ORGANIZATION(-41000, "用户没有归属任何组织"),
    ORGANIZATION_NOT_EXIST(-41001, "组织不存在"),
    ORGANIZATION_NOT_FIND(-41002, "未获取到组织"),
    TAX_SUBJECT_NOT_FIND(-41003,"该组织下无有效纳税主体"),
    TAX_SUBJECT__NOT_BELONG_TO_ANY_ORGANIZATION(-41004,"纳税主体不属于当前组织"),
    PROJECT_SUBJECT_NOT_FIND(-41005,"该纳税主体下无项目"),
    NOT_FOUND_MAIN(-41006,"纳税主体不存在"),
    P_ORGANIZATION_NOT_EXIST(-41007, "上级组织不存在"),
    ORG_CODE_EXISTED(-41008, "组织编码已存在"),
    ORG_EXISTED(-41009, "组织已有关联用户"),
    DIS_NON_EXIST(-41010,"区域不存在，请刷新后重试"),
    DIS_ORG_EXIST(-41011,"该组织或该组织下属组织已经绑定其他区域无法再次绑定"),
    DIS_CORD_EXISTED(-41012,"区域编码已存在"),
    ORG_SWITH_ERROR(-41013, "组织切换失败，该组织不属于用户组织列表"),
    ORG_EXIST_ERROR(-41014, "组织已存在"),
    ORG_DISABLED(-41015, "组织已禁用"),
    ORG_DELETE_ERROR(-41016, "组织删除失败，组织禁用状态才可删除"),
    ORG_EXIST_LOWER_ERROR(-41017, "组织关联下级组织，不可禁用"),
    ORG_EXIST_DELETE_ERROR(-41018, "组织已删除，不可操作"),

    // 角色管理
    ROLE_EXISTED(-43000, "角色已存在"),
    ROLE_NOT_EXIST(-43001, "角色不存在"),
    ROLE_GRANTED_USER(-43002, "此角色已有授权用户，暂不能删除此角色"),
    SYSTEM_CONFIG_ROLE_DELETE_DENIED(-43003, "此角色为系统内置角色，不允许删除"),
    SYSTEM_CONFIG_ROLE_UPDATE_DENIED(-43004, "此角色为系统内置角色，不允许修改"),
    // 用户管理
    USERNAME_EXISTED(-42000, "账号已被注册"),
    MOBILE_EXISTED(-42001, "手机号码已被使用"),
    EMAIL_EXISTED(-42002, "邮箱已被使用"),
    EMAIL_SEND_ERROR(-42003, "邮件发送失败"),
    PASSWORD_RESET_EMAIL_SEND_ERROR(-42004, "密码重置邮件发送失败"),
    USER_CREATED_BUT_EMAIL_SEND_ERROR(-42005, "用户创建成功，但邮件发送失败"),
    PASSWORD_ORIGINAL_ERROR(-42006, "原密码有误"),
    // 证照管理
    CONTRACT_EXISTED(-44000,"合同编号已存在"),
    PROJECT_ID_ERROR(-44001,"项目id错误"),
    LICENSE_KEY_EXISTED(-44002,"建设用地规划许可证号已存在"),
    LAND_USE_NUM_EXISTED(-44003,"土地证编号已存在"),
    REPLY_EXISTED(-44004,"批复文号已存在"),
    CONTRACT_NOT_EXISTED(-44005,"合同编号不存在"),
    HOUSE_OWNERSHIP_ID_ERROR(-44006,"大产权ID错误"),
    HOUSE_SALES_NUMBER_EXISTED(-44007,"商品房预售许可证编号已存在"),
    COMPLETE_RECORD_RECORD_NUMBER_EXISTED(-44008,"竣工编号已存在"),
    BUILD_PROJECT_NUMBER_EXISTED(-44009,"建设工程施工许可证编号已存在"),
    BUILD_PROJECT_PLAN_NUMBER_EXISTED(-44010,"建设工程规划许可证编号已存在"),
    WARRANT_NUM_NUMBER_EXISTED(-44011,"该项目分期下的权证号已经存在"),

    //其他档案
    TAXES_NAME_ID_NOT_MATCH(-47001,"纳税主体名称和ID不匹配"),
    TAXES_ID_NOT_EXIST(-47002,"纳税主体不存在请刷新后重试"),

    COLLECTION_EXISTED(-47002,"该发票已经属于无需匹配状态，请刷新界面"),
    COLLECTION_REVOKE_ERROR(-47003,"部分发票已经属于未匹配数据，请刷新重试"),
    ROOM_NOT_DELETE(-47004,"已经匹配无法删除"),
    NO_INVOICE_MATCH(-47005,"暂无满足匹配条件的数据，如需匹配请在未匹配发票页签中对发票记录进行手工匹配。"),

    // 定时任务
    JOB_EXISTED(-51000,"定时任务已存在"),

    //纳税申报
    ACCOUNT_DEC_EXISTED(-61000,"纳税申报已提交，无法对当前台账进行操作"),
    ACCOUNT_EXISTED(-61001,"台账已提交请先撤回后再提交"),
    AUTH_MONTH_NOT_EXISTED(-61002,"请选择月份后再上传"),
    CAN_NOT_RESET(-61003,"当前相关台账已提交，请撤回后操作"),
    MAIN_TABLE_NOT_EXISTED(-61004,"主表数据不存在请刷新重试"),
    PARAM_ERROR(-61005,"参数设置错误"),
    ACCOUNT_SUBJECT_NOT_ALLOW_EDIT(-61006,"台账已经提交，禁止操作数据"),
    VOUCHER_NUM_EXISTED(-64006,"凭证号重复"),
    REVOKE_EXISTED(-64007,"已撤回请勿重复操作"),
    SUBMIT_EXISTED(-64008,"已提交请勿重复操作"),
    SAVE_AND_SUBMIT(-64009,"请先保存后操作"),
    SAVE_EXISTED(-64010,"已保存请勿重复操作"),
    DECLARE_ERROR(-64011,"创建申报失败，请刷新后重试"),
    DECLARE_DEC_EXISTED(-64011,"当前该纳税主体当前月份纳税申报已经申请或存在，请勿重复操作"),
    DECLARE_NOT_EXISTED(-64012,"选择纳税申报不存在，请重新选择"),
    PROCESS_NOT_EXISTED(-64013,"选择所属流程不存在，请重新选择"),
    TAX_MODALITY_NOT_EXISTED(-64014,"选择纳税形式不存在，请重新选择"),
    ACCOUNT_MAIN_NOT_EXISTED(-64015,"当前纳税主体该月份下还未申报，请刷新界面"),
    STOP_EXISTED(-64016,"已终止请勿重复操作"),
    DEC_FINISH(-64017,"已经申报完成无法终止"),
    RESET_ERROR(-64018,"重算失败请刷新重试"),
    ORDER_ERROR(-64019,"请先提交扣除项目汇总台账和税款计算台账再查询"),
    OUTPUT_COLLECTION_UN_SUBJECT(-64020,"当月销项发票匹配未提交，无法生成该台账"),
    RESET_ACCOUNT(-64021,"台账重算中，请稍后。"),
    ARCHIVING_FAILURE(-64022,"归档失败"),
    REVIEW_ERROR(-64023,"已完成申报，请务重复操作"),
    FILE_ERROR(-64024,"已完成归档，请务重复操作"),
    FILE_NOT_EXISTED(-64025,"未完成申报，请完成申报后操作"),
    FLAG_ERROR(-64026,"标记失败，请刷新重试"),
    REPORT_ERROR(-64027,"报表生成中，请稍后操作"),
    OUTPUT_COLLECTION_NOT_SUBJECT(-64028,"当月销项发票未提交，无法生成该台账"),
    OUTPUT_INVOICE_IS_USED_NOT_ALLOW_DELETE(-64029,"当期销项发票已经匹配，无法删除"),
    SIMPLE_TAX_RATE_EMPTY_ERROR(-64030,"简易计税方法税率不能为空"),
    ACCOUNT_BILLING_SALE_NOT_SUBJECT(-64031,"当月开票销售台账未提交，无法生成该台账"),

    TAX_NUM_EXIST(-10200,"社会信用代码已存在"),
    ID_NOT_EXIST(-10201,"id不能为空"),
    STATUS_NOE_EXIST(-10203,"状态码不存在"),

    PROJECT_CODE_EXIST(-10700,"项目代码重复"),
    STAGES_CODE_EXIST(-10701,"项目分期代码重复"),
    PROJECT_STAGES_IS_USERD(-10702,"项目信息已被使用，禁止删除"),
    TAX_METHOD_EMPTY(-10702,"数据字典计税方法不存在"),
    DICT_CORD_EXISTED(-10703,"数据字典编码已存在"),
    STAGES_NUM_EXIST(-10704,"项目分期编码重复"),
    PROJECT_STAGES_NOT_EXIST(-10705,"项目分期不存在"),


    ERROR_TAX_SUBJECT_NOT_EXIST(-30100,"操作失败纳税主体不存在"),
    INVOICE_NUM_EXIST(-30101,"发票号码已存在"),
    UPLOAD_FILE_INVOICE_NUM_EXIST(-30102,"导入文件中存在重复的发票号码"),
    UPLOAD_FILE_ROOM_NUM_EXIST(-30103,"导入文件中存在重复的房间编码"),
    UPLOAD_FILE_INVOICE_TYPE_EXIST(-30104,"导入文件中存在重复的发票类型"),
    UPLOAD_FILE_CONTRACT_NUM_EXIST(-30105,"导入文件中存在重复的合同编码"),
    // 营改增售房管理
    NOT_FIND_ROOM_CODE(-30401,"所属组织未匹配到房间编码"),
    REPETITION_ROOM_CODE(-30402,"房间编码已存在"),

    //撤回前置判断
    REVOKE_OUTPUTINVCOLLECTIONMAIN(-90001,"销项发票采集已提交，操作失败"),
    REVOKE_OUTPUTROOMFILESMAIN(-90002,"房间交易档案采集已提交，操作失败"),
    REVOKE_INTERFINANCIALVOUCHERMAIN(-90003,"财务凭证采集已提交，操作失败"),
    REVOKE_OUTPUTINVOICEMARRYMAIN(-90004,"销项发票数据匹配已提交，操作失败"),
    REVOKE_ACCOUNTBILLINGSALEMAIN(-90005,"开票销售台账已提交，操作失败"),
    REVOKE_ACCOUNTNOTINVOICESALEMAIN(-90006,"未开票销售台账-地产已提交，操作失败"),
    REVOKE_ACCOUNTNOTINVOICEUNSALEREALTYMAIN(-90007,"未开票销售台账-非地产已提交，操作失败"),
    REVOKE_ACCOUNTOTHERTAXMAIN(-90008,"其他涉税调整台账已提交，操作失败"),
    REVOKE_INCOMEINVOICECOLLECTIONMAIN(-90009,"进项发票采集已提交，操作失败"),
    REVOKE_FIXEDASSETCARDMAIN(-90010,"固定资产信息采集已提交，操作失败"),
    REVOKE_INCOMEFINANCEDETAILSMAIN(-90011,"其他扣税凭证已提交，操作失败"),
    REVOKE_ACCOUNTINCOMETAXDETAILMAIN(-90012,"进项税额明细台账已提交，操作失败"),
    REVOKE_ACCOUNTINCOMETAXOUTMAIN(-90013,"其他类型进项税额转出台账已提交，操作失败"),
    REVOKE_ACCOUNTINCOMESIMPLEOUTMAIN(-90014,"简易计税进项税额转出台账已提交，操作失败"),
    REVOKE_ACCOUNTINCOMEESTATEMAIN(-90015,"不动产进项税额抵扣台账已提交，操作失败"),
    REVOKE_LANDPRICEMANAGEMAIN(-90016,"土地价款管理已提交，操作失败"),
    REVOKE_ACCOUNTLANDPRICEDEDUCTED(-90017,"土地价款扣除明细台账已提交，操作失败"),
    REVOKE_ACCOUNTOTHERTAXDEDUCTEDMAIN(-90018,"其他应税项目扣除台账已提交，操作失败"),
    REVOKE_ACCOUNTPREPAYTAXMAIN(-90019,"预缴税款台账已提交，操作失败"),
    REVOKE_ACCOUNTOTHERREDUCETAXDETAILMAIN(-90020,"减免税明细台账已提交，操作失败"),
    REVOKE_ACCOUNTTAXCALCULATIONMAIN(-90021,"税款计算台账已提交，操作失败"),

    // 提交前置判断
    SUBMIT_OUTPUTINVCOLLECTIONMAIN(-90022,"销项发票采集未提交，操作失败"),
    SUBMIT_OUTPUTROOMFILESMAIN(-90023,"房间交易档案采集未提交，操作失败"),
    SUBMIT_INTERFINANCIALVOUCHERMAIN(-90024,"财务凭证采集未提交，操作失败"),
    SUBMIT_OUTPUTINVOICEMARRYMAIN(-90025,"销项发票数据匹配未提交，操作失败"),
    SUBMIT_ACCOUNTBILLINGSALEMAIN(-90026,"开票销售台账未提交，操作失败"),
    SUBMIT_ACCOUNTNOTINVOICESALEMAIN(-90027,"未开票销售台账-地产未提交，操作失败"),
    SUBMIT_ACCOUNTNOTINVOICEUNSALEREALTYMAIN(-90028,"未开票销售台账-非地产未提交，操作失败"),
    SUBMIT_ACCOUNTOTHERTAXMAIN(-90029,"其他涉税调整台账未提交，操作失败"),
    SUBMIT_INCOMEINVOICECOLLECTIONMAIN(-90030,"进项发票采集未提交，操作失败"),
    SUBMIT_FIXEDASSETCARDMAIN(-90031,"固定资产信息采集未提交，操作失败"),
    SUBMIT_INCOMEFINANCEDETAILSMAIN(-90032,"其他扣税凭证未提交，操作失败"),
    SUBMIT_ACCOUNTINCOMETAXDETAILMAIN(-90033,"进项税额明细台账未提交，操作失败"),
    SUBMIT_ACCOUNTINCOMETAXOUTMAIN(-90034,"其他类型进项税额转出台账未提交，操作失败"),
    SUBMIT_ACCOUNTINCOMESIMPLEOUTMAIN(-90035,"简易计税进项税额转出台账未提交，操作失败"),
    SUBMIT_ACCOUNTINCOMEESTATEMAIN(-90036,"不动产进项税额抵扣台账未提交，操作失败"),
    SUBMIT_LANDPRICEMANAGEMAIN(-90037,"土地价款管理未提交，操作失败"),
    SUBMIT_ACCOUNTLANDPRICEDEDUCTED(-90038,"土地价款扣除明细台账未提交，操作失败"),
    SUBMIT_ACCOUNTOTHERTAXDEDUCTEDMAIN(-90039,"其他应税项目扣除台账未提交，操作失败"),
    SUBMIT_ACCOUNTPREPAYTAXMAIN(-90040,"预缴税款台账未提交，操作失败"),
    SUBMIT_ACCOUNTOTHERREDUCETAXDETAILMAIN(-90041,"减免税明细台账未提交，操作失败"),
    SUBMIT_ACCOUNTTAXCALCULATIONMAIN(-90042,"税款计算台账未提交，操作失败"),
    SUBMIT_ACCOUNTOTHERREVISIONMAIN(-90042,"其他事项调整台账未提交，操作失败")
    ;

    private final int value;
    private final String description;

    ResponseCode(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int value() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

    public static ResponseCode valueOf(int code) {
        for (ResponseCode responseCode : values()) {
            if (responseCode.value == code) {
                return responseCode;
            }
        }
        throw new IllegalArgumentException("No matching constant for [" + code + "]");
    }

    @Override
    public String toString() {
        return Integer.toString(this.value);
    }

}
