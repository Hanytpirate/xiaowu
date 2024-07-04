import request from '@/utils/request'

// 查询团队管理列表
export function listTeam(query) {
  return request({
    url: '/manager/team/list',
    method: 'get',
    params: query
  })
}

// 查询团队管理详细
export function getTeam(teamId) {
  return request({
    url: '/manager/team/' + teamId,
    method: 'get'
  })
}

// 新增团队管理
export function addTeam(data) {
  return request({
    url: '/manager/team',
    method: 'post',
    data: data
  })
}

// 修改团队管理
export function updateTeam(data) {
  return request({
    url: '/manager/team',
    method: 'put',
    data: data
  })
}

// 删除团队管理
export function delTeam(teamId) {
  return request({
    url: '/manager/team/' + teamId,
    method: 'delete'
  })
}

