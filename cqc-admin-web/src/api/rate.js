import request from '@/utils/request'
export function fetchList(params) {
  return request({
    url:'/rate/list',
    method:'get',
    params:params
  })
}

export function setRate(data) {
  return request({
    url:'/rate/setRate',
    method:'post',
    data:data
  })
}
