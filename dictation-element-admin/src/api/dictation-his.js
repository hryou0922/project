import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/dictation-his/list',
    method: 'post',
    params: query
  })
}

