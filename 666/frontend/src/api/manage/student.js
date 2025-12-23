import request from '@/utils/request'

// 查询学生信息扩展列表
export function listStudent(query) {
  return request({
    url: '/manage/student/list',
    method: 'get',
    params: query
  })
}

// 查询学生信息扩展详细
export function getStudent(studentId) {
  return request({
    url: '/manage/student/' + studentId,
    method: 'get'
  })
}

// 新增学生信息扩展
export function addStudent(data) {
  return request({
    url: '/manage/student',
    method: 'post',
    data: data
  })
}

// 修改学生信息扩展
export function updateStudent(data) {
  return request({
    url: '/manage/student',
    method: 'put',
    data: data
  })
}

// 删除学生信息扩展
export function delStudent(studentId) {
  return request({
    url: '/manage/student/' + studentId,
    method: 'delete'
  })
}
