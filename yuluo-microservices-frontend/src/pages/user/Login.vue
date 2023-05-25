<template>
    <div class="index">
        <el-card shadow="hover" class="login">
            <el-image :src=imageSrc alt="logo" width="40%" />
            <el-form ref="ruleFormRef" :model="ruleForm" status-icon :rules="rules">
                <el-form-item prop="username">
                    <el-input clearable :prefix-icon="User" placeholder="用户名" v-model="ruleForm.username" type="text"
                        autocomplete="off" />
                </el-form-item>
                <el-form-item prop="password">
                    <el-input clearable :prefix-icon="Promotion" placeholder="用户密码" v-model="ruleForm.password"
                        type="password" show-password autocomplete="off" />
                </el-form-item>
                <el-form-item prop="code" type="text">
                    <el-input clearable :prefix-icon="Check" placeholder="验证码" v-model.number="ruleForm.code">
                        <template #append>
                            <img :src=codeSrc>
                            &nbsp;&nbsp;
                            <el-tooltip effect="dark" content="点击刷新验证码" placement="bottom">
                                <el-icon @click="code()">
                                    <Refresh />
                                </el-icon>
                            </el-tooltip>
                        </template>
                    </el-input>
                </el-form-item>
                <el-form-item class="function_btn">
                    <el-button type="primary" @click="login(ruleFormRef)">登录</el-button>
                    <el-button @click="resetForm(ruleFormRef)">重置</el-button>
                </el-form-item>
            </el-form>
        </el-card>
    </div>
</template>

<script setup lang="ts">
import type { FormInstance, FormRules } from 'element-plus'
import { User, Promotion, Refresh, Check } from '@element-plus/icons-vue'
import { ref, reactive, onMounted, onBeforeUnmount } from "vue"
import { useRouter } from "vue-router";
import { ElNotification } from 'element-plus'
import { getCode, userLogin } from "../../api/user/user.ts"
import { h } from 'vue'
import logo from "../../assets/logo.png"

const imageSrc = logo

// 监听回车事件
const onKeyUp = (e: any) => {
    // console.log(e);
    if (e.key === "Enter") {
        onMounted(e);
        // 点击回车后执行登录按钮
        login(ruleFormRef);
    }
}

onMounted(() => {
    // 页面加载之前添加键盘监听
    document.addEventListener("keyup", onKeyUp);
    // 获取验证码
    code()
});

// 页面卸载之前移除键盘监听
onBeforeUnmount(() => {
    document.removeEventListener("keyup", onKeyUp);
});

// 登录验证码部分
const codeSrc = ref<string>('')
const code = () => {
    getCode(null).then((res: any) => {
        // console.log(res)
        if (res.code === 8291) {
            localStorage.setItem("code-uuid", res.data.uuid)
            codeSrc.value = "data:image/png;base64," + res.data.img
        } else {
            ElNotification({
                title: 'Error',
                message: h('i', { style: 'color: red' }, '获取验证码失败'),
            })
        }
    }).catch((_: any) => {
        ElNotification({
            title: 'Error',
            message: h('i', { style: 'color: teal' }, '服务器响应失败'),
        })
    })
}

// 路由声明
const router = useRouter();

// form 表单部分
const ruleFormRef = ref<typeof FormInstance>()

// 表单数据声明
const ruleForm = reactive({
    username: '',
    password: '',
    code: '',
})

const validateUsername = (_: any, value: any, callback: any) => {
    if (value === '') {
        callback(new Error('请输入用户名'))
    } else {
        if (ruleForm.password !== '') {
            if (!ruleFormRef.value) return
            ruleFormRef.value.validateField('validatePassword', () => null)
        }
        callback()
    }
}

const validatePassword = (_: any, value: any, callback: any) => {
    if (value === '') {
        callback(new Error('请输入密码'))
    } else {
        if (ruleForm.password !== '') {
            if (!ruleFormRef.value) return
            ruleFormRef.value.validateField('validatePassword', () => null)
        }
        callback()
    }
}

const rules = reactive<typeof FormRules>({
    username: [{ validator: validateUsername, trigger: 'blur' }],
    password: [{ validator: validatePassword, trigger: 'blur' }],
})

const login = (formEl: typeof FormInstance | undefined) => {

    const codeUUId = localStorage.getItem("code-uuid")
    localStorage.removeItem("code-uuid")

    if (!formEl) return
    formEl.validate((valid: any) => {
        if (valid) {
            userLogin({
                username: ruleForm.username,
                password: ruleForm.password,
                code: ruleForm.code,
                uuid: codeUUId
            }).then((res: any) => {
                if (res.code === 500) {
                  ElNotification({
                    title: 'Error',
                    message: h('i', { style: 'color: teal' }, res.message),
                  })
                } else {
                  if (res.code === 8291) {
                    sessionStorage.setItem("user-token", res.data)
                    router.push("/index")
                  } else {
                    ElNotification({
                      title: 'Error',
                      message: h('i', { style: 'color: teal' }, "服务器内部异常"),
                    })
                  }
                }
            }).catch((_: any) => {
                ElNotification({
                    title: 'Error',
                    message: h('i', { style: 'color: teal' }, '用户登录失败！'),
                })
            })
        } else {
            ElNotification({
                title: 'Error',
                message: h('i', { style: 'color: teal' }, '服务器响应失败！'),
            })
        }
    })
}

const resetForm = (formEl: typeof FormInstance | undefined) => {
    if (!formEl) return
    formEl.resetFields()
}


</script>
  
<style lang="scss" scoped>
* {
    user-select: none;
    /* 无法选中，整体感更强 */
}

.login {
    position: absolute;
    width: 50%;
    height: 50%;
    width: 400px;
    height: 380px;
    border-radius: 25px;
    text-align: center;
    background-color: rgba(0, 0, 0, 0.1)
}

.el-image {
    width: 280px;
    height: 110px;
    margin: auto;
}

.index {
    position: absolute;
    width: 100%;
    height: 100%;
    background-image: url(../../assets/wallpaper.jpg);
    background-size: cover;
    background-attachment: fixed;

    // 子容器 居中
    display: flex;
    align-items: center;
    justify-content: space-around;

}

.el-input {
    margin: 0 auto;
    height: 40px;
    width: 280px;
}

.function_btn {
    margin-top: 25px;
    margin-left: 110px;

    .el-button {
        width: 65px;
        height: 35px;
    }

    .el-button:first-child {
        margin-right: 15px;
    }
}
</style>
