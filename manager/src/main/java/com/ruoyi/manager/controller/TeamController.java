package com.ruoyi.manager.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.manager.domain.Team;
import com.ruoyi.manager.service.ITeamService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 团队管理Controller
 * 
 * @author hanyt
 * @date 2024-07-03
 */
@RestController
@RequestMapping("/team")
public class TeamController extends BaseController
{
    @Autowired
    private ITeamService teamService;

    /**
     * 查询团队管理列表
     */
    @RequiresPermissions("manager:team:list")
    @GetMapping("/list")
    public TableDataInfo list(Team team)
    {
        startPage();
        List<Team> list = teamService.selectTeamList(team);
        return getDataTable(list);
    }

    /**
     * 导出团队管理列表
     */
    @RequiresPermissions("manager:team:export")
    @Log(title = "团队管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Team team)
    {
        List<Team> list = teamService.selectTeamList(team);
        ExcelUtil<Team> util = new ExcelUtil<Team>(Team.class);
        util.exportExcel(response, list, "团队管理数据");
    }

    /**
     * 获取团队管理详细信息
     */
    @RequiresPermissions("manager:team:query")
    @GetMapping(value = "/{teamId}")
    public AjaxResult getInfo(@PathVariable("teamId") Long teamId)
    {
        return success(teamService.selectTeamByTeamId(teamId));
    }

    /**
     * 新增团队管理
     */
    @RequiresPermissions("manager:team:add")
    @Log(title = "团队管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Team team)
    {
        return toAjax(teamService.insertTeam(team));
    }

    /**
     * 修改团队管理
     */
    @RequiresPermissions("manager:team:edit")
    @Log(title = "团队管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Team team)
    {
        return toAjax(teamService.updateTeam(team));
    }

    /**
     * 删除团队管理
     */
    @RequiresPermissions("manager:team:remove")
    @Log(title = "团队管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{teamIds}")
    public AjaxResult remove(@PathVariable Long[] teamIds)
    {
        return toAjax(teamService.deleteTeamByTeamIds(teamIds));
    }
}
