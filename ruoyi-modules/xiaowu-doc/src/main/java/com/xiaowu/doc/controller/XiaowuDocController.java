package com.xiaowu.doc.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.xiaowu.doc.domain.XiaowuDoc;
import com.xiaowu.doc.service.IXiaowuDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 文档管理Controller
 *
 * @author hanyt
 * @date 2024-07-22
 */
@RestController
@RequestMapping("/doc")
public class XiaowuDocController extends BaseController
{
    @Autowired
    private IXiaowuDocService xiaowuDocService;

    /**
     * 查询文档管理列表
     */
    @RequiresPermissions("manager:doc:list")
    @GetMapping("/list")
    public TableDataInfo list(XiaowuDoc xiaowuDoc)
    {
        startPage();
        List<XiaowuDoc> list = xiaowuDocService.selectXiaowuDocList(xiaowuDoc);
        return getDataTable(list);
    }

    /**
     * 导出文档管理列表
     */
    @RequiresPermissions("manager:doc:export")
    @Log(title = "文档管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XiaowuDoc xiaowuDoc)
    {
        List<XiaowuDoc> list = xiaowuDocService.selectXiaowuDocList(xiaowuDoc);
        ExcelUtil<XiaowuDoc> util = new ExcelUtil<XiaowuDoc>(XiaowuDoc.class);
        util.exportExcel(response, list, "文档管理数据");
    }

    /**
     * 获取文档管理详细信息
     */
    @RequiresPermissions("manager:doc:query")
    @GetMapping(value = "/{docId}")
    public AjaxResult getInfo(@PathVariable("docId") Long docId)
    {
        return success(xiaowuDocService.selectXiaowuDocByDocId(docId));
    }

    /**
     * 新增文档管理
     */
    @RequiresPermissions("manager:doc:add")
    @Log(title = "文档管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XiaowuDoc xiaowuDoc)
    {
        return toAjax(xiaowuDocService.insertXiaowuDoc(xiaowuDoc));
    }

    /**
     * 修改文档管理
     */
    @RequiresPermissions("manager:doc:edit")
    @Log(title = "文档管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XiaowuDoc xiaowuDoc)
    {
        return toAjax(xiaowuDocService.updateXiaowuDoc(xiaowuDoc));
    }

    /**
     * 删除文档管理
     */
    @RequiresPermissions("manager:doc:remove")
    @Log(title = "文档管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{docIds}")
    public AjaxResult remove(@PathVariable Long[] docIds)
    {
        return toAjax(xiaowuDocService.deleteXiaowuDocByDocIds(docIds));
    }
}
