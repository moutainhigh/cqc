import request from '@/utils/request'
export function fetchList(params) {
  return request({
    url:'/orderPublish/list',
    method:'get',
    params:params
  })
}

export function push(data) {
  return request({
    url:'/orderPublish/push',
    method:'post',
    data:data
  })
}
