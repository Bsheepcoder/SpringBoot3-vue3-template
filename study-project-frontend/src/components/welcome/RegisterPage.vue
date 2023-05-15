

<template>
    <div style="text-align: center;margin: 0 20px">
        <div style="margin-top: 150px">
            <div style="font-size: 25px">注册新用户</div>
            <div style="font-size: 14px">欢迎注册，请填写相关信息</div>
        </div>
        <div style="margin-top: 50px">
            <el-form v-model="form" >
                <el-form-item >
                    <el-input v-model="form.username"  type="text" placeholder="用户名">
                        <template #prefix>
                            <el-icon><User /></el-icon>
                        </template>
                    </el-input>
                </el-form-item>
                <el-form-item>
                    <el-input  v-model="form.password" type="password" style="margin-top: 10px" placeholder="密码">
                        <template #prefix>
                            <el-icon><Lock /></el-icon>
                        </template>
                    </el-input>
                </el-form-item>
                <el-form-item>
                    <el-input  v-model="form.password_repeat"  type="password" style="margin-top: 10px" placeholder="重复输入密码">
                        <template #prefix>
                            <el-icon><Lock /></el-icon>
                        </template>
                    </el-input>
                </el-form-item>
                <el-form-item>
                    <el-input v-model="form.email"  type="email" style="margin-top: 10px" placeholder="邮箱">
                        <template #prefix>
                            <el-icon><Message /></el-icon>
                        </template>
                    </el-input>
                </el-form-item>
                <el-form-item>
                    <el-row :gutter="10" style="display:flow;">
                        <el-col :span="17" style="display: flex">
                            <el-input v-model="form.code" type="text" style="margin-top: 10px" placeholder="验证码">
                                <template #prefix>
                                    <el-icon><EditPen /></el-icon>
                                </template>
                            </el-input>
                        </el-col>
                        <el-col :span="6" style="margin-top: 10px;">
                            <el-button type="success" @click="validateEmail">获取验证码</el-button>
                        </el-col>
                    </el-row>
                </el-form-item>
            </el-form>




        </div>
        <div style="margin-top: 0px">

        </div>
        <div style="margin-top: 30px">
            <el-button style="width: 200px" type="warning">注册</el-button>
        </div>
        <div style="margin-top: 20px">
           <el-text style="align-items: center;" >已有账号？<el-link style="vertical-align: 0;" @click="router.push('/')">立即登录</el-link> </el-text>
        </div>

    </div>
</template>

<script setup>

import router from "@/router";
import {Lock, User, Message, EditPen} from "@element-plus/icons-vue";
import {reactive, ref} from "vue";
import {post} from "@/net";
import {ElMessage} from "element-plus";

const form = reactive(
    {
        username:'',
        password:'',
        password_repeat:'',
        email:'',
        code:''
    }
)
const formRef = ref();

const validateEmail = () =>{
   post('/api/auth/valid-email',{
       email:form.email
   },(message)=>{
       ElMessage.success(message)
   } )
}

</script>



<style scoped>

</style>