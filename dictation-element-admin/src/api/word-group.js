import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/word-group/list',
    method: 'post',
    params: query
  })
}

export function deleteWordGroup(data) {
  return request({
    url: '/word-group/delete-word-group',
    method: 'post',
    data
  })
}

export function fetchWordList(data) {
  return request({
    url: '/word-group/word-list',
    method: 'post',
    data
  })
}

export function play(data) {
  return request({
    url: '/word-group/play',
    method: 'post',
    data
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
