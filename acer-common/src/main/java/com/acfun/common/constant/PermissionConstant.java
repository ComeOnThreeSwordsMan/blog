package com.acfun.common.constant;

/**
 * 权限码由模块id和操作id组成   详见：permissionId(int moduleid, int actionid)
 * <p>
 * 常量提供给 @PermissionGroup  @Permission注解使用
 * <p>
 * <p>
 * 权限常量
 *
 * @author kwer
 * @Date 2018/4/23/023 18:48
 */
public class PermissionConstant {


    private PermissionConstant() {
        throw new Error();
    }

    /**
     * 获取权限id 由（模块id * 10000 + 操作id）组成
     *
     * @param moduleid
     * @param actionid
     * @return
     */
    public static int permissionId(int moduleid, int actionid) {
        return moduleid * 10000 + actionid;
    }
    //----------------------------------------------模块ID---最小3位数--------------------------------------------------------
    /**
     * 菜单
     */
    public static final int MODULE_ROUTING = 100;
    /**
     * 用户管理
     */
    public static final int MODULE_USER = 101;

    /**
     * 权限管理
     */
    public static final int MODULE_AUTH = 102;

    /**
     * 角色管理
     */
    public static final int MODULE_ROLE = 103;
    /**
     * 认证服务
     */
    public static final int MODULE_OAUTH = 104;
    /**
     * 纳税主体模块
     */
    public static final int MODULE_SUBJECT = 105;
    /**
     * 销项发票采集
     */
    public static final int MODULE_OUTPUTINV = 106;
    /**
     * 创建申报
     */
    public static final int MODULE_CCSB = 107;
    /**
     * 申报办理
     */
    public static final int MODULE_SBBL = 108;
    /**
     * 税收优惠
     */
    public static final int MODULE_PREFERENCES = 109;

    /**
     * 申报参数
     */
    public static final int MODULE_DECLARATION_PARAM = 111;
    /**
     * 期初数据采集
     */
    public static final int MODULE_DATACOLLECTION = 112;
    /**
     * 稽查报告
     */
    public static final int MODULE_REPORT = 117;
    /**
     * 备案资料
     */
    public static final int MODULE_RECORDINFO = 118;
    /**
     * 证照管理
     */
    public static final int MODULE_CERTIFICATE = 119;
    /**
     * 其他档案
     */
    public static final int MODULE_OTHERFILE = 120;
    /**
     * 销项发票匹配
     */
    public static final int MODULE_MARRY = 121;

    /**
     * 开票销售台账
     */
    public static final int MODULE_BILLINGSALE = 122;
    /**
     * 财务凭证
     */
    public static final int MODULE_VOUCHER = 123;
    /**
     * 固定资产进项台账
     */
//    public static final int MODULE_FIXEDASSETS = 124;

    /**
     * 不动产进项税额抵扣台账
     */
    public static final int MODULE_ACCOUNTINCOMEESTATE = 125;
    /**
     * 土地价款扣除明细台账
     */
    public static final int MODULE_DEDUCTEDDETAILS = 126;
    /**
     * 其他应税项目扣除台账
     */
    public static final int MODULE_DEDUCTED = 127;
    /**
     * 其他应税项目扣除台账
     */
    public static final int MODULE_OTHERTAX_DEDUCTED = 128;
    /**
     * 营改增税负分析测算台账
     */
    public static final int MODULE_CAMPING = 129;
    /**减免税明细台账-安庆*/
    public static final int MODULE_ACCOUNTOTHERREDUCETAXDETAIL =  130;
    /**其他涉税调整台账-蒋*/
    public static final int MODULE_ACCOUNTOTHERTAX =  131;
    /**其他涉税调整台账 - 主表*/
    public static final int MODULE_ACCOUNTOTHERTAXMAIN =  132;
    /**预缴税款台账-安庆*/
    public static final int MODULE_ACCOUNTPREPAYTAX =  133;
    /**未开票销售台账-康-作废*/
    public static final int MODULE_ACCOUNTNOTINVOICESALE =  134;
    /**未开票销售台账-地产类-蒋*/
    public static final int MODULE_ACCOUNTNOTINVOICESALEREALTY =  135;
    /**[未开票销售台账-非地产]-康*/
    public static final int MODULE_ACCOUNTNOTINVOICEUNSALEREALTY =  136;
    /**税款计算台账-康*/
    public static final int MODULE_ACCOUNTTAXCALCULATION =  137;
    /**进项税额明细台账-康*/
    public static final int MODULE_ACCOUNTINCOMETAXDETAIL =  138;
    /**简易计税进项税额转出台账*/
    public static final int MODULE_ACCOUNTINCOMESIMPLEOUT =  139;
    /**其他业务进项税额转出台账-康/蒋*/
    public static final int MODULE_ACCOUNTINCOMETAXOUT =  140;
    /**房屋所有权证--安庆*/
    public static final int MODULE_CARDHOUSEOWNERSHIP =  141;
    /**房屋所有权证-明细--安庆*/
    public static final int MODULE_CARDHOUSEOWNERSHIPDETAIL =  142;
    /**商品房预售许可证--安庆*/
    public static final int MODULE_CARDHOUSESALES =  143;
    /**建设用地规划许可证*/
    public static final int MODULE_CARDLANDBUILDPLAN =  144;
    /**国有土地使用证*/
    public static final int MODULE_CARDLANDUSE =  145;
    /**建设工程施工许可证--安庆*/
    public static final int MODULE_CARDPROJECTBUILD =  146;
    /**建设工程规划许可证--安庆*/
    public static final int MODULE_CARDPROJECTBUILDPLAN =  147;
    /**国有土地使用权出让合同*/
    public static final int MODULE_CONTRACTLAND =  148;
    /**进项发票采集*/
    public static final int MODULE_INCOMEINVOICECOLLECTION =  149;
    /**进项发票匹配-安庆*/
    public static final int MODULE_INCOMEINVOICEIMPORT =  150;
    /**固定资产信息采集*/
    public static final int MODULE_FIXEDASSETCARD =  151;
    /**其他扣税凭证*/
    public static final int MODULE_INCOMEFINANCEDETAILS =  152;
    /**可售建筑面积信息-邓傅升*/
    public static final int MODULE_INTERAVAILABLEBUILDINGAREAINFORMATION =  153;
    /**[土地价款管理-新]-安庆*/
    public static final int MODULE_LANDPRICEMANAGE =  154;
    /**房间交易档案-安庆-邓傅升*/
    public static final int MODULE_OUTPUTROOMFILES =  155;
    /**项目立项批复*/
    public static final int MODULE_PROJECTAPPROVAL =  156;
    /**竣工验收备案表--安庆*/
    public static final int MODULE_PROJECTFINISHACCEPTANCE =  157;
    /**项目信息*/
    public static final int MODULE_PROJECTINFO =  158;
    /**分期对应土地出让合同*/
    public static final int MODULE_PROJECTSTAGE =  159;
    /**发票查询*/
    public static final int MODULE_REPORTINVOICEQUIRE =  160;
//    /**认证服务*/
//    public static final int MODULE_OAUTH =  161;
    /**组织架构服务*/
    public static final int MODULE_ORGANIZATION =  162;
    /**权限服务*/
    public static final int MODULE_PERMISSION =  163;
//    /**角色服务*/
//    public static final int MODULE_ROLE =  164;
    /**菜单-康*/
    public static final int MODULE_SECMENU =  165;
    /**菜单权限-康*/
    public static final int MODULE_SECMENUPERMISSION =  166;
//    /**用户服务*/
//    public static final int MODULE_USER =  167;
    /**数据字典--安庆*/
    public static final int MODULE_DICT =  168;
    /**文件管理-蒋*/
    public static final int MODULE_FILE =  169;
    /**主营业务收入科目税率对应表-蒋*/
    public static final int MODULE_INCOMEANDTAXRATECORRESPONDENCE =  170;
    /**区域管理-安庆*/
    public static final int MODULE_SECDISTRICT =  171;
    /**应税项目-蒋*/
    public static final int MODULE_TAXABLEPROJECT =  172;
    /**税收分类编码*/
    public static final int MODULE_TAXCLASSIFICATIONCODING =  173;
    /**项目税率*/
    public static final int MODULE_TAXRATE =  174;
    /**纳税申报主表*/
    public static final int MODULE_TAXDECCONDUCTMAIN =  175;
    /**纳税申报-附表5*/
    public static final int MODULE_TAXDECLARATIONADDENDUMFIVE =  176;
    /**纳税申报-附表4*/
    public static final int MODULE_TAXDECLARATIONADDENDUMFOUR =  177;
    /**纳税申报-附表1*/
    public static final int MODULE_TAXDECLARATIONADDENDUMONE =  178;
    /**纳税申报-附表3*/
    public static final int MODULE_TAXDECLARATIONADDENDUMTHREE =  179;
    /**纳税申报-附表2*/
    public static final int MODULE_TAXDECLARATIONADDENDUMTWO =  180;
    /**营改增税负分析测算明细表-安庆*/
    public static final int MODULE_TAXDECLARATIONCAMPING =  181;
    /**固定资产-安庆*/
    public static final int MODULE_TAXDECLARATIONFIXEDASSETS =  182;
    /**增值税预缴税款表-安庆*/
    public static final int MODULE_TAXDECLARATIONPREPAYTAX =  183;
    /**增值税减免税申报明细表-安庆*/
    public static final int MODULE_TAXDECLARATIONREDUCE =  184;
    /**纳税申报-本期抵扣进项税额结构明细表*/
    public static final int MODULE_TAXDECLARATIONTAXSTRUCTURE =  185;
    /**房间交易档案报表*/
    public static final int MODULE_OUTPUTROOMFILES_REPORT =  186;
    /**固定资产卡片报表*/
    public static final int MODULE_FIXEDASSETCARD_REPORT =  187;
    /**进项发票采集报表*/
    public static final int MODULE_INCOMEINVOICECOLLECTION_REPORT =  188;
    /**财务凭证报表*/
    public static final int MODULE_VOUCHER_REPORT =  189;
    /**销项发票采集报表*/
    public static final int MODULE_OUTPUTINV_REPORT =  190;
    /**纳税申报*/
    public static final int MODULE_TAXDECCONDUCT =  191;
    /**收入检查*/
    public static final int MODULE_INCOME_CHECK =  192;
    /**查询申报*/
    public static final int MODULE_CXSB =  193;
    /**其他事项调整台账**/
    public static final int MODULE_ACCOUNTOTHERREVISIONCONTROLLER =  194;


    //----------------------------------------------操作ID  不超过4位数-----------------------------------------------------------
    /**
     * 查询
     */
    public static final int ACTION_QUERY = 1001;
    /**
     * 查看
     */
    public static final int ACTION_PAGE = 1002;

    /**
     * 新增
     */
    public static final int ACTION_CREATE = 1003;

    /**
     * 修改
     */
    public static final int ACTION_UPDATE = 1004;
    /**
     * 导入
     */
    public static final int ACTION_UPLOAD = 1005;
    /**
     * 下载
     */
    public static final int ACTION_DOWNLOAD = 1006;
    /**
     * 导出
     */
    public static final int ACTION_EXPORT = 1007;

    /**
     * 删除
     */
    public static final int ACTION_DELETE = 1008;

    /**
     * 撤回
     */
    public static final int ACTION_RESET = 1009;
    /**
     * 提交
     */
    public static final int ACTION_SUBMIT = 1010;
    /**
     * 撤回
     */
    public static final int ACTION_REVOKE = 1011;

    /**
     * 修改密码
     */
    public static final int ACTION_PASSWORD = 1012;

    /**
     * 所有
     */
    public static final int ACTION_ALL = 1013;
    /**
     * 启用禁用
     */
    public static final int ACTION_ENABLEORDISABLE = 1014;

    /**
     * 操作权限1
     */
    public static final int ACTION_0 = 5000;
    /**
     * 操作权限1
     */
    public static final int ACTION_1 = 5001;
    /**
     * 操作权限2
     */
    public static final int ACTION_2 = 5002;
    /**
     * 操作权限3
     */
    public static final int ACTION_3 = 5003;
    /**
     * 操作权限4
     */
    public static final int ACTION_4 = 5004;
    /**
     * 操作权限5
     */
    public static final int ACTION_5 = 5005;
    /**
     * 操作权限6
     */
    public static final int ACTION_6 = 5006;
    /**
     * 操作权限7
     */
    public static final int ACTION_7 = 5007;
    /**
     * 操作权限8
     */
    public static final int ACTION_8 = 5008;
    /**
     * 操作权限9
     */
    public static final int ACTION_9 = 5009;
    /**
     * 操作权限10
     */
    public static final int ACTION_10 = 5010;
    /**
     * 操作权限11
     */
    public static final int ACTION_11 = 5011;
    /**
     * 操作权限12
     */
    public static final int ACTION_12 = 5012;
    /**
     * 操作权限13
     */
    public static final int ACTION_13 = 5013;
    /**
     * 操作权限13
     */
    public static final int ACTION_14 = 5014;
    /**
     * 操作权限15
     */
    public static final int ACTION_15 = 5015;

//    /**
//     * 分配角色
//     */
//    public static final int ACTION_GRANT_ROLE = 3000;
//    /**
//     * 查询角色已分配的权限
//     */
//    public static final int ACTION_GRANTED_PERMISSION = 3001;
//    /**
//     * 修改角色权限
//     */
//    public static final int ACTION_GRANT_PERMISSIONS = 3002;
//    /**
//     * 查询用户已分配的角色
//     */
//    public static final int ACTION_GET_GRANTED_ROLE = 3003;
//    /**
//     * 用户分配权限
//     */
//    public static final int ACTION_ASSIGNPERMISSION_USER = 3004;
//
//    /**
//     * 查询用户权限列表
//     */
//    public static final int ACTION_QUERYUSERPERMISSIONS = 3005;
//    /**
//     * 查询角色所有有效用户列表
//     */
//    public static final int ACTION_GET_GRANTED_USER = 3007;
//    /**
//     * 分配用户
//     */
//    public static final int ACTION_GRANT_USER = 3008;
//    /**
//     * 登录
//     */
//    public static final int ACTION_LOGIN = 4000;
//    /**
//     * 报表管理菜单
//     */
//    public static final int ACTION_BBGL = 5000;
//    /**
//     * 增值税管理菜单
//     */
//    public static final int ACTION_ZZSGL = 5001;
//    /**
//     * 纳税申报菜单
//     */
//    public static final int ACTION_NSSB = 5002;
//    /**
//     * 流程终止
//     */
//    public static final int ACTION_STOP = 7000;
//    /**
//     * 申报查询
//     */
//    public static final int ACTION_SBCX = 8000;
//    /**
//     * 申报归档
//     */
//    public static final int ACTION_GD = 8001;
//    /**
//     * 申报完成
//     */
//    public static final int ACTION_FINISH = 8002;
//
//    /**
//     * 数据匹配
//     */
//    public static final int ACTION_AUTOMATIC = 8003;


}
