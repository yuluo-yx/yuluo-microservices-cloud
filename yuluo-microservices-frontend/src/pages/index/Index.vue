<template>
  <div>
    <el-button type="primary">欢迎使用 yuluo microservice framework</el-button>
    <el-button type="primary" @click=update()>更新权限写入用户操作日志测试按钮，执行之后请查看数据库日志信息</el-button>
  </div>
</template>

<script setup lang="ts">
import {updateRoleWriteOperLogTestApi} from "../../api/role/role"

const update = () => {
  updateRoleWriteOperLogTestApi(null).then((res: any) => {
    if (res.code === 8291) {
      console.log("更新权限写入操作日志测试完成，请到数据库查看操作日志！")
    }
  }).catch((_: any) => {
    console.log("服务内部异常")
  })
}

</script>

<style lang="scss" scoped>

.el-button:first-child {
  position: absolute;
  top: 35%;
  left: 42%;
}

.el-button:last-child {
  position: absolute;
  top: 45%;
  left: 36%;
}
</style>
  
  