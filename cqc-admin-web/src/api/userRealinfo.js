import request from '@/utils/request'
export function fetchList(params) {
  return request({
    url:'/userRealInfo/list',
    method:'get',
    params:params
  })
}

export function detail(id) {
  return request({
    url:'/userRealInfo/detail?id='+id,
    method:'get'
  })
}

export function audit(params) {
  return request({
    url:'/userRealInfo/audit',
    method:'get',
    params: params
  })
}

