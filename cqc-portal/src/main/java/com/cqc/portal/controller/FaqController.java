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
@RequestMapping("/faq")
public class FaqController {

    @Autowired
    private FaqService faqService;

    @ApiOperation("用户列表")
    @GetMapping("/list")
    public Result<Page<Faq>> faqList() {

        return Result.success(page);
    }


}
