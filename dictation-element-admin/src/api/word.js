import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/word/list',
    method: 'post',
    params: query
  })
}

export function play(query) {
  return request({
    url: '/word/play',
    method: 'post',
    params: query
  })
}

export function stop(query) {
  return request({
    url: '/word/stop',
    method: 'get',
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
