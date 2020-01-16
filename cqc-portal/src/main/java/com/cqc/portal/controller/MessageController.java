package com.cqc.portal.controller;


import com.cqc.portal.service.FaqService;

/**
 * <p>
 * 常见问题 前端控制器
 * </p>
 *
 * @author wanglz
 * @since 2020-01-16
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private FaqService faqService;

    @ApiOperation("最近消息列表")
    @GetMapping("/getNew")
    public Result<Page<Message>> getNew() {

        String userId = PortalUserUtil.getCurrentUserId();



        return Result.success(page);
    }


}
