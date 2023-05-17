

<template>
    <div style="text-align: center;margin: 0 20px">
        <div style="margin-top: 70px">
            <div style="font-size: 25px">注册新用户</div>
            <div style="font-size: 14px">欢迎注册，请填写相关信息</div>
        </div>
        <div style="margin-top: 50px">
            <el-form  :model="form" :rules="rules" @validate="onValidate" ref="formRef">
                <el-form-item prop="username">
                    <el-input v-model="form.username" :maxlength="8" type="text" placeholder="用户名">
                        <template #prefix>
                            <el-icon><User /></el-icon>
                        </template>
                    </el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input  v-model="form.password" :maxlength="16" type="password" placeholder="密码">
                        <template #prefix>
                            <el-icon><Lock /></el-icon>
                        </template>
                    </el-input>
                </el-form-item>
                <el-form-item prop="password_repeat">
                    <el-input  v-model="form.password_repeat"  type="password" placeholder="重复输入密码">
                        <template #prefix>
                            <el-icon><Lock /></el-icon>
                        </template>
                    </el-input>
                </el-form-item>
                <el-form-item prop="email">
                    <el-input v-model="form.email"  type="email" placeholder="邮箱">
                        <template #prefix>
                            <el-icon><Message /></el-icon>
                        </template>
                    </el-input>
                </el-form-item>
                <el-form-item prop="code">
                    <el-row :gutter="10" style="display:flow; width:100%;">
                        <el-col :span="17" style="display: flex">
                            <el-input v-model="form.code" type="text" :maxlength="6" placeholder="验证码">
                                <template #prefix>
                                    <el-icon><EditPen /></el-icon>
                                </template>
                            </el-input>
                        </el-col>
                        <el-col :span="6" style="margin-top: 10px;">
                            <el-button type="success" @click="validateEmail" 
                                       :disabled="!isEmailValid|| coldTime>0">
                                       {{ coldTime > 0 ? '请稍后'+coldTime+'秒':'获取验证码' }}
                                       </el-button>
                        </el-col>
                    </el-row>
                </el-form-item>
            </el-form>
        </div>
        <div style="margin-top: 0px">

        </div>
        <div style="margin-top: 30px">
            <el-button style="width: 200px" type="warning" @click="register">注册</el-button>
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
const validateUsername = (rule, value , callback) => {
  if (value === '') {
    callback(new Error('请输入用户名:'))
  } else if(!/^[a-zA-Z0-9\u4e00-\u9fa5]+$/.test(value)) {
    callback(new Error('用户名不能包含特殊字符，只能是中文、英文')) 
  }else{
    callback()
  }
}
const validatePassword = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('请再次输入密码:'))
  } else if (value !== form.password) {
    callback(new Error("两次输入的密码不一致"))
  } else {
    callback()
  }
}
const rules = {
    username:[
        { validator:validateUsername, trigger: ['blur','change'] },
        { min: 2, max: 10, message: '用户名长度必须在2-10个字符之间', trigger: ['blur','change'] },
    ],
    password:[
        {required: true,message: '请输入密码',tigger: 'blur'},
        { min: 6, max: 16, message: '密码长度必须在6-16个字符之间', trigger: ['blur','change'] },
    ],
    password_repeat:[
        { validator:validatePassword, trigger: ['blur','change'] },
    ],
    email:[
        {required: true,message: '请输入邮件地址',tigger: 'blur'},
        {type:'email',message: '请输入合法的邮件地址',trigger: ['blur', 'change'],}
    ],
    code:[
        {required: true,message: '请输入获取的验证码',tigger: 'blur'},
    ]
}
const formRef = ref()
const isEmailValid = ref('false')
const isSendEmail = ref(false)
const coldTime = ref(0)
const onValidate = (prop,isValid)=>{
    if(prop==='email')
        isEmailValid.value = isValid
}
const register = () => {
    formRef.value.validate((isValid)=>{
        if(isValid){

        }else{
            ElMessage.warning('请完整填写上述表单注册内容')
        }
    })
}
const validateEmail = () =>{
   post('/api/auth/valid-email',{
       email:form.email
   },(message)=>{
       ElMessage.success(message)
       coldTime.value = 60
       setInterval(()=>coldTime.value--,1000)
   } )
}
</script>



<style scoped>

</style>