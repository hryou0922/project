import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/dictation-his-tmp/list',
    method: 'post',
    params: query
  })
}

export function batchUpdate(data) {
  return request({
    url: '/dictation-his-tmp/batch_update',
    method: 'post',
    data
  })
}

export function archive(data) {
  return request({
    url: '/dictation-his-tmp/archive',
    method: 'post',
    data
  })
}

