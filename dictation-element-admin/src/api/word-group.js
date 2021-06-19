import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/word-group/list',
    method: 'post',
    params: query
  })
}

export function fetchWordList(query) {
  return request({
    url: '/word-group/word-list',
    method: 'post',
    params: query
  })
}

export function play(query) {
  return request({
    url: '/word-group/play',
    method: 'post',
    params: query
  })
}

export function stop(query) {
  return request({
    url: '/word-group/stop',
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
