package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.utils.DateUtils;

import com.ruoyi.common.datascope.annotation.MyDataScope;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.domain.SysUser;
import com.ruoyi.system.api.domain.Team;
import com.ruoyi.system.api.model.LoginUser;
import com.ruoyi.system.domain.TeamUser;
import com.ruoyi.system.mapper.TeamMapper;
import com.ruoyi.system.mapper.TeamUserMapper;
import com.ruoyi.system.service.ITeamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 团队管理Service业务层处理
 *
 * @author hanyt
 * @date 2024-07-03
 */
@Service
public class TeamServiceImpl implements ITeamService
{
    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private TeamUserMapper teamUserMapper;
    /**
     * 查询团队管理
     *
     * @param teamId 团队管理主键
     * @return 团队管理
     */
    @Override
    public Team selectTeamByTeamId(Long teamId)
    {
        Team team = new Team();
        team.setTeamId(teamId);
        return teamMapper.selectTeamByTeamId(team);
    }

    /**
     * 查询团队管理列表
     *
     * @param team 团队管理
     * @return 团队管理
     */
    @Override
    @MyDataScope(alias = "t")
    public List<Team> selectTeamList(Team team)
    {
        return teamMapper.selectTeamList(team);
    }

    /**
     * 添加以自己为项目经理的团队
     *
     * @param team 团队管理
     * @return 结果
     */
    @Override
    public int insertTeam(Team team)
    {
        // 把leader_uid设置为当前用户的uid
        LoginUser loginUser = SecurityUtils.getLoginUser();
        team.setLeaderUid(loginUser.getUserid());
        team.setCreateTime(DateUtils.getNowDate());
        // 添加新的团队记录
        int res = teamMapper.insertTeam(team);
        // 把当前用户添加添加到团队中

        insertTeamUser(team.getTeamId(),loginUser.getUserid());
        return res;
    }

    /**
     * 修改团队管理
     *
     * @param team 团队管理
     * @return 结果
     */
    @Override
    public int updateTeam(Team team)
    {
        return teamMapper.updateTeam(team);
    }

    /**
     * 批量删除团队管理
     *
     * @param teamIds 需要删除的团队管理主键
     * @return 结果
     */
    @Override
    public int deleteTeamByTeamIds(Long[] teamIds)
    {
        return teamMapper.deleteTeamByTeamIds(teamIds);
    }

    /**
     * 删除团队管理信息
     *
     * @param teamId 团队管理主键
     * @return 结果
     */
    @Override
    public int deleteTeamByTeamId(Long teamId)
    {
        return teamMapper.deleteTeamByTeamId(teamId);
    }
    /**
     * 查询没有被加入到团队中的用户
     */
    @Override
    public List<SysUser> selectUnassignUserList(Long teamId, SysUser sysUser) {
        return teamMapper.selectTeamUserList(teamId,sysUser,true);
    }

    @Override
    public List<SysUser> selectAssignUserList(Long teamId, SysUser sysUser) {
        return teamMapper.selectTeamUserList(teamId,sysUser,false);
    }

    private void checkTeamDataScope(Long teamId, String msg) {
        if (!SysUser.isAdmin(SecurityUtils.getUserId()))
        {
            Team teamRequest = new Team();
            teamRequest.setTeamId(teamId);
            Team team = teamMapper.selectTeamList(teamRequest).get(0);
            if(!team.getLeaderUid().equals(SecurityUtils.getUserId())){
                throw new ServiceException(msg);
            }
        }
    }

    @Override
    public int deleteTeamUser(TeamUser teamUser) {
        return teamUserMapper.deleteTeamUser(teamUser);
    }

    @Override
    public int deleteTeamUserByTeamId(Long teamId) {
        return teamUserMapper.deleteTeamUserByTeamId(teamId);
    }

    private int insertTeamUser(Long teamId, Long userId) {
        TeamUser teamUser = new TeamUser();
        teamUser.setTeamId(teamId);
        teamUser.setUserId(userId);
        return teamUserMapper.insertTeamUser(teamUser);
    }
    @Override
    public int insertAssignUsers(Long teamId, Long[] userIds) {
        checkTeamDataScope(teamId, "您不是"+teamId+"团队的项目经理,没有权限添加团队成员");
        List<TeamUser> teamUserList = new ArrayList<TeamUser>();
        for (Long userId : userIds) {
            TeamUser teamUser = new TeamUser();
            teamUser.setTeamId(teamId);
            teamUser.setUserId(userId);
            teamUserList.add(teamUser);
        }
        return batchInsertTeamUser(teamUserList);
    }

    @Override
    public int deleteAssignUser(TeamUser teamUser) {

        return teamUserMapper.deleteTeamUser(teamUser);
    }

    @Override
    public int deleteAssignUsers(Long teamId, Long[] userIds) {
        return teamUserMapper.deleteTeamUsers(teamId,userIds);
    }

    @Override
    public List<Team> selectTeamListByUserId(Long userId) {
        return teamMapper.selectTeamListByUserId(userId);
    }

    private int batchInsertTeamUser(List<TeamUser> teamUserList) {
        return teamUserMapper.batchInsertTeamUser(teamUserList);
    }
}
