package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.TeamUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeamUserMapper {
    /** 删除指定的成员关系 */
    public int deleteTeamUser(TeamUser teamUser);
    /** 删除所有与某一团队相关的所有关系 */
    public int deleteTeamUserByTeamId(Long teamId);
    /** 插入指定的成员关系 */
    public int insertTeamUser(TeamUser teamUser);
    /** 批量插入成员关系 */
    public int batchInsertTeamUser(List<TeamUser> teamUserList);

    public int deleteTeamUsers(@Param("teamId") Long teamId,@Param("userIds")  Long[] userIds);

}
