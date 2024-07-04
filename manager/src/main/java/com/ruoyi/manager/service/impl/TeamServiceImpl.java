package com.ruoyi.manager.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.manager.mapper.TeamMapper;
import com.ruoyi.manager.domain.Team;
import com.ruoyi.manager.service.ITeamService;

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

    /**
     * 查询团队管理
     * 
     * @param teamId 团队管理主键
     * @return 团队管理
     */
    @Override
    public Team selectTeamByTeamId(Long teamId)
    {
        return teamMapper.selectTeamByTeamId(teamId);
    }

    /**
     * 查询团队管理列表
     * 
     * @param team 团队管理
     * @return 团队管理
     */
    @Override
    public List<Team> selectTeamList(Team team)
    {
        return teamMapper.selectTeamList(team);
    }

    /**
     * 新增团队管理
     * 
     * @param team 团队管理
     * @return 结果
     */
    @Override
    public int insertTeam(Team team)
    {
        team.setCreateTime(DateUtils.getNowDate());
        return teamMapper.insertTeam(team);
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
}
