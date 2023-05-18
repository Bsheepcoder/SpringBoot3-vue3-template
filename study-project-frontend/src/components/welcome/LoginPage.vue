<template>
    <div style="text-align: center;margin: 0 20px">
        <div style="margin-top: 130px">
            <div style="font-size: 25px">登录</div>
            <div style="font-size: 14px">在进入系统之前请先输入用户名和密码进行登录</div>
        </div>
        <div style="margin-top: 50px">
            <el-input v-model="form.username" type="text" placeholder="用户名">
                <template #prefix>
                    <el-icon><User /></el-icon>
                </template>
            </el-input>
            <el-input v-model="form.password" type="password" style="margin-top: 10px" placeholder="密码">
                <template #prefix>
                    <el-icon><Lock /></el-icon>
                </template>
            </el-input>
        </div>
        <div style="margin-top: 5px">
            <el-row style="display: flex;justify-items: center ;align-items: center">
                <el-col :span="12">
                    <el-checkbox v-model="form.remember" label="记住我"/>
                </el-col>
                <el-col :span="12">
                    <el-link>忘记密码?</el-link>
                </el-col>
            </el-row>
        </div>
        <div style="margin-top: 30px">
            <el-button @click="login()"  style="width: 270px"  type="success"  plain>立即登录</el-button>
        </div>

        <el-divider>
            <span style="color:fuchsia">没有账户</span>
        </el-divider>
        <div style="margin-top: 30px">
            <el-button @click="router.push('register')" style="width: 270px" type="warning"  plain>注册账户</el-button>
        </div>
    </div>
</template>

 
<style>
/*alert 成功弹出框样式*/
</style>

<script setup>
import {Lock, User} from "@element-plus/icons-vue";
import {reactive} from "vue";
import {ElMessage} from "element-plus";
import router from "@/router";
import {post} from "@/net";

const form = reactive({
    username:'',
    password:'',
    remember:false
})
const login = () =>{  
    if(!form.username || !form.password){ 
        ElMessage.warning('请填写用户名和密码！')
    }else{
        post('/api/auth/login',{
            username:form.username,
            password:form.password,
            remember:form.remember
        }, (message)=>{
            ElMessage.success(message)
            router.push('/index')
        })
    }
}
</script>