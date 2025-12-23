import request from '@/utils/request'

// 查询维修报修列表
export function listRepair(query) {
  return request({
    url: '/manage/repair/list',
    method: 'get',
    params: query
  })
}

// 查询维修报修详细
export function getRepair(repairId) {
  return request({
    url: '/manage/repair/' + repairId,
    method: 'get'
  })
}

// 新增维修报修
export function addRepair(data) {
  return request({
    url: '/manage/repair',
    method: 'post',
    data: data
  })
}

// 修改维修报修
export function updateRepair(data) {
  return request({
    url: '/manage/repair',
    method: 'put',
    data: data
  })
}

// 删除维修报修
export function delRepair(repairId) {
  return request({
    url: '/manage/repair/' + repairId,
    method: 'delete'
  })
}
