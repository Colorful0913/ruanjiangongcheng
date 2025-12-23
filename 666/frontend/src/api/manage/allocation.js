import request from '@/utils/request'

// 查询住宿分配列表
export function listAllocation(query) {
  return request({
    url: '/manage/allocation/list',
    method: 'get',
    params: query
  })
}

// 查询住宿分配详细
export function getAllocation(allocationId) {
  return request({
    url: '/manage/allocation/' + allocationId,
    method: 'get'
  })
}

// 新增住宿分配
export function addAllocation(data) {
  return request({
    url: '/manage/allocation',
    method: 'post',
    data: data
  })
}

// 修改住宿分配
export function updateAllocation(data) {
  return request({
    url: '/manage/allocation',
    method: 'put',
    data: data
  })
}

// 删除住宿分配
export function delAllocation(allocationId) {
  return request({
    url: '/manage/allocation/' + allocationId,
    method: 'delete'
  })
}
