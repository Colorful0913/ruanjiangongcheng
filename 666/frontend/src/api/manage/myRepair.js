import request from '@/utils/request'

// 查询我的报修列表
export function listMyRepair(query) {
    return request({
        url: '/manage/myRepair/list',
        method: 'get',
        params: query
    })
}

// 查询我的报修详细
export function getMyRepair(repairId) {
    return request({
        url: '/manage/myRepair/' + repairId,
        method: 'get'
    })
}

// 新增我的报修
export function addMyRepair(data) {
    return request({
        url: '/manage/myRepair',
        method: 'post',
        data: data
    })
}

// 修改我的报修
export function updateMyRepair(data) {
    return request({
        url: '/manage/myRepair',
        method: 'put',
        data: data
    })
}

// 删除我的报修
export function delMyRepair(repairId) {
    return request({
        url: '/manage/myRepair/' + repairId,
        method: 'delete'
    })
}