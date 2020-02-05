const host = "http://localhost:9090";

const login_url = "/login";

const register_uri = "/register";

const getInfo_url = "/user/getInfo";

// 获取用户信息
const get_user_info = "/user/userInfo";

const inviteCode_uri = "/inviteCode/get";

const invite_create_uri = "/inviteCode/create";

const bank_card_list_uri = "/bankCard/myCardList";

const bank_card_add_uri = "/bankCard/add";

const bank_card_set_default_uri = "/bankCard/setDefault";

const bank_card_remove_uri = "/bankCard/remove";

const bank_list_uri = "/bank/list";

// 佣金费率
const rate_list_uri = "/userRate/list";

// 提交实名认证
const realInfo_apply_uri = "/realInfo/apply";

const realInfo_get_uri = "/realInfo/get";

// 修改登录密码
const modify_login_pwd_uri = "/user/modifyLoginPwd";

// 修改支付密码
const modify_pay_pwd_uri = "/user/modifyPayPwd";

// 修改地区
const modify_area_uri = "/user/modifyArea";

//获取常见问题
const faq_list_uri = "/faq/list";
// 获取公告
const notice_list_uri = "/notice/list";
// 获取消息
const msg_list_uri = "/message/getNew";
// 我的订单
const my_order_list = "/order/list";
// 抢单后获取新订单
const my_order_new_list = "/order/getNew";
// 开启自动下单
const open_auto_buy_uri = "/user/openAutoBuy";
// 关闭自动下单
const close_auto_buy_uri = "/user/closeAutoBuy";
// 获取抢单状态
const get_auto_order_status = "/user/getAutoOrderStatus";

// 下单接口
const buy_order_uri = "/order/buyOrder";

// 确认收款
const order_confirm_pay_uri = "/order/confirmPay";
// 我的账变记录
const my_fund_list = "/userFund/myList";
// 代理收益统计
const income_agentIncome_uri = "/income/agentIncome";

// 收款码列表
const receive_list = "/receiveCode/list";
// 启用、关闭收款码
const open_receive_code_uri = "/receiveCode/open";
// 关闭收款码
const close_receive_code_uri = "/receiveCode/close";
// 添加收款码
const receive_add_uri = "/receiveCode/add";
//缴纳押金
const PAY_DEPOSIT_URI = "/user/payDeposit";

// 提现
const with_draw_apply_uri = "/withDraw/add";

// 获取密钥和二维码
const google_auth_secret_uri = "/googleAuth/secret";

// 绑定谷歌验证器
const google_auth_bind_uri = "/googleAuth/bind";

//发送短信验证码
const send_sms_uri = "/sms/send";

//获取谷歌密钥
const google_secret_uri = "/googleAuth/secret";

// 绑定谷歌验证码
const google_bind_uri = "/googleAuth/bind";