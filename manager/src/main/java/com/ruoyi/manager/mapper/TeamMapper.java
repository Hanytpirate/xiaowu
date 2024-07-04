package com.ruoyi.manager.mapper;

import java.util.List;
import com.ruoyi.manager.domain.Team;

/**
 * 团队管理Mapper接口
 * 
 * @author hanyt
 * @date 2024-07-03
 */
public interface TeamMapper 
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
     * 删除团队管理
     * 
     * @param teamId 团队管理主键
     * @return 结果
     */
    public int deleteTeamByTeamId(Long teamId);

    /**
     * 批量删除团队管理
     * 
     * @param teamIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTeamByTeamIds(Long[] teamIds);
}
