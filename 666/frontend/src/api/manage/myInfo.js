import request from '@/utils/request'

// 查询我的学生信息
export function getMyStudentInfo() {
    return request({
        url: '/manage/myInfo/student',
        method: 'get'
    })
}

// 查询我的住宿分配信息
export function getMyAllocationInfo() {
    return request({
        url: '/manage/myInfo/allocation',
        method: 'get'
    })
}

// 查询我的完整信息
export function getMyFullInfo() {
    return request({
        url: '/manage/myInfo/full',
        method: 'get'
    })
}