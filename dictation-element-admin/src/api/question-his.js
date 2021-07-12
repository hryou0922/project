import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/question-his/list',
    method: 'post',
    params: query
  })
}

export function fetchListTmp(query) {
  return request({
    url: '/question-his-tmp/list',
    method: 'post',
    params: query
  })
}

export function batchUpdate(data) {
  return request({
    url: '/question-his-tmp/batch_update',
    method: 'post',
    data
  })
}

export function archive(data) {
  return request({
    url: '/question-his-tmp/archive',
    method: 'post',
    data
  })
}

