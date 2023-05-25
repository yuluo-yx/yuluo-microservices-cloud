import { get, post, del } from '../axios.ts'

// post
export const userLogin = (data: any) => post('/token/auth/login', data)

export const userLogout = (data: any) => del('/token/auth/logout', data)

// get
export const getUser = (params: any) => post('/token/auth/getUserByUsername', params)

export const updateUser = (params: any) => post('/token/auth/login', params)

// 用户验证码
export const getCode = (params: any) => get("/code", params)