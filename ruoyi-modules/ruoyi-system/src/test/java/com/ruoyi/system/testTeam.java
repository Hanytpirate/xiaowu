package com.ruoyi.system;

import com.ruoyi.system.api.domain.SysUser;
import com.ruoyi.system.api.domain.Team;
import com.ruoyi.system.domain.TeamUser;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.TeamMapper;
import com.ruoyi.system.mapper.TeamUserMapper;
import com.ruoyi.system.service.ITeamService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class testTeam {
    @Autowired
    ITeamService teamService;
    @Autowired
    TeamUserMapper teamUserMapper;
    @Autowired
    TeamMapper teamMapper;
    @Test
    public void testTeam() {
//        Team team = teamService.selectTeamByTeamId(3l);
//        System.out.println(team.getUsers());
//        SysUser sysUser = sysUserMapper.selectUserById(101l);
//        System.out.println(sysUser.getRoles());
//        TeamUser teamUser = new TeamUser();
//        teamUser.setTeamId(5l);
//        teamUser.setUserId(101l);
//        teamUserMapper.insertTeamUser(teamUser);
        SysUser sysUser = new SysUser();
//        sysUser.setUserName("韩");
//        List<SysUser> sysUsers = teamMapper.selectUnassignUserList(12l, sysUser);
//        System.out.println(sysUsers);
        List<SysUser> sysUsers = teamService.selectAssignUserList(12l, sysUser);
        System.out.println(sysUsers);
    }
}
