import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/word/list',
    method: 'post',
    params: query
  })
}

// export function fetchList(token) {
//   return request({
//     url: '/user/info',
//     method: 'get',
//     params: { token }
//   })
// }
