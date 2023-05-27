<template>
  <div>
    <el-button type="success">欢迎使用 yuluo microservice framework</el-button>
    <el-button type="primary" @click="exportLoginInfo">导出系统访问日志</el-button>
    <el-button type="primary" @click="exportOperLog">导出系统操作日志</el-button>
    <el-button type="primary" @click=update()>更新权限写入用户操作日志测试按钮，执行之后请查看数据库日志信息</el-button>
  </div>
</template>

<script setup lang="ts">
import {updateRoleWriteOperLogTestApi} from "../../api/role/role"
import {exportLoginLogInfo, exportOperLogInfo} from "../../api/sys/sys";
import {ElNotification} from "element-plus";
import {h} from "vue";
import {saveAs} from "file-saver";

const update = () => {
  updateRoleWriteOperLogTestApi(null).then((res: any) => {
    if (res.code === 8291) {
      console.log("更新权限写入操作日志测试完成，请到数据库查看操作日志！")
    }
  }).catch((_: any) => {
    console.log("服务内部异常")
  })
}

const exportLoginInfo = () => {
  exportLoginLogInfo(null).then((res: any) => {
    console.log(res)
    const blob = new Blob([res])
    saveAs(blob, `logininfo_${new Date().getTime()}.xlsx`)
  }).catch((_: any) => {
    ElNotification({
      title: 'Error',
      message: h('i', {style: 'color: red'}, '服务器内部异常'),
    })
  })
}

const exportOperLog = () => {
  exportOperLogInfo(null).then((res: any) => {
    const blob = new Blob([res])
    saveAs(blob, `operlog_${new Date().getTime()}.xlsx`)
  }).catch((_: any) => {
    ElNotification({
      title: 'Error',
      message: h('i', {style: 'color: red'}, '服务器内部异常'),
    })
  })
}

</script>

<style lang="scss" scoped>

.el-button:first-child {
  position: absolute;
  top: 35%;
  left: 37.5%;
}

.el-button:nth-child(2) {
  position: absolute;
  top: 43%;
  left: 46%;
}

.el-button:nth-child(3) {
  position: absolute;
  top: 43%;
  left: 36%;
}

.el-button:last-child {
  position: absolute;
  top: 50%;
  left: 32%;
}
</style>
  
  