import request from '@/utils/request'
export function fetchList(params) {
  return request({
    url:'/user/list',
    method:'get',
    params:params
  })
}

export function add(data) {
  return request({
    url:'/user/add',
    method:'post',
    data:data
  })
}

export function close(params) {
  return request({
    url:'/user/close',
    method:'get',
    params:params
  })
}

export function open(id) {
  return request({
    url:'/user/open?userId='+id,
    method:'get'
  })
}


export function delUser(ids) {
  return request({
    url:'/user/delete?id='+ids,
    method:'get'
  })
}

export function recharge(data) {
  return request({
    url:'/user/recharge',
    method:'post',
    data:data
  })
}

export function getUserRateList(id) {
  return request({
    url:'/userRate/list?userId='+id,
    method:'get'
  })
}
export function setUserRate(data) {
  return request({
    url:'/userRate/setUserRate',
    method:'post',
    data:data
  })
}



