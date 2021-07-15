import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/question-group/list',
    method: 'post',
    params: query
  })
}

export function deleteQuestionGroup(data) {
  return request({
    url: '/question-group/delete-question-group',
    method: 'post',
    data
  })
}

export function fetchQuestionList(data) {
  return request({
    url: '/question-group/question-list',
    method: 'post',
    data
  })
}

export function play(data) {
  return request({
    url: '/question-group/play',
    method: 'post',
    data
  })
}

export function stop(query) {
  return request({
    url: '/question-group/stop',
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
