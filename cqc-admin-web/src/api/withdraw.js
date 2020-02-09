import request from '@/utils/request'
export function fetchList(params) {
  return request({
    url:'/withDraw/list',
    method:'get',
    params:params
  })
}

export function back(id) {
  return request({
    url:'/withDraw/back?id='+id,
    method:'get'
  })
}

export function confirm(id) {
  return request({
    url:'/withDraw/confirm?id='+id,
    method:'get'
  })
}
