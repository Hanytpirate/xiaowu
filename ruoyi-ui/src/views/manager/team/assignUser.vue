<template>
  <div class="app-container">
    <h4 class="form-header h4">基本信息</h4>
    <el-form ref="form" :model="team" label-width="80px">
      <el-row>
        <el-col :span="8" :offset="2">
          <el-form-item label="团队名称" prop="nickName">
            <el-input v-model="team.teamName" disabled />
          </el-form-item>
        </el-col>
        <el-col :span="8" :offset="2">
          <el-form-item label="项目经理" prop="userName">
            <el-input v-model="manager.userName" disabled />
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>

    <h4 class="form-header h4">团队成员</h4>
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch">
      <el-form-item label="用户名" prop="userName">
        <el-input v-model="queryParams.userName" placeholder="请输入用户名称" clearable style="width: 240px"
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="手机号码" prop="phonenumber">
        <el-input v-model="queryParams.phonenumber" placeholder="请输入手机号码" clearable style="width: 240px"
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="openSelectUser"
          v-hasPermi="['manager:team:edit']">添加团队成员</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-circle-close" size="mini" :disabled="multiple"
          @click="cancelAssignUserAll" v-hasPermi="['manager:team:edit']">批量移除成员</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>
    <el-table v-loading="loading" 
      @selection-change="handleSelectionChange" :data="users">
      <el-table-column label="序号" type="index" align="center">
        <template slot-scope="scope">
          <span>{{ (queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1 }}</span>
        </template>
      </el-table-column>
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column label="头像" align="center">
        <template slot-scope="scope">
          <img :src="scope.row.avatar || require('@/assets/images/profile.jpg')" class="user-avatar">
        </template>
      </el-table-column>
      <el-table-column label="用户名" align="center" prop="userName" />
      <el-table-column label="真实姓名" align="center" prop="nickName" />
      <el-table-column label="联系电话" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.phonenumber || '该用户没有提供' }}</span>
        </template>
      </el-table-column>
      <el-table-column label="邮箱地址" align="center" width="180" >
        <template slot-scope="scope">
          <span>{{ scope.row.email || '该用户没有提供' }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-circle-close" @click="cancelAssignUser(scope.row)"
            v-hasPermi="['system:team:edit']">移除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList"/>

    <select-user ref="select"  @ok="handleQuery" />
  </div>
</template>

<script>
import { getUser } from "@/api/system/user";
import { getAssignUser,getTeam,assignUserCancel,assignUserCancelAll } from "@/api/manager/team";
import selectUser from "./selectUser";
export default {
  name: "AssignUser",
  components: { selectUser },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 总条数
      total: 0,
      // 选中角色编号
      userIds: [],
      // 非多个禁用
      multiple: true,
      // 角色信息
      users: [],
      teamId:0,
      team: {},
      manager: {},
      // 显示搜索条件
      showSearch: true,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userName: undefined,
        phonenumber: undefined
      }
    };
  },
  created() {
    this.teamId = this.$route.params && this.$route.params.teamId;
    getTeam(this.teamId).then((response)=>{
      this.team = response.data
      return getUser(this.team.leaderUid)
    }).then((response)=>{
      this.manager = response.data
    })
    this.getList()
    
  },
  methods: {
    getList() {
      if (this.teamId) {
        this.loading = true;
        getAssignUser(this.teamId,this.queryParams).then((response) => {
          this.users = response.rows
          this.total = response.total
          this.loading = false;
          console.log(this.total)
        });
      }
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.userIds = selection.map((item) => item.userId);
      this.multiple = !selection.length
    },
    openSelectUser() {
      // if (this.$refs.select) {
      //   this.$refs.select.show();
      // } else {
      //   console.error("selectUser component not found");
      // }
      this.$refs.select.setTeamId(this.$route.params && this.$route.params.teamId)
      this.$refs.select.show();
    },
    /** 取消授权按钮操作 */
    cancelAssignUser(row) {
      const teamId = this.teamId;
      this.$modal.confirm("确认要从'"+ this.team.teamName + "'团队中移除" + row.userName + "'成员吗？").then(function() {
        return assignUserCancel({ userId: row.userId, teamId: teamId });
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("移除用户成功");
      }).catch(() => {});
    },
    cancelAssignUserAll(row){
      const teamId = this.teamId;
      const userIds = this.userIds.join(",");
      this.$modal.confirm("确认要从'"+ this.team.teamName + "'团队中移除选中用户吗？").then(function() {
        return assignUserCancelAll({ teamId: teamId, userIds: userIds });
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("移除用户成功");
      }).catch(() => {});
    }
  }
};
</script>
<style>
.user-avatar {
  cursor: pointer;
  width: 40px;
  height: 40px;
  border-radius: 10px;
}
</style>
