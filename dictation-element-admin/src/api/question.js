import request from '@/utils/request'

export function fetchList(data) {
  return request({
    url: '/question/list',
    method: 'post',
    data
  })
}

// export function createTmpWordGroup(data) {
//   return request({
//     url: '/word-group/create-tmp-word-group',
//     method: 'post',
//     data
//   })
// }

export function play(data) {
  return request({
    url: '/question/play',
    method: 'post',
    data
  })
}

export function stop(query) {
  return request({
    url: '/question/stop',
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
