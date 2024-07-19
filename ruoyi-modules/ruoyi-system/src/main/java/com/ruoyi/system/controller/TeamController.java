package com.ruoyi.system.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;

import com.ruoyi.system.api.domain.SysUser;
import com.ruoyi.system.api.domain.Team;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.domain.TeamUser;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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

    @Autowired
    private ISysUserService userService;
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

    @RequiresPermissions("manager:team:query")
    @GetMapping("/assignUser/{teamId}")
    public TableDataInfo assignUser(SysUser user,@PathVariable("teamId") Long teamId){
        startPage();
        List<SysUser> sysUsers = teamService.selectAssignUserList(teamId, user);
        return getDataTable(sysUsers);
    }
    /**
     * 查询目前没有被分配到该团队的成员
     */
    @RequiresPermissions("manager:team:query")
    @GetMapping("/unassignUser/{teamId}")
    public TableDataInfo selectUnassignUserList(SysUser user, @PathVariable("teamId") Long teamId){
        startPage();
        List<SysUser> sysUsers = teamService.selectUnassignUserList(teamId, user);
        return getDataTable(sysUsers);
    }
    @RequiresPermissions("manager:team:edit")
    @PutMapping("/assignUser/selectAll")
    public AjaxResult selectAssignUserAll(Long teamId, Long[] userIds)
    {

        return toAjax(teamService.insertAssignUsers(teamId, userIds));
    }
    @RequiresPermissions("manager:team:edit")
    @PutMapping("/assignUser/cancel")
    public AjaxResult cancelAssignUser(@RequestBody TeamUser teamUser)
    {
        return toAjax(teamService.deleteAssignUser(teamUser));
    }
    @RequiresPermissions("system:team:edit")
    @PutMapping("/assignUser/cancelAll")
    public AjaxResult cancelAssignUserAll(Long teamId, Long[] userIds)
    {
        return toAjax(teamService.deleteAssignUsers(teamId, userIds));
    }
}
