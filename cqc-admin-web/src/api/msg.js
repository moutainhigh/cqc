import request from '@/utils/request'
export function fetchList(params) {
  return request({
    url:'/message/list',
    method:'get',
    params:params
  })
}

export function delMsg(params) {
  return request({
    url:'/message/del?id='+params.id,
    method:'get'
  })
}

export function add(data) {
  return request({
    url:'/message/add',
    method:'post',
    data: data
  })
}
