package com.ruoyi.system.api.domain;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 团队管理对象 xiaowu_team
 *
 * @author hanyt
 * @date 2024-07-03
 */
public class Team extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 团队id */
    @Excel(name = "团队id")
    private Long teamId;

    /** 项目经理的uid */
    @Excel(name = "项目经理的uid")
    private Long leaderUid;

    /** 团队名称 */
    @Excel(name = "团队名称")
    private String teamName;

    /** 团队成员 */
    private List<SysUser> users;
    public List<SysUser> getUsers() {
        return users;
    }

    public void setUsers(List<SysUser> users) {
        this.users = users;
    }

    public void setTeamId(Long teamId)
    {
        this.teamId = teamId;
    }

    public Long getTeamId()
    {
        return teamId;
    }
    public void setLeaderUid(Long leaderUid)
    {
        this.leaderUid = leaderUid;
    }

    public Long getLeaderUid()
    {
        return leaderUid;
    }
    public void setTeamName(String teamName)
    {
        this.teamName = teamName;
    }

    public String getTeamName()
    {
        return teamName;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamId=" + teamId +
                ", leaderUid=" + leaderUid +
                ", teamName='" + teamName + '\'' +
                ", users=" + users +
                '}';
    }
}
