import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/word/list',
    method: 'get',
    params: query
  })
}


export function searchUser(name) {
  return request({
    url: '/vue-element-admin/search/user',
    method: 'get',
    params: { name }
  })
}
