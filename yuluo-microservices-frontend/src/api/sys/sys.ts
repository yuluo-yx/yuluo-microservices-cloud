import { download } from '../axios.ts'

export const exportLoginLogInfo = (data: any) => download("/sys/loginInfo/export", data)

export const exportOperLogInfo = (data: any) => download("/sys/operlog/export", data)
