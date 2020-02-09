import request from '@/utils/request'
export function fetchList(params) {
  return request({
    url:'/notice/list',
    method:'get',
    params:params
  })
}

export function delNotice(id) {
  return request({
    url:'/notice/delete?id='+id,
    method:'get'
  })
}

export function add(data) {
  return request({
    url:'/notice/add',
    method:'post',
    data:data
  })
}

export function edit(data) {
  return request({
    url:'/notice/edit',
    method:'post',
    data:data
  })
}


