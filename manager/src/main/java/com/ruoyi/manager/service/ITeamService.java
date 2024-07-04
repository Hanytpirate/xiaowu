package com.ruoyi.manager.service;

import java.util.List;
import com.ruoyi.manager.domain.Team;

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
}
