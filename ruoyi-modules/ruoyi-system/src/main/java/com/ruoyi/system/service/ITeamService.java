package com.ruoyi.system.service;

import com.ruoyi.system.api.domain.SysUser;
import com.ruoyi.system.api.domain.Team;
import com.ruoyi.system.domain.TeamUser;

import java.util.List;

/**
 * 团队管理Service接口
 *
 * @author hanyt
 * @date 2024-07-03
 */
public interface ITeamService
{
    /**
     * 查询团队管理
     *
     * @param teamId 团队管理主键
     * @return 团队管理
     */

    public Team selectTeamByTeamId(Long teamId);

    /**
     * 查询团队管理列表
     *
     * @param team 团队管理
     * @return 团队管理集合
     */

    public List<Team> selectTeamList(Team team);

    /**
     * 新增团队管理
     *
     * @param team 团队管理
     * @return 结果
     */
    public int insertTeam(Team team);

    /**
     * 修改团队管理
     *
     * @param team 团队管理
     * @return 结果
     */
    public int updateTeam(Team team);

    /**
     * 批量删除团队管理
     *
     * @param teamIds 需要删除的团队管理主键集合
     * @return 结果
     */
    public int deleteTeamByTeamIds(Long[] teamIds);

    /**
     * 删除团队管理信息
     *
     * @param teamId 团队管理主键
     * @return 结果
     */
    public int deleteTeamByTeamId(Long teamId);
    /**
     * 查询没有被加入到团队中的用户
     */
    public List<SysUser> selectUnassignUserList(Long teamId, SysUser sysUser);
    public List<SysUser> selectAssignUserList(Long teamId, SysUser sysUser);

    /** 删除指定的成员关系 */
    public int deleteTeamUser(TeamUser teamUser);
    /** 删除所有与某一团队相关的所有关系 */
    public int deleteTeamUserByTeamId(Long teamId);
    public int insertAssignUsers(Long teamId, Long[] userIds);

    public int deleteAssignUser(TeamUser teamUser);

    public int deleteAssignUsers(Long teamId, Long[] userIds);

}
