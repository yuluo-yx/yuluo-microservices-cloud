import { put } from '../axios.ts'

export const updateRoleWriteOperLogTestApi =(data: any) =>put(`/sys/role/updateTest`, data)