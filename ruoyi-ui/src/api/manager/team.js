import request from '@/utils/request'

// 查询团队管理列表
export function listTeam(query) {
  return request({
    url: '/system/team/list',
    method: 'get',
    params: query
  })
}

// 查询团队管理详细
export function getTeam(teamId) {
  return request({
    url: '/system/team/' + teamId,
    method: 'get'
  })
}

// 新增团队管理
export function addTeam(data) {
  return request({
    url: '/system/team',
    method: 'post',
    data: data
  })
}

// 修改团队管理
export function updateTeam(data) {
  return request({
    url: '/system/team',
    method: 'put',
    data: data
  })
}

// 删除团队管理
export function delTeam(teamId) {
  return request({
    url: '/system/team/' + teamId,
    method: 'delete'
  })
}
// 获取已经加入该团队的用户
export function getAssignUser(teamId, params) {
  return request({
    url: '/system/team/assignUser/' + teamId,
    method: 'get',
    params
  })
}

// 获取没有加入该团队的用户

export function getUnassignUser(teamId, params) {
  return request({
    url: '/system/team/unassignUser/' + teamId,
    method: 'get',
    params
  })
}

// 授权用户选择
export function assignUserSelectAll(data) {
  return request({
    url: '/system/team/assignUser/selectAll',
    method: 'put',
    params: data
  })
}

export function assignUserCancel(data){
  return request({
    url: '/system/team/assignUser/cancel',
    method: 'put',
    data: data
  })
}
// 批量取消用户授权角色
export function assignUserCancelAll(data) {
  return request({
    url: '/system/team/assignUser/cancelAll',
    method: 'put',
    params: data
  })
}
