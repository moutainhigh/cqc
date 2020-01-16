package com.cqc.portal.controller;


import com.cqc.portal.service.FaqService;
import com.cqc.portal.service.NoticeService;

/**
 * <p>
 * 常见问题 前端控制器
 * </p>
 *
 * @author wanglz
 * @since 2020-01-16
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @ApiOperation("获取最新公告")
    @GetMapping("/list")
    public Result<Page<Notice>> noticeList(@NotNull(message="type不能为空")Integer type) {

        return Result.success(page);
    }




}
